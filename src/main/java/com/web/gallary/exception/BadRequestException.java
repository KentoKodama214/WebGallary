package com.web.gallary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.web.gallary.enumuration.ErrorValueEnum;

import lombok.Getter;

/**
 * リクエストパラメータ不正のExceptionクラス
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
public class BadRequestException extends Exception {
	/** エラーコード */
	private final String errorCode;
	
	public BadRequestException(ErrorValueEnum error) {
		super(error.getErrorMessage());
		this.errorCode = error.getErrorCode();
	}
}