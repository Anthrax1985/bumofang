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
	var fieldArray = ["MOBILE","PAY_PASSWORD","BALANCE",""];
	
	//保存
	function save(target){
			if($("#MOBILE").val()==""){
				popupTip('MOBILE', '请输入手机号');
				return false;
			}
			if($("#PAY_PASSWORD").val()==""){
				popupTip('PAY_PASSWORD', '请输入支付密码');
				return false;
			}
			if($("#BALANCE").val()==""){
				popupTip('BALANCE', '请输入余额');
				return false;
			}
	
		var action = '${msg}';
		var url = 'capital/rest/' + action;
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
	<form action="capital/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="ID" id="ID" value="${pd.ID}"/>
		<div id="zhongxin" style="padding-top:20px;">
		<table id="table_report" class="table noline">
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>手机号:</td>
				<td><input type="text" name="MOBILE" id="MOBILE" value="${pd.MOBILE}" maxlength="32" placeholder="请输入手机号" title="手机号"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>支付密码:</td>
				<td><input type="text" name="PAY_PASSWORD" id="PAY_PASSWORD" value="${pd.PAY_PASSWORD}" maxlength="32" placeholder="请输入支付密码" title="支付密码"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>余额:</td>
				<td><input type="text" name="BALANCE" id="BALANCE" value="${pd.BALANCE}" maxlength="32" placeholder="请输入余额" title="余额"/></td>
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