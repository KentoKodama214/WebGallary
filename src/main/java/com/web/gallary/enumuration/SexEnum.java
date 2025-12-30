package com.web.gallary.enumuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.web.gallary.constant.Consts;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 性別を管理するEnumクラス
 */
@Getter
@AllArgsConstructor
public enum SexEnum {
	/** 未選択 */
	@JsonProperty(Consts.STRING_NONE)
	NONE(Consts.STRING_NONE),
	/** 男性 */
	@JsonProperty("man")
	MAN("man"),
	/** 女性 */
	@JsonProperty("woman")
	WOMAN("woman");
	
	/** コード */
	private final String code;
	
	/**
	 * 名称からEnumを取得する<p>
	 * 該当するEnumがなければ、デフォルトでNONEを返す
	 * 
	 * @param value 値
	 * @return {@link SexEnum}
	 */
	public static SexEnum getOrDefault(String value) {
        try {
            String upperValue = value.toUpperCase();
            return SexEnum.valueOf(upperValue);
        } catch (IllegalArgumentException | NullPointerException e) {
            return SexEnum.NONE;
        }
    }
}