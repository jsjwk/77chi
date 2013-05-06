package com.bb.neighbor.renren.home;

import org.json.simple.JSONArray;
import org.springframework.stereotype.Component;
import com.renren.api.client.RenrenApiClient;
import com.renren.api.client.param.impl.SessionKey;

@Component
public class RenrenAPIHome {

	private static RenrenApiClient apiClient = RenrenApiClient.getInstance();
	private static final String FIELDS = "uid,name,sex,star,zidou,vip,birthday,tinyurl,headurl,mainurl,hometown_location,work_history,university_history,hs_history";

	/**
	 * 获取用户名头像
	 * 
	 * @param sessionKey
	 * @param renrenUserId
	 * @return
	 */
	public JSONArray getRenrenUserInfo(String sessionKey, String renrenUserId) {
		JSONArray userInfo = apiClient.getUserService().getInfo(renrenUserId, FIELDS, new SessionKey(sessionKey));
		return userInfo;
	}

	/**
	 * 获取好友列表数据
	 * 
	 * @param page
	 * @param count
	 * @param sessionKey
	 * @return
	 */
	public JSONArray getFriendsList(int page, int count, String sessionKey) {
		JSONArray friendsList = apiClient.getFriendsService().getFriends(page, count, new SessionKey(sessionKey));
		return friendsList;
	}

	/**
	 * 获得安装了该应用的列表
	 * 
	 * @param sessionKey
	 * @return
	 */
	public JSONArray getAppuserList(String sessionKey) {
		JSONArray appuserList = apiClient.getFriendsService().getAppUsers("tinyurl", new SessionKey(sessionKey));
		return appuserList;
	}
}
