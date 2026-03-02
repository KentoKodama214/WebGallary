# GitHub Actions ワークフロー

## ワークフロー一覧

| ワークフロー | ファイル | トリガー |
|---|---|---|
| Javadocチェック | `checkstyle.yml` | `main`へのPR |
| テスト実行 | `test.yml` | `main`へのPR |

## 実行順序と依存関係

```
checkstyle.yml:
  Javadocチェック ──────────────────────→ (独立)

test.yml:
  単体テスト ──→ (成功時のみ) 結合テスト
```

- Javadocチェックとテスト実行は別ワークフローのため、**並列に実行**される
- 単体テストが失敗した場合、結合テストは**スキップ**される
- Javadocチェックの成否はテスト実行に**影響しない**

## 各ジョブの詳細

### Javadocチェック (`checkstyle.yml`)

Checkstyleを使用して、`src/main/java`配下の全クラス・全メソッドにJavadocが記載されているかをチェックする。

**チェック内容:**
- クラス・インターフェース・EnumにJavadocがあるか
- 全メソッド（public/protected/package/private）にJavadocがあるか
- `@param`、`@return`、`@throws`タグが正しく記載されているか
- Javadocの説明文が空でないか

**失敗時:** チェック結果レポートがアーティファクトとしてアップロードされる

### 単体テスト (`test.yml` - `unit-test`)

`./gradlew test`を実行し、結合テスト(`*IntegrationTest*`)とMapperテスト(`mapper/*Test*`)を除く単体テストを実行する。

### 結合テスト (`test.yml` - `integration-test`)

PostgreSQLサービスコンテナを起動し、`./gradlew integrationTest`を実行する。単体テストが成功した場合のみ実行される。
