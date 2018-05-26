<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>">
<link rel="stylesheet" href="<%=basePath%>static/css/bmf/common.css">
		
<script type="text/javascript">
	//保存
	function save(){
		if($("#NUMBER").val()==""){
			popupTip('NUMBER', '请输入编号');
			return false;
		}
		if($("#NAME").val()==""){
			popupTip('NAME', '请输入品名');
			return false;
		}
		if($("#COLOR").val()==""){
			popupTip('COLOR', '请输入色号');
			return false;
		}
		if($("#INGREDIENTS").val()==""){
			popupTip('INGREDIENTS', '请输入成份');
			return false;
		}
		if($("#WIDTH").val()==""){
			popupTip('WIDTH', '请输入门幅');
			return false;
		}
		if($("#FLOWER_SIZE").val()==""){
			popupTip('FLOWER_SIZE', '请输入花幅');
			return false;
		}
		if($("#FLOWER_DISTANCE").val()==""){
			popupTip('FLOWER_DISTANCE', '请输入花距');
			return false;
		}
		if($("#WEIGHT").val()==""){
			popupTip('WEIGHT', '请输入克重');
			return false;
		}
		if($("#FLAT_PICTURE").val()==""){
			popupTip('FLAT_PICTURE', '请输入平面图片');
			return false;
		}
		if($("#ART_PICTURE").val()==""){
			popupTip('ART_PICTURE', '请输入艺术图片');
			return false;
		}
		if($("#STREAM_PICTURE").val()==""){
			popupTip('STREAM_PICTURE', '请输入瀑布流图片');
			return false;
		}
// 		if($("#USE_LABELS").val()==""){
// 			popupTip('USE_LABELS', '请输入用途标签');
// 			return false;
// 		}
// 		if($("#COLOR_LABELS").val()==""){
// 			popupTip('COLOR_LABELS', '请输入颜色标签 ');
// 			return false;
// 		}
// 		if($("#STYLE_TAGS").val()==""){
// 			popupTip('STYLE_TAGS', '请输入风格标签');
// 			return false;
// 		}
		if($("#COLLOCATION_TAGS").val()==""){
			popupTip('COLLOCATION_TAGS', '请输入搭配标签');
			return false;
		}
		if($("#DESIGN_TAGS").val()==""){
			popupTip('DESIGN_TAGS', '请输入款式标签');
			return false;
		}
		if($("#OTHER_TAGS").val()==""){
			popupTip('OTHER_TAGS', '请输入其他标签');
			return false;
		}
		if($("#PRICE").val()==""){
			popupTip('PRICE', '请输入单价');
			return false;
		}
		
		$("#saveBtn").attr("disabled","disabled");
		$("#saveBtn").value("保存中");
	
// 		var action = '${msg}';
// 		var url = 'fabric/rest/' + action;
// 		ajaxSave(target, url, action);
		return true;
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
	
	$(".newspic").click(function() {
		$(this).siblings('input').click();
	});
	
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
	
	function showPic(data) {
		if (data.files && data.files[0]) {
			var reader = new FileReader();
			reader.onload = function(evt) {
				data.parentNode.childNodes[1].childNodes[1].src = evt.target.result;
			}
			reader.readAsDataURL(data.files[0]);
		}
	}
	
	function selectColor(){
		var value = $("#COLOR_LABELS").val();
		var array = value.split(",");
		$('.col-sm-4 input:checkbox').each(function(index, element){
			var value = $(element).val();
    		for(var i=0;i<array.length;i++){
    			var oneItem = array[i];
    			if(value == oneItem){
					$(element).attr("checked", true);
					break;
    			}
     		}

    	});
		
		var message = $("#message_div").html();
		BootstrapDialog.show({cssClass:'two-row-dialog',
            message: "<div id='color_div'>" + message + "</div>",
            title: '选择颜色',
            buttons: [{
                label: '取消',
                action: function(dialog) {
                    dialog.close();
                }
            }, {
                label: '确定',
                cssClass: 'btn-primary',
                action: function(dialog) {
                	var selectValue = ",";
                	$('#color_div div .col-sm-4 input:checkbox:checked').each(function(index, element){
                		selectValue += $(element).val() + ",";
                	});
                	$("#COLOR_LABELS").val(selectValue);
                	
                	dialog.close();
                }
            }]
          });
        
	}
	
</script>
	</head>
<body>
	<form action="fabric/saveMulti.do" name="Form" id="Form" method="post" enctype="multipart/form-data" onsubmit="return save();">
		<input type="hidden" name="ID" id="ID" value="${pd.ID}"/>
		<div id="zhongxin" style="padding-top:0px;">
		<table id="table_report" class="table noline">
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>编号:</td>
				<td><input type="text" name="NUMBER" id="NUMBER" value="${pd.NUMBER}" maxlength="32" placeholder="请输入编号" title="编号"/></td>
				
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>单价:</td>
				<td><input type="text" name="PRICE" id="PRICE" value="${pd.PRICE}" maxlength="32" placeholder="请输入单价" title="单价"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>品名:</td>
				<td><input type="text" name="NAME" id="NAME" value="${pd.NAME}" maxlength="32" placeholder="请输入品名" title="品名"/></td>
				
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>用途标签:</td>
				<td><input type="text" name="USE_LABELS" id="USE_LABELS" value="${pd.USE_LABELS}" maxlength="32" placeholder="请输入用途标签" title="用途标签"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>色号:</td>
				<td><input type="text" name="COLOR" id="COLOR" value="${pd.COLOR}" maxlength="32" placeholder="请输入色号" title="色号"/></td>
				
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>风格标签:</td>
				<td><input type="text" name="STYLE_TAGS" id="STYLE_TAGS" value="${pd.STYLE_TAGS}" maxlength="32" placeholder="请输入风格标签" title="风格标签"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>成份:</td>
				<td><input type="text" name="INGREDIENTS" id="INGREDIENTS" value="${pd.INGREDIENTS}" maxlength="32" placeholder="请输入成份" title="成份"/></td>
				
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>搭配标签:</td>
				<td><input type="text" name="COLLOCATION_TAGS" id="COLLOCATION_TAGS" value="${pd.COLLOCATION_TAGS}" maxlength="32" placeholder="请输入搭配标签" title="搭配标签"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>门幅:</td>
				<td><input type="text" name="WIDTH" id="WIDTH" value="${pd.WIDTH}" maxlength="32" placeholder="请输入门幅" title="门幅"/></td>
				
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>款式标签:</td>
				<td><input type="text" name="DESIGN_TAGS" id="DESIGN_TAGS" value="${pd.DESIGN_TAGS}" maxlength="32" placeholder="请输入款式标签" title="款式标签"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>花幅:</td>
				<td><input type="text" name="FLOWER_SIZE" id="FLOWER_SIZE" value="${pd.FLOWER_SIZE}" maxlength="32" placeholder="请输入花幅" title="花幅"/></td>
				
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>其他标签:</td>
				<td><input type="text" name="OTHER_TAGS" id="OTHER_TAGS" value="${pd.OTHER_TAGS}" maxlength="32" placeholder="请输入其他标签" title="其他标签"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>花距:</td>
				<td><input type="text" name="FLOWER_DISTANCE" id="FLOWER_DISTANCE" value="${pd.FLOWER_DISTANCE}" maxlength="32" placeholder="请输入花距" title="花距"/></td>
				
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>克重:</td>
				<td><input type="text" name="WEIGHT" id="WEIGHT" value="${pd.WEIGHT}" maxlength="32" placeholder="请输入克重" title="克重"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>颜色标签 :</td>
				<td><input type="text" name="COLOR_LABELS" id="COLOR_LABELS" value="${pd.COLOR_LABELS}" 
					maxlength="32" placeholder="请选择颜色标签" title="颜色标签" 
					onclick="selectColor()" readOnly="true" 
					/>
				</td>
				
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">平面图片:</td>
<%-- 				<td><input type="text" name="FLAT_PICTURE" id="FLAT_PICTURE" value="${pd.FLAT_PICTURE}" maxlength="32" placeholder="请输入平面图片" title="平面图片"/></td> --%>
				<td>
					<a href="javascript:void(0)" class="flat_picture newspic">
						<c:if test="${pd.FLAT_PICTURE != null && pd.FLAT_PICTURE != ''}"><img src="<%=basePath%>${pd.FLAT_PICTURE}"/></c:if>
						<c:if test="${pd.FLAT_PICTURE == null || pd.FLAT_PICTURE == ''}"><img src="<%=basePath%>static/img/selectimg.png"/></c:if>
					</a>
					<input type="file" id="flat_picture" name="flat_picture" onchange="showPic(this)"  style="display: none">
				</td>
							
							
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>艺术图片:</td>
<%-- 				<td><input type="text" name="ART_PICTURE" id="ART_PICTURE" value="${pd.ART_PICTURE}" maxlength="32" placeholder="请输入艺术图片" title="艺术图片"/></td>			 --%>
				<td>
					<a href="javascript:void(0)" class="art_picture newspic">
						<c:if test="${pd.ART_PICTURE != null && pd.ART_PICTURE != ''}"><img src="<%=basePath%>${pd.ART_PICTURE}"/></c:if>
						<c:if test="${pd.ART_PICTURE == null || pd.ART_PICTURE == ''}"><img src="<%=basePath%>static/img/selectimg.png"/></c:if>
					</a>
					<input type="file" id="art_picture" name="art_picture" onchange="showPic(this)"  style="display: none">
				</td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">瀑布流图片:</td>
<%-- 				<td><input type="text" name="STREAM_PICTURE" id="STREAM_PICTURE" value="${pd.STREAM_PICTURE}" maxlength="32" placeholder="请输入瀑布流图片" title="瀑布流图片"/></td> --%>
				<td>
					<a href="javascript:void(0)" class="stream_picture newspic">
						<c:if test="${pd.STREAM_PICTURE != null && pd.STREAM_PICTURE != ''}"><img src="<%=basePath%>${pd.STREAM_PICTURE}"/></c:if>
						<c:if test="${pd.STREAM_PICTURE == null || pd.STREAM_PICTURE == ''}"><img src="<%=basePath%>static/img/selectimg.png"/></c:if>
					</a>
					<input type="file" id="stream_picture" name="stream_picture" onchange="showPic(this)"  style="display: none">
				</td>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>渲染文件:</td>
				<td>
					<input type="file" id="render_file" name="render_file" >
				</td>
				
			</tr>
			<tr>
				<td id="successMessage" style="text-align: center;display:none;color:#045e9f" colspan="10">
					保存成功
				</td>
			</tr>
			<tr>
				<td style="text-align: center;" colspan="10">
					<button id="saveBtn" class="btn btn-primary" type="submit" style="width:100px">保存</button>
				</td>
			</tr>
		</table>
		</div>
		<div id="message_div" style="display: none;">
			<div class="row color_labels">
				<%
					for(int i=1;i<=34;i++){
				%>
					<div class="col-sm-4">
						<input id="checkbox_<%=(i) %>" value="<%=(i) %>" type="checkbox" name="color_labels" class="form-control">
						<span style="background-color: <%=toRgb(i) %>" class="colorblock"></span>
						<span><%=toRgb(i) %></span>
					</div>
				<%
					}
				%>
			</div>
		</div>
	</form>
</body>
</html>