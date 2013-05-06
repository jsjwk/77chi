package com.bb.neighbor.message.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.paoding.rose.web.InvocationLocal;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.springframework.beans.factory.annotation.Autowired;

import com.bb.mongo.utils.DateFormat;
import com.bb.neighbor.common.Constants;
import com.bb.neighbor.common.Page;
import com.bb.neighbor.common.StatusConstants;
import com.bb.neighbor.common.model.MessageInfo;
import com.bb.neighbor.message.home.InboxHome;
import com.bb.neighbor.message.home.OutboxHome;
import com.bb.neighbor.message.model.Inbox;
import com.bb.neighbor.message.model.Outbox;
import com.bb.neighbor.message.utils.MessageUtils;
import com.bb.neighbor.user.home.UserHome;
import com.bb.neighbor.user.model.UserInfo;
import com.google.gson.Gson;

/**
 * 收件箱控制器
 * 
 * @author song.zhang@boco.jp
 * 
 */
@Path("/inbox")
public class InboxController {
	@Autowired
	private InvocationLocal inv;
	@Autowired
	private MessageUtils messageUtils;
	@Autowired
	private InboxHome inboxHome;
	@Autowired
	private OutboxHome outboxHome;
	@Autowired
	private UserHome userHome;

	/**
	 * 收件箱列表页面
	 * 
	 * @return
	 */
	@Get("/home")
	public String inboxHome() {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);

		int pageIndex = inv.getParameter("page") != null ? Integer.parseInt(inv.getParameter("page")) : 1;
		int total = (int) inboxHome.getInboxCount(userInfo.getId());
		List<Inbox> inboxList = inboxHome.getInboxList(userInfo.getId(), pageIndex, Constants.PAGE_SIZE_INBOX);
		Page<Inbox> page = new Page<Inbox>(total, Constants.PAGE_SIZE_INBOX, pageIndex, inboxList);

		List<UserInfo> userList = inboxHome.getUserListByInboxList(inboxList);

