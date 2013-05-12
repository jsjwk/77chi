package com.chi.controller;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chi.po.UserInfo;
import com.chi.service.UserInfoService;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.taobao.api.ApiException;

@Controller(value = "loginByQQController")
public class LoginByQQController extends UserBaseController {

	@Resource(name="userInfoService")
	private UserInfoService userInfoService;
	private static final Logger LOG = LoggerFactory.getLogger(LoginByQQController.class);
	
	
	/**
	 * QQ登陆
	 */
	@RequestMapping("/qqLogin.do")
	public String qqLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ApiException 
	{
		com.qq.connect.oauth.Oauth oauth = new com.qq.connect.oauth.Oauth();
		try {
			com.qq.connect.javabeans.AccessToken accessTokenObj = oauth.getAccessTokenByRequest(request);
			String accessToken = accessTokenObj.getAccessToken();
			// 利用获取到的accessToken 去获取当前用的openid
            OpenID openIDObj =  new OpenID(accessToken);
            String openID = openIDObj.getUserOpenID();
            com.qq.connect.api.qzone.UserInfo qzoneUserInfo = new com.qq.connect.api.qzone.UserInfo(accessToken, openID);
            com.qq.connect.javabeans.qzone.UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();

            UserInfo userInfo = new UserInfo();
            userInfo.setAccessToken(accessToken);
            userInfo.setAccountType(UserInfo.ACCOUNTTYPE_DEFAULT);
            userInfo.setCreateTime(new Date());
            userInfo.setOpenId(openID);
            if(userInfoBean!=null){
            	int gender = "男".equals(userInfoBean.getGender())==true?1:2;
            	userInfo.setGender(gender);
            	userInfo.setNickName(userInfoBean.getNickname());
            	userInfo.setSourceType(UserInfo.SOURCETYPE_QQ);
            }
            userInfoService.saveOrUpdateUserInfo(userInfo);
		} catch (QQConnectException e) {
			e.printStackTrace();
		}
		return "index";
	}

}
