<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="g" uri="/WEB-INF/tags-core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>预约请求</title>
<%@include file="/WEB-INF/include/taglib.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#dt_reservation").attr("class", "active_dt");
	});
</script>
</head>
<body>
	<div class="warp">
		<div class="content">
			<%@include file="/WEB-INF/include/top-menu.jsp"%>
			<div class="article notice">
				<%@include file="/WEB-INF/include/message-menu.jsp"%>
				
				<%@include file="/WEB-INF/include/reservation.jsp"%>
			</div>
		</div>
	</div>
</body>
</html>