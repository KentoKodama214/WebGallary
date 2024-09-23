package com.official.kento.controller;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.official.kento.config.PhotoConfig;
import com.official.kento.controller.request.PhotoDeleteRequest;
import com.official.kento.controller.request.PhotoListRequest;
import com.official.kento.controller.request.PhotoSaveRequest;
import com.official.kento.controller.response.PhotoEditResponse;
import com.official.kento.controller.response.PhotoListGetResponse;
import com.official.kento.controller.response.PhotoListResponse;
import com.official.kento.enumuration.ErrorValues;
import com.official.kento.exception.BadRequestException;
import com.official.kento.exception.FileDuplicateException;
import com.official.kento.exception.ForbiddenAccountException;
import com.official.kento.exception.PhotoNotAdditableException;
import com.official.kento.exception.RegistFailureException;
import com.official.kento.exception.UpdateFailureException;
import com.official.kento.helper.SessionHelper;
import com.official.kento.model.PhotoDeleteModel;
import com.official.kento.model.PhotoDetailModel;
import com.official.kento.model.PhotoListGetModel;
import com.official.kento.model.PhotoModel;
import com.official.kento.model.PhotoTagModel;
import com.official.kento.service.PhotoService;

import lombok.RequiredArgsConstructor;

/**
 * 写真に関するAPI通信を扱うRestControllerクラス
 * @author	Kento Kodama
 * @version	1.0.0
 * @since	1.0.0
*/
@RestController
@RequiredArgsConstructor
public class PhotoRestController {
	
	private final PhotoService photoService;
	private final SessionHelper sessionHelper;
	private final PhotoConfig photoConfig;
	
	/**
	 * 写真一覧の写真取得<br>
	 * リクエストの抽出条件に該当する写真を、指定の並び順で取得する
	 * @param	photoAccountId		ページ所有者のアカウントID
	 * @param	photoListRequest	{@link PhotoListRequest}
	 * @return						{@link PhotoListGetResponse}
	 */
	@PostMapping(value = "photo/{photoAccountId}/photo_list/get")
	public ResponseEntity<PhotoListGetResponse> getPhotoList(
			@PathVariable String photoAccountId, 
			@RequestBody @Validated PhotoListRequest photoListRequest) {
		photoListRequest.setDirectionKbnCode(
				Optional.ofNullable(photoListRequest.getDirectionKbnCode()).orElse(""));
		
		photoListRequest.setIsFavorite(
				Optional.ofNullable(photoListRequest.getIsFavorite()).orElse(false));
		
		photoListRequest.setSortBy(
				Optional.ofNullable(photoListRequest.getSortBy()).orElse("photoAt"));
		
		photoListRequest.setPageNo(
				Optional.ofNullable(photoListRequest.getPageNo()).orElse(1));
		
		Optional<String> tagsOpt = Optional.ofNullable(photoListRequest.getTagList());
		photoListRequest.setTagList(tagsOpt.map(tag -> tag.replace(" ", "　")).orElse(""));
		List<String> tagList = tagsOpt.map(tag -> 
			new ArrayList<String>(Arrays.asList(tag.replace("　", " ").split(" ")))).orElse(new ArrayList<String>());
		
		// 抽出条件に該当する写真の一覧を、指定の並び順で取得する
		List<PhotoModel> photoList = photoService.getPhotoList(
				PhotoListGetModel.builder()
					.accountNo(sessionHelper.getAccountNo())
					.photoAccountId(photoAccountId)
					.directionKbnCode(photoListRequest.getDirectionKbnCode())
					.isFavoriteOnly(photoListRequest.getIsFavorite())
					.tagList(tagList)
					.sortBy(photoListRequest.getSortBy())
					.build()
			);
		return ResponseEntity.ok(createPhotoListGetResponse(photoList, photoListRequest.getPageNo()));
	}
	
