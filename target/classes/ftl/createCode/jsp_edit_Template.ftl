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
	var fieldArray = [<#list fieldList as var><#if var[3] == "是">"${var[0] }",</#if></#list>""];
	
	//保存
	function save(target){
		<#list fieldList as var>
			<#if var[3] == "是">
			if($("#${var[0] }").val()==""){
				popupTip('${var[0]}', '请输入${var[2] }');
				return false;
			}
			</#if>
		</#list>
	
		var action = '${r"${msg}"}';
		var url = '${objectNameLower}/rest/' + action;
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
	<form action="${objectNameLower}/${r"${msg }"}.do" name="Form" id="Form" method="post">
		<input type="hidden" name="id" id="id" value="${r"${pd."}id${r"}"}"/>
		<div id="zhongxin" style="padding-top:20px;">
		<table id="table_report" class="table noline">
<#list fieldList as var>
	<#if var[3] == "是">
		<#if var[1] == 'Date'>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>${var[2] }:</td>
				<td><input class="span10 date-picker" name="${var[0] }" id="${var[0] }" value="${r"${pd."}${var[0] }${r"}"}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="${var[2] }" title="${var[2] }"/></td>
			</tr>
		<#elseif var[1] == 'Integer'>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>${var[2] }:</td>
				<td><input type="number" name="${var[0] }" id="${var[0] }" value="${r"${pd."}${var[0] }${r"}"}" maxlength="32" placeholder="请输入${var[2] }" title="${var[2] }"/></td>
			</tr>
		<#else>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>${var[2] }:</td>
				<td><input type="text" name="${var[0] }" id="${var[0] }" value="${r"${pd."}${var[0] }${r"}"}" maxlength="32" placeholder="请输入${var[2] }" title="${var[2] }"/></td>
			</tr>
		</#if>
	</#if>
</#list>
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