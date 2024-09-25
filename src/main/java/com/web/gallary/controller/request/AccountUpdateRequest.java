package com.web.gallary.controller.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * アカウント更新時のリクエストパラメータを保持するクラス
 */
@Data
public class AccountUpdateRequest {
	/** アカウントID	<br>
	 *	半角英数8〜16桁、ブランクなし
	 */
	@NotBlank(message = "{validation.common.notBlank}")
	@Size(min = 8, max = 16, message = "{validation.common.min_max_length}")
	@Pattern(regexp = "[a-zA-Z0-9]{8,16}", message = "{validation.common.pattern}")
	private String accountId;
	
	/** アカウント名<br>
	 *	ブランクなし
	 */
	@NotBlank(message = "{validation.common.notBlank}")
	@Pattern(regexp = "[^　]+", message = "{validation.common.all_space}")
	private String accountName;

	/** 新しいパスワード<br>
	 *	半角英数8桁以上、ブランクなし
	 */
	@NotBlank(message = "{validation.common.notBlank}")
	@Size(min = 8, message = "{validation.common.min_length}")
	@Pattern(regexp = "[a-zA-Z0-9]{8,}", message = "{validation.common.pattern}")
	private String newPassword;
    
    /** 生年月日 <br>
     * 	yyyy-mm-ddで、過去日付
     */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past(message = "{validation.common.pastDate}")
	private LocalDate birthdate;

	/** 性別区分コード<br>
	 * 	man: 男性　　woman: 女性
	 */
	private String sexKbnCode;

	/** 出身都道府県区分コード */
	private String birthplacePrefectureKbnCode;

	/** 在住都道府県区分コード */
	private String residentPrefectureKbnCode;

	/** フリーメモ */
	private String freeMemo;
}