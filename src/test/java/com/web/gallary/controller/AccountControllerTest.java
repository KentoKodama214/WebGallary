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
public class AccountControllerTest {

	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class register {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void register_success() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class account_setting {
		@Test
		@Order(1)
		@DisplayName("正常系：生年月日が1900/01/01の場合")
		void account_setting_birthdate_is_19000101() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：生年月日が1900/01/01以外の場合")
		void account_setting_birthdate_is_not_19000101() {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：ForbiddenAccountExceptionをthrowする")
		void account_setting_ForbiddenAccountException() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(3)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class account_list {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void account_list_success() {
			assertTrue(false);
		}
	}
}