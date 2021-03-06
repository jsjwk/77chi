<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/index.css" />
<script src="js/jquery.js"></script>
<script src="js/script.js"></script>
<script src="js/scroll.js"></script>
<!--[if IE 6]> 
	<script type="text/javascript" src="js/png.js" ></script>
	
	<script type="text/javascript">
	DD_belatedPNG.fix('.shadow,.topNav h1,.shopCart,.login,.reg,.sBtn,.shopCart span');
	</script>
	<![endif]-->
</head>

<body>
<!-- 头部结构 -->
<!--<div class="topBar">想获得更好的浏览体验，77吃建议你更新浏览器。推荐使用：Chrome　360极速　IE8　Firefox</div>-->

<div class="topNav">

<jsp:include page="include/header.jsp"></jsp:include>

</div>


<div class="topMenu">
<div class="menuBox">
<ul class="navbar">
<a href="#" class="current">首页</a>
<a href="list.html">进口食品</a>
<a href="list2.html">休闲零食</a>
<a href="#">饮料</a>
<a href="#">酒水</a>
<a href="#">绿色瓜果</a>
<a href="#">母婴食品</a>
<a href="#">保健食品</a>
</ul>

<div class="searchBox"><div class="sText"></label><i>商品名称 / 分类</i><input type="text" id="nav-username" name="login_name" placeholder="商品名称 / 分类 / 标签"></div>
<input name="" type="button" class="sBtn"/>
</div>
</div>
</div>


<!-- 滑动图片及热搜 -->
<div class="scrollBox">
<div class="scrollPic">
<div id="xxx">
     <script>
     var box =new PPTBox();
     box.width = 642; //宽度
     box.height = 240;//高度
     box.autoplayer = 3;//自动播放间隔时间

     //box.add({"url":"图片地址","title":"悬浮标题","href":"链接地址"})
	 box.add({"url":"images/tu0.jpg","href":"http://www.114.com/","title":"悬浮提示标题1"})
     box.add({"url":"images/tu1.jpg","href":"http://www.114.com/","title":"悬浮提示标题1"})
     box.add({"url":"images/tu2.jpg","href":"http://www.114.com/","title":"悬浮提示标题2"})
     box.add({"url":"images/tu3.jpg","href":"http://www.114.com/","title":"悬浮提示标题3"})
     box.add({"url":"images/tu4.jpg","href":"http://www.114.com/","title":"悬浮提示标题4"})
     box.show();
    </script>
</div>
</div>
<div class="hotSearch">
<div class="hd">
<h3>强力推荐</h3>
<span class="more"><a href="#" class="gray">更多>></a></span>
</div>
<div class="bd">
<dl class="kword">
	<dt><a href="#">产地</a></dt>
    <dd><a href="#">中国大陆</a></dd>
    <dd class="red"><a href="#">港澳台</a></dd>
    <dd><a href="#">东南亚</a></dd>
    <dd class="red"><a href="#">美国</a></dd>  
    <dd><a href="#">法国</a></dd> 
</dl>
<dl class="kword">
	<dt>休闲</dt>
    <dd class="red"><a href="#">坚果炒货</a></dd>
    <dd><a href="#">肉类即食</a></dd>
    <dd><a href="#">糖果</a></dd>
    <dd><a href="#">巧克力</a></dd>  
    <dd><a href="#">布丁</a></dd> 
</dl>
<dl class="kword">
	<dt>瓜果</dt>
    <dd><a href="#">中国大陆</a></dd>
    <dd class="red"><a href="#">港澳台</a></dd>
    <dd><a href="#">东南亚</a></dd>
    <dd><a href="#">美国</a></dd>  
    <dd><a href="#">法国</a></dd> 
</dl>
<dl class="kword">
	<dt>品牌</dt>
    <dd><a href="#">中国大陆</a></dd>
    <dd class="red"><a href="#">港澳台</a></dd>
    <dd><a href="#">东南亚</a></dd>
    <dd><a href="#">美国</a></dd>  
    <dd><a href="#">法国</a></dd> 
