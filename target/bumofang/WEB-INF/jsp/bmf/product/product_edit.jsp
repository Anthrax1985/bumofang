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
		<script type="text/javascript" src="static/js/jquery.min.js"></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<style>
			.img21{width:140px;height:50px;}
			.img11{width:66px;height:50px;}
			.span21{display:inline-block;position:relative;left:-120px;top:20px; font-size:16px;color:#C8C8C8;}
			.span11{display:inline-block;position:relative;left:-80px;top:20px; font-size:16px;color:#C8C8C8;}
			.td_img_height{height:80px;}
			table tr td:first-child{width:200px;padding-right: 50px; text-align: right;}
			table tr td{margin:20px 0px;padding:20px 0;}
			.input_len_short{width:80px; margin:0 10px;}
			.input_len_mid{width:150px; margin:0 10px;}
			.input_len_long{width:500px; height: 30px;}
			.submitBtn{width:160px; height:30px; inline-height:30px; cursor:pointer;font-size:18px;padding:0px 28px; border:1px solid black; border-radius:4px;background:#fff;}
			.oDiv{width:140px; position:relative;dislay:inline-block; margin:20px 20px 0 0;float:left;cursor: pointer; text-align:center;}
			.newspic { text-decoration: none; }
		</style>
	</head>
<body>
	<form action="product/saveProduct.do" name="editForm" id="editForm" method="post" enctype="multipart/form-data" onsubmit="return save();">
		<input type="hidden" name="id" id="id" value="${entity.id}"/>
		<input type="hidden" name="productColorList" id="productColorList" value=""/>
		<input type="hidden" name="productStyleList" id="productStyleList" value=""/>
		<input type="hidden" name="productApplicationList" id="productApplicationList" value=""/>
		<input type="hidden" name="washingMethodList" id="washingMethodList" value=""/>
		<input type="hidden" name="productMatchIdList" id="productMatchIdList" value=""/>
		<input type="hidden" name="craftId" id="craftId" value="${entity.productCraft}"/>
		<input type="hidden" name="materialId" id="materialId" value="${entity.productMaterial}"/>
		<input type="hidden" name="pdColorIdres" id="pdColorIdres" value="${pdColorIdres.pdColorIdres}"/>
		<div id="zhongxin" style="padding-top:20px;">
		<table id="table_report" class="table noline">
			<tr>
				<td>品名</td>
				<td><input type="text" name="productName" id="productName" value="${entity.productName}" class="input_len_long"/></td>
			</tr>
			<tr>
				<td >颜色</td>
				 <td style="width:500px;">
					<c:choose >
						<c:when test="${not empty colorList}">
							<c:forEach items="${colorList}" var="var" varStatus="vs">
								<label style="width:200px;display:inline-block"  >
									<input type="checkbox" name="productColor" class="productColor" value="${var.id}" <c:if test="${var.selected == 1}">checked</c:if>/>
									<img style="width:66px;height:24px; vertical-align:middle" src="<%=basePath%>${var.colorIcon}"/>
									${var.colorName}
								</label>
							</c:forEach>
						</c:when>
					</c:choose>
				</td>
			</tr>
			<tr>
				 <td >风格</td>
				 <td>
					<c:choose>
						<c:when test="${not empty styleList}">
							<c:forEach items="${styleList}" var="var" varStatus="vs">
								<c:if test="${var.id!=5}">
									<label style="margin-right:50px;"><input type="checkbox" name="productStyle" class="productStyle"  value="${var.id}" <c:if test="${var.selected == 1}">checked</c:if>/>${var.styleName}</label>
								</c:if>
							</c:forEach>
						</c:when>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td >工艺</td>
				 <td>
					<c:choose>
						<c:when test="${not empty craftList}">
							<c:forEach items="${craftList}" var="var" varStatus="vs">
								<label style="margin-right:50px;"><input type="radio" name="productCraft" class="productCraft"  value="${var.id}" />${var.craftName}</label>
							</c:forEach>
						</c:when>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td >材质</td>
				 <td>
					<c:choose>
						<c:when test="${not empty materialList}">
							<c:forEach items="${materialList}" var="var" varStatus="vs">
								<label style="margin-right:50px;"><input type="radio" name="productMaterial" class="productMaterial" value="${var.id}" />${var.materialName}</label>
							</c:forEach>
						</c:when>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td >应用</td>
				 <td>
					<c:choose>
						<c:when test="${not empty applicationList}">
							<c:forEach items="${applicationList}" var="var" varStatus="vs">
								<c:if test="${var.id < 5}">
									<label style="margin-right:50px;"><input type="checkbox" name="productApplication" class="productApplication" id="productApplication" value="${var.id}" <c:if test="${var.selected == 1}">checked</c:if>/>${var.applicationName}</label>
								</c:if>
							</c:forEach>
						</c:when>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>价格</td>
				<td>
					<input type="text" class="input_len_short" name="productNarrowPrice" id="productNarrowPrice" value="${entity.productNarrowPrice}" /><label>元/米</label>
					<input type="hidden" class="input_len_short" name="productWidePrice" id="productWidePrice" value="0" />		
				</td>
			</tr>
			<tr>
				<td >成份</td>
				<td><input type="text" class="input_len_mid" name="productComponent" id="productComponent" value="${entity.productComponent}" maxlength="32" /><label></label></td>
			</tr>
			<tr>
				<td >每平方克重</td>
				<td><input type="text" class="input_len_mid" name="productUnitWeight" id="productUnitWeight" value="${entity.productUnitWeight}" maxlength="32" /><label>g/m<sup>2</sup>± 5%</label></td>
			</tr>
			<tr>
				<td >幅宽</td>
				<td>
					<input type="text" class="input_len_short" name="productNarrowWidth" id="productNarrowWidth" value="${entity.productNarrowWidth}" /><label>cm ± 3cm</label>		
					<input type="hidden" class="input_len_short" name="productWideWidth" id="productWideWidth" value="0" />
				</td>
			</tr>
			<tr>
				<td >水洗标志</td>
			 	<td >
					<c:choose>
						<c:when test="${not empty washingMethodList}">
							<c:forEach items="${washingMethodList}" var="var" varStatus="vs">
								<label style="width:220px; display:inline-block; margin-top:15px;">
									<input type="checkbox" name="washingMethod" class="washingMethod" value="${var.id}"  <c:if test="${var.selected == 1}">checked</c:if>/>
									<img style="width:50px;height:30px; vertical-align:middle" src="<%=basePath%>${var.washingMethodIcon}"/>
								</label>
							</c:forEach>
						</c:when>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td >花型尺寸</td>
				<td>
					<span>H.</span><input type="text" class="input_len_short" name="patternHorizontalSize" id="patternHorizontalSize" value="${entity.patternHorizontalSize}" /><span>cm</span>
					<span style="width:30px;display:inline-block;"></span>
					<span>V.</span><input type="text" class="input_len_short" name="patternVerticalSize" id="patternVerticalSize" value="${entity.patternVerticalSize}" /><span>cm</span>
				</td>
			</tr>
			<tr>
				<td >原产地</td>
				<td><input type="text" name="productSourceArea" id="productSourceArea" value="${entity.productSourceArea}" class="input_len_long"/></td>
			</tr>
<!-- 			</table>
			<table id="table_report2" class="table noline"> -->
		<tr class="td_img_height">
				<td >花型图</td>
				<td>
					<span>
					<a href="javascript:void(0)" class="newspic">
						<c:if test="${entity.patternPicture1 != null && entity.patternPicture1 != ''}"><img class="img21" src="<%=basePath%>${entity.patternPicture1}"/></c:if>
						<c:if test="${entity.patternPicture1 == null || entity.patternPicture1 == ''}"><img class="img21" src="<%=basePath%>static/img/selectimg400_200.png"/></c:if>
					</a>
					<input type="file" id="patternPicture1" name="patternPicture1" onchange="showPic(this)"  style="display: none"/>
					<span class="span21">1600px*800px</span>
				<!-- </td>
				<td> -->
					</span>
					<span>
					<a href="javascript:void(0)" class="newspic">
						<c:if test="${entity.patternPicture2 != null && entity.patternPicture2 != ''}"><img class="img11" src="<%=basePath%>${entity.patternPicture2}"/></c:if>
						<c:if test="${entity.patternPicture2 == null || entity.patternPicture2 == ''}"><img class="img11" src="<%=basePath%>static/img/selectimg200_200.png"/></c:if>
					</a>
					<input type="file" id="patternPicture2" name="patternPicture2" onchange="showPic(this)"  style="display: none"/>
					<span class="span11" >800px*800px</span>
					</span>
				</td>
			</tr>
		<tr class="td_img_height">
				<td >质地图</td>
				<td>
					<span>
					<a href="javascript:void(0)" class="newspic">
						<c:if test="${entity.qualityPicture1 != null && entity.qualityPicture1 != ''}"><img class="img21" src="<%=basePath%>${entity.qualityPicture1}"/></c:if>
						<c:if test="${entity.qualityPicture1 == null || entity.qualityPicture1 == ''}"><img class="img21"  src="<%=basePath%>static/img/selectimg400_200.png"/></c:if>
					</a>
					<input type="file" id="qualityPicture1" name="qualityPicture1" onchange="showPic(this)"  style="display: none"/>
					<span class="span21" >1600px*800px</span>
			<!-- 	</td>
				<td> -->
				</span>
				<span>
					<a href="javascript:void(0)" class="newspic">
						<c:if test="${entity.qualityPicture2 != null && entity.qualityPicture2 != ''}"><img class="img21"  src="<%=basePath%>${entity.qualityPicture2}"/></c:if>
						<c:if test="${entity.qualityPicture2 == null || entity.qualityPicture2 == ''}"><img class="img21"  src="<%=basePath%>static/img/selectimg400_200.png"/></c:if>
					</a>
					<input type="file" id="qualityPicture2" name="qualityPicture2" onchange="showPic(this)"  style="display:none"/>
					<span class="span21">400px*200px</span>
				<!-- </td>
				<td> -->
				</span>
				<span>
					<a href="javascript:void(0)" class="newspic">
						<c:if test="${entity.qualityPicture3 != null && entity.qualityPicture3 != ''}"><img class="img11"  src="<%=basePath%>${entity.qualityPicture3}"/></c:if>
						<c:if test="${entity.qualityPicture3 == null || entity.qualityPicture3 == ''}"><img class="img11"   src="<%=basePath%>static/img/selectimg200_200.png"/></c:if>
					</a>
					<input type="file" id="qualityPicture3" name="qualityPicture3" onchange="showPic(this)"  style="display: none"/>
					<span class="span11">800px*800px</span>
				<!-- </td>
				<td> -->
				</span>
				<span>
					<a href="javascript:void(0)" class="newspic">
						<c:if test="${entity.qualityPicture4 != null && entity.qualityPicture4 != ''}"><img class="img11"  src="<%=basePath%>${entity.qualityPicture4}"/></c:if>
						<c:if test="${entity.qualityPicture4 == null || entity.qualityPicture4 == ''}"><img class="img11"   src="<%=basePath%>static/img/selectimg200_200.png"/></c:if>
					</a>
					<input type="file" id="qualityPicture4" name="qualityPicture4" onchange="showPic(this)"  style="display: none"/>
					<span class="span11">400px*400px</span>
				</span>
				</td>
			</tr>
			<tr class="td_img_height">
				<td >搭配方案 </td>
				<td>
					<div  class="oDiv" id="addMatchSchedule">
						<img  class="img21" src="<%=basePath%>static/img/selectimg400_200.png"/>
					</div>
					<div id="matchFrame" style="height:70px;display:inline-block;">
						<c:choose>
						<c:when test="${not empty matchItemList}">
							<c:forEach items="${matchItemList}" var="var" varStatus="vs">								
								<div class="oDiv">
									<input type="hidden" name="productMatchId" value="${var.matchProductId}"></input>
									<img class="img21" src = "<%=basePath%>${var.matchProductIcon}"></img>
					 			    <span>${var.matchProductName}</span>
								</div>																		
							</c:forEach>
						</c:when>
						</c:choose>
					</div>
					
				</td> 
			</tr>
			<tr class="td_img_height">
				<td >质检报告:</td>
				<td>
					<div class="oDiv">
					<a href="javascript:void(0)" class="newspic">
						<c:if test="${entity.qualityControlReport != null && entity.qualityControlReport != ''}"><img class="img21"   src="<%=basePath%>${entity.qualityControlReport}"/></c:if>
						<c:if test="${entity.qualityControlReport == null || entity.qualityControlReport == ''}"><img class="img21"   src="<%=basePath%>static/img/selectimg400_200.png"/></c:if>
					</a>
					<input type="file" id="qualityControlReport" name="qualityControlReport" onchange="showPic(this)"  style="display: none"/>
					</div>
				</td>
			</tr>
			<tr>
				<td id="successMessage" style="text-align: center;display:none;color:#045e9f" colspan="10">
					保存成功
				</td>
			</tr>
			<tr>
				<td style="text-align: center;" colspan="10">
					<c:if test="${entity.id==null}">	<button  class="submitBtn" type="submit" >添加上传</button></c:if>
					<c:if test="${entity.id!=null}"><button  class="submitBtn" type="submit" >确认</button></c:if>

				</td>
			</tr>
		</table>
		</div>
		
	</form>
<script type="text/javascript">

	//进入添加搭配方案页面
	$("#addMatchSchedule").click(function(){
		window.open('<%=basePath%>product/goAddMatchSchdule.do?',"新增","width=500,height=480,screenX=400,screenY=100")
	})
	//将选择方案页面的数据获取到，并展示在编辑页面
	function showAddMatchSchedule(idList,iconList,matchProductNameList){
			var matchFrame =$("#matchFrame");
			var len = idList.length;
			for(var i=0;i<len; i++){
				var id = idList[i];
				var src = iconList[i];
				var matchProductName = matchProductNameList[i];
				var  oDiv = $("<div  class='oDiv'></div>");
			   var inputId=$("<input type='hidden' name='productMatchId' value='"+id+"'></input>");
			   var imgIcon=$("<img  class='img21' src = '<%=basePath%>"+src+"'></img>"); 
			   var span=$("<span style='position:absolute;top:60px;left:10px;'>"+matchProductName+"</span>");
			   inputId.appendTo(oDiv);
	 		   imgIcon.appendTo(oDiv); 
	 		   span.appendTo(oDiv); 
	 		  oDiv.appendTo(matchFrame); 
			}
	}
	//编辑状态下展示单选框被选中数据-工艺
	var craftId = $("#craftId").val();
	if(craftId.length>0){
		$('input[name="productCraft"]').each(function(){  
			if(this.value ==craftId){
				$(this).attr("checked",true);
			}
		});  		
	}
	//编辑状态下展示单选框被选中数据-材质
	var materialId = $("#materialId").val();
	if(materialId.length>0){
		$('input[name="productMaterial"]').each(function(){  
			if(this.value ==materialId){
				$(this).attr("checked",true);
			}
		});  		
	}
/* 	alert($("#pdColorIdres").val());
	var a = new Array($("#pdColorIdres").val()); 
	alert(a); */
	
	//保存
	function save(target){	

		if($("#productName").val()==""){
			alert("请输入品名");
			return false;
		}
		//包装颜色参数
		var colorArray=new Array();  
		$('input[name="productColor"]:checked').each(function(){  
			colorArray.push($(this).val());//向数组中添加元素  
		});  
		if(colorArray.length ==0 ){
			alert("请选择颜色"); 
			return false;
		}
		var colorStr=colorArray.join(',');//将数组元素连接起来以构建一个字符串  
		$("#productColorList").val(colorStr);
		//包装风格
		var styleArray=new Array();  
		$('input[name="productStyle"]:checked').each(function(){  
			styleArray.push($(this).val());//向数组中添加元素  
		});  
		if(styleArray.length ==0 ){
			alert("请选择风格")
			return false;
		}
		var styleStr=styleArray.join(',');//将数组元素连接起来以构建一个字符串  
		$("#productStyleList").val(styleStr);
		//工艺	
		var craftChosen = false;
		var craftChosen= $('input:radio[name="productCraft"]:checked').val();
		if(craftChosen==null){
		 alert("请选择工艺");
		    return false;
		} 
		//材质	
		var materialChosen = false;
		var materialChosen= $('input:radio[name="productMaterial"]:checked').val();
		if(materialChosen==null){
		 alert("请选择材质");
		    return false;
		} 
		//包装应用
		var applicationArray=new Array();  
		$('input[name="productApplication"]:checked').each(function(){  
			applicationArray.push($(this).val());//向数组中添加元素  
		});  
		if(applicationArray.length ==0 ){
			alert("请选择应用")
			return false;
		}
		var applicationStr=applicationArray.join(',');//将数组元素连接起来以构建一个字符串  
		$("#productApplicationList").val(applicationStr);
		//单幅价格，宽幅价格，产品成分，每平方克重，单幅宽度，宽幅宽度
		if($("#productNarrowPrice").val() =="" ){
			alert("请输入单幅价格");
			return false;
		}
		if($("#productWidePrice").val() =="" ){
			alert("请输入宽幅价格");
			return false;
		}
		if($("#productComponent").val() =="" ){
			alert("请输入产品成分");
			return false;
		}
		if($("#productUnitWeight").val() =="" ){
			alert("请输入每平方克重");
			return false;
		}
		if($("#productNarrowWidth").val() =="" ){
			alert("请输入单幅宽度");
			return false;
		}
		if($("#productWideWidth").val() =="" ){
			alert("请输入宽幅宽度");
			return false;
		}
		//包装水洗标志
		var washingMethodArray=new Array();  
		$('input[name="washingMethod"]:checked').each(function(){  
			washingMethodArray.push($(this).val());//向数组中添加元素  
		});  
		if(washingMethodArray.length ==0 ){
			alert("请选择水洗标志");
			return false;
		}
		var washingMethodStr=washingMethodArray.join(',');//将数组元素连接起来以构建一个字符串  
		$("#washingMethodList").val(washingMethodStr);
		//花型尺寸-水平，花型尺寸-垂直，原产地
		if($("#patternHorizontalSize").val() =="" ){
			alert("请输入花型尺寸-水平");
			return false;
		}
		if($("#patternVerticalSize").val() =="" ){
			alert("请输入花型尺寸-垂直");
			return false;
		}
		if($("#productSourceArea").val() =="" ){
			alert("请输入原产地");
			return false;
		}
		//包装搭配方案
		var productMatchIdArray=new Array();  
		$('input[name="productMatchId"]').each(function(){  
			productMatchIdArray.push($(this).val());//向数组中添加元素  
		});  
		var productMatchIdStr=productMatchIdArray.join(',');//将数组元素连接起来以构建一个字符串  
		$("#productMatchIdList").val(productMatchIdStr);
		//质检图
/* 		if($("#qualityControlReport").val() =="" ){
			alert("请上传质检图");
			return false;
		} */
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
		$(this).next('input').click();
	});
	
	function showPic(data) {
		if (data.files && data.files[0]) {
			var reader = new FileReader();
 			reader.onload = function(evt) {
/* 			var $img = #(data).prev().children('img');
			$img.css("src",evt.target.result);
	 			data.previousSbiling.childNodes[1].src = evt.target.result;  */
 				data.parentNode.childNodes[1].childNodes[1].src = evt.target.result; 
			}
			reader.readAsDataURL(data.files[0]);
		}
	}
</script>
</body>
</html>