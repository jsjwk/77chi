package com.bb.neighbor.purpose.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.paoding.rose.web.InvocationLocal;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.bb.mongo.utils.DateFormat;
import com.bb.mongo.utils.SequenceUtil;
import com.bb.neighbor.area.home.AreaHome;
import com.bb.neighbor.area.model.Area;
import com.bb.neighbor.common.Constants;
import com.bb.neighbor.common.ImageSize;
import com.bb.neighbor.common.Page;
import com.bb.neighbor.common.StatusConstants;
import com.bb.neighbor.common.UploadUtil;
import com.bb.neighbor.common.constant.CrawlerConstants;
import com.bb.neighbor.community.home.CommunityInfoHome;
import com.bb.neighbor.community.model.CommunityInfo;
import com.bb.neighbor.house.home.HouseAttentionHome;
import com.bb.neighbor.house.home.HouseInfoHome;
import com.bb.neighbor.house.model.HouseImg;
import com.bb.neighbor.house.model.HouseInfo;
import com.bb.neighbor.listener.SessionContext;
import com.bb.neighbor.message.home.ReservationHome;
import com.bb.neighbor.message.model.Reservation;
import com.bb.neighbor.purpose.home.PurposeHome;
import com.bb.neighbor.purpose.model.Purpose;
import com.bb.neighbor.purpose.utils.PurposeUtils;
import com.bb.neighbor.task.home.TaskImageHome;
import com.bb.neighbor.task.model.TaskImage;
import com.bb.neighbor.user.home.UserHome;
import com.bb.neighbor.user.model.Habit;
import com.bb.neighbor.user.model.UserInfo;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;

@Path("purpose")
public class PurposeController {
	@Autowired
	private InvocationLocal inv;
	@Autowired
	private PurposeHome purposeHome;
	@Autowired
	private HouseInfoHome houseInfoHome;
	@Autowired
	private TaskImageHome taskImageHome;
	@Autowired
	private PurposeUtils purposeUtils;
	@Autowired
	private CommunityInfoHome communityInfoHome;
	@Autowired
	private AreaHome areaHome;
	@Autowired
	private UserHome userHome;
	@Autowired
	private ReservationHome reservationHome;
	@Autowired
	private HouseAttentionHome houseAttentionHome;

	/************************************ 找房 ******************************************/
	/**
	 * 转到找房意向首页
	 * 
	 * @return
	 */
	@Get("/house/where")
	public String houseWhere() {
		return "purpose-house-where";
	}

	/**
	 * 找房意向：获取输入的房屋地点，跳转到房子要求页
	 * 
	 * @param purpose
	 * @return
	 */
	@Post("/house/demand")
	public String houseDemand(Purpose purpose) {
		inv.getRequest().getSession().setAttribute("purpose", purpose);
		return "purpose-house-demand";
	}

	/**
	 * 找房意向：获取租金信息，跳转到房屋条件选择页
	 * 
	 * @return
	 */
	@Post("/house/personal")
	public String housePersonal() {
		String minRent = inv.getRequest().getParameter("minRent");
		String maxRent = inv.getRequest().getParameter("maxRent");
		String roomNum = inv.getRequest().getParameter("roomNum");
		String checkInTime = inv.getRequest().getParameter("checkInTime");

		Purpose pp = (Purpose) inv.getRequest().getSession().getAttribute("purpose");
		pp.setMinRent(Float.parseFloat(minRent));
		pp.setMaxRent(Float.parseFloat(maxRent));
		pp.setRentInfo(minRent + "-" + maxRent);
		pp.setRoomNum(roomNum);
		pp.setCheckInTime(DateFormat.stringToLong(checkInTime, Constants.PARAM_DATE));
		pp.setHouseType(inv.getRequest().getParameter("roomNum") + "室" + inv.getRequest().getParameter("hallNum") + "厅" + inv.getRequest().getParameter("looNum") + "卫");
		inv.getRequest().getSession().setAttribute("purpose", pp);
		return "purpose-house-personal";
	}

