package com.chi.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chi.service.VMFactory;

/**
 * 
 * @author wangkui
 * 
 */
@Controller(value = "velocityController")
public class VelocityController extends UserBaseController {

    @RequestMapping("/showVelocity.do")
    public String showVelocity(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
	// 默认显示饮料
	int itemType = 1;
	try
	{
	    itemType = Integer.parseInt(request.getParameter("itemType"));
	} catch (Exception e)
	{
	}
	request.setAttribute("itemType", itemType);
	
	String templateName = request.getParameter("templateName");
	String templateString = request.getParameter("templateString");
	if (StringUtils.isEmpty(templateName) || StringUtils.isEmpty(templateString))
	{//显示
	    templateName = "template.vm";
	    String path = getVmTemplatePath(request, templateName, itemType);
	    File templateFile = new File(path);
	    templateString = FileUtils.readFileToString(templateFile, "UTF-8");

	} else
	{//更新
	    String path = getVmTemplatePath(request, templateName, itemType);
	    File destFile = new File(path);
	    FileUtils.writeStringToFile(destFile, templateString, "utf-8");
	}

	request.setAttribute("templateName", templateName);
	request.setAttribute("templateString", templateString);
	return "showVelocity";
    }


}