</dl>
<dl class="kword">
	<dt>健康</dt>
    <dd><a href="#">中国大陆</a></dd>
    <dd class="red"><a href="#">港澳台</a></dd>
    <dd><a href="#">东南亚</a></dd>
    <dd><a href="#">美国</a></dd>  
    <dd><a href="#">法国</a></dd> 
</dl>
<dl class="kword">
	<dt>儿童</dt>
    <dd><a href="#">中国大陆</a></dd>
    <dd class="red"><a href="#">港澳台</a></dd>
    <dd><a href="#">东南亚</a></dd>
    <dd><a href="#">美国</a></dd>  
    <dd><a href="#">法国</a></dd> 
</dl>
</div>
</div>
</div>
<!-- 滑动图片及热搜 -->


<!-- 今日必尝 -->
<div class="hd_title">
<div class="hd">
<h2>今日必尝</h2>
<span class="more"><a href="#" class="gray">更多>></a></span>
</div>
</div>

<div class="todayBox">

<div class="bd clearfix">


<div class="foodBox">
<div class="fBox">
	<div class="foodPic">
		<a href="#"><img src="images/pic01.jpg" width="220" height="220" />
	</a></div>
	<p class="foodPrice">
	<em title="65.99">¥65.99</em>
	<span class="food-ave">17.80元/500g</span>
	</p>
	<p class="foodTitle">
	 <a href="#" target="_blank" title="最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮">最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮</a>
	</p>
	<p class="foodAction">
	    <a class="fav" href="#" title="收藏">10</a>
	    <a class="good" href="#" title="喜欢">20</a>
	    <a class="comment" href="#" title="评论">30</a>
	</p>
	<p class="foodAttrs hidden">
	<span><a href="#" title="友臣">友臣</a></span>
	<span>| <a href="#">中国大陆</a></span>
	</p>
    <p class="foodInfo hidden">天然绿色的环保材质，柔软舒适，提花的设计，低调中带有一丝靓丽，家中必备，我中必选	</p>
</div>
</div>




<div class="foodBox">
<div class="fBox">
	<div class="foodPic">
		<a href="#"><img src="images/pic02.jpg" width="220" height="220" />
	</a></div>
	<p class="foodPrice">
	<em title="65.99">¥65.99</em>
	<span class="food-ave">17.80元/500g</span>
	</p>
	<p class="foodTitle">
	 <a href="#" target="_blank" title="最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮">最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮</a>
	</p>
	<p class="foodAction">
	    <a class="fav" href="#" title="收藏">10</a>
	    <a class="good" href="#" title="喜欢">20</a>
	    <a class="comment" href="#" title="评论">30</a>
	</p>
	<p class="foodAttrs hidden">
	<span><a href="#" title="友臣">友臣</a></span>
	<span>| <a href="#">中国大陆</a></span>
	</p>
    <p class="foodInfo hidden">天然绿色的环保材质，柔软舒适，提花的设计，低调中带有一丝靓丽，家中必备，我中必选	</p>
</div>
</div>




<div class="foodBox">
<div class="fBox">
	<div class="foodPic">
		<a href="#"><img src="images/pic03.jpg" width="220" height="220" />
	</a></div>
	<p class="foodPrice">
	<em title="65.99">¥65.99</em>
	<span class="food-ave">17.80元/500g</span>
	</p>
	<p class="foodTitle">
	 <a href="#" target="_blank" title="最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮">最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮</a>
	</p>
	<p class="foodAction">
	    <a class="fav" href="#" title="收藏">10</a>
	    <a class="good" href="#" title="喜欢">20</a>
	    <a class="comment" href="#" title="评论">30</a>
	</p>
	<p class="foodAttrs hidden">
	<span><a href="#" title="友臣">友臣</a></span>
	<span>| <a href="#">中国大陆</a></span>
	</p>
    <p class="foodInfo hidden">天然绿色的环保材质，柔软舒适，提花的设计，低调中带有一丝靓丽，家中必备，我中必选	</p>
</div>
</div>




