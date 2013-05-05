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
<script type="text/javascript" src="${urlStatic}/lib/swfupload/swfupload.js"></script>
<script type="text/javascript" src="${urlStatic}/lib/swfupload/handlers.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var swfu;
		swfu = new SWFUpload({
			upload_url : "/purpose/user/houseimg/upload",
			flash_url : "/swfupload/swfupload.swf",
			post_params: {"jsessionid" : "<%=session.getId()%>"},
			use_query_string : true,
			//File Upload Settings
			file_size_limit : "2 MB",
			file_types : "*.jpg;*.gif;*.png",
			file_types_description : "图片类型",
			file_upload_limit : "10",
			// Button Settings
			button_placeholder_id : "spanButtonPlaceholder",
			button_width: 180,
			button_height: 80,
			button_text : '<span class="button">点击添加房子图片<br/><span class="buttonSmall">(单张图片不超过2M)</span></span>',
			button_text_style : '.button { font-family: Helvetica, Arial, sans-serif; font-size: 12pt; } .buttonSmall { font-size: 10pt; }',
			button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
			button_cursor: SWFUpload.CURSOR.HAND,
			
			//event handler
			file_queue_error_handler : fileQueueError,
			file_dialog_complete_handler : fileDialogComplete,
			upload_progress_handler : uploadProgress,
			upload_error_handler : uploadError,
			upload_success_handler : uploadSuccess,
			upload_complete_handler : uploadComplete,
			
			custom_settings : {
				upload_target : "divFileProgressContainer"
			}
		});
	});
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
					<dl>
						<dt class="hide_dt"><a href="#">我求租</a></dt>
						<dd><a href="/usercenter/housePurpose">房子意向</a></dd>
						<dd><a href="/usercenter/personPurpose">合租伙伴意向</a></dd>
						<dd><a href="/usercenter/attentionHouse">关注和预约</a></dd>
						<dd><a href="/invitation/find/receiveInvitation">收到的邀请</a></dd>
					</dl>
					<dl class="show_dd">
						<dt class="active_dt show_dt"><a href="#">我出租</a></dt>
						<dd class="active_dd"><a href="/usercenter/rentout/house">我的房子</a></dd>
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
						我的房子的情况：
					</div>
					<div class="noti_form">
						<ul>
							<form>
								<li>
									<div class="sub_div add_div">
										<span id="spanButtonPlaceholder"></span>
										<p>有图片的房子更容易被快速出租哦</p>
										<div id="divFileProgressContainer" style="height: 75px;"></div>
										<div id="thumbnails"></div>
									</div>
								</li>
							</form>
							<form action="/usercenter/rentout/houseModify/${house.id}" method="post" id="aboutme_form">
							<li>
								<label>城市：</label>
								<div class="sub_div">
									<select name="cityName" id="cityName" onchange="reinitialize()">
										<c:forEach items="${cityList}" var="city">
											<option value="${city}" <c:if test ="${house.cityName eq city}" >selected="selected"</c:if>>${city}</option>
										</c:forEach>
									</select>
								</div>
							</li>
							<li>
								<label>小区：</label>
								<div class="sub_div">
									<input type="hidden" id="houseWhereId" name="houseWhereId">
									<input type="hidden" id="whereType" name="whereType">
									<input type="hidden" id="areaCode" name="areaCode">
									<input type="text" size="50" name="houseWhereName" id="input_search" class="text">
								</div>
							</li>
							<li>
								<label>户型：</label>
								<div class="sub_div">
								<select name="houseType1" style="width:50px">
									<option value="1" <c:if test ="${houseType1 eq '1'}" >selected="selected"</c:if>>1</option>
									<option value="2" <c:if test ="${houseType1 eq '2'}" >selected="selected"</c:if>>2</option>
									<option value="3" <c:if test ="${houseType1 eq '3'}" >selected="selected"</c:if>>3</option>
									<option value="4" <c:if test ="${houseType1 eq '4'}" >selected="selected"</c:if>>4</option>
									<option value="不限" <c:if test ="${houseType1 eq '不限'}" >selected="selected"</c:if>>不限</option>
									<option value="其他" <c:if test ="${houseType1 eq '其他'}" >selected="selected"</c:if>>其他</option>
								</select>室
								<select name="houseType2" style="width:50px">
									<option value="1" <c:if test ="${houseType2 eq '1'}" >selected="selected"</c:if>>1</option>
									<option value="2" <c:if test ="${houseType2 eq '2'}" >selected="selected"</c:if>>2</option>
									<option value="3" <c:if test ="${houseType2 eq '3'}" >selected="selected"</c:if>>3</option>
									<option value="4" <c:if test ="${houseType2 eq '4'}" >selected="selected"</c:if>>4</option>
									<option value="不限" <c:if test ="${houseType2 eq '不限'}" >selected="selected"</c:if>>不限</option>
									<option value="其他" <c:if test ="${houseType2 eq '其他'}" >selected="selected"</c:if>>其他</option>
								</select>厅
								<select name="houseType3" style="width:50px">
									<option value="1" <c:if test ="${houseType3 eq '1'}" >selected="selected"</c:if>>1</option>
									<option value="2" <c:if test ="${houseType3 eq '2'}" >selected="selected"</c:if>>2</option>
									<option value="3" <c:if test ="${houseType3 eq '3'}" >selected="selected"</c:if>>3</option>
									<option value="4" <c:if test ="${houseType3 eq '4'}" >selected="selected"</c:if>>4</option>
									<option value="不限" <c:if test ="${houseType3 eq '不限'}" >selected="selected"</c:if>>不限</option>
									<option value="其他" <c:if test ="${houseType3 eq '其他'}" >selected="selected"</c:if>>其他</option>
								</select>卫
							</div>
							</li>
							<li>
								<label>房间：</label>
								<div class="sub_div">
									<select name="roomType">
										<option value="主卧带卫生间" <c:if test ="${house.roomType eq '主卧带卫生间'}" >selected="selected"</c:if>>主卧带卫生间</option>
										<option value="主卧不带卫生间" <c:if test ="${house.roomType eq '主卧不带卫生间'}" >selected="selected"</c:if>>主卧不带卫生间</option>
										<option value="次卧" <c:if test ="${house.roomType eq '次卧'}" >selected="selected"</c:if>>次卧</option>
										<option value="隔断" <c:if test ="${house.roomType eq '隔断'}" >selected="selected"</c:if>>隔断</option>
										<option value="床位" <c:if test ="${house.roomType eq '床位'}" >selected="selected"</c:if>>床位</option>
									</select>
								</div>
							</li>
							<li>
								<label>楼层：</label>
								<div class="sub_div">
									<input class="text floor" type="text" id="floorNum" name="floorNum" value="${house.floorNum}" />层，共
									<input class="text floor" type="text" id="floorTotalNum" name="floorTotalNum" value="${house.floorTotalNum}" />层
								</div>
							</li>
							<li>
								<label>租金：</label>
								<div class="sub_div">
									<input class="text money" type="text" id="rent" name="rent" value="${house.rent}" />元/月
								</div>
							</li>
							<li>
								<label>面积：</label>
								<div class="sub_div">
									<input class="text money" type="text" id="rentArea" name="rentArea" value="${house.rentArea}"  />平方米
								</div>
							</li>
							<li>
								<label>朝向：</label>
								<div class="sub_div">
									<input class="text money" type="text" name="towards" value="${house.towards}"  />
								</div>
							</li>
							<li>
								<label>入住时间：</label>
								<div class="sub_div">
									<input class="Wdate" type="text" onClick="WdatePicker()" name="checkInTime"  id="checkInTime" readonly value ="${house.checkInTime}" />
								</div>
							</li>
							<li>
								<label>描述：</label>
								<div class="sub_div">
									<textarea name="discription">${house.discription}</textarea>
								</div>
							</li>
							<li>
								<label>联系方式：</label>
								<div class="sub_div">
									<input class="text" type="text" id="contactNum" name="contactNum" value="${house.contactNum}" />
								</div>
							</li>
							<li class="sub_btn">
								<div class="sub_div">
									<a href="javascript:void(0)" onclick="sub();"><img src="${urlStatic}/images/app/save.png" width="80" height="32" alt="logo" /></a>
								</div>
							</li>
						  </form>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
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
			$("#input_search").Watermark("请输入关键字（区域或小区）");	
		}
		
		function reinitialize(){
			initialize();
		}
		
		jQuery(function() {
			initialize();
		});
		
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
		
		function sub(){
			if($("#areaCode").val() == ""){
				alert("输入的区域无效！");
				return;
			}
			var regexpFloor = /^[1-9]d*$/;
			if($("#floorNum").val().match(regexpFloor) == null){
				alert("所在楼层数无效！");
				$("#floorNum").focus();
				return;
			}
			if($("#floorTotalNum").val().match(regexpFloor) == null){
				alert("最高楼层数无效！");
				$("#floorTotalNum").focus();
				return;
			}
			var regexpRent = /^[0-9]+(.[0-9]{1,2})?$/;
			if($("#rent").val().match(regexpRent) == null){
				alert("租金无效！");
				$("#rent").focus();
				return;
			}
			if($("#rentArea").val().match(regexpRent) == null){
				alert("面积无效！");
				$("#rentArea").focus();
				return;
			}
			if($("#checkInTime").val() == ""){
				alert("请选择入住时间！");
				return;
			}
			var regexpMobile = /^1\d{10}$/;
			if($("#contactNum").val().match(regexpMobile) == null){
				alert("手机号码无效！");
				$("#contactNum").focus();
				return;
			}
			$("#aboutme_form").submit();
		}
	</script>
</body>
</html>