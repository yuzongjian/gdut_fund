<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/iconfont/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css" />
</head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/admin/";
%>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header nav-header">
			<div class="layui-logo nav-logo">
            	超级管理员后台
            	<i class="iconfont" id="nav_slide_btn">&#xe61c;</i>
            </div>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item">
					<a href="#" class="nav-a">
						<i class="iconfont">&#xe625;</i>
						超级管理员
					</a>
				</li>
				<li class="layui-nav-item"><a href="${pageContext.request.contextPath}/cancel.do" class="nav-a" id="nav_log_out">退出登录</a></li>
			</ul>
		</div>

		       <div class="layui-side layui-bg-black" id="left_nav">
            <div class="layui-side-scroll">
                <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                    <li class="layui-nav-item layui-nav-itemed">
                        <a class="" href="javascript:;">项目管理</a>
                        <dl class="layui-nav-child">
                            <dd><a href="${pageContext.request.contextPath}/adminSelectAll.do">项目申请工作跟踪</a></dd>
                        </dl>
                        <dl class="layui-nav-child">
                            <dd><a href="<%=basePath%>ProjectList.do">申报项目清单</a></dd>
                        </dl>
                       
                    </li>
                    <li class="layui-nav-item layui-nav-itemed">
                        <a href="javascript:;">统计分析</a>
                        <dl class="layui-nav-child">
                            <dd><a href="<%=basePath%>StatisticList.do">项目统计分析</a></dd>
              
                        </dl>
                    </li>
                    <li class="layui-nav-item layui-nav-itemed">
                        <a href="javascript:;">系统管理</a>
                        <dl class="layui-nav-child">
                            <dd><a href="<%=basePath%>DepartmentList.do">部门管理</a></dd>
                            <dd><a href="${pageContext.request.contextPath}/adminSelectUser.do">师资管理</a></dd>
                        </dl>
                    </li>
                </ul>
            </div>
        </div>
