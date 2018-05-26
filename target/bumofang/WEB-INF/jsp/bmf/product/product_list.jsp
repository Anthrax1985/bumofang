
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
	<link rel="stylesheet" href="static/css/product_list.css" />
	<style type="text/css">
		table select{width:80px; }
		table label{margin-left:30px; }
		.img_big{width:200px; hegiht:200px;display:none;position:absolute;left:75px;top:25px;z-index:10;}
		.td_ellipsis{white-space:nowrap;overflow:hidden;text-overflow:ellipsis;width:60px;}
		.showMatchItem{width:440px; position:absolute;left:-450px;top:50px;display:none;background:#E8E8E8;border-radius:10px;z-index:2;}
		.oDiv{width:100px; height:60px; position:relative; float:left;margin:20px;}
		.oDivTitle{font-size:30px; font-weight:bold;text-align:left;padding:20px 0 0 40px;}
		#table_report{width:100%;}
		table thead tr th{width:140px;}
	 </style>
<script type="text/javascript">
$(function(){
     $(".img_show").hover(function(){  
    		$(this).next('img').show();	
        }, function(){  
        	$(this).next('img').hide();
        }   
    ) ;  
     $(".color_show").hover(function(){  
    	 $(".color_show").children(".colorIcon").empty().hide();
   		var oDiv = $(this).children(".colorIcon");
   		var productId = $(this).children("input").val();
		var ajaxObject = {};
		ajaxObject.url = "<%=basePath%>product/colorIcon/list";
		ajaxObject.data = {productId:productId};
		ajaxObject.success = function(data){
			var len = data.data.length;		
			for(var i=0;i<len; i++){
				var src = data.data[i].color_icon;
			   var imgIcon=$("<img  style='width:60px; height:20px;margin-right:10px;' src = '<%=basePath%>"+src+"'></img>");  
	 		   imgIcon.appendTo(oDiv); 
			}
			oDiv.show();
		};
		ajaxObject.fail = function(result){
			 bootbox.alert("未知错误");
		};			
		ajaxPost(ajaxObject);   		
        } , function(){  
        	 $(this).children(".colorIcon").empty().hide();
        }    
    ) ;  
})

</script>
	</head>
<body>
		
<div class="container-fluid" id="main-container">

<div id="page-content" class="clearfix">
						
  <div class="row-fluid">

	<div class="row-fluid">
			<div>
				<p style="float:left; margin-left:30px; font-size:20px; font-weight:bold;">产品中心</p>
				<div style="float:right;">
					<c:if test="${QX.edit == 1 }">
						<button class="btn btn-mini btn-info" onclick="open_win_add();" style="padding:0 30px; margin:3px 0 0 30px;">添加产品</button>
					</c:if>
					<c:if test="${QX.del == 1 }">
						<!-- <button class="btn btn-mini btn-info"  style="padding:0 30px; margin:3px 0 0 30px;" title="删除产品">删除产品</button> -->
<button class="btn btn-mini btn-info" onclick="return deleteProduct();" style="padding:0 30px; margin:3px 0 0 30px;" title="删除产品">删除产品</button>					
					</c:if>


					<!-- <button class="btn btn-mini btn-info" onclick="return deleteProduct();" style="padding:0 30px; margin:3px 0 0 30px;" title="删除产品">删除产品</button> -->
				</div> 
			</div>
			<div style="clear:both;"></div>    
			<hr/>   			
			<!-- 检索  -->
			<form action="product/list.do" method="post" name="Form" id="Form">
			<table>
				<tr>
					<td>
						<span class="input-icon">
						<label>品名</label>
							<input autocomplete="off" style="width:100px;" type="text" name="query_key" value="${pd.query_key}"  />
						<!-- 	<i class="ace-icon fa fa-search nav-search-icon"></i> -->
						</span>
					</td>   
					<td>
						<label >颜色</label>
						<select name="query_color" >
							<%--<c:if test="${pd.query_color != null && pd.query_color !=''}">--%>
								<%--<option class="productColor" value="${pd.query_color}">${pd.query_color}</option>--%>
							<%--</c:if>--%>
							<%--<c:if test="${pd.query_color == null || pd.query_color ==''}">--%>
								<option  class="productColor" value="">全部</option>
							<%--</c:if>--%>
							<c:choose>
								<c:when test="${not empty colorList}">
									<c:forEach items="${colorList}" var="var" varStatus="vs">
										<c:if test="${pd.query_color == var.colorName}">
											<option name="productColor" class="productColor" selected="true" value="${var.colorName}">${var.colorName}</option>
										</c:if>
										<c:if test="${pd.query_color != var.colorName}">
											<option name="productColor" class="productColor" value="${var.colorName}">${var.colorName}</option>
										</c:if>
									</c:forEach>
								</c:when>
							</c:choose>
						</select>
					</td>
					<td>
						<label >风格</label>
						<select name="query_style" >
							<%--<c:if test="${pd.query_style != null && pd.query_style !=''}">--%>
								<%--<option class="productStyle" value="${pd.query_style}">${pd.query_style}</option>--%>
							<%--</c:if>--%>
							<%--<c:if test="${pd.query_style == null || pd.query_style ==''}">--%>
								<option  class="productStyle" value="">全部</option>
							<%--</c:if>--%>
							<c:choose>
								<c:when test="${not empty styleList}">
									<c:forEach items="${styleList}" var="var" varStatus="vs">
										<c:if test="${pd.query_style == var.styleName}">
											<option  class="productStyle" selected="true" value="${var.styleName}">${var.styleName}</option>
										</c:if>
										<c:if test="${pd.query_style != var.styleName}">
											<option  class="productStyle" value="${var.styleName}">${var.styleName}</option>
										</c:if>
									</c:forEach>
								</c:when>
							</c:choose>
						</select>
					</td>
					<td>
						<label >工艺</label>
						<select name="query_craft" >
							<%--<c:if test="${pd.query_craft != null && pd.query_craft !=''}">--%>
								<%--<option class="productCraft" value="${pd.query_craft}">${pd.query_craft}</option>--%>
							<%--</c:if>--%>
							<%--<c:if test="${pd.query_craft == null || pd.query_craft ==''}">--%>
								<option  class="productCraft" value="">全部</option>
							<%--</c:if>--%>
							<c:choose>
								<c:when test="${not empty craftList}">
									<c:forEach items="${craftList}" var="var" varStatus="vs">
										<c:if test="${pd.query_craft == var.craftName}">
											<option  class="productCraft" selected="true" value="${var.craftName}">${var.craftName}</option>
										</c:if>
										<c:if test="${pd.query_craft != var.craftName}">
											<option  class="productCraft" value="${var.craftName}">${var.craftName}</option>
										</c:if>
									</c:forEach>
								</c:when>
							</c:choose>
						</select>
					</td>
					<td>
						<label >材质</label>
						<select name="query_material" >
							<%--<c:if test="${pd.query_material != null && pd.query_material !=''}">--%>
								<%--<option class="productMaterial" value="${pd.query_material}">${pd.query_material}</option>--%>
							<%--</c:if>--%>
							<%--<c:if test="${pd.query_material == null || pd.query_material ==''}">--%>
								<option  class="productMaterial" value="">全部</option>
							<%--</c:if>--%>
							<c:choose>
								<c:when test="${not empty materialList}">
									<c:forEach items="${materialList}" var="var" varStatus="vs">
										<c:if test="${pd.query_material == var.materialName}">
											<option  class="productMaterial" selected="true" value="${var.materialName}">${var.materialName}</option>
										</c:if>
										<c:if test="${pd.query_material != var.materialName}">
											<option  class="productMaterial" value="${var.materialName}">${var.materialName}</option>
										</c:if>
									</c:forEach>
								</c:when>
							</c:choose>
						</select>
					</td>
					<td>
						<label >应用</label>
						<select name="query_application" >
							<%--<c:if test="${pd.query_application != null && pd.query_application !=''}">--%>
								<%--<option class="productApplication" value="${pd.query_application}">${pd.query_application}</option>--%>
							<%--</c:if>--%>
							<%--<c:if test="${pd.query_application == null || pd.query_application ==''}">--%>
								<option  class="productApplication" value="">全部</option>
							<%--</c:if>--%>
							<c:choose>
								<c:when test="${not empty applicationList}">
									<c:forEach items="${applicationList}" var="var" varStatus="vs">
										<c:if test="${pd.query_application == var.applicationName}">
											<option  class="productApplication" selected="true" value="${var.applicationName}">${var.applicationName}</option>
										</c:if>
										<c:if test="${pd.query_application != var.applicationName}">
											<option  class="productApplication" value="${var.applicationName}">${var.applicationName}</option>
										</c:if>
									</c:forEach>
								</c:when>
							</c:choose>
						</select>
					</td>
					<td><button class="btn btn-mini btn-info" onclick="search();" style="padding:0 30px; margin:3px 0 0 30px;" title="检索">搜  索</button></td>
					<!-- <td>	<button class="btn btn-mini btn-info" onclick="open_win_add();">添加产品</button></td>
					<td><button class="btn btn-mini btn-info" onclick="return deleteProduct();" style="padding:0 30px; margin:3px 0 0 30px;" title="删除产品">删除产品</button></td> -->
					<!-- <td style="vertical-align:top;"><a class="btn btn-mini btn-light" onclick="searchReset();" title="重置"><i class="fa form-btn-icon fa-refresh"></i></a></td> -->
				</tr>
			</table>
			<!-- 检索  -->
		
		
			<table id="table_report" class="table table-hover" style="table-layout:fixed;">				
				<thead>
					<tr>
						<th>
						<label><input type="checkbox" id="zcheckbox" class="ace" /><span class="lbl"></span></label>
						</th>
						<th>品名</th>
						<th>花型图</th>
						<th>质地图</th>
						<th >颜色</th>
						<th >风格</th>					
						<th>工艺</th>
						<th>材质</th>
						<th >应用</th>					
						<th>单幅价格</th>					
						<!-- <th>宽幅价格</th>	 -->				
						<th style="width: 156px">操作</th>
					</tr>
				</thead>
										
				<tbody>
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${varList == null}">
				    	<tr class="main_info">
							<td colspan="100" class="center" >请输入搜索条件</td>
						</tr>
					</c:when>
					<c:when test="${not empty varList}">
						<c:if test="${QX.cha == 1 }">
						<c:forEach items="${varList}" var="var" varStatus="vs">
							<tr>
								<td >
									<label>
										<input type='checkbox' name='ids' class="ace" value="${var.id}" />
										<span class="lbl"></span>
									</label>
									<input type='hidden' name='reqProcuctName'  value="${var.productName}" />
								</td>
									<td><a class=" " title="产品详情"  onclick="productDetail('${var.id}');">${var.productName}</a></td>
<%-- 										<td><a class="btn btn-mini" title="产品详情" onclick="productDetial('${var.id}');">${var.productName}</a></td> --%>
									<td style="position:relative">
											<img class="img_show" style="width:50px; hegiht:50px;" src="<%=basePath%>static/img/bmf_logo.png"/>
											<img  class="img_big" src="<%=basePath%>static/img/bmf_logo.png"/>
									</td>
									<td style="position:relative">
										<img  class="img_show" style="width:50px; hegiht:50px;" src="${productDomainUrl}${var.productName}-Q2.jpg"/>
										<img class="img_big"  src="${productDomainUrl}${var.productName}-Q2.jpg"/>
									</td>																				
									<td class="td_ellipsis color_show" style="position:relative;overflow:visible;">
										<input type="hidden" value="${var.id}">
										<span style="height:20px;display:block;overflow:hidden;">${var.colorStrList}</span>
										<div class="colorIcon" style="display:none;width:200px;z-index:10;position:absolute;left:30px;top:40px;"></div>									
									</td> 
									<td class="td_ellipsis" title="${var.styleStrList}">${var.styleStrList}</td>
									<td>${var.craftStr}</td>
									<td>${var.materialStr}</td>
									<td class="td_ellipsis" title="${var.applicationStrList}">${var.applicationStrList}</td>
									<td>${var.productNarrowPrice}</td>
									<!--  <td>${var.productWidePrice}</td>-->
								<td style="width: 70px;" class="center">
									<div>
									
										<c:if test="${QX.edit != 1 && QX.del != 1 }">
										<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="fa fa-lock" title="无权限"></i></span>
										</c:if>
										<div class="inline position-relative">
											<c:if test="${QX.edit == 1 }">
												<input type='hidden' name='reqProcuctName'  value="${var.id}" />
												<a class='btn btn-mini btn-info matchClick' title="搭配方案"  >搭配方案</a>
												<div class="showMatchItem" ></div> 
											</c:if>
											<c:if test="${QX.edit == 1 }">
												<a class='btn btn-mini btn-info' title="编辑" onclick="edit('${var.id}');">编辑产品</a>
											</c:if>


											<button class="btn btn-mini btn-info add_member_cart"  onclick="return add_member_cart('${var.id}')" style="padding:0 32px; margin:3px 0 0 3px;" title="添加购物车">添加购物车</button>

<%-- 											<c:if test="${QX.del == 1 }">
												<a class='btn btn-mini btn-danger' title="删除产品" onclick="toBlackList('${var.id}','${var.productName}');">删除产品</a>
											</c:if> --%>
<%-- 											<c:if test="${QX.del == 1 }">
												<a class='btn btn-mini btn-danger' title="删除" onclick="del('${var.id}');"><i class="fa fa-trash"></i></a>
											</c:if> --%>
										</div>
									</div>
								</td>
							</tr>
						
						</c:forEach>
						</c:if>
						<c:if test="${QX.cha == 0 }">
							<tr>
								<td colspan="100" class="center">您无权查看</td>
							</tr>
						</c:if>
					</c:when>
					<c:otherwise>
						<tr class="main_info">
							<td colspan="100" class="center" >没有相关数据</td>
						</tr>
					</c:otherwise>
				</c:choose>
					
				
				</tbody>
			</table>
			
			<div class="page-header position-relative">
					<div class="pagination" style="float:right;margin:0px auto;width:600px;">${page.pageStr}</div>
			</div>

		</form>
	</div>
	<!-- PAGE CONTENT ENDS HERE -->
  </div><!--/row-->
	
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
		
<script type="text/javascript">
		
	$(top.hangge());
	
	//检索
	function search(){
		top.jzts();
		$("#Form").submit();
	}
	
	//新增
	function add(){
		 BootstrapDialog.show({cssClass:'four-row-dialog',
            message: $('<div></div>').load('<%=basePath%>product/goAdd.do'),
            title: '新增'
          });
	}

	
	//弹出新增或编辑界面
	function open_win_add() {
		location.href = '<%=basePath%>product/goAdd.do?';
		<%-- window.open('<%=basePath%>product/goAdd.do?',"_self","新增","width=1024,height=880,screenX=400,screenY=100") --%>
	}
	
	//修改
	function edit(id){
		location.href = '<%=basePath%>product/goEdit/'+id;
	}


	//产品详情
	function productDetail(id){
		location.href = '<%=basePath%>productdetail/goProductDetail/'+id;
	}
	function deleteProduct(){
		var chk_value =[]; 
		var productNameList=[];
		$('input[name="ids"]:checked').each(function(){
			chk_value.push($(this).val());
			productNameList.push($(this).parent().next().val());
		});
		if(chk_value.length!=1 ){
			alert("请选择一个产品"); 
			return false;
		}
		var id = chk_value[0];
		var product_name= productNameList[0];		

		var url = "<%=basePath%>product/toBlackList/"+id;

		bootbox.confirm("是否确认删除产品"+product_name+"?", function(result) {
			if(!result){return;}
			var ajaxObject = {};
			ajaxObject.url = url;
			ajaxObject.data = {};
			ajaxObject.success = function(result){
				location.reload();
			};
			ajaxObject.fail = function(result){
				 bootbox.alert("拉入黑名单失败");
			};			
			ajaxPost(ajaxObject);
		});
		return false;
		//alert(url);
	}

	function get_selected_products(){
		var chk_value =[];
		var productNameList=[];
		$('input[name="ids"]:checked').each(function(){
			chk_value.push($(this).val());
			productNameList.push($(this).parent().next().val());
		});

		return {
			chk_value:chk_value,
			productNameList:productNameList
		};
	}

	//添加购物车
    function add_member_cart(id){
		// var selList =get_selected_products();
		// if(selList.chk_value.length < 1 ){
		// 	alert("请选择一个产品");
		// 	return false;
		// }
		var pruduct_id = [];
        pruduct_id.push(id);
		var strHTML = " <div class='pop_dialog'>     " +
				"		<div>															" +
       			"			<select id='sel_member_id'>		 						" +
       			<c:forEach items="${memberList}" var="var" varStatus="vs">
				"			<option value='${var["ID"]}'>${var["NICKNAME"]}</option>    " +
				</c:forEach>				
				"			</select>													" +
				"		</div>															" +
				//"		<div>															" +
				//"			<select id='sel_widthkind_id'>		 					" +
				//"				<option value='0'>单幅价格</option>    			" +
            	//"				<option value='1'>宽幅价格</option>    			" +
				//"			</select>													" +
				//"		</div>															" +
			 	"		<div id='choose_number'>									" +
				"			<p class='p_num'>											" +
				"				<span class='sy_minus' id='sy_minus_gid1'>-</span> 		" +
				"				<input class='sy_num' id='sy_num_gid1' type='text' name='number1' value='1' /> " +
				"				<span class='sy_plus' id='sy_plus_gid1'>+</span>				" +
				"			</p>																" +
			 	"		</div>															" +

				"		<div>															" +
				"			<button class='add_member_cart_confirm'>确定</button>		" +
				"		</div>															" +
				"		</div>															";
        layer.open({
            type: 1,
            title: '添加购物车',
            shadeClose: true,
            shade: 0.8,
            area: ['300px', '260px'],
            content: strHTML
        });

        $(document)
        .off('click.add_member_cart_confirm')
        .on(
        	'click.add_member_cart_confirm', 
        	'.add_member_cart_confirm',
        	function(){
	        	// var selList =get_selected_products();
	        	var member_id = $('#sel_member_id').val();
               // var option_id = $('#sel_widthkind_id').val();
                 var option_id = 0;
                var total_num = $('#sy_num_gid1').val();

	        	var ajaxObject = {};
				ajaxObject.url = "<%=basePath%>membercart/rest/addList";
				ajaxObject.data = {
	        		// product_list : selList.chk_value,
                    product_list : pruduct_id,
	        		member_id    : member_id,
                    option_id    :option_id,
                    total_num      :total_num
	        	};
				ajaxObject.success = function(result){
					layer.msg('添加购物车成功');
					setTimeout(function(){
						location.reload();
					}, 1000);				
				};
				ajaxObject.fail = function(result){
					 bootbox.alert("添加购物车失败");				 
				};			
				ajaxPost(ajaxObject);
        });

		return false;
    }



	//拉入黑名单
		function toBlackList(id,productName){
		
			var url = "<%=basePath%>product/toBlackList/"+id;
			bootbox.confirm("确定要将产品"+productName+"拉入黑名单吗?", function(result) {
				if(!result){return;}
				var ajaxObject = {};
				ajaxObject.url = url;
				ajaxObject.data = {};
				ajaxObject.success = function(result){
					location.reload();
				};
				ajaxObject.fail = function(result){
					 bootbox.alert("拉入黑名单失败");
				};			
				ajaxPost(ajaxObject);
			});
		}
		$ (document).off("click.sy_minus").on("click.sy_minus", ".sy_minus", function (){
			var number = parseInt($(".sy_num").val());
			if(number !=undefined && number !=null && number > 1){
			    number -=1;
			}
            $(".sy_num").val(number);
		});

    $ (document).off("click.sy_plus").on("click.sy_plus", ".sy_plus", function (){
            var number = parseInt($(".sy_num").val());
            number +=1;
            $(".sy_num").val(number);
		});
		//展示产品方案
		$(".matchClick").hover(function(){
			var productId = $(this).prev().val();
			//alert(productId)
			var showMatchItem =  $(this).next();
			showMatchItem.css("display","block");
				var url = "<%=basePath%>product/matchMethod/list";
					var ajaxObject = {};
					ajaxObject.url = url;
					ajaxObject.data = {productId:productId};
					ajaxObject.success = function(data){
						var oDivTitle = $("<div  class='oDivTitle'>搭配方案</div>");
						showMatchItem.html("");
						oDivTitle.appendTo(showMatchItem); 
						var len = data.data.length;
						for(var i=0;i<len; i++){
							var id =data.data[i].matchProductId;
							var  oDiv = $("<div  class='oDiv'></div>");
							var matchProductName =data.data[i].matchProductName;
							var matchProductIcon =data.data[i].matchProductIcon;
						   var span=$("<span style='position:absolute;top:60px;left:10px;'>"+matchProductName+"</span>");
						   var imgIcon=$("<img  style='width:100px;height:50px;' src = '<%=basePath%>"+matchProductIcon+"'></img>");  
				 		   imgIcon.appendTo(oDiv); 
						   span.appendTo(oDiv);
				 		  oDiv.appendTo(showMatchItem); 
						}
					};
					ajaxObject.fail = function(result){						
					};	
					ajaxPost(ajaxObject);
		},function(){
			var showMatchItem =  $(this).next();
			showMatchItem.html("");
			showMatchItem.css("display","none");
			
		})
		
</script>
		
<script type="text/javascript">
		
	$(function() {
		
		//下拉框
		$(".chzn-select").chosen(); 
		$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
		
		//日期框
		$('.date-picker').datepicker();
		
		//复选框
		$('table th input:checkbox').on('click' , function(){
			var that = this;
			$(this).closest('table').find('tr > td:first-child input:checkbox')
			.each(function(){
				this.checked = that.checked;
				$(this).closest('tr').toggleClass('selected');
			});
		});
		
	});
	
	//批量操作
	function makeAll(){
		var url = '<%=basePath%>product/deleteBatch';
		ajaxDeleteBatch(url);
	}
	
	function searchReset(){
		$("#Form").find(':input').not(':button, :submit, :reset').val('').removeAttr('checked').removeAttr('selected');
	}
</script>
		
	</body>
</html>