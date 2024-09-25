package com.web.gallary.controller;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.web.gallary.exception.ForbiddenAccountException;
import com.web.gallary.exception.PhotoNotFoundException;
import com.web.gallary.helper.SessionHelper;

import lombok.RequiredArgsConstructor;

/**
 * システム共通のExceptionHandlerを扱うControllerAdviceクラス
 * @author	Kento Kodama
 * @version	1.0.0
 * @since	1.0.0
*/
@ControllerAdvice
@Component
@RequiredArgsConstructor
public class CommonControllerAdvice {
	
	private final SessionHelper sessionHelper;

	/**
	 * 権限のないアカウントからの不正アクセスがあったときに制御するExceptionHandler
	 * @param	exception	{@link ForbiddenAccountException}
	 * @return				エラーページ。Modelとして以下を返す<br>
	 * 						goBackPageUrl:	遷移先URL<br>
	 * 						errorMessage:	メッセージ<br>
	 * 						errorCode:		エラーコード<br>
	 * 						httpStatus:		ステータス:403
	 */
	@ExceptionHandler(ForbiddenAccountException.class)
	public ModelAndView handleFileForbiddenAccountException(ForbiddenAccountException exception) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error_page");
		mv.addObject("httpStatus", HttpStatus.FORBIDDEN.value());
		mv.addObject("errorCode", exception.getErrorCode());
		mv.addObject("errorMessage", exception.getMessage());
		mv.addObject("goBackPageUrl", getGoBackPageUrl());
		
		return mv;
	}
	
	/**
	 * 削除済みで写真が存在しないときに制御するExceptionHandler
	 * @param	exception	{@link PhotoNotFoundException}
	 * @return				エラーページ。Modelとして以下を返す<br>
	 * 						goBackPageUrl:	遷移先URL<br>
	 * 						errorMessage:	メッセージ<br>
	 * 						errorCode:		エラーコード<br>
	 * 						httpStatus:		ステータス:400
	 */
	@ExceptionHandler(PhotoNotFoundException.class)
	public ModelAndView handlePhotoNotFoundException(PhotoNotFoundException exception) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error_page");
		mv.addObject("httpStatus", HttpStatus.BAD_REQUEST.value());
		mv.addObject("errorCode", exception.getErrorCode());
		mv.addObject("errorMessage", exception.getMessage());
		mv.addObject("goBackPageUrl", getGoBackPageUrl());
		
		return mv;
	}
	
	/**
	 * エラーページから戻るページのURLを生成する<br>
	 * 未ログイン者の場合はログインページ、ログイン者は自身の写真一覧ページへ戻る
	 * @return	遷移先ページのURL
	 */
	private String getGoBackPageUrl() {
		return Objects.isNull(sessionHelper.getAccountId()) ? "/login" : "/photo/" + sessionHelper.getAccountId() + "/photo_list";
	}
}