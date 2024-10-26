package com.web.gallary.repository.impl.integration;

import static org.junit.jupiter.api.Assertions.*;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.web.gallary.entity.Account;
import com.web.gallary.exception.RegistFailureException;
import com.web.gallary.exception.UpdateFailureException;
import com.web.gallary.model.AccountModel;
import com.web.gallary.repository.impl.AccountRepositoryImpl;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
@Sql("/sql/common/ResetAccountNoSeq.sql")
public class AccountRepositoryImplIntegrationTest {
	@Autowired
	private AccountRepositoryImpl accountRepositoryImpl;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/repository/AccountRepositoryImplIntegrationTest.sql")
	class getByAccountNo {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウントが取得できた場合")
		void getByAccountNo_found() {
			Account actual = accountRepositoryImpl.getByAccountNo(1);
			
			assertEquals(1, actual.getAccountNo());
			assertEquals(1, actual.getCreatedBy());
			assertEquals(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)), actual.getCreatedAt());
			assertEquals(1, actual.getUpdatedBy());
			assertEquals(OffsetDateTime.of(2001, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)), actual.getUpdatedAt());
			assertFalse(actual.getIsDeleted());
			assertEquals("aaaaaaaa", actual.getAccountId());
			assertEquals("AAAAAAAA", actual.getAccountName());
			assertEquals("$2a$10$password1", actual.getPassword());
			assertEquals(LocalDate.of(1991, 2, 14), actual.getBirthdate());
			assertEquals("", actual.getSexKbnCode());
			assertEquals("", actual.getBirthplacePrefectureKbnCode());
			assertEquals("", actual.getResidentPrefectureKbnCode());
			assertEquals("", actual.getFreeMemo());
			assertEquals("administrator", actual.getAuthorityKbnCode());
			assertEquals(OffsetDateTime.of(2002, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)), actual.getLastLoginDatetime());
			assertEquals(0, actual.getLoginFailureCount());
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：アカウントが取得できなかった場合")
		void getByAccountNo_not_found() {
			Account actual = accountRepositoryImpl.getByAccountNo(99);
			assertNull(actual);
		}
	}
	
	@Nested
	@Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/repository/AccountRepositoryImplIntegrationTest.sql")
	class getByAccountId {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウントが取得できた場合")
		void getByAccountId_found() {
			Account actual = accountRepositoryImpl.getByAccountId("aaaaaaaa");
			
			assertEquals(1, actual.getAccountNo());
			assertEquals(1, actual.getCreatedBy());
			assertEquals(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)), actual.getCreatedAt());
			assertEquals(1, actual.getUpdatedBy());
			assertEquals(OffsetDateTime.of(2001, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)), actual.getUpdatedAt());
			assertFalse(actual.getIsDeleted());
			assertEquals("aaaaaaaa", actual.getAccountId());
			assertEquals("AAAAAAAA", actual.getAccountName());
			assertEquals("$2a$10$password1", actual.getPassword());
			assertEquals(LocalDate.of(1991, 2, 14), actual.getBirthdate());
			assertEquals("", actual.getSexKbnCode());
			assertEquals("", actual.getBirthplacePrefectureKbnCode());
			assertEquals("", actual.getResidentPrefectureKbnCode());
			assertEquals("", actual.getFreeMemo());
			assertEquals("administrator", actual.getAuthorityKbnCode());
			assertEquals(OffsetDateTime.of(2002, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)), actual.getLastLoginDatetime());
			assertEquals(0, actual.getLoginFailureCount());
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：アカウントが取得できなかった場合")
		void getByAccountId_not_found() {
			Account actual = accountRepositoryImpl.getByAccountId("zzzzzzzz");
			assertNull(actual);
		}
	}
	
	@Nested
	@Order(3)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class regist {
		@Test
		@Order(1)
		@DisplayName("正常系：Nullのパラメータを含むAccountModelの登録")
		void regist_contain_null_parameter() throws RegistFailureException {
			AccountModel accountModel = AccountModel.builder()
					.accountId("zzzzzzzz")
					.accountName("ZZZZZZZZ")
					.password("zzzzzzzz")
					.build();
			
			accountRepositoryImpl.regist(accountModel);
			
			List<Account> actualData = jdbcTemplate.query(
					"SELECT * FROM common.account where account_id='zzzzzzzz'", (rs, rowNum) ->
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
			
			assertEquals(1, actualData.size());
			assertEquals(1, actualData.getFirst().getAccountNo());
			assertEquals(0, actualData.getFirst().getCreatedBy());
			assertEquals(0, actualData.getFirst().getUpdatedBy());
			assertFalse(actualData.getFirst().getIsDeleted());
			assertEquals("zzzzzzzz", actualData.getFirst().getAccountId());
			assertEquals("ZZZZZZZZ", actualData.getFirst().getAccountName());
			assertEquals(LocalDate.of(1900, 1, 1), actualData.getFirst().getBirthdate());
			assertEquals("none", actualData.getFirst().getSexKbnCode());
			assertEquals("none", actualData.getFirst().getBirthplacePrefectureKbnCode());
			assertEquals("none", actualData.getFirst().getResidentPrefectureKbnCode());
			assertEquals("", actualData.getFirst().getFreeMemo());
			assertEquals("mini-user", actualData.getFirst().getAuthorityKbnCode());
			assertEquals(OffsetDateTime.of(1900, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)), actualData.getFirst().getLastLoginDatetime().plusHours(9));
			assertEquals(0, actualData.getFirst().getLoginFailureCount());
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：Nullのパラメータを含まないAccountModelの登録")
		void regist_not_contain_null_parameter() throws RegistFailureException {
			AccountModel accountModel = AccountModel.builder()
					.accountId("zzzzzzzz")
					.accountName("ZZZZZZZZ")
					.password("zzzzzzzz")
					.birthdate(LocalDate.of(1991, 2, 14))
					.sexKbnCode("woman")
					.birthplacePrefectureKbnCode("Hokkaido")
					.residentPrefectureKbnCode("Okinawa")
					.freeMemo("フリーメモ")
					.build();
			
			accountRepositoryImpl.regist(accountModel);
			
			List<Account> actualData = jdbcTemplate.query(
					"SELECT * FROM common.account where account_id='zzzzzzzz'", (rs, rowNum) ->
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
			
			assertEquals(1, actualData.size());
			assertEquals(1, actualData.getFirst().getAccountNo());
			assertEquals(0, actualData.getFirst().getCreatedBy());
			assertEquals(0, actualData.getFirst().getUpdatedBy());
			assertFalse(actualData.getFirst().getIsDeleted());
			assertEquals("zzzzzzzz", actualData.getFirst().getAccountId());
			assertEquals("ZZZZZZZZ", actualData.getFirst().getAccountName());
			assertEquals(LocalDate.of(1991, 2, 14), actualData.getFirst().getBirthdate());
			assertEquals("woman", actualData.getFirst().getSexKbnCode());
			assertEquals("Hokkaido", actualData.getFirst().getBirthplacePrefectureKbnCode());
			assertEquals("Okinawa", actualData.getFirst().getResidentPrefectureKbnCode());
			assertEquals("フリーメモ", actualData.getFirst().getFreeMemo());
			assertEquals("mini-user", actualData.getFirst().getAuthorityKbnCode());
			assertEquals(OffsetDateTime.of(1900, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)), actualData.getFirst().getLastLoginDatetime().plusHours(9));
			assertEquals(0, actualData.getFirst().getLoginFailureCount());
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：RegistFailureExceptionをthrowする")
		@Sql("/sql/repository/AccountRepositoryImplIntegrationTest.sql")
		void regist_RegistFailureException() throws RegistFailureException {
			AccountModel accountModel = AccountModel.builder()
					.accountId("aaaaaaaa")
					.accountName("AAAAAAAA")
					.password("aaaaaaaa")
					.build();
			
			assertThrows(RegistFailureException.class, () -> accountRepositoryImpl.regist(accountModel));
		}
	}
	
	@Nested
	@Order(4)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/repository/AccountRepositoryImplIntegrationTest.sql")
	class update {
		@Test
		@Order(1)
		@DisplayName("正常系：Nullのパラメータを含むAccountModelでの更新")
		void update_contain_null_parameter() throws UpdateFailureException {
			AccountModel accountModel = AccountModel.builder()
					.accountNo(1)
					.accountId("aaaaaaaa")
					.accountName("AAAAAAAA")
					.build();
			
			accountRepositoryImpl.update(accountModel);
			
			List<Account> actualData = jdbcTemplate.query(
					"SELECT * FROM common.account where account_id='aaaaaaaa'", (rs, rowNum) ->
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
			
			assertEquals(1, actualData.size());
			assertEquals(1, actualData.getFirst().getAccountNo());
			assertEquals(1, actualData.getFirst().getCreatedBy());
			assertEquals(1, actualData.getFirst().getUpdatedBy());
			assertFalse(actualData.getFirst().getIsDeleted());
			assertEquals("aaaaaaaa", actualData.getFirst().getAccountId());
			assertEquals("AAAAAAAA", actualData.getFirst().getAccountName());
			assertEquals("$2a$10$password1", actualData.getFirst().getPassword());
			assertEquals(LocalDate.of(1900, 1, 1), actualData.getFirst().getBirthdate());
			assertEquals("none", actualData.getFirst().getSexKbnCode());
			assertEquals("none", actualData.getFirst().getBirthplacePrefectureKbnCode());
			assertEquals("none", actualData.getFirst().getResidentPrefectureKbnCode());
			assertEquals("", actualData.getFirst().getFreeMemo());
			assertEquals("administrator", actualData.getFirst().getAuthorityKbnCode());
			assertEquals(OffsetDateTime.of(1900, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)), actualData.getFirst().getLastLoginDatetime().plusHours(9));
			assertEquals(0, actualData.getFirst().getLoginFailureCount());
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：Nullのパラメータを含まないAccountModelでの更新")
		void update_not_contain_null_parameter() throws UpdateFailureException {
			AccountModel accountModel = AccountModel.builder()
					.accountNo(1)
					.accountId("aaaaaaaa")
					.accountName("AAAAAAAA")
					.password("aaaaaaaa")
					.birthdate(LocalDate.of(1991, 2, 14))
					.sexKbnCode("woman")
					.birthplacePrefectureKbnCode("Hokkaido")
					.residentPrefectureKbnCode("Okinawa")
					.freeMemo("フリーメモ")
					.lastLoginDatetime(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(9)))
					.loginFailureCount(2)
					.build();
			
			accountRepositoryImpl.update(accountModel);
			
			List<Account> actualData = jdbcTemplate.query(
					"SELECT * FROM common.account where account_id='aaaaaaaa'", (rs, rowNum) ->
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
			
			assertEquals(1, actualData.size());
			assertEquals(1, actualData.getFirst().getAccountNo());
			assertEquals(1, actualData.getFirst().getCreatedBy());
			assertEquals(1, actualData.getFirst().getUpdatedBy());
			assertFalse(actualData.getFirst().getIsDeleted());
			assertEquals("aaaaaaaa", actualData.getFirst().getAccountId());
			assertEquals("AAAAAAAA", actualData.getFirst().getAccountName());
			assertNotEquals("$2a$10$password1", actualData.getFirst().getPassword());
			assertEquals(LocalDate.of(1991, 2, 14), actualData.getFirst().getBirthdate());
			assertEquals("woman", actualData.getFirst().getSexKbnCode());
			assertEquals("Hokkaido", actualData.getFirst().getBirthplacePrefectureKbnCode());
			assertEquals("Okinawa", actualData.getFirst().getResidentPrefectureKbnCode());
			assertEquals("フリーメモ", actualData.getFirst().getFreeMemo());
			assertEquals("administrator", actualData.getFirst().getAuthorityKbnCode());
			assertEquals(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)), actualData.getFirst().getLastLoginDatetime().plusHours(9));
			assertEquals(2, actualData.getFirst().getLoginFailureCount());
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：UpdateFailureExceptionをthrowする")
		void update_UpdateFailureException() {
			AccountModel accountModel = AccountModel.builder()
					.accountNo(13)
					.accountId("aaaaaaaa")
					.accountName("AAAAAAAA")
					.build();
			
			assertThrows(UpdateFailureException.class, () -> accountRepositoryImpl.update(accountModel));
		}
	}
	
	@Nested
	@Order(5)
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/repository/AccountRepositoryImplIntegrationTest.sql")
	class updateLoginFailureCount {
		@Test
		@Order(1)
		@DisplayName("正常系：Nullのパラメータを含むAccountModelでの更新")
		void updateLoginFailureCount_contain_null_parameter() throws UpdateFailureException {
			AccountModel accountModel = AccountModel.builder()
					.accountNo(8)
					.build();
			
			accountRepositoryImpl.updateLoginFailureCount(accountModel);
			
			List<Account> actualData = jdbcTemplate.query(
					"SELECT * FROM common.account where account_no=8", (rs, rowNum) ->
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
			
			assertEquals(1, actualData.size());
			assertEquals(8, actualData.getFirst().getAccountNo());
			assertEquals(8, actualData.getFirst().getCreatedBy());
			assertEquals(8, actualData.getFirst().getUpdatedBy());
			assertFalse(actualData.getFirst().getIsDeleted());
			assertEquals("hhhhhhhh", actualData.getFirst().getAccountId());
			assertEquals("HHHHHHHH", actualData.getFirst().getAccountName());
			assertEquals("$2a$10$password8", actualData.getFirst().getPassword());
			assertEquals(LocalDate.of(1900, 1, 1), actualData.getFirst().getBirthdate());
			assertEquals("", actualData.getFirst().getSexKbnCode());
			assertEquals("", actualData.getFirst().getBirthplacePrefectureKbnCode());
			assertEquals("", actualData.getFirst().getResidentPrefectureKbnCode());
			assertEquals("", actualData.getFirst().getFreeMemo());
			assertEquals("administrator", actualData.getFirst().getAuthorityKbnCode());
			assertEquals(OffsetDateTime.of(2002, 1, 1, 9, 0, 0, 0, ZoneOffset.ofHours(0)), actualData.getFirst().getLastLoginDatetime().plusHours(9));
			assertEquals(0, actualData.getFirst().getLoginFailureCount());
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：Nullのパラメータを含まないAccountModelでの更新")
		void updateLoginFailureCount_not_contain_null_parameter() throws UpdateFailureException {
			AccountModel accountModel = AccountModel.builder()
					.accountNo(1)
					.lastLoginDatetime(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(9)))
					.loginFailureCount(2)
					.build();
			
			accountRepositoryImpl.updateLoginFailureCount(accountModel);
			
			List<Account> actualData = jdbcTemplate.query(
					"SELECT * FROM common.account where account_no=1", (rs, rowNum) ->
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
			
			assertEquals(1, actualData.size());
			assertEquals(1, actualData.getFirst().getAccountNo());
			assertEquals(1, actualData.getFirst().getCreatedBy());
			assertEquals(1, actualData.getFirst().getUpdatedBy());
			assertFalse(actualData.getFirst().getIsDeleted());
			assertEquals("aaaaaaaa", actualData.getFirst().getAccountId());
			assertEquals("AAAAAAAA", actualData.getFirst().getAccountName());
			assertEquals("$2a$10$password1", actualData.getFirst().getPassword());
			assertEquals(LocalDate.of(1991, 2, 14), actualData.getFirst().getBirthdate());
			assertEquals("", actualData.getFirst().getSexKbnCode());
			assertEquals("", actualData.getFirst().getBirthplacePrefectureKbnCode());
			assertEquals("", actualData.getFirst().getResidentPrefectureKbnCode());
			assertEquals("", actualData.getFirst().getFreeMemo());
			assertEquals("administrator", actualData.getFirst().getAuthorityKbnCode());
			assertEquals(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)), actualData.getFirst().getLastLoginDatetime().plusHours(9));
			assertEquals(2, actualData.getFirst().getLoginFailureCount());
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：UpdateFailureExceptionをthrowする")
		void updateLoginFailureCount_UpdateFailureException() {
			AccountModel accountModel = AccountModel.builder()
					.accountNo(13)
					.loginFailureCount(2)
					.build();
			
			assertThrows(UpdateFailureException.class, () -> accountRepositoryImpl.updateLoginFailureCount(accountModel));
		}
	}
	
	@Nested
	@Order(6)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/repository/AccountRepositoryImplIntegrationTest.sql")
	class isExistAccount {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウントが存在する場合")
		void isExistAccount_true() {
			assertTrue(accountRepositoryImpl.isExistAccount(2, "aaaaaaaa"));
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：アカウントが存在しない場合")
		void isExistAccount_false() {
			assertFalse(accountRepositoryImpl.isExistAccount(1, "zzzzzzzz"));
		}
	}
	
	@Nested
	@Order(7)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class getAccountList {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウントを2件以上取得")
		@Sql("/sql/repository/AccountRepositoryImplIntegrationTest.sql")
		void getAccountList_found_some_accounts() {
			List<AccountModel> actual = accountRepositoryImpl.getAccountList();
			assertEquals(11, actual.size());
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：アカウントが0件")
		void getAccountList_not_found() {
			List<AccountModel> actual = accountRepositoryImpl.getAccountList();
			assertEquals(0, actual.size());
		}
	}
}