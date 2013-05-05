<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="g" uri="/WEB-INF/tags-core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>房间搜索结果</title>
<%@include file="/WEB-INF/include/taglib.jsp"%>
<script>
	function sub(){
		var param = $("#search_form").serialize();
		var url = "/search/house?" + param;
		location.href = url;
	}
	
	$(document).ready(function() {
		$("#topli_search").attr("class", "active");
	});
	
	function changeCity(v){
		location.href = '/search?searchType=house&cityName=' + v;
	}
	
	function toUserSearch(){
		location.href = '/search?searchType=user';
	}
	
	function attentionAll(checkName){
		var arr = getCheckArrValue(checkName);
		if(arr == ""){
			alert("请选择至少一项");
		}else{
			$.ajax({
				type : "post",
				url : "/houseAttention/batch/save",
				dataType : "text",
				data : "houseIds=" + arr,
				success : function(data) {
					alert(data);
				}
			});
		}
	}
	
	function reservationDivShow(houseId){
		//$("#recipient").val(userId);
		$("#houseId").val(houseId);
		$('#reservation_div').show();
	}
	
	function reservationFormSub(houseId){
		var param = $("#reservation_form_" + houseId).serialize();
		$.ajax({
			type : "post",
			url : "/reservation/save/search/house",
			dataType : "text",
			data : param,
			success : function(data) {
				alert(data);
				window.location.reload();
			}
		});
	}