<div class="foodBox">
<div class="fBox">
	<div class="foodPic">
		<a href="#"><img src="images/pic04.jpg" width="220" height="220" />
	</a></div>
	<p class="foodPrice">
	<em title="65.99">¥65.99</em>
	<span class="food-ave">17.80元/500g</span>
	</p>
	<p class="foodTitle">
	 <a href="#" target="_blank" title="最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮">最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮</a>
	</p>
	<p class="foodAction">
	    <a class="fav" href="#" title="收藏">10</a>
	    <a class="good" href="#" title="喜欢">20</a>
	    <a class="comment" href="#" title="评论">30</a>
	</p>
	<p class="foodAttrs hidden">
	<span><a href="#" title="友臣">友臣</a></span>
	<span>| <a href="#">中国大陆</a></span>
	</p>
    <p class="foodInfo hidden">天然绿色的环保材质，柔软舒适，提花的设计，低调中带有一丝靓丽，家中必备，我中必选	</p>
</div>
</div>



<div class="foodBox">
<div class="fBox">
	<div class="foodPic">
		<a href="#"><img src="images/pic05.jpg" width="220" height="220" />
	</a></div>
	<p class="foodPrice">
	<em title="65.99">¥65.99</em>
	<span class="food-ave">17.80元/500g</span>
	</p>
	<p class="foodTitle">
	 <a href="#" target="_blank" title="最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮">最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮</a>
	</p>
	<p class="foodAction">
	    <a class="fav" href="#" title="收藏">10</a>
	    <a class="good" href="#" title="喜欢">20</a>
	    <a class="comment" href="#" title="评论">30</a>
	</p>
	<p class="foodAttrs hidden">
	<span><a href="#" title="友臣">友臣</a></span>
	<span>| <a href="#">中国大陆</a></span>
	</p>
    <p class="foodInfo hidden">天然绿色的环保材质，柔软舒适，提花的设计，低调中带有一丝靓丽，家中必备，我中必选	</p>
</div>
</div>



<div class="foodBox">
<div class="fBox">
	<div class="foodPic">
		<a href="#"><img src="images/pic06.jpg" width="220" height="220" />
	</a></div>
	<p class="foodPrice">
	<em title="65.99">¥65.99</em>
	<span class="food-ave">17.80元/500g</span>
	</p>
	<p class="foodTitle">
	 <a href="#" target="_blank" title="最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮">最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮</a>
	</p>
	<p class="foodAction">
	    <a class="fav" href="#" title="收藏">10</a>
	    <a class="good" href="#" title="喜欢">20</a>
	    <a class="comment" href="#" title="评论">30</a>
	</p>
	<p class="foodAttrs hidden">
	<span><a href="#" title="友臣">友臣</a></span>
	<span>| <a href="#">中国大陆</a></span>
	</p>
    <p class="foodInfo hidden">天然绿色的环保材质，柔软舒适，提花的设计，低调中带有一丝靓丽，家中必备，我中必选	</p>
</div>
</div>



<div class="foodBox">
<div class="fBox">
	<div class="foodPic">
		<a href="#"><img src="images/pic07.jpg" width="220" height="220" />
	</a></div>
	<p class="foodPrice">
	<em title="65.99">¥65.99</em>
	<span class="food-ave">17.80元/500g</span>
	</p>
	<p class="foodTitle">
	 <a href="#" target="_blank" title="最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮">最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮</a>
	</p>
	<p class="foodAction">
	    <a class="fav" href="#" title="收藏">10</a>
	    <a class="good" href="#" title="喜欢">20</a>
	    <a class="comment" href="#" title="评论">30</a>
	</p>
	<p class="foodAttrs hidden">
	<span><a href="#" title="友臣">友臣</a></span>
	<span>| <a href="#">中国大陆</a></span>
	</p>
    <p class="foodInfo hidden">天然绿色的环保材质，柔软舒适，提花的设计，低调中带有一丝靓丽，家中必备，我中必选	</p>
</div>
</div>



