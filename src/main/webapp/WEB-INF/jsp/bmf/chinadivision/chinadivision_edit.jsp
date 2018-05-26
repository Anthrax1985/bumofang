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
		if($("#divisionName").val()==""){
			popupTip('divisionName', '请输入division_name');
			return false;
		}
		if($("#divisionOrderby").val()==""){
			popupTip('divisionOrderby', '请输入行政区划排序');
			return false;
		}
		if($("#divisionLevel").val()==""){
			popupTip('divisionLevel', '请输入行政区划级别');
			return false;
		}
		if($("#divisionParentid").val()==""){
			popupTip('divisionParentid', '请输入行政区划父节点id');
			return false;
		}
		if($("#zipCode").val()==""){
			popupTip('zipCode', '请输入邮编');
			return false;
		}
		if($("#addressCode").val()==""){
			popupTip('addressCode', '请输入区域编码');
			return false;
		}
		if($("#delFlag").val()==""){
			popupTip('delFlag', '请输入删掉标识');
			return false;
		}
		if($("#updateUserId").val()==""){
			popupTip('updateUserId', '请输入修改者id');
			return false;
		}
		if($("#updateTime").val()==""){
			popupTip('updateTime', '请输入修改时间');
			return false;
		}
	
		var action = '${msg}';
		var url = 'chinadivision/' + action;
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
	<form action="chinadivision/${msg}.do" name="editForm" id="editForm" method="post">
		<input type="hidden" name="id" id="id" value="${entity.id}"/>
		<div id="zhongxin" style="padding-top:20px;">
		<table id="table_report" class="table noline">
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>division_name:</td>
				<td><input type="text" name="divisionName" id="divisionName" value="${entity.divisionName}" maxlength="32" placeholder="请输入division_name"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>行政区划排序:</td>
				<td><input type="number" name="divisionOrderby" id="divisionOrderby" value="${entity.divisionOrderby}" maxlength="32" placeholder="请输入行政区划排序"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>行政区划级别:</td>
				<td><input type="number" name="divisionLevel" id="divisionLevel" value="${entity.divisionLevel}" maxlength="32" placeholder="请输入行政区划级别"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>行政区划父节点id:</td>
				<td><input type="number" name="divisionParentid" id="divisionParentid" value="${entity.divisionParentid}" maxlength="32" placeholder="请输入行政区划父节点id"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>邮编:</td>
				<td><input type="text" name="zipCode" id="zipCode" value="${entity.zipCode}" maxlength="32" placeholder="请输入邮编"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>区域编码:</td>
				<td><input type="text" name="addressCode" id="addressCode" value="${entity.addressCode}" maxlength="32" placeholder="请输入区域编码"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>删掉标识:</td>
				<td><input type="number" name="delFlag" id="delFlag" value="${entity.delFlag}" maxlength="32" placeholder="请输入删掉标识"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>修改者id:</td>
				<td><input type="number" name="updateUserId" id="updateUserId" value="${entity.updateUserId}" maxlength="32" placeholder="请输入修改者id"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>修改时间:</td>
				<td><input class="span10 date-picker" name="updateTime" id="updateTime" value="${entity.updateTime}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="修改时间"/></td>
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