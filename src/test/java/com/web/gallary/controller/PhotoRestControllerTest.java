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
public class PhotoRestControllerTest {

	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class getPhotoList {
		@Test
		@Order(1)
		@DisplayName("正常系：Nullのパラメータがある場合")
		void getPhotoList_with_null_parameter() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：タグに半角スペースが含まれている場合")
		void getPhotoList_with_halfspace_tag() {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：タグに全角スペースが含まれている場合")
		void getPhotoList_with_fullspace_tag() {
			assertTrue(false);
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：写真が0件の場合")
		void getPhotoList_not_found_photo() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class savePhoto {
		@Test
		@Order(1)
		@DisplayName("正常系：新規追加。写真タグなし、撮影日時なし")
		void savePhoto_addPhoto_not_photoTag_and_photoAt() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：新規追加。写真タグあり、撮影日時あり")
		void savePhoto_addPhoto_with_photoTag_and_photoAt() {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：更新。写真タグなし、撮影日時なし")
		void savePhoto_updatePhoto_not_photoTag_and_photoAt() {
			assertTrue(false);
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：更新。写真タグあり、撮影日時あり")
		void savePhoto_updatePhoto_with_photoTag_and_photoAt() {
			assertTrue(false);
		}
		
		@Test
		@Order(5)
		@DisplayName("異常系：アクセス不正。ForbiddenAccountExceptionをthrowする")
		void savePhoto_ForbiddenAccountException() {
			assertTrue(false);
		}
		
		@Test
		@Order(6)
		@DisplayName("異常系：登録上限に達している。PhotoNotAdditableExceptionをthrowする")
		void savePhoto_PhotoNotAdditableException() {
			assertTrue(false);
		}
		
		@Test
		@Order(7)
		@DisplayName("異常系：画像ファイル、ファイルパスともにnull。BadRequestExceptionをthrowする")
		void savePhoto_BadRequestException_file_and_filepath_is_null() {
			assertTrue(false);
		}
		
		@Test
		@Order(8)
		@DisplayName("異常系：画像ファイルがnull、ファイルパスがblank。BadRequestExceptionをthrowする")
		void savePhoto_BadRequestException_file_and_filepath_is_blank() {
			assertTrue(false);
		}
		
		@Test
		@Order(9)
		@DisplayName("異常系：画像ファイル、ファイルパス以外のパラメータ不正。BadRequestExceptionをthrowする")
		void savePhoto_BadRequestException_others() {
			// TODO 各種パラメータの不正パターンを網羅
			assertTrue(false);
		}
		
		@Test
		@Order(10)
		@DisplayName("異常系：FileDuplicateExceptionをthrowする")
		void savePhoto_FileDuplicateException() {
			assertTrue(false);
		}
		
		@Test
		@Order(11)
		@DisplayName("異常系：RegistFailureExceptionをthrowする")
		void savePhoto_RegistFailureException() {
			assertTrue(false);
		}
		
		@Test
		@Order(12)
		@DisplayName("異常系：UpdateFailureExceptionをthrowする")
		void savePhoto_UpdateFailureException() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(3)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class deletePhoto {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void deletePhoto_success() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("異常系：不正アクセス。ForbiddenAccountExceptionをthrowする")
		void deletePhoto_ForbiddenAccountException() {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：パラメータ不正。BadRequestExceptionをthrowする")
		void deletePhoto_BadRequestException() {
			// TODO 各種パラメータの不正パターンを網羅
			assertTrue(false);
		}
		
		@Test
		@Order(4)
		@DisplayName("異常系：UpdateFailureExceptionをthrowする")
		void deletePhoto_UpdateFailureException() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(4)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class createPhotoListGetResponse {
		@Test
		@Order(1)
		@DisplayName("正常系：ページ番号が1で、2ページに到達しない")
		void createPhotoListGetResponse_pageNo_1_lastPage() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：ページ番号が1で、2ページに到達する")
		void createPhotoListGetResponse_pageNo_1() {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：ページ番号が2で、3ページに到達しない")
		void createPhotoListGetResponse_pageNo_2_lastPage() {
			assertTrue(false);
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：ページ番号が2で、3ページに到達する")
		void createPhotoListGetResponse_pageNo_2() {
			assertTrue(false);
		}
	}
}