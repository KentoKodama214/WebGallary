package com.web.gallary.repository.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import com.web.gallary.entity.Account;
import com.web.gallary.exception.RegistFailureException;
import com.web.gallary.exception.UpdateFailureException;
import com.web.gallary.mapper.AccountMapper;
import com.web.gallary.model.AccountModel;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class AccountRepositoryImplTest {
	@InjectMocks
	private AccountRepositoryImpl accountRepositoryImpl;
	
	@Mock
	private AccountMapper accountMapper;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class getByAccountNo {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウントが取得できた場合")
		void getByAccountNo_found() {
			Account account = Account.builder()
					.accountNo(1)
					.createdBy(1)
					.createdAt(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.updatedBy(1)
					.updatedAt(OffsetDateTime.of(2001, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.isDeleted(false)
					.accountId("aaaaaaaa")
					.accountName("AAAAAAAA")
					.password("$2a$10$password1")
					.birthdate(LocalDate.of(1991, 2, 14))
					.sexKbnCode("")
					.birthplacePrefectureKbnCode("")
					.residentPrefectureKbnCode("")
					.freeMemo("")
					.authorityKbnCode("administrator")
					.lastLoginDatetime(OffsetDateTime.of(2002, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.loginFailureCount(0)
					.build();

			List<Account> accountList = new ArrayList<Account>();
			accountList.add(account);
			
			ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
			doReturn(accountList).when(accountMapper).select(accountCaptor.capture());
			
			Account getAccount = accountRepositoryImpl.getByAccountNo(1);

			verify(accountMapper).select(any(Account.class));
			Account accountCapture = accountCaptor.getValue();
			assertEquals(accountCapture.getAccountNo(), 1);

			assertNotNull(getAccount);
			assertEquals(getAccount, accountList.getFirst());
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：アカウントが取得できなかった場合")
		void getByAccountNo_not_found() {
			ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
			doReturn(new ArrayList<Account>()).when(accountMapper).select(accountCaptor.capture());
			
			Account getAccount = accountRepositoryImpl.getByAccountNo(1);
			
			verify(accountMapper).select(any(Account.class));
			Account accountCapture = accountCaptor.getValue();
			assertEquals(accountCapture.getAccountNo(), 1);
			
			assertNull(getAccount);
		}
	}
	
	@Nested
	@Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class getByAccountId {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウントが取得できた場合")
		void getByAccountId_found() {
			Account account = Account.builder()
					.accountNo(1)
					.createdBy(1)
					.createdAt(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.updatedBy(1)
					.updatedAt(OffsetDateTime.of(2001, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.isDeleted(false)
					.accountId("aaaaaaaa")
					.accountName("AAAAAAAA")
					.password("$2a$10$password1")
					.birthdate(LocalDate.of(1991, 2, 14))
					.sexKbnCode("")
					.birthplacePrefectureKbnCode("")
					.residentPrefectureKbnCode("")
					.freeMemo("")
					.authorityKbnCode("administrator")
					.lastLoginDatetime(OffsetDateTime.of(2002, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.loginFailureCount(0)
					.build();

			List<Account> accountList = new ArrayList<Account>();
			accountList.add(account);
			
			ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
			doReturn(accountList).when(accountMapper).select(accountCaptor.capture());
			
			Account getAccount = accountRepositoryImpl.getByAccountId("aaaaaaaa");

			verify(accountMapper).select(any(Account.class));
			Account accountCapture = accountCaptor.getValue();
			assertEquals(accountCapture.getAccountId(), "aaaaaaaa");

			assertNotNull(getAccount);
			assertEquals(getAccount, accountList.getFirst());
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：アカウントが取得できなかった場合")
		void getByAccountId_not_found() {
			ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
			doReturn(new ArrayList<Account>()).when(accountMapper).select(accountCaptor.capture());
			
			Account getAccount = accountRepositoryImpl.getByAccountId("aaaaaaaa");
			
			verify(accountMapper).select(any(Account.class));
			Account accountCapture = accountCaptor.getValue();
			assertEquals(accountCapture.getAccountId(), "aaaaaaaa");
			
			assertNull(getAccount);
		}
	}
	
	@Nested
	@Order(3)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class regist {
		@Test
		@Order(1)
		@DisplayName("正常系：Nullのパラメータを含むAccountModelの登録")
		void regist_contain_null_parameter() throws RegistFailureException {
			AccountModel accountModel = AccountModel.builder()
					.accountId("aaaaaaaa")
					.accountName("AAAAAAAA")
					.password("aaaaaaaa")
					.build();
			
			ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
			doReturn(1).when(accountMapper).insert(accountCaptor.capture());
			doReturn("$2a$10$password1").when(passwordEncoder).encode("aaaaaaaa");
			
			accountRepositoryImpl.regist(accountModel);
			
			verify(accountMapper).insert(any(Account.class));
			Account accountCapture = accountCaptor.getValue();
			assertEquals(accountCapture.getAccountNo(), null);
			assertEquals(accountCapture.getCreatedBy(), 0);
			assertEquals(accountCapture.getCreatedAt(), null);
			assertEquals(accountCapture.getUpdatedBy(), 0);
			assertEquals(accountCapture.getUpdatedAt(), null);
			assertEquals(accountCapture.getIsDeleted(), null);
			assertEquals(accountCapture.getAccountId(), "aaaaaaaa");
			assertEquals(accountCapture.getAccountName(), "AAAAAAAA");
			assertEquals(accountCapture.getPassword(), "$2a$10$password1");
			assertEquals(accountCapture.getBirthdate(), LocalDate.of(1900, 1, 1));
			assertEquals(accountCapture.getSexKbnCode(), "none");
			assertEquals(accountCapture.getBirthplacePrefectureKbnCode(), "none");
			assertEquals(accountCapture.getResidentPrefectureKbnCode(), "none");
			assertEquals(accountCapture.getFreeMemo(), "");
			assertEquals(accountCapture.getAuthorityKbnCode(), "mini-user");
			assertEquals(accountCapture.getLastLoginDatetime(), OffsetDateTime.of(1900, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(9)));
			assertEquals(accountCapture.getLoginFailureCount(), 0);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：Nullのパラメータを含まないAccountModelの登録")
		void regist_not_contain_null_parameter() throws RegistFailureException {
			AccountModel accountModel = AccountModel.builder()
					.accountId("aaaaaaaa")
					.accountName("AAAAAAAA")
					.password("aaaaaaaa")
					.birthdate(LocalDate.of(1991, 2, 14))
					.sexKbnCode("woman")
					.birthplacePrefectureKbnCode("Hokkaido")
					.residentPrefectureKbnCode("Okinawa")
					.freeMemo("フリーメモ")
					.build();
			
			ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
			doReturn(1).when(accountMapper).insert(accountCaptor.capture());
			doReturn("$2a$10$password1").when(passwordEncoder).encode("aaaaaaaa");
			
			accountRepositoryImpl.regist(accountModel);
			
			verify(accountMapper).insert(any(Account.class));
			Account accountCapture = accountCaptor.getValue();
			assertEquals(accountCapture.getAccountNo(), null);
			assertEquals(accountCapture.getCreatedBy(), 0);
			assertEquals(accountCapture.getCreatedAt(), null);
			assertEquals(accountCapture.getUpdatedBy(), 0);
			assertEquals(accountCapture.getUpdatedAt(), null);
			assertEquals(accountCapture.getIsDeleted(), null);
			assertEquals(accountCapture.getAccountId(), "aaaaaaaa");
			assertEquals(accountCapture.getAccountName(), "AAAAAAAA");
			assertEquals(accountCapture.getPassword(), "$2a$10$password1");
			assertEquals(accountCapture.getBirthdate(), LocalDate.of(1991, 2, 14));
			assertEquals(accountCapture.getSexKbnCode(), "woman");
			assertEquals(accountCapture.getBirthplacePrefectureKbnCode(), "Hokkaido");
			assertEquals(accountCapture.getResidentPrefectureKbnCode(), "Okinawa");
			assertEquals(accountCapture.getFreeMemo(), "フリーメモ");
			assertEquals(accountCapture.getAuthorityKbnCode(), "mini-user");
			assertEquals(accountCapture.getLastLoginDatetime(), OffsetDateTime.of(1900, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(9)));
			assertEquals(accountCapture.getLoginFailureCount(), 0);
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：DuplicateKeyExceptionをthrowする")
		void regist_DuplicateKeyException() throws RegistFailureException {
			AccountModel accountModel = AccountModel.builder()
					.accountId("aaaaaaaa")
					.accountName("AAAAAAAA")
					.password("aaaaaaaa")
					.build();
			
			ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
			doThrow(DuplicateKeyException.class).when(accountMapper).insert(accountCaptor.capture());
			doReturn("$2a$10$password1").when(passwordEncoder).encode("aaaaaaaa");
			
			assertThrows(RegistFailureException.class, () -> accountRepositoryImpl.regist(accountModel));
			
			verify(accountMapper).insert(any(Account.class));
			Account accountCapture = accountCaptor.getValue();
			assertEquals(accountCapture.getAccountNo(), null);
			assertEquals(accountCapture.getCreatedBy(), 0);
			assertEquals(accountCapture.getCreatedAt(), null);
			assertEquals(accountCapture.getUpdatedBy(), 0);
			assertEquals(accountCapture.getUpdatedAt(), null);
			assertEquals(accountCapture.getIsDeleted(), null);
			assertEquals(accountCapture.getAccountId(), "aaaaaaaa");
			assertEquals(accountCapture.getAccountName(), "AAAAAAAA");
			assertEquals(accountCapture.getPassword(), "$2a$10$password1");
			assertEquals(accountCapture.getBirthdate(), LocalDate.of(1900, 1, 1));
			assertEquals(accountCapture.getSexKbnCode(), "none");
			assertEquals(accountCapture.getBirthplacePrefectureKbnCode(), "none");
			assertEquals(accountCapture.getResidentPrefectureKbnCode(), "none");
			assertEquals(accountCapture.getFreeMemo(), "");
			assertEquals(accountCapture.getAuthorityKbnCode(), "mini-user");
			assertEquals(accountCapture.getLastLoginDatetime(), OffsetDateTime.of(1900, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(9)));
			assertEquals(accountCapture.getLoginFailureCount(), 0);
		}
	}
	
	@Nested
	@Order(4)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class update {
		@Test
		@Order(1)
		@DisplayName("正常系：Nullのパラメータを含むAccountModelでの更新")
		void update_contain_null_parameter() throws UpdateFailureException {
			AccountModel accountModel = AccountModel.builder()
					.accountNo(1)
					.accountId("aaaaaaaa")
					.accountName("AAAAAAAA")
					.build();
			
			ArgumentCaptor<Account> cndAccountCaptor = ArgumentCaptor.forClass(Account.class);
			ArgumentCaptor<Account> targetAccountCaptor = ArgumentCaptor.forClass(Account.class);
			doReturn(1).when(accountMapper).update(cndAccountCaptor.capture(), targetAccountCaptor.capture());
			
			accountRepositoryImpl.update(accountModel);
			
			verify(accountMapper).update(any(Account.class), any(Account.class));
			Account cndAccountCapture = cndAccountCaptor.getValue();
			assertEquals(cndAccountCapture.getAccountNo(), 1);
			
			Account targetAccountCapture = targetAccountCaptor.getValue();
			assertEquals(targetAccountCapture.getCreatedBy(), null);
			assertEquals(targetAccountCapture.getCreatedAt(), null);
			assertEquals(targetAccountCapture.getUpdatedBy(), null);
			assertEquals(targetAccountCapture.getUpdatedAt(), null);
			assertEquals(targetAccountCapture.getIsDeleted(), null);
			assertEquals(targetAccountCapture.getAccountId(), "aaaaaaaa");
			assertEquals(targetAccountCapture.getAccountName(), "AAAAAAAA");
			assertEquals(targetAccountCapture.getPassword(), null);
			assertEquals(targetAccountCapture.getBirthdate(), LocalDate.of(1900, 1, 1));
			assertEquals(targetAccountCapture.getSexKbnCode(), "none");
			assertEquals(targetAccountCapture.getBirthplacePrefectureKbnCode(), "none");
			assertEquals(targetAccountCapture.getResidentPrefectureKbnCode(), "none");
			assertEquals(targetAccountCapture.getFreeMemo(), "");
			assertEquals(targetAccountCapture.getAuthorityKbnCode(), null);
			assertEquals(targetAccountCapture.getLastLoginDatetime(), OffsetDateTime.of(1900, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(9)));
			assertEquals(targetAccountCapture.getLoginFailureCount(), 0);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：Nullのパラメータを含まないAccountModelでの更新")
		void update_not_contain_null_parameter() throws UpdateFailureException {
			AccountModel accountModel = AccountModel.builder()
					.accountNo(1)
					.accountId("aaaaaaaa")
					.accountName("AAAAAAAA")
					.password("aaaaaaaa")
					.birthdate(LocalDate.of(1991, 2, 14))
					.sexKbnCode("woman")
					.birthplacePrefectureKbnCode("Hokkaido")
					.residentPrefectureKbnCode("Okinawa")
					.freeMemo("フリーメモ")
					.lastLoginDatetime(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(9)))
					.loginFailureCount(2)
					.build();
			
			ArgumentCaptor<Account> cndAccountCaptor = ArgumentCaptor.forClass(Account.class);
			ArgumentCaptor<Account> targetAccountCaptor = ArgumentCaptor.forClass(Account.class);
			doReturn(1).when(accountMapper).update(cndAccountCaptor.capture(), targetAccountCaptor.capture());
			doReturn("$2a$10$password1").when(passwordEncoder).encode("aaaaaaaa");
			
			accountRepositoryImpl.update(accountModel);
			
			verify(accountMapper).update(any(Account.class), any(Account.class));
			Account cndAccountCapture = cndAccountCaptor.getValue();
			assertEquals(cndAccountCapture.getAccountNo(), 1);
			
			Account targetAccountCapture = targetAccountCaptor.getValue();
			assertEquals(targetAccountCapture.getCreatedBy(), null);
			assertEquals(targetAccountCapture.getCreatedAt(), null);
			assertEquals(targetAccountCapture.getUpdatedBy(), null);
			assertEquals(targetAccountCapture.getUpdatedAt(), null);
			assertEquals(targetAccountCapture.getIsDeleted(), null);
			assertEquals(targetAccountCapture.getAccountId(), "aaaaaaaa");
			assertEquals(targetAccountCapture.getAccountName(), "AAAAAAAA");
			assertEquals(targetAccountCapture.getPassword(), "$2a$10$password1");
			assertEquals(targetAccountCapture.getBirthdate(), LocalDate.of(1991, 2, 14));
			assertEquals(targetAccountCapture.getSexKbnCode(), "woman");
			assertEquals(targetAccountCapture.getBirthplacePrefectureKbnCode(), "Hokkaido");
			assertEquals(targetAccountCapture.getResidentPrefectureKbnCode(), "Okinawa");
			assertEquals(targetAccountCapture.getFreeMemo(), "フリーメモ");
			assertEquals(targetAccountCapture.getAuthorityKbnCode(), null);
			assertEquals(targetAccountCapture.getLastLoginDatetime(), OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(9)));
			assertEquals(targetAccountCapture.getLoginFailureCount(), 2);
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：UpdateFailureExceptionをthrowする")
		void update_UpdateFailureException() {
			AccountModel accountModel = AccountModel.builder()
					.accountNo(1)
					.accountId("aaaaaaaa")
					.accountName("AAAAAAAA")
					.build();
			
			ArgumentCaptor<Account> cndAccountCaptor = ArgumentCaptor.forClass(Account.class);
			ArgumentCaptor<Account> targetAccountCaptor = ArgumentCaptor.forClass(Account.class);
			doReturn(0).when(accountMapper).update(cndAccountCaptor.capture(), targetAccountCaptor.capture());
			
			assertThrows(UpdateFailureException.class, () -> accountRepositoryImpl.update(accountModel));
			
			verify(accountMapper).update(any(Account.class), any(Account.class));
			Account cndAccountCapture = cndAccountCaptor.getValue();
			assertEquals(cndAccountCapture.getAccountNo(), 1);
			
			Account targetAccountCapture = targetAccountCaptor.getValue();
			assertEquals(targetAccountCapture.getCreatedBy(), null);
			assertEquals(targetAccountCapture.getCreatedAt(), null);
			assertEquals(targetAccountCapture.getUpdatedBy(), null);
			assertEquals(targetAccountCapture.getUpdatedAt(), null);
			assertEquals(targetAccountCapture.getIsDeleted(), null);
			assertEquals(targetAccountCapture.getAccountId(), "aaaaaaaa");
			assertEquals(targetAccountCapture.getAccountName(), "AAAAAAAA");
			assertEquals(targetAccountCapture.getPassword(), null);
			assertEquals(targetAccountCapture.getBirthdate(), LocalDate.of(1900, 1, 1));
			assertEquals(targetAccountCapture.getSexKbnCode(), "none");
			assertEquals(targetAccountCapture.getBirthplacePrefectureKbnCode(), "none");
			assertEquals(targetAccountCapture.getResidentPrefectureKbnCode(), "none");
			assertEquals(targetAccountCapture.getFreeMemo(), "");
			assertEquals(targetAccountCapture.getAuthorityKbnCode(), null);
			assertEquals(targetAccountCapture.getLastLoginDatetime(), OffsetDateTime.of(1900, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(9)));
			assertEquals(targetAccountCapture.getLoginFailureCount(), 0);
		}
	}
	
	@Nested
	@Order(5)
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class updateLoginFailureCount {
		@Test
		@Order(1)
		@DisplayName("正常系：Nullのパラメータを含むAccountModelでの更新")
		void updateLoginFailureCount_contain_null_parameter() throws UpdateFailureException {
			AccountModel accountModel = AccountModel.builder()
					.accountNo(1)
					.build();
			
			ArgumentCaptor<Account> cndAccountCaptor = ArgumentCaptor.forClass(Account.class);
			ArgumentCaptor<Account> targetAccountCaptor = ArgumentCaptor.forClass(Account.class);
			doReturn(1).when(accountMapper).update(cndAccountCaptor.capture(), targetAccountCaptor.capture());
			
			accountRepositoryImpl.updateLoginFailureCount(accountModel);
			
			verify(accountMapper).update(any(Account.class), any(Account.class));
			Account cndAccountCapture = cndAccountCaptor.getValue();
			assertEquals(cndAccountCapture.getAccountNo(), 1);
			
			Account targetAccountCapture = targetAccountCaptor.getValue();
			assertEquals(targetAccountCapture.getCreatedBy(), null);
			assertEquals(targetAccountCapture.getCreatedAt(), null);
			assertEquals(targetAccountCapture.getUpdatedBy(), null);
			assertEquals(targetAccountCapture.getUpdatedAt(), null);
			assertEquals(targetAccountCapture.getIsDeleted(), null);
			assertEquals(targetAccountCapture.getAccountId(), null);
			assertEquals(targetAccountCapture.getAccountName(), null);
			assertEquals(targetAccountCapture.getPassword(), null);
			assertEquals(targetAccountCapture.getBirthdate(), null);
			assertEquals(targetAccountCapture.getSexKbnCode(), null);
			assertEquals(targetAccountCapture.getBirthplacePrefectureKbnCode(), null);
			assertEquals(targetAccountCapture.getResidentPrefectureKbnCode(), null);
			assertEquals(targetAccountCapture.getFreeMemo(), null);
			assertEquals(targetAccountCapture.getAuthorityKbnCode(), null);
			assertEquals(targetAccountCapture.getLastLoginDatetime(), null);
			assertEquals(targetAccountCapture.getLoginFailureCount(), 0);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：Nullのパラメータを含まないAccountModelでの更新")
		void updateLoginFailureCount_not_contain_null_parameter() throws UpdateFailureException {
			AccountModel accountModel = AccountModel.builder()
					.accountNo(1)
					.loginFailureCount(2)
					.build();
			
			ArgumentCaptor<Account> cndAccountCaptor = ArgumentCaptor.forClass(Account.class);
			ArgumentCaptor<Account> targetAccountCaptor = ArgumentCaptor.forClass(Account.class);
			doReturn(1).when(accountMapper).update(cndAccountCaptor.capture(), targetAccountCaptor.capture());
			
			accountRepositoryImpl.updateLoginFailureCount(accountModel);
			
			verify(accountMapper).update(any(Account.class), any(Account.class));
			Account cndAccountCapture = cndAccountCaptor.getValue();
			assertEquals(cndAccountCapture.getAccountNo(), 1);
			
			Account targetAccountCapture = targetAccountCaptor.getValue();
			assertEquals(targetAccountCapture.getCreatedBy(), null);
			assertEquals(targetAccountCapture.getCreatedAt(), null);
			assertEquals(targetAccountCapture.getUpdatedBy(), null);
			assertEquals(targetAccountCapture.getUpdatedAt(), null);
			assertEquals(targetAccountCapture.getIsDeleted(), null);
			assertEquals(targetAccountCapture.getAccountId(), null);
			assertEquals(targetAccountCapture.getAccountName(), null);
			assertEquals(targetAccountCapture.getPassword(), null);
			assertEquals(targetAccountCapture.getBirthdate(), null);
			assertEquals(targetAccountCapture.getSexKbnCode(), null);
			assertEquals(targetAccountCapture.getBirthplacePrefectureKbnCode(), null);
			assertEquals(targetAccountCapture.getResidentPrefectureKbnCode(), null);
			assertEquals(targetAccountCapture.getFreeMemo(), null);
			assertEquals(targetAccountCapture.getAuthorityKbnCode(), null);
			assertEquals(targetAccountCapture.getLastLoginDatetime(), null);
			assertEquals(targetAccountCapture.getLoginFailureCount(), 2);
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：UpdateFailureExceptionをthrowする")
		void updateLoginFailureCount_UpdateFailureException() {
			AccountModel accountModel = AccountModel.builder()
					.accountNo(1)
					.build();
			
			ArgumentCaptor<Account> cndAccountCaptor = ArgumentCaptor.forClass(Account.class);
			ArgumentCaptor<Account> targetAccountCaptor = ArgumentCaptor.forClass(Account.class);
			doReturn(0).when(accountMapper).update(cndAccountCaptor.capture(), targetAccountCaptor.capture());
			
			assertThrows(UpdateFailureException.class, () -> accountRepositoryImpl.updateLoginFailureCount(accountModel));
			
			verify(accountMapper).update(any(Account.class), any(Account.class));
			Account cndAccountCapture = cndAccountCaptor.getValue();
			assertEquals(cndAccountCapture.getAccountNo(), 1);
			
			Account targetAccountCapture = targetAccountCaptor.getValue();
			assertEquals(targetAccountCapture.getCreatedBy(), null);
			assertEquals(targetAccountCapture.getCreatedAt(), null);
			assertEquals(targetAccountCapture.getUpdatedBy(), null);
			assertEquals(targetAccountCapture.getUpdatedAt(), null);
			assertEquals(targetAccountCapture.getIsDeleted(), null);
			assertEquals(targetAccountCapture.getAccountId(), null);
			assertEquals(targetAccountCapture.getAccountName(), null);
			assertEquals(targetAccountCapture.getPassword(), null);
			assertEquals(targetAccountCapture.getBirthdate(), null);
			assertEquals(targetAccountCapture.getSexKbnCode(), null);
			assertEquals(targetAccountCapture.getBirthplacePrefectureKbnCode(), null);
			assertEquals(targetAccountCapture.getResidentPrefectureKbnCode(), null);
			assertEquals(targetAccountCapture.getFreeMemo(), null);
			assertEquals(targetAccountCapture.getAuthorityKbnCode(), null);
			assertEquals(targetAccountCapture.getLastLoginDatetime(), null);
			assertEquals(targetAccountCapture.getLoginFailureCount(), 0);
		}
	}
	
	@Nested
	@Order(6)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class isExistAccount {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウントが存在する場合")
		void isExistAccount_true() {
			ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
			doReturn(true).when(accountMapper).isExistAccount(accountCaptor.capture());
			
			assertTrue(accountRepositoryImpl.isExistAccount(1, "aaaaaaaa"));
			verify(accountMapper, times(1)).isExistAccount(any(Account.class));
			
			Account accountCapture = accountCaptor.getValue();
			assertEquals(accountCapture.getAccountNo(), 1);
			assertEquals(accountCapture.getAccountId(), "aaaaaaaa");
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：アカウントが存在しない場合")
		void isExistAccount_false() {
			ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
			doReturn(false).when(accountMapper).isExistAccount(accountCaptor.capture());
			
			assertFalse(accountRepositoryImpl.isExistAccount(1, "aaaaaaaa"));
			verify(accountMapper, times(1)).isExistAccount(any());
			
			Account accountCapture = accountCaptor.getValue();
			assertEquals(accountCapture.getAccountNo(), 1);
			assertEquals(accountCapture.getAccountId(), "aaaaaaaa");
		}
	}
	
	@Nested
	@Order(7)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class getAccountList {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウントを2件以上取得")
		void getAccountList_found_some_accounts() {
			
			Account account1 = Account.builder()
					.accountNo(1)
					.createdBy(1)
					.createdAt(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.updatedBy(1)
					.updatedAt(OffsetDateTime.of(2001, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.isDeleted(false)
					.accountId("aaaaaaaa")
					.accountName("AAAAAAAA")
					.password("$2a$10$password1")
					.birthdate(LocalDate.of(1991, 1, 1))
					.sexKbnCode("man")
					.birthplacePrefectureKbnCode("Hokkaido")
					.residentPrefectureKbnCode("Aomori")
					.freeMemo("よろしく")
					.authorityKbnCode("mini-user")
					.lastLoginDatetime(OffsetDateTime.of(2001, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.loginFailureCount(0)
					.build();
			Account account2 = Account.builder()
					.accountNo(2)
					.createdBy(2)
					.createdAt(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.updatedBy(2)
					.updatedAt(OffsetDateTime.of(2001, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.isDeleted(false)
					.accountId("bbbbbbbb")
					.accountName("BBBBBBBB")
					.password("$2a$10$password2")
					.birthdate(LocalDate.of(1991, 2, 1))
					.sexKbnCode("woman")
					.birthplacePrefectureKbnCode("Iwate")
					.residentPrefectureKbnCode("Okinawa")
					.freeMemo("お願いします")
					.authorityKbnCode("administrator")
					.lastLoginDatetime(OffsetDateTime.of(2002, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
					.loginFailureCount(1)
					.build();
			
			List<Account> accountList = new ArrayList<Account>();
			accountList.add(account1);
			accountList.add(account2);
			
			doReturn(accountList).when(accountMapper).select(any(Account.class));

			List<AccountModel> actual = accountRepositoryImpl.getAccountList();
			
			AccountModel actualAccountModel1 = actual.stream().sorted(Comparator.comparing(AccountModel::getAccountNo)).toList().getFirst();
			assertEquals(actualAccountModel1.getAccountNo(), 1);
			assertEquals(actualAccountModel1.getAccountId(), "aaaaaaaa");
			assertEquals(actualAccountModel1.getAccountName(), "AAAAAAAA");
			assertEquals(actualAccountModel1.getPassword(), "$2a$10$password1");
			assertEquals(actualAccountModel1.getBirthdate(), LocalDate.of(1991, 1, 1));
			assertEquals(actualAccountModel1.getSexKbnCode(), "man");
			assertEquals(actualAccountModel1.getBirthplacePrefectureKbnCode(), "Hokkaido");
			assertEquals(actualAccountModel1.getResidentPrefectureKbnCode(), "Aomori");
			assertEquals(actualAccountModel1.getFreeMemo(), "よろしく");
			assertEquals(actualAccountModel1.getAuthorityKbnCode(), "mini-user");
			assertEquals(actualAccountModel1.getLastLoginDatetime(), OffsetDateTime.of(2001, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)));
			assertEquals(actualAccountModel1.getLoginFailureCount(), 0);
			
			AccountModel actualAccountModel2 = actual.stream().sorted(Comparator.comparing(AccountModel::getAccountNo)).toList().getLast();
			assertEquals(actualAccountModel2.getAccountNo(), 2);
			assertEquals(actualAccountModel2.getAccountId(), "bbbbbbbb");
			assertEquals(actualAccountModel2.getAccountName(), "BBBBBBBB");
			assertEquals(actualAccountModel2.getPassword(), "$2a$10$password2");
			assertEquals(actualAccountModel2.getBirthdate(), LocalDate.of(1991, 2, 1));
			assertEquals(actualAccountModel2.getSexKbnCode(), "woman");
			assertEquals(actualAccountModel2.getBirthplacePrefectureKbnCode(), "Iwate");
			assertEquals(actualAccountModel2.getResidentPrefectureKbnCode(), "Okinawa");
			assertEquals(actualAccountModel2.getFreeMemo(), "お願いします");
			assertEquals(actualAccountModel2.getAuthorityKbnCode(), "administrator");
			assertEquals(actualAccountModel2.getLastLoginDatetime(), OffsetDateTime.of(2002, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)));
			assertEquals(actualAccountModel2.getLoginFailureCount(), 1);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：アカウントが0件")
		void getAccountList_not_found() {
			List<Account> accountList = new ArrayList<Account>();
			
			doReturn(accountList).when(accountMapper).select(any(Account.class));

			List<AccountModel> actual = accountRepositoryImpl.getAccountList();
			assertEquals(actual, accountList);
		}
	}
}