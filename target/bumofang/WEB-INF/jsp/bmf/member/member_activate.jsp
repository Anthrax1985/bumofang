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
	</head>
<body>
	<form action="memberblack/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="ID" id="ID" value="${pd.ID}"/>
		<div id="zhongxin" style="padding-top:20px;">
		<table id="table_report" class="table noline">
			<tr>
				<td style="width:80px;text-align: right; ">ID</td>
				<td>${pd.ID}</td>
			</tr>			
			<tr>
				<td style="width:80px;text-align: right; ">名称</td>
				<td>${pd.NICKNAME}</td>
			</tr>			
			<tr>
				<td style="width:80px;text-align: right; ">类型</td>
				<td>${pd.PROFESSION_SUP}/${pd.PROFESSION_SUB}</td>
			</tr>			
			<tr>
				<td style="width:80px;text-align: right; ">手机号</td>
				<td>${pd.MOBILE}</td>
			</tr>			
			<tr>
				<td style="width:80px;text-align: right; ">所在区域</td>
				<td>${pd.ADDR_PROVINCE}/${pd.ADDR_CITY}</td>
			</tr>	
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">激活原因</td>
				<td><input type="text" name="REASON" id="REASON" value="" /></td>
			</tr>			
			<tr>
				<td style="text-align: center;" colspan="10">
					<a id="saveBtn" class="btn btn-primary" style="width:100px" onclick="save(this);">激活</a>
				</td>
			</tr>
		</table>
		</div>		
	</form>
<script type="text/javascript">
	//保存
	function save(target){	
		var action = '${msg}';
		var url = 'memberblack/rest/' + action;
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
</body>
</html>