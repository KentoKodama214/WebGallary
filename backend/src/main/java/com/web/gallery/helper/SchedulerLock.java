package com.web.gallery.helper;

import com.web.gallery.enumeration.SchedulerLockName;
import com.web.gallery.repository.SchedulerLockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 複数インスタンス構成でスケジューラ処理の多重実行を防ぐためのHelperクラス
 *
 * <p>PostgreSQL のトランザクションレベルのアドバイザリーロック（{@code pg_try_advisory_xact_lock}）を用いて、
 * 同一ロック名の処理を全インスタンス中で1つだけ実行させる。
 *
 * <p>ロックの取得と{@code task}の実行を同一トランザクションで行うため、ロックは{@code task}完了後の
 * コミット時まで保持される。ロックを取得できなかったインスタンスは{@code task}を実行せずスキップする。
 *
 * @author Kento Kodama
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SchedulerLock {
  private final SchedulerLockRepository schedulerLockRepository;

  /**
   * 指定したロックを取得できた場合のみ{@code task}を実行する
   *
   * <p>他インスタンスがロックを保持している場合は{@code task}を実行せず、待機もせずに戻る。
   *
   * @param lockName ロック名
   * @param task ロック取得時に実行する処理
   */
  @Transactional
  public void runIfLocked(SchedulerLockName lockName, Runnable task) {
    if (!schedulerLockRepository.tryLock(lockName)) {
      log.info("他インスタンスが実行中のため、{} の処理をスキップします。", lockName);
      return;
    }
    log.info("{} のロックを取得しました。処理を開始します。", lockName);
    task.run();
  }
}
