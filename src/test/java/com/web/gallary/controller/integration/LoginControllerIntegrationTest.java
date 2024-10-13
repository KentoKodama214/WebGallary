package com.web.gallary.controller.integration;

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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.web.gallary.controller.LoginController;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
public class LoginControllerIntegrationTest {
	@Autowired
	private LoginController loginController;
	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class login {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void login_success() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class success {
		@Test
		@Order(1)
		@DisplayName("正常系：非ログインユーザーの場合")
		void success_not_login_user() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：ログインユーザーの場合")
		void success_login_user() {
			assertTrue(false);
		}
	}
}