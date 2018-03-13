/**
*公共页面函数类，用过操作表单的函数，如保存，删除，编辑等
*/
FormFunction = {
/**
	*总的入口
	*eventType 操作类型
	* width 弹出窗口的宽，为空时取系统配置的默认值，
	* height 弹出窗口的高，为空时取系统配置的默认值，
	* V_openType 页面打开模式(direct 直接进去, pop 弹出窗口)，为空时取系统配置的默认值，
	* 其中 width height 只在 页面打开模式(openType=pop)是弹出模式时才有效
	*/
	defaultEvent:function(eventType,width,height,V_openType) {
		switch(eventType) {
			case 'add':
				FormFunction.add(addUrl,width,height,V_openType);
				break;
			case 'edit':
				FormFunction.edit(editUrl,width,height,V_openType);
				break;
			case 'view':
				FormFunction.view(viewUrl,width,height,V_openType);
				break;
			case 'search':
				FormFunction.search(listUrl);
				break;
			case 'save':
				FormFunction.save(saveUrl);
				break;
			case 'del':
				FormFunction.del(delUrl);
				break;
			case 'exit':
				FormFunction.exit(listUrl,V_openType);
				//FormFunction.exit("",V_openType);
				break;
			case 'saveo':
			    FormFunction.save(saveOUrl);
			    break;
			case 'delo':
				FormFunction.del(delOUrl);
				break;
			case 'savement':
			    FormFunction.savement(saveUrl);
			    break;
			case 'editment':
			    FormFunction.editment(saveUrl,flag);
			    break;
			default:
				alert("类型："+eventType+"没定义");
		}
	
	},

   customOperation:function(url,width,height,V_openType) {
       if(!V_openType) {//如果没设此参数，就用系统默认的
			V_openType = openType;
		}
   		if(V_openType==1) {//普通方式打开,直接进入
			parent.url = listUrl;
			window.location.href=url;
		} else {//弹出方式
			var tempWidth = width;
			if(!tempWidth) {
				tempWidth = 800;
			}
			var tempHeight = height;
			if(!tempHeight) {
				tempHeight = 600;
			}
			openLink(url,tempWidth,tempHeight);
		}
	},
    
/**
	*新增
	*url 跳转页面的url
	* width 弹出窗口的宽，为空时取系统配置的默认值，
	* height 弹出窗口的高，为空时取系统配置的默认值，
	* V_openType 页面打开模式(direct 直接进去, pop 弹出窗口)，为空时取系统配置的默认值，
	* 其中 width height 只在 页面打开模式(openType=pop)是弹出模式时才有效
	*/
	add:function(url,width,height,V_openType)
	{
		if(!V_openType)//如果没设此参数，就用系统默认的
		{
			V_openType = openType;
		}
		if(V_openType==1)//普通方式打开,直接进入
		{
			//parent.url = window.location.href;//用父窗口记录list地址,在edit页面退出时用到。
			parent.url = listUrl;
			//alert(parent.url);
			window.location.href=url;
			//var editDiv =$("#editDiv");
			//editDiv.attr("src",url);
			//editDiv.attr("style.display","");
		}
		else//弹出方式
		{	
			var tempWidth = width;
			if(!tempWidth)
			{
				tempWidth = 800;
			}
			var tempHeight = height;
			if(!tempHeight)
			{
				tempHeight = 600;
			}
			openLink(url,tempWidth,tempHeight);
		}
	
	},
/**
	*编辑(修改)
	*url 跳转页面的url
	* width 弹出窗口的宽，为空时取系统配置的默认值，
	* height 弹出窗口的高，为空时取系统配置的默认值，
	* V_openType 页面打开模式(direct 直接进去, pop 弹出窗口)，为空时取系统配置的默认值，
	* 其中 width height 只在 页面打开模式(openType=pop)是弹出模式时才有效
	*/
	edit:function(url,width,height, V_openType) {
		var id = getSelectedRowId();
	    if(id) {
	      	FormFunction.editSingle(id,url,width,height, V_openType);
	    }
	},

/**
	*编辑(修改)单个
	*对象的id
	*url 跳转页面的url
	* width 弹出窗口的宽，为空时取系统配置的默认值，
	* height 弹出窗口的高，为空时取系统配置的默认值，
	* V_openType 页面打开模式(direct 直接进去, pop 弹出窗口)，为空时取系统配置的默认值，
	* 其中 width height 只在 页面打开模式(openType=pop)是弹出模式时才有效
	*/
	editSingle:function(id,url,width,height, V_openType) {
		if(!V_openType)//如果没设此参数，就用系统默认的
		{
			V_openType = openType;
		}
		var temp = url;
		if(!temp) {
				if(editUrl) {
					temp = editUrl;
				} else {
					alert("editUrl变量没有定义");
					return;
				}
			}
		if(temp.indexOf('?')>0)
			temp = temp + "&id="+id;
		else
			temp = temp + "?id="+id;

			if(V_openType==1) { //普通方式打开,直接进入
				//parent.url = window.location.href;//用父窗口记录list地址,在edit页面退出时用到。
				parent.url = listUrl;
				window.location.href = temp;
			}
			else { //弹出方式
				var tempWidth = width;
				if(!tempWidth) {
					tempWidth = 800;
				}
				var tempHeight = height;
				if(!tempHeight) {
					tempHeight = 600;
				}
				openLink(temp,tempWidth,tempHeight);
			}
	},

/**
	*查看
	*对象的id
	*url 跳转页面的url
	* width 弹出窗口的宽，为空时取系统配置的默认值，
	* height 弹出窗口的高，为空时取系统配置的默认值，
	* V_openType 页面打开模式(direct 直接进去, pop 弹出窗口)，为空时取系统配置的默认值，
	* 其中 width height 只在 页面打开模式(openType=pop)是弹出模式时才有效
	*/
	viewSingle:function(id,url,width,height, V_openType) {
	
		if(!V_openType) { //如果没设此参数，就用系统默认的
			V_openType = openType;
		}
		var temp = viewUrl;
		if(!temp) {
				if(viewUrl) {
					temp = viewUrl;
				} else {
					alert("viewUrl变量没有定义");
					return;
				}
			}
		if(temp.indexOf('?')>0)
			temp = temp + "&id="+id;
		else
			temp = temp + "?id="+id;

			if(V_openType==1) { //普通方式打开,直接进入
				//parent.url = window.location.href;//用父窗口记录list地址,在edit页面退出时用到。
				parent.url = listUrl;
				window.location.href = temp;
			} else { //弹出方式
				var tempWidth = width;
				if(!tempWidth) {
					tempWidth = 800;
				}
				var tempHeight = height;
				if(!tempHeight) {
					tempHeight = 600;
				}
				openLink(temp,tempWidth,tempHeight);
			}
	},

/**
	*查看单个
	*url 跳转页面的url
	* width 弹出窗口的宽，为空时取系统配置的默认值，
	* height 弹出窗口的高，为空时取系统配置的默认值，
	* V_openType 页面打开模式(direct 直接进去, pop 弹出窗口)，为空时取系统配置的默认值，
	* 其中 width height 只在 页面打开模式(openType=pop)是弹出模式时才有效
	*/
	view:function(url,width,height, V_openType) {
		var id = getSelectedRowId();
	    if(id) {
	      	FormFunction.viewSingle(id,url,width,height, V_openType);
	    }
	},

/**
	*查询
	*url 查询的url
	*/
	search:function(url) {
		var tempObj = $('#searchForm');
		if(!tempObj) {
			tempObj = document.forms[0];
		}
		
		if(tempObj) {
			if(url.indexOf("pageNo=")!=-1){
				var lStr=url.substring(0,url.indexOf("pageNo=")+1);
				var rStr=url.substring(url.indexOf("pageNo="));
				rStr=rStr.substring(rStr.indexOf("&"));
				url=lStr+rStr;
				try{
					document.getElementById("pageNo").value="1";
				}catch(e){};
			}

			document.forms[0].action=url;
			document.forms[0].submit();
		} else {
			alert("没找到form");
		}
	},

/**
	*退出
	*url 返回list的url, 页面打开模式(openType=pop)是弹出模式时可填null
	* V_openType 页面打开模式(direct 直接进去, pop 弹出窗口)，为空时取系统配置的默认值，
	*/
	exit:function(url,V_openType) {
		if(!V_openType)//如果没设此参数，就用系统默认的
		{
			V_openType = openType;
		}
		if(V_openType==1)//普通方式打开,直接进入
		{
			window.location.href=url;
		}
		else//弹出方式
		{
			//opener.refreshSearch();
			window.close();
		}
	
	},
/**
	*保存
	*url 保存的url
	*/
	save:function(url) {
			var xx = document.forms.length-1;
			document.forms[0].action=url;
			try{
						
			if(!saveCheck(3)){return false;}//表单验证
			}catch(e)
			{
			}
		   if(window.parent.frames['tree']!=null)	
			        window.parent.frames['tree'].location.reload();
			document.forms[0].submit();
	},
		savement:function(url) {
			var xx = document.forms.length-1;
			document.forms[0].action=url;
			try{
						
			if(!saveCheck(3)){return false;}//表单验证
			}catch(e)
			{
			}
		   if(window.parent.frames['tree']!=null)	
			        window.parent.frames['tree'].location.reload();
		   if(window.parent.parent.frames['leftFrameMent']!=null)	
			        window.parent.parent.frames['leftFrameMent'].location.reload();
			document.forms[0].submit();
	},
	editment:function(url,flag) {
			var xx = document.forms.length-1;
			document.forms[0].action=url;
			try{
						
			if(!saveCheck(3)){return false;}//表单验证
			}catch(e)
			{
			}
		   if(window.parent.frames['tree']!=null)	
			        window.parent.frames['tree'].location.reload();
		   if(flag==1){
		  			if(window.parent.parent.frames['leftFrameMent']!=null)	
			        window.parent.parent.frames['leftFrameMent'].location.reload();
		     }
			document.forms[0].submit();
	},
/**
	*删除
	*url 删除的url
	*/
	del:function(url) {
		if(confirm("确认删除?")) {
			var ids = getSelectedRowIds();
			
		    if(ids&&ids.length>0) {
					var temp = url;
					if(!temp) {
						if(delUrl) {
							temp = delUrl;
						} else {
							alert("delUrl变量没有定义");
							return;
						}
					}
					//Begin add by caochuncheng 2009-09-21
					var tempObj = getSearchForm();
					tempObj.append("<input type='hidden' name='opType' value='DEL' >");
					parent.url = listUrl;
					//End add by caochuncheng 2009-09-21
					submitSearchForm(temp);
		    } else {
		    	alert("请选择删除的记录");
		    
		    }
	    }
	
	},
/**
	*删除单个
	*对象的id
	*url 删除的url,如果为空，用delUrl 变量
	*/
	delSingle:function(id,url) {
		if(confirm("确认删除?")) {
			var temp = url;
			if(!temp) {
				if(delUrl) {
					temp = delUrl;
				}else {
					alert("delUrl变量没有定义");
					return;
				}
			}
			var tempObj = getSearchForm();
			tempObj.append("<input type='hidden' name='id' value='"+id+"' >");
			tempObj.append("<input type='hidden' name='opType' value='DEL' >");
			
			if(window.parent.frames.tree!=null)
			   window.parent.frames['tree'].location.reload();
			submitSearchForm(temp);
			
		}
	
	},
	
	delKmingle:function(id,url) {
		if(confirm("确认删除?")) {
			var temp = url;
			if(!temp) {
				if(delUrl) {
					temp = delUrl;
				}else {
					alert("delUrl变量没有定义");
					return;
				}
			}
			var tempObj = getSearchForm();
			tempObj.append("<input type='hidden' name='id' value='"+id+"' >");
			tempObj.append("<input type='hidden' name='opType' value='DEL' >");
			
			if(window.parent.parent.frames['leftFrameMent']!=null)
				window.parent.parent.frames['leftFrameMent'].location.reload();
				
			submitSearchForm(temp);
			
		}
	
	},
	
	delOSingle:function(id,url) {
		if(confirm("确认删除?")) {
			var temp = url;
			if(!temp) {
				if(delUrl) {
					temp = delOUrl;
				}else {
					alert("delUrl变量没有定义");
					return;
				}
			}
			var tempObj = getSearchForm();
			tempObj.append("<input type='hidden' name='id' value='"+id+"' >");
			tempObj.append("<input type='hidden' name='opType' value='DEL' >");
			
			
			submitSearchForm(temp);
			
		}
	
	},
	/**
	*改变状态
	*statusCode 状态的code
	*moduleCode 功能代码
	*id 对象的id
	*nextRoute 下一节点
	* width 弹出窗口的宽，为空时取系统配置的默认值，
	* height 弹出窗口的高，为空时取系统配置的默认值，
	*/
	changeStatus:function(statusCode,moduleCode,id,nextRoute,width,height) {
		var changStatusUrl = ctx+"/admin/status/status!initStatusWindow.action?statusForm.nextRoute="+nextRoute+"&statusForm.statusCode="+statusCode+"&statusForm.moduleCode="+moduleCode+"&statusForm.objectId="+id;

		var tempWidth = width;
		if(!tempWidth)
		{
			tempWidth = 400;
		}
		var tempHeight = height;
		if(!tempHeight)
		{
			tempHeight = 200;
		}
		openLink(changStatusUrl,tempWidth,tempHeight);
	}
}

