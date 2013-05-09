package com.bb.neighbor.user.home;

import org.junit.Test;

import com.bb.mongo.utils.SequenceUtil;
import com.bb.neighbor.common.StatusConstants;
import com.bb.neighbor.user.model.UserInfo;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;

public class TestUserHome {
	private UserHome userHome = new UserHome();

	@Test
	public void testInsertUser() {
		UserInfo ui = new UserInfo();
		ui.setId(SequenceUtil.getId(SequenceUtil.USERINFO_ID_VALUE));
		ui.setUserName("dabao");
		userHome.save(ui);
	}

	@Test
	public void testUpdateUser() {
		UpdateOperations<UserInfo> ops = userHome.getUpdateOps();
		ops.set("isActivate", StatusConstants.USER_ACCOUNT_ACTIVATE_NOT);
		ops.set("authCode", 1234);
		Query<UserInfo> query = userHome.getQuery();
		query.filter("id =", 28);
		userHome.update(query, ops);
	}
}
