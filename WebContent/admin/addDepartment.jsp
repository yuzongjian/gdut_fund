<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html lang="zh">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/admin/adminCommon.jsp" %>
<head><title>添加部门</title></head>
		<div class="layui-body" id="content_body">
			<!-- 面包屑 -->
			<span class="layui-breadcrumb head-breadcrumb">
				<i class="iconfont">&#xe61a;</i>
				<a href="${pageContext.request.contextPath}/SelectAll.do">首页</a>
				<a href="<%=basePath%>DepartmentList.do">部门管理</a>
				<a><cite>添加部门</cite></a>
			</span>
			<!-- 内容主体区域 -->
			 <div class="layui-tab-content">
        <fieldset class="layui-elem-field layui-field-title">
            <legend>添加部门</legend>
        </fieldset>
		<form:form commandName="department" class="layui-form" action="${pageContext.request.contextPath}/admin/AddDepartmentOver.do" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label">部门名称：</label>
                <div class="layui-input-inline">
                    <form:input path="dept_name" id="dept_name" value="" placeholder="请输入要添加的部门名称" class="layui-input" lay-verify="required" maxlength="30"/>
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
</script>