package com.web.gallary.controller.request;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PhotoFavoriteDeleteRequest {
	/** お気に入り写真アカウント番号 */
	@Positive(message = "{validation.common.positive}")
	private Integer favoritePhotoAccountNo;
	
	/** お気に入り写真番号 */
	@Positive(message = "{validation.common.positive}")
	private Integer favoritePhotoNo;
}