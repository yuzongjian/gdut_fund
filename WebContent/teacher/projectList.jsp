<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="zh">
<%@ include file="/teacher/teacherCommon.jsp" %>
<head><title>申报项目清单</title>
<style type="text/css"> .pagination {
  display: inline-block;
  padding-left: 0;
  margin: 20px 0;
  border-radius: 4px;
}
.pagination > li {
  display: inline;
}
.pagination > li > a,
.pagination > li > span {
  position: relative;
  float: left;
  padding: 6px 12px;
  margin-left: -1px;
  line-height: 1.42857143;
  color: #337ab7;
  text-decoration: none;
  background-color: #fff;
  border: 1px solid #ddd;
}
.pagination > li:first-child > a,
.pagination > li:first-child > span {
  margin-left: 0;
  border-top-left-radius: 4px;
  border-bottom-left-radius: 4px;
}
.pagination > li:last-child > a,
.pagination > li:last-child > span {
  border-top-right-radius: 4px;
  border-bottom-right-radius: 4px;
}</style>
</head>
		<div class="layui-body" id="content_body">
			<!-- 面包屑 -->
			<span class="layui-breadcrumb head-breadcrumb">
				<i class="iconfont">&#xe61a;</i>
				<a href="<%=basePath%>SelectAll.do">首页</a>
				<a href="<%=basePath%>ProjectList.do">项目管理</a>
				<a><cite>申报项目清单</cite></a>
			</span>
			<!-- 内容主体区域 -->
			<div style="padding: 15px;">
				
<form class="layui-form" action="${pageContext.request.contextPath}/teacher/SearchProject.do" method="post">
<input type="hidden"id="name" value="${name}">
<input type="hidden" id="type" value="${type}">
 <div class="layui-field-box">
  <div class="layui-form-item">
  <label class="layui-form-label">项目名称：</label>
         <div class="layui-input-inline">
 <input type="text" class="layui-input" placeholder="请输入项目名称" autocomplete="off" name="name" id="name">
     </div>
         <div class="layui-inline">
      <label class="layui-form-label">项目类型：</label>
      <div class="layui-input-inline">
          <select name="type" id="type" lay-search>
              <option value="">项目类型选择</option>                					            
				<option value="青年基金" >青年基金</option>
				<option value="面目基金">面目基金</option>
				<option value="优秀青年基金" >优秀青年基金</option>
				<option value="联合基金">联合基金</option>
				<option value="重点项目" >重点项目</option>
				<option value="一般项目">一般项目</option>
            </select>
      </div>
    </div>
    <div class="layui-inline">
						<button class="layui-btn layui-btn-normal layui-btn-small" layui-submit>
							<i class="layui-icon">&#xe615;</i> 查询
						</button>
						<button class="layui-btn layui-btn-normal layui-btn-small" type="reset">
							<i class="layui-icon">&#x1002;</i>重置条件
						</button>
							<button class="layui-btn layui-btn-normal layui-btn-small" type="reset" onclick="javascript:window.location.href='${pageContext.request.contextPath}/teacher/CreateProject.do';">
							<i class="layui-icon">&#xe608;</i>新建
						</button>
					</div>
	</form>
  <table class="layui-table">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>项目名称</th>
                    <th>项目类别</th>
                    <th>所属学院</th>
                    <th>申请人</th>
                    <th>操作</th>
                </tr>
             <!--       以下内容替换成循环遍历  -->
             <tbody>
             <c:forEach items="${projectList}" var="project" varStatus="st">
                 <tr>
                    <th>${st.index+1}</th>
                    <th>${project.name}</th>
                    <th>${project.type}</th>
                    <th>${project.department}</th>
                    <th>${project.applicant}</th>
                    <th><a href="${pageContext.request.contextPath}/teacher/SearchOrChangProject.do?id=${project.id}"  class="layui-btn layui-btn-normal layui-btn-mini">修改 </a> 
                    <a href="${pageContext.request.contextPath}/teacher/DeleteProject.do?id=${project.id}" class="layui-btn layui-btn-danger layui-btn-mini">删除</a></th>   
                </tr>
              </c:forEach>
         </tbody>          
  </table>
  <center>		
		<ul class="pagination">
			${pageCode }
		</ul>
	
	</center>
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
	
	</script>
</body>
</html>