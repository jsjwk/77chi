<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>个人中心-我出租-合租伙伴意向</title>
<%@include file="/WEB-INF/include/taglib.jsp"%>
<link rel="stylesheet" href="${urlStatic}/lib/autosuggest/autosuggest_inquisitor.css" type="text/css" media="screen" charset="utf-8" />
<script type="text/javascript" src="${urlStatic}/lib/autosuggest/jquery.watermarkinput.js"></script>
<script type="text/javascript" src="${urlStatic}/lib/autosuggest/bsn.AutoSuggest_2.1.3.js"></script>
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
					<dl class="show_dd">
						<dt class="active_dt show_dt"><a href="#">我出租</a></dt>
						<dd><a href="/usercenter/rentout/house">我的房子</a></dd>
						<dd class="active_dd"><a href="/usercenter/rentout/personPurpose">合租伙伴意向</a></dd>
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
						我想租这样的房子：
					</div>
					<div class="noti_form">
					<form action="/purpose/house/modify2" method="post" id="personal_form">
						<ul>
							<li>
								<label>性别：</label>
								<div class="sub_div">
									<select name="sex">
										<option>请选择</option>
										<option value="0" <c:if test ="${purpose.sex==0}">selected="selected"</c:if>>女</option>
										<option value="1" <c:if test ="${purpose.sex==1}">selected="selected"</c:if>>男</option>
										<option value="2" <c:if test ="${purpose.sex==2}">selected="selected"</c:if>>不限</option>
									</select>
								</div>
							</li>
							<li>
								<label>职业：</label>
								<div class="sub_div">
									<select name="professionPartner">
										<option value="-1" <c:if test ="${empty purpose.professionPartner}" >selected="selected"</c:if>>请选择</option>
										<option value="1" <c:if test ="${purpose.professionPartner==1}" >selected="selected"</c:if>>普通职业</option>
										<option value="2" <c:if test ="${purpose.professionPartner==2}" >selected="selected"</c:if>>学生</option>
										<option value="3" <c:if test ="${purpose.professionPartner==3}" >selected="selected"</c:if>>自由职业</option>
										<option value="4" <c:if test ="${purpose.professionPartner==4}" >selected="selected"</c:if>>其他</option>
									</select>
								</div>
							</li>
							<li>
								<label>习惯：</label>
								<div class="sub_div">
									<input type="checkbox" name=isSmokingPartner value="1" <c:if test ="${purpose.isSmokingPartner==1}">checked="checked"</c:if> />抽烟&nbsp;&nbsp;
									<input type="checkbox" name="isKeepingPetsPartner" value="1" <c:if test ="${purpose.isKeepingPetsPartner==1}">checked="checked"</c:if> />养宠物
								</div>
							</li>
							<li class="sub_btn">
								<label></label>
								<div class="sub_div">
									<a href="javascript:void(0)" onclick="$('#personal_form').submit();"><img src="${urlStatic}/images/app/save.png" width="80" height="32" alt="logo" /></a>
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