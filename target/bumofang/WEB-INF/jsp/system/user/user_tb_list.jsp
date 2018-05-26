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

	<!-- jsp文件头和头部 -->
	<%@ include file="../admin/top.jsp"%> 
	
	</head> 

<body>
		
<div class="container-fluid" id="main-container">



<div id="page-content" class="clearfix">
						
  <div class="row-fluid">

	
<div class="row-fluid">
	
		<table id="table_report" class="table table-hover">
			
			<thead>
				<tr>
					<th>
						<label><input type="checkbox" class="ace" id="zcheckbox" /><span class="lbl"></span></label>
					</th>
					<th>编号</th>
					<th>用户名</th>
					<th>姓名</th>
					<th>职位</th>
					<th><i class="fa fa-envelope"></i>邮箱</th>
					<th>操作</th>
				</tr>
			</thead>
								
			<tbody>
				
		<!-- 开始循环 -->	
		<c:choose>
			<c:when test="${not empty userList}">
				<c:if test="${QX.cha == 1 }">
				<c:forEach items="${userList}" var="user" varStatus="vs">
				<tr>
					<td class='center' style="width: 30px;">
						<c:if test="${user.USERNAME != 'admin'}"><label><input type='checkbox' class="ace" name='ids' value="${user.USER_ID }"/><span class="lbl"></span></label></c:if>
						<c:if test="${user.USERNAME == 'admin'}"><label><input type='checkbox' class="ace" disabled="disabled" /><span class="lbl"></span></label></c:if>
					</td>
					<td>${user.NUMBER }</td>
					<td><a>${user.USERNAME }</a></td>
					<td>${user.NAME }</td>
					<td>${user.ROLE_NAME }</td>
					<td>${user.EMAIL }</td>
					<td>
						<div class='hidden-phone visible-desktop btn-group'>
										
							<c:if test="${QX.edit != 1 && QX.del != 1 }">
							<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="fa fa-lock" title="无权限"></i></span>
							</c:if>
							
							<c:if test="${QX.edit == 1 }">
							<c:if test="${user.USERNAME != 'admin'}"><a class='btn btn-mini btn-info' title="编辑" onclick="editUser('${user.USER_ID }');"><i class='fa fa-edit'></i></a></c:if>
							<c:if test="${user.USERNAME == 'admin'}"><a class='btn btn-mini btn-info' title="不能编辑"><i class='fa fa-edit'></i></a></c:if>
							</c:if>
							<c:choose>
								<c:when test="${user.USERNAME=='admin'}"></c:when>
								<c:otherwise>
									<c:if test="${QX.del == 1 }">
									 <a class='btn btn-mini btn-danger' title="删除" onclick="delUser('${user.USER_ID }','${user.USERNAME }');"><i class='fa fa-trash'></i></a>
									</c:if>
								</c:otherwise>
							</c:choose>
						</div>
					</td>
				</tr>
				
				</c:forEach>
				</c:if>
				
				<c:if test="${QX.cha == 0 }">
					<tr>
						<td colspan="10" class="center">您无权查看</td>
					</tr>
				</c:if>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<td colspan="10" class="center">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>				
				
			</tbody>
		</table>
	
</div>
 
 		<div class="page-header position-relative">
		<table style="width:100%;">
			<tr>
				<td style="vertical-align:top;">
					<c:if test="${QX.add == 1 }">
					<a class="btn btn-small btn-success" onclick="add();">新增</a>
					</c:if>
					<c:if test="${QX.del == 1 }">
					<a class="btn btn-small btn-danger" onclick="delAll();" title="批量删除" ><i class='fa fa-trash'></i></a>
					</c:if>
				</td>
			</tr>
		</table>
		</div>
 
 
	<!-- PAGE CONTENT ENDS HERE -->
  </div><!--/row-->
	
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
		
		<!-- 返回顶部  -->
		
		
		
		<script type="text/javascript">
		$(top.hangge());
		$(function() {
			var oTable1 = $('#table_report').dataTable( {
			"aoColumns": [
		      { "bSortable": false },
		      null, null,null, null, null,
			  { "bSortable": false }
			] } );
			
			
			$('table th input:checkbox').on('click' , function(){
				var that = this;
				$(this).closest('table').find('tr > td:first-child input:checkbox')
				.each(function(){
					this.checked = that.checked;
					$(this).closest('tr').toggleClass('selected');
				});
					
			});
			$('[data-rel=tooltip]').tooltip();
		})
		
		//检索
		function search(){
			top.jzts();
			$("#userForm").submit();
		}
		
		//新增
		function add(){
			 BootstrapDialog.show({cssClass:'one-row-dialog',
	            message: $('<div></div>').load('<%=basePath%>user/goAddU.do'),
	            title: '新增'
	          });
		}
		
		//修改
		function editUser(user_id){
			BootstrapDialog.show({cssClass:'one-row-dialog',
	            message: $('<div></div>').load('<%=basePath%>user/goEditU.do?USER_ID='+user_id),
	            title: '资料'
	          });
		}
		
		//删除
		function delUser(userId,msg){
			bootbox.confirm("确定要删除["+msg+"]吗?", function(result) {
				if(result) {
					var url = "<%=basePath%>user/deleteU.do?USER_ID="+userId+"&tm="+new Date().getTime();
					$.get(url,function(data){
						if(data=="success"){
							document.location.reload();
							top.jzts();
						}
					});
				}
			});
		}
		
		
		//批量删除
		function delAll(){
			bootbox.confirm("确定要删除选中的数据吗?", function(result) {
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
						$.ajax({
							type: "POST",
							url: '<%=basePath%>user/deleteAllU.do?tm='+new Date().getTime(),
					    	data: {USER_IDS:str},
							dataType:'json',
							//beforeSend: validateData,
							cache: false,
							success: function(data){
								 $.each(data.list, function(i, list){
									 document.location.reload();
									 top.jzts();
								 });
							}
						});
						
					}
				}
			});
		}
		
		</script>
		
		<script type="text/javascript">
		$(function() {
			//日期框
			$('.date-picker').datepicker();
		});
		</script>
		
	</body>
</html>

