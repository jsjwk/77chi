package com.chi.dao;

import com.chi.po.UserInfo;

public interface UserInfoDao {

	/**
	 * 保存一个UserInfo
	 * @param userInfo
	 * @return
	 */
	public boolean saveOrUpdateUserInfo(UserInfo userInfo);
	
}