<div class="foodBox">
<div class="fBox">
	<div class="foodPic">
		<a href="#"><img src="images/pic08.jpg" width="220" height="220" />
	</a></div>
	<p class="foodPrice">
	<em title="65.99">¥65.99</em>
	<span class="food-ave">17.80元/500g</span>
	</p>
	<p class="foodTitle">
	 <a href="#" target="_blank" title="最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮">最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮</a>
	</p>
	<p class="foodAction">
	    <a class="fav" href="#" title="收藏">10</a>
	    <a class="good" href="#" title="喜欢">20</a>
	    <a class="comment" href="#" title="评论">30</a>
	</p>
	<p class="foodAttrs hidden">
	<span><a href="#" title="友臣">友臣</a></span>
	<span>| <a href="#">中国大陆</a></span>
	</p>
    <p class="foodInfo hidden">天然绿色的环保材质，柔软舒适，提花的设计，低调中带有一丝靓丽，家中必备，我中必选	</p>
</div>
</div>

</div>
</div>
<!-- 今日必赏 -->



<!-- 最受吃货欢迎 -->
<div class="hd_title">
<div class="hd">
<h2>最受吃货欢迎</h2>
<span class="type"><a href="#">巧克力</a><a href="#">糖果</a><a href="#">坚果</a><a href="#">水果</a><a href="#">进口食品</a><a href="#">巧克力</a><a href="#">糖果</a><a href="#">坚果</a><a href="#">水果</a><a href="#">进口食品</a></span>
<span class="more"><a href="#" class="gray">更多>></a></span>
</div>
</div>

<div class="topBox">

<div class="bd clearfix">

<div class="foodBox">
<div class="fBox">
	<div class="foodPic">
		<a href="#"><img src="images/pic01.jpg" width="220" height="220" />
	</a></div>
	<p class="foodPrice">
	<em title="65.99">¥65.99</em>
	<span class="food-ave">17.80元/500g</span>
	</p>
	<p class="foodTitle">
	 <a href="#" target="_blank" title="最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮">最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮</a>
	</p>
	<p class="foodAction">
	    <a class="fav" href="#" title="收藏">10</a>
	    <a class="good" href="#" title="喜欢">20</a>
	    <a class="comment" href="#" title="评论">30</a>
	</p>
	<p class="foodAttrs hidden">
	<span><a href="#" title="友臣">友臣</a></span>
	<span>| <a href="#">中国大陆</a></span>
	</p>
    <p class="foodInfo hidden">天然绿色的环保材质，柔软舒适，提花的设计，低调中带有一丝靓丽，家中必备，我中必选	</p>
</div>
</div>




<div class="foodBox">
<div class="fBox">
	<div class="foodPic">
		<a href="#"><img src="images/pic02.jpg" width="220" height="220" />
	</a></div>
	<p class="foodPrice">
	<em title="65.99">¥65.99</em>
	<span class="food-ave">17.80元/500g</span>
	</p>
	<p class="foodTitle">
	 <a href="#" target="_blank" title="最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮">最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮</a>
	</p>
	<p class="foodAction">
	    <a class="fav" href="#" title="收藏">10</a>
	    <a class="good" href="#" title="喜欢">20</a>
	    <a class="comment" href="#" title="评论">30</a>
	</p>
	<p class="foodAttrs hidden">
	<span><a href="#" title="友臣">友臣</a></span>
	<span>| <a href="#">中国大陆</a></span>
	</p>
    <p class="foodInfo hidden">天然绿色的环保材质，柔软舒适，提花的设计，低调中带有一丝靓丽，家中必备，我中必选	</p>
</div>
</div>




