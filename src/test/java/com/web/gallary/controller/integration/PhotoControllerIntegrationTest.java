package com.web.gallary.controller.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.web.gallary.controller.PhotoController;
import com.web.gallary.controller.request.PhotoTagRequest;
import com.web.gallary.exception.ForbiddenAccountException;
import com.web.gallary.exception.PhotoNotFoundException;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
@Sql("/sql/common/ResetAccountNoSeq.sql")
public class PhotoControllerIntegrationTest {
	@Autowired
	private PhotoController photoController;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/controller/PhotoControllerIntegrationTest.sql")
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
	@Sql("/sql/controller/PhotoControllerIntegrationTest.sql")
	class photoDetail {
		@Test
		@Order(1)
		@DisplayName("正常系：非ログインユーザーの場合")
		void photoDetail_not_login_user() throws PhotoNotFoundException {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：ログインユーザー（ページ所有者でない）の場合")
		void photoDetail_login_user_not_owner() throws PhotoNotFoundException {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：ログインユーザー（ページ所有者）の場合")
		void photoDetail_login_user_owner() throws PhotoNotFoundException {
			assertTrue(false);
		}
		
		@Test
		@Order(4)
		@DisplayName("異常系：PhotoNotFoundExceptionをthrowする")
		void photoDetail_PhotoNotFoundException() throws PhotoNotFoundException {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(3)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/controller/PhotoControllerIntegrationTest.sql")
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
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：更新（写真タグが登録済み）")
		void photoSetting_updatePhoto_with_photoTag() throws ForbiddenAccountException {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：不正アクセスの場合")
		void photoSetting_ForbiddenAccountException() {
			assertTrue(false);
		}
	}
}
