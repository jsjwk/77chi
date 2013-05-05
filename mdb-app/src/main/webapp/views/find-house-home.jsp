<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="g" uri="/WEB-INF/tags-core"%>
<%@taglib uri= "http://java.sun.com/jsp/jstl/fmt" prefix ="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>找房主页</title>
<%@include file="/WEB-INF/include/taglib.jsp"%>
<script>
	$(document).ready(function() {
		$("#topli_house").attr("class", "active");
		var sortCol = '${sortCol}';
		if(sortCol == "time"){
			$("#a_time").attr("class", "time_list alist");
			$("#a_rent").attr("class", "money_list");
		}
		if(sortCol == "rent"){
			$("#a_time").attr("class", "time_list");
			$("#a_rent").attr("class", "money_list alist");
		}
	});
	
	function sharePurpose(){
		var resourceUrl = "http://${appRenren}";
		var pic = " ";
		var title = "我在帮你租找房";
		var description = " ";
		var message = "我在帮你租发布了一条求租信息，想在“${purpose.houseWhereName}”附近寻找“${purpose.rentInfo}元/月左右”的“${purpose.roomNum}居”的房子，希望大家帮我推荐！";
		shareClick(resourceUrl, pic, title, description, message);
	}
	
	function shareHouse(rent,areaName,houseType,checkInTime){
		var resourceUrl = "http://${appRenren}";
		var pic = " ";
		var title = "分享房源信息";
		var description = " ";
		var message = "我在帮你租(http://${appRenren})看到一条房源信息，价格: " + rent +"元/月;位置: "+areaName+";户型: "+houseType+";可入住时间："+checkInTime+"，这里还有更多的房源信息，来看看吧！";
		shareClick(resourceUrl, pic, title, description, message);
	}
</script>
</head>
<body>
	<div class="warp">
		<div class="content">
			<%@include file="/WEB-INF/include/top-menu.jsp"%>
			
			<div class="article home">
				<div class="section first gray_14">
					您要在<span class="orange_14">${purpose.houseWhereName}</span>附近寻找 <span class="orange_14"><fmt:formatNumber value="${purpose.minRent}" pattern="#.##" type="number"/>-<fmt:formatNumber value="${purpose.maxRent}" pattern="#.##" type="number"/></span>元/月左右<span class="orange_14">${purpose.roomNum}居</span>的房子
					<a class="share gray9_12" href="javascript:void(0)" onclick="sharePurpose();">分享</a>
					<a class="changemind gray9_12" href="/usercenter/housePurpose">修改意向</a>
				</div>
				<h1 class="havebg">
					<span>符合您意向的推荐（${page.total}条）</span>
					<span>排序：</span>
					<a href="/purpose/house/search?sortCol=time" id="a_time">时间</a>
					<a href="/purpose/house/search?sortCol=rent" id="a_rent">租金</a>
					<div class="page_change">
						<g:pager curPage="${page.curPage}" total="${page.total}" pageSize="${page.pageSize}" forwardUrl="" />
					</div>
				</h1>
				<div class="section second">
					<ul>
						<c:forEach items="${houseList}" var="houseInfo">
							<li>
								<div class="house_pic">
									<c:if test="${not empty houseInfo.houseImgList}">
										<a href="/house/info/${houseInfo.id}">
										<img src="${urlImg}${houseInfo.houseImgList[0].path}${houseInfo.houseImgList[0].fileName}${houseInfo.houseImgList[0].suffix}" width="107" height="78"/></a>
									</c:if>
									<c:if test="${empty houseInfo.houseImgList}">
										<a href="/house/info/${houseInfo.id}">
										<img src="${urlStatic}/images/www/house_small.png" width="107" height="78"/></a>
									</c:if>
								</div>
								<div class="house_txt">
									<a href="/house/info/${houseInfo.id}"><h1>${houseInfo.houseTitle}</h1></a>
									<a href="javascript:void(0)" onclick="shareHouse('${houseInfo.rent}','${houseInfo.area.name}','${houseInfo.houseType}','${houseInfo.checkInTime}');">
										<img src="${urlStatic}/images/app/renren_ico.png"/></a>
									<div class="house_type">${houseInfo.area.name} &nbsp;&nbsp;--&nbsp;&nbsp;${houseInfo.communityName} | ${houseInfo.houseType} </div>
									<div class="house_money"><span>￥<fmt:formatNumber value="${houseInfo.rent}" pattern="#.##" type="number"/></span>元/月&nbsp;&nbsp;可入住时间：<c:if test="${not empty houseInfo.checkInTime}">${houseInfo.checkInTime}以后</c:if></div>
								</div>
								<div class="time"><script>document.write(getSmpFormatDate(new Date(${houseInfo.updateTime}), true));</script></div>
								<div class="likeit">也想租此房的人：
									<c:forEach items="${houseInfo.wantRentUserList}" var="wantRentUser">
										<a href="javascript:void(0)"><img src="${wantRentUser.tinyUrl}" width="28" height="28" alt="${wantRentUser.userName}"/></a>
									</c:forEach>
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
						<c:forEach items="${aboutUserList}" var="user">
							<a href="#"><img src="${user.tinyUrl}" width="45" height="45" alt="${user.userName}" /></a>
						</c:forEach>
					</div>
					<div class=anyhouse>
						<c:forEach items="${areaList}" var="area">
							<a href="/search/house?rentStart=${purpose.minRent}&rentStop=${purpose.maxRent}&areaCode=${area.code}&houseType=${purpose.roomNum}">${area.name}</a>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>