</script>
</head>
<body>
	<div class="warp">
		<div class="content">
			<%@include file="/WEB-INF/include/top-menu.jsp"%>
			<div class="article home">
				<h1 class="havebg">
					<span>找找看</span>
					<input type="radio" checked/><font>出租，找房子</font>
					<input type="radio" onclick="toUserSearch();"/><font>出租，找合租伙伴</font>
					<div class="change_city">
						切换城市：
						<select name="cityName" id="cityName" onchange="changeCity(this.value)">
							<c:forEach items="${cityList}" var="city">
								<option value="${city}" <c:if test="${city == searchCityName}">selected</c:if>>${city}</option>
							</c:forEach>
						</select>
					</div>
				</h1>
				<div class="section search_factor">
					<form id="search_form">
						<div class="sele_port">
							<select name="areaCode">
								<option value="-1">城区</option>
								<c:forEach items="${searchAreaList}" var="area">
									<option value="${area.code}" <c:if test="${area.code == areaCode}">selected</c:if>>${area.name}</option>
								</c:forEach>
							</select>
							<span>区</span>
							<select name="houseType">
								<option value="-1">户型</option>
								<option value="1" <c:if test="${houseType == 1}">selected</c:if>>1</option>
								<option value="2" <c:if test="${houseType == 2}">selected</c:if>>2</option>
								<option value="3" <c:if test="${houseType == 3}">selected</c:if>>3</option>
								<option value="4" <c:if test="${houseType == 4}">selected</c:if>>4</option>
							</select>
							<span>居</span>
							<input class="text money" type="text" name="rent" <c:if test="${rent > 0}">value="${rent}"</c:if>/>
							<span>元/月</span>
							<a href="javascript:void(0);" onclick="sub();">
								<img src="${urlStatic}/images/app/search_btn.png" width="66" height="32" alt="search" /></a>
							<input type="text" name="keyWord"
									<c:choose><c:when test="${empty keyWord || keyWord == ''}">value="关键词"</c:when><c:otherwise>value="${keyWord}"</c:otherwise></c:choose>
									onfocus="if(this.value == '关键词'){this.value = '';this.style.color='#000000';}"
									onBlur="if(this.value == ''){this.value = '关键词';this.style.color='#777777';}" style='color:#777777' class="text"/>
						</div>
					</form>
					<div class="quick_port">
						<p>
							快速搜索：
							<a href="/search/house?rentStop=500&areaCode=${areaCode}">500元以下</a>
							<a href="/search/house?rentStart=500&rentStop=1000&areaCode=${areaCode}">500-1000元</a>
							<a href="/search/house?rentStart=1000&rentStop=2000&areaCode=${areaCode}">1000-2000元</a>
							<a href="/search/house?rentStart=2000&rentStop=3000&areaCode=${areaCode}">2000-3000元</a>
							<a href="/search/house?rentStart=3000&rentStop=5000&areaCode=${areaCode}">3000-5000元</a>
							<a href="/search/house?rentStart=5000&rentStop=8000&areaCode=${areaCode}">5000-8000元</a>
							<a href="/search/house?rentStart=8000&areaCode=${areaCode}">8000元以上</a>
						</p>
						<p>
							热点区域：
							<c:forEach items="${searchAreaList}" var="area">
								<a href="/search/house?areaCode=${area.code}&rentStart=${rentStart}&rentStop=${rentStop}">${area.name}</a>
							</c:forEach>
						</p>
					</div>
					<div class="search_num">共计找到符合您条件的${page.total}条信息</div>
				</div>
				<h1 class="havebg">
					<input type="checkbox" name="checkedthis" id="checkAll" onclick="checkAll(this.id, 'checkbox')"/><font>全选</font>
					<div class="page_change">
						<g:pager curPage="${page.curPage}" total="${page.total}" pageSize="${page.pageSize}" forwardUrl="" />
					</div>
					<a class="interest" href="javascript:void(0);" onclick="attentionAll('checkbox')">
						<img src="${urlStatic}/images/app/interest.png" width="96" height="24" alt="interest" /></a>
				</h1>
				<div class="section second">
					<ul>
						<c:forEach items="${houseList}" var="houseInfo">
							<li>
								<input type="checkbox" name="checkbox" id="checkbox" value="${houseInfo.id}"/>
								<div class="house_pic">
									<a href="/house/info/${houseInfo.id}">
										<c:if test="${empty houseInfo.houseImgList}">
											<img src="${urlStatic}/images/www/house_small.png" width="107" height="78" alt="${houseInfo.houseTitle}" />
										</c:if>
										<c:if test="${not empty houseInfo.houseImgList}">
											<img src="${urlImg}${houseInfo.houseImgList[0].path}${houseInfo.houseImgList[0].fileName}${houseInfo.houseImgList[0].suffix}" width="96" height="72" alt="${houseInfo.houseTitle}" />
										</c:if>
									</a>
								</div>
								<div class="house_txt">
									<a href="/house/info/${houseInfo.id}"><h1>${houseInfo.houseTitle}</h1></a>
									<div class="house_money height_35">
										<span>￥<fmt:formatNumber value="${houseInfo.rent}" pattern="#.##" type="number"/></span>元/月&nbsp;&nbsp;可入住时间：<c:if test="${not empty houseInfo.checkInTime}">${houseInfo.checkInTime}以后</c:if></div>
									<div class="house_type">评价：很好(5) | 好(4) | 一般(8) | 差 (1)</div>
								</div>
								<div class="pic2_div">
									<c:forEach items="${publisherList}" var="publisher">
										<c:if test="${publisher.id == houseInfo.publisherId}">
											<c:if test="${not empty publisher.tinyUrl}">
												<img class="haveborder" src="${publisher.tinyUrl}" width="42" height="42" alt="${publisher.userName}" />
											</c:if>
											<c:if test="${empty publisher.tinyUrl}">
												<img class="haveborder" src="${urlStatic}/images/www/img_user40.png" width="42" height="42" alt="${publisher.userName}" />
											</c:if>
										</c:if>
									</c:forEach>
									<a class="add_reservation" href="#all_reservation_${houseInfo.id}">
										<img src="${urlStatic}/images/app/yuyue.png" width="66" height="24" alt="" /></a>
								</div>
								
								<div style="display:none;">
									<div id="all_reservation_${houseInfo.id}">
										<form id="reservation_form_${houseInfo.id}">
											<input type="hidden" id="houseId" name="houseId" value="${houseInfo.id}"/>
											<table>
												<tr>
													<td>留言：</td>
													<td><textarea rows="5" cols="40" name="content" id="content"></textarea></td>
												</tr>
												<tr>
													<td>电话：</td>
													<td><input type="text" name="senderPhone" id="senderPhone" size="30" value="${sessionUser.mobile}"/></td>
												</tr>
												<tr>
													<td colspan="2"><input type="button" onclick="reservationFormSub(${houseInfo.id});" value="提交"/>
												</tr>
											</table>
										</form>
									</div>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
	
	
</body>
</html>