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
public class PhotoFavoriteMapperTest {
	@Autowired
	private PhotoFavoriteMapper photoFavoriteMapper;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class insert {
		@Test
		@Order(1)
		@DisplayName("正常系：登録成功")
		void insert_success() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/PhotoFavoriteMapperTest.sql")
	class delete {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウント番号でのdelete")
		void delete_by_accountNo() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：お気に入り写真アカウント番号でのdelete")
		void delete_by_favoritePhotoAccountNo() {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：お気に入り写真番号でのdelete")
		void delete_by_favoritePhotoNo() {
			assertTrue(false);
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：削除対象のレコードなし")
		void delete_not_found() {
			assertTrue(false);
		}
		
		@Test
		@Order(5)
		@DisplayName("正常系：deleteで2件以上の場合")
		void delete_PhotoFavorites() {
			assertTrue(false);
		}
		
		@Test
		@Order(6)
		@DisplayName("正常系：複数の条件でdeleteする場合")
		void delete_some_conditions() {
			assertTrue(false);
		}
	}
}