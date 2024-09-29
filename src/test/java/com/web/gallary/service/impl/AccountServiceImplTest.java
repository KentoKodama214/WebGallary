package com.web.gallary.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class AccountServiceImplTest {

	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
	class registAccount {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウントを新規登録")
		void registAccount_success() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：アカウントが既に存在する")
		void registAccount_account_already_exist() {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：RegistFailureExceptionをthrowする")
		void registAccount_RegistFailureException() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(3)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class updateAccount {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウントを更新")
		void updateAccount_success() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：アカウントが既に存在する")
		void updateAccount_account_already_exist() {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：UpdateFailureExceptionをthrowする")
		void updateAccount_UpdateFailureException() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(4)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
	class handleAuthenticationSuccess {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void handle_success() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("異常系：UpdateFailureExceptionをthrowする")
		void handle_UpdateFailureException() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(7)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class handleAuthenticationFailureBadCredentials {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウントが存在する場合")
		void handle_account_found() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：アカウントが存在しない場合")
		void handle_account_not_found() {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：UpdateFailureExceptionをthrowする")
		void handle_UpdateFailureException() {
			assertTrue(false);
		}
	}
}