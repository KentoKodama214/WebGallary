package com.web.gallary.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

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
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.servlet.ModelAndView;

import com.web.gallary.enumuration.ErrorValues;
import com.web.gallary.exception.ForbiddenAccountException;
import com.web.gallary.exception.PhotoNotFoundException;
import com.web.gallary.helper.SessionHelper;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class CommonControllerAdviceTest {
	@InjectMocks
	private CommonControllerAdvice commonControllerAdvice;
	
	@Mock
	private SessionHelper sessionHelper;
	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class handleFileForbiddenAccountException {
		@Test
		@Order(1)
		@DisplayName("正常系：非ログインユーザーの場合")
		void handleFileForbiddenAccountException_not_login_user() {
			doReturn(null).when(sessionHelper).getAccountId();
			ForbiddenAccountException exception = new ForbiddenAccountException(ErrorValues.EC0000);
			
			ModelAndView actual = commonControllerAdvice.handleFileForbiddenAccountException(exception);
			assertEquals("error_page", actual.getViewName());
			assertEquals(HttpStatus.FORBIDDEN, actual.getStatus());
			Map<String, Object> models = actual.getModel();
			assertEquals(HttpStatus.FORBIDDEN.value(), (int)models.get("httpStatus"));
			assertEquals(ErrorValues.EC0000.getErrorCode(), models.get("errorCode").toString());
			assertEquals(ErrorValues.EC0000.getErrorMessage(), models.get("errorMessage").toString());
			assertEquals("/login", models.get("goBackPageUrl").toString());
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：ログインユーザーの場合")
		void handleFileForbiddenAccountException_login_user() {
			String accountId = "aaaaaaaa";
			doReturn(accountId).when(sessionHelper).getAccountId();
			ForbiddenAccountException exception = new ForbiddenAccountException(ErrorValues.EC0000);
			
			ModelAndView actual = commonControllerAdvice.handleFileForbiddenAccountException(exception);
			assertEquals("error_page", actual.getViewName());
			assertEquals(HttpStatus.FORBIDDEN, actual.getStatus());
			Map<String, Object> models = actual.getModel();
			assertEquals(HttpStatus.FORBIDDEN.value(), (int)models.get("httpStatus"));
			assertEquals(ErrorValues.EC0000.getErrorCode(), models.get("errorCode").toString());
			assertEquals(ErrorValues.EC0000.getErrorMessage(), models.get("errorMessage").toString());
			assertEquals("/photo/" + accountId + "/photo_list", models.get("goBackPageUrl").toString());
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
			doReturn(null).when(sessionHelper).getAccountId();
			PhotoNotFoundException exception = new PhotoNotFoundException(ErrorValues.EP0001);
			
			ModelAndView actual = commonControllerAdvice.handlePhotoNotFoundException(exception);
			assertEquals("error_page", actual.getViewName());
			assertEquals(HttpStatus.BAD_REQUEST, actual.getStatus());
			Map<String, Object> models = actual.getModel();
			assertEquals(HttpStatus.BAD_REQUEST.value(), (int)models.get("httpStatus"));
			assertEquals(ErrorValues.EP0001.getErrorCode(), models.get("errorCode").toString());
			assertEquals(ErrorValues.EP0001.getErrorMessage(), models.get("errorMessage").toString());
			assertEquals("/login", models.get("goBackPageUrl").toString());
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：ログインユーザーの場合")
		void handlePhotoNotFoundException_login_user() {
			String accountId = "aaaaaaaa";
			doReturn(accountId).when(sessionHelper).getAccountId();
			PhotoNotFoundException exception = new PhotoNotFoundException(ErrorValues.EP0001);
			
			ModelAndView actual = commonControllerAdvice.handlePhotoNotFoundException(exception);
			assertEquals("error_page", actual.getViewName());
			assertEquals(HttpStatus.BAD_REQUEST, actual.getStatus());
			Map<String, Object> models = actual.getModel();
			assertEquals(HttpStatus.BAD_REQUEST.value(), (int)models.get("httpStatus"));
			assertEquals(ErrorValues.EP0001.getErrorCode(), models.get("errorCode").toString());
			assertEquals(ErrorValues.EP0001.getErrorMessage(), models.get("errorMessage").toString());
			assertEquals("/photo/" + accountId + "/photo_list", models.get("goBackPageUrl").toString());
		}
	}
	
	@Nested
	@Order(3)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class getGoBackPageUrl {
		@Test
		@Order(1)
		@DisplayName("正常系：非ログインユーザーの場合")
		void getGoBackPageUrl_not_login_user() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
			Method goBackPageUrl = CommonControllerAdvice.class.getDeclaredMethod("getGoBackPageUrl");
			goBackPageUrl.setAccessible(true);
			doReturn(null).when(sessionHelper).getAccountId();
			assertEquals("/login", (String) goBackPageUrl.invoke(commonControllerAdvice));
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：ログインユーザーの場合")
		void getGoBackPageUrl_login_user() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
			Method goBackPageUrl = CommonControllerAdvice.class.getDeclaredMethod("getGoBackPageUrl");
			goBackPageUrl.setAccessible(true);
			doReturn("aaaaaaaa").when(sessionHelper).getAccountId();
			assertEquals("/photo/aaaaaaaa/photo_list", (String) goBackPageUrl.invoke(commonControllerAdvice));
		}
	}
}