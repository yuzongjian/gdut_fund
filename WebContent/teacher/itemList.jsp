<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html lang="zh">
<%@ include file="/teacher/teacherCommon.jsp" %>
<head><title>立项清单管理</title></head>
		<div class="layui-body" id="content_body">
			<!-- 面包屑 -->
			<span class="layui-breadcrumb head-breadcrumb">
				<i class="iconfont">&#xe61a;</i>
				<a href="<%=basePath%>projectApplyList.jsp">首页</a>
				<a href="<%=basePath%>itemList.jsp">项目管理</a>
				<a><cite>立项清单管理</cite></a>
			</span>
			<!-- 内容主体区域 -->
			<div style="padding: 15px;">
				<!-- 查询部分 -->
				<form class="layui-form" action="" method="">
			
  <div class="layui-form-item">
  <label class="layui-form-label">批准号：</label>
         <div class="layui-input-inline">
 <input type="text" class="layui-input" placeholder="请输入项目批准号" autocomplete="off" name="">
     </div>
      <label class="layui-form-label">项目名称：</label>
        <div class="layui-input-inline">
 <input type="text" class="layui-input" placeholder="请输入项目名称" autocomplete="off" name="">
     </div>   
					<button class="layui-btn layui-btn-normal layui-btn-small" layui-submit>
							<i class="layui-icon">&#xe615;</i> 查询
						</button>
						<button class="layui-btn layui-btn-normal layui-btn-small" type="reset">
							<i class="layui-icon">&#x1002;</i>重置条件
						</button>
							<button class="layui-btn layui-btn-normal layui-btn-small" type="reset">
							<i class="layui-icon">&#xe608;</i>新建
						</button>
					</div>
	</form>
  <table class="layui-table">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>项目批准号</th>
                    <th>项目名称</th>
                    <th>二级单位</th>
                    <th>资助类型</th>
                    <th>操作</th>     
                </tr>
                
<!--       以下内容替换成循环遍历  -->
             <tbody>
                 <tr>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>     
                </tr>
         </tbody>          
<div class="fenyeWrap">
  </table>
  <center>
  <div id="fenye"></div>
                <div>共  条记录</div>
            </div>
        </div>
    </div>
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