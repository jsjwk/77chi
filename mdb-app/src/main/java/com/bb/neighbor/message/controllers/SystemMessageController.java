package com.bb.neighbor.message.controllers;

import java.util.List;

import net.paoding.rose.web.InvocationLocal;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.springframework.beans.factory.annotation.Autowired;

import com.bb.neighbor.common.Constants;
import com.bb.neighbor.common.Page;
import com.bb.neighbor.message.home.SystemMessageHome;
import com.bb.neighbor.message.model.SystemMessage;
import com.bb.neighbor.message.utils.MessageUtils;
import com.bb.neighbor.user.model.UserInfo;
import com.google.gson.Gson;

/**
 * 系统消息控制器
 * 
 * @author song.zhang@boco.jp
 * 
 */
@Path("/system/message")
public class SystemMessageController {
	@Autowired
	private InvocationLocal inv;
	@Autowired
	private MessageUtils messageUtils;
	@Autowired
	private SystemMessageHome systemMessageHome;

	/**
	 * 系统消息列表页面
	 * 
	 * @return
	 */
	@Get("/home")
	public String systemMessageHome() {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		int pageIndex = inv.getParameter("page") != null ? Integer.parseInt(inv.getParameter("page")) : 1;
		int total = systemMessageHome.getSystemMessageCount(userInfo.getId());
		List<SystemMessage> systemMessageList = systemMessageHome.getSystemMessageList(userInfo.getId(), pageIndex, Constants.PAGE_SIZE_SYSTEM_MESSAGE);
		Page<SystemMessage> page = new Page<SystemMessage>(total, Constants.PAGE_SIZE_SYSTEM_MESSAGE, pageIndex, systemMessageList);

		inv.addModel("page", page);
		inv.addModel("systemMessageList", systemMessageList);
		return "system-message-home";
	}

	/**
	 * 读取系统消息
	 * 
	 * @param id
	 * @param curPage
	 * @return
	 */
	@Get("/detail/{id}/{curPage}")
	public String systemMessageDetail(@Param("id") Integer id, @Param("curPage") int curPage) {
		systemMessageHome.updateSystemMessageRead(id);
		SystemMessage systemMessage = systemMessageHome.getSystemMessageById(id);
		inv.addModel("systemMessage", systemMessage);
		inv.addModel("curPage", curPage);
		messageUtils.getMessageCount(inv);
		return "system-message-detail";
	}

	/**
	 * 批量删除选中的系统消息
	 * 
	 * @param idArray
	 * @param curPage
	 * @return
	 */
	@Get("/batchDelete/{ids}/{curPage}")
	public String systemMessageBatchDelete(@Param("ids") Integer[] idArray, @Param("curPage") int curPage) {
		for (Integer id : idArray) {
			systemMessageHome.deleteSystemMessageById(id);
		}
		messageUtils.getMessageCount(inv);
		return "r:/system/message/home?page=" + curPage;
	}

	/**
	 * 删除系统消息
	 * 
	 * @param id
	 * @param curPage
	 * @return
	 */
	@Post("/delete/{id}/{curPage}")
	public String systemMessageDelete(@Param("id") Integer id, @Param("curPage") int curPage) {
		systemMessageHome.deleteSystemMessageById(id);
		messageUtils.getMessageCount(inv);
		return "r:/system/message/home?page=" + curPage;
	}

	@Post("/messageList")
	public String getNewSystemMessage() {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		List<SystemMessage> messageList = systemMessageHome.getNewMessage(userInfo.getId());
		Gson gson = new Gson();
		return "@" + gson.toJson(messageList);
	}

	/**
	 * 异步读取系统消息
	 * 
	 * @param id
	 * @return
	 */
	@Post("/detail/{id}")
	public String systemMessageDetail(@Param("id") Integer id) {
		// 将消息设置为已读
		systemMessageHome.updateSystemMessageRead(id);
		SystemMessage systemMessage = systemMessageHome.getSystemMessageById(id);
		// 更新系统消息统计
		messageUtils.getMessageCountUpdate(inv);
		Gson gson = new Gson();
		// 添加系统消息统计到前台,更新前台数据
		StringBuffer result = new StringBuffer(gson.toJson(systemMessage));
		result.deleteCharAt(result.lastIndexOf("}"));
		result.append(",\"messageCount\":").append(inv.getRequest().getSession().getAttribute("systemMessageCount")).append("}");
		return "@" + result.toString();
	}
}
