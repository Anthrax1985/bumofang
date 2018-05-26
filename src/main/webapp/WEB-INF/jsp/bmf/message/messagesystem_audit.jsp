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
	
	//通过
	function pass(target){	
		$("#status").val(1);
		var action = '${msg}';
		var url = 'messagesystem/' + action;
		ajaxSave(target, url, action);
		return false;
	}
	//拒绝
	function reject(target){	
		$("#status").val(2);
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
	</head>
<body>
	<form action="messagesystem/${msg}.do" name="editForm" id="editForm" method="post">
		<input type="hidden" name="id" id="id" value="${entity.id}"/>
		<div id="zhongxin" style="padding-top:20px;">
			<input type="hidden" id="status" name="status"/>
			<table id="table_report" class="table noline">
				<tr>
					<td style="width:80px;text-align: right;">消息标题</td>
					<td><span id="releaseTitle">${entity.releaseTitle}</span></td>
				</tr>
				<tr>
					<td style="width:80px;text-align: right; ">消息内容</td>
					<td><textarea  style="width:300px;height:100px; resize:none;"name="releaseInfo" id="releaseInfo" readonly="readonly">${entity.releaseInfo}</textarea></td>
				</tr>
				<tr>
					<td style="width:80px;text-align: right; ">提交时间</td>
					<td><span class="releaser">${entity.releaseTime}</span></td>
				</tr>
				<tr>
					<td style="width:80px;text-align: right; ">提交人</td>
					<td><span class="releaser">${entity.releaserInfo}</span></td>
				</tr>
			</table>
			<div>
				<a  id="pass" class="btn btn-primary saveBtn" style="width:100px; margin-left:100px;" onclick="pass(this);">批准</a>
				<a  id="reject" class="btn btn-primary saveBtn" style="width:100px;margin-left:20px;" onclick="reject(this);">退回</a>
			</div>
		</div>		
	</form>
</body>
</html>