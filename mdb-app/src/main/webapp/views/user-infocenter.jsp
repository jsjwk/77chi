<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>个人中心-个人资料</title>
<%@include file="/WEB-INF/include/taglib.jsp"%>
</head>
<body>
	<div class="warp">
		<div class="content">
			<%@include file="/WEB-INF/include/top-menu.jsp"%>
			<div class="article notice">
				<div class="noti_left">
					<div class="top_notileft">个人中心</div>
					<dl>
						<dt class="active_dt"><a href="/usercenter/infocenter">个人资料</a></dt>
					</dl>
					<dl>
						<dt class="hide_dt"><a href="javascript:void(0)">我求租</a></dt>
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
					<div class="top_notiright2">
						基本信息：
					</div>
					<div class="noti_mian">
						<div class="user_pic"><img src="${sessionUser.headUrl}" width="94" height="94" alt="user pic" /></div>
						<div class="user_txt">
							<p>姓名：${sessionUser.userName}</p>
							<p>性别：
								<c:choose>
									<c:when test="${sessionUser.sex == 0}">
									     女 
									</c:when>
									<c:otherwise>
									     男
									</c:otherwise>
								</c:choose>
							</p>
							<p>年龄：${sessionUser.age}</p>
							<p>大学：
								<c:choose>
									<c:when test="${empty sessionUser.universityInfo}">
									     暂无信息
									</c:when>
									<c:otherwise>
									   <c:forEach items="${sessionUser.universityInfo}" var="universityInfo" >
											${universityInfo.name}
									   </c:forEach>
									</c:otherwise>
								</c:choose>
							</p>
							<p>公司：
								<c:choose>
									<c:when test="${empty sessionUser.workHistory}">
									     暂无信息
									</c:when>
									<c:otherwise>
									   <c:forEach items="${sessionUser.workHistory}" var="workHistory" >
											${workHistory.companyName}
									   </c:forEach>
									</c:otherwise>
								</c:choose>
							</p>
						</div>
					</div>
					<div class="top_notiright2">
						其他信息：
					</div>
					<div class="noti_form">
					  <form method="post" action="/usercenter/saveInfo" name="other_info"/>
						<ul>
							<li>
								<label>职业：</label>
								<div class="sub_div">
								<select name="professionMe">
									<option value="-1" <c:if test ="${empty sessionUser.career}" >selected="selected"</c:if>>请选择</option>
									<option value="1" <c:if test ="${sessionUser.career==1}" >selected="selected"</c:if>>普通职业</option>
									<option value="2" <c:if test ="${sessionUser.career==2}" >selected="selected"</c:if>>学生</option>
									<option value="3" <c:if test ="${sessionUser.career==3}" >selected="selected"</c:if>>自由职业</option>
									<option value="4" <c:if test ="${sessionUser.career==4}" >selected="selected"</c:if>>其他</option>
								</select>
							</div>
							</li>
							<li>
								<label>习惯：</label>
								<div class="sub_div">
									<input type="checkbox" name="smoke" value="1" <c:if test ="${sessionUser.habit.smoke==1}">checked="checked"</c:if> />抽烟&nbsp;&nbsp;
									<input type="checkbox" name="havePet" value="1" <c:if test ="${sessionUser.habit.havePet==1}">checked="checked"</c:if> />养宠物
								</div>
							</li>
							<li>
								<label>手机号码：</label>
								<div class="sub_div">
									<input class="text" name="mobile" type="text" value="${sessionUser.mobile}" /><span>请填写真实号码以便联系</span>
								</div>
							</li>
							<li class="sub_btn">
								<label></label>
								<div class="sub_div">
									<a href ="javascript:void(0)" onclick = "document.other_info.submit();" ><img src="${urlStatic}/images/app/save.png" width="80" height="32" alt="logo" /></a>
								</div>
							</li>
						</ul>
					  </form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>