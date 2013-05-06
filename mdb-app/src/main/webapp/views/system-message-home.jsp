<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="g" uri="/WEB-INF/tags-core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>系统消息主页</title>
<%@include file="/WEB-INF/include/taglib.jsp"%>
<script type="text/javascript">
	function deleteAll(checkName){
		var arr = getCheckArrValue(checkName);
		if(arr == ""){
			alert("请选择至少一项");
		}else{
			if(confirm("确定要删除选中的所有消息吗？")){
				location.href = "/system/message/batchDelete/" + arr + "/" + ${page.curPage};
			}
		}
	}
	
	function deleteOne(id){
		if(confirm("确定要删除这条消息吗？")){
			location.href = "/system/message/delete/" + id + "/" + ${page.curPage};
		}
	}
	
	$(document).ready(function() {
		$("#dt_sysmessage").attr("class", "active_dt");
	});
</script>
</head>
<body>
	<div class="warp">
		<div class="content">
			<%@include file="/WEB-INF/include/top-menu.jsp"%>
			<div class="article notice">
				<%@include file="/WEB-INF/include/message-menu.jsp"%>
				<div class="noti_right">
					<div class="top_notiright">
						<input type="checkbox" id="checkAll" onclick="checkAll(this.id, 'checkbox')"/><span>全选</span>
						<a class="deleteall" href="javascript:void(0);" onclick="deleteAll('checkbox')">删除</a>
						<span class="noti_num">${systemMessageCount}条信息未读，共${page.total}条</span>
					</div>
					<ul>
						<c:forEach items="${systemMessageList}" var="systemMessage">
							<li>
								<input type="checkbox" name="checkbox" value="${systemMessage.id}" />
								<div class="noti_txt">
								<p class="noti_time"><script>document.write(getSmpFormatDate(new Date(${systemMessage.sendTime}), true));</script></p>
								<c:if test="${systemMessage.isRead == 1}">
									<p><a href="/system/message/detail/${systemMessage.id}/${page.curPage}">${systemMessage.title}</a></p>
								</c:if>
								<c:if test="${systemMessage.isRead == 0}">
									<p><b><a href="/system/message/detail/${systemMessage.id}/${page.curPage}">${systemMessage.title}</a></b></p>
								</c:if>
								</div>
								<a class="delete" href="javascript:void(0)" onclick="deleteOne(${systemMessage.id});">
									<img src="${urlStatic}/images/app/deleone_ico.png" width="18" height="19" alt="删除${systemMessage.title}" /></a>
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