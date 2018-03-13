<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="zh">
<%@ include file="adminCommon.jsp" %>

<head><title>师资管理</title>
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
				<a href="${pageContext.request.contextPath}/adminSelectAll.do">首页</a>
				<a href="${pageContext.request.contextPath}/adminSelectUser.do">系统管理</a>
				<a><cite>师资管理</cite></a>
			</span>
			<!-- 内容主体区域 -->
			<div style="padding: 15px;">
				<!-- 查询部分 -->
				<form class="layui-form" action="${pageContext.request.contextPath}/adminSelectUser.do" method="post">
				<input type="hidden" id="dept2" value=${department}>
					 <div class="layui-form-item">
  <label class="layui-form-label">姓名：</label>
         <div class="layui-input-inline">
 <input type="text" class="layui-input" placeholder="请输入姓名" autocomplete="off" name="name">
     </div>
      <label class="layui-form-label">职工号：</label>
        <div class="layui-input-inline">
 <input type="text" class="layui-input" placeholder="请输入职工号" autocomplete="off" name="account">
     </div>   
     <label class="layui-form-label">部门：</label>
                    <div class="layui-input-inline">
                         <select id="department" name="department"  lay-filter="districtFilter">    
                        	 <option value=""></option>          
                            <c:forEach items="${departments}" var="department">
                            	<option value="${department}">${department}</option>
                            </c:forEach>
                        </select>
                    </div>
						<button class="layui-btn layui-btn-normal layui-btn-small" layui-submit>
							<i class="layui-icon">&#xe615;</i> 查询
						</button>
						<button class="layui-btn layui-btn-normal layui-btn-small" type="reset" onclick="submitForm()" id="down">
							<i class="layui-icon">&#xe601;</i>导出数据
						</button>
						<button class="layui-btn layui-btn-normal layui-btn-small" type="reset" onclick="javascript:window.location.href='${pageContext.request.contextPath}/adminA.do';" >
							<i class="layui-icon">&#xe608;</i>添加教师
						</button>
					</div>
	</form>
  <table class="layui-table">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>职工号</th>
                     <th>姓名</th>
                    <th>所属部门</th>
                     <th>操作</th>
                </tr>
             <!--       以下内容替换成循环遍历  -->
             <tbody>
                 <tr>
                    <c:forEach items="${User}" var="user" varStatus="st">
                 <tr>
                    <th>${st.index+1}</th>
                    <th>${user.account}</th>
                    <th>${user.name}</th>
                    <th>${user.department}</th>
                    <th>
                    <a href="${pageContext.request.contextPath}/adminRepassword.do?id=${user.id}"  class="layui-btn  layui-btn-mini layui-btn-mini" onclick="return ok();">重置密码 </a>
                    <a href="${pageContext.request.contextPath}/adminSelectuser.do?id=${user.id}"  class="layui-btn layui-btn-normal layui-btn-mini">修改</a>
                    <a href="${pageContext.request.contextPath}/adminDeleteUser.do?id=${user.id}"  class="layui-btn layui-btn-danger layui-btn-mini" >删除</a>
                    </th> 
                        
                </tr>
              </c:forEach>                
                </tr>
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
 layui.use('element', function(){
            var element = layui.element;
        });
 layui.use('form', function(){
     var form = layui.form;
   });
 
 function ok ()
 {
	 alert("密码重置为123456");
	 
 }
 $(document).ready(function() {
     $("#down").click(function() {
             var d = $("#dept2").val();
             window.location.href = "${pageContext.request.contextPath}/DownLoadTeachersExcel.do?department="+d;
         });
 });
	</script>
</body>
</html>