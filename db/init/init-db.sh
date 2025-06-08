#!/bin/bash

# データベース、ユーザー、パスワードを指定
DATABASE=${POSTGRES_DB:-"web_gallary"}
USER=${POSTGRES_USER:-"postgres"}
PW=${POSTGRES_PASSWORD:-"postgres"}

# 作成するスキーマ名
SCHEMA1="common"
SCHEMA2="photo"

# psqlコマンドでパスワード入力を省略するために環境変数を利用
export PGPASSWORD="$PW"

echo "Running initialization scripts..."

# PostgreSQLにスキーマを作成
psql -U $USER -d $DATABASE -c "CREATE SCHEMA IF NOT EXISTS $SCHEMA1;"
psql -U $USER -d $DATABASE -c "CREATE SCHEMA IF NOT EXISTS $SCHEMA2;"

for f in /docker-entrypoint-initdb.d/common/*.sql; do
  if [ -e "$f" ]; then
    echo "Executing $f"
    psql -U "$USER" -d "$DATABASE" -f "$f"
  fi
done

for f in /docker-entrypoint-initdb.d/photo/*.sql; do
  if [ -e "$f" ]; then
    echo "Executing $f"
    psql -U "$USER" -d "$DATABASE" -f "$f"
  fi
done

# 終了後、環境変数を消す場合
unset PW

echo "Initialization complete."