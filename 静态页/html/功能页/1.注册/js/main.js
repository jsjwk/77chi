$(function(){
//垂直居中
var body_hight = document.documentElement.clientHeight;
var margin_top = (body_hight - $(".reg_md").height())/2 + "px";
$(".reg_md").css("margin-top",margin_top);
})