<div class="foodBox">
<div class="fBox">
	<div class="foodPic">
		<a href="#"><img src="images/pic03.jpg" width="220" height="220" />
	</a></div>
	<p class="foodPrice">
	<em title="65.99">¥65.99</em>
	<span class="food-ave">17.80元/500g</span>
	</p>
	<p class="foodTitle">
	 <a href="#" target="_blank" title="最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮">最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮</a>
	</p>
	<p class="foodAction">
	    <a class="fav" href="#" title="收藏">10</a>
	    <a class="good" href="#" title="喜欢">20</a>
	    <a class="comment" href="#" title="评论">30</a>
	</p>
	<p class="foodAttrs hidden">
	<span><a href="#" title="友臣">友臣</a></span>
	<span>| <a href="#">中国大陆</a></span>
	</p>
    <p class="foodInfo hidden">天然绿色的环保材质，柔软舒适，提花的设计，低调中带有一丝靓丽，家中必备，我中必选	</p>
</div>
</div>




<div class="foodBox">
<div class="fBox">
	<div class="foodPic">
		<a href="#"><img src="images/pic04.jpg" width="220" height="220" />
	</a></div>
	<p class="foodPrice">
	<em title="65.99">¥65.99</em>
	<span class="food-ave">17.80元/500g</span>
	</p>
	<p class="foodTitle">
	 <a href="#" target="_blank" title="最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮">最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮</a>
	</p>
	<p class="foodAction">
	    <a class="fav" href="#" title="收藏">10</a>
	    <a class="good" href="#" title="喜欢">20</a>
	    <a class="comment" href="#" title="评论">30</a>
	</p>
	<p class="foodAttrs hidden">
	<span><a href="#" title="友臣">友臣</a></span>
	<span>| <a href="#">中国大陆</a></span>
	</p>
    <p class="foodInfo hidden">天然绿色的环保材质，柔软舒适，提花的设计，低调中带有一丝靓丽，家中必备，我中必选	</p>
</div>
</div>



<div class="foodBox">
<div class="fBox">
	<div class="foodPic">
		<a href="#"><img src="images/pic05.jpg" width="220" height="220" />
	</a></div>
	<p class="foodPrice">
	<em title="65.99">¥65.99</em>
	<span class="food-ave">17.80元/500g</span>
	</p>
	<p class="foodTitle">
	 <a href="#" target="_blank" title="最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮">最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮</a>
	</p>
	<p class="foodAction">
	    <a class="fav" href="#" title="收藏">10</a>
	    <a class="good" href="#" title="喜欢">20</a>
	    <a class="comment" href="#" title="评论">30</a>
	</p>
	<p class="foodAttrs hidden">
	<span><a href="#" title="友臣">友臣</a></span>
	<span>| <a href="#">中国大陆</a></span>
	</p>
    <p class="foodInfo hidden">天然绿色的环保材质，柔软舒适，提花的设计，低调中带有一丝靓丽，家中必备，我中必选	</p>
</div>
</div>



<div class="foodBox">
<div class="fBox">
	<div class="foodPic">
		<a href="#"><img src="images/pic06.jpg" width="220" height="220" />
	</a></div>
	<p class="foodPrice">
	<em title="65.99">¥65.99</em>
	<span class="food-ave">17.80元/500g</span>
	</p>
	<p class="foodTitle">
	 <a href="#" target="_blank" title="最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮">最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮</a>
	</p>
	<p class="foodAction">
	    <a class="fav" href="#" title="收藏">10</a>
	    <a class="good" href="#" title="喜欢">20</a>
	    <a class="comment" href="#" title="评论">30</a>
	</p>
	<p class="foodAttrs hidden">
	<span><a href="#" title="友臣">友臣</a></span>
	<span>| <a href="#">中国大陆</a></span>
	</p>
    <p class="foodInfo hidden">天然绿色的环保材质，柔软舒适，提花的设计，低调中带有一丝靓丽，家中必备，我中必选	</p>
</div>
</div>



<div class="foodBox">
<div class="fBox">
	<div class="foodPic">
		<a href="#"><img src="images/pic07.jpg" width="220" height="220" />
	</a></div>
	<p class="foodPrice">
	<em title="65.99">¥65.99</em>
	<span class="food-ave">17.80元/500g</span>
	</p>
	<p class="foodTitle">
	 <a href="#" target="_blank" title="最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮">最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮</a>
	</p>
	<p class="foodAction">
	    <a class="fav" href="#" title="收藏">10</a>
	    <a class="good" href="#" title="喜欢">20</a>
	    <a class="comment" href="#" title="评论">30</a>
	</p>
	<p class="foodAttrs hidden">
	<span><a href="#" title="友臣">友臣</a></span>
	<span>| <a href="#">中国大陆</a></span>
	</p>
    <p class="foodInfo hidden">天然绿色的环保材质，柔软舒适，提花的设计，低调中带有一丝靓丽，家中必备，我中必选	</p>
