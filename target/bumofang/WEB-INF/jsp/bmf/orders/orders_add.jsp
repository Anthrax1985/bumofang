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
		<base href="<%=basePath%>"><!-- jsp文件头和头部 -->
		<%@ include file="../../system/admin/top.jsp"%> 
	
		<link rel="stylesheet" href="static/css/base.css" />
		<link rel="stylesheet" href="static/css/H-ui.css" />
		<link rel="stylesheet" href="static/css/list_skin.css" />

		<script src="static/js/layer/3.0.1/layer.js"></script>
		<script src="static/js/bmf/controller/orders_info.js"></script>

	</head>
<body>
		
<div class="container-fluid container-full" id="main-container">

	  <div class="t_add">
		  <form class="form form-horizontal" id="form-add" method="post" onsubmit="return false" style="">
			  <div class="row cl">
				  <label class="form-label col-xs-2 col-sm-2"><span >用户类型</span></label>
				  <div class="formControls col-xs-8 col-sm-8">
					  <select id="type" class="select" datatype="s0-100" >
						  <option value='2'>标记医生</option>
						  <option value='3'>审核医生</option>
						  <!--<option value='4'>统计医生</option>-->
					  </select>
					  <span id="admin_type" style='display: none'>管理员</span>
				  </div>
			  </div>

			  <div class="row cl">
				  <label class="form-label col-xs-2 col-sm-2"><span style="color: #C00;">* </span>医生姓名</label>
				  <div class="formControls col-xs-8 col-sm-8">
					  <input type="text" class="input-text per_50" placeholder="" id="doctorname" datatype="s1-10" nullmsg="医生姓名不能为空" errormsg="医生姓名不能超过10位">
				  </div>
			  </div>

			  <div class="row cl">
				  <label class="form-label col-xs-2 col-sm-2"><span >简介</span></label>
				  <div class="formControls col-xs-8 col-sm-8">
					  <input type="text" class="input-text per_50" placeholder="" id="disc" datatype="s0-200">
				  </div>
			  </div>

			  <div class="row cl">
				  <label class="form-label col-xs-2 col-sm-2"><span >联系方式</span></label>
				  <div class="formControls col-xs-8 col-sm-8">
					  <input type="text" class="input-text per_50" placeholder="" id="tel" datatype="s0-20">
				  </div>
			  </div>

			  <div class="row cl">
				  <label class="form-label col-xs-2 col-sm-2"><span >地址</span></label>
				  <div class="formControls col-xs-8 col-sm-8">
					  <input type="text" class="input-text per_50" placeholder="" id="address" datatype="s0-100">
				  </div>
			  </div>

			  <div class="row cl">
				  <label class="form-label col-xs-2 col-sm-2"><span style="color: #C00;">* </span>登录帐号</label>
				  <div class="formControls col-xs-8 col-sm-8">
					  <input type="text" class="input-text per_50" placeholder="" id="username" datatype="username" nullmsg="登录帐号不能为空" errormsg="登录帐号是3-16位的英文字母">
					  <span id='username_span' style="display: none"></span>
				  </div>
			  </div>


			  <div class="row cl">
				  <label class="form-label col-xs-2 col-sm-2"><span style="color: #C00;">* </span>密码</label>
				  <div class="formControls col-xs-8 col-sm-8">
					  <img id="eye" style="position: absolute;right: 25px;" src="img/eye.png">
					  <input type="password" class="input-text per_50 " placeholder="" id="password" datatype="pass" nullmsg="密码不能为空" errormsg="密码是6-20位" >
					  <input type="text" class="input-text per_50"  id="text"  style="display: none;">
				  </div>
			  </div>


			  <div class="row cl">
				  <label class="form-label col-xs-2 col-sm-2"><span style="color: #C00;">* </span>密码确认</label>
				  <div class="formControls col-xs-8 col-sm-8">
					  <input type="password" class="input-text per_50 " placeholder="" id="password_confirm" datatype="pass" nullmsg="密码不能为空" errormsg="密码是6-20位">
				  </div>
			  </div>


			  <div class="row cl">
				  <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					  <button id="submit_btn" class="btn btn-success radius">&nbsp;&nbsp;确定&nbsp;&nbsp;</button>
					  <button class="btn btn-default radius ml-40 cancel_add" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
				  </div>
			  </div>

		  </form>




	  </div>





	

</div><!--/.fluid-container#main-container-->
		
		<script type="text/javascript">

		$(document).ready(function(){
			if(top.hangge != undefined){
				top.hangge();
			}
		});
		
		</script>
		

	</body>
</html>

