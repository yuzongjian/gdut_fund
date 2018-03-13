<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<html lang="zh">
<%@ include file="/admin/adminCommon.jsp" %>
<head><title>修改申报项目</title></head>
		<div class="layui-body" id="content_body">
			<!-- 面包屑 -->
			<span class="layui-breadcrumb head-breadcrumb">
				<i class="iconfont">&#xe61a;</i>
				<a href="${pageContext.request.contextPath}/adminSelectAll.do">首页</a>
				<a href="<%=basePath%>ProjectList.do">申报项目清单</a>
				<a><cite>修改申报项目</cite></a>
			</span>
			<!-- 内容主体区域 -->
			 <div class="layui-tab-content">
        <fieldset class="layui-elem-field layui-field-title">
            <legend>修改申报项目</legend>
        </fieldset>
		<form:form class="layui-form" action="${pageContext.request.contextPath}/admin/EditProject.do" method="post" commandName="project">
           	<form:hidden path="id" id="id"/>
           	<form:hidden path="user_id" id="user_id"/>
            <div class="layui-form-item">
                <label class="layui-form-label">项目名称：</label>
                <div class="layui-input-inline">
                    <form:input path="name" id="name" value="" placeholder="项目名称" class="layui-input" lay-verify="required" maxlength="30"/>
                </div>
                <div class="layui-form-mid layui-word-aux star">必填</div>
            </div>
   <div class="layui-form-item">
                    <label class="layui-form-label">项目类型</label>
                    <div class="layui-input-inline">
                          <form:select path="type" id="type" lay-filter="districtFilter">
				                <form:option value="青年基金" />
				           		<form:option value="面目基金"/>
				             	<form:option value="优秀青年基金" />
				           		<form:option value="联合基金"/>
				             	<form:option value="重点项目" />
				        	    <form:option value="一般项目"/>
				           </form:select>     
				      </div>
                       <div class="layui-form-mid layui-word-aux star">必填</div>  <div class="layui-form-mid layui-word-aux star">${error}</div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">所属学院</label>
                    <div class="layui-input-inline">
                         <form:select id="department" path="department"  lay-filter="districtFilter">              
                            <form:options  items="${departments}" itemLabel="dept_name" itemvalue="dept_name" />
                        </form:select>
                    </div>
                       <div class="layui-form-mid layui-word-aux star">必填</div>
                </div>      
            <div class="layui-form-item">
                <label class="layui-form-label">申请人：</label>
                <div class="layui-input-inline">
                    <form:input path="applicant" id="applicant" value="" placeholder="请输入申请人" class="layui-input" autocomplete="off" lay-verify="required"/>
                </div>
                <div class="layui-form-mid layui-word-aux star">必填</div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">科学部编码：</label>
                <div class="layui-input-inline">
                    <form:input path="science_code" id="science_code" value="" placeholder="请输入科学部编码" class="layui-input" maxlength="30"/>
                </div>
                <div class="layui-form-mid layui-word-aux star">必填</div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">申请代码：</label>
                <div class="layui-input-inline">
                    <form:input path="apply_code" id="apply_code" value="" placeholder="请输入申请代码" class="layui-input" maxlength="30"/>
                </div>
                <div class="layui-form-mid layui-word-aux star">必填</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">版本号：</label>
                <div class="layui-input-inline">
                    <form:input path="version" id="version" value="" placeholder="请输入版本号" class="layui-input" maxlength="30"/>
                </div>
                <div class="layui-form-mid layui-word-aux star">必填</div>
            </div>
 
            <div class="layui-form-item submit-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
            
        </form:form>
    </div>
</div>
	<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/nav.js"></script>
	<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
    layui.use(['upload','form'], function(){
    });
    layui.use('element', function(){
        var element = layui.element;
    });
    layui.use('laydate', function(){
    	  var laydate = layui.laydate;
    	  
    	  //执行一个laydate实例
    	  laydate.render({
    	    elem: '#test1' //指定元素
    	  });
    	});
</script>