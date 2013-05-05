<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="g" uri="/WEB-INF/tags-core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>部落详细页</title>
<%@include file="/WEB-INF/include/taglib.jsp"%>
  <script type="text/javascript">
	function showReleaseDiv(){
		$("#releaseDiv").show();
	}
	
	function hideReleaseDiv(){
		$("#releaseDiv").hide();
	}
	
	function releaseSave(){
		$("#releaseForm").submit();
	}
	
	function sendRequest(isSendToMany, toId) {
	      var params = {"accept_url":"http://apps.renren.com/songdemo","accept_label":"接受邀请"};
	      var style = null;
	      if (isSendToMany) {
	          params["actiontext"] = "邀请好友来玩吧";
	          style={
	        		  "top":0,
	        		  "left":20,
	        		  "width":500,
	        		  "height":520
	          }
	      }
	      else {
	          params["to"] = toId;
			  style = {
				  "width":500,  
				  "height":350  
			  };
	      }
		  var uiOpts = {
			  url : "request",
			  display : "iframe",
			  params : params,
			  style : style,/*设置弹层的位置和尺寸*/
			  onSuccess: function(r){},
			  onFailure: function(r){} 
		  };
		  Renren.ui(uiOpts);
	    };

Renren.init({appId:"201518"});
</script>  
</head>
<body>
	<div class="warp">
		<div class="content">
			<%@include file="/WEB-INF/include/top-menu.jsp"%>
			<div class="article tribe">
				<h1 class="height60">
					<span>${tribe.name}</span>
					<a href="/tribe/home">返回部落首页&gt;&gt;</a>
				</h1>
				<div class="tribe_info">
					<div class="inifo_pic">
						<%-- <img src="${urlStatic}/images/app/ceshi/tribe_logo100.jpg" width="100" height="100" alt="tribe logo" /> --%>
					</div>
					<div class="info_txt">
						${tribe.description}
						<a href="#">▼展开</a>
					</div>
					<div class="info_sub">
						<c:if test="${isMemberTag == 1}">
							<a href="javascript:void(0)" onclick="excute('/tribe/admin/quitTribe/${tribe.id}')"><img src="${urlStatic}/images/app/tribe_join.png" width="90" height="32" alt="tribe logo" /></a>
						</c:if>
						<c:if test="${isMemberTag == 0}">
							<a href="#"><img src="${urlStatic}/images/app/tribe_nojoin.png" width="90" height="32" alt="tribe logo" /></a>
							<!-- <a href="javascript:void(0)" onclick="">加入部落</a> -->
						</c:if>
						<a href="javascript:sendRequest(true)">邀请好友</a>
					</div>
				</div>
				<div class="title">
					<a class="active" href="javascript:void(0)" onclick="location.href='/tribe/detail/${tribe.id}'">全部</a>
					<a href="javascript:void(0)" onclick="location.href='/tribe/detail/${tribe.id}?type=1'">出租</a>
					<a class="last" href="javascript:void(0)" onclick="location.href='/tribe/detail/${tribe.id}?type=2'">求租</a>
				</div>
					
				<div class="section newtribe topnone">
					<div class="list">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td class="underline" width="444">话题</td>
								<td class="underline" width="136">发布者</td>
								<td class="underline">回复</td>
								<td class="underline" width="104">最后回复时间</td>
							</tr>
							<c:choose>
								<c:when test="${not empty page.datas}">
									<c:forEach items="${page.datas}" var="tribeTopic">
										<tr>
											<td>
												<span class="gray6_12">
													<c:if test="${tribeTopic.type == 1}">[出租]</c:if>
													<c:if test="${tribeTopic.type == 2}">[求租]</c:if>
												</span>
												<a href='/tribe/topic/topicDetial/${tribeTopic.id}?tribeName=${tribe.name}'>
													${tribeTopic.title}
												</a>
											</td>
												<td>
													<c:forEach items="${userList}" var="user">
														<c:if test="${user.id == tribeTopic.releaser}">
															${user.userName}
														</c:if>
													</c:forEach>
												</td>
											<td>${tribeTopic.replyNum}</td>
											<td><script>document.write(getFormatDateByLong(${tribeTopic.lastReplyTime}, "MM-dd hh:mm"));</script></td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="4" align="center"><span class="gray6_12">暂无话题</span></td>
									</tr>
								</c:otherwise>
							</c:choose>
						</table>
						<div class="info_page">
						<li>
							<g:pager curPage="${page.curPage}" total="${page.total}" pageSize="${page.pageSize}" forwardUrl="" />
						</li>
							<!-- 当前页显示1-6个/共178个 
							<div class="page_num">
								<span>1</span>
								<a href="#">2</a>
								<a href="#">3</a>
								<a href="#">4</a>
								<a href="#">5</a>
								<a href="#">下一页</a>
								<a href="#">最后一页</a> -->
							</div>
						</div>
					</div>
				</div>
					<a href="javascript:void(0)" onclick="showReleaseDiv();">发布</a>
					<div style="display:none" id="releaseDiv">
		<form action="/tribe/topic/release/save" method="post" id="releaseForm">
			<input type="hidden" name="tribeId" value="${tribe.id}"/>
			<table width="98%" border="0">
				<tr>
					<td colspan="3"><b>发布信息</b></td>
				</tr>
				<tr>
					<td>类型：</td>
					<td>
						<select name="type">
							<option value="1">出租</option>
							<option value="2">求租</option>
						</select>
					</td>
					<td rowspan="4">
						图
					</td>
				</tr>
				<tr>
					<td>标题：</td>
					<td>
						<input type="text" name="title" size="50"/>
					</td>
				</tr>
				<tr>
					<td>内容：</td>
					<td>
						<textarea name="content" cols="50" rows="8"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<a href="javascript:void(0)" onclick="releaseSave();">发布</a>&nbsp;&nbsp;
						<a href="javascript:void(0)" onclick="hideReleaseDiv();">取消</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
			</div>
		</div>
	</div>
</body>
</html>