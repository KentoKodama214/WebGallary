package com.official.kento.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.official.kento.model.KbnMstModel;
import com.official.kento.repository.KbnMstRepository;
import com.official.kento.service.KbnMstService;

import lombok.RequiredArgsConstructor;

/**
 * 区分マスタに関するビジネスロジックを行うServiceの実装クラス
 */
@Service
@RequiredArgsConstructor
public class KbnMstServiceImpl implements KbnMstService {

	private final KbnMstRepository kbnMstRepository;
	
	/**
	 * 都道府県の区分マスタを取得する
	 * @return	{@link KbnMstModel}
	 */
	@Override
	public List<KbnMstModel> getPrefectureList() {
		return kbnMstRepository.get("prefecture");
	}
}