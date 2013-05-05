<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="g" uri="/WEB-INF/tags-core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>个人中心-我求租-收到的邀请</title>
<%@include file="/WEB-INF/include/taglib.jsp"%>
<link rel="stylesheet"
	href="${urlStatic}/lib/autosuggest/autosuggest_inquisitor.css"
	type="text/css" media="screen" charset="utf-8" />
<script type="text/javascript"
	src="${urlStatic}/lib/autosuggest/jquery.watermarkinput.js"></script>
<script type="text/javascript"
	src="${urlStatic}/lib/autosuggest/bsn.AutoSuggest_2.1.3.js"></script>
<script type="text/javascript">
	function deleteAll(checkName){
		var arr = getCheckArrValue(checkName);
		if(arr == ""){
			alert("请选择至少一项");
		}else{
// 			if(confirm("确定要删除选中的所有消息吗？")){
				$.ajax({
		            type : "post",
		            url : "/invitation/ignore/"+arr,
		            data : 'replyMessage='+$("#replyMessage").val(),
		            success : function(data) {
		            	if(data>0){
			            	alert("删除成功!");
		            	}else{
		            		alert("删除失败!");
		            	}
		                window.location.href = "/invitation/find/receiveInvitation?page="+${page.curPage};
		            }
		      });
// 			}
		}
	}
	function submitReply(id){
		$.ajax({
            type : "post",
            url : "/invitation/reply/"+id,
            data : 'replyMessage='+$("#replyMessage").val(),
            success : function(data) {
            	if(data>0){
	            	alert("回复成功!");
            	}else{
            		alert("回复失败!");
            	}
                window.location.href = "/invitation/find/receiveInvitation?page="+${page.curPage} ;
            }
      });
	}
	function refuse(id){
		$.ajax({
            type : "post",
            url : "/invitation/refuse/"+id,
            data : "",
            success : function(data) {
            	if(data>0){
	            	alert("拒绝成功!");
            	}else{
            		alert("拒绝失败!");
            	}
                window.location.href = "/invitation/find/receiveInvitation?page="+${page.curPage} ;
            }
      });

	}
	function doRefuse(id,toId,houseId){
		$.ajax({
            type : "post",
            url : "/invitation/doRefuse/"+id+"/"+toId+"/"+houseId+"/house",
            data : "",
            success : function(data) {
            	if(data>0){
	            	alert("拒绝成功!");
            	}else{
            		alert("拒绝失败!");
            	}
                window.location.href = "/invitation/find/receiveInvitation?page="+${page.curPage} ;
            }
      });
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
						<dt>
							<a href="/usercenter/infocenter">个人资料</a>
						</dt>
					</dl>
					<dl class="show_dd">
						<dt class="active_dt show_dt">
							<a href="#">我求租</a>
						</dt>
						<dd>
							<a href="/usercenter/housePurpose">房子意向</a>
						</dd>
						<dd>
							<a href="/usercenter/personPurpose">合租伙伴意向</a>
						</dd>
						<dd>
							<a href="/usercenter/attentionHouse">关注和预约</a>
						</dd>
						<dd class="active_dd">
							<a href="/invitation/find/receiveInvitation">收到的邀请</a>
						</dd>
					</dl>
					<dl>
						<dt class="hide_dt">
							<a href="#">我出租</a>
						</dt>
						<dd>
							<a href="/usercenter/rentout/house">我的房子</a>
						</dd>
						<dd>
							<a href="/usercenter/rentout/personPurpose">合租伙伴意向</a>
						</dd>
						<dd>
							<a href="/invitation/rentout/myInvitation">我的邀请</a>
						</dd>
						<dd>
							<a href="/usercenter/rentout/myReservation">收到的预约</a>
						</dd>
					</dl>
					<dl>
						<dt>
							<a href="#">订阅管理</a>
						</dt>
					</dl>
					<dl>
						<dt class="hide_dt">
							<a href="#">站内信</a>
						</dt>
						<dd>
							<a href="/inbox/home">收件箱</a>
						</dd>
						<dd>
							<a href="/outbox/home">发件箱</a>
						</dd>
					</dl>
					<dl>
						<dt><a href="/usercenter/listHosueComment">回访评价</a></dt>
					</dl>
				</div>
				<div class="noti_right">
					<div class="top_notiright">
						<input type="checkbox" id="checkAll" onclick="checkAll(this.id, 'checkbox')" /><span>全选</span> 
						<a class="deleteall" href="javascript:void(0);" onclick="deleteAll('checkbox')">删除</a> 
						<a class="dball" href="#">对比</a>
						<select>
							<option selected="selected">全部</option>
							<option>挨踢男啊</option>
						</select>
						<div class="px">
							<span>排序：</span> 
						    <c:choose>
								<c:when test="${orderBy eq 'rent'}">
									<a class="time_list" href="/invitation/find/receiveInvitation">时间</a><a class="money_list alist" href="/invitation/find/receiveInvitation/house?orderBy=rent">租金</a>
								</c:when>
								<c:otherwise>
									<a class="time_list alist" href="/invitation/find/receiveInvitation">时间</a><a class="money_list" href="/invitation/find/receiveInvitation/house?orderBy=rent">租金</a>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<ul>
						<c:forEach items="${page.datas}" var="invitation">
									<li>
										<input type="checkbox" value="${invitation.id}"	name="checkbox">
										<div class="yq_mian">
											<div class="yq_txt">
												<p>${invitation.content}</p>
												<div class="bot_yq">
													<c:choose>
														<c:when test="${empty invitation.house.address}">
															暂无数据
														</c:when>
														<c:otherwise>
														  	${invitation.house.address} 
														</c:otherwise>
													</c:choose>
													— ${invitation.house.communityName} | ${invitation.house.rent}元/月
													<a href="/house/info/${invitation.house.id}">查看详情 </a>
													<span class="time"><script>document.write(getFormatDateByLong(${invitation.house.updateTime}, "MM-dd hh:mm"));</script></span>
												</div>
												<div class="arrow_left"></div>
											</div>
											<div class="btn_yq">
												<input type="hidden" id="replyMessage" name="replyMessage" value="同意邀请." />
												<c:if test="${invitation.status==0}">
													<a href="javascript:void(0)" onclick="doRefuse(${invitation.id},${invitation.sender.id},${invitation.house.id});" title="该用户不能再向您发出任何邀请">不再接受此人邀请 </a>
													<a class="right_yq" href="javascript:void(0)" onclick="submitReply(${invitation.id});"><img class="haveborder" src="${urlStatic}/images/app/reply.png" width="66" height="24" /></a>
													<a class="right_yq" href="javascript:void(0)" onclick="refuse(${invitation.id});"><img class="haveborder" src="${urlStatic}/images/app/jujue.png" width="66" height="24" title="拒绝此次邀请，在规定时间内该用户不能向您发出邀请" /></a>
												</c:if>
												<c:if test="${invitation.status==1}">
													&nbsp;&nbsp;&nbsp;&nbsp;已接受
												</c:if>
												<c:if test="${invitation.status==2}">
													&nbsp;&nbsp;&nbsp;&nbsp;已拒绝
												</c:if>
											</div>
										</div>
										<div class="pic2_div">
											<img class="haveborder" src="${invitation.sender.tinyUrl}" width="50" height="50" alt="" />
											<span>李梅</span>
										</div>
									</li>
						</c:forEach>
						<li>
							<g:pager curPage="${page.curPage}" total="${page.total}" pageSize="${page.pageSize}" forwardUrl="" />
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>