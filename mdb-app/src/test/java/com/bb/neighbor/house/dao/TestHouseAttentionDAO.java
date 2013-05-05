package com.bb.neighbor.house.dao;

import org.junit.Test;

import com.bb.mongo.utils.SequenceUtil;
import com.bb.neighbor.house.model.HouseAttention;

public class TestHouseAttentionDAO {
	@Test
	public void save() {
		HouseAttentionDAO dao = HouseAttentionDAO.getInstance();
		HouseAttention houseAttention = new HouseAttention();
		houseAttention.setCreateTime(System.currentTimeMillis());
		houseAttention.setHouseId(76236);
		houseAttention.setId(SequenceUtil.getId(SequenceUtil.HOUSE_ATTENTION_ID));
		houseAttention.setUserId(27);
		houseAttention.setReservationStatus(2);
		dao.save(houseAttention);
	}
}
