<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>管理部落日志</title>
<%@include file="/WEB-INF/include/taglib.jsp"%>
</head>
<body>
	<div class="warp">
		<div class="content">
			<%@include file="/WEB-INF/include/top-menu.jsp"%>
			<div class="article tribe">
				<h1 class="height60 noline">
					<span>管理我的部落</span>
					<a href="/tribe/home">返回部落首页&gt;&gt;</a>
				</h1>
				<div class="title">
					<a href="/tribe/admin/manageTribe/${tribeId}">基本信息</a>
					<a href="/tribe/admin/membermanage/${tribeId}">成员管理</a>
					<a href="/tribe/admin/manageTopic/${tribeId}">内容管理</a>
					<a class="active last" href ="javascript:void(0)" >管理日志</a>
				</div>
				<div class="section newtribe topnone">
					<div class="list">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td class="underline" width="428">事件</td>
								<td class="underline">时间</td>
							</tr>
							<!-- <tr>
								<td>部长<span>张珊珊</span>修改了部落介绍</td>
								<td>08-30 20：56</td>
							</tr> -->
							<c:forEach items="${tribeManageLogs}" var="log"  varStatus="status">
		                         <tr>
									<td>${status.index + 1}<span>${log.operatorName}</span>${log.modifyContent}</td>
									<td><script>document.write(getFormatDateByLong(${log.operateTime}, "MM-dd hh:mm" ));</script ></td>
								 </tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>