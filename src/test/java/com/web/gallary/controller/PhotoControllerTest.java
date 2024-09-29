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
public class PhotoControllerTest {

	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class photoList {
		@Test
		@Order(1)
		@DisplayName("正常系：非ログインユーザーの場合")
		void photoList_not_login_user() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：ログインユーザー（ページ所有者でない）の場合")
		void photoList_login_user_not_owner() {
			assertTrue(false);
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