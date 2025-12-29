package com.web.gallary.controller.request;

import lombok.Data;

/**
 * 写真一覧表示時のリクエストパラメータを保持するクラス
 */
@Data
public class PhotoListRequest {
	/** 
	 * 向き区分コード
	 * <p>
	 * vertical: 縦<p>
	 * horizontal: 横
	 */
	private String directionKbnCode;
	
	/** お気に入り写真のみ */
	private Boolean isFavorite;
	
	/** タグリスト */
	private String tagList;
	
	/** 
	 * 並び順
	 * <p>
	 * photoAt: 撮影日順<p>
	 * favorite: お気に入り数順<p>
	 * season: 季節順
	 */
	private String sortBy;
	
	/** ページ番号 */
	private Integer pageNo;
}