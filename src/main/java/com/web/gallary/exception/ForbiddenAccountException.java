package com.web.gallary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.web.gallary.enumuration.ErrorValues;

import lombok.Getter;

/**
 * 権限のないアカウントからの不正アクセスの時のExceptionクラス
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
@Getter
public class ForbiddenAccountException extends Exception {
	/** エラーコード */
	private final String errorCode;
	
	public ForbiddenAccountException(ErrorValues error) {
        super(error.getErrorMessage());
        this.errorCode = error.getErrorCode();
    }
}