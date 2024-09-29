package com.web.gallary.repository.impl;

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
public class PhotoDetailRepositoryImplTest {
	
	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class getPhotoList {
		@Test
		@Order(1)
		@DisplayName("正常系：写真が0件の場合")
		void getPhotoList_photo_not_found() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：写真が1件以上、写真タグが0件の場合")
		void getPhotoList_photoTag_not_found() {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：写真が1件以上、写真タグが1件以上の場合")
		void getPhotoList_photoTag_found() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class getPhotoDetail {
		@Test
		@Order(1)
		@DisplayName("正常系：写真タグが0件の場合")
		void getPhotoDetail_photoTag_not_found() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：写真タグが1件以上の場合")
		void getPhotoDetail_photoTag_found() {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：写真のメタデータがデフォルト値の場合")
		void getPhotoDetail_default_value() {
			assertTrue(false);
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：写真のメタデータがデフォルト値でない場合")
		void getPhotoDetail_not_default_value() {
			assertTrue(false);
		}
		
		@Test
		@Order(5)
		@DisplayName("異常系：PhotoNotFoundExceptionをthrowする")
		void getPhotoDetail_PhotoNotFoundException() {
			assertTrue(false);
		}
	}
}