
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

		<script src="static/js/batchUpload/ui.js"></script>
		<script src="static/js/batchUpload/qiniu.min.js"></script>
		<script src="static/js/batchUpload/plupload/moxie.js"></script>
		<script src="static/js/batchUpload/plupload/plupload.dev.js"></script>
		<script src="static/js/batchUpload/plupload/zh_CN.js"></script>
		<script src="static/js/batchUpload/main.js"></script>
	</head>
<body>
		
<div class="container-fluid" id="main-container">

<div id="page-content" class="clearfix">
						

	
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
		
<script type="text/javascript">
		$(document).ready(function(){
		    if(top.hangge != undefined){
                top.hangge();
			}
		});
</script>


	<div role="tabpanel" class="tab-pane fade in active" id="demo" aria-labelledby="demo-tab">

		<div class="row" style="margin-top: 20px;">
			<input type="hidden" id="domain" value="http://7xocov.com1.z0.glb.clouddn.com/">
			<input type="hidden" id="uptoken_url" value="uptoken">
			<ul class="tip col-md-12 text-mute">
				<li>
					<small>本示例限制最大上传文件100M。</small>
				</li>
			</ul>
			<div class="col-md-12">
				<div id="container" style="position: relative;">
					<a class="btn btn-default btn-lg " id="pickfiles" href="#" style="position: relative; z-index: 1;">
						<i class="glyphicon glyphicon-plus"></i>
						<span>上传文件</span>
					</a>
					<div id="html5_1c0l1goho19kt1a1aapn1hgp1u6u4_container" class="moxie-shim moxie-shim-html5" style="position: absolute; top: 0px; left: 0px; width: 171px; height: 46px; overflow: hidden; z-index: 0;"><input id="html5_1c0l1goho19kt1a1aapn1hgp1u6u4" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" multiple="" accept=""></div></div>
			</div>
			<div style="display:none" id="success" class="col-md-12">
				<div class="alert-success">
					队列全部文件处理完毕
				</div>
			</div>
			<div class="col-md-12 ">
				<table class="table table-striped table-hover text-left" style="margin-top:40px;display:none">
					<thead>
					<tr>
						<th class="col-md-4">Filename</th>
						<th class="col-md-2">Size</th>
						<th class="col-md-6">Detail</th>
					</tr>
					</thead>
					<tbody id="fsUploadProgress">
					</tbody>
				</table>
			</div>
		</div>

	</div>



		
	</body>
</html>