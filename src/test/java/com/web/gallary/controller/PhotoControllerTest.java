package com.web.gallary.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.servlet.ModelAndView;

import com.web.gallary.config.PhotoConfig;
import com.web.gallary.controller.request.PhotoDetailRequest;
import com.web.gallary.controller.request.PhotoSettingRequest;
import com.web.gallary.controller.request.PhotoTagRequest;
import com.web.gallary.entity.Account;
import com.web.gallary.exception.ForbiddenAccountException;
import com.web.gallary.exception.PhotoNotFoundException;
import com.web.gallary.helper.SessionHelper;
import com.web.gallary.model.PhotoDetailGetModel;
import com.web.gallary.model.PhotoDetailModel;
import com.web.gallary.service.impl.AccountServiceImpl;
import com.web.gallary.service.impl.PhotoServiceImpl;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class PhotoControllerTest {
	@InjectMocks
	private PhotoController photoController;
	
	@Mock
	private PhotoServiceImpl photoServiceImpl;
	
	@Mock
	private AccountServiceImpl accountServiceImpl;
	
	@Mock
	private SessionHelper sessionHelper;
	
	@Mock
	private PhotoConfig photoConfig;
	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class photoList {
		@Test
		@Order(1)
		@DisplayName("正常系：非ログインユーザーの場合")
		void photoList_not_login_user() {
			String photoAccountId = "aaaaaaaa";
			String accountName = "AAAAAAAA";
			doReturn(null).when(sessionHelper).getAccountNo();
			doReturn(true).when(photoServiceImpl).isReachedUpperLimit(null);
			doReturn(null).when(sessionHelper).getAccountId();
			Account account = Account.builder().accountId(photoAccountId).accountName(accountName).build();
			doReturn(account).when(accountServiceImpl).getAccountById(photoAccountId);
			
			ModelAndView  actual = photoController.photoList(photoAccountId);
			Map<String, Object> models = actual.getModel();
			assertEquals("photo_list", actual.getViewName());
			assertEquals(7, models.size());
			assertEquals(photoAccountId, models.get("ownerId").toString());
			assertEquals(false, (Boolean) models.get("isOwner"));
			assertEquals(false, (Boolean) models.get("isAbleToAddPhoto"));
			assertEquals(accountName, models.get("accountName").toString());
			assertEquals("/account_list", models.get("account_list_url").toString());
			assertEquals("/photo/" + photoAccountId + "/photo_list", models.get("photo_list_url").toString());
			assertEquals("/photo/" + photoAccountId + "/photo_detail", models.get("photo_detail_url").toString());
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：ログインユーザー（ページ所有者でない）の場合")
		void photoList_login_user_not_owner() {
			String photoAccountId = "aaaaaaaa";
			String accountName = "AAAAAAAA";
			String loginAccountId = "bbbbbbbb";
			doReturn(1).when(sessionHelper).getAccountNo();
			doReturn(true).when(photoServiceImpl).isReachedUpperLimit(1);
			doReturn(loginAccountId).when(sessionHelper).getAccountId();
			Account account = Account.builder().accountId(photoAccountId).accountName(accountName).build();
			doReturn(account).when(accountServiceImpl).getAccountById(photoAccountId);
			
			ModelAndView  actual = photoController.photoList(photoAccountId);
			Map<String, Object> models = actual.getModel();
			assertEquals("photo_list", actual.getViewName());
			assertEquals(12, models.size());
			assertEquals(photoAccountId, models.get("ownerId").toString());
			assertEquals(false, (Boolean) models.get("isOwner"));
			assertEquals(false, (Boolean) models.get("isAbleToAddPhoto"));
			assertEquals(accountName, models.get("accountName").toString());
			assertEquals("/account_list", models.get("account_list_url").toString());
			assertEquals("/photo/" + photoAccountId + "/photo_list", models.get("photo_list_url").toString());
			assertEquals("/photo/" + photoAccountId + "/photo_detail", models.get("photo_detail_url").toString());
			assertEquals("/" + loginAccountId + "/account_setting", models.get("account_setting_url").toString());
			assertEquals("/photo/" + loginAccountId + "/photo_list", models.get("my_photo_list_url").toString());
			assertEquals("/photo/" + loginAccountId + "/photo_setting", models.get("photo_setting_url").toString());
			assertEquals("/photo/" + loginAccountId + "/delete", models.get("photo_delete_url").toString());
			assertEquals("/photo/" + loginAccountId + "/save", models.get("photo_save_url").toString());
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：ログインユーザー（ページ所有者）で、登録枚数の上限に達していないの場合")
		void photoList_login_user_owner_not_ReachedUpperLimit() {
			String photoAccountId = "aaaaaaaa";
			String accountName = "AAAAAAAA";
			String loginAccountId = "aaaaaaaa";
			doReturn(1).when(sessionHelper).getAccountNo();
			doReturn(false).when(photoServiceImpl).isReachedUpperLimit(1);
			doReturn(loginAccountId).when(sessionHelper).getAccountId();
			Account account = Account.builder().accountId(photoAccountId).accountName(accountName).build();
			doReturn(account).when(accountServiceImpl).getAccountById(photoAccountId);
			
			ModelAndView  actual = photoController.photoList(photoAccountId);
			Map<String, Object> models = actual.getModel();
			assertEquals("photo_list", actual.getViewName());
			assertEquals(12, models.size());
			assertEquals(photoAccountId, models.get("ownerId").toString());
			assertEquals(true, (Boolean) models.get("isOwner"));
			assertEquals(true, (Boolean) models.get("isAbleToAddPhoto"));
			assertEquals(accountName, models.get("accountName").toString());
			assertEquals("/account_list", models.get("account_list_url").toString());
			assertEquals("/photo/" + photoAccountId + "/photo_list", models.get("photo_list_url").toString());
			assertEquals("/photo/" + photoAccountId + "/photo_detail", models.get("photo_detail_url").toString());
			assertEquals("/" + loginAccountId + "/account_setting", models.get("account_setting_url").toString());
			assertEquals("/photo/" + loginAccountId + "/photo_list", models.get("my_photo_list_url").toString());
			assertEquals("/photo/" + loginAccountId + "/photo_setting", models.get("photo_setting_url").toString());
			assertEquals("/photo/" + loginAccountId + "/delete", models.get("photo_delete_url").toString());
			assertEquals("/photo/" + loginAccountId + "/save", models.get("photo_save_url").toString());
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：ログインユーザー（ページ所有者）で、登録枚数の上限に達している場合")
		void photoList_login_user_owner_ReachedUpperLimit() {
			String photoAccountId = "aaaaaaaa";
			String accountName = "AAAAAAAA";
			String loginAccountId = "aaaaaaaa";
			
			doReturn(1).when(sessionHelper).getAccountNo();
			doReturn(true).when(photoServiceImpl).isReachedUpperLimit(1);
			doReturn(loginAccountId).when(sessionHelper).getAccountId();
			Account account = Account.builder().accountId(photoAccountId).accountName(accountName).build();
			doReturn(account).when(accountServiceImpl).getAccountById(photoAccountId);
			
			ModelAndView  actual = photoController.photoList(photoAccountId);
			Map<String, Object> models = actual.getModel();
			assertEquals("photo_list", actual.getViewName());
			assertEquals(12, models.size());
			assertEquals(photoAccountId, models.get("ownerId").toString());
			assertEquals(true, (Boolean) models.get("isOwner"));
			assertEquals(false, (Boolean) models.get("isAbleToAddPhoto"));
			assertEquals(accountName, models.get("accountName").toString());
			assertEquals("/account_list", models.get("account_list_url").toString());
			assertEquals("/photo/" + photoAccountId + "/photo_list", models.get("photo_list_url").toString());
			assertEquals("/photo/" + photoAccountId + "/photo_detail", models.get("photo_detail_url").toString());
			assertEquals("/" + loginAccountId + "/account_setting", models.get("account_setting_url").toString());
			assertEquals("/photo/" + loginAccountId + "/photo_list", models.get("my_photo_list_url").toString());
			assertEquals("/photo/" + loginAccountId + "/photo_setting", models.get("photo_setting_url").toString());
			assertEquals("/photo/" + loginAccountId + "/delete", models.get("photo_delete_url").toString());
			assertEquals("/photo/" + loginAccountId + "/save", models.get("photo_save_url").toString());
		}
	}
	
	@Nested
	@Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class photoDetail {
		@Test
		@Order(1)
		@DisplayName("正常系：非ログインユーザーの場合")
		void photoDetail_not_login_user() throws PhotoNotFoundException {
			String photoAccountId = "aaaaaaaa";
			String accountName = "AAAAAAAA";
			
			PhotoDetailRequest photoDetailRequest = new PhotoDetailRequest();
			photoDetailRequest.setAccountNo(1);
			photoDetailRequest.setPhotoNo(1);
			
			PhotoDetailModel photoDetailModel = PhotoDetailModel.builder()
					.accountNo(1)
					.photoNo(1)
					.imageFilePath("https://localhost:8080/image/DSC111.jpg")
					.build();
			
			doReturn(null).when(sessionHelper).getAccountNo();
			doReturn(null).when(sessionHelper).getAccountId();
			
			ArgumentCaptor<PhotoDetailGetModel> photoDetailGetModelCaptor = ArgumentCaptor.forClass(PhotoDetailGetModel.class);
			doReturn(photoDetailModel).when(photoServiceImpl).getPhotoDetail(photoDetailGetModelCaptor.capture());
			
			Account account = Account.builder().accountId(photoAccountId).accountName(accountName).build();
			doReturn(account).when(accountServiceImpl).getAccountById(photoAccountId);
			
			ModelAndView actual = photoController.photoDetail(photoAccountId, photoDetailRequest);
			Map<String, Object> models = actual.getModel();
			assertEquals("photo_detail", actual.getViewName());
			assertEquals(6, models.size());
			assertEquals(photoDetailModel, (PhotoDetailModel) models.get("PhotoSettingRequest"));
			assertEquals(false, (Boolean) models.get("isOwner"));
			assertEquals(accountName, models.get("accountName").toString());
			assertEquals("/account_list", models.get("account_list_url").toString());
			assertEquals("/photo/" + photoAccountId + "/photo_list", models.get("photo_list_url").toString());
			assertEquals("/photo/" + photoAccountId + "/photo_detail", models.get("photo_detail_url").toString());
			
			PhotoDetailGetModel photoDetailGetModel = photoDetailGetModelCaptor.getValue();
			assertNull(photoDetailGetModel.getAccountNo());
			assertEquals(1, photoDetailGetModel.getPhotoAccountNo());
			assertEquals(1, photoDetailGetModel.getPhotoNo());
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：ログインユーザー（ページ所有者でない）の場合")
		void photoDetail_login_user_not_owner() throws PhotoNotFoundException {
			String photoAccountId = "aaaaaaaa";
			String accountName = "AAAAAAAA";
			String loginAccountId = "bbbbbbbb";
			
			PhotoDetailRequest photoDetailRequest = new PhotoDetailRequest();
			photoDetailRequest.setAccountNo(1);
			photoDetailRequest.setPhotoNo(1);
			
			PhotoDetailModel photoDetailModel = PhotoDetailModel.builder()
					.accountNo(1)
					.photoNo(1)
					.imageFilePath("https://localhost:8080/image/DSC111.jpg")
					.build();
			
			doReturn(1).when(sessionHelper).getAccountNo();
			doReturn(loginAccountId).when(sessionHelper).getAccountId();
			
			ArgumentCaptor<PhotoDetailGetModel> photoDetailGetModelCaptor = ArgumentCaptor.forClass(PhotoDetailGetModel.class);
			doReturn(photoDetailModel).when(photoServiceImpl).getPhotoDetail(photoDetailGetModelCaptor.capture());
			
			Account account = Account.builder().accountId(photoAccountId).accountName(accountName).build();
			doReturn(account).when(accountServiceImpl).getAccountById(photoAccountId);
			
			ModelAndView actual = photoController.photoDetail(photoAccountId, photoDetailRequest);
			Map<String, Object> models = actual.getModel();
			assertEquals("photo_detail", actual.getViewName());
			assertEquals(11, models.size());
			assertEquals(photoDetailModel, (PhotoDetailModel) models.get("PhotoSettingRequest"));
			assertEquals(false, (Boolean) models.get("isOwner"));
			assertEquals(accountName, models.get("accountName").toString());
			assertEquals("/account_list", models.get("account_list_url").toString());
			assertEquals("/photo/" + photoAccountId + "/photo_list", models.get("photo_list_url").toString());
			assertEquals("/photo/" + photoAccountId + "/photo_detail", models.get("photo_detail_url").toString());
			assertEquals("/" + loginAccountId + "/account_setting", models.get("account_setting_url").toString());
			assertEquals("/photo/" + loginAccountId + "/photo_list", models.get("my_photo_list_url").toString());
			assertEquals("/photo/" + loginAccountId + "/photo_setting", models.get("photo_setting_url").toString());
			assertEquals("/photo/" + loginAccountId + "/delete", models.get("photo_delete_url").toString());
			assertEquals("/photo/" + loginAccountId + "/save", models.get("photo_save_url").toString());
			
			PhotoDetailGetModel photoDetailGetModel = photoDetailGetModelCaptor.getValue();
			assertEquals(1, photoDetailGetModel.getAccountNo());
			assertEquals(1, photoDetailGetModel.getPhotoAccountNo());
			assertEquals(1, photoDetailGetModel.getPhotoNo());
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：ログインユーザー（ページ所有者）の場合")
		void photoDetail_login_user_owner() throws PhotoNotFoundException {
			String photoAccountId = "aaaaaaaa";
			String accountName = "AAAAAAAA";
			String loginAccountId = "aaaaaaaa";
			
			PhotoDetailRequest photoDetailRequest = new PhotoDetailRequest();
			photoDetailRequest.setAccountNo(1);
			photoDetailRequest.setPhotoNo(1);
			
			PhotoDetailModel photoDetailModel = PhotoDetailModel.builder()
					.accountNo(1)
					.photoNo(1)
					.imageFilePath("https://localhost:8080/image/DSC111.jpg")
					.build();
			
			doReturn(1).when(sessionHelper).getAccountNo();
			doReturn(loginAccountId).when(sessionHelper).getAccountId();

			Account account = Account.builder().accountId(photoAccountId).accountName(accountName).build();
			doReturn(account).when(accountServiceImpl).getAccountById(photoAccountId);
			
			ArgumentCaptor<PhotoDetailGetModel> photoDetailGetModelCaptor = ArgumentCaptor.forClass(PhotoDetailGetModel.class);
			doReturn(photoDetailModel).when(photoServiceImpl).getPhotoDetail(photoDetailGetModelCaptor.capture());
			
			ModelAndView actual = photoController.photoDetail(photoAccountId, photoDetailRequest);
			Map<String, Object> models = actual.getModel();
			assertEquals("photo_detail", actual.getViewName());
			assertEquals(11, models.size());
			assertEquals(photoDetailModel, (PhotoDetailModel) models.get("PhotoSettingRequest"));
			assertEquals(true, (Boolean) models.get("isOwner"));
			assertEquals(accountName, models.get("accountName").toString());
			assertEquals("/account_list", models.get("account_list_url").toString());
			assertEquals("/photo/" + photoAccountId + "/photo_list", models.get("photo_list_url").toString());
			assertEquals("/photo/" + photoAccountId + "/photo_detail", models.get("photo_detail_url").toString());
			assertEquals("/" + loginAccountId + "/account_setting", models.get("account_setting_url").toString());
			assertEquals("/photo/" + loginAccountId + "/photo_list", models.get("my_photo_list_url").toString());
			assertEquals("/photo/" + loginAccountId + "/photo_setting", models.get("photo_setting_url").toString());
			assertEquals("/photo/" + loginAccountId + "/delete", models.get("photo_delete_url").toString());
			assertEquals("/photo/" + loginAccountId + "/save", models.get("photo_save_url").toString());
			
			PhotoDetailGetModel photoDetailGetModel = photoDetailGetModelCaptor.getValue();
			assertEquals(1, photoDetailGetModel.getAccountNo());
			assertEquals(1, photoDetailGetModel.getPhotoAccountNo());
			assertEquals(1, photoDetailGetModel.getPhotoNo());
		}
		
		@Test
		@Order(4)
		@DisplayName("異常系：PhotoNotFoundExceptionをthrowする")
		void photoDetail_PhotoNotFoundException() throws PhotoNotFoundException {
			String photoAccountId = "aaaaaaaa";
			String accountName = "AAAAAAAA";
			String loginAccountId = "aaaaaaaa";
			
			PhotoDetailRequest photoDetailRequest = new PhotoDetailRequest();
			photoDetailRequest.setAccountNo(1);
			photoDetailRequest.setPhotoNo(1);
			
			doReturn(1).when(sessionHelper).getAccountNo();
			doReturn(loginAccountId).when(sessionHelper).getAccountId();
			
			Account account = Account.builder().accountId(photoAccountId).accountName(accountName).build();
			doReturn(account).when(accountServiceImpl).getAccountById(photoAccountId);
			
			ArgumentCaptor<PhotoDetailGetModel> photoDetailGetModelCaptor = ArgumentCaptor.forClass(PhotoDetailGetModel.class);
			doThrow(PhotoNotFoundException.class).when(photoServiceImpl).getPhotoDetail(photoDetailGetModelCaptor.capture());
			
			assertThrows(PhotoNotFoundException.class, () -> photoController.photoDetail(photoAccountId, photoDetailRequest));
			
			verify(sessionHelper, times(1)).getAccountId();
			
			PhotoDetailGetModel photoDetailGetModel = photoDetailGetModelCaptor.getValue();
			assertEquals(1, photoDetailGetModel.getAccountNo());
			assertEquals(1, photoDetailGetModel.getPhotoAccountNo());
			assertEquals(1, photoDetailGetModel.getPhotoNo());
		}
	}
	
	@Nested
	@Order(3)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class photoSetting {
		private List<PhotoTagRequest> createPhotoTagRequestList() {
			List<PhotoTagRequest> photoTagRequestList = new ArrayList<PhotoTagRequest>();
			PhotoTagRequest photoTagRequest1 = new PhotoTagRequest();
			photoTagRequest1.setAccountNo(1);
			photoTagRequest1.setPhotoNo(1);
			photoTagRequest1.setTagNo(1);
			photoTagRequest1.setTagJapaneseName("太陽");
			photoTagRequest1.setTagEnglishName("sun");
			photoTagRequestList.add(photoTagRequest1);
			
			PhotoTagRequest photoTagRequest2 = new PhotoTagRequest();
			photoTagRequest2.setAccountNo(1);
			photoTagRequest2.setPhotoNo(1);
			photoTagRequest2.setTagNo(2);
			photoTagRequest2.setTagJapaneseName("海");
			photoTagRequest2.setTagEnglishName("sea");
			photoTagRequestList.add(photoTagRequest2);
			
			PhotoTagRequest photoTagRequest3 = new PhotoTagRequest();
			photoTagRequest3.setAccountNo(1);
			photoTagRequest3.setPhotoNo(1);
			photoTagRequest3.setTagNo(3);
			photoTagRequest3.setTagJapaneseName("空");
			photoTagRequest3.setTagEnglishName("sky");
			photoTagRequestList.add(photoTagRequest3);

			return photoTagRequestList;
		}
		
		@Test
		@Order(1)
		@DisplayName("正常系：新規登録（写真タグが未登録）")
		void photoSetting_addPhoto() throws ForbiddenAccountException {
			String accountId = "aaaaaaaa";
			String accountName = "AAAAAAAA";
			
			PhotoSettingRequest photoSettingRequest = new PhotoSettingRequest();
			
			doReturn(accountId).when(sessionHelper).getAccountId();
			doReturn(1).when(sessionHelper).getAccountNo();
			doReturn(5).when(photoConfig).getMaxFileSizeMb();
			
			Account account = Account.builder().accountName(accountName).build();
			doReturn(account).when(accountServiceImpl).getAccountById(accountId);
			
			ModelAndView actual = photoController.photoSetting(accountId, photoSettingRequest);
			assertEquals("photo_setting", actual.getViewName());
			Map<String, Object> models = actual.getModel();
			photoSettingRequest.setAccountNo(1);
			assertEquals(photoSettingRequest, (PhotoSettingRequest) models.get("PhotoSettingRequest"));
			assertEquals(12, models.size());
			assertEquals(5, (Integer) models.get("maxFileSizeMb"));
			assertEquals(true, (Boolean) models.get("isAddMode"));
			assertEquals(accountName, models.get("accountName").toString());
			assertEquals("/account_list", models.get("account_list_url").toString());
			assertEquals("/photo/" + accountId + "/photo_list", models.get("photo_list_url").toString());
			assertEquals("/photo/" + accountId + "/photo_detail", models.get("photo_detail_url").toString());
			assertEquals("/" + accountId + "/account_setting", models.get("account_setting_url").toString());
			assertEquals("/photo/" + accountId + "/photo_list", models.get("my_photo_list_url").toString());
			assertEquals("/photo/" + accountId + "/photo_setting", models.get("photo_setting_url").toString());
			assertEquals("/photo/" + accountId + "/delete", models.get("photo_delete_url").toString());
			assertEquals("/photo/" + accountId + "/save", models.get("photo_save_url").toString());
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：更新（写真タグが登録済み）")
		void photoSetting_updatePhoto_with_photoTag() throws ForbiddenAccountException {
			String accountId = "aaaaaaaa";
			String accountName = "AAAAAAAA";
			
			List<PhotoTagRequest> photoTagRequestList = createPhotoTagRequestList();
			
			PhotoSettingRequest photoSettingRequest = new PhotoSettingRequest();
			photoSettingRequest.setAccountNo(1);
			photoSettingRequest.setPhotoNo(1);
			photoSettingRequest.setPhotoTagRequestList(photoTagRequestList);
			
			doReturn(accountId).when(sessionHelper).getAccountId();
			doReturn(1).when(sessionHelper).getAccountNo();
			doReturn(5).when(photoConfig).getMaxFileSizeMb();
			
			Account account = Account.builder().accountName(accountName).build();
			doReturn(account).when(accountServiceImpl).getAccountById(accountId);
			
			ModelAndView actual = photoController.photoSetting(accountId, photoSettingRequest);
			assertEquals("photo_setting", actual.getViewName());
			Map<String, Object> models = actual.getModel();
			photoSettingRequest.setAccountNo(1);
			assertEquals(photoSettingRequest, (PhotoSettingRequest) models.get("PhotoSettingRequest"));
			assertEquals(13, models.size());
			assertEquals(5, (Integer) models.get("maxFileSizeMb"));
			assertEquals(false, (Boolean) models.get("isAddMode"));
			assertEquals(photoTagRequestList.size(), (Integer) models.get("maxTagNo"));
			assertEquals(accountName, models.get("accountName").toString());
			assertEquals("/account_list", models.get("account_list_url").toString());
			assertEquals("/photo/" + accountId + "/photo_list", models.get("photo_list_url").toString());
			assertEquals("/photo/" + accountId + "/photo_detail", models.get("photo_detail_url").toString());
			assertEquals("/" + accountId + "/account_setting", models.get("account_setting_url").toString());
			assertEquals("/photo/" + accountId + "/photo_list", models.get("my_photo_list_url").toString());
			assertEquals("/photo/" + accountId + "/photo_setting", models.get("photo_setting_url").toString());
			assertEquals("/photo/" + accountId + "/delete", models.get("photo_delete_url").toString());
			assertEquals("/photo/" + accountId + "/save", models.get("photo_save_url").toString());
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：不正アクセスの場合")
		void photoSetting_ForbiddenAccountException() {
			String accountId = "aaaaaaaa";
			PhotoSettingRequest photoSettingRequest = new PhotoSettingRequest();
			photoSettingRequest.setAccountNo(1);
			photoSettingRequest.setPhotoNo(1);
			
			doReturn(null).when(sessionHelper).getAccountId();
			assertThrows(ForbiddenAccountException.class, () -> photoController.photoSetting(accountId, photoSettingRequest));
			
			verify(sessionHelper, times(0)).getAccountNo();
			verify(sessionHelper, times(1)).getAccountId();
			verify(photoConfig, times(0)).getMaxFileSizeMb();
			verify(accountServiceImpl, times(0)).getAccountById(any(String.class));
		}
	}
	
	@Nested
	@Order(4)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class getModelAndView {
		@Test
		@Order(1)
		@DisplayName("正常系：非ログインユーザーの場合")
		void getModelAndView_not_login_user() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
			Method getModelAndView = PhotoController.class.getDeclaredMethod("getModelAndView", String.class, String.class);
			getModelAndView.setAccessible(true);
			
			String viewName = "photo_setting";
			String photoAccountId = "aaaaaaaa";
			String accountName = "AAAAAAAA";
			
			Account account = Account.builder().accountName(accountName).build();
			doReturn(account).when(accountServiceImpl).getAccountById(photoAccountId);
			doReturn(null).when(sessionHelper).getAccountId();
			
			ModelAndView actual = (ModelAndView) getModelAndView.invoke(photoController, viewName, photoAccountId);
			assertEquals(viewName, actual.getViewName());
			Map<String, Object> models = actual.getModel();
			assertEquals(4, models.size());
			assertEquals(accountName, models.get("accountName").toString());
			assertEquals("/account_list", models.get("account_list_url").toString());
			assertEquals("/photo/" + photoAccountId + "/photo_list", models.get("photo_list_url").toString());
			assertEquals("/photo/" + photoAccountId + "/photo_detail", models.get("photo_detail_url").toString());
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：ログインユーザーの場合")
		void getModelAndView_login_user() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
			Method getModelAndView = PhotoController.class.getDeclaredMethod("getModelAndView", String.class, String.class);
			getModelAndView.setAccessible(true);
			
			String viewName = "photo_setting";
			String photoAccountId = "aaaaaaaa";
			String loginAccountId = "bbbbbbbb";
			String accountName = "AAAAAAAA";
			
			Account account = Account.builder().accountName(accountName).build();
			doReturn(account).when(accountServiceImpl).getAccountById(photoAccountId);
			doReturn(loginAccountId).when(sessionHelper).getAccountId();
			
			ModelAndView actual = (ModelAndView) getModelAndView.invoke(photoController, viewName, photoAccountId);
			assertEquals(viewName, actual.getViewName());
			Map<String, Object> models = actual.getModel();
			assertEquals(9, models.size());
			assertEquals(accountName, models.get("accountName").toString());
			assertEquals("/account_list", models.get("account_list_url").toString());
			assertEquals("/photo/" + photoAccountId + "/photo_list", models.get("photo_list_url").toString());
			assertEquals("/photo/" + photoAccountId + "/photo_detail", models.get("photo_detail_url").toString());
			
			assertEquals("/" + loginAccountId + "/account_setting", models.get("account_setting_url").toString());
			assertEquals("/photo/" + loginAccountId + "/photo_list", models.get("my_photo_list_url").toString());
			assertEquals("/photo/" + loginAccountId + "/photo_setting", models.get("photo_setting_url").toString());
			assertEquals("/photo/" + loginAccountId + "/delete", models.get("photo_delete_url").toString());
			assertEquals("/photo/" + loginAccountId + "/save", models.get("photo_save_url").toString());
		}
	}
}