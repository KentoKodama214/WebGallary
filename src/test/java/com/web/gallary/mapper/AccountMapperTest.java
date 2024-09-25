package com.web.gallary.mapper;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
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
	
	private LocalDateTime now;
	
	@BeforeEach
	void setup() {
		Instant fixedInstant = Instant.parse("2024-01-01T00:00:00Z");
        Clock fixedClock = Clock.fixed(fixedInstant, ZoneId.of("Asia/Tokyo"));
        now = LocalDateTime.now(fixedClock);
	}
	
	@Nested
	@Sql("/sql/AccountMapperTest.sql")
	class select {
		@Test
		@DisplayName("正常系 アカウント番号でのselectで1件の場合")
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
		
//		@Test
//		@DisplayName("select: 正常系 削除フラグでのselectで1件の場合")
//		void select_by_isDeleted() {
//			
//		}
//		
//		@Test
//		@DisplayName("select: 正常系 アカウントIDでのselectで1件の場合")
//		void select_by_accountId() {
//			
//		}
//		
//		@Test
//		@DisplayName("select: 正常系 アカウント名でのselectで1件の場合")
//		void select_by_accountName() {
//			
//		}
//		
//		@Test
//		@DisplayName("select: 正常系 パスワードでのselectで1件の場合")
//		void select_by_password() {
//			
//		}
//		
//		@Test
//		@DisplayName("select: 正常系 性別区分コードでのselectで1件の場合")
//		void select_by_sexKbnCode() {
//			
//		}
//		
//		@Test
//		@DisplayName("select: 正常系 出身都道府県区分コードでのselectで1件の場合")
//		void select_by_birthplacePrefectureKbnCode() {
//			
//		}
//		
//		@Test
//		@DisplayName("select: 正常系 在住都道府県区分コードでのselectで1件の場合")
//		void select_by_residentPrefectureKbnCode() {
//			
//		}
//		
//		@Test
//		@DisplayName("select: 正常系 フリーメモでのselectで1件の場合")
//		void select_by_freeMemo() {
//			
//		}
//		
//		@Test
//		@DisplayName("select: 正常系 権限区分コードでのselectで1件の場合")
//		void select_by_authorityKbnCode() {
//			
//		}
//		
//		@Test
//		@DisplayName("select: 正常系 最終ログイン日時でのselectで1件の場合")
//		void select_by_lastLoginDatetime() {
//			
//		}
//		
//		@Test
//		@DisplayName("select: 正常系 ログイン失敗回数でのselectで1件の場合")
//		void select_by_loginFailureCount() {
//			
//		}
//		
//		@Test
//		@DisplayName("select: 正常系 selectで0件の場合")
//		void select_not_fount() {
//			
//		}
//		
//		@Test
//		@DisplayName("select: 正常系 selectで2件以上の場合")
//		void select_accounts() {
//			
//		}
		
//		@Test
//		@DisplayName("select: 正常系 複数の条件でselectする場合")
//		void select_some_conditions() {
//			
//		}
	}

	@Nested
	@Sql("/sql/AccountMapperTest.sql")
	class count {
		@Test
		@DisplayName("count: 正常系 アカウント番号でのcount")
		void count_by_accountNo() {
			Account account = Account.builder().accountNo(1).build();
			Integer count = accountMapper.count(account);
			assertEquals(count, 1);
		}
		
		// TODO
	}
	
	@Nested
	class insert {
		@Test
		@DisplayName("insert: 正常系 登録成功")
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
			
//			List<Account> expectedAccountList = jdbcTemplate.queryForList("SELECT * FROM common.account", Account.class);
//	        assertEquals(1, expectedAccountList.size());
//			assertThat(expectedAccountList.getFirst()).isEqualTo(insertAccount);
		}
	}
	
	@Nested
	@Sql("/sql/AccountMapperTest.sql")
	class update {
		@Test
		@DisplayName("update: 正常系 更新成功")
		void update_success() {
			
		}
		
		// TODO
		
		@Test
		@DisplayName("update: 正常系 更新対象のレコードなし")
		void update_not_found() {
			
		}
	}
	
	@Nested
	@Sql("/sql/AccountMapperTest.sql")
	class delete {
		@Test
		@DisplayName("delete: 正常系 アカウント番号での削除")
		void delete_by_accountNo() {
			Account deleteAccount = Account.builder().accountNo(1).build();
			Integer deleteCount = accountMapper.delete(deleteAccount);
			assertEquals(deleteCount, 1);
		}
		
		// TODO 
		
		@Test
		@DisplayName("delete: 正常系 削除対象のレコードなし")
		void delete_not_found() {
			Account deleteAccount = Account.builder().accountNo(99).build();
			Integer deleteCount = accountMapper.delete(deleteAccount);
			assertEquals(deleteCount, 0);
		}
	}
	
	@Nested
	@Sql("/sql/AccountMapperTest.sql")
	class isExistAccount {
//		@Test
//		@DisplayName("isExistAccount: 正常系 アカウントIDが一致するアカウントが存在する")
//		void isExistAccount_by_accountId_exists() {
//			
//		}
//		
//		@Test
//		@DisplayName("isExistAccount: 正常系 アカウントIDが一致するアカウントが存在しない")
//		void isExistAccount_by_accountId_not_exist() {
//			
//		}
//		
//		@Test
//		@DisplayName("isExistAccount: 正常系 アカウント番号・アカウントIDが一致するアカウントが存在する")
//		void isExistAccount_by_accountId_and_accountNo_exists() {
//			
//		}
//		
//		@Test
//		@DisplayName("isExistAccount: 正常系 アカウント番号・アカウントIDが一致するアカウントが存在しない")
//		void isExistAccount_by_accountId_and_accountNo_not_exist() {
//			
//		}
	}
}