<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="g" uri="/WEB-INF/tags-core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>个人中心-我求租-我的邀请</title>
<%@include file="/WEB-INF/include/taglib.jsp"%>
<link rel="stylesheet"
	href="${urlStatic}/lib/autosuggest/autosuggest_inquisitor.css"
	type="text/css" media="screen" charset="utf-8" />
<script type="text/javascript"
	src="${urlStatic}/lib/autosuggest/jquery.watermarkinput.js"></script>
<script type="text/javascript"
	src="${urlStatic}/lib/autosuggest/bsn.AutoSuggest_2.1.3.js"></script>
<script type="text/javascript">
function deleteAll(checkName){
	var arr = getCheckArrValue(checkName);
	if(arr == ""){
		alert("请选择至少一项");
	}else{
			$.ajax({
	            type : "post",
	            url : "/invitation/delete/"+arr,
	            data : 'replyMessage='+$("#replyMessage").val(),
	            success : function(data) {
	            	if(data>0){
		            	alert("删除成功!");
	            	}else{
	            		alert("删除失败!");
	            	}
	                window.location.href = "/invitation/rentout/myInvitation?page="+${page.curPage};
	            }
	      });
	}
}
function submitReply(id){
	$.ajax({
        type : "post",
        url : "/invitation/reInvite/"+id,
        data : 'replyMessage='+$("#replyMessage").val(),
        success : function(data) {
        	if(data>0){
            	alert("预约成功!");
        	}else{
        		alert("预约失败!");
        	}
            window.location.href = "/invitation/rentout/myInvitation?page="+${page.curPage} ;
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
						<dt>
							<a href="/usercenter/infocenter">个人资料</a>
						</dt>
					</dl>
					<dl>
						<dt class="hide_dt">
							<a href="#">我求租</a>
						</dt>
						<dd>
							<a href="/usercenter/housePurpose">房子意向</a>
						</dd>
						<dd>
							<a href="/usercenter/personPurpose">合租伙伴意向</a>
						</dd>
						<dd>
							<a href="/usercenter/attentionHouse">关注和预约</a>
						</dd>
						<dd>
							<a href="/invitation/find/receiveInvitation">收到的邀请</a>
						</dd>
					</dl>
					<dl class="show_dd">
						<dt class="active_dt show_dt">
							<a href="#">我出租</a>
						</dt>
						<dd>
							<a href="/usercenter/rentout/house">我的房子</a>
						</dd>
						<dd>
							<a href="/usercenter/rentout/personPurpose">合租伙伴意向</a>
						</dd>
						<dd class="active_dd">
							<a href="/invitation/rentout/myInvitation">我的邀请</a>
						</dd>
						<dd>
							<a href="/usercenter/rentout/myReservation">收到的预约</a>
						</dd>
					</dl>
					<dl>
						<dt>
							<a href="#">订阅管理</a>
						</dt>
					</dl>
					<dl>
						<dt class="hide_dt">
							<a href="#">站内信</a>
						</dt>
						<dd>
							<a href="/inbox/home">收件箱</a>
						</dd>
						<dd>
							<a href="/outbox/home">发件箱</a>
						</dd>
					</dl>
					<dl>
						<dt><a href="/usercenter/listHosueComment">回访评价</a></dt>
					</dl>
				</div>
				<div class="noti_right">
					<div class="top_notiright">
						<input type="checkbox" id="checkAll" onclick="checkAll(this.id, 'checkbox')" /><span>全选</span> 
						<a class="deleteall" href="javascript:void(0);" onclick="deleteAll('checkbox')">删除</a> 
					</div>
					<ul>
					<c:forEach items="${page.datas}" var="invitation">
						<li>
							<input type="checkbox" value="${invitation.id}"	name="checkbox">
							<div class="yq_mian">
								<div class="user_zl">
									<p>${invitation.sender.userName} | ${invitation.sender.age}岁 | 
									<c:if test ="${invitation.sender.career==1}">普通职业</c:if>
									<c:if test ="${invitation.sender.career==2}">学生</c:if>
									<c:if test ="${invitation.sender.career==3}">自由职业</c:if>
									<c:if test ="${invitation.sender.career==4}">其他职业</c:if>
									</p>
									<p>
										<c:choose>
										  <c:when test="${empty invitation.sender.universityInfo}">暂无大学信息</c:when>
										  <c:otherwise>
										    <c:forEach items="${invitation.sender.universityInfo}" var="university" >
										      ${university.name}
										    </c:forEach>
										  </c:otherwise>
										</c:choose>
										  |  
										<c:choose>
										  <c:when test="${empty invitation.sender.workHistory}">暂无工作信息</c:when>
										  <c:otherwise>
										    <c:forEach items="${invitation.sender.workHistory}" var="company" >
										      ${company.companyName}
										    </c:forEach>
										  </c:otherwise>
										</c:choose>
									</p>
								</div>
								<div class="user_yxqy">
									<font>TA的意向区域：</font>
									<span>${invitation.sender.purpose.areaName}</span>
								</div>
								<p class="pre_time">
								上次发出邀请时间：<span><script>document.write(getFormatDateByLong(${invitation.sendTime}, "MM-dd hh:mm"));</script></span></p>
							</div>
							<div class="pic2_div">
								<img class="haveborder" src="${urlStatic}/images/app/ceshi/user_pic.jpg" width="42" height="42" alt="" />
								<c:if test="${invitation.status==2}">
									<a href="javascript:void(0)" onclick="submitReply(${invitation.id});"><img class="haveborder" src="${urlStatic}/images/app/zcyq.png" width="66" height="24" alt="" /></a>
								</c:if>
									<c:if test="${invitation.status==0}"><br/>处理中</c:if>
									<c:if test="${invitation.status==1}"><br/>已接受邀请</c:if>
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