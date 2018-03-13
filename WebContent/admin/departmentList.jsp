<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="zh">
<%@ include file="/admin/adminCommon.jsp" %>
<head><title>部门管理</title></head>
		<div class="layui-body" id="content_body">
			<!-- 面包屑 -->
			<span class="layui-breadcrumb head-breadcrumb">
				<i class="iconfont">&#xe61a;</i>
				<a href="${pageContext.request.contextPath}/adminSelectAll.do">首页</a>
				<a href="<%=basePath%>DepartmentList.do">系统管理</a>
				<a><cite>部门管理</cite></a>
			</span>
			<!-- 内容主体区域 -->
			<div style="padding: 15px;">
				<!-- 查询部分 -->
				<form class="layui-form" action="${pageContext.request.contextPath}/admin/SearchDepartment.do" method="post">
					 <label class="layui-form-label">部门名称：</label>
        <div class="layui-input-inline">
 <input type="text" id="dept_name" name="dept_name" class="layui-input" placeholder="请输入部门名称" autocomplete="off">
     </div> 
						<button class="layui-btn layui-btn-normal layui-btn-small" layui-submit>
							<i class="layui-icon">&#xe615;</i> 查询
						</button>
						<button id = "down" class="layui-btn layui-btn-normal layui-btn-small" type="reset" onclick="submitForm()">
							<i class="layui-icon">&#xe601;</i>导出数据
						</button>
						<button class="layui-btn layui-btn-normal layui-btn-small" type="reset" onclick="javascript:window.location.href='${pageContext.request.contextPath}/admin/AddDepartment.do';">
							<i class="layui-icon">&#xe608;</i>增加部门
						</button>
						</form>
					</div>				
				
  <table class="layui-table">
                 <colgroup>
    <col width="100">
    <col width="250">
    <col width="150">
    <col>
  </colgroup>
                <thead>
                <tr>
                    <th>序号</th>
                    <th>部门名称</th>
                    <th>操作</th>
                </tr>
             <!--       以下内容替换成循环遍历  -->
             <tbody>
                <c:forEach items="${departments}" var="department" varStatus="st">
                 <tr>
                    <th>${st.index+1}</th>
                    <th>${department.dept_name}</th>
                    <th><a href="${pageContext.request.contextPath}/admin/UpdateDepartment.do?dept_id=${department.dept_id}"  class="layui-btn layui-btn-normal layui-btn-mini">修改 </a> 
                    <a href="${pageContext.request.contextPath}/admin/DeleteDepartment.do?dept_id=${department.dept_id}" class="layui-btn layui-btn-danger layui-btn-mini">删除</a></th>   
                </tr>
              </c:forEach>
         </tbody>          
<div class="fenyeWrap">
  </table>
 <!--  <center>
  <div id="fenye"></div>
                <div>共  条记录</div>
            </div>
        </div>
    </div>
    </center> -->
	<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/nav.js"></script>
	<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
	<script>
	//JavaScript代码区域
	layui.use('laypage', function(){
  var laypage = layui.laypage;
  
  //执行一个laypage实例
  laypage.render({	
    elem: 'fenye' //注意，这里的 test1 是 ID，不用加 # 号
    ,count: 50 //数据总数，从服务端得到
  });
});
 layui.use('element', function(){
            var element = layui.element;
        });
 layui.use('form', function(){
     var form = layui.form;
   });
 $(document).ready(function() {
     $("#down").click(function() {
             window.location.href = "${pageContext.request.contextPath}/admin/DownLoadDepartmentExcel.do";
         });
 });
	</script>
</body>
</html>