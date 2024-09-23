package com.official.kento.repository;

import com.official.kento.exception.RegistFailureException;
import com.official.kento.model.PhotoTagDeleteModel;
import com.official.kento.model.PhotoTagModel;

/**
 * 写真タグマスタデータを永続化するRepositoryクラス
 */
public interface PhotoTagMstRepository {
	/**
	 * 写真タグマスタを登録する
	 * @param	photoTagModel			{@link PhotoTagModel}
	 * @throws	RegistFailureException	登録に失敗した場合
	 */
	void regist(PhotoTagModel photoTagModel) throws RegistFailureException;
	
	/**
	 * 該当写真の写真タグを全件削除する
	 * @param	photoTagDeleteModel	{@link PhotoTagDeleteModel}
	 */
	void clear(PhotoTagDeleteModel photoTagDeleteModel);
}