	/**
	 * 找房意向：保存意向
	 * 
	 * @return
	 */
	@Post("/house/save")
	public String houseSave() {
		Integer professionMe = Integer.parseInt(inv.getRequest().getParameter("professionMe"));
		Integer isSmokingMe = inv.getRequest().getParameter("isSmokingMe") != null ? StatusConstants.SMOKING : StatusConstants.NO_SMOKING;
		Integer isKeepingPetsMe = inv.getRequest().getParameter("isKeepingPetsMe") != null ? StatusConstants.KEEPING_PETS : StatusConstants.NO_KEEPING_PETS;

		Purpose pp = (Purpose) inv.getRequest().getSession().getAttribute("purpose");
		pp.setProfessionMe(professionMe);
		pp.setIsSmokingMe(isSmokingMe);
		pp.setIsKeepingPetsMe(isKeepingPetsMe);
		Long time = DateFormat.stringToLong(DateFormat.dateToStr(new Date(), Constants.PARAM_DATE_TIME), Constants.PARAM_DATE_TIME);
		pp.setCreateTime(time);
		pp.setUpdateTime(time);
		pp.setPurposeType(1);
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		pp.setUserInfoId(userInfo.getId());
		pp.setSex(Integer.parseInt(String.valueOf(userInfo.getSex())));
		purposeHome.save(pp);

		// 保存习惯、职业到用户表
		UpdateOperations<UserInfo> ops = userHome.getUpdateOps();
		ops.set("career", inv.getRequest().getParameter("professionMe"));
		Habit habit = new Habit();
		habit.setHavePet(isKeepingPetsMe);
		habit.setSmoke(isSmokingMe);
		ops.set("habit", habit);
		Query<UserInfo> query = userHome.getQuery();
		query.filter("id", userInfo.getId());
		userHome.update(query, ops);

		userHome.replaceSessionUser(inv);
		inv.getRequest().getSession().setAttribute("purpose", pp);
		return "r:/purpose/house/search";
	}

	/**
	 * 找房意向：修改意向
	 * 
	 * @return
	 */
	@Post("/house/modify")
	public String houseModify(Purpose purpose) {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		// 查出原来的意向,如果没有意向,则新建意向
		Purpose oldPurpose = purposeHome.getLastUpdateHousePurpose(userInfo.getId());
		String checkInTime = inv.getParameter("checkInTime");
		String houseType = purpose.getHouseType1() + "室" + purpose.getHouseType2() + "厅" + purpose.getHouseType3() + "卫";
		if (oldPurpose != null) {
			Query<Purpose> query = purposeHome.getQuery().filter("id", oldPurpose.getId());
			UpdateOperations<Purpose> ops = purposeHome.getOps();
			if (StringUtils.isNotEmpty(purpose.getAreaCode()) && !purpose.getAreaCode().equals(oldPurpose.getAreaCode())) {
				ops.set("areaCode", purpose.getAreaCode());
				// 修改区域名称
				if (!oldPurpose.getHouseWhereName().equals(purpose.getHouseWhereName())) {
					ops.set("houseWhereName", purpose.getHouseWhereName());
				}
			}
			if (!houseType.equals(oldPurpose.getHouseType())) {
				ops.set("houseType", houseType);
			}
			if (!purpose.getMinRent().equals(oldPurpose.getMinRent())) {
				ops.set("minRent", purpose.getMinRent());
			}
			if (!purpose.getMaxRent().equals(oldPurpose.getMaxRent())) {
				ops.set("maxRent", purpose.getMaxRent());
			}
			if (checkInTime != oldPurpose.getCheckInTime()) {
				ops.set("checkInTime", DateFormat.stringToLong(checkInTime, "yyyy-MM-dd"));
			}
			ops.set("updateTime", System.currentTimeMillis());
			purposeHome.update(query, ops);
		} else {
			purpose.setUserInfoId(userInfo.getId());
			purpose.setHouseType(houseType);
			purpose.setCheckInTime(DateFormat.stringToLong(checkInTime, "yyyy-MM-dd"));
			purpose.setPurposeType(Constants.PURPOSE_TYPE_HOUSE);
			purposeHome.save(purpose);
		}
		return "@" + Constants.RETURN_SUCCESS;
	}

