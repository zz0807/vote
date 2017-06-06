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
 
	<c:forEach items="${result.item}" var="i" >	
		<input type="hidden" name="item" value="${i.item}">
		<input type="hidden" name="number" value="${i.number}">	
	</c:forEach>
	
    <div id="histogram" style="width: 550px; height: 400px; margin: 0 auto"></div>
    <div id="pie" style="width: 550px; height: 400px; margin: 0 auto"></div>

    <script src="Public/bootstrap/jquery/jquery.min.js"></script>
	<script src="Public/bootstrap/js/bootstrap.min.js"></script>
    <script src="Public/highcharts/highcharts.js"></script>
    <script language="JavaScript">
$(document).ready(function() {  
   var chart = {
      type: 'column'
   };
   var title = {
      text: '${result.theme}'
   };
   var subtitle = {
	  text: '发起人:${result.owner}'

   };
   var xAxis = {
      categories: ['选项'],
      crosshair: true
   };
   var yAxis = {
      min: 0,
      title: {
         text: '票数'         
      }      
   };
   var tooltip = {
      headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
      pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
         '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
      footerFormat: '</table>',
      shared: true,
      useHTML: true
   };
   var plotOptions = {
      column: {
         pointPadding: 0.2,
         borderWidth: 0
      }
   };  
   var credits = {
      enabled: false
   };
   var series = new Array();
   
   var item = document.getElementsByName("item");
   var number = document.getElementsByName("number");
   for(var i=0;i<item.length;i++)
   {
	   var it = new Object();
       it.name = item[i].value;
       var t = [parseInt(number[i].value)];
       it.data = t;
       series[i] = it;
   }		
       
   var json = {};   
   json.chart = chart; 
   json.title = title;   
   json.subtitle = subtitle; 
   json.tooltip = tooltip;
   json.xAxis = xAxis;
   json.yAxis = yAxis;  
   json.series = series;
   json.plotOptions = plotOptions;  
   json.credits = credits;
   $('#histogram').highcharts(json);
   var chart = {
	       plotBackgroundColor: null,
	       plotBorderWidth: null,
	       plotShadow: false
	   };
	   var title = {
	      text: ''   
	   };      
	   var tooltip = {
	      pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	   };
	   var plotOptions = {
	      pie: {
	         allowPointSelect: true,
	         cursor: 'pointer',
	         dataLabels: {
	            enabled: true,
	            format: '<b>{point.name}%</b>: {point.percentage:.1f} %',
	            style: {
	               color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
	            }
	         }
	      }
	   };
	   var data = new Array();
	   for(var i=0;i<item.length;i++)
	   {
		   var t1 = new Array();
	       t1[0] = item[i].value;
	       t1[1]= parseInt(number[i].value)
	       data[i] = t1;
	   }		
	   var series= [{
	      type: 'pie',
	      name: '选项',
	  /*     data: [
	         ['Firefox',   45.0],
	         ['IE',       26.8],
	         ['Safari',    8.5],
	         ['Opera',     6.2],
	         ['Others',   0.7]
	      ] */
	   }];  
	   series[0].data = data;
	      
	   var json = {};   
	   json.chart = chart; 
	   json.title = title;     
	   json.tooltip = tooltip;  
	   json.series = series;
	   json.plotOptions = plotOptions;
   $('#pie').highcharts(json);

  
});
</script>
</body>
</html>