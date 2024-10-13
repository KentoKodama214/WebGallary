package com.web.gallary.controller.integration;

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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.web.gallary.controller.BaseController;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
public class BaseControllerIntegrationTest {
	@Autowired
	private BaseController baseController;
	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class header {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void header_success() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class footer {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void footer_success() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(3)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class error {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void error_success() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(4)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class error_page {
		@Test
		@Order(1)
		@DisplayName("正常系：Nullのパラメータを含むErrorRequest")
		void error_contain_null_parameter() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：Nullのパラメータを含まないErrorRequest")
		void error_not_contain_null_parameter() {
			assertTrue(false);
		}
	}
}