function getSearchForm() {
	var tempObj = document.forms['searchForm'];
		if(!tempObj) {
			tempObj = document.forms[0];
		}
		if(tempObj) {
			tempObj = $(tempObj);
		}
		return tempObj;
}

//内部函数，提交搜索
function submitSearchForm(url)
{

	var tempObj = getSearchForm();
		
	if(tempObj) {
		document.forms[0].action=url;
		document.forms[0].submit();
	
	}else {
		alert("没找到form");
	}
}

/**
* 取得checkbox中选中的id，单个或多个
*/
function getSelectedRowIds(){

	var count=0;
	    
	    var selectedRow = document.getElementsByName("selectedRow");
	    var ids = new Array();
	    for (i=0;i<selectedRow.length;i++){
	      if (selectedRow[i].checked){
	      	ids[count] = selectedRow[i].value;
	        count ++;
	      }
	    }
	    
	    return ids;

}

/**
* 取得checkbox中选中的id，单个
*/
function getSelectedRowId(){

	var count=0;
	    var id = "";
	    var selectedRow = document.getElementsByName("selectedRow");
	    for (i=0;i<selectedRow.length;i++){
	      if (selectedRow[i].checked){
	      	id = selectedRow[i].value;
	        count ++;
	      }
	    }
	    if (count < 1) {
	    	alert("请选择一条记录进行操作");
	    	return;
	    }
	    if (count == 1) {
	      	return id;
	    }else {
	    	alert("只能选择一条记录进行操作");
	    	return;
	    }
}

