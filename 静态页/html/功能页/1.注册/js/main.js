$(function(){
/*垂直居中*/
middle();

/*帐号密码清空*/
empty_input();

/*初始化输入框*/
init_input();
})

/*垂直居中*/
function middle(){
var body_hight = document.documentElement.clientHeight-50;
var margin_top = (body_hight - $(".md").height())/2 + "px";
$(".md").css("margin-top",margin_top);	
}

/*帐号密码清空*/
function empty_input(){

$('.login input').focus(function() {
	$(this).siblings("i").css("display", "none");
	/*更换背景*/
	$(this).parent(".i").css("background-position","0px -66px");
});
$('.login input').blur(function() {
	if ($.trim(this.value) == "") {
		$(this).siblings("i").css("display", "block");	
	}
	/*更换背景*/
	$(this).parent(".i").css("background-position","0px -27px");
});
}

/*初始化输入框*/
function init_input(){
	/*IE下按F5,文本框不会被清空,密码框会被清空; Chrome都会清空.*/
	if ($.trim($("#un").val()) == "") {
		$("#un").siblings("i").css("display", "block");	
	}else{
		$("#un").siblings("i").css("display", "none");	
	}
}