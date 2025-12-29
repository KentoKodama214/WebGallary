package com.web.gallary.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.web.gallary.controller.request.ErrorRequest;
import com.web.gallary.controller.response.BadRequestResponse;
import com.web.gallary.enumuration.ErrorValueEnum;
import com.web.gallary.exception.BadRequestException;
import com.web.gallary.exception.FileDuplicateException;
import com.web.gallary.exception.ForbiddenAccountException;
import com.web.gallary.exception.PhotoNotAdditableException;
import com.web.gallary.exception.RegistFailureException;
import com.web.gallary.exception.UpdateFailureException;
import com.web.gallary.helper.SessionHelper;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class CommonRestControllerAdviceTest {
	@InjectMocks
	private CommonRestControllerAdvice commonRestControllerAdvice;
	
	@Mock
	private SessionHelper sessionHelper;
	
	@Nested
	@Order(1)
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class handleBadRequestException {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void handleBadRequestException_not_login_user() {
			BadRequestException exception = new BadRequestException(ErrorValueEnum.INVALID_INPUT);
			
			ResponseEntity<BadRequestResponse> actual
				= commonRestControllerAdvice.handleBadRequestException(exception);
			
			assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
			assertEquals(HttpStatus.BAD_REQUEST.value(), actual.getBody().getHttpStatus());
			assertEquals(false, actual.getBody().getIsSuccess());
			assertEquals(ErrorValueEnum.INVALID_INPUT.getErrorMessage(), actual.getBody().getMessage());
		}
	}
	
	@Nested
	@Order(2)
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class handleFileForbiddenAccountException {
		@Test
		@Order(1)
		@DisplayName("正常系：非ログインユーザーの場合")
		void handleFileForbiddenAccountException_not_login_user() {
			doReturn(null).when(sessionHelper).getAccountId();
			ForbiddenAccountException exception = new ForbiddenAccountException(ErrorValueEnum.INVALID_INPUT);
			
			ResponseEntity<ErrorRequest> actual
				= commonRestControllerAdvice.handleForbiddenAccountException(exception);
			
			assertEquals(HttpStatus.FORBIDDEN, actual.getStatusCode());
			assertEquals(HttpStatus.FORBIDDEN.value(), actual.getBody().getHttpStatus());
			assertEquals(ErrorValueEnum.INVALID_INPUT.getErrorMessage(), actual.getBody().getErrorMessage());
			assertEquals("/login", actual.getBody().getGoBackPageUrl());
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：ログインユーザーの場合")
		void handleFileForbiddenAccountException_login_user() {
			String accountId = "aaaaaaaa";
			doReturn(accountId).when(sessionHelper).getAccountId();
			ForbiddenAccountException exception = new ForbiddenAccountException(ErrorValueEnum.INVALID_INPUT);
			
			ResponseEntity<ErrorRequest> actual
				= commonRestControllerAdvice.handleForbiddenAccountException(exception);
			
			assertEquals(HttpStatus.FORBIDDEN, actual.getStatusCode());
			assertEquals(HttpStatus.FORBIDDEN.value(), actual.getBody().getHttpStatus());
			assertEquals(ErrorValueEnum.INVALID_INPUT.getErrorMessage(), actual.getBody().getErrorMessage());
			assertEquals("/photo/" + accountId + "/photo_list", actual.getBody().getGoBackPageUrl());
		}
	}
	
	@Nested
	@Order(3)
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class handleFileDuplicateException {
		@Test
		@Order(1)
		@DisplayName("正常系：非ログインユーザーの場合")
		void handleFileDuplicateException_not_login_user() {
			doReturn(null).when(sessionHelper).getAccountId();
			FileDuplicateException exception = new FileDuplicateException(ErrorValueEnum.INVALID_INPUT);
			
			ResponseEntity<ErrorRequest> actual
				= commonRestControllerAdvice.handleFileDuplicateException(exception);
			
			assertEquals(HttpStatus.CONFLICT, actual.getStatusCode());
			assertEquals(HttpStatus.CONFLICT.value(), actual.getBody().getHttpStatus());
			assertEquals(ErrorValueEnum.INVALID_INPUT.getErrorMessage(), actual.getBody().getErrorMessage());
			assertEquals("/login", actual.getBody().getGoBackPageUrl());
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：ログインユーザーの場合")
		void handleFileDuplicateException_login_user() {
			String accountId = "aaaaaaaa";
			doReturn(accountId).when(sessionHelper).getAccountId();
			FileDuplicateException exception = new FileDuplicateException(ErrorValueEnum.INVALID_INPUT);
			
			ResponseEntity<ErrorRequest> actual
				= commonRestControllerAdvice.handleFileDuplicateException(exception);
			
			assertEquals(HttpStatus.CONFLICT, actual.getStatusCode());
			assertEquals(HttpStatus.CONFLICT.value(), actual.getBody().getHttpStatus());
			assertEquals(ErrorValueEnum.INVALID_INPUT.getErrorMessage(), actual.getBody().getErrorMessage());
			assertEquals("/photo/" + accountId + "/photo_list", actual.getBody().getGoBackPageUrl());
		}
	}
	
	@Nested
	@Order(4)
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class handlePhotoNotAdditableException {
		@Test
		@Order(1)
		@DisplayName("正常系：非ログインユーザーの場合")
		void handlePhotoNotAdditableException_not_login_user() {
			doReturn(null).when(sessionHelper).getAccountId();
			PhotoNotAdditableException exception = new PhotoNotAdditableException(ErrorValueEnum.INVALID_INPUT);
			
			ResponseEntity<ErrorRequest> actual
				= commonRestControllerAdvice.handlePhotoNotAdditableException(exception);
			
			assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
			assertEquals(HttpStatus.BAD_REQUEST.value(), actual.getBody().getHttpStatus());
			assertEquals(ErrorValueEnum.INVALID_INPUT.getErrorMessage(), actual.getBody().getErrorMessage());
			assertEquals("/login", actual.getBody().getGoBackPageUrl());
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：ログインユーザーの場合")
		void handlePhotoNotAdditableException_login_user() {
			String accountId = "aaaaaaaa";
			doReturn(accountId).when(sessionHelper).getAccountId();
			PhotoNotAdditableException exception = new PhotoNotAdditableException(ErrorValueEnum.INVALID_INPUT);
			
			ResponseEntity<ErrorRequest> actual
				= commonRestControllerAdvice.handlePhotoNotAdditableException(exception);
			
			assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
			assertEquals(HttpStatus.BAD_REQUEST.value(), actual.getBody().getHttpStatus());
			assertEquals(ErrorValueEnum.INVALID_INPUT.getErrorMessage(), actual.getBody().getErrorMessage());
			assertEquals("/photo/" + accountId + "/photo_list", actual.getBody().getGoBackPageUrl());
		}
	}
	
	@Nested
	@Order(5)
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class handleInsertFailedException {
		@Test
		@Order(1)
		@DisplayName("正常系：非ログインユーザーの場合")
		void handleInsertFailedException_not_login_user() {
			doReturn(null).when(sessionHelper).getAccountId();
			RegistFailureException exception = new RegistFailureException(ErrorValueEnum.INVALID_INPUT);
			
			ResponseEntity<ErrorRequest> actual
				= commonRestControllerAdvice.handleInsertFailedException(exception);
			
			assertEquals(HttpStatus.CONFLICT, actual.getStatusCode());
			assertEquals(HttpStatus.CONFLICT.value(), actual.getBody().getHttpStatus());
			assertEquals(ErrorValueEnum.INVALID_INPUT.getErrorMessage(), actual.getBody().getErrorMessage());
			assertEquals("/login", actual.getBody().getGoBackPageUrl());
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：ログインユーザーの場合")
		void handleInsertFailedException_login_user() {
			String accountId = "aaaaaaaa";
			doReturn(accountId).when(sessionHelper).getAccountId();
			RegistFailureException exception = new RegistFailureException(ErrorValueEnum.INVALID_INPUT);
			
			ResponseEntity<ErrorRequest> actual
				= commonRestControllerAdvice.handleInsertFailedException(exception);
			
			assertEquals(HttpStatus.CONFLICT, actual.getStatusCode());
			assertEquals(HttpStatus.CONFLICT.value(), actual.getBody().getHttpStatus());
			assertEquals(ErrorValueEnum.INVALID_INPUT.getErrorMessage(), actual.getBody().getErrorMessage());
			assertEquals("/photo/" + accountId + "/photo_list", actual.getBody().getGoBackPageUrl());
		}
	}
	
	@Nested
	@Order(6)
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class handleUpdateFailureException {
		@Test
		@Order(1)
		@DisplayName("正常系：非ログインユーザーの場合")
		void handleUpdateFailureException_not_login_user() {
			doReturn(null).when(sessionHelper).getAccountId();
			UpdateFailureException exception = new UpdateFailureException(ErrorValueEnum.INVALID_INPUT);
			
			ResponseEntity<ErrorRequest> actual
				= commonRestControllerAdvice.handleUpdateFailureException(exception);
			
			assertEquals(HttpStatus.CONFLICT, actual.getStatusCode());
			assertEquals(HttpStatus.CONFLICT.value(), actual.getBody().getHttpStatus());
			assertEquals(ErrorValueEnum.INVALID_INPUT.getErrorMessage(), actual.getBody().getErrorMessage());
			assertEquals("/login", actual.getBody().getGoBackPageUrl());
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：ログインユーザーの場合")
		void handleUpdateFailureException_login_user() {
			String accountId = "aaaaaaaa";
			doReturn(accountId).when(sessionHelper).getAccountId();
			UpdateFailureException exception = new UpdateFailureException(ErrorValueEnum.INVALID_INPUT);
			
			ResponseEntity<ErrorRequest> actual
				= commonRestControllerAdvice.handleUpdateFailureException(exception);
			
			assertEquals(HttpStatus.CONFLICT, actual.getStatusCode());
			assertEquals(HttpStatus.CONFLICT.value(), actual.getBody().getHttpStatus());
			assertEquals(ErrorValueEnum.INVALID_INPUT.getErrorMessage(), actual.getBody().getErrorMessage());
			assertEquals("/photo/" + accountId + "/photo_list", actual.getBody().getGoBackPageUrl());
		}
	}
	
	@Nested
	@Order(7)
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class getGoBackPageUrl {
		@Test
		@Order(1)
		@DisplayName("正常系：非ログインユーザーの場合")
		void getGoBackPageUrl_not_login_user() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
			Method goBackPageUrl = CommonRestControllerAdvice.class.getDeclaredMethod("getGoBackPageUrl");
			goBackPageUrl.setAccessible(true);
			doReturn(null).when(sessionHelper).getAccountId();
			assertEquals("/login", (String) goBackPageUrl.invoke(commonRestControllerAdvice));
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：ログインユーザーの場合")
		void getGoBackPageUrl_login_user() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
			Method goBackPageUrl = CommonRestControllerAdvice.class.getDeclaredMethod("getGoBackPageUrl");
			goBackPageUrl.setAccessible(true);
			doReturn("aaaaaaaa").when(sessionHelper).getAccountId();
			assertEquals("/photo/aaaaaaaa/photo_list", (String) goBackPageUrl.invoke(commonRestControllerAdvice));
		}
	}
}