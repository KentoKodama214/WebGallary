package com.web.gallary.util;

import java.util.Objects;

import com.web.gallary.constant.ApiRoutes;

/**
 * 写真関連のURLを取得するUtilityクラス
 */
public final class PhotoUrlUtil {
	/**
	 * アカウントIDに対する写真一覧ページのパスを取得する<p>
	 * アカウントIDがnullや空文字の場合は、ログインページのURLを返す
	 * 
	 * @param accountId	アカウントID
	 * @return
	 */
	public static String getPhotoListUrl(String accountId) {
		if(Objects.isNull(accountId) || accountId.trim().isEmpty()) {
			return ApiRoutes.LOGIN;
		}
		
		return ApiRoutes.PHOTO_LIST.replace(ApiRoutes.PHOTO_ACCOUNT_ID, accountId);
	}
	
	/**
	 * アカウントIDに対する写真詳細ページのパスを取得する<p>
	 * アカウントIDがnullや空文字の場合は、ログインページのURLを返す
	 * 
	 * @param accountId	アカウントID
	 * @return
	 */
	public static String getPhotoDetailUrl(String accountId) {
		if(Objects.isNull(accountId) || accountId.trim().isEmpty()) {
			return ApiRoutes.LOGIN;
		}
		
		return ApiRoutes.PHOTO_DETAIL.replace(ApiRoutes.PHOTO_ACCOUNT_ID, accountId);
	}
	
	/**
	 * アカウントIDに対する写真登録・編集ページのパスを取得する<p>
	 * アカウントIDがnullや空文字の場合は、ログインページのURLを返す
	 * 
	 * @param accountId	アカウントID
	 * @return
	 */
	public static String getPhotoSettingUrl(String accountId) {
		if(Objects.isNull(accountId) || accountId.trim().isEmpty()) {
			return ApiRoutes.LOGIN;
		}
		
		return ApiRoutes.PHOTO_SETTING.replace(ApiRoutes.PHOTO_ACCOUNT_ID, accountId);
	}
	
	/**
	 * アカウントIDに対する写真保存APIのパスを取得する<p>
	 * アカウントIDがnullや空文字の場合は、ログインページのURLを返す
	 * 
	 * @param accountId	アカウントID
	 * @return
	 */
	public static String getPhotoSaveUrl(String accountId) {
		if(Objects.isNull(accountId) || accountId.trim().isEmpty()) {
			return ApiRoutes.LOGIN;
		}
		
		return ApiRoutes.SAVE_PHOTO.replace(ApiRoutes.PHOTO_ACCOUNT_ID, accountId);
	}
	
	/**
	 * アカウントIDに対する写真削除APIのパスを取得する<p>
	 * アカウントIDがnullや空文字の場合は、ログインページのURLを返す
	 * 
	 * @param accountId	アカウントID
	 * @return
	 */
	public static String getPhotoDeleteUrl(String accountId) {
		if(Objects.isNull(accountId) || accountId.trim().isEmpty()) {
			return ApiRoutes.LOGIN;
		}
		
		return ApiRoutes.DELETE_PHOTO.replace(ApiRoutes.PHOTO_ACCOUNT_ID, accountId);
	}
}