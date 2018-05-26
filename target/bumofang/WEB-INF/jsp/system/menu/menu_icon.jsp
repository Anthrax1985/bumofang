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
				//保存
				function save(){
					if($("#MENU_ICON").val()==""){
						alert('请选择图标');
						return false;
					}
					$("#menuForm").submit();
					$("#zhongxin").hide();
					$("#zhongxin2").show();
				}
			function seticon(icon){
				$("#MENU_ICON").val(icon);
			}	
			
		</script>
		
	</head>
	
	<body>
		<form action="menu/editicon.do" name="menuForm" id="menuForm" method="post">
			<input type="hidden" name="MENU_ID" id="menuId" value="${pd.MENU_ID}"/>
			<input type="hidden" name="MENU_ICON" id="MENU_ICON" value=""/>
			<div id="zhongxin">
			<table id="table_report" class="table table-striped table-bordered table-hover">
				
				<tr>
					<td><label onclick="seticon('fa fa-desktop');"><input name="form-field-radio" type="radio" value="fa fa-edit"><span class="lbl">&nbsp;<i class="fa fa-desktop"></i></span></label></td>
					<td><label onclick="seticon('fa fa-list');"><input name="form-field-radio" type="radio" value="fa fa-edit"><span class="lbl">&nbsp;<i class="fa fa-list"></i></span></label></td>
					<td><label onclick="seticon('fa fa-edit');"><input name="form-field-radio" type="radio" value="fa fa-edit"><span class="lbl">&nbsp;<i class="fa fa-edit"></i></span></label></td>
					<td><label onclick="seticon('fa fa-list-alt');"><input name="form-field-radio" type="radio" value="fa fa-edit"><span class="lbl">&nbsp;<i class="fa fa-list-alt"></i></span></label></td>
					<td><label onclick="seticon('fa fa-calendar');"><input name="form-field-radio" type="radio" value="fa fa-edit"><span class="lbl">&nbsp;<i class="fa fa-calendar"></i></span></label></td>
					<td><label onclick="seticon('fa fa-picture-o');"><input name="form-field-radio" type="radio" value="fa fa-edit"><span class="lbl">&nbsp;<i class="fa fa-picture-o"></i></span></label></td>
					<td><label onclick="seticon('fa fa-th');"><input name="form-field-radio" type="radio" value="fa fa-edit"><span class="lbl">&nbsp;<i class="fa fa-th"></i></span></label></td>
					<td><label onclick="seticon('fa fa-file');"><input name="form-field-radio" type="radio" value="fa fa-edit"><span class="lbl">&nbsp;<i class="fa fa-file"></i></span></label></td>
					<td><label onclick="seticon('fa fa-folder-open');"><input name="form-field-radio" type="radio" value="fa fa-edit"><span class="lbl">&nbsp;<i class="fa fa-folder-open"></i></span></label></td>
				</tr>
				<tr>
					<td><label onclick="seticon('fa fa-book');"><input name="form-field-radio" type="radio" value="fa fa-edit"><span class="lbl">&nbsp;<i class="fa fa-book"></i></span></label></td>
					<td><label onclick="seticon('fa fa-cogs');"><input name="form-field-radio" type="radio" value="fa fa-edit"><span class="lbl">&nbsp;<i class="fa fa-cogs"></i></span></label></td>
					<td><label onclick="seticon('fa fa-comments');"><input name="form-field-radio" type="radio" value="fa fa-edit"><span class="lbl">&nbsp;<i class="fa fa-comments"></i></span></label></td>
					<td><label onclick="seticon('fa fa-envelope-alt');"><input name="form-field-radio" type="radio" value="fa fa-edit"><span class="lbl">&nbsp;<i class="fa fa-envelope-alt"></i></span></label></td>
					<td><label onclick="seticon('fa fa-film');"><input name="form-field-radio" type="radio" value="fa fa-edit"><span class="lbl">&nbsp;<i class="fa fa-film"></i></span></label></td>
					<td><label onclick="seticon('fa fa-heart');"><input name="form-field-radio" type="radio" value="fa fa-edit"><span class="lbl">&nbsp;<i class="fa fa-heart"></i></span></label></td>
					<td><label onclick="seticon('fa fa-lock');"><input name="form-field-radio" type="radio" value="fa fa-edit"><span class="lbl">&nbsp;<i class="fa fa-lock"></i></span></label></td>
					<td><label onclick="seticon('fa fa-exclamation-sign');"><input name="form-field-radio" type="radio" value="fa fa-edit"><span class="lbl">&nbsp;<i class="fa fa-exclamation-sign"></i></span></label></td>
					<td><label onclick="seticon('fa fa-facetime-video');"><input name="form-field-radio" type="radio" value="fa fa-edit"><span class="lbl">&nbsp;<i class="fa fa-facetime-video"></i></span></label></td>
				</tr>
				<tr>
				<td style="text-align: center;" colspan="100">
					<a class="btn btn-primary" onclick="save();">保存</a>
					
				</td>
			</tr>
			</table>
			</div>
			<div id="zhongxin2" class="center" style="display:none"><img src="static/images/jzx.gif" /></div>
		</form>
	</body>
</html>