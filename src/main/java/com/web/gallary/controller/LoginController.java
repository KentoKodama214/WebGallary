package com.web.gallary.controller;

import java.util.Objects;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.web.gallary.helper.SessionHelper;

import lombok.RequiredArgsConstructor;

/**
 * ログインに関するページを扱うControllerクラス
 * @author	Kento Kodama
 * @version	1.0.0
 * @since	1.0.0
*/
@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private final SessionHelper sessionHelper;
	
	/**
	 * ログインページ
	 * @return	ModelAndView	ログインページ
	 */
	@GetMapping("/login")
    public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		
        return mv;
    }
    
	/**
	 * ベースURLからのリダイレクトページ<br>
	 * 未ログインの場合はログインページ、ログイン中の場合は写真一覧へリダイレクトする
	 * @return	String	リダイレクト先のURL
	 */
    @GetMapping("/")
    public String success() {
    	String accountId = sessionHelper.getAccountId();
    	
    	if(Objects.isNull(accountId)) return "redirect:/login";
    	else return "redirect:/photo/" + accountId + "/photo_list";
    }
}