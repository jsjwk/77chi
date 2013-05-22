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

    @RequestMapping("/viewItemParam.do")
    public String viewItemParam(HttpServletRequest request, HttpServletResponse response)
    {
	Long numIid = 0L;
	try
	{
	    numIid = Long.parseLong(request.getParameter("numIid"));
	} catch (Exception e)
	{
	}
	TaobaokeItemVo taobaokeItem = taobaokeItemService.getItemByNumIid(numIid);
	request.setAttribute("taobaokeItem", taobaokeItem);

	return "item_param_view";
    }
    
    /**
     * 删除mysql中商品
     */
    @RequestMapping("/ajaxDeleteItem.do")
    public void ajaxDeleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
	Long[] numIids = null;
	String msg = "";

	do
	{
	    try
	    {
		String numIidsStr = request.getParameter("numIids");
		String[] numIidsArr = numIidsStr.split(",");
		if (numIidsArr == null || numIidsArr.length <= 0)
		{
		    msg = "error";
		    break;
		}
		for (String numIidStr : numIidsArr)
		{
		    Long numIid = Long.parseLong(numIidStr);
		    boolean flag = taobaokeItemService.deleteItemByNumIid(numIid);
		    if (flag)
		    {
			msg = "success";
		    }
		}
	    } catch (Exception e)
	    {
		msg = "error";
		e.printStackTrace();
		break;
	    }

	} while (false);

	writePlain(request, response, msg);
	return;
    }

}
