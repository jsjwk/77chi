package com.bb.neighbor.house.controllers;

import java.util.Date;

import net.paoding.rose.web.InvocationLocal;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Post;

import org.springframework.beans.factory.annotation.Autowired;

import com.bb.mongo.utils.DateFormat;
import com.bb.neighbor.common.Constants;
import com.bb.neighbor.house.home.HouseCommentHome;
import com.bb.neighbor.house.model.HouseComment;
import com.bb.neighbor.user.model.UserInfo;

/**
 * 评论控制器
 * @author song.zhang@boco.jp
 *
 */
@Path("/houseComment")
public class HouseCommentController {
	@Autowired
	private InvocationLocal inv;
	@Autowired
	private HouseCommentHome houseCommentHome;
	
	/**
	 * 保存评论
	 * @param houseComment
	 * @return
	 */
	@Post("/save")
	public String houseCommentSave(HouseComment houseComment){
		UserInfo userInfo = (UserInfo)inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		houseComment.setCommenter(userInfo.getId());
		houseComment.setCreateTime(DateFormat.dateToLong(new Date(), Constants.PARAM_DATE_TIME));
		houseCommentHome.save(houseComment);
		return "r:/house/info/" + houseComment.getHouseId();
	}
}
