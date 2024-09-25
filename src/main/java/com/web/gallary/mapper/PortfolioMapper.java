package com.web.gallary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.web.gallary.entity.Portfolio;

/**
 * ポートフォリオテーブルのMapperクラス
 */
@Mapper
public interface PortfolioMapper {
	/**
	 * 条件に該当するポートフォリオの一覧を取得する
	 * @param	portfolio	抽出条件
	 * @return				{@link Portfolio}
	 */
	public List<Portfolio> select(Portfolio portfolio);
	
	/**
	 * 条件に該当するポートフォリオの件数を取得する
	 * @param	portfolio	カウント条件
	 * @return				抽出件数
	 */
	public Integer count(Portfolio portfolio);
	
	/**
	 * ポートフォリオを登録する
	 * @param	portfolio	{@link Portfolio}
	 * @return				登録件数
	 */
	public Integer insert(Portfolio portfolio);
	
	/**
	 * ポートフォリオを更新する
	 * @param	conditionPortfolio	更新対象の抽出条件
	 * @param	targetPortfolio		更新内容
	 * @return						更新件数
	 */
	public Integer update(@Param("condition") Portfolio conditionPortfolio, @Param("target") Portfolio targetPortfolio);
	
	/**
	 * ポートフォリオを削除する
	 * @param	portfolio	削除対象の抽出条件
	 * @return				削除件数
	 */
	public Integer delete(Portfolio portfolio);
}