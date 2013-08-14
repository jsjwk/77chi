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
var d = $("#un, #ps");
d.focusin(function(i) {
	$(this).siblings("i").css("display", "none");
	/*更换背景*/
	$(this).parent(".i").css("background-position","0px -66px");
});
d.focusout(function(i) {
	/*if ($.trim(this.value) == "") {
		$(this).siblings("i").css("display", "block");	
	}*/
	change_dis(this.value);
	/*更换背景*/
	$(this).parent(".i").css("background-position","0px -27px");
});
}

/*初始化输入框*/
function init_input(){
	var v = $("#un").val();
	change_dis(v);
	v = $("#pw").val();
	change_dis(v);
}

function change_dis(v){
	if($.trim(v)==""){
		$(this).siblings("i").css("display", "block");	
	}else{
		$(this).siblings("i").css("display", "none");	
	}
}