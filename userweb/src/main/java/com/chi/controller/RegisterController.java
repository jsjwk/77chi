package com.chi.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chi.constant.ConstatVar;
import com.qq.connect.QQConnectException;
import com.taobao.api.ApiException;

/**
 * 用户注册
 * 
 * @author wangkui
 * 
 */
@Controller(value = "registerController")
public class RegisterController extends UserBaseController {

	private static final Logger LOG = LoggerFactory.getLogger(RegisterController.class);


}
