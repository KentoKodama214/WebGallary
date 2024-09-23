package com.official.kento.controller.response;

import lombok.Builder;
import lombok.Data;

/**
 * 写真一覧の写真のメタデータを含めた詳細情報のレスポンスパラメータを保持するクラス
 */
@Data
@Builder
public class PhotoListResponse {
	/** アカウント番号 */
	private Integer accountNo;
	
	/** 写真番号 */
	private Integer photoNo;

	/** お気に入り */
	private Boolean isFavorite;
	
	/** 画像ファイルパス */
	private String imageFilePath;
	
	/** キャプション */
	private String caption;
	
	/** 向き区分コード<br>
	 * 	vertical: 縦　　horizontal: 横
	 */
	private String directionKbnCode;
}