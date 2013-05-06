<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>收件箱消息明细</title>
<%@include file="/WEB-INF/include/taglib.jsp"%>
<script type="text/javascript">
	function deleteOne(id){
		if(confirm("确定要删除这条消息吗？")){
			location.href = "/inbox/delete/" + id + "/" + ${curPage};
		}
	}
</script>
</head>
<body>
	<div class="warp">
		<div class="content">
			<%@include file="/WEB-INF/include/top-menu.jsp"%>
			<div class="article notice">
				<div class="noti_left">
					<div class="top_notileft">个人中心</div>
					<dl>
						<dt><a href="/usercenter/infocenter">个人资料</a></dt>
					</dl>
					<dl>
						<dt class="hide_dt"><a href="#">我求租</a></dt>
						<dd><a href="/usercenter/housePurpose">房子意向</a></dd>
						<dd><a href="/usercenter/personPurpose">合租伙伴意向</a></dd>
						<dd><a href="/usercenter/attentionHouse">关注和预约</a></dd>
						<dd><a href="#">收到的邀请</a></dd>
					</dl>
					<dl>
						<dt class="hide_dt"><a href="#">我出租</a></dt>
						<dd><a href="/usercenter/rentout/house">我的房子</a></dd>
						<dd><a href="/usercenter/rentout/personPurpose">合租伙伴意向</a></dd>
						<dd><a href="#">我的邀请</a></dd>
						<dd><a href="/usercenter/rentout/myReservation">收到的预约</a></dd>
					</dl>
					<dl>
						<dt><a href="#">订阅管理</a></dt>
					</dl>
					<dl class="show_dd">
						<dt class="active_dt show_dt"><a href="#">站内信</a></dt>
						<dd class="active_dd"><a href="/inbox/home">收件箱</a></dd>
						<dd><a href="/outbox/home">发件箱</a></dd>
					</dl>
					<dl>
						<dt><a href="/usercenter/listHosueComment">回访评价</a></dt>
					</dl>
				</div>
				<div class="noti_right">
					<div class="top_notiright">
						<a class="backall" href="/inbox/home?page=${curPage}">返回</a>
						<a class="deleteall" href="javascript:void(0)" onclick="deleteOne(${inbox.id});">删除</a>
						<!-- <div class="noti_num"><a href="#">上一封</a>|<a href="#">下一封</a></div> -->
					</div>
					<div class="noti_xiangx">
						<div class="pic_fjr"><img src="${user.tinyUrl}" alt="" />${user.userName}</div>
						<div class="txt_fjr">
							<div class="txt_title">【标题】：${inbox.title}<span><script>document.write(getSmpFormatDate(new Date(${inbox.sendTime}), true));</script></span></div>
							<div class="txt_mian">
								${inbox.content}
							</div>
							<!-- <div class="txt_bottom">美女合租</div> -->
						</div>
					</div>
					<!-- <div class="noti_huif">
						<div class="huif_title">回复美女合租：</div>
						<p><input class="text" type="text" value="标题：" /></p>
						<p><textarea rows="" cols="">内容：</textarea></p>
						<p><a href="/inbox/reply/${inbox.id}/${curPage}"><img src="../../../images/app/fasong.png" width="66" height="24" alt="" /></a></p>
					</div> -->
				</div>
			</div>
		</div>
	</div>
</body>
</html>