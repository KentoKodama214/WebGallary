package com.web.gallary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.web.gallary.entity.PhotoTagMst;

/**
 * 写真タグマスタのMapperクラス
 */
@Mapper
public interface PhotoTagMstMapper {
	/**
	 * 条件に該当する写真タグマスタの一覧を取得する
	 * @param	photoTagMst	抽出条件
	 * @return				@{link PhotoTagMst}
	 */
	public List<PhotoTagMst> select(PhotoTagMst photoTagMst);
	
	/**
	 * 条件に該当する写真タグマスタの件数を取得する
	 * @param	photoTagMst	カウント条件
	 * @return				抽出件数
	 */
	public Integer count(PhotoTagMst photoTagMst);
	
	/**
	 * 写真タグマスタを登録する
	 * @param	photoTagMst	{@link PhotoTagMst}
	 * @return				登録件数
	 */
	public Integer insert(PhotoTagMst photoTagMst);
	
	/**
	 * 写真タグマスタを更新する
	 * @param	conditionPhotoTagMst	更新対象の抽出条件
	 * @param	targetPhotoTagMst		更新内容
	 * @return							更新件数
	 */
	public Integer update(@Param("condition") PhotoTagMst conditionPhotoTagMst, @Param("target") PhotoTagMst targetPhotoTagMst);
	
	/**
	 * 写真タグマスタを削除する
	 * @param	photoTagMst	削除対象の抽出条件
	 * @return				削除件数
	 */
	public Integer delete(PhotoTagMst photoTagMst);
}