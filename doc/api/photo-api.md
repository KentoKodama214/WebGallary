# 写真管理API

## 1. 写真一覧取得

### 基本情報

| 項目 | 内容 |
|------|------|
| エンドポイント | `GET /api/v1/accounts/{photoAccountId}/photos` |
| 概要 | 指定アカウントの写真一覧を取得する |
| 認証 | 不要（お気に入り状態は認証済みユーザーのみ反映） |

### パスパラメータ

| パラメータ | 型 | 必須 | 説明 |
|-----------|-----|------|------|
| photoAccountId | String | Yes | 写真を所有するアカウントのID |

### クエリパラメータ

| パラメータ | 型 | 必須 | デフォルト | バリデーション | 説明 |
|-----------|-----|------|-----------|--------------|------|
| directionKbn | String | No | `none` | `none` / `vertical` / `horizontal` / `square` | 写真の向きでフィルタ |
| isFavorite | Boolean | No | `false` | `true` / `false` | お気に入りのみ表示 |
| tagList | String | No | - | スペース区切り（半角・全角対応） | タグ名でフィルタ |
| sortBy | String | No | `photoAt` | `photoAt` / `favorite` / `season` | ソート順 |
| pageNo | Integer | No | `1` | 正の整数 | ページ番号 |

### リクエスト例

```
GET /api/v1/accounts/testuser01/photos?directionKbn=horizontal&sortBy=favorite&pageNo=1
```

### 成功レスポンス

**ステータスコード: 200 OK**

| フィールド | 型 | 説明 |
|-----------|-----|------|
| isLast | Boolean | 最終ページかどうか |
| photoList | Array | 写真情報の配列 |

#### photoListの各要素

| フィールド | 型 | 説明 |
|-----------|-----|------|
| accountNo | Integer | アカウント番号 |
| photoNo | Integer | 写真番号 |
| isFavorite | Boolean | お気に入り登録済みか |
| imageFilePath | String | 画像ファイルパス |
| caption | String | キャプション |
| directionKbn | String | 写真の向き |

```json
{
  "isLast": false,
  "photoList": [
    {
      "accountNo": 1,
      "photoNo": 1,
      "isFavorite": true,
      "imageFilePath": "/image/testuser01/photo01.jpg",
      "caption": "東京タワーの夜景",
      "directionKbn": "horizontal"
    },
    {
      "accountNo": 1,
      "photoNo": 2,
      "isFavorite": false,
      "imageFilePath": "/image/testuser01/photo02.jpg",
      "caption": "富士山",
      "directionKbn": "horizontal"
    }
  ]
}
```

---

## 2. 写真登録

### 基本情報

| 項目 | 内容 |
|------|------|
| エンドポイント | `POST /api/v1/accounts/{photoAccountId}/photos` |
| 概要 | 新しい写真を登録する |
| 認証 | 必要（自分自身の写真のみ登録可能） |
| Content-Type | `multipart/form-data` |

### パスパラメータ

| パラメータ | 型 | 必須 | 説明 |
|-----------|-----|------|------|
| photoAccountId | String | Yes | 写真を登録するアカウントのID |

### リクエストボディ

| パラメータ | 型 | 必須 | バリデーション | 説明 |
|-----------|-----|------|--------------|------|
| accountNo | Integer | Yes | 正の整数 | アカウント番号 |
| photoNo | Integer | No | 正の整数 | 写真番号 |
| favoriteCount | Integer | No | - | お気に入り数 |
| isFavorite | Boolean | No | - | お気に入りフラグ |
| photoAt | String | No | 過去日時、`yyyy-MM-ddTHH:mm:ss`形式 | 撮影日時 |
| locationNo | Integer | No | - | ロケーション番号 |
| address | String | No | - | 住所 |
| latitude | Decimal | No | - | 緯度 |
| longitude | Decimal | No | - | 経度 |
| locationName | String | No | - | ロケーション名 |
| imageFile | File | 条件付き | `imageFilePath`が未指定の場合は必須、最大5MB | 画像ファイル |
| imageFilePath | String | 条件付き | `imageFile`が未指定の場合は必須 | 画像ファイルパス |
| photoJapaneseTitle | String | No | - | 写真タイトル（日本語） |
| photoEnglishTitle | String | No | - | 写真タイトル（英語） |
| caption | String | No | - | キャプション |
| directionKbn | String | Yes | `none` / `vertical` / `horizontal` / `square` | 写真の向き |
| focalLength | Integer | No | 正の整数 | 焦点距離 |
| fValue | Decimal | No | 正の小数 | F値 |
| shutterSpeed | Decimal | No | 正の小数 | シャッタースピード |
| iso | Integer | No | 正の整数 | ISO感度 |
| photoTagRegistRequestList | Array | No | - | タグ情報の配列 |

