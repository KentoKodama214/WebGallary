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
import com.web.gallary.repository.impl.PhotoMstRepositoryImpl;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
@Sql("/sql/common/ResetAccountNoSeq.sql")
public class PhotoMstRepositoryImplIntegrationTest {
	@Autowired
	private PhotoMstRepositoryImpl photoMstRepositoryImpl;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/repository/PhotoMstRepositoryImplIntegrationTest.sql")
	class regist {
		@Test
		@Order(1)
		@DisplayName("正常系：Nullのパラメータを含むPhotoDetailModelの登録")
		void regist_contain_null_parameter() throws RegistFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：Nullのパラメータを含まないPhotoDetailModelの登録")
		void regist_not_contain_null_parameter() throws RegistFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：RegistFailureExceptionをthrowする")
		void regist_RegistFailureException() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/repository/PhotoMstRepositoryImplIntegrationTest.sql")
	class update {
		@Test
		@Order(1)
		@DisplayName("正常系：Nullのパラメータを含むPhotoDetailModelでの更新")
		void update_contain_null_parameter() throws UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：Nullのパラメータを含まないPhotoDetailModelでの更新")
		void update_not_contain_null_parameter() throws UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：UpdateFailureExceptionをthrowする")
		void update_UpdateFailureException() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(3)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/repository/PhotoMstRepositoryImplIntegrationTest.sql")
	class delete {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void delete_success() throws UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("異常系：UpdateFailureExceptionをthrowする")
		void delete_UpdateFailureException() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(4)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/repository/PhotoMstRepositoryImplIntegrationTest.sql")
	class getNewPhotoNo {
		@Test
		@Order(1)
		@DisplayName("正常系：getMaxPhotoNoがある場合")
		void getNewPhotoNo_getMaxPhotoNo_found() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：getMaxPhotoNoがない場合")
		void getNewPhotoNo_getMaxPhotoNo_not_found() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(5)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/repository/PhotoMstRepositoryImplIntegrationTest.sql")
	class isExistPhoto {
		@Test
		@Order(1)
		@DisplayName("正常系：画像ファイルパスに該当する写真がない場合")
		void isExistPhoto_found() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：画像ファイルパスに該当する写真がない場合")
		void isExistPhoto_not_found() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(6)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/repository/PhotoMstRepositoryImplIntegrationTest.sql")
	class count {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void count_success() {
			assertTrue(false);
		}
	}
}