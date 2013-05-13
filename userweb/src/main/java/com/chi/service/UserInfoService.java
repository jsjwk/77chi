package com.chi.service;

import com.chi.po.UserInfo;

public interface UserInfoService {

	/**
	 * 保存一个UserInfo
	 * @param userInfo
	 * @return
	 */
	public boolean saveOrUpdateUserInfo(UserInfo userInfo);
	
}
