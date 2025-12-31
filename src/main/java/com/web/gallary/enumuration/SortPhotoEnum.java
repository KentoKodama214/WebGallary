package com.web.gallary.enumuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 写真の表示順を管理するEnumクラス
 */
@Getter
@AllArgsConstructor
public enum SortPhotoEnum {
	/** 撮影日順 */
	@JsonProperty("photoAt")
	PHOTO_AT,
	/** お気に入り数順 */
	@JsonProperty("favorite")
	FAVORITE,
	/** 季節・時期順 */
	@JsonProperty("season")
	SEASON;
}