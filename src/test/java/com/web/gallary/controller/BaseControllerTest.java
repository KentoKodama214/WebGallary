package com.web.gallary.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.servlet.ModelAndView;

import com.web.gallary.controller.request.ErrorRequest;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class BaseControllerTest {
	@InjectMocks
	private BaseController baseController;
	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class header {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void header_success() {
			assertEquals("header", baseController.header());
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
			assertEquals("footer", baseController.footer());
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
			assertEquals("error", baseController.error());
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
			ErrorRequest errorRequest = ErrorRequest.builder()
					.errorCode("400")
					.httpStatus(400)
					.build();
			
			ModelAndView actual = baseController.error_page(errorRequest);
			Map<String, Object> models = actual.getModel();
			assertEquals("error_page", actual.getViewName());
			assertEquals("/", models.get("goBackPageUrl").toString());
			assertEquals("エラーが発生しました。システム管理者に問い合わせてください。", models.get("errorMessage").toString());
			assertEquals("400", models.get("errorCode").toString());
			assertEquals(400, (Integer) models.get("httpStatus"));
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：Nullのパラメータを含まないErrorRequest")
		void error_not_contain_null_parameter() {
			ErrorRequest errorRequest = ErrorRequest.builder()
					.errorCode("400")
					.httpStatus(400)
					.goBackPageUrl("/login")
					.errorMessage("パラメータが不正です。")
					.build();
			
			ModelAndView actual = baseController.error_page(errorRequest);
			Map<String, Object> models = actual.getModel();
			assertEquals("error_page", actual.getViewName());
			assertEquals("/login", models.get("goBackPageUrl").toString());
			assertEquals("パラメータが不正です。", models.get("errorMessage").toString());
			assertEquals("400", models.get("errorCode").toString());
			assertEquals(400, (Integer) models.get("httpStatus"));
		}
	}
}