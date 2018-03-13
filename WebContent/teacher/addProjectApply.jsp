<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@taglib prefix="form" 
  uri="http://www.springframework.org/tags/form"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="zh">

<head>
	<link href="${pageContext.request.contextPath}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/static/ecoa/common/css/newsys.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/static/ecoa/base/css/base.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/static/ecoa/base/css/common.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/static/ecoa/base/css/fix.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/static/ecoa/base/css/skinBlue.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/static/datepicker/WdatePicker.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/jquery-validation/1.11.1/validator_addMethod.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/static/Style/css/base.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/iconfont/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css" />
<title>添加国家基金申请工作跟踪表</title></head>
<body>
<div class="OpenNormal">
	<form:form  commandName="Item_track" id="inputForm" action="${pageContext.request.contextPath}/teacher/AddItem_track.do" method="post" class="form-horizontal">		
			<div class="top"></div>
			<div style="text-align: center;"><span style="font-size:25px">国家基金项目申请工作跟踪表</span></div>
			<form:hidden path="id" id="id"/>
			
		<table>
		<tr>
		<th width="10%"><span style="color: red;">* </span>姓名</th><td width="15%"><form:input path="name" id="name"  value="" class="required" /></td>
		<th width="10%"><span style="color: red;">* </span>学院</th><td width="20%">
		<form:input path="department" id="department"  class="required" />
		</td>
		<th rowspan="2" width="10%"><span style="color: red;">* </span>研究领域</th><td colspan="3" rowspan="2"><form:textarea path="research" id="research" class="required"/></td>
		</tr>
		
		<tr>
		<th ><span style="color: red;">* </span>手机</th><td ><form:input  id="telephone" path="telephone" value="" class="required isMobile"/></td>
		<th ><span style="color: red;">* </span>邮箱</th><td ><form:input  id="email" path="email" value="" class="required"/></td>
		</tr>
		
		<tr>
		<th ><span style="color: red;">* </span>所属团队</th><td colspan="2"><form:input  id="teamfor" path="team" value=""  class="required"/></td>
		<th colspan="2"><span style="color: red;">* </span>团队负责人</th><td colspan="2"><form:input path="team_leader" id="teamleader"  value="" class="required"/></td>
		</tr>	
		</table>
		
		<table>
		<tr>
		<th colspan="4" style="background-color:#DDDDDD;">个人学术社会关系情况</th>
		</tr>
		
		<tr>
		<th width="15%">熟悉大同行专家（会评专家）</th><td width="35%"><form:textarea path="b_expert" id="bexpert" /></td>
		<th width="15%">熟悉小同行专家（函评专家）</th><td width="35%"><form:textarea path="s_expert" id="sexpert" /></td>
		</tr>
		
		<tr>
		<th >需要学校开展的工作建议</th><td colspan="3"><form:textarea path="advice" id="advice" /></td>
		</tr>
		</table>
		
		<table>
		<tr>
		<th colspan="7" style="background-color:#DDDDDD;">2018年拟报项目信息</th>
		</tr>
		
		<tr>
		<th><span style="color: red;">* </span>题目</th><td colspan="3"><form:input  id="title"  path="title"  value="" class="required"/></td>
		<th><span style="color: red;">* </span>申请领域</th><td colspan="2"><form:textarea id="applyResearch"  path="apply_field" class="required"/></td>
		</tr>
		
		<tr>
			<th width="10%">过往申报基金情况</th>
			<td width="15%">
				<form:radiobutton path="old_apply" style="width:10%"  id="oldApply1"  value="1"  />是
				<form:radiobutton path="old_apply" style="width:10%" id="oldApply2"  value="0"  />否
			</td>
			<th width="10%">申报年份</th><td width="15%">
				<form:input path="apply_year" id="oldApplyYear"   value="2018" readonly="true" reg="^.+$" />
			</td>
			<th width="15%">参与申请年份</th><td colspan="2"><form:input path="join_year" id="oldJoinYear"   value="" onfocus="WdatePicker({dateFmt:'yyyy'})" readonly="true" reg="^.+$" /></td>
		</tr>
		
		<tr>
		<th>主要内容及创新点</th><td colspan="6"><form:textarea path="major_content" id="majorContent" /></td>
		</tr>
		
		<tr>
		<th rowspan="2">近3年的成果基础</th>
		<th>论文、专利情况</th>
		<td colspan="5">论文：发表SCI一区论文<form:input class="shoInput" id="sciCount" path="sci_first_count" value="" />篇，
			二区论文<form:input class="shoInput" id="secondCount" path="sci_second_count" value="" />篇；
			EI论文<form:input class="shoInput" id="eiCount" path="ei_count" value="" />篇。<br>
			专利：申请发明<form:input class="shoInput" id="inventionCount" path="invent_count" value="" />件，
			授权<form:input class="shoInput" id="autoInvenCount" path="invent_accredit_count" value="" />件；
			申请实用新型<form:input class="shoInput" id="utilityCount" path="utility_count" value="" />件，
			授权<form:input class="shoInput" id="autoUtilityCount" path="utility_accredit_count" value="" />件。
			</td>
		</tr>
		
		<tr>
		<th>项目情况</th>
		<td colspan="5"><form:textarea style="height: 80px" path="situation" id="situation" /></td>
		</tr>
		
		<tr>
		<th>队伍基础</th><td colspan="6"><form:textarea style="height: 80px" path="team_basic" id="teamBasic" /></td>
		</tr>
		</table>
	
		
		<table  id="projectTable">
		<tr>
		<th width="25%">近年报过的基金题目</th> <th width="55%">评价情况</th><td width="10%"><a href="###" onclick="insRow()" class="img">添加</a></td>
		</tr>
	    <c:forEach items="${Item_track.list}" varStatus="status" var="item_track" >		
		<tr>
		<td ><input type="text" name="old_title"  value=${item_track.old_title}  /></td>
		
		<td ><input type="textarea"  name="old_evaluate" style="height:70px"   value=${item_track.old_evaluate}  /></td>
		<td></td>
		</tr>
		</c:forEach>
		</table>		
		<table id="trackTable">
		<tr>
		<th colspan="4" style="background-color:#DDDDDD;">2018年项目申请进展跟踪</th>
		</tr>			
		<tr>
		<th width="20%">时间</th><th width="55%">完成内容</th><th width="15%">跟踪人签字</th><td width="10%"><a href="###" onclick="insRow2()" class="img">添加</a></td>
		</tr>	
		<c:forEach items="${Item_track.list}" var="item_track">	
		<tr>
		<input type="hidden" value=${item_track.id} name="tid"/>
		<td><input type="text" name="time" id="appalyDate1" value=${item_track.time} onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true" reg="^.+$" /></td>
		<td><input  type="text" name="content" type=textarea style="height:70px" id="content1" value=${item_track.content} /></td>
		<td><input  type="text" id="sign1" name="sign" value=${item_track.sign} /></td>
		<td></td>
		</c:forEach>
				
		</table>		
		
				
			 <div class="bottom">
                    <button class="layui-btn layui-btn-radius layui-btn-normal" lay-submit="" lay-filter="demo1">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-radius layui-btn-normal">重置</button>
                </div>         
	</form:form>
	<script>
	
	function insRow(){
		var projectTable = document.getElementById("projectTable");
		var y = projectTable.insertRow(-1);
		var addNum = $("#projectTable").find("tr").length-1;
		y.id ="trone_" + addNum;
		
		var a = y.insertCell(0);
		var b = y.insertCell(1);
		var c = y.insertCell(2);
		
		a.innerHTML="<td><input type=\"text\" id=\"oldTitle"+addNum+"\" name=\"old_title\" value=\"\" ></td>";
		b.innerHTML="<td><textarea style=\"height:70px\"  value=\"\"  id=\"oldEvaluate"+addNum+"\" name=\"old_evaluate\" ></textarea></td>";
		c.innerHTML="<td><a href=\"###\" onclick=\"deleteRow('" +y.id+"');\" class=\"img\">删除</a></td>";

		addNum++;
		
	}
	
	function insRow2(){
		var trackTable = document.getElementById("trackTable");
		var y = trackTable.insertRow(-1);
		var addNum = $("#trackTable").find("tr").length-1;
		y.id ="trtwo_" + addNum;
		
		var a = y.insertCell(0);
		var b = y.insertCell(1);
		var c = y.insertCell(2);
		var d = y.insertCell(3);
		
		a.innerHTML="<td><input type=\"text\" id=\"appalyDate"+addNum+"\" name=\"time\" onfocus=\"WdatePicker({dateFmt:'yyyy-MM-dd'})\" readonly=\"true\" reg=\"^.+$\" ></td>";
		b.innerHTML="<td><textarea style=\"height:70px\" id=\"content"+addNum+"\" name=\"content\" ></textarea></td>";
		c.innerHTML="<td><input type=\"text\" id=\"sign"+addNum+"\" name=\"sign\" ></td>";
		d.innerHTML="<td><a href=\"###\" onclick=\"deleteRow('" +y.id+"');\" class=\"img\">删除</a></td>";

		addNum++;
		
	}
	
	
	function deleteRow(rowId){	
		$("tr[id="+rowId+"]").remove();

		}
	
		$(document).ready(function() {
			//聚焦第一个输入框
			//$("#task_title").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
		
		
		
		 jQuery.validator.addMethod("isMobile", function(value, element) {    
		      var length = value.length;    
		      return this.optional(element) || (length == 11 && /^(\d{11})$/.test(value));    
		    }, "请正确填写您的手机号码。");
	</script>
		
		
		 
	
	</div>
</body>
</html>