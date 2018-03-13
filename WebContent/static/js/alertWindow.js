/**
 * 打开新窗口open方式
 * @param url 窗口显示页面的地址
 * @param width 窗口宽度
 * @param heigth 窗口高度
 */
function openNewWindow(url,width,heigth){
	var top = (screen.height-heigth)/2;
	var left = (screen.width-width)/2;
	window.open(url, 'newwindow', 'height='+heigth+', width='+width+', top='+top+', left='+left
			+', toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no'); 
}

function openNewWindow1(url,width,heigth){
	var top = (screen.height-heigth)/2;
	var left = (screen.width-width)/2;
	window.open(url, 'newwindow1', 'height='+heigth+', width='+width+', top='+top+', left='+left
			+', toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no'); 
}

/**
 * 关闭本窗口，刷新父窗口
 */
function closeReflush(){
	// 如果父窗口存在并且没有关闭 刷新
	if(window.opener && !window.opener.closed){
		// 刷新父窗口
		window.opener.location.reload(); 
	}
	//关闭自身
    self.close(); 
}  





/**
 * 在子窗口中 使用编辑、更新、添加使用Ajax提交到URL，并且放回提示信息关闭子窗口,并刷新父窗口
 * @param url 提交地址URL
 * @param data 提交数据
 * @param method 提交方式 
 * 提示：使用此方法前提 是 Controller必须得支持json格式   
 * 例如：@RequestMapping(value = "update", produces = "application/json; charset=utf-8", method = RequestMethod.POST)@ResponseBody 
 */
function ajaxToSave(url,data,method)
{
	$.ajax({
		cache: true,
		type: method,
		url:url,
		//data:$('#inputForm').serialize(),// 你的formid
		data:data,
		async: false,
	    error: function(request) 
	    {
	        alert("Connection error");
	    },
	    success: function(data) 
	    {
	    	if (data)
	    	{
	    		//使用隐藏域中的id  来判别操作是添加操作  还是更新操作
	    		if ($("#id").val() == "" ||  $("#id").val() == "-1")
		    	{
	    			alert("添加成功");
	    		}else{
	    			alert("更新成功");
	    		}
	    		closeReflush();
	    	}else
	    	{
	    		if ($("#id").val() == "" ||  $("#id").val() == "-1")
		    	{
	    			alert("添加失败");
	    		}else{
	    			alert("更新失败");
	    		}	
	    	}
	    	
	    }
	});

}



function OpenModalDialog(Url,Width,Height,WindowObj){
	var returnStr = window.showModalDialog(Url,WindowObj,'dialogWidth:'+Width+'pt;dialogHeight:'+Height+'pt;status:no;help:no;scroll:yes;');
	return returnStr;
}