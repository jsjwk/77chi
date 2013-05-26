package com.chi.dao.mysql;

import com.chi.po.UserInfo;

public interface UserInfoDao {

	/**
	 * 保存一个UserInfo
	 * @param userInfo
	 * @return
	 */
	public boolean saveOrUpdateUserInfo(UserInfo userInfo);
	
	/**
	 * 根据openId获取UserInfo
	 * @param openId
	 * @return
	 */
	UserInfo getUserInfoByOpenId(String openId);
	
	/**
	 * 根据email获取UserInfo
	 * @param openId
	 * @return
	 */
	UserInfo getUserInfoByEmail(String email);
}
