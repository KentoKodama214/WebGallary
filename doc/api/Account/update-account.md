# アカウント更新

## 基本情報

| 項目 | 内容 |
|------|------|
| エンドポイント | `PUT /api/v1/accounts/{accountId}` |
| 概要 | アカウント情報を更新する |
| 認証 | 必要（自分自身のアカウントのみ更新可能） |
| Content-Type | `application/json` |

## パスパラメータ

| パラメータ | 型 | 必須 | 説明 |
|-----------|-----|------|------|
| accountId | String | Yes | 更新対象のアカウントID |

## リクエストボディ

| パラメータ | 型 | 必須 | バリデーション | 説明 |
|-----------|-----|------|--------------|------|
| accountId | String | Yes | 半角英数字8〜16文字 | 新しいアカウントID |
| accountName | String | Yes | 空白不可（全角スペースのみも不可） | アカウント名 |
| newPassword | String | No | 半角英数字8文字以上（空文字の場合はパスワード変更なし） | 新しいパスワード |
| birthdate | String | No | `yyyy-MM-dd`形式、過去日付のみ | 生年月日 |
| sexKbn | String | No | `none` / `man` / `woman` | 性別区分 |
| birthplacePrefectureKbnCode | String | No | - | 出身地の都道府県コード |
| residentPrefectureKbnCode | String | No | - | 居住地の都道府県コード |
| freeMemo | String | No | - | 自由メモ |

## リクエスト例

```json
{
  "accountId": "testuser01",
  "accountName": "テストユーザー更新",
  "newPassword": "",
  "birthdate": "1990-01-15",
  "sexKbn": "man",
  "birthplacePrefectureKbnCode": "13",
  "residentPrefectureKbnCode": "14",
  "freeMemo": "プロフィールを更新しました"
}
```

## 成功レスポンス

**ステータスコード: 200 OK**

| フィールド | 型 | 説明 |
|-----------|-----|------|
| httpStatus | Integer | HTTPステータスコード |
| isDuplicateAccountId | Boolean | アカウントIDが重複しているか |
| isAccountIdChanged | Boolean | アカウントIDが変更されたか |
| isPasswordChanged | Boolean | パスワードが変更されたか |
| message | String | メッセージ |

```json
{
  "httpStatus": 200,
  "isDuplicateAccountId": false,
  "isAccountIdChanged": true,
  "isPasswordChanged": false,
  "message": ""
}
```

## エラーレスポンス

| ステータスコード | エラーコード | 説明 |
|-----------------|-------------|------|
| 400 Bad Request | E-C-0000 | 入力内容に誤りがある |
| 409 Conflict | E-C-0002 | アカウント更新に失敗 |
