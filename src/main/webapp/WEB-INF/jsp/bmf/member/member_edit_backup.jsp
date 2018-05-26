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
	var fieldArray = ["NUMBER","USERNAME","PASSWORD","NICKNAME","AVATAR","MOBILE","PROFESSION","DEFAULT_AREA","DEFAULT_ADDRESS","REALNAME","ID_CARD","BANK_CARD","STATUS","WECHAT_ID","QQ_ID","WEIBO_ID",""];
	
	//保存
	function save(target){
/* 			if($("#NUMBER").val()==""){
				popupTip('NUMBER', '请输入编号');
				return false;
			}
			if($("#USERNAME").val()==""){
				popupTip('USERNAME', '请输入用户名');
				return false;
			}
			if($("#PASSWORD").val()==""){
				popupTip('PASSWORD', '请输入密码');
				return false;
			}
			if($("#NICKNAME").val()==""){
				popupTip('NICKNAME', '请输入昵称');
				return false;
			}
			if($("#AVATAR").val()==""){
				popupTip('AVATAR', '请输入头像');
				return false;
			} */
			if($("#MOBILE").val()==""){
				popupTip('MOBILE', '请输入手机号');
				return false;
			}
/* 			if($("#PROFESSION").val()==""){
				popupTip('PROFESSION', '请输入职业');
				return false;
			}
			if($("#DEFAULT_AREA").val()==""){
				popupTip('DEFAULT_AREA', '请输入默认区域');
				return false;
			}
			if($("#DEFAULT_ADDRESS").val()==""){
				popupTip('DEFAULT_ADDRESS', '请输入默认地址');
				return false;
			}
			if($("#REALNAME").val()==""){
				popupTip('REALNAME', '请输入真实姓名');
				return false;
			}
			if($("#ID_CARD").val()==""){
				popupTip('ID_CARD', '请输入身份证号');
				return false;
			}
			if($("#BANK_CARD").val()==""){
				popupTip('BANK_CARD', '请输入银行卡号');
				return false;
			}
			if($("#STATUS").val()==""){
				popupTip('STATUS', '请输入状态');
				return false;
			}
			if($("#WECHAT_ID").val()==""){
				popupTip('WECHAT_ID', '请输入三方登录，微信ID');
				return false;
			}
			if($("#QQ_ID").val()==""){
				popupTip('QQ_ID', '请输入三方登录，QQID');
				return false;
			}
			if($("#WEIBO_ID").val()==""){
				popupTip('WEIBO_ID', '请输入三方登录，新浪微博ID');
				return false;
			} */
	
		var action = '${msg}';
		var url = 'member/rest/' + action;
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
	<form action="member/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="ID" id="ID" value="${pd.ID}"/>
		<div id="zhongxin" style="padding-top:20px;">
		<table id="table_report" class="table noline">
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">编号:</td>
				<td><input type="text" name="NUMBER" id="NUMBER" value="${pd.NUMBER}" maxlength="32" placeholder="请输入编号" title="编号"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">用户名:</td>
				<td><input type="text" name="USERNAME" id="USERNAME" value="${pd.USERNAME}" maxlength="32" placeholder="请输入用户名" title="用户名"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">手机号:</td>
				<td><input type="text" name="MOBILE" id="MOBILE" value="${pd.MOBILE}" maxlength="32" placeholder="请输入手机号" title="手机号"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">昵称:</td>
				<td><input type="text" name="NICKNAME" id="NICKNAME" value="${pd.NICKNAME}" maxlength="32" placeholder="请输入昵称" title="昵称"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">头像:</td>
				<td><input type="text" name="AVATAR" id="AVATAR" value="${pd.AVATAR}" maxlength="32" placeholder="请输入头像" title="头像"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">IDFA设备号:</td>
				<td><input type="text" name="IDFA" id="IDFA" value="${pd.IDFA}" placeholder="请输入IDFA" title="IDFA"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">职业:</td>
				<td><input type="text" name="PROFESSION" id="PROFESSION" value="${pd.PROFESSION}" maxlength="32" placeholder="请输入职业" title="职业"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">默认区域:</td>
				<td><input type="text" name="DEFAULT_AREA" id="DEFAULT_AREA" value="${pd.DEFAULT_AREA}" maxlength="32" placeholder="请输入默认区域" title="默认区域"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">默认地址:</td>
				<td><input type="text" name="DEFAULT_ADDRESS" id="DEFAULT_ADDRESS" value="${pd.DEFAULT_ADDRESS}" maxlength="32" placeholder="请输入默认地址" title="默认地址"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">真实姓名:</td>
				<td><input type="text" name="REALNAME" id="REALNAME" value="${pd.REALNAME}" maxlength="32" placeholder="请输入真实姓名" title="真实姓名"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">身份证号:</td>
				<td><input type="text" name="ID_CARD" id="ID_CARD" value="${pd.ID_CARD}" maxlength="32" placeholder="请输入身份证号" title="身份证号"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">银行卡号:</td>
				<td><input type="text" name="BANK_CARD" id="BANK_CARD" value="${pd.BANK_CARD}" maxlength="32" placeholder="请输入银行卡号" title="银行卡号"/></td>
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