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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.web.gallary.controller.PhotoFavoriteController;
import com.web.gallary.exception.BadRequestException;
import com.web.gallary.exception.RegistFailureException;
import com.web.gallary.exception.UpdateFailureException;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
@Sql("/sql/common/ResetAccountNoSeq.sql")
public class PhotoFavoriteControllerIntegrationTest {
	@Autowired
	private PhotoFavoriteController photoFavoriteController;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/controller/PhotoFavoriteControllerIntegrationTest.sql")
	class addFavorite {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void addFavorite_success() throws BadRequestException, RegistFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("異常系：BadRequestExceptionをthrowする")
		void addFavorite_BadRequestException() throws BadRequestException, RegistFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：RegistFailureExceptionをthrowする")
		void addFavorite_RegistFailureException() throws BadRequestException, RegistFailureException {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/controller/PhotoFavoriteControllerIntegrationTest.sql")
	class deleteFavorite {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void deleteFavorite_success() throws BadRequestException, UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("異常系：BadRequestExceptionをthrowする")
		void deleteFavorite_BadRequestException() throws BadRequestException, UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：UpdateFailureExceptionをthrowする")
		void deleteFavorite_UpdateFailureException() throws BadRequestException, UpdateFailureException {
			assertTrue(false);
		}
	}
}