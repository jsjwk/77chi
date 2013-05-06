package com.bb.neighbor.message.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.paoding.rose.web.InvocationLocal;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.springframework.beans.factory.annotation.Autowired;

import com.bb.neighbor.area.home.AreaHome;
import com.bb.neighbor.area.model.Area;
import com.bb.neighbor.common.Constants;
import com.bb.neighbor.common.Page;
import com.bb.neighbor.common.model.MessageInfo;
import com.bb.neighbor.house.home.HouseInfoHome;
import com.bb.neighbor.house.model.HouseInfo;
import com.bb.neighbor.message.home.InvitationHome;
import com.bb.neighbor.message.model.Invitation;
import com.bb.neighbor.message.utils.MessageUtils;
import com.bb.neighbor.operation.home.BlacklistHome;
import com.bb.neighbor.operation.model.Blacklist;
import com.bb.neighbor.purpose.home.PurposeHome;
import com.bb.neighbor.purpose.model.Purpose;
import com.bb.neighbor.user.home.UserHome;
import com.bb.neighbor.user.model.UserInfo;
import com.google.gson.Gson;

/**
 * 
 * @author ly.jiao@live.cn
 * 
 */
@Path("/invitation")
public class InvitationController {
	@Autowired
	private InvocationLocal inv;
	@Autowired
	private InvitationHome invitationHome;
	@Autowired
	private UserHome userHome;
	@Autowired
	private AreaHome areaHome;
	@Autowired
	private HouseInfoHome houseHome;
	@Autowired
	private PurposeHome purposeHome;
	@Autowired
	private BlacklistHome blacklistHome;
	@Autowired
	private MessageUtils messageUtils;

	/**
	 * 我收到的邀请--根据邀请信息来排序
	 * 
	 * @return
	 */
	@Get("/find/receiveInvitation")
	public String receiveInvitation() {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		int pageIndex = inv.getParameter("page") != null ? Integer.parseInt(inv.getParameter("page")) : 1;
		int total = invitationHome.countByReceiverId(userInfo.getId());
		List<Invitation> invitations = invitationHome.getReceiveInvitationsList(userInfo.getId(), pageIndex, 5);
		for (Invitation bean : invitations) {
			// 填充用户信息
			bean.setSender(userHome.getUserInfoByUserId(bean.getSenderId()));
			// 填充房屋信息
			bean.setHouse(houseHome.findById(bean.getHouseId()));
		}
		Page<Invitation> page = new Page<Invitation>(total, 5, pageIndex, invitations);
		inv.addModel("page", page);
		return "user-invitation-Receivelist";
	}

