# エラー定義

## 共通エラーレスポンス形式

### バリデーションエラー（400 Bad Request）

入力内容に誤りがある場合に返却されます。

| フィールド | 型 | 説明 |
|-----------|-----|------|
| httpStatus | Integer | HTTPステータスコード（400） |
| isSuccess | Boolean | 処理成功フラグ（false） |
| message | String | エラーメッセージ |

```json
{
  "httpStatus": 400,
  "isSuccess": false,
  "message": "入力内容に誤りがあります。再度入力してください。"
}
```

### 業務エラー（403 Forbidden / 409 Conflict）

権限エラーやデータ競合が発生した場合に返却されます。

| フィールド | 型 | 説明 |
|-----------|-----|------|
| httpStatus | Integer | HTTPステータスコード |
| errorCode | String | エラーコード |
| errorMessage | String | エラーメッセージ |
| goBackPageUrl | String | 戻り先ページのURL |

```json
{
  "httpStatus": 409,
  "errorCode": "E-P-0001",
  "errorMessage": "写真の登録に失敗しました。",
  "goBackPageUrl": "/photo/testuser01/photo_list"
}
```

---

## 例外とHTTPステータスの対応表

| 例外クラス | HTTPステータス | 説明 |
|-----------|---------------|------|
| BadRequestException | 400 Bad Request | 入力パラメータが不正 |
| PhotoNotAdditableException | 400 Bad Request | 写真登録上限に達している |
| ForbiddenAccountException | 403 Forbidden | リソースへのアクセス権限がない |
| RegistFailureException | 409 Conflict | データ登録に失敗 |
| UpdateFailureException | 409 Conflict | データ更新に失敗 |
| FileDuplicateException | 409 Conflict | ファイル名が重複している |

---

## エラーコード一覧

### 共通・アカウントエラー（E-C-xxxx）

| エラーコード | 説明 |
|-------------|------|
| E-C-0000 | 入力内容に誤りがある |
| E-C-0001 | アカウント登録に失敗 |
| E-C-0002 | アカウント更新に失敗 |
| E-C-0003 | アカウントの編集権限がない |

### 写真エラー（E-P-xxxx）

| エラーコード | 説明 |
|-------------|------|
| E-P-0001 | 写真登録に失敗 |
| E-P-0002 | 写真更新に失敗 |
| E-P-0003 | 写真削除に失敗 |
| E-P-0004 | 写真タグ登録に失敗 |
| E-P-0005 | お気に入り登録に失敗 |
| E-P-0006 | お気に入り解除に失敗 |
| E-P-0007 | 写真ファイルが重複している |
| E-P-0008 | 写真の登録・編集権限がない |
| E-P-0009 | 写真が見つからない |
| E-P-0010 | 写真登録上限に達している |
