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
		
<script type="text/javascript">
	
	//保存
	function save(target){
		if($("#userName").val()==""){
			popupTip('userName', '请输入用户名');
			return false;
		}
		if($("#userIp").val()==""){
			popupTip('userIp', '请输入用户IP');
			return false;
		}
		if($("#roleType").val()==""){
			popupTip('roleType', '请输入职级');
			return false;
		}
		if($("#pageName").val()==""){
			popupTip('pageName', '请输入页面名称');
			return false;
		}
		if($("#operationContent").val()==""){
			popupTip('operationContent', '请输入操作内容');
			return false;
		}
	
		var action = '${msg}';
		var url = 'syslog/' + action;
		ajaxSave(target, url, action);
		return false;
	}
	
	function popupTip(field, msg){
		$("#"+field).tips({
			side:3,
            msg:msg,
            bg:'#AE81FF',
            time:2
        });
		$("#field").focus();
	}
	
</script>
	</head>
<body>
	<form action="syslog/${msg}.do" name="editForm" id="editForm" method="post">
		<input type="hidden" name="id" id="id" value="${entity.id}"/>
		<div id="zhongxin" style="padding-top:20px;">
		<table id="table_report" class="table noline">
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>用户名:</td>
				<td><input type="text" name="userName" id="userName" value="${entity.userName}" maxlength="32" placeholder="请输入用户名"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>用户IP:</td>
				<td><input type="text" name="userIp" id="userIp" value="${entity.userIp}" maxlength="32" placeholder="请输入用户IP"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>职级:</td>
				<td><input type="text" name="roleType" id="roleType" value="${entity.roleType}" maxlength="32" placeholder="请输入职级"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>页面名称:</td>
				<td><input type="text" name="pageName" id="pageName" value="${entity.pageName}" maxlength="32" placeholder="请输入页面名称"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>操作内容:</td>
				<td><input type="text" name="operationContent" id="operationContent" value="${entity.operationContent}" maxlength="32" placeholder="请输入操作内容"/></td>
			</tr>
			<tr>
				<td id="successMessage" style="text-align: center;display:none;color:#045e9f" colspan="10">
					保存成功
				</td>
			</tr>
			<tr>
				<td style="text-align: center;" colspan="10">
					<a id="saveBtn" class="btn btn-primary" style="width:100px" onclick="save(this);">保存</a>
				</td>
			</tr>
		</table>
		</div>
		
	</form>
</body>
</html>