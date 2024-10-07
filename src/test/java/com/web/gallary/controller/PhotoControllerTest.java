package com.web.gallary.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.servlet.ModelAndView;

import com.web.gallary.config.PhotoConfig;
import com.web.gallary.entity.Account;
import com.web.gallary.helper.SessionHelper;
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
			assertTrue(false);
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：ログインユーザー（ページ所有者）で、登録枚数の上限に達している場合")
		void photoList_login_user_owner_ReachedUpperLimit() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class photoDetail {
		@Test
		@Order(1)
		@DisplayName("正常系：非ログインユーザーの場合")
		void photoDetail_not_login_user() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：ログインユーザー（ページ所有者でない）の場合")
		void photoDetail_login_user_not_owner() {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：ログインユーザー（ページ所有者）の場合")
		void photoDetail_login_user_owner() {
			assertTrue(false);
		}
		
		@Test
		@Order(4)
		@DisplayName("異常系：PhotoNotFoundExceptionをthrowする")
		void photoDetail_PhotoNotFoundException() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(3)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class photoSetting {
		@Test
		@Order(1)
		@DisplayName("正常系：新規登録の場合")
		void photoSetting_addPhoto() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：更新で、写真タグが未登録の場合")
		void photoSetting_updatePhoto_not_photoTag() {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：更新で、写真タグが登録済みの場合")
		void photoSetting_updatePhoto_with_photoTag() {
			assertTrue(false);
		}
		
		@Test
		@Order(4)
		@DisplayName("異常系：不正アクセスの場合")
		void photoSetting_ForbiddenAccountException() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(4)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class getModelAndView {
		@Test
		@Order(1)
		@DisplayName("正常系：非ログインユーザーの場合")
		void getModelAndView_not_login_user() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：ログインユーザーの場合")
		void getModelAndView_login_user() {
			assertTrue(false);
		}
	}
}