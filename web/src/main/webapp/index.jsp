<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jstl/fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>77chi项目-敬请期待</title>
</head>
<body>
<h1>77chi项目-敬请期待</h1>

<table>
	<tr>
		<td></td>
		<td></td>
	</tr>

	<c:if test="${listTaobaokeItem ne null}">
		<c:forEach items="${listTaobaokeItem }" var="taobaokeItem">
			<tr>
				<td>${taobaokeItem.title }</td>
				<td><img alt="" src="${taobaokeItem.picUrl }"> </td>
			</tr>
		</c:forEach>
	</c:if>

</table>
</body>
</html>