# アカウント登録

## 基本情報

| 項目 | 内容 |
|------|------|
| エンドポイント | `POST /api/v1/accounts` |
| 概要 | 新規アカウントを登録する |
| 認証 | 不要 |
| Content-Type | `application/json` |

## リクエストボディ

| パラメータ | 型 | 必須 | バリデーション | 説明 |
|-----------|-----|------|--------------|------|
| accountId | String | Yes | 半角英数字8〜16文字 | アカウントID |
| accountName | String | Yes | 空白不可（全角スペースのみも不可） | アカウント名 |
| password | String | Yes | 半角英数字8文字以上 | パスワード |
| birthdate | String | No | `yyyy-MM-dd`形式、過去日付のみ | 生年月日 |
| sexKbn | String | No | `none` / `man` / `woman` | 性別区分 |
| birthplacePrefectureKbnCode | String | No | - | 出身地の都道府県コード |
| residentPrefectureKbnCode | String | No | - | 居住地の都道府県コード |
| freeMemo | String | No | - | 自由メモ |

## リクエスト例

```json
{
  "accountId": "testuser01",
  "accountName": "テストユーザー",
  "password": "password01",
  "birthdate": "1990-01-15",
  "sexKbn": "man",
  "birthplacePrefectureKbnCode": "Hokkaido",
  "residentPrefectureKbnCode": "Aomori",
  "freeMemo": "よろしくお願いします"
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
  "message": ""
}
```

## エラーレスポンス

| ステータスコード | エラーコード | 説明 |
|-----------------|-------------|------|
| 400 Bad Request | E-C-0000 | 入力内容に誤りがある |
| 409 Conflict | E-C-0001 | アカウント登録に失敗（アカウントID重複など） |
