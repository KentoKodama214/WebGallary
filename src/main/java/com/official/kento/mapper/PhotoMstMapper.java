package com.official.kento.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.official.kento.entity.PhotoMst;

/**
 * 写真マスタテーブルのMapperクラス
 */
@Mapper
public interface PhotoMstMapper {
	/**
	 * 条件に該当する写真マスタの一覧を取得する
	 * @param	photoMst	抽出条件
	 * @return				{@link PhotoMst}
	 */
	public List<PhotoMst> select(PhotoMst photoMst);
	
	/**
	 * 条件に該当する写真マスタの件数を取得する
	 * @param	photoMst	カウント条件
	 * @return				抽出件数
	 */
	public Integer count(PhotoMst photoMst);
	
	/**
	 * 写真マスタを登録する
	 * @param	photoMst	{@link PhotoMst}
	 * @return				登録件数
	 */
	public Integer insert(PhotoMst photoMst);
	
	/**
	 * 写真マスタを更新する
	 * @param	conditionPhotoMst	更新対象の抽出条件
	 * @param	targetPhotoMst		更新内容
	 * @return						更新件数
	 */
	public Integer update(@Param("condition") PhotoMst conditionPhotoMst, @Param("target") PhotoMst targetPhotoMst);
	
	/**
	 * 写真マスタを削除する
	 * @param	photoMst	削除対象の抽出条件
	 * @return				削除件数
	 */
	public Integer delete(PhotoMst photoMst);
	
	/**
	 * アカウントが登録済みの最大の写真番号を取得する
	 * @param	accountNo	アカウント番号
	 * @return				最大の写真番号
	 */
	public Integer getMaxPhotoNo(Integer accountNo);
	
	/**
	 * ファイル名から登録済みの写真家判定する
	 * @param	filename	ファイル名
	 * @return				登録有無
	 */
	public Boolean isExistPhoto(String filename);
}