</div>
</div>



<div class="foodBox">
<div class="fBox">
	<div class="foodPic">
		<a href="#"><img src="images/pic08.jpg" width="220" height="220" />
	</a></div>
	<p class="foodPrice">
	<em title="65.99">¥65.99</em>
	<span class="food-ave">17.80元/500g</span>
	</p>
	<p class="foodTitle">
	 <a href="#" target="_blank" title="最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮">最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮</a>
	</p>
	<p class="foodAction">
	    <a class="fav" href="#" title="收藏">10</a>
	    <a class="good" href="#" title="喜欢">20</a>
	    <a class="comment" href="#" title="评论">30</a>
	</p>
	<p class="foodAttrs hidden">
	<span><a href="#" title="友臣">友臣</a></span>
	<span>| <a href="#">中国大陆</a></span>
	</p>
    <p class="foodInfo hidden">天然绿色的环保材质，柔软舒适，提花的设计，低调中带有一丝靓丽，家中必备，我中必选	</p>
</div>
</div>




<div class="foodBox">
<div class="fBox">
	<div class="foodPic">
		<a href="#"><img src="images/pic01.jpg" width="220" height="220" />
	</a></div>
	<p class="foodPrice">
	<em title="65.99">¥65.99</em>
	<span class="food-ave">17.80元/500g</span>
	</p>
	<p class="foodTitle">
	 <a href="#" target="_blank" title="最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮">最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮</a>
	</p>
	<p class="foodAction">
	    <a class="fav" href="#" title="收藏">10</a>
	    <a class="good" href="#" title="喜欢">20</a>
	    <a class="comment" href="#" title="评论">30</a>
	</p>
	<p class="foodAttrs hidden">
	<span><a href="#" title="友臣">友臣</a></span>
	<span>| <a href="#">中国大陆</a></span>
	</p>
    <p class="foodInfo hidden">天然绿色的环保材质，柔软舒适，提花的设计，低调中带有一丝靓丽，家中必备，我中必选	</p>
</div>
</div>




<div class="foodBox">
<div class="fBox">
	<div class="foodPic">
		<a href="#"><img src="images/pic02.jpg" width="220" height="220" />
	</a></div>
	<p class="foodPrice">
	<em title="65.99">¥65.99</em>
	<span class="food-ave">17.80元/500g</span>
	</p>
	<p class="foodTitle">
	 <a href="#" target="_blank" title="最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮">最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮</a>
	</p>
	<p class="foodAction">
	    <a class="fav" href="#" title="收藏">10</a>
	    <a class="good" href="#" title="喜欢">20</a>
	    <a class="comment" href="#" title="评论">30</a>
	</p>
	<p class="foodAttrs hidden">
	<span><a href="#" title="友臣">友臣</a></span>
	<span>| <a href="#">中国大陆</a></span>
	</p>
    <p class="foodInfo hidden">天然绿色的环保材质，柔软舒适，提花的设计，低调中带有一丝靓丽，家中必备，我中必选	</p>
</div>
</div>




<div class="foodBox">
<div class="fBox">
	<div class="foodPic">
		<a href="#"><img src="images/pic03.jpg" width="220" height="220" />
	</a></div>
	<p class="foodPrice">
	<em title="65.99">¥65.99</em>
	<span class="food-ave">17.80元/500g</span>
	</p>
	<p class="foodTitle">
	 <a href="#" target="_blank" title="最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮">最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮</a>
	</p>
	<p class="foodAction">
	    <a class="fav" href="#" title="收藏">10</a>
	    <a class="good" href="#" title="喜欢">20</a>
	    <a class="comment" href="#" title="评论">30</a>
	</p>
	<p class="foodAttrs hidden">
	<span><a href="#" title="友臣">友臣</a></span>
	<span>| <a href="#">中国大陆</a></span>
	</p>
    <p class="foodInfo hidden">天然绿色的环保材质，柔软舒适，提花的设计，低调中带有一丝靓丽，家中必备，我中必选	</p>
