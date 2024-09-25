package com.web.gallary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.web.gallary.enumuration.ErrorValues;

import lombok.Getter;

/**
 * 登録枚数の上限に達して写真が追加できない時のExceptionクラス
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
public class PhotoNotAdditableException extends Exception {
	/** エラーコード */
	private final String errorCode;
	
	public PhotoNotAdditableException(ErrorValues error) {
        super(error.getErrorMessage());
        this.errorCode = error.getErrorCode();
    }
}