	/**
	 * 写真保存
	 * @param	photoAccountId				ページ所有者のアカウントID
	 * @param	photoSaveRequest			{@link PhotoSaveRequest}
	 * @param	result						PhotoSaveRequestのバインディング結果
	 * @return								{@link PhotoEditResponse}
	 * @throws	ForbiddenAccountException 	写真の所有者以外がリクエストした場合
	 * @throws	PhotoNotAdditableException	写真の登録枚数の上限に達している場合
	 * @throws	BadRequestException 		リクエストパラメータが不正の場合
	 * @throws	FileDuplicateException 		保存するファイルが重複した場合
	 * @throws	RegistFailureException 		写真の登録に失敗した場合
	 * @throws	UpdateFailureException 		写真の更新に失敗した場合
	 */
	@PostMapping(value = "photo/{photoAccountId}/save")
	public ResponseEntity<PhotoEditResponse> savePhoto(
			@PathVariable String photoAccountId, 
			@ModelAttribute @Validated PhotoSaveRequest photoSaveRequest, 
			BindingResult result) throws FileDuplicateException, ForbiddenAccountException, RegistFailureException, UpdateFailureException, BadRequestException, PhotoNotAdditableException {
		
		if(!photoAccountId.equals(sessionHelper.getAccountId())) {
			throw new ForbiddenAccountException(ErrorValues.EP0009);
		}
		
		if(Objects.isNull(photoSaveRequest.getPhotoNo()) && photoService.isReachedUpperLimit(sessionHelper.getAccountNo())) {
			throw new PhotoNotAdditableException(ErrorValues.EP0011);
		}
		
		if(Objects.isNull(photoSaveRequest.getImageFile()) && 
				(Objects.isNull(photoSaveRequest.getImageFilePath()) || "".equals(photoSaveRequest.getImageFilePath()))) {
			throw new BadRequestException(ErrorValues.EC0000);
		}
		
		for(FieldError error : result.getFieldErrors()) {
			if(!error.isBindingFailure()) {
				throw new BadRequestException(ErrorValues.EC0000);
			}
		};
		
		List<PhotoTagModel> photoTagModelList = new ArrayList<PhotoTagModel>();
		
		if(!Objects.isNull(photoSaveRequest.getPhotoTagRegistRequestList())) {
			photoSaveRequest.getPhotoTagRegistRequestList().stream().forEach(photoTag -> {
				photoTagModelList.add(
					PhotoTagModel.builder()
						.accountNo(photoTag.getAccountNo())
						.photoNo(photoTag.getPhotoNo())
						.tagNo(photoTag.getTagNo())
						.tagJapaneseName(photoTag.getTagJapaneseName())
						.tagEnglishName(photoTag.getTagEnglishName())
						.build()
				);
			});
		}
		
		List<PhotoDetailModel> photoDetailModelList = new ArrayList<PhotoDetailModel>();
		photoDetailModelList.add(
			PhotoDetailModel.builder()
				.accountNo(photoSaveRequest.getAccountNo())
				.photoNo(photoSaveRequest.getPhotoNo())
				.isFavorite(photoSaveRequest.getIsFavorite())
				.photoAt(
					Optional.ofNullable(photoSaveRequest.getPhotoAt())
						.map(photoAt -> photoAt.atOffset(ZoneOffset.of("+09:00"))).orElse(null))
				.locationNo(photoSaveRequest.getLocationNo())
				.address(photoSaveRequest.getAddress())
				.latitude(photoSaveRequest.getLatitude())
				.longitude(photoSaveRequest.getLongitude())
				.locationName(photoSaveRequest.getLocationName())
				.imageFile(photoSaveRequest.getImageFile())
				.imageFilePath(photoSaveRequest.getImageFilePath())
				.photoJapaneseTitle(photoSaveRequest.getPhotoJapaneseTitle())
				.photoEnglishTitle(photoSaveRequest.getPhotoEnglishTitle())
				.caption(photoSaveRequest.getCaption())
				.directionKbnCode(photoSaveRequest.getDirectionKbnCode())
				.focalLength(photoSaveRequest.getFocalLength())
				.fValue(photoSaveRequest.getFValue())
				.shutterSpeed(photoSaveRequest.getShutterSpeed())
				.iso(photoSaveRequest.getIso())
				.photoTagModelList(photoTagModelList)
				.build()
		);
		
		photoService.savePhotos(photoAccountId, photoDetailModelList);
		
		return ResponseEntity.ok(PhotoEditResponse.builder()
				.httpStatus(HttpStatus.OK.value())
				.isSuccess(true)
				.message("写真登録が完了しました。")
				.build());
	}
	
