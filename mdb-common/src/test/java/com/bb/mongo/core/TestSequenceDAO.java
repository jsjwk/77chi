package com.bb.mongo.core;

import org.junit.Test;

import com.bb.mongo.dao.SequenceDAO;

public class TestSequenceDAO {
	@Test
	public void testGetSequence() {
		SequenceDAO sequenceDAO = SequenceDAO.getInstance();
		long id = sequenceDAO.getID("houseInfoId");
		System.out.println(id);
	}
}
