package com.official.kento.repository.impl;

import org.springframework.stereotype.Repository;

import com.official.kento.model.FileModel;
import com.official.kento.repository.FileRepository;

import lombok.RequiredArgsConstructor;

/**
 * ファイルを永続化するRepositoryの実装クラス
 */
@Repository
@RequiredArgsConstructor
public class FileRepositoryImpl implements FileRepository {

	@Override
	public void save(FileModel fileModel) {
		// TODO 自動生成されたメソッド・スタブ
		return;
	}

	@Override
	public void delete(String filePath) {
		// TODO 自動生成されたメソッド・スタブ
		return;
	}
}