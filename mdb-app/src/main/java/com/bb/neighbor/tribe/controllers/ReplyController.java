package com.bb.neighbor.tribe.controllers;

import net.paoding.rose.web.InvocationLocal;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Post;

import org.springframework.beans.factory.annotation.Autowired;

import com.bb.neighbor.common.Constants;
import com.bb.neighbor.tribe.home.TribeReplyHome;
import com.bb.neighbor.tribe.model.Reply;
import com.bb.neighbor.user.model.UserInfo;

/**
 * 
 * @author ly.jiao@live.cn
 * 
 */
@Path("/tribe/reply")
public class ReplyController {
	@Autowired
	private InvocationLocal inv;
	@Autowired
	private TribeReplyHome replyHome;

	/**
	 * 回复话题
	 * 
	 * @param tribeTopic
	 * @return
	 */
	@Post("/topic")
	public String reply() {
		Reply reply = new Reply();
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		String topicId = inv.getParameter("topicId");
		String content = inv.getParameter("replyContent");
		reply.setTopicID(Integer.parseInt(topicId));
		reply.setContent(content);
		if (userInfo != null) {
			reply.setReleaserId(userInfo.getId());
		}
		replyHome.save(reply);
		return "@<script>alert(\"发布成功！\");location.href=\"/tribe/topic/topicDetial/" + reply.getTopicID() + "\";</script>";
	}
}
