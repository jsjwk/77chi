package com.chi.service;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class VMFactory
{
	private String templatebody = null;
	Template tpl = null;
	private VelocityContext context = null;
	private static VelocityEngine engine = new VelocityEngine();
	private String ret_error = "";

	static
	{
		init();
	}

	public VMFactory()
	{
		context = new VelocityContext();
	}

	public static void init()
	{
		//首先创建一个模板引擎的实例，并予以初始化
		try
		{
			//engine.setProperty(VelocityEngine.RUNTIME_LOG_LOGSYSTEM, new MyVelocityLog());
			engine.init();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	//设定模板
	public void setTemplate(String templatefile)
	{
		try
		{
			tpl = engine.getTemplate(templatefile, "GBK");
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		this.templatebody = null;
	}

	//设定模板内容
	public void setTemplateBody(String templatebody)
	{
		try
		{
			this.templatebody = templatebody;
			this.tpl = null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	//填充数据
	public void put(String name, Object obj)
	{
		// 创建上下文，填充数据
		context.put(name, obj);
	}

	public String toString()
	{
		StringWriter out = new StringWriter();
		try
		{
			output(out);
			return out.toString();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
		finally
		{
			try
			{
				out.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public String getError()
	{
		return ret_error;
	}
	
	//输出到流
	public void output(Writer out) throws IOException
	{
		try
		{
			//这样做的目的是可以使用嵌套的模板语句
			if (this.tpl != null)
			{
				// 获得一个模板
				// 把模板和数据合并，输出到Writer 
				tpl.merge(context, out);
			}
			else
			{
				// 直接使用templatebody合并
				//System.out.println("context:"+context+" out:"+out+" ");
				engine.evaluate(context, out, "", templatebody);
			}
		}
		catch (Exception e)
		{
			ret_error = e.toString(); 
			//out.write("<!--" + e.toString() + "-->");
			e.printStackTrace();
		}
	}
}
