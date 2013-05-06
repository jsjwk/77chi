<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>部落详细页</title>
<%@include file="/WEB-INF/include/taglib.jsp"%>
<script>
		function replyTopic(){
			document.replyForm.submit();
		}
		function sharePurpose(){
			var resourceUrl = "http://${appRenren}";
			var pic = " ";
			var title = "租房话题";
			var description = " ";
			var message = "大家都在讨论租房话题:${tribeTopic.title},你也来发表下你的看法吧?"/* +window.location.href */;
			shareClick(resourceUrl, pic, title, description, message);
		}
</script>
</head>
<body>
	<div class="warp">
		<div class="content">
			<%@include file="/WEB-INF/include/top-menu.jsp"%>
			<div class="article tribe">
				<h1 class="height60">
					<span>${tribeName}<font> &gt;&gt; ${tribeTopic.title}</font></span>
					<a href="/tribe/home">返回部落首页&gt;&gt;</a>
				</h1>
				<div class="tribe_info">
					<div class="inifo_picmin">
						<%-- <img src="${urlStatic}/images/app/ceshi/user_pic.jpg" alt="user img" /> --%>
						<img src="${tribeTopic.releaser.headUrl}"/>
						<a href="#">${tribeTopic.releaser.userName}</a>
					</div>
					<div class="info_txtmax">
						<h2>${tribeTopic.title}</h2>
						<!-- <p>2012年7月16日  20:16：23 </p> -->
						<p><script>document.write(getFormatDateByLong(${tribeTopic.lastReplyTime}, "yyyy年M月dd日 hh:mm:ss"));</script></p>
						<p>${tribeTopic.content}</p>
						<div class="share_right">
							<a class="share gray9_12" href="javascript:void(0)" onclick="sharePurpose();">分享</a>|
							<a href="/tribe/topic/accusation/${tribeTopic.id}">举报</a>
						</div>
					</div>
				</div>
				<div class="section newtribe topnone">
					<div class="list">
						<ul>
							<c:forEach items="${topicReplies}" var="topicReply" >
								<li>
									<div class="inifo_picmin">
										<img src="${topicReply.releaser.headUrl}"/>
									</div>
									<div class="info_pl">
										<h2> ${topicReply.releaser.userName} 
											<span><script>document.write(getFormatDateByLong(${topicReply.releaseTime}, "yyyy年M月dd日 hh:mm:ss"));</script></span>
										</h2>
										<p>${topicReply.content} </p>
									</div>
									<div class="btn_hf">
										<a href="#">回复</a>|
										<a href="#">举报</a>
									</div>
								</li>
							</c:forEach>
							<li class="noline">
								<div class="inifo_picmin">
									<img src="${urlStatic}/images/app/ceshi/user_pic.jpg" alt="user img" />
								</div>
								<form name="replyForm" method="post" action="/tribe/reply/topic?topicId=${tribeTopic.id}">
								<div class="info_pl">
									<textarea rows="" cols="" name="replyContent" >最多300个汉字</textarea>
									<p><a href ="javascript:void(0)" onclick ="replyTopic();"><img src="${urlStatic}/images/app/reply.png" width="66" height="24" alt="reply" /></a></p>
								</div>
								</form>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>