#### photoTagRegistRequestListの各要素

| パラメータ | 型 | 必須 | バリデーション | 説明 |
|-----------|-----|------|--------------|------|
| accountNo | Integer | Yes | 正の整数 | アカウント番号 |
| photoNo | Integer | Yes | 正の整数 | 写真番号 |
| tagNo | Integer | Yes | 正の整数 | タグ番号 |
| tagJapaneseName | String | Yes | スペース不可（半角・全角） | タグ名（日本語） |
| tagEnglishName | String | No | - | タグ名（英語） |

### 成功レスポンス

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
  "message": "写真登録が完了しました。"
}
```

### エラーレスポンス

| ステータスコード | エラーコード | 説明 |
|-----------------|-------------|------|
| 400 Bad Request | E-C-0000 | 入力内容に誤りがある |
| 400 Bad Request | E-P-0010 | 写真登録上限に達している |
| 403 Forbidden | E-P-0008 | 写真を登録する権限がない |
| 409 Conflict | E-P-0007 | 画像ファイルが重複している |
| 409 Conflict | E-P-0001 | 写真登録に失敗 |

---

## 3. 写真更新

### 基本情報

| 項目 | 内容 |
|------|------|
| エンドポイント | `PUT /api/v1/accounts/{photoAccountId}/photos` |
| 概要 | 写真情報を更新する |
| 認証 | 必要（自分自身の写真のみ更新可能） |
| Content-Type | `multipart/form-data` |

### パスパラメータ

| パラメータ | 型 | 必須 | 説明 |
|-----------|-----|------|------|
| photoAccountId | String | Yes | 写真を所有するアカウントのID |

### リクエストボディ

写真登録と同一のパラメータを使用します。詳細は「[2. 写真登録](#2-写真登録)」を参照してください。

### 成功レスポンス

**ステータスコード: 200 OK**

```json
{
  "httpStatus": 200,
  "isSuccess": true,
  "message": "写真更新が完了しました。"
}
```

### エラーレスポンス

| ステータスコード | エラーコード | 説明 |
|-----------------|-------------|------|
| 400 Bad Request | E-C-0000 | 入力内容に誤りがある |
| 403 Forbidden | E-P-0008 | 写真を更新する権限がない |
| 409 Conflict | E-P-0007 | 画像ファイルが重複している |
| 409 Conflict | E-P-0002 | 写真更新に失敗 |

---

## 4. 写真削除

### 基本情報

| 項目 | 内容 |
|------|------|
| エンドポイント | `DELETE /api/v1/accounts/{photoAccountId}/photos` |
| 概要 | 写真を削除する |
| 認証 | 必要（自分自身の写真のみ削除可能） |
| Content-Type | `application/json` |

### パスパラメータ

| パラメータ | 型 | 必須 | 説明 |
|-----------|-----|------|------|
| photoAccountId | String | Yes | 写真を所有するアカウントのID |

### リクエストボディ

| パラメータ | 型 | 必須 | バリデーション | 説明 |
|-----------|-----|------|--------------|------|
| accountNo | Integer | Yes | 正の整数 | アカウント番号 |
| photoNo | Integer | Yes | 正の整数 | 写真番号 |
| imageFilePath | String | Yes | 空白不可 | 画像ファイルパス |

### リクエスト例

```json
{
  "accountNo": 1,
  "photoNo": 1,
  "imageFilePath": "/image/testuser01/photo01.jpg"
}
```

### 成功レスポンス

**ステータスコード: 200 OK**

```json
{
  "httpStatus": 200,
  "isSuccess": true,
  "message": "写真削除が完了しました。"
}
```

### エラーレスポンス

| ステータスコード | エラーコード | 説明 |
|-----------------|-------------|------|
| 400 Bad Request | E-C-0000 | 入力内容に誤りがある |
| 403 Forbidden | E-P-0008 | 写真を削除する権限がない |
| 409 Conflict | E-P-0003 | 写真削除に失敗 |
