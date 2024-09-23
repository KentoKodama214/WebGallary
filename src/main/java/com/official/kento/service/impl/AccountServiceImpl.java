package com.official.kento.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.official.kento.AccountPrincipal;
import com.official.kento.config.LoginConfig;
import com.official.kento.entity.Account;
import com.official.kento.exception.RegistFailureException;
import com.official.kento.exception.UpdateFailureException;
import com.official.kento.model.AccountModel;
import com.official.kento.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

/**
 * アカウントに関するビジネスロジックを行うServiceの実装クラス
 */
@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements UserDetailsService {

	private final AccountRepository accountRepository;
	private final LoginConfig loginConfig;
	
	/**
	 * アカウントIDからアカウント情報の存在を確認する
	 * @param	username					アカウントID
	 * @return								{@link UserDetails}
	 * @throws	UsernameNotFoundException	ユーザーが存在しない場合
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.getByAccountId(username);
		
		if (Objects.isNull(account)) {
			throw new UsernameNotFoundException("User not found");
		}
		return new AccountPrincipal(account, loginConfig.getFailCount());
	}
	
	/**
	 * アカウントを新規登録する
	 * @param	accountModel			{@link AccountModel}
	 * @return							登録に成功した場合、true
	 * @throws	RegistFailureException	登録に失敗した場合
	 */
	public Boolean registAccount(AccountModel accountModel) throws RegistFailureException {
		Boolean isExist = accountRepository.isExistAccount(null, accountModel.getAccountId());
		if(!isExist) accountRepository.regist(accountModel);
		
		return !isExist;
	}
	
	/**
	 * アカウントを更新する
	 * @param	accountModel			{@link AccountModel}
	 * @return							更新に成功した場合、true
	 * @throws UpdateFailureException	更新に失敗した場合
	 */
	public Boolean updateAccount(AccountModel accountModel) throws UpdateFailureException {
		Boolean isExist = accountRepository.isExistAccount(accountModel.getAccountNo(), accountModel.getAccountId());
		if(!isExist) accountRepository.update(accountModel);
		return isExist;
	}
	
	/**
	 * アカウントIDからアカウント情報を取得する
	 * @param	accountId	アカウントID
	 * @return				{@link Account}
	 */
	public Account getAccountById(String accountId) {
		return accountRepository.getByAccountId(accountId);
	}
	
	/**
	 * アカウントの一覧を取得する
	 * @return	{@link AccountModel}
	 */
	public List<AccountModel> getAccountList() {
		return accountRepository.getAccountList().stream().sorted(Comparator.comparing(AccountModel::getAccountId)).toList();
	}
	
	/**
	 * 認証成功
	 * @param	event					{@link AuthenticationSuccessEvent}
	 * @throws	UpdateFailureException	更新に失敗した場合
	 */
	@EventListener
	public void handle(AuthenticationSuccessEvent event) throws UpdateFailureException {
		Account account = accountRepository.getByAccountId(event.getAuthentication().getName());
		
		AccountModel accountModel = AccountModel.builder()
				.accountNo(account.getAccountNo())
				.loginFailureCount(0)
				.build();
		accountRepository.updateLoginFailureCount(accountModel);
	}
	
	/**
	 * 認証失敗
	 * @param	event					{@link AuthenticationFailureBadCredentialsEvent}
	 * @throws	UpdateFailureException	更新に失敗した場合
	 */
	@EventListener
	public void handle(AuthenticationFailureBadCredentialsEvent event) throws UpdateFailureException {
		Account account = accountRepository.getByAccountId(event.getAuthentication().getName());
		
		if(!Objects.isNull(account)) {
			AccountModel accountModel = AccountModel.builder()
					.accountNo(account.getAccountNo())
					.loginFailureCount(account.getLoginFailureCount() + 1)
					.build();
			accountRepository.updateLoginFailureCount(accountModel);
		}
	}
}