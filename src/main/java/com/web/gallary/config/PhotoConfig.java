package com.web.gallary.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * application.ymlのファイルに関するプロパティを保持するConfigクラス
 * @author	Kento Kodama
 * @version	1.0.0
 * @since	1.0.0
 */
@RequiredArgsConstructor
@Getter
@ConfigurationProperties(prefix="app.photo")
public class PhotoConfig {
	/** 写真一覧で、1ページあたりの表示枚数 */
	private final Integer photoCountPerPage;
	
	/** 最大ファイルサイズ（MB） */
	private final Integer maxFileSizeMb;
	
	/** 出力パス */
	private final String outputPath;
}