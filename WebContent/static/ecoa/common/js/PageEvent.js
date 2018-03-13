

window.onload=function () {
	
//	$("body").css("overflow-x","hidden");
//	$("html").css("overflow-x","hidden");

	$("a").attr("onfocus","this.blur()");
		
    var paras1=$(".FixFirstMenu");
    var paras2=$(".FixSecondMenu");
	
    paras1.first().css("border-bottom","1px solid #b1cde2");
	paras1.first().find(".SpanMenu").css("background-position","-543px -115px").show(370);
	
	paras2.first().css('display','block');

	
	
	
	SetOpenPageHeight();
	onresize=SetOpenPageHeight;



};

function SetOpenPageHeight(){

//	var FixAutoHeight = $(document).height();
//	$(".FrameLeft").css('height','');
//	$(".FrameLeft").css('height', FixAutoHeight + 'px');

	var FixOpenCenterHeight= $(window).height() - $("#OpenTopDiv").height() - $("#OpenBottomDiv").height() - 2;
	var FixOpenCenterHeight2= $(window).height() - $("#OpenTopDiv").height() - 2;

	$("#OpenCenterDiv").css('height',FixOpenCenterHeight + 'px');
    $("#targetFrameContral").css('height',FixOpenCenterHeight2 + 'px');
    
    var FixFrameWidth = $(document).width() - 270;
    $("#rightContral").css('width',FixFrameWidth + 'px');
    
//	$("body").css("overflow-y","hidden");$("html").css("overflow-y","hidden");

	var CataWidth = parseInt($("#CataList").css('width'));

	if(document.getElementById("CataList")!=undefined){
     	var FixAutoWidth = $(document).width() - CataWidth - 40;
		$("#RightForm").css('width', FixAutoWidth + 'px');
    }
	else{  
	    $("#RightForm").css('width', '100%');
	}

    return ;
}

//function SetHeight(){
//	
//	var FixAutoHeight = $(document).height();
////	alert(FixAutoHeight);
//	$(".FrameLeft").css('height', FixAutoHeight);
//}
//
//function SetWidth(){
//	
//	var FixAutoWidth = $(document).width() - 251;
////	alert(FixAutoHeight);
//	$("#RightForm").css('width', FixAutoWidth);
//}

function ShowLeftSubMenu(MenuId){
	
	
	var Control_FirstMenuId = "f_"+MenuId;
	var Control_FirstMenu_AId = "a_"+MenuId;
	var Control_SecondMenu_Id = "div_"+MenuId;
	
	//	alert(Control_FirstMenuId+Control_SecondMenu_Id);
	
	if($("#"+Control_SecondMenu_Id).css("display")=="none")
	{
		
	    $(".FirstMenu").css("border-bottom","none");
		$(".SecondMenu").hide(370);
		$(".SpanMenu").css("background-position","-542px -132px").show(370);
		
	  $("#"+Control_FirstMenuId).css("border-bottom","1px solid #b1cde2");
	  $("#"+Control_FirstMenu_AId).find("span").css("background-position","-543px -115px").show(370);
	  $("#"+Control_SecondMenu_Id).show(370);
	  
	}
	else{
	  $("#"+Control_FirstMenuId).css("border-bottom","none");
	  $("#"+Control_FirstMenu_AId).find("span").css("background-position","-542px -132px").show(370);
	  $("#"+Control_SecondMenu_Id).hide(370);
	}

}



$(function() {
	$(".curr").click();
});

function Details(name, cursel, n) {	
	for (var i = 1; i <= n; i++) {
		var menu = document.getElementById(name + i);
		var con = document.getElementById("con_" + name + "_" + i);
		menu.className = i == cursel ? "curr" : "";
		con.style.display = i == cursel ? "block" : "none";
	}
}


function SetFileUrlValue(tarBase,tarGet){
	
	var CurVal = $("#"+tarBase).val();
	$("#"+tarGet).val(CurVal);
}


function WinPage(tarUrl,tarHeight,tarWidth,tarBar){
	

    window.open (tarUrl,'','height=' + tarHeight + ',width=' + tarWidth + ',top=20,left=150,toolbar=no,menubar=no,scrollbars=' + tarBar + ',resizable=yes,location=no,status=no') ;
	return false;
	
//window.open 弹出新窗口的命令； 
//page.html 弹出新窗口的文件名； 
//newwindow 弹出窗口的名字（不是文件名），可用空 ″代替； 
//height=100 窗口高度； 
//top=0 窗口距离屏幕上方的像素值； 
//left=0 窗口距离屏幕左侧的像素值； 
//toolbar=no 是否显示工具栏，yes为显示； 
//menubar,scrollbars 表示菜单栏和滚动栏； 
//resizable=no 是否允许改变窗口大小，yes为允许； 
//location=no 是否显示地址栏，yes为允许； 
//status=no 是否显示状态栏内的信息（通常是文件已经打开），yes为允许； 

}



function HideDiv(tarName){

  $("#"+ tarName).hide(370);
  
}



function WinClose(){
	
//    this.window.opener = null;  window.close();  
	  window.opener=null;    
	  //window.opener=top;    
	  window.open("","_self");    
	  window.close();    
}


/**
 * 刷新父窗口并关闭自身
 */
function WinCloseAndFlushParent(){
	// 刷新父窗口
	window.opener.location.reload(); 
	//关闭自身
	window.close();    
}




function HideOverflow(){
	
	$("body").css("overflow-x","hidden");
	$("html").css("overflow-x","hidden");
	$("body").css("overflow-y","hidden");
	$("html").css("overflow-y","hidden");


}
