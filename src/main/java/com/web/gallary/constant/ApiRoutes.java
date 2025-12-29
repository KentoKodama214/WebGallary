package com.web.gallary.constant;

/**
 * エンドポイントを管理するクラス
 */
public final class ApiRoutes {
	// 共通
	/** ログインページ */
	public static final String LOGIN = "/login";
	/** ログアウト */
	public static final String LOGOUT = "/logout";
	/** ヘッダー */
	public static final String HEADER = "/header";
	/** フッター */
	public static final String FOOTER = "/footer";
	/** エラー */
	public static final String ERROR = "/error";
	/** エラーページ */
	public static final String ERROR_PAGE = "/error_page";
	
	// アカウント関連
	/** アカウントID */
	public static final String ACCOUNT_ID = "{accountId}";
	/** アカウント登録ページ/API */
	public static final String REGISTER = "/register";
	/** アカウント更新API */
	public static final String UPDATE = "/update";
	/** アカウント設定ページ */
	public static final String ACCOUNT_SETTING = "/" + ACCOUNT_ID + "/account_setting";
	/** アカウント一覧ページ */
	public static final String ACCOUNT_LIST = "/account_list";
	
	// 写真関連
	/** 写真 */
	public static final String PHOTO = "/photo";
	/** お気に入り */
	public static final String FAVORITE = "/favorite";
	/** 写真アカウントID */
	public static final String PHOTO_ACCOUNT_ID = "{photoAccountId}";
	/** 写真一覧ページ */
	public static final String PHOTO_LIST = PHOTO + "/" + PHOTO_ACCOUNT_ID + "/photo_list";
	/** 写真一覧取得API */
	public static final String GET_PHOTO_LIST = PHOTO_LIST + "/get";
	/** 写真詳細ページ */
	public static final String PHOTO_DETAIL = PHOTO + "/" + PHOTO_ACCOUNT_ID + "/photo_detail";
	/** 写真登録・編集ページ */
	public static final String PHOTO_SETTING = PHOTO + "/" + PHOTO_ACCOUNT_ID + "/photo_setting";
	/** 写真保存API */
	public static final String SAVE_PHOTO = PHOTO + "/" + PHOTO_ACCOUNT_ID + "/save";
	/** 写真削除API */
	public static final String DELETE_PHOTO = PHOTO + "/" + PHOTO_ACCOUNT_ID + "/delete";
	/** お気に入り登録API */
	public static final String ADD_FAVORITE = PHOTO + FAVORITE + "/add";
	/** お気に入り解除API */
	public static final String CANDEL_FAVORITE = PHOTO + FAVORITE + "/delete";
}