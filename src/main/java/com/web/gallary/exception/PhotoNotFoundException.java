package com.web.gallary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.web.gallary.enumuration.ErrorValues;

import lombok.Getter;

/**
 * 写真が存在しない時のExceptionクラス
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
public class PhotoNotFoundException extends Exception {
	/** エラーコード */
	private final String errorCode;
	
	public PhotoNotFoundException(ErrorValues error) {
        super(error.getErrorMessage());
        this.errorCode = error.getErrorCode();
    }
}