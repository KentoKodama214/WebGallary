package com.web.gallary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.web.gallary.entity.KbnMst;

/**
 * 区分マスタテーブルのMapperクラス
 */
@Mapper
public interface KbnMstMapper {
	/**
	 * 条件に該当する区分マスタの一覧を取得する
	 * @param	kbnMst	抽出条件
	 * @return			{@link KbnMst}
	 */
	public List<KbnMst> select(KbnMst kbnMst);
	
	/**
	 * 条件に該当する区分マスタの件数を取得する
	 * @param	kbnMst	カウント条件
	 * @return			抽出件数
	 */
	public Integer count(KbnMst kbnMst);

	/**
	 * 区分マスタを登録する
	 * @param	kbnMst	{@link KbnMst}
	 * @return			登録件数
	 */
	public Integer insert(KbnMst kbnMst);
	
	/**
	 * 区分マスタを更新する
	 * @param	conditionKbnMst	更新対象の抽出条件
	 * @param	targetKbnMst	更新内容
	 * @return					更新件数
	 */
	public Integer update(@Param("condition") KbnMst conditionKbnMst, @Param("target") KbnMst targetKbnMst);
	
	/**
	 * 区分マスタを削除する
	 * @param	kbnMst	削除対象の抽出条件
	 * @return			削除件数
	 */
	public Integer delete(KbnMst kbnMst);
}