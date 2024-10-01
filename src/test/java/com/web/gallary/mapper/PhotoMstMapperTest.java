package com.web.gallary.mapper;

import static org.junit.jupiter.api.Assertions.*;

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
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：写真番号でのcountで1件の場合")
		void count_by_photoNo() {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：削除フラグでのcountで1件の場合")
		void count_by_isDeleted() {
			assertTrue(false);
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：撮影日時でのcountで1件の場合")
		void count_by_photoAt() {
			assertTrue(false);
		}
		
		@Test
		@Order(5)
		@DisplayName("正常系：ロケーション番号でのcountで1件の場合")
		void count_by_locationNo() {
			assertTrue(false);
		}
		
		@Test
		@Order(6)
		@DisplayName("正常系：画像ファイルパスでのcountで1件の場合")
		void count_by_imageFilePath() {
			assertTrue(false);
		}
		
		@Test
		@Order(7)
		@DisplayName("正常系：写真タイトル日本語でのcountで1件の場合")
		void count_by_photoJapaneseTitle() {
			assertTrue(false);
		}
		
		@Test
		@Order(8)
		@DisplayName("正常系：写真タイトル英語でのcountで1件の場合")
		void count_by_photoEnglishTitle() {
			assertTrue(false);
		}
		
		@Test
		@Order(9)
		@DisplayName("正常系：キャプションでのcountで1件の場合")
		void count_by_caption() {
			assertTrue(false);
		}
		
		@Test
		@Order(10)
		@DisplayName("正常系：向き区分コードでのcountで1件の場合")
		void count_by_directionKbnCode() {
			assertTrue(false);
		}
		
		@Test
		@Order(11)
		@DisplayName("正常系：焦点距離でのcountで1件の場合")
		void count_by_focalLength() {
			assertTrue(false);
		}
		
		@Test
		@Order(12)
		@DisplayName("正常系：F値でのcountで1件の場合")
		void count_by_fValue() {
			assertTrue(false);
		}
		
		@Test
		@Order(13)
		@DisplayName("正常系：シャッタースピードでのcountで1件の場合")
		void count_by_shutterSpeed() {
			assertTrue(false);
		}
		
		@Test
		@Order(14)
		@DisplayName("正常系：ISOでのcountで1件の場合")
		void count_by_iso() {
			assertTrue(false);
		}
		
		@Test
		@Order(15)
		@DisplayName("正常系：countで0件の場合")
		void count_not_found() {
			assertTrue(false);
		}
		
		@Test
		@Order(16)
		@DisplayName("正常系：countで2件以上の場合")
		void count_photos() {
			assertTrue(false);
		}
		
		@Test
		@Order(17)
		@DisplayName("正常系：複数の条件でcountする場合")
		void count_some_conditions() {
			assertTrue(false);
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
			assertTrue(false);
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
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：写真番号でのupdate")
		void update_by_photoNo() {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：削除フラグでのupdate")
		void update_by_isDeleted() {
			assertTrue(false);
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：撮影日時でのupdate")
		void update_by_photoAt() {
			assertTrue(false);
		}
		
		@Test
		@Order(5)
		@DisplayName("正常系：ロケーション番号でのupdate")
		void update_by_locationNo() {
			assertTrue(false);
		}
		
		@Test
		@Order(6)
		@DisplayName("正常系：画像ファイルパスでのupdate")
		void update_by_imageFilePath() {
			assertTrue(false);
		}
		
		@Test
		@Order(7)
		@DisplayName("正常系：写真タイトル日本語でのupdate")
		void update_by_photoJapaneseTitle() {
			assertTrue(false);
		}
		
		@Test
		@Order(8)
		@DisplayName("正常系：写真タイトル英語でのupdate")
		void update_by_photoEnglishTitle() {
			assertTrue(false);
		}
		
		@Test
		@Order(9)
		@DisplayName("正常系：キャプションでのcountで1件の場合")
		void count_by_caption() {
			assertTrue(false);
		}
		
		@Test
		@Order(10)
		@DisplayName("正常系：向き区分コードでのupdate")
		void update_by_directionKbnCode() {
			assertTrue(false);
		}
		
		@Test
		@Order(11)
		@DisplayName("正常系：焦点距離でのupdate")
		void update_by_focalLength() {
			assertTrue(false);
		}
		
		@Test
		@Order(12)
		@DisplayName("正常系：F値でのupdate")
		void update_by_fValue() {
			assertTrue(false);
		}
		
		@Test
		@Order(13)
		@DisplayName("正常系：シャッタースピードでのupdate")
		void update_by_shutterSpeed() {
			assertTrue(false);
		}
		
		@Test
		@Order(14)
		@DisplayName("正常系：ISOでのupdate")
		void update_by_iso() {
			assertTrue(false);
		}
		
		@Test
		@Order(15)
		@DisplayName("正常系：updateで0件の場合")
		void update_not_found() {
			assertTrue(false);
		}
		
		@Test
		@Order(16)
		@DisplayName("正常系：updateで2件以上の場合")
		void update_photos() {
			assertTrue(false);
		}
		
		@Test
		@Order(17)
		@DisplayName("正常系：複数の条件でupdateする場合")
		void update_some_conditions() {
			assertTrue(false);
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
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：アカウント番号に該当する写真がない場合")
		void getMaxPhotoNo_not_found() {
			assertTrue(false);
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
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：画像ファイルパスに該当する写真がない場合")
		void isExistPhoto_not_found() {
			assertTrue(false);
		}
	}
}