package com.bb.neighbor.message.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import net.paoding.rose.web.InvocationLocal;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import com.bb.neighbor.message.utils.MessageUtils;

/**
 * 消息控制器
 * 
 * @author song.zhang@boco.jp
 * 
 */
@Path("/message")
public class MessageController {
	@Autowired
	private InvocationLocal inv;

	/**
	 * 进入系统消息首页
	 * 
	 * @return
	 */
	@Get("/home")
	public String messageHome() {
		return "r:/system/message/home";
	}

	/**
	 * 统计未读消息总数
	 * 
	 * @return
	 */
	@Post("/count/totalUnreadMessage")
	public String countTotalMessage() {
		int totalNum = MessageUtils.countTotalMessage(inv);
		return "@" + totalNum;
	}

}
