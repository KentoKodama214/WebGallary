# テーブル定義書

## スキーマ
| スキーマ名 | コメント |
|---|---|
| common | すべてのドメインに対して共通のデータを管理するスキーマ |
| photo | 写真サイトのデータを管理するスキーマ |

<br>

## commonスキーマ
### テーブル一覧
| 物理名 | 論理名 | 説明 |
|:---|:---|:---|
| account | アカウント | アカウント情報を管理する |
| kbn_mst | 区分マスタ | 区分を管理する |

<br>

--- 

### テーブル定義
#### account ( アカウント ) : アカウント情報を管理する
| 物理名 | 論理名 | データ型 | NULL許可 | デフォルト | コメント |
|:---|:---|:---|---|:---|:---|
| account_no | アカウント番号 | serial | NOT NULL | - |  |
| created_by | 作成者 | int | NOT NULL | - |  |
| created_at | 作成日時 | timestamp with time zone | NOT NULL | - |  |
| updated_by | 更新者 | int | NOT NULL | - |  |
| updated_at | 更新日時 | timestamp with time zone | NOT NULL | - |  |
| is_deleted | 削除フラグ | boolean | NOT NULL | false |  |
| account_id | アカウントID | varchar(20) | NOT NULL | - | 半角英数字で、8文字以上20文字以下 |
| account_name | アカウント名 | varchar(50) | NOT NULL | - |  |
| password | パスワード | text | NOT NULL | - |  |
| birthdate | 生年月日 | date | NOT NULL | 1900-01-01 | 個人情報管理の観点で、必須入力なし、かつ年月まで。データ登録時にすべて1日に変換する |
| sex_kbn_code | 性別区分コード | varchar(20) | NOT NULL | none | none: 未設定<br>man: 男<br>woman: 女 |
| birthplace_prefecture_kbn_code | 出身都道府県区分コード | varchar(20) | NOT NULL | none | none: 未設定 |
| resident_prefecture_kbn_code | 在住都道府県区分コード | varchar(20) | NOT NULL | none | none: 未設定 |
| free_memo | フリーメモ | text | NOT NULL | "" |  |
| authority_kbn_code | 権限区分コード | varchar(20) | NOT NULL | - | mini-user: 簡易ユーザー<br>normal-user: 一般ユーザー<br>special-user: 特別ユーザー<br>administrator: 管理者 |
| last_login_datetime | 最終ログイン日時 | timestamp with time zone | NOT NULL | - |  |
| login_failure_count | ログイン失敗回数 | smallint | NOT NULL | 0 |  |

| 制約 | 物理名 | カラム | 説明 |
|:---|:---|:---|:---|
| Primary Key | - | account_no |  |
| Unique Key | - | account_id |  |

<br>

#### kbn_mst ( 区分マスタ ) : 区分を管理する
| 物理名 | 論理名 | データ型 | NULL許可 | デフォルト | コメント |
|:---|:---|:---|---|:---|:---|
| kbn_class_code | 区分分類コード | varchar(20) | NOT NULL | - |  |
| kbn_code | 区分コード | varchar(20) | NOT NULL | - |  |
| created_by | 作成者 | int | NOT NULL | - |  |
| created_at | 作成日時 | timestamp with time zone | NOT NULL | - |  |
| sort_order | 並び順 | int | NOT NULL | - | |
| kbn_group_code | 区分グループコード | varchar(20) | NOT NULL | "" |  |
| kbn_class_japanese_name | 区分分類日本語名 | varchar(20) | NOT NULL | - | 空文字不可 |
| kbn_group_japanese_name | 区分グループ日本語名 | varchar(20) | NOT NULL | "" |  |
| kbn_japanese_name | 区分日本語名 | varchar(20) | NOT NULL | - | 空文字不可 |
| kbn_class_english_name | 区分分類英語名 | varchar(20) | NOT NULL | "" |  |
| kbn_group_english_name | 区分グループ英語名 | varchar(20) | NOT NULL | "" |  |
| kbn_english_name | 区分英語名 | varchar(20) | NOT NULL | "" |  |
| explanation | 説明 | text | NOT NULL | "" |  |

| 制約 | 物理名 | カラム | 説明 |
|:---|:---|:---|:---|
| Primary Key | - | kbn_class_code, kbn_code |  |
| Unique Key | japanese_name_unique | kbn_class_japanese_name, kbn_japanese_name |  |

<br>