	/**
	 * 找房意向：根据意向，获取房屋信息
	 * 
	 * @return
	 */
	@Get("/house/search")
	public String houseSearch() {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		Purpose purpose = purposeHome.getUserPurpose(userInfo.getId(), Constants.PURPOSE_TYPE_HOUSE);
		if (null == purpose) {
			return "r:/purpose/house/where";
		}

		String sortCol = inv.getParameter("sortCol") == null ? "time" : inv.getParameter("sortCol");
		int pageIndex = inv.getParameter("page") != null ? Integer.parseInt(inv.getParameter("page")) : 1;
		int total = purposeHome.countGetHouseInfoList(purpose, sortCol);
		List<HouseInfo> houseList = purposeHome.getHouseInfoList(purpose, pageIndex, Constants.PAGE_SIZE_PURPOSE_HOUSE, sortCol);
		Page<HouseInfo> page = new Page<HouseInfo>(total, Constants.PAGE_SIZE_PURPOSE_HOUSE, pageIndex, houseList);

		// 获取也想租此房的用户
		if (null != houseList && houseList.size() > 0) {
			for (HouseInfo hi : houseList) {
				List<UserInfo> wantRentUserList = new ArrayList<UserInfo>();
				// 获取4个不包含自己发送的预约人
				List<Reservation> reservationList = reservationHome.getReservationListByHouseId(hi.getId(), userInfo.getId());
				wantRentUserList = reservationHome.getUserListByReservationList(reservationList);
				// 不足4个，再获取剩余的不包含自己的关注人
				if (wantRentUserList.size() < 4) {
					int num = 4 - wantRentUserList.size();
					List<UserInfo> attentionUserList = houseAttentionHome.getAttentionUserList(hi.getId(), num, userInfo.getId());
					if (null != attentionUserList && attentionUserList.size() > 0) {
						for (UserInfo ui : attentionUserList) {
							wantRentUserList.add(ui);
						}
					}
				}
				hi.setWantRentUserList(wantRentUserList);
			}
		}

		List<Purpose> purposeList = purposeHome.getPurposeAboutUserList(purpose.getAreaCode(), userInfo.getId());
		List<UserInfo> aboutUserList = purposeHome.getUserInfoListByPurposeList(purposeList);

		Area area = areaHome.getAreaByCode(purpose.getAreaCode());
		List<Area> areaList = null;
		if (null != area.getLocation())
			areaList = areaHome.findNearBy(area.getLocation(), 0.01f, 0.005f);

		inv.addModel("aboutUserList", aboutUserList);
		inv.addModel("purpose", purpose);
		inv.addModel("houseList", houseList);
		inv.addModel("page", page);
		inv.addModel("sortCol", sortCol);
		inv.addModel("areaList", areaList);
		return "find-house-home";
	}

	/**
	 * 找房意向：房屋地点ajax查询
	 * 
	 * @return
	 */
	@Get("/house/where/search")
	public String houseWhereSearch() {
		String result = purposeUtils.houseWhere(inv);
		return "@" + result;
	}

