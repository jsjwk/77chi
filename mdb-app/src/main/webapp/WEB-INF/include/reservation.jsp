<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	function deleteAll(checkName){
		var arr = getCheckArrValue(checkName);
		if(arr == ""){
			alert("请选择至少一项");
		}else{
			if(confirm("确定要删除选中的所有预约吗？")){
				location.href = "/reservation/batchDelete/" + arr + "/" + ${page.curPage};
			}
		}
	}
</script>
<div class="noti_right">
	<div class="top_notiright">
		<input type="checkbox" id="checkAll" onclick="checkAll(this.id, 'checkbox')"/><span>全选</span>
		<a class="deleteall" href="javascript:void(0)" onclick="deleteAll('checkbox')">删除</a>
	</div>
	<ul>
		<c:choose>
			<c:when test="${not empty reservationList}">
				<c:forEach items="${reservationList}" var="reservation">
					<li>
						<input type="checkbox" name="checkbox" value="${reservation.id}" />
						<div class="yq_mian">
							<div class="yq_txt">
								<p>${reservation.content}</p>
								<div class="bot_yq">
									<c:forEach items="${houseList}" var="houseInfo">
										<c:if test="${reservation.houseId == houseInfo.id}">
											房子信息：${houseInfo.area.name} — ${houseInfo.communityName} | ${houseInfo.rent}元/月
										</c:if>
									</c:forEach>
									<span class="time"><script>document.write(getSmpFormatDate(new Date(${reservation.sendTime}), true));</script></span>
								</div>
								<div class="arrow_left"></div>
							</div>
							<div class="btn_yq">
								<c:if test="${reservation.handleStatus == 0}">
									<a class="right_yq" href="/reservation/refuse/${reservation.id}/${page.curPage}"><img class="haveborder" src="${urlStatic}/images/app/jujue2.png" width="66" height="24" alt="拒绝" /></a>
									<a class="right_yq" href="/reservation/accept/${reservation.id}/${page.curPage}"><img class="haveborder" src="${urlStatic}/images/app/jieshou.png" width="66" height="24" alt="接受" /></a>
									<a href="/reservation/rent/${reservation.id}/${page.curPage}">已出租</a>
								</c:if>
								<c:if test="${reservation.handleStatus == 1}">
									已接受|
									<a href="/reservation/rent/${reservation.id}/${page.curPage}">已出租</a>
								</c:if>
								<c:if test="${reservation.handleStatus == 2}">
									已拒绝|
									<a href="/reservation/rent/${reservation.id}/${page.curPage}">已出租</a>
								</c:if>
							</div>
						</div>
						<div class="pic2_div">
							<c:forEach items="${senderList}" var="userInfo">
								<c:if test="${reservation.sender == userInfo.id}">
									<img class="haveborder" src="${userInfo.tinyUrl}" width="42" height="42" alt="${userInfo.userName}" />
									<span>${userInfo.userName}</span>
								</c:if>
							</c:forEach>
						</div>
					</li>
				</c:forEach>
				<li>
					<g:pager curPage="${page.curPage}" total="${page.total}" pageSize="${page.pageSize}" forwardUrl="" />
				</li>
			</c:when>
			<c:otherwise>
				<li><div align="center">暂无预约</div></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>