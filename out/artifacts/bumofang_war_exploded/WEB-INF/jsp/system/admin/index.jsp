<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">

	<!-- jsp文件头和头部 -->
	<%@ include file="top.jsp"%>
	
	<!-- 即时通讯 -->
	<!--<link rel="stylesheet" type="text/css" href="plugins/websocketInstantMsg/ext4/resources/css/ext-all.css">
	<link rel="stylesheet" type="text/css" href="plugins/websocketInstantMsg/css/websocket.css" />
	<script type="text/javascript" src="plugins/websocketInstantMsg/ext4/ext-all-debug.js"></script>
	<script type="text/javascript" src="plugins/websocketInstantMsg/websocket.js"></script>
	-->
	<!-- 即时通讯 -->
	
	<script type="text/javascript">
		$("#sidebar-collapse").on("click",function(){
      $("#sidebar").toggleClass("menu-min");
      $(this.firstChild).toggleClass("icon-double-angle-right");
      a=$("#sidebar").hasClass("menu-min");
     // document.cookie="hasMenu="+a+";";
     if(a){$(".open > .submenu").removeClass("open")}
      }
		);
	</script>
	
</head>
<body class="no-skin" onready="setContentHeight()">

	<!-- 页面顶部¨ -->
	<%@ include file="head.jsp"%>
	<div lass="main-container ace-save-state" id="main-container">
		<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>

		<script type="text/javascript">
				function setContentHeight(){
					var content = document.getElementById("main-content");
					var bheightT = document.documentElement.clientHeight;
					content.style.height = (bheightT  - 51) + 'px';
				}
				window.onload = function(){
					setContentHeight();
				}
				window.onresize=function(){  
					setContentHeight();
				};
			</script>	
		<!-- menu toggler -->

		<!-- 左侧菜单 -->
		<%@ include file="left.jsp"%>

		<div id="main-content" class="main-content">
			<div class="main-content-inner" style="height:100%">
					<!-- #section:basics/content.breadcrumbs -->
					<div id="jzts" style="display:none; width:100%; position:fixed; z-index:99999999;">
						<div style="padding-left: 45%;padding-top: 20%;">
							<div style="float: left;margin-top: 3px;"><img src="static/images/loading2.gif" /> </div>
						</div>
					</div>

					<div style="height:100%">
						<iframe name="mainFrame" id="mainFrame" frameborder="0" src="tab.do" style="margin:0 auto;width:100%;height:100%;"></iframe>
					</div>
					
			</div>	
			

			
			<!--/#ace-settings-container-->

		</div>
		<!-- #main-content -->
	</div>
	<!--/.fluid-container#main-container-->
	<!-- basic scripts -->
		<!-- 引入 -->
	
</body>
</html>
