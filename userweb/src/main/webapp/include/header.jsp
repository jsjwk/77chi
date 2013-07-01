<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jstl/fn"%>

<div class="tnavBox">
	<h1><a href="index.html">77吃，美味的都在这里！</a></h1>
	
	<div class="navBox">
	
	<c:if test="${user_session_id eq null }">
	<dl class="reg">
	<dt><a href="/preReg.do" title="注册" class="gray">注册</a></dt>
	</dl>
	<dl class="login">
	<dt>登录</dt>
	<dd>
		<form action="/login.do" method="POST">
		<div class="unme"><label for="nav-username"></label><i>邮箱</i><input type="text" id="nav-username" name="userMail" placeholder="邮箱"></div>
		<div class="pswd"><label for="nav-password"></label><i>密码</i><input type="password" id="nav-password" name="pas1" placeholder="密码"></div>
		<div class="submit zoom">
		<input name="" type="submit" value="登录" class="btn btn_login" />
		<div class="u-chk zoom"><input class="chk" type="checkbox" name="remember" id="nav-remember" value="记住我"><label for="nav-remember">记住我</label><span>|&nbsp;</span><a href="/getpasswd/">忘记密码？</a></div>
		</div>
		</form>
		<div class="sites"><p>使用合作网站账号登录：</p><div class="clr"><a class="weibo" href="/preLogin.do?regMethod=weibo">新浪微博</a><a class="qqsite" href="/preLogin.do?regMethod=qq">QQ</a><a class="taobao" href="/preLogin.do?regMethod=taobao">淘宝</a></div><br />
		</div>
    </dd>
	</dl>
	</c:if>
	
	<c:if test="${user_session_id ne null }">
	<dl class="loginIn">
	<dt><img src="images/photo02.jpg" /><div>${user_session_id.nickName}</div></dt>
	<dd>
    <a href="#" class="msg"><span>1</span>系统消息</a>
    <a href="#" class="eml"><span>1</span>站内信</a>
    <a href="#" class="set">账号设置</a>
    <a href="/logout.do" class="exit">退出</a>
    </dd>
	</dl>
	</c:if>
	
	
	
    
    <dl class="shopCart">
	<dt><span>0</span>购物车<i></i></dt>
	<dd>
    <div class="noshop">购物车中还没有商品，赶紧选购吧！</div>
</dd>
	</dl>
	
</div>

</div>