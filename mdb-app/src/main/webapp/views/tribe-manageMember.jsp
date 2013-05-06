<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="g" uri="/WEB-INF/tags-core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>部落详细页</title>
<%@include file="/WEB-INF/include/taglib.jsp"%>
<script type="text/javascript">
	function searchMember(){
		 window.location.href="/tribe/admin/searchMember/"+${tribeId}+"/"+$("#memberName").val();
	}
</script>
</head>
<body>
	<div class="warp">
		<div class="content">
			<%@include file="/WEB-INF/include/top-menu.jsp"%>
			<div class="article tribe">
				<h1 class="height60">
					<span>管理我的部落</span>
					<a href="/tribe/home">返回部落首页&gt;&gt;</a>
				</h1>
				<div class="title">
					<a href="/tribe/admin/manageTribe/${tribeId}">基本信息</a>
					<a class="active" href="/tribe/admin/membermanage/${tribeId}">成员管理</a>
					<a href="/tribe/admin/manageTopic/${tribeId}">内容管理</a>
					<a class="last" href="/tribe/admin/manageTribeLog/${tribeId}">管理日志</a>
				</div>
				<div class="section newtribe">
					<div class="search_bar">
						<input class="search" type="text" id="memberName"/>
						<a href ="javascript:void(0)" onclick ="searchMember()"><img src="${urlStatic}/images/app/search_bg.png" width="32" height="30" alt="" /></a>
					</div>
					<div class="tribe_body">
						<div class="body_list">
							<label>管理员： </label>
							<div class="body_mian">
								<img src="${admin.userInfo.tinyUrl}" alt="" />
								<div class="body_txt">
									<p><a href="#">${admin.userInfo.userName}</a></p>
									<p><script>parseSex(${admin.userInfo.sex})</script> |  <script>parseCareer(${admin.userInfo.career})</script> | 
										<c:choose>
										  <c:when test="${empty admin.userInfo.workHistory}">暂无工作信息</c:when>
										  <c:otherwise>
										    <c:forEach items="${admin.userInfo.workHistory}" var="company" >
										      ${company.companyName}
										    </c:forEach>
										  </c:otherwise>
										</c:choose>
									 </p>
								</div>
							</div>
						</div>
						<div class="body_list">
							<label>副管理员： </label>
							<div class="body_rlist">
							  <c:forEach items="${managers}" var="manage">
								<div class="body_mian">
									<img src="${manage.userInfo.tinyUrl}" alt="" />
									<div class="body_txt">
										<p><a href="#">${manage.userInfo.userName}</a></p>
										<p><script>parseSex(${manage.userInfo.sex})</script> | <script>parseCareer(${manage.userInfo.career})</script>  | 
									 	<c:choose>
										  <c:when test="${empty manage.userInfo.workHistory}">暂无工作信息</c:when>
										  <c:otherwise>
										    <c:forEach items="${manage.userInfo.workHistory}" var="company" >
										      ${company.companyName}
										    </c:forEach>
										  </c:otherwise>
										</c:choose>
									  </p>
									</div>
									<div class="body_manage">
										<a href="#">降为成员</a>
									</div>
								</div>
							  </c:forEach>
							</div>
						</div>
						<div class="body_list">
							<label>成员： </label>
							<div class="body_rlist">
							<c:forEach items="${page.datas}" var="member">
								<div class="body_mian">
									<img src="${member.userInfo.tinyUrl}" alt="" />
									<div class="body_txt">
										<p><a href="#">${member.userInfo.userName}</a></p>
										<p><script>parseSex(${member.userInfo.sex})</script> | <script>parseCareer(${member.userInfo.career})</script> | 
											<c:choose>
											  <c:when test="${empty member.userInfo.workHistory}">暂无工作信息</c:when>
											  <c:otherwise>
											    <c:forEach items="${member.userInfo.workHistory}" var="company" >
											      ${company.companyName}
											    </c:forEach>
											  </c:otherwise>
											</c:choose>
										</p>
									</div>
									<div class="body_manage">
										<a href="#">升为副管理员</a>  |  <a href="#">T了TA</a>  |  <a href="#">禁言</a>
									</div>
								</div>
								</c:forEach>
								<li>
									<g:pager curPage="${page.curPage}" total="${page.total}" pageSize="${page.pageSize}" forwardUrl="" />
								</li>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>