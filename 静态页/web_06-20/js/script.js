// JavaScript Document
//图片定高宽>
var flag=false;
function DrawImage(ImgD,w,h){
var image=new Image();
var iwidth = w;
var iheight = h; //定义允许高度，当宽度大于这个值时等比例缩小
image.src=ImgD.src;
if(image.width>0 && image.height>0){
flag=true;
if(image.width/image.height>= iwidth/iheight){
   if(image.width>iwidth){ 
    ImgD.width=iwidth;
    ImgD.height=(image.height*iwidth)/image.width;
   }else{
    ImgD.width=image.width; 
    ImgD.height=image.height;
   }
}else{
   if(image.height>iheight){ 
    ImgD.height=iheight;
    ImgD.width=(image.width*iheight)/image.height; 
   }else{
    ImgD.width=image.width; 
    ImgD.height=image.height;
   }
}
}
} 
//调用：<img src="图片" onload="javascript:DrawImage(this)">

$(function(){
	
	$(".shopCart").mouseover(function(){
		$(this).addClass("hover");	
	});
	$(".shopCart").mouseout(function(){
		$(this).removeClass("hover");	
	});
	
	$(".login").mouseover(function(){
		$(this).addClass("hover");	
	});
	$(".login").mouseout(function(){
		$(this).removeClass("hover");	
	});
	
	$(".loginIn").mouseover(function(){
		$(this).addClass("hover");	
	});
	$(".loginIn").mouseout(function(){
		$(this).removeClass("hover");	
	});
	
	$(".ipttxt").focus(function(){
		$(this).css("background","#faffbd");
	});
	$(".ipttxt").blur(function(){
		$(this).css("background","");
	});
	
	$(".foodBox").mouseover(function(){
		$(".hidden",this).show();
		$(".fBox",this).css({
			zIndex:"7"
		})	
	});
	$(".foodBox").mouseout(function(){
		$(".hidden",this).hide();
		$(".fBox",this).css({
			zIndex:"1"
		})	
	});


	/*浮动效果*/
	var l = $(".topMenu");
	var t = $(".topNav");
	var b = $(".topBar");
	$(window).scroll(function(E) {
						  
		var D = $(this),
		G = D.scrollTop(),
		C = t.height()+b.height();

		if (G < C) {
			l.css({
				position: "relative"
			})
		} else {
			l.css({
				position: "fixed",zIndex:"9"
			})
		}

	});
	//注册验证
	$("#userMail").blur(function(){//用户名文本框失去焦点触发验证事件
        if(!$(this).val()){
			$(this).next(".yesBox").hide();
			$(this).parent().next(".error").show().removeClass("blue").addClass("red").html("邮箱不能为空");
        }
		else if(!$(this).val().match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)){
			$(this).next(".yesBox").hide();
			$(this).parent().next(".error").show().removeClass("blue").addClass("red").html("邮箱格式不正确");
		}else{
			$(this).parent().next(".error").hide();
			$(this).next(".yesBox").show();
        }

     });
	 
	 $("#pas1").blur(function(){//用户名文本框失去焦点触发验证事件
        if(!$(this).val()){
			$(this).next(".yesBox").hide();
            $(this).parent().next(".error").show().removeClass("blue").addClass("red").html("密码不能为空");
        }else if(!$(this).val().match(/^[0-9a-zA-Z]*$/)){
			$(this).next(".yesBox").hide();
			$(this).parent().next(".error").show().removeClass("blue").addClass("red").html("密码只能为英文或者数字");		
		}else if($(this).val().length<6){
			$(this).next(".yesBox").hide();
			$(this).parent().next(".error").show().removeClass("blue").addClass("red").html("输入的密码过短，请重新输入！");
		}else if($(this).val().length>16){
			$(this).next(".yesBox").hide();
			$(this).parent().next(".error").show().removeClass("blue").addClass("red").html("输入的密码过长，请重新输入！");	
		}else{
            $(this).parent().next(".error").hide();
			$(this).next(".yesBox").show();
        }

     });
     $("#pas2").blur(function(){//用户名文本框失去焦点触发验证事件
	 	if(!$(this).val()){
			$(this).next(".yesBox").hide();
            $(this).parent().next(".error").show().removeClass("blue").addClass("red").html("密码不能为空");
        }else if(!$(this).val().match(/^[0-9a-zA-Z]*$/)){
			$(this).next(".yesBox").hide();
			$(this).parent().next(".error").show().removeClass("blue").addClass("red").html("密码只能为英文或者数字");		
		}else if($(this).val().length<6){
			$(this).next(".yesBox").hide();
			$(this).parent().next(".error").show().removeClass("blue").addClass("red").html("输入的密码过短，请重新输入！");
		}else if($(this).val().length>16){
			$(this).next(".yesBox").hide();
			$(this).parent().next(".error").show().removeClass("blue").addClass("red").html("输入的密码过长，请重新输入！");	
		}else if($(this).val() != $("#pas1").val() ){
            $(this).next(".yesBox").hide();
			$(this).parent().next(".error").show().removeClass("blue").addClass("red").html("密码与上面的密码不一致");			
		}else{
            $(this).parent().next(".error").hide();
			$(this).next(".yesBox").show();
        }

     });
	 

	$(window).scroll(function(){
		$(window).scrollTop()>1000 ? $("#gotopbtn").css('display','').click(function(){
			$(window).scrollTop(0);
		}):$("#gotopbtn").css('display','none');	
	});
	
	$(".proBox:first").show();
	$(".picUl li").mouseover(function(){
		$(this).siblings("li").removeClass("curr");	
		$(this).addClass("curr");	
		$(".proBox").hide();
		$(".proBox").eq($(".picUl li").index(this)).show();
	});

	
});

