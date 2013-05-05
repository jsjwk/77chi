<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script language="javascript">
</script>
<div class="header">
	<div class="logo"><a href="javascript:void(0)"><img src="${urlStatic}/images/app/renren_logo.png" width="127" height="47" alt="logo" /></a></div>
	<div class="nav">
		<ul>
			<li id="topli_house"><a href="/purpose/house/search"><img src="${urlStatic}/images/app/renren_nav1.png" width="65" height="17" alt="nav" /></a></li>
			<li id="topli_user"><a href="/purpose/user/search"><img src="${urlStatic}/images/app/renren_nav2.png" width="65" height="17" alt="nav" /></a></li>
			<li id="topli_tribe"><a href="/tribe/home"><img src="${urlStatic}/images/app/renren_nav3.png" width="65" height="17" alt="nav" /></a></li>
			<li id="topli_search"><a href="/search"><img src="${urlStatic}/images/app/renren_nav4.png" width="65" height="17" alt="nav" /></a></li>
		</ul>
	</div>
	<div class="setting"><a href="/usercenter/infocenter"><img src="${urlStatic}/images/app/renren_setico.png" width="24" height="20" alt="set" /></a></div>
	<div class="message orange_10" id="topTotalCount">
		<c:if test="${totalCount > 0 && totalCount <= 99}">${totalCount}</c:if>
		<c:if test="${totalCount > 99}">99+</c:if>
	</div>
	<div class="poop_all">
		<div class="poop_tit">
			<span>查看所有消息</span>
			<a class="poopback" href="javascript:void(0);">返回</a>
		</div>
		<div class="poop_mian">
			<ul>
				<li id="li_first">
							<c:if test ="${systemMessageCount > 0}" > 
					 			<div id="xitong" class="pooplist poopback2" onclick="xitongList('${urlStatic}')">
								 <div class="poop_pic"><img src="${urlStatic}/images/app/ico_xiaoxi.png" width="18" height="19" /></div>
									 <div class="poop_txt">
										 <p>您收到 <span id="systemMessageCount1">${systemMessageCount}</span>条系统消息</p>
										 <p>管理员向您发送了 <span id="systemMessageCount2">${systemMessageCount}</span>条提醒</p>
									</div>
								</div>
							</c:if>
							<c:if test ="${inboxMessage.total > 0}" > 
								<div id="xinxi" class="pooplist poopback2" onclick="xinxiList()">
									<div class="poop_pic"><img src="${urlStatic}/images/app/ico_xinxi.png" width="18" height="19" alt="set" /></div>
									<div class="poop_txt">
										<p>有 <span id="inboxCount">${inboxMessage.total}</span>个人给您发了消息 </p>
										<p id="inboxUserInfo">
											<c:forEach items="${inboxMessage.senderList}" var="sender" varStatus="status">
												 <c:if test="${status.index  < 5}">
													<img src="${sender.tinyUrl}" width="30" height="30" title="${sender.userName}" border="5" />
												 </c:if>  
											</c:forEach>
										</p>
									</div>
								</div>
							</c:if>
							<c:if test ="${reservationMessage.total > 0}" > 
								<div id="kf" class="pooplist poopback2" onclick="yuyueList()">
									<div class="poop_pic"><img src="${urlStatic}/images/app/ico_kf.png" width="18" height="19" alt="set" /></div>
									<div class="poop_txt">
										<p>您有 <span id="yuyueCount">${reservationMessage.total}</span>条看房请求，来自 <code id="yuyueUserNames">
												<!-- 最多展示3个人名 -->
												<c:forEach items="${reservationMessage.senderList}" var="sender" varStatus="status">
													<c:if test ="${status.index < 3}" > 
														${sender.userName}
													</c:if>
													<c:if test ="${status.index == 3}" > 
														等
													</c:if>
												</c:forEach></code></p>
										<p id="yuyueUserHeadImgs">
											<c:forEach items="${reservationMessage.senderList}" var="sender" varStatus="status">
												<!-- 最多展示5个头像 -->
												<c:if test ="${status.index < 5}" > 
													<img src="${sender.tinyUrl}" width="30" height="30" title="${sender.userName}" />
												</c:if>
											</c:forEach>
										</p>
									</div>
								</div>
							</c:if>
							<c:if test ="${invitationMessage.total > 0}" > 
								<div id="yq" class="pooplist poopback2" onclick="yaoqingList()">
									<div class="poop_pic"><img src="${urlStatic}/images/app/ico_yq.png" width="18" height="19" alt="set" /></div>
									<div class="poop_txt">
										<p>您收到 <span id="yaoqingCount">${invitationMessage.total}</span>条邀请，来自<code id="yaoqingUserNames">
												<!-- 最多展示3个人名 -->
												<c:forEach items="${invitationMessage.senderList}" var="sender" varStatus="status">
													<c:if test ="${status.index < 3}" > 
														${sender.userName}
													</c:if>
													<c:if test ="${status.index == 3}" > 
														等
													</c:if>
												</c:forEach></code></p>
										<p id="yaoqingUserHeadImgs">
											<c:forEach items="${invitationMessage.senderList}" var="sender" varStatus="status">
												<!-- 最多展示5个头像 -->
												<c:if test ="${status.index < 5}" > 
													<img src="${sender.tinyUrl}" width="30" height="30" title="${sender.userName}" />
												</c:if>
											</c:forEach>
										</p>
									</div>
								</div>
							</c:if>
							<c:if test ="${appraiseMessage > 0}" > 
								<div  id="pj" class="pooplist lastone poopback2" onclick="fangpingList('${urlStatic}')">
									<div class="poop_pic"><img src="${urlStatic}/images/app/ico_pj.png" width="18" height="19" alt="set" /></div>
									<div class="poop_txt">
										<p>有<span id="fangpingCount">${appraiseMessage}</span>处房子尚未评价</p>
									</div>
								</div>
							</c:if>
							</li>
							<li id="li_second">
								<!-- xitong -->
								<div class="pooplist poopback3"></div>
								<div class="pooplist poopback3"></div>
								<div class="pooplist poopback3"></div>
								<div class="pooplist poopback3"></div>
							</li>
							<li id="detialLi">
								<!-- xitong -->
								<div class="pooplast">
									</div>
								</div>
							</li>
						</ul>
					</div>
					</div>
				</div>
			</div>