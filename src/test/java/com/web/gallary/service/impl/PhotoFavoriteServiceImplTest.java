package com.web.gallary.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class PhotoFavoriteServiceImplTest {

	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class addFavorite {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void addFavorite_success() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("異常系：RegistFailureExceptionをthrowする")
		void addFavorite_RegistFailureException() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class deleteFavorite {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void deleteFavorite_success() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("異常系：UpdateFailureExceptionをthrowする")
		void deleteFavorite_UpdateFailureException() {
			assertTrue(false);
		}
	}
}