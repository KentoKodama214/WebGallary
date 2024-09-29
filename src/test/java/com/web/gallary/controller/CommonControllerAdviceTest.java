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
public class CommonControllerAdviceTest {
	
	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class handleFileForbiddenAccountException {
		@Test
		@Order(1)
		@DisplayName("正常系：非ログインユーザーの場合")
		void handleFileForbiddenAccountException_not_login_user() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：ログインユーザーの場合")
		void handleFileForbiddenAccountException_login_user() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class handlePhotoNotFoundException {
		@Test
		@Order(1)
		@DisplayName("正常系：非ログインユーザーの場合")
		void handlePhotoNotFoundException_not_login_user() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：ログインユーザーの場合")
		void handlePhotoNotFoundException_login_user() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(3)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class getGoBackPageUrl {
		@Test
		@Order(1)
		@DisplayName("正常系：非ログインユーザーの場合")
		void getGoBackPageUrl_not_login_user() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：ログインユーザーの場合")
		void getGoBackPageUrl_login_user() {
			assertTrue(false);
		}
	}
}