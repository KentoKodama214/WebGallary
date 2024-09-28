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
public class KbmMstMapperTest {
	@Autowired
	private KbnMstMapper kbnMstMapper;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/KbnMstMapperTest.sql")
	class select {
		@Test
		@Order(1)
		@DisplayName("正常系：区分クラスコードでのselectで1件の場合")
		void select_by_kbnClassCode() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：区分コードでのselectで1件の場合")
		void select_by_kbnCode() {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：並び順でのselectで1件の場合")
		void select_by_sortOrder() {
			assertTrue(false);
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：区分グループコードでのselectで1件の場合")
		void select_by_kbnGroupCode() {
			assertTrue(false);
		}
		
		@Test
		@Order(5)
		@DisplayName("正常系：区分クラス日本語名でのselectで1件の場合")
		void select_by_kbnClassJapaneseName() {
			assertTrue(false);
		}
		
		@Test
		@Order(6)
		@DisplayName("正常系：区分グループ日本語名でのselectで1件の場合")
		void select_by_kbnGroupJapaneseName() {
			assertTrue(false);
		}
		
		@Test
		@Order(7)
		@DisplayName("正常系：区分日本語名でのselectで1件の場合")
		void select_by_kbnJapaneseName() {
			assertTrue(false);
		}
		
		@Test
		@Order(8)
		@DisplayName("正常系：区分クラス英語名でのselectで1件の場合")
		void select_by_kbnClassEnglishName() {
			assertTrue(false);
		}
		
		@Test
		@Order(9)
		@DisplayName("正常系：区分グループ英語名でのselectで1件の場合")
		void select_by_kbnGroupEnglishName() {
			assertTrue(false);
		}
		
		@Test
		@Order(10)
		@DisplayName("正常系：区分英語名でのselectで1件の場合")
		void select_by_kbnEnglishName() {
			assertTrue(false);
		}
		
		@Test
		@Order(11)
		@DisplayName("正常系：説明でのselectで1件の場合")
		void select_by_explanation() {
			assertTrue(false);
		}
		
		@Test
		@Order(12)
		@DisplayName("正常系：selectで0件の場合")
		void select_not_found() {
			assertTrue(false);
		}
		
		@Test
		@Order(13)
		@DisplayName("正常系：selectで2件以上の場合")
		void select_kbnMsts() {
			assertTrue(false);
		}
		
		@Test
		@Order(14)
		@DisplayName("正常系：複数の条件でselectする場合")
		void select_some_conditions() {
			assertTrue(false);
		}
	}
}