package com.web.gallary.mapper;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.web.gallary.entity.Account;

@MybatisTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountMapperTest {
	@Autowired
	private AccountMapper accountMapper;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/AccountMapperTest.sql")
	class select {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウント番号でのselectで1件の場合")
		void select_by_accountNo() {
			Account account = Account.builder().accountNo(1).build();
			List<Account> selectedAccoutList = accountMapper.select(account);
			
			Account expectedAccount = Account.builder()
					.accountNo(1)
					.createdBy(1)
					.createdAt(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.updatedBy(1)
					.updatedAt(OffsetDateTime.of(2001, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.isDeleted(false)
					.accountId("aaaaaaaa")
					.accountName("AAAAAAAA")
					.password("$2a$10$password1")
					.birthdate(LocalDate.of(1991, 2, 14))
					.sexKbnCode("")
					.birthplacePrefectureKbnCode("")
					.residentPrefectureKbnCode("")
					.freeMemo("")
					.authorityKbnCode("administrator")
					.lastLoginDatetime(OffsetDateTime.of(2002, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.loginFailureCount(0)
					.build();
			
			List<Account> expectedAccoutList = new ArrayList<Account>();
			expectedAccoutList.add(expectedAccount);
			
			assertEquals(selectedAccoutList.size(), 1);
			assertEquals(selectedAccoutList, expectedAccoutList);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：削除フラグでのselectで1件の場合")
		void select_by_isDeleted() {
			Account account = Account.builder().isDeleted(true).build();
			List<Account> selectedAccoutList = accountMapper.select(account);
		
			Account expectedAccount = Account.builder()
					.accountNo(9)
					.createdBy(9)
					.createdAt(OffsetDateTime.of(2000, 1, 9, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.updatedBy(9)
					.updatedAt(OffsetDateTime.of(2001, 1, 9, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.isDeleted(true)
					.accountId("iiiiiiii")
					.accountName("IIIIIIII")
					.password("$2a$10$password9")
					.birthdate(LocalDate.of(1900, 1, 1))
					.sexKbnCode("")
					.birthplacePrefectureKbnCode("")
					.residentPrefectureKbnCode("")
					.freeMemo("")
					.authorityKbnCode("special-user")
					.lastLoginDatetime(OffsetDateTime.of(2002, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.loginFailureCount(0)
					.build();
			
			List<Account> expectedAccoutList = new ArrayList<Account>();
			expectedAccoutList.add(expectedAccount);
			
			assertEquals(selectedAccoutList.size(), 1);
			assertEquals(selectedAccoutList, expectedAccoutList);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：アカウントIDでのselectで1件の場合")
		void select_by_accountId() {
			Account account = Account.builder().accountId("aaaaaaaa").build();
			List<Account> selectedAccoutList = accountMapper.select(account);
			
			Account expectedAccount = Account.builder()
					.accountNo(1)
					.createdBy(1)
					.createdAt(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.updatedBy(1)
					.updatedAt(OffsetDateTime.of(2001, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.isDeleted(false)
					.accountId("aaaaaaaa")
					.accountName("AAAAAAAA")
					.password("$2a$10$password1")
					.birthdate(LocalDate.of(1991, 2, 14))
					.sexKbnCode("")
					.birthplacePrefectureKbnCode("")
					.residentPrefectureKbnCode("")
					.freeMemo("")
					.authorityKbnCode("administrator")
					.lastLoginDatetime(OffsetDateTime.of(2002, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.loginFailureCount(0)
					.build();
			
			List<Account> expectedAccoutList = new ArrayList<Account>();
			expectedAccoutList.add(expectedAccount);
			
			assertEquals(selectedAccoutList.size(), 1);
			assertEquals(selectedAccoutList, expectedAccoutList);
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：アカウント名でのselectで1件の場合")
		void select_by_accountName() {
			Account account = Account.builder().accountName("AAAAAAAA").build();
			List<Account> selectedAccoutList = accountMapper.select(account);
			
			Account expectedAccount = Account.builder()
					.accountNo(1)
					.createdBy(1)
					.createdAt(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.updatedBy(1)
					.updatedAt(OffsetDateTime.of(2001, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.isDeleted(false)
					.accountId("aaaaaaaa")
					.accountName("AAAAAAAA")
					.password("$2a$10$password1")
					.birthdate(LocalDate.of(1991, 2, 14))
					.sexKbnCode("")
					.birthplacePrefectureKbnCode("")
					.residentPrefectureKbnCode("")
					.freeMemo("")
					.authorityKbnCode("administrator")
					.lastLoginDatetime(OffsetDateTime.of(2002, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.loginFailureCount(0)
					.build();
			
			List<Account> expectedAccoutList = new ArrayList<Account>();
			expectedAccoutList.add(expectedAccount);
			
			assertEquals(selectedAccoutList.size(), 1);
			assertEquals(selectedAccoutList, expectedAccoutList);
		}
		
		@Test
		@Order(5)
		@DisplayName("正常系：パスワードでのselectで1件の場合")
		void select_by_password() {
			Account account = Account.builder().password("$2a$10$password1").build();
			List<Account> selectedAccoutList = accountMapper.select(account);
			
			Account expectedAccount = Account.builder()
					.accountNo(1)
					.createdBy(1)
					.createdAt(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.updatedBy(1)
					.updatedAt(OffsetDateTime.of(2001, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.isDeleted(false)
					.accountId("aaaaaaaa")
					.accountName("AAAAAAAA")
					.password("$2a$10$password1")
					.birthdate(LocalDate.of(1991, 2, 14))
					.sexKbnCode("")
					.birthplacePrefectureKbnCode("")
					.residentPrefectureKbnCode("")
					.freeMemo("")
					.authorityKbnCode("administrator")
					.lastLoginDatetime(OffsetDateTime.of(2002, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.loginFailureCount(0)
					.build();
			
			List<Account> expectedAccoutList = new ArrayList<Account>();
			expectedAccoutList.add(expectedAccount);
			
			assertEquals(selectedAccoutList.size(), 1);
			assertEquals(selectedAccoutList, expectedAccoutList);
		}
		
		@Test
		@Order(6)
		@DisplayName("正常系：生年月日でのselectで1件の場合")
		void select_by_birthdate() {
			Account account = Account.builder().birthdate(LocalDate.of(1991, 2, 14)).build();
			List<Account> selectedAccoutList = accountMapper.select(account);
			
			Account expectedAccount = Account.builder()
					.accountNo(1)
					.createdBy(1)
					.createdAt(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.updatedBy(1)
					.updatedAt(OffsetDateTime.of(2001, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.isDeleted(false)
					.accountId("aaaaaaaa")
					.accountName("AAAAAAAA")
					.password("$2a$10$password1")
					.birthdate(LocalDate.of(1991, 2, 14))
					.sexKbnCode("")
					.birthplacePrefectureKbnCode("")
					.residentPrefectureKbnCode("")
					.freeMemo("")
					.authorityKbnCode("administrator")
					.lastLoginDatetime(OffsetDateTime.of(2002, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.loginFailureCount(0)
					.build();
			
			List<Account> expectedAccoutList = new ArrayList<Account>();
			expectedAccoutList.add(expectedAccount);
			
			assertEquals(selectedAccoutList.size(), 1);
			assertEquals(selectedAccoutList, expectedAccoutList);
		}
		
		@Test
		@Order(7)
		@DisplayName("正常系：性別区分コードでのselectで1件の場合")
		void select_by_sexKbnCode() {
			Account account = Account.builder().sexKbnCode("man").build();
			List<Account> selectedAccoutList = accountMapper.select(account);
			
			Account expectedAccount = Account.builder()
					.accountNo(2)
					.createdBy(2)
					.createdAt(OffsetDateTime.of(2000, 1, 2, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.updatedBy(2)
					.updatedAt(OffsetDateTime.of(2001, 1, 2, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.isDeleted(false)
					.accountId("bbbbbbbb")
					.accountName("BBBBBBBB")
					.password("$2a$10$password2")
					.birthdate(LocalDate.of(1900, 1, 1))
					.sexKbnCode("man")
					.birthplacePrefectureKbnCode("")
					.residentPrefectureKbnCode("")
					.freeMemo("")
					.authorityKbnCode("administrator")
					.lastLoginDatetime(OffsetDateTime.of(2002, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.loginFailureCount(0)
					.build();
			
			List<Account> expectedAccoutList = new ArrayList<Account>();
			expectedAccoutList.add(expectedAccount);
			
			assertEquals(selectedAccoutList.size(), 1);
			assertEquals(selectedAccoutList, expectedAccoutList);
		}
		
		@Test
		@Order(8)
		@DisplayName("正常系：出身都道府県区分コードでのselectで1件の場合")
		void select_by_birthplacePrefectureKbnCode() {
			Account account = Account.builder().birthplacePrefectureKbnCode("Hokkaido").build();
			List<Account> selectedAccoutList = accountMapper.select(account);
			
			Account expectedAccount = Account.builder()
					.accountNo(3)
					.createdBy(3)
					.createdAt(OffsetDateTime.of(2000, 1, 3, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.updatedBy(3)
					.updatedAt(OffsetDateTime.of(2001, 1, 3, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.isDeleted(false)
					.accountId("cccccccc")
					.accountName("CCCCCCCC")
					.password("$2a$10$password3")
					.birthdate(LocalDate.of(1900, 1, 1))
					.sexKbnCode("")
					.birthplacePrefectureKbnCode("Hokkaido")
					.residentPrefectureKbnCode("")
					.freeMemo("")
					.authorityKbnCode("administrator")
					.lastLoginDatetime(OffsetDateTime.of(2002, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.loginFailureCount(0)
					.build();
			
			List<Account> expectedAccoutList = new ArrayList<Account>();
			expectedAccoutList.add(expectedAccount);
			
			assertEquals(selectedAccoutList.size(), 1);
			assertEquals(selectedAccoutList, expectedAccoutList);
		}
		
		@Test
		@Order(9)
		@DisplayName("正常系：在住都道府県区分コードでのselectで1件の場合")
		void select_by_residentPrefectureKbnCode() {
			Account account = Account.builder().residentPrefectureKbnCode("Okinawa").build();
			List<Account> selectedAccoutList = accountMapper.select(account);
			
			Account expectedAccount = Account.builder()
					.accountNo(4)
					.createdBy(4)
					.createdAt(OffsetDateTime.of(2000, 1, 4, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.updatedBy(4)
					.updatedAt(OffsetDateTime.of(2001, 1, 4, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.isDeleted(false)
					.accountId("dddddddd")
					.accountName("DDDDDDDD")
					.password("$2a$10$password4")
					.birthdate(LocalDate.of(1900, 1, 1))
					.sexKbnCode("")
					.birthplacePrefectureKbnCode("")
					.residentPrefectureKbnCode("Okinawa")
					.freeMemo("")
					.authorityKbnCode("administrator")
					.lastLoginDatetime(OffsetDateTime.of(2002, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.loginFailureCount(0)
					.build();
			
			List<Account> expectedAccoutList = new ArrayList<Account>();
			expectedAccoutList.add(expectedAccount);
			
			assertEquals(selectedAccoutList.size(), 1);
			assertEquals(selectedAccoutList, expectedAccoutList);
		}
		
		@Test
		@Order(10)
		@DisplayName("正常系：フリーメモでのselectで1件の場合")
		void select_by_freeMemo() {
			Account account = Account.builder().freeMemo("フリーメモ").build();
			List<Account> selectedAccoutList = accountMapper.select(account);
			
			Account expectedAccount = Account.builder()
					.accountNo(5)
					.createdBy(5)
					.createdAt(OffsetDateTime.of(2000, 1, 5, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.updatedBy(5)
					.updatedAt(OffsetDateTime.of(2001, 1, 5, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.isDeleted(false)
					.accountId("eeeeeeee")
					.accountName("EEEEEEEE")
					.password("$2a$10$password5")
					.birthdate(LocalDate.of(1900, 1, 1))
					.sexKbnCode("")
					.birthplacePrefectureKbnCode("")
					.residentPrefectureKbnCode("")
					.freeMemo("フリーメモ")
					.authorityKbnCode("administrator")
					.lastLoginDatetime(OffsetDateTime.of(2002, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.loginFailureCount(0)
					.build();
			
			List<Account> expectedAccoutList = new ArrayList<Account>();
			expectedAccoutList.add(expectedAccount);
			
			assertEquals(selectedAccoutList.size(), 1);
			assertEquals(selectedAccoutList, expectedAccoutList);
		}
		
		@Test
		@Order(11)
		@DisplayName("正常系：権限区分コードでのselectで1件の場合")
		void select_by_authorityKbnCode() {
			Account account = Account.builder().authorityKbnCode("mini-user").build();
			List<Account> selectedAccoutList = accountMapper.select(account);
			
			Account expectedAccount = Account.builder()
					.accountNo(6)
					.createdBy(6)
					.createdAt(OffsetDateTime.of(2000, 1, 6, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.updatedBy(6)
					.updatedAt(OffsetDateTime.of(2001, 1, 6, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.isDeleted(false)
					.accountId("ffffffff")
					.accountName("FFFFFFFF")
					.password("$2a$10$password6")
					.birthdate(LocalDate.of(1900, 1, 1))
					.sexKbnCode("")
					.birthplacePrefectureKbnCode("")
					.residentPrefectureKbnCode("")
					.freeMemo("")
					.authorityKbnCode("mini-user")
					.lastLoginDatetime(OffsetDateTime.of(2002, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.loginFailureCount(0)
					.build();
			
			List<Account> expectedAccoutList = new ArrayList<Account>();
			expectedAccoutList.add(expectedAccount);
			
			assertEquals(selectedAccoutList.size(), 1);
			assertEquals(selectedAccoutList, expectedAccoutList);
		}
		
		@Test
		@Order(12)
		@DisplayName("正常系：最終ログイン日時でのselectで1件の場合")
		void select_by_lastLoginDatetime() {
			Account account = Account.builder().lastLoginDatetime(OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0))).build();
			List<Account> selectedAccoutList = accountMapper.select(account);
			
			Account expectedAccount = Account.builder()
					.accountNo(7)
					.createdBy(7)
					.createdAt(OffsetDateTime.of(2000, 1, 7, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.updatedBy(7)
					.updatedAt(OffsetDateTime.of(2001, 1, 7, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.isDeleted(false)
					.accountId("gggggggg")
					.accountName("GGGGGGGG")
					.password("$2a$10$password7")
					.birthdate(LocalDate.of(1900, 1, 1))
					.sexKbnCode("")
					.birthplacePrefectureKbnCode("")
					.residentPrefectureKbnCode("")
					.freeMemo("")
					.authorityKbnCode("administrator")
					.lastLoginDatetime(OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.loginFailureCount(0)
					.build();
			
			List<Account> expectedAccoutList = new ArrayList<Account>();
			expectedAccoutList.add(expectedAccount);
			
			assertEquals(selectedAccoutList.size(), 1);
			assertEquals(selectedAccoutList, expectedAccoutList);
		}
		
		@Test
		@Order(13)
		@DisplayName("正常系：ログイン失敗回数でのselectで1件の場合")
		void select_by_loginFailureCount() {
			Account account = Account.builder().loginFailureCount(2).build();
			List<Account> selectedAccoutList = accountMapper.select(account);
			
			Account expectedAccount = Account.builder()
					.accountNo(8)
					.createdBy(8)
					.createdAt(OffsetDateTime.of(2000, 1, 8, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.updatedBy(8)
					.updatedAt(OffsetDateTime.of(2001, 1, 8, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.isDeleted(false)
					.accountId("hhhhhhhh")
					.accountName("HHHHHHHH")
					.password("$2a$10$password8")
					.birthdate(LocalDate.of(1900, 1, 1))
					.sexKbnCode("")
					.birthplacePrefectureKbnCode("")
					.residentPrefectureKbnCode("")
					.freeMemo("")
					.authorityKbnCode("administrator")
					.lastLoginDatetime(OffsetDateTime.of(2002, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.loginFailureCount(2)
					.build();
			
			List<Account> expectedAccoutList = new ArrayList<Account>();
			expectedAccoutList.add(expectedAccount);
			
			assertEquals(selectedAccoutList.size(), 1);
			assertEquals(selectedAccoutList, expectedAccoutList);
		}
		
		@Test
		@Order(14)
		@DisplayName("正常系：selectで0件の場合")
		void select_not_found() {
			Account account = Account.builder().accountNo(99).build();
			List<Account> selectedAccoutList = accountMapper.select(account);
			List<Account> expectedAccoutList = new ArrayList<Account>();
			
			assertEquals(selectedAccoutList.size(), 0);
			assertEquals(selectedAccoutList, expectedAccoutList);
		}
		
		@Test
		@Order(15)
		@DisplayName("正常系：selectで2件以上の場合")
		void select_accounts() {
			Account account = Account.builder().authorityKbnCode("special-user").build();
			List<Account> selectedAccoutList = accountMapper.select(account);
			
			Account expectedAccount1 = Account.builder()
					.accountNo(9)
					.createdBy(9)
					.createdAt(OffsetDateTime.of(2000, 1, 9, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.updatedBy(9)
					.updatedAt(OffsetDateTime.of(2001, 1, 9, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.isDeleted(true)
					.accountId("iiiiiiii")
					.accountName("IIIIIIII")
					.password("$2a$10$password9")
					.birthdate(LocalDate.of(1900, 1, 1))
					.sexKbnCode("")
					.birthplacePrefectureKbnCode("")
					.residentPrefectureKbnCode("")
					.freeMemo("")
					.authorityKbnCode("special-user")
					.lastLoginDatetime(OffsetDateTime.of(2002, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.loginFailureCount(0)
					.build();
			
			Account expectedAccount2 = Account.builder()
					.accountNo(10)
					.createdBy(10)
					.createdAt(OffsetDateTime.of(2000, 1, 10, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.updatedBy(10)
					.updatedAt(OffsetDateTime.of(2001, 1, 10, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.isDeleted(false)
					.accountId("jjjjjjjj")
					.accountName("JJJJJJJJ")
					.password("$2a$10$password10")
					.birthdate(LocalDate.of(1900, 1, 1))
					.sexKbnCode("")
					.birthplacePrefectureKbnCode("")
					.residentPrefectureKbnCode("")
					.freeMemo("")
					.authorityKbnCode("special-user")
					.lastLoginDatetime(OffsetDateTime.of(2002, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.loginFailureCount(0)
					.build();
			
			List<Account> expectedAccoutList = new ArrayList<Account>();
			expectedAccoutList.add(expectedAccount1);
			expectedAccoutList.add(expectedAccount2);
			
			assertEquals(selectedAccoutList.size(), 2);
			assertEquals(selectedAccoutList, expectedAccoutList);
		}
		
		@Test
		@Order(16)
		@DisplayName("正常系：複数の条件でselectする場合")
		void select_some_conditions() {
			Account account = Account.builder()
					.accountId("llllllll")
					.sexKbnCode("woman")
					.birthplacePrefectureKbnCode("Okinawa")
					.residentPrefectureKbnCode("Tokyo")
					.freeMemo("よろしく")
					.build();
			List<Account> selectedAccoutList = accountMapper.select(account);
			
			Account expectedAccount1 = Account.builder()
					.accountNo(12)
					.createdBy(12)
					.createdAt(OffsetDateTime.of(2000, 1, 12, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.updatedBy(12)
					.updatedAt(OffsetDateTime.of(2001, 1, 12, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.isDeleted(false)
					.accountId("llllllll")
					.accountName("LLLLLLLL")
					.password("$2a$10$password12")
					.birthdate(LocalDate.of(1900, 1, 1))
					.sexKbnCode("woman")
					.birthplacePrefectureKbnCode("Okinawa")
					.residentPrefectureKbnCode("Tokyo")
					.freeMemo("よろしく")
					.authorityKbnCode("normal-user")
					.lastLoginDatetime(OffsetDateTime.of(2002, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.loginFailureCount(3)
					.build();
			
			List<Account> expectedAccoutList = new ArrayList<Account>();
			expectedAccoutList.add(expectedAccount1);
			
			assertEquals(selectedAccoutList.size(), 1);
			assertEquals(selectedAccoutList, expectedAccoutList);
		}
	}

	@Nested
	@Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/AccountMapperTest.sql")
	class count {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウント番号でのcount")
		void count_by_accountNo() {
			Account account = Account.builder().accountNo(1).build();
			Integer count = accountMapper.count(account);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：削除フラグでのcount")
		void count_by_isDeleted() {
			Account account = Account.builder().isDeleted(true).build();
			Integer count = accountMapper.count(account);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：アカウントIDでのcount")
		void count_by_accountId() {
			Account account = Account.builder().accountId("aaaaaaaa").build();
			Integer count = accountMapper.count(account);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：アカウント名でのcount")
		void count_by_accountName() {
			Account account = Account.builder().accountName("AAAAAAAA").build();
			Integer count = accountMapper.count(account);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(5)
		@DisplayName("正常系：パスワードでのcount")
		void count_by_password() {
			Account account = Account.builder().password("$2a$10$password1").build();
			Integer count = accountMapper.count(account);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(6)
		@DisplayName("正常系：生年月日でのcount")
		void count_by_birthdate() {
			Account account = Account.builder().birthdate(LocalDate.of(1991, 2, 14)).build();
			Integer count = accountMapper.count(account);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(7)
		@DisplayName("正常系：性別区分コードでのcount")
		void count_by_sexKbnCode() {
			Account account = Account.builder().sexKbnCode("man").build();
			Integer count = accountMapper.count(account);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(8)
		@DisplayName("正常系：出身都道府県区分コードでのcount")
		void count_by_birthplacePrefectureKbnCode() {
			Account account = Account.builder().birthplacePrefectureKbnCode("Hokkaido").build();
			Integer count = accountMapper.count(account);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(9)
		@DisplayName("正常系：在住都道府県区分コードでのcount")
		void count_by_residentPrefectureKbnCode() {
			Account account = Account.builder().residentPrefectureKbnCode("Okinawa").build();
			Integer count = accountMapper.count(account);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(10)
		@DisplayName("正常系：フリーメモでのcount")
		void count_by_freeMemo() {
			Account account = Account.builder().freeMemo("フリーメモ").build();
			Integer count = accountMapper.count(account);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(11)
		@DisplayName("正常系：権限区分コードでのcount")
		void count_by_authorityKbnCode() {
			Account account = Account.builder().authorityKbnCode("mini-user").build();
			Integer count = accountMapper.count(account);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(12)
		@DisplayName("正常系：最終ログイン日時でのcount")
		void count_by_lastLoginDatetime() {
			Account account = Account.builder()
					.lastLoginDatetime(OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.build();
			Integer count = accountMapper.count(account);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(13)
		@DisplayName("正常系：ログイン失敗回数でのcount")
		void count_by_loginFailureCount() {
			Account account = Account.builder().loginFailureCount(2).build();
			Integer count = accountMapper.count(account);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(14)
		@DisplayName("正常系：countで0件の場合")
		void count_not_found() {
			Account account = Account.builder().accountNo(99).build();
			Integer count = accountMapper.count(account);
			assertEquals(count, 0);
		}
		
		@Test
		@Order(15)
		@DisplayName("正常系：countで2件以上の場合")
		void count_accounts() {
			Account account = Account.builder().authorityKbnCode("special-user").build();
			Integer count = accountMapper.count(account);
			assertEquals(count, 2);
		}
		
		@Test
		@Order(16)
		@DisplayName("正常系：複数の条件でcountする場合")
		void count_some_conditions() {
			Account account = Account.builder()
					.accountId("llllllll")
					.sexKbnCode("woman")
					.birthplacePrefectureKbnCode("Okinawa")
					.residentPrefectureKbnCode("Tokyo")
					.freeMemo("よろしく")
					.build();
			Integer count = accountMapper.count(account);
			assertEquals(count, 1);
		}
	}
	
	@Nested
	@Order(3)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class insert {
		@Test
		@Order(1)
		@DisplayName("正常系：登録成功")
		@Sql("/sql/ResetAccountNoSeq.sql")
		void insert_success() {
			Account insertAccount = Account.builder()
					.accountNo(1)
					.createdBy(1)
					.createdAt(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(9)))
					.updatedBy(1)
					.updatedAt(OffsetDateTime.of(2001, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(9)))
					.isDeleted(false)
					.accountId("aaaaaaaa")
					.accountName("AAAAAAAA")
					.password("$2a$10$password1")
					.birthdate(LocalDate.of(1991, 2, 14))
					.sexKbnCode("")
					.birthplacePrefectureKbnCode("")
					.residentPrefectureKbnCode("")
					.freeMemo("")
					.authorityKbnCode("administrator")
					.lastLoginDatetime(OffsetDateTime.of(2002, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(9)))
					.loginFailureCount(0)
					.build();
			
			Integer insertCount = accountMapper.insert(insertAccount);
			assertThat(insertCount).isEqualTo(1);
			
			List<Account> expectedAccountList = jdbcTemplate.query(
					"SELECT * FROM common.account", (rs, rowNum) ->
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
			
			assertEquals(1, expectedAccountList.size());
		}
	}
	
	@Nested
	@Order(4)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/AccountMapperTest.sql")
	class update {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウント番号でのupdate")
		void update_by_accountNo() {
			Account conditionAccount = Account.builder().accountNo(1).build();
			Account targetAccount = Account.builder().loginFailureCount(1).build();
			Integer count = accountMapper.update(conditionAccount, targetAccount);
			assertThat(count).isEqualTo(1);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：削除フラグでのupdate")
		void update_by_isDeleted() {
			Account conditionAccount = Account.builder().isDeleted(true).build();
			Account targetAccount = Account.builder().loginFailureCount(1).build();
			Integer count = accountMapper.update(conditionAccount, targetAccount);
			assertThat(count).isEqualTo(1);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：アカウントIDでのupdate")
		void update_by_accountId() {
			Account conditionAccount = Account.builder().accountId("aaaaaaaa").build();
			Account targetAccount = Account.builder().loginFailureCount(1).build();
			Integer count = accountMapper.update(conditionAccount, targetAccount);
			assertThat(count).isEqualTo(1);
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：アカウント名でのupdate")
		void update_by_accountName() {
			Account conditionAccount = Account.builder().accountName("AAAAAAAA").build();
			Account targetAccount = Account.builder().loginFailureCount(1).build();
			Integer count = accountMapper.update(conditionAccount, targetAccount);
			assertThat(count).isEqualTo(1);
		}
		
		@Test
		@Order(5)
		@DisplayName("正常系：パスワードでのupdate")
		void update_by_password() {
			Account conditionAccount = Account.builder().password("$2a$10$password1").build();
			Account targetAccount = Account.builder().loginFailureCount(1).build();
			Integer count = accountMapper.update(conditionAccount, targetAccount);
			assertThat(count).isEqualTo(1);
		}
		
		@Test
		@Order(6)
		@DisplayName("正常系：生年月日でのupdate")
		void update_by_birthdate() {
			Account conditionAccount = Account.builder().birthdate(LocalDate.of(1991, 2, 14)).build();
			Account targetAccount = Account.builder().loginFailureCount(1).build();
			Integer count = accountMapper.update(conditionAccount, targetAccount);
			assertThat(count).isEqualTo(1);
		}
		
		@Test
		@Order(7)
		@DisplayName("正常系：性別区分コードでのupdate")
		void update_by_sexKbnCode() {
			Account conditionAccount = Account.builder().sexKbnCode("man").build();
			Account targetAccount = Account.builder().loginFailureCount(1).build();
			Integer count = accountMapper.update(conditionAccount, targetAccount);
			assertThat(count).isEqualTo(1);
		}
		
		@Test
		@Order(8)
		@DisplayName("正常系：出身都道府県区分コードでのupdate")
		void update_by_birthplacePrefectureKbnCode() {
			Account conditionAccount = Account.builder().birthplacePrefectureKbnCode("Hokkaido").build();
			Account targetAccount = Account.builder().loginFailureCount(1).build();
			Integer count = accountMapper.update(conditionAccount, targetAccount);
			assertThat(count).isEqualTo(1);
		}
		
		@Test
		@Order(9)
		@DisplayName("正常系：在住都道府県区分コードでのupdate")
		void update_by_residentPrefectureKbnCode() {
			Account conditionAccount = Account.builder().residentPrefectureKbnCode("Okinawa").build();
			Account targetAccount = Account.builder().loginFailureCount(1).build();
			Integer count = accountMapper.update(conditionAccount, targetAccount);
			assertThat(count).isEqualTo(1);
		}
		
		@Test
		@Order(10)
		@DisplayName("正常系：フリーメモでのupdate")
		void update_by_freeMemo() {
			Account conditionAccount = Account.builder().freeMemo("フリーメモ").build();
			Account targetAccount = Account.builder().loginFailureCount(1).build();
			Integer count = accountMapper.update(conditionAccount, targetAccount);
			assertThat(count).isEqualTo(1);
		}
		
		@Test
		@Order(11)
		@DisplayName("正常系：権限区分コードでのupdate")
		void update_by_authorityKbnCode() {
			Account conditionAccount = Account.builder().authorityKbnCode("mini-user").build();
			Account targetAccount = Account.builder().loginFailureCount(1).build();
			Integer count = accountMapper.update(conditionAccount, targetAccount);
			assertThat(count).isEqualTo(1);
		}
		
		@Test
		@Order(12)
		@DisplayName("正常系：最終ログイン日時でのupdate")
		void update_by_lastLoginDatetime() {
			Account conditionAccount = Account.builder()
					.lastLoginDatetime(OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.build();
			Account targetAccount = Account.builder().loginFailureCount(1).build();
			Integer count = accountMapper.update(conditionAccount, targetAccount);
			assertThat(count).isEqualTo(1);
		}
		
		@Test
		@Order(13)
		@DisplayName("正常系：ログイン失敗回数でのupdate")
		void update_by_loginFailureCounte() {
			Account conditionAccount = Account.builder().loginFailureCount(2).build();
			Account targetAccount = Account.builder().loginFailureCount(0).build();
			Integer count = accountMapper.update(conditionAccount, targetAccount);
			assertThat(count).isEqualTo(1);
		}
		
		@Test
		@Order(14)
		@DisplayName("正常系：更新対象のレコードなし")
		void update_not_found() {
			Account conditionAccount = Account.builder().accountNo(99).build();
			Account targetAccount = Account.builder().loginFailureCount(0).build();
			Integer count = accountMapper.update(conditionAccount, targetAccount);
			assertThat(count).isEqualTo(0);
		}
		
		@Test
		@Order(15)
		@DisplayName("正常系：2件以上updateの場合")
		void update_accounts() {
			Account conditionAccount = Account.builder().authorityKbnCode("special-user").build();
			Account targetAccount = Account.builder().loginFailureCount(1).build();
			Integer count = accountMapper.update(conditionAccount, targetAccount);
			assertThat(count).isEqualTo(2);
		}
		
		@Test
		@Order(16)
		@DisplayName("正常系：複数の条件でupdateする場合")
		void update_some_conditions() {
			Account conditionAccount = Account.builder()
					.accountId("llllllll")
					.sexKbnCode("woman")
					.birthplacePrefectureKbnCode("Okinawa")
					.residentPrefectureKbnCode("Tokyo")
					.freeMemo("よろしく")
					.build();
			Account targetAccount = Account.builder().loginFailureCount(0).build();
			Integer count = accountMapper.update(conditionAccount, targetAccount);
			assertThat(count).isEqualTo(1);
		}
	}
	
	@Nested
	@Order(5)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/AccountMapperTest.sql")
	class delete {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウント番号でのdelete")
		void delete_by_accountNo() {
			Account deleteAccount = Account.builder().accountNo(1).build();
			Integer deleteCount = accountMapper.delete(deleteAccount);
			assertEquals(deleteCount, 1);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：削除フラグでのdelete")
		void delete_by_isDeleted() {
			Account deleteAccount = Account.builder().isDeleted(true).build();
			Integer deleteCount = accountMapper.delete(deleteAccount);
			assertEquals(deleteCount, 1);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：アカウントIDでのdelete")
		void delete_by_accountId() {
			Account deleteAccount = Account.builder().accountId("aaaaaaaa").build();
			Integer deleteCount = accountMapper.delete(deleteAccount);
			assertEquals(deleteCount, 1);
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：アカウント名でのdelete")
		void delete_by_accountName() {
			Account deleteAccount = Account.builder().accountName("AAAAAAAA").build();
			Integer deleteCount = accountMapper.delete(deleteAccount);
			assertEquals(deleteCount, 1);
		}
		
		@Test
		@Order(5)
		@DisplayName("正常系：パスワードでのdelete")
		void delete_by_password() {
			Account deleteAccount = Account.builder().password("$2a$10$password1").build();
			Integer deleteCount = accountMapper.delete(deleteAccount);
			assertEquals(deleteCount, 1);
		}
		
		@Test
		@Order(6)
		@DisplayName("正常系：生年月日でのdelete")
		void delete_by_birthdate() {
			Account deleteAccount = Account.builder().birthdate(LocalDate.of(1991, 2, 14)).build();
			Integer deleteCount = accountMapper.delete(deleteAccount);
			assertEquals(deleteCount, 1);
		}
		
		@Test
		@Order(7)
		@DisplayName("正常系：性別区分コードでのdelete")
		void delete_by_sexKbnCode() {
			Account deleteAccount = Account.builder().sexKbnCode("man").build();
			Integer deleteCount = accountMapper.delete(deleteAccount);
			assertEquals(deleteCount, 1);
		}
		
		@Test
		@Order(8)
		@DisplayName("正常系：出身都道府県区分コードでのdelete")
		void delete_by_birthplacePrefectureKbnCode() {
			Account deleteAccount = Account.builder().birthplacePrefectureKbnCode("Hokkaido").build();
			Integer deleteCount = accountMapper.delete(deleteAccount);
			assertEquals(deleteCount, 1);
		}
		
		@Test
		@Order(9)
		@DisplayName("正常系：在住都道府県区分コードでのdelete")
		void delete_by_residentPrefectureKbnCode() {
			Account deleteAccount = Account.builder().residentPrefectureKbnCode("Okinawa").build();
			Integer deleteCount = accountMapper.delete(deleteAccount);
			assertEquals(deleteCount, 1);
		}
		
		@Test
		@Order(10)
		@DisplayName("正常系：フリーメモでのdelete")
		void delete_by_freeMemo() {
			Account deleteAccount = Account.builder().freeMemo("フリーメモ").build();
			Integer deleteCount = accountMapper.delete(deleteAccount);
			assertEquals(deleteCount, 1);
		}
		
		@Test
		@Order(11)
		@DisplayName("正常系：権限区分コードでのdelete")
		void delete_by_authorityKbnCode() {
			Account deleteAccount = Account.builder().authorityKbnCode("mini-user").build();
			Integer deleteCount = accountMapper.delete(deleteAccount);
			assertEquals(deleteCount, 1);
		}
		
		@Test
		@Order(12)
		@DisplayName("正常系：最終ログイン日時でのdelete")
		void delete_by_lastLoginDatetime() {
			Account deleteAccount = Account.builder()
					.lastLoginDatetime(OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.build();
			Integer deleteCount = accountMapper.delete(deleteAccount);
			assertEquals(deleteCount, 1);
		}
		
		@Test
		@Order(13)
		@DisplayName("正常系：ログイン失敗回数でのdelete")
		void delete_by_loginFailureCount() {
			Account deleteAccount = Account.builder().loginFailureCount(2).build();
			Integer deleteCount = accountMapper.delete(deleteAccount);
			assertEquals(deleteCount, 1);
		}
		
		@Test
		@Order(14)
		@DisplayName("正常系：削除対象のレコードなし")
		void delete_not_found() {
			Account deleteAccount = Account.builder().accountNo(99).build();
			Integer deleteCount = accountMapper.delete(deleteAccount);
			assertEquals(deleteCount, 0);
		}
		
		@Test
		@Order(15)
		@DisplayName("正常系：2件以上deleteする場合")
		void delete_accounts() {
			Account deleteAccount = Account.builder().authorityKbnCode("special-user").build();
			Integer deleteCount = accountMapper.delete(deleteAccount);
			assertEquals(deleteCount, 2);
		}
		
		@Test
		@Order(16)
		@DisplayName("正常系：複数の条件でdeleteする場合")
		void delete_some_conditions() {
			Account deleteAccount = Account.builder()
					.accountId("llllllll")
					.sexKbnCode("woman")
					.birthplacePrefectureKbnCode("Okinawa")
					.residentPrefectureKbnCode("Tokyo")
					.freeMemo("よろしく")
					.build();
			Integer deleteCount = accountMapper.delete(deleteAccount);
			assertEquals(deleteCount, 1);
		}
	}
	
	@Nested
	@Order(6)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/AccountMapperTest.sql")
	class isExistAccount {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウントIDが一致するアカウントが存在する")
		void isExistAccount_by_accountId_exists() {
			Account account = Account.builder().accountId("aaaaaaaa").build();
			Boolean isExist = accountMapper.isExistAccount(account);
			assertTrue(isExist);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：アカウントIDが一致するアカウントが存在しない")
		void isExistAccount_by_accountId_not_exist() {
			Account account = Account.builder().accountId("xxxxxxxx").build();
			Boolean isExist = accountMapper.isExistAccount(account);
			assertFalse(isExist);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：アカウント番号以外で、アカウントIDが一致するアカウントが存在しない")
		void isExistAccount_by_accountId_and_accountNo_exists() {
			Account account = Account.builder()
					.accountNo(1)
					.accountId("aaaaaaaa")
					.build();
			Boolean isExist = accountMapper.isExistAccount(account);
			assertFalse(isExist);
		}
		
		@Test
		@DisplayName("正常系：アカウント番号以外で、アカウントIDが一致するアカウントが存在する")
		void isExistAccount_by_accountId_and_accountNo_not_exist() {
			Account account = Account.builder()
					.accountNo(1)
					.accountId("bbbbbbbb")
					.build();
			Boolean isExist = accountMapper.isExistAccount(account);
			assertTrue(isExist);
		}
	}
}