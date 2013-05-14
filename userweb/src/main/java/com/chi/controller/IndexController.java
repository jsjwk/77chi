package com.chi.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chi.po.TaobaokeItemVo;
import com.chi.service.TaobaokeItemService;
import com.taobao.api.ApiException;

/**
 * 账户信息
 * 
 * @author wangkui
 * 
 */
@Controller(value = "indexController")
public class IndexController extends UserBaseController {

    @Resource(name="taobaokeItemService")
    private TaobaokeItemService taobaokeItemService;
    
    /**
     * 进入用户信息页面
     * 
     * @throws ApiException
     */
    @RequestMapping("/test/index.do")
    public String index(HttpServletRequest request, HttpServletResponse response)
    {
	List<TaobaokeItemVo> listTaobaokeItem = taobaokeItemService.findAllItems();
	request.setAttribute("listTaobaokeItem", listTaobaokeItem);

	return "test/index";
    }

}
