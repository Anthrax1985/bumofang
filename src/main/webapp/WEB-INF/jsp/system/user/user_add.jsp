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
<style>
#table1,#table2{display:none;}
</style>		
<script type="text/javascript">
	$(top.hangge());
	$(document).ready(function(){	
	});
		
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

</script>
	</head>
<body>
	<form action="user/${msg }.do" name="userForm" id="userForm" method="post">
		<div id="zhongxin" style="padding-top:20px;">
		<table class="table noline" id="table0">
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">账号</td>
				<td><input type="text" name="USERNAME0" id="loginname" value="${pd.USERNAME }" maxlength="32" title="用户名"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">姓名</td>
				<td><input type="text" name="NAME0" id="name"  value="${pd.NAME }"  maxlength="32" title="请输入姓名"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">工号</td>
				<td><input type="text" name="JOB_NUMBER0" id="job_number"  value="${pd.JOB_NUMBER}"  maxlength="32" title="姓名"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">职级</td>
				<td>
					<select class="chzn-select" name="ROLE_ID0" id="role_id" data-placeholder="职位" style="vertical-align:top;">
						<c:if test="${role.ROLE_ID == null ||role.ROLE_ID ==''}">
							<option value="" selected>请选择职级</option>
						</c:if>
						<c:forEach items="${roleList}" var="role">
							<option value="${role.ROLE_ID }" <c:if test="${role.ROLE_ID == pd.ROLE_ID }">selected</c:if>>${role.ROLE_NAME }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>	
				<td style="width:80px;text-align: right;padding-top: 13px;"> </td>
				<td><span>初始密码: 12345678</span></td>
			</tr>	
		</table>
		<table class="table noline" id="table1">
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">账号</td>
				<td><input type="text" name="USERNAME1" id="loginname" value="${pd.USERNAME }" maxlength="32" title="用户名"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">姓名</td>
				<td><input type="text" name="NAME1" id="name"  value="${pd.NAME }"  maxlength="32" title="请输入姓名"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">工号</td>
				<td><input type="text" name="JOB_NUMBER1" id="job_number"  value="${pd.JOB_NUMBER}"  maxlength="32" title="姓名"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">职级</td>
				<td>
					<select class="chzn-select" name="ROLE_ID1" id="role_id" data-placeholder="职位" style="vertical-align:top;">
						<c:if test="${role.ROLE_ID == null ||role.ROLE_ID ==''}">
							<option value="" selected>请选择职级</option>
						</c:if>
						<c:forEach items="${roleList}" var="role">
							<option value="${role.ROLE_ID }" <c:if test="${role.ROLE_ID == pd.ROLE_ID }">selected</c:if>>${role.ROLE_NAME }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>	
				<td style="width:80px;text-align: right;padding-top: 13px;">    </td>
				<td><span>初始密码: 12345678</span></td>
			</tr>	
		</table>
		<table class="table noline" id="table2">
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">账号</td>
				<td><input type="text" name="USERNAME2" id="loginname" value="${pd.USERNAME }" maxlength="32" title="用户名"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">姓名</td>
				<td><input type="text" name="NAME2" id="name"  value="${pd.NAME }"  maxlength="32" title="请输入姓名"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">工号</td>
				<td><input type="text" name="JOB_NUMBER2" id="job_number"  value="${pd.JOB_NUMBER}"  maxlength="32" title="姓名"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">职级</td>
				<td>
					<select class="chzn-select" name="ROLE_ID2" id="role_id" data-placeholder="职位" style="vertical-align:top;">
						<c:if test="${role.ROLE_ID == null ||role.ROLE_ID ==''}">
							<option value="" selected>请选择职级</option>
						</c:if>
						<c:forEach items="${roleList}" var="role">
							<option value="${role.ROLE_ID }" <c:if test="${role.ROLE_ID == pd.ROLE_ID }">selected</c:if>>${role.ROLE_NAME }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>	
				<td style="width:80px;text-align: right;padding-top: 13px;">    </td>
				<td><span>初始密码: 12345678</span></td>
			</tr>	
		</table>
		<span style="text-align: center; width:420px;margin-left:100px;">
			<a id="saveBtn" class="btn btn-primary" style="width:80px;margin-right:50px;" onclick="save(this);">保  存</a>
			<a id="saveBtn" class="btn btn-primary" style="width:80px;" onclick="addMore();">继续添加</a>
		</span>
		</div>		
	</form>
<script type="text/javascript">
var num = 0;
function addMore(){
	num++;
	if(num>2){
		alert("一次最多只能添加3个账号！");
		return false;
	}
	$("#table"+num).css("display","block");
}
</script>	
</body>
</html>