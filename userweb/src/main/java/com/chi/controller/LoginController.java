package com.chi.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chi.constant.ConstatVar;
import com.chi.service.UserInfoService;
import com.taobao.api.ApiException;

@Controller(value = "loginController")
public class LoginController extends UserBaseController {

	@Resource(name="userInfoService")
	private UserInfoService userInfoService;
	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
	
    /**
     * 用户登录
     */
    @RequestMapping("/login.do")
    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ApiException
    {
    	
    	return null;
    }
    
	/**
	 * 用户选择登陆渠道
	 */
	@RequestMapping("/preLogin.do")
	public void preLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ApiException {
		String regMethod = request.getParameter("regMethod");
		if ("weibo".equalsIgnoreCase(regMethod)) {// 新浪微博登陆
			weibo4j.Oauth oauth = new weibo4j.Oauth();
			try {
				String openUrl = oauth.authorize("code", ConstatVar.WEIBO_APPKEY, ConstatVar.WEIBO_SECRET);
				response.sendRedirect(openUrl);
			} catch (weibo4j.model.WeiboException e) {
				e.printStackTrace();
				// TODO 跳转到错误页，提示用户重新选择注册渠道
			}
		} else if ("qq".equalsIgnoreCase(regMethod)) {// QQ登陆
			com.qq.connect.oauth.Oauth oauth = new com.qq.connect.oauth.Oauth();
			try {
				String openUrl = oauth.getAuthorizeURL(request);
				response.sendRedirect(openUrl);
			} catch (com.qq.connect.QQConnectException e) {
				e.printStackTrace();
				// TODO 跳转到错误页，提示用户重新选择注册渠道
			}
		} else {
			// TODO
		}
		return;
	}


	/**
	 * 淘宝登陆
	 */
	@RequestMapping("/taobaotLogin.do")
	public String taobaoLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ApiException 
	{
		return null;
	}
    
}
