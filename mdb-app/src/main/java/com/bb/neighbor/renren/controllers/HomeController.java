package com.bb.neighbor.renren.controllers;

import javax.servlet.http.HttpServletRequest;

import net.paoding.rose.web.InvocationLocal;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.bb.neighbor.common.Constants;
import com.bb.neighbor.message.utils.MessageUtils;
import com.bb.neighbor.purpose.home.PurposeHome;
import com.bb.neighbor.purpose.model.Purpose;
import com.bb.neighbor.renren.home.RenrenAPIHome;
import com.bb.neighbor.renren.utils.RenrenUtils;
import com.bb.neighbor.tribe.utils.TribeUtils;
import com.bb.neighbor.user.home.UserHome;
import com.bb.neighbor.user.model.UserInfo;
import com.renren.api.client.RenrenApiConfig;

@Path("home")
public class HomeController {
	@Autowired
	private InvocationLocal inv;
	@Autowired
	private RenrenAPIHome renrenAPIHome;
	@Autowired
	private PurposeHome purposeHome;
	@Autowired
	private UserHome userHome;
	@Autowired
	private MessageUtils messageUtils;
	@Autowired
	private TribeUtils tribeUtils;
	
	/**
	 * 用户通过oauth后，从人人网获取用户信息
	 * @return
	 */
	@Get
	public String home() {
		HttpServletRequest request = inv.getRequest();
		String sessionKey = request.getParameter("xn_sig_session_key");
		String renrenUserId = request.getParameter("xn_sig_user");
		if (sessionKey != null && renrenUserId != null) {
			request.getSession().setAttribute("session_key", sessionKey);

			JSONArray userInfo = renrenAPIHome.getRenrenUserInfo(sessionKey, renrenUserId);
			if (userInfo != null && userInfo.size() > 0) {
				JSONObject currentUser = (JSONObject) userInfo.get(0);

				if (currentUser != null) {
					request.getSession().setAttribute("userName", (String) currentUser.get("name"));
					request.getSession().setAttribute("userHead", (String) currentUser.get("headurl"));
					
					// 保存用户信息
					UserInfo user = userHome.getUserInfoByRenrenUserId(renrenUserId);
					user = RenrenUtils.assemblyUserInfo(currentUser, user);
					userHome.saveUser(user);
					
					// 存储登录用户到session
					inv.getRequest().getSession().setAttribute(Constants.SESSION_USER_KEY, user);
					
					// 保存部落
					tribeUtils.saveTribeAboutInfoByLogin(user);
					
					// 获取消息记录数
					messageUtils.getMessageCount(inv);
				}
			}
			// 获取好友列表数据
			// 最多取30个好友
			/* JSONArray friendsList = renrenAPIHome.getFriendsList(1, 30, sessionKey); */
			// 获得安装了该应用的列表
			/*
			 * JSONArray appuserList = renrenAPIHome.getAppuserList(sessionKey); request.setAttribute("friendsList", friendsList); request.setAttribute("appUsersList", appuserList);
			 */
		}
		request.setAttribute("appId", RenrenApiConfig.renrenAppID);
		return "r:/home/purpose/turn";
	}
	
	/**
	 * 根据用户意向跳转页面
	 * @return
	 */
	@Get("/purpose/turn")
	public String purposeTurn(){
		UserInfo userInfo = (UserInfo)inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		Purpose purpose = purposeHome.getLastUpdatePurpose(userInfo.getId());
		if(null == purpose){
			//初期用户少的情况下，xx表示平台总用户数，用户多的时候应该是预约成功的用户数
			long count = userHome.getUserCount();
			inv.addModel("count", count);
			return "purpose-home";
		}else{
			switch (purpose.getPurposeType()) {
			case 1:
				return "r:/purpose/house/search";
			case 2:
				return "r:/purpose/user/search";
			default:
				return "";
			}
		}
	}
}