</div>
</div>




<div class="foodBox">
<div class="fBox">
	<div class="foodPic">
		<a href="#"><img src="images/pic04.jpg" width="220" height="220" />
	</a></div>
	<p class="foodPrice">
	<em title="65.99">¥65.99</em>
	<span class="food-ave">17.80元/500g</span>
	</p>
	<p class="foodTitle">
	 <a href="#" target="_blank" title="最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮">最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮</a>
	</p>
	<p class="foodAction">
	    <a class="fav" href="#" title="收藏">10</a>
	    <a class="good" href="#" title="喜欢">20</a>
	    <a class="comment" href="#" title="评论">30</a>
	</p>
	<p class="foodAttrs hidden">
	<span><a href="#" title="友臣">友臣</a></span>
	<span>| <a href="#">中国大陆</a></span>
	</p>
    <p class="foodInfo hidden">天然绿色的环保材质，柔软舒适，提花的设计，低调中带有一丝靓丽，家中必备，我中必选	</p>
</div>
</div>



<div class="foodBox">
<div class="fBox">
	<div class="foodPic">
		<a href="#"><img src="images/pic05.jpg" width="220" height="220" />
	</a></div>
	<p class="foodPrice">
	<em title="65.99">¥65.99</em>
	<span class="food-ave">17.80元/500g</span>
	</p>
	<p class="foodTitle">
	 <a href="#" target="_blank" title="最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮">最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮</a>
	</p>
	<p class="foodAction">
	    <a class="fav" href="#" title="收藏">10</a>
	    <a class="good" href="#" title="喜欢">20</a>
	    <a class="comment" href="#" title="评论">30</a>
	</p>
	<p class="foodAttrs hidden">
	<span><a href="#" title="友臣">友臣</a></span>
	<span>| <a href="#">中国大陆</a></span>
	</p>
    <p class="foodInfo hidden">天然绿色的环保材质，柔软舒适，提花的设计，低调中带有一丝靓丽，家中必备，我中必选	</p>
</div>
</div>



<div class="foodBox">
<div class="fBox">
	<div class="foodPic">
		<a href="#"><img src="images/pic06.jpg" width="220" height="220" />
	</a></div>
	<p class="foodPrice">
	<em title="65.99">¥65.99</em>
	<span class="food-ave">17.80元/500g</span>
	</p>
	<p class="foodTitle">
	 <a href="#" target="_blank" title="最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮">最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮</a>
	</p>
	<p class="foodAction">
	    <a class="fav" href="#" title="收藏">10</a>
	    <a class="good" href="#" title="喜欢">20</a>
	    <a class="comment" href="#" title="评论">30</a>
	</p>
	<p class="foodAttrs hidden">
	<span><a href="#" title="友臣">友臣</a></span>
	<span>| <a href="#">中国大陆</a></span>
	</p>
    <p class="foodInfo hidden">天然绿色的环保材质，柔软舒适，提花的设计，低调中带有一丝靓丽，家中必备，我中必选	</p>
</div>
</div>



<div class="foodBox">
<div class="fBox">
	<div class="foodPic">
		<a href="#"><img src="images/pic07.jpg" width="220" height="220" />
	</a></div>
	<p class="foodPrice">
	<em title="65.99">¥65.99</em>
	<span class="food-ave">17.80元/500g</span>
	</p>
	<p class="foodTitle">
	 <a href="#" target="_blank" title="最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮">最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮</a>
	</p>
	<p class="foodAction">
	    <a class="fav" href="#" title="收藏">10</a>
	    <a class="good" href="#" title="喜欢">20</a>
	    <a class="comment" href="#" title="评论">30</a>
	</p>
	<p class="foodAttrs hidden">
	<span><a href="#" title="友臣">友臣</a></span>
	<span>| <a href="#">中国大陆</a></span>
	</p>
    <p class="foodInfo hidden">天然绿色的环保材质，柔软舒适，提花的设计，低调中带有一丝靓丽，家中必备，我中必选	</p>
