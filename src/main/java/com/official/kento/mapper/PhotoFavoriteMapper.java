package com.official.kento.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.official.kento.entity.PhotoFavorite;

/**
 * 写真お気に入りテーブルのMapperクラス
 */
@Mapper
public interface PhotoFavoriteMapper {
	/**
	 * 条件に該当する写真お気に入りの一覧を取得する
	 * @param	photoFavorite	抽出条件
	 * @return					{@link PhotoFavorite}
	 */
	public List<PhotoFavorite> select(PhotoFavorite photoFavorite);
	
	/**
	 * 条件に該当する写真お気に入りの件数を取得する
	 * @param	photoFavorite	カウント条件
	 * @return					抽出件数
	 */
	public Integer count(PhotoFavorite photoFavorite);
	
	/**
	 * 写真お気に入りを登録する
	 * @param	photoFavorite	{@link PhotoFavorite}
	 * @return					登録件数
	 */
	public Integer insert(PhotoFavorite photoFavorite);
	
	/**
	 * 写真お気に入りを更新する
	 * @param	conditionPhotoFavorite	更新対象の抽出条件
	 * @param	targetPhotoFavorite		更新内容
	 * @return							更新件数
	 */
	public Integer update(@Param("condition") PhotoFavorite conditionPhotoFavorite, @Param("target") PhotoFavorite targetPhotoFavorite);
	
	/**
	 * 写真お気に入りを削除する
	 * @param	photoFavorite	削除対象の抽出条件
	 * @return					削除件数
	 */
	public Integer delete(PhotoFavorite photoFavorite);
}