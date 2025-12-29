package com.web.gallary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.web.gallary.enumuration.ErrorValueEnum;

import lombok.Getter;

/**
 * 更新失敗時のExceptionクラス
 */
@ResponseStatus(HttpStatus.CONFLICT)
@Getter
public class UpdateFailureException extends Exception {
	/** エラーコード */
	private final String errorCode;
	
	public UpdateFailureException(ErrorValueEnum error) {
		super(error.getErrorMessage());
		this.errorCode = error.getErrorCode();
	}
}