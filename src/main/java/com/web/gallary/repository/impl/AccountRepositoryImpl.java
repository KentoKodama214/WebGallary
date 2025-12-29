package com.web.gallary.repository.impl;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.web.gallary.entity.Account;
import com.web.gallary.enumuration.ErrorValueEnum;
import com.web.gallary.exception.RegistFailureException;
import com.web.gallary.exception.UpdateFailureException;
import com.web.gallary.mapper.AccountMapper;
import com.web.gallary.model.AccountModel;
import com.web.gallary.repository.AccountRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * アカウントデータを永続化するRepositoryの実装クラス
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

	private final AccountMapper accountMapper;
	private final PasswordEncoder passwordEncoder;
	
	/**
	 * Accountテーブルで該当するレコードを取得する
	 * @param	accountNo	アカウント番号
	 * @return	Account		{@link Account}<br>
	 * 						取得できない場合はnullを返す
	 */
	@Override
	public Account getByAccountNo(Integer accountNo) {
		Account account = Account.builder()
				.accountNo(accountNo)
				.build();
		
		List<Account> accountList = accountMapper.select(account);
		
		return accountList.isEmpty() ? null : accountList.getFirst();
	}
	
	/**
	 * Accountテーブルで該当するレコードを取得する
	 * @param	accountId	アカウントId
	 * @return	Account		{@link Account}<br>
	 * 						取得できない場合はnullを返す
	 */
	@Override
	public Account getByAccountId(String accountId) {
		Account account = Account.builder()
				.accountId(accountId)
				.build();
		
		List<Account> accountList = accountMapper.select(account);
		
		return accountList.isEmpty() ? null : accountList.getFirst();
	}

	/**
	 * Accountテーブルへ登録する
	 * @param	accountModel			{@link AccountModel}
	 * @throws	RegistFailureException	登録に失敗した場合
	 */
	@Override
	public void regist(AccountModel accountModel) throws RegistFailureException {
		Account account = Account.builder()
			.createdBy(0)
			.updatedBy(0)
			.accountId(accountModel.getAccountId())
			.accountName(accountModel.getAccountName())
			.password(passwordEncoder.encode(accountModel.getPassword()))
			.birthdate(
					Optional.ofNullable(accountModel.getBirthdate()).orElse(LocalDate.of(1900, 1, 1)))
			.sexKbnCode(
					Optional.ofNullable(accountModel.getSexKbnCode()).orElse("none"))
			.birthplacePrefectureKbnCode(
					Optional.ofNullable(accountModel.getBirthplacePrefectureKbnCode()).orElse("none"))
			.residentPrefectureKbnCode(
					Optional.ofNullable(accountModel.getResidentPrefectureKbnCode()).orElse("none"))
			.freeMemo(
					Optional.ofNullable(accountModel.getFreeMemo()).orElse(""))
			.authorityKbnCode("mini-user")
			.lastLoginDatetime(OffsetDateTime.of(1900, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(9)))
			.loginFailureCount(0)
			.build();
		
		try {
			accountMapper.insert(account);
		}
		catch (DuplicateKeyException e) {
			log.error("Account: Duplicate Key (AccountId: "  + accountModel.getAccountId() + ")");
			throw new RegistFailureException(ErrorValueEnum.FAIL_TO_REGIST_ACCOUNT);
		}
	}

	/**
	 * Accountテーブルで該当するレコードを更新する
	 * @param	accountModel			{@link AccountModel}
	 * @throws	UpdateFailureException	更新に失敗した場合 
	 */
	@Override
	public void update(AccountModel accountModel) throws UpdateFailureException {
		Account cndAccount = Account.builder().accountNo(accountModel.getAccountNo()).build();
		
		Account targetAccount = Account.builder()
				.accountId(accountModel.getAccountId())
				.accountName(accountModel.getAccountName())
				.birthdate(
					Optional.ofNullable(accountModel.getBirthdate()).orElse(LocalDate.of(1900, 1, 1)))
				.sexKbnCode(
					Optional.ofNullable(accountModel.getSexKbnCode()).orElse("none"))
				.birthplacePrefectureKbnCode(
					Optional.ofNullable(accountModel.getBirthplacePrefectureKbnCode()).orElse("none"))
				.residentPrefectureKbnCode(
					Optional.ofNullable(accountModel.getResidentPrefectureKbnCode()).orElse("none"))
				.freeMemo(
					Optional.ofNullable(accountModel.getFreeMemo()).orElse(""))
				.lastLoginDatetime(
					Optional.ofNullable(accountModel.getLastLoginDatetime())
						.orElse(OffsetDateTime.of(1900, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(9))))
				.loginFailureCount(
						Optional.ofNullable(accountModel.getLoginFailureCount()).orElse(0))
				.build();
		
		if(!Objects.isNull(accountModel.getPassword())) {
			targetAccount.setPassword(passwordEncoder.encode(accountModel.getPassword()));
		}
		
		if (accountMapper.update(cndAccount, targetAccount) < 1) {
			log.error("Account: Update Failed(AccountNo: "  + accountModel.getAccountNo() + ")");
			throw new UpdateFailureException(ErrorValueEnum.FAIL_TO_UPDATE_ACCOUNT);
		}
	}
	
	/**
	 * Accountテーブルのログイン失敗回数を更新する
	 * @param	accountModel			{@link AccountModel}
	 * @throws	UpdateFailureException	更新に失敗した場合
	 */
	@Override
	public void updateLoginFailureCount(AccountModel accountModel) throws UpdateFailureException {
		Account cndAccount = Account.builder().accountNo(accountModel.getAccountNo()).build();
		
		Account targetAccount = Account.builder()
				.lastLoginDatetime(accountModel.getLastLoginDatetime())
				.loginFailureCount(Optional.ofNullable(accountModel.getLoginFailureCount()).orElse(0))
				.build();
		
		if (accountMapper.update(cndAccount, targetAccount) < 1) {
			log.error("Account: Update Failed(AccountNo: "  + accountModel.getAccountNo() + ")");
			throw new UpdateFailureException(ErrorValueEnum.FAIL_TO_UPDATE_ACCOUNT);
		}
	}
	
	/**
	 * アカウントIDに該当するアカウントの存在有無をチェックする
	 * @param	accountNo	検索対象外のアカウント番号
	 * @param	accountId	アカウントID
	 * @return				true：存在する
	 */
	@Override
	public Boolean isExistAccount(Integer accountNo, String accountId) {
		Account account =  Account.builder().accountNo(accountNo).accountId(accountId).build();
		return accountMapper.isExistAccount(account);
	}
	
	/**
	 * アカウントの一覧を取得する
	 * @return	{@link AccountModel}
	 */
	@Override
	public List<AccountModel> getAccountList() {
		Account account = Account.builder().isDeleted(false).build();
		
		List<Account> accountList = accountMapper.select(account);
		
		List<AccountModel> accountModelList
			= accountList.stream().map(accountData -> AccountModel.builder()
					.accountNo(accountData.getAccountNo())
					.accountId(accountData.getAccountId())
					.accountName(accountData.getAccountName())
					.password(accountData.getPassword())
					.birthdate(accountData.getBirthdate())
					.sexKbnCode(accountData.getSexKbnCode())
					.birthplacePrefectureKbnCode(accountData.getBirthplacePrefectureKbnCode())
					.residentPrefectureKbnCode(accountData.getResidentPrefectureKbnCode())
					.freeMemo(accountData.getFreeMemo())
					.authorityKbnCode(accountData.getAuthorityKbnCode())
					.lastLoginDatetime(accountData.getLastLoginDatetime())
					.loginFailureCount(accountData.getLoginFailureCount())
					.build()).toList();
		
		return accountModelList;
	}
}