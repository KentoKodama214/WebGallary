# テーブル定義・カラム一覧

## 目次

- [1. common.account - アカウント](#1-commonaccount---アカウント)
- [2. common.kbn_mst - 区分マスタ](#2-commonkbn_mst---区分マスタ)
- [3. common.location_mst - ロケーションマスタ](#3-commonlocation_mst---ロケーションマスタ)
- [4. photo.photo_mst - 写真マスタ](#4-photophoto_mst---写真マスタ)
- [5. photo.photo_tag_mst - 写真タグマスタ](#5-photophoto_tag_mst---写真タグマスタ)
- [6. photo.photo_favorite - 写真お気に入り](#6-photophoto_favorite---写真お気に入り)

---

## 1. common.account - アカウント

ユーザーアカウント情報を管理するテーブル。

### カラム一覧

| No | カラム名 | データ型 | NOT NULL | デフォルト値 | 説明 |
|----|----------|----------|----------|-------------|------|
| 1 | account_no | serial | YES | (自動採番) | アカウント番号 |
| 2 | created_by | int | YES | - | 作成者 |
| 3 | created_at | timestamp with time zone | YES | - | 作成日時 |
| 4 | updated_by | int | YES | - | 更新者 |
| 5 | updated_at | timestamp with time zone | YES | - | 更新日時 |
| 6 | is_deleted | boolean | YES | false | 削除フラグ |
| 7 | account_id | varchar(20) | YES | - | アカウントID（8〜20文字の英数字） |
| 8 | account_name | varchar(50) | YES | - | アカウント名 |
| 9 | password | text | YES | - | パスワード（ハッシュ化済み） |
| 10 | birthdate | date | YES | '1900-01-01' | 生年月日（年月のみ使用、日は1固定） |
| 11 | sex_kbn | common.sex_enum | YES | 'none' | 性別区分（man/woman/none） |
| 12 | birthplace_prefecture_kbn_code | varchar(20) | YES | 'none' | 出身地の都道府県区分コード |
| 13 | resident_prefecture_kbn_code | varchar(20) | YES | 'none' | 居住地の都道府県区分コード |
| 14 | free_memo | text | YES | '""' | フリーメモ |
| 15 | authority_kbn | common.authority_enum | YES | - | 権限区分 |
| 16 | last_login_datetime | timestamp with time zone | YES | - | 最終ログイン日時 |
| 17 | login_failure_count | smallint | YES | 0 | ログイン失敗回数 |

### 制約

| 制約種別 | 制約名 | 対象カラム |
|----------|--------|------------|
| PRIMARY KEY | account_pkey | account_no |
| UNIQUE | account_id_unique | account_id |

---

## 2. common.kbn_mst - 区分マスタ

区分コードや分類を管理するマスタテーブル。都道府県情報等を格納する。

### カラム一覧

| No | カラム名 | データ型 | NOT NULL | デフォルト値 | 説明 |
|----|----------|----------|----------|-------------|------|
| 1 | kbn_class_code | varchar(20) | YES | - | 区分クラスコード |
| 2 | kbn_code | varchar(20) | YES | - | 区分コード |
| 3 | created_by | int | YES | - | 作成者 |
| 4 | created_at | timestamp with time zone | YES | - | 作成日時 |
| 5 | sort_order | int | YES | - | 表示順 |
| 6 | kbn_group_code | varchar(20) | YES | '""' | 区分グループコード |
| 7 | kbn_class_japanese_name | varchar(20) | YES | - | 区分クラス日本語名 |
| 8 | kbn_group_japanese_name | varchar(20) | YES | '""' | 区分グループ日本語名 |
| 9 | kbn_japanese_name | varchar(20) | YES | - | 区分日本語名 |
| 10 | kbn_class_english_name | varchar(20) | YES | '""' | 区分クラス英語名 |
| 11 | kbn_group_english_name | varchar(20) | YES | '""' | 区分グループ英語名 |
| 12 | kbn_english_name | varchar(20) | YES | '""' | 区分英語名 |
| 13 | explanation | text | YES | '""' | 説明 |

### 制約

| 制約種別 | 制約名 | 対象カラム |
|----------|--------|------------|
| PRIMARY KEY | kbn_mst_pkey | kbn_class_code, kbn_code |
| UNIQUE | kbn_mst_unique | kbn_class_japanese_name, kbn_japanese_name |

### マスタデータ

47都道府県が地域別にグループ分けされて格納されています。

| グループコード | グループ名 | 含まれる都道府県 |
|----------------|------------|-----------------|
| Hokkaido_Tohoku | 北海道・東北 | 北海道、青森県、岩手県、宮城県、秋田県、山形県、福島県 |
| Kanto | 関東 | 茨城県、栃木県、群馬県、埼玉県、千葉県、東京都、神奈川県 |
| Chubu | 中部 | 新潟県、富山県、石川県、福井県、山梨県、長野県、岐阜県、静岡県、愛知県 |
| Kansai | 関西 | 三重県、滋賀県、京都府、大阪府、兵庫県、奈良県、和歌山県 |
| Chugoku | 中国 | 鳥取県、島根県、岡山県、広島県、山口県 |
| Shikoku | 四国 | 徳島県、香川県、愛媛県、高知県 |
| Kyushu_Okinawa | 九州・沖縄 | 福岡県、佐賀県、長崎県、熊本県、大分県、宮崎県、鹿児島県、沖縄県 |

---

## 3. common.location_mst - ロケーションマスタ

撮影場所の情報を管理するマスタテーブル。

### カラム一覧

| No | カラム名 | データ型 | NOT NULL | デフォルト値 | 説明 |
|----|----------|----------|----------|-------------|------|
| 1 | id | serial | YES | (自動採番) | ロケーションID |
| 2 | account_no | int | YES | - | アカウント番号 |
| 3 | location_no | int | YES | - | ロケーション番号 |
| 4 | created_by | int | YES | - | 作成者 |
| 5 | created_at | timestamp with time zone | YES | - | 作成日時 |
| 6 | updated_by | int | YES | - | 更新者 |
| 7 | updated_at | timestamp with time zone | YES | - | 更新日時 |
| 8 | is_deleted | boolean | YES | false | 削除フラグ |
| 9 | location_name | text | YES | - | ロケーション名 |
| 10 | address | text | YES | '' | 住所 |
| 11 | latitude | decimal(11,4) | YES | - | 緯度 |
| 12 | longitude | decimal(11,4) | YES | - | 経度 |

### 制約

| 制約種別 | 制約名 | 対象カラム |
|----------|--------|------------|
| PRIMARY KEY | location_mst_pkey | id |
| UNIQUE | location_mst_unique1 | account_no, location_no |
| UNIQUE | location_mst_unique2 | account_no, location_name |
| FOREIGN KEY | location_mst_fkey | account_no → common.account(account_no) |

---

## 4. photo.photo_mst - 写真マスタ

写真のメタデータおよびEXIF情報を管理するテーブル。

### カラム一覧

| No | カラム名 | データ型 | NOT NULL | デフォルト値 | 説明 |
|----|----------|----------|----------|-------------|------|
| 1 | id | serial | YES | (自動採番) | 写真ID |
| 2 | account_no | int | YES | - | アカウント番号 |
| 3 | photo_no | int | YES | - | 写真番号 |
| 4 | created_by | int | YES | - | 作成者 |
| 5 | created_at | timestamp with time zone | YES | - | 作成日時 |
| 6 | updated_by | int | YES | - | 更新者 |
| 7 | updated_at | timestamp with time zone | YES | - | 更新日時 |
| 8 | is_deleted | boolean | YES | false | 削除フラグ |
| 9 | photo_at | timestamp with time zone | YES | - | 撮影日時 |
| 10 | location_no | int | YES | - | ロケーション番号 |
| 11 | image_file_path | text | YES | - | 画像ファイルパス |
| 12 | photo_japanese_title | varchar(100) | YES | - | 写真タイトル（日本語） |
| 13 | photo_english_title | varchar(100) | YES | '""' | 写真タイトル（英語） |
| 14 | caption | text | YES | '""' | キャプション |
| 15 | direction_kbn | photo.direction_enum | YES | 'none' | 写真の向き（vertical/horizontal/square/none） |
| 16 | focal_length | int | YES | - | 焦点距離（mm） |
| 17 | f_value | decimal(5,2) | YES | - | F値 |
| 18 | shutter_speed | decimal(10,5) | YES | - | シャッタースピード（秒） |
| 19 | iso | int | YES | - | ISO感度 |

### 制約

| 制約種別 | 制約名 | 対象カラム |
|----------|--------|------------|
| PRIMARY KEY | photo_mst_pkey | id |
| UNIQUE | photo_mst_unique | account_no, photo_no |
| FOREIGN KEY | photo_mst_fkey | account_no → common.account(account_no) |

---

## 5. photo.photo_tag_mst - 写真タグマスタ

写真に付与されたタグを管理するテーブル。

### カラム一覧

| No | カラム名 | データ型 | NOT NULL | デフォルト値 | 説明 |
|----|----------|----------|----------|-------------|------|
| 1 | id | serial | YES | (自動採番) | タグID |
| 2 | account_no | int | YES | - | アカウント番号 |
| 3 | photo_no | int | YES | - | 写真番号 |
| 4 | tag_no | int | YES | - | タグ番号 |
| 5 | created_by | int | YES | - | 作成者 |
| 6 | created_at | timestamp with time zone | YES | - | 作成日時 |
| 7 | tag_japanese_name | varchar(20) | YES | - | タグ名（日本語） |
| 8 | tag_english_name | varchar(20) | YES | '""' | タグ名（英語） |

### 制約

| 制約種別 | 制約名 | 対象カラム |
|----------|--------|------------|
| PRIMARY KEY | photo_tag_mst_pkey | id |
| UNIQUE | photo_tag_mst_unique | account_no, photo_no, tag_no |
| FOREIGN KEY | photo_tag_mst_fkey | (account_no, photo_no) → photo.photo_mst(account_no, photo_no) |

---

## 6. photo.photo_favorite - 写真お気に入り

ユーザーが写真をお気に入りに登録する情報を管理するテーブル。

### カラム一覧

| No | カラム名 | データ型 | NOT NULL | デフォルト値 | 説明 |
|----|----------|----------|----------|-------------|------|
| 1 | id | serial | YES | (自動採番) | お気に入りID |
| 2 | account_no | int | YES | - | アカウント番号（お気に入りしたユーザー） |
| 3 | favorite_photo_account_no | int | YES | - | 写真所有者のアカウント番号 |
| 4 | favorite_photo_no | int | YES | - | 写真番号 |
| 5 | created_by | int | YES | - | 作成者 |
| 6 | created_at | timestamp with time zone | YES | - | 作成日時 |

### 制約

| 制約種別 | 制約名 | 対象カラム |
|----------|--------|------------|
| PRIMARY KEY | photo_favorite_pkey | id |
| UNIQUE | photo_favorite_unique | account_no, favorite_photo_account_no, favorite_photo_no |
| FOREIGN KEY | photo_favorite_fkey1 | account_no → common.account(account_no) |
| FOREIGN KEY | photo_favorite_fkey2 | (favorite_photo_account_no, favorite_photo_no) → photo.photo_mst(account_no, photo_no) |
