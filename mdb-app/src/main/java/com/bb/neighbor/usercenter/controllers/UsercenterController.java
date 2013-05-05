package com.bb.neighbor.usercenter.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.paoding.rose.web.InvocationLocal;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bb.mongo.utils.DateFormat;
import com.bb.neighbor.area.home.AreaHome;
import com.bb.neighbor.area.model.Area;
import com.bb.neighbor.common.Constants;
import com.bb.neighbor.common.Page;
import com.bb.neighbor.house.home.HouseAttentionHome;
import com.bb.neighbor.house.home.HouseCommentHome;
import com.bb.neighbor.house.home.HouseInfoHome;
import com.bb.neighbor.house.model.HouseAttention;
import com.bb.neighbor.house.model.HouseImg;
import com.bb.neighbor.house.model.HouseInfo;
import com.bb.neighbor.message.home.ReservationHome;
import com.bb.neighbor.message.model.Reservation;
import com.bb.neighbor.message.utils.ReservationUtils;
import com.bb.neighbor.purpose.home.PurposeHome;
import com.bb.neighbor.purpose.model.Purpose;
import com.bb.neighbor.task.utils.MatcherUtil;
import com.bb.neighbor.user.home.UserHome;
import com.bb.neighbor.user.model.Habit;
import com.bb.neighbor.user.model.UserInfo;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;

/**
 * 
 * @author ly.jiao@live.cn
 * 
 */
@Path("/usercenter")
public class UsercenterController {
	@Autowired
	private InvocationLocal inv;
	@Autowired
	private UserHome userHome;
	@Autowired
	private AreaHome areaHome;
	@Autowired
	private HouseInfoHome houseHome;
	@Autowired
	private PurposeHome purposeHome;
	@Autowired
	private ReservationHome reservationHome;
	@Autowired
	private HouseAttentionHome houseAttentionHome;
	@Autowired
	private ReservationUtils reservationUtils;
	@Autowired
	private HouseCommentHome houseCommentHome;

	/**
	 * 个人资料
	 * 
	 * @param tribeTopic
	 * @return
	 */
	@Get("/infocenter")
	public String info() {
		return "user-infocenter";
	}

	@Post("/saveInfo")
	public String saveInfo() {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		Query<UserInfo> query = userHome.getQuery().filter("id", userInfo.getId());
		UpdateOperations<UserInfo> ops = userHome.getUpdateOps();
		String career = inv.getParameter("professionMe");
		if (StringUtils.isNotEmpty(career)) {
			ops.set("career", career);
		}
		String smoke = inv.getParameter("smoke");
		String havePet = inv.getParameter("havePet");
		Habit habit = null;
		if (StringUtils.isNotEmpty(smoke) || StringUtils.isNotEmpty(havePet)) {
			habit = new Habit();
			if (StringUtils.isNotEmpty(smoke)) {
				habit.setSmoke(Integer.parseInt(smoke));
			}
			if (StringUtils.isNotEmpty(havePet)) {
				habit.setHavePet(Integer.parseInt(havePet));
			}
		}
		if (habit != null) {
			ops.set("habit", habit);
		}
		String mobile = inv.getParameter("mobile");
		if (StringUtils.isNotEmpty(mobile)) {
			ops.set("mobile", mobile);
		}
		// 更新
		userHome.update(query, ops);
		UserInfo info = userHome.getUserInfoByUserId(userInfo.getId());
		inv.getRequest().getSession().setAttribute(Constants.SESSION_USER_KEY, info);
		return "user-infocenter";
	}

	/**
	 * 得到房子意向
	 * 
	 * @return
	 */
	@Get("/housePurpose")
	public String housePurpose() {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		Purpose purpose = purposeHome.getLastUpdateHousePurpose(userInfo.getId());
		if (purpose != null) {
			if (StringUtils.isNotEmpty(purpose.getAreaCode())) {
				String cityName = areaHome.getCityNameByCode(purpose.getAreaCode());
				if (StringUtils.isNotEmpty(cityName)) {
					purpose.setCityName(cityName);
				}
			}
			Area area = areaHome.getAreaByCode(purpose.getAreaCode());
			if (area != null) {
				purpose.setAreaName(area.getName());
			}
			String houseType = purpose.getHouseType();
			if (StringUtils.isNotEmpty(houseType)) {
				purpose.setHouseType1(MatcherUtil.matcher("(\\d+|不限|其他)室", houseType).replace("室", ""));
				purpose.setHouseType2(MatcherUtil.matcher("(\\d+|不限|其他)厅", houseType).replace("厅", ""));
				purpose.setHouseType3(MatcherUtil.matcher("(\\d+|不限|其他)卫", houseType).replace("卫", ""));
			}
			inv.addModel("purpose", purpose);
		}
		return "user-find-house";
	}

