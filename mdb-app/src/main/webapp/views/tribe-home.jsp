<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>部落首页</title>
<%@include file="/WEB-INF/include/taglib.jsp"%>
<script type="text/javascript">
	function search(obj, event) {
		if (event.keyCode == 13) {
			search_form.action="/tribe/searchTribe/"+document.getElementById("tribeName").value;
			document.search_form.submit();
		}
	}
	/**
	 * 初始化输入框的值
	 */
	function initTestArea(obj, param) {
		if (param == 0) {
			obj.value = "";
		} else {
			obj.value = param;
		}
	}
</script>
</head>
<body>
	<div class="warp">
		<div class="content">
			<%@include file="/WEB-INF/include/top-menu.jsp"%>
			<div class="article tribe">
				<h1 class="havebg">
					<span>部落</span>
					<form name="search_form" action="" method="post">
						<input class="search" id="tribeName"
							onkeydown="search(this,event)" onfocus="initTestArea(this,0)"
							onBlur='initTestArea(this,"搜索部落(部落名称或部落介绍)")' type="text"
							value="搜索部落(部落名称或部落介绍)" />
					</form>
				</h1>
				<div class="section first">
					<div class="left_port">
						<h2>
							<span>我加入的部落</span>
						</h2>
						<ul>
							<c:choose>
								<c:when test="${not empty myTribeList}">
									<c:forEach items="${myTribeList}" var="myTribe">
										<li class="tribe_mine">
											<%-- 	 <img src="${urlStatic}/images/app/ceshi/tribe_logo72.jpg" width="72" height="72" alt="tribe logo" /> --%>
											<div class="tribe_txt">
												<h2><a href="javascript:void(0)" onclick="location.href='/tribe/detail/${myTribe.id}'">${myTribe.name}</a></h2>
												<a href="javascript:void(0)" onclick="location.href='/tribe/detail/${myTribe.id}'">${myTribe.topicNum}条信息</a>
											</div>
										</li>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<li class="tribe_mine">
										<div class="tribe_txt">
											<h2>您未加入任何部落</h2>
										</div>
									</li>
								</c:otherwise>
							</c:choose>
						</ul>
						<h2>
							<span>热门部落</span>
						</h2>
						<ul>
							<c:choose>
								<c:when test="${not empty hotTribeList}">
									<c:forEach items="${hotTribeList}" var="hotTribe">
										<li>
											<%-- 	 	<img src="${urlStatic}/images/app/ceshi/tribe_logo50.jpg" width="50" height="50" alt="tribe logo" /> --%>
											<div class="tribe_txt">
												<h2>${hotTribe.name}</h2>
												<a href="javascript:void(0)"
													onclick="location.href='/tribe/detail/${hotTribe.id}'">${hotTribe.topicNum}条信息</a>
											</div>
										</li>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<li>
										<div class="tribe_txt">
											<h2>您未加入任何部落</h2>
										</div>
									</li>
								</c:otherwise>
							</c:choose>
						</ul>
						<h2>
							<span><a href="/tribe/listTribes">所有部落</a></span>
						</h2>
						<ul>
						<c:forEach items="${tribes}" var="tribe">
							<li>
							<c:choose>
								<c:when test="${empty tribe.headImg.midUrl}">
									<img src="${urlStatic}/images/app/ceshi/tribe_logo50.jpg" width="50" height="50" alt="tribe logo" />
								</c:when>
								<c:otherwise>
									<img src="${tribe.headImg.midUrl}" width="50" height="50" alt="tribe logo" />
								</c:otherwise>
							</c:choose>
								<div class="tribe_txt">
									<h2>${tribe.name}</h2>
									<a href="#">${tribe.topicNum}条信息</a>
								</div>
							</li>
						</c:forEach>
						</ul>
					</div>
					<div class="right_port">
						<div class="right_main">
							<ul>
								<c:if test="${not empty createTribeIds}">
									<li class="tribe_set"><a href="/tribe/admin/listMyTribes?tribeIds=${createTribeIds}">管理我的部落</a></li>
								</c:if>
									<li class="tribe_add"><a href="/tribe/applyCreate">申请创建部落</a></li>
							</ul>
							<h2>热门话题</h2>
							<ul>
							  <c:forEach items= "${hotTopicList}" var="topic" >
								<li><a href="/tribe/topic/topicDetial/${topic.id}?tribeName=${topic.tribe.name}">${topic.title}</a></li>
							  </c:forEach>
							</ul>
							<h2>我的话题</h2>
							<ul>
							  <c:forEach items= "${myTopicList}" var="topic" >
								<li><a href="/tribe/topic/topicDetial/${topic.id}?tribeName=${topic.tribe.name}">${topic.title}</a></li>
							  </c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>