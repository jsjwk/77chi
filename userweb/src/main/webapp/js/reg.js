$(function(){
	
	// 注册验证
	$("#userMail").blur(checkUserMail);// 用户名文本框失去焦点触发验证事件
	$("#userMail").blur(checkPas1);// 用户名文本框失去焦点触发验证事件
	
});

function checkUserMail(){
    if(!$("#userMail").val()){
		$("#userMail").next().next(".yesBox").hide();
		$("#userMail").parent().next(".error").show().html("邮箱不能为空！");
    }
	else if(!$("#userMail").val().match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)){
		$("#userMail").next().next(".yesBox").hide();
		$("#userMail").parent().next(".error").show().html("邮箱格式不正确！");
	}else{
		$("#userMail").parent().next(".error").hide();
		$("#userMail").siblings(".yesBox").show();
		return true;
    }
    return false;
}

function checkPas1(){
    if(!$("#userMail").val()){
		$("#userMail").next(".yesBox").hide();
        $("#userMail").parent().next(".error").show().html("密码不能为空！");
    }else if($("#userMail").val().length<6){
		$("#userMail").next(".yesBox").hide();
		$("#userMail").parent().next(".error").show().html("输入的密码过短，请重新输入！");
	}else if($("#userMail").val().length>16){
		$("#userMail").next(".yesBox").hide();
		$("#userMail").parent().next(".error").show().html("输入的密码过长，请重新输入！");	
	}else{
        $("#userMail").parent().next(".error").hide();
		$("#userMail").next(".yesBox").show();
		return true;
    }
    return false;
}

function checkForm()
{
	if(!checkUserMail() || !checkPas1()){
		return false;
	}
	return true;
}

