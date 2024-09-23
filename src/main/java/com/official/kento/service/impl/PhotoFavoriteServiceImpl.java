package com.official.kento.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.official.kento.exception.RegistFailureException;
import com.official.kento.exception.UpdateFailureException;
import com.official.kento.model.PhotoFavoriteDeleteModel;
import com.official.kento.model.PhotoFavoriteModel;
import com.official.kento.repository.PhotoFavoriteRepository;
import com.official.kento.service.PhotoFavoriteService;

import lombok.RequiredArgsConstructor;

/**
 * 写真お気に入りに関するビジネスロジックを行うServiceの実装クラス
 */
@Service
@Transactional
@RequiredArgsConstructor
public class PhotoFavoriteServiceImpl implements PhotoFavoriteService {
	
	private final PhotoFavoriteRepository photoFavoriteRepository;
	
	/**
	 * お気に入りを追加する
	 * @param	photoFavoriteModel		{@link PhotoFavoriteModel}
	 * @throws	RegistFailureException	登録に失敗した場合
	 */
	@Override
	public void addFavorite(PhotoFavoriteModel photoFavoriteModel) throws RegistFailureException {
		photoFavoriteRepository.regist(photoFavoriteModel);
	}
	
	/**
	 * お気に入りを解除する
	 * @param	photoFavoriteModel		{@link PhotoFavoriteModel}
	 * @throws	UpdateFailureException	解除に失敗した場合
	 */
	@Override
	public void deleteFavorite(PhotoFavoriteModel photoFavoriteModel) throws UpdateFailureException {
		photoFavoriteRepository.delete(PhotoFavoriteDeleteModel.builder()
				.accountNo(photoFavoriteModel.getAccountNo())
				.favoritePhotoAccountNo(photoFavoriteModel.getFavoritePhotoAccountNo())
				.favoritePhotoNo(photoFavoriteModel.getFavoritePhotoNo())
				.build());
	}
}