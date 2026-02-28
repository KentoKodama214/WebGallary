# 写真削除

## 基本情報

| 項目 | 内容 |
|------|------|
| エンドポイント | `DELETE /api/v1/accounts/{photoAccountId}/photos` |
| 概要 | 写真を削除する |
| 認証 | 必要（自分自身の写真のみ削除可能） |
| Content-Type | `application/json` |

## パスパラメータ

| パラメータ | 型 | 必須 | 説明 |
|-----------|-----|------|------|
| photoAccountId | String | Yes | 写真を所有するアカウントのID |

## リクエストボディ

| パラメータ | 型 | 必須 | バリデーション | 説明 |
|-----------|-----|------|--------------|------|
| accountNo | Integer | Yes | 正の整数 | アカウント番号 |
| photoNo | Integer | Yes | 正の整数 | 写真番号 |
| imageFilePath | String | Yes | 空白不可 | 画像ファイルパス |

## リクエスト例

```json
{
  "accountNo": 1,
  "photoNo": 1,
  "imageFilePath": "/image/testuser01/photo01.jpg"
}
```

## 成功レスポンス

**ステータスコード: 200 OK**

| フィールド | 型 | 説明 |
|-----------|-----|------|
| httpStatus | Integer | HTTPステータスコード |
| isSuccess | Boolean | 処理成功フラグ |
| message | String | メッセージ |

```json
{
  "httpStatus": 200,
  "isSuccess": true,
  "message": "写真削除が完了しました。"
}
```

## エラーレスポンス

| ステータスコード | エラーコード | 説明 |
|-----------------|-------------|------|
| 400 Bad Request | E-C-0000 | 入力内容に誤りがある |
| 403 Forbidden | E-P-0008 | 写真を削除する権限がない |
| 409 Conflict | E-P-0003 | 写真削除に失敗 |
