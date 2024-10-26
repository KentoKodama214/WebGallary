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

import com.web.gallary.controller.AccountRestController;
import com.web.gallary.exception.BadRequestException;
import com.web.gallary.exception.RegistFailureException;
import com.web.gallary.exception.UpdateFailureException;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
@Sql("/sql/common/ResetAccountNoSeq.sql")
public class AccountRestControllerIntegrationTest {
	@Autowired
	private AccountRestController accountRestController;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Nested
	@Order(1)
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/controller/AccountRestControllerIntegrationTest.sql")
	class register {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void register_regist_success() throws RegistFailureException, BadRequestException {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：既に使われているアカウントIDの場合")
		void register_exist_accountId() throws RegistFailureException, BadRequestException {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：BadRequestExceptionをthrowする")
		void account_setting_BadRequestException_accountId_is_blank() throws RegistFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(4)
		@DisplayName("異常系：RegistFailureExceptionをthrowする")
		void account_setting_RegistFailureException() throws RegistFailureException {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(2)
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/controller/AccountRestControllerIntegrationTest.sql")
	class update {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウントID、パスワード変更なし")
		void update_not_change_accountID_and_password() throws UpdateFailureException, BadRequestException {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：アカウントID変更あり、パスワード変更なし")
		void update_change_accountID() throws UpdateFailureException, BadRequestException {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：アカウントID変更なし、パスワード変更あり")
		void update_change_password() throws UpdateFailureException, BadRequestException {
			assertTrue(false);
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：アカウントID変更あり、パスワード変更あり")
		void update_change_accountId_and_password() throws UpdateFailureException, BadRequestException {
			assertTrue(false);
		}
		
		@Test
		@Order(5)
		@DisplayName("正常系：アカウントID重複")
		void update_duplicate_accountId() throws UpdateFailureException, BadRequestException {
			assertTrue(false);
		}
		
		@Test
		@Order(6)
		@DisplayName("異常系：パスワード変更なしで、パスワード以外のパラメータが不正")
		void update_BadRequestException_account_id() throws UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(7)
		@DisplayName("異常系：パスワード変更ありで不正でなく、パスワード以外のパラメータが不正")
		void update_BadRequestException_account_id_with_change_password() throws UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(8)
		@DisplayName("異常系：パスワード変更ありで、パスワードが不正")
		void update_BadRequestException_password() throws UpdateFailureException {
			assertTrue(false);
		}
		
		@Test
		@Order(9)
		@DisplayName("異常系：UpdateFailureExceptionをthrowする")
		void update_UpdateFailureException() throws UpdateFailureException, BadRequestException {
			assertTrue(false);
		}
	}
}