package com.web.gallary.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.servlet.ModelAndView;

import com.web.gallary.helper.SessionHelper;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {
	@InjectMocks
	private LoginController loginController;
	
	@Mock
	private SessionHelper sessionHelper;
	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class login {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void login_success() {
			ModelAndView actual = loginController.login();
			assertEquals("login", actual.getViewName());
			assertEquals(new HashMap<>(), actual.getModel());
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
			doReturn(null).when(sessionHelper).getAccountId();
			assertEquals("redirect:/login", loginController.success());
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：ログインユーザーの場合")
		void success_login_user() {
			String accountId = "aaaaaaaa";
			doReturn(accountId).when(sessionHelper).getAccountId();
			assertEquals("redirect:/photo/" + accountId + "/photo_list", loginController.success());
		}
	}
}