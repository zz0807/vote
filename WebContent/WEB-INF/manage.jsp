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
<title>后台管理界面</title>
<link href="Public/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="Public/bootstrap/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<nav class="navbar navbar-inverse">
	 <div class="container">
	   	<ul class="nav navbar-nav">
		 	 <li>
	           <a class="navbar-brand">后台管理</a>
	         </li>
	    </ul>
	  	<ul class="nav navbar-nav navbar-right">
	      	  <li><a>欢迎你</a></li>
	      	  <li><a>${username}</a></li>
	     </ul>
	   </div>
</nav>
<div class="container">
	<button class="btn btn-default navbar-btn" type="button" data-toggle="modal" data-target="#vote">发起投票</button>
	<table class="table table-hover">
		<tr><th>投票主题</th><th>发起时间</th><th>截止时间</th><th>详情</th><th>状态</th><th>禁止</th><th>恢复</th></tr>
		<c:forEach items="${result}" var="i" >
		 	<tr><td>${i.theme}</td><td>${i.startDate}</td><td>${i.endDate}</td><td><a href="detail?vote_id=${i.id}"><button class="btn btn-default" type="button">查看详情</button></a>
		 	</td><td><c:choose>
					    <c:when test="${i.status == 0}">
					    正常
					    </c:when>
					    <c:otherwise>
							禁止			   
						 </c:otherwise>
					</c:choose></td><td><button class="btn btn-danger" type="button" onclick="op(${i.id},1)">禁止</button></td><td><button class="btn btn-warning" type="button" onclick="op(${i.id},0)">恢复</button></td></tr>
		</c:forEach>
	</table>
</div>
<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" id="vote">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
   	   <div class="modal-header">
      		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      		<h4 class="modal-title">发起投票</h4>
       </div>
       <form action="newvote" method="post">
	      <div class="modal-body">
			  <div class="form-group">
			    <label for="theme">主题</label>
			    <input type="text" class="form-control" id="theme" placeholder="主题" name="theme">
			  </div>
		      <div class="form-group">
                    <label for="end_date" class="control-label">截止日期</label>
                    <div class="input-group date form_date" data-date="" data-date-format="dd MM yyyy" data-link-field="end_date" data-link-format="yyyy-mm-dd">
	                    <input class="form-control" size="16" type="text" value="" readonly>
	     			    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                     </div>
     				<input type="hidden" id="end_date" value="" name="end_date"/>
               </div>
			    <label class="radio-inline">
			    <input type="radio" name="type" id="type" value="1">
			    单选
			   </label>
			  <label class="radio-inline">
			    <input type="radio" name="type" id="type" value="2">
			    多选			  
			    </label>
			    <hr/>
			    <div id="item">
				    <label>选项</label>
				    <div class="form-group">
				    	<input type="text" class="form-control"name="item">
				    </div>
				     <div class="form-group">
				    	<input type="text" class="form-control"name="item">
				    </div>
			    </div>
			    <div class="text_center"><button type="button" class="btn btn-info" id="add_item"><span class="glyphicon glyphicon-plus">添加选项</span></button></div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="submit" class="btn btn-primary">提交</button>
	      </div>
	   </form>
    </div>
  </div>
</div>
<script src="Public/bootstrap/jquery/jquery.min.js" type="text/javascript"></script>
<script src="Public/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="Public/js/bootstrap-datetimepicker.js" type="text/javascript"></script>
<script src="Public/js/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>
<script src="Public/js/vote.js" type="text/javascript"></script>
</body>
</html>