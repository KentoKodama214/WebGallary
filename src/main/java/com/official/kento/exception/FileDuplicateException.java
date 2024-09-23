package com.official.kento.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.official.kento.enumuration.ErrorValues;

import lombok.Getter;

/**
 * 保存するファイルが重複した時のExceptionクラス
 */
@ResponseStatus(HttpStatus.CONFLICT)
@Getter
public class FileDuplicateException extends Exception {
	/** エラーコード */
	private final String errorCode;
	
	public FileDuplicateException(ErrorValues error) {
        super(error.getErrorMessage());
        this.errorCode = error.getErrorCode();
    }
}