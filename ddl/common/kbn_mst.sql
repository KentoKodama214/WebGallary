/* Drop Tables */
DROP TABLE IF EXISTS common.kbn_mst;


/* Create Tables */
CREATE TABLE common.kbn_mst
(
	-- 区分分類コード
	kbn_class_code varchar(20) NOT NULL,
	-- 区分コード
	kbn_code varchar(20) NOT NULL,
	-- 作成者
	created_by int NOT NULL,
	-- 作成日時
	created_at timestamp with time zone NOT NULL,
	-- 並び順
	sort_order int NOT NULL,
	-- 区分グループコード
	kbn_group_code varchar(20) DEFAULT '""' NOT NULL,
	-- 区分分類日本語名: 空文字不可
	kbn_class_japanese_name varchar(20) NOT NULL,
	-- 区分グループ日本語名
	kbn_group_japanese_name varchar(20) DEFAULT '""' NOT NULL,
	-- 区分日本語名: 空文字不可
	kbn_japanese_name varchar(20) NOT NULL,
	-- 区分分類英語名
	kbn_class_english_name varchar(20) DEFAULT '""' NOT NULL,
	-- 区分グループ英語名
	kbn_group_english_name varchar(20) DEFAULT '""' NOT NULL,
	-- 区分英語名
	kbn_english_name varchar(20) DEFAULT '""' NOT NULL,
	-- 説明
	explanation text DEFAULT '""' NOT NULL,
	PRIMARY KEY (kbn_class_code, kbn_code),
	CONSTRAINT japanese_name_unique UNIQUE (kbn_class_japanese_name, kbn_japanese_name)
) WITHOUT OIDS;


/* Comments */
COMMENT ON TABLE common.kbn_mst IS '区分マスタ';
COMMENT ON COLUMN common.kbn_mst.kbn_class_code IS '区分分類コード';
COMMENT ON COLUMN common.kbn_mst.kbn_code IS '区分コード';
COMMENT ON COLUMN common.kbn_mst.created_by IS '作成者';
COMMENT ON COLUMN common.kbn_mst.created_at IS '作成日時';
COMMENT ON COLUMN common.kbn_mst.sort_order IS '並び順';
COMMENT ON COLUMN common.kbn_mst.kbn_class_japanese_name IS '区分分類日本語名 : 空文字不可';
COMMENT ON COLUMN common.kbn_mst.kbn_class_english_name IS '区分分類英語名';
COMMENT ON COLUMN common.kbn_mst.kbn_japanese_name IS '区分日本語名 : 空文字不可';
COMMENT ON COLUMN common.kbn_mst.kbn_english_name IS '区分英語名';
COMMENT ON COLUMN common.kbn_mst.explanation IS '説明';