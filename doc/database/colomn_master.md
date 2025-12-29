# カラム一覧
| 物理名 | 論理名 | データ型 | NULL許可 | デフォルト | コメント |
|:---|:---|:---|---|:---|:---|
| account_id | アカウントID | varchar(20) | NOT NULL | - | 半角英数字で、8文字以上20文字以下 |
| account_name | アカウント名 | varchar(50) | NOT NULL | - |  |
| account_no | アカウント番号 | serial | NOT NULL | - | アカウントを一意に特定するための番号 |
| authority_kbn_code | 権限区分コード | varchar(20) | NOT NULL | - | mini-user: 簡易ユーザー<br>normal-user: 一般ユーザー<br>special-user: 特別ユーザー<br>administrator: 管理者 |
| birthdate | 生年月日 | date | NOT NULL | 1900-01-01 | 個人情報管理の観点で、必須入力なし、かつ年月まで。データ登録時にすべて1日に変換する |
| birthplace_prefecture_kbn_code | 出身都道府県区分コード | varchar(20) | NOT NULL | none | none: 未設定<br>分類コード：prefecture |
| caption | キャプション | text | NOT NULL | "" |  |
| created_at | 作成日時 | timestamp with time zone | NOT NULL | - | レコードが作成された日時。DBサーバのシステム日付を保持する |
| created_by | 作成者 | int | NOT NULL | - | コードを作成したアカウント番号。システム側が作成した場合は'0'を入れる |
| direction_kbn_code | 向き区分コード | varchar(20) | NOT NULL | - | vertical: 縦<br>horizontal: 横<br>square: 正方形 |
| explanation | 説明 | text | NOT NULL | "" |  |
| f_value | F値 | decimal(5,2) | NOT NULL | - |  |
| favorite_photo_account_no | お気に入り写真のアカウント番号 | int | NOT NULL | - |  |
| favorite_photo_no | お気に入り写真番号 | int | NOT NULL | - |  |
| focal_length | 焦点距離 | int | NOT NULL | - | 単位: mm |
| free_memo | フリーメモ | text | NOT NULL | "" |  |
| image_file_path | 画像ファイルパス | text | NOT NULL | - | 空文字不可 |
| is_deleted | 削除フラグ | boolean | NOT NULL | false |  |
| iso | ISO | int | NOT NULL | - |  |
| kbn_class_code | 区分分類コード | varchar(20) | NOT NULL | - |  |
| kbn_class_english_name | 区分分類英語名 | varchar(20) | NOT NULL | "" |  |
| kbn_class_japanese_name | 区分分類日本語名 | varchar(20) | NOT NULL | - | 空文字不可 |
| kbn_code | 区分コード | varchar(20) | NOT NULL | - |  |
| kbn_english_name | 区分英語名 | varchar(20) | NOT NULL | "" |  |
| kbn_group_code | 区分グループコード | varchar(20) | NOT NULL | "" |  |
| kbn_group_english_name | 区分グループ英語名 | varchar(20) | NOT NULL | "" |  |
| kbn_group_japanese_name | 区分グループ日本語名 | varchar(20) | NOT NULL | "" |  |
| kbn_japanese_name | 区分日本語名 | varchar(20) | NOT NULL | - | 空文字不可 |
| last_login_datetime | 最終ログイン日時 | timestamp with time zone | NOT NULL | - |  |
| location_no | ロケーション番号 | int | NOT NULL | - |  |
| login_failure_count | ログイン失敗回数 | smallint | NOT NULL | 0 |  |
| password | パスワード | text | NOT NULL | - |  |
| photo_at | 撮影日時 | timestamp with time zone | NOT NULL | - |  |
| photo_english_title | 写真タイトル英語名 | varchar(100) | NOT NULL | "" |  |
| photo_japanese_title | 写真タイトル日本語名 | varchar(100) | NOT NULL | - | 空文字不可 |
| photo_no | 写真番号 | int | NOT NULL | - |  |
| resident_prefecture_kbn_code | 在住都道府県区分コード | varchar(20) | NOT NULL | none | none: 未設定<br>分類コード：prefecture |
| sex_kbn_code | 性別区分コード | varchar(20) | NOT NULL | none | none: 未設定<br>man: 男<br>woman: 女 |
| shutter_speed | シャッタースピード | decimal(10,5) | NOT NULL | - | 単位: 秒 |
| sort_order | 並び順 | int | NOT NULL | - | |
| tag_english_name | タグ英語名 | varchar(20) | NOT NULL | "" |  |
| tag_japanese_name | タグ日本語名 | varchar(20) | NOT NULL | - | 空文字不可 |
| tag_no | タグ番号 | int | NOT NULL | - |  |
| updated_at | 更新日時 | timestamp with time zone | NOT NULL | - |  |
| updated_by | 更新者 | int | NOT NULL | - |  |
