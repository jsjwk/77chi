<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>人人分享</title>
</head>
<body>
<hr/>
js传递信息的分享
<hr/>
分享资源的url：<input type="text" value="http://v.youku.com/v_show/id_XMzM1OTIwNzE2.html" id="resourceUrl"/><br/>
分享的主题图片url：<input type="text" value="http://t1.baidu.com/it/u=2132217041,2521165880&fm=9&gp=0.jpg" id="pic"/><br/>
分享的标题：<input type="text" value="路飞" id="title"/><br/>
分享的详细内容：<input type="text" value="海贼王我当定了！！！" id="description" /><br/>
分享的默认评论：<input type="text" value="这个不错~" id="message" /><br/>
 <!-- 分享部件的JavaScript代码 -->
 <br/>
<script type="text/javascript" src="http://widget.renren.com/js/rrshare.js"></script>

分享按钮样式一：<br/>
<a name="xn_share" onclick="shareClick()" type="button_large" href="javascript:;"></a>
<br/>
分享按钮样式二：<br/>
<a name="xn_share" onclick="shareClick()" type="icon" href="javascript:;"></a>

<script type="text/javascript">
	function shareClick() {
		var resourceUrl=document.getElementById("resourceUrl").value;
		var pic=document.getElementById("pic").value;
		var title=document.getElementById("title").value;
		var description=document.getElementById("description").value;
		var message=document.getElementById("message").value;
		var rrShareParam = {
			resourceUrl : resourceUrl,	/*分享的资源Url*/
			pic : pic,		/*分享的主题图片Url*/
			title : title,		/*分享的标题*/
			description : description,	/*分享的详细描述*/
			message : message		/*分享的标题*/
		};
		rrShareOnclick(rrShareParam);
	}
</script>
<br/><br/>
<hr/>
参数传递信息的分享，可以自定义分享图片
<hr/>
<!--在后面追加四个参数即可：url、title、description、pic、message(默认评论)；注意参数的value必须是按照utf8格式urlencode之后的-->
<a target='_blank' href="http://widget.renren.com/dialog/share?url=http%3A%2F%2Fwww.renren.com&title=%e5%bc%b1%e5%a5%b3%e5%ad%90%e6%b1%82%e5%8a%a9%e5%a6%82%e4%bd%95%e6%8a%8a%e5%ae%85%e7%94%b7%e5%be%80%e5%b1%8b%e5%a4%96%e6%8b%bd%ef%bc%81%ef%bc%81%ef%bc%81&pic=http%3A%2F%2Fuc.56we.com%2fdata%2favatar%2f000%2f02%2f89%2f47_avatar_middle.jpg&description=%E6%B5%8B%E8%AF%95des&message=12312312" >分享到人人</a>
</body>
</html>