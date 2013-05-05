<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>房屋要求选择</title>
<%@include file="/WEB-INF/include/taglib.jsp"%>
<script>
	function sub(){
		var regexpRent = /^[0-9]+(.[0-9]{1,2})?$/;
		if($("#minRent").val().match(regexpRent) == null){
			alert("最小租金输入无效！");
			$("#minRent").focus();
			return;
		}
		if($("#maxRent").val().match(regexpRent) == null){
			alert("最大租金输入无效！");
			$("#maxRent").focus();
			return;
		}
		if($("#checkInTime").val() == ""){
			alert("请选择期望入住时间！");
			return;
		}
		$('#personal_form').submit();
	}
</script>
</head>
<body>
	<%@include file="/WEB-INF/include/top-menu.jsp"%>
	<div class="warp">
		<div class="content intention">
			<div class="header">
				<div class="logo"><img src="${urlStatic}/images/app/renren_logo.png" width="127" height="47" alt="logo" /></div>
			</div>
		</div>
		<div class="article intention">
			<div class="section second">
				<div class="findhouse_bg"><img src="${urlStatic}/images/app/findhouse_bg2.png" width="445" height="130" alt="logo" /></div>
				<form action="/purpose/house/personal" method="post" id="personal_form">
					<ul>
						<li class="adr_info">在<span>${purpose.houseWhereName}</span>附近</li>
						<li>
							<label>户型：</label>
							<div class="sub_div">
								<select name="roomNum" style="width:50px">
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="不限">不限</option>
									<option value="其他">其他</option>
								</select>室
								<select name="hallNum" style="width:50px">
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="不限">不限</option>
									<option value="其他">其他</option>
								</select>厅
								<select name="looNum" style="width:50px">
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="不限">不限</option>
									<option value="其他">其他</option>
								</select>卫
							</div>
						</li>
						<li>
							<label>租金：</label>
							<div class="sub_div">
								<input class="text money" type="text" id="minRent" name="minRent" value="" onkeydown="return onlyNum(event);"/>-
								<input class="text money" type="text" id="maxRent" name="maxRent" value="" onkeydown="return onlyNum(event);"/>元/月
							</div>
						</li>
						<li>
							<label>期望入住时间：</label>
							<div class="sub_div">
								<input class="Wdate" type="text" onClick="WdatePicker()" id="checkInTime" name="checkInTime" readonly/>
							</div>
						</li>
						<li class="sub_btn">
							<label></label>
							<div class="sub_div">
								<a href="javascript:void(0)" onclick="sub();"><img src="${urlStatic}/images/app/find_next.png" width="80" height="32" alt="logo" /></a>
							</div>
						</li>
						<li>
							<label></label>
							<div class="sub_div">
								<a href="javascript:void(0)" onclick="history.back();">&lt;&nbsp;&nbsp;返回上一步</a>
							</div>
						</li>
					</ul>
				</form>
			</div>
		</div>
	</div>
</body>
</html>