package com.official.kento.controller.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.Data;

/**
 * 写真保存時のリクエストパラメータを保持するクラス
 */
@Data
public class PhotoSaveRequest {
	/** アカウント番号 */
	@NotNull(message = "{validation.common.notBlank}")
	@Positive(message = "{validation.common.positive}")
	private Integer accountNo;
	
	/** 写真番号 */
	@Positive(message = "{validation.common.positive}")
	private Integer photoNo;

	/** お気に入り数 */
	private Integer favoriteCount;
	
	/** お気に入り */
	private Boolean isFavorite;
	
	/** 撮影日時 */
	@Past(message = "{validation.common.pastDate}")
	private LocalDateTime photoAt;
	
	/** ロケーション番号 */
	private Integer locationNo;
	
	/** 住所 */
	private String address;

	/** 緯度 */
	private BigDecimal latitude;

	/** 経度 */
	private BigDecimal longitude;
	
	/** ロケーション名 */
	private String locationName;
	
	/** 画像ファイル */
	private MultipartFile imageFile;
	
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
	@NotBlank(message = "{validation.file.notFound}")
	private String directionKbnCode;

	/** 焦点距離 */
	@Positive(message = "{validation.common.positive}")
	private Integer focalLength;

	/** F値 */
	@Positive(message = "{validation.common.positive}")
	private BigDecimal fValue;

	/** シャッタースピード */
	@Positive(message = "{validation.common.positive}")
	private BigDecimal shutterSpeed;

	/** ISO */
	@Positive(message = "{validation.common.positive}")
	private Integer iso;
	
	/** 写真タグリスト */
	@Valid
	private List<PhotoTagSaveRequest> photoTagRegistRequestList;
}