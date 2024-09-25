package com.web.gallary.model;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

/**
 * ポートフォリオの情報を受け渡すためのModelクラス
 */
@Value
@Builder
public class PortfolioModel {
	/** アカウント番号 */
	@NonNull
	private Integer accountNo;
	
	/** 写真番号 */
	@NonNull
	private Integer photoNo;
	
	/** 並び順 */
	@NonNull
	private Integer sortOrder;
}