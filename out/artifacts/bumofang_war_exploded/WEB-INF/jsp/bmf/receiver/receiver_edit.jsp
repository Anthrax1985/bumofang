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
	var fieldArray = ["name","mobile","addr_province","addr_city","addr_county","addr_detail","post_code","create_user","create_time","update_time",""];
	
	//保存
	function save(target){
			if($("#name").val()==""){
				popupTip('name', '请输入姓名');
				return false;
			}
			if($("#mobile").val()==""){
				popupTip('mobile', '请输入手机号');
				return false;
			}
			if($("#addr_province").val()==""){
				popupTip('addr_province', '请输入收件人省份');
				return false;
			}
			if($("#addr_city").val()==""){
				popupTip('addr_city', '请输入收件人城市');
				return false;
			}
			if($("#addr_county").val()==""){
				popupTip('addr_county', '请输入收件人区、县');
				return false;
			}
			if($("#addr_detail").val()==""){
				popupTip('addr_detail', '请输入收件人详细地址');
				return false;
			}
			if($("#post_code").val()==""){
				popupTip('post_code', '请输入邮编');
				return false;
			}
			if($("#create_user").val()==""){
				popupTip('create_user', '请输入创建用户id');
				return false;
			}
			if($("#create_time").val()==""){
				popupTip('create_time', '请输入创建时间');
				return false;
			}
			if($("#update_time").val()==""){
				popupTip('update_time', '请输入修改时间');
				return false;
			}
	
		var action = '${msg}';
		var url = 'receiver/rest/' + action;
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
	<form action="receiver/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="id" id="id" value="${pd.id}"/>
		<div id="zhongxin" style="padding-top:20px;">
		<table id="table_report" class="table noline">
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>姓名:</td>
				<td><input type="text" name="name" id="name" value="${pd.name}" maxlength="32" placeholder="请输入姓名" title="姓名"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>手机号:</td>
				<td><input type="text" name="mobile" id="mobile" value="${pd.mobile}" maxlength="32" placeholder="请输入手机号" title="手机号"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>收件人省份:</td>
				<td><input type="text" name="addr_province" id="addr_province" value="${pd.addr_province}" maxlength="32" placeholder="请输入收件人省份" title="收件人省份"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>收件人城市:</td>
				<td><input type="text" name="addr_city" id="addr_city" value="${pd.addr_city}" maxlength="32" placeholder="请输入收件人城市" title="收件人城市"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>收件人区、县:</td>
				<td><input type="text" name="addr_county" id="addr_county" value="${pd.addr_county}" maxlength="32" placeholder="请输入收件人区、县" title="收件人区、县"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>收件人详细地址:</td>
				<td><input type="text" name="addr_detail" id="addr_detail" value="${pd.addr_detail}" maxlength="32" placeholder="请输入收件人详细地址" title="收件人详细地址"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>邮编:</td>
				<td><input type="text" name="post_code" id="post_code" value="${pd.post_code}" maxlength="32" placeholder="请输入邮编" title="邮编"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>创建用户id:</td>
				<td><input type="text" name="create_user" id="create_user" value="${pd.create_user}" maxlength="32" placeholder="请输入创建用户id" title="创建用户id"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>创建时间:</td>
				<td><input type="text" name="create_time" id="create_time" value="${pd.create_time}" maxlength="32" placeholder="请输入创建时间" title="创建时间"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>修改时间:</td>
				<td><input type="text" name="update_time" id="update_time" value="${pd.update_time}" maxlength="32" placeholder="请输入修改时间" title="修改时间"/></td>
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