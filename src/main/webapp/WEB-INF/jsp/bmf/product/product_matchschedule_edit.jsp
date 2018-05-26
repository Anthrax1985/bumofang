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
		<%@ include file="../../system/admin/top.jsp"%> 
<%-- 		<script type="text/javascript" src="<%=basePath%>static/js/multiselect.min.js"></script> --%>
		<script src="static/js/myjs/table-define.js?v=3"></script>
		<%-- <link href="<%=basePath%>/static/css/bootstrap.min.css?v=1" rel="stylesheet" /> --%>
<script type="text/javascript">
	$(function () {
		initTableInfo();
	});
	//保存
	function save(){
		if($("#storeHouseId").val()==""){
			popupTip('storeHouseId', '请选择仓库');
			return false;
		}
		if($("#bankAccountNum").val()==""){
			popupTip('bankAccountNum', '请付款账户');
			return false;
		}
		
		var target = $("#editForm");
		var action = 'saveWithItem';
		var url = 'storeorder/' + action;
		var data = serializeArray(target);
		
		var selectDataList = $("#selectProduct").bootstrapTable('getData', false);
		var itemArray = [];
		for(var i=0;i<selectDataList.length;i++){
			var oneData = selectDataList[i];
			var pushData = {};
			pushData.productId = oneData.productId;
			pushData.productName = oneData.productName;
			pushData.productSkuId = oneData.productSkuId;
			pushData.productSkuName = oneData.skuListName;
			var amountValue = $("#amount" + i).val();
			pushData.amount = parseInt(amountValue);
			pushData.unitPrice = $("#unitPrice" + i).val();
			
			itemArray.push(pushData);
		}
		data.orderItemList = itemArray;
		data.storeName = getSelectedText("storeHouseId");
		data.supplierName = getSelectedText("supplierCode");
		data.bankName = getSelectedText("bankAccountNum");
		debugger;
		delete data["btSelectItem"];
		
		var ajaxObject = {};
		ajaxObject.success = function(result){
			$("#editForm")[0].reset();
			$('#selectProduct').bootstrapTable('removeAll');
			bootbox.alert("保存成功");
		};
		ajaxObject.data = data;
		ajaxObject.url = url;
		
		ajaxPost(ajaxObject);
		return false;
	}
</script>
	</head>
<body>
	<form action="product/saveProduct.do" name="editForm" id="editForm" method="post" enctype="multipart/form-data" onsubmit="return save();">
		<input type="hidden" name="id" id="id" value="${entity.id}"/>
		<input type="hidden" name="productColorList" id="productColorList" value=""/>
		<div id="zhongxin" style="padding-top:20px;"></div>
		<div class="panel-body" style="margin-top:-33px;padding-bottom:0px;width:50%;float:left;">
	         <form id="formSearch" class="form-horizontal">
	             <div class="form-group" >
	                 <div class="col-sm-3" >
	                     <input type="text" class="form-control" style="width:180px;"  id="search_key" placeholder="输入名称或编号">
	                 </div>
	                 
	                 <div class="col-sm-4" style="text-align:left;padding-left:50px;">
	                     <select id="productCategorySelect" style="width:140px;">
						</select>
	                 </div>
	                 <div class="col-sm-4" style="text-align:left;">
	                     <button type="button" id="btn_query" class="btn btn-primary">查询</button>
	                 </div>
	             </div>
	         </form>
        	<table id="productList" ></table>
	    </div>
	    	    <div class="panel-body" style="padding-bottom:0px;width:50%;float:left;margin-top:-33px;">
	    	<div id="toolbar" class="btn-group">
			    <button id="del" class="btn btn-default" title="删除">
			        <i class="glyphicon glyphicon-minus"></i> 删除
			    </button>
			</div>
	    
	        <table id="selectProduct" ></table>
	    </div>
		<table id="table_report" class="table noline">
			
			<tr>
				<td id="successMessage" style="text-align: center;display:none;color:#045e9f" colspan="10">
					保存成功
				</td>
			</tr>
			<tr>
				<td style="text-align: center;" colspan="10">
					<!-- <a id="saveBtn" class="btn btn-primary" style="width:100px" onclick="save(this);">保存</a> -->
					<button id="saveBtn" class="btn btn-primary" type="submit" style="width:100px;margin-left:15px;">保存</button>
				<!-- <button type="submit" id="saveBtn" class="btn btn-primary" style="width:100px;" value="保存">保存</button> -->
				</td>
			</tr>
		</table>

	</form>
<script type="text/javascript">	
</script>
</body>
</html>