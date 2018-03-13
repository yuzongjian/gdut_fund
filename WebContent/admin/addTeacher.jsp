<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="form" 
  uri="http://www.springframework.org/tags/form"%>
<html lang="zh">
<%@ include file="/admin/adminCommon.jsp" %>
<head><title>添加教师</title></head>
		<div class="layui-body" id="content_body">
			<!-- 面包屑 -->
			<span class="layui-breadcrumb head-breadcrumb">
				<i class="iconfont">&#xe61a;</i>
				<a href="${pageContext.request.contextPath}/SelectAll.do">首页</a>
				<a href="${pageContext.request.contextPath}/adminSelectUser.do">师资管理</a>
				<a><cite>添加教师</cite></a>
			</span>
			<!-- 内容主体区域 -->
			 <div class="layui-tab-content">
        <fieldset class="layui-elem-field layui-field-title">
            <legend>添加教师</legend>
        </fieldset>
		<form:form  commandName="User" class="layui-form" action="${pageContext.request.contextPath}/adminInsertUser.do" method="post" >
            <div class="layui-form-item">
                <label class="layui-form-label">姓名：</label>
                <div class="layui-input-inline">
                    <form:input  path="name"  value="" placeholder="姓名" class="layui-input" lay-verify="required" maxlength="30"/>
                </div>
                <div class="layui-form-mid layui-word-aux star">必填</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">职工号：</label>
                <div class="layui-input-inline">
                    <form:input path="account" value="" placeholder="请输入职工号" class="layui-input" autocomplete="off" lay-verify="required"/>
                </div>
                <div class="layui-form-mid layui-word-aux star">必填（作为登陆账号）</div><div class="layui-form-mid layui-word-aux star">${error}</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">部门</label>
                <div class="layui-input-inline">
                    <form:select id="department" path="department"  lay-filter="districtFilter">              
                            <form:options  items="${departments}" itemLabel="dept_name" itemvalue="dept_name" />
                        </form:select>
                </div>
                <div class="layui-form-mid layui-word-aux star"></div>
            </div>
             <div class="layui-form-item">
                <label class="layui-form-label">密码：</label>
                <div class="layui-input-inline">
                    <form:input path="password" value="" placeholder="请输入密码" class="layui-input" autocomplete="off" lay-verify="required"/>
                </div>
                <div class="layui-form-mid layui-word-aux star">必填</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">确认密码</label>
                <div class="layui-input-inline">
                    <input type="text" name="repassword" value="" placeholder="请再次输入密码" class="layui-input" autocomplete="off" lay-verify="required"/>
                </div>
                <div class="layui-form-mid layui-word-aux star">必填</div>
            </div>
             <div class="layui-form-item">
                    <label class="layui-form-label">性别：</label>
                    <form:radiobutton path="sex" value="1" title="男" />
                    <form:radiobutton path="sex" value="2" title="女"  />
                </div>      
       <div class="layui-form-item">
                <label class="layui-form-label">出生年月：</label>
                <div class="layui-input-inline">
                  <form:input path="birthday" class="layui-input" id="test1" placeholder="选择日期"/>
                </div>
                <div class="layui-form-mid layui-word-aux star">必填</div>
            </div>
            
            <div class="layui-form-item">
                <label class="layui-form-label">学历：</label>
                <div class="layui-input-inline">
                    <form:input path="education_background" value="" placeholder="请输入学历" class="layui-input" maxlength="30"/>
                </div>
                <div class="layui-form-mid layui-word-aux star">必填</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">职称：</label>
                <div class="layui-input-inline">
                    <form:input path="job_title" value="" placeholder="请输入职称" class="layui-input" maxlength="30"/>
                </div>
                <div class="layui-form-mid layui-word-aux star">必填</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">联系电话：</label>
                <div class="layui-input-inline">
                    <form:input path="telephone" value="" placeholder="请输入联系电话" class="layui-input" maxlength="30"/>
                </div>
                <div class="layui-form-mid layui-word-aux star">必填</div>
            </div>
            
            <div class="layui-form-item">
                <label class="layui-form-label">邮箱：</label>
                <div class="layui-input-inline">
                    <form:input path="email" value=""  class="layui-input" maxlength="30"/>
                </div>            
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