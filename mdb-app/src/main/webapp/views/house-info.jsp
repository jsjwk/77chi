<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>房屋详情</title>
<%@include file="/WEB-INF/include/taglib.jsp"%>
<script>
	var index = 0;
	var arr = new Array();
	$(document).ready(function() {
		$("#topli_house").attr("class", "active");
		
		<c:forEach items="${attentionUserList}" var="user">
			arr[index] = "${user.id}";
			index++;
		</c:forEach>
		if(arr.length > 0){
			$("#" + arr[0]).show();
			$("#showUserId").val(arr[0]);
		}
	});
	
	function showAttentionUser(type){
		for(var i = 0; i < arr.length; i++){
			if(arr[i] == $("#showUserId").val()){
				if(type == "prev" && $("#showUserId").val() != arr[0]){
					$("#" + arr[i]).hide();
					$("#" + arr[i - 1]).show();
					$("#showUserId").val(arr[i - 1]);
				}
				if(type == "next" && $("#showUserId").val() != arr[arr.length - 1]){
					$("#" + arr[i]).hide();
					$("#" + arr[i + 1]).show();
					$("#showUserId").val(arr[i + 1]);
				}
			}
		}
	}
	
	function share(){
		var resourceUrl = "http://${appRenren}";
		var pic = " ";
		var title = "分享房源信息";
		var description = " ";
		var message = "我在帮你租(http://${appRenren})看到一条房源信息，价格: ${houseInfo.rent}元/月;位置: ${houseInfo.area.name};户型: ${houseInfo.houseType};可入住时间：${houseInfo.checkInTime}，这里还有更多的房源信息，来看看吧！";
		shareClick(resourceUrl, pic, title, description, message);
	}
	
	function reservationDivShow(houseId){
		//$("#recipient").val(userId);
		$("#houseId").val(houseId);
		$('#reservation_div').show();
	}
	
	function reservationFormSub(){
		var param = $("#reservation_form").serialize();
		$.ajax({
			type : "post",
			url : "/reservation/save",
			dataType : "text",
			data : param,
			success : function(data) {
				location.href = "/house/info/${houseInfo.id}";
			}
		});
	}
	
	function submitInvitation(userId){
		var param = $("#invitation_from").serialize();
		$.ajax({
            type : "post",
            url : "/invitation/save/"+userId,
            data : "bean="+param,
            success : function(data) {
            	if (data>0){
                    alert( "邀请成功!" ) ;
                    } else if (data== -1){
                    alert( "缺少参数,邀请失败!" ) ;
                    } else if (data== -2){
                    alert( "已经邀请过了o(╯□╰)o!" ) ;
                    } else {
                    alert( "邀请失败!" ) ;
                    }
            }
      });
	}
	function appreciationSave(houseId,status){
		$.ajax({
            type : "post",
            url : "/house/appreciation/"+houseId+"/"+status,
            data : "",
            success : function(data) {
            	if (data>0){
                    alert( "评价成功!" ) ;
                    window.location.href="/house/info/"+houseId;
                    } else if (data== -1){
                    alert( "缺少参数,评价失败!" ) ;
                    } else if (data== -2){
                    alert( "已经评价过了o(╯□╰)o!" ) ;
                    } else {
                    alert( "评价失败!" ) ;
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
				<div class="section house gray_14">
					<div class="inifo_picmin">
						<c:if test="${not empty userInfo.tinyUrl}">
							<img src="${userInfo.tinyUrl}" alt="${userInfo.userName}" />
						</c:if>
						<c:if test="${empty userInfo.tinyUrl}">
							<img src="${urlStatic}/images/www/img_user40.png" alt="${userInfo.userName}" />
						</c:if>
					</div>
					<h2>
						${houseInfo.houseTitle}
						<%-- <a href="javascript:void(0);"><img src="${urlStatic}/images/app/fenxiang.jpg" alt="" /></a> --%>
					</h2>
					<a href="javascript:void(0)" onclick="share();"><img src="${urlStatic}/images/app/renren_ico.png"/></a>
					<p>
						发布时间：<script>document.write(getSmpFormatDate(new Date(${houseInfo.updateTime}), false));</script>
					</p>
					<div class="right_top">
						<a class="goback gray9_12" href="#">返回房屋列表&gt;&gt;</a>
					</div>
				</div>
				<div class="title nomarbot">
					<!-- <a class="active" href="#">房源图片</a>
					<a href="#">房源描述</a>
					<a href="#">地理位置</a>
					<a href="#">周边生活</a>
					<a class="last" href="#">公共交通</a> -->
					<a id="title_0" class="change_tit active" href="javascript:void(0);">房源图片</a>
					<a id="title_1" class="change_tit" href="javascript:void(0);">房源描述</a>
					<a id="title_2" class="change_tit" href="javascript:void(0);">地理位置</a>
					<a id="title_3" class="change_tit" href="javascript:void(0);">周边生活</a>
					<a id="title_4" class="change_tit last" href="javascript:void(0);">公共交通</a>
					<div class="right_top house_titright">
						<c:choose>
							<c:when test="${empty houseInfo.rentStatus || houseInfo.rentStatus == 0}">
								<c:choose>
									<c:when test="${empty houseAttention}">
										<a class="add_reservation" href="#all_reservation">直接预约</a>|
										<a class="share gray9_12" href="/houseAttention/save/${houseInfo.id}" id="a_attention">加关注</a>|
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${houseAttention.reservationStatus == 1}">
												预约处理中|
											</c:when>
											<c:when test="${houseAttention.reservationStatus == 2}">
												预约成功|
											</c:when>
											<c:when test="${houseAttention.reservationStatus == 3}">
												<c:choose>
													<c:when test="">
														<a class="add_reservation" href="#all_reservation">直接预约</a>|
													</c:when>
													<c:otherwise>
														<a class="add_reservation" href="#all_reservation">直接预约</a>|
													</c:otherwise>
												</c:choose>
											</c:when>
											<c:otherwise>
												<c:choose>
													<c:when test="">
														<a class="add_reservation" href="#all_reservation">直接预约</a>|
													</c:when>
													<c:otherwise>
														<a class="add_reservation" href="#all_reservation">直接预约</a>|
													</c:otherwise>
												</c:choose>
											</c:otherwise>
										</c:choose>
										已关注|
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>已出租</c:otherwise>
						</c:choose>
						<a class="changemind gray9_12" href="#">举报</a>
					</div>
				</div>
				<%-- <div id="active_title_1" class="section houseinfo none">${houseInfo.discription}</div>
				<div id="active_title_2" class="section houseinfo none"></div>
				<div id="active_title_3" class="section houseinfo none">
					周边商场：${communityInfo.markets}</br>
					周边医院：${communityInfo.hospitals}</br>
					周边银行：${communityInfo.banks}</br>
					周边学校：${communityInfo.schools}</br>
				</div>
				<div id="active_title_4" class="section houseinfo none"></div> --%>
				<div class="section houseinfo">
					<div id="active_title_1" class="anybody none">
						<div class="baidu_map">
							${houseInfo.discription}
						</div>
					</div>
					<div id="active_title_2" class="anybody none">
						<div class="baidu_map"></div>
					</div>
					<div id="active_title_3" class="anybody none">
						<div class="baidu_map">
							<ul>
								<li>
									<span>超市</span>
									<div class="life_txt">
										${communityInfo.markets}
									</div>
								</li>
								<li>
									<span>医院</span>
									<div class="life_txt">
										${communityInfo.hospitals}
									</div>
								</li>
								<li>
									<span>银行</span>
									<div class="life_txt">
										${communityInfo.banks}
									</div>
								</li>
								<li>
									<span>学校</span>
									<div class="life_txt">
										${communityInfo.schools}
									</div>
								</li>
							</ul>
						</div>
					</div>
					<div id="active_title_4" class="anybody none">
						<div class="baidu_map">
							<ul>
								<li>
									<span>公交</span>
									<div class="life_txt">
										<p>政法小区 ( 654路 665路 运通202路 )</p>
										<p>方庄南路南口 ( 213路 684路 685路 )</p>
										<p>宋家庄枢纽站 ( 524路 684路 )</p>
										<p>东铁营桥东 ( 25路 654路 685路 723路 运通107路 运通202路 )</p>
										<p>政馨园小区 ( 684路 )</p>
									</div>
								</li>
								<li>
									<span>地铁</span>
									<div class="life_txt">
										柳芳 ( 13号线 )
									</div>
								</li>
							</ul>
						</div>
					</div>
					<div id="active_title_0" class="anybody">
						<div class="user_pic">
							<c:if test="${empty houseInfo.houseImgList}">
								<img src="${urlStatic}/images/www/house_big.png" alt="${houseInfo.houseTitle}" />
							</c:if>
							<c:if test="${not empty houseInfo.houseImgList}">
								<img src="${urlImg}${houseInfo.houseImgList[0].path}${houseInfo.houseImgList[0].fileName}${houseInfo.houseImgList[0].suffix}" alt="${houseInfo.houseTitle}" />
							</c:if>
						</div>
						<div class="user_picbtn">
							<div class="btndiv"><a class="prev" href="javascript:void(0);"><img src="${urlStatic}/images/app/btn_left.png" alt="turn left" /></a></div>
							<div class="house_img">
								<ul>
									<c:if test="${not empty houseInfo.houseImgList}">
										<c:forEach items="${houseInfo.houseImgList}" var="houseImg">
											<li><img src="${urlImg}${houseImg.path}${houseImg.fileName}${houseImg.suffix}" alt="house img" /></li>
										</c:forEach>
									</c:if>
									<c:if test="${empty houseInfo.houseImgList}">
										<li><img src="${urlStatic}/images/www/house_small.png" alt="house img" /></li>
									</c:if>
								</ul>
							</div>
							<div class="btndiv"><a class="next" href="javascript:void(0);"><img src="${urlStatic}/images/app/btn_right.png" alt="turn right" /></a></div>
						</div>
						<div class="user_txt">
							价格: <fmt:formatNumber value="${houseInfo.rent}" pattern="#.##" type="number"/>元/月<br />
							位置: ${houseInfo.area.name}<br />
							户型: ${houseInfo.houseType}<br />
							面积: <fmt:formatNumber value="${houseInfo.rentArea}" pattern="#.##" type="number"/>平方米<br />
							朝向: ${houseInfo.towards}<br />
							楼层: 第${houseInfo.floorNum}层 共${houseInfo.floorTotalNum}层<br /><br />
							可入住时间：<br />
							<c:if test="${not empty houseInfo.checkInTime}"><span>${houseInfo.checkInTime}</span>之后</c:if>
						</div>
					</div>
					<div class="anyhouse">
						<div class="btn_left"><a href="javascript:void(0);" onclick="showAttentionUser('prev');"><img src="${urlStatic}/images/app/msg_leftico.png" alt="turn left" /></a></div>
						<div class="btn_right"><a href="javascript:void(0);" onclick="showAttentionUser('next');"><img src="${urlStatic}/images/app/msg_rightico.png" alt="turn left" /></a></div>
						<input type="hidden" value="" id="showUserId"/>
						<p class="pj_tit">也想租此房的人（${attentionCount}）</p>
						<c:forEach items="${attentionUserList}" var="auser">
							<div id="${auser.id}" style="display:none">
								<img class="userbod" src="${auser.tinyUrl}" width="94" height="94" alt="${auser.userName}" />
								<a href="#">${auser.userName}</a>
								<p>
									<c:forEach items="${auser.universityInfo}" var="uninfo">
										 ${uninfo.name}
									</c:forEach>
								</p>
								<p>${auser.hometown.province} ${auser.hometown.city}</p>
								<div class="sub_hz"><a  onclick="submitInvitation(${auser.id});" href="javascript:void(0)"><img src="${urlStatic}/images/app/find_hz.png" width="90" height="32" alt="message to" /></a></div>
							</div>
						</c:forEach>
					</div>
					<div class=anyhouse>
						<div class="body_pj">
							<p class="pj_tit">大众评价 （${appraiseInfo.totalNum}）</p>
							<c:choose>
								<c:when test="${houseAttention.reservationStatus == 2}">
									<p><font>很好：</font><span class="allong"><span style="width:${appraiseInfo.veryGood}%;height:10px; background-color:#ffb7a5; display: block;"></span></span>
									   <a class="changethis" href="javascript:void(0);" onclick ="appreciationSave(${houseInfo.id},1);">╋</a></p>
									<p><font>好：</font><span class="allong"><span style="width:${appraiseInfo.good}%;height:10px; background-color:#ffd4a5; display: block;"></span></span>
									   <a class="changethis" href="javascript:void(0);" onclick ="appreciationSave(${houseInfo.id},2);">╋</a></p>
									<p><font>一般：</font><span class="allong"><span style="width:${appraiseInfo.normal}%;height:10px; background-color:#d4ffa5; display: block;"></span></span>
									   <a class="changethis" href="javascript:void(0);" onclick ="appreciationSave(${houseInfo.id},3);">╋</a></p>
									<p><font>差：</font><span class="allong"><span style="width:${appraiseInfo.bad}%;height:10px; background-color:#d4a5ff; display: block;"></span></span>
									   <a class="changethis" href="javascript:void(0);" onclick ="appreciationSave(${houseInfo.id},4);">╋</a></p>
									<p><font>很差：</font><span class="allong"><span style="width:${appraiseInfo.veryBad}%;height:10px; background-color:#aba5ff; display: block;"></span></span>
									   <a class="changethis" href="javascript:void(0);" onclick ="appreciationSave(${houseInfo.id},5);">╋</a></p>
								</c:when>
								<c:otherwise>
								  	<p><font>很好：</font><span class="allong"><span style="width:${appraiseInfo.veryGood}%;height:10px; background-color:#ffb7a5; display: block;"></span></span></p>
									<p><font>好：</font><span class="allong"><span style="width:${appraiseInfo.good}%;height:10px; background-color:#ffd4a5; display: block;"></span></span></p>
									<p><font>一般：</font><span class="allong"><span style="width:${appraiseInfo.normal}%;height:10px; background-color:#d4ffa5; display: block;"></span></span></p>
									<p><font>差：</font><span class="allong"><span style="width:${appraiseInfo.bad}%;height:10px; background-color:#d4a5ff; display: block;"></span></span></p>
									<p><font>很差：</font><span class="allong"><span style="width:${appraiseInfo.veryBad}%;height:10px; background-color:#aba5ff; display: block;"></span></span></p>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
				<div class="section housepl">
					<div class="pl_mian">
						<h2>评论（最多300个汉字）</h2>
						<div class="list_mian">
							<form action="/houseComment/save" method="post" id="comment_form">
								<input type="hidden" name="houseId" value="${houseInfo.id}"/>
								<textarea rows="" cols="" name="content"></textarea>
								<div class="text_btn">
									<a href="javascript:void(0);" onclick="$('#comment_form').submit();">
										<img src="${urlStatic}/images/app/fb.png" width="80" height="32" alt="message to" /></a></div>
							</form>
							<ul>
								<c:forEach items="${commentList}" var="comment">
									<c:forEach items="${commenterUserList}" var="user">
										<c:if test="${comment.commenter == user.id}">
											<li>
												<div class="inifo_picmin">
													<img src="${user.tinyUrl}" alt="${user.userName}" />
												</div>
												<div class="pl_txt">
													<h2>${user.userName}<span><script>document.write(getSmpFormatDate(new Date(${purpose.checkInTime}), true));</script></span></h2>
													<p class="adr">${user.hometown.province} ${user.hometown.city}</p>
													<p>${comment.content}</p>
												</div>
											</li>
										</c:if>
									</c:forEach>
								</c:forEach>
							</ul>
						</div>
					</div>
					<div class="like_body">
						<h2>感兴趣的人</h2>
						<div class="list_mian">
							<div class="maybe_friend">
								<c:forEach items="${attentionUserList}" var="attuser">
									<a href="javascript:void(0)"><img src="${attuser.tinyUrl}" alt="${attuser.userName}" /></a>
								</c:forEach>
							</div>
						</div>
						<h2>该房源所在的部落</h2>
						<ul>
							<li><a href="#">长安大学校园周边部落长</a></li>
							<li><a href="#">西安大学校园周边部落</a></li>
							<li><a href="#">长安大学校园周边部落</a></li>
							<li><a href="#">西安大学校园周边部落</a></li>
							<li><a href="#">长安大学校园周边部落</a></li>
							<li><a href="#">西安大学校园周边部落</a></li>
						</ul>
					</div>
					
					<div style="display:none;" id="div_reservation">
						<div id="all_reservation">
							<form id="reservation_form">
								<input type="hidden" id="houseId" name="houseId" value="${houseInfo.id}"/>
								<table>
									<tr>
										<td>留言：</td>
										<td><textarea rows="5" cols="40" name="content" id="content"></textarea></td>
									</tr>
									<tr>
										<td>电话：</td>
										<td><input type="text" name="senderPhone" id="senderPhone" size="50" value="${sessionUser.mobile}"/></td>
									</tr>
									<tr>
										<td colspan="2"><input type="button" onclick="reservationFormSub();" value="提交"/>
									</tr>
								</table>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>