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
	$(top.hangge());

	
/* 	//保存
	function save(target){			
		
			var action = '${msg }';
			var url = 'user/rest/' + action;
			ajaxSave(target, url, action);

		return false;
	}
	 */
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
	<form action="user/rest/uploadAvatar.do" name="userForm" id="userForm" method="post" enctype="multipart/form-data" >
		<input type="hidden" name="USER_ID" id="user_id" value="${pd.USER_ID }"/>
		<div id="zhongxin" style="padding-top:20px;">
		<table class="table noline">		
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">上传头像</td>
				<td>
					<a href="javascript:void(0)" class="newspic">
						<c:if test="${pd.AVATAR != null && pd.AVATAR != ''}"><img style ="width:180px;height:180px;" src="<%=basePath%>${pd.AVATAR}"/></c:if>
						<c:if test="${pd.AVATAR == null || pd.AVATAR == ''}"><img style ="width:180px;height:180px;" src="<%=basePath%>static/img/selectimg.png"/></c:if>
					</a>
					<input type="file" id="AVATAR" name="AVATAR" onchange="showPic(this)"  style="display: none">
				</td>
			</tr>		
		</table>
		<span style="text-align: center; width:420px;margin-left:120px;">
			<button type="submit" id="saveBtn" class="btn btn-primary" style="width:100px;" value="保存">保存</button>
		</span>
		</div>
	</form>	
</body>
</html>