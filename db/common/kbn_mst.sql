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


/* Insert Master Data */
-- 都道府県
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Hokkaido', 0, now(), 1, 'Hokkaido_Tohoku', '都道府県', '北海道・東北', '北海道', 'prefecture', 'Hokkaido_Tohoku', 'Hokkaido', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Aomori', 0, now(), 2, 'Hokkaido_Tohoku', '都道府県', '北海道・東北', '青森', 'prefecture', 'Hokkaido_Tohoku', 'Aomori', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Iwate', 0, now(), 3, 'Hokkaido_Tohoku', '都道府県', '北海道・東北', '岩手', 'prefecture', 'Hokkaido_Tohoku', 'Iwate', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Miyagi', 0, now(), 4, 'Hokkaido_Tohoku', '都道府県', '北海道・東北', '宮城', 'prefecture', 'Hokkaido_Tohoku', 'Miyagi', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Akita', 0, now(), 5, 'Hokkaido_Tohoku', '都道府県', '北海道・東北', '秋田', 'prefecture', 'Hokkaido_Tohoku', 'Akita', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Yamagata', 0, now(), 6, 'Hokkaido_Tohoku', '都道府県', '北海道・東北', '山形', 'prefecture', 'Hokkaido_Tohoku', 'Yamagata', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Fukushima', 0, now(), 7, 'Hokkaido_Tohoku', '都道府県', '北海道・東北', '福島', 'prefecture', 'Hokkaido_Tohoku', 'Fukushima', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Ibaraki', 0, now(), 8, 'Hokkaido_Tohoku', '都道府県', '北海道・東北', '茨城', 'prefecture', 'Hokkaido_Tohoku', 'Ibaraki', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Tochigi', 0, now(), 9, 'Kanto', '都道府県', '関東', '栃木', 'prefecture', 'Kanto', 'Tochigi', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Gunma', 0, now(), 10, 'Kanto', '都道府県', '関東', '群馬', 'prefecture', 'Kanto', 'Gunma', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Saitama', 0, now(), 11, 'Kanto', '都道府県', '関東', '埼玉', 'prefecture', 'Kanto', 'Saitama', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Chiba', 0, now(), 12, 'Kanto', '都道府県', '関東', '千葉', 'prefecture', 'Kanto', 'Chiba', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Tokyo', 0, now(), 13, 'Kanto', '都道府県', '関東', '東京', 'prefecture', 'Kanto', 'Tokyo', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Kanagawa', 0, now(), 14, 'Kanto', '都道府県', '関東', '神奈川', 'prefecture', 'Kanto', 'Kanagawa', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Niigata', 0, now(), 15, 'Chubu', '都道府県', '中部', '新潟', 'prefecture', 'Kanto', 'Niigata', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Toyama', 0, now(), 16, 'Chubu', '都道府県', '中部', '富山', 'prefecture', 'Chubu', 'Toyama', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Ishikawa', 0, now(), 17, 'Chubu', '都道府県', '中部', '石川', 'prefecture', 'Chubu', 'Ishikawa', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Fukui', 0, now(), 18, 'Chubu', '都道府県', '中部', '福井', 'prefecture', 'Chubu', 'Fukui', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Yamanashi', 0, now(), 19, 'Chubu', '都道府県', '中部', '山梨', 'prefecture', 'Chubu', 'Yamanashi', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Nagano', 0, now(), 20, 'Chubu', '都道府県', '中部', '長野', 'prefecture', 'Chubu', 'Nagano', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Gifu', 0, now(), 21, 'Chubu', '都道府県', '中部', '岐阜', 'prefecture', 'Chubu', 'Gifu', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Shizuoka', 0, now(), 22, 'Chubu', '都道府県', '中部', '静岡', 'prefecture', 'Chubu', 'Shizuoka', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Aichi', 0, now(), 23, 'Chubu', '都道府県', '中部', '愛知', 'prefecture', 'Chubu', 'Aichi', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Mie', 0, now(), 24, 'Chubu', '都道府県', '中部', '三重', 'prefecture', 'Chubu', 'Mie', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Shiga', 0, now(), 25, 'Kansai', '都道府県', '関西', '滋賀', 'prefecture', 'Kansai', 'Shiga', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Kyoto', 0, now(), 26, 'Kansai', '都道府県', '関西', '京都', 'prefecture', 'Kansai', 'Kyoto', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Osaka', 0, now(), 27, 'Kansai', '都道府県', '関西', '大阪', 'prefecture', 'Kansai', 'Osaka', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Hyogo', 0, now(), 28, 'Kansai', '都道府県', '関西', '兵庫', 'prefecture', 'Kansai', 'Hyogo', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Nara', 0, now(), 29, 'Kansai', '都道府県', '関西', '奈良', 'prefecture', 'Kansai', 'Nara', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Wakayama', 0, now(), 30, 'Kansai', '都道府県', '関西', '和歌山', 'prefecture', 'Kansai', 'Wakayama', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Tottori', 0, now(), 31, 'Chugoku', '都道府県', '中国', '鳥取', 'prefecture', 'Chugoku', 'Tottori', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Shimane', 0, now(), 32, 'Chugoku', '都道府県', '中国', '島根', 'prefecture', 'Chugoku', 'Shimane', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Okayama', 0, now(), 33, 'Chugoku', '都道府県', '中国', '岡山', 'prefecture', 'Chugoku', 'Okayama', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Hiroshima', 0, now(), 34, 'Chugoku', '都道府県', '中国', '広島', 'prefecture', 'Chugoku', 'Hiroshima', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Yamaguchi', 0, now(), 35, 'Chugoku', '都道府県', '中国', '山口', 'prefecture', 'Chugoku', 'Yamaguchi', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Tokushima', 0, now(), 36, 'Shikoku', '都道府県', '四国', '徳島', 'prefecture', 'Shikoku', 'Tokushima', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Kagawa', 0, now(), 37, 'Shikoku', '都道府県', '四国', '香川', 'prefecture', 'Shikoku', 'Kagawa', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Ehime', 0, now(), 38, 'Shikoku', '都道府県', '四国', '愛媛', 'prefecture', 'Shikoku', 'Ehime', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Kochi', 0, now(), 39, 'Shikoku', '都道府県', '四国', '高知', 'prefecture', 'Shikoku', 'Kochi', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Fukuoka', 0, now(), 40, 'Kyushu_Okinawa', '都道府県', '九州・沖縄', '福岡', 'prefecture', 'Kyushu_Okinawa', 'Fukuoka', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Saga', 0, now(), 41, 'Kyushu_Okinawa', '都道府県', '九州・沖縄', '佐賀', 'prefecture', 'Kyushu_Okinawa', 'Saga', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Nagasaki', 0, now(), 42, 'Kyushu_Okinawa', '都道府県', '九州・沖縄', '長崎', 'prefecture', 'Kyushu_Okinawa', 'Nagasaki', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Kumamoto', 0, now(), 43, 'Kyushu_Okinawa', '都道府県', '九州・沖縄', '熊本', 'prefecture', 'Kyushu_Okinawa', 'Kumamoto', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Oita', 0, now(), 44, 'Kyushu_Okinawa', '都道府県', '九州・沖縄', '大分', 'prefecture', 'Kyushu_Okinawa', 'Oita', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Miyazaki', 0, now(), 45, 'Kyushu_Okinawa', '都道府県', '九州・沖縄', '宮崎', 'prefecture', 'Kyushu_Okinawa', 'Miyazaki', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Kagoshima', 0, now(), 46, 'Kyushu_Okinawa', '都道府県', '九州・沖縄', '鹿児島', 'prefecture', 'Kyushu_Okinawa', 'Kagoshima', '');
INSERT INTO common.kbn_mst(kbn_class_code, kbn_code, created_by, created_at, sort_order, kbn_group_code, kbn_class_japanese_name, kbn_group_japanese_name, kbn_japanese_name, kbn_class_english_name, kbn_group_english_name, kbn_english_name, explanation)
VALUES('prefecture', 'Okinawa', 0, now(), 47, 'Kyushu_Okinawa', '都道府県', '九州・沖縄', '沖縄', 'prefecture', 'Kyushu_Okinawa', 'Okinawa', '');
