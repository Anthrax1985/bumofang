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
		if($("#productId").val()==""){
			popupTip('productId', '请输入产品id');
			return false;
		}
		if($("#matchProductId").val()==""){
			popupTip('matchProductId', '请输入搭配方案');
			return false;
		}
		if($("#matchProductIcon").val()==""){
			popupTip('matchProductIcon', '请输入搭配方案图片');
			return false;
		}
	
		var action = '${msg}';
		var url = 'productrecordmatchscheme/' + action;
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
	<form action="productrecordmatchscheme/${msg}.do" name="editForm" id="editForm" method="post">
		<input type="hidden" name="id" id="id" value="${entity.id}"/>
		<div id="zhongxin" style="padding-top:20px;">
		<table id="table_report" class="table noline">
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>产品id:</td>
				<td><input type="number" name="productId" id="productId" value="${entity.productId}" maxlength="32" placeholder="请输入产品id"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>搭配方案:</td>
				<td><input type="number" name="matchProductId" id="matchProductId" value="${entity.matchProductId}" maxlength="32" placeholder="请输入搭配方案"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>搭配方案图片:</td>
				<td><input type="text" name="matchProductIcon" id="matchProductIcon" value="${entity.matchProductIcon}" maxlength="32" placeholder="请输入搭配方案图片"/></td>
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