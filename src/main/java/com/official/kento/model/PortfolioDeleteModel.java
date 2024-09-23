package com.official.kento.model;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

/**
 * ポートフォリオを削除するときの情報を受け渡すためのModelクラス
 */
@Value
@Builder
public class PortfolioDeleteModel {
	/** アカウント番号 */
	@NonNull
	private Integer accountNo;
	
	/** 写真番号 */
	@NonNull
	private Integer photoNo;
}