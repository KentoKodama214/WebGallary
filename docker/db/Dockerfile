# 最新バージョンのPostgreSQL公式イメージを使用
FROM postgres:latest

RUN apt-get update && apt-get install -y bash

COPY db/init/init-db.sh /docker-entrypoint-initdb.d/init-db.sh
RUN chmod +x /docker-entrypoint-initdb.d/init-db.sh