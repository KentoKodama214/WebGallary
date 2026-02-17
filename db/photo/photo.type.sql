/* Create Type */
-- 向き
CREATE TYPE photo.direction_enum AS ENUM ('vertical', 'horizontal', 'square', 'none');
COMMENT ON TYPE photo.direction_enum IS '向きを管理するEnum型: vertical(縦)、horizontal(横)、square(正方形)、none(未設定)';
