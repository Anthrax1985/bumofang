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
		if($("#replyInfo").val()==""){
			popupTip('replyInfo', '请输入回复内容');
			return false;
		}
		var action = 'editReply';
		var url = 'messagemember/' + action;
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
<style type="text/css">
	#replyInfo{width:440px;height:220px; display:block;margin:10px auto;resize:none;}
	#saveBtn{text-align:center;width:200px; padding:12px 30px;display:block;margin:0 auto;}
</style>
	</head>
<body>
	<form action="messagemember/${msg}.do" name="editForm" id="editForm" method="post">
		<input type="hidden" name="id" id="id" value="${entity.id}"/>
		<div id="zhongxin" style="padding-top:20px;">
			<textarea name="replyInfo" id="replyInfo"/></textarea>
			<a id="saveBtn" class="btn btn-primary"  onclick="save(this);">确定</a>
		</div>		
	</form>
</body>
</html>