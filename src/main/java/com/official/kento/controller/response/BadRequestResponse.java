package com.official.kento.controller.response;

import lombok.Builder;
import lombok.Data;

/**
 * パラメータ不正の時のレスポンスパラメータを保持するクラス
 */
@Data
@Builder
public class BadRequestResponse {
	/** HTTPステータス */
	private Integer httpStatus;
	
	/** 登録成功 */
	private Boolean isSuccess;

	/** メッセージ */
	private String message;
}