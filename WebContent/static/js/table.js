function checkChk(){
		  var isgoon=false;
		  var len= $("input[name='ids']:checked").length;		  
		  if(len>0){
		  	isgoon=true;
		  }
		 //alert(len+" "+isgoon);
		  return isgoon;
	}
	 function selectall(v) {  
	 	//alert(v);      
	   $("input[name='ids']").each(function (i){
		   if(!$(this).is(':disabled')){
	           $(this).prop("checked",v);
		   }
  		 }  
	   );	   	    	 
 	}
	 function deletes(dir){
	 		if(!checkChk()){			  
					  		alert('请至少选择一条记录.');
					  		return;
			}
	 		if(confirm('确认删除所选项吗？'))
	 		{
	 			$("#mainForm").attr("action",dir+"logicDeletes");
	 			$("#mainForm").submit();
	 		}
	 	}
	 
	 function del(dir,objid){
	 		if(confirm('确认删除所选项吗？'))
	 		{
	 			window.location=dir+"logicDelete/"+objid;
	 		}
	 	}
//list页面点击标题排序
	 function orderCol(sortItem) {
			if ($("#sortItem").val() == sortItem) {
				if ($("#sortType").val() == "") {
					$("#sortType").val("desc");
				}
				else if ($("#sortType").val() == "desc") {
					$("#sortType").val("asc");
				}
				else if ($("#sortType").val() == "asc") {
					$("#sortType").val("desc");
				}
			}
			else {
				$("#sortItem").val(sortItem);
				$("#sortType").val("desc");
			}
			$("#mainForm").submit();
		}
	 
	 function jumpPage(page) {
			$("#page").val(page);
			$('#pageSize').val($('#newPageSize').val());
			$("#mainForm").submit();
		}
	 
	 function searchForm(){
		 jumpPage(1);
	 }