
window.onload=function () {
	
	$("a").attr("onfocus","this.blur()");
	$("area").attr("onfocus","this.blur()");
	// $("body").css("overflow-x","hidden");
	$("html").css("overflow-x","hidden");
	$("body").addClass("leftBg");
	
//    var FixSideBar=document.getElementById('root');
//    FixSideBar.style.height=document.documentElement.clientHeight+'px'

	var FixAutoHeight = $(document).height();
//	alert(FixAutoHeight);
	$(".FixLayout").css('height', FixAutoHeight);
	
	
};


function SetHeight(){
	
	var FixAutoHeight = $(document).height();
//	alert(FixAutoHeight);
	$(".FixLayout").css('height', FixAutoHeight);
}



function FixShowSubMenu(MenuId){
	
	var Control_FirstMenuId = "f_"+MenuId;
	var Control_FirstMenu_AId = "a_"+MenuId;
	var Control_SecondMenu_Id = "div_"+MenuId;
	
	if($("#"+Control_SecondMenu_Id).css("display")=="none")
	{
		$(".FixFirstMenu").css("border-bottom","none");
		$(".FixSecondMenu").hide();
		$(".FixSpanMenu").css("background-position","-26px -21px").show();
		
	  $("#"+Control_FirstMenuId).css("border-bottom","1px solid #b1cde2");
	  $("#"+Control_FirstMenu_AId).find("span").css("background-position","-26px 0px").show();
	  $("#"+Control_SecondMenu_Id).show();
	  // 默认展开第一个子菜单
	  $("#"+Control_SecondMenu_Id+" a")[0].click();
	}
	else{
	  $("#"+Control_FirstMenuId).css("border-bottom","none");
	  $("#"+Control_FirstMenu_AId).find("span").css("background-position","-26px -21px").show();
	  $("#"+Control_SecondMenu_Id).hide();
	}


	
}