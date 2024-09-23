package com.official.kento.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.official.kento.controller.request.AccountRegistRequest;
import com.official.kento.controller.request.AccountUpdateRequest;
import com.official.kento.controller.request.ErrorRequest;
import com.official.kento.controller.response.AccountRegistResponse;
import com.official.kento.controller.response.AccountUpdateResponse;
import com.official.kento.enumuration.ErrorValues;
import com.official.kento.exception.BadRequestException;
import com.official.kento.exception.RegistFailureException;
import com.official.kento.exception.UpdateFailureException;
import com.official.kento.helper.SessionHelper;
import com.official.kento.model.AccountModel;
import com.official.kento.service.impl.AccountServiceImpl;

import lombok.RequiredArgsConstructor;

/**
 * アカウントに関するAPI通信を扱うRestControllerクラス
 * @author	Kento Kodama
 * @version	1.0.0
 * @since	1.0.0
*/
@RestController
@RequiredArgsConstructor
public class AccountRestController {
	
    private final AccountServiceImpl accountServiceImpl;
    private final SessionHelper sessionHelper;
    private final PasswordEncoder passwordEncoder;
    
    /**
     * アカウント登録
     * @param	accuontRegistRequest	{@link AccountRegistRequest}
     * @param	result					AccountRegistRequestのバインディング結果
     * @return							{@link AccountRegistResponse}
     * @throws	BadRequestException 	リクエストパラメータが不正の場合
     * @throws	RegistFailureException 	一意制約違反でアカウントの登録に失敗した場合
     */
    @PostMapping("/register")
    public ResponseEntity<AccountRegistResponse> register(
    		@RequestBody @Validated AccountRegistRequest accuontRegistRequest, 
    		BindingResult result) throws BadRequestException, RegistFailureException {
    	
    	if(result.hasErrors()) {
    		throw new BadRequestException(ErrorValues.EC0000);
    	}
    	
        AccountModel accountModel = AccountModel.builder()
        		.accountId(accuontRegistRequest.getAccountId())
        		.accountName(accuontRegistRequest.getAccountName())
        		.password(accuontRegistRequest.getPassword())
        		.birthdate(accuontRegistRequest.getBirthdate())
        		.sexKbnCode(accuontRegistRequest.getSexKbnCode())
        		.birthplacePrefectureKbnCode(accuontRegistRequest.getBirthplacePrefectureKbnCode())
        		.residentPrefectureKbnCode(accuontRegistRequest.getResidentPrefectureKbnCode())
        		.freeMemo(accuontRegistRequest.getFreeMemo())
        		.loginFailureCount(0)
        		.build();
        
        Boolean isSuccess = accountServiceImpl.registAccount(accountModel);
        return ResponseEntity.ok(AccountRegistResponse.builder()
        			.httpStatus(HttpStatus.OK.value())
        			.isSuccess(isSuccess)
        			.message("")
        			.build());
    }
    
    /**
     * アカウント更新
     * @param	accountUpdateRequest	{@link AccountUpdateRequest}
     * @param	result					AccountUpdateRequestのバインディング結果
     * @return							{@link AccountUpdateResponse}
     * @throws	BadRequestException		リクエストパラメータが不正の場合
     * @throws	UpdateFailureException	更新に失敗した場合
     */
    @PostMapping("/update")
    public ResponseEntity<AccountUpdateResponse> update(
    		@RequestBody @Validated AccountUpdateRequest accountUpdateRequest, 
    		BindingResult result) throws BadRequestException, UpdateFailureException {
    	
    	if(result.hasErrors()) {
    		List<String> fieldList
    			= result.getFieldErrors().stream().map(error -> error.getField()).distinct().toList();
    		
    		if(!(fieldList.size() == 1 && 
    			fieldList.getFirst().equals("newPassword") && 
    			accountUpdateRequest.getNewPassword().isEmpty())) {
    				// 新しいパスワードが空欄で他にパラメータ不正がない場合は、スキップ
    				// 新しいパスワード以外や新しいパスワードの入力に不正がある場合は、例外
    				throw new BadRequestException(ErrorValues.EC0000);
    		}
    	}
    	
    	AccountModel accountModel = AccountModel.builder()
    			.accountNo(sessionHelper.getAccountNo())
        		.accountId(accountUpdateRequest.getAccountId())
        		.accountName(accountUpdateRequest.getAccountName())
        		.password(accountUpdateRequest.getNewPassword().isEmpty() ? null : accountUpdateRequest.getNewPassword())
        		.birthdate(accountUpdateRequest.getBirthdate())
        		.sexKbnCode(accountUpdateRequest.getSexKbnCode())
        		.birthplacePrefectureKbnCode(accountUpdateRequest.getBirthplacePrefectureKbnCode())
        		.residentPrefectureKbnCode(accountUpdateRequest.getResidentPrefectureKbnCode())
        		.freeMemo(accountUpdateRequest.getFreeMemo())
        		.build();
    	
    	Boolean isDuplicateAccountId = accountServiceImpl.updateAccount(accountModel);
    	
    	return ResponseEntity.ok(AccountUpdateResponse.builder()
    				.httpStatus(HttpStatus.OK.value())
    				.isDuplicateAccountId(isDuplicateAccountId)
    				.isAccountIdChanged(!accountUpdateRequest.getAccountId().equals(sessionHelper.getAccountId()))
    				.isPasswordChanged(!accountUpdateRequest.getNewPassword().isEmpty())
    				.message("")
    				.build());
    }
    
    /**
     * アカウント登録に失敗した時のExceptionHandler
     * @param	exception	{@link RegistFailureException}
     * @return				{@link ErrorRequest}
     */
    @ExceptionHandler(RegistFailureException.class)
    public ResponseEntity<ErrorRequest> handleInsertFailedException(RegistFailureException exception) {
    	ErrorRequest errorResponse = ErrorRequest.builder()
    			.httpStatus(HttpStatus.CONFLICT.value())
    			.errorCode(exception.getErrorCode())
    			.errorMessage(exception.getMessage())
    			.goBackPageUrl("/register").build();
    	
    	return new ResponseEntity<ErrorRequest>(errorResponse, HttpStatus.CONFLICT);
    }
}