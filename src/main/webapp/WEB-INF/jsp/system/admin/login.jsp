<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html>
<html class="height-full">

<head>
<title>${pd.SYSNAME}</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="stylesheet" href="static/css/bootstrap.min.css">
<!-- <link rel="stylesheet" href="static/css/bmf/style.css"> -->
<script type="text/javascript" src="static/js/jquery.min.js"></script>
<style type="text/css">
body { background: #ffffff; }
.text-center { text-align: center; }
.mb40 { margin-bottom: 40px; }
.p-title { height:60px; line-height: 60px; padding-left:40px; font-size:20px; font-weight:bold;color:#ffffff;}
</style>

</head>
<body class="height-full" >
    <div style="height:60px; background:#666;">
    	<p class="p-title">后台管理系统</p>
    </div>
    <div class="form-bg">
    <div class="container">
        <div class="row" style="padding-top:10%">
            <div class="col-md-offset-3 col-md-6 col-sm-8 col-sm-offset-2">
                <form class="form-horizontal" method="POST" >
                	<div class="form-group mb40">
                		<div class="col-sm-10 col-sm-offset-2 text-center"><img style="width:260px; "alt="" src="<%=basePath%>static/img/login_logo.png"></div>
                    </div>
                    <div class="form-group">
					    <label for="loginname" class="col-sm-2 control-label">账户</label>
					    <div class="col-sm-10">
					        <input type="text" id="loginname" name="loginname" class="form-control" placeholder="请输入邮箱">
					    </div>
					</div>
					<div class="form-group mb40">
						<label for="password" class="col-sm-2 control-label">密码</label>
						<div class="col-sm-10">
                        	<input type="password" id="password" name="password" class="form-control" placeholder="请输入密码">
                        </div>
                    </div>
                    <div class="form-group">
						<div class="col-sm-10 col-sm-offset-2">
                        	<button type="submit" class="btn btn-default btn-primary btn-block"  onclick="return severCheck(this);">登录</button>
                        </div>
                    </div>
                    <!-- <div class="form-group">
                        <input type="text" id="loginname" name="loginname" class="form-control account" placeholder="用户名">
                        <i class="iconfont">&#xe633;</i>
                    </div> 
                    <div class="form-group help">
                        <input type="password" id="password" name="password" class="form-control password" placeholder="密　码">
                        <i class="iconfont">&#xe620;</i>
                    </div>-->
                    
<!--                     <div class="form-group help" style="float: left;width:60%">
                        <input type="text" id="code" name="code" class="form-control account" placeholder="验证码">
                        <i class="iconfont">&#xe656;</i>
                    </div>
                    
                    <div style="float: left;width:70px;">
						<img style="height:25px;width:70px;margin-top:7px;margin-left:-20px;" id="codeImg" alt="点击更换"
								title="点击更换" src="" />
						</div> -->
						<div style="clear:both;"></div>
                    <div class="form-group">
<!--                         <div class="main-checkbox"> -->
<!--                             <input type="checkbox" name="remember" value="None" id="checkbox1"/> -->
<!--                             <label for="checkbox1"></label> -->
<!--                         </div> -->
<!--                         <span class="text">记住我</span> -->
                        <!-- <button type="submit" class="btn btn-default web-font submit"  onclick="return severCheck(this);">登录</button> -->
<!--                         <button type="submit" class="btn btn-default web-font submit" onclick="return severCheck(this);">登录</button> -->
                        
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
    
    

	<script type="text/javascript">
	
		//服务器校验
		function severCheck(target){
			if(check()){

				$(target).text("登录中...");
				$(target).attr("disabled", "disabled");
				
				var loginname = $("#loginname").val();
				var password = $("#password").val();
			//	var code = $("#code").val();
				var formData = {loginname:loginname,password:password};
/* 				var formData = {loginname:loginname,password:password, code:code}; */
				$.ajax({
					type: "POST",
					url: 'login_login',
			    	data: JSON.stringify(formData),
					dataType:'json',
					contentType: "application/json;charset=UTF-8",
					cache: false,
					success: function(data){
							

						if("success" == data.result){
							window.location.href="main/index";
							return;
						}else{
							$(target).text("登录");
							$(target).removeAttr("disabled");	
						}


						if("usererror" == data.result){
							$("#loginname").tips({
								side : 1,
								msg : "用户名或密码有误",
								bg : '#FF5080',
								time : 15
							});
							$("#loginname").focus();
						}else if("codeerror" == data.result){
							$("#code").tips({
								side : 1,
								msg : "验证码输入有误",
								bg : '#FF5080',
								time : 15
							});
							$("#code").focus();
						}else{
							$("#loginname").tips({
								side : 1,
								msg : "缺少参数",
								bg : '#FF5080',
								time : 15
							});
							$("#loginname").focus();
						}
					},
					error:function(data){
						alert(data);
						
					}
				});
			}

			return false;
		}
	
		$(document).ready(function() {
			changeCode();
			$("#codeImg").bind("click", changeCode);
		});

		$(document).keyup(function(event) {
			if (event.keyCode == 13) {
				$("#to-recover").trigger("click");
			}
		});

		function genTimestamp() {
			var time = new Date();
			return time.getTime();
		}

		function changeCode() {
			$("#codeImg").attr("src", "code.do?t=" + genTimestamp());
		}

		//客户端校验
		function check() {

			if ($("#loginname").val() == "") {

				$("#loginname").tips({
					side : 2,
					msg : '用户名不得为空',
					bg : '#AE81FF',
					time : 3
				});

				$("#loginname").focus();
				return false;
			} else {
				$("#loginname").val(jQuery.trim($('#loginname').val()));
			}

			if ($("#password").val() == "") {

				$("#password").tips({
					side : 2,
					msg : '密码不得为空',
					bg : '#AE81FF',
					time : 3
				});

				$("#password").focus();
				return false;
			}
			if ($("#code").val() == "") {

				$("#code").tips({
					side : 1,
					msg : '验证码不得为空',
					bg : '#AE81FF',
					time : 3
				});

				$("#code").focus();
				return false;
			}

			$("#loginbox").tips({
				side : 1,
				msg : '正在登录 , 请稍后 ...',
				bg : '#68B500',
				time : 10
			});

			return true;
		}

		function savePaw() {
			if (!$("#saveid").attr("checked")) {
				$.cookie('loginname', '', {
					expires : -1
				});
				$.cookie('password', '', {
					expires : -1
				});
				$("#loginname").val('');
				$("#password").val('');
			}
		}

		function saveCookie() {
			if ($("#saveid").attr("checked")) {
				$.cookie('loginname', $("#loginname").val(), {
					expires : 7
				});
				$.cookie('password', $("#password").val(), {
					expires : 7
				});
			}
		}
		function quxiao() {
			$("#loginname").val('');
			$("#password").val('');
		}
		
		jQuery(function() {
			var loginname = $.cookie('loginname');
			var password = $.cookie('password');
			if (typeof(loginname) != "undefined"
					&& typeof(password) != "undefined") {
				$("#loginname").val(loginname);
				$("#password").val(password);
				$("#saveid").attr("checked", true);
				$("#code").focus();
			}
		});
	</script>
	<script>
		//TOCMAT重启之后 点击左侧列表跳转登录首页 
		if (window != top) {
			top.location.href = location.href;
		}
	</script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript" src="static/js/jquery.cookie.js"></script>
</body>

</html>