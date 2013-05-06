<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="g" uri="/WEB-INF/tags-core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>个人中心-我求租-关注和预约</title>
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
				location.href = "/houseAttention/delete/" + arr + "/" + ${page.curPage};
			}
		}
	}
	
	function reservationFormSub(houseId){
		var param = $("#reservation_form_" + houseId).serialize();
		$.ajax({
			type : "post",
			url : "/reservation/save/search/house",
			dataType : "text",
			data : param,
			success : function(data) {
				alert(data);
				window.location.reload();
			}
		});
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
					<dl class="show_dd">
						<dt class="active_dt show_dt"><a href="#">我求租</a></dt>
						<dd><a href="/usercenter/housePurpose">房子意向</a></dd>
						<dd><a href="/usercenter/personPurpose">合租伙伴意向</a></dd>
						<dd class="active_dd"><a href="/usercenter/attentionHouse">关注和预约</a></dd>
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
						<a class="dball" href="#">对比</a>
						<select>
							<option selected="selected">全部</option>
							<option>挨踢男啊</option>
						</select>
						<div class="px">
							<span>排序：</span>
							<c:choose>
								<c:when test="${orderBy eq 'rent'}">
									<a class="time_list" href="/usercenter/attentionHouse">时间</a><a class="money_list alist" href="/usercenter/attentionHouse?orderBy=rent">租金</a>
								</c:when>
								<c:otherwise>
									<a class="time_list alist" href="/usercenter/attentionHouse">时间</a><a class="money_list" href="/usercenter/attentionHouse?orderBy=rent">租金</a>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<ul>
					  <c:forEach items="${page.datas}" var="house" >
						<li>
							<input type="checkbox" value="${house.attentionId}" name="checkbox">
							<div class="pic_div">
								<img class="haveborder" src="${house.houseImgList[0].path}${house.houseImgList[0].fileName}400×300${house.houseImgList[0].suffix}" width="107" height="78" alt="" /></div>
							<div class="noti_txt">
								<p class="noti_time"><script>document.write(getFormatDateByLong(${house.updateTime}, "MM-dd hh:mm"));</script></p>
								<p>${house.houseTitle}</p>
								<p class="noti_tit">${house.address} — ${house.communityName} | ${house.houseType}   </p>
								<p class="noti_tit"><font class="orange_14">￥${house.rent}</font>元/月</p>
							</div>
							<div class="pic2_div">
								<img class="haveborder" src="${house.publisher.tinyUrl}" width="50" height="50" alt="" />
								<c:choose>
									<c:when test="${house.rentStatus==1}">
									 	房源已出租
									</c:when>
									<c:otherwise>
										<c:if test="${empty house.reservation}">
											<%-- <form action="/reservation/save" name="form_${house.id}" method="post">
												<input type="hidden" name="content" value="想预约您看房." />
												<input type="hidden" name="sender" value="${sessionUser.id}" />
												<input type="hidden" name="recipient" value="${house.publisherId}" />
												<input type="hidden" name="senderPhone" value="${sessionUser.mobile}" />
												<input type="hidden" name="houseId" value="${house.id}" />
											</form> --%>
											<div style="display:none;">
												<div id="all_reservation_${house.id}">
													<form id="reservation_form_${house.id}">
														<input type="hidden" id="houseId" name="houseId" value="${house.id}"/>
														<table>
															<tr>
																<td>留言：</td>
																<td><textarea rows="5" cols="40" name="content" id="content"></textarea></td>
															</tr>
															<tr>
																<td>电话：</td>
																<td><input type="text" name="senderPhone" id="senderPhone" size="30" value="${sessionUser.mobile}"/></td>
															</tr>
															<tr>
																<td colspan="2"><input type="button" onclick="reservationFormSub(${house.id});" value="提交"/>
															</tr>
														</table>
													</form>
												</div>
											</div>
											<a class="add_reservation" href="#all_reservation_${house.id}"><img src="${urlStatic}/images/app/yuyue.png" width="66" height="24" alt="" /></a>
										</c:if>
										<c:if test="${house.reservation.handleStatus==0}">
											<br/>请求处理中
										</c:if>
										<c:if test="${house.reservation.handleStatus==1}">
											<br/>预约成功
										</c:if>
										<c:if test="${house.reservation.handleStatus==2}">
											<br/>预约失败${house.reservation.reservationNumber}次，再次发送看房请求.
										</c:if>
									</c:otherwise>
								</c:choose>
								
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