## photoスキーマ
### テーブル一覧
| 物理名 | 論理名 | 説明 |
|:---|:---|:---|
| photo_mst | 写真マスタ | サイトに載せる写真を管理するマスタ |
| photo_tag_mst | 写真タグマスタ | 写真に付随する、タグを管理する |
| photo_favorite | 写真お気に入り | 自分、自分以外の写真のお気に入りを管理する |

--- 

### テーブル定義
#### photo_mst ( 写真マスタ ) : サイトに載せる写真を管理するマスタ
| 物理名 | 論理名 | データ型 | NULL許可 | デフォルト | コメント |
|:---|:---|:---|---|:---|:---|
| account_no | アカウント番号 | int | NOT NULL | - |  |
| photo_no | 写真番号 | int | NOT NULL | - |  |
| created_by | 作成者 | int | NOT NULL | - |  |
| created_at | 作成日時 | timestamp with time zone | NOT NULL | - |  |
| updated_by | 更新者 | int | NOT NULL | - |  |
| updated_at | 更新日時 | timestamp with time zone | NOT NULL | - |  |
| is_deleted | 削除フラグ | boolean | NOT NULL | false |  |
| photo_at | 撮影日時 | timestamp with time zone | NOT NULL | - |  |
| location_no | ロケーション番号 | int | NOT NULL | - |  |
| image_file_path | 画像ファイルパス | text | NOT NULL | - | 空文字不可 |
| photo_japanese_title | 写真タイトル日本語名 | varchar(100) | NOT NULL | - | 空文字不可 |
| photo_english_title | 写真タイトル英語名 | varchar(100) | NOT NULL | "" |  |
| caption | キャプション | text | NOT NULL | "" |  |
| direction_kbn_code | 向き区分コード | varchar(20) | NOT NULL | - | vertical: 縦<br>horizontal: 横<br>square: 正方形 |
| focal_length | 焦点距離 | int | NOT NULL | - | 単位: mm |
| f_value | F値 | decimal(5,2) | NOT NULL | - |  |
| shutter_speed | シャッタースピード | decimal(10,5) | NOT NULL | - | 単位: 秒 |
| iso | ISO | int | NOT NULL | - |  |

| 制約 | 物理名 | カラム | 説明 |
|:---|:---|:---|:---|
| Primary Key | - | account_no, photo_no |  |
| Foreign Key | - | account_no | common.account(account_no) ON UPDATE RESTRICT ON DELETE RESTRICT |

<br>

#### photo_tag_mst ( 写真タグマスタ ) : 写真に付随する、タグを管理する
| 物理名 | 論理名 | データ型 | NULL許可 | デフォルト | コメント |
|:---|:---|:---|---|:---|:---|
| account_no | アカウント番号 | int | NOT NULL | - |  |
| photo_no | 写真番号 | int | NOT NULL | - |  |
| tag_no | タグ番号 | int | NOT NULL | - |  |
| created_by | 作成者 | int | NOT NULL | - |  |
| created_at | 作成日時 | timestamp with time zone | NOT NULL | - |  |
| tag_japanese_name | タグ日本語名 | varchar(20) | NOT NULL | - | 空文字不可 |
| tag_english_name | タグ英語名 | varchar(20) | NOT NULL | "" |  |

| 制約 | 物理名 | カラム | 説明 |
|:---|:---|:---|:---|
| Primary Key | - | account_no, photo_no, tag_no |  |
| Foreign Key | - | account_no, photo_no | photo.photo_mst(account_no, photo_no) ON UPDATE RESTRICT ON DELETE RESTRICT |

<br>

#### photo_favorite ( 写真お気に入り ) : 自分、自分以外の写真のお気に入りを管理する
| 物理名 | 論理名 | データ型 | NULL許可 | デフォルト | コメント |
|:---|:---|:---|---|:---|:---|
| account_no | アカウント番号 | int | NOT NULL | - | お気に入りを付けたアカウント |
| favorite_photo_account_no | お気に入り写真のアカウント番号 | int | NOT NULL | - |  |
| favorite_photo_no | お気に入り写真番号 | int | NOT NULL | - |  |
| created_by | 作成者 | int | NOT NULL | - |  |
| created_at | 作成日時 | timestamp with time zone | NOT NULL | - |  |

| 制約 | 物理名 | カラム | 説明 |
|:---|:---|:---|:---|
| Primary Key | - | account_no, favorite_photo_account_no, favorite_photo_no |  |
| Foreign Key | - | account_no | common.account(account_no) ON UPDATE RESTRICT ON DELETE RESTRICT |
| Foreign Key | - | favorite_photo_account_no, favorite_photo_no | photo.photo_mst(account_no, photo_no) ON UPDATE RESTRICT ON DELETE RESTRICT |