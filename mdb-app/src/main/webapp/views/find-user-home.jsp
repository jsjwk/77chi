<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="g" uri="/WEB-INF/tags-core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>找人主页</title>
<%@include file="/WEB-INF/include/taglib.jsp"%>
<script type="text/javascript">
	var index = 0;
	var arr = new Array();
	$(document).ready(function(){
		<c:forEach items="${userList}" var="user">
			arr[index] = "${user.id}";
			index++;
		</c:forEach>
		if(arr.length > 0){
			$("#" + arr[0]).show();
		}
		
		$("#topli_user").attr("class", "active");
	});
	
	function showUserInfo(id, a){
		$("#recipientId").val(id);
		for(var i = 0; i < arr.length; i++){
			$("#" + arr[i]).hide();
		}
		$("#" + id).show();
		a.attr("class", "show_this");
		document.getElementById("#recipientId").value=id;
	}
	
	function share(){
		var resourceUrl = "http://${appRenren}";
		var pic = " ";
		var title = "分享找合租伙伴意向";
		var description = " ";
		var message = "我在帮你租上发布了一条出租信息，要在“${housePurpose.houseWhereName}”附近出租“${housePurpose.rentInfo}元/月”左右的房子，想找一位“"
				+ "<c:if test='${userPurpose.sex == 1}'>男</c:if><c:if test='${userPurpose.sex == 0}'>女</c:if><c:if test='${userPurpose.sex == 2}'>不限</c:if>”、"
				+ "“<c:choose><c:when test='${userPurpose.professionPartner == 1}'>普通职业</c:when><c:when test='${userPurpose.professionPartner == 2}'>学生</c:when><c:when test='${userPurpose.professionPartner == 3}'>自由职业</c:when><c:otherwise>其他</c:otherwise></c:choose>”、“"
				+ "<c:if test='${userPurpose.isSmokingPartner == 1}'>抽烟</c:if><c:if test='${userPurpose.isSmokingPartner == 0}'>不抽烟</c:if><c:if test='${userPurpose.isKeepingPetsPartner == 1}'>养宠物</c:if><c:if test='${userPurpose.isKeepingPetsPartner == 0}'>不养宠物</c:if>”的合租伙伴，希望大家来帮我推荐！";
		shareClick(resourceUrl, pic, title, description, message);
	}
	
	function submitInvitation(){
		$.ajax({
            type : "post",
            url : "/invitation/save/"+$("#recipientId").val(),
            data : "",
            success : function(data) {
            	if(data>0){
                  alert("邀请成功!") ;
            	}else if(data==-1){
                  alert("缺少参数,邀请失败!") ;
            	}else if(data==-2){
                  alert("已经邀请过了o(╯□╰)o!") ;
            	}else{
                  alert("邀请失败!") ;
            	}
            }
      });
	}
	
	function inboxFormSub(userId){
		var param = $("#inbox_form_" + userId).serialize();
		$.ajax({
			type : "post",
			url : "/inbox/send",
			dataType : "text",
			data : param,
			success : function(data) {
				if(data == "success"){
					alert("发送成功");
					window.location.reload();
				}
			}
		});
	}
