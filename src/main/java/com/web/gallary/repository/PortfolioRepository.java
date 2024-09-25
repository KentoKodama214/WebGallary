package com.web.gallary.repository;

import com.web.gallary.exception.RegistFailureException;
import com.web.gallary.model.PortfolioDeleteModel;
import com.web.gallary.model.PortfolioModel;

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