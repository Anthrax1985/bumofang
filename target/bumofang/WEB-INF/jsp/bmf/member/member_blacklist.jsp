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
	</head>
<body>
		
<div class="container-fluid" id="main-container">


<div id="page-content" class="clearfix">					
  <div class="row-fluid">
	<div class="row-fluid">	
			<!-- 检索  -->
			<form action="member/list.do" method="post" name="Form" id="Form">
<%-- 			<table>
				<tr>
					<td>
						<span class="input-icon">
							<input autocomplete="off" id="nav-search-input" type="text" name="QUERY_KEY" value="${pd.QUERY_KEY}" placeholder="这里输入关键词" />
							<i class="ace-icon fa fa-search nav-search-icon"></i>
						</span>
					</td>
					<td><input class="span10 date-picker" name="QUERY_TIME_START" id="QUERY_TIME_START" value="${pd.QUERY_TIME_START}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="开始日期"/></td>
					<td><input class="span10 date-picker" name="QUERY_TIME_END" id="QUERY_TIME_END" value="${pd.QUERY_TIME_END}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="结束日期"/></td>
					<td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();"  title="检索"><i class="fa form-btn-icon fa-search"></i></button></td>
					<td style="vertical-align:top;"><a class="btn btn-mini btn-light" onclick="searchReset();" title="重置"><i class="fa form-btn-icon fa-refresh"></i></a></td>
					<c:if test="${QX.cha == 1 }">
					<td style="vertical-align:top;"><a class="btn btn-mini btn-light" onclick="toExcel();" title="导出到EXCEL"><i class="fa form-btn-icon fa-download"></i></a></td>
					</c:if>
				</tr>
			</table> --%>
			<!-- 检索  -->		
			<table id="table_report" class="table table-hover">				
				<thead>
					<tr>
						<th>
						<label><input type="checkbox" id="zcheckbox" class="ace" /><span class="lbl"></span></label>
						</th>
						<th>用户ID</th>
						<th>用户名称</th>
						<th>用户类型</th>
						<th>手机号</th>
						<th>所在区域</th>
						<th>设限时间</th>
						<th>拉黑/激活原因</th>
						<th>用户状态</th>
						<th>操作</th>
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
									<label><input type='checkbox' name='ids' class="ace" value="${var.ID}" /><span class="lbl"></span></label>
								</td>
										<td>${var.ID}</td>
										<td>${var.NICKNAME}</td>
										<td>${var.PROFESSION_SUP}/${var.PROFESSION_SUB}</td>
										<td>${var.MOBILE}</td>
										<td>${var.ADDR_PROVINCE}/${var.ADDR_CITY}</td>
										<td>${var.UPDATE_TIME}</td>
										<td>${var.REASON}</td>
										<td>
											<c:if test="${var.DEL_FLAG == 1}">正常</c:if>
											<c:if test="${var.DEL_FLAG == 2}">禁用</c:if>
											<c:if test="${var.DEL_FLAG == 3}">待审核</c:if>
										</td>
								<td style="width: 70px;" class="center">
									<div>
									
										<c:if test="${QX.edit != 1 && QX.del != 1 }">
										<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="fa fa-lock" title="无权限"></i></span>
										</c:if>
										<div class="inline position-relative">
											<c:if test="${QX.edit == 1 && var.DEL_FLAG == 2}">
												<a class='btn btn-mini btn-info' title="激活账号" onclick="activate('${var.ID}');">激活</a>
											</c:if>
											<c:if test="${QX.del == 1 && var.DEL_FLAG == 3}">
												<a class='btn btn-mini btn-info' title="审核" onclick="audit('${var.ID}');">审核</a>
											</c:if>
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
		<table style="width:100%;">
			<tr>
				<td style="vertical-align:top;">
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
		
		//检索
		function search(){
			top.jzts();
			$("#Form").submit();
		}
		
		//新增
		function add(){
			 BootstrapDialog.show({cssClass:'two-row-dialog',
	            message: $('<div></div>').load('<%=basePath%>member/goAdd.do'),
	            title: '新增'
	          });
		}
		
		//修改
		function edit(id){
			BootstrapDialog.show({cssClass:'two-row-dialog',
	            message: $('<div></div>').load('<%=basePath%>member/goEdit.do?ID='+id),
	            title: '编辑'
	          });
		}
		//激活
		function activate(id){
			BootstrapDialog.show({cssClass:'two-row-dialog',
	            message: $('<div></div>').load('<%=basePath%>memberblack/goActivateMember.do?ID='+id),
	            title: '激活'
	          });
		}
		//激活
		function audit(id){
			BootstrapDialog.show({cssClass:'two-row-dialog',
	            message: $('<div></div>').load('<%=basePath%>memberblack/goAudit.do?ID='+id),
	            title: '审核'
	          });
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
		function makeAll(msg){
			bootbox.confirm(msg, function(result) {
				if(result) {
					var str = '';
					for(var i=0;i < document.getElementsByName('ids').length;i++)
					{
						  if(document.getElementsByName('ids')[i].checked){
						  	if(str=='') str += document.getElementsByName('ids')[i].value;
						  	else str += ',' + document.getElementsByName('ids')[i].value;
						  }
					}
					if(str==''){
						bootbox.dialog("您没有选择任何内容!", 
							[
							  {
								"label" : "关闭",
								"class" : "btn-small btn-success",
								"callback": function() {
									//Example.show("great success");
									}
								}
							 ]
						);
						
						$("#zcheckbox").tips({
							side:3,
				            msg:'点这里全选',
				            bg:'#AE81FF',
				            time:8
				        });
						
						return;
					}else{
						if(msg == '确定要删除选中的数据吗?'){
							top.jzts();
							$.ajax({
								type: "POST",
								url: '<%=basePath%>member/deleteAll.do?tm='+new Date().getTime(),
						    	data: {DATA_IDS:str},
								dataType:'json',
								//beforeSend: validateData,
								cache: false,
								success: function(data){
									 $.each(data.list, function(i, list){
											nextPage(${page.currentPage});
									 });
								}
							});
						}
					}
				}
			});
		}
		
		//导出excel
		function toExcel(){
			window.location.href='<%=basePath%>member/excel.do';
		}
		
		function searchReset(){
			$("#Form").find(':input').not(':button, :submit, :reset').val('').removeAttr('checked').removeAttr('selected');
		}
		</script>
		
	</body>
</html>

