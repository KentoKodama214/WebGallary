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

import com.web.gallary.repository.impl.KbnMstRepositoryImpl;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
public class KbnMstRepositoryImplIntegrationTest {
	@Autowired
	private KbnMstRepositoryImpl kbnMstRepositoryImpl;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/repository/KbnMstRepositoryImplIntegrationTest.sql")
	class get {
		@Test
		@Order(1)
		@DisplayName("正常系：区分マスタが取得できた場合")
		void get_found() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：区分マスタが取得できなかった場合")
		void get_not_found() {
			assertTrue(false);
		}
	}
}