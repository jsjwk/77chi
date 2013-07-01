<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆 - 77吃零食好吃又便宜</title>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/login.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/inputmail.js"></script>
<script type="text/javascript">
$(function(){
	$("#userMail").changeTips({
		divTip:".on_changes"
	}); 
})
</script>
<script type="text/javascript" src="js/reg.js"></script>
<!--[if IE 6]> 
	<script type="text/javascript" src="js/png.js" ></script>
	
	<script type="text/javascript">
	DD_belatedPNG.fix('');
	</script>
	<![endif]-->
</head>
<body>
<div class="header">
  <h1> <a href="index.html" title="77吃首页">77吃首页</a> </h1>
</div>
<div class="logbox">
  <div class="fl">
  <div class="logintitle"></div>    
    <form action="/login.do" method="POST" id="regform" name="regform">
      <div class="inputBox">
        <div></div>
        <i>邮箱</i>
        <input type="text" id="userMail" name="userMail" placeholder="" autocomplete="off">
        <ul class="on_changes">
          <li email="">请选择邮箱类型</li>
          <li email="@qq.com"></li>
          <li email="@126.com"></li>
          <li email="@163.com"></li>
          <li email="@sina.com"></li>
          <li email="@sohu.com"></li>
          <li email="@yahoo.com"></li>
          <li email="@yahoo.com.cn"></li>
          <li email="@139.com"></li>
          <li email="@21cn.com"></li>
          <li email="@yahoo.com"></li>
          <li email="@gmail.com"></li>
          <li email="@tom.com"></li>
          <li email="@hotmail.com"></li>
          <li email="@yeah.net"></li>
        </ul>
      </div>
      <div class="error"></div>
      <div class="inputBox" style="margin-top:30px;"> <i>密码</i>
        <input type="password" id="pas1" name="pas1" placeholder="">
      </div>
      <div class="error"></div>
      <div class="serText">
        <input name="checkbox" type="checkbox" id="service" checked="checked" />
        <label for="service"><span style="color:#777">&nbsp;记住登陆状态</a></span>
        <a href="#" class="blue" style="position:relative; padding-left:100px; color:#777">忘记密码？</a>
      </div>
      <div class="subBtn">
        <input name="btn_login" type="submit" class="btn_login" value=""/>
        <a href="/preReg.do" class="blue" style="position:relative; padding-left:30px;">没有账号？ 去注册</a>
      </div>
    </form>
  </div>
  <div class="fr">
    <h3>合作网站帐号登录:</h3>
    <ul>
      <li><a href="/preLogin.do?regMethod=weibo" title="新浪微博登录" class="sina"></a></li>
      <li><a href="/preLogin.do?regMethod=qq" title="QQ账号登录" class="qq"></a></li>
      <li><a href="/preLogin.do?regMethod=taobao" title="淘宝网登录" class="taobao"></a></li>
    </ul>
  </div>
</div>
</body>
</html>