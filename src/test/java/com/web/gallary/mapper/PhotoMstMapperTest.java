package com.web.gallary.mapper;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.web.gallary.entity.PhotoMst;

@MybatisTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PhotoMstMapperTest {
	@Autowired
	private PhotoMstMapper photoMstMapper;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/mapper/PhotoMstMapperTest.sql")
	class count {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウント番号でのcountで1件の場合")
		void count_by_accountNo() {
			PhotoMst photoMst = PhotoMst.builder().accountNo(1).build();
			Integer count = photoMstMapper.count(photoMst);
			assertEquals(count, 3);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：写真番号でのcountで1件の場合")
		void count_by_photoNo() {
			PhotoMst photoMst = PhotoMst.builder().photoNo(1).build();
			Integer count = photoMstMapper.count(photoMst);
			assertEquals(count, 2);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：削除フラグでのcountで1件の場合")
		void count_by_isDeleted() {
			PhotoMst photoMst = PhotoMst.builder().isDeleted(true).build();
			Integer count = photoMstMapper.count(photoMst);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：撮影日時でのcountで1件の場合")
		void count_by_photoAt() {
			PhotoMst photoMst = PhotoMst.builder()
					.photoAt(OffsetDateTime.of(2021, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0))).build();
			Integer count = photoMstMapper.count(photoMst);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(5)
		@DisplayName("正常系：ロケーション番号でのcountで1件の場合")
		void count_by_locationNo() {
			PhotoMst photoMst = PhotoMst.builder().locationNo(1).build();
			Integer count = photoMstMapper.count(photoMst);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(6)
		@DisplayName("正常系：画像ファイルパスでのcountで1件の場合")
		void count_by_imageFilePath() {
			PhotoMst photoMst = PhotoMst.builder().imageFilePath("https://www.xxx.com/DSC111.jpg").build();
			Integer count = photoMstMapper.count(photoMst);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(7)
		@DisplayName("正常系：写真タイトル日本語でのcountで1件の場合")
		void count_by_photoJapaneseTitle() {
			PhotoMst photoMst = PhotoMst.builder().photoJapaneseTitle("タイトル11").build();
			Integer count = photoMstMapper.count(photoMst);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(8)
		@DisplayName("正常系：写真タイトル英語でのcountで1件の場合")
		void count_by_photoEnglishTitle() {
			PhotoMst photoMst = PhotoMst.builder().photoEnglishTitle("title11").build();
			Integer count = photoMstMapper.count(photoMst);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(9)
		@DisplayName("正常系：キャプションでのcountで1件の場合")
		void count_by_caption() {
			PhotoMst photoMst = PhotoMst.builder().caption("キャプション11").build();
			Integer count = photoMstMapper.count(photoMst);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(10)
		@DisplayName("正常系：向き区分コードでのcountで1件の場合")
		void count_by_directionKbnCode() {
			PhotoMst photoMst = PhotoMst.builder().directionKbnCode("vertical").build();
			Integer count = photoMstMapper.count(photoMst);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(11)
		@DisplayName("正常系：焦点距離でのcountで1件の場合")
		void count_by_focalLength() {
			PhotoMst photoMst = PhotoMst.builder().focalLength(24).build();
			Integer count = photoMstMapper.count(photoMst);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(12)
		@DisplayName("正常系：F値でのcountで1件の場合")
		void count_by_fValue() {
			PhotoMst photoMst = PhotoMst.builder().fValue(BigDecimal.valueOf(8.0)).build();
			Integer count = photoMstMapper.count(photoMst);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(13)
		@DisplayName("正常系：シャッタースピードでのcountで1件の場合")
		void count_by_shutterSpeed() {
			PhotoMst photoMst = PhotoMst.builder().shutterSpeed(BigDecimal.valueOf(1)).build();
			Integer count = photoMstMapper.count(photoMst);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(14)
		@DisplayName("正常系：ISOでのcountで1件の場合")
		void count_by_iso() {
			PhotoMst photoMst = PhotoMst.builder().iso(100).build();
			Integer count = photoMstMapper.count(photoMst);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(15)
		@DisplayName("正常系：countで0件の場合")
		void count_not_found() {
			PhotoMst photoMst = PhotoMst.builder().accountNo(100).build();
			Integer count = photoMstMapper.count(photoMst);
			assertEquals(count, 0);
		}
		
		@Test
		@Order(16)
		@DisplayName("正常系：複数の条件でcountする場合")
		void count_some_conditions() {
			PhotoMst photoMst = PhotoMst.builder().accountNo(1).photoNo(1).build();
			Integer count = photoMstMapper.count(photoMst);
			assertEquals(count, 1);
		}
	}
	
	@Nested
	@Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/mapper/PhotoMstMapperTest.sql")
	class insert {
		@Test
		@Order(1)
		@DisplayName("正常系：登録成功")
		void insert_success() {
			PhotoMst insertPhotoMst = PhotoMst.builder()
					.accountNo(1)
					.photoNo(4)
					.createdBy(1)
					.createdAt(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(9)))
					.updatedBy(1)
					.updatedAt(OffsetDateTime.of(2001, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(9)))
					.isDeleted(false)
					.photoAt(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(9)))
					.locationNo(6)
					.imageFilePath("https://www.xxx.com/DSC666.jpg")
					.photoJapaneseTitle("")
					.photoEnglishTitle("")
					.caption("")
					.directionKbnCode("")
					.focalLength(100)
					.fValue(BigDecimal.valueOf(2.8))
					.shutterSpeed(BigDecimal.valueOf(0.01))
					.iso(100)
					.build();
			
			Integer insertCount = photoMstMapper.insert(insertPhotoMst);
			assertThat(insertCount).isEqualTo(1);
			
			List<PhotoMst> expectedPhotoMstList = jdbcTemplate.query(
					"SELECT * FROM photo.photo_mst WHERE account_no=1 and photo_no=4", (rs, rowNum) ->
						PhotoMst.builder()
							.accountNo(rs.getInt("account_no"))
							.accountNo(rs.getInt("photo_no"))
							.createdBy(rs.getInt("created_by"))
							.createdAt(rs.getObject("created_at", OffsetDateTime.class))
							.updatedBy(rs.getInt("updated_by"))
							.updatedAt(rs.getObject("updated_at", OffsetDateTime.class))
							.isDeleted(rs.getBoolean("is_deleted"))
							.photoAt(rs.getObject("photo_at", OffsetDateTime.class))
							.locationNo(rs.getInt("location_no"))
							.imageFilePath(rs.getString("image_file_path"))
							.photoJapaneseTitle(rs.getString("photo_japanese_title"))
							.photoEnglishTitle(rs.getString("photo_english_title"))
							.caption(rs.getString("caption"))
							.directionKbnCode(rs.getString("direction_kbn_code"))
							.focalLength(rs.getInt("focal_length"))
							.fValue(rs.getBigDecimal("f_value"))
							.shutterSpeed(rs.getBigDecimal("shutter_speed"))
							.iso(rs.getInt("iso"))
							.build());
			
			assertEquals(1, expectedPhotoMstList.size());
		}
	}
	
	@Nested
	@Order(3)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/mapper/PhotoMstMapperTest.sql")
	class update {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウント番号でのupdate")
		void update_by_accountNo() {
			PhotoMst conditionPhotoMst = PhotoMst.builder().accountNo(1).build();
			PhotoMst targetPhotoMst = PhotoMst.builder().iso(1000).build();
			Integer count = photoMstMapper.update(conditionPhotoMst, targetPhotoMst);
			assertEquals(count, 3);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：写真番号でのupdate")
		void update_by_photoNo() {
			PhotoMst conditionPhotoMst = PhotoMst.builder().photoNo(1).build();
			PhotoMst targetPhotoMst = PhotoMst.builder().iso(1000).build();
			Integer count = photoMstMapper.update(conditionPhotoMst, targetPhotoMst);
			assertEquals(count, 2);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：削除フラグでのupdate")
		void update_by_isDeleted() {
			PhotoMst conditionPhotoMst = PhotoMst.builder().isDeleted(true).build();
			PhotoMst targetPhotoMst = PhotoMst.builder().iso(1000).build();
			Integer count = photoMstMapper.update(conditionPhotoMst, targetPhotoMst);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：撮影日時でのupdate")
		void update_by_photoAt() {
			PhotoMst conditionPhotoMst = PhotoMst.builder()
					.photoAt(OffsetDateTime.of(2021, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0))).build();
			PhotoMst targetPhotoMst = PhotoMst.builder().iso(1000).build();
			Integer count = photoMstMapper.update(conditionPhotoMst, targetPhotoMst);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(5)
		@DisplayName("正常系：ロケーション番号でのupdate")
		void update_by_locationNo() {
			PhotoMst conditionPhotoMst = PhotoMst.builder().locationNo(1).build();
			PhotoMst targetPhotoMst = PhotoMst.builder().iso(1000).build();
			Integer count = photoMstMapper.update(conditionPhotoMst, targetPhotoMst);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(6)
		@DisplayName("正常系：画像ファイルパスでのupdate")
		void update_by_imageFilePath() {
			PhotoMst conditionPhotoMst = PhotoMst.builder().imageFilePath("https://www.xxx.com/DSC111.jpg").build();
			PhotoMst targetPhotoMst = PhotoMst.builder().iso(1000).build();
			Integer count = photoMstMapper.update(conditionPhotoMst, targetPhotoMst);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(7)
		@DisplayName("正常系：写真タイトル日本語でのupdate")
		void update_by_photoJapaneseTitle() {
			PhotoMst conditionPhotoMst = PhotoMst.builder().photoJapaneseTitle("タイトル11").build();
			PhotoMst targetPhotoMst = PhotoMst.builder().iso(1000).build();
			Integer count = photoMstMapper.update(conditionPhotoMst, targetPhotoMst);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(8)
		@DisplayName("正常系：写真タイトル英語でのupdate")
		void update_by_photoEnglishTitle() {
			PhotoMst conditionPhotoMst = PhotoMst.builder().photoEnglishTitle("title11").build();
			PhotoMst targetPhotoMst = PhotoMst.builder().iso(1000).build();
			Integer count = photoMstMapper.update(conditionPhotoMst, targetPhotoMst);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(9)
		@DisplayName("正常系：キャプションでのcountで1件の場合")
		void update_by_caption() {
			PhotoMst conditionPhotoMst = PhotoMst.builder().caption("キャプション11").build();
			PhotoMst targetPhotoMst = PhotoMst.builder().iso(1000).build();
			Integer count = photoMstMapper.update(conditionPhotoMst, targetPhotoMst);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(10)
		@DisplayName("正常系：向き区分コードでのupdate")
		void update_by_directionKbnCode() {
			PhotoMst conditionPhotoMst = PhotoMst.builder().directionKbnCode("vertical").build();
			PhotoMst targetPhotoMst = PhotoMst.builder().iso(1000).build();
			Integer count = photoMstMapper.update(conditionPhotoMst, targetPhotoMst);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(11)
		@DisplayName("正常系：焦点距離でのupdate")
		void update_by_focalLength() {
			PhotoMst conditionPhotoMst = PhotoMst.builder().focalLength(24).build();
			PhotoMst targetPhotoMst = PhotoMst.builder().iso(1000).build();
			Integer count = photoMstMapper.update(conditionPhotoMst, targetPhotoMst);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(12)
		@DisplayName("正常系：F値でのupdate")
		void update_by_fValue() {
			PhotoMst conditionPhotoMst = PhotoMst.builder().fValue(BigDecimal.valueOf(8.0)).build();
			PhotoMst targetPhotoMst = PhotoMst.builder().iso(1000).build();
			Integer count = photoMstMapper.update(conditionPhotoMst, targetPhotoMst);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(13)
		@DisplayName("正常系：シャッタースピードでのupdate")
		void update_by_shutterSpeed() {
			PhotoMst conditionPhotoMst = PhotoMst.builder().shutterSpeed(BigDecimal.valueOf(1)).build();
			PhotoMst targetPhotoMst = PhotoMst.builder().iso(1000).build();
			Integer count = photoMstMapper.update(conditionPhotoMst, targetPhotoMst);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(14)
		@DisplayName("正常系：ISOでのupdate")
		void update_by_iso() {
			PhotoMst conditionPhotoMst = PhotoMst.builder().iso(100).build();
			PhotoMst targetPhotoMst = PhotoMst.builder().iso(1000).build();
			Integer count = photoMstMapper.update(conditionPhotoMst, targetPhotoMst);
			assertEquals(count, 1);
		}
		
		@Test
		@Order(15)
		@DisplayName("正常系：updateで0件の場合")
		void update_not_found() {
			PhotoMst conditionPhotoMst = PhotoMst.builder().accountNo(100).build();
			PhotoMst targetPhotoMst = PhotoMst.builder().iso(1000).build();
			Integer count = photoMstMapper.update(conditionPhotoMst, targetPhotoMst);
			assertEquals(count, 0);
		}
		
		@Test
		@Order(16)
		@DisplayName("正常系：複数の条件でupdateする場合")
		void update_some_conditions() {
			PhotoMst conditionPhotoMst = PhotoMst.builder().accountNo(1).photoNo(1).build();
			PhotoMst targetPhotoMst = PhotoMst.builder().iso(1000).build();
			Integer count = photoMstMapper.update(conditionPhotoMst, targetPhotoMst);
			assertEquals(count, 1);
		}
	}
	
	@Nested
	@Order(4)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/mapper/PhotoMstMapperTest.sql")
	class getMaxPhotoNo {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウント番号に該当する写真がある場合")
		void getMaxPhotoNo_found() {
			Integer maxPhotoNo = photoMstMapper.getMaxPhotoNo(1);
			assertEquals(maxPhotoNo,3);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：アカウント番号に該当する写真がない場合")
		void getMaxPhotoNo_not_found() {
			Integer maxPhotoNo = photoMstMapper.getMaxPhotoNo(3);
			assertNull(maxPhotoNo);
		}
	}
	
	@Nested
	@Order(5)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/mapper/PhotoMstMapperTest.sql")
	class isExistPhoto {
		@Test
		@Order(1)
		@DisplayName("正常系：画像ファイルパスに該当する写真がない場合")
		void isExistPhoto_found() {
			assertTrue(photoMstMapper.isExistPhoto("https://www.xxx.com/DSC111.jpg"));
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：画像ファイルパスに該当する写真がない場合")
		void isExistPhoto_not_found() {
			assertFalse(photoMstMapper.isExistPhoto("https://www.xxx.com/DSC999.jpg"));
		}
	}
}