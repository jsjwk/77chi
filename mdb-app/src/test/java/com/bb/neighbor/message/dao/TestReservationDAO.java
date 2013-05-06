package com.bb.neighbor.message.dao;

import org.junit.Test;

import com.bb.mongo.utils.SequenceUtil;
import com.bb.neighbor.message.model.Reservation;
import com.google.code.morphia.query.Query;

public class TestReservationDAO {
	@Test
	public void save() {
		ReservationDAO dao = ReservationDAO.getInstace();
		Reservation reservation = new Reservation();
		reservation.setId(SequenceUtil.getId(SequenceUtil.RESERVATION_ID));
		reservation.setSender(28);
		reservation.setRecipient(27);
		reservation.setHouseId(71689);
		reservation.setContent("测试评价信息");
		reservation.setSendTime(System.currentTimeMillis());
		reservation.setSenderPhone("12456235663");
		reservation.setHandleStatus(1);
		dao.save(reservation);
	}

	@Test
	public void update() {
		ReservationDAO dao = ReservationDAO.getInstace();
		Query<Reservation> query = dao.createQuery().filter("isDelete", null);
		dao.update(query, dao.createUpdateOperations().set("isDelete", 0));
	}
}
