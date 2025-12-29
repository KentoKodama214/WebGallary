package com.web.gallary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.web.gallary.enumuration.ErrorValueEnum;

import lombok.Getter;

/**
 * 登録失敗時のExceptionクラス
 */
@ResponseStatus(HttpStatus.CONFLICT)
@Getter
public class RegistFailureException extends Exception {
	/** エラーコード */
	private final String errorCode;
		
	public RegistFailureException(ErrorValueEnum error) {
		super(error.getErrorMessage());
		this.errorCode = error.getErrorCode();
	}
}