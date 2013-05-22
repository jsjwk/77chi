package com.chi.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 饮料 - 1 保健营养 - 2 进口食品 - 3 酒类 - 4 母婴食品 - 5 水果 - 6 休闲零食 - 7
 * 
 * @author wangkui
 * 
 */
@Controller(value = "adminIndexController")
public class AdminIndexController extends UserBaseController {

    @RequestMapping("/index.do")
    public String index(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
	response.sendRedirect("/showMongoItems.do?itemType=1");
	return "";
    }

}
