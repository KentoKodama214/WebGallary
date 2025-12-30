package com.web.gallary.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.web.gallary.controller.request.AccountRegistRequest;
import com.web.gallary.controller.request.AccountUpdateRequest;
import com.web.gallary.controller.request.ErrorRequest;
import com.web.gallary.controller.response.AccountRegistResponse;
import com.web.gallary.controller.response.AccountUpdateResponse;
import com.web.gallary.enumuration.ErrorEnum;
import com.web.gallary.enumuration.SexEnum;
import com.web.gallary.exception.BadRequestException;
import com.web.gallary.exception.RegistFailureException;
import com.web.gallary.exception.UpdateFailureException;
import com.web.gallary.helper.SessionHelper;
import com.web.gallary.model.AccountModel;
import com.web.gallary.service.impl.AccountServiceImpl;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class AccountRestControllerTest {
	@InjectMocks
	private AccountRestController accountRestController;
	
	@Mock
	private AccountServiceImpl accountServiceImpl;
	
	@Mock
	private SessionHelper sessionHelper;
	
	@Nested
	@Order(1)
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class register {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void register_regist_success() throws RegistFailureException, BadRequestException {
			String accountId = "aaaaaaaa";
			String accountName = "AAAAAAAA";
			String password = "password01";
			LocalDate birthDate = LocalDate.of(2000, 1, 1);
			String birthplacePrefectureKbnCode = "Hokkaido";
			String residentPrefectureKbnCode = "Okinawa";
			String freeMemo = "フリーメモ";
			
			AccountRegistRequest accuontRegistRequest = new AccountRegistRequest();
			accuontRegistRequest.setAccountId(accountId);
			accuontRegistRequest.setAccountName(accountName);
			accuontRegistRequest.setPassword(password);
			accuontRegistRequest.setBirthdate(birthDate);
			accuontRegistRequest.setSexKbn(SexEnum.WOMAN);
			accuontRegistRequest.setBirthplacePrefectureKbnCode(birthplacePrefectureKbnCode);
			accuontRegistRequest.setResidentPrefectureKbnCode(residentPrefectureKbnCode);
			accuontRegistRequest.setFreeMemo(freeMemo);
			
			BindingResult result = new DataBinder(accuontRegistRequest).getBindingResult();
			
			ArgumentCaptor<AccountModel> accountModelCaptor = ArgumentCaptor.forClass(AccountModel.class);
			doReturn(true).when(accountServiceImpl).registAccount(accountModelCaptor.capture());
			
			ResponseEntity<AccountRegistResponse> actual
				= accountRestController.register(accuontRegistRequest, result);
			
			assertEquals(HttpStatus.OK, actual.getStatusCode());
			assertEquals(HttpStatus.OK.value(), actual.getBody().getHttpStatus());
			assertEquals(true, actual.getBody().getIsSuccess());
			assertEquals("", actual.getBody().getMessage());
			
			AccountModel accountModel = accountModelCaptor.getValue();
			assertNull(accountModel.getAccountNo());
			assertEquals(accountId, accountModel.getAccountId());
			assertEquals(accountName, accountModel.getAccountName());
			assertEquals(password, accountModel.getPassword());
			assertEquals(birthDate, accountModel.getBirthdate());
			assertEquals(SexEnum.WOMAN, accountModel.getSexKbn());
			assertEquals(birthplacePrefectureKbnCode, accountModel.getBirthplacePrefectureKbnCode());
			assertEquals(residentPrefectureKbnCode, accountModel.getResidentPrefectureKbnCode());
			assertEquals(freeMemo, accountModel.getFreeMemo());
			assertNull(accountModel.getAuthorityKbnCode());
			assertNull(accountModel.getLastLoginDatetime());
			assertEquals(0, accountModel.getLoginFailureCount());
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：既に使われているアカウントIDの場合")
		void register_exist_accountId() throws RegistFailureException, BadRequestException {
			String accountId = "aaaaaaaa";
			String accountName = "AAAAAAAA";
			String password = "password01";
			LocalDate birthDate = LocalDate.of(2000, 1, 1);
			String birthplacePrefectureKbnCode = "Hokkaido";
			String residentPrefectureKbnCode = "Okinawa";
			String freeMemo = "フリーメモ";
			
			AccountRegistRequest accuontRegistRequest = new AccountRegistRequest();
			accuontRegistRequest.setAccountId(accountId);
			accuontRegistRequest.setAccountName(accountName);
			accuontRegistRequest.setPassword(password);
			accuontRegistRequest.setBirthdate(birthDate);
			accuontRegistRequest.setSexKbn(SexEnum.WOMAN);
			accuontRegistRequest.setBirthplacePrefectureKbnCode(birthplacePrefectureKbnCode);
			accuontRegistRequest.setResidentPrefectureKbnCode(residentPrefectureKbnCode);
			accuontRegistRequest.setFreeMemo(freeMemo);
			
			BindingResult result = new DataBinder(accuontRegistRequest).getBindingResult();
			
			ArgumentCaptor<AccountModel> accountModelCaptor = ArgumentCaptor.forClass(AccountModel.class);
			doReturn(false).when(accountServiceImpl).registAccount(accountModelCaptor.capture());
			
			ResponseEntity<AccountRegistResponse> actual
				= accountRestController.register(accuontRegistRequest, result);
			
			assertEquals(HttpStatus.OK, actual.getStatusCode());
			assertEquals(HttpStatus.OK.value(), actual.getBody().getHttpStatus());
			assertEquals(false, actual.getBody().getIsSuccess());
			assertEquals("", actual.getBody().getMessage());
			
			AccountModel accountModel = accountModelCaptor.getValue();
			assertNull(accountModel.getAccountNo());
			assertEquals(accountId, accountModel.getAccountId());
			assertEquals(accountName, accountModel.getAccountName());
			assertEquals(password, accountModel.getPassword());
			assertEquals(birthDate, accountModel.getBirthdate());
			assertEquals(SexEnum.WOMAN, accountModel.getSexKbn());
			assertEquals(birthplacePrefectureKbnCode, accountModel.getBirthplacePrefectureKbnCode());
			assertEquals(residentPrefectureKbnCode, accountModel.getResidentPrefectureKbnCode());
			assertEquals(freeMemo, accountModel.getFreeMemo());
			assertNull(accountModel.getAuthorityKbnCode());
			assertNull(accountModel.getLastLoginDatetime());
			assertEquals(0, accountModel.getLoginFailureCount());
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：BadRequestExceptionをthrowする")
		void account_setting_BadRequestException_accountId_is_blank() throws RegistFailureException {
			String accountId = "";
			String accountName = "AAAAAAAA";
			String password = "password01";
			LocalDate birthDate = LocalDate.of(2000, 1, 1);
			String birthplacePrefectureKbnCode = "Hokkaido";
			String residentPrefectureKbnCode = "Okinawa";
			String freeMemo = "フリーメモ";
			
			AccountRegistRequest accuontRegistRequest = new AccountRegistRequest();
			accuontRegistRequest.setAccountId(accountId);
			accuontRegistRequest.setAccountName(accountName);
			accuontRegistRequest.setPassword(password);
			accuontRegistRequest.setBirthdate(birthDate);
			accuontRegistRequest.setSexKbn(SexEnum.WOMAN);
			accuontRegistRequest.setBirthplacePrefectureKbnCode(birthplacePrefectureKbnCode);
			accuontRegistRequest.setResidentPrefectureKbnCode(residentPrefectureKbnCode);
			accuontRegistRequest.setFreeMemo(freeMemo);
			
			BindingResult result = new DataBinder(accuontRegistRequest).getBindingResult();
			FieldError fError = new FieldError("accuontRegistRequest","account_id", "");
			result.addError((ObjectError) fError);
			assertThrows(BadRequestException.class, () -> accountRestController.register(accuontRegistRequest, result));
			
			verify(accountServiceImpl, times(0)).registAccount(any(AccountModel.class));
		}
		
		@Test
		@Order(4)
		@DisplayName("異常系：RegistFailureExceptionをthrowする")
		void account_setting_RegistFailureException() throws RegistFailureException {
			String accountId = "aaaaaaaa";
			String accountName = "AAAAAAAA";
			String password = "password01";
			LocalDate birthDate = LocalDate.of(2000, 1, 1);
			String birthplacePrefectureKbnCode = "Hokkaido";
			String residentPrefectureKbnCode = "Okinawa";
			String freeMemo = "フリーメモ";
			
			AccountRegistRequest accuontRegistRequest = new AccountRegistRequest();
			accuontRegistRequest.setAccountId(accountId);
			accuontRegistRequest.setAccountName(accountName);
			accuontRegistRequest.setPassword(password);
			accuontRegistRequest.setBirthdate(birthDate);
			accuontRegistRequest.setSexKbn(SexEnum.WOMAN);
			accuontRegistRequest.setBirthplacePrefectureKbnCode(birthplacePrefectureKbnCode);
			accuontRegistRequest.setResidentPrefectureKbnCode(residentPrefectureKbnCode);
			accuontRegistRequest.setFreeMemo(freeMemo);
			
			BindingResult result = new DataBinder(accuontRegistRequest).getBindingResult();
			
			ArgumentCaptor<AccountModel> accountModelCaptor = ArgumentCaptor.forClass(AccountModel.class);
			doThrow(RegistFailureException.class).when(accountServiceImpl).registAccount(accountModelCaptor.capture());
			
			assertThrows(RegistFailureException.class, () -> accountRestController.register(accuontRegistRequest, result));
			
			AccountModel accountModel = accountModelCaptor.getValue();
			assertNull(accountModel.getAccountNo());
			assertEquals(accountId, accountModel.getAccountId());
			assertEquals(accountName, accountModel.getAccountName());
			assertEquals(password, accountModel.getPassword());
			assertEquals(birthDate, accountModel.getBirthdate());
			assertEquals(SexEnum.WOMAN, accountModel.getSexKbn());
			assertEquals(birthplacePrefectureKbnCode, accountModel.getBirthplacePrefectureKbnCode());
			assertEquals(residentPrefectureKbnCode, accountModel.getResidentPrefectureKbnCode());
			assertEquals(freeMemo, accountModel.getFreeMemo());
			assertNull(accountModel.getAuthorityKbnCode());
			assertNull(accountModel.getLastLoginDatetime());
			assertEquals(0, accountModel.getLoginFailureCount());
		}
	}
	
	@Nested
	@Order(2)
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class update {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウントID、パスワード変更なし")
		void update_not_change_accountID_and_password() throws UpdateFailureException, BadRequestException {
			String accountId = "aaaaaaaa";
			String accountName = "AAAAAAAA";
			String password = "";
			LocalDate birthDate = LocalDate.of(2000, 1, 1);
			String birthplacePrefectureKbnCode = "Hokkaido";
			String residentPrefectureKbnCode = "Okinawa";
			String freeMemo = "フリーメモ";
			
			AccountUpdateRequest accountUpdateRequest = new AccountUpdateRequest();
			accountUpdateRequest.setAccountId(accountId);
			accountUpdateRequest.setAccountName(accountName);
			accountUpdateRequest.setNewPassword(password);
			accountUpdateRequest.setBirthdate(birthDate);
			accountUpdateRequest.setSexKbn(SexEnum.WOMAN);
			accountUpdateRequest.setBirthplacePrefectureKbnCode(birthplacePrefectureKbnCode);
			accountUpdateRequest.setResidentPrefectureKbnCode(residentPrefectureKbnCode);
			accountUpdateRequest.setFreeMemo(freeMemo);
			
			BindingResult result = new DataBinder(accountUpdateRequest).getBindingResult();
			FieldError fError = new FieldError("accountUpdateRequest","newPassword", "");
			result.addError((ObjectError) fError);
			
			doReturn(1).when(sessionHelper).getAccountNo();
			doReturn(accountId).when(sessionHelper).getAccountId();
			
			ArgumentCaptor<AccountModel> accountModelCaptor = ArgumentCaptor.forClass(AccountModel.class);
			doReturn(false).when(accountServiceImpl).updateAccount(accountModelCaptor.capture());
			
			ResponseEntity<AccountUpdateResponse> actual
				= accountRestController.update(accountUpdateRequest, result);
			
			assertEquals(HttpStatus.OK, actual.getStatusCode());
			assertEquals(HttpStatus.OK.value(), actual.getBody().getHttpStatus());
			assertEquals(false, actual.getBody().getIsDuplicateAccountId());
			assertEquals(false, actual.getBody().getIsAccountIdChanged());
			assertEquals(false, actual.getBody().getIsPasswordChanged());
			assertEquals("", actual.getBody().getMessage());
			
			AccountModel accountModel = accountModelCaptor.getValue();
			assertEquals(1, accountModel.getAccountNo());
			assertEquals(accountId, accountModel.getAccountId());
			assertEquals(accountName, accountModel.getAccountName());
			assertNull(accountModel.getPassword());
			assertEquals(birthDate, accountModel.getBirthdate());
			assertEquals(SexEnum.WOMAN, accountModel.getSexKbn());
			assertEquals(birthplacePrefectureKbnCode, accountModel.getBirthplacePrefectureKbnCode());
			assertEquals(residentPrefectureKbnCode, accountModel.getResidentPrefectureKbnCode());
			assertEquals(freeMemo, accountModel.getFreeMemo());
			assertNull(accountModel.getAuthorityKbnCode());
			assertNull(accountModel.getLastLoginDatetime());
			assertNull(accountModel.getLoginFailureCount());
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：アカウントID変更あり、パスワード変更なし")
		void update_change_accountID() throws UpdateFailureException, BadRequestException {
			String accountId = "aaaaaaaa";
			String accountName = "AAAAAAAA";
			String password = "";
			LocalDate birthDate = LocalDate.of(2000, 1, 1);
			String birthplacePrefectureKbnCode = "Hokkaido";
			String residentPrefectureKbnCode = "Okinawa";
			String freeMemo = "フリーメモ";
			
			AccountUpdateRequest accountUpdateRequest = new AccountUpdateRequest();
			accountUpdateRequest.setAccountId(accountId);
			accountUpdateRequest.setAccountName(accountName);
			accountUpdateRequest.setNewPassword(password);
			accountUpdateRequest.setBirthdate(birthDate);
			accountUpdateRequest.setSexKbn(SexEnum.WOMAN);
			accountUpdateRequest.setBirthplacePrefectureKbnCode(birthplacePrefectureKbnCode);
			accountUpdateRequest.setResidentPrefectureKbnCode(residentPrefectureKbnCode);
			accountUpdateRequest.setFreeMemo(freeMemo);
			
			BindingResult result = new DataBinder(accountUpdateRequest).getBindingResult();
			FieldError fError = new FieldError("accountUpdateRequest","newPassword", "");
			result.addError((ObjectError) fError);
			
			doReturn(1).when(sessionHelper).getAccountNo();
			doReturn("bbbbbbbb").when(sessionHelper).getAccountId();
			
			ArgumentCaptor<AccountModel> accountModelCaptor = ArgumentCaptor.forClass(AccountModel.class);
			doReturn(false).when(accountServiceImpl).updateAccount(accountModelCaptor.capture());
			
			ResponseEntity<AccountUpdateResponse> actual
				= accountRestController.update(accountUpdateRequest, result);
			
			assertEquals(HttpStatus.OK, actual.getStatusCode());
			assertEquals(HttpStatus.OK.value(), actual.getBody().getHttpStatus());
			assertEquals(false, actual.getBody().getIsDuplicateAccountId());
			assertEquals(true, actual.getBody().getIsAccountIdChanged());
			assertEquals(false, actual.getBody().getIsPasswordChanged());
			assertEquals("", actual.getBody().getMessage());
			
			AccountModel accountModel = accountModelCaptor.getValue();
			assertEquals(1, accountModel.getAccountNo());
			assertEquals(accountId, accountModel.getAccountId());
			assertEquals(accountName, accountModel.getAccountName());
			assertNull(accountModel.getPassword());
			assertEquals(birthDate, accountModel.getBirthdate());
			assertEquals(SexEnum.WOMAN, accountModel.getSexKbn());
			assertEquals(birthplacePrefectureKbnCode, accountModel.getBirthplacePrefectureKbnCode());
			assertEquals(residentPrefectureKbnCode, accountModel.getResidentPrefectureKbnCode());
			assertEquals(freeMemo, accountModel.getFreeMemo());
			assertNull(accountModel.getAuthorityKbnCode());
			assertNull(accountModel.getLastLoginDatetime());
			assertNull(accountModel.getLoginFailureCount());
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：アカウントID変更なし、パスワード変更あり")
		void update_change_password() throws UpdateFailureException, BadRequestException {
			String accountId = "aaaaaaaa";
			String accountName = "AAAAAAAA";
			String password = "password01";
			LocalDate birthDate = LocalDate.of(2000, 1, 1);
			String birthplacePrefectureKbnCode = "Hokkaido";
			String residentPrefectureKbnCode = "Okinawa";
			String freeMemo = "フリーメモ";
			
			AccountUpdateRequest accountUpdateRequest = new AccountUpdateRequest();
			accountUpdateRequest.setAccountId(accountId);
			accountUpdateRequest.setAccountName(accountName);
			accountUpdateRequest.setNewPassword(password);
			accountUpdateRequest.setBirthdate(birthDate);
			accountUpdateRequest.setSexKbn(SexEnum.WOMAN);
			accountUpdateRequest.setBirthplacePrefectureKbnCode(birthplacePrefectureKbnCode);
			accountUpdateRequest.setResidentPrefectureKbnCode(residentPrefectureKbnCode);
			accountUpdateRequest.setFreeMemo(freeMemo);
			
			BindingResult result = new DataBinder(accountUpdateRequest).getBindingResult();
			
			doReturn(1).when(sessionHelper).getAccountNo();
			doReturn(accountId).when(sessionHelper).getAccountId();
			
			ArgumentCaptor<AccountModel> accountModelCaptor = ArgumentCaptor.forClass(AccountModel.class);
			doReturn(false).when(accountServiceImpl).updateAccount(accountModelCaptor.capture());
			
			ResponseEntity<AccountUpdateResponse> actual
				= accountRestController.update(accountUpdateRequest, result);
			
			assertEquals(HttpStatus.OK, actual.getStatusCode());
			assertEquals(HttpStatus.OK.value(), actual.getBody().getHttpStatus());
			assertEquals(false, actual.getBody().getIsDuplicateAccountId());
			assertEquals(false, actual.getBody().getIsAccountIdChanged());
			assertEquals(true, actual.getBody().getIsPasswordChanged());
			assertEquals("", actual.getBody().getMessage());
			
			AccountModel accountModel = accountModelCaptor.getValue();
			assertEquals(1, accountModel.getAccountNo());
			assertEquals(accountId, accountModel.getAccountId());
			assertEquals(accountName, accountModel.getAccountName());
			assertEquals(password, accountModel.getPassword());
			assertEquals(birthDate, accountModel.getBirthdate());
			assertEquals(SexEnum.WOMAN, accountModel.getSexKbn());
			assertEquals(birthplacePrefectureKbnCode, accountModel.getBirthplacePrefectureKbnCode());
			assertEquals(residentPrefectureKbnCode, accountModel.getResidentPrefectureKbnCode());
			assertEquals(freeMemo, accountModel.getFreeMemo());
			assertNull(accountModel.getAuthorityKbnCode());
			assertNull(accountModel.getLastLoginDatetime());
			assertNull(accountModel.getLoginFailureCount());
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：アカウントID変更あり、パスワード変更あり")
		void update_change_accountId_and_password() throws UpdateFailureException, BadRequestException {
			String accountId = "aaaaaaaa";
			String accountName = "AAAAAAAA";
			String password = "password01";
			LocalDate birthDate = LocalDate.of(2000, 1, 1);
			String birthplacePrefectureKbnCode = "Hokkaido";
			String residentPrefectureKbnCode = "Okinawa";
			String freeMemo = "フリーメモ";
			
			AccountUpdateRequest accountUpdateRequest = new AccountUpdateRequest();
			accountUpdateRequest.setAccountId(accountId);
			accountUpdateRequest.setAccountName(accountName);
			accountUpdateRequest.setNewPassword(password);
			accountUpdateRequest.setBirthdate(birthDate);
			accountUpdateRequest.setSexKbn(SexEnum.WOMAN);
			accountUpdateRequest.setBirthplacePrefectureKbnCode(birthplacePrefectureKbnCode);
			accountUpdateRequest.setResidentPrefectureKbnCode(residentPrefectureKbnCode);
			accountUpdateRequest.setFreeMemo(freeMemo);
			
			BindingResult result = new DataBinder(accountUpdateRequest).getBindingResult();
			
			doReturn(1).when(sessionHelper).getAccountNo();
			doReturn("bbbbbbbb").when(sessionHelper).getAccountId();
			
			ArgumentCaptor<AccountModel> accountModelCaptor = ArgumentCaptor.forClass(AccountModel.class);
			doReturn(false).when(accountServiceImpl).updateAccount(accountModelCaptor.capture());
			
			ResponseEntity<AccountUpdateResponse> actual
				= accountRestController.update(accountUpdateRequest, result);
			
			assertEquals(HttpStatus.OK, actual.getStatusCode());
			assertEquals(HttpStatus.OK.value(), actual.getBody().getHttpStatus());
			assertEquals(false, actual.getBody().getIsDuplicateAccountId());
			assertEquals(true, actual.getBody().getIsAccountIdChanged());
			assertEquals(true, actual.getBody().getIsPasswordChanged());
			assertEquals("", actual.getBody().getMessage());
			
			AccountModel accountModel = accountModelCaptor.getValue();
			assertEquals(1, accountModel.getAccountNo());
			assertEquals(accountId, accountModel.getAccountId());
			assertEquals(accountName, accountModel.getAccountName());
			assertEquals(password, accountModel.getPassword());
			assertEquals(birthDate, accountModel.getBirthdate());
			assertEquals(SexEnum.WOMAN, accountModel.getSexKbn());
			assertEquals(birthplacePrefectureKbnCode, accountModel.getBirthplacePrefectureKbnCode());
			assertEquals(residentPrefectureKbnCode, accountModel.getResidentPrefectureKbnCode());
			assertEquals(freeMemo, accountModel.getFreeMemo());
			assertNull(accountModel.getAuthorityKbnCode());
			assertNull(accountModel.getLastLoginDatetime());
			assertNull(accountModel.getLoginFailureCount());
		}
		
		@Test
		@Order(5)
		@DisplayName("正常系：アカウントID重複")
		void update_duplicate_accountId() throws UpdateFailureException, BadRequestException {
			String accountId = "aaaaaaaa";
			String accountName = "AAAAAAAA";
			String password = "";
			LocalDate birthDate = LocalDate.of(2000, 1, 1);
			String birthplacePrefectureKbnCode = "Hokkaido";
			String residentPrefectureKbnCode = "Okinawa";
			String freeMemo = "フリーメモ";
			
			AccountUpdateRequest accountUpdateRequest = new AccountUpdateRequest();
			accountUpdateRequest.setAccountId(accountId);
			accountUpdateRequest.setAccountName(accountName);
			accountUpdateRequest.setNewPassword(password);
			accountUpdateRequest.setBirthdate(birthDate);
			accountUpdateRequest.setSexKbn(SexEnum.WOMAN);
			accountUpdateRequest.setBirthplacePrefectureKbnCode(birthplacePrefectureKbnCode);
			accountUpdateRequest.setResidentPrefectureKbnCode(residentPrefectureKbnCode);
			accountUpdateRequest.setFreeMemo(freeMemo);
			
			BindingResult result = new DataBinder(accountUpdateRequest).getBindingResult();
			
			doReturn(1).when(sessionHelper).getAccountNo();
			doReturn("bbbbbbbb").when(sessionHelper).getAccountId();
			
			ArgumentCaptor<AccountModel> accountModelCaptor = ArgumentCaptor.forClass(AccountModel.class);
			doReturn(true).when(accountServiceImpl).updateAccount(accountModelCaptor.capture());
			
			ResponseEntity<AccountUpdateResponse> actual
				= accountRestController.update(accountUpdateRequest, result);
			
			assertEquals(HttpStatus.OK, actual.getStatusCode());
			assertEquals(HttpStatus.OK.value(), actual.getBody().getHttpStatus());
			assertEquals(true, actual.getBody().getIsDuplicateAccountId());
			assertEquals(true, actual.getBody().getIsAccountIdChanged());
			assertEquals(false, actual.getBody().getIsPasswordChanged());
			assertEquals("", actual.getBody().getMessage());
			
			AccountModel accountModel = accountModelCaptor.getValue();
			assertEquals(1, accountModel.getAccountNo());
			assertEquals(accountId, accountModel.getAccountId());
			assertEquals(accountName, accountModel.getAccountName());
			assertNull(accountModel.getPassword());
			assertEquals(birthDate, accountModel.getBirthdate());
			assertEquals(SexEnum.WOMAN, accountModel.getSexKbn());
			assertEquals(birthplacePrefectureKbnCode, accountModel.getBirthplacePrefectureKbnCode());
			assertEquals(residentPrefectureKbnCode, accountModel.getResidentPrefectureKbnCode());
			assertEquals(freeMemo, accountModel.getFreeMemo());
			assertNull(accountModel.getAuthorityKbnCode());
			assertNull(accountModel.getLastLoginDatetime());
			assertNull(accountModel.getLoginFailureCount());
		}
		
		@Test
		@Order(6)
		@DisplayName("異常系：パスワード変更なしで、パスワード以外のパラメータが不正")
		void update_BadRequestException_account_id() throws UpdateFailureException {
			String accountId = "";
			String accountName = "AAAAAAAA";
			String password = "";
			LocalDate birthDate = LocalDate.of(2000, 1, 1);
			String birthplacePrefectureKbnCode = "Hokkaido";
			String residentPrefectureKbnCode = "Okinawa";
			String freeMemo = "フリーメモ";
			
			AccountUpdateRequest accountUpdateRequest = new AccountUpdateRequest();
			accountUpdateRequest.setAccountId(accountId);
			accountUpdateRequest.setAccountName(accountName);
			accountUpdateRequest.setNewPassword(password);
			accountUpdateRequest.setBirthdate(birthDate);
			accountUpdateRequest.setSexKbn(SexEnum.WOMAN);
			accountUpdateRequest.setBirthplacePrefectureKbnCode(birthplacePrefectureKbnCode);
			accountUpdateRequest.setResidentPrefectureKbnCode(residentPrefectureKbnCode);
			accountUpdateRequest.setFreeMemo(freeMemo);
			
			BindingResult result = new DataBinder(accountUpdateRequest).getBindingResult();
			FieldError fError1 = new FieldError("accountUpdateRequest","newPassword", "");
			result.addError((ObjectError) fError1);
			FieldError fError2 = new FieldError("accountUpdateRequest","accountId", "");
			result.addError((ObjectError) fError2);
			
			assertThrows(BadRequestException.class, () -> accountRestController.update(accountUpdateRequest, result));
			verify(sessionHelper, times(0)).getAccountNo();
			verify(sessionHelper, times(0)).getAccountId();
			verify(accountServiceImpl,times(0)).updateAccount(any(AccountModel.class));
		}
		
		@Test
		@Order(7)
		@DisplayName("異常系：パスワード変更ありで不正でなく、パスワード以外のパラメータが不正")
		void update_BadRequestException_account_id_with_change_password() throws UpdateFailureException {
			String accountId = "";
			String accountName = "AAAAAAAA";
			String password = "password01";
			LocalDate birthDate = LocalDate.of(2000, 1, 1);
			String birthplacePrefectureKbnCode = "Hokkaido";
			String residentPrefectureKbnCode = "Okinawa";
			String freeMemo = "フリーメモ";
			
			AccountUpdateRequest accountUpdateRequest = new AccountUpdateRequest();
			accountUpdateRequest.setAccountId(accountId);
			accountUpdateRequest.setAccountName(accountName);
			accountUpdateRequest.setNewPassword(password);
			accountUpdateRequest.setBirthdate(birthDate);
			accountUpdateRequest.setSexKbn(SexEnum.WOMAN);
			accountUpdateRequest.setBirthplacePrefectureKbnCode(birthplacePrefectureKbnCode);
			accountUpdateRequest.setResidentPrefectureKbnCode(residentPrefectureKbnCode);
			accountUpdateRequest.setFreeMemo(freeMemo);
			
			BindingResult result = new DataBinder(accountUpdateRequest).getBindingResult();
			FieldError fError1 = new FieldError("accountUpdateRequest","accountId", "");
			result.addError((ObjectError) fError1);
			
			assertThrows(BadRequestException.class, () -> accountRestController.update(accountUpdateRequest, result));
			verify(sessionHelper, times(0)).getAccountNo();
			verify(sessionHelper, times(0)).getAccountId();
			verify(accountServiceImpl,times(0)).updateAccount(any(AccountModel.class));
		}
		
		@Test
		@Order(8)
		@DisplayName("異常系：パスワード変更ありで、パスワードが不正")
		void update_BadRequestException_password() throws UpdateFailureException {
			String accountId = "aaaaaaaa";
			String accountName = "AAAAAAAA";
			String password = "pass";
			LocalDate birthDate = LocalDate.of(2000, 1, 1);
			String birthplacePrefectureKbnCode = "Hokkaido";
			String residentPrefectureKbnCode = "Okinawa";
			String freeMemo = "フリーメモ";
			
			AccountUpdateRequest accountUpdateRequest = new AccountUpdateRequest();
			accountUpdateRequest.setAccountId(accountId);
			accountUpdateRequest.setAccountName(accountName);
			accountUpdateRequest.setNewPassword(password);
			accountUpdateRequest.setBirthdate(birthDate);
			accountUpdateRequest.setSexKbn(SexEnum.WOMAN);
			accountUpdateRequest.setBirthplacePrefectureKbnCode(birthplacePrefectureKbnCode);
			accountUpdateRequest.setResidentPrefectureKbnCode(residentPrefectureKbnCode);
			accountUpdateRequest.setFreeMemo(freeMemo);
			
			BindingResult result = new DataBinder(accountUpdateRequest).getBindingResult();
			FieldError fError1 = new FieldError("accountUpdateRequest","newPassword", "");
			result.addError((ObjectError) fError1);
			
			assertThrows(BadRequestException.class, () -> accountRestController.update(accountUpdateRequest, result));
			verify(sessionHelper, times(0)).getAccountNo();
			verify(sessionHelper, times(0)).getAccountId();
			verify(accountServiceImpl,times(0)).updateAccount(any(AccountModel.class));
		}
		
		@Test
		@Order(9)
		@DisplayName("異常系：UpdateFailureExceptionをthrowする")
		void update_UpdateFailureException() throws UpdateFailureException, BadRequestException {
			String accountId = "aaaaaaaa";
			String accountName = "AAAAAAAA";
			String password = "password01";
			LocalDate birthDate = LocalDate.of(2000, 1, 1);
			String birthplacePrefectureKbnCode = "Hokkaido";
			String residentPrefectureKbnCode = "Okinawa";
			String freeMemo = "フリーメモ";
			
			AccountUpdateRequest accountUpdateRequest = new AccountUpdateRequest();
			accountUpdateRequest.setAccountId(accountId);
			accountUpdateRequest.setAccountName(accountName);
			accountUpdateRequest.setNewPassword(password);
			accountUpdateRequest.setBirthdate(birthDate);
			accountUpdateRequest.setSexKbn(SexEnum.WOMAN);
			accountUpdateRequest.setBirthplacePrefectureKbnCode(birthplacePrefectureKbnCode);
			accountUpdateRequest.setResidentPrefectureKbnCode(residentPrefectureKbnCode);
			accountUpdateRequest.setFreeMemo(freeMemo);
			
			BindingResult result = new DataBinder(accountUpdateRequest).getBindingResult();
			
			doReturn(1).when(sessionHelper).getAccountNo();
			
			ArgumentCaptor<AccountModel> accountModelCaptor = ArgumentCaptor.forClass(AccountModel.class);
			doThrow(UpdateFailureException.class).when(accountServiceImpl).updateAccount(accountModelCaptor.capture());
			
			assertThrows(UpdateFailureException.class, () -> accountRestController.update(accountUpdateRequest, result));
			verify(sessionHelper, times(0)).getAccountId();
			
			AccountModel accountModel = accountModelCaptor.getValue();
			assertEquals(1, accountModel.getAccountNo());
			assertEquals(accountId, accountModel.getAccountId());
			assertEquals(accountName, accountModel.getAccountName());
			assertEquals(password, accountModel.getPassword());
			assertEquals(birthDate, accountModel.getBirthdate());
			assertEquals(SexEnum.WOMAN, accountModel.getSexKbn());
			assertEquals(birthplacePrefectureKbnCode, accountModel.getBirthplacePrefectureKbnCode());
			assertEquals(residentPrefectureKbnCode, accountModel.getResidentPrefectureKbnCode());
			assertEquals(freeMemo, accountModel.getFreeMemo());
			assertNull(accountModel.getAuthorityKbnCode());
			assertNull(accountModel.getLastLoginDatetime());
			assertNull(accountModel.getLoginFailureCount());
		}
	}
	
	@Nested
	@Order(3)
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class handleInsertFailedException {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void handleInsertFailedException_success() {
			RegistFailureException exception = new RegistFailureException(ErrorEnum.INVALID_INPUT);
			
			ResponseEntity<ErrorRequest> actual
				= accountRestController.handleInsertFailedException(exception);
			
			assertEquals(HttpStatus.CONFLICT, actual.getStatusCode());
			assertEquals(HttpStatus.CONFLICT.value(), actual.getBody().getHttpStatus());
			assertEquals(ErrorEnum.INVALID_INPUT.getErrorMessage(), actual.getBody().getErrorMessage());
			assertEquals("/register", actual.getBody().getGoBackPageUrl());
		}
	}
}