package com.web.gallary.enumuration;

import com.web.gallary.constant.MessageConst;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorValueEnum {
	/** 入力内容に誤りがあります。再度入力してください。 */
	INVALID_INPUT("E-C-0000", MessageConst.ERR_INVALID_INPUT),
	
	/** アカウント登録でエラーが発生しました。登録をやり直してください。 */
	FAIL_TO_REGIST_ACCOUNT("E-C-0001", MessageConst.ERR_FAIL_TO_REGIST_ACCOUNT),

	/** アカウント更新でエラーが発生しました。更新をやり直してください。 */
	FAIL_TO_UPDATE_ACCOUNT("E-C-0002", MessageConst.ERR_FAIL_TO_UPDATE_ACCOUNT),
	
	/** アカウントを編集する権限がありません。 */
	NOT_AUTHORIZED_TO_EDIT_ACCOUNT("E-C-0003", MessageConst.ERR_NOT_AUTHORIZED_TO_EDIT_ACCOUNT),
	
	/** 写真登録でエラーが発生しました。登録をやり直してください。 */
	FAIL_TO_REGIST_PHOTO("E-P-0001", MessageConst.ERR_FAIL_TO_REGIST_PHOTO),
	
	/** 写真更新でエラーが発生しました。更新をやり直してください。 */
	FAIL_TO_UPDATE_PHOTO("E-P-0002", MessageConst.ERR_FAIL_TO_UPDATE_PHOTO),
	
	/** 写真削除でエラーが発生しました。削除をやり直してください。 */
	FAIL_TO_DELETE_PHOTO("E-P-0003", MessageConst.ERR_FAIL_TO_DELETE_PHOTO),
	
	/** 写真タグ登録でエラー発生しました。登録をやり直してください。 */
	FAIL_TO_REGIST_PHOTO_TAG("E-P-0004", MessageConst.ERR_FAIL_TO_REGIST_PHOTO_TAG),
	
	/** お気に入り登録でエラー発生しました。登録をやり直してください。 */
	FAIL_TO_REGIST_FAVORITE("E-P-0005", MessageConst.ERR_FAIL_TO_REGIST_FAVORITE),

	/** お気に入り解除でエラー発生しました。解除をやり直してください。 */
	FAIL_TO_CANCEL_FAVORITE("E-P-0006", MessageConst.ERR_FAIL_TO_CANCEL_FAVORITE),
	
	/** ポートフォリオ登録でエラー発生しました。登録をやり直してください。 */
	FAIL_TO_REGIST_PORTFOLIO("E-P-0007", MessageConst.ERR_FAIL_TO_REGIST_PORTFOLIO),
	
	/** 写真登録でエラーが発生しました。（既に同じファイル名でアップロード済みです） */
	DUPLICATE_PHOTO_FILE("E-P-0008", MessageConst.ERR_DUPLICATE_PHOTO_FILE),
	
	/** 写真を登録・編集する権限がありません。 */
	NOT_AUTHORIZED_TO_EDIT_PHOTO("E-P-0009", MessageConst.ERR_NOT_AUTHORIZED_TO_EDIT_PHOTO),

	/** 写真が存在しません。 */
	PHOTO_NOT_FOUND("E-P-0010", MessageConst.ERR_PHOTO_NOT_FOUND),
	
	/** 写真の登録枚数が上限に達しています。 */
	REACHED_REGISTRATION_LIMIT("E-P-0011", MessageConst.ERR_REACHED_REGISTRATION_LIMIT);
	
	/** エラーコード */
	private final String errorCode;
	
	/** エラーメッセージ */
	private final String errorMessage;
}