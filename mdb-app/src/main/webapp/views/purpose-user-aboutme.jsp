<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>我的情况</title>
<%@include file="/WEB-INF/include/taglib.jsp"%>
<script>
	function sub(){
		if($("#professionMe").val() == "-1"){
			alert("请选择职业！");
			return;
		}
		$('#aboutpartner_form').submit();
	}
</script>
</head>
<body>
	<div class="warp">
		<div class="content intention">
			<div class="header">
				<div class="logo"><a href="#"><img src="${urlStatic}/images/app/renren_logo.png" width="127" height="47" alt="logo" /></a></div>
			</div>
		</div>
		<div class="article intention">
			<div class="section second">
				<div class="findhouse_bg"><img src="${urlStatic}/images/app/findbody_bg2.png" width="452" height="130" alt="logo" /></div>
				<form action="/purpose/user/aboutpartner" method="post" id="aboutpartner_form">
					<ul>
						<li class="adr_info">在<span>${purpose.houseWhereName}</span>附近&nbsp;&nbsp;出租<span>${purpose.rentInfo}元/月</span>的房子</li>
						<li>
							<label>职业：</label>
							<div class="sub_div">
								<select name="professionMe" id="professionMe">
									<option value="-1">请选择</option>
									<option value="1">普通职业</option>
									<option value="2">学生</option>
									<option value="3">自由职业</option>
									<option value="4">其他</option>
								</select>
							</div>
						</li>
						<li>
							<label>习惯：</label>
							<div class="sub_div">
								<input type="checkbox" name="isSmokingMe" value="1"/>抽烟&nbsp;&nbsp;
								<input type="checkbox" name="isKeepingPetsMe" value="1"/>养宠物
							</div>
						</li>
						<li class="adr_info">以后还可以在【个人中心】里进行修改</li>
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