	/**
	 * 写真削除
	 * @param photoAccountId				ページ所有者のアカウントID
	 * @param photoDeleteRequest			{@link PhotoDeleteRequest}
	 * @param result						PhotoDeleteRequestのバインディング結果
	 * @return								{@link PhotoEditResponse}
	 * @throws BadRequestException 			リクエストパラメータが不正の場合
	 * @throws ForbiddenAccountException 	写真の所有者以外がリクエストした場合
	 * @throws UpdateFailureException 		写真の削除に失敗した場合
	 */
	@PostMapping(value = "photo/{photoAccountId}/delete")
	public ResponseEntity<PhotoEditResponse> deletePhoto(
			@PathVariable String photoAccountId, 
			@ModelAttribute @Validated PhotoDeleteRequest photoDeleteRequest, 
			BindingResult result) throws BadRequestException, ForbiddenAccountException, UpdateFailureException {
		
		if(!photoAccountId.equals(sessionHelper.getAccountId())) {
			throw new ForbiddenAccountException(ErrorValues.EP0009);
		}
		
		if(result.hasErrors()) {
			throw new BadRequestException(ErrorValues.EC0000);
		}
		
		List<PhotoDeleteModel> photoDeleteModelList = new ArrayList<PhotoDeleteModel>();
		photoDeleteModelList.add(
			PhotoDeleteModel.builder()
				.accountNo(photoDeleteRequest.getAccountNo())
				.photoNo(photoDeleteRequest.getPhotoNo())
				.imageFilePath(photoDeleteRequest.getImageFilePath())
				.build()
		);
		
		photoService.deletePhotos(photoAccountId, photoDeleteModelList);
		
		return ResponseEntity.ok(PhotoEditResponse.builder()
				.httpStatus(HttpStatus.OK.value())
				.isSuccess(true)
				.message("写真削除が完了しました。")
				.build());
	}
	
	/**
	 * ページ番号から写真のリストを絞り込み、写真一覧のレスポンスのクラスへ詰め替えをする
	 * @param	photoList	{@link PhotoModel}
	 * @param	pageNo		ページ番号
	 * @return				{@link PhotoListGetResponse}
	 */
	private PhotoListGetResponse createPhotoListGetResponse(List<PhotoModel> photoList, Integer pageNo) {
		List<PhotoListResponse> photoListResponseList = new ArrayList<PhotoListResponse>();
		
		photoList.subList(
			(pageNo - 1) * photoConfig.getPhotoCountPerPage(), 
			Math.min(pageNo * photoConfig.getPhotoCountPerPage(), photoList.size())).forEach(photo -> {
				photoListResponseList.add(PhotoListResponse.builder()
						.accountNo(photo.getAccountNo())
						.photoNo(photo.getPhotoNo())
						.isFavorite(photo.getIsFavorite())
						.imageFilePath(photo.getImageFilePath())
						.caption(photo.getCaption())
						.directionKbnCode(photo.getDirectionKbnCode())
						.build());
			});
		
		return PhotoListGetResponse.builder()
				.isLast(pageNo * photoConfig.getPhotoCountPerPage() >= photoList.size())
				.photolList(photoListResponseList)
				.build();
	}
}