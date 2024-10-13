package com.web.gallary.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.web.gallary.controller.request.ErrorRequest;

import lombok.RequiredArgsConstructor;

/**
 * システム共通のページを扱うControllerクラス
 * @author	Kento Kodama
 * @version	1.0.0
 * @since	1.0.0
*/
@Controller
@RequiredArgsConstructor
public class BaseController {
	
	/**
	 * ヘッダーページ
	 * @return	String	"header"
	 */
	@GetMapping("header")
	public String header() {
		return "header";
	}
	
	/**
	 * フッターページ
	 * @return	String	"footer"
	 */
	@GetMapping("footer")
	public String footer() {
		return "footer";
	}
	
	/**
	 * デフォルトのエラーページ
	 * @return	String	"error"
	 */
	@GetMapping("error")
	public String error() {
		return "error";
	}
	
	/**
	 * 独自のエラーページ
	 * @param	errorRequest	{@link ErrorRequest}
	 * @return	ModelAndView	エラーページ。Modelとして以下を返す<br>
	 * 							goBackPageUrl:	遷移先URL<br>
	 * 							errorMessage:	メッセージ<br>
	 * 							errorCode:		エラーコード<br>
	 * 							httpStatus:		HTTPステータス
	 */
	@GetMapping("error_page")
	public ModelAndView error_page(@ModelAttribute ErrorRequest errorRequest) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error_page");
		mv.addObject("goBackPageUrl", Optional.ofNullable(errorRequest.getGoBackPageUrl()).orElse("/"));
		mv.addObject("errorMessage", Optional.ofNullable(errorRequest.getErrorMessage()).orElse("エラーが発生しました。システム管理者に問い合わせてください。"));
		mv.addObject("errorCode", errorRequest.getErrorCode());
		mv.addObject("httpStatus", errorRequest.getHttpStatus());
		
		return mv;
	}
}