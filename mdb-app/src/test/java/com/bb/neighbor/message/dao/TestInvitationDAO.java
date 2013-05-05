package com.bb.neighbor.message.dao;

import org.junit.Test;

import com.bb.neighbor.message.model.Invitation;
import com.google.code.morphia.query.Query;

public class TestInvitationDAO {
	@Test
	public void save() {
		InvitationDAO dao = InvitationDAO.getInstace();
		Invitation invitation = new Invitation();
		invitation.setSenderId(28);
		invitation.setRecipientId(27);
		invitation.setHouseId(48807);
		invitation.setContent("我们想和你住一起.");
		dao.save(invitation);
	}

	@Test
	public void update() {
		InvitationDAO dao = InvitationDAO.getInstace();
		Query<Invitation> query = dao.createQuery().filter("isIgnore", null);
		dao.update(query, dao.createUpdateOperations().set("isIgnore", 0));
	}

	@Test
	public void delelte() {
		InvitationDAO dao = InvitationDAO.getInstace();
		Query<Invitation> query = dao.createQuery().filter("senderId", 27);
		System.out.println(dao.deleteByQuery(query).getN());
	}

}
