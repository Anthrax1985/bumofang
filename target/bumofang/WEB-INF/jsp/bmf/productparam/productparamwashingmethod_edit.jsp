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
/* 		if($("#washingMethodName").val()==""){
			popupTip('washingMethodName', '请输入水洗标志名称');
			return false;
		}
		if($("#washingMethodIcon").val()==""){
			popupTip('washingMethodIcon', '请输入水洗标志图标');
			return false;
		}
		if($("#washingMethodOrder").val()==""){
			popupTip('washingMethodOrder', '请输入水洗标志排序');
			return false;
		} */
	
		var action = '${msg}';
		var url = 'productparamwashingmethod/' + action;
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
	$(".newspic").click(function() {
		$(this).siblings('input').click();
	});
	
	function showPic(data) {
		if (data.files && data.files[0]) {
			var reader = new FileReader();
			reader.onload = function(evt) {
				data.parentNode.childNodes[1].childNodes[1].src = evt.target.result;
			}
			reader.readAsDataURL(data.files[0]);
		}
	}		
</script>
	</head>
<body>
<%-- 	<form action="productparamwashingmethod/${msg}.do" name="editForm" id="editForm" method="post"> --%>
	<form action="productparamwashingmethod/rest/saveWashingMethed.do" name="editForm" id="editForm" method="post" enctype="multipart/form-data" >
		<input type="hidden" name="id" id="id" value="${entity.id}"/>
		<div id="zhongxin" style="padding-top:20px;">
		<table id="table_report" class="table noline">
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>水洗标志名称:</td>
				<td><input type="text" name="washingMethodName" id="washingMethodName" value="${entity.washingMethodName}" maxlength="32" placeholder="请输入水洗标志名称"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>水洗标志图标:</td>
				<td>
					<a href="javascript:void(0)" class="newspic">
						<c:if test="${entity.washingMethodIcon != null && entity.washingMethodIcon != ''}"><img style="width:200px;height:100px;" src="<%=basePath%>${entity.washingMethodIcon}"/></c:if>
						<c:if test="${entity.washingMethodIcon == null || entity.washingMethodIcon == ''}"><img style="width:200px;height:100px;" src="<%=basePath%>static/img/selectimg.png"/></c:if>
					</a>
					<input type="file" id="washingMethodIcon" name="washingMethodIcon" onchange="showPic(this)"  style="display: none">
				</td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>水洗标志排序:</td>
				<td><input type="number" name="washingMethodOrder" id="washingMethodOrder" value="${entity.washingMethodOrder}" maxlength="32" placeholder="请输入水洗标志排序"/></td>
			</tr>
			<tr>
				<td id="successMessage" style="text-align: center;display:none;color:#045e9f" colspan="10">
					保存成功
				</td>
			</tr>
			<tr>
				<td style="text-align: center;" colspan="10">
					<!-- <a id="saveBtn" class="btn btn-primary" style="width:100px" onclick="save(this);">保存</a> -->
					<button type="submit" id="saveBtn" class="btn btn-primary" style="width:100px;" value="保存">保存</button>
				</td>
			</tr>
		</table>
		</div>
		
	</form>
</body>
</html>