package com.web.gallary.repository.impl;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.web.gallary.entity.PhotoMst;
import com.web.gallary.enumuration.ErrorValues;
import com.web.gallary.exception.RegistFailureException;
import com.web.gallary.exception.UpdateFailureException;
import com.web.gallary.mapper.PhotoMstMapper;
import com.web.gallary.model.PhotoDeleteModel;
import com.web.gallary.model.PhotoDetailModel;
import com.web.gallary.repository.PhotoMstRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 写真マスタデータを永続化するRepositoryの実装クラス
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class PhotoMstRepositoryImpl implements PhotoMstRepository {

	private final PhotoMstMapper photoMstMapper;
	
	/**
	 * 写真マスタを登録する
	 * @param	photoDetailModel		{@link PhotoDetailModel}
	 * @param	filePath				写真の保存ファイルパス
	 * @param	newPhotoNo				新規採番した写真番号
	 * @throws	RegistFailureException	登録に失敗した場合
	 */
	@Override
	public void regist(PhotoDetailModel photoDetailModel, String filePath, Integer newPhotoNo) throws RegistFailureException {
		PhotoMst photoMst = PhotoMst.builder()
				.accountNo(photoDetailModel.getAccountNo())
				.photoNo(newPhotoNo)
				.createdBy(photoDetailModel.getAccountNo())
				.updatedBy(photoDetailModel.getAccountNo())
				.photoAt(
					Optional.ofNullable(photoDetailModel.getPhotoAt())
						.orElse(OffsetDateTime.of(1900, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(9))))
				.locationNo(
					Optional.ofNullable(photoDetailModel.getLocationNo()).orElse(0))
				.imageFilePath(filePath)
				.photoJapaneseTitle(
					Optional.ofNullable(photoDetailModel.getPhotoJapaneseTitle()).orElse(""))
				.photoEnglishTitle(
					Optional.ofNullable(photoDetailModel.getPhotoEnglishTitle()).orElse(""))
				.caption(
					Optional.ofNullable(photoDetailModel.getCaption()).orElse(""))
				.directionKbnCode(
					Optional.ofNullable(photoDetailModel.getDirectionKbnCode()).orElse("none"))
				.focalLength(
					Optional.ofNullable(photoDetailModel.getFocalLength()).orElse(0))
				.fValue(
					Optional.ofNullable(photoDetailModel.getFValue()).orElse(BigDecimal.ZERO))
				.shutterSpeed(
					Optional.ofNullable(photoDetailModel.getShutterSpeed()).orElse(BigDecimal.ZERO))
				.iso(
					Optional.ofNullable(photoDetailModel.getIso()).orElse(0))
				.build();
		
		try {
			photoMstMapper.insert(photoMst);
		}
		catch (DuplicateKeyException e) {
			log.error("PhotoMst: Duplicate Key (AccountNo: " + photoDetailModel.getAccountNo()
											 + ", PhotoNo: " + newPhotoNo + ")");
			throw new RegistFailureException(ErrorValues.EP0001);
		}
	}
	
	/**
	 * 写真マスタを更新する
	 * @param	photoDetailModel		{@link PhotoDetailModel}
	 * @throws	UpdateFailureException	更新に失敗した場合
	 */
	@Override
	public void update(PhotoDetailModel photoDetailModel) throws UpdateFailureException {
		PhotoMst cndPhotoMst = PhotoMst.builder()
				.accountNo(photoDetailModel.getAccountNo())
				.photoNo(photoDetailModel.getPhotoNo())
				.build();
		
		PhotoMst targetPhotoMst = PhotoMst.builder()
				.updatedBy(photoDetailModel.getAccountNo())
				.isDeleted(false)
				.photoAt(
						Optional.ofNullable(photoDetailModel.getPhotoAt())
							.orElse(OffsetDateTime.of(1900, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(9))))
				.locationNo(
						Optional.ofNullable(photoDetailModel.getLocationNo()).orElse(0))
				.imageFilePath(photoDetailModel.getImageFilePath())
				.photoJapaneseTitle(
						Optional.ofNullable(photoDetailModel.getPhotoJapaneseTitle()).orElse(""))
					.photoEnglishTitle(
						Optional.ofNullable(photoDetailModel.getPhotoEnglishTitle()).orElse(""))
					.caption(
						Optional.ofNullable(photoDetailModel.getCaption()).orElse(""))
					.directionKbnCode(
						Optional.ofNullable(photoDetailModel.getDirectionKbnCode()).orElse("none"))
					.focalLength(
						Optional.ofNullable(photoDetailModel.getFocalLength()).orElse(0))
					.fValue(
						Optional.ofNullable(photoDetailModel.getFValue()).orElse(BigDecimal.ZERO))
					.shutterSpeed(
						Optional.ofNullable(photoDetailModel.getShutterSpeed()).orElse(BigDecimal.ZERO))
					.iso(
						Optional.ofNullable(photoDetailModel.getIso()).orElse(0))
				.build();
		
		if (photoMstMapper.update(cndPhotoMst, targetPhotoMst) < 1) {
			log.error("PhotoMst: Update Failed(AccountNo: " + cndPhotoMst.getAccountNo() 
										  + ", PhotoNo: "   + cndPhotoMst.getPhotoNo() + ")");
			throw new UpdateFailureException(ErrorValues.EP0002);
		}
	}
	
	/**
	 * 写真マスタを削除する
	 * @param	photoDeleteModel		{@link PhotoDeleteModel}
	 * @throws	UpdateFailureException	削除に失敗した場合
	 */
	@Override
	public void delete(PhotoDeleteModel photoDeleteModel) throws UpdateFailureException {
		PhotoMst cndPhotoMst = PhotoMst.builder()
				.accountNo(photoDeleteModel.getAccountNo())
				.photoNo(photoDeleteModel.getPhotoNo())
				.build();
		
		PhotoMst targetPhotoMst = PhotoMst.builder()
				.updatedBy(photoDeleteModel.getAccountNo())
				.isDeleted(true)
				.build();
		
		if (photoMstMapper.update(cndPhotoMst, targetPhotoMst) < 1) {
			log.error("PhotoMst: Delete Failed(AccountNo: " + cndPhotoMst.getAccountNo() 
										  + ", PhotoNo: "   + cndPhotoMst.getPhotoNo() + ")");
			throw new UpdateFailureException(ErrorValues.EP0003);
		}
	}
	
	/**
	 * アカウント番号から新しい写真番号を発番する
	 * @param	accountNo	アカウント番号
	 * @return				新規採番した写真番号
	 */
	@Override
	public Integer getNewPhotoNo(Integer accountNo) {
		Integer photoNo = photoMstMapper.getMaxPhotoNo(accountNo);
		return Optional.ofNullable(photoNo).map(num -> num + 1).orElse(1);
	}
	
	/**
	 * 同じファイル名の写真が存在するかチェックする
	 * @param	photoDetailModel	{@link PhotoDetailModel}
	 * @return						写真が存在する場合、true
	 */
	@Override
	public Boolean isExistPhoto(PhotoDetailModel photoDetailModel) {
		PhotoMst photoMst = PhotoMst.builder()
				.accountNo(photoDetailModel.getAccountNo())
				.imageFilePath(photoDetailModel.getImageFile().getOriginalFilename())
				.build();
		return photoMstMapper.isExistPhoto(photoMst);
	}
	
	/**
	 * アカウントに登録されている写真の件数を取得する
	 * @param	accountNo	アカウント番号
	 * @return				登録件数
	 */
	@Override
	public Integer count(Integer accountNo) {
		PhotoMst photoMst = PhotoMst.builder().accountNo(accountNo).isDeleted(false).build();
		return photoMstMapper.count(photoMst);
	}
}