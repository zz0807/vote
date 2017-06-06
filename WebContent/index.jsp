<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投票</title>
<link href="Public/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<style>
.search{
width:30%;
margin:0 auto;
}
</style>
</head>
<body>
	<nav class="navbar navbar-inverse">
		 <div class="container">
		   	<ul class="nav navbar-nav">
			 	<li>
	          	   <a class="navbar-brand">投票系统</a>
	          	</li>
          	</ul>
		  	<ul class="nav navbar-nav navbar-right">
        	   <li><button class="btn btn-info navbar-btn" type="button" data-toggle="modal" data-target="#login">登录</button></li>
        	   <li>&nbsp&nbsp</li>
        	   <li><button class="btn btn-success navbar-btn" type="button" data-toggle="modal" data-target="#register">注册</button></li>
          	</ul>
          </div>
	</nav>
    <div class="container">
    	<div class="search">
	     	<form class="form-inline" action="search" method="post">
		        <div class="form-group">
		          <input type="text" class="form-control" name="theme_key"placeholder="输入投票主题">
		        </div>
	        	<input type="submit" class="btn btn-success" value="搜索">
	      	</form>
      	</div>
      	<br/>
		<table class="table table-hover">
	  		<tr><th>投票主题</th><th>发起人</th><th>结束时间</th><th>进入投票</th><th>查看结果</th></tr>
	  	    <c:forEach items="${result}" var="i" >
		 	<tr>
		 		<td>${i.theme}</td>
		 		<td>${i.owner}</td>
		 		<td>${i.endDate}</td>
		 		<td>
					<c:choose>
					    <c:when test="${i.show == 0}">
					    过期了
					    </c:when>
					     <c:when test="${i.status == 1 && i.show == 1}">
					    发起人禁止
					    </c:when>
					    <c:otherwise>
							<a href="voting?vote_id=${i.id}">
							  <input class="btn btn-default" type="button" value="点击进入">
							</a>					   
						 </c:otherwise>
					</c:choose>
	
		 		</td>
			 	<td>
			 		<a href="detail?vote_id=${i.id}"><input class="btn btn-default" type="button" value="点击查看"></a>
			 	</td>
		 	</tr>
		</c:forEach>
	  	</table>
	</div>
<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" id="login">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
   	   <div class="modal-header">
      		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      		<h4 class="modal-title">登录</h4>
       </div>
       <form action="login" method="post">
	      <div class="modal-body">
			  <div class="form-group">
			    <label for="account">账号</label>
			    <input type="text" class="form-control" id="account" placeholder="账号" name="account">
			  </div>
			  <div class="form-group">
			    <label for="password">密码</label>
			    <input type="password" class="form-control" id="password" placeholder="密码" name="password">
			  </div>	
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="submit" class="btn btn-primary">登录</button>
	      </div>
	   </form>
    </div>
  </div>
</div>
<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" id="register">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
        <div class="modal-header">
      		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      		<h4 class="modal-title">注册</h4>
       </div>
	   <form action="register" method="post">
	      <div class="modal-body">
	      	  <div class="form-group">
			    <label for="account">账户</label>
			    <input type="text" class="form-control" id="account" name="account" placeholder="注册账户">
			  </div>
			  <div class="form-group">
			    <label for="r_username">姓名</label>
			    <input type="text" class="form-control" id="r_username" name="username" placeholder="注册用户名">
			  </div>
			  <div class="form-group">
			    <label for="r_password">密码</label>
			    <input type="password" class="form-control" id="r_password" name="password"placeholder="注册密码">
			  </div>	
			  <div class="form-group">
			    <label for="confirm_password">确认密码</label>
			    <input type="password" class="form-control" id="confirm_password" placeholder="再输一遍密码">
			  </div>	
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="submit" class="btn btn-success">注册</button>
	      </div>
	   </form>
    </div>
  </div>
</div>
<script src="Public/bootstrap/jquery/jquery.min.js"></script>
<script src="Public/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>