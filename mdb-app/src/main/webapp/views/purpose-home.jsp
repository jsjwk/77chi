<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>意向引导首页</title>
<%@include file="/WEB-INF/include/taglib.jsp"%>
</head>
<body>
	<div class="warp">
		<div class="content intention">
			<div class="header">
				<div class="logo"><a href="#"><img src="${urlStatic}/images/app/renren_logo.png" width="127" height="47" alt="logo" /></a></div>
			</div>
		</div>
		<div class="article intention">
			<div class="section first">
				<p>至今，已经有 ${count} 位同学,通过比邻平台找到了合适的房子与合租伙伴</p>
				<p>请选择您的使用意向：</p>
				<div class="inten_door"><img src="${urlStatic}/images/app/renren_intentionlogin.png" width="500" height="202" alt="intentionlogin_img" /></div>
				<div class="inten_sub">
					<a href="/purpose/house/where"><img src="${urlStatic}/images/app/find_house.png" width="122" height="31" alt="find_house" /></a>
					<span>OR</span>
					<a href="/purpose/user/where"><img src="${urlStatic}/images/app/find_anybody.png" width="122" height="31" alt="find_anybody" /></a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>