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

import com.web.gallary.exception.RegistFailureException;
import com.web.gallary.exception.UpdateFailureException;
import com.web.gallary.repository.impl.PhotoFavoriteRepositoryImpl;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
@Sql("/sql/common/ResetAccountNoSeq.sql")
public class PhotoFavoriteRepositoryImplIntegrationTest {
	@Autowired
	private PhotoFavoriteRepositoryImpl photoFavoriteRepositoryImpl;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/repository/PhotoFavoriteRepositoryImplIntegrationTest.sql")
	class regist {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void regist_contain_null_parameter() throws RegistFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("異常系：RegistFailureExceptionをthrowする")
		void regist_RegistFailureException() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/repository/PhotoFavoriteRepositoryImplIntegrationTest.sql")
	class delete {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void delete_contain_null_parameter() throws UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("異常系：UpdateFailureExceptionをthrowする")
		void delete_UpdateFailureException() throws UpdateFailureException {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(3)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/repository/PhotoFavoriteRepositoryImplIntegrationTest.sql")
	class clear {
		@Test
		@Order(1)
		@DisplayName("正常系：")
		void clear_success() {
			assertTrue(false);
		}
	}
}