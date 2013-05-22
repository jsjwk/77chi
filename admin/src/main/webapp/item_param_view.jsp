<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jstl/fn"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<jsp:include page="/include/head.jsp"></jsp:include>
<jsp:include page="/include/js.jsp"></jsp:include>
</head>

<body>
	<!-- topbar starts -->
	<jsp:include page="/include/header.jsp"></jsp:include>
	<!-- topbar ends -->
	
		<div class="container-fluid">
		<div class="row-fluid">
				
			<!-- left menu starts -->
			<jsp:include page="/include/left.jsp"></jsp:include>
			<!-- left menu ends -->
			
			<noscript>
				<div class="alert alert-block span10">
					<h4 class="alert-heading">Warning!</h4>
					<p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a> enabled to use this site.</p>
				</div>
			</noscript>
			
			<div id="content" class="span10">
			<!-- content starts -->
			

			<div>
				<ul class="breadcrumb">
					<li>
						<a href="#">Home</a> <span class="divider">/</span>
					</li>
					<li>
						<a href="#">Dashboard</a>
					</li>
				</ul>
			</div>
			
			<div class="row-fluid sortable">		
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-user"></i> 商品基本参数</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<table class="table table-striped table-bordered bootstrap-datatable datatable">
						  <thead>
							  <tr>
								  <th>参数名称</th>
								  <th>参数值</th>
							  </tr>
						  </thead>   
						  <tbody>
						  
						  <c:if test="${taobaokeItem ne null}">
							<tr>
								<td>numIid </td>
								<td>${taobaokeItem.numIid }</td>
							</tr>
							<tr>
								<td>cid</td>
								<td>${taobaokeItem.cid }</td>
							</tr>
							<tr>
								<td>createTime</td>
								<td>${taobaokeItem.createTime }</td>
							</tr>
							<tr>
								<td>itemType</td>
								<td>${taobaokeItem.itemType }</td>
							</tr>
							<tr>
								<td>overseasItem</td>
								<td>${taobaokeItem.overseasItem }</td>
							</tr>
							<tr>
								<td>updateTime</td>
								<td>${taobaokeItem.updateTime }</td>
							</tr>
							<tr>
								<td>clickUrl</td>
								<td>${taobaokeItem.clickUrl }</td>
							</tr>
							<tr>
								<td>commission</td>
								<td>${taobaokeItem.commission }</td>
							</tr>
							<tr>
								<td>commissionNum</td>
								<td>${taobaokeItem.commissionNum }</td>
							</tr>
							<tr>
								<td>commissionRate</td>
								<td>${taobaokeItem.commissionRate }</td>
							</tr>
							<tr>
								<td>commissionVolume</td>
								<td>${taobaokeItem.commissionVolume }</td>
							</tr>
							<tr>
								<td>couponEndTime</td>
								<td>${taobaokeItem.couponEndTime }</td>
							</tr>
							<tr>
								<td>couponPrice</td>
								<td>${taobaokeItem.couponPrice }</td>
							</tr>
							<tr>
								<td>couponRate</td>
								<td>${taobaokeItem.couponRate }</td>
							</tr>
							<tr>
								<td>couponStartTime</td>
								<td>${taobaokeItem.couponStartTime }</td>
							</tr>
							<tr>
								<td>itemLocation</td>
								<td>${taobaokeItem.itemLocation }</td>
							</tr>
							<tr>
								<td>keywordClickUrl</td>
								<td>${taobaokeItem.keywordClickUrl }</td>
							</tr>
							<tr>
								<td>nick</td>
								<td>${taobaokeItem.nick }</td>
							</tr>
							<tr>
								<td>picUrl</td>
								<td>${taobaokeItem.picUrl }</td>
							</tr>
							<tr>
								<td>price</td>
								<td>${taobaokeItem.price }</td>
							</tr>
							<tr>
								<td>promotionPrice</td>
								<td>${taobaokeItem.promotionPrice }</td>
							</tr>
							<tr>
								<td>sellerCreditScore</td>
								<td>${taobaokeItem.sellerCreditScore }</td>
							</tr>
							<tr>
								<td>sellerId</td>
								<td>${taobaokeItem.sellerId }</td>
							</tr>
							<tr>
								<td>shopClickUrl</td>
								<td>${taobaokeItem.shopClickUrl }</td>
							</tr>
							<tr>
								<td>shopType</td>
								<td>${taobaokeItem.shopType }</td>
							</tr>
							<tr>
								<td>taobaokeCatClickUrl</td>
								<td>${taobaokeItem.taobaokeCatClickUrl }</td>
							</tr>
							<tr>
								<td>title</td>
								<td>${taobaokeItem.title }</td>
							</tr>
							<tr>
								<td>volume</td>
								<td>${taobaokeItem.volume }</td>
							</tr>
							</c:if>
							
						  </tbody>
					  </table>            
					</div>
				</div><!--/span-->
			
			</div><!--/row-->

			
					<!-- content ends -->
			</div><!--/#content.span10-->
				</div><!--/fluid-row-->
				
		<hr>

		<div class="modal hide fade" id="myModal">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h3>Settings</h3>
			</div>
			<div class="modal-body">
				<p>Here settings can be configured...</p>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal">Close</a>
				<a href="#" class="btn btn-primary">Save changes</a>
			</div>
		</div>

		<footer>
			<p class="pull-left">&copy; <a href="http://usman.it" target="_blank">Muhammad Usman</a> 2012</p>
			<p class="pull-right">Powered by: <a href="http://usman.it/free-responsive-admin-template">Charisma</a></p>
		</footer>
		
	</div><!--/.fluid-container-->

   <script type="text/javascript">
    
    /*全选与全取消*/
    function Opearation(bool_val, checkboxType) {
    	$('input[name=' + checkboxType + ']').each(function() {
    		$(this).attr("checked", bool_val);
    	});
    }

    function changeOpearation()
    {
    	if($("#all_checkbox").attr("checked"))
    	{
    		Opearation(true,'checkbox_block');
    	}else{
    		Opearation(false,'checkbox_block');
    	}
    }

    function changeClickStatus()
    {
    	var all_flag = true;
    	$('input[name=checkbox_block]').each(function() {
    		if($(this).attr("checked")!='checked'){
    			all_flag = false;
    			return;
    		}
    	});
    	$("#all_checkbox").attr("checked", all_flag);
    }

    Opearation(false,'checkbox_block');

    var numIids = "";
    function select_item(numIid)
    {
    	numIids = numIid;
    	ajaxDeleteItem();
    }

    //删除商品
    function ajaxDeleteItem()
    {
    	if(numIids=="select_items")
    	{
    		numIids = "";
    		$('input[name=checkbox_block]').each(function() 
    		{
    			if($(this).attr("checked")=='checked'){
    				numIids = numIids + $(this).val()+",";
    			}
    			
    		});
    		numIids = numIids.substring(0,numIids.length-1);
    	}
    	
    	if(numIids.length<=0){
    		alert("请选择选项.");
    		return false;
    	}
    	var surl = "/ajaxDeleteItem.do";
    	$.ajax({
    		type : "POST",
    		timeout : 20000,
    		url : surl,
    		dataType : "text",
    		data : "numIids=" + numIids,	
    		cache : false,
    		async : true,
    		global : false,
    		success : function(data) {
    			data = $.trim(data);
    			if (data == "success") {
    				window.location.reload();
    			} else if (data == "error") {
    				alert('error');
    			}

    		},
    		error : function(t, v) {
    			alert("系统错误"+t+v);
    		}
    	});
    }
    </script>
    
	
		
</body>
</html>


