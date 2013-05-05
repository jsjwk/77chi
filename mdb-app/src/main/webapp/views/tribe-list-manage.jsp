<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>管理部落列表</title>
<%@include file="/WEB-INF/include/taglib.jsp"%>
<script type="text/javascript">
	function search(obj, event) {
		if (event.keyCode == 13) {
			search_form.action="/tribe/searchTribe/"+document.getElementById("tribeName").value;
			document.search_form.submit();
		}
	}
	/**
	 * 初始化输入框的值
	 */
	function initTestArea(obj, param) {
		if (param == 0) {
			obj.value = "";
		} else {
			obj.value = param;
		}
	}
</script>
</head>
<body>
	<div class="warp">
		<div class="content">
			<%@include file="/WEB-INF/include/top-menu.jsp"%>
		<div class="article tribe">
				<h1 class="havebg">
					<span>部落</span>
					<form name="search_form" action="" method="post">
						<input class="search" id="tribeName"
							onkeydown="search(this,event)" onfocus="initTestArea(this,0)"
							onBlur='initTestArea(this,"搜索部落(部落名称或部落介绍)")' type="text"
							value="搜索部落(部落名称或部落介绍)" />
					</form>
				</h1>
				<div class="section first">
					<div class="left_port">
						<h2><span>所有部落</span></h2>
						<div class="sele_div">
							<span>类型：</span>
							<select>
								<option selected="selected">任何人都可以加入</option>
								<option>要申请验证后才能加入</option>
							</select>
							<span>标签：</span>
							<select>
								<option selected="selected">任何人都可以加入</option>
								<option>要申请验证后才能加入</option>
							</select>
						</div>
						<div class="paix_list">
							<a class="all_paixu" href="#">全部</a>
							<a href="#">A</a>
							<a class="active" href="#">B</a>
							<a href="#">C</a>
							<a href="#">D</a>
							<a href="#">E</a>
							<a href="#">F</a>
							<a href="#">G</a>
							<a href="#">H</a>
							<a href="#">I</a>
							<a href="#">J</a>
							<a href="#">K</a>
							<a href="#">L</a>
							<a href="#">M</a>
							<a href="#">N</a>
							<a href="#">O</a>
							<a href="#">P</a>
							<a href="#">Q</a>
							<a href="#">R</a>
							<a href="#">S</a>
							<a href="#">T</a>
							<a href="#">U</a>
							<a href="#">V</a>
							<a href="#">W</a>
							<a href="#">X</a>
							<a href="#">Y</a>
							<a href="#">Z</a>
							<a href="#">数字或符号</a>
						</div>
						<ul>
						<c:forEach items="${tribes}" var="tribe">
							<li>
								<img src="${tribe.headImg.midUrl}" width="50" height="50" />
								<div class="tribe_txt">
									<h2><a href="/tribe/admin/manageTribe/${tribe.id}">${tribe.name}</a></h2>
									 ${tribe.topicNum}条信息
								</div>
							</li>
						</c:forEach>
						</ul>
						 
					</div>
					<div class="right_port">
						<div class="right_main">
							<ul>
								<li class="tribe_back"><a href="/tribe/home">返回部落</a></li>
								<li class="tribe_add"><a href="/tribe/applyCreate">申请创建部落</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>