/**
* 打开链接
*/
function openLink(link,w,h){
	if (link!=""){
		var url= link;
		var winW=w;
		var winH=h;
		var winX=(window.screen.width-winW)/2;
		var winY=(window.screen.height-winH)/2;
		var openArticle=window.open(url,'','left='+winX+',top='+winY+',width='+winW+',height='+winH+',toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no');
		openArticle.focus();
	}
}
/**
* 刷新当前页面
*/
var thisUrl = window.location.href;
function refresh(){
	//alert(thisUrl);
	window.location.href = thisUrl;
}
function refreshSearch(){
	//alert(thisUrl);
	//window.location.href = thisUrl;
	//listUrl = thisUrl;
	//alert(listUrl);
	defaultEvent('search');
	
}

function clearSearchKeyWord(){
	var inputObjs=document.getElementsByTagName("INPUT");
	for(var i=0;i<inputObjs.length;i++){
		if(inputObjs[i].name.indexOf("searchValue[")!=-1){
			inputObjs[i].value="";
		}
	}
	var selectObjs=document.getElementsByTagName("SELECT");
	for(var i=0;i<selectObjs.length;i++){
		if(selectObjs[i].name.indexOf("searchValue[")!=-1){
			selectObjs[i].selectedIndex=0;
		}
	}
}