	/**
	 * 得到合租伙伴意向
	 * 
	 * @return
	 */
	@Get("/personPurpose")
	public String personPurpose() {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		Purpose purpose = purposeHome.getLastUpdatePersonPurpose(userInfo.getId());
		if (purpose != null) {
			inv.addModel("purpose", purpose);
		}
		return "user-find-person";
	}

	/**
	 * 得到我感兴趣的房源列表
	 * 
	 * @return
	 */
	@Get("/attentionHouse/old")
	public String attentionHouse2() {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		int pageIndex = inv.getParameter("page") != null ? Integer.parseInt(inv.getParameter("page")) : 1;
		int total = houseAttentionHome.countByUserId(userInfo.getId());
		List<HouseAttention> list = houseAttentionHome.getListByUserId(userInfo.getId(), pageIndex, 5);
		List<HouseInfo> houseList = new ArrayList<HouseInfo>();
		for (HouseAttention bean : list) {
			HouseInfo house = houseHome.findById(bean.getHouseId());
			if (house != null) {
				house.setAttentionId(bean.getId());
				UserInfo user = userHome.getUserInfoByUserId(house.getPublisherId());
				house.setPublisher(user);
				Reservation reservation = reservationHome.getReservation(userInfo.getId(), house.getPublisherId(), house.getId());
				house.setReservation(reservation);
				houseList.add(house);
			}
		}
		if (houseList.size() > 0) {
			inv.addModel("houseList", houseList);
		}
		Page<HouseInfo> page = new Page<HouseInfo>(total, 5, pageIndex, houseList);
		inv.addModel("page", page);
		return "user-attentionHouses";
	}

	/**
	 * 得到我感兴趣的房源列表
	 * 
	 * @return
	 */
	@Get("/attentionHouse")
	public String attentionHouse() {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		int pageIndex = inv.getParameter("page") != null ? Integer.parseInt(inv.getParameter("page")) : 1;
		List<HouseAttention> list = houseAttentionHome.getByUserId(userInfo.getId());
		if (list.size() > 0) {
			Map<Integer, HouseAttention> attentionMap = new HashMap<Integer, HouseAttention>();
			for (HouseAttention bean : list) {
				attentionMap.put(bean.getHouseId(), bean);
			}
			int total = houseHome.countByIds(attentionMap.keySet());
			String orderBy = inv.getParameter("orderBy");
			int type = Constants.ORDERBY_ASC;
			// 按租金升序排列
			if ("rent".equals(orderBy)) {
				orderBy = "rent";
			} else {
				// 默认按时间倒序排列
				orderBy = "updateTime";
				type = Constants.ORDERBY_DESC;
			}
			List<HouseInfo> houseList = houseHome.findByIds(attentionMap.keySet(), pageIndex, 5, orderBy, type);
			for (HouseInfo bean : houseList) {
				bean.setAttentionId(attentionMap.get(bean.getId()).getId());
				UserInfo user = userHome.getUserInfoByUserId(bean.getPublisherId());
				bean.setPublisher(user);
				Reservation reservation = reservationHome.getReservation(userInfo.getId(), bean.getPublisherId(), bean.getId());
				bean.setReservation(reservation);
			}

			Page<HouseInfo> page = new Page<HouseInfo>(total, 5, pageIndex, houseList);
			inv.addModel("page", page);
			inv.addModel("orderBy", orderBy);
		}
		return "user-attentionHouses";
	}

	/**
	 * 修改我出租的房子
	 * 
	 * @return
	 */
	@Get("/rentout/house")
	public String rentHouse() {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		HouseInfo house = houseHome.findByPublisherId(userInfo.getId());
		if (house != null) {
			Area area = house.getArea();
			if (area != null) {
				String cityName = areaHome.getCityNameByCode(area.getCode());
				if (StringUtils.isNotEmpty(cityName)) {
					house.setCityName(cityName);
				}
			}
			String houseType = house.getHouseType();
			if (StringUtils.isNotEmpty(houseType)) {
				inv.addModel("houseType1", MatcherUtil.matcher("(\\d+|不限|其他)室", houseType).replace("室", ""));
				inv.addModel("houseType2", MatcherUtil.matcher("(\\d+|不限|其他)厅", houseType).replace("厅", ""));
				inv.addModel("houseType3", MatcherUtil.matcher("(\\d+|不限|其他)卫", houseType).replace("卫", ""));
			}
			inv.addModel("house", house);
		}
		return "user-rent-house";
	}

