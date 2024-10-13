package com.web.gallary.controller.integration;

import static org.junit.jupiter.api.Assertions.*;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.web.gallary.controller.PhotoRestController;
import com.web.gallary.exception.BadRequestException;
import com.web.gallary.exception.FileDuplicateException;
import com.web.gallary.exception.ForbiddenAccountException;
import com.web.gallary.exception.PhotoNotAdditableException;
import com.web.gallary.exception.RegistFailureException;
import com.web.gallary.exception.UpdateFailureException;
import com.web.gallary.model.PhotoModel;
import com.web.gallary.model.PhotoTagModel;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
@Sql("/sql/common/ResetAccountNoSeq.sql")
public class PhotoRestControllerIntegrationTest {
	@Autowired
	private PhotoRestController photoRestController;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/controller/PhotoRestControllerIntegrationTest.sql")
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
					.directionKbnCode("vertical")
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
					.directionKbnCode("horizontal")
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
					.directionKbnCode("horizontal")
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
					.directionKbnCode("horizontal")
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
					.directionKbnCode("horizontal")
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
					.directionKbnCode("horizontal")
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
					.directionKbnCode("horizontal")
					.photoTagModelList(new ArrayList<PhotoTagModel>())
					.build());
			
			return photoList;
		}
		
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
	@Sql("/sql/controller/PhotoRestControllerIntegrationTest.sql")
	class savePhoto {
		@Test
		@Order(1)
		@DisplayName("正常系：写真タグなし、撮影日時なし。Nullパラメータあり")
		void savePhoto_addPhoto_not_photoTag_and_photoAt() throws FileDuplicateException, RegistFailureException, UpdateFailureException, ForbiddenAccountException, BadRequestException, PhotoNotAdditableException {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：写真タグあり、撮影日時あり。Nullパラメータなし")
		void savePhoto_addPhoto_with_photoTag_and_photoAt() throws FileDuplicateException, RegistFailureException, UpdateFailureException, ForbiddenAccountException, BadRequestException, PhotoNotAdditableException {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：アクセス不正。ForbiddenAccountExceptionをthrowする")
		void savePhoto_ForbiddenAccountException() throws FileDuplicateException, RegistFailureException, UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(4)
		@DisplayName("異常系：登録上限に達している。PhotoNotAdditableExceptionをthrowする")
		void savePhoto_PhotoNotAdditableException() throws FileDuplicateException, RegistFailureException, UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(5)
		@DisplayName("異常系：画像ファイル、ファイルパスともにnull。BadRequestExceptionをthrowする")
		void savePhoto_BadRequestException_file_and_filepath_is_null() throws FileDuplicateException, RegistFailureException, UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(6)
		@DisplayName("異常系：画像ファイルがnull、ファイルパスがblank。BadRequestExceptionをthrowする")
		void savePhoto_BadRequestException_file_and_filepath_is_blank() throws FileDuplicateException, RegistFailureException, UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(7)
		@DisplayName("異常系：画像ファイル、ファイルパス以外のパラメータ不正。BadRequestExceptionをthrowする")
		void savePhoto_BadRequestException_others() throws FileDuplicateException, RegistFailureException, UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(8)
		@DisplayName("異常系：FileDuplicateExceptionをthrowする")
		void savePhoto_FileDuplicateException() throws UpdateFailureException, FileDuplicateException, RegistFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(9)
		@DisplayName("異常系：RegistFailureExceptionをthrowする")
		void savePhoto_RegistFailureException() throws FileDuplicateException, RegistFailureException, UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(10)
		@DisplayName("異常系：UpdateFailureExceptionをthrowする")
		void savePhoto_UpdateFailureException() throws FileDuplicateException, RegistFailureException, UpdateFailureException {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(3)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/controller/PhotoRestControllerIntegrationTest.sql")
	class deletePhoto {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void deletePhoto_success() throws UpdateFailureException, BadRequestException, ForbiddenAccountException {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("異常系：不正アクセス。ForbiddenAccountExceptionをthrowする")
		void deletePhoto_ForbiddenAccountException() throws UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：パラメータ不正。BadRequestExceptionをthrowする")
		void deletePhoto_BadRequestException() throws UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(4)
		@DisplayName("異常系：UpdateFailureExceptionをthrowする")
		void deletePhoto_UpdateFailureException() throws UpdateFailureException {
			assertTrue(false);
		}
	}
}