<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>房屋位置选择</title>
<%@include file="/WEB-INF/include/taglib.jsp"%>
<link rel="stylesheet" href="${urlStatic}/lib/autosuggest/autosuggest_inquisitor.css" type="text/css" media="screen" charset="utf-8" />
<script type="text/javascript" src="${urlStatic}/lib/autosuggest/jquery.watermarkinput.js"></script>
<script type="text/javascript" src="${urlStatic}/lib/autosuggest/bsn.AutoSuggest_2.1.3.js"></script>
</head>
<body>
	<div class="warp">
		<div class="content intention">
			<div class="header">
				<div class="logo"><img src="${urlStatic}/images/app/renren_logo.png" width="127" height="47" alt="logo" /></div>
			</div>
		</div>
		<div class="article intention">
			<div class="section second">
				<div class="findhouse_bg"><img src="${urlStatic}/images/app/findhouse_bg1.png" width="445" height="130" alt="logo" /></div>
				<ul>
					<li>
						<label>城市：</label>
						<div class="sub_div">
							<select name="cityName" id="cityName" onchange="reinitialize()">
								<c:forEach items="${cityList}" var="city">
									<option value="${city}">${city}</option>
								</c:forEach>
							</select>
						</div>
					</li>
					<li>
						<label>我想住在：</label>
						<div class="sub_div">
							<form method="post" action="/purpose/house/demand" id="form_search">
								<input type="hidden" id="houseWhereId" name="houseWhereId"/>
								<input type="hidden" id="whereType" name="whereType"/>
								<input type="hidden" id="areaCode" name="areaCode"/>
								<input type="text" size="50" name="houseWhereName" id="input_search" class="text" onkeydown="return onkey(event);"/>
							</form>
						</div>
					</li>
					<li class="sub_btn">
						<label></label>
						<div class="sub_div">
							<a href="javascript:void(0)" onclick="sub();"><img src="${urlStatic}/images/app/find_next.png" width="80" height="32" alt="logo" /></a>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function initialize(){
			var options = {
					script:"/purpose/house/where/search?cityName=" + $("#cityName").val() + "&",
					varname:"keyword",
					json:true,
					shownoresults:true,
					noresults:"搜索不到相关数据！",
					//maxresults:10,
					cache:false,
					minchars:1,
					timeout:false,
					callback: function (obj) {
						$("#houseWhereId").val(obj.id);
						$("#whereType").val(obj.type);
						$("#areaCode").val(obj.code);
					}
				};
				var as_json = new bsn.AutoSuggest('input_search', options);
				$("#input_search").val("");
				$("#input_search").Watermark("请输入关键字（区域或小区）");	
		}
		
		function reinitialize(){
			initialize();
		}
		
		function sub(){
			if($("#areaCode").val() == ""){
				alert("输入的区域无效！");
			}else{
				$("#form_search").submit();
			}
		}
		
		function onkey(e){
			if (window.event) { // IE
				keynum = e.keyCode;
			} else if (e.which) { // Netscape/Firefox/Opera
				keynum = e.which;
			}
			if (keynum == 13) {
				return false;
			}
		}
		
		jQuery(function() {
			initialize();
		});
	</script>
</body>
</html>