	/**
	 * 修改找房意向
	 * 
	 * @param purpose
	 * @return
	 */
	@Post("/house/modify2")
	public String houseModify2(Purpose purpose) {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		// 查出原来的意向,如果原来的意向不存在则保存新的意向
		Purpose oldPurpose = purposeHome.getLastUpdateHousePurpose(userInfo.getId());
		if (oldPurpose != null) {
			Query<Purpose> query = purposeHome.getQuery().filter("id", oldPurpose.getId());
			UpdateOperations<Purpose> ops = purposeHome.getOps();
			if (purpose.getSex() != oldPurpose.getSex()) {
				ops.set("sex", purpose.getSex());
			}
			if (purpose.getProfessionPartner() != oldPurpose.getProfessionPartner()) {
				ops.set("professionPartner", purpose.getProfessionPartner() == null ? 0 : purpose.getProfessionPartner());
			}
			if (purpose.getIsSmokingPartner() != oldPurpose.getIsSmokingPartner()) {
				ops.set("isSmokingPartner", purpose.getIsSmokingPartner() == null ? 0 : purpose.getIsSmokingPartner());
			}
			if (purpose.getIsKeepingPetsPartner() != oldPurpose.getIsKeepingPetsPartner()) {
				ops.set("isKeepingPetsPartner", purpose.getIsKeepingPetsPartner() == null ? 0 : purpose.getIsKeepingPetsPartner());
			}
			ops.set("updateTime", System.currentTimeMillis());
			purposeHome.update(query, ops);
		} else {
			purpose.setUserInfoId(userInfo.getId());
			purpose.setPurposeType(Constants.PURPOSE_TYPE_HOUSE);
			purposeHome.save(purpose);
		}
		return "r:/usercenter/rentout/personPurpose";
	}

	/************************************ 找人 ******************************************/
	/**
	 * 找人意向：转到录入房源信息页
	 * 
	 * @return
	 */
	@Get("/user/where")
	public String userWhere() {
		HouseInfo hi = new HouseInfo();
		List<HouseImg> houseImgList = new ArrayList<HouseImg>();
		inv.getRequest().getSession().setAttribute("houseInfo", hi);
		inv.getRequest().getSession().setAttribute("houseImgList", houseImgList);
		return "purpose-user-where";
	}

	/**
	 * 找人意向：获取房源信息，转到个人信息页
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Post("/user/aboutme")
	public String userAboutme() {
		HouseInfo hi = (HouseInfo) inv.getRequest().getSession().getAttribute("houseInfo");
		hi.setCityName(inv.getParameter("cityName"));
		hi.setHouseType(inv.getParameter("roomNum") + "室" + inv.getParameter("hallNum") + "厅" + inv.getParameter("looNum") + "卫");
		hi.setRoomType(inv.getParameter("roomType"));
		String floorNum = inv.getParameter("floorNum");
		if (StringUtils.isNotBlank(floorNum)) {
			hi.setFloorNum(Integer.valueOf(floorNum));
		}
		String floorTotalNum = inv.getParameter("floorTotalNum");
		if (StringUtils.isNotBlank(floorTotalNum)) {
			hi.setFloorTotalNum(Integer.valueOf(floorTotalNum));
		}
		String rent = inv.getParameter("rent");
		if (StringUtils.isNotBlank(rent)) {
			hi.setRent(Float.valueOf(inv.getParameter("rent")));
		}
		hi.setCheckInTime(DateFormat.stringToLong(inv.getParameter("checkInTime"), Constants.PARAM_DATE));
		hi.setDiscription(inv.getParameter("discription"));
		hi.setContactNum(inv.getParameter("contactNum"));

		CommunityInfo communityInfo = communityInfoHome.getCommunityInfoByName(inv.getParameter("houseWhereName"));
		/*
		 * if(StringUtils.isBlank(inv.getParameter("areaCode"))){ communityInfo = communityInfoHome.getCommunityInfoByName(inv.getParameter("houseWhereName")); }else{ communityInfo =
		 * communityInfoHome.getCommunityInfoByAreaCode(inv.getParameter("areaCode")); }
		 */
		hi.setCommunityName(communityInfo.getName());
		hi.setArea(communityInfo.getArea());
		hi.setHouseTitle("出租" + communityInfo.getName() + inv.getParameter("roomType"));

