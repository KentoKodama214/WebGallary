/* Drop Tables */
DROP TABLE IF EXISTS photo.photo_favorite;


/* Create Tables */
CREATE TABLE photo.photo_favorite
(
	-- アカウント番号
	account_no int NOT NULL,
	-- お気に入り写真アカウント番号
	favorite_photo_account_no int NOT NULL,
	-- お気に入り写真番号
	favorite_photo_no int NOT NULL,
	-- 作成者
	created_by int NOT NULL,
	-- 作成日時
	created_at timestamp with time zone NOT NULL,
	PRIMARY KEY (account_no, favorite_photo_account_no, favorite_photo_no)
) WITHOUT OIDS;


/* Create Foreign Keys */
ALTER TABLE photo.photo_favorite
	ADD FOREIGN KEY (account_no)
	REFERENCES common.account (account_no)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;
ALTER TABLE photo.photo_favorite
	ADD FOREIGN KEY (favorite_photo_account_no, favorite_photo_no)
	REFERENCES photo.photo_mst (account_no, photo_no)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


/* Comments */
COMMENT ON TABLE photo.photo_favorite IS '写真お気に入り';
COMMENT ON COLUMN photo.photo_favorite.account_no IS 'アカウント番号';
COMMENT ON COLUMN photo.photo_favorite.favorite_photo_account_no IS 'お気に入り写真アカウント番号';
COMMENT ON COLUMN photo.photo_favorite.favorite_photo_no IS 'お気に入り写真番号';
COMMENT ON COLUMN photo.photo_favorite.created_by IS '作成者';
COMMENT ON COLUMN photo.photo_favorite.created_at IS '作成日時';