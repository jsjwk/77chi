package com.chi.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chi.po.TaobaokeItemVo;
import com.chi.service.TaobaokeItemService;

/**
 * 账户信息
 * 
 * @author wangkui
 * 
 */
@Controller(value = "indexController")
public class IndexController extends UserBaseController {

	@Resource(name = "taobaokeItemService")
	private TaobaokeItemService taobaokeItemService;

	/**
	 * 进入首页
	 * 
	 */
	@RequestMapping("/index.do")
	public String index(HttpServletRequest request, HttpServletResponse response) 
	{
		List<TaobaokeItemVo> listTaobaokeItem = taobaokeItemService.findAllItems();
		request.setAttribute("listTaobaokeItem", listTaobaokeItem);

		
		return "index";
	}

}
