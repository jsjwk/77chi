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

import weibo4j.Account;
import weibo4j.model.PostParameter;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;

import com.chi.po.UserInfo;
import com.chi.service.UserInfoService;
import com.taobao.api.ApiException;

@Controller(value = "loginByWeiboController")
public class LoginByWeiboController extends UserBaseController {

	@Resource(name="userInfoService")
	private UserInfoService userInfoService;
	private static final Logger LOG = LoggerFactory.getLogger(LoginByWeiboController.class);

	/**
	 * 新浪微博登陆
	 * @throws JSONException 
	 */
	@RequestMapping("/weiboLogin.do")
	public String weiboLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ApiException, JSONException 
	{
		String code = request.getParameter("code");
		weibo4j.Oauth oauth = new weibo4j.Oauth();
		try {
			weibo4j.http.AccessToken accessTokenObj = oauth.getAccessTokenByCode(code);
			String accessToken = accessTokenObj.getAccessToken();
			Account am = new Account();
			am.client.setToken(accessToken);
			JSONObject uid = am.getUid();
			System.out.println("============"+uid);
			weibo4j.model.PostParameter[] postParameters = new PostParameter[]{new PostParameter("access_token", accessToken),new PostParameter("uid", uid.getString("uid"))};
			weibo4j.http.Response weiboResponse = am.client.get("https://api.weibo.com/2/users/show.json",postParameters);
			
			UserInfo userInfo = new UserInfo();
            userInfo.setAccessToken(accessToken);
            userInfo.setAccountType(UserInfo.ACCOUNTTYPE_DEFAULT);
            userInfo.setCreateTime(new Date());
            userInfo.setOpenId(uid.getString("uid"));
            if(weiboResponse!=null){
            	weibo4j.org.json.JSONObject jSONObject = weiboResponse.asJSONObject();
            	int gender = "m".equals(jSONObject.get("gender"))==true?1:2;
            	userInfo.setGender(gender);
            	userInfo.setNickName(jSONObject.getString("screen_name"));
            	userInfo.setSourceType(UserInfo.SOURCETYPE_WEIBO);
            }
            userInfoService.saveOrUpdateUserInfo(userInfo);
		} catch (weibo4j.model.WeiboException e) {
			if (401 == e.getStatusCode()) {
				LOG.info("Unable to get the access token.");
			} else {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
