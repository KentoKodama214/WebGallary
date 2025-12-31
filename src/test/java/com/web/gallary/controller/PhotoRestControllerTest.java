package com.web.gallary.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.multipart.MultipartFile;

import com.web.gallary.config.PhotoConfig;
import com.web.gallary.controller.request.PhotoDeleteRequest;
import com.web.gallary.controller.request.PhotoListRequest;
import com.web.gallary.controller.request.PhotoSaveRequest;
import com.web.gallary.controller.request.PhotoTagSaveRequest;
import com.web.gallary.controller.response.PhotoEditResponse;
import com.web.gallary.controller.response.PhotoListGetResponse;
import com.web.gallary.enumuration.DirectionEnum;
import com.web.gallary.enumuration.SortPhotoEnum;
import com.web.gallary.exception.BadRequestException;
import com.web.gallary.exception.FileDuplicateException;
import com.web.gallary.exception.ForbiddenAccountException;
import com.web.gallary.exception.PhotoNotAdditableException;
import com.web.gallary.exception.RegistFailureException;
import com.web.gallary.exception.UpdateFailureException;
import com.web.gallary.helper.SessionHelper;
import com.web.gallary.model.PhotoDeleteModel;
import com.web.gallary.model.PhotoDetailModel;
import com.web.gallary.model.PhotoListGetModel;
import com.web.gallary.model.PhotoModel;
import com.web.gallary.model.PhotoTagModel;
import com.web.gallary.service.impl.PhotoServiceImpl;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class PhotoRestControllerTest {
	@InjectMocks
	private PhotoRestController photoRestController;
	
	@Mock
	private PhotoServiceImpl photoServiceImpl;
	
	@Mock
	private SessionHelper sessionHelper;
	
	@Mock
	private PhotoConfig photoConfig;
	
	@Nested
	@Order(1)
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class getPhotoList {
		private List<PhotoModel> createPhotoModelList() {
			List<PhotoModel> photoList = new ArrayList<PhotoModel>();
			photoList.add(PhotoModel.builder()
					.accountNo(1)
					.photoNo(1)
					.favoriteCount(1)
					.isFavorite(false)
					.photoAt(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.imageFilePath("https://localhost:8080/image/aaaaaaaa/DSC111.jpg")
					.caption("キャプション1")
					.directionKbn(DirectionEnum.VERTICAL)
					.photoTagModelList(new ArrayList<PhotoTagModel>())
					.build());
			photoList.add(PhotoModel.builder()
					.accountNo(1)
					.photoNo(2)
					.favoriteCount(1)
					.isFavorite(true)
					.photoAt(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.imageFilePath("https://localhost:8080/image/aaaaaaaa/DSC222.jpg")
					.caption("キャプション2")
					.directionKbn(DirectionEnum.HORIZONTAL)
					.photoTagModelList(new ArrayList<PhotoTagModel>())
					.build());
			photoList.add(PhotoModel.builder()
					.accountNo(1)
					.photoNo(3)
					.favoriteCount(1)
					.isFavorite(true)
					.photoAt(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.imageFilePath("https://localhost:8080/image/aaaaaaaa/DSC333.jpg")
					.caption("キャプション3")
					.directionKbn(DirectionEnum.HORIZONTAL)
					.photoTagModelList(new ArrayList<PhotoTagModel>())
					.build());
			photoList.add(PhotoModel.builder()
					.accountNo(1)
					.photoNo(4)
					.favoriteCount(1)
					.isFavorite(true)
					.photoAt(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.imageFilePath("https://localhost:8080/image/aaaaaaaa/DSC444.jpg")
					.caption("キャプション4")
					.directionKbn(DirectionEnum.HORIZONTAL)
					.photoTagModelList(new ArrayList<PhotoTagModel>())
					.build());
			photoList.add(PhotoModel.builder()
					.accountNo(1)
					.photoNo(5)
					.favoriteCount(1)
					.isFavorite(true)
					.photoAt(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.imageFilePath("https://localhost:8080/image/aaaaaaaa/DSC555.jpg")
					.caption("キャプション5")
					.directionKbn(DirectionEnum.HORIZONTAL)
					.photoTagModelList(new ArrayList<PhotoTagModel>())
					.build());
			photoList.add(PhotoModel.builder()
					.accountNo(1)
					.photoNo(6)
					.favoriteCount(1)
					.isFavorite(true)
					.photoAt(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.imageFilePath("https://localhost:8080/image/aaaaaaaa/DSC666.jpg")
					.caption("キャプション6")
					.directionKbn(DirectionEnum.HORIZONTAL)
					.photoTagModelList(new ArrayList<PhotoTagModel>())
					.build());
			photoList.add(PhotoModel.builder()
					.accountNo(1)
					.photoNo(7)
					.favoriteCount(1)
					.isFavorite(true)
					.photoAt(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.imageFilePath("https://localhost:8080/image/aaaaaaaa/DSC777.jpg")
					.caption("キャプション7")
					.directionKbn(DirectionEnum.HORIZONTAL)
					.photoTagModelList(new ArrayList<PhotoTagModel>())
					.build());
			
			return photoList;
		}
		
		@Test
		@Order(1)
		@DisplayName("正常系：Nullのパラメータがある場合")
		void getPhotoList_with_null_parameter() {
			String photoAccountId = "aaaaaaaa";
			PhotoListRequest photoListRequest = new PhotoListRequest();
			
			doReturn(1).when(sessionHelper).getAccountNo();
			
			List<PhotoModel> photoList = createPhotoModelList();
			ArgumentCaptor<PhotoListGetModel> photoListGetModelCaptor = ArgumentCaptor.forClass(PhotoListGetModel.class);
			doReturn(photoList).when(photoServiceImpl).getPhotoList(photoListGetModelCaptor.capture());
			
			doReturn(3).when(photoConfig).getPhotoCountPerPage();
			
			ResponseEntity<PhotoListGetResponse> actual
				= photoRestController.getPhotoList(photoAccountId, photoListRequest);
			
			assertEquals(HttpStatus.OK, actual.getStatusCode());
			assertEquals(false, actual.getBody().getIsLast());
			
			assertEquals(3, actual.getBody().getPhotoList().size());
			assertEquals(1, actual.getBody().getPhotoList().get(0).getAccountNo());
			assertEquals(1, actual.getBody().getPhotoList().get(0).getPhotoNo());
			assertFalse(actual.getBody().getPhotoList().get(0).getIsFavorite());
			assertEquals("https://localhost:8080/image/aaaaaaaa/DSC111.jpg", actual.getBody().getPhotoList().get(0).getImageFilePath());
			assertEquals("キャプション1", actual.getBody().getPhotoList().get(0).getCaption());
			assertEquals(DirectionEnum.VERTICAL, actual.getBody().getPhotoList().get(0).getDirectionKbn());
			assertEquals(1, actual.getBody().getPhotoList().get(1).getAccountNo());
			assertEquals(2, actual.getBody().getPhotoList().get(1).getPhotoNo());
			assertTrue(actual.getBody().getPhotoList().get(1).getIsFavorite());
			assertEquals("https://localhost:8080/image/aaaaaaaa/DSC222.jpg", actual.getBody().getPhotoList().get(1).getImageFilePath());
			assertEquals("キャプション2", actual.getBody().getPhotoList().get(1).getCaption());
			assertEquals(DirectionEnum.HORIZONTAL, actual.getBody().getPhotoList().get(1).getDirectionKbn());
			assertEquals(1, actual.getBody().getPhotoList().get(2).getAccountNo());
			assertEquals(3, actual.getBody().getPhotoList().get(2).getPhotoNo());
			assertTrue(actual.getBody().getPhotoList().get(2).getIsFavorite());
			assertEquals("https://localhost:8080/image/aaaaaaaa/DSC333.jpg", actual.getBody().getPhotoList().get(2).getImageFilePath());
			assertEquals("キャプション3", actual.getBody().getPhotoList().get(2).getCaption());
			assertEquals(DirectionEnum.HORIZONTAL, actual.getBody().getPhotoList().get(2).getDirectionKbn());
			
			PhotoListGetModel photoListGetModel = photoListGetModelCaptor.getValue();
			assertEquals(1, photoListGetModel.getAccountNo());
			assertEquals(photoAccountId, photoListGetModel.getPhotoAccountId());
			assertEquals(DirectionEnum.NONE, photoListGetModel.getDirectionKbn());
			assertFalse(photoListGetModel.getIsFavoriteOnly());
			assertEquals(new ArrayList<String>(), photoListGetModel.getTagList());
			assertEquals(SortPhotoEnum.PHOTO_AT, photoListGetModel.getSortBy());
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：タグに半角スペースが含まれている場合")
		void getPhotoList_with_halfspace_tag() {
			String photoAccountId = "aaaaaaaa";
			
			PhotoListRequest photoListRequest = new PhotoListRequest();
			photoListRequest.setDirectionKbn(DirectionEnum.VERTICAL);
			photoListRequest.setIsFavorite(true);
			photoListRequest.setSortBy(SortPhotoEnum.SEASON);
			photoListRequest.setTagList("太陽 海");
			photoListRequest.setPageNo(2);
			
			doReturn(1).when(sessionHelper).getAccountNo();
			
			List<PhotoModel> photoList = createPhotoModelList().subList(0, 4);
			ArgumentCaptor<PhotoListGetModel> photoListGetModelCaptor = ArgumentCaptor.forClass(PhotoListGetModel.class);
			doReturn(photoList).when(photoServiceImpl).getPhotoList(photoListGetModelCaptor.capture());
			
			doReturn(3).when(photoConfig).getPhotoCountPerPage();
			
			ResponseEntity<PhotoListGetResponse> actual
				= photoRestController.getPhotoList(photoAccountId, photoListRequest);
			
			assertEquals(HttpStatus.OK, actual.getStatusCode());
			assertEquals(true, actual.getBody().getIsLast());
			
			assertEquals(1, actual.getBody().getPhotoList().size());
			assertEquals(1, actual.getBody().getPhotoList().get(0).getAccountNo());
			assertEquals(4, actual.getBody().getPhotoList().get(0).getPhotoNo());
			assertTrue(actual.getBody().getPhotoList().get(0).getIsFavorite());
			assertEquals("https://localhost:8080/image/aaaaaaaa/DSC444.jpg", actual.getBody().getPhotoList().get(0).getImageFilePath());
			assertEquals("キャプション4", actual.getBody().getPhotoList().get(0).getCaption());
			assertEquals(DirectionEnum.HORIZONTAL, actual.getBody().getPhotoList().get(0).getDirectionKbn());
			
			PhotoListGetModel photoListGetModel = photoListGetModelCaptor.getValue();
			assertEquals(1, photoListGetModel.getAccountNo());
			assertEquals(photoAccountId, photoListGetModel.getPhotoAccountId());
			assertEquals(DirectionEnum.VERTICAL, photoListGetModel.getDirectionKbn());
			assertTrue(photoListGetModel.getIsFavoriteOnly());
			assertEquals("太陽", photoListGetModel.getTagList().get(0));
			assertEquals("海", photoListGetModel.getTagList().get(1));
			assertEquals(SortPhotoEnum.SEASON, photoListGetModel.getSortBy());
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：タグに全角スペースが含まれている場合")
		void getPhotoList_with_fullspace_tag() {
			String photoAccountId = "aaaaaaaa";
			
			PhotoListRequest photoListRequest = new PhotoListRequest();
			photoListRequest.setDirectionKbn(DirectionEnum.VERTICAL);
			photoListRequest.setIsFavorite(true);
			photoListRequest.setSortBy(SortPhotoEnum.SEASON);
			photoListRequest.setTagList("太陽　海");
			photoListRequest.setPageNo(2);
			
			doReturn(1).when(sessionHelper).getAccountNo();
			
			List<PhotoModel> photoList = createPhotoModelList().subList(0, 4);
			ArgumentCaptor<PhotoListGetModel> photoListGetModelCaptor = ArgumentCaptor.forClass(PhotoListGetModel.class);
			doReturn(photoList).when(photoServiceImpl).getPhotoList(photoListGetModelCaptor.capture());
			
			doReturn(3).when(photoConfig).getPhotoCountPerPage();
			
			ResponseEntity<PhotoListGetResponse> actual
				= photoRestController.getPhotoList(photoAccountId, photoListRequest);
			
			assertEquals(HttpStatus.OK, actual.getStatusCode());
			assertEquals(true, actual.getBody().getIsLast());
			
			assertEquals(1, actual.getBody().getPhotoList().size());
			assertEquals(1, actual.getBody().getPhotoList().get(0).getAccountNo());
			assertEquals(4, actual.getBody().getPhotoList().get(0).getPhotoNo());
			assertTrue(actual.getBody().getPhotoList().get(0).getIsFavorite());
			assertEquals("https://localhost:8080/image/aaaaaaaa/DSC444.jpg", actual.getBody().getPhotoList().get(0).getImageFilePath());
			assertEquals("キャプション4", actual.getBody().getPhotoList().get(0).getCaption());
			assertEquals(DirectionEnum.HORIZONTAL, actual.getBody().getPhotoList().get(0).getDirectionKbn());
			
			PhotoListGetModel photoListGetModel = photoListGetModelCaptor.getValue();
			assertEquals(1, photoListGetModel.getAccountNo());
			assertEquals(photoAccountId, photoListGetModel.getPhotoAccountId());
			assertEquals(DirectionEnum.VERTICAL, photoListGetModel.getDirectionKbn());
			assertTrue(photoListGetModel.getIsFavoriteOnly());
			assertEquals("太陽", photoListGetModel.getTagList().get(0));
			assertEquals("海", photoListGetModel.getTagList().get(1));
			assertEquals(SortPhotoEnum.SEASON, photoListGetModel.getSortBy());
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：写真が0件の場合")
		void getPhotoList_not_found_photo() {
			String photoAccountId = "aaaaaaaa";
			PhotoListRequest photoListRequest = new PhotoListRequest();
			
			doReturn(1).when(sessionHelper).getAccountNo();
			
			List<PhotoModel> photoList = new ArrayList<PhotoModel>();
			ArgumentCaptor<PhotoListGetModel> photoListGetModelCaptor = ArgumentCaptor.forClass(PhotoListGetModel.class);
			doReturn(photoList).when(photoServiceImpl).getPhotoList(photoListGetModelCaptor.capture());
			
			doReturn(3).when(photoConfig).getPhotoCountPerPage();
			
			ResponseEntity<PhotoListGetResponse> actual
				= photoRestController.getPhotoList(photoAccountId, photoListRequest);
			
			assertEquals(HttpStatus.OK, actual.getStatusCode());
			assertTrue(actual.getBody().getIsLast());
			assertEquals(0, actual.getBody().getPhotoList().size());
			
			PhotoListGetModel photoListGetModel = photoListGetModelCaptor.getValue();
			assertEquals(1, photoListGetModel.getAccountNo());
			assertEquals(photoAccountId, photoListGetModel.getPhotoAccountId());
			assertEquals(DirectionEnum.NONE, photoListGetModel.getDirectionKbn());
			assertFalse(photoListGetModel.getIsFavoriteOnly());
			assertEquals(new ArrayList<String>(), photoListGetModel.getTagList());
			assertEquals(SortPhotoEnum.PHOTO_AT, photoListGetModel.getSortBy());
		}
	}
	
	@Nested
	@Order(2)
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class savePhoto {
		@Test
		@Order(1)
		@SuppressWarnings("unchecked")
		@DisplayName("正常系：写真タグなし、撮影日時なし。Nullパラメータあり")
		void savePhoto_addPhoto_not_photoTag_and_photoAt() throws FileDuplicateException, RegistFailureException, UpdateFailureException, ForbiddenAccountException, BadRequestException, PhotoNotAdditableException {
			String photoAccountId = "aaaaaaaa";
			String loginAccountId = "aaaaaaaa";
			String imageFilePath = "https://localhost:8080/image/aaaaaaaa/DSC111.jpg";
			MultipartFile multipartFile = new MockMultipartFile(
					"file",
					"DSC111.jpg",
					"multipart/form-data",
					"sample image".getBytes());
					
			PhotoSaveRequest photoSaveRequest = new PhotoSaveRequest();
			photoSaveRequest.setAccountNo(1);
			photoSaveRequest.setImageFile(multipartFile);
			photoSaveRequest.setImageFilePath(imageFilePath);
			
			BindingResult result = new DataBinder(photoSaveRequest).getBindingResult();
			
			doReturn(loginAccountId).when(sessionHelper).getAccountId();
			doReturn(1).when(sessionHelper).getAccountNo();
			doReturn(false).when(photoServiceImpl).isReachedUpperLimit(1);
			
			ArgumentCaptor<List<PhotoDetailModel>> photoDetailModelCaptor = ArgumentCaptor.forClass(List.class);
			ArgumentCaptor<String> photoAcountIdCaptor = ArgumentCaptor.forClass(String.class);
			doNothing().when(photoServiceImpl).savePhotos(photoAcountIdCaptor.capture(), photoDetailModelCaptor.capture());
			
			ResponseEntity<PhotoEditResponse> actual
				= photoRestController.savePhoto(photoAccountId, photoSaveRequest, result);
			
			assertEquals(HttpStatus.OK, actual.getStatusCode());
			assertEquals(HttpStatus.OK.value(), actual.getBody().getHttpStatus());
			assertEquals(true, actual.getBody().getIsSuccess());
			assertEquals("写真登録が完了しました。", actual.getBody().getMessage());
			
			List<PhotoDetailModel> photoDetailModelList = photoDetailModelCaptor.getValue();
			assertEquals(1, photoDetailModelList.size());
			assertEquals(1, photoDetailModelList.getFirst().getAccountNo());
			assertNull(photoDetailModelList.getFirst().getPhotoNo());
			assertNull(photoDetailModelList.getFirst().getIsFavorite());
			assertNull(photoDetailModelList.getFirst().getPhotoAt());
			assertNull(photoDetailModelList.getFirst().getLocationNo());
			assertNull(photoDetailModelList.getFirst().getAddress());
			assertNull(photoDetailModelList.getFirst().getLatitude());
			assertNull(photoDetailModelList.getFirst().getLongitude());
			assertNull(photoDetailModelList.getFirst().getLocationName());
			assertEquals(multipartFile, photoDetailModelList.getFirst().getImageFile());
			assertEquals(imageFilePath, photoDetailModelList.getFirst().getImageFilePath());
			assertNull(photoDetailModelList.getFirst().getPhotoJapaneseTitle());
			assertNull(photoDetailModelList.getFirst().getPhotoEnglishTitle());
			assertNull(photoDetailModelList.getFirst().getCaption());
			assertNull(photoDetailModelList.getFirst().getDirectionKbnCode());
			assertNull(photoDetailModelList.getFirst().getFocalLength());
			assertNull(photoDetailModelList.getFirst().getFValue());
			assertNull(photoDetailModelList.getFirst().getShutterSpeed());
			assertNull(photoDetailModelList.getFirst().getIso());
			assertEquals(new ArrayList<PhotoDetailModel>(), photoDetailModelList.getFirst().getPhotoTagModelList());
			
			assertEquals(photoAccountId, photoAcountIdCaptor.getValue());
		}
		
		@Test
		@Order(2)
		@SuppressWarnings("unchecked")
		@DisplayName("正常系：写真タグあり、撮影日時あり。Nullパラメータなし")
		void savePhoto_addPhoto_with_photoTag_and_photoAt() throws FileDuplicateException, RegistFailureException, UpdateFailureException, ForbiddenAccountException, BadRequestException, PhotoNotAdditableException {
			String photoAccountId = "aaaaaaaa";
			String loginAccountId = "aaaaaaaa";
			LocalDateTime photoAt = LocalDateTime.of(2000, 12, 1, 0, 0);
			String address = "東京都港区芝公園４丁目２−８";
			String locationName = "東京タワー";
			String imageFilePath = "https://localhost:8080/image/aaaaaaaa/DSC111.jpg";
			MultipartFile multipartFile = new MockMultipartFile(
					"file",
					"DSC111.jpg",
					"multipart/form-data",
					"sample image".getBytes());
			String photoJapaneseTitle = "タイトル";
			String photoEnglishTitle = "title";
			String caption = "キャプション";
			String directionKbnCode = "vertical";
			List<PhotoTagSaveRequest> photoTagRegistRequestList = new ArrayList<PhotoTagSaveRequest>();
			PhotoTagSaveRequest photoTagSaveRequest1 = new PhotoTagSaveRequest();
			photoTagSaveRequest1.setAccountNo(1);
			photoTagSaveRequest1.setPhotoNo(1);
			photoTagSaveRequest1.setTagNo(1);
			photoTagSaveRequest1.setTagJapaneseName("太陽");
			photoTagSaveRequest1.setTagEnglishName("sun");
			photoTagRegistRequestList.add(photoTagSaveRequest1);
			PhotoTagSaveRequest photoTagSaveRequest2 = new PhotoTagSaveRequest();
			photoTagSaveRequest2.setAccountNo(1);
			photoTagSaveRequest2.setPhotoNo(1);
			photoTagSaveRequest2.setTagNo(2);
			photoTagSaveRequest2.setTagJapaneseName("海");
			photoTagSaveRequest2.setTagEnglishName(null);
			photoTagRegistRequestList.add(photoTagSaveRequest2);
			
			PhotoSaveRequest photoSaveRequest = new PhotoSaveRequest();
			photoSaveRequest.setAccountNo(1);
			photoSaveRequest.setPhotoNo(1);
			photoSaveRequest.setIsFavorite(false);
			photoSaveRequest.setPhotoAt(photoAt);
			photoSaveRequest.setLocationNo(1);
			photoSaveRequest.setAddress(address);
			photoSaveRequest.setLatitude(BigDecimal.valueOf(35.000));
			photoSaveRequest.setLongitude(BigDecimal.valueOf(135.00));
			photoSaveRequest.setLocationName(locationName);
			photoSaveRequest.setImageFile(multipartFile);
			photoSaveRequest.setImageFilePath(imageFilePath);
			photoSaveRequest.setPhotoJapaneseTitle(photoJapaneseTitle);
			photoSaveRequest.setPhotoEnglishTitle(photoEnglishTitle);
			photoSaveRequest.setCaption(caption);
			photoSaveRequest.setDirectionKbnCode(directionKbnCode);
			photoSaveRequest.setFocalLength(50);
			photoSaveRequest.setFValue(BigDecimal.valueOf(8.0));
			photoSaveRequest.setShutterSpeed(BigDecimal.valueOf(0.001));
			photoSaveRequest.setIso(100);
			photoSaveRequest.setPhotoTagRegistRequestList(photoTagRegistRequestList);
			
			BindingResult result = new DataBinder(photoSaveRequest).getBindingResult();
			
			doReturn(loginAccountId).when(sessionHelper).getAccountId();
			
			ArgumentCaptor<List<PhotoDetailModel>> photoDetailModelCaptor = ArgumentCaptor.forClass(List.class);
			ArgumentCaptor<String> photoAcountIdCaptor = ArgumentCaptor.forClass(String.class);
			doNothing().when(photoServiceImpl).savePhotos(photoAcountIdCaptor.capture(), photoDetailModelCaptor.capture());
			
			ResponseEntity<PhotoEditResponse> actual
				= photoRestController.savePhoto(photoAccountId, photoSaveRequest, result);
			verify(sessionHelper, times(0)).getAccountNo();
			verify(photoServiceImpl, times(0)).isReachedUpperLimit(1);
			
			assertEquals(HttpStatus.OK, actual.getStatusCode());
			assertEquals(HttpStatus.OK.value(), actual.getBody().getHttpStatus());
			assertEquals(true, actual.getBody().getIsSuccess());
			assertEquals("写真登録が完了しました。", actual.getBody().getMessage());
			
			List<PhotoDetailModel> photoDetailModelList = photoDetailModelCaptor.getValue();
			assertEquals(1, photoDetailModelList.size());
			assertEquals(1, photoDetailModelList.getFirst().getAccountNo());
			assertEquals(1, photoDetailModelList.getFirst().getPhotoNo());
			assertFalse(photoDetailModelList.getFirst().getIsFavorite());
			assertEquals(OffsetDateTime.of(2000, 12, 1, 0, 0, 0, 0, ZoneOffset.ofHours(9)), photoDetailModelList.getFirst().getPhotoAt());
			assertEquals(1, photoDetailModelList.getFirst().getLocationNo());
			assertEquals(address, photoDetailModelList.getFirst().getAddress());
			assertEquals(0, BigDecimal.valueOf(35.000).compareTo(photoDetailModelList.getFirst().getLatitude()));
			assertEquals(0, BigDecimal.valueOf(135.000).compareTo(photoDetailModelList.getFirst().getLongitude()));
			assertEquals(locationName, photoDetailModelList.getFirst().getLocationName());
			assertEquals(multipartFile, photoDetailModelList.getFirst().getImageFile());
			assertEquals(imageFilePath, photoDetailModelList.getFirst().getImageFilePath());
			assertEquals(photoJapaneseTitle, photoDetailModelList.getFirst().getPhotoJapaneseTitle());
			assertEquals(photoEnglishTitle, photoDetailModelList.getFirst().getPhotoEnglishTitle());
			assertEquals(caption, photoDetailModelList.getFirst().getCaption());
			assertEquals(directionKbnCode, photoDetailModelList.getFirst().getDirectionKbnCode());
			assertEquals(50, photoDetailModelList.getFirst().getFocalLength());
			assertEquals(0, BigDecimal.valueOf(8.0).compareTo(photoDetailModelList.getFirst().getFValue()));
			assertEquals(0, BigDecimal.valueOf(0.001).compareTo(photoDetailModelList.getFirst().getShutterSpeed()));
			assertEquals(100, photoDetailModelList.getFirst().getIso());
			
			assertEquals(1, photoDetailModelList.getFirst().getPhotoTagModelList().get(0).getTagNo());
			assertEquals("太陽", photoDetailModelList.getFirst().getPhotoTagModelList().get(0).getTagJapaneseName());
			assertEquals("sun", photoDetailModelList.getFirst().getPhotoTagModelList().get(0).getTagEnglishName());
			assertEquals(2, photoDetailModelList.getFirst().getPhotoTagModelList().get(1).getTagNo());
			assertEquals("海", photoDetailModelList.getFirst().getPhotoTagModelList().get(1).getTagJapaneseName());
			assertEquals("", photoDetailModelList.getFirst().getPhotoTagModelList().get(1).getTagEnglishName());
			
			assertEquals(photoAccountId, photoAcountIdCaptor.getValue());
		}
		
		@Test
		@Order(3)
		@SuppressWarnings("unchecked")
		@DisplayName("異常系：アクセス不正。ForbiddenAccountExceptionをthrowする")
		void savePhoto_ForbiddenAccountException() throws FileDuplicateException, RegistFailureException, UpdateFailureException {
			String photoAccountId = "aaaaaaaa";
			String loginAccountId = "bbbbbbbb";
			PhotoSaveRequest photoSaveRequest = new PhotoSaveRequest();
			
			BindingResult result = new DataBinder(photoSaveRequest).getBindingResult();
			
			doReturn(loginAccountId).when(sessionHelper).getAccountId();
			
			assertThrows(ForbiddenAccountException.class, 
					() -> photoRestController.savePhoto(photoAccountId, photoSaveRequest, result));
			verify(photoServiceImpl, times(0)).isReachedUpperLimit(any(Integer.class));
			verify(photoServiceImpl, times(0)).savePhotos(any(String.class), any(List.class));
		}
		
		@Test
		@Order(4)
		@SuppressWarnings("unchecked")
		@DisplayName("異常系：登録上限に達している。PhotoNotAdditableExceptionをthrowする")
		void savePhoto_PhotoNotAdditableException() throws FileDuplicateException, RegistFailureException, UpdateFailureException {
			String photoAccountId = "aaaaaaaa";
			String loginAccountId = "aaaaaaaa";
			PhotoSaveRequest photoSaveRequest = new PhotoSaveRequest();
			
			BindingResult result = new DataBinder(photoSaveRequest).getBindingResult();
			
			doReturn(loginAccountId).when(sessionHelper).getAccountId();
			doReturn(1).when(sessionHelper).getAccountNo();
			doReturn(true).when(photoServiceImpl).isReachedUpperLimit(1);
			
			assertThrows(PhotoNotAdditableException.class,
					() -> photoRestController.savePhoto(photoAccountId, photoSaveRequest, result));
			verify(photoServiceImpl, times(0)).savePhotos(any(String.class), any(List.class));
		}
		
		@Test
		@Order(5)
		@SuppressWarnings("unchecked")
		@DisplayName("異常系：画像ファイル、ファイルパスともにnull。BadRequestExceptionをthrowする")
		void savePhoto_BadRequestException_file_and_filepath_is_null() throws FileDuplicateException, RegistFailureException, UpdateFailureException {
			String photoAccountId = "aaaaaaaa";
			String loginAccountId = "aaaaaaaa";
			PhotoSaveRequest photoSaveRequest = new PhotoSaveRequest();
			
			BindingResult result = new DataBinder(photoSaveRequest).getBindingResult();
			
			doReturn(loginAccountId).when(sessionHelper).getAccountId();
			doReturn(1).when(sessionHelper).getAccountNo();
			doReturn(false).when(photoServiceImpl).isReachedUpperLimit(1);
			
			assertThrows(BadRequestException.class,
					() -> photoRestController.savePhoto(photoAccountId, photoSaveRequest, result));
			verify(photoServiceImpl, times(0)).savePhotos(any(String.class), any(List.class));
		}
		
		@Test
		@Order(6)
		@SuppressWarnings("unchecked")
		@DisplayName("異常系：画像ファイルがnull、ファイルパスがblank。BadRequestExceptionをthrowする")
		void savePhoto_BadRequestException_file_and_filepath_is_blank() throws FileDuplicateException, RegistFailureException, UpdateFailureException {
			String photoAccountId = "aaaaaaaa";
			String loginAccountId = "aaaaaaaa";
			PhotoSaveRequest photoSaveRequest = new PhotoSaveRequest();
			photoSaveRequest.setImageFilePath("");
			
			BindingResult result = new DataBinder(photoSaveRequest).getBindingResult();
			
			doReturn(loginAccountId).when(sessionHelper).getAccountId();
			doReturn(1).when(sessionHelper).getAccountNo();
			doReturn(false).when(photoServiceImpl).isReachedUpperLimit(1);
			
			assertThrows(BadRequestException.class,
					() -> photoRestController.savePhoto(photoAccountId, photoSaveRequest, result));
			verify(photoServiceImpl, times(0)).savePhotos(any(String.class), any(List.class));
		}
		
		@Test
		@Order(7)
		@SuppressWarnings("unchecked")
		@DisplayName("異常系：画像ファイル、ファイルパス以外のパラメータ不正。BadRequestExceptionをthrowする")
		void savePhoto_BadRequestException_others() throws FileDuplicateException, RegistFailureException, UpdateFailureException {
			String photoAccountId = "aaaaaaaa";
			String loginAccountId = "aaaaaaaa";
			String imageFilePath = "https://localhost:8080/image/aaaaaaaa/DSC111.jpg";
			PhotoSaveRequest photoSaveRequest = new PhotoSaveRequest();
			photoSaveRequest.setImageFilePath(imageFilePath);
			
			BindingResult result = new DataBinder(photoSaveRequest).getBindingResult();
			FieldError fError = new FieldError("photoSaveRequest","account_id", "");
			result.addError((ObjectError) fError);
			
			doReturn(loginAccountId).when(sessionHelper).getAccountId();
			doReturn(1).when(sessionHelper).getAccountNo();
			doReturn(false).when(photoServiceImpl).isReachedUpperLimit(1);
			
			assertThrows(BadRequestException.class,
					() -> photoRestController.savePhoto(photoAccountId, photoSaveRequest, result));
			verify(photoServiceImpl, times(0)).savePhotos(any(String.class), any(List.class));
		}
		
		@Test
		@Order(8)
		@SuppressWarnings("unchecked")
		@DisplayName("異常系：FileDuplicateExceptionをthrowする")
		void savePhoto_FileDuplicateException() throws UpdateFailureException, FileDuplicateException, RegistFailureException {
			String photoAccountId = "aaaaaaaa";
			String loginAccountId = "aaaaaaaa";
			String imageFilePath = "https://localhost:8080/image/aaaaaaaa/DSC111.jpg";
			MultipartFile multipartFile = new MockMultipartFile(
					"file",
					"DSC111.jpg",
					"multipart/form-data",
					"sample image".getBytes());
					
			PhotoSaveRequest photoSaveRequest = new PhotoSaveRequest();
			photoSaveRequest.setAccountNo(1);
			photoSaveRequest.setPhotoNo(1);
			photoSaveRequest.setPhotoAt(null);
			photoSaveRequest.setImageFile(multipartFile);
			photoSaveRequest.setImageFilePath(imageFilePath);
			
			BindingResult result = new DataBinder(photoSaveRequest).getBindingResult();
			
			doReturn(loginAccountId).when(sessionHelper).getAccountId();
			
			
			ArgumentCaptor<List<PhotoDetailModel>> photoDetailModelCaptor = ArgumentCaptor.forClass(List.class);
			ArgumentCaptor<String> photoAcountIdCaptor = ArgumentCaptor.forClass(String.class);
			doThrow(FileDuplicateException.class).when(photoServiceImpl).savePhotos(photoAcountIdCaptor.capture(), photoDetailModelCaptor.capture());
			
			assertThrows(FileDuplicateException.class, () -> photoRestController.savePhoto(photoAccountId, photoSaveRequest, result));
			verify(photoServiceImpl, times(0)).isReachedUpperLimit(any(Integer.class));
			verify(sessionHelper, times(0)).getAccountNo();
			
			List<PhotoDetailModel> photoDetailModelList = photoDetailModelCaptor.getValue();
			assertEquals(1, photoDetailModelList.size());
			assertEquals(1, photoDetailModelList.getFirst().getAccountNo());
			assertEquals(1, photoDetailModelList.getFirst().getPhotoNo());
			assertNull(photoDetailModelList.getFirst().getIsFavorite());
			assertNull(photoDetailModelList.getFirst().getPhotoAt());
			assertNull(photoDetailModelList.getFirst().getLocationNo());
			assertNull(photoDetailModelList.getFirst().getAddress());
			assertNull(photoDetailModelList.getFirst().getLatitude());
			assertNull(photoDetailModelList.getFirst().getLongitude());
			assertNull(photoDetailModelList.getFirst().getLocationName());
			assertEquals(multipartFile, photoDetailModelList.getFirst().getImageFile());
			assertEquals(imageFilePath, photoDetailModelList.getFirst().getImageFilePath());
			assertNull(photoDetailModelList.getFirst().getPhotoJapaneseTitle());
			assertNull(photoDetailModelList.getFirst().getPhotoEnglishTitle());
			assertNull(photoDetailModelList.getFirst().getCaption());
			assertNull(photoDetailModelList.getFirst().getDirectionKbnCode());
			assertNull(photoDetailModelList.getFirst().getFocalLength());
			assertNull(photoDetailModelList.getFirst().getFValue());
			assertNull(photoDetailModelList.getFirst().getShutterSpeed());
			assertNull(photoDetailModelList.getFirst().getIso());
			assertEquals(new ArrayList<PhotoDetailModel>(), photoDetailModelList.getFirst().getPhotoTagModelList());
			
			assertEquals(photoAccountId, photoAcountIdCaptor.getValue());
		}
		
		@Test
		@Order(9)
		@SuppressWarnings("unchecked")
		@DisplayName("異常系：RegistFailureExceptionをthrowする")
		void savePhoto_RegistFailureException() throws FileDuplicateException, RegistFailureException, UpdateFailureException {
			String photoAccountId = "aaaaaaaa";
			String loginAccountId = "aaaaaaaa";
			String imageFilePath = "https://localhost:8080/image/aaaaaaaa/DSC111.jpg";
			MultipartFile multipartFile = new MockMultipartFile(
					"file",
					"DSC111.jpg",
					"multipart/form-data",
					"sample image".getBytes());
					
			PhotoSaveRequest photoSaveRequest = new PhotoSaveRequest();
			photoSaveRequest.setAccountNo(1);
			photoSaveRequest.setPhotoNo(1);
			photoSaveRequest.setPhotoAt(null);
			photoSaveRequest.setImageFile(multipartFile);
			photoSaveRequest.setImageFilePath(imageFilePath);
			
			BindingResult result = new DataBinder(photoSaveRequest).getBindingResult();
			
			doReturn(loginAccountId).when(sessionHelper).getAccountId();
			
			
			ArgumentCaptor<List<PhotoDetailModel>> photoDetailModelCaptor = ArgumentCaptor.forClass(List.class);
			ArgumentCaptor<String> photoAcountIdCaptor = ArgumentCaptor.forClass(String.class);
			doThrow(RegistFailureException.class).when(photoServiceImpl).savePhotos(photoAcountIdCaptor.capture(), photoDetailModelCaptor.capture());
			
			assertThrows(RegistFailureException.class, () -> photoRestController.savePhoto(photoAccountId, photoSaveRequest, result));
			verify(photoServiceImpl, times(0)).isReachedUpperLimit(any(Integer.class));
			verify(sessionHelper, times(0)).getAccountNo();
			
			List<PhotoDetailModel> photoDetailModelList = photoDetailModelCaptor.getValue();
			assertEquals(1, photoDetailModelList.size());
			assertEquals(1, photoDetailModelList.getFirst().getAccountNo());
			assertEquals(1, photoDetailModelList.getFirst().getPhotoNo());
			assertNull(photoDetailModelList.getFirst().getIsFavorite());
			assertNull(photoDetailModelList.getFirst().getPhotoAt());
			assertNull(photoDetailModelList.getFirst().getLocationNo());
			assertNull(photoDetailModelList.getFirst().getAddress());
			assertNull(photoDetailModelList.getFirst().getLatitude());
			assertNull(photoDetailModelList.getFirst().getLongitude());
			assertNull(photoDetailModelList.getFirst().getLocationName());
			assertEquals(multipartFile, photoDetailModelList.getFirst().getImageFile());
			assertEquals(imageFilePath, photoDetailModelList.getFirst().getImageFilePath());
			assertNull(photoDetailModelList.getFirst().getPhotoJapaneseTitle());
			assertNull(photoDetailModelList.getFirst().getPhotoEnglishTitle());
			assertNull(photoDetailModelList.getFirst().getCaption());
			assertNull(photoDetailModelList.getFirst().getDirectionKbnCode());
			assertNull(photoDetailModelList.getFirst().getFocalLength());
			assertNull(photoDetailModelList.getFirst().getFValue());
			assertNull(photoDetailModelList.getFirst().getShutterSpeed());
			assertNull(photoDetailModelList.getFirst().getIso());
			assertEquals(new ArrayList<PhotoDetailModel>(), photoDetailModelList.getFirst().getPhotoTagModelList());
			
			assertEquals(photoAccountId, photoAcountIdCaptor.getValue());
		}
		
		@Test
		@Order(10)
		@SuppressWarnings("unchecked")
		@DisplayName("異常系：UpdateFailureExceptionをthrowする")
		void savePhoto_UpdateFailureException() throws FileDuplicateException, RegistFailureException, UpdateFailureException {
			String photoAccountId = "aaaaaaaa";
			String loginAccountId = "aaaaaaaa";
			String imageFilePath = "https://localhost:8080/image/aaaaaaaa/DSC111.jpg";
			MultipartFile multipartFile = new MockMultipartFile(
					"file",
					"DSC111.jpg",
					"multipart/form-data",
					"sample image".getBytes());
					
			PhotoSaveRequest photoSaveRequest = new PhotoSaveRequest();
			photoSaveRequest.setAccountNo(1);
			photoSaveRequest.setPhotoNo(1);
			photoSaveRequest.setImageFile(multipartFile);
			photoSaveRequest.setImageFilePath(imageFilePath);
			
			BindingResult result = new DataBinder(photoSaveRequest).getBindingResult();
			
			doReturn(loginAccountId).when(sessionHelper).getAccountId();
			
			
			ArgumentCaptor<List<PhotoDetailModel>> photoDetailModelCaptor = ArgumentCaptor.forClass(List.class);
			ArgumentCaptor<String> photoAcountIdCaptor = ArgumentCaptor.forClass(String.class);
			doThrow(UpdateFailureException.class).when(photoServiceImpl).savePhotos(photoAcountIdCaptor.capture(), photoDetailModelCaptor.capture());
			
			assertThrows(UpdateFailureException.class, () -> photoRestController.savePhoto(photoAccountId, photoSaveRequest, result));
			verify(photoServiceImpl, times(0)).isReachedUpperLimit(any(Integer.class));
			verify(sessionHelper, times(0)).getAccountNo();
			
			List<PhotoDetailModel> photoDetailModelList = photoDetailModelCaptor.getValue();
			assertEquals(1, photoDetailModelList.size());
			assertEquals(1, photoDetailModelList.getFirst().getAccountNo());
			assertEquals(1, photoDetailModelList.getFirst().getPhotoNo());
			assertNull(photoDetailModelList.getFirst().getIsFavorite());
			assertNull(photoDetailModelList.getFirst().getPhotoAt());
			assertNull(photoDetailModelList.getFirst().getLocationNo());
			assertNull(photoDetailModelList.getFirst().getAddress());
			assertNull(photoDetailModelList.getFirst().getLatitude());
			assertNull(photoDetailModelList.getFirst().getLongitude());
			assertNull(photoDetailModelList.getFirst().getLocationName());
			assertEquals(multipartFile, photoDetailModelList.getFirst().getImageFile());
			assertEquals(imageFilePath, photoDetailModelList.getFirst().getImageFilePath());
			assertNull(photoDetailModelList.getFirst().getPhotoJapaneseTitle());
			assertNull(photoDetailModelList.getFirst().getPhotoEnglishTitle());
			assertNull(photoDetailModelList.getFirst().getCaption());
			assertNull(photoDetailModelList.getFirst().getDirectionKbnCode());
			assertNull(photoDetailModelList.getFirst().getFocalLength());
			assertNull(photoDetailModelList.getFirst().getFValue());
			assertNull(photoDetailModelList.getFirst().getShutterSpeed());
			assertNull(photoDetailModelList.getFirst().getIso());
			assertEquals(new ArrayList<PhotoDetailModel>(), photoDetailModelList.getFirst().getPhotoTagModelList());
			
			assertEquals(photoAccountId, photoAcountIdCaptor.getValue());
		}
	}
	
	@Nested
	@Order(3)
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class deletePhoto {
		@Test
		@Order(1)
		@SuppressWarnings("unchecked")
		@DisplayName("正常系")
		void deletePhoto_success() throws UpdateFailureException, BadRequestException, ForbiddenAccountException {
			String photoAccountId = "aaaaaaaa";
			String loginAccountId = "aaaaaaaa";
			String imageFilePath = "https://localhost:8080/image/aaaaaaaa/DSC111.jpg";
			
			PhotoDeleteRequest photoDeleteRequest = new PhotoDeleteRequest();
			photoDeleteRequest.setAccountNo(1);
			photoDeleteRequest.setPhotoNo(1);
			photoDeleteRequest.setImageFilePath(imageFilePath);
			
			BindingResult result = new DataBinder(photoDeleteRequest).getBindingResult();
			
			doReturn(loginAccountId).when(sessionHelper).getAccountId();
			
			ArgumentCaptor<List<PhotoDeleteModel>> photoDeleteModelCaptor = ArgumentCaptor.forClass(List.class);
			ArgumentCaptor<String> photoAcountIdCaptor = ArgumentCaptor.forClass(String.class);
			doNothing().when(photoServiceImpl).deletePhotos(photoAcountIdCaptor.capture(), photoDeleteModelCaptor.capture());
			
			ResponseEntity<PhotoEditResponse> actual
				= photoRestController.deletePhoto(photoAccountId, photoDeleteRequest, result);
			
			assertEquals(HttpStatus.OK, actual.getStatusCode());
			assertEquals(HttpStatus.OK.value(), actual.getBody().getHttpStatus());
			assertEquals(true, actual.getBody().getIsSuccess());
			assertEquals("写真削除が完了しました。", actual.getBody().getMessage());
			
			List<PhotoDeleteModel> photoDeleteModelList = photoDeleteModelCaptor.getValue();
			assertEquals(1, photoDeleteModelList.size());
			assertEquals(1, photoDeleteModelList.getFirst().getAccountNo());
			assertEquals(1, photoDeleteModelList.getFirst().getPhotoNo());
			assertEquals(imageFilePath, photoDeleteModelList.getFirst().getImageFilePath());
			assertEquals(photoAccountId, photoAcountIdCaptor.getValue());
		}
		
		@Test
		@Order(2)
		@DisplayName("異常系：不正アクセス。ForbiddenAccountExceptionをthrowする")
		void deletePhoto_ForbiddenAccountException() throws UpdateFailureException {
			String photoAccountId = "aaaaaaaa";
			String loginAccountId = "bbbbbbbb";
			String imageFilePath = "https://localhost:8080/image/aaaaaaaa/DSC111.jpg";
			
			PhotoDeleteRequest photoDeleteRequest = new PhotoDeleteRequest();
			photoDeleteRequest.setAccountNo(1);
			photoDeleteRequest.setPhotoNo(1);
			photoDeleteRequest.setImageFilePath(imageFilePath);
			
			BindingResult result = new DataBinder(photoDeleteRequest).getBindingResult();
			
			doReturn(loginAccountId).when(sessionHelper).getAccountId();
			
			assertThrows(ForbiddenAccountException.class, () -> photoRestController.deletePhoto(photoAccountId, photoDeleteRequest, result));

			verify(photoServiceImpl, times(0)).deletePhotos(any(), any());
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：パラメータ不正。BadRequestExceptionをthrowする")
		void deletePhoto_BadRequestException() throws UpdateFailureException {
			String photoAccountId = "aaaaaaaa";
			String loginAccountId = "aaaaaaaa";
			String imageFilePath = "https://localhost:8080/image/aaaaaaaa/DSC111.jpg";
			
			PhotoDeleteRequest photoDeleteRequest = new PhotoDeleteRequest();
			photoDeleteRequest.setAccountNo(1);
			photoDeleteRequest.setPhotoNo(1);
			photoDeleteRequest.setImageFilePath(imageFilePath);
			
			BindingResult result = new DataBinder(photoDeleteRequest).getBindingResult();
			FieldError fError = new FieldError("photoDeleteRequest","account_id", "");
			result.addError((ObjectError) fError);
			
			doReturn(loginAccountId).when(sessionHelper).getAccountId();
			
			assertThrows(BadRequestException.class, () -> photoRestController.deletePhoto(photoAccountId, photoDeleteRequest, result));
			verify(photoServiceImpl, times(0)).deletePhotos(any(), any());
		}
		
		@Test
		@Order(4)
		@SuppressWarnings("unchecked")
		@DisplayName("異常系：UpdateFailureExceptionをthrowする")
		void deletePhoto_UpdateFailureException() throws UpdateFailureException {
			String photoAccountId = "aaaaaaaa";
			String loginAccountId = "aaaaaaaa";
			String imageFilePath = "https://localhost:8080/image/aaaaaaaa/DSC111.jpg";
			
			PhotoDeleteRequest photoDeleteRequest = new PhotoDeleteRequest();
			photoDeleteRequest.setAccountNo(1);
			photoDeleteRequest.setPhotoNo(1);
			photoDeleteRequest.setImageFilePath(imageFilePath);
			
			BindingResult result = new DataBinder(photoDeleteRequest).getBindingResult();
			
			doReturn(loginAccountId).when(sessionHelper).getAccountId();
			
			ArgumentCaptor<List<PhotoDeleteModel>> photoDeleteModelCaptor = ArgumentCaptor.forClass(List.class);
			ArgumentCaptor<String> photoAcountIdCaptor = ArgumentCaptor.forClass(String.class);
			doThrow(UpdateFailureException.class).when(photoServiceImpl).deletePhotos(photoAcountIdCaptor.capture(), photoDeleteModelCaptor.capture());
			
			assertThrows(UpdateFailureException.class, () -> photoRestController.deletePhoto(photoAccountId, photoDeleteRequest, result));
			
			List<PhotoDeleteModel> photoDeleteModelList = photoDeleteModelCaptor.getValue();
			assertEquals(1, photoDeleteModelList.size());
			assertEquals(1, photoDeleteModelList.getFirst().getAccountNo());
			assertEquals(1, photoDeleteModelList.getFirst().getPhotoNo());
			assertEquals(imageFilePath, photoDeleteModelList.getFirst().getImageFilePath());
			assertEquals(photoAccountId, photoAcountIdCaptor.getValue());
		}
	}
	
	@Nested
	@Order(4)
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class createPhotoListGetResponse {
		private List<PhotoModel> createPhotoList() {
			List<PhotoModel> photoList = new ArrayList<PhotoModel>();
			photoList.add(PhotoModel.builder()
					.accountNo(1)
					.photoNo(1)
					.favoriteCount(1)
					.isFavorite(false)
					.photoAt(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.imageFilePath("https://localhost:8080/image/aaaaaaaa/DSC111.jpg")
					.caption("キャプション1")
					.directionKbn(DirectionEnum.VERTICAL)
					.photoTagModelList(new ArrayList<PhotoTagModel>())
					.build());
			photoList.add(PhotoModel.builder()
					.accountNo(1)
					.photoNo(2)
					.favoriteCount(1)
					.isFavorite(true)
					.photoAt(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.imageFilePath("https://localhost:8080/image/aaaaaaaa/DSC222.jpg")
					.caption("キャプション2")
					.directionKbn(DirectionEnum.HORIZONTAL)
					.photoTagModelList(new ArrayList<PhotoTagModel>())
					.build());
			photoList.add(PhotoModel.builder()
					.accountNo(1)
					.photoNo(3)
					.favoriteCount(1)
					.isFavorite(true)
					.photoAt(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.imageFilePath("https://localhost:8080/image/aaaaaaaa/DSC333.jpg")
					.caption("キャプション3")
					.directionKbn(DirectionEnum.HORIZONTAL)
					.photoTagModelList(new ArrayList<PhotoTagModel>())
					.build());
			photoList.add(PhotoModel.builder()
					.accountNo(1)
					.photoNo(4)
					.favoriteCount(1)
					.isFavorite(true)
					.photoAt(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.imageFilePath("https://localhost:8080/image/aaaaaaaa/DSC444.jpg")
					.caption("キャプション4")
					.directionKbn(DirectionEnum.HORIZONTAL)
					.photoTagModelList(new ArrayList<PhotoTagModel>())
					.build());
			photoList.add(PhotoModel.builder()
					.accountNo(1)
					.photoNo(5)
					.favoriteCount(1)
					.isFavorite(true)
					.photoAt(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.imageFilePath("https://localhost:8080/image/aaaaaaaa/DSC555.jpg")
					.caption("キャプション5")
					.directionKbn(DirectionEnum.HORIZONTAL)
					.photoTagModelList(new ArrayList<PhotoTagModel>())
					.build());
			photoList.add(PhotoModel.builder()
					.accountNo(1)
					.photoNo(6)
					.favoriteCount(1)
					.isFavorite(true)
					.photoAt(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.imageFilePath("https://localhost:8080/image/aaaaaaaa/DSC666.jpg")
					.caption("キャプション6")
					.directionKbn(DirectionEnum.HORIZONTAL)
					.photoTagModelList(new ArrayList<PhotoTagModel>())
					.build());
			photoList.add(PhotoModel.builder()
					.accountNo(1)
					.photoNo(7)
					.favoriteCount(1)
					.isFavorite(true)
					.photoAt(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.imageFilePath("https://localhost:8080/image/aaaaaaaa/DSC777.jpg")
					.caption("キャプション7")
					.directionKbn(DirectionEnum.HORIZONTAL)
					.photoTagModelList(new ArrayList<PhotoTagModel>())
					.build());
			
			return photoList;
		}
		
		@Test
		@Order(1)
		@DisplayName("正常系：ページ番号が1で、2ページに到達しない")
		void createPhotoListGetResponse_pageNo_1_lastPage() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
			Integer pageNo = 1;
			List<PhotoModel> photoList = createPhotoList().subList(0, 1);
			
			doReturn(3).when(photoConfig).getPhotoCountPerPage();
			
			Method createPhotoListGetResponse = PhotoRestController.class.getDeclaredMethod("createPhotoListGetResponse", List.class, Integer.class);
			createPhotoListGetResponse.setAccessible(true);
			
			PhotoListGetResponse actual = (PhotoListGetResponse) createPhotoListGetResponse.invoke(photoRestController, photoList, pageNo);
			assertEquals(1, actual.getPhotoList().size());
			assertEquals(1, actual.getPhotoList().getFirst().getAccountNo());
			assertEquals(1, actual.getPhotoList().getFirst().getPhotoNo());
			assertFalse(actual.getPhotoList().getFirst().getIsFavorite());
			assertEquals("https://localhost:8080/image/aaaaaaaa/DSC111.jpg", actual.getPhotoList().getFirst().getImageFilePath());
			assertEquals("キャプション1", actual.getPhotoList().getFirst().getCaption());
			assertEquals(DirectionEnum.VERTICAL, actual.getPhotoList().getFirst().getDirectionKbn());
			assertTrue(actual.getIsLast());
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：ページ番号が1で、2ページに到達する")
		void createPhotoListGetResponse_pageNo_1() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
			Integer pageNo = 1;
			List<PhotoModel> photoList = createPhotoList().subList(0, 4);
			
			doReturn(3).when(photoConfig).getPhotoCountPerPage();
			
			Method createPhotoListGetResponse = PhotoRestController.class.getDeclaredMethod("createPhotoListGetResponse", List.class, Integer.class);
			createPhotoListGetResponse.setAccessible(true);
			
			PhotoListGetResponse actual = (PhotoListGetResponse) createPhotoListGetResponse.invoke(photoRestController, photoList, pageNo);
			assertEquals(3, actual.getPhotoList().size());
			assertEquals(1, actual.getPhotoList().get(0).getAccountNo());
			assertEquals(1, actual.getPhotoList().get(0).getPhotoNo());
			assertFalse(actual.getPhotoList().get(0).getIsFavorite());
			assertEquals("https://localhost:8080/image/aaaaaaaa/DSC111.jpg", actual.getPhotoList().get(0).getImageFilePath());
			assertEquals("キャプション1", actual.getPhotoList().get(0).getCaption());
			assertEquals(DirectionEnum.VERTICAL, actual.getPhotoList().get(0).getDirectionKbn());
			assertEquals(1, actual.getPhotoList().get(1).getAccountNo());
			assertEquals(2, actual.getPhotoList().get(1).getPhotoNo());
			assertTrue(actual.getPhotoList().get(1).getIsFavorite());
			assertEquals("https://localhost:8080/image/aaaaaaaa/DSC222.jpg", actual.getPhotoList().get(1).getImageFilePath());
			assertEquals("キャプション2", actual.getPhotoList().get(1).getCaption());
			assertEquals(DirectionEnum.HORIZONTAL, actual.getPhotoList().get(1).getDirectionKbn());
			assertEquals(1, actual.getPhotoList().get(2).getAccountNo());
			assertEquals(3, actual.getPhotoList().get(2).getPhotoNo());
			assertTrue(actual.getPhotoList().get(2).getIsFavorite());
			assertEquals("https://localhost:8080/image/aaaaaaaa/DSC333.jpg", actual.getPhotoList().get(2).getImageFilePath());
			assertEquals("キャプション3", actual.getPhotoList().get(2).getCaption());
			assertEquals(DirectionEnum.HORIZONTAL, actual.getPhotoList().get(2).getDirectionKbn());
			assertFalse(actual.getIsLast());
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：ページ番号が2で、3ページに到達しない")
		void createPhotoListGetResponse_pageNo_2_lastPage() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
			Integer pageNo = 2;
			List<PhotoModel> photoList = createPhotoList().subList(0, 4);
			
			doReturn(3).when(photoConfig).getPhotoCountPerPage();
			
			Method createPhotoListGetResponse = PhotoRestController.class.getDeclaredMethod("createPhotoListGetResponse", List.class, Integer.class);
			createPhotoListGetResponse.setAccessible(true);
			
			PhotoListGetResponse actual = (PhotoListGetResponse) createPhotoListGetResponse.invoke(photoRestController, photoList, pageNo);
			assertEquals(1, actual.getPhotoList().size());
			assertEquals(1, actual.getPhotoList().get(0).getAccountNo());
			assertEquals(4, actual.getPhotoList().get(0).getPhotoNo());
			assertTrue(actual.getPhotoList().get(0).getIsFavorite());
			assertEquals("https://localhost:8080/image/aaaaaaaa/DSC444.jpg", actual.getPhotoList().get(0).getImageFilePath());
			assertEquals("キャプション4", actual.getPhotoList().get(0).getCaption());
			assertEquals(DirectionEnum.HORIZONTAL, actual.getPhotoList().get(0).getDirectionKbn());
			assertTrue(actual.getIsLast());
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：ページ番号が2で、3ページに到達する")
		void createPhotoListGetResponse_pageNo_2() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
			Integer pageNo = 2;
			List<PhotoModel> photoList = createPhotoList();
			
			doReturn(3).when(photoConfig).getPhotoCountPerPage();
			
			Method createPhotoListGetResponse = PhotoRestController.class.getDeclaredMethod("createPhotoListGetResponse", List.class, Integer.class);
			createPhotoListGetResponse.setAccessible(true);
			
			PhotoListGetResponse actual = (PhotoListGetResponse) createPhotoListGetResponse.invoke(photoRestController, photoList, pageNo);
			assertEquals(3, actual.getPhotoList().size());
			assertEquals(1, actual.getPhotoList().get(0).getAccountNo());
			assertEquals(4, actual.getPhotoList().get(0).getPhotoNo());
			assertTrue(actual.getPhotoList().get(0).getIsFavorite());
			assertEquals("https://localhost:8080/image/aaaaaaaa/DSC444.jpg", actual.getPhotoList().get(0).getImageFilePath());
			assertEquals("キャプション4", actual.getPhotoList().get(0).getCaption());
			assertEquals(DirectionEnum.HORIZONTAL, actual.getPhotoList().get(0).getDirectionKbn());
			assertEquals(1, actual.getPhotoList().get(1).getAccountNo());
			assertEquals(5, actual.getPhotoList().get(1).getPhotoNo());
			assertTrue(actual.getPhotoList().get(1).getIsFavorite());
			assertEquals("https://localhost:8080/image/aaaaaaaa/DSC555.jpg", actual.getPhotoList().get(1).getImageFilePath());
			assertEquals("キャプション5", actual.getPhotoList().get(1).getCaption());
			assertEquals(DirectionEnum.HORIZONTAL, actual.getPhotoList().get(1).getDirectionKbn());
			assertEquals(1, actual.getPhotoList().get(2).getAccountNo());
			assertEquals(6, actual.getPhotoList().get(2).getPhotoNo());
			assertTrue(actual.getPhotoList().get(2).getIsFavorite());
			assertEquals("https://localhost:8080/image/aaaaaaaa/DSC666.jpg", actual.getPhotoList().get(2).getImageFilePath());
			assertEquals("キャプション6", actual.getPhotoList().get(2).getCaption());
			assertEquals(DirectionEnum.HORIZONTAL, actual.getPhotoList().get(2).getDirectionKbn());
			assertFalse(actual.getIsLast());
		}
	}
}