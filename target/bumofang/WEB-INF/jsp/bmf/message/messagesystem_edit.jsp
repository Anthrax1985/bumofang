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
		if($("#releaseTitle").val()==""){
			popupTip('releaseTitle', '请输入消息标题');
			return false;
		}
		if($("#releaseInfo").val()==""){
			popupTip('releaseInfo', '请输入消息内容');
			return false;
		}
	
		var action = '${msg}';
		var url = 'messagesystem/' + action;
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
<style>
	.contentTitle{text-align:center;font-size:18px;}
	#releaseTitle{width:450px;display:block; margin:10px auto;}
	#releaseInfo{width:450px;height:200px;display:block; margin:10px auto;resize:none;}
	#saveBtn{width:100px;display:block; margin:10px 0 10px 300px;padding:8px 20px;}
	.releaser{margin-left:20px; font-size:16px;}
</style>
	</head>
<body>
	<form action="messagesystem/${msg}.do" name="editForm" id="editForm" method="post">
		<input type="hidden" name="id" id="id" value="${entity.id}"/>
		<div id="zhongxin" style="padding-top:20px;">
			<div>
				<p class="contentTitle">消息标题</p>
				<input type="text" name="releaseTitle" id="releaseTitle" value="${entity.releaseTitle}"/>
			</div>
			<div>
				<p class="contentTitle">消息内容</p>
				<textarea name="releaseInfo" id="releaseInfo">${entity.releaseInfo}</textarea>
			</div>
			<div>
					<p class="releaser">发布人员&nbsp; &nbsp;${userInfo.NAME}/${userInfo.ROLE_NAME}</p>
					<a id="saveBtn" class="btn btn-primary" style="width:100px" onclick="save(this);">提交审批</a>
			</div>
		</div>		
	</form>
</body>
</html>