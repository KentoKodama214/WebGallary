package com.web.gallary.model;

import java.util.List;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

/**
 * 写真の一覧を取得するために必要な情報を受け渡すためのModelクラス
 */
@Value
@Builder
public class PhotoListGetModel {
	/** ログイン中のアカウントNo */
	private Integer accountNo;
	
	/** 写真のアカウントID */
	@NonNull
	private String photoAccountId;
	
	/** 写真の向き<br>
	 * 	vertical: 縦　　horizontal: 横
	 */
	@NonNull
	private String directionKbnCode;
	
	/** お気に入り写真のみ */
	@NonNull
	private Boolean isFavoriteOnly;
	
	/** タグワードリスト */
	@NonNull
	private List<String> tagList;
	
	/** 並び順<br>
	 *	photoAt: 撮影日順　　favorite: お気に入り数順　　season: 季節順
	 */
	@NonNull
	private String sortBy;
}