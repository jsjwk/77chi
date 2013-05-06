package com.bb.neighbor.message.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import net.paoding.rose.web.InvocationLocal;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.springframework.beans.factory.annotation.Autowired;

import com.bb.neighbor.common.Constants;
import com.bb.neighbor.common.model.MessageInfo;
import com.bb.neighbor.message.home.ReservationHome;
import com.bb.neighbor.message.model.Reservation;
import com.bb.neighbor.message.utils.MessageUtils;
import com.bb.neighbor.message.utils.ReservationUtils;
import com.bb.neighbor.user.home.UserHome;
import com.bb.neighbor.user.model.UserInfo;
import com.google.gson.Gson;

/**
 * 预约请求控制器
 * 
 * @author song.zhang@boco.jp
 * 
 */
@Path("/reservation")
public class ReservationController {
	@Autowired
	private InvocationLocal inv;
	@Autowired
	private MessageUtils messageUtils;
	@Autowired
	private ReservationUtils reservationUtils;
	@Autowired
	private ReservationHome reservationHome;
	@Autowired
	private UserHome userHome;

	/**
	 * 收到的预约列表
	 * 
	 * @return
	 */
	@Get("/message/list")
	public String messageList() {
		reservationUtils.reservationHome(inv);
		return "reservation-message-home";
	}

	/**
	 * 保存预约（房屋详情）
	 * 
	 * @param reservation
	 * @return
	 */
	@Post("/save")
	public String save(Reservation reservation) {
		reservationUtils.saveReservation(reservation, inv);
		// return "r:/house/info/" + reservation.getHouseId();
		return "@";
	}

	/**
	 * 保存预约（房屋搜索）
	 * 
	 * @param reservation
	 * @return
	 */
	@Post("/save/search/house")
	public String saveBySearchHouse(Reservation reservation) {
		reservationUtils.saveReservation(reservation, inv);
		return "@预约成功！";
	}

	/**
	 * 批量删除预约
	 * 
	 * @param idArray
	 * @param curPage
	 * @return
	 */
	@Get("/batchDelete/{ids}/{curPage}")
	public String batchDelete(@Param("ids") Integer[] idArray, @Param("curPage") int curPage) {
		reservationUtils.batchDeleteReservation(idArray);
		messageUtils.getMessageCount(inv);
		return "r:/reservation/message/list?page=" + curPage;
	}

	/**
	 * 批量删除预约
	 * 
	 * @param idArray
	 * @param curPage
	 * @return
	 */
	@Post("/delete/{ids}")
	public int delete(@Param("ids") Integer[] idArray) {
		reservationUtils.batchDeleteReservation(idArray);
		messageUtils.getMessageCount(inv);
		return Constants.RETURN_SUCCESS;
	}

	/**
	 * 预约拒绝
	 * 
	 * @param id
	 * @param curPage
	 * @return
	 */
	@Get("/refuse/{id}/{curPage}")
	public String refuse(@Param("id") Integer id, @Param("curPage") int curPage) {
		reservationUtils.refuseReservation(id);
		return "@<script>alert('预约已成功拒绝！');location.href='r:/reservation/message/list?page='" + curPage + ";</script>";
	}

	/**
	 * 接受预约
	 * 
	 * @param id
	 * @param curPage
	 * @return
	 */
	@Get("/accept/{id}/{curPage}")
	public String accept(@Param("id") Integer id, @Param("curPage") int curPage) {
		reservationUtils.acceptReservation(id);
		return "@<script>alert('预约已成功接受！');location.href='/reservation/message/list?page=" + curPage + "';</script>";
	}

	/**
	 * 已出租
	 * 
	 * @param id
	 * @param curPage
	 * @return
	 */
	@Get("/rent/{id}/{curPage}")
	public String rent(@Param("id") Integer id, @Param("curPage") int curPage) {
		reservationUtils.rentReservation(id);
		return "@<script>alert('预约已成功设置为已出租！');location.href='/reservation/message/list?page=" + curPage + "';</script>";
	}

	/**
	 * 得到最新预约列表
	 * 
	 * @return
	 */
	@Post("/reservationList")
	public String getNewReservation() {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		List<Reservation> inboxList = reservationHome.getNewMessage(userInfo.getId());
		Gson gson = new Gson();
		return "@" + gson.toJson(inboxList);
	}

	/**
	 * 查看一条预约信息
	 * 
	 * @return
	 */
	@Post("/reservation/{id}")
	public String getReservation(@Param("id") Integer id) {
		Reservation reservation = reservationHome.getReservationById(id);
		reservation.setSenderInfo(userHome.getUserInfoByUserId(reservation.getSender()));
		Gson gson = new Gson();
		return "@" + gson.toJson(reservation);
	}

	/**
	 * 预约拒绝(异步操作)
	 * 
	 * @param id
	 * @param curPage
	 * @return
	 */
	@Post("/refuse/{id}")
	public String doRefuse(@Param("id") Integer id, @Param("curPage") int curPage) {
		reservationUtils.refuseReservation(id);
		return "@" + Constants.RETURN_SUCCESS;
	}

	/**
	 * 接受预约(异步操作)
	 * 
	 * @param id
	 * @param curPage
	 * @return
	 */
	@Post("/accept/{id}")
	public String doAccept(@Param("id") Integer id, @Param("curPage") int curPage) {
		reservationUtils.acceptReservation(id);
		return "@" + Constants.RETURN_SUCCESS;
	}

	/**
	 * 更新统计数据
	 * 
	 * @return
	 */
	@Post("/updateCount")
	public String updateInboxCount() {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		MessageInfo messageInfo = reservationHome.countUnhandledReservation(userInfo.getId());
		HttpSession session = inv.getRequest().getSession();
		session.setAttribute("inboxMessage", messageInfo);
		Gson gson = new Gson();
		return "@" + gson.toJson(messageInfo);
	}
}
