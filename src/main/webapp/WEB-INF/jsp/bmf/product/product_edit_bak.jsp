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
		/* if($("#productName").val()==""){
			popupTip('productName', '请输入品名');
			return false;
		}
		if($("#productCraft").val()==""){
			popupTip('productCraft', '请输入工艺');
			return false;
		}
		if($("#productMaterial").val()==""){
			popupTip('productMaterial', '请输入材质');
			return false;
		}
		if($("#productNarrowPrice").val()==""){
			popupTip('productNarrowPrice', '请输入单幅价格');
			return false;
		}
		if($("#productWidePrice").val()==""){
			popupTip('productWidePrice', '请输入宽幅价格');
			return false;
		}
		if($("#productComponent").val()==""){
			popupTip('productComponent', '请输入成份');
			return false;
		}
		if($("#productUnitWeight").val()==""){
			popupTip('productUnitWeight', '请输入每平方客重');
			return false;
		}
		if($("#productNarrowWidth").val()==""){
			popupTip('productNarrowWidth', '请输入幅宽单幅');
			return false;
		}
		if($("#productWideWidth").val()==""){
			popupTip('productWideWidth', '请输入幅宽宽幅');
			return false;
		}
		if($("#patternHorizontalSize").val()==""){
			popupTip('patternHorizontalSize', '请输入花型尺寸-水平');
			return false;
		}
		if($("#patternVerticalSize").val()==""){
			popupTip('patternVerticalSize', '请输入花型尺寸-垂直');
			return false;
		}
		if($("#productSourceArea").val()==""){
			popupTip('productSourceArea', '请输入原产地');
			return false;
		}
		if($("#patternPicture1").val()==""){
			popupTip('patternPicture1', '请输入花型图1600*800');
			return false;
		}
		if($("#patternPicture2").val()==""){
			popupTip('patternPicture2', '请输入花型图800*800');
			return false;
		}
		if($("#qualityPicture1").val()==""){
			popupTip('qualityPicture1', '请输入质地图1600*800');
			return false;
		}
		if($("#qualityPicture2").val()==""){
			popupTip('qualityPicture2', '请输入质地图400*200');
			return false;
		}
		if($("#qualityPicture3").val()==""){
			popupTip('qualityPicture3', '请输入质地图800*800');
			return false;
		}
		if($("#qualityPicture4").val()==""){
			popupTip('qualityPicture4', '请输入质地图400*400');
			return false;
		}
		if($("#qualityControlReport").val()==""){
			popupTip('qualityControlReport', '请输入质检报告');
			return false;
		}
		if($("#delFlag").val()==""){
			popupTip('delFlag', '请输入删除标记');
			return false;
		} */
	
		var action = '${msg}';
		var url = 'product/' + action;
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
	$(".newspic").click(function() {
		$(this).siblings('input').click();
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
</script>
	</head>
<body>
	<form action="product/${msg}.do" name="editForm" id="editForm" method="post">
		<input type="hidden" name="id" id="id" value="${entity.id}"/>
		<div id="zhongxin" style="padding-top:20px;">
		<table id="table_report" class="table noline">
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>品名:</td>
				<td><input type="text" name="productName" id="productName" value="${entity.productName}" maxlength="32" placeholder="请输入品名"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>颜色:</td>
				<td>
					<label><input type="checkbox" name="productColor" class="productColor" value="红色" />红色</label>
					<label><input type="checkbox" name="productColor" class="productColor" value="黄色" />黄色</label>
					<label><input type="checkbox" name="productColor" class="productColor" value="绿色" />绿色</label>
					<label><input type="checkbox" name="productColor" class="productColor" value="蓝色" />蓝色</label>
					<label><input type="checkbox" name="productColor" class="productColor" value="咖色" />咖色</label>
					<label><input type="checkbox" name="productColor" class="productColor" value="黑白灰" />黑白灰</label>
				</td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>风格:</td>
				<td>
					<label><input type="checkbox" name="productStyle" class="productStyle" value="红色" />中式</label>
					<label><input type="checkbox" name="productStyle" class="productStyle" value="红色" />美式</label>
					<label><input type="checkbox" name="productStyle" class="productStyle" value="红色" />欧式</label>
					<label><input type="checkbox" name="productStyle" class="productStyle" value="红色" />现代</label>
					<label><input type="checkbox" name="productStyle" class="productStyle" value="红色" />混搭</label>
				</td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>工艺:</td>
				<td>
					<label><input type="radio" name="productCraft" class="productCraft" value="" />提花</label>
					<label><input type="radio" name="productCraft" class="productCraft" value="" />绣花</label>
					<label><input type="radio" name="productCraft" class="productCraft" value="" />印花</label>
					<label><input type="radio" name="productCraft" class="productCraft" value="" />素织物</label>
					<label><input type="radio" name="productCraft" class="productCraft" value="" />其他</label>
				</td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>材质:</td>
				<td>
					<label><input type="radio" name="productMaterial" class="productMaterial" value="" />棉麻</label>
					<label><input type="radio" name="productMaterial" class="productMaterial" value="" />雪尼尔</label>
					<label><input type="radio" name="productMaterial" class="productMaterial" value="" />涤丝精棉</label>
					<label><input type="radio" name="productMaterial" class="productMaterial" value="" />羊毛混织</label>
					<label><input type="radio" name="productMaterial" class="productMaterial" value="" />其他</label>
				</td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>应用:</td>
				<td>
					<label><input type="checkbox" name="productApplication" class="productApplication" value="红色" />沙发</label>
					<label><input type="checkbox" name="productApplication" class="productApplication" value="红色" />窗帘</label>
					<label><input type="checkbox" name="productApplication" class="productApplication" value="红色" />墙布</label>
					<label><input type="checkbox" name="productApplication" class="productApplication" value="红色" />抱枕</label>
					<label><input type="checkbox" name="productApplication" class="productApplication" value="红色" />床品</label>
					<label><input type="checkbox" name="productApplication" class="productApplication" value="红色" />其他</label>
				</td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>价格:</td>
				<td>
					<label>单幅</label>
					<input type="text" name="productNarrowPrice" id="productNarrowPrice" value="${entity.productNarrowPrice}" /><label>元/米</label>
					<label>宽幅</label>	
					<input type="text" name="productWidePrice" id="productWidePrice" value="${entity.productWidePrice}" /><label>元/米</label>			
				</td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>成份:</td>
				<td><input type="text" name="productComponent" id="productComponent" value="${entity.productComponent}" maxlength="32" /><label>%</label></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>每平方克重:</td>
				<td><input type="text" name="productUnitWeight" id="productUnitWeight" value="${entity.productUnitWeight}" maxlength="32" /><label>g/m<sup>2</sup>+5%</label></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>幅宽:</td>
				<td>
					<label>单幅</label>
					<input type="text" name="productNarrowWidth" id="productNarrowWidth" value="${entity.productNarrowWidth}" /><label>cm+3cm</label>		
					<label>宽幅</label>
					<input type="text" name="productWideWidth" id="productWideWidth" value="${entity.productWideWidth}" /><label>cm+3cm</label>	
				</td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>水洗标志:</td>
				<td>
					<label><input type="checkbox" name="washingMethod" class="washingMethod" value="红色" />水洗1</label>
					<label><input type="checkbox" name="washingMethod" class="washingMethod" value="红色" />水洗2</label>
					<label><input type="checkbox" name="washingMethod" class="washingMethod" value="红色" />水洗3</label>
					<label><input type="checkbox" name="washingMethod" class="washingMethod" value="红色" />水洗4</label>
					<label><input type="checkbox" name="washingMethod" class="washingMethod" value="红色" />水洗5</label>
					<label><input type="checkbox" name="washingMethod" class="washingMethod" value="红色" />水洗6</label>
					<label><input type="checkbox" name="washingMethod" class="washingMethod" value="红色" />水洗7</label>
					<label><input type="checkbox" name="washingMethod" class="washingMethod" value="红色" />水洗8</label>
					<label><input type="checkbox" name="washingMethod" class="washingMethod" value="红色" />水洗9</label>
					<label><input type="checkbox" name="washingMethod" class="washingMethod" value="红色" />水洗10</label>
				</td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>花型尺寸-水平:</td>
				<td><input type="text" name="patternHorizontalSize" id="patternHorizontalSize" value="${entity.patternHorizontalSize}" /></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>花型尺寸-垂直:</td>
				<td><input type="text" name="patternVerticalSize" id="patternVerticalSize" value="${entity.patternVerticalSize}" /></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>原产地:</td>
				<td><input type="text" name="productSourceArea" id="productSourceArea" value="${entity.productSourceArea}" /></td>
			</tr>
			</table>
			<table id="table_report2" class="table noline">
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>花型图1600*800:</td>
				<td>
					<a href="javascript:void(0)" class="newspic">
						<c:if test="${entity.patternPicture1 != null && entity.patternPicture1 != ''}"><img style="width:200px;height:100px;" src="<%=basePath%>${entity.patternPicture1}"/></c:if>
						<c:if test="${entity.patternPicture1 == null || entity.patternPicture1 == ''}"><img style="width:200px;height:100px;" src="<%=basePath%>static/img/selectimg400_200.png"/></c:if>
					</a>
					<input type="file" id="patternPicture1" name="patternPicture1" onchange="showPic(this)"  style="display: none">
					<span>1600*800</span>
				</td>
				<td>
					<a href="javascript:void(0)" class="newspic">
						<c:if test="${entity.patternPicture2 != null && entity.patternPicture2 != ''}"><img style="width:100px;height:100px;"src="<%=basePath%>${entity.patternPicture2}"/></c:if>
						<c:if test="${entity.patternPicture2 == null || entity.patternPicture2 == ''}"><img style="width:100px;height:100px;" src="<%=basePath%>static/img/selectimg200_200.png"/></c:if>
					</a>
					<input type="file" id="patternPicture2" name="patternPicture2" onchange="showPic(this)"  style="display: none">
					<span>800*800</span>
				</td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>质地图:</td>
				<td>
					<a href="javascript:void(0)" class="newspic">
						<c:if test="${entity.qualityPicture1 != null && entity.qualityPicture1 != ''}"><img style="width:200px;height:100px;" src="<%=basePath%>${entity.qualityPicture1}"/></c:if>
						<c:if test="${entity.qualityPicture1 == null || entity.qualityPicture1 == ''}"><img style="width:200px;height:100px;"  src="<%=basePath%>static/img/selectimg400_200.png"/></c:if>
					</a>
					<input type="file" id="qualityPicture1" name="qualityPicture1" onchange="showPic(this)"  style="display: none">
					<span>1600*800</span>
				</td>
				<td>
					<a href="javascript:void(0)" class="newspic">
						<c:if test="${entity.qualityPicture2 != null && entity.qualityPicture2 != ''}"><img style="width:200px;height:100px;"  src="<%=basePath%>${entity.qualityPicture2}"/></c:if>
						<c:if test="${entity.qualityPicture2 == null || entity.qualityPicture2 == ''}"><img style="width:200px;height:100px;"  src="<%=basePath%>static/img/selectimg400_200.png"/></c:if>
					</a>
					<input type="file" id="qualityPicture2" name="qualityPicture2" onchange="showPic(this)"  style="display: none">
					<span>400*200</span>
				</td>
				<td>
					<a href="javascript:void(0)" class="newspic">
						<c:if test="${entity.qualityPicture3 != null && entity.qualityPicture3 != ''}"><img style="width:100px;height:100px;"  src="<%=basePath%>${entity.qualityPicture3}"/></c:if>
						<c:if test="${entity.qualityPicture3 == null || entity.qualityPicture3 == ''}"><img style="width:100px;height:100px;"   src="<%=basePath%>static/img/selectimg200_200.png"/></c:if>
					</a>
					<input type="file" id="qualityPicture3" name="qualityPicture3" onchange="showPic(this)"  style="display: none">
					<span>800*800</span>
				</td>
				<td>
					<a href="javascript:void(0)" class="newspic">
						<c:if test="${entity.qualityPicture4 != null && entity.qualityPicture4 != ''}"><img style="width:100px;height:100px;"   src="<%=basePath%>${entity.qualityPicture4}"/></c:if>
						<c:if test="${entity.qualityPicture4 == null || entity.qualityPicture4 == ''}"><img style="width:100px;height:100px;"   src="<%=basePath%>static/img/selectimg200_200.png"/></c:if>
					</a>
					<input type="file" id="qualityPicture4" name="qualityPicture4" onchange="showPic(this)"  style="display: none">
					<span>400*400</span>
				</td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>添加搭配方案:</td>
				<td>
					<a href="javascript:void(0)" class="newspic">
						<c:if test="${entity.qualityPicture1 != null && entity.qualityPicture1 != ''}"><img style="width:200px;height:100px;"   src="<%=basePath%>${entity.qualityPicture1}"/></c:if>
						<c:if test="${entity.qualityPicture1 == null || entity.qualityPicture1 == ''}"><img style="width:200px;height:100px;"   src="<%=basePath%>static/img/selectimg400_200.png"/></c:if>
					</a>
					<input type="file" id="qualityPicture1" name="qualityPicture1" onchange="showPic(this)"  style="display: none">
					<span>1600*800</span>
				</td>
				<td>
					<a href="javascript:void(0)" class="newspic">
						<c:if test="${entity.qualityPicture2 != null && entity.qualityPicture2 != ''}"><img  style="width:200px;height:100px;" src="<%=basePath%>${entity.qualityPicture2}"/></c:if>
						<c:if test="${entity.qualityPicture2 == null || entity.qualityPicture2 == ''}"><img  style="width:200px;height:100px;"src="<%=basePath%>static/img/selectimg400_200.png"/></c:if>
					</a>
					<input type="file" id="qualityPicture2" name="qualityPicture2" onchange="showPic(this)"  style="display: none">
					<span>400*200</span>
				</td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;"><em>*</em>质检报告:</td>
				<td>
					<a href="javascript:void(0)" class="newspic">
						<c:if test="${entity.qualityControlReport != null && entity.qualityControlReport != ''}"><img style="width:200px;height:100px;"   src="<%=basePath%>${entity.qualityControlReport}"/></c:if>
						<c:if test="${entity.qualityControlReport == null || entity.qualityControlReport == ''}"><img style="width:200px;height:100px;"   src="<%=basePath%>static/img/selectimg400_200.png"/></c:if>
					</a>
					<input type="file" id="qualityControlReport" name="qualityControlReport" onchange="showPic(this)"  style="display: none">
				</td>
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