		hi.setUpdateTime(DateFormat.dateToLong(new Date(), Constants.PARAM_DATE_TIME));
		hi.setHireType(Constants.HIRE_TYPE_ROOMMATE);
		hi.setId(SequenceUtil.getId(SequenceUtil.HOUSE_ID));
		String rentArea = inv.getParameter("rentArea");
		if (StringUtils.isNotBlank(rentArea)) {
			hi.setRentArea(Float.valueOf(rentArea));
		}
		hi.setTowards(inv.getParameter("towards"));
		List<HouseImg> houseImgList = (List<HouseImg>) inv.getRequest().getSession().getAttribute("houseImgList");
		hi.setHouseImgList(houseImgList);
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		hi.setPublisherId(userInfo.getId());
		hi.setInfoFrom(Constants.HOUSE_INFO_FROM_BANGNIZU);
		hi.setRentStatus(StatusConstants.HOUSE_RENT_STATUS_NOT);
		hi.setReservationSms(StatusConstants.HOUSE_RESERVATION_SMS_YES);

		Purpose p = new Purpose();
		String houseWhereId = inv.getParameter("houseWhereId");
		if (StringUtils.isNotBlank(houseWhereId)) {
			p.setHouseWhereId(Integer.valueOf(inv.getParameter("houseWhereId")));
		}
		String whereType = inv.getParameter("whereType");
		if (StringUtils.isNotBlank(whereType)) {
			p.setWhereType(Integer.valueOf(inv.getParameter("whereType")));
		}
		p.setAreaCode(inv.getParameter("areaCode"));
		p.setHouseWhereName(inv.getParameter("houseWhereName"));
		p.setRoomNum(inv.getParameter("roomNum"));
		p.setRentInfo(inv.getParameter("rent"));

