package com.chi.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chi.po.TaobaokeItemVo;
import com.chi.service.TaobaokeItemService;
import com.chi.service.VMFactory;

/**
 * 
 * @author wangkui
 * 
 */
@Controller(value = "flushPageController")
public class FlushPageController extends UserBaseController {

    @Resource(name = "taobaokeItemService")
    private TaobaokeItemService taobaokeItemService;
    
    
    @RequestMapping("/flushPage.do")
    public void flushPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
	String msg = "success";

	do
	{
	    int itemType = 0;
	    try
	    {
		String itemTypeStr = request.getParameter("itemType");
		itemType = Integer.parseInt(itemTypeStr);
	    } catch (Exception e)
	    {
	    }
	    if(itemType<=0)
	    {
		msg = "error";
		break;
	    }

	    List<TaobaokeItemVo> listTaobaokeItem = taobaokeItemService.listTaobaokeItemVoByType(itemType);
	    
	    String templateName = "template.vm";
	    String templatePath = getVmTemplatePath(request, templateName, itemType);
	    File templateFile = new File(templatePath);
	    VMFactory vm = new VMFactory();
	    vm.setTemplateBody(FileUtils.readFileToString(templateFile, "UTF-8"));
	    vm.put("listTaobaokeItem", listTaobaokeItem);
	    File destFile = new File("J:/data/test/test.html");
	    FileUtils.writeStringToFile(destFile, vm.toString(), "utf-8");

	} while (false);

	writePlain(request, response, msg);
	return;
    }

}
