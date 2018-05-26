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
	$(document).ready(function(){
		if($("#user_id").val()!=""){
			$("#loginname").attr("readonly","readonly");
			$("#loginname").css("color","gray");			
		}else{
			$("#formatPassword").css("display","none");
		}
	});
	
	function cancel(){
		location.reload();
	}
	
	//保存
	function save(target){
		if($("#role_id").val()==""){
			
			$("#role_id").tips({
				side:3,
	            msg:'选择角色',
	            bg:'#AE81FF',
	            time:2
	        });
			
			$("#role_id").focus();
			return false;
		}
		if($("#loginname").val()=="" || $("#loginname").val()=="此用户名已存在!"){
			
			$("#loginname").tips({
				side:3,
	            msg:'输入用户名',
	            bg:'#AE81FF',
	            time:2
	        });
			
			$("#loginname").focus();
			$("#loginname").val('');
			$("#loginname").css("background-color","white");
			return false;
		}else{
			$("#loginname").val(jQuery.trim($('#loginname').val()));
		}
		/* 
		if($("#NUMBER").val()==""){
			
			$("#NUMBER").tips({
				side:3,
	            msg:'输入编号',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#NUMBER").focus();
			return false;
		}else{
			$("#NUMBER").val($.trim($("#NUMBER").val()));
		} */
		
		/* if($("#user_id").val()=="" && $("#password").val()==""){
			
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
		} */
		if($("#name").val()==""){
			
			$("#name").tips({
				side:3,
	            msg:'输入姓名',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#name").focus();
			return false;
		}
		
		if($("#job_number").val()==""){
			
			$("#job_number").tips({
				side:3,
	            msg:'输入工号',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#name").focus();
			return false;
		}
		
/* 		var myreg = /^(((13[0-9]{1})|159)+\d{8})$/;
		if($("#PHONE").val()==""){
			
			$("#PHONE").tips({
				side:3,
	            msg:'输入手机号',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#PHONE").focus();
			return false;
		}else if($("#PHONE").val().length != 11 && !myreg.test($("#PHONE").val())){
			$("#PHONE").tips({
				side:3,
	            msg:'手机号格式不正确',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#PHONE").focus();
			return false;
		}
		
		if($("#EMAIL").val()!="" && !ismail($("#EMAIL").val())){
			$("#EMAIL").tips({
				side:3,
	            msg:'邮箱格式不正确',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#EMAIL").focus();
			return false;
		} */
		
		if($("#user_id").val()==""){
			hasU(target);
		}else{
			var action = '${msg }';
			var url = 'user/rest/' + action;
			ajaxSave(target, url, action);
		}

		return false;
	}
	
	function ismail(mail){
		return(new RegExp(/^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/).test(mail));
		}
	
	//判断用户名是否存在
	function hasU(target){
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
					setTimeout("$('#loginname').val('此用户名已存在!')",500);
				 }
			}
		});
	}
	
	//判断邮箱是否存在
<%-- 	function hasE(USERNAME){
		var EMAIL = $.trim($("#EMAIL").val());
		$.ajax({
			type: "POST",
			url: '<%=basePath%>user/hasE.do',
	    	data: {EMAIL:EMAIL,USERNAME:USERNAME,tm:new Date().getTime()},
			dataType:'json',
			cache: false,
			success: function(data){
				 if("success" != data.result){
					 $("#EMAIL").tips({
							side:3,
				            msg:'邮箱已存在',
				            bg:'#AE81FF',
				            time:3
				        });
					setTimeout("$('#EMAIL').val('')",2000);
				 }
			}
		});
	} --%>
	
	//判断编码是否存在
<%-- 	function hasN(USERNAME){
		var NUMBER = $.trim($("#NUMBER").val());
		$.ajax({
			type: "POST",
			url: '<%=basePath%>user/hasN.do',
	    	data: {NUMBER:NUMBER,USERNAME:USERNAME,tm:new Date().getTime()},
			dataType:'json',
			cache: false,
			success: function(data){
				 if("success" != data.result){
					 $("#NUMBER").tips({
							side:3,
				            msg:'编号已存在',
				            bg:'#AE81FF',
				            time:3
				        });
					setTimeout("$('#NUMBER').val('')",2000);
				 }
			}
		});
	} --%>
	
