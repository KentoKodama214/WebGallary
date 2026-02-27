# お気に入り解除

## 基本情報

| 項目 | 内容 |
|------|------|
| エンドポイント | `DELETE /api/v1/photos/favorites` |
| 概要 | 写真のお気に入りを解除する |
| 認証 | 必要 |
| Content-Type | `application/json` |

## リクエストボディ

| パラメータ | 型 | 必須 | バリデーション | 説明 |
|-----------|-----|------|--------------|------|
| favoritePhotoAccountNo | Integer | Yes | 正の整数 | お気に入り解除対象の写真を所有するアカウント番号 |
| favoritePhotoNo | Integer | Yes | 正の整数 | お気に入り解除対象の写真番号 |

## リクエスト例

```json
{
  "favoritePhotoAccountNo": 1,
  "favoritePhotoNo": 5
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
  "message": "お気に入りを解除しました。"
}
```

## エラーレスポンス

| ステータスコード | エラーコード | 説明 |
|-----------------|-------------|------|
| 400 Bad Request | E-C-0000 | 入力内容に誤りがある |
| 409 Conflict | E-P-0006 | お気に入り解除に失敗 |
