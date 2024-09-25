package com.web.gallary.repository.impl;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.web.gallary.entity.Portfolio;
import com.web.gallary.enumuration.ErrorValues;
import com.web.gallary.exception.RegistFailureException;
import com.web.gallary.mapper.PortfolioMapper;
import com.web.gallary.model.PortfolioDeleteModel;
import com.web.gallary.model.PortfolioModel;
import com.web.gallary.repository.PortfolioRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * ポートフォリオデータを永続化するRepositoryの実装クラス
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class PortfolioRepositoryImpl implements PortfolioRepository {
	
	private final PortfolioMapper portfolioMapper;
	
	/**
	 * ポートフォリオを登録する
	 * @param	portfolioModel			{@link PortfolioModel}
	 * @throws	RegistFailureException	登録に失敗した場合
	 */
	@Override
	public void regist(PortfolioModel portfolioModel) throws RegistFailureException {
		Portfolio portfolio = Portfolio.builder()
				.accountNo(portfolioModel.getAccountNo())
				.photoNo(portfolioModel.getPhotoNo())
				.sortOrder(portfolioModel.getSortOrder())
				.build();
		
		try {
			portfolioMapper.insert(portfolio);
		}
		catch (DuplicateKeyException e) {
			log.error("Portfolio: Duplicate Key (AccountId: "  + portfolioModel.getAccountNo()
			  								+ ", PhototNo: "   + portfolioModel.getPhotoNo() + ")");
			throw new RegistFailureException(ErrorValues.EP0007);
		}
	}
	
	/**
	 * 該当写真のポートフォリオを全件削除する
	 * @param	portfolioDeleteModel	{@link PortfolioDeleteModel}
	 */
	@Override
	public void clear(PortfolioDeleteModel portfolioDeleteModel) {
		Portfolio portfolio = Portfolio.builder()
				.accountNo(portfolioDeleteModel.getAccountNo())
				.photoNo(portfolioDeleteModel.getPhotoNo())
				.build();
		
		portfolioMapper.delete(portfolio);
	}
}