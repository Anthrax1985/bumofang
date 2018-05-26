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
<style type="text/css">  
	table select{width:80px; }
	table label{margin-left:30px; }
	.img_big{width:200px; hegiht:200px;display:none;position:absolute;left:75px;top:25px;z-index:10;}	
	.td_ellipsis{white-space:nowrap;overflow:hidden;text-overflow:ellipsis;width:60px;}
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
	
			<!-- 检索  -->
			<form action="product/goAddMatchSchdule.do" method="post" name="Form" id="Form">
<table>
				<tr>
					<td>
						<span class="input-icon">
						<label>品名</label>
							<input autocomplete="off" style="width:80px;" type="text" name="query_key" value="${pd.query_key}"  />
						<!-- 	<i class="ace-icon fa fa-search nav-search-icon"></i> -->
						</span>
					</td>   
					<td>
						<label >颜色</label>
						<select name="query_color" >
							<c:if test="${pd.query_color != null && pd.query_color !=''}">
								<option class="productColor" value="${pd.query_color}">${pd.query_color}</option>
							</c:if>							
							<c:if test="${pd.query_color == null || pd.query_color ==''}">
								<option  class="productColor" value="">全部</option>
							</c:if>							
							<c:choose>
								<c:when test="${not empty colorList}">
									<c:forEach items="${colorList}" var="var" varStatus="vs">
										<option name="productColor" class="productColor" value="${var.colorName}">${var.colorName}</option>
									</c:forEach>
								</c:when>
							</c:choose>
						</select>
					</td>
					<td>
						<label >风格</label>
						<select name="query_style" >
							<c:if test="${pd.query_style != null && pd.query_style !=''}">
								<option class="productStyle" value="${pd.query_style}">${pd.query_style}</option>
							</c:if>							
							<c:if test="${pd.query_style == null || pd.query_style ==''}">
								<option  class="productStyle" value="">全部</option>
							</c:if>		
							<c:choose>
								<c:when test="${not empty styleList}">
									<c:forEach items="${styleList}" var="var" varStatus="vs">
										<option  class="productStyle" value="${var.styleName}">${var.styleName}</option>
									</c:forEach>
								</c:when>
							</c:choose>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<label >工艺</label>
						<select name="query_craft" >
							<c:if test="${pd.query_craft != null && pd.query_craft !=''}">
								<option class="productCraft" value="${pd.query_craft}">${pd.query_craft}</option>
							</c:if>							
							<c:if test="${pd.query_craft == null || pd.query_craft ==''}">
								<option  class="productCraft" value="">全部</option>
							</c:if>		
							<c:choose>
								<c:when test="${not empty craftList}">
									<c:forEach items="${craftList}" var="var" varStatus="vs">
										<option  class="productCraft" value="${var.craftName}">${var.craftName}</option>
									</c:forEach>
								</c:when>
							</c:choose>
						</select>
					</td>
					<td>
						<label >材质</label>
						<select name="query_material" >
							<c:if test="${pd.query_material != null && pd.query_material !=''}">
								<option class="productMaterial" value="${pd.query_material}">${pd.query_material}</option>
							</c:if>							
							<c:if test="${pd.query_material == null || pd.query_material ==''}">
								<option  class="productMaterial" value="">全部</option>
							</c:if>		
							<c:choose>
								<c:when test="${not empty materialList}">
									<c:forEach items="${materialList}" var="var" varStatus="vs">
										<option  class="productMaterial" value="${var.materialName}">${var.materialName}</option>
									</c:forEach>
								</c:when>
							</c:choose>
						</select>
					</td>
					<td>
						<label >应用</label>
						<select name="query_application" >
							<c:if test="${pd.query_application != null && pd.query_application !=''}">
								<option class="productApplication" value="${pd.query_application}">${pd.query_application}</option>
							</c:if>							
							<c:if test="${pd.query_application == null || pd.query_application ==''}">
								<option  class="productApplication" value="">全部</option>
							</c:if>		
							<c:choose>
								<c:when test="${not empty applicationList}">
									<c:forEach items="${applicationList}" var="var" varStatus="vs">
										<option  class="productApplication" value="${var.applicationName}">${var.applicationName}</option>
									</c:forEach>
								</c:when>
							</c:choose>
						</select>
					</td>
					<td><button class="btn btn-mini btn-info" onclick="search();" style="padding:0 30px; margin:3px 0 0 30px;" title="检索">搜  索</button></td>
				</tr>
			</table>
			<table id="table_report" class="table table-hover" style="table-layout:fixed;">				
				<thead>
					<tr>
						<th>
						<label><input type="checkbox" id="zcheckbox" class="ace" /><span class="lbl"></span></label>
						</th>
						<th>品名</th>
						<th>花型图</th>
						<th>质地图</th>
						<th>颜色</th>
						<th>风格</th>					
						<th>工艺</th>
						<th>材质</th>
						<th>应用</th>			
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
								<td style="width: 30px;">
									<label><input type='checkbox' name='ids' class="ace" value="${var.id}" /><span class="lbl"></span></label>
									<input type='hidden'  class="iconSrc" value="${var.qualityPicture3}" />
									<input type='hidden'  class="matchProductName" value="${var.productName}" />
								</td>
								<td><a class=" " title="产品详情"  onclick="productDetail('${var.id}');">${var.productName}</a></td>
															<%-- 	<td><a class=" " title="产品详情" >${var.productName}</a></td> --%>
