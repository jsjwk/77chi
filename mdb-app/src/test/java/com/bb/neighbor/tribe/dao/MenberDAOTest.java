package com.bb.neighbor.tribe.dao;

import org.junit.Test;

import com.bb.mongo.utils.SequenceUtil;
import com.bb.neighbor.tribe.model.TribeMember;

public class MenberDAOTest {
	@Test
	public void save() {
		// for (int i = 20; i <= 26; i++) {
		TribeMemberDAO dao = TribeMemberDAO.getInstance();
		TribeMember bean = new TribeMember();
		bean.setId(SequenceUtil.getId(SequenceUtil.TRIBE_MEMBER_ID));
		bean.setIsCreater(0);
		bean.setIsScreen(0);
		bean.setJoinTime(System.currentTimeMillis());
		bean.setMember(28);
		bean.setRole(2);
		bean.setTribeId(2);
		dao.save(bean);
		// }
	}
}
