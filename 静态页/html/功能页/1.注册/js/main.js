$(function(){
/*垂直居中*/
middle();

/*帐号密码清空*/
empty_input();
})

/*垂直居中*/
function middle(){
var body_hight = document.documentElement.clientHeight-50;
var margin_top = (body_hight - $(".md").height())/2 + "px";
$(".md").css("margin-top",margin_top);	
}

/*帐号密码清空*/
function empty_input(){
var d = $("#username, #password");
d.focusin(function(i) {
	$(this).siblings("i").css("display", "none");
	/*更换背景*/
	$(this).css("background-position","0px -66x");
});
d.focusout(function(i) {
	if ($.trim(this.value) == "") {
		$(this).siblings("i").css("display", "block");
		/*更换背景*/
		$(this).css("background-position","0px -27x");
	}
});
}