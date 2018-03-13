

window.onload=function () {
	
//	$("body").css("overflow-x","hidden");
//	$("html").css("overflow-x","hidden");

	$("a").attr("onfocus","this.blur()")

	
	SetAutoLayout();
	onresize=SetAutoLayout;



}
function SetAutoLayout(){
	
	
//	var FixDownloadPageCenterWidth = $(window).width() - 115 - 115;
//	
//
//
//	$(".download .top .center").css('width',FixDownloadPageCenterWidth + 'px');
	
	
}



function HideOverflow(){
	
	$("body").css("overflow-x","hidden");
	$("html").css("overflow-x","hidden");
	$("body").css("overflow-y","hidden");
	$("html").css("overflow-y","hidden");


}