	/**
	 * 修改我出租的房子
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Post("/rentout/houseModify/{houseId}")
	public String rentHouseModify(@Param("houseId") Integer houseId, HouseInfo houseInfo) {
		if (houseId > 0) {
			List<HouseImg> houseImgList = (List<HouseImg>) inv.getRequest().getSession().getAttribute("houseImgList");
			houseInfo.setHouseImgList(houseImgList);
			Area area = areaHome.getAreaByCode(inv.getParameter("areaCode"));
			houseInfo.setArea(area);
			houseInfo.setHouseType(inv.getParameter("houseType1") + "室" + inv.getParameter("houseType2") + "厅" + inv.getParameter("houseType3") + "卫");
			String checkInTime = inv.getParameter("checkInTime");
			if (StringUtils.isNotBlank(checkInTime)) {
				houseInfo.setCheckInTime(DateFormat.stringToLong(checkInTime, "yyyy-MM-dd"));
			}
			// 更新
			houseInfo.setId(houseId);
			houseHome.update(houseInfo);
		}
		return "r:/usercenter/rentout/house";
	}

	/**
	 * 出租:得到合租伙伴意向
	 * 
	 * @return
	 */
	@Get("/rentout/personPurpose")
	public String rentPersonPurpose() {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		Purpose purpose = purposeHome.getUserPurpose(userInfo.getId(), Constants.PURPOSE_TYPE_USER);
		if (purpose != null) {
			inv.addModel("purpose", purpose);
		}
		return "user-rent-person";
	}

	/**
	 * 我出租:收到的预约
	 * 
	 * @return
	 */
	@Get("/rentout/myReservation")
	public String myReservation() {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		int pageIndex = inv.getParameter("page") != null ? Integer.parseInt(inv.getParameter("page")) : 1;
		int total = reservationHome.countByUserId(userInfo.getId());
		List<Reservation> reservations = reservationHome.getReservationList(userInfo.getId(), pageIndex, 10);
		for (Reservation bean : reservations) {
			// 填充用户信息
			bean.setSenderInfo(userHome.getUserInfoByUserId(bean.getSender()));
			// 填充房屋信息
			bean.setHouse(houseHome.findById(bean.getHouseId()));
		}
		Page<Reservation> page = new Page<Reservation>(total, 10, pageIndex, reservations);
		inv.addModel("page", page);
		return "user-rent-reservation";
	}

	/**
	 * 预约拒绝
	 * 
	 * @param id
	 * @param curPage
	 * @return
	 */
	@Get("/reservation/refuse/{id}/{curPage}")
	public String refuse(@Param("id") Integer id, @Param("curPage") int curPage) {
		reservationUtils.refuseReservation(id);
		return "@<script>alert('预约已成功拒绝！');location.href='/usercenter/rentout/myReservation?page=" + curPage + "';</script>";
	}

	/**
	 * 接受预约
	 * 
	 * @param id
	 * @param curPage
	 * @return
	 */
	@Get("/reservation/accept/{id}/{curPage}")
	public String accept(@Param("id") Integer id, @Param("curPage") int curPage) {
		reservationUtils.acceptReservation(id);
		return "@<script>alert('预约已成功接受！');location.href='/usercenter/rentout/myReservation?page=" + curPage + "';</script>";
	}

	/**
	 * 批量删除预约
	 * 
	 * @param idArray
	 * @param curPage
	 * @return
	 */
	@Get("/reservation/delete/{ids}/{curPage}")
	public String batchDelete(@Param("ids") Integer[] idArray, @Param("curPage") int curPage) {
		reservationUtils.batchDeleteReservation(idArray);
		return "r:/usercenter/rentout/myReservation?page=" + curPage;
	}

	@Get("/listHosueComment")
	public String listHosueComment() {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		int pageIndex = inv.getParameter("page") != null ? Integer.parseInt(inv.getParameter("page")) : 1;
		int total = reservationHome.countAcceptByUserId(userInfo.getId());
		// 得到预约成功的房子
		List<Reservation> reservationList = reservationHome.getAcceptReservationList(userInfo.getId(), pageIndex, 5);
		List<HouseInfo> houses = new ArrayList<HouseInfo>();
		if (reservationList != null && reservationList.size() > 0) {
			// 得到房子列表
			for (Reservation bean : reservationList) {
				HouseInfo house = houseHome.findById(bean.getHouseId());
				if (house != null) {
					house.setReservation(bean);
					// 得到评价数
					house.setCommentNum(houseCommentHome.countByHouseId(house.getId()));
					houses.add(house);
				}
			}
		}
		Page<HouseInfo> page = new Page<HouseInfo>(total, 10, pageIndex, houses);
		inv.addModel("page", page);
		return "user-list-houseComment";
	}
}