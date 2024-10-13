package com.web.gallary.service.impl.integration;

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

import com.web.gallary.service.impl.KbnMstServiceImpl;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
public class KbnMstServiceImplIntegrationTest {
	@Autowired
	private KbnMstServiceImpl kbnMstServiceImpl;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/service/KbnMstServiceImplIntegrationTest.sql")
	class getPrefectureList {
		@Test
		@Order(1)
		@DisplayName("正常系：区分マスタが存在する場合")
		void getPrefectureList_found() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：区分マスタが存在しない場合")
		void getPrefectureList_not_found() {
			assertTrue(false);
		}
	}
}