</script>
</head>
<body>
	<div class="warp">
		<div class="content">
			<%@include file="/WEB-INF/include/top-menu.jsp"%>
			
			<div class="article home">
				<div class="section first gray_14">
					<p>
					您<%-- 要在<span class="orange_14">${housePurpose.houseWhereName}</span>附近出租 <span class="orange_14">${housePurpose.rentInfo}</span>元/月
					<span class="orange_14">${housePurpose.roomNum}</span>居的房子， --%>想找性别
						<c:if test="${userPurpose.sex == 1}"><span class="orange_14">男</span></c:if>
						<c:if test="${userPurpose.sex == 0}"><span class="orange_14">女</span></c:if>
						<c:if test="${userPurpose.sex == 2}"><span class="orange_14">不限</span></c:if>
					<!-- 年龄 --> 
					,职业
					<c:choose>
						<c:when test='${userPurpose.professionPartner == 1}'><span class="orange_14">普通职业</span></c:when>
						<c:when test='${userPurpose.professionPartner == 2}'><span class="orange_14">学生</span></c:when>
						<c:when test='${userPurpose.professionPartner == 3}'><span class="orange_14">自由职业</span></c:when>
						<c:otherwise>其他</c:otherwise>
					</c:choose>
					<c:if test='${userPurpose.isSmokingPartner == 1}'><span class="orange_14">抽烟</span></c:if>
					<c:if test='${userPurpose.isSmokingPartner == 0}'><span class="orange_14">不抽烟</span></c:if>
					<c:if test='${userPurpose.isKeepingPetsPartner == 1}'><span class="orange_14">养宠物</span></c:if>
					<c:if test='${userPurpose.isKeepingPetsPartner == 0}'><span class="orange_14">不养宠物</span></c:if>
					的合租伙伴。
					</p>
					<a class="share gray9_12" href="javascript:void(0)" onclick="share();">分享</a>
					<a class="changemind gray9_12" href="/usercenter/personPurpose">修改意向</a>
				</div>
				<h1 class="havebg height28">
					<span>符合您意向的推荐（${page.total}条）</span>
					<div class="page_change">
						<g:pager curPage="${page.curPage}" total="${page.total}" pageSize="${page.pageSize}" forwardUrl="" />
					</div>
				</h1>
				<div class="section fourth">
					<c:forEach items="${userList}" var="user">
						<c:forEach items="${purposeList}" var="purpose">
							<c:if test="${user.id == purpose.userInfoId}">
								<div class="anybody" id="${user.id}" style="display: none">
									<div class="user_pic"><img src="${user.mainUrl}" width="230" height="230" alt="user_img" /></div>
									<div class="user_txt">
										<h2>${user.userName}</h2>
										${user.age}岁<br />
										毕业院校：<c:forEach items="${user.universityInfo}" var="un">
													${un.name}&nbsp;&nbsp;
												</c:forEach><br />
										所属公司：<c:forEach items="${user.workHistory}" var="wk">
													${wk.companyName}&nbsp;&nbsp;
												</c:forEach><br />
										职业：<c:choose>
												<c:when test="${purpose.professionPartner == 1}">普通职业</c:when>
												<c:when test="${purpose.professionPartner == 2}">学生</c:when>
												<c:when test="${purpose.professionPartner == 3}">自由职业</c:when>
												<c:otherwise>其他</c:otherwise>
											</c:choose><br />
										习惯：<c:if test="${purpose.isSmokingPartner == 1}"><img src="${urlStatic}/images/app/chouyan.png"/></c:if>
											 <c:if test="${purpose.isKeepingPetsPartner == 1}"><img src="${urlStatic}/images/app/chongwu.png"/></c:if><br />
										期望入住时间：${purpose.checkInTime}<br />
										<a class="dialog_inbox" href="#all_inbox_${user.id}">
											<img src="${urlStatic}/images/app/find_msg.png" width="90" height="32" alt="message to" /></a>
									</div>
								</div>
								<!-- 发送站内信弹出层 -->
								<div style="display:none;">
									<div id="all_inbox_${user.id}">
										<form id="inbox_form_${user.id}">
											<input type="hidden" id="recipient" name="recipient" value="${user.id}"/>
											<table>
												<tr>
													<td>标题：</td>
													<td><input type="text" id="title" name="title" size="30"/></td>
												</tr>
												<tr>
													<td>内容：</td>
													<td><textarea rows="5" cols="40" name="content" id="content"></textarea></td>
												</tr>
												<tr>
													<td colspan="2"><input type="button" onclick="inboxFormSub(${user.id});" value="提交"/>
												</tr>
											</table>
										</form>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</c:forEach>
					<div class=anyhouse>
						<h2>TA的意向区域：</h2>
						<a class="yx_adr" href="/search/house?areaCode=${purpose.areaCode}">${purpose.houseWhereName}</a>
						<div class="sub_hz"><a href ="javascript:void(0);" onclick ="submitInvitation();"><img src="${urlStatic}/images/app/find_hz.png" width="90" height="32" alt="message to" /></a></div>
					</div>
					<div class="otherbody">
						<c:forEach items="${userList}" var="user" varStatus="status">
							<c:if test ="${status.index==0}" ><input type="hidden" id="recipientId" name="recipientId" value="${user.id}" /></c:if>
							<a href="javascript:void(0)" onclick="showUserInfo(${user.id}, this)">
								<img src="${user.headUrl}" alt="${user.userName}" /></a>
						</c:forEach>
					</div>
				</div>
				<h1 class="havebg height28">
					<span>这些部落可能会有帮助：</span>
				</h1>
				<div class="section fifth">
					<ul>
						<li><a href="#">长安大学校园周边部落长安大学校园周边部落</a></li>
						<li><a href="#">西安大学校园周边部落</a> </li>
						<li><a href="#">长安大学校园周边部落</a></li>
						<li><a href="#">西安大学校园周边部落</a> </li>
						<li><a href="#">长安大学校园周边部落</a></li>
						<li><a href="#">西安大学校园周边部落</a> </li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	
	
	
</body>
</html>