package com.official.kento.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.official.kento.dto.PhotoDetailDto;
import com.official.kento.dto.PhotoDetailGetDto;
import com.official.kento.dto.PhotoDto;
import com.official.kento.dto.PhotoListGetDto;

/**
 * 写真のメタデータを含めた詳細情報のMapperクラス
 */
@Mapper
public interface PhotoDetailMapper {
	/**
	 * 写真の一覧を取得する
	 * @param	photoSelectDto	{@link PhotoListGetDto}
	 * @return					{@link PhotoDto}
	 */
	public List<PhotoDto> getPhotoList(PhotoListGetDto photoSelectDto);
	
	/**
	 * 1枚の写真のメタデータを含めた詳細情報を取得する
	 * @param	photoGetDto	{@link PhotoDetailGetDto}
	 * @return				{@link PhotoDetailDto}
	 */
	public PhotoDetailDto getPhotoDetail(PhotoDetailGetDto photoGetDto);
}