</div>
</div>



<div class="foodBox">
<div class="fBox">
	<div class="foodPic">
		<a href="#"><img src="images/pic08.jpg" width="220" height="220" />
	</a></div>
	<p class="foodPrice">
	<em title="65.99">¥65.99</em>
	<span class="food-ave">17.80元/500g</span>
	</p>
	<p class="foodTitle">
	 <a href="#" target="_blank" title="最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮">最新日期 友臣金丝肉松饼整箱5斤 拍套餐更给力 北京包邮</a>
	</p>
	<p class="foodAction">
	    <a class="fav" href="#" title="收藏">10</a>
	    <a class="good" href="#" title="喜欢">20</a>
	    <a class="comment" href="#" title="评论">30</a>
	</p>
	<p class="foodAttrs hidden">
	<span><a href="#" title="友臣">友臣</a></span>
	<span>| <a href="#">中国大陆</a></span>
	</p>
    <p class="foodInfo hidden">天然绿色的环保材质，柔软舒适，提花的设计，低调中带有一丝靓丽，家中必备，我中必选	</p>
</div>
</div>



</div>
</div>





<!-- 最受吃货欢迎 -->


<!-- 更多品牌 -->

<!-- <div class="brandBox">
<div class="hd">
<h3>最受吃货欢迎</h3>
<span class="more"><a href="#" class="gray">更多>></a></span>
</div>
<div class="bd">
</div>
</div> -->

<!-- 更多品牌 -->

<div style="display:none;" id="gotopbtn" class="to_top"><a title="返回顶部" href="javascript:void(0);"></a></div>
<!-- 底部结构 -->

<div class="footer">
<div class="footBox">
<div class="botMenu"><a href="list.html">关于我们</a> | <a href="list.html">加入我们</a> | <a href="list.html">免责声明</a> | <a href="list.html">帮助中心</a> | <a href="list.html">加入我们</a> | <a href="list.html">免责声明</a> | <a href="list.html">帮助中心</a></li>
</ul>
<!--<ul class="link">
	<li>友情链接：</li>
	<li><a href="list.html">泡椒网</a></li>
	<li><a href="list.html">泡椒网</a></li>
    <li><a href="list.html">泡椒网</a></li>
    <li><a href="list.html">泡椒网</a></li>
    <li><a href="list.html">泡椒网</a></li>
    <li><a href="list.html">泡椒网</a></li>
    <li><a href="list.html">泡椒网</a></li>
    <li><a href="list.html">泡椒网</a></li>
    <li><a href="list.html">泡椒网</a></li>
    <li><a href="list.html">泡椒网</a></li>
    <li><a href="list.html">泡椒网</a></li>
    <li><a href="list.html">泡椒网</a></li>
    <li><a href="list.html">泡椒网</a></li>
    <li><a href="list.html">泡椒网</a></li>
    <li><a href="list.html">泡椒网</a></li>
    <li><a href="list.html">泡椒网</a></li>
    <li><a href="list.html">泡椒网</a></li>
    <li><a href="list.html">泡椒网</a></li>
    <li><a href="list.html">泡椒网</a></li>
    <li><a href="list.html">泡椒网</a></li>
    <li><a href="list.html">泡椒网</a></li>
    <li><a href="list.html">泡椒网</a></li>
    <li><a href="list.html">泡椒网</a></li>
    <li><a href="list.html">泡椒网</a></li>
</ul>-->
<div class="copyright">©Copyright by 77吃 2010-2013 77chi.com, all rights reserved <span class="fr">备案号：浙ICP备05034519号</span></div>
</div>
</div>
<!-- 底部结构 -->
</body>
</html>