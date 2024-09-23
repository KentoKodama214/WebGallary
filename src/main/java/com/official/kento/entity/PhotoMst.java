package com.official.kento.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Builder;
import lombok.Data;

/**
 * 写真マスタテーブルのEntityクラス
 */
@Data
@Builder
public class PhotoMst {
	/** アカウント番号 */
	private Integer accountNo;

	/** 写真番号 */
	private Integer photoNo;

	/** 作成者 */
	private Integer createdBy;

	/** 作成日時 */
	private OffsetDateTime createdAt;

	/** 更新者 */
	private Integer updatedBy;

	/** 更新日時 */
	private OffsetDateTime updatedAt;

	/** 削除フラグ */
	private Boolean isDeleted;

	/** 撮影日時 */
	private OffsetDateTime photoAt;

	/** ロケーション番号 */
	private Integer locationNo;

	/** 画像ファイルパス */
	private String imageFilePath;

	/** 写真タイトル日本語名 */
	private String photoJapaneseTitle;

	/** 写真タイトル英語名 */
	private String photoEnglishTitle;

	/** キャプション */
	private String caption;

	/** 向き区分コード<br>
	 * 	vertical: 縦　　horizontal: 横
	 */
	private String directionKbnCode;

	/** 焦点距離 */
	private Integer focalLength;

	/** F値 */
	private BigDecimal fValue;

	/** シャッタースピード */
	private BigDecimal shutterSpeed;

	/** ISO */
	private Integer iso;
}