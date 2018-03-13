<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html lang="zh">
<%@ include file="/admin/adminCommon.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head><title>项目统计分析</title></head>
		<div class="layui-body" id="content_body">
			<!-- 面包屑 -->
			<span class="layui-breadcrumb head-breadcrumb">
				<i class="iconfont">&#xe61a;</i>
				<a href="${pageContext.request.contextPath}/adminSelectAll.do">首页</a>
				<a href="<%=basePath%>StatisticList.do">统计分析</a>
				<a><cite>项目统计分析</cite></a>
			</span>
			<!-- 内容主体区域 -->
			<div style="padding: 15px;">
				<!-- 查询部分 -->
<form class="layui-form" action="${pageContext.request.contextPath}/admin/SearchStatistics.do" method="post">
<input type="hidden" id="d" value="${dept_name}">
<input type="hidden" id="f" value="${firstYear}">
<input type="hidden" id="s" value="${secondYear}">
					<label class="layui-form-label">学院：</label>
<div class="layui-input-inline">
 <input type="text" class="layui-input" placeholder="请输入学院" autocomplete="off" name="dept_name" id="dept_name">
     </div>
         <div class="layui-inline">
      <label class="layui-form-label">年份范围：</label>
      <div class="layui-input-inline">
        <input type="text" class="layui-input" id="firstYear" name="firstYear" placeholder="选择年份">
      </div>
    </div>
      <div class="layui-input-inline">
        <input type="text" class="layui-input" id="secondYear" name="secondYear" placeholder="选择年份">
      </div>
      	<button class="layui-btn layui-btn-normal layui-btn-small" layui-submit>
							<i class="layui-icon">&#xe615;</i> 查询
						</button>
						<button class="layui-btn layui-btn-normal layui-btn-small" layui-submit>
							<i class="layui-icon">&#xe615;</i> 统计
						</button>						
						<button id = "down" class="layui-btn layui-btn-normal layui-btn-small" type="reset" onclick="submitForm()">
							<i class="layui-icon">&#xe601;</i>导出数据
						</button>
					</div>

  <table class="layui-table">
                <thead>
                <tr>
                    <th>学院</th>
                    <th>教师人数</th>
                    <th>提交跟踪表</th>
                </tr>
             <!--       以下内容替换成循环遍历  -->
             <tbody>
                 <tr>
                    <c:forEach items="${statistics}" var="statistic" varStatus="st">
                 <tr>
                  	<c:if test="${statistic.itemTrackNum!=0}">
	                    <th>${statistic.dept_name}</th>
	                    <th>${statistic.teacherNum}</th>
	                    <th>${statistic.itemTrackNum}</th>
                    </c:if>
                </tr>
              </c:forEach>    
                </tr>
         </tbody>          
<div class="fenyeWrap">
  </table>
  </form>
	<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/nav.js"></script>
	<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
	<script>
	//JavaScript代码区域
	layui.use('laypage', function(){
  var laypage = layui.laypage;
  
	//JavaScript代码区域
	 layui.use('layer', function(){
var layer = layui.layer;
});              

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
 layui.use('laydate', function(){
	  var laydate = layui.laydate;	
	  laydate.render({
		    elem: '#firstYear'
		    ,type: 'year'
		  });
});
 layui.use('laydate', function(){
	  var laydate = layui.laydate;	
	  laydate.render({
		    elem: '#secondYear'
		    ,type: 'year'
		  });
});
 function checkForm(){
	//询问框
	 layer.confirm('您是否确定要删除？', {
	   btn: ['是','否'] //按钮
	 }, function(){
	   layer.msg('已成功删除', {icon: 1});
	   return true;
	 }, function(){
		 return false;
	 });}
  
    
 $(document).ready(function() {
     $("#down").click(function() {
             var d = $("#d").val();
             var f = $("#f").val();
             var s = $("#s").val();
             window.location.href = "${pageContext.request.contextPath}/DownLoadStatisticExcel.do?d="+ d + "&f=" + f +"&s="+s;
         });
 });
	</script>
</body>
</html>