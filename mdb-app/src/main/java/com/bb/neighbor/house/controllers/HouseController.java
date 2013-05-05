package com.bb.neighbor.house.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.paoding.rose.web.InvocationLocal;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.springframework.beans.factory.annotation.Autowired;

import com.bb.neighbor.common.Constants;
import com.bb.neighbor.common.ImageSize;
import com.bb.neighbor.common.ImgConstants;
import com.bb.neighbor.common.Page;
import com.bb.neighbor.community.home.CommunityInfoHome;
import com.bb.neighbor.community.model.CommunityInfo;
import com.bb.neighbor.house.home.AppreciationHome;
import com.bb.neighbor.house.home.HouseAttentionHome;
import com.bb.neighbor.house.home.HouseCommentHome;
import com.bb.neighbor.house.home.HouseInfoHome;
import com.bb.neighbor.house.model.AppraiseInfo;
import com.bb.neighbor.house.model.Appreciation;
import com.bb.neighbor.house.model.HouseAttention;
import com.bb.neighbor.house.model.HouseComment;
import com.bb.neighbor.house.model.HouseImg;
import com.bb.neighbor.house.model.HouseInfo;
import com.bb.neighbor.message.home.ReservationHome;
import com.bb.neighbor.user.home.UserHome;
import com.bb.neighbor.user.model.UserInfo;
import com.google.gson.Gson;

@Path("house")
public class HouseController {
	@Autowired
	private InvocationLocal inv;
	@Autowired
	private UserHome userHome;
	@Autowired
	private HouseInfoHome houseHome;
	@Autowired
	private HouseAttentionHome houseAttentionHome;
	@Autowired
	private HouseCommentHome houseCommentHome;
	@Autowired
	private CommunityInfoHome communityInfoHome;
	@Autowired
	private AppreciationHome appreciationHome;
	@Autowired
	private ReservationHome reservationHome;
	/**
	 * 每页显示数据条数
	 */
	private static final int PAGE_SIZE = 1;
	/**
	 * 查询条件：暂初始化金额
	 */
	private static final float rent = 4000;

	/**
	 * 根据用户意向获取房源信息列表
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Get("/search/house")
	public String searchHouse() {
		int pageIndex = inv.getParameter("page") != null ? Integer.parseInt(inv.getParameter("page")) : 1;
		int total = houseHome.getByRentTotal(rent);
		HouseInfo hi = houseHome.getByRent(rent, pageIndex);
		List<HouseInfo> houseInfoList = new ArrayList<HouseInfo>();
		houseInfoList.add(hi);
		Page page = new Page(total, PAGE_SIZE, pageIndex, houseInfoList);
		inv.addModel("page", page);
		inv.addModel("houseInfoList", houseInfoList);
		return "home";
	}

	/**
	 * 查看房屋详情
	 * 
	 * @param id
	 * @return
	 */
	@Get("/info/{id}")
	public String houseInfo(@Param("id") Integer id) {
		HouseInfo houseInfo = houseHome.getById(id);
		UserInfo userInfo = userHome.getUserInfoByUserId(houseInfo.getPublisherId());

		UserInfo user = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		// boolean isAttention = houseAttentionHome.isAttention(houseInfo.getId(), user.getId());
		long attentionCount = houseAttentionHome.countGetAttentionUserList(id, Constants.PAGE_SIZE_HOUSE_ATTENTION_MAX);
		List<UserInfo> attentionUserList = houseAttentionHome.getAttentionUserList(id, Constants.PAGE_SIZE_HOUSE_ATTENTION_MAX, user.getId());
		// 获取评论
		List<HouseComment> commentList = houseCommentHome.getCommentListByHouseId(id);
		List<UserInfo> commenterUserList = houseCommentHome.getCommenterListByCommentList(commentList);

		// 获取小区信息
		CommunityInfo communityInfo = null;
		if (null != houseInfo.getArea()) {
			communityInfo = communityInfoHome.getCommunityInfoByAreaCode(houseInfo.getArea().getCode());
		}

		/**
		 * 预约状态 获取 1.房源未关注：显示直接预约 2.房源已关注、已预约：显示预约状态
		 */
		HouseAttention houseAttention = houseAttentionHome.getHouseAttention(houseInfo.getId(), user.getId());
		/**
		 * 房评
		 */
		AppraiseInfo appraiseInfo = appreciationHome.getAppraiseInfo(houseInfo.getId());
		inv.addModel("attentionUserList", attentionUserList);
		inv.addModel("attentionCount", attentionCount);
		inv.addModel("appraiseInfo", appraiseInfo);
		inv.addModel("houseAttention", houseAttention);
		inv.addModel("userInfo", userInfo);
		inv.addModel("houseInfo", houseInfo);
		inv.addModel("commentList", commentList);
		inv.addModel("commenterUserList", commenterUserList);
		inv.addModel("communityInfo", communityInfo);
		return "house-info";
	}

	/**
	 * 房评
	 * 
	 * @param houseId
	 * @param status
	 * @return
	 */
	@Post("/appreciation/{houseId}/{status}")
	public String appreciation(@Param("houseId") Integer houseId, @Param("status") Integer status) {
		UserInfo user = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		Appreciation appreciation = new Appreciation();
		appreciation.setHouseId(houseId);
		appreciation.setCommenterId(user.getId());
		appreciation.setStatus(status);
		// 更新房子评价状态
		houseHome.updateIsAppraise(houseId);
		//更新统计数据
		updateCount();
		return "@" + appreciationHome.save(appreciation);
	}

	/**
	 * 得到最新还未评价的房子列表
	 * 
	 * @return
	 */
	@Post("/acceptReservationList")
	public String getNewReservation() {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		List<Integer> houseIds = reservationHome.getAcceptHouseIdList(userInfo.getId());
		List<HouseInfo> houseList = houseHome.getUnappraiseHouseList(houseIds);
		// 避免头像为空
		for (HouseInfo house : houseList) {
			if (house.getHouseImgList() != null) {
				HouseImg houseImg = house.getHouseImgList().get(0);
				house.setImgSrc(houseImg.getPath() + houseImg.getFileName() + ImageSize.CONNECTOR + houseImg.getSuffix());
			} else {
				house.setImgSrc(ImgConstants.HOUSE_DEFAULT_SMALL_HEADIMG);
			}
			if (house.getCheckInTime() == null) {
				house.setCheckInTime(System.currentTimeMillis());
			}
		}
		Gson gson = new Gson();
		return "@" + gson.toJson(houseList);
	}

	/**
	 * 更新统计数据
	 * 
	 * @return
	 */
	@Post("/updateAppraiseCount")
	public String updateAppraiseCount() {
		return "@" + updateCount();
	}

	/**
	 * 更新统计数据
	 * 
	 * @return
	 */
	public int updateCount() {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		int appraiseMessage = houseHome.countUnappraiseHouse(userInfo.getId());
		HttpSession session = inv.getRequest().getSession();
		session.setAttribute("appraiseMessage", appraiseMessage);
		return appraiseMessage;
	}
}
