package com.bb.neighbor.user.dao;

import com.bb.mongo.core.BasicDAO;
import com.bb.mongo.core.MongoConstants;
import com.bb.neighbor.user.model.UserInfo;

public class UserDAO extends BasicDAO<UserInfo, String> {

	private UserDAO() {
		super(UserInfo.class, MongoConstants.METADATA);
	}

	private static UserDAO dao;

	public static UserDAO getInstance() {
		if (dao == null) {
			dao = new UserDAO();
		}
		return dao;
	}

}
