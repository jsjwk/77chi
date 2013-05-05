<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="g" uri="/WEB-INF/tags-core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>个人中心-我出租-收到的预约</title>
<%@include file="/WEB-INF/include/taglib.jsp"%>
<link rel="stylesheet" href="${urlStatic}/lib/autosuggest/autosuggest_inquisitor.css" type="text/css" media="screen" charset="utf-8" />
<script type="text/javascript" src="${urlStatic}/lib/autosuggest/jquery.watermarkinput.js"></script>
<script type="text/javascript" src="${urlStatic}/lib/autosuggest/bsn.AutoSuggest_2.1.3.js"></script>
<script type="text/javascript">
	function deleteAll(checkName){
		var arr = getCheckArrValue(checkName);
		if(arr == ""){
			alert("请选择至少一项");
		}else{
			if(confirm("确定要删除选中的所有消息吗？")){
				location.href = "/usercenter/reservation/delete/" + arr + "/" + ${page.curPage};
			}
		}
	}
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
						<dd ><a href="/usercenter/personPurpose">合租伙伴意向</a></dd>
						<dd><a href="/usercenter/attentionHouse">关注和预约</a></dd>
						<dd><a href="/invitation/find/receiveInvitation">收到的邀请</a></dd>
					</dl>
					<dl class="show_dd">
						<dt class="active_dt show_dt"><a href="#">我出租</a></dt>
						<dd><a href="/usercenter/rentout/house">我的房子</a></dd>
						<dd><a href="/usercenter/rentout/personPurpose">合租伙伴意向</a></dd>
						<dd><a href="/invitation/rentout/myInvitation">我的邀请</a></dd>
						<dd class="active_dd"><a href="/usercenter/rentout/myReservation">收到的预约</a></dd>
					</dl>
					<dl>
						<dt><a href="#">订阅管理</a></dt>
					</dl>
					<dl>
						<dt class="hide_dt"><a href="#">站内信</a></dt>
						<dd><a href="/inbox/home">收件箱</a></dd>
						<dd><a href="/outbox/home">发件箱</a></dd>
					</dl>
					<dl>
						<dt><a href="/usercenter/listHosueComment">回访评价</a></dt>
					</dl>
				</div>
				<div class="noti_right">
					<div class="top_notiright">
						<input type="checkbox" id="checkAll" onclick="checkAll(this.id, 'checkbox')"/><span>全选</span>
						<a class="deleteall" href="javascript:void(0);" onclick="deleteAll('checkbox')">删除</a>
					</div>
					<ul>
						 <c:forEach items="${page.datas}" var="reservation" >
						<li>
							<input type="checkbox" value="${reservation.id}" name="checkbox">
							<div class="yq_mian">
								<div class="yq_txt">
									<p>${reservation.content}</p>
									<div class="bot_yq">
										${reservation.house.address} — ${reservation.house.communityName} | ${reservation.house.rent}元/月
										<span class="time">
											 <script>document.write(getFormatDateByLong(${reservation.house.updateTime}, "MM-dd hh:mm"));</script>
										</span>
									</div>
									<div class="arrow_left"></div>
								</div>
								<div class="btn_yq">
								<c:if test ="${reservation.handleStatus==0}" >
									<a class="right_yq" href="/usercenter/reservation/refuse/${reservation.id}/${page.curPage}"><img class="haveborder" src="${urlStatic}/images/app/jujue2.png" width="66" height="24" alt="" /></a>
									<a class="right_yq" href="/usercenter/reservation/accept/${reservation.id}/${page.curPage}"><img class="haveborder" src="${urlStatic}/images/app/jieshou.png" width="66" height="24" alt="" /></a>
								</c:if>
								<c:if test ="${reservation.handleStatus==1}" >已接受</c:if>
								<c:if test ="${reservation.handleStatus==2}" >已拒绝</c:if>
								</div>
							</div>
							<div class="pic2_div">
							  <c:if test ="${not empty reservation.senderInfo.tinyUrl}" >
								<img class="haveborder" src="${reservation.senderInfo.tinyUrl}" width="42" height="42" alt="" />
							  </c:if>
								<span>${reservation.senderInfo.userName}</span>
							</div>
						</li>						
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