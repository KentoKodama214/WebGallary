package com.official.kento.service;

import java.util.List;

import com.official.kento.model.KbnMstModel;

/**
 * 区分マスタに関するビジネスロジックを行うServiceクラス
 */
public interface KbnMstService {
	/**
	 * 都道府県の区分マスタを取得する
	 * @return	{@link KbnMstModel}
	 */
	List<KbnMstModel> getPrefectureList();
}