<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>Welcome</title>
  <script type="text/javascript" src="${urlStatic}/js/renren.js"></script>
</head>
<body>
  <script type="text/javascript">
  /*使用js sdk 实现弹层授权*/
	  var uiOpts = {
		  url : "http://graph.renren.com/oauth/authorize",/*用户授权之后页面跳转的地址*/
		  display : "iframe", /*弹层方式*/
		  style:{/*设置弹层的位置和尺寸*/
			  top:50,
			  left:200,
			  width:450,
			  height:350
		  },
		  params : {"response_type":"token","client_id":"201518"/*开发者的appId*/,
			  		"scope":"photo_upload" /*需要用户授权的具体参数*/
			       },/*详细的参数*/
		  onSuccess: function(r){
		    top.location = "http://apps.renren.com/songdemo";/*确定时进行的操作*/
		  },
		  onFailure: function(r){} /*取消时进行的操作*/
	  };
	  Renren.ui(uiOpts);
  </script>

</body>
</html>