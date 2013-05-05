<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>申请创建部落</title>
<%@include file="/WEB-INF/include/taglib.jsp"%>
<script type="text/javascript"
	src="${urlStatic}/lib/swfupload/swfupload.js"></script>
<script type="text/javascript"
	src="${urlStatic}/lib/swfupload/handlers.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var swfu;
		swfu = new SWFUpload({
			upload_url : "/tribe/tribeImg/upload",
			flash_url : "/swfupload/swfupload.swf",
			//File Upload Settings
			file_size_limit : "2 MB",
			file_types : "*.jpg;*.gif;*.png",
			file_types_description : "图片类型",
			//图片数量
			file_upload_limit : "1",
			// Button Settings
			button_placeholder_id : "spanButtonPlaceholder",
			button_width : 180,
			button_height : 80,
			button_text : '<span class="button">点击添加部落Logo<br/><span class="buttonSmall">(单张图片不超过2M)</span></span>',
			button_text_style : '.button { font-family: Helvetica, Arial, sans-serif; font-size: 12pt; } .buttonSmall { font-size: 10pt; }',
			button_window_mode : SWFUpload.WINDOW_MODE.TRANSPARENT,
			button_cursor : SWFUpload.CURSOR.HAND,

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
	/**
	 * 初始化输入框的值
	 */
	function initTestArea(obj, param) {
		if (param == 0&& (obj.value == "最多40个字符或者20个汉字" || obj.value == "最多500个汉字")) {
			obj.value = "";
		} else if(param != 0&&(obj.value == "" || obj.value == "")){
			obj.value = param;
		}
	}
	function execute() {
		var param = $("#tribeForm").serialize();
		$.ajax({
	        type : "post",
	        url : "/tribe/saveTribe",
	        //contentType : "application/json",
	        //dataType : 'application/json',
	        data : param,
	        success : function(data) {
	        	if(data=="overLimit"){
	        		alert("最多只能创建3个部落!");
	        	}else{
	           		window.location.href = data ;
	        	}
	        }
	  });
	}
	
	function dropLabel(obj){
		$(obj).parent("span").remove();
	}
	
	function addLabel(){
		var name = $("#addLabel").val();
		if(name==""){
			alert("请填写标签");
			return;
		}
		var value = $("#label_list").html();
		value += '<span onmousemove="showDropBtn(this,0)" onmouseout="showDropBtn(this,1)"><input style="border:1px solid #999;height: 20px;text-align: center;vertical-align: middle;" size="8" maxlength="8" type="text" value="'+name+'" name="labels"/><a href ="javascript:void(0)" onclick="dropLabel(this)" style="visibility:hidden">×</a></span>';
		$("#label_list").html(value);
	}
	
	function showDropBtn(obj,param){
		if(param==1){
			$(obj).children("a").css("visibility","hidden");
		}else if(param==0){
			$(obj).children("a").css("visibility","visible");
		}
	}
	
	
</script>
</head>
<body>
	<div class="warp">
		<div class="content">
			<%@include file="/WEB-INF/include/top-menu.jsp"%>
			<div class="article tribe">
				<h1 class="height60">
					<span>部落</span> <a href="/tribe/home">返回部落首页&gt;&gt;</a>
				</h1>
				<div class="section newtribe">
				  <form id="tribeForm">
					<ul>
						<li><label>部落名称：</label>
							<div class="sub_newtribe">
								<input class="text" name="name" onfocus="initTestArea(this,0)"
									onBlur='initTestArea(this,"最多40个字符或者20个汉字")' type="text"
									value="最多40个字符或者20个汉字" />
							</div>
						</li>
						<li><label>部落介绍：</label>
							<div class="sub_newtribe">
								<textarea rows="" cols="" name="description" onfocus="initTestArea(this,0)"
									onBlur='initTestArea(this,"最多500个汉字")'>最多500个汉字</textarea>
							</div></li>
						<li><label>部落LOGO：</label>
							<div class="sub_newtribe">
								<!-- <a class="uppic" href="#">点击上传图片</a> -->
								<span id="spanButtonPlaceholder"></span>
								<p>支持JPG、GIF、PNG格式，小于2M</p>
								<div id="divFileProgressContainer" style="height: 75px;"></div>
									<div id="thumbnails"></div>
							</div> 
							<!-- <div class="sub_div add_div">
									<span id="spanButtonPlaceholder"></span>
									 <p>有图片的房子更容易被快速出租哦</p> 
									<div id="divFileProgressContainer" style="height: 75px;"></div>
									<div id="thumbnails"></div>
							</div>  -->
						</li>
						<li>
							<label>成员加入方式：</label>
							<div class="sub_newtribe">
								<select name="joinType">
									<option selected="selected" value="1">任何人都可以加入</option>
									<option value="2">要申请验证后才能加入</option>
								</select>
							</div>
						</li>
						<li>
							<label>标签：</label>
							<div class="sub_newtribe">
								<input type="text" value="" id="addLabel" /> <a href ="javascript:void(0)" onclick ="addLabel();">添加</a><br/> 
								<span id="label_list"></span>
							</div>
						</li>
						<li><label></label>
							<div class="sub_newtribe">
								<input type="checkbox" value="" checked="checked" />我知道了
								<p class="red">由于中国法律法规和相关政策的要求，请您勿发布色情、政治话题、或激进形态方面的讨论，我们并保留解散这类话题部落的权利。</p>
							</div>
						</li>
						<li><label></label>
							<div class="sub_newtribe">
								<a href ="javascript:void(0)" onclick ="execute()"><img src="${urlStatic}/images/app/sure.png"
									width="80" height="32" alt="nav" /></a> <a href="#"><img
									src="${urlStatic}/images/app/cancel.png" width="80" height="32"
									alt="nav" /></a>
							</div>
						</li>
					</ul>
				  </form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>