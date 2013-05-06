<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="noti_left">
	<div class="top_notileft">我的消息</div>
	<dl>
		<dt id="dt_sysmessage"><a href="/system/message/home">系统消息</a><c:if test="${systemMessageCount > 0}"><span>${systemMessageCount}</span></c:if></dt>
	</dl>
	<%-- <dl>
		<dt id="dt_inbox"><a href="/inbox/home">站内信</a><c:if test="${inboxMessage.total > 0}"><span>${inboxMessage.total}</span></c:if></dt>
	</dl>
	<dl>
		<dt><a class="znx" href="/inbox/home">收件箱</a>|<a class="znx" href="/outbox/home">发件箱</a></dt>
	</dl>
	<dl>
		<dt><a href="javascript:void(0);">合租邀请</a></dt>
	</dl>
	<dl>
		<dt id="dt_reservation"><a href="/reservation/message/list">预约请求</a><c:if test="${reservationMessage.total > 0}"><span>${reservationMessage.total}</span></c:if></dt>
	</dl>
	<dl>
		<dt><a href="javascript:void(0);">回访评价</a></dt>
	</dl> --%>
</div>