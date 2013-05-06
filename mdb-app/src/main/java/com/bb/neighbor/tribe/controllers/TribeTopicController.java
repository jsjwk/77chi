package com.bb.neighbor.tribe.controllers;

import java.util.Date;
import java.util.List;

import net.paoding.rose.web.InvocationLocal;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.springframework.beans.factory.annotation.Autowired;

import com.bb.mongo.utils.DateFormat;
import com.bb.neighbor.common.Constants;
import com.bb.neighbor.common.StatusConstants;
import com.bb.neighbor.tribe.home.TribeHome;
import com.bb.neighbor.tribe.home.TribeReplyHome;
import com.bb.neighbor.tribe.home.TribeTopicHome;
import com.bb.neighbor.tribe.model.Reply;
import com.bb.neighbor.tribe.model.Tribe;
import com.bb.neighbor.tribe.model.TribeTopic;
import com.bb.neighbor.user.home.UserHome;
import com.bb.neighbor.user.model.UserInfo;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;

/**
 * 部落话题控制器
 * 
 * @author song.zhang@boco.jp
 * 
 */
@Path("/tribe/topic")
public class TribeTopicController {
	@Autowired
	private InvocationLocal inv;
	@Autowired
	private TribeTopicHome tribeTopicHome;
	@Autowired
	private TribeHome tribeHome;
	@Autowired
	private UserHome userHome;
	@Autowired
	private TribeReplyHome replyHome;

	/**
	 * 发布话题
	 * 
	 * @param id
	 * @return
	 */
	@Get("{tribeId}")
	public String publish(@Param("tribeId") Integer tribeId) {
		inv.addModel("tribeId", tribeId);
		return "tribe-input";
	}

	/**
	 * 发布话题
	 * 
	 * @param tribeTopic
	 * @return
	 */
	@Post("/release/save")
	public String tribeTopicReleaseSave(TribeTopic tribeTopic) {
		// 发布话题
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		Long releaseTime = DateFormat.dateToLong(new Date(), Constants.PARAM_DATE_TIME);
		tribeTopic.setReleaserId(userInfo.getId());
		tribeTopic.setReleaseTime(releaseTime);
		tribeTopic.setReplyNum(0L);
		tribeTopic.setLastReplyTime(releaseTime);
		tribeTopicHome.save(tribeTopic);

		// 更新部落话题数
		tribeHome.updateTribeTopicNum(tribeTopic.getTribeId());
		return "@<script>alert(\"发布成功！\");location.href=\"/tribe/detail/" + tribeTopic.getTribeId() + "\";</script>";
	}

	/**
	 * 话题详情页
	 * 
	 * @param id
	 * @return
	 */
	@Get("/topicDetial/{id}")
	public String topicDetial(@Param("id") Integer id) {
		TribeTopic tribeTopic = tribeTopicHome.getTribeTopicById(id);
		UserInfo userInfo = userHome.getUserInfoByUserId(tribeTopic.getReleaserId());
		tribeTopic.setReleaser(userInfo);
		Integer tribeId = tribeTopic.getTribeId();
		Tribe tribe = tribeHome.getTribeById(tribeId);
		inv.addModel("tribeTopic", tribeTopic);
		// 回复:
		if (tribe != null) {
			inv.addModel("tribeName", tribe.getName());
		}
		List<Reply> topicReplies = replyHome.getTopicReplyById(id);
		// 添加用户信息
		for (Reply reply : topicReplies) {
			UserInfo user = userHome.getUserInfoByUserId(reply.getReleaserId());
			reply.setReleaser(user);
		}
		inv.addModel("topicReplies", topicReplies);
		return "tribeTopic-detail";
	}

	/**
	 * 举报话题
	 * 
	 * @param id
	 * @return
	 */
	@Get("/accusation/{id}")
	public void accusation(@Param("id") Integer id) {
		Query<TribeTopic> query = tribeTopicHome.getQuery().filter("id", id);
		// 举报
		UpdateOperations<TribeTopic> ops = tribeTopicHome.getUpdateOps().set("accusation", StatusConstants.ACCUSATION_YES);
		tribeTopicHome.update(query, ops);		
	}

	/**
	 * 置顶(取消)话题
	 * 
	 * @param id
	 * @return
	 */
	@Post("/top/{topicId}/{flag}")
	public void top(@Param("topicId") Integer topicId, @Param("flag") Integer flag) {
		Query<TribeTopic> query = tribeTopicHome.getQuery().filter("id", topicId);
		// 举报
		// flag=1置顶,0取消置顶
		UpdateOperations<TribeTopic> ops = tribeTopicHome.getUpdateOps().set("isTop", flag);
		tribeTopicHome.update(query, ops);
	}

	/**
	 * 删除话题
	 * 
	 * @param id
	 * @return
	 */
	@Post("/delete/{topicId}")
	public void delete(@Param("topicId") Integer topicId) {
		Query<TribeTopic> query = tribeTopicHome.getQuery().filter("id", topicId);
		// 举报
		// flag=1置顶,0取消置顶
		UpdateOperations<TribeTopic> ops = tribeTopicHome.getUpdateOps().set("isDelete", StatusConstants.DELETE_YES);
		tribeTopicHome.update(query, ops);
		// 删除话题里面的回复和评论--暂时不做
	}
}
