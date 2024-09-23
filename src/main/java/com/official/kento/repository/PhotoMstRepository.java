package com.official.kento.repository;

import com.official.kento.exception.RegistFailureException;
import com.official.kento.exception.UpdateFailureException;
import com.official.kento.model.PhotoDeleteModel;
import com.official.kento.model.PhotoDetailModel;

/**
 * 写真マスタデータを永続化するRepositoryクラス
 */
public interface PhotoMstRepository {
	/**
	 * 写真マスタを登録する
	 * @param	photoDetailModel		{@link PhotoDetailModel}
	 * @param	filePath				写真の保存ファイルパス
	 * @param	newPhotoNo				新規採番した写真番号
	 * @throws	RegistFailureException	登録に失敗した場合
	 */
	void regist(PhotoDetailModel photoDetailModel, String filePath, Integer newPhotoNo) throws RegistFailureException;
	
	/**
	 * 写真マスタを更新する
	 * @param	photoDetailModel		{@link PhotoDetailModel}
	 * @throws	UpdateFailureException	更新に失敗した場合
	 */
	void update(PhotoDetailModel photoDetailModel) throws UpdateFailureException;
	
	/**
	 * 写真マスタを削除する
	 * @param	photoDeleteModel		{@link PhotoDeleteModel}
	 * @throws	UpdateFailureException	削除に失敗した場合
	 */
	void delete(PhotoDeleteModel photoDeleteModel) throws UpdateFailureException;
	
	/**
	 * アカウント番号から新しい写真番号を発番する
	 * @param	accountNo	アカウント番号
	 * @return				新規採番した写真番号
	 */
	Integer getNewPhotoNo(Integer accountNo);
	
	/**
	 * 同じファイル名の写真が存在するかチェックする
	 * @param	fileName	ファイル名
	 * @return				写真が存在する場合、true
	 */
	Boolean isExistPhoto(String fileName);
	
	/**
	 * アカウントに登録されている写真の件数を取得する
	 * @param	accountNo	アカウント番号
	 * @return				登録件数
	 */
	Integer count(Integer accountNo);
}