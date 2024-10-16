package com.web.gallary.enumuration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorValues {
	/** 入力内容に誤りがあります。再度入力してください。 */
	EC0000("E-C-0000", "入力内容に誤りがあります。再度入力してください。"),
	
	/** アカウント登録でエラーが発生しました。登録をやり直してください。 */
	EC0001("E-C-0001", "アカウント登録でエラーが発生しました。登録をやり直してください。"),

	/** アカウント更新でエラーが発生しました。更新をやり直してください。 */
	EC0002("E-C-0002", "アカウント更新でエラーが発生しました。更新をやり直してください。"),
	
	/** アカウントを編集する権限がありません。 */
	EC0003("E-C-0003", "アカウントを編集する権限がありません。"),
	
	/** 写真登録でエラーが発生しました。登録をやり直してください。 */
	EP0001("E-P-0001", "写真登録でエラーが発生しました。登録をやり直してください。"),
	
	/** 写真更新でエラーが発生しました。更新をやり直してください。 */
	EP0002("E-P-0002", "写真更新でエラーが発生しました。更新をやり直してください。"),
	
	/** 写真削除でエラーが発生しました。削除をやり直してください。 */
	EP0003("E-P-0003", "写真削除でエラーが発生しました。削除をやり直してください。"),
	
	/** 写真タグ登録でエラー発生しました。登録をやり直してください。 */
	EP0004("E-P-0004", "写真タグ登録でエラー発生しました。登録をやり直してください。"),
	
	/** お気に入り登録でエラー発生しました。登録をやり直してください。 */
	EP0005("E-P-0005", "お気に入り登録でエラー発生しました。登録をやり直してください。"),

	/** お気に入り解除でエラー発生しました。解除をやり直してください。 */
	EP0006("E-P-0006", "お気に入り解除でエラー発生しました。解除をやり直してください。"),
	
	/** ポートフォリオ登録でエラー発生しました。登録をやり直してください。 */
	EP0007("E-P-0007", "ポートフォリオ登録でエラー発生しました。登録をやり直してください。"),
	
	/** 写真登録でエラーが発生しました。（既に同じファイル名でアップロード済みです） */
	EP0008("E-P-0008", "写真登録でエラーが発生しました。（既に同じファイル名でアップロード済みです）"),
	
	/** 写真を登録・編集する権限がありません。 */
	EP0009("E-P-0009", "写真を登録・編集する権限がありません。"),

	/** 写真が存在しません。 */
	EP0010("E-P-0010", "写真が存在しません。"),
	
	/** 写真の登録枚数が上限に達しています。 */
	EP0011("E-P-0011", "写真の登録枚数が上限に達しています。");
	
	/** エラーコード */
	private final String errorCode;
	
	/** エラーメッセージ */
	private final String errorMessage;
}