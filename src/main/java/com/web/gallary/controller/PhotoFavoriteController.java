package com.web.gallary.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.gallary.controller.request.PhotoFavoriteDeleteRequest;
import com.web.gallary.controller.request.PhotoFavoriteRegistRequest;
import com.web.gallary.controller.response.PhotoFavoriteResponse;
import com.web.gallary.enumuration.ErrorValues;
import com.web.gallary.exception.BadRequestException;
import com.web.gallary.exception.RegistFailureException;
import com.web.gallary.exception.UpdateFailureException;
import com.web.gallary.helper.SessionHelper;
import com.web.gallary.model.PhotoFavoriteModel;
import com.web.gallary.service.PhotoFavoriteService;

import lombok.RequiredArgsConstructor;

/**
 * 写真のお気に入りの登録・解除を扱うRestControllerクラス
 * @author	Kento Kodama
 * @version	1.0.0
 * @since	1.0.0
*/
@RestController
@RequiredArgsConstructor
public class PhotoFavoriteController {
	
	private final PhotoFavoriteService photoFavoriteService;
	private final SessionHelper sessionHelper;

	/**
	 * お気に入り登録
	 * @param	photoFavoriteRegistRequest	{@link PhotoFavoriteRegistRequest}
	 * @return	PhotoFavoriteResponse		{@link PhotoFavoriteResponse}
	 * @throws	BadRequestException 		リクエストパラメータが不正の場合
	 * @throws	RegistFailureException 		お気に入りの登録に失敗した場合
	 */
	@PostMapping(value = "photo/favorite/add")
	public ResponseEntity<PhotoFavoriteResponse> addFavorite(
			@RequestBody @Validated PhotoFavoriteRegistRequest photoFavoriteRegistRequest,
			BindingResult result) throws BadRequestException, RegistFailureException {
		
		if(result.hasErrors()) {
    		throw new BadRequestException(ErrorValues.EC0000);
    	}
		
		PhotoFavoriteModel photoFavoriteModel = PhotoFavoriteModel.builder()
				.accountNo(sessionHelper.getAccountNo())
				.favoritePhotoAccountNo(photoFavoriteRegistRequest.getFavoritePhotoAccountNo())
				.favoritePhotoNo(photoFavoriteRegistRequest.getFavoritePhotoNo())
				.build();
		photoFavoriteService.addFavorite(photoFavoriteModel);
		
		return ResponseEntity.ok(PhotoFavoriteResponse.builder()
				.httpStatus(HttpStatus.OK.value())
				.isSuccess(true)
				.message("お気に入りに追加しました。")
				.build());
	}
	
	/**
	 * お気に入り解除
	 * @param	photoFavoriteDeleteRequest	{@link PhotoFavoriteDeleteRequest}
	 * @return	PhotoFavoriteResponse		{@link PhotoFavoriteResponse}
	 * @throws	BadRequestException 		リクエストパラメータが不正の場合
	 * @throws	UpdateFailureException 		お気に入りの解除に失敗した場合
	 */
	@PostMapping(value = "photo/favorite/delete")
	public ResponseEntity<PhotoFavoriteResponse> deleteFavorite(
			@RequestBody @Validated PhotoFavoriteDeleteRequest photoFavoriteDeleteRequest,
			BindingResult result) throws BadRequestException, UpdateFailureException {

		if(result.hasErrors()) {
    		throw new BadRequestException(ErrorValues.EC0000);
    	}
		
		PhotoFavoriteModel photoFavoriteModel = PhotoFavoriteModel.builder()
				.accountNo(sessionHelper.getAccountNo())
				.favoritePhotoAccountNo(photoFavoriteDeleteRequest.getFavoritePhotoAccountNo())
				.favoritePhotoNo(photoFavoriteDeleteRequest.getFavoritePhotoNo())
				.build();
		photoFavoriteService.deleteFavorite(photoFavoriteModel);
		
		return ResponseEntity.ok(PhotoFavoriteResponse.builder()
				.httpStatus(HttpStatus.OK.value())
				.isSuccess(true)
				.message("お気に入りを解除しました。")
				.build());
	}
}