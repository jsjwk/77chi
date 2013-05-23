 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

			<!-- left menu starts -->
			<div class="span2 main-menu-span">
				<div class="well nav-collapse sidebar-nav">
					<ul class="nav nav-tabs nav-stacked main-menu">
						<li class="nav-header hidden-tablet">抓取的Mongo数据</li>
						<li ><a class="ajax-link" href="/showMongoItems.do?itemType=1"><i class="icon-align-justify"></i><span class="hidden-tablet"> 饮料</span></a></li>
						<li><a class="ajax-link" href="/showMongoItems.do?itemType=2"><i class="icon-align-justify"></i><span class="hidden-tablet"> 保健营养</span></a></li>
						<li><a class="ajax-link" href="/showMongoItems.do?itemType=3"><i class="icon-align-justify"></i><span class="hidden-tablet"> 进口食品</span></a></li>
						<li><a class="ajax-link" href="/showMongoItems.do?itemType=4"><i class="icon-align-justify"></i><span class="hidden-tablet"> 酒类</span></a></li>
						<li><a class="ajax-link" href="/showMongoItems.do?itemType=5"><i class="icon-align-justify"></i><span class="hidden-tablet"> 母婴食品</span></a></li>
						<li><a class="ajax-link" href="/showMongoItems.do?itemType=6"><i class="icon-align-justify"></i><span class="hidden-tablet"> 水果</span></a></li>
						<li><a class="ajax-link" href="/showMongoItems.do?itemType=7"><i class="icon-align-justify"></i><span class="hidden-tablet"> 休闲零食</span></a></li>
						
						<li class="nav-header hidden-tablet">线上数据</li>
						<li><a class="ajax-link" href="/showMysqlItems.do?itemType=1"><i class="icon-align-justify"></i><span class="hidden-tablet"> 饮料</span></a></li>
						<li><a class="ajax-link" href="/showMysqlItems.do?itemType=2"><i class="icon-align-justify"></i><span class="hidden-tablet"> 保健营养</span></a></li>
						<li><a class="ajax-link" href="/showMysqlItems.do?itemType=3"><i class="icon-align-justify"></i><span class="hidden-tablet"> 进口食品</span></a></li>
						<li><a class="ajax-link" href="/showMysqlItems.do?itemType=4"><i class="icon-align-justify"></i><span class="hidden-tablet"> 酒类</span></a></li>
						<li><a class="ajax-link" href="/showMysqlItems.do?itemType=5"><i class="icon-align-justify"></i><span class="hidden-tablet"> 母婴食品</span></a></li>
						<li><a class="ajax-link" href="/showMysqlItems.do?itemType=6"><i class="icon-align-justify"></i><span class="hidden-tablet"> 水果</span></a></li>
						<li><a class="ajax-link" href="/showMysqlItems.do?itemType=7"><i class="icon-align-justify"></i><span class="hidden-tablet"> 休闲零食</span></a></li>
						
						<li class="nav-header hidden-tablet">模版管理</li>
						<li><a class="ajax-link" href="/showVelocity.do?itemType=0"><i class="icon-edit"></i><span class="hidden-tablet"> 首页模版</span></a></li>
						<li><a class="ajax-link" href="/showVelocity.do?itemType=1"><i class="icon-edit"></i><span class="hidden-tablet"> 饮料模版</span></a></li>


						<li class="nav-header hidden-tablet">Backup</li>
						<li><a class="ajax-link" href="/ui-demo/index.html"><i class="icon-home"></i><span class="hidden-tablet"> Dashboard</span></a></li>
						<li><a class="ajax-link" href="/ui-demo/ui.html"><i class="icon-eye-open"></i><span class="hidden-tablet"> UI Features</span></a></li>
						<li><a class="ajax-link" href="/ui-demo/form.html"><i class="icon-edit"></i><span class="hidden-tablet"> Forms</span></a></li>
						<li><a class="ajax-link" href="/ui-demo/chart.html"><i class="icon-list-alt"></i><span class="hidden-tablet"> Charts</span></a></li>
						<li><a class="ajax-link" href="/ui-demo/typography.html"><i class="icon-font"></i><span class="hidden-tablet"> Typography</span></a></li>
						<li><a class="ajax-link" href="/ui-demo/gallery.html"><i class="icon-picture"></i><span class="hidden-tablet"> Gallery</span></a></li>
						<li><a class="ajax-link" href="/ui-demo/table.html"><i class="icon-align-justify"></i><span class="hidden-tablet"> Tables</span></a></li>
						<li><a class="ajax-link" href="/ui-demo/calendar.html"><i class="icon-calendar"></i><span class="hidden-tablet"> Calendar</span></a></li>
						<li><a class="ajax-link" href="/ui-demo/grid.html"><i class="icon-th"></i><span class="hidden-tablet"> Grid</span></a></li>
						<li><a class="ajax-link" href="/ui-demo/file-manager.html"><i class="icon-folder-open"></i><span class="hidden-tablet"> File Manager</span></a></li>
						<li><a href="/ui-demo/tour.html"><i class="icon-globe"></i><span class="hidden-tablet"> Tour</span></a></li>
						<li><a class="ajax-link" href="/ui-demo/icon.html"><i class="icon-star"></i><span class="hidden-tablet"> Icons</span></a></li>
						<li><a href="/ui-demo/error.html"><i class="icon-ban-circle"></i><span class="hidden-tablet"> Error Page</span></a></li>
						<li><a href="/ui-demo/login.html"><i class="icon-lock"></i><span class="hidden-tablet"> Login Page</span></a></li>
					</ul>
					<label id="for-is-ajax" class="hidden-tablet" for="is-ajax"><input id="is-ajax" type="checkbox"> Ajax on menu</label>
				</div><!--/.well -->
			</div><!--/span-->
			<!-- left menu ends -->