<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<base href="<%=basePath%>">
	<!-- jsp文件头和头部 -->
	<%@ include file="../admin/top.jsp"%> 
	</head> 
	<style type="text/css">
	.fluid { padding:0 5%; font-size:14px;}
	.fluid h4 { padding: 20px 0; font-weight: bold; font-size: 16px; }
	.ml-row { margin-bottom: 20px; }
	.ml-row .col-xs-1 { text-align: right;}
	.border-bottom { padding-bottom: 40px; border-bottom: 1px solid #cccccc; }
	.btn-gray { padding: 4px 17px; background: #f1f1f1; }
	</style>
<body>
		
<div class="container-fluid fluid">
	<h4>基本信息</h4>
	<div class="row ml-row">
		<div class="col-xs-1">账号</div>
		<div class="col-xs-11">${userInfo.USERNAME}</div>
	</div>
	<div class="row ml-row">
		<div class="col-xs-1">姓名</div>
		<div class="col-xs-11">${userInfo.NAME}</div>
	</div>
	<div class="row ml-row">
		<div class="col-xs-1">工号</div>
		<div class="col-xs-11">${userInfo.JOB_NUMBER}</div>
	</div>
	<div class="row ml-row">
		<div class="col-xs-1">职级</div>
		<div class="col-xs-11"> ${userInfo.ROLE_NAME}</div>
	</div>
	<div class="row ml-row">
		<div class="col-xs-1">密码</div>
		<div class="col-xs-11"> 
			<div class="row">
				<div class="col-xs-2">******** </div>
				<div class="col-xs-10"><input type="button" class='btn btn-xs btn-info' value="修改密码" onclick="changePassword('${userInfo.USER_ID }');"></div>
			</div>
		</div>
	</div>
	<div class="row ml-row border-bottom">
		<div class="col-xs-1">登陆时间</div>
		<div class="col-xs-11">上次登陆时间 ${userInfo.LAST_LOGIN_SHOW} </div>
	</div>
	<h4>邮箱绑定</h4>
	<div class="row ml-row border-bottom">
		<div class="col-xs-1">${userInfo.USERNAME}</div>
		<div class="col-xs-11"> 
			<div class="row">
				<div class="col-xs-2"></div>
				<div class="col-xs-10"><input type="button" class='btn btn-xs btn-info' value="修改邮箱" onclick="changeUserName('${userInfo.USER_ID }');"></div>
			</div>
		</div>
	</div>
	<h4>头像</h4>
	<div class="row ml-row">
		<div class="col-xs-2">
		<c:if test="${userInfo.AVATAR == null || userInfo.AVATAR == ''}">
					<img  src="static/img/default_avatar.png" style="width:200px;height:200px;border:1px solid #686868;">
		</c:if>
		<c:if test="${userInfo.AVATAR != null && userInfo.AVATAR != ''}">
					<img src="<%=basePath %>${userInfo.AVATAR}" style="width:200px;height:200px;border:1px solid #686868;">
		</c:if>		
		</div>
		<div class="col-xs-10">
				<div><input type="button" class='btn btn-default btn-gray' value="浏览" onclick="uploadAvatar('${userInfo.USER_ID }');"> 上传小于5M的jpg、png、bmp或gif文件</div>
				<!-- <div style="margin-top:130px;"><input type="button" class="btn btn-xs btn-info" style="width:64px;margin-right:10px" value="保存"><input type="button" class='btn btn-default btn-gray' value="取消	"></div> -->
		</div>
	</div>
</div>


<%-- 
<h3>基本信息</h3>
<p>账号 ${userInfo.USERNAME}</p>
<p>姓名  ${userInfo.NAME}</p>
<p>工号  ${userInfo.JOB_NUMBER}</p>
<p>职级 ${userInfo.ROLE_NAME}</p>
<p>密码  ******** <input type="button" class='btn btn-mini btn-info' value="修改密码" onclick="changePassword('${userInfo.USER_ID }');"></p> 
<p>上次登陆时间：${userInfo.LAST_LOGIN} </p>
<div>
<h1>邮箱绑定</h1>
<p>${userInfo.USERNAME}  <input type="button" class='btn btn-mini btn-info' value="修改邮箱" onclick="changeUserName('${userInfo.USER_ID }');"></p>
</div>
<div>
	<h1>头像</h1>
	<p>
		<img src="<%=basePath %>${userInfo.AVATAR}" height="80">
		<input type="button" class='btn btn-mini btn-info' value="修改头像" onclick="uploadAvatar('${userInfo.USER_ID }');">
	</p>
	<p><input type="button" value="浏览">上传小于5M的jpg、png、bmp或gif文件</p>
	<p><input type="button" value="保存"><input type="button" value="取消	"></p>
</div> --%>
				
<script type="text/javascript">
$(top.hangge());
//修改密码
function changePassword(user_id){
	BootstrapDialog.show({cssClass:'two-row-dialog',
        message: $('<div></div>').load('<%=basePath%>user/goChangePassword.do?USER_ID='+user_id),
        title: '修改密码'
      });
}
//修改邮箱（即账号username）
function changeUserName(user_id){
	BootstrapDialog.show({cssClass:'two-row-dialog',
        message: $('<div></div>').load('<%=basePath%>user/goChangeUserName.do?USER_ID='+user_id),
        title: '修改邮箱'
      });
}
//上传头像
function uploadAvatar(user_id){
	BootstrapDialog.show({cssClass:'two-row-dialog',
        message: $('<div></div>').load('<%=basePath%>user/goUploadAvatar.do?USER_ID='+user_id),
        title: '修改邮箱'
      });
}
</script>
		
	</body>
</html>

