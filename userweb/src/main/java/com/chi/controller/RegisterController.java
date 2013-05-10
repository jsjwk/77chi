package com.chi.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import weibo4j.Oauth;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;

import com.chi.constant.ConstatVar;
import com.taobao.api.ApiException;

/**
 * 用户注册
 * @author wangkui
 *
 */
@Controller(value = "registerController")
public class RegisterController extends UserBaseController {
    
    private static final Logger LOG = LoggerFactory.getLogger(RegisterController.class);
    
    /**
     * 用户选择注册渠道
     */
    @RequestMapping("/preReg.do")
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ApiException
    {
	String regMethod = request.getParameter("regMethod");
	if("weibo".equalsIgnoreCase(regMethod))
	{//新浪微博注册
	    Oauth oauth = new Oauth();
	    try
	    {
		String openUrl = oauth.authorize("code", ConstatVar.WEIBO_APPKEY, ConstatVar.WEIBO_SECRET);
		response.sendRedirect(openUrl);
	    } catch (WeiboException e)
	    {
		e.printStackTrace();
		//TODO 跳转到错误页，提示用户重新选择注册渠道
	    }
	}else{
	    //TODO 
	}
	return;
    }

    /**
     * 新浪微博注册
     */
    @RequestMapping("/weiboReg.do")
    public String weiboReg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ApiException
    {
	String code = request.getParameter("code");
	Oauth oauth = new Oauth();
	try
	{
	    AccessToken accessToken = oauth.getAccessTokenByCode(code);
	} catch (WeiboException e)
	{
	    if (401 == e.getStatusCode())
	    {
		LOG.info("Unable to get the access token.");
	    } else
	    {
		e.printStackTrace();
	    }
	}
    	return null;
    }
    
}
