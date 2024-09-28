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

@MybatisTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PhotoTagMstMapperTest {
	@Autowired
	private PhotoTagMstMapper photoTagMstMapper;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class select {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウント番号でのselectで1件の場合")
		void select_by_accountNo() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：写真番号でのselectで1件の場合")
		void select_by_photoNo() {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：タグ番号でのselectで1件の場合")
		void select_by_tagNo() {
			assertTrue(false);
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：タグ日本語名でのselectで1件の場合")
		void select_by_tagJapaneseName() {
			assertTrue(false);
		}
		
		@Test
		@Order(5)
		@DisplayName("正常系：タグ英語名でのselectで1件の場合")
		void select_by_tagEnglishName() {
			assertTrue(false);
		}
		
		@Test
		@Order(6)
		@DisplayName("正常系：selectで0件の場合")
		void select_not_found() {
			assertTrue(false);
		}
		
		@Test
		@Order(7)
		@DisplayName("正常系：selectで2件以上の場合")
		void select_photoTags() {
			assertTrue(false);
		}
		
		@Test
		@Order(8)
		@DisplayName("正常系：複数の条件でselectする場合")
		void select_some_conditions() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(2)
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
	@Order(3)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class delete {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウント番号でのdelete")
		void delete_by_accountNo() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：写真番号でのdelete")
		void delete_by_photoNo() {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：タグ番号でのdelete")
		void delete_by_tagNo() {
			assertTrue(false);
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：タグ日本語名でのdelete")
		void delete_by_tagJapaneseName() {
			assertTrue(false);
		}
		
		@Test
		@Order(5)
		@DisplayName("正常系：タグ英語名でのdelete")
		void delete_by_tagEnglishName() {
			assertTrue(false);
		}
		
		@Test
		@Order(6)
		@DisplayName("正常系：deleteで0件の場合")
		void delete_not_found() {
			assertTrue(false);
		}
		
		@Test
		@Order(7)
		@DisplayName("正常系：deleteで2件以上の場合")
		void delete_photoTags() {
			assertTrue(false);
		}
		
		@Test
		@Order(8)
		@DisplayName("正常系：複数の条件でdeleteする場合")
		void delete_some_conditions() {
			assertTrue(false);
		}
	}
}