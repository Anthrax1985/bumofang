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
/* 	function save(target){
		if($("#colorName").val()==""){
			popupTip('colorName', '请输入颜色名称');
			return false;
		}
		if($("#colorIcon").val()==""){
			popupTip('colorIcon', '请输入颜色图标');
			return false;
		}
		if($("#colorOrder").val()==""){
			popupTip('colorOrder', '请输入颜色排序');
			return false;
		}
	
		var action = '${msg}';
		var url = 'productparamcolor/' + action;
		ajaxSave(target, url, action);
		return false;
	} */
	
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
	<form action="productparamcolor/rest/saveColor.do" name="editForm" id="editForm" method="post" enctype="multipart/form-data" >
		<input type="hidden" name="id" id="id" value="${entity.id}"/>
		<div id="zhongxin" style="padding-top:20px;">
		<table id="table_report" class="table noline">
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>颜色名称:</td>
				<td><input type="text" name="colorName" id="colorName" value="${entity.colorName}" maxlength="32" placeholder="请输入颜色名称"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">颜色图标:</td>
				<td>
					<a href="javascript:void(0)" class="newspic">
						<c:if test="${entity.colorIcon != null && entity.colorIcon != ''}"><img style="width:200px;height:100px;" src="<%=basePath%>${entity.colorIcon}"/></c:if>
						<c:if test="${entity.colorIcon == null || entity.colorIcon == ''}"><img style="width:200px;height:100px;" src="<%=basePath%>static/img/selectimg.png"/></c:if>
					</a>
					<input type="file" id="colorIcon" name="colorIcon" onchange="showPic(this)"  style="display: none">
				</td>
			</tr>	
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>颜色排序:</td>
				<td><input type="number" name="colorOrder" id="colorOrder" value="${entity.colorOrder}" maxlength="32" placeholder="请输入颜色排序"/></td>
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