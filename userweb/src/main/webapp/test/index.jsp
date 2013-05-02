<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jstl/fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if lt IE 7]> <html dir="ltr" lang="en-US" class="ie6"> <![endif]-->
<!--[if IE 7]>    <html dir="ltr" lang="en-US" class="ie7"> <![endif]-->
<!--[if IE 8]>    <html dir="ltr" lang="en-US" class="ie8"> <![endif]-->
<!--[if gt IE 8]><!--> <html dir="ltr" lang="en-US"> <!--<![endif]-->

<!-- BEGIN head -->
<head>

	<!--Meta Tags-->
	<meta name="viewport" content="width=device-width; initial-scale=1.0" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
		
	<!--Title-->
	<title>77chi shop</title>

	<!--Stylesheets-->
	<link rel="stylesheet" href="<%=request.getContextPath() %>/test/css/superfish.css" type="text/css" media="all" />
	<link rel="stylesheet" href="<%=request.getContextPath() %>/test/css/prettyPhoto.css" type="text/css" media="all" />
	<link type="text/css" href="<%=request.getContextPath() %>/test/css/jqueryui/jquery.ui.datepicker.css" rel="stylesheet" />
	<link rel="stylesheet" href="<%=request.getContextPath() %>/test/style.css" type="text/css" media="all" />
	<link rel="stylesheet" href="<%=request.getContextPath() %>/test/css/responsive.css" type="text/css" media="all" />
	<link rel="stylesheet" href="<%=request.getContextPath() %>/test/css/flexslider.css" type="text/css" media="all" />
	<link rel="stylesheet" href="<%=request.getContextPath() %>/test/css/colours/green.css" type="text/css" media="all" />
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css' />
	<link href='http://fonts.googleapis.com/css?family=Cardo:400,400italic,700' rel='stylesheet' type='text/css' />

	<!--Favicon-->
	<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />

	<!--JavaScript-->
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
	<script type='text/javascript' src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js'></script>
	<script type='text/javascript' src='<%=request.getContextPath() %>/test/js/jquery.prettyPhoto.js'></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/test/js/superfish.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/test/js/jquery.flexslider-min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/test/js/scripts.js"></script>
	
	<!--[if (gte IE 6)&(lte IE 8)]>
		<script type="text/javascript" src="js/selectivizr-min.js"></script>
	<![endif]-->

<!-- END head -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>

