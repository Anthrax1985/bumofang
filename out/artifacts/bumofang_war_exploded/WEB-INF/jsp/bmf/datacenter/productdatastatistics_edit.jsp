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
		if($("#memberId").val()==""){
			popupTip('memberId', '请输入用户id');
			return false;
		}
		if($("#productId").val()==""){
			popupTip('productId', '请输入产品id');
			return false;
		}
		if($("#ipadIp").val()==""){
			popupTip('ipadIp', '请输入iPadIP');
			return false;
		}
		if($("#browseTime").val()==""){
			popupTip('browseTime', '请输入浏览时间');
			return false;
		}
	
		var action = '${msg}';
		var url = 'productdatastatistics/' + action;
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
	<form action="productdatastatistics/${msg}.do" name="editForm" id="editForm" method="post">
		<input type="hidden" name="id" id="id" value="${entity.id}"/>
		<div id="zhongxin" style="padding-top:20px;">
		<table id="table_report" class="table noline">
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>用户id:</td>
				<td><input type="number" name="memberId" id="memberId" value="${entity.memberId}" maxlength="32" placeholder="请输入用户id"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>产品id:</td>
				<td><input type="number" name="productId" id="productId" value="${entity.productId}" maxlength="32" placeholder="请输入产品id"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>iPadIP:</td>
				<td><input type="text" name="ipadIp" id="ipadIp" value="${entity.ipadIp}" maxlength="32" placeholder="请输入iPadIP"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>浏览时间:</td>
				<td><input type="text" name="browseTime" id="browseTime" value="${entity.browseTime}" maxlength="32" placeholder="请输入浏览时间"/></td>
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