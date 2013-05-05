<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="g" uri="/WEB-INF/tags-core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>发件箱主页</title>
<%@include file="/WEB-INF/include/taglib.jsp"%>
<script type="text/javascript">
	function deleteAll(checkName){
		var arr = getCheckArrValue(checkName);
		if(arr == ""){
			alert("请选择至少一项");
		}else{
			if(confirm("确定要删除选中的所有消息吗？")){
				location.href = "/outbox/batchDelete/" + arr + "/" + ${page.curPage};
			}
		}
	}
	
	function deleteOne(id){
		if(confirm("确定要删除这条消息吗？")){
			location.href = "/outbox/delete/" + id + "/" + ${page.curPage};
		}
	}
	
	$(document).ready(function() {
		$("#dt_inbox").attr("class", "active_dt");
	});
</script>
</head>
<body>
	<div class="warp">
		<div class="content">
			<%@include file="/WEB-INF/include/top-menu.jsp"%>
			<div class="article notice">
				<div class="noti_left">
					<div class="top_notileft">个人中心</div>
					<dl>
						<dt><a href="/usercenter/infocenter">个人资料</a></dt>
					</dl>
					<dl>
						<dt class="hide_dt"><a href="#">我求租</a></dt>
						<dd><a href="/usercenter/housePurpose">房子意向</a></dd>
						<dd><a href="/usercenter/personPurpose">合租伙伴意向</a></dd>
						<dd><a href="/usercenter/attentionHouse">关注和预约</a></dd>
						<dd><a href="/invitation/find/receiveInvitation">收到的邀请</a></dd>
					</dl>
					<dl>
						<dt class="hide_dt"><a href="#">我出租</a></dt>
						<dd><a href="/usercenter/rentout/house">我的房子</a></dd>
						<dd><a href="/usercenter/rentout/personPurpose">合租伙伴意向</a></dd>
						<dd><a href="/invitation/rentout/myInvitation">我的邀请</a></dd>
						<dd><a href="/usercenter/rentout/myReservation">收到的预约</a></dd>
					</dl>
					<dl>
						<dt><a href="#">订阅管理</a></dt>
					</dl>
					<dl class="show_dd">
						<dt class="active_dt show_dt"><a href="#">站内信</a></dt>
						<dd><a href="/inbox/home">收件箱</a></dd>
						<dd class="active_dd"><a href="/outbox/home">发件箱</a></dd>
					</dl>
					<dl>
						<dt><a href="/usercenter/listHosueComment">回访评价</a></dt>
					</dl>
				</div>
				<div class="noti_right">
					<div class="top_notiright">
						<input type="checkbox" id="checkAll" onclick="checkAll(this.id, 'checkbox')"/><span>全选</span>
						<a class="deleteall" href="javascript:void(0)" onclick="deleteAll('checkbox')">删除</a>
						<span class="noti_num">共${page.total}条</span>
					</div>
					<ul>
						<c:forEach items="${outboxList}" var="outbox">
							<c:forEach items="${userList}" var="user">
								<c:if test="${outbox.recipient == user.id}">
									<li>
										<input type="checkbox" name="checkbox" value="${outbox.id}" />
										<%-- <img src="${user.tinyUrl}"/> --%>
										<div class="noti_txt">
										<p>${user.userName}</p>
										<p class="noti_tit"><a href="/outbox/detail/${outbox.id}/${page.curPage}">${outbox.title}</a></p>
										</div>
										<span><script>document.write(getSmpFormatDate(new Date(${outbox.sendTime}), true));</script></span>
									</li>
								</c:if>
							</c:forEach>
						</c:forEach>
						<li>
							<g:pager curPage="${page.curPage}" total="${page.total}" pageSize="${page.pageSize}" forwardUrl="" />
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>