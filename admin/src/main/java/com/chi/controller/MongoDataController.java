package com.chi.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chi.po.MongoTaobaokeItemVo;
import com.chi.po.TaobaokeItemVo;
import com.chi.service.MongoTaobaokeItemService;
import com.chi.service.TaobaokeItemService;

/**
 * 饮料 - 1 保健营养 - 2 进口食品 - 3 酒类 - 4 母婴食品 - 5 水果 - 6 休闲零食 - 7
 * 
 * @author wangkui
 * 
 */
@Controller(value = "mongoDataController")
public class MongoDataController extends UserBaseController {

    @Resource(name = "mongoTaobaokeItemService")
    private MongoTaobaokeItemService mongoTaobaokeItemService;
    @Resource(name = "taobaokeItemService")
    private TaobaokeItemService taobaokeItemService;

    
    @RequestMapping("/showMongoItems.do")
    public String showMongoItems(HttpServletRequest request, HttpServletResponse response)
    {
	// List<TaobaokeItemVo> listTaobaokeItem = mongoTaobaokeItemService.findAllItems();
	// 默认显示饮料
	int itemType = 1;
	try
	{
	    itemType = Integer.parseInt(request.getParameter("itemType"));
	} catch (Exception e)
	{
	}
	request.setAttribute("itemType", itemType);
	
	List<MongoTaobaokeItemVo> listTaobaokeItem = mongoTaobaokeItemService.findItemsByItemType(itemType);
	request.setAttribute("listTaobaokeItem", listTaobaokeItem);
	
	return "item_mongo";
    }

    /**
     * 将此类型下面所有的商品上线
     * @throws IOException 
     */
    @RequestMapping("/selectTypeAllItems.do")
    public void selectTypeAllItems(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
	int itemType = 1;
	try
	{
	    itemType = Integer.parseInt(request.getParameter("itemType"));
	} catch (Exception e)
	{
	}
	List<MongoTaobaokeItemVo> listTaobaokeItem = mongoTaobaokeItemService.findItemsByItemType(itemType);
	for (MongoTaobaokeItemVo mongoTaobaokeItemVo : listTaobaokeItem)
	{
	    TaobaokeItemVo taobaokeItemVo = new  TaobaokeItemVo(mongoTaobaokeItemVo);
	    boolean flag = taobaokeItemService.save(taobaokeItemVo);
	    if (flag)
	    {
		mongoTaobaokeItemService.deleteItemByNumIid(mongoTaobaokeItemVo.getNumIid());
	    }
	}
	
	response.sendRedirect("/showMongoItems.do?itemType="+itemType);
	return;
    }

    /**
     * 选择商品放入mysql
     */
    @RequestMapping("/ajaxUsedItem.do")
    public void ajaxUsedItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
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
		    MongoTaobaokeItemVo mongoTaobaokeItemVo = mongoTaobaokeItemService.getItemByNumIid(numIid);
		    TaobaokeItemVo taobaokeItemVo = new  TaobaokeItemVo(mongoTaobaokeItemVo);
		    boolean flag = taobaokeItemService.save(taobaokeItemVo);
		    if (flag)
		    {
			mongoTaobaokeItemService.deleteItemByNumIid(numIid);
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
