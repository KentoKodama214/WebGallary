# API一覧
## 共通
| クラス名 | エンドポイント | パラメータ | 説明 |
|:---|:---|:---|:---|
| LoginController | GET /login | - | ログインページ |
| - | GET /logout | - | ログアウト |
| - | GET /header | - | ヘッダー |
| BaseController | GET /foter | - | フッター |
| BaseController | GET /error | - | エラー |
| BaseController | GET /error_page | - | エラーページ |

<br>

## アカウント関連
| クラス名 | エンドポイント | パラメータ | 説明 |
|:---|:---|:---|:---|
| AccountController | GET /account_list | - | アカウント一覧ページ |
| AccountController | GET /{accountId}/account_setting | accountId: アカウントID | アカウント設定ページ |
| AccountController | GET /register | - | アカウント登録ページ |
| AccountRestController | POST /register | AccountRegistRequest | アカウント登録API |
| AccountRestController | POST /update | AccountUpdateRequest | アカウト更新API |

<br>

## 写真関連
| クラス名 | エンドポイント | パラメータ | 説明 |
|:---|:---|:---|:---|
| PhotoController | GET /photo/{photoAccountId}/photo_list | photoAccountId: 写真アカウントID | 写真一覧ページ |
| PhotoController | GET /photo/{photoAccountId}/photo_detail | photoAccountId: 写真アカウントID<br>PhotoDetailRequest | 写真詳細ページ |
| PhotoController | GET /photo/{photoAccountId}/photo_setting | photoAccountId: 写真アカウントID<br>PhotoSettingRequest | 写真登録・編集ページ |
| PhotoRestController | POST /photo/{photoAccountId}/photo_list/get | photoAccountId: 写真アカウントID<br>PhotoListRequest | 写真一覧取得API |
| PhotoRestController | POST /photo/{photoAccountId}/save | photoAccountId: 写真アカウントID<br>PhotoSaveRequest | 写真保存API |
| PhotoRestController | POST /photo/{photoAccountId}/delete | photoAccountId: 写真アカウントID<br>PhotoDeleteRequest | 写真削除API |
| PhotoFavoriteController | POST /photo/favorite/add | PhotoFavoriteRegistRequest | お気に入り登録API |
| PhotoFavoriteController | POST /photo/favorite/delete | PhotoFavoriteDeleteRequest | お気に入り解除PI |