	/**
	 * 我收到的邀请--根据房子信息来排序
	 * 
	 * @return
	 */
	@Get("/find/receiveInvitation/house")
	public String receiveInvitation2() {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		int pageIndex = inv.getParameter("page") != null ? Integer.parseInt(inv.getParameter("page")) : 1;
		List<Invitation> list = invitationHome.getReceiveInvitationsList(userInfo.getId(), pageIndex, 5);
		if (list.size() > 0) {
			Map<Integer, Invitation> attentionMap = new HashMap<Integer, Invitation>();
			for (Invitation bean : list) {
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
			List<Invitation> invitations = new ArrayList<Invitation>();
			for (HouseInfo bean : houseList) {
				Invitation invitation = attentionMap.get(bean.getId());
				// 添加房子信息
				invitation.setHouse(bean);
				// 填充用户信息
				invitation.setSender(userHome.getUserInfoByUserId(invitation.getSenderId()));
				invitations.add(invitation);
			}
			Page<Invitation> page = new Page<Invitation>(total, 5, pageIndex, invitations);
			inv.addModel("page", page);
			inv.addModel("orderBy", orderBy);
		}
		return "user-invitation-Receivelist";
	}

	/**
	 * 我发出去的邀请
	 * 
	 * @return
	 */
	@Get("/rentout/myInvitation")
	public String sendInvitation() {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		int pageIndex = inv.getParameter("page") != null ? Integer.parseInt(inv.getParameter("page")) : 1;
		int total = invitationHome.countBySenderId(userInfo.getId());
		List<Invitation> invitations = invitationHome.getSendReservationList(userInfo.getId(), pageIndex, 5);
		for (Invitation bean : invitations) {
			UserInfo user = userHome.getUserInfoByUserId(bean.getSenderId());
			if (user != null) {
				// 填充意向
				Purpose purpose = purposeHome.getLastUpdateHousePurpose(bean.getSenderId());
				if (purpose != null) {
					Area area = areaHome.getAreaByCode(purpose.getAreaCode());
					if (area != null) {
						purpose.setAreaName(area.getName());
					}
					user.setPurpose(purpose);
				}
				// 填充用户信息
				bean.setSender(user);
			}
		}
		Page<Invitation> page = new Page<Invitation>(total, 5, pageIndex, invitations);
		inv.addModel("page", page);
		return "user-invitation-sendlist";
	}

	/**
	 * 保存邀请
	 * 
	 * @param userId
	 * @return
	 */
	@Post("/save/{userId}")
	public String saveByUserId(@Param("userId") int userId) {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		Invitation invitation = new Invitation();
		invitation.setSenderId(userInfo.getId());
		HouseInfo house = houseHome.getHouseInfoByPublisherId(userInfo.getId());
		invitation.setHouseId(house.getId());
		invitation.setRecipientId(userId);
		try {
			return "@" + invitationHome.save(invitation);
		} finally {
			String title = userInfo.getUserName() + "邀请您一起合租房子:" + house.getHouseTitle();
			String content = title;
			messageUtils.sendMessage(userInfo.getId(), userId, title, content);
		}
	}

	/**
	 * 回复(接受邀请)
	 * 
	 * @param invitation
	 */
	@Post("/reply/{id}")
	public int save(@Param("id") int id) {
		String replyMessage = inv.getParameter("replyMessage");
		return invitationHome.reply(id, replyMessage);
	}

	/**
	 * 邀请拒绝
	 * 
	 * @param id
	 * @param curPage
	 * @return
	 */
	@Post("/refuse/{id}")
	public int refuse(@Param("id") Integer id) {
		return invitationHome.refuse(id);
	}

	/**
	 * 永远拒绝
	 * 
	 * @param id
	 * @param curPage
	 * @return
	 */
	@Post("/doRefuse/{id}/{toId}/{collectionId}/{type}")
	public int doRefuse(@Param("id") Integer id, @Param("toId") int toId, @Param("collectionId") int collectionId, @Param("type") String type) {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		// 加入黑名单
		Blacklist blacklist = new Blacklist(userInfo.getId(), toId, collectionId, type);
		int result = blacklistHome.save(blacklist);
		if (result == -1) {
			// 缺少参数或者有非法参数
			return result;
		}
		if (result == -2) {
			// 重复操作
			return result;
		}
		// 拒绝
		return invitationHome.refuse(id);
	}

	/**
	 * 重新邀请
	 * 
	 * @param invitation
	 */
	@Post("/reInvite/{id}")
	public int reInvite(@Param("id") Integer id) {
		return invitationHome.reInvite(id);
	}

	/**
	 * 批量删除邀请(发送的一方)
	 * 
	 * @param idArray
	 * @param curPage
	 * @return
	 */
	@Post("/delete/{ids}")
	public int delete(@Param("ids") Integer[] idArray) {
		return invitationHome.deleteByIds(idArray);
	}

	/**
	 * 批量忽略邀请(收到的一方)
	 * 
	 * @param idArray
	 * @param curPage
	 * @return
	 */
	@Post("/ignore/{ids}")
	public int ignore(@Param("ids") Integer[] idArray) {
		return invitationHome.ignoreByIds(idArray);
	}

	/**
	 * 得到最新邀请列表(5条数据)
	 * 
	 * @return
	 */
	@Post("/invitationList")
	public String getNewReservation() {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		List<Invitation> invitationList = invitationHome.getNewMessage(userInfo.getId());
		Gson gson = new Gson();
		return "@" + gson.toJson(invitationList);
	}

	/**
	 * 查看一条邀请信息
	 * 
	 * @return
	 */
	@Post("/detail/{id}")
	public String getInvitation(@Param("id") Integer id) {
		Invitation invitation = invitationHome.getInvitationById(id);
		// 添加用户信息
		invitation.setSender(userHome.getUserInfoByUserId(invitation.getSenderId()));
		// 添加房子信息
		HouseInfo house = houseHome.findById(invitation.getHouseId());
		invitation.setHouse(house == null ? new HouseInfo() : house);
		Gson gson = new Gson();
		return "@" + gson.toJson(invitation);
	}

	/**
	 * 更新统计数据
	 * 
	 * @return
	 */
	@Post("/updateCount")
	public String updateCount() {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		MessageInfo messageInfo = invitationHome.countUnhandledInvitation(userInfo.getId());
		HttpSession session = inv.getRequest().getSession();
		session.setAttribute("invitationMessage", messageInfo);
		Gson gson = new Gson();
		return "@" + gson.toJson(messageInfo);
	}
}
