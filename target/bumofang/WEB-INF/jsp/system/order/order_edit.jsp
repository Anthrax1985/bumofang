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
	var fieldArray = ["product_id","price","create_time",""];
	
	//保存
	function save(target){
			if($("#product_id").val()==""){
				popupTip('product_id', '请输入商品id');
				return false;
			}
			if($("#price").val()==""){
				popupTip('price', '请输入价格');
				return false;
			}
			if($("#create_time").val()==""){
				popupTip('create_time', '请输入创建');
				return false;
			}
	
		var action = '${msg}';
		var url = 'order/rest/' + action;
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
	<form action="order/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="id" id="id" value="${pd.id}"/>
		<div id="zhongxin" style="padding-top:20px;">
		<table id="table_report" class="table noline">
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>商品id:</td>
				<td><input type="text" name="product_id" id="product_id" value="${pd.product_id}" maxlength="32" placeholder="请输入商品id" title="商品id"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>价格:</td>
				<td><input type="text" name="price" id="price" value="${pd.price}" maxlength="32" placeholder="请输入价格" title="价格"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>创建:</td>
				<td><input type="text" name="create_time" id="create_time" value="${pd.create_time}" maxlength="32" placeholder="请输入创建" title="创建"/></td>
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