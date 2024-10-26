package com.web.gallary.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.web.gallary.controller.request.PhotoFavoriteDeleteRequest;
import com.web.gallary.controller.request.PhotoFavoriteRegistRequest;
import com.web.gallary.controller.response.PhotoFavoriteResponse;
import com.web.gallary.exception.BadRequestException;
import com.web.gallary.exception.RegistFailureException;
import com.web.gallary.exception.UpdateFailureException;
import com.web.gallary.helper.SessionHelper;
import com.web.gallary.model.PhotoFavoriteModel;
import com.web.gallary.service.impl.PhotoFavoriteServiceImpl;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class PhotoFavoriteControllerTest {
	@InjectMocks
	private PhotoFavoriteController photoFavoriteController;
	
	@Mock
	private PhotoFavoriteServiceImpl photoFavoriteServiceImpl;
	
	@Mock
	private SessionHelper sessionHelper;
	
	@Nested
	@Order(1)
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class addFavorite {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void addFavorite_success() throws BadRequestException, RegistFailureException {
			PhotoFavoriteRegistRequest photoFavoriteRegistRequest = new PhotoFavoriteRegistRequest();
			photoFavoriteRegistRequest.setFavoritePhotoAccountNo(2);
			photoFavoriteRegistRequest.setFavoritePhotoNo(3);
			
			BindingResult result = new DataBinder(photoFavoriteRegistRequest).getBindingResult();
			
			doReturn(1).when(sessionHelper).getAccountNo();
			
			ArgumentCaptor<PhotoFavoriteModel> photoFavoriteModelCaptor = ArgumentCaptor.forClass(PhotoFavoriteModel.class);
			doNothing().when(photoFavoriteServiceImpl).addFavorite(photoFavoriteModelCaptor.capture());
			
			ResponseEntity<PhotoFavoriteResponse> actual
				= photoFavoriteController.addFavorite(photoFavoriteRegistRequest, result);
			
			assertEquals(HttpStatus.OK, actual.getStatusCode());
			assertEquals(HttpStatus.OK.value(), actual.getBody().getHttpStatus());
			assertEquals(true, actual.getBody().getIsSuccess());
			assertEquals("お気に入りに追加しました。", actual.getBody().getMessage());
			
			PhotoFavoriteModel photoFavoriteModel = photoFavoriteModelCaptor.getValue();
			assertEquals(1, photoFavoriteModel.getAccountNo());
			assertEquals(2, photoFavoriteModel.getFavoritePhotoAccountNo());
			assertEquals(3, photoFavoriteModel.getFavoritePhotoNo());
		}
		
		@Test
		@Order(2)
		@DisplayName("異常系：BadRequestExceptionをthrowする")
		void addFavorite_BadRequestException() throws BadRequestException, RegistFailureException {
			PhotoFavoriteRegistRequest photoFavoriteRegistRequest = new PhotoFavoriteRegistRequest();
			photoFavoriteRegistRequest.setFavoritePhotoAccountNo(2);
			photoFavoriteRegistRequest.setFavoritePhotoNo(3);
			
			BindingResult result = new DataBinder(photoFavoriteRegistRequest).getBindingResult();
			FieldError fError = new FieldError("photoFavoriteRegistRequest","favoritePhotoAccountNo", "");
			result.addError((ObjectError) fError);
			
			assertThrows(BadRequestException.class, () -> photoFavoriteController.addFavorite(photoFavoriteRegistRequest, result));
			verify(sessionHelper, times(0)).getAccountNo();
			verify(photoFavoriteServiceImpl, times(0)).addFavorite(any(PhotoFavoriteModel.class));
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：RegistFailureExceptionをthrowする")
		void addFavorite_RegistFailureException() throws BadRequestException, RegistFailureException {
			PhotoFavoriteRegistRequest photoFavoriteRegistRequest = new PhotoFavoriteRegistRequest();
			photoFavoriteRegistRequest.setFavoritePhotoAccountNo(2);
			photoFavoriteRegistRequest.setFavoritePhotoNo(3);
			
			BindingResult result = new DataBinder(photoFavoriteRegistRequest).getBindingResult();
			
			doReturn(1).when(sessionHelper).getAccountNo();
			
			ArgumentCaptor<PhotoFavoriteModel> photoFavoriteModelCaptor = ArgumentCaptor.forClass(PhotoFavoriteModel.class);
			doThrow(RegistFailureException.class).when(photoFavoriteServiceImpl).addFavorite(photoFavoriteModelCaptor.capture());
			
			assertThrows(RegistFailureException.class, () -> photoFavoriteController.addFavorite(photoFavoriteRegistRequest, result));
			
			PhotoFavoriteModel photoFavoriteModel = photoFavoriteModelCaptor.getValue();
			assertEquals(1, photoFavoriteModel.getAccountNo());
			assertEquals(2, photoFavoriteModel.getFavoritePhotoAccountNo());
			assertEquals(3, photoFavoriteModel.getFavoritePhotoNo());
		}
	}
	
	@Nested
	@Order(2)
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class deleteFavorite {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void deleteFavorite_success() throws BadRequestException, UpdateFailureException {
			PhotoFavoriteDeleteRequest photoFavoriteDeleteRequest = new PhotoFavoriteDeleteRequest();
			photoFavoriteDeleteRequest.setFavoritePhotoAccountNo(2);
			photoFavoriteDeleteRequest.setFavoritePhotoNo(3);
			
			BindingResult result = new DataBinder(photoFavoriteDeleteRequest).getBindingResult();
			
			doReturn(1).when(sessionHelper).getAccountNo();
			
			ArgumentCaptor<PhotoFavoriteModel> photoFavoriteModelCaptor = ArgumentCaptor.forClass(PhotoFavoriteModel.class);
			doNothing().when(photoFavoriteServiceImpl).deleteFavorite(photoFavoriteModelCaptor.capture());
			
			ResponseEntity<PhotoFavoriteResponse> actual
				= photoFavoriteController.deleteFavorite(photoFavoriteDeleteRequest, result);
			
			assertEquals(HttpStatus.OK, actual.getStatusCode());
			assertEquals(HttpStatus.OK.value(), actual.getBody().getHttpStatus());
			assertEquals(true, actual.getBody().getIsSuccess());
			assertEquals("お気に入りを解除しました。", actual.getBody().getMessage());
			
			PhotoFavoriteModel photoFavoriteModel = photoFavoriteModelCaptor.getValue();
			assertEquals(1, photoFavoriteModel.getAccountNo());
			assertEquals(2, photoFavoriteModel.getFavoritePhotoAccountNo());
			assertEquals(3, photoFavoriteModel.getFavoritePhotoNo());
		}
		
		@Test
		@Order(2)
		@DisplayName("異常系：BadRequestExceptionをthrowする")
		void deleteFavorite_BadRequestException() throws BadRequestException, UpdateFailureException {
			PhotoFavoriteDeleteRequest photoFavoriteDeleteRequest = new PhotoFavoriteDeleteRequest();
			photoFavoriteDeleteRequest.setFavoritePhotoAccountNo(2);
			photoFavoriteDeleteRequest.setFavoritePhotoNo(3);
			
			BindingResult result = new DataBinder(photoFavoriteDeleteRequest).getBindingResult();
			FieldError fError = new FieldError("photoFavoriteDeleteRequest","favoritePhotoAccountNo", "");
			result.addError((ObjectError) fError);
			
			assertThrows(BadRequestException.class, () -> photoFavoriteController.deleteFavorite(photoFavoriteDeleteRequest, result));
			verify(sessionHelper, times(0)).getAccountNo();
			verify(photoFavoriteServiceImpl, times(0)).deleteFavorite(any(PhotoFavoriteModel.class));
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：UpdateFailureExceptionをthrowする")
		void deleteFavorite_UpdateFailureException() throws BadRequestException, UpdateFailureException {
			PhotoFavoriteDeleteRequest photoFavoriteDeleteRequest = new PhotoFavoriteDeleteRequest();
			photoFavoriteDeleteRequest.setFavoritePhotoAccountNo(2);
			photoFavoriteDeleteRequest.setFavoritePhotoNo(3);
			
			BindingResult result = new DataBinder(photoFavoriteDeleteRequest).getBindingResult();
			
			doReturn(1).when(sessionHelper).getAccountNo();
			
			ArgumentCaptor<PhotoFavoriteModel> photoFavoriteModelCaptor = ArgumentCaptor.forClass(PhotoFavoriteModel.class);
			doThrow(UpdateFailureException.class).when(photoFavoriteServiceImpl).deleteFavorite(photoFavoriteModelCaptor.capture());
			
			assertThrows(UpdateFailureException.class, () -> photoFavoriteController.deleteFavorite(photoFavoriteDeleteRequest, result));
			
			PhotoFavoriteModel photoFavoriteModel = photoFavoriteModelCaptor.getValue();
			assertEquals(1, photoFavoriteModel.getAccountNo());
			assertEquals(2, photoFavoriteModel.getFavoritePhotoAccountNo());
			assertEquals(3, photoFavoriteModel.getFavoritePhotoNo());
		}
	}
}
