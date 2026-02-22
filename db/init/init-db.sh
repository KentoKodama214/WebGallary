#!/bin/bash

# データベース、ユーザー、パスワードを指定
DATABASE=${POSTGRES_DB:-"web_gallary"}
DATABASE_TEST="${DATABASE}_test"
USER=${POSTGRES_USER:-"postgres"}
PW=${POSTGRES_PASSWORD:-"postgres"}

# 作成するスキーマ名
SCHEMA1="common"
SCHEMA2="photo"

# psqlコマンドでパスワード入力を省略するために環境変数を利用
export PGPASSWORD="$PW"

# 依存関係を考慮した実行順序でSQLファイルを実行
SQL_FILES=(
  # commonスキーマ: 型定義 → テーブル定義（外部キー依存順）
  "common/common.type.sql"
  "common/account.sql"
  "common/kbn_mst.sql"
  "common/location_mst.sql"
  # photoスキーマ: 型定義 → テーブル定義（外部キー依存順）
  "photo/photo.type.sql"
  "photo/photo_mst.sql"
  "photo/photo_tag_mst.sql"
  "photo/photo_favorite.sql"
)

# 本番用データベースの初期化
echo "Running initialization scripts for $DATABASE..."

psql -U $USER -d $DATABASE -c "CREATE SCHEMA IF NOT EXISTS $SCHEMA1;"
psql -U $USER -d $DATABASE -c "CREATE SCHEMA IF NOT EXISTS $SCHEMA2;"

for f in "${SQL_FILES[@]}"; do
  filepath="/docker-entrypoint-initdb.d/$f"
  if [ -e "$filepath" ]; then
    echo "Executing $filepath on $DATABASE"
    psql -U "$USER" -d "$DATABASE" -f "$filepath"
  fi
done

echo "Initialization complete for $DATABASE."

# テスト用データベースの初期化
echo "Running initialization scripts for $DATABASE_TEST..."

psql -U $USER -c "CREATE DATABASE $DATABASE_TEST;"
psql -U $USER -d $DATABASE_TEST -c "CREATE SCHEMA IF NOT EXISTS $SCHEMA1;"
psql -U $USER -d $DATABASE_TEST -c "CREATE SCHEMA IF NOT EXISTS $SCHEMA2;"

for f in "${SQL_FILES[@]}"; do
  filepath="/docker-entrypoint-initdb.d/$f"
  if [ -e "$filepath" ]; then
    echo "Executing $filepath on $DATABASE_TEST"
    psql -U "$USER" -d "$DATABASE_TEST" -f "$filepath"
  fi
done

echo "Initialization complete for $DATABASE_TEST."

# 終了後、環境変数を消す場合
unset PW