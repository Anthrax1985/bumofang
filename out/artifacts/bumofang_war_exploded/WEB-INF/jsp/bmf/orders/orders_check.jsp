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
var fieldArray = ["num","buy_id","total_price","real_pay","discount","coupon_id","receiver_id","express_id","express_name","status","create_time","pay_time","pay_way","pay_account","remark",""];
	
	//保存
	function save(target){
			if($("#num").val()==""){
				popupTip('num', '请输入订单编号');
				return false;
			}
			if($("#buy_id").val()==""){
				popupTip('buy_id', '请输入购买者id');
				return false;
			}
			if($("#total_price").val()==""){
				popupTip('total_price', '请输入总价');
				return false;
			}
			if($("#real_pay").val()==""){
				popupTip('real_pay', '请输入实际支付');
				return false;
			}
			if($("#discount").val()==""){
				popupTip('discount', '请输入折扣');
				return false;
			}
			if($("#coupon_id").val()==""){
				popupTip('coupon_id', '请输入优惠券id');
				return false;
			}
			if($("#receiver_id").val()==""){
				popupTip('receiver_id', '请输入接收人id');
				return false;
			}
			if($("#express_id").val()==""){
				popupTip('express_id', '请输入快递id');
				return false;
			}
			if($("#express_name").val()==""){
				popupTip('express_name', '请输入快递名称');
				return false;
			}
			if($("#status").val()==""){
				popupTip('status', '请输入订单状态');
				return false;
			}
			if($("#create_time").val()==""){
				popupTip('create_time', '请输入创建时间');
				return false;
			}
			if($("#pay_time").val()==""){
				popupTip('pay_time', '请输入支付时间');
				return false;
			}
			if($("#pay_way").val()==""){
				popupTip('pay_way', '请输入支付方式');
				return false;
			}
			if($("#pay_account").val()==""){
				popupTip('pay_account', '请输入支付账户');
				return false;
			}
			if($("#remark").val()==""){
				popupTip('remark', '请输入备注');
				return false;
			}
	
		var action = '${msg}';
		var url = 'orders/rest/' + action;
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
	<form action="orders/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="id" id="id" value="${pd.id}"/>
		<div id="zhongxin" style="padding-top:20px;">
		<table id="table_report" class="table noline">
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>订单编号:</td>
				<td><input type="text" name="num" id="num" value="${pd.num}" maxlength="32" placeholder="请输入订单编号" title="订单编号" disabled="disabled"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>用户昵称:</td>
				<td><input type="text" name="nickname" id="nickname" value="${pd.nickname}" maxlength="32" placeholder="请输入用户昵称" title="用户昵称" disabled="disabled"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>订单金额:</td>
				<td><input type="text" name="total_price" id="total_price" value="${pd.total_price}" maxlength="32" placeholder="请输入订单金额" title="订单金额" disabled="disabled"/></td>
			</tr>
			
			
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>实际支付:</td>
				<td><input type="text" name="real_pay" id="real_pay" value="${pd.real_pay}" maxlength="32" placeholder="请输入实际支付" title="实际支付" disabled="disabled"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>折扣:</td>
				<td><input type="text" name="discount" id="discount" value="${pd.discount}" maxlength="32" placeholder="请输入折扣" title="折扣" disabled="disabled"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>优惠券id:</td>
				<td><input type="text" name="coupon_id" id="coupon_id" value="${pd.coupon_id}" maxlength="32" placeholder="请输入优惠券id" title="优惠券id" disabled="disabled"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>接收人id:</td>
				<td><input type="text" name="receiver_id" id="receiver_id" value="${pd.receiver_id}" maxlength="32" placeholder="请输入接收人id" title="接收人id" disabled="disabled"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>快递id:</td>
				<td><input type="text" name="express_id" id="express_id" value="${pd.express_id}" maxlength="32" placeholder="请输入快递id" title="快递id" disabled="disabled"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>快递名称:</td>
				<td><input type="text" name="express_name" id="express_name" value="${pd.express_name}" maxlength="32" placeholder="请输入快递名称" title="快递名称" disabled="disabled"/></td>
			</tr>
			
			
			<tr>
				<td style="width:80px;text-align: right;padd
				+ing-top: 13px;"><em>*</em>订单状态:</td>
				<td><input type="text" name="status" id="status" value="${pd.status}" maxlength="32" placeholder="请输入订单状态" title="订单状态" disabled="disabled"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>日期:</td>
				<td><input type="text" name="create_time" id="create_time" value="${pd.create_time}" maxlength="32" placeholder="" title="日期" disabled="disabled"/></td>
			</tr>
			
			
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>支付时间:</td>
				<td><input type="text" name="pay_time" id="pay_time" value="${pd.pay_time}" maxlength="32" placeholder="" title="支付时间" disabled="disabled"/></td>
			</tr>	
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>支付方式:</td>
				<td><input type="text" name="pay_way" id="pay_way" value="${pd.pay_way}" maxlength="32" placeholder="" title="支付方式" disabled="disabled"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>支付账户:</td>
				<td><input type="text" name="pay_account" id="pay_account" value="${pd.pay_account}" maxlength="32" placeholder="" title="支付账户" disabled="disabled"/></td>
			</tr>								
			
										
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;" ><em>*</em>用户号码:</td>
				<td><input type="text" name="mobile" id="mobile" value="${pd.mobile}" maxlength="32" placeholder="请输入用户号码" disabled="disabled" title="用户号码"/></td>
			</tr>
		</table>
		</div>
		
	</form>
</body>
</html>