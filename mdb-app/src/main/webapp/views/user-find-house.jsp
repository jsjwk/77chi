<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>个人中心-我求租-房子意向</title>
<%@include file="/WEB-INF/include/taglib.jsp"%>
<link rel="stylesheet" href="${urlStatic}/lib/autosuggest/autosuggest_inquisitor.css" type="text/css" media="screen" charset="utf-8" />
<script type="text/javascript" src="${urlStatic}/lib/autosuggest/jquery.watermarkinput.js"></script>
<script type="text/javascript" src="${urlStatic}/lib/autosuggest/bsn.AutoSuggest_2.1.3.js"></script>
 <script type="text/javascript">
 function initialize(){
		var options = {
			script:"/purpose/user/where/search?cityName=" + $("#cityName").val() + "&",
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
		<c:choose>
			<c:when test="${empty purpose.houseWhereName}">
				$("#input_search").Watermark("请输入关键字（区域或小区）");	
			</c:when>
			<c:otherwise>
				$("#input_search").Watermark("${purpose.houseWhereName}");	
			</c:otherwise>
		</c:choose>
				<c:if test="${not empty purpose.houseWhereName}">
					$("#houseWhereId").val("${purpose.houseWhereId}");
					$("#whereType").val("${purpose.whereType}");
					$("#areaCode").val("${purpose.areaCode}")
				</c:if>
	}
	
	function reinitialize(){
		initialize();
	}
	
	jQuery(function() {
		initialize();
	});
		
		function sub(){
			$("#form_search").submit();
		}
		
	</script>  
</head>
<body>
	<div class="warp">
		<div class="content">
			<%@include file="/WEB-INF/include/top-menu.jsp"%>
			<div class="article notice">
				<div class="noti_left">
					<div class="top_notileft">个人中心</div>
					<dl>
						<dt><a href="/usercenter/infocenter">个人资料</a></dt>
					</dl>
					<dl class="show_dd">
						<dt class="active_dt show_dt"><a href="#">我求租</a></dt>
						<dd class="active_dd"><a href="/usercenter/housePurpose">房子意向</a></dd>
						<dd><a href="/usercenter/personPurpose">合租伙伴意向</a></dd>
						<dd><a href="/usercenter/attentionHouse">关注和预约</a></dd>
						<dd><a href="/invitation/find/receiveInvitation">收到的邀请</a></dd>
					</dl>
					<dl>
						<dt class="hide_dt"><a href="#">我出租</a></dt>
						<dd><a href="/usercenter/rentout/house">我的房子</a></dd>
						<dd><a href="/usercenter/rentout/personPurpose">合租伙伴意向</a></dd>
						<dd><a href="/invitation/rentout/myInvitation">我的邀请</a></dd>
						<dd><a href="/usercenter/rentout/myReservation">收到的预约</a></dd>
					</dl>
					<dl>
						<dt><a href="#">订阅管理</a></dt>
					</dl>
					<dl>
						<dt class="hide_dt"><a href="#">站内信</a></dt>
						<dd><a href="/inbox/home">收件箱</a></dd>
						<dd><a href="/outbox/home">发件箱</a></dd>
					</dl>
					<dl>
						<dt><a href="/usercenter/listHosueComment">回访评价</a></dt>
					</dl>
				</div>
				<div class="noti_right">
					<div class="top_notiright2">
						我想租这样的房子：
					</div>
					<div class="noti_form">
						<form id="personal_form">
					<ul>
						<li>
							<label>城市：</label>
							<div class="sub_div">
								<select name="cityName" id="cityName" onchange="reinitialize()">
									<c:forEach items="${cityList}" var="city">
										<option value="${city}" <c:if test="${city eq purpose.cityName}">selected="selected"</c:if>>${city}</option>
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
									<input type="text" size="50" name="houseWhereName" id="input_search" class="text"/>
								</form>
							</div>
						</li>
						<li>
							<label>户型：</label>
							<div class="sub_div">
								<select name="houseType1" style="width:50px">
									<option value="1" <c:if test ="${purpose.houseType1 eq '1'}" >selected="selected"</c:if>>1</option>
									<option value="2" <c:if test ="${purpose.houseType1 eq '2'}" >selected="selected"</c:if>>2</option>
									<option value="3" <c:if test ="${purpose.houseType1 eq '3'}" >selected="selected"</c:if>>3</option>
									<option value="4" <c:if test ="${purpose.houseType1 eq '4'}" >selected="selected"</c:if>>4</option>
									<option value="不限" <c:if test ="${purpose.houseType1 eq '不限'}" >selected="selected"</c:if>>不限</option>
									<option value="其他" <c:if test ="${purpose.houseType1 eq '其他'}" >selected="selected"</c:if>>其他</option>
								</select>室
								<select name="houseType2" style="width:50px">
									<option value="1" <c:if test ="${purpose.houseType2 eq '1'}" >selected="selected"</c:if>>1</option>
									<option value="2" <c:if test ="${purpose.houseType2 eq '2'}" >selected="selected"</c:if>>2</option>
									<option value="3" <c:if test ="${purpose.houseType2 eq '3'}" >selected="selected"</c:if>>3</option>
									<option value="4" <c:if test ="${purpose.houseType2 eq '4'}" >selected="selected"</c:if>>4</option>
									<option value="不限" <c:if test ="${purpose.houseType2 eq '不限'}" >selected="selected"</c:if>>不限</option>
									<option value="其他" <c:if test ="${purpose.houseType2 eq '其他'}" >selected="selected"</c:if>>其他</option>
								</select>厅
								<select name="houseType3" style="width:50px">
									<option value="1" <c:if test ="${purpose.houseType3 eq '1'}" >selected="selected"</c:if>>1</option>
									<option value="2" <c:if test ="${purpose.houseType3 eq '2'}" >selected="selected"</c:if>>2</option>
									<option value="3" <c:if test ="${purpose.houseType3 eq '3'}" >selected="selected"</c:if>>3</option>
									<option value="4" <c:if test ="${purpose.houseType3 eq '4'}" >selected="selected"</c:if>>4</option>
									<option value="不限" <c:if test ="${purpose.houseType3 eq '不限'}" >selected="selected"</c:if>>不限</option>
									<option value="其他" <c:if test ="${purpose.houseType3 eq '其他'}" >selected="selected"</c:if>>其他</option>
								</select>卫
							</div>
						</li>
						<li>
							<label>租金：</label>
							<div class="sub_div">
								<input class="text money" type="text" id="minRent" name="minRent" value="${purpose.minRent}" />-
								<input class="text money" type="text" id="maxRent" name="maxRent" value="${purpose.maxRent}" />元/月
							</div>
						</li>
						<li>
							<label>可入住时间：</label>
							<div class="sub_div">
								<input class="Wdate" type="text" onClick="WdatePicker()" name="checkInTime" readonly id="checkInTime" value="${purpose.checkInTime}" />
							</div>
						</li>
						<li class="sub_btn">
							<label></label>
							<div class="sub_div">
								<a href="javascript:void(0)" onclick="submit()"><img src="${urlStatic}/images/app/save.png" width="80" height="32" alt="logo" /></a>
							</div>
						</li>
					</ul>
				</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
function submit(){
	if($("#areaCode").val() == ""){
		alert("请根据输入框提示,填写有效的区域！");
		return;
	}
	var regexpRent = /^[0-9]+(.[0-9]{1,2})?$/;
	if($("#minRent").val().match(regexpRent) == null){
		alert("租金无效！");
		$("#minRent").focus();
		return;
	}
	if($("#maxRent").val().match(regexpRent) == null){
		alert("租金无效！");
		$("#maxRent").focus();
		return;
	}
	var param = $("#personal_form").serialize();
	$.ajax({
        type : "post",
        url : "/purpose/house/modify",
        data : param,
        success : function(data) {
            if(data>0){
            	alert("保存成功!");
            }else{
            	alert("保存失败!");
            };
        }
  });

}
</script>