		inv.addModel("page", page);
		inv.addModel("inboxList", inboxList);
		inv.addModel("userList", userList);
		return "inbox-home";
	}

	/**
	 * 读取收件箱消息
	 * 
	 * @param id
	 * @param curPage
	 * @return
	 */
	@Get("/detail/{id}/{curPage}")
	public String inboxDetail(@Param("id") Integer id, @Param("curPage") int curPage) {
		inboxHome.updateInboxRead(id);
		Inbox inbox = inboxHome.getInboxById(id);
		UserInfo user = userHome.getUserInfoByUserId(inbox.getSender());
		inv.addModel("inbox", inbox);
		inv.addModel("user", user);
		inv.addModel("curPage", curPage);
		messageUtils.getMessageCount(inv);
		return "inbox-detail";
	}

	/**
	 * 批量删除选中的收件箱消息
	 * 
	 * @param idArray
	 * @param curPage
	 * @return
	 */
	@Get("/batchDelete/{ids}/{curPage}")
	public String inboxBatchDelete(@Param("ids") Integer[] idArray, @Param("curPage") int curPage) {
		for (Integer id : idArray) {
			inboxHome.deleteInboxById(id);
		}
		messageUtils.getMessageCount(inv);
		return "r:/inbox/home?page=" + curPage;
	}

	/**
	 * 删除收件箱消息
	 * 
	 * @param id
	 * @param curPage
	 * @return
	 */
	@Get("/delete/{id}/{curPage}")
	public String inboxDelete(@Param("id") Integer id, @Param("curPage") int curPage) {
		inboxHome.deleteInboxById(id);
		messageUtils.getMessageCount(inv);
		return "r:/inbox/home?page=" + curPage;
	}

	/**
	 * 转到回复页面
	 * 
	 * @param id
	 * @param curPage
	 * @return
	 */
	@Get("/reply/{id}/{curPage}")
	public String inboxReply(@Param("id") Integer id, @Param("curPage") int curPage) {
		Inbox inbox = inboxHome.getInboxById(id);
		String sendTime = DateFormat.longToStr(inbox.getSendTime(), Constants.PARAM_DATE_TIME);
		UserInfo user = userHome.getUserInfoByUserId(inbox.getSender());
		inv.addModel("inbox", inbox);
		inv.addModel("user", user);
		inv.addModel("curPage", curPage);
		inv.addModel("sendTime", sendTime);
		return "inbox-reply";
	}

	/**
	 * 回复
	 * 
	 * @param outbox
	 * @return
	 */
	@Post("/reply/save")
	public String inboxReplySave(Outbox outbox) {
		int curPage = Integer.parseInt((String) inv.getRequest().getParameter("curPage"));
		long replyTime = System.currentTimeMillis();
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);

		// 回复时保存至发件箱
		outbox.setSender(userInfo.getId());
		outbox.setSendTime(replyTime);
		outboxHome.saveOutbox(outbox);

		// 同时给接收人新增收件
		Inbox inbox = new Inbox();
		inbox.setSender(userInfo.getId());
		inbox.setSendTime(replyTime);
		inbox.setRecipient(outbox.getRecipient());
		inbox.setTitle(outbox.getTitle());
		inbox.setContent(outbox.getContent());
		inbox.setIsRead(StatusConstants.MESSAGE_UNREAD);
		inboxHome.saveInbox(inbox);

		return "@<script>alert(\"回复成功！\");location.href=\"/inbox/home/?page=" + curPage + "\";</script>";
	}

	/**
	 * 发送站内信
	 * 
	 * @param inbox
	 * @return
	 */
	@Post("/send")
	public String inboxSend(Inbox inbox) {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		long sendTime = System.currentTimeMillis();
		// 保存收件
		inbox.setSendTime(sendTime);
		inbox.setIsRead(StatusConstants.MESSAGE_UNREAD);
		inbox.setSender(userInfo.getId());
		inboxHome.saveInbox(inbox);

		// 保存发件
		Outbox outbox = new Outbox();
		outbox.setTitle(inbox.getTitle());
		outbox.setSendTime(sendTime);
		outbox.setContent(inbox.getContent());
		outbox.setSender(userInfo.getId());
		outbox.setRecipient(inbox.getRecipient());
		outboxHome.saveOutbox(outbox);
		return "@success";
	}

	@Post("/inboxList")
	public String getNewMessage() {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		List<Inbox> inboxList = inboxHome.getNewMessage(userInfo.getId());
		Gson gson = new Gson();
		return "@" + gson.toJson(inboxList);
	}

	/**
	 * 获取未读邮件
	 * 
	 * @param id
	 * @return
	 */
	@Post("/detail/{id}")
	public String inboxNewDetail(@Param("id") Integer id) {
		// 设置为已读
		inboxHome.updateInboxRead(id);
		Inbox inbox = inboxHome.getInboxById(id);
		inbox.setSenderInfo(userHome.getUserInfoByUserId(inbox.getSender()));
		// messageUtils.getMessageCount(inv);
		Gson gson = new Gson();
		return "@" + gson.toJson(inbox);
	}

	@Post("/updateInboxCount")
	public String updateInboxCount() {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		MessageInfo messageInfo = inboxHome.getNotReadInboxCount(userInfo.getId());
		HttpSession session = inv.getRequest().getSession();
		session.setAttribute("inboxMessage", messageInfo);
		Gson gson = new Gson();
		return "@" + gson.toJson(messageInfo);
	}

	/**
	 * 回复信息
	 * 
	 * @param userId
	 * @param title
	 * @param content
	 * @return
	 */
	@Post("/toReply/{userId}")
	public String reply(@Param("userId") Integer userId) {
		String title = inv.getParameter("title");
		String content = inv.getParameter("content");
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		Inbox inbox = new Inbox();
		long replyTime = DateFormat.dateToLong(new Date(), Constants.PARAM_DATE_TIME);
		inbox.setSender(userInfo.getId());
		inbox.setSendTime(replyTime);
		inbox.setRecipient(userId);
		inbox.setTitle(title);
		inbox.setContent(content);
		inbox.setIsRead(StatusConstants.MESSAGE_UNREAD);
		inboxHome.saveInbox(inbox);
		return "@";
	}
}
