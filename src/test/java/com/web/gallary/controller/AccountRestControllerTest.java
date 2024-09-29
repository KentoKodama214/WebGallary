package com.web.gallary.controller;

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
public class AccountRestControllerTest {

	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class register {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void register_regist_success() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：既に使われているアカウントIDの場合")
		void register_exist_accountId() {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：BadRequestExceptionをthrowする")
		void account_setting_BadRequestException() {
			// TODO 各種パラメータの不正パターンを網羅
			assertTrue(false);
		}
		
		@Test
		@Order(4)
		@DisplayName("異常系：RegistFailureExceptionをthrowする")
		void account_setting_RegistFailureException() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class update {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウントID、パスワード変更なし")
		void update_not_change_accountID_and_password() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：アカウントID変更あり、パスワード変更なし")
		void update_not_change_password() {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：アカウントID変更なし、パスワード変更あり")
		void update_not_change_accountId() {
			assertTrue(false);
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：アカウントID変更あり、パスワード変更あり")
		void update_change_accountId_and_password() {
			assertTrue(false);
		}
		
		@Test
		@Order(5)
		@DisplayName("正常系：アカウントID重複")
		void update_duplicate_accountId() {
			assertTrue(false);
		}
		
		@Test
		@Order(6)
		@DisplayName("異常系：パスワード以外のパラメータ不正あり")
		void update_BadRequestException() {
			// TODO 各種パラメータの不正パターンを網羅
			assertTrue(false);
		}
		
		@Test
		@Order(7)
		@DisplayName("異常系：UpdateFailureExceptionをthrowする")
		void update_UpdateFailureException() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(3)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class handleInsertFailedException {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void handleInsertFailedException_success() {
			assertTrue(false);
		}
	}
}