
/* 	
使用说明
	使用显示/隐藏树形结构操作  先引入 对应的showZtree.js 和 showZtree.css
		在页面中新增 
		<div  id="lookCataListRight" >
			<a href="javascript:void(0)" onclick="showTreeDiv('2')" >&lt;</a>
			</div>
		<div  id="lookCataListleft">
			<a href="javascript:void(0)" onclick="showTreeDiv('1')" >&gt;</a>
		</div>
		
	如果不使用显示/隐藏树形结构操作
	//js文件中会先显示 ，在这里会改为 不使用此功能
	hideTreeDiv();
*/


var oldFromWith = "";

$(function(){
	$("#CataList").css("display", "inline");
	$("#lookCataListRight").css("display", "inline");
	$("#lookCataListleft").css("display", "none");
	oldFromWith =  $("#RightForm").width();
});

/**
 * 使用显示/隐藏树形结构操作  
 * @param flag 1 : 显示树形操作  2：隐藏树形操作
 */
function showTreeDiv(flag)
{
	//显示
	if(flag == "1")
	{
		$("#CataList").css("display", "inline");
		$("#lookCataListRight").css("display", "inline");
		$("#lookCataListleft").css("display", "none");
		$("#RightForm").css("width",oldFromWith+"px");
	}else
	{
		$("#CataList").css("display", "none");
		$("#lookCataListRight").css("display", "none");
		$("#lookCataListleft").css("display", "inline");
		var fromWidth = $(".top").width();
		$("#RightForm").css("width",fromWidth);
	}
}

/**
 * 不使用显示/隐藏树形结构操作  
 */
function hideTreeDiv()
{
	$("#CataList").css("display", "inline");
	$("#lookCataListRight").css("display", "none");
	$("#lookCataListleft").css("display", "none");
}




