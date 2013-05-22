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
 * 饮料 - 1 保健营养 - 2 进口食品 - 3 酒类 - 4 母婴食品 - 5 水果 - 6 休闲零食 - 7
 * 
 * @author wangkui
 * 
 */
@Controller(value = "mysqlDataController")
public class MysqlDataController extends UserBaseController {

    @Resource(name = "taobaokeItemService")
    private TaobaokeItemService taobaokeItemService;


    @RequestMapping("/showMysqlItems.do")
    public String showMysqlItems(HttpServletRequest request, HttpServletResponse response)
    {
	// 默认显示饮料
	int itemType = 1;
	try
	{
	    itemType = Integer.parseInt(request.getParameter("itemType"));
	} catch (Exception e)
	{
	}
	List<TaobaokeItemVo> listTaobaokeItem = taobaokeItemService.listTaobaokeItemVoByType(itemType);
	request.setAttribute("listTaobaokeItem", listTaobaokeItem);

	return "item_mysql";
    }

}
