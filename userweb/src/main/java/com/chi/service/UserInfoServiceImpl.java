package com.chi.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chi.dao.UserInfoDao;
import com.chi.po.UserInfo;

@Service(value="userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

	@Resource(name="userInfoDao")
	private UserInfoDao userInfoDao; 
	
	@Override
	public boolean saveOrUpdateUserInfo(UserInfo userInfo) 
	{
		return userInfoDao.saveOrUpdateUserInfo(userInfo);
	}

}
