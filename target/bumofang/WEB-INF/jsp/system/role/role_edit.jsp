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
	
	top.hangge();
	
	//保存
	function save(target){
		if($("#roleName").val()==""){
			$("#roleName").focus();
			return false;
		}
		
		var url = "role/rest/edit";
		ajaxSave(target, url, "edit");
		
		return false;	
	}
	
</script>
	</head>
<body>
		<form action="role/edit.do" name="form1" id="form1"  method="post">
		<input type="hidden" name="ROLE_ID" id="id" value="${pd.ROLE_ID}"/>
			<div id="zhongxin" style="width:200px">
			<table class="table noline">
				<tr>
					<td><input type="text" name="ROLE_NAME" id="roleName" value="${pd.ROLE_NAME}" placeholder="请输入名称" title="名称" /></td>
				</tr>
				<tr>
					<td style="text-align: center;">
						<a class="btn btn-primary" onclick="save(this);">保存</a>
						
					</td>
				</tr>
			</table>
			</div>
		</form>
	
	<div id="zhongxin2" class="center" style="display:none"><img src="static/images/jzx.gif"  style="width: 50px;" /><br/><h4 class="lighter block green"></h4></div>
</body>
</html>
