package com.bb.neighbor.search.controllers;

import java.util.List;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bb.neighbor.area.home.AreaHome;
import com.bb.neighbor.area.model.Area;
import com.bb.neighbor.common.Constants;
import com.bb.neighbor.common.Page;
import com.bb.neighbor.house.model.HouseInfo;
import com.bb.neighbor.purpose.home.PurposeHome;
import com.bb.neighbor.purpose.model.Purpose;
import com.bb.neighbor.search.home.SearchHome;
import com.bb.neighbor.search.model.SearchModel;
import com.bb.neighbor.user.model.UserInfo;
/**
 * 查询控制器
 * @author song.zhang@boco.jp
 *
 */
@Path("/search")
public class SearchController {
	@Autowired
	private SearchHome searchHome;
	@Autowired
	private Invocation inv;
	@Autowired
	private AreaHome areaHome;
	@Autowired
	private PurposeHome purposeHome;
	private static final String USER = "user";
	
	/**
	 * 查询页面跳转
	 * @return
	 */
	@Get("")
	public String search(){
		UserInfo userInfo = (UserInfo)inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		String searchType = "";
		
		String cityName = StringUtils.isBlank(inv.getParameter("cityName")) ? Constants.BEIJING : inv.getParameter("cityName");
		String cityCode = areaHome.findCodeByName(cityName, Constants.AREA_LEVEL_CITY);
		List<Area> searchAreaList = areaHome.getAreaListByCityCode(cityCode);
		inv.getRequest().getSession().setAttribute("searchAreaList", searchAreaList);
		inv.getRequest().getSession().setAttribute("searchCityName", cityName);
		
		if(StringUtils.isBlank(inv.getParameter("searchType"))){
			Purpose purpose = purposeHome.getLastUpdatePurpose(userInfo.getId());
			if(purpose.getPurposeType() == Constants.PURPOSE_TYPE_USER){
				return "search-user";
			}
		}else{
			searchType = inv.getParameter("searchType");
			if(searchType.equals(USER)){
				return "search-user";
			}
		}
		return "search-house";
	}

	/**
	 * 房屋查询
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Get("/house")
	public String searchHouse(){
		List<Area> searchAreaList = (List<Area>)inv.getRequest().getSession().getAttribute("searchAreaList");
		if(null == searchAreaList){
			String cityName = StringUtils.isBlank(inv.getParameter("cityName")) ? Constants.BEIJING : inv.getParameter("cityName");
			String cityCode = areaHome.findCodeByName(cityName, Constants.AREA_LEVEL_CITY);
			searchAreaList = areaHome.getAreaListByCityCode(cityCode);
			inv.getRequest().getSession().setAttribute("searchAreaList", searchAreaList);
			inv.getRequest().getSession().setAttribute("searchCityName", cityName);
		}
		
		String keyWord = "";
		if(StringUtils.isNotBlank(inv.getParameter("keyWord"))){
			keyWord = StringUtils.trim(inv.getParameter("keyWord")).equals("关键词") ? null : StringUtils.trim(inv.getParameter("keyWord"));
		}
		String rentStart = StringUtils.isBlank(inv.getParameter("rentStart")) ? "0" : inv.getParameter("rentStart");
		String rentStop = StringUtils.isBlank(inv.getParameter("rentStop")) ? "0" : inv.getParameter("rentStop");
		String rent = StringUtils.isBlank(inv.getParameter("rent")) ? "0" : inv.getParameter("rent");
		String areaCode = inv.getParameter("areaCode");
		String houseType = inv.getParameter("houseType");
		
		SearchModel searchModel = new SearchModel();
		searchModel.setKeyWord(keyWord);
		searchModel.setRentStart(rentStart);
		searchModel.setRentStop(rentStop);
		searchModel.setRent(rent);
		searchModel.setAreaCode(areaCode);
		searchModel.setHouseType(houseType);
		
		inv.addModel("keyWord", keyWord);
		inv.addModel("rent", rent);
		inv.addModel("areaCode", areaCode);
		inv.addModel("houseType", houseType);
		int pageIndex = inv.getParameter("page") != null ? Integer.parseInt(inv.getParameter("page")) : 1;
		int total = searchHome.countGetHouseInfoList(searchModel);
		List<HouseInfo> houseList = searchHome.getHouseInfoList(searchModel, pageIndex, Constants.PAGE_SIZE_SEARCH);
		List<UserInfo> publisherList = searchHome.getPublisherListByHouseInfoList(houseList);
		Page<HouseInfo> page = new Page<HouseInfo>(total, Constants.PAGE_SIZE_SEARCH, pageIndex, houseList);
		inv.addModel("page", page);
		inv.addModel("houseList", houseList);
		inv.addModel("publisherList", publisherList);
		return "search-house";
	}
	
	/**
	 * 用户搜索
	 * @return
	 */
	@Get("/user")
	public String searchUser(){
		String rentStart = StringUtils.isBlank(inv.getParameter("rentStart")) ? "0" : inv.getParameter("rentStart");
		String rentStop = StringUtils.isBlank(inv.getParameter("rentStop")) ? "0" : inv.getParameter("rentStop");
		String areaCode = inv.getParameter("areaCode");
		
		SearchModel searchModel = new SearchModel();
		searchModel.setRentStart(rentStart);
		searchModel.setRentStop(rentStop);
		searchModel.setRent(inv.getParameter("rent"));
		searchModel.setAreaCode(areaCode);
		searchModel.setSex(inv.getParameter("sex"));
		searchModel.setHabit(inv.getParameter("habit"));
		searchModel.setCareer(inv.getParameter("career"));
		UserInfo userInfo = (UserInfo)inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		searchModel.setUserInfoId(userInfo.getId());
		
		//根据意向条件获取找房意向列表
		int pageIndex = inv.getParameter("page") != null ? Integer.parseInt(inv.getParameter("page")) : 1;
		int total = searchHome.countGetPurposeList(searchModel);
		List<Purpose> purposeList = searchHome.getPurposeList(searchModel, pageIndex, Constants.PAGE_SIZE_SEARCH);
		Page<Purpose> page = new Page<Purpose>(total, Constants.PAGE_SIZE_SEARCH, pageIndex, purposeList);
		//根据找房意向列表，获取用户列表
		List<UserInfo> userInfoList = searchHome.getUserInfoListByPurposeList(purposeList);
		
		inv.addModel("rentStart", rentStart);
		inv.addModel("rentStop", rentStop);
		inv.addModel("rent", inv.getParameter("rent"));
		inv.addModel("areaCode", areaCode);
		inv.addModel("sex", inv.getParameter("sex"));
		inv.addModel("habit", inv.getParameter("habit"));
		inv.addModel("career", inv.getParameter("career"));
		
		inv.addModel("userInfoList", userInfoList);
		inv.addModel("purposeList", purposeList);
		inv.addModel("userInfoList", userInfoList);
		inv.addModel("page", page);
		return "search-user";
	}
}
