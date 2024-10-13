package com.web.gallary.repository.impl.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

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
	@Sql("/sql/repository/AccountRepositoryImplIntegrationTest.sql")
	class regist {
		@Test
		@Order(1)
		@DisplayName("正常系：Nullのパラメータを含むAccountModelの登録")
		void regist_contain_null_parameter() throws RegistFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：Nullのパラメータを含まないAccountModelの登録")
		void regist_not_contain_null_parameter() throws RegistFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：RegistFailureExceptionをthrowする")
		void regist_RegistFailureException() throws RegistFailureException {
			assertTrue(false);
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
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：Nullのパラメータを含まないAccountModelでの更新")
		void update_not_contain_null_parameter() throws UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：UpdateFailureExceptionをthrowする")
		void update_UpdateFailureException() {
			assertTrue(false);
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
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：Nullのパラメータを含まないAccountModelでの更新")
		void updateLoginFailureCount_not_contain_null_parameter() throws UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：UpdateFailureExceptionをthrowする")
		void updateLoginFailureCount_UpdateFailureException() {
			assertTrue(false);
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
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：アカウントが存在しない場合")
		void isExistAccount_false() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(7)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/repository/AccountRepositoryImplIntegrationTest.sql")
	class getAccountList {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウントを2件以上取得")
		void getAccountList_found_some_accounts() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：アカウントが0件")
		void getAccountList_not_found() {
			assertTrue(false);
		}
	}
}