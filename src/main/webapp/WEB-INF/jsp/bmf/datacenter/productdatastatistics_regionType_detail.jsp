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
	<style>
	body,*,#pagination{background:#d7d7d7;}
	#main-container{width:1000px;margin: 0px auto;}
 	#pagination{margin:10px 0px 0px 170px;} 
		table,tr,td,th{border:1px solid black;background:white; font-size:16px;}
		th,td{width:170px;height:20px; text-align:center;}
	</style>
	</head>
<body>
		
<div class="container-fluid" id="main-container">

		<!-- 检索  -->
			<!-- <form action="productdatastatistics/tableList.do" method="post" name="Form" id="Form"> -->
			<form action="productdatastatistics/regionTypeDetailList/${year}" method="post" name="Form" id="Form">	
			<!-- <table id="table_report" class="table table-hover"> -->			
			<p style="font-size:20px;">${year}年浏览用户类型统计详情</p>
			<table id="table_report" >			
				<thead>
					<tr>
						<th>省份</th>
						<th>浏览次数(次)</th>
						<th>下单次数(次)</th>
					    <th>转化率</th>
					    <th>下单米数(米)</th>
					    <th>下单金额(元)</th>
				    </tr>
				</thead>
										
			<tbody>	
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty tableDetailList}">

						<c:forEach items="${tableDetailList}" var="var" varStatus="vs">
							<tr>
								<td>${var.name}</td>
								<td>${var.value}</td>
								<td>0</td>
								<td>0%</td>
								<td>0</td>
								<td>0</td>
							</tr>
					
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr class="main_info">
							<td colspan="100" class="center" >没有相关数据</td>
						</tr>
					</c:otherwise>
				</c:choose>				
				</tbody>
			</table>
			

			<div class="pagination" id="pagination">${page.pageStr}</div>
<%-- 		<table style="width:100%;">
			<tr>
				<td style="vertical-align:top;"><div class="pagination" style="padding-top: 0px;margin: 0px auto;">${page.pageStr}</div></td>
			</tr>
		</table> --%>
		</form>
	</div>
	<!-- PAGE CONTENT ENDS HERE -->

		
<script type="text/javascript">
		
	$(top.hangge());
	
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
	
	//批量操作
	function makeAll(){
		var url = '<%=basePath%>productdatastatistics/deleteBatch';
		ajaxDeleteBatch(url);
	}
	
	function searchReset(){
		$("#Form").find(':input').not(':button, :submit, :reset').val('').removeAttr('checked').removeAttr('selected');
	}
</script>
		
	</body>
</html>