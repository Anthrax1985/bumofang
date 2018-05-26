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
		//判断用户名是否存在
		var USERNAME = $.trim($("#loginname").val());
		$.ajax({
			type: "POST",
			url: '<%=basePath%>user/hasU.do',
	    	data: {USERNAME:USERNAME,tm:new Date().getTime()},
			dataType:'json',
			cache: false,
			success: function(data){
				 if("success" == data.result){
					var action = '${msg }';
					var url = 'user/rest/' + action;
					ajaxSave(target, url, action);
				 }else{
					$("#loginname").css("background-color","#D16E6C");
					setTimeout("$('#loginname').val('此邮箱已存在!')",500);
				 }
			}
		});
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
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>新邮箱:</td>
				<td><input type="text" name="USERNAME" id="loginname" value="${pd.USERNAME }" maxlength="32" placeholder="请输入账号" title="用户名"/></td>
			</tr>		
		</table>
		<span style="text-align: center; width:420px;margin-left:120px;">
			<a id="saveBtn" class="btn btn-primary" style="width:100px;" onclick="save(this);">保  存</a>
		</span>
		</div>
		
	</form>
	
</body>
</html>