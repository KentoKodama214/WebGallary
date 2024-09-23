package com.official.kento.repository;

import com.official.kento.exception.RegistFailureException;
import com.official.kento.model.PortfolioDeleteModel;
import com.official.kento.model.PortfolioModel;

/**
 * ポートフォリオデータを永続化するRepositoryクラス
 */
public interface PortfolioRepository {
	/**
	 * ポートフォリオを登録する
	 * @param	portfolioModel			{@link PortfolioModel}
	 * @throws	RegistFailureException	登録に失敗した場合
	 */
	void regist(PortfolioModel portfolioModel) throws RegistFailureException;
	
	/**
	 * 該当写真のポートフォリオを全件削除する
	 * @param	portfolioDeleteModel	{@link PortfolioDeleteModel}
	 */
	void clear(PortfolioDeleteModel portfolioDeleteModel);
}