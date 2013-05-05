package com.bb.neighbor.house.controllers;

import java.util.Date;

import net.paoding.rose.web.InvocationLocal;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.springframework.beans.factory.annotation.Autowired;

import com.bb.mongo.utils.DateFormat;
import com.bb.neighbor.common.Constants;
import com.bb.neighbor.common.StatusConstants;
import com.bb.neighbor.house.home.HouseAttentionHome;
import com.bb.neighbor.house.model.HouseAttention;
import com.bb.neighbor.user.model.UserInfo;

@Path("/houseAttention")
public class HouseAttentionController {
	@Autowired
	private InvocationLocal inv;
	@Autowired
	private HouseAttentionHome houseAttentionHome;

	/**
	 * 保存房屋关注
	 * 
	 * @param houseId
	 * @return
	 */
	@Get("/save/{houseId}")
	public String houseAttentionSave(@Param("houseId") Integer houseId) {
		HouseAttention ha = new HouseAttention();
		ha.setHouseId(houseId);
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		ha.setUserId(userInfo.getId());
		ha.setCreateTime(DateFormat.dateToLong(new Date(), Constants.PARAM_DATE_TIME));
		ha.setReservationStatus(StatusConstants.ATTENTION_RESERVATION_NOT);
		houseAttentionHome.save(ha);
		return "r:/house/info/" + houseId;
	}

	/**
	 * 批量删除房屋关注
	 * 
	 * @param idArray
	 * @param curPage
	 * @return
	 */
	@Get("/delete/{ids}/{curPage}")
	public String batchDelete(@Param("ids") Integer[] idArray, @Param("curPage") int curPage) {
		houseAttentionHome.deleteByIds(idArray);
		return "r:/usercenter/attentionHouse?page=" + curPage;
	}
	
	/**
	 * 批量加入我的关注
	 * @param ids
	 * @return
	 */
	@Post("/batch/save")
	public String batchAttentionSave(@Param("houseIds")Integer[] houseIds){
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		Long createTime = DateFormat.dateToLong(new Date(), Constants.PARAM_DATE_TIME);
		for(Integer houseId : houseIds){
			boolean isAttention = houseAttentionHome.isAttention(houseId, userInfo.getId());
			if(!isAttention){
				HouseAttention ha = new HouseAttention();
				ha.setHouseId(houseId);
				ha.setUserId(userInfo.getId());
				ha.setCreateTime(createTime);
				ha.setReservationStatus(StatusConstants.ATTENTION_RESERVATION_NOT);
				houseAttentionHome.save(ha);
			}
		}
		return "@加入成功！";
	}
}
