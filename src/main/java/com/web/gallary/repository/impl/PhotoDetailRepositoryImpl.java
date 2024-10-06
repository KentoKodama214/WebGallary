package com.web.gallary.repository.impl;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import com.web.gallary.dto.PhotoDetailDto;
import com.web.gallary.dto.PhotoDetailGetDto;
import com.web.gallary.dto.PhotoDto;
import com.web.gallary.dto.PhotoListGetDto;
import com.web.gallary.entity.PhotoTagMst;
import com.web.gallary.enumuration.ErrorValues;
import com.web.gallary.exception.PhotoNotFoundException;
import com.web.gallary.mapper.PhotoDetailMapper;
import com.web.gallary.mapper.PhotoTagMstMapper;
import com.web.gallary.model.PhotoDetailGetModel;
import com.web.gallary.model.PhotoDetailModel;
import com.web.gallary.model.PhotoGetModel;
import com.web.gallary.model.PhotoModel;
import com.web.gallary.model.PhotoTagModel;
import com.web.gallary.repository.PhotoDetailRepository;

import lombok.RequiredArgsConstructor;

/**
 * 写真のメタデータを含めた詳細情報を永続化するRepositoryの実装クラス
 */
@Repository
@RequiredArgsConstructor
public class PhotoDetailRepositoryImpl implements PhotoDetailRepository {

	private final PhotoTagMstMapper photoTagMstMapper;
	private final PhotoDetailMapper photoDetailMapper;
	private ModelMapper modelMapper = new ModelMapper();
	private OffsetDateTime defaultOffsetDateTime = OffsetDateTime.of(1900, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(9));
	
	/**
	 * 該当アカウントの写真の一覧を取得する
	 * @param	photoGetModel	{@link PhotoGetModel}
	 * @return						{@link PhotoModel}
	 */
	@Override
	public List<PhotoModel> getPhotoList(PhotoGetModel photoGetModel) {
		PhotoListGetDto photoSelectDto = modelMapper.map(photoGetModel, PhotoListGetDto.class);
		List<PhotoDto> photoDtoList = photoDetailMapper.getPhotoList(photoSelectDto);
		
		PhotoTagMst photoTagMst = PhotoTagMst.builder()
				.accountNo(photoGetModel.getPhotoAccountNo())
				.build();
		List<PhotoTagMst> photoTagMstList = photoTagMstMapper.select(photoTagMst);
		
		List<PhotoTagModel> photoTagModelList
			= photoTagMstList.stream().map(photoTagModel -> PhotoTagModel.builder()
					.accountNo(photoTagModel.getAccountNo())
					.photoNo(photoTagModel.getPhotoNo())
					.tagNo(photoTagModel.getTagNo())
					.tagJapaneseName(photoTagModel.getTagJapaneseName())
					.tagEnglishName(photoTagModel.getTagEnglishName())
					.build()
				).toList();
		
		List<PhotoModel> photoModelList = new ArrayList<PhotoModel>();
		photoDtoList.stream().forEach(photoDto -> {
			PhotoModel photoModel = PhotoModel.builder()
					.accountNo(photoDto.getAccountNo())
					.photoNo(photoDto.getPhotoNo())
					.favoriteCount(photoDto.getFavoriteCount())
					.isFavorite(photoDto.getIsFavorite())
					.photoAt(photoDto.getPhotoAt().plusHours(9))
					.imageFilePath(photoDto.getImageFilePath())
					.caption(photoDto.getCaption())
					.directionKbnCode(photoDto.getDirectionKbnCode())
					.photoTagModelList(
							photoTagModelList.stream().filter(photoTagModel -> 
								photoTagModel.getAccountNo() == photoDto.getAccountNo() &&
								photoTagModel.getPhotoNo()   == photoDto.getPhotoNo()
						).toList())
					.build();
			photoModelList.add(photoModel);
		});
		
		return photoModelList;
	}
	
	/**
	 * 写真のメタデータを含めた詳細情報を取得する
	 * @param	photoDetailGetModel		{@link PhotoDetailGetModel}
	 * @return							{@link PhotoDetailModel}
	 * @throws	PhotoNotFoundException	写真が存在しなかった場合
	 */
	@Override
	public PhotoDetailModel getPhotoDetail(PhotoDetailGetModel photoDetailGetModel) throws PhotoNotFoundException {
		PhotoDetailGetDto photoGetDto = modelMapper.map(photoDetailGetModel, PhotoDetailGetDto.class);
		PhotoDetailDto photoDetailDto = photoDetailMapper.getPhotoDetail(photoGetDto);
		
		if(Objects.isNull(photoDetailDto)) {
			throw new PhotoNotFoundException(ErrorValues.EP0010);
		}
		
		PhotoTagMst photoTagMst = PhotoTagMst.builder()
				.accountNo(photoDetailGetModel.getPhotoAccountNo())
				.photoNo(photoDetailGetModel.getPhotoNo())
				.build();
		List<PhotoTagMst> photoTagMstList = photoTagMstMapper.select(photoTagMst);

		List<PhotoTagModel> photoTagModelList
			= photoTagMstList.stream().map(photoTagModel -> PhotoTagModel.builder()
					.accountNo(photoTagModel.getAccountNo())
					.photoNo(photoTagModel.getPhotoNo())
					.tagNo(photoTagModel.getTagNo())
					.tagJapaneseName(photoTagModel.getTagJapaneseName())
					.tagEnglishName(photoTagModel.getTagEnglishName())
					.build()
				).toList();
		
		PhotoDetailModel photoDetailModel = PhotoDetailModel.builder()
				.accountNo(photoDetailDto.getAccountNo())
				.photoNo(photoDetailDto.getPhotoNo())
				.isFavorite(photoDetailDto.getIsFavorite())
				.photoAt(
					photoDetailDto.getPhotoAt()
						.isEqual(defaultOffsetDateTime) ? null : photoDetailDto.getPhotoAt().plusHours(9))
				.locationNo(photoDetailDto.getLocationNo())
				.address(photoDetailDto.getAddress())
				.latitude(photoDetailDto.getLatitude())
				.longitude(photoDetailDto.getLongitude())
				.locationName(photoDetailDto.getLocationName())
				.imageFilePath(photoDetailDto.getImageFilePath())
				.photoJapaneseTitle(photoDetailDto.getPhotoJapaneseTitle())
				.photoEnglishTitle(photoDetailDto.getPhotoEnglishTitle())
				.caption(photoDetailDto.getCaption())
				.directionKbnCode(photoDetailDto.getDirectionKbnCode())
				.focalLength(photoDetailDto.getFocalLength() != 0 ? photoDetailDto.getFocalLength() : null)
				.fValue(photoDetailDto.getFValue().compareTo(BigDecimal.ZERO) == 1 ? photoDetailDto.getFValue(): null)
				.shutterSpeed(photoDetailDto.getShutterSpeed().compareTo(BigDecimal.ZERO) == 1 ? photoDetailDto.getShutterSpeed() : null)
				.iso(photoDetailDto.getIso() != 0 ? photoDetailDto.getIso() : null)
				.photoTagModelList(photoTagModelList)
				.build();
		
		return photoDetailModel;
	}
}