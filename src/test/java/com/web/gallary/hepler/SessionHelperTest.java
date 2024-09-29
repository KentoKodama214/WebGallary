package com.web.gallary.hepler;

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
public class SessionHelperTest {

	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class getAccountNo {
		@Test
		@Order(1)
		@DisplayName("正常系：セッションに存在し、アカウント番号を返す")
		void getAccountNo_found() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：セッションに存在せず、nullを返す")
		void getAccountNo_not_found() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class getAccountId {
		@Test
		@Order(1)
		@DisplayName("正常系：セッションに存在し、アカウントIDを返す")
		void getAccountId_found() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：セッションに存在せず、nullを返す")
		void getAccountId_not_found() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class getPassword {
		@Test
		@Order(1)
		@DisplayName("正常系：セッションに存在し、パスワードを返す")
		void getPassword_found() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：セッションに存在せず、nullを返す")
		void getPassword_not_found() {
			assertTrue(false);
		}
	}
}