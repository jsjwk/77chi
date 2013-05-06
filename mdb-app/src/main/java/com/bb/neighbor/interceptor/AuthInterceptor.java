package com.bb.neighbor.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.paoding.rose.web.ControllerInterceptorAdapter;
import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Interceptor;
import org.springframework.stereotype.Component;
/**
 * 人人网授权拦截器
 * @author song.zhang@boco.jp
 *
 */
@Component
@Interceptor(oncePerRequest = true)
public class AuthInterceptor extends ControllerInterceptorAdapter {

	@Override
	protected Object before(Invocation inv) throws Exception{
		HttpServletRequest request = inv.getRequest();
		//判断是否授权过
	    String isAuth = request.getParameter("xn_sig_added");
	    if(null != isAuth && isAuth.equals("0")){
	    	return "r:/auth";
	    }
	    
	    HttpServletResponse response = inv.getResponse();
		response.setHeader("P3P","CP=CAO PSA OUR");
		return true;
	}
}
