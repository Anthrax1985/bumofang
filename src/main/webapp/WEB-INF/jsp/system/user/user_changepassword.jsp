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
	$(top.hangge());

	
	//保存
	function save(target){			
		if($("#user_id").val()=="" && $("#password").val()==""){		
			$("#password").tips({
				side:3,
	            msg:'输入密码',
	            bg:'#AE81FF',
	            time:2
	        });
			
			$("#password").focus();
			return false;
		}
		if($("#password").val()!=$("#chkpwd").val()){
			
			$("#chkpwd").tips({
				side:3,
	            msg:'两次密码不相同',
	            bg:'#AE81FF',
	            time:3
	        });
			
			$("#chkpwd").focus();
			return false;
		} 
		
			var action = '${msg }';
			var url = 'user/rest/' + action;
			ajaxSave(target, url, action);

		return false;
	}
	
	function ismail(mail){
		return(new RegExp(/^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/).test(mail));
		}
	

	

	
</script>
	</head>
<body>
	<form action="user/${msg }.do" name="userForm" id="userForm" method="post">
		<input type="hidden" name="USER_ID" id="user_id" value="${pd.USER_ID }"/>
		<div id="zhongxin" style="padding-top:20px;">
		<table class="table noline">		
 			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>输入新密码:</td>
				<td><input type="password" name="PASSWORD" id="password"  maxlength="32" minlength="6" placeholder="输入密码(最少六位)" title="密码"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>重新输入密码:</td>
				<td><input type="password" name="chkpwd" id="chkpwd"  maxlength="32" minlength="6" placeholder="确认密码" title="确认密码" /></td>
			</tr>			
		</table>
		<span style="text-align: center; width:420px;margin-left:120px;">
			<a id="saveBtn" class="btn btn-primary" style="width:100px;" onclick="save(this);">保  存</a>
		</span>
		</div>
		
	</form>
	
</body>
</html>