<!-- 面料添加页面 -->
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%!
	String[] array = new String[]{
			"(230,0,18)", "(235,97,0)","(243,152,0)", 
			"(252,200,0)", "(255,251,0)", "(207,0,219)",
			"(143,195,31)", "(34,172,56)", "(0,153,68)",
			"(0,155,107)", "(0,158,150)", "(0,160,193)",
			"(0,160,233)", "(0,134,209)", "(0,104,183)",
			"(0,71,157)", "(29,32,136)", "(96,25,134)",
			"(146,7,131)", "(190,0,129)", "(228,0,127)",
			"(229,0,106)", "(229,0,79)", "(230,0,51)",
			"(255,255,255)", "(224,224,224)", "(197,197,197)",
			"(168,168,168)", "(141,141,141)", "(112,112,112)",
			"(85,85,85)", "(57,57,57)", "(28,28,28)",
			"(0,0,0)" };
	String toRgb(int index){
		return "RGB" + array[index - 1];
	}
%>


<div class="row">
	<div class="col-xs-12 col-sm-4">
		<div class="cblock">
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<form role="form" id="form" action="<%=basePath%>/fabrics/save" method="POST" enctype="multipart/form-data" onsubmit="return formcheck();">
						<input type="text" placeholder="编号" name="id" value="${pd.id}" style="display: none"/>
						
						<div class="form-group">
							<label for="input1">编号</label>
							<input type="text" class="form-control" id="number" placeholder="编号" name="number" value="${pd.number}"/>
						</div>
						<div class="form-group">
							<label for="input2">品名</label>
							<input type="text" class="form-control" id="name" placeholder="名称" name="name" value="${pd.name}"/>
						</div>
						<div class="form-group">
							<label for="input3">色号</label>
							<input type="text" class="form-control" id="input3" placeholder="色号" name="color" value="${pd.color}"/>
						</div>
						<div class="form-group">
							<label for="input4">成份</label>
							<input type="text" class="form-control" id="input4" placeholder="成份" name="ingredients" value="${pd.ingredients}"/>
						</div>
						<div class="form-group">
							<label for="input5">门幅（cm）</label>
							<input type="text" class="form-control" id="input5" placeholder="门幅" name="width" value="${pd.width}"/>
						</div>
						<div class="form-group">
							<label for="input6">花幅（cm）</label>
							<input type="text" class="form-control" id="input6" placeholder="花幅" name="flower_size" value="${pd.flower_size}"/>
						</div>
						<div class="form-group">
							<label for="input7">花距（cm）</label>
							<input type="text" class="form-control" id="input7" placeholder="花距" name="flower_distance" value="${pd.flower_distance}"/>
						</div>
						<div class="form-group">
							<label for="input8">克重（g）</label>
							<input type="text" class="form-control" id="input8" placeholder="克重" name="gram" value="${pd.gram}"/>
						</div>
						<div class="form-group">
							<label for="input9">平面图片</label>
							<a href="javascript:void(0)" class="flat_picture newspic">
								<img src="<%=basePath%>/static/bumofang/admin/img/selectimg.png"/>
							</a>
							<input type="file" id="flat_picture" name="flat_picture" onchange="showPic(this)"  style="display: none">
						</div>
						<div class="form-group">
							<label for="input10">艺术图片</label>
							<a href="javascript:void(0)" class="art_picture newspic">
								<img src="<%=basePath%>static/bumofang/admin/img/selectimg.png"></a>
							<input type="file" id="input10" name="art_picture" onchange="showPic(this)" style="display: none">
						</div>
						<div class="form-group">
							<label for="input11">瀑布流图片</label>
							<a href="javascript:void(0)" class="stream_picture newspic">
							<img src="<%=basePath%>static/bumofang/admin/img/selectimg.png"></a>
							<input type="file" id="input11" name="stream_picture" onchange="showPic(this)" style="display: none">
						</div>
						<div class="form-group">
							<label for="input12">用途标签</label>
							<input type="text" class="form-control" id="input12" placeholder="用途标签" name="use_labels" value="${pd.use_labels}"/>
						</div>
						<div class="form-group">
							<label>颜色标签</label> <br>
							<div class="row color_labels">
							<%
								for(int i=1;i<=34;i++){
							%>
								<div class="col-sm-4">
									<input type="checkbox" name="color_labels[]" v-model="color_labels" v-bind:value="<%=i %>" name="color_labels" class="form-control">
									<span style="background-color: <%=toRgb(i) %>" class="colorblock"></span>
									<span><%=toRgb(i) %></span>
								</div>
							<%
								}
							%>
							
							<input type="text" name="select_colors" style="display: none;"/>
							</div>
						</div>
						<div class="form-group">
							<label for="input14">风格标签</label>
							<input type="text" class="form-control" id="input14" placeholder="风格标签" name="style_tags" value="${pd.style_tags}"/>
						</div>
						<div class="form-group">
							<label for="input15">搭配标签</label>
							<input type="text" class="form-control" id="input15" placeholder="搭配标签" name="collocation_tag" value="${pd.collocation_tag}"/>
						</div>
						<div class="form-group">
							<label for="input16">款式标签</label>
							<input type="text" class="form-control" id="input16" placeholder="款式标签" name="design_tags" value="${pd.design_tags}"/>
						</div>
						<div class="form-group">
							<label for="input17">其他标签</label>
							<input type="text" class="form-control" id="input17" placeholder="其他标签" name="other_tags" value="${pd.other_tags}"/>
						</div>
						<div class="form-group">
							<label for="input18">单价</label>
							<input type="text" class="form-control" id="input18" placeholder="单价（米/元）" name="price" value="${pd.price}"/>
						</div>
						<button type="submit" class="btn btn-default">确定</button>
					</form>
				</div>
				<script>
					function formcheck() {
						var price = document.getElementsByName('price');
						var number = document.getElementsByName('number');
						var name = document.getElementsByName('name');
						var color = document.getElementsByName('color');
						var ingredients = document
								.getElementsByName('ingredients');
						var width = document.getElementsByName('width');
						var flower_size = document
								.getElementsByName('flower_size');
						var flower_distance = document
								.getElementsByName('flower_distance');
						var gram = document.getElementsByName('gram');

						if (number[0].value == undefined
								|| number[0].value == "") {
							alert('请输入编号！');
							return false;
						}
						if (name[0].value == undefined || name[0].value == "") {
							alert('请输入品名！');
							return false;
						}
						if (color[0].value == undefined || color[0].value == "") {
							alert('请输入色号！');
							return false;
						}
						if (ingredients[0].value == undefined
								|| ingredients[0].value == "") {
							alert('请输入成份！');
							return false;
						}
						if (width[0].value == undefined || width[0].value == "") {
							alert('请输入门幅！');
							return false;
						}
						if (flower_size[0].value == undefined
								|| flower_size[0].value == "") {
							alert('请输入花幅！');
							return false;
						}
						if (flower_distance[0].value == undefined
								|| flower_distance[0].value == "") {
							alert('请输入花距！');
							return false;
						}
						if (gram[0].value == undefined || gram[0].value == "") {
							alert('请输入克重！');
							return false;
						}
						if (price[0].value == undefined || price[0].value == "") {
							alert('请输入单价！');
							return false;
						}
						return true;
					}
					var vue = new Vue(
							{
								el : '.color_labels',
								data : function() {
									return {
										color_labels : '${pd.color_labels}'
												.split(','),
									}
								}
							});
					$(".newspic").click(function() {
						$(this).siblings('input').click();
					});
					function showPic(data) {
						if (data.files && data.files[0]) {
							var reader = new FileReader();
							reader.onload = function(evt) {
								data.parentNode.childNodes[3].childNodes[1].src = evt.target.result;
							}
							reader.readAsDataURL(data.files[0]);
						}
					}
				</script>
			</div>
		</div>
	</div>
</div>