<!-- BEGIN body -->
<body>
	
	<!-- BEGIN .wrapper -->
	<div class="wrapper">
		
		<!-- BEGIN .topbar -->
		<div class="topbar clearfix">
			
			<!-- BEGIN .social-icons -->
			<ul class="social-icons">
				<li><a href="#"><span id="twitter_icon"></span></a></li>
				<li><a href="#"><span id="facebook_icon"></span></a></li>
				<li><a href="#"><span id="googleplus_icon"></span></a></li>
				<li><a href="#"><span id="skype_icon"></span></a></li>
				<li><a href="#"><span id="flickr_icon"></span></a></li>
				<li><a href="#"><span id="linkedin_icon"></span></a></li>
				<li><a href="#"><span id="vimeo_icon"></span></a></li>
				<li><a href="#"><span id="youtube_icon"></span></a></li>
				<li><a href="#"><span id="rss_icon"></span></a></li>
			<!-- END .social-icons -->
			</ul>
			
			<!-- BEGIN .topbar-right -->
			<div class="topbar-right clearfix">
				
				<ul class="clearfix">
					<li class="checkout-icon"><a href="checkout.html">Checkout</a></li>
					<li class="myaccount-icon"><a href="my-account.html">My Account</a></li>
				</ul>
		
				<div class="cart-top">
					<p><a href="cart.html">9 Items</a></p>
				</div>
				
			<!-- END .topbar-right -->
			</div>
		
		<!-- END .topbar -->
		</div>
		
		<!-- BEGIN #site-title -->
		<div id="site-title">
			<a href="index.html">
				<h1>77chi <span>shop</span></h1>
			</a>
		<!-- END #site-title -->
		</div>
		
		<!-- BEGIN .main-menu-wrapper -->
		<div id="main-menu-wrapper" class="clearfix">
			
			<ul id="main-menu" class="fl">
				<li class="current_page_item"><a href="index.html">首页</a></li>
				<li><a href="products.html">进口美食</a>
					<ul>
						<li><a href="product-single.html">Cleansers</a></li>
						<li><a href="product-single.html">Exfoliators &amp; Masks</a></li>
						<li><a href="product-single.html">Toners</a></li>
						<li><a href="product-single.html">Moisturisers</a>
							<ul>
								<li><a href="product-single.html">Hands</a></li>
								<li><a href="product-single.html">Face</a></li>
							</ul>
						</li>
					</ul>
				</li>
				<li><a href="blog.html">推荐零食</a></li>
				<li><a href="testimonials.html">留言板</a></li>
				<li><a href="typography.html">关于</a>
					<ul>
						<li><a href="typography.html">Typography</a></li>
						<li><a href="js-elements.html">JS Elements</a></li>
					</ul>
				</li>
				<li><a href="contact.html">联系我们</a></li>	
			</ul>
			
			<form method="get" action="#" id="menu-search" class="fr" >
				<input type="text" name="s" />
			</form>
		
		<!-- END .main-menu-wrapper -->	
		</div>
		
		<!-- BEGIN .slider -->
		<div class="slider slide-loader clearfix">
			<ul class="slides">
				<li>
					<a href="#" title="Slide 1" target="_blank"><img src="images/slide3.jpg" alt="" /></a>
					<div class="flex-caption">
						<p>进口美食</p>
						<div class="clearboth"></div>
						<p>好吃!</p>
					</div>
				</li>
				<li>
					<a href="#" title="Slide 2" target="_blank"><img src="images/slide2.jpg" alt="" /></a>
					<div class="flex-caption">
						<p>推荐零食</p>
						<div class="clearboth"></div>
						<p>好吃</p>
					</div>
				</li>
				<li>
					<a href="#" title="Slide 3" target="_blank"><img src="images/slide1.jpg" alt="" /></a>
					<div class="flex-caption">
						<p>We believe in a fair deal</p>
						<div class="clearboth"></div>
						<p>which is why all our products are fair trade</p>
					</div>
				</li>
			</ul>
		<!-- END .slider -->
		</div>
		
		<!-- BEGIN .section -->
		<div class="section section-mini">
			<h2 class="site-intro">推荐如下</h2>
		<!-- END .section -->
		</div>
		
		<!-- BEGIN .section -->
		<div class="section-mini2">
			
			<div class="tag-title-wrap clearfix">
				<h4 class="tag-title">推荐零食</h4>
			</div>
			
			<ul class="columns-4 clearfix">
			<c:if test="${listTaobaokeItem ne null}">
			<c:forEach items="${listTaobaokeItem }" var="taobaokeItem">
				<li class="col4">
					<a href="${taobaokeItem.clickUrl }"><img src="${taobaokeItem.picUrl }" alt="" class="full-image product-image" /></a>
					<p class="product-title"><a href="${taobaokeItem.clickUrl }">${taobaokeItem.title }</a></p>
					<p class="product-price">${taobaokeItem.price }</p>
					<p class="product-button clearfix"><a href="${taobaokeItem.clickUrl }" class="button2">点击详情 &raquo;</a></p>
				</li>
			</c:forEach>
			</c:if>
			</ul>
			
		<!-- END .section -->
		</div>
		
		<!-- BEGIN .section -->
		<div class="section">
			
			<div class="tag-title-wrap clearfix">
				<h4 class="tag-title">评论</h4>
			</div>
			
			<ul class="columns-2 clearfix">
				<li class="col2">
					<div class="testimonial-wrapper clearfix">
						<div class="testimonial-author-image">
							<img src="images/author.png" alt="" />
						</div>
						<p class="testimonial-text">&ldquo;I made a mistake with my order but Organic Shop let me change it free of charge, thanks! The delivery is also super quick!&rdquo;</p>
						<div class="testimonial-speech"></div>
					</div>	
					<p class="testimonial-author">Sarah Cooper - <span>Purchased Classic Brown Shoes</span></p>
				</li>
				<li class="col2">
					<div class="testimonial-wrapper clearfix">
						<div class="testimonial-author-image">
							<img src="images/author.png" alt="" />
						</div>
						<p class="testimonial-text">&ldquo;I made a mistake with my order but Organic Shop let me change it free of charge, thanks! The delivery is also super quick!&rdquo;</p>
						<div class="testimonial-speech"></div>
					</div>	
					<p class="testimonial-author">Dave Jones - <span>Purchased Classic Brown Shoes</span></p>
				</li>
			</ul>
			
		<!-- END .section -->
		</div>
		
		<!-- BEGIN #footer -->
		<div id="footer">
			
			<ul class="columns-4 clearfix">
				<li class="col4">
					
					<!-- BEGIN .widget -->
					<div class="widget">
						<div class="widget-title clearfix">
							<h6>Customer Services</h6>
						</div>
						
						<ul>
							<li class="contact-phone">+44 0123456789</li>
							<li class="contact-mail">mail [at] website [dot] com</li>
						</ul>
						
					<!-- END .widget -->
					</div>
					
				</li>
				<li class="col4">
					
					<!-- BEGIN .widget -->
					<div class="widget">
						<div class="widget-title clearfix">
							<h6>Categories</h6>
						</div>
						
						<ul>
							<li><a href="#">Skin Care</a></li>
							<li><a href="#">Bath &amp; Body Care</a></li>
							<li><a href="#">Fragrance</a></li>
							<li><a href="#">Make-Up</a></li>
							<li><a href="#">Hair</a></li>
							<li><a href="#">Moisturisers</a></li>
						</ul>
						
					<!-- END .widget -->
					</div>
					
				</li>
				<li class="col4">
					
					<!-- BEGIN .widget -->
					<div class="widget">
						<div class="widget-title clearfix">
							<h6>Tags</h6>
						</div>
						
						<ul class="wp-tag-cloud clearfix">
							<li><a href="#">Body Scrubs</a></li>
							<li><a href="#">Eye Care</a></li>
							<li><a href="#">Eyes</a></li>
							<li><a href="#">Lips</a></li>
							<li><a href="#">Cheeks</a></li>
							<li><a href="#">Candles</a></li>
							<li><a href="#">Shampoo</a></li>
							<li><a href="#">Conditioner</a></li>
							<li><a href="#">Body Wash</a></li>
						</ul>
						
					<!-- END .widget -->
					</div>
					
				</li>
				<li class="col4">
					
					<!-- BEGIN .widget -->
					<div class="widget">
						<div class="widget-title clearfix">
							<h6>Flickr</h6>
						</div>
						
						<div class="flickr_badge_wrapper clearfix">
							<div style="clear:both;margin:0 0 10px 0;"></div>
							<p class="button2"><a href="http://sc.chinaz.com/">sc.chinaz.com</a></p>
						</div>
						
					<!-- END .widget -->
					</div>
					
				</li>
			</ul>
		
		<!-- END #footer -->
		</div>
		
		<div id="footer-bottom" class="clearfix">
			
			<div class="fl">
				
				<ul>
					<li><a href="index.html">Home</a></li>
					<li><a href="blog.html">Blog</a></li>
					<li><a href="contact.html">Contact Us</a></li>
					<li><a href="products.html">Products</a></li>
				</ul>
				
				<p>&copy; Copyright 2013</p>
				
			</div>
			
			<div class="fr">
				<img src="images/payment-methods.png" alt="Payment Methods" />
			</div>
			
		</div>
			
	<!-- END .wrapper -->
	</div>
	
<!-- END body -->
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>