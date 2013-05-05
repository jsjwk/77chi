package com.bb.neighbor.message.controllers;

import java.util.List;

import net.paoding.rose.web.InvocationLocal;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

import org.springframework.beans.factory.annotation.Autowired;

import com.bb.neighbor.common.Constants;
import com.bb.neighbor.common.Page;
import com.bb.neighbor.message.home.OutboxHome;
import com.bb.neighbor.message.model.Outbox;
import com.bb.neighbor.user.home.UserHome;
import com.bb.neighbor.user.model.UserInfo;

/**
 * 发件箱控制器
 * @author song.zhang@boco.jp
 *
 */
@Path("/outbox")
public class OutboxController {
	@Autowired
	private InvocationLocal inv;
	@Autowired
	private UserHome userHome;
	@Autowired
	private OutboxHome outboxHome;
	
	/**
	 * 发件箱列表页面
	 * @return
	 */
	@Get("/home")
	public String outboxHome(){
		UserInfo userInfo = (UserInfo)inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		
		int pageIndex = inv.getParameter("page") != null ? Integer.parseInt(inv.getParameter("page")) : 1;
		int total = (int)outboxHome.getOutboxCount(userInfo.getId());
		List<Outbox> outboxList = outboxHome.getOutboxList(userInfo.getId(), pageIndex, Constants.PAGE_SIZE_OUTBOX);
		Page<Outbox> page = new Page<Outbox>(total, Constants.PAGE_SIZE_OUTBOX, pageIndex, outboxList);
		
		List<UserInfo> userList = outboxHome.getUserListByOutboxList(outboxList);
		
		inv.addModel("page", page);
		inv.addModel("outboxList", outboxList);
		inv.addModel("userList", userList);
		return "outbox-home";
	}
	
	/**
	 * 读取发件箱消息
	 * @param id
	 * @param curPage
	 * @return
	 */
	@Get("/detail/{id}/{curPage}")
	public String outboxDetail(@Param("id")Integer id, @Param("curPage")int curPage){
		Outbox outbox = outboxHome.getOutboxById(id);
		UserInfo user = userHome.getUserInfoByUserId(outbox.getRecipient());
		inv.addModel("outbox", outbox);
		inv.addModel("user", user);
		inv.addModel("curPage", curPage);
		return "outbox-detail";
	}
	
	/**
	 * 批量删除选中的发件箱消息
	 * @param idArray
	 * @param curPage
	 * @return
	 */
	@Get("/batchDelete/{ids}/{curPage}")
	public String outboxBatchDelete(@Param("ids")Integer[] idArray, @Param("curPage")int curPage){
		for(Integer id : idArray){
			outboxHome.deleteOutboxById(id);
		}
		return "r:/outbox/home?page=" + curPage;
	}
	
	/**
	 * 删除发件箱消息
	 * @param id
	 * @param curPage
	 * @return
	 */
	@Get("/delete/{id}/{curPage}")
	public String outboxDelete(@Param("id")Integer id, @Param("curPage")int curPage){
		outboxHome.deleteOutboxById(id);
		return "r:/outbox/home?page=" + curPage;
	}
}
