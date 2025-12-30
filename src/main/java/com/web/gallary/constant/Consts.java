package com.web.gallary.constant;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * デフォルト値を管理するクラス
 */
public final class Consts {
	// String
	/** 空文字（""） */
	public static final String STRING_EMPTY = "";
	/** 半角スペース */
	public static final String HALF_SPACE = " ";
	/** 全角スペース */
	public static final String FULL_SPACE = "　";
	/** none */
	public static final String STRING_NONE = "none";
	/** mini-user */
	public static final String AUTHORITY_KBN_CODE = "mini-user";
	
	// ZoneOffset
	public static final ZoneOffset JST = ZoneOffset.ofHours(9);
	
	// LocalDate
	/** 1900-01-01 */
	public static final LocalDate MIN_LOCAL_DATE = LocalDate.of(1900, 1, 1);
	
	// OffsetDateTime
	/** 1900-01-01 00:00:00 (+9:00) */
	public static final OffsetDateTime MIN_OFFSET_DATE_TIME = OffsetDateTime.of(1900, 1, 1, 0, 0, 0, 0, JST);
}