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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@MybatisTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PhotoDetailMapperTest {
	@Autowired
	private PhotoDetailMapper photoDetailMapper;
	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/mapper/PhotoDetailMapperTest.sql")
	class getPhotoList {
		@Test
		@Order(1)
		@DisplayName("正常系：selectで1件以上の場合")
		void getPhotoList_some_photos() {
			assertTrue(false);
		}
		@Test
		@Order(2)
		@DisplayName("正常系：selectで0件の場合")
		void getPhotoList_not_found() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/mapper/PhotoDetailMapperTest.sql")
	class getPhotoDetail {
		@Test
		@Order(1)
		@DisplayName("正常系：selectで1件の場合")
		void getPhotoDetail_found() {
			assertTrue(false);
		}
		@Test
		@Order(2)
		@DisplayName("正常系：selectで0件の場合")
		void getPhotoDetail_not_found() {
			assertTrue(false);
		}
	}
}