package com.chi.controller;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chi.po.UserInfo;
import com.chi.service.UserInfoService;
import com.taobao.api.ApiException;

/**
 * 用户注册
 * 
 * @author wangkui
 * 
 */
@Controller(value = "registerController")
public class RegisterController extends UserBaseController {

	@Resource(name="userInfoService")
	private UserInfoService userInfoService;
	private static final Logger LOG = LoggerFactory.getLogger(RegisterController.class);

    /**
     * 用户登录
     */
    @RequestMapping("/reg.do")
    public String register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ApiException
    {
    	String email = request.getParameter("email");
    	String password = request.getParameter("password");
    	String repassword = request.getParameter("repassword");
    	if(StringUtils.isEmpty(email) || StringUtils.isEmpty(password)
    			|| !password.equals(repassword))
    	{
    		return "error";
    	}
    	
    	UserInfo userInfo = new UserInfo();
    	userInfo.setAccountType(UserInfo.ACCOUNTTYPE_DEFAULT);
    	userInfo.setCreateTime(new Date());
    	userInfo.setEmail(email);
    	userInfo.setNickName(email.substring(0, email.indexOf("@")));
    	userInfo.setSourceType(UserInfo.SOURCETYPE_REGISTER);
    	userInfo.setPassword(password);
    	userInfoService.saveOrUpdateUserInfo(userInfo);
    	return "index";
    }

}
