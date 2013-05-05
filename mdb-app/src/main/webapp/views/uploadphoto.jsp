<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>上传图片</title>
<style type="text/css">
body{
	margin:0;
	padding:0;
}
.photo{
	width:500px;
	height:300px;
	text-align:left;
	display:none;
	margin:10px;
	background-color:#ccc;
	padding:5px;
	padding-top:80px;
}
</style>
<script type="text/javascript">
function change(id){
	if(id=="local"){
		var showdiv=document.getElementById("local");
		var hiddendiv=document.getElementById("url");
	}else{
		var showdiv=document.getElementById("url");
		var hiddendiv=document.getElementById("local");
	}
	showdiv.style.display="block";
	hiddendiv.style.display="none";
	document.getElementById("flag").value=id;
}
</script>
</head>
<body>
<form action="<%=request.getContextPath() %>/uploadphoto/upload" method="post">
<input type="hidden" value="local" id="flag" name="flag"/>
<a href="javascript:change('local')">本地上传</a>&nbsp;<a href="javascript:change('url')">网络上传</a><br/>
<div id="local" class="photo">
使用UploadLocalImg方法，开发者可以将服务器端的图片上传至人人网用户的相册中<br/>
假设将以下测试图片放入服务器指定位置，并输入文件的绝对路径(请开发者自行更改)<br/>
服务器图片：<img src="img/test.jpg"/>&nbsp;<input type="text" name="filename" value="C:\test.jpg"/><br/>
<b style="color:#F00">请确保得到文件的绝对路径</b>
</div>
<div id="url" class="photo">
使用UploadURLImg方法，可以直接将网络中的图片上传到人人网用户的相册中<br/>
网络图片：<input type="text" name="fileurl"/>
</div>
<input type="submit" value="上传"/>
</form>
</body>
</html>