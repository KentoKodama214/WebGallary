package com.official.kento.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.official.kento.enumuration.ErrorValues;

import lombok.Getter;

/**
 * 登録失敗時のExceptionクラス
 */
@ResponseStatus(HttpStatus.CONFLICT)
@Getter
public class RegistFailureException extends Exception {
	/** エラーコード */
	private final String errorCode;
		
	public RegistFailureException(ErrorValues error) {
        super(error.getErrorMessage());
        this.errorCode = error.getErrorCode();
    }
}