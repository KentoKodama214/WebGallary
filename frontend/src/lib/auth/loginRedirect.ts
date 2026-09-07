/**
 * ログアウト時の `/login` 退避先を「次の1回だけ」上書きするための仕組み
 *
 * 通常、ログイン必須ページで未認証になると {@link AuthGuard} が現在のパスを
 * `redirect` クエリに載せて `/login` へ退避させ、再ログイン後に元の画面へ戻す。
 * しかしアカウント削除のように「退避元ページ（本人しか開けないアカウント設定など）へ
 * 戻す意味がなく、別アカウントでログインし直すと権限エラーになる」ケースでは、
 * そのパスを `redirect` に載せてはいけない。
 *
 * そのようなケースの直前でこのモジュールに退避先を指定しておくと、次に AuthGuard が
 * 退避する際はその指定先へ遷移し（`redirect` クエリは付かない）、指定は消費される。
 */

/** 次回のログアウト退避先の上書き指定（1回消費されるとクリアされる） */
let nextLogoutDestination: string | null = null;

/**
 * 次回の `/login` 退避先を固定する（`redirect` クエリは付けない）
 *
 * @param path 退避先の内部パス（例: `"/login?deleted=1"`）
 */
export function setNextLogoutDestination(path: string): void {
  nextLogoutDestination = path;
}

/**
 * 退避先の上書き指定があれば返し、指定を消費する（1回限り）
 *
 * @returns 上書き指定された退避先。指定がなければ `null`
 */
export function consumeNextLogoutDestination(): string | null {
  const destination = nextLogoutDestination;
  nextLogoutDestination = null;
  return destination;
}
