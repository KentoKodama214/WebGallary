package com.web.gallary.repository.impl.integration;

import static org.junit.jupiter.api.Assertions.*;

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

import com.web.gallary.exception.PhotoNotFoundException;
import com.web.gallary.repository.impl.PhotoDetailRepositoryImpl;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
@Sql("/sql/common/ResetAccountNoSeq.sql")
public class PhotoDetailRepositoryImplIntegrationTest {
	@Autowired
	private PhotoDetailRepositoryImpl photoDetailRepositoryImpl;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/repository/PhotoDetailRepositoryImplIntegrationTest.sql")
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
	@Sql("/sql/repository/PhotoDetailRepositoryImplIntegrationTest.sql")
	class getPhotoDetail {
		@Test
		@Order(1)
		@DisplayName("正常系：写真のメタデータがデフォルト値、写真タグが0件の場合")
		void getPhotoDetail_photoTag_default_value_not_found() throws PhotoNotFoundException {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：写真のメタデータがデフォルト値でない場、写真タグが1件以上の場合")
		void getPhotoDetail_not_default_value_photoTag_found() throws PhotoNotFoundException {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：PhotoNotFoundExceptionをthrowする")
		void getPhotoDetail_PhotoNotFoundException() {
			assertTrue(false);
		}
	}
}