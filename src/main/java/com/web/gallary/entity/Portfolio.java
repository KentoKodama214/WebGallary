package com.web.gallary.entity;

import java.time.OffsetDateTime;

import lombok.Builder;
import lombok.Data;

/**
 * ポートフォリオテーブルのEntityクラス
 */
@Data
@Builder
public class Portfolio {
	/** アカウント. */
	private Integer accountNo;

	/** 写真番号 */
	private Integer photoNo;

	/** 作成日時 */
	private OffsetDateTime createdAt;

	/** 並び順 */
	private Integer sortOrder;
}