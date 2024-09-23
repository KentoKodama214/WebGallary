package com.official.kento.entity;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import lombok.Builder;
import lombok.Data;

/**
 * アカウントテーブルのEntityクラス
 */
@Data
@Builder
public class Account {
	/** アカウント番号 */
	private Integer accountNo;

	/** 作成者 */
	private Integer createdBy;

	/** 作成日時 */
	private OffsetDateTime createdAt;

	/** 更新者 */
	private Integer updatedBy;

	/** 更新日時 */
	private OffsetDateTime updatedAt;

	/** 削除フラグ */
	private Boolean isDeleted;

	/** アカウントID */
	private String accountId;

	/** アカウント名 */
	private String accountName;

	/** パスワード */
	private String password;

	/** 生年月日 */
	private LocalDate birthdate;

	/** 性別区分コード<br>
	 *	man:男性　　woman:女性 
	 */
	private String sexKbnCode;

	/** 出身都道府県区分コード */
	private String birthplacePrefectureKbnCode;

	/** 在住都道府県区分コード */
	private String residentPrefectureKbnCode;

	/** フリーメモ */
	private String freeMemo;

	/** 権限区分コード */
	private String authorityKbnCode;

	/** 最終ログイン日時 */
	private OffsetDateTime lastLoginDatetime;
	
	/** ログイン失敗回数 */
	private Integer loginFailureCount;
}