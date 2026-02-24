# データ辞書

全テーブルのカラムを物理名・論理名・データ型で重複排除し、物理名の昇順で一覧化したものです。

## カラム辞書

| No | 物理名 | 論理名 | データ型 | NOT NULL | デフォルト値 | 使用テーブル |
|----|--------|--------|----------|----------|-------------|-------------|
| 1 | account_id | アカウントID | varchar(20) | YES | - | account |
| 2 | account_name | アカウント名 | varchar(50) | YES | - | account |
| 3 | account_no | アカウント番号 | serial / int | YES | (自動採番) / - | account, location_mst, photo_mst, photo_tag_mst, photo_favorite |
| 4 | address | 住所 | text | YES | '' | location_mst |
| 5 | authority_kbn | 権限区分 | common.authority_enum | YES | - | account |
| 6 | birthdate | 生年月日 | date | YES | '1900-01-01' | account |
| 7 | birthplace_prefecture_kbn_code | 出身地都道府県区分コード | varchar(20) | YES | 'none' | account |
| 8 | caption | キャプション | text | YES | '""' | photo_mst |
| 9 | created_at | 作成日時 | timestamp with time zone | YES | - | account, kbn_mst, location_mst, photo_mst, photo_tag_mst, photo_favorite |
| 10 | created_by | 作成者 | int | YES | - | account, kbn_mst, location_mst, photo_mst, photo_tag_mst, photo_favorite |
| 11 | direction_kbn | 写真の向き | photo.direction_enum | YES | 'none' | photo_mst |
| 12 | explanation | 説明 | text | YES | '""' | kbn_mst |
| 13 | f_value | F値 | decimal(5,2) | YES | - | photo_mst |
| 14 | favorite_photo_account_no | 写真所有者のアカウント番号 | int | YES | - | photo_favorite |
| 15 | favorite_photo_no | お気に入り写真番号 | int | YES | - | photo_favorite |
| 16 | focal_length | 焦点距離 | int | YES | - | photo_mst |
| 17 | free_memo | フリーメモ | text | YES | '""' | account |
| 18 | id | ID | serial | YES | (自動採番) | location_mst, photo_mst, photo_tag_mst, photo_favorite |
| 19 | image_file_path | 画像ファイルパス | text | YES | - | photo_mst |
| 20 | is_deleted | 削除フラグ | boolean | YES | false | account, location_mst, photo_mst |
| 21 | iso | ISO感度 | int | YES | - | photo_mst |
| 22 | kbn_class_code | 区分クラスコード | varchar(20) | YES | - | kbn_mst |
| 23 | kbn_class_english_name | 区分クラス英語名 | varchar(20) | YES | '""' | kbn_mst |
| 24 | kbn_class_japanese_name | 区分クラス日本語名 | varchar(20) | YES | - | kbn_mst |
| 25 | kbn_code | 区分コード | varchar(20) | YES | - | kbn_mst |
| 26 | kbn_english_name | 区分英語名 | varchar(20) | YES | '""' | kbn_mst |
| 27 | kbn_group_code | 区分グループコード | varchar(20) | YES | '""' | kbn_mst |
| 28 | kbn_group_english_name | 区分グループ英語名 | varchar(20) | YES | '""' | kbn_mst |
| 29 | kbn_group_japanese_name | 区分グループ日本語名 | varchar(20) | YES | '""' | kbn_mst |
| 30 | kbn_japanese_name | 区分日本語名 | varchar(20) | YES | - | kbn_mst |
| 31 | last_login_datetime | 最終ログイン日時 | timestamp with time zone | YES | - | account |
| 32 | latitude | 緯度 | decimal(11,4) | YES | - | location_mst |
| 33 | location_name | ロケーション名 | text | YES | - | location_mst |
| 34 | location_no | ロケーション番号 | int | YES | - | location_mst, photo_mst |
| 35 | login_failure_count | ログイン失敗回数 | smallint | YES | 0 | account |
| 36 | longitude | 経度 | decimal(11,4) | YES | - | location_mst |
| 37 | password | パスワード | text | YES | - | account |
| 38 | photo_at | 撮影日時 | timestamp with time zone | YES | - | photo_mst |
| 39 | photo_english_title | 写真タイトル（英語） | varchar(100) | YES | '""' | photo_mst |
| 40 | photo_japanese_title | 写真タイトル（日本語） | varchar(100) | YES | - | photo_mst |
| 41 | photo_no | 写真番号 | int | YES | - | photo_mst, photo_tag_mst |
| 42 | resident_prefecture_kbn_code | 居住地都道府県区分コード | varchar(20) | YES | 'none' | account |
| 43 | sex_kbn | 性別区分 | common.sex_enum | YES | 'none' | account |
| 44 | shutter_speed | シャッタースピード | decimal(10,5) | YES | - | photo_mst |
| 45 | sort_order | 表示順 | int | YES | - | kbn_mst |
| 46 | tag_english_name | タグ名（英語） | varchar(20) | YES | '""' | photo_tag_mst |
| 47 | tag_japanese_name | タグ名（日本語） | varchar(20) | YES | - | photo_tag_mst |
| 48 | tag_no | タグ番号 | int | YES | - | photo_tag_mst |
| 49 | updated_at | 更新日時 | timestamp with time zone | YES | - | account, location_mst, photo_mst |
| 50 | updated_by | 更新者 | int | YES | - | account, location_mst, photo_mst |

## カスタム型辞書

| No | 型名 | 定義値 | 説明 | 使用カラム |
|----|------|--------|------|-----------|
| 1 | common.sex_enum | man, woman, none | 性別区分 | account.sex_kbn |
| 2 | common.authority_enum | mini-user, normal-user, special-user, administrator | 権限区分 | account.authority_kbn |
| 3 | photo.direction_enum | vertical, horizontal, square, none | 写真の向き | photo_mst.direction_kbn |
