<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh">
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<title>交易汇总</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/iconfont/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css" />
</head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/departmentAdmin/";
%>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header nav-header">
			<div class="layui-logo nav-logo">
            	渠道商管理后台
            	<i class="iconfont" id="nav_slide_btn">&#xe61c;</i>
            </div>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item">
					<a href="#" class="nav-a">
						<i class="iconfont">&#xe625;</i>
						渠道商
					</a>
				</li>
				<li class="layui-nav-item"><a href="/login.jsp" class="nav-a" id="nav_log_out">退出登录</a></li>
			</ul>
		</div>

		       <div class="layui-side layui-bg-black" id="left_nav">
            <div class="layui-side-scroll">
                <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                    <li class="layui-nav-item layui-nav-itemed">
                        <a class="" href="javascript:;">项目管理</a>
                        <dl class="layui-nav-child">
                            <dd><a href="<%=basePath%>statisticsData.jsp">项目申请工作跟踪</a></dd>
                        </dl>
                        <dl class="layui-nav-child">
                            <dd><a href="<%=basePath%>data.jsp">申报项目清单</a></dd>
                        </dl>
                        <dl class="layui-nav-child">
                            <dd><a href="<%=basePath%>statisticsData.jsp">立项清单管理</a></dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item layui-nav-itemed">
                        <a href="javascript:;">统计分析</a>
                        <dl class="layui-nav-child">
                            <dd><a href="<%=basePath%>tradeDetails.html">项目统计分析</a></dd>
              
                        </dl>
                    </li>
                    <li class="layui-nav-item layui-nav-itemed">
                        <a href="javascript:;">系统管理</a>
                        <dl class="layui-nav-child">
                            <dd><a href="<%=basePath%>addMerchant.jsp">部门管理</a></dd>
                            <dd><a href="<%=basePath%>merchantList.jsp">师资管理</a></dd>
                            <dd><a href="<%=basePath%>lowerMerchant.jsp">菜单管理</a></dd>
                        </dl>
                    </li>
                </ul>
            </div>
        </div>

		<div class="layui-body" id="content_body">
			<!-- 面包屑 -->
			<span class="layui-breadcrumb head-breadcrumb">
				<i class="iconfont">&#xe61a;</i>
				<a href="<%=basePath%>statisticsData.jsp">首页</a>
				<a href="<%=basePath%>tradeDetails.jsp">交易记录</a>
				<a><cite>交易明细</cite></a>
			</span>
			<!-- 内容主体区域 -->
			<div style="padding: 15px;">
				<!-- 查询部分 -->
				<form class="layui-form" action="" method="">
					<div class="layui-form-item">
						<!-- 选择时间范围 -->
						

					<div class="layui-form-item">
						<button class="layui-btn layui-btn-normal layui-btn-small" layui-submit>
							<i class="layui-icon">&#xe615;</i> 查询
						</button>
						<button class="layui-btn layui-btn-normal layui-btn-small" type="reset">
							<i class="layui-icon">&#x1002;</i>重置条件
						</button>
						<button class="layui-btn layui-btn-normal layui-btn-small">
							<i class="layui-icon">&#xe601;</i>导出数据
						</button>
					</div>
	</form>
  <table class="layui-table">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>题目</th>
                    <th>申请领域</th>
                    <th>申报年份</th>
                    <th>姓名</th>
                    <th>操作</th>
                </tr>
                </thead>

</table>
	<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="../js/nav.js"></script>
	<script src="../layui/layui.js"></script>
	<script>
	//JavaScript代码区域
	 layui.use('element', function(){
            var element = layui.element;
        });

		laydate.render({
			elem: '#start_time',
			range: true
		})
	});
	</script>
</body>
</html>