		inv.getRequest().getSession().setAttribute("purpose", p);
		inv.getRequest().getSession().setAttribute("houseInfo", hi);
		return "purpose-user-aboutme";
	}

	/**
	 * 修改找人意向
	 * 
	 * @param purpose
	 * @return
	 */
	@Post("/user/modify")
	public String userModify(Purpose purpose) {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		// 查出原来的意向,如果原来的意向不存在则保存新的意向
		Purpose oldPurpose = purposeHome.getLastUpdatePersonPurpose(userInfo.getId());
		if (oldPurpose != null) {
			Query<Purpose> query = purposeHome.getQuery().filter("id", oldPurpose.getId());
			UpdateOperations<Purpose> ops = purposeHome.getOps();
			if (purpose.getSex() != oldPurpose.getSex()) {
				ops.set("sex", purpose.getSex());
			}
			if (purpose.getProfessionPartner() != oldPurpose.getProfessionPartner()) {
				ops.set("professionPartner", purpose.getProfessionPartner());
			}
			if (purpose.getIsSmokingPartner() != oldPurpose.getIsSmokingPartner()) {
				ops.set("isSmokingPartner", purpose.getIsSmokingPartner() == null ? 0 : purpose.getIsSmokingPartner());
			}
			if (purpose.getIsKeepingPetsPartner() != oldPurpose.getIsKeepingPetsPartner()) {
				ops.set("isKeepingPetsPartner", purpose.getIsKeepingPetsPartner() == null ? 0 : purpose.getIsKeepingPetsPartner());
			}
			ops.set("updateTime", System.currentTimeMillis());
			purposeHome.update(query, ops);
		} else {
			purpose.setUserInfoId(userInfo.getId());
			purpose.setPurposeType(Constants.PURPOSE_TYPE_USER);
			purposeHome.save(purpose);
		}
		return "@" + Constants.RETURN_SUCCESS;
	}

	/**
	 * 上传房间图片
	 * 
	 * @param files
	 * @return
	 */
	@Post("/user/houseimg/upload")
	public String userHouseImgUpload(MultipartFile file) {
		HouseImg houseImg = new HouseImg();
		String fileName = file.getOriginalFilename();
		String name = fileName.substring(0, fileName.lastIndexOf("."));
		String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());

		Integer taskImageId = SequenceUtil.getId(SequenceUtil.TASK_IMAGE_ID);
		String saveFilePath = CrawlerConstants.IMG_PATH + CrawlerConstants.IMG_TEMP_PATH + "/house/" + taskImageId + "/";
		String saveDBPath = CrawlerConstants.IMG_TEMP_PATH + "/house/" + taskImageId + "/";

		HttpSession session = SessionContext.getInstance().getSession(inv.getParameter("jsessionid"));
		// 保存任务
		TaskImage taskImage = new TaskImage();
		taskImage.setId(taskImageId);
		HouseInfo hi = (HouseInfo) session.getAttribute("houseInfo");
		taskImage.setCollectionsId(hi.getId());
		taskImage.setStatus(StatusConstants.TASK_IMAGE_STATUS_UNPLAYED);
		taskImage.setCreateTime(DateFormat.dateToLong(new Date(), Constants.PARAM_DATE_TIME));
		taskImage.setType(StatusConstants.TASK_IMAGE_TYPE_HOUSE);
		session.setAttribute("taskImage", taskImage);

		@SuppressWarnings("unchecked")
		List<HouseImg> houseImgList = (List<HouseImg>) session.getAttribute("houseImgList");
		try {
			UploadUtil.saveFile(file, saveFilePath, fileName);
			houseImg.setFileName(name);
			houseImg.setSuffix(suffix);
			houseImg.setPath(saveDBPath);
			houseImgList.add(houseImg);
			session.setAttribute("houseImgList", houseImgList);

			UploadUtil.processHead(saveFilePath, name, suffix, ImageSize.HOUSE_IMAGE_SMALL_WIDTH, ImageSize.HOUSE_IMAGE_SMALL_HEIGHT);
			UploadUtil.processHead(saveFilePath, name, suffix, ImageSize.HOUSE_IMAGE_MEDIUM_WIDTH, ImageSize.HOUSE_IMAGE_MEDIUM_HEIGHT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "@" + com.bb.neighbor.listener.Constants.URL_IMG + saveDBPath + name + ImageSize.HOUSE_IMAGE_SMALL_WIDTH + ImageSize.CONNECTOR + ImageSize.HOUSE_IMAGE_SMALL_HEIGHT + suffix;
	}

	/**
	 * 找人意向：获取个人信息，转到合租伙伴信息页
	 * 
	 * @return
	 */
	@Post("/user/aboutpartner")
	public String userAboutPartner() {
		Integer professionMe = Integer.parseInt(inv.getRequest().getParameter("professionMe"));
		Integer isSmokingMe = inv.getRequest().getParameter("isSmokingMe") != null ? StatusConstants.SMOKING : StatusConstants.NO_SMOKING;
		Integer isKeepingPetsMe = inv.getRequest().getParameter("isKeepingPetsMe") != null ? StatusConstants.KEEPING_PETS : StatusConstants.NO_KEEPING_PETS;

		Purpose pp = (Purpose) inv.getRequest().getSession().getAttribute("purpose");
		pp.setProfessionMe(professionMe);
		pp.setIsSmokingMe(isSmokingMe);
		pp.setIsKeepingPetsMe(isKeepingPetsMe);
		inv.getRequest().getSession().setAttribute("purpose", pp);
		return "purpose-user-aboutpartner";
	}

	/**
	 * 找人意向：获取合租伙伴信息，保存意向，转到找人主页
	 * 
	 * @return
	 */
	@Post("/user/save")
	public String userSave() {
		Integer professionPartner = Integer.parseInt(inv.getRequest().getParameter("professionPartner"));
		Integer sex = Integer.parseInt(inv.getRequest().getParameter("sex"));
		Integer isSmokingPartner = inv.getRequest().getParameter("isSmokingPartner") != null ? StatusConstants.SMOKING : StatusConstants.NO_SMOKING;
		Integer isKeepingPetsPartner = inv.getRequest().getParameter("isKeepingPetsPartner") != null ? StatusConstants.KEEPING_PETS : StatusConstants.NO_KEEPING_PETS;

		Purpose pp = (Purpose) inv.getRequest().getSession().getAttribute("purpose");
		pp.setProfessionPartner(professionPartner);
		pp.setSex(sex);
		pp.setIsSmokingPartner(isSmokingPartner);
		pp.setIsKeepingPetsPartner(isKeepingPetsPartner);
		Long time = DateFormat.stringToLong(DateFormat.dateToStr(new Date(), Constants.PARAM_DATE_TIME), Constants.PARAM_DATE_TIME);
		pp.setCreateTime(time);
		pp.setUpdateTime(time);
		pp.setPurposeType(2);
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		pp.setUserInfoId(userInfo.getId());

		HouseInfo hi = (HouseInfo) inv.getRequest().getSession().getAttribute("houseInfo");
		TaskImage taskImage = (TaskImage) inv.getRequest().getSession().getAttribute("taskImage");
		pp.setHouseId(hi.getId());

		// 保存习惯、职业到用户表
		UpdateOperations<UserInfo> ops = userHome.getUpdateOps();
		ops.set("career", String.valueOf(pp.getProfessionMe()));
		Habit habit = new Habit();
		habit.setHavePet(pp.getIsKeepingPetsMe());
		habit.setSmoke(pp.getIsSmokingMe());
		ops.set("habit", habit);
		ops.set("mobile", hi.getContactNum());
		Query<UserInfo> query = userHome.getQuery();
		query.filter("id", userInfo.getId());
		userHome.update(query, ops);

		houseInfoHome.insert(hi);
		purposeHome.save(pp);
		taskImageHome.save(taskImage);

		userHome.replaceSessionUser(inv);
		inv.getRequest().getSession().setAttribute("purpose", pp);
		return "r:/purpose/user/search";
	}

	/**
	 * 找人意向：根据意向，获取用户列表
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Get("/user/search")
	public String userSearch() {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		Purpose userPurpose = purposeHome.getUserPurpose(userInfo.getId(), Constants.PURPOSE_TYPE_USER);
		// Purpose housePurpose = purposeHome.getUserPurpose(userInfo.getId(), Constants.PURPOSE_TYPE_HOUSE);
		if (null == userPurpose) {
			return "r:/purpose/user/where";
		}

		int pageIndex = inv.getParameter("page") != null ? Integer.parseInt(inv.getParameter("page")) : 1;
		int total = purposeHome.countGetPurposeListByPurpose(userPurpose);
		List<Purpose> purposeList = purposeHome.getPurposeListByPurpose(userPurpose, pageIndex, Constants.PAGE_SIZE_PURPOSE_USER);
		Page page = new Page(total, Constants.PAGE_SIZE_PURPOSE_USER, pageIndex, purposeList);
		List<UserInfo> userList = purposeHome.getUserInfoListByPurposeList(purposeList);

		inv.addModel("userPurpose", userPurpose);
		// inv.addModel("housePurpose", housePurpose);
		inv.addModel("page", page);
		inv.addModel("userList", userList);
		inv.addModel("purposeList", purposeList);
		return "find-user-home";
	}

	/**
	 * 找人意向：房屋地点ajax查询
	 * 
	 * @return
	 */
	@Get("/user/where/search")
	public String userWhereSearch() {
		String result = purposeUtils.userWhere(inv);
		return "@" + result;
	}

	/**
	 * 验证输入的小区名是否存在
	 * 
	 * @param houseWhereName
	 * @return
	 */
	@Post("/verify/whereName")
	public String verifyHouseWhereName(@Param("houseWhereName") String houseWhereName) {
		CommunityInfo communityInfo = communityInfoHome.getCommunityInfoByName(houseWhereName);
		if (null == communityInfo)
			return "@0";
		return "@1";
	}
}
