package com.official.kento.repository;

import com.official.kento.model.FileModel;

/**
 * ファイルを永続化するRepositoryクラス
 */
public interface FileRepository {

	void save(FileModel fileModel);
	
	void delete(String filePath);
}