</script>
	</head>
<body>
	<form action="user/${msg }.do" name="userForm" id="userForm" method="post">
		<input type="hidden" name="USER_ID" id="user_id" value="${pd.USER_ID }"/>
		<div id="zhongxin" style="padding-top:20px;">
		<table class="table noline">
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>账户</td>
				<td><input type="text" name="USERNAME" id="loginname" value="${pd.USERNAME }" maxlength="32" placeholder="请输入账号" title="用户名"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>姓名</td>
				<td><input type="text" name="NAME" id="name"  value="${pd.NAME }"  maxlength="32" placeholder="姓名" title="请输入姓名"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>工号</td>
				<td><input type="text" name="JOB_NUMBER" id="job_number"  value="${pd.JOB_NUMBER}"  maxlength="32" placeholder="请输入工号" title="姓名"/></td>
			</tr>

			<c:if test="${fx != 'head'}">
			<c:if test="${pd.ROLE_ID != '1'}">	
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>职位</td>
				<td>
					<select class="chzn-select" name="ROLE_ID" id="role_id" data-placeholder="职位" style="vertical-align:top;">
						<c:if test="${role.ROLE_ID == null ||role.ROLE_ID ==''}">
							<option value="" selected>请选择工号</option>
						</c:if>
						<c:forEach items="${roleList}" var="role">
							<option value="${role.ROLE_ID }" <c:if test="${role.ROLE_ID == pd.ROLE_ID }">selected</c:if>>${role.ROLE_NAME }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			</c:if>
			<c:if test="${pd.ROLE_ID == '1'}">
			<input name="ROLE_ID" id="role_id" value="1" type="hidden" />
			</c:if>
			</c:if>
			<tr>	
				<td style="width:80px;text-align: right;padding-top: 13px;"> <input type="checkbox" name ="formatPassword"  id="formatPassword" value="1"> </td>
				<td><span>初始密码: 12345678</span></td>
			</tr>
			<c:if test="${fx == 'head'}">
				<input name="ROLE_ID" id="role_id" value="${pd.ROLE_ID }" type="hidden" />
			</c:if>			
<%-- 			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>账户名称:</td>
				<td><input type="password" name="PASSWORD" id="password"  maxlength="32" minlength="6" placeholder="输入密码(最少六位)" title="密码"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>账户名称:</td>
				<td><input type="password" name="chkpwd" id="chkpwd"  maxlength="32" minlength="6" placeholder="确认密码" title="确认密码" /></td>
			</tr>

			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>账户名称:</td>
				<td><input type="hidden" name="PHONE" id="PHONE"  value="${pd.PHONE }"  maxlength="11" minlength="11" placeholder="请输入手机号" title="手机号"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>账户名称:</td>
				<td><input type="email" name="EMAIL" id="EMAIL"  value="${pd.EMAIL }" maxlength="32" placeholder="输入邮箱(可不填)" title="邮箱"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>账户名称:</td>
				<td><input type="text" name="BZ" id="BZ"value="${pd.BZ }" placeholder="请输入备注" maxlength="64" title="备注"/></td>
			</tr> --%>
<!-- 			<tr>
				<td style="text-align: center;">
					<a id="saveBtn" class="btn btn-primary" style="width:200px;" onclick="save(this);">保  存</a>
				</td>				
			</tr> -->
		</table>
		<span style="text-align: center; width:420px;margin-left:100px;">
			<a id="saveBtn" class="btn btn-primary" style="width:80px;margin-right:50px;" onclick="save(this);">保  存</a>
			<a id="saveBtn" class="btn btn-primary" style="width:80px;" onclick="cancel();">取 消</a>
		</span>
		</div>
		
	</form>
	
</body>
</html>