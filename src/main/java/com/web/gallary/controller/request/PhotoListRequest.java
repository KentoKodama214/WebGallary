package com.web.gallary.controller.request;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.web.gallary.constant.Consts;
import com.web.gallary.enumuration.SortPhotoEnum;

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
	@JsonSetter(nulls = Nulls.SKIP)
	private String directionKbnCode = Consts.STRING_EMPTY;
	
	/** お気に入り写真のみ */
	@JsonSetter(nulls = Nulls.SKIP)
	private Boolean isFavorite = Boolean.FALSE;
	
	/** タグリスト */
	private String tagList;
	
	/** 
	 * 並び順
	 * <p>
	 * {@link SortPhotoEnum}
	 */
	@JsonSetter(nulls = Nulls.SKIP)
	private SortPhotoEnum sortBy = SortPhotoEnum.PHOTO_AT;
	
	/** ページ番号 */
	@JsonSetter(nulls = Nulls.SKIP)
	private Integer pageNo = 1;
}