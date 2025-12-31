package com.web.gallary.enumuration;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 写真の向きを管理するEnumクラス
 */
@Getter
@AllArgsConstructor
public enum DirectionEnum {
	/** 未選択 */
	NONE(""),
	/** 縦 */
	VERTICAL("vertical"),
	/** 横 */
	HORIZONTAL("horizontal"),
	/** 正方形 */
	SQUARE("square");
	
	/** コード */
	private final String code;
	
	/**
	 * 名称からEnumを取得する<p>
	 * 該当するEnumがなければ、デフォルトでNONEを返す
	 * 
	 * @param value 値
	 * @return {@link DirectionEnum}
	 */
	@JsonCreator
	public static DirectionEnum getOrDefault(String value) {
		return Arrays.stream(DirectionEnum.values())
				.filter(e -> e.getCode().equals(value))
				.findFirst()
				.orElse(DirectionEnum.NONE);
    }
}