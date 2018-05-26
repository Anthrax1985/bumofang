
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
		<script src="static/js/batchUpload/main.js?v=1.2"></script>
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

			function deleteConfirm(callback){
	            layer.alert('是否删除',
				{
					closeBtn: 1    // 是否显示关闭按钮
					,anim: 1 //动画类型
					,btn: ['确定','取消'] //按钮
					,icon: 2    // icon
					,yes:function(){
						callback();
					},
					btn2:function(){
						layer.closeAll();
					}
	            });
	        }

			$('#download').off('click').on('click', function(){
				var ajaxObject = {};
		        ajaxObject.url = "productPicUpload/rest/getZipPath";
		        ajaxObject.data = {
		        };
		        ajaxObject.success = function(result){
		          window.open("/upload/files.zip");
		        };
		        ajaxObject.fail = function(result){
		        };      
		        ajaxPost(ajaxObject);
			});


			$('#remove_all').off('click').on('click', function(){
				deleteConfirm(function(){
					var ajaxObject = {};
			        ajaxObject.url = "productPicUpload/rest/remove_all";
			        ajaxObject.data = {
			        };
			        ajaxObject.success = function(result){
			          	layer.closeAll();
			          	location.reload();
			        };
			        ajaxObject.fail = function(result){
			        };      
			        ajaxPost(ajaxObject);
				});				
			});
		});
</script>


	<div role="tabpanel" class="tab-pane fade in active" id="demo" aria-labelledby="demo-tab">

		<div class="row" style="margin-top: 20px;">
			<%--<input type="hidden" id="domain" value="http://p0l8q7nll.bkt.clouddn.com/">--%>
			<input type="hidden" id="domain" value="${uploadDomain}">
			<input type="hidden" id="imgProductDomain" value="${imgProductDomain}">
			<input type="hidden" id="imgD3dDomain" value="${imgD3dDomain}">
			<input type="hidden" id="imgSceneDomain" value="${imgSceneDomain}">
			<input type="hidden" id="uptoken" value="${token}">
			<ul class="tip col-md-12 text-mute">
				<li>
					<small>上传场景图（A_0001.jpg），物件透明遮罩图（A_0001_PI.png），坐标文件（A_0001.txt）3D渲染图（A_0001_PI_FAHTD0001-B03.png）</small>
					<br/>
					<small>上传后Pad端刷新一下图片会更新</small>
					<br/>
					<small>可以在左侧下一个菜单栏[产品3D渲染图]看到上传日期</small>
				</li>
			</ul>
			<div class="col-md-12">
				<div id="container" style="position: relative;">
					<a class="btn btn-default btn-lg " id="pickfiles" href="#" style="position: relative; z-index: 1;">
						<i class="glyphicon glyphicon-plus"></i>
						<span>上传文件</span>
					</a>

					<%--<a class="btn btn-default btn-lg " id="download" href="javascript:void()" style="position: relative; z-index: 1;">--%>
						<%--<i class="glyphicon glyphicon-file"></i>--%>
						<%--<span>下载ZIP文件</span>--%>
					<%--</a>--%>


					<%--<a class="btn btn-default btn-lg " id="remove_all" href="javascript:void()" style="position: relative; z-index: 1;">--%>
					<%--<i class="glyphicon glyphicon-remove"></i>--%>
					<%--<span>清除所有图片</span>--%>
					<%--</a>--%>

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