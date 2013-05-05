<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="g" uri="/WEB-INF/tags-core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>比邻人人APP-首页</title>
<%@include file="/WEB-INF/include/taglib.jsp"%>
</head>
<body>
	<div class="warp">
		<div class="content">
			<%@include file="/WEB-INF/include/top-menu.jsp"%>
			
			<div class="article home">
				<div class="section first gray_14">
					您要在<span class="orange_14">三元桥</span>附近寻找 <span class="orange_14">900</span>元/月左右<span class="orange_14">2居</span>的房子
					<a class="share gray9_12" href="#">分享</a>
					<a class="changemind gray9_12" href="/usercenter/housePurpose">修改意向</a>
				</div>
				<h1 class="havebg">
					<span>符合您意向的推荐（${page.total}条）</span>
					<span>排序：</span>
					<a class="time_list alist" href="#">时间</a><a class="money_list" href="#">租金</a>
					<div class="page_change">
						<a class="page_prev nochange" href="#">&lt;&nbsp;上一组</a>
						<a class="page_next" href="#">下一组&nbsp;&gt;</a>
					</div>
				</h1>
				<div class="section second">
					<ul>
						<c:forEach items ="${houseInfoList}" var="houseInfo">
							<li>
								<div class="house_pic"><a href="#"><img src="${urlStatic}/images/app/ceshi/house_pic.jpg" width="107" height="78" alt="house_img" /></a></div>
								<div class="house_txt">
									<h1>${houseInfo.adress}</h1>
									<div class="house_type">三元桥 &nbsp;&nbsp;--&nbsp;&nbsp;静安里小区 | ${houseInfo.propertyType} </div>
									<div class="house_money"><span>￥${houseInfo.rent}</span>元/月&nbsp;&nbsp;可入住时间：2012.7.12以后</div>
								</div>
								<div class="time">2012-7-12  13:19</div>
								<div class="likeit">感兴趣的人：
									<a href="#"><img src="${urlStatic}/images/app/ceshi/user_pic28.jpg" width="28" height="28" alt="user_img" /></a>
									<a href="#"><img src="${urlStatic}/images/app/ceshi/user_pic28.jpg" width="28" height="28" alt="user_img" /></a>
									<a href="#"><img src="${urlStatic}/images/app/ceshi/user_pic28.jpg" width="28" height="28" alt="user_img" /></a>
									<a href="#"><img src="${urlStatic}/images/app/ceshi/user_pic28.jpg" width="28" height="28" alt="user_img" /></a>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
				<h1>
					<span>TA们也在找附近的房子</span>
					<span class="span_right">看看以下区域的房子</span>
				</h1>
				<div class="section third">
					<div class="anybody">
						<a href="#"><img src="${urlStatic}/images/app/ceshi/user_pic.jpg" width="45" height="45" alt="user_logo" /></a>
						<a href="#"><img src="${urlStatic}/images/app/ceshi/user_pic.jpg" width="45" height="45" alt="user_logo" /></a>
						<a href="#"><img src="${urlStatic}/images/app/ceshi/user_pic.jpg" width="45" height="45" alt="user_logo" /></a>
						<a href="#"><img src="${urlStatic}/images/app/ceshi/user_pic.jpg" width="45" height="45" alt="user_logo" /></a>
						<a href="#"><img src="${urlStatic}/images/app/ceshi/user_pic.jpg" width="45" height="45" alt="user_logo" /></a>
						<a href="#"><img src="${urlStatic}/images/app/ceshi/user_pic.jpg" width="45" height="45" alt="user_logo" /></a>
						<a href="#"><img src="${urlStatic}/images/app/ceshi/user_pic.jpg" width="45" height="45" alt="user_logo" /></a>
						<a href="#"><img src="${urlStatic}/images/app/ceshi/user_pic.jpg" width="45" height="45" alt="user_logo" /></a>
					</div>
					<div class=anyhouse>
						<a href="#">静安庄</a>
						<a href="#">西坝河</a>
						<a href="#">和平里</a>
						<a href="#">安贞门</a>
						<a href="#">柳芳</a>
						<a href="#">国展</a>
						<a href="#">亮马桥 </a>
						<a href="#">东直门</a>
						<a href="#">三里屯</a>
						<a href="#">煤炭医院</a>
						<a href="#">国贸</a>
						<a href="#">西坝河</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>