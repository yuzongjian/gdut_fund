// JavaScript Document


//初始化
$(document).ready(function(){

	$("body").css("overflow-x","hidden");
	$("html").css("overflow-x","hidden");
	
	$("a").attr("onfocus","this.blur()")


//菜单栏修正

    $(".TopMenu ul li:first-child ").addClass("FixFirstBorder");
	$(".TopMenu ul li:last-child").addClass("FixLastWidth");
	$(".TopMenu ul li:last-child").find("div").addClass("FixLastDiv");
	
	$(".TopMenu ul li").each(function() {
	   $(this).hover(function() {
		   $(".TopMenu ul li").find("div").hide();
		   $(this).find("div").show();
	   },
	function() {
       //no active
	});
	});
	
	
	$(".TopMenu ul li div").each(function() {
	   $(this).hover(function() {
		   $(this).show();
	   },
	function() {
		   $(this).hide();
	});
	});
	


}); //结束





function ShowMutliDiv(n) {

var CurSpan = "MutliSpan_"+ n;
var CurDiv = "MutliDiv_"+ n;

$(".lay4 .nlong .top").find("span").removeClass("curr");
$(".lay4 .nlong .inner").hide();

$("#"+CurSpan).addClass("curr");
$("#"+CurDiv).show();


}












