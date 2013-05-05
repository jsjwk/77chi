package com.bb.neighbor.purpose;

import java.util.List;
import java.util.regex.Pattern;

import org.junit.Test;

import com.bb.neighbor.area.home.AreaHome;
import com.bb.neighbor.area.model.Area;
import com.bb.neighbor.house.dao.HouseDAO;
import com.bb.neighbor.house.model.HouseInfo;
import com.bb.neighbor.purpose.home.PurposeHome;
import com.bb.neighbor.purpose.model.Purpose;
import com.google.code.morphia.query.Query;
import com.google.gson.Gson;

public class TestPurposeHome {
	private AreaHome areaHome = new AreaHome();
	private PurposeHome purposeHome = new PurposeHome();
	
	//@Test
	public void testPurposeSearchCode(){
		Query<Area> query = areaHome.getQuery();
		Pattern re = Pattern.compile("01\\d{4}");
		query.filter("code", re);
		List<Area> areaList = query.asList();
		for(Area area : areaList){
			System.out.println(area.getCode());
		}
		System.out.println(areaList.size());
	}
	
	@Test
	public void testInsertPurpose(){
		Purpose p = new Purpose();
		p.setAreaCode("010000");
		purposeHome.save(p);
		
		HouseDAO dao = HouseDAO.getInstance();
		Query<HouseInfo> query = dao.createQuery();
		query.field("discription").equal("13545885415");
		HouseInfo hi = dao.findOne(query);
		System.out.println(hi.getId());
		Gson gson = new Gson();
		System.out.println(gson.toJson(hi));
	}
}
