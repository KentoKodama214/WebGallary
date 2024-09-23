package com.official.kento.repository;

import java.util.List;

import com.official.kento.exception.PhotoNotFoundException;
import com.official.kento.model.PhotoDetailGetModel;
import com.official.kento.model.PhotoDetailModel;
import com.official.kento.model.PhotoGetModel;
import com.official.kento.model.PhotoModel;

/**
 * 写真のメタデータを含めた詳細情報を永続化するRepositoryクラス
 */
public interface PhotoDetailRepository {
	/**
	 * 該当アカウントの写真の一覧を取得する
	 * @param	photoSelectModel	{@link PhotoGetModel}
	 * @return						{@link PhotoModel}
	 */
	List<PhotoModel> getPhotoList(PhotoGetModel photoSelectModel);
	
	/**
	 * 写真のメタデータを含めた詳細情報を取得する
	 * @param	photoDetailGetModel		{@link PhotoDetailGetModel}
	 * @return							{@link PhotoDetailModel}
	 * @throws	PhotoNotFoundException	写真が存在しなかった場合
	 */
	PhotoDetailModel getPhotoDetail(PhotoDetailGetModel photoDetailModel) throws PhotoNotFoundException;
}