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
 * 
 * @author wangkui
 * 
 */
@Controller(value = "adminIndexController")
public class AdminIndexController extends UserBaseController {

	@Resource(name = "taobaokeItemService")
	private TaobaokeItemService taobaokeItemService;

	@RequestMapping("/index.do")
	public String index(HttpServletRequest request, HttpServletResponse response) 
	{
		List<TaobaokeItemVo> listTaobaokeItem = taobaokeItemService.findAllItems();
		request.setAttribute("listTaobaokeItem", listTaobaokeItem);

		return "index";
	}

}