<%-- 										<td><a class="btn btn-mini" title="产品详情" onclick="productDetial('${var.id}');">${var.productName}</a></td> --%>
									<td style="position:relative">
											<img class="img_show" style="width:50px; hegiht:50px;" src="<%=basePath%>static/img/bmf_logo.png"/>
											<img  class="img_big" src="<%=basePath%>static/img/bmf_logo.png"/>
									</td>
									<td style="position:relative">
										<img  class="img_show" style="width:50px; hegiht:50px;" src="<%=basePath%>${var.qualityPicture3}"/>
										<img class="img_big"  src="<%=basePath%>${var.qualityPicture3}"/>
									</td>																				
									<td class="td_ellipsis color_show" style="position:relative;overflow:visible;">
										<input type="hidden" value="${var.id}">
										<span style="width:28px;height:20px;display:block;overflow:hidden;">${var.colorStrList}</span>
										<div class="colorIcon" style="display:none;width:200px;z-index:10;position:absolute;left:30px;top:40px;"></div>									
									</td> 
									<td  class="td_ellipsis" title="${var.styleStrList}">${var.styleStrList}</td>
									<td>${var.craftStr}</td>
									<td>${var.materialStr}</td>
									<td  class="td_ellipsis" title="${var.applicationStrList}">${var.applicationStrList}</td>
							</tr>						
						</c:forEach>
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
		<table style="width:100%;">
			<tr>
				<td style="vertical-align:top;">
					<a class="btn btn-small btn-info" onclick="addSchduleItem();" title="确定" >确定</a>			
				</td>
				<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
			</tr>
		</table>
		</div>
		</form>
	</div>
	<!-- PAGE CONTENT ENDS HERE -->
  </div><!--/row-->
	
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
		
<script type="text/javascript">
		
	$(top.hangge());
	//产品详情
	function productDetail(id){
		location.href = '<%=basePath%>productdetail/goProductDetail/'+id;
	}
	//检索
	function search(){
		top.jzts();
		$("#Form").submit();
	}	
	
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
	
	//添加搭配，并将数据传回编辑页面
		function addSchduleItem(){
			var idList = new Array();
			var iconList = new Array();
			var matchProductNameList = new Array();
			$("input:checked").each(function(){
				var id = $(this).val();
				idList.push(id);
				var src = $(this).parent().next().val();
				iconList.push(src);
				var matchProductName = $(this).parent().next().next().val();
				matchProductNameList.push(matchProductName);
			})
			if(idList.length == 0){
				alert("请选择搭配方案")
				return;
			}	
		  if (window.opener != null && !window.opener.closed) {
			  window.opener.showAddMatchSchedule(idList,iconList,matchProductNameList);
		  }  
	}
	
	//批量操作
<%-- 	function makeAll(){
		var url = '<%=basePath%>product/deleteBatch';
		ajaxDeleteBatch(url);
	} --%>
	
	function searchReset(){
		$("#Form").find(':input').not(':button, :submit, :reset').val('').removeAttr('checked').removeAttr('selected');
	}
</script>
		
	</body>
</html>