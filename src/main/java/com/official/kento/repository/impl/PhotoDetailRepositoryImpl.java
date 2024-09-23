package com.official.kento.repository.impl;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import com.official.kento.dto.PhotoDetailDto;
import com.official.kento.dto.PhotoDetailGetDto;
import com.official.kento.dto.PhotoDto;
import com.official.kento.dto.PhotoListGetDto;
import com.official.kento.entity.PhotoTagMst;
import com.official.kento.enumuration.ErrorValues;
import com.official.kento.exception.PhotoNotFoundException;
import com.official.kento.mapper.PhotoDetailMapper;
import com.official.kento.mapper.PhotoTagMstMapper;
import com.official.kento.model.PhotoDetailGetModel;
import com.official.kento.model.PhotoDetailModel;
import com.official.kento.model.PhotoGetModel;
import com.official.kento.model.PhotoModel;
import com.official.kento.model.PhotoTagModel;
import com.official.kento.repository.PhotoDetailRepository;

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
	 * @param	photoSelectModel	{@link PhotoGetModel}
	 * @return						{@link PhotoModel}
	 */
	@Override
	public List<PhotoModel> getPhotoList(PhotoGetModel photoSelectModel) {
		PhotoListGetDto photoSelectDto = modelMapper.map(photoSelectModel, PhotoListGetDto.class);
		List<PhotoDto> photoDtoList = photoDetailMapper.getPhotoList(photoSelectDto);
		
		PhotoTagMst photoTagMst = PhotoTagMst.builder()
				.accountNo(photoSelectModel.getPhotoAccountNo())
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