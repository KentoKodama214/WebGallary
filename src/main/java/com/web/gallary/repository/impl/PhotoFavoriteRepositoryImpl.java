package com.web.gallary.repository.impl;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.web.gallary.entity.PhotoFavorite;
import com.web.gallary.enumuration.ErrorValues;
import com.web.gallary.exception.RegistFailureException;
import com.web.gallary.exception.UpdateFailureException;
import com.web.gallary.mapper.PhotoFavoriteMapper;
import com.web.gallary.model.PhotoFavoriteDeleteModel;
import com.web.gallary.model.PhotoFavoriteModel;
import com.web.gallary.repository.PhotoFavoriteRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 写真お気に入りデータを永続化するRepositoryの実装クラス
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class PhotoFavoriteRepositoryImpl implements PhotoFavoriteRepository{
	
	private final PhotoFavoriteMapper photoFavoriteMapper;
	
	/**
	 * 写真お気に入りを登録する
	 * @param	favoriteModel			{@link PhotoFavoriteModel}
	 * @throws	RegistFailureException	登録に失敗した場合
	 */
	@Override
	public void regist(PhotoFavoriteModel favoriteModel) throws RegistFailureException {
		PhotoFavorite photoFavorite = PhotoFavorite.builder()
				.accountNo(favoriteModel.getAccountNo())
				.favoritePhotoAccountNo(favoriteModel.getFavoritePhotoAccountNo())
				.favoritePhotoNo(favoriteModel.getFavoritePhotoNo())
				.createdBy(favoriteModel.getAccountNo())
				.build();
		
		try {
			photoFavoriteMapper.insert(photoFavorite);
		}
		catch (DuplicateKeyException e) {
			log.error("PhotoFavorite: Duplicate Key (AccountNo: "  + favoriteModel.getAccountNo()
									+ ", FavoritePhotoAccountNo: " + favoriteModel.getFavoritePhotoAccountNo()
									+ ", FavoritePhotoNo: " 	   + favoriteModel.getFavoritePhotoNo() + ")");
			throw new RegistFailureException(ErrorValues.EP0005);
		}
	}
	
	/**
	 * 写真お気に入りを削除する
	 * @param	favoriteDeleteModel		{@link PhotoFavoriteDeleteModel}
	 * @throws	UpdateFailureException	更新に失敗した場合
	 */
	@Override
	public void delete(PhotoFavoriteDeleteModel favoriteDeleteModel) throws UpdateFailureException {
		PhotoFavorite photoFavorite = PhotoFavorite.builder()
				.accountNo(favoriteDeleteModel.getAccountNo())
				.favoritePhotoAccountNo(favoriteDeleteModel.getFavoritePhotoAccountNo())
				.favoritePhotoNo(favoriteDeleteModel.getFavoritePhotoNo())
				.build();
		
		if (photoFavoriteMapper.delete(photoFavorite) < 1) {
			log.error("PhotoFavorite: Delete Failed(AccountNo: "  + favoriteDeleteModel.getAccountNo() 
									+ ", FavoritePhotoAccountNo:" + favoriteDeleteModel.getFavoritePhotoAccountNo()
									+ ", FavoritePhotoNo: "       + favoriteDeleteModel.getFavoritePhotoNo() + ")");
			throw new UpdateFailureException(ErrorValues.EP0006);
		}
	}
	
	/**
	 * 該当写真の写真お気に入りを全件削除する
	 * @param	favoriteDeleteModel	{@link PhotoFavoriteDeleteModel}
	 */
	@Override
	public void clear(PhotoFavoriteDeleteModel favoriteDeleteModel) {
		PhotoFavorite photoFavorite = PhotoFavorite.builder()
				.favoritePhotoAccountNo(favoriteDeleteModel.getFavoritePhotoAccountNo())
				.favoritePhotoNo(favoriteDeleteModel.getFavoritePhotoNo())
				.build();
		
		photoFavoriteMapper.delete(photoFavorite);
	}
}