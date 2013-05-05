<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>收件箱消息回复</title>
<%@include file="/WEB-INF/include/taglib.jsp"%>
<script type="text/javascript">
	function saveReply(){
		$("#replyForm").submit();
	}
</script>
</head>
<body>
	<%@include file="/WEB-INF/include/top-menu.jsp"%>
	<table width="98%" border="1">
		<tr>
			<td width="20%"><p>消息</p>
				<p><b><a href="/system/message/home">系统消息</a>&nbsp;&nbsp;
					<c:if test="${systemMessageCount > 0}">
						<font color="red">${systemMessageCount}</font></b>
					</c:if>
					</p>
				<p><a href="/inbox/home">站内信</a></p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/inbox/home">收件箱</a>&nbsp;&nbsp;
					<c:if test="${inboxMessage.total > 0}">
						<font color="red">${inboxMessage.total}</font></b>
					</c:if>
				</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/outbox/home">发件箱</a></p>
				<p>合租邀请</p>
				<p>预约请求</p>
				<p>回访评价</p></td>
			<td>
				<form action="/inbox/reply/save" method="post" id="replyForm">
					<table width="100%" border="0">
						<tr>
							<td colspan="2">
								<a href="javascript:void(0)" onclick="history.back()">返回</a>
							</td>
						</tr>
						<tr>
							<td>
								<b>回复</b>
							</td>
							<td>
								${user.userName}
								<input type="hidden" name="recipient" value="${user.id}"/>
								<input type="hidden" name="curPage" value="${curPage}"/>
							</td>
						</tr>
						<tr>
							<td>
								<b>标题</b>
							</td>
							<td>
								<input type="text" name="title" value="re:${inbox.title}" size="90"/>
							</td>
						</tr>
						<tr>
							<td>
								<b>内容</b>
							</td>
							<td>
								<textarea rows="8" cols="70" name="content">${sendTime}&nbsp;&nbsp;${user.userName}说：<br>${inbox.content}</textarea>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<a href="javascript:void(0)" onclick="saveReply();">发送</a>&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="javascript:void(0)" onclick="deleteOne(${inbox.id});">删除</a>
							</td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>
</body>
</html>