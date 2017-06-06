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
<title>查看结果</title>
<link href="Public/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="Public/css/style.css" rel="stylesheet">
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
	   <div class="panel panel-info">
		  <div class="panel-heading">
		    <h3 class="panel-title">投票</h3>
		  </div>
		  <div class="panel-body">
		  <div class="text_center">
			<h3>${result.theme}</h3>
			<p>发起人：${result.owner} </p>
		    <table class="table table-hover">	
		    	<tr><th>选项</th><th>票数</th></tr>
				<c:forEach items="${result.item}" var="i" >		
				  <tr><td>${i.item}</td><td>${i.number}</td></tr>
				</c:forEach>
		    </table>
		    </div>
		  </div>
		</div>
    </div>
    <script src="Public/bootstrap/jquery/jquery.min.js"></script>
	<script src="Public/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>