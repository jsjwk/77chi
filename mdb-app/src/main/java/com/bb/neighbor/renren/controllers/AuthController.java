package com.bb.neighbor.renren.controllers;

import javax.servlet.http.HttpServletRequest;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

import com.renren.api.client.RenrenApiConfig;

@Path("auth")
public class AuthController {

	@Get
	public String auth(Invocation inv){
		HttpServletRequest request = inv.getRequest();
		request.setAttribute("appId", RenrenApiConfig.renrenAppID);
		return "auth";
	}
}
