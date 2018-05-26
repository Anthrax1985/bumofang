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
		if($("#memberId").val()==""){
			popupTip('memberId', '请输入用户id');
			return false;
		}
		if($("#memberName").val()==""){
			popupTip('memberName', '请输入用户名称');
			return false;
		}
		if($("#memberAvatar").val()==""){
			popupTip('memberAvatar', '请输入用户头像');
			return false;
		}
		if($("#contentInfo").val()==""){
			popupTip('contentInfo', '请输入用户留言');
			return false;
		}
		if($("#contentTime").val()==""){
			popupTip('contentTime', '请输入留言时间');
			return false;
		}
		if($("#replyInfo").val()==""){
			popupTip('replyInfo', '请输入回复内容');
			return false;
		}
		if($("#replyTime").val()==""){
			popupTip('replyTime', '请输入回复内容');
			return false;
		}
		if($("#replierId").val()==""){
			popupTip('replierId', '请输入回复人id');
			return false;
		}
		if($("#replierName").val()==""){
			popupTip('replierName', '请输入回复人名称');
			return false;
		}
		if($("#replierAvatar").val()==""){
			popupTip('replierAvatar', '请输入回复人头像');
			return false;
		}
		if($("#status").val()==""){
			popupTip('status', '请输入留言状态(0待回复1已回复)');
			return false;
		}
		if($("#delFlag").val()==""){
			popupTip('delFlag', '请输入删除标记(0未删除1已删除)');
			return false;
		}
	
		var action = '${msg}';
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
	</head>
<body>
	<form action="messagemember/${msg}.do" name="editForm" id="editForm" method="post">
		<input type="hidden" name="id" id="id" value="${entity.id}"/>
		<div id="zhongxin" style="padding-top:20px;">
		<table id="table_report" class="table noline">
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>用户id:</td>
				<td><input type="number" name="memberId" id="memberId" value="${entity.memberId}" maxlength="32" placeholder="请输入用户id"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>用户名称:</td>
				<td><input type="text" name="memberName" id="memberName" value="${entity.memberName}" maxlength="32" placeholder="请输入用户名称"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>用户头像:</td>
				<td><input type="text" name="memberAvatar" id="memberAvatar" value="${entity.memberAvatar}" maxlength="32" placeholder="请输入用户头像"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>用户留言:</td>
				<td><input type="text" name="contentInfo" id="contentInfo" value="${entity.contentInfo}" maxlength="32" placeholder="请输入用户留言"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>留言时间:</td>
				<td><input type="text" name="contentTime" id="contentTime" value="${entity.contentTime}" maxlength="32" placeholder="请输入留言时间"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>回复内容:</td>
				<td><input type="text" name="replyInfo" id="replyInfo" value="${entity.replyInfo}" maxlength="32" placeholder="请输入回复内容"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>回复内容:</td>
				<td><input type="text" name="replyTime" id="replyTime" value="${entity.replyTime}" maxlength="32" placeholder="请输入回复内容"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>回复人id:</td>
				<td><input type="number" name="replierId" id="replierId" value="${entity.replierId}" maxlength="32" placeholder="请输入回复人id"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>回复人名称:</td>
				<td><input type="text" name="replierName" id="replierName" value="${entity.replierName}" maxlength="32" placeholder="请输入回复人名称"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>回复人头像:</td>
				<td><input type="text" name="replierAvatar" id="replierAvatar" value="${entity.replierAvatar}" maxlength="32" placeholder="请输入回复人头像"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>留言状态(0待回复1已回复):</td>
				<td><input type="number" name="status" id="status" value="${entity.status}" maxlength="32" placeholder="请输入留言状态(0待回复1已回复)"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>删除标记(0未删除1已删除):</td>
				<td><input type="number" name="delFlag" id="delFlag" value="${entity.delFlag}" maxlength="32" placeholder="请输入删除标记(0未删除1已删除)"/></td>
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