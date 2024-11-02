package com.web.gallary.controller.integration;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.web.gallary.AccountPrincipal;
import com.web.gallary.controller.request.AccountRegistRequest;
import com.web.gallary.controller.request.AccountUpdateRequest;
import com.web.gallary.entity.Account;
import com.web.gallary.enumuration.ErrorValues;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class AccountRestControllerIntegrationTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Nested
	@Order(1)
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/controller/AccountRestControllerIntegrationTest.sql")
	class register {
		private List<Account> getAccountList(String accountId) {
			return jdbcTemplate.query(
					"SELECT * FROM common.account where account_id='" + accountId + "'", (rs, rowNum) ->
					Account.builder()
						.accountNo(rs.getInt("account_no"))
						.createdBy(rs.getInt("created_by"))
						.createdAt(rs.getObject("created_at", OffsetDateTime.class))
						.updatedBy(rs.getInt("updated_by"))
						.updatedAt(rs.getObject("updated_at", OffsetDateTime.class))
						.isDeleted(rs.getBoolean("is_deleted"))
						.accountId(rs.getString("account_id"))
						.accountName(rs.getString("account_name"))
						.password(rs.getString("password"))
						.birthdate(rs.getObject("birthdate", LocalDate.class))
						.sexKbnCode(rs.getString("sex_kbn_code"))
						.birthplacePrefectureKbnCode(rs.getString("birthplace_prefecture_kbn_code"))
						.residentPrefectureKbnCode(rs.getString("resident_prefecture_kbn_code"))
						.freeMemo(rs.getString("free_memo"))
						.authorityKbnCode(rs.getString("authority_kbn_code"))
						.lastLoginDatetime(rs.getObject("last_login_datetime", OffsetDateTime.class))
						.loginFailureCount(rs.getInt("login_failure_count"))
						.build());
		}
		
		@Test
		@Order(1)
		@DisplayName("正常系")
		void register_regist_success() throws JsonProcessingException, Exception {
			String accountId = "dddddddd";
			String accountName = "DDDDDDDD";
			String password = "password04";
			LocalDate birthDate = LocalDate.of(2000, 1, 1);
			String sexKbnCode = "woman";
			String birthplacePrefectureKbnCode = "Hokkaido";
			String residentPrefectureKbnCode = "Okinawa";
			String freeMemo = "フリーメモ";
			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			
			AccountRegistRequest request = new AccountRegistRequest();
			request.setAccountId(accountId);
			request.setAccountName(accountName);
			request.setPassword(password);
			request.setBirthdate(birthDate);
			request.setSexKbnCode(sexKbnCode);
			request.setBirthplacePrefectureKbnCode(birthplacePrefectureKbnCode);
			request.setResidentPrefectureKbnCode(residentPrefectureKbnCode);
			request.setFreeMemo(freeMemo);
			
			mockMvc.perform(
					post("/register")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request))
					.with(csrf())
				)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.httpStatus").value(200))
				.andExpect(jsonPath("$.isSuccess").value(true))
				.andExpect(jsonPath("$.message").value(""));
			
			List<Account> actualData = getAccountList(accountId);
			
			assertEquals(1, actualData.size());
			assertEquals(4, actualData.getFirst().getAccountNo());
			assertEquals(0, actualData.getFirst().getCreatedBy());
			assertEquals(0, actualData.getFirst().getUpdatedBy());
			assertFalse(actualData.getFirst().getIsDeleted());
			assertEquals(accountId, actualData.getFirst().getAccountId());
			assertEquals(accountName, actualData.getFirst().getAccountName());
			assertEquals(birthDate, actualData.getFirst().getBirthdate());
			assertEquals(sexKbnCode, actualData.getFirst().getSexKbnCode());
			assertEquals(birthplacePrefectureKbnCode, actualData.getFirst().getBirthplacePrefectureKbnCode());
			assertEquals(residentPrefectureKbnCode, actualData.getFirst().getResidentPrefectureKbnCode());
			assertEquals(freeMemo, actualData.getFirst().getFreeMemo());
			assertEquals("mini-user", actualData.getFirst().getAuthorityKbnCode());
			assertEquals(OffsetDateTime.of(1900, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)), actualData.getFirst().getLastLoginDatetime().plusHours(9));
			assertEquals(0, actualData.getFirst().getLoginFailureCount());
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：既に使われているアカウントIDの場合")
		void register_exist_accountId() throws JsonProcessingException, Exception {
			String accountId = "aaaaaaaa";
			String accountName = "DDDDDDDD";
			String password = "password04";
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			AccountRegistRequest request = new AccountRegistRequest();
			request.setAccountId(accountId);
			request.setAccountName(accountName);
			request.setPassword(password);
			
			mockMvc.perform(
					post("/register")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request))
					.with(csrf())
				)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.httpStatus").value(200))
				.andExpect(jsonPath("$.isSuccess").value(false))
				.andExpect(jsonPath("$.message").value(""));
			
			List<Account> actualData = getAccountList(accountId);
			assertEquals(1, actualData.size());
			assertEquals(accountId, actualData.getFirst().getAccountId());
			assertEquals("AAAAAAAA", actualData.getFirst().getAccountName());
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：BadRequestExceptionをthrowする")
		void account_setting_BadRequestException_accountId_is_blank() throws JsonProcessingException, Exception {
			String accountId = "";
			String accountName = "DDDDDDDD";
			String password = "password04";
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			AccountRegistRequest request = new AccountRegistRequest();
			request.setAccountId(accountId);
			request.setAccountName(accountName);
			request.setPassword(password);
			
			mockMvc.perform(
					post("/register")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request))
					.with(csrf())
				)
				.andExpect(status().isBadRequest())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.httpStatus").value(HttpStatus.BAD_REQUEST.value()))
				.andExpect(jsonPath("$.isSuccess").value(false))
				.andExpect(jsonPath("$.message").value(ErrorValues.EC0000.getErrorMessage()));
		}
	}
	
	@Nested
	@Order(2)
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/controller/AccountRestControllerIntegrationTest.sql")
	class update {
		private List<Account> getAccountList(String accountId) {
			return jdbcTemplate.query(
					"SELECT * FROM common.account where account_id='" + accountId + "'", (rs, rowNum) ->
					Account.builder()
						.accountNo(rs.getInt("account_no"))
						.createdBy(rs.getInt("created_by"))
						.createdAt(rs.getObject("created_at", OffsetDateTime.class))
						.updatedBy(rs.getInt("updated_by"))
						.updatedAt(rs.getObject("updated_at", OffsetDateTime.class))
						.isDeleted(rs.getBoolean("is_deleted"))
						.accountId(rs.getString("account_id"))
						.accountName(rs.getString("account_name"))
						.password(rs.getString("password"))
						.birthdate(rs.getObject("birthdate", LocalDate.class))
						.sexKbnCode(rs.getString("sex_kbn_code"))
						.birthplacePrefectureKbnCode(rs.getString("birthplace_prefecture_kbn_code"))
						.residentPrefectureKbnCode(rs.getString("resident_prefecture_kbn_code"))
						.freeMemo(rs.getString("free_memo"))
						.authorityKbnCode(rs.getString("authority_kbn_code"))
						.lastLoginDatetime(rs.getObject("last_login_datetime", OffsetDateTime.class))
						.loginFailureCount(rs.getInt("login_failure_count"))
						.build());
		}
		
		@Test
		@Order(1)
		@DisplayName("正常系：アカウントID、パスワード変更なし")
		void update_not_change_accountID_and_password() throws JsonProcessingException, Exception {
			String accountId = "aaaaaaaa";
			String accountName = "AAAAAAAA";
			String password = "";
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			AccountUpdateRequest request = new AccountUpdateRequest();
			request.setAccountId(accountId);
			request.setAccountName(accountName);
			request.setNewPassword(password);
			
			Account sessionAccount = Account.builder()
					.accountNo(1)
					.accountId(accountId)
					.accountName(accountName)
					.password("$2a$10$password1")
					.authorityKbnCode("administrator")
					.build();
			
			AccountPrincipal accountPrincipal = new AccountPrincipal(sessionAccount, 0);
			Authentication authentication = new UsernamePasswordAuthenticationToken(accountPrincipal, null);
			
			mockMvc.perform(
					post("/update")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request))
					.with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
					.with(csrf())
				)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.httpStatus").value(HttpStatus.OK.value()))
				.andExpect(jsonPath("$.isDuplicateAccountId").value(false))
				.andExpect(jsonPath("$.isAccountIdChanged").value(false))
				.andExpect(jsonPath("$.isPasswordChanged").value(false))
				.andExpect(jsonPath("$.message").value(""));
			
			List<Account> actual = getAccountList(accountId);
			assertEquals(1, actual.size());
			assertEquals(1, actual.getFirst().getAccountNo());
			assertEquals(1, actual.getFirst().getCreatedBy());
			assertNotEquals(OffsetDateTime.of(2001, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)), actual.getFirst().getCreatedAt());
			assertEquals(1, actual.getFirst().getUpdatedBy());
			assertFalse(actual.getFirst().getIsDeleted());
			assertEquals(accountId, actual.getFirst().getAccountId());
			assertEquals(accountName, actual.getFirst().getAccountName());
			assertEquals("$2a$10$password1", actual.getFirst().getPassword());
			assertEquals(LocalDate.of(1900, 1, 1), actual.getFirst().getBirthdate());
			assertEquals("none", actual.getFirst().getSexKbnCode());
			assertEquals("none", actual.getFirst().getBirthplacePrefectureKbnCode());
			assertEquals("none", actual.getFirst().getResidentPrefectureKbnCode());
			assertEquals("", actual.getFirst().getFreeMemo());
			assertEquals("administrator", actual.getFirst().getAuthorityKbnCode());
			assertEquals(OffsetDateTime.of(1900, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)), actual.getFirst().getLastLoginDatetime().plusHours(9));
			assertEquals(0, actual.getFirst().getLoginFailureCount());
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：アカウントID変更あり、パスワード変更なし")
		void update_change_accountID() throws JsonProcessingException, Exception {
			String accountId = "aaaaaaaab";
			String accountName = "AAAAAAAA";
			String password = "";
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			AccountUpdateRequest request = new AccountUpdateRequest();
			request.setAccountId(accountId);
			request.setAccountName(accountName);
			request.setNewPassword(password);
			
			Account sessionAccount = Account.builder()
					.accountNo(1)
					.accountId("aaaaaaaa")
					.accountName(accountName)
					.password("$2a$10$password1")
					.authorityKbnCode("administrator")
					.build();
			
			AccountPrincipal accountPrincipal = new AccountPrincipal(sessionAccount, 0);
			Authentication authentication = new UsernamePasswordAuthenticationToken(accountPrincipal, null);
			
			mockMvc.perform(
					post("/update")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request))
					.with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
					.with(csrf())
				)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.httpStatus").value(HttpStatus.OK.value()))
				.andExpect(jsonPath("$.isDuplicateAccountId").value(false))
				.andExpect(jsonPath("$.isAccountIdChanged").value(true))
				.andExpect(jsonPath("$.isPasswordChanged").value(false))
				.andExpect(jsonPath("$.message").value(""));
			
			List<Account> actual = getAccountList(accountId);
			assertEquals(1, actual.size());
			assertEquals(1, actual.getFirst().getAccountNo());
			assertEquals(1, actual.getFirst().getCreatedBy());
			assertNotEquals(OffsetDateTime.of(2001, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)), actual.getFirst().getCreatedAt());
			assertEquals(1, actual.getFirst().getUpdatedBy());
			assertFalse(actual.getFirst().getIsDeleted());
			assertEquals(accountId, actual.getFirst().getAccountId());
			assertEquals(accountName, actual.getFirst().getAccountName());
			assertEquals("$2a$10$password1", actual.getFirst().getPassword());
			assertEquals(LocalDate.of(1900, 1, 1), actual.getFirst().getBirthdate());
			assertEquals("none", actual.getFirst().getSexKbnCode());
			assertEquals("none", actual.getFirst().getBirthplacePrefectureKbnCode());
			assertEquals("none", actual.getFirst().getResidentPrefectureKbnCode());
			assertEquals("", actual.getFirst().getFreeMemo());
			assertEquals("administrator", actual.getFirst().getAuthorityKbnCode());
			assertEquals(OffsetDateTime.of(1900, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)), actual.getFirst().getLastLoginDatetime().plusHours(9));
			assertEquals(0, actual.getFirst().getLoginFailureCount());
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：アカウントID変更なし、パスワード変更あり")
		void update_change_password() throws JsonProcessingException, Exception {
			String accountId = "aaaaaaaa";
			String accountName = "AAAAAAAA";
			String password = "password111";
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			AccountUpdateRequest request = new AccountUpdateRequest();
			request.setAccountId(accountId);
			request.setAccountName(accountName);
			request.setNewPassword(password);
			
			Account sessionAccount = Account.builder()
					.accountNo(1)
					.accountId(accountId)
					.accountName(accountName)
					.password("$2a$10$password1")
					.authorityKbnCode("administrator")
					.build();
			
			AccountPrincipal accountPrincipal = new AccountPrincipal(sessionAccount, 0);
			Authentication authentication = new UsernamePasswordAuthenticationToken(accountPrincipal, null);
			
			mockMvc.perform(
					post("/update")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request))
					.with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
					.with(csrf())
				)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.httpStatus").value(HttpStatus.OK.value()))
				.andExpect(jsonPath("$.isDuplicateAccountId").value(false))
				.andExpect(jsonPath("$.isAccountIdChanged").value(false))
				.andExpect(jsonPath("$.isPasswordChanged").value(true))
				.andExpect(jsonPath("$.message").value(""));
			
			List<Account> actual = getAccountList(accountId);
			assertEquals(1, actual.size());
			assertEquals(1, actual.getFirst().getAccountNo());
			assertEquals(1, actual.getFirst().getCreatedBy());
			assertNotEquals(OffsetDateTime.of(2001, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)), actual.getFirst().getCreatedAt());
			assertEquals(1, actual.getFirst().getUpdatedBy());
			assertFalse(actual.getFirst().getIsDeleted());
			assertEquals(accountId, actual.getFirst().getAccountId());
			assertEquals(accountName, actual.getFirst().getAccountName());
			assertNotEquals("$2a$10$password1", actual.getFirst().getPassword());
			assertEquals(LocalDate.of(1900, 1, 1), actual.getFirst().getBirthdate());
			assertEquals("none", actual.getFirst().getSexKbnCode());
			assertEquals("none", actual.getFirst().getBirthplacePrefectureKbnCode());
			assertEquals("none", actual.getFirst().getResidentPrefectureKbnCode());
			assertEquals("", actual.getFirst().getFreeMemo());
			assertEquals("administrator", actual.getFirst().getAuthorityKbnCode());
			assertEquals(OffsetDateTime.of(1900, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)), actual.getFirst().getLastLoginDatetime().plusHours(9));
			assertEquals(0, actual.getFirst().getLoginFailureCount());
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：アカウントID変更あり、パスワード変更あり")
		void update_change_accountId_and_password() throws JsonProcessingException, Exception {
			String accountId = "aaaaaaaab";
			String accountName = "AAAAAAAA";
			String password = "password111";
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			AccountUpdateRequest request = new AccountUpdateRequest();
			request.setAccountId(accountId);
			request.setAccountName(accountName);
			request.setNewPassword(password);
			
			Account sessionAccount = Account.builder()
					.accountNo(1)
					.accountId("aaaaaaaa")
					.accountName(accountName)
					.password("$2a$10$password1")
					.authorityKbnCode("administrator")
					.build();
			
			AccountPrincipal accountPrincipal = new AccountPrincipal(sessionAccount, 0);
			Authentication authentication = new UsernamePasswordAuthenticationToken(accountPrincipal, null);
			
			mockMvc.perform(
					post("/update")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request))
					.with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
					.with(csrf())
				)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.httpStatus").value(HttpStatus.OK.value()))
				.andExpect(jsonPath("$.isDuplicateAccountId").value(false))
				.andExpect(jsonPath("$.isAccountIdChanged").value(true))
				.andExpect(jsonPath("$.isPasswordChanged").value(true))
				.andExpect(jsonPath("$.message").value(""));
			
			List<Account> actual = getAccountList(accountId);
			assertEquals(1, actual.size());
			assertEquals(1, actual.getFirst().getAccountNo());
			assertEquals(1, actual.getFirst().getCreatedBy());
			assertNotEquals(OffsetDateTime.of(2001, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)), actual.getFirst().getCreatedAt());
			assertEquals(1, actual.getFirst().getUpdatedBy());
			assertFalse(actual.getFirst().getIsDeleted());
			assertEquals(accountId, actual.getFirst().getAccountId());
			assertEquals(accountName, actual.getFirst().getAccountName());
			assertNotEquals("$2a$10$password1", actual.getFirst().getPassword());
			assertEquals(LocalDate.of(1900, 1, 1), actual.getFirst().getBirthdate());
			assertEquals("none", actual.getFirst().getSexKbnCode());
			assertEquals("none", actual.getFirst().getBirthplacePrefectureKbnCode());
			assertEquals("none", actual.getFirst().getResidentPrefectureKbnCode());
			assertEquals("", actual.getFirst().getFreeMemo());
			assertEquals("administrator", actual.getFirst().getAuthorityKbnCode());
			assertEquals(OffsetDateTime.of(1900, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)), actual.getFirst().getLastLoginDatetime().plusHours(9));
			assertEquals(0, actual.getFirst().getLoginFailureCount());
		}
		
		@Test
		@Order(5)
		@DisplayName("正常系：アカウントID重複")
		void update_duplicate_accountId() throws JsonProcessingException, Exception {
			String accountId = "bbbbbbbb";
			String accountName = "AAAAAAAA";
			String password = "";
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			AccountUpdateRequest request = new AccountUpdateRequest();
			request.setAccountId(accountId);
			request.setAccountName(accountName);
			request.setNewPassword(password);
			
			Account sessionAccount = Account.builder()
					.accountNo(1)
					.accountId("aaaaaaaa")
					.accountName(accountName)
					.password("$2a$10$password1")
					.authorityKbnCode("administrator")
					.build();
			
			AccountPrincipal accountPrincipal = new AccountPrincipal(sessionAccount, 0);
			Authentication authentication = new UsernamePasswordAuthenticationToken(accountPrincipal, null);
			
			mockMvc.perform(
					post("/update")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request))
					.with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
					.with(csrf())
				)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.httpStatus").value(HttpStatus.OK.value()))
				.andExpect(jsonPath("$.isDuplicateAccountId").value(true))
				.andExpect(jsonPath("$.isAccountIdChanged").value(true))
				.andExpect(jsonPath("$.isPasswordChanged").value(false))
				.andExpect(jsonPath("$.message").value(""));
		}
		
		@Test
		@Order(6)
		@DisplayName("異常系：パスワード変更なしで、パスワード以外のパラメータが不正")
		void update_BadRequestException_account_id() throws JsonProcessingException, Exception {
			String accountId = "";
			String accountName = "AAAAAAAA";
			String password = "";
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			AccountUpdateRequest request = new AccountUpdateRequest();
			request.setAccountId(accountId);
			request.setAccountName(accountName);
			request.setNewPassword(password);
			
			Account sessionAccount = Account.builder()
					.accountNo(1)
					.accountId("aaaaaaaa")
					.accountName(accountName)
					.password("$2a$10$password1")
					.authorityKbnCode("administrator")
					.build();
			
			AccountPrincipal accountPrincipal = new AccountPrincipal(sessionAccount, 0);
			Authentication authentication = new UsernamePasswordAuthenticationToken(accountPrincipal, null);
			
			mockMvc.perform(
					post("/update")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request))
					.with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
					.with(csrf())
				)
				.andExpect(status().isBadRequest());
		}
		
		@Test
		@Order(7)
		@DisplayName("異常系：パスワード変更ありで不正でなく、パスワード以外のパラメータが不正")
		void update_BadRequestException_account_id_with_change_password() throws JsonProcessingException, Exception {
			String accountId = "";
			String accountName = "AAAAAAAA";
			String password = "pasword111";
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			AccountUpdateRequest request = new AccountUpdateRequest();
			request.setAccountId(accountId);
			request.setAccountName(accountName);
			request.setNewPassword(password);
			
			Account sessionAccount = Account.builder()
					.accountNo(1)
					.accountId("aaaaaaaa")
					.accountName(accountName)
					.password("$2a$10$password1")
					.authorityKbnCode("administrator")
					.build();
			
			AccountPrincipal accountPrincipal = new AccountPrincipal(sessionAccount, 0);
			Authentication authentication = new UsernamePasswordAuthenticationToken(accountPrincipal, null);
			
			mockMvc.perform(
					post("/update")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request))
					.with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
					.with(csrf())
				)
				.andExpect(status().isBadRequest());
		}
		
		@Test
		@Order(8)
		@DisplayName("異常系：パスワード変更ありで、パスワードが不正")
		void update_BadRequestException_password() throws JsonProcessingException, Exception {
			String accountId = "aaaaaaaa";
			String accountName = "AAAAAAAA";
			String password = "$$$$$12345";
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			AccountUpdateRequest request = new AccountUpdateRequest();
			request.setAccountId(accountId);
			request.setAccountName(accountName);
			request.setNewPassword(password);
			
			Account sessionAccount = Account.builder()
					.accountNo(1)
					.accountId("aaaaaaaa")
					.accountName(accountName)
					.password("$2a$10$password1")
					.authorityKbnCode("administrator")
					.build();
			
			AccountPrincipal accountPrincipal = new AccountPrincipal(sessionAccount, 0);
			Authentication authentication = new UsernamePasswordAuthenticationToken(accountPrincipal, null);
			
			mockMvc.perform(
					post("/update")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request))
					.with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
					.with(csrf())
				)
				.andExpect(status().isBadRequest());
		}
		
		@Test
		@Order(9)
		@DisplayName("異常系：UpdateFailureExceptionをthrowする")
		void update_UpdateFailureException() throws JsonProcessingException, Exception {
			String accountId = "zzzzzzzz";
			String accountName = "AAAAAAAA";
			String password = "";
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			AccountUpdateRequest request = new AccountUpdateRequest();
			request.setAccountId(accountId);
			request.setAccountName(accountName);
			request.setNewPassword(password);
			
			Account sessionAccount = Account.builder()
					.accountNo(9)
					.accountId(accountId)
					.accountName(accountName)
					.password("$2a$10$password1")
					.authorityKbnCode("administrator")
					.build();
			
			AccountPrincipal accountPrincipal = new AccountPrincipal(sessionAccount, 0);
			Authentication authentication = new UsernamePasswordAuthenticationToken(accountPrincipal, null);
			
			mockMvc.perform(
					post("/update")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request))
					.with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
					.with(csrf())
				)
				.andExpect(status().isConflict())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.httpStatus").value(HttpStatus.CONFLICT.value()))
				.andExpect(jsonPath("$.errorCode").value(ErrorValues.EC0002.getErrorCode()))
				.andExpect(jsonPath("$.errorMessage").value(ErrorValues.EC0002.getErrorMessage()))
				.andExpect(jsonPath("$.goBackPageUrl").value("/photo/zzzzzzzz/photo_list"));
		}
	}
}