package com.chi.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taobao.api.ApiException;

@Controller(value = "loginController")
public class LoginController extends UserBaseController {

    /**
     * 用户登录
     */
    @RequestMapping("/login.do")
    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ApiException
    {
    	
    	return null;
    }
    
}
