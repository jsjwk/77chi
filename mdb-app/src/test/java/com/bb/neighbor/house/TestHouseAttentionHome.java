package com.bb.neighbor.house;

import org.junit.Test;

import com.bb.neighbor.house.home.HouseInfoHome;
import com.bb.neighbor.house.model.HouseInfo;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;

public class TestHouseAttentionHome {
	private HouseInfoHome houseInfoHome = new HouseInfoHome();
	@Test
	public void save() {
		UpdateOperations<HouseInfo> ops = houseInfoHome.getUpdateOps();
		ops.set("infoFrom", "bangnizu");
		Query<HouseInfo> query = houseInfoHome.getQuery();
		query.filter("id <>", null);
		houseInfoHome.updateMulti(query, ops);
	}
}
