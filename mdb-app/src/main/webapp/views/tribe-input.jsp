<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="g" uri="/WEB-INF/tags-core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>发布信息</title>
<%@include file="/WEB-INF/include/taglib.jsp"%>
</head>
<body>
	<div class="warp">
		<div class="content">
			<%@include file="/WEB-INF/include/top-menu.jsp"%>
			<div class="article tribe">
				<h1 class="height60">
					<span>部落<font>&gt;&gt; 发布信息</font></span>
					<a href="/tribe/home">返回部落首页&gt;&gt;</a>
				</h1>
				<div class="section newtribe">
				  <form action="/tribe/topic/release/save" method="post" id="releaseForm">
				    <input type="hidden" name="tribeId" value="${tribeId}"/>
					<ul>
						<li>
							<label>类型：</label>
							<div class="sub_newtribe">
								<select class="zuf" name="type">
									<option value="1">出租</option>
									<option value="2">求租</option>
								</select>
							</div>
						</li>
						<li>
							<label>标题：</label>
							<div class="sub_newtribe">
								<input class="text maxwth" id="title" name="title" type="text" value="" />
							</div>
						</li>
						<li>
							<label>内容：</label>
							<div class="sub_newtribe">
								<div class="titdiv">
									<a class="send_pic" href="#">图片</a>
									<a class="send_link" href="#">链接</a>
								</div>
								<textarea class="maxaera" id="content" name="content" rows="" cols=""></textarea>
							</div>
						</li>
						<li>
							<label></label>
							<div class="sub_newtribe">
								<a href ="javascript:void(0)" onclick ="submit()"><img src="${urlStatic}/images/app/fb.png" width="80" height="32" alt="nav" /></a>
								<a href ="javascript:void(0)" onclick ="window.history.back(-1);"><img src="${urlStatic}/images/app/cancel.png" width="80" height="32" alt="nav" /></a>
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
<script type="text/javascript">
	function submit(){
		if($("#title").val() == ""){
			alert("请输入标题！");
			return;
		}
		if($("#content").val() == ""){
			alert("请输入内容！");
			return;
		}
		$("#releaseForm").submit();
	}
</script>