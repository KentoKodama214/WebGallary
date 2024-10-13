package com.web.gallary.service.impl.integration;

import static org.junit.jupiter.api.Assertions.*;

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

import com.web.gallary.exception.RegistFailureException;
import com.web.gallary.exception.UpdateFailureException;
import com.web.gallary.service.impl.AccountServiceImpl;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
@Sql("/sql/common/ResetAccountNoSeq.sql")
public class AccountServiceImplIntegrationTest {
	@Autowired
	private AccountServiceImpl accountServiceImpl;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/service/AccountServiceImplIntegrationTest.sql")
	class loadUserByUsername {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void loadUserByUsername_success() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("異常系：UsernameNotFoundExceptionをthrowする")
		void loadUserByUsername_UsernameNotFoundException() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/service/AccountServiceImplIntegrationTest.sql")
	class registAccount {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウントを新規登録")
		void registAccount_success() throws RegistFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：アカウントが既に存在する")
		void registAccount_account_already_exist() throws RegistFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：RegistFailureExceptionをthrowする")
		void registAccount_RegistFailureException() throws RegistFailureException {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(3)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/service/AccountServiceImplIntegrationTest.sql")
	class updateAccount {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウントを更新")
		void updateAccount_success() throws UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：アカウントが既に存在する")
		void updateAccount_account_already_exist() throws UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：UpdateFailureExceptionをthrowする")
		void updateAccount_UpdateFailureException() throws UpdateFailureException {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(4)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/service/AccountServiceImplIntegrationTest.sql")
	class getAccountById {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウントが存在する場合")
		void getAccountById_found() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：アカウントが存在しない場合、nullを返す")
		void getAccountById_not_found() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(5)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/service/AccountServiceImplIntegrationTest.sql")
	class getAccountList {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウントが存在する場合")
		void getAccountList_found() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：アカウントが存在しない場合")
		void getAccountList_not_found() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(6)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/service/AccountServiceImplIntegrationTest.sql")
	class handleAuthenticationSuccess {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void handle_success() throws UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("異常系：UpdateFailureExceptionをthrowする")
		void handle_UpdateFailureException() throws UpdateFailureException {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(7)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/service/AccountServiceImplIntegrationTest.sql")
	class handleAuthenticationFailureBadCredentials {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウントが存在する場合")
		void handle_account_found() throws UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：アカウントが存在しない場合")
		void handle_account_not_found() throws UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：UpdateFailureExceptionをthrowする")
		void handle_UpdateFailureException() throws UpdateFailureException {
			assertTrue(false);
		}
	}
}