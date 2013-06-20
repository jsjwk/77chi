$(function(){
	
	//注册验证
	$("#userMail").blur(function(){//用户名文本框失去焦点触发验证事件
        if(!$(this).val()){
			$(this).next().next(".yesBox").hide();
			$(this).parent().next(".error").show().html("邮箱不能为空！");
        }
		else if(!$(this).val().match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)){
			$(this).next().next(".yesBox").hide();
			$(this).parent().next(".error").show().html("邮箱格式不正确！");
		}else{
			$(this).parent().next(".error").hide();
			$(this).siblings(".yesBox").show();
        }

     });
	 
	 $("#pas1").blur(function(){//用户名文本框失去焦点触发验证事件
        if(!$(this).val()){
			$(this).next(".yesBox").hide();
            $(this).parent().next(".error").show().html("密码不能为空！");
        }else if($(this).val().length<6){
			$(this).next(".yesBox").hide();
			$(this).parent().next(".error").show().html("输入的密码过短，请重新输入！");
		}else if($(this).val().length>16){
			$(this).next(".yesBox").hide();
			$(this).parent().next(".error").show().html("输入的密码过长，请重新输入！");	
		}else{
            $(this).parent().next(".error").hide();
			$(this).next(".yesBox").show();
        }

     });
     
	
});

