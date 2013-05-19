$(function(){
	$(".ipttxt").focus(function(){
		$(this).css("background","#faffbd");
	});
	$(".ipttxt").blur(function(){
		$(this).css("background","");
	});
});

each(document.querySelectorAll('#passWord'),function(){
	var textVal = this.value;
	this.onfocus = function(){
		
		if (this.value == textVal ){
			this.value = "";
			this.type = "password";
			this.style.color="#000"
		}
			
	};	
	this.onblur = function(){
		if (this.value == "" ){
			this.value = textVal;
			this.type = "text";
			this.removeAttribute("style");
		}else {
			this.type = "password";
			this.style.color="#000"
		}
	};
	
	
});

// 数组循环操作
function each(Aobj,fn){
	if(!Aobj||!Aobj.length||typeof fn !== 'function')return;
	for(var i=0,j=Aobj.length;i<j;i++){
		fn.call(Aobj[i],i);
	}
}
