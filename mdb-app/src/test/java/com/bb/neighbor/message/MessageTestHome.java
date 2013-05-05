package com.bb.neighbor.message;

import java.util.Date;

import org.junit.Test;

import com.bb.mongo.utils.DateFormat;
import com.bb.neighbor.message.home.InboxHome;
import com.bb.neighbor.message.home.OutboxHome;
import com.bb.neighbor.message.home.SystemMessageHome;
import com.bb.neighbor.message.model.Inbox;
import com.bb.neighbor.message.model.Outbox;
import com.bb.neighbor.message.model.SystemMessage;

public class MessageTestHome {
	private SystemMessageHome systemMessageHome = new SystemMessageHome();
	private InboxHome inboxHome = new InboxHome();
	private OutboxHome outboxHome = new OutboxHome();
	
	/**
	 * 测试新增系统消息
	 */
	//@Test
	public void testInsertSystemMessage(){
		for(int i = 0; i < 120; i++){
			SystemMessage sm = new SystemMessage();
			sm.setTitle("系统提醒：请勿轻易将自己的个人信息泄露给他人。" + i);
			sm.setContent("亲爱的人人网朋友：<br><br>感谢您使用人人帮你租，请您在求租与出租的时候，请不要轻易地将自己的个人信息泄露给他人，以免给您带来不必要的麻烦。" + i);
			sm.setIsRead(0);
			sm.setSendTime(DateFormat.stringToLong(DateFormat.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss"));
			sm.setSender(28);
			sm.setRecipient(27);
			systemMessageHome.saveSystemMessage(sm);
		}
	}
	
	/**
	 * 测试修改系统消息状态
	 */
	//@Test
	public void testUpdateSystemMessageIsRead(){
		systemMessageHome.updateSystemMessageRead(1);
	}
	
	/**
	 * 测试删除系统消息
	 */
	//@Test
	public void testDeleteSystemMessage(){
		systemMessageHome.deleteSystemMessageById(1);
	}
	
	/**
	 * 测试新增收件
	 */
	//@Test
	public void testInsertInbox(){
		for(int i = 0; i < 20; i++){
			Inbox in = new Inbox();
			in.setTitle("请问房子租出去了吗？" + i);
			in.setContent("我们这边有2个女生想要租房。<br>请问...." + i);
			in.setIsRead(0);
			in.setSendTime(DateFormat.stringToLong(DateFormat.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss"));
			in.setSender(28);
			in.setRecipient(27);
			inboxHome.saveInbox(in);
		}
	}
	
	/**
	 * 测试新增发件
	 */
	@Test
	public void testInsertOutbox(){
		for(int i = 0; i < 20; i++){
			Outbox out = new Outbox();
			out.setTitle("请问房子租出去了吗？" + i);
			out.setContent("我们这边有2个女生想要租房。<br>请问...." + i);
			out.setSendTime(DateFormat.stringToLong(DateFormat.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss"));
			out.setSender(27);
			out.setRecipient(28);
			outboxHome.saveOutbox(out);
		}
	}
}
