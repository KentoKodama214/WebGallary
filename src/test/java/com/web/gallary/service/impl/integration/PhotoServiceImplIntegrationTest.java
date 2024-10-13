package com.web.gallary.service.impl.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.web.gallary.exception.FileDuplicateException;
import com.web.gallary.exception.PhotoNotFoundException;
import com.web.gallary.exception.RegistFailureException;
import com.web.gallary.exception.UpdateFailureException;
import com.web.gallary.model.PhotoDetailModel;
import com.web.gallary.model.PhotoModel;
import com.web.gallary.model.PhotoTagModel;
import com.web.gallary.service.impl.PhotoServiceImpl;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
@Sql("/sql/common/ResetAccountNoSeq.sql")
public class PhotoServiceImplIntegrationTest {
	@Autowired
	private PhotoServiceImpl photoServiceImpl;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/service/PhotoServiceImplIntegrationTest.sql")
	class getPhotoList {
		List<PhotoModel> createPhotoModelList() {
			String directionKbnCode = "vertical";
			List<PhotoModel> photoModelList = new ArrayList<PhotoModel>();
			
			List<PhotoTagModel> photoTagModelList1 = new ArrayList<PhotoTagModel>();
			photoTagModelList1.add(PhotoTagModel.builder()
					.accountNo(1)
					.photoNo(1)
					.tagNo(1)
					.tagJapaneseName("太陽")
					.tagEnglishName("sun")
					.build());
			photoTagModelList1.add(PhotoTagModel.builder()
					.accountNo(1)
					.photoNo(1)
					.tagNo(2)
					.tagJapaneseName("海")
					.tagEnglishName("sea")
					.build());
			PhotoModel photoModel1 = PhotoModel.builder()
					.accountNo(1)
					.photoNo(1)
					.favoriteCount(1)
					.isFavorite(false)
					.photoAt(OffsetDateTime.of(2000, 12, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.imageFilePath("DSC111.jpg")
					.caption("キャプション1")
					.directionKbnCode(directionKbnCode)
					.photoTagModelList(photoTagModelList1)
					.build();
			photoModelList.add(photoModel1);
			
			List<PhotoTagModel> photoTagModelList2 = new ArrayList<PhotoTagModel>();
			photoTagModelList2.add(PhotoTagModel.builder()
					.accountNo(1)
					.photoNo(2)
					.tagNo(1)
					.tagJapaneseName("太陽")
					.tagEnglishName("sun")
					.build());
			photoTagModelList2.add(PhotoTagModel.builder()
					.accountNo(1)
					.photoNo(2)
					.tagNo(2)
					.tagJapaneseName("海")
					.tagEnglishName("sea")
					.build());
			PhotoModel photoModel2 = PhotoModel.builder()
					.accountNo(1)
					.photoNo(2)
					.favoriteCount(3)
					.isFavorite(false)
					.photoAt(OffsetDateTime.of(2001, 6, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.imageFilePath("DSC222.jpg")
					.caption("キャプション2")
					.directionKbnCode(directionKbnCode)
					.photoTagModelList(photoTagModelList2)
					.build();
			photoModelList.add(photoModel2);
			
			List<PhotoTagModel> photoTagModelList3 = new ArrayList<PhotoTagModel>();
			photoTagModelList3.add(PhotoTagModel.builder()
					.accountNo(1)
					.photoNo(3)
					.tagNo(1)
					.tagJapaneseName("太陽")
					.tagEnglishName("sun")
					.build());
			photoTagModelList3.add(PhotoTagModel.builder()
					.accountNo(1)
					.photoNo(3)
					.tagNo(2)
					.tagJapaneseName("海")
					.tagEnglishName("sea")
					.build());
			PhotoModel photoModel3 = PhotoModel.builder()
					.accountNo(1)
					.photoNo(3)
					.favoriteCount(2)
					.isFavorite(false)
					.photoAt(OffsetDateTime.of(2002, 3, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.imageFilePath("DSC333.jpg")
					.caption("キャプション3")
					.directionKbnCode(directionKbnCode)
					.photoTagModelList(photoTagModelList3)
					.build();
			photoModelList.add(photoModel3);
			
			List<PhotoTagModel> photoTagModelList4 = new ArrayList<PhotoTagModel>();
			photoTagModelList4.add(PhotoTagModel.builder()
					.accountNo(1)
					.photoNo(4)
					.tagNo(1)
					.tagJapaneseName("太陽")
					.tagEnglishName("sun")
					.build());
			PhotoModel photoModel4 = PhotoModel.builder()
					.accountNo(1)
					.photoNo(4)
					.favoriteCount(4)
					.isFavorite(true)
					.photoAt(OffsetDateTime.of(2001, 4, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.imageFilePath("DSC444.jpg")
					.caption("キャプション4")
					.directionKbnCode(directionKbnCode)
					.photoTagModelList(photoTagModelList4)
					.build();
			photoModelList.add(photoModel4);
			
			List<PhotoTagModel> photoTagModelList5 = new ArrayList<PhotoTagModel>();
			photoTagModelList5.add(PhotoTagModel.builder()
					.accountNo(1)
					.photoNo(5)
					.tagNo(1)
					.tagJapaneseName("海")
					.tagEnglishName("sea")
					.build());
			PhotoModel photoModel5 = PhotoModel.builder()
					.accountNo(1)
					.photoNo(5)
					.favoriteCount(10)
					.isFavorite(false)
					.photoAt(OffsetDateTime.of(2001, 5, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.imageFilePath("DSC444.jpg")
					.caption("キャプション4")
					.directionKbnCode("horizontal")
					.photoTagModelList(photoTagModelList5)
					.build();
			photoModelList.add(photoModel5);
			
			PhotoModel photoModel6 = PhotoModel.builder()
					.accountNo(1)
					.photoNo(6)
					.favoriteCount(0)
					.isFavorite(false)
					.photoAt(OffsetDateTime.of(2001, 6, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.imageFilePath("DSC666.jpg")
					.caption("キャプション6")
					.directionKbnCode(directionKbnCode)
					.photoTagModelList(new ArrayList<PhotoTagModel>())
					.build();
			photoModelList.add(photoModel6);
			
			return photoModelList;
		}
		
		@Test
		@Order(1)
		@DisplayName("正常系：写真が存在しなかった場合")
		void getPhotoList_not_found() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：写真が存在した場合で、撮影日順に並び替え")
		void getPhotoList_sortBy_photoAt() {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：写真が存在した場合で、お気に入り数順に並び替え")
		void getPhotoList_sortBy_Favorite() {
			assertTrue(false);
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：写真が存在した場合で、季節・時期順に並び替え")
		void getPhotoList_sortBy_season() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/service/PhotoServiceImplIntegrationTest.sql")
	class getPhotoDetail {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void getPhotoDetail_success() throws PhotoNotFoundException {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("異常系：PhotoNotFoundExceptionをthrowする")
		void getPhotoDetail_PhotoNotFoundException() throws PhotoNotFoundException {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(3)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/service/PhotoServiceImplIntegrationTest.sql")
	class savePhotos {
		PhotoDetailModel createNewPhotoWithTag() {
			List<PhotoTagModel> photoTagModelList = new ArrayList<PhotoTagModel>();
			photoTagModelList.add(PhotoTagModel.builder()
					.accountNo(1)
					.photoNo(5)
					.tagNo(1)
					.tagJapaneseName("太陽")
					.tagEnglishName("sun")
					.build());
			photoTagModelList.add(PhotoTagModel.builder()
					.accountNo(1)
					.photoNo(5)
					.tagNo(2)
					.tagJapaneseName("海")
					.tagEnglishName("sea")
					.build());
			MultipartFile multipartFile = new MockMultipartFile(
					"file",
					"DSC111.jpg",
					"multipart/form-data",
					"sample image".getBytes()
			);
			return PhotoDetailModel.builder()
					.accountNo(1)
					.photoAt(OffsetDateTime.of(2000, 12, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.imageFile(multipartFile)
					.imageFilePath("")
					.photoJapaneseTitle("タイトル1")
					.photoEnglishTitle("title1")
					.caption("キャプション1")
					.focalLength(24)
					.fValue(BigDecimal.valueOf(2.8))
					.shutterSpeed(BigDecimal.valueOf(0.01))
					.iso(100)
					.photoTagModelList(photoTagModelList)
					.build();
		}
		
		PhotoDetailModel createNewPhoto() {
			MultipartFile multipartFile = new MockMultipartFile(
					"file",
					"DSC222.jpg",
					"multipart/form-data",
					"sample image".getBytes()
				);
			return PhotoDetailModel.builder()
					.accountNo(1)
					.imageFile(multipartFile)
					.imageFilePath("")
					.build();
		}
		
		PhotoDetailModel createUpdatePhotoWithTag() {
			List<PhotoTagModel> photoTagModelList = new ArrayList<PhotoTagModel>();
			photoTagModelList.add(PhotoTagModel.builder()
					.accountNo(1)
					.photoNo(2)
					.tagNo(1)
					.tagJapaneseName("太陽")
					.tagEnglishName("sun")
					.build());
			photoTagModelList.add(PhotoTagModel.builder()
					.accountNo(1)
					.photoNo(2)
					.tagNo(2)
					.tagJapaneseName("海")
					.tagEnglishName("sea")
					.build());
			MultipartFile multipartFile = new MockMultipartFile(
					"file",
					"DSC222.jpg",
					"multipart/form-data",
					"sample image".getBytes()
			);
			return PhotoDetailModel.builder()
					.accountNo(1)
					.photoNo(2)
					.photoAt(OffsetDateTime.of(2000, 12, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.imageFile(multipartFile)
					.imageFilePath("https://localhost:8080/image/DSC222.jpg")
					.photoJapaneseTitle("タイトル2")
					.photoEnglishTitle("title2")
					.caption("キャプション2")
					.focalLength(24)
					.fValue(BigDecimal.valueOf(2.8))
					.shutterSpeed(BigDecimal.valueOf(0.01))
					.iso(100)
					.photoTagModelList(photoTagModelList)
					.build();
		}
		
		PhotoDetailModel createUpdatePhoto() {
			MultipartFile multipartFile = new MockMultipartFile(
					"file",
					"DSC333.jpg",
					"multipart/form-data",
					"sample image".getBytes()
			);
			return PhotoDetailModel.builder()
					.accountNo(1)
					.photoNo(3)
					.photoAt(OffsetDateTime.of(2000, 12, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.imageFile(multipartFile)
					.imageFilePath("https://localhost:8080/image/DSC333.jpg")
					.photoJapaneseTitle("タイトル3")
					.photoEnglishTitle("title3")
					.caption("キャプション3")
					.focalLength(24)
					.fValue(BigDecimal.valueOf(2.8))
					.shutterSpeed(BigDecimal.valueOf(0.01))
					.iso(100)
					.build();
		}
		
		@Test
		@Order(1)
		@DisplayName("正常系：photoDetailModelListがnullの場合、終了")
		void savePhotos_photoDetailModelList_is_null() throws FileDuplicateException, RegistFailureException, UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：photoDetailModelListがemptyの場合、終了")
		void savePhotos_photoDetailModelList_is_empty() throws FileDuplicateException, RegistFailureException, UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：新規登録のみ")
		void savePhotos_newPhoto() throws FileDuplicateException, RegistFailureException, UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：更新のみ")
		void savePhotos_updatePhoto() throws FileDuplicateException, RegistFailureException, UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(5)
		@DisplayName("正常系：新規登録＋更新")
		void savePhotos_newPhoto_and_updatePhoto() throws FileDuplicateException, RegistFailureException, UpdateFailureException  {
			assertTrue(false);
		}
		
		@Test
		@Order(6)
		@DisplayName("異常系：FileDuplicateExceptionをthrowする（写真は複数枚）")
		void savePhotos_FileDuplicateException() throws FileDuplicateException, RegistFailureException, UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(7)
		@DisplayName("異常系：写真登録でRegistFailureExceptionをthrowする（写真は複数枚）")
		void savePhotos_registPhoto_RegistFailureException() throws FileDuplicateException, RegistFailureException, UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(8)
		@DisplayName("異常系：新規登録時、写真タグ登録でRegistFailureExceptionをthrowする（写真は複数枚）")
		void savePhotos_newPhoto_registPhotoTag_RegistFailureException() throws FileDuplicateException, RegistFailureException, UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(9)
		@DisplayName("異常系：更新時、写真タグ登録でRegistFailureExceptionをthrowする（写真は複数枚）")
		void savePhotos_updatePhoto_registPhotoTag_RegistFailureException() throws FileDuplicateException, RegistFailureException, UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(10)
		@DisplayName("異常系：UpdateFailureExceptionをthrowする（写真は複数枚）")
		void savePhotos_UpdateFailureException() throws FileDuplicateException, RegistFailureException, UpdateFailureException {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(4)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/service/PhotoServiceImplIntegrationTest.sql")
	class deletePhotos {
		@Test
		@Order(1)
		@DisplayName("正常系：photoDeleteModelListが0件の場合、終了")
		void deletePhotos_photoDeleteModelList_empty() throws UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：photoDetailModelListが2件以上の場合")
		void deletePhotos_success() throws UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：UpdateFailureExceptionをthrowする")
		void deletePhotos_UpdateFailureException() throws UpdateFailureException {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(5)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/service/PhotoServiceImplIntegrationTest.sql")
	class isReachedUpperLimit {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウント番号がnullの場合")
		void isReachedUpperLimit_accountNo_is_null() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：mini-userで、10枚登録済みの場合")
		void isReachedUpperLimit_mini_user_10_photos() {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：mini-userで、9枚登録済みの場合")
		void isReachedUpperLimit_mini_user_9_photos() {
			assertTrue(false);
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：normal-userで、1000枚登録済みの場合")
		void isReachedUpperLimit_normal_user_1000_photos() {
			assertTrue(false);
		}
		
		@Test
		@Order(5)
		@DisplayName("正常系：normal-userで、999枚登録済みの場合")
		void isReachedUpperLimit_normal_user_999_photos() {
			assertTrue(false);
		}
		
		@Test
		@Order(6)
		@DisplayName("正常系：special-userの場合")
		void isReachedUpperLimit_special_user() {
			assertTrue(false);
		}
		
		@Test
		@Order(7)
		@DisplayName("正常系：administratorの場合")
		void isReachedUpperLimit_administrator() {
			assertTrue(false);
		}
		
		@Test
		@Order(8)
		@DisplayName("正常系：それ以外の場合")
		void isReachedUpperLimit_others() {
			assertTrue(false);
		}
	}
}