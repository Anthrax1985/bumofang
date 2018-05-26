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
	var fieldArray = ["fabric_id","amount","unit_price","total_price","buy_id","status","create_time","update_time",""];
	
	//保存
	function save(target){
			if($("#fabric_id").val()==""){
				popupTip('fabric_id', '请输入面料id');
				return false;
			}
			if($("#amount").val()==""){
				popupTip('amount', '请输入数量');
				return false;
			}
			if($("#unit_price").val()==""){
				popupTip('unit_price', '请输入单价');
				return false;
			}
			if($("#total_price").val()==""){
				popupTip('total_price', '请输入总价');
				return false;
			}
			if($("#buy_id").val()==""){
				popupTip('buy_id', '请输入收件人详细地址');
				return false;
			}
			if($("#status").val()==""){
				popupTip('status', '请输入状态');
				return false;
			}
			if($("#create_time").val()==""){
				popupTip('create_time', '请输入创建时间');
				return false;
			}
			if($("#update_time").val()==""){
				popupTip('update_time', '请输入更新时间');
				return false;
			}
	
		var action = '${msg}';
		var url = 'shopcart/rest/' + action;
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
	<form action="shopcart/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="id" id="id" value="${pd.id}"/>
		<div id="zhongxin" style="padding-top:20px;">
		<table id="table_report" class="table noline">
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>面料id:</td>
				<td><input type="text" name="fabric_id" id="fabric_id" value="${pd.fabric_id}" maxlength="32" placeholder="请输入面料id" title="面料id"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>数量:</td>
				<td><input type="text" name="amount" id="amount" value="${pd.amount}" maxlength="32" placeholder="请输入数量" title="数量"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>单价:</td>
				<td><input type="text" name="unit_price" id="unit_price" value="${pd.unit_price}" maxlength="32" placeholder="请输入单价" title="单价"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>总价:</td>
				<td><input type="text" name="total_price" id="total_price" value="${pd.total_price}" maxlength="32" placeholder="请输入总价" title="总价"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>收件人详细地址:</td>
				<td><input type="text" name="buy_id" id="buy_id" value="${pd.buy_id}" maxlength="32" placeholder="请输入收件人详细地址" title="收件人详细地址"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>状态:</td>
				<td><input type="text" name="status" id="status" value="${pd.status}" maxlength="32" placeholder="请输入状态" title="状态"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>创建时间:</td>
				<td><input type="text" name="create_time" id="create_time" value="${pd.create_time}" maxlength="32" placeholder="请输入创建时间" title="创建时间"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>更新时间:</td>
				<td><input type="text" name="update_time" id="update_time" value="${pd.update_time}" maxlength="32" placeholder="请输入更新时间" title="更新时间"/></td>
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