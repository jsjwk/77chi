<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>系统消息明细</title>
<%@include file="/WEB-INF/include/taglib.jsp"%>
<script>
	function deleteOne(id){
		if(confirm("确定要删除这条消息吗？")){
			location.href = "/system/message/delete/" + id + "/" + ${curPage};
		}
	}
	
	$(document).ready(function() {
		$("#dt_sysmessage").attr("class", "active_dt");
	});
</script>
</head>
<body>
	<div class="warp">
		<div class="content">
			<%@include file="/WEB-INF/include/top-menu.jsp"%>
			<div class="article notice">
				<%@include file="/WEB-INF/include/message-menu.jsp"%>
				<div class="noti_right">
					<div class="top_notiright">
						<!-- <a class="backall" href="#">返回</a> -->
						<a class="deleteall" href="javascript:void(0);" onclick="deleteOne(${systemMessage.id});">删除</a>
						<!-- <div class="noti_num"><a href="#">上一封</a>|<a href="#">下一封</a></div> -->
					</div>
					<div class="noti_xiangx">
						<div class="pic_fjr"><img src="${urlStatic}/images/app/xitong_ico.jpg" alt="" />管理员</div>
						<div class="txt_fjr">
							<div class="txt_title">${systemMessage.title}<span><script>document.write(getSmpFormatDate(new Date(${systemMessage.sendTime}), true));</script></span></div>
							<div class="txt_mian">
								${systemMessage.content}
							</div>
							<div class="txt_bottom">人人帮你租管理员</div>
						</div>
					</div>
					<div class="noti_huif">
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>