/**
* 全选函数
*/
function selectAll(flgChecked,selectId) {
		if(!selectId) {
			selectId = "selectedRow";
		}
		var rows = document.getElementsByName(selectId);
		for(var i=0; i<rows.length; i++) {
			if(!rows[i].disabled) {
			    rows[i].checked = flgChecked;
			}
		}
	}
	
	
/**
* 页面元素的转换成文本显示，为了查看功能而做
* 会自动把id为save的button隐藏。
*/	
function disableElement(){


	$("input[@type=text]").each(
	function(i) {
		//$(this).hide();
		$(this).parent().html($(this).val());
	}
	);
	
	$("input[@type=checkbox]").each(
	function(i) {
		//$(this).hide();
		$(this).attr("disabled",true);
	}
	);
	
	$("input[@type=radio]").each(
	function(i) {
		//$(this).hide();
		$(this).attr("disabled",true);
	}
	);
	
	$("select").each(
	function(i) {
		//$(this).hide();
		$(this).parent().html($(this)[0].options[$(this)[0].selectedIndex].text);
	}
	);
	
	
	$("textarea").each(
	function(i) {
		//$(this).hide();
		$(this).parent().html($(this).val());
	}
	);
	
	$("#save[@type=button]").hide();
	
	
}

/**
* 搜索div的收缩
*/	
function titleControl(divId){
	if($("#titlebtn").attr("src").indexOf("titletooffbtn.gif")>=0){
		$("#titlebtn").attr("src", rootURL+"/images/titletoonbtn.gif");
					
		$("#"+divId).hide();
	}else {
		$("#titlebtn").attr("src", rootURL+"/images/titletooffbtn.gif")
		$("#"+divId).show();
	}
}