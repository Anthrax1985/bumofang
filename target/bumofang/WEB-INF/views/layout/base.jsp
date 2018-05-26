<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%-- <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> --%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html class="height-full">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<title>后台管理</title>
	<link rel="stylesheet" href="<%=basePath%>static/bumofang/admin/css/bootstrap.css">
	<link rel="stylesheet" href="<%=basePath%>static/bumofang/admin/css/style.css">
	<script src="<%=basePath%>static/bumofang/admin/js/jquery-3.1.1.min.js"></script>
	<script src="<%=basePath%>static/bumofang/admin/js/bootstrap.min.js"></script>
	<script src="<%=basePath%>static/bumofang/admin/js/zooming.min.js"></script>
	<script src="<%=basePath%>static/bumofang/js/vue.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".navmenu ul li").each(function(index,element){
			    var url=location.href;
			    var href = $(".navmenu ul li a" + ":eq(" + index + ")").attr("href");
			    if(!href){
			    	return;
			    }
			    var base = "<%=path%>";
			    var index = href.indexOf(base);
			    var value = href.substring(index + base.length + 1);
			    value = value.substring(0, value.indexOf("/"));
			    if(url.indexOf("/" + value + "/") != -1){
			      $(element).addClass("active");
			      return false;
			    }
			  });
		});
	</script>
</head>
<body class="height-full">
	<div class="top">
		<div class="mname">
			<div class="mtitle">布魔方后台管理系统</div>
		</div>
		<div class="user">
			<a href="" class="logout"><i class="iconfont">&#xe67d;</i></a>
		</div>
	</div>
	<nav class="navmenu">
		<div class="logo">
			<i class="iconfont">&#xe657;</i>
		</div>
		<ul>
			<li class=""><a href="<%=basePath %>users/list/1" class="menubtn">用户</a></li>
			<li class=""><a href="<%=basePath %>collocations/list/1" class="menubtn">搭配</a></li>
			<li class=""><a href="<%=basePath %>programmes/list/1" class="menubtn">方案</a></li>
			<li class=""><a href="<%=basePath %>orders/list/1" class="menubtn">订单</a></li>
			<li class=""><a href="<%=basePath %>fabrics/list/1" class="menubtn">面料</a></li>
			<li class=""><a href="" class="menubtn">组</a></li>
			<li class=""><a href="<%=basePath %>accessories/list/1" class="menubtn">配饰</a></li>
			<li class=""><a href="" class="menubtn">3D</a></li>
			<li class=""><a href="" class="menubtn">资金</a></li>
			<li class=""><a href="" class="menubtn">统计</a></li>
		</ul>
	</nav>
	<div class="container-box">
		<div class="container-fluid">
			<tiles:insertAttribute name="content" />
		</div>
	</div>
</body>
</html>