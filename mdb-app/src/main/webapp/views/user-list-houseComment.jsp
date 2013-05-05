<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="g" uri="/WEB-INF/tags-core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>个人中心-回访评价</title>
<%@include file="/WEB-INF/include/taglib.jsp"%>
<link rel="stylesheet" href="${urlStatic}/lib/autosuggest/autosuggest_inquisitor.css" type="text/css" media="screen" charset="utf-8" />
<script type="text/javascript" src="${urlStatic}/lib/autosuggest/jquery.watermarkinput.js"></script>
<script type="text/javascript" src="${urlStatic}/lib/autosuggest/bsn.AutoSuggest_2.1.3.js"></script>
<script type="text/javascript">
	function deleteAll(checkName){
		var arr = getCheckArrValue(checkName);
		if(arr == ""){
			alert("请选择至少一项");
		}else{
				$.ajax({
		            type : "post",
		            url : "/reservation/delete/"+arr,
		            data : '',
		            success : function(data) {
		            	if(data>0){
			            	alert("删除成功!");
		            	}else{
		            		alert("删除失败!");
		            	}
		                window.location.href = "/usercenter/listHosueComment?page="+${page.curPage};
		            }
		      });
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
						<dd ><a href="/usercenter/personPurpose">合租伙伴意向</a></dd>
						<dd><a href="/usercenter/attentionHouse">关注和预约</a></dd>
						<dd><a href="/invitation/find/receiveInvitation">收到的邀请</a></dd>
					</dl>
					<dl>
						<dt class="hide_dt"><a href="#">我出租</a></dt>
						<dd><a href="/usercenter/rentout/house">我的房子</a></dd>
						<dd><a href="/usercenter/rentout/personPurpose">合租伙伴意向</a></dd>
						<dd><a href="/invitation/rentout/myInvitation">我的邀请</a></dd>
						<dd><a href="/usercenter/rentout/myReservation">收到的预约</a></dd>
					</dl>
					<dl>
						<dt><a href="#">订阅管理</a></dt>
					</dl>
					<dl>
						<dt class="hide_dt"><a href="#">站内信</a></dt>
						<dd><a href="/inbox/home">收件箱</a></dd>
						<dd><a href="/outbox/home">发件箱</a></dd>
					</dl>
					<dl>
						<dt class="active_dt"><a href="/usercenter/listHosueComment">回访评价</a></dt>
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
							<a class="time_list alist" href="#">时间</a><a class="money_list" href="#">租金</a>
						</div>
					</div>
					<ul>
					  <c:forEach items="${page.datas}" var="house">
						<li>
							<input type="checkbox" value="${house.reservation.id}"	name="checkbox">
							<div class="pic_div"><img class="haveborder" src="${urlStatic}/images/app/ceshi/house_pic.jpg" width="107" height="78" alt="" /></div>
							<div class="noti_txt">
								<p class="noti_time"><script>document.write(getFormatDateByLong(${house.updateTime}, "MM-dd hh:mm"));</script></span></p>
								<p>${house.houseTitle}</p>
								<p class="noti_tit">
									<c:choose>
										 <c:when test="${empty house.address}">
											 暂无数据
										 </c:when>
									 	 <c:otherwise>
										     ${house.address} 
										 </c:otherwise>
									</c:choose>								
 									— ${house.communityName}  | ${house.roomType} | 
 									可入住时间：
 									  <c:choose>
										 <c:when test="${empty house.checkInTime}">
											 暂无数据
										 </c:when>
									 	 <c:otherwise>
										     ${house.checkInTime} 
										 </c:otherwise>
									</c:choose></p> 
								<p class="noti_tit"><font class="orange_14">￥${house.rent}</font>元/月</p>
							</div>
							<div class="pic2_div">
								<p><span>已有${house.commentNum}条评价</span></p>
								<a href="/house/info/${house.id}"><img src="${urlStatic}/images/app/qwpj.png" width="66" height="24" alt="" /></a>
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