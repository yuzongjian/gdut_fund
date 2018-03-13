<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="zh">
<%@ include file="adminCommon.jsp" %>
<head><title>项目申请工作跟踪</title>
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
				<a href="${pageContext.request.contextPath}/adminSelectAll.do">项目管理</a>
				<a><cite>项目申请工作跟踪</cite></a>
			</span>
			
			<!-- 内容主体区域 -->
			<div style="padding: 15px;">
				<!-- 查询部分 -->
				<form class="layui-form" action="${pageContext.request.contextPath}/adminSelectAll.do" method="post">
				<input type="hidden" id="apyear" value="${appay_year}">
				<input type="hidden" id="tit" value="${title}">
<label class="layui-form-label">题目：</label>
<div class="layui-input-inline">
 <input type="text" class="layui-input" placeholder="请输入题目" autocomplete="off" name="title" >
     </div>
         <div class="layui-inline">
      <label class="layui-form-label">年份：</label>
      <div class="layui-input-inline">
        <input type="text" class="layui-input" id="year" placeholder="选择年份" name="apply_year" >
      </div>
    </div>
    <div class="layui-inline">
						<button class="layui-btn layui-btn-normal layui-btn-small" layui-submit>
							<i class="layui-icon">&#xe615;</i> 查询
						</button>
						<button class="layui-btn layui-btn-normal layui-btn-small" type="reset">
							<i class="layui-icon">&#x1002;</i>重置条件
						</button>
						<button id="down" class="layui-btn layui-btn-normal layui-btn-small" type="reset" onclick="submitForm()">
							<i class="layui-icon">&#xe601;</i>导出数据
						</button>
					</div>
	</form>
  <table class="layui-table">
                 <colgroup>
    <col width="100">
    <col width="300">
    <col width="150">
    <col width="100">
    <col width="150">
    <col width="200">
    <col>
  </colgroup>
                <thead>
                <tr>
                    <th>序号</th>
                    <th>题目</th>
                    <th>申请领域</th>
                    <th>申报年份</th>
                    <th>姓名</th>
                    <th>操作</th>     
                </tr>
                
<!--       以下内容替换成循环遍历  -->
             <tbody>
                 <tr>
                    <c:forEach items="${item_track}" var="item_track" varStatus="st">
                 <tr>
                    <th>${st.index+1}</th>
                    <th>${item_track.title}</th>
                    <th>${item_track.apply_field}</th>
                    <th>${item_track.apply_year}</th>
                    <th>${item_track.name}</th>
                    <th><a href="${pageContext.request.contextPath}/adminSelectById.do?id=${item_track.id}"  class="layui-btn layui-btn-normal layui-btn-mini">修改</a>  
                    <a href="${pageContext.request.contextPath}/adminDeleteById.do?id=${item_track.id}"  class="layui-btn layui-btn-danger layui-btn-mini" >删除</a>  </th>
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
	layui.use('laypage', function(){
  var laypage = layui.laypage;
  
  //执行一个laypage实例
  laypage.render({	
    elem: 'fenye' //注意，这里的 test1 是 ID，不用加 # 号
    ,count: 2//数据总数，从服务端得到
    ,limit: 1
  });
});
 layui.use('element', function(){
     var element = layui.element;
        });
 layui.use('laydate', function(){
	  var laydate = layui.laydate;	
	  laydate.render({
		    elem: '#year'
		    ,type: 'year'
		  });
 });
 layui.use('form', function(){
     var form = layui.form;
   });
 $(document).ready(function() {
     $("#down").click(function() {
             var title = $("#tit").val();
             var apply_year = $("#apyear").val();
             window.location.href = "${pageContext.request.contextPath}/DownLoadItemTrackExcel.do?title="+ title + "&apply_year=" + apply_year;
         });
 });
	</script>
</body>
</html>