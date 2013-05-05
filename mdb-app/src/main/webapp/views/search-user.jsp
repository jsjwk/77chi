<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="g" uri="/WEB-INF/tags-core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>用户搜索结果</title>
<%@include file="/WEB-INF/include/taglib.jsp"%>
<script>
	function sub(){
		var param = $("form").serialize();
		var url = "/search/user?" + param;
		location.href = url;
	}
	
	$(document).ready(function() {
		$("#topli_search").attr("class", "active");
	});
	
	function changeCity(v){
		location.href = '/search?searchType=user&cityName=' + v;
	}
	
	function toHouseSearch(){
		location.href = '/search?searchType=house';
	}
	
	function submitInvitation(userId){
		var param = $("#invitation_from").serialize();
		$.ajax({
            type : "post",
            url : "/invitation/save/"+userId,
            data : "bean="+param,
            success : function(data) {
            	if (data>0){
                    alert( "邀请成功!" ) ;
                    } else if (data== -1){
                    alert( "缺少参数,邀请失败!" ) ;
                    } else if (data== -2){
                    alert( "已经邀请过了o(╯□╰)o!" ) ;
                    } else {
                    alert( "邀请失败!" ) ;
                    }
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
				<input type="radio" onclick="toHouseSearch();"/><font>出租，找房子</font>
				<input type="radio" checked/><font>出租，找合租伙伴</font>
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
				<form>
					<div class="sele_port">
						<span class="leftspan">性别：</span>
						<select class="sex" name="sex">
							<option value="-1">不限</option>
							<option value="1" <c:if test="${sex == 1}">selected</c:if>>男</option>
							<option value="0" <c:if test="${sex == 0}">selected</c:if>>女</option>
						</select>
						<span class="leftspan">习惯：</span>
						<select class="small" name="habit">
							<option value="-1">不限</option>
							<option value="1" <c:if test="${habit == 1}">selected</c:if>>不抽烟</option>
							<option value="2" <c:if test="${habit == 2}">selected</c:if>>不养宠物</option>
							<option value="3" <c:if test="${habit == 3}">selected</c:if>>不抽烟也不养宠物</option>
						</select>
						<span class="leftspan">职业：</span>
						<select class="small" name="career">
							<option value="-1">不限</option>
							<option value="1" <c:if test="${career == 1}">selected</c:if>>普通职业</option>
							<option value="2" <c:if test="${career == 2}">selected</c:if>>学生</option>
							<option value="3" <c:if test="${career == 3}">selected</c:if>>自由职业</option>
							<option value="4" <c:if test="${career == 4}">selected</c:if>>其他</option>
						</select>
						<span class="leftspan">区域：</span>
						<select class="small" name="areaCode">
							<option value="-1">城区</option>
							<c:forEach items="${searchAreaList}" var="area">
								<option value="${area.code}" <c:if test="${area.code == areaCode}">selected</c:if>>${area.name}</option>
							</c:forEach>
						</select>
						<span class="leftspan">价位：</span>
						<input class="text money" type="text" name="rent" <c:if test="${rent > 0}">value="${rent}"</c:if>/>
						<span>元/月</span>
						<a href="javascript:void(0);" onclick="sub();"><img src="${urlStatic}/images/app/search_btn.png" width="66" height="32" alt="search" /></a>
					</div>
				</form>
				<div class="quick_port">
					<p>
						快速搜索：
						<a href="/search/user?rentStop=500&areaCode=${areaCode}">500元以下</a>
							<a href="/search/user?rentStart=500&rentStop=1000&areaCode=${areaCode}">500-1000元</a>
							<a href="/search/user?rentStart=1000&rentStop=2000&areaCode=${areaCode}">1000-2000元</a>
							<a href="/search/user?rentStart=2000&rentStop=3000&areaCode=${areaCode}">2000-3000元</a>
							<a href="/search/user?rentStart=3000&rentStop=5000&areaCode=${areaCode}">3000-5000元</a>
							<a href="/search/user?rentStart=5000&rentStop=8000&areaCode=${areaCode}">5000-8000元</a>
							<a href="/search/user?rentStart=8000&areaCode=${areaCode}">8000元以上</a>
					</p>
					<p>
						热点区域：
						<c:forEach items="${searchAreaList}" var="area">
							<a href="/search/user?areaCode=${area.code}&rentStart=${rentStart}&rentStop=${rentStop}">${area.name}</a>
						</c:forEach>
					</p>
				</div>
				<div class="search_num">共计找到符合您条件的${page.total}条信息</div>
			</div>
			<h1 class="havebg">
				<input type="checkbox" name="checkedthis" /><font>全选</font>
				<div class="page_change">
					<g:pager curPage="${page.curPage}" total="${page.total}" pageSize="${page.pageSize}" forwardUrl="" />
				</div>
				<a class="interest" href="#"><img src="${urlStatic}/images/app/hzyq.png" width="66" height="24" alt="interest" /></a>
			</h1>
			<div class="section second">
				<ul>
					<c:forEach items="${purposeList}" var="purpose">
						<c:forEach items="${userInfoList}" var="userInfo">
							<c:if test="${purpose.userInfoId == userInfo.id}">
								<li>
									<input type="checkbox" name="checkedthis" />
									<div class="pic3_div">
										<img class="haveborder" src="${userInfo.tinyUrl}" width="42" height="42" alt="" />
										<span>${userInfo.userName}</span>
									</div>
									<div class="house_txt">
										<p>${userInfo.age}岁 | <c:forEach items="${userInfo.universityInfo}" var="ui">${ui.name} </c:forEach>|  <c:forEach items="${userInfo.workHistory}" var="wk">${wk.companyName}</c:forEach> </p>
										<p>
											<font>习惯：</font>
											<c:if test="${purpose.isKeepingPetsMe == 1}">
												<img src="${urlStatic}/images/app/chongwu.png" width="19" height="18" alt="" />
											</c:if>
											<c:if test="${purpose.isSmokingMe == 1}">
												<img src="${urlStatic}/images/app/chouyan.png" width="19" height="18" alt="" />
											</c:if>
										</p>
										<p>承受租金：<span class="orange_12"><fmt:formatNumber value="${purpose.minRent}" pattern="#.##" type="number"/>-<fmt:formatNumber value="${purpose.maxRent}" pattern="#.##" type="number"/></span>元/月 | 期望入住时间：${purpose.checkInTime}</p>
									</div>
									<div class="pic2_div">
										<a href ="javascript:void(0)" onclick ="submitInvitation(${userInfo.id});"><img src="${urlStatic}/images/app/hzyq2.png" width="66" height="24" alt="" /></a>
									</div>
								</li>
							</c:if>
						</c:forEach>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</div>
</body>
</html>