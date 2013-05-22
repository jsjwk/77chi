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
	
	@RequestMapping("/indexVelocity.do")
	public String indexVelocity(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		String templateName = request.getParameter("templateName");
		String templateString = request.getParameter("templateString");
		if(StringUtils.isEmpty(templateName) || StringUtils.isEmpty(templateString))
		{
			templateName = "template.vm";
			String path = request.getSession().getServletContext().getRealPath("template/"+templateName);
			File templateFile = new File(path);
			templateString = FileUtils.readFileToString(templateFile, "UTF-8");
			
		}else{
			String path = request.getSession().getServletContext().getRealPath("template/"+templateName);
			File destFile = new File(path);
			FileUtils.writeStringToFile(destFile, templateString, "utf-8");
		}
		
		request.setAttribute("templateName", templateName);
		request.setAttribute("templateString", templateString);
		return "indexVelocity";
	}
	

	@RequestMapping("/parse.do")
	public String parse(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		List<String> list = new ArrayList<String>();
		list.add("test1");
		list.add("test2");
		
		String path = request.getSession().getServletContext().getRealPath("template/template.vm");
		File templateFile = new File(path);
		VMFactory vm = new VMFactory();
		vm.setTemplateBody(FileUtils.readFileToString(templateFile, "UTF-8"));
		vm.put("pictureList", list);
		vm.put("atlas", list);
		File destFile = new File("D:/test.html");
		FileUtils.writeStringToFile(destFile, vm.toString(), "utf-8");
		
		return "";
	}

}
