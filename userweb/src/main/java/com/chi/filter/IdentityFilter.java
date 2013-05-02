package com.chi.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 身份验证filter
 * 
 * @author bd17kaka
 * 
 */
public class IdentityFilter implements Filter {

    private static final Logger Log = LoggerFactory.getLogger(IdentityFilter.class);

    public void destroy()
    {
	Log.info(" IdentityFilter destroy");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
    	
		Log.debug("start do Filter.....");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		chain.doFilter(req, resp);
		return;
    }

    public void init(FilterConfig filterConfig) throws ServletException
    {
    	Log.info(" IdentityFilter init");
    }

}
