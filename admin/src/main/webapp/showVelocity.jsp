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
						<a href="#">Forms</a>
					</li>
				</ul>
			</div>
			
			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"></i> 模版</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<form class="form-horizontal" action="/indexVelocity.do" method="post">
						  <fieldset>
							<legend>
							  <a href="javascript:flushPage(${itemType })" class="btn btn-success"><i class="icon-shopping-cart icon-white"></i>重新刷新生成此类别页面</a>
							 </legend>
							 
							<div class="control-group">
							  <label class="control-label" for="typeahead">模版名称 </label>
							  <div class="controls">
								<input type="text" name="templateName" value="${templateName }" class="span6 typeahead" id="typeahead"  data-provide="typeahead" data-items="4" data-source='["Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","Florida","Georgia","Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana","Maine","Maryland","Massachusetts","Michigan","Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","New Hampshire","New Jersey","New Mexico","New York","North Dakota","North Carolina","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island","South Carolina","South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington","West Virginia","Wisconsin","Wyoming"]'>
								<p class="help-block">填写用于标记的名称，常用名称会自动填充!</p>
							  </div>
							</div>
							
							<!-- 
							<div class="control-group">
							  <label class="control-label" for="date01">日期</label>
							  <div class="controls">
								<input type="text" class="input-xlarge datepicker" id="date01" value="02/16/12">
							  </div>
							</div>

							<div class="control-group">
							  <label class="control-label" for="fileInput">File input</label>
							  <div class="controls">
								<input class="input-file uniform_on" id="fileInput" type="file">
							  </div>
							</div>
							 -->
							          
							<div class="control-group">
							  <label class="control-label" for="textarea2">模版内容</label>
							  <div class="controls" >
								<textarea   name="templateString" class="cleditor" id="textarea2" >${templateString }</textarea>
							  </div>
							</div>
							<div class="form-actions">
							  <button type="submit" class="btn btn-primary">保存</button>
							  <button type="reset" class="btn">取消</button>
							</div>
						  </fieldset>
						</form>   

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

	
<script>
//刷新生成页面
function flushPage(itemType)
{
	var surl = "/flushPage.do";
	$.ajax({
		type : "POST",
		timeout : 20000,
		url : surl,
		dataType : "text",
		data : "itemType=" + itemType,	
		cache : false,
		async : true,
		global : false,
		success : function(data) {
			data = $.trim(data);
			if (data == "success") {
				//window.location.reload();
				alert(data);
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