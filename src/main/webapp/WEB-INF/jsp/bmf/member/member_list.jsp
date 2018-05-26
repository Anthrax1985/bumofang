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
		.td_gap{margin-right:50px;}
	</style>
	</head>
<body>
		
<div class="container-fluid" id="main-container">


<div id="page-content" class="clearfix">
						
  <div class="row-fluid">

	<div class="row-fluid">
	
			<!-- 检索  -->
			<form action="member/list.do" method="post" name="Form" id="Form">
	<!-- 		<table id="search_table">
				<tr >
					<td class="td_gap"> -->
						<span>用户名称</span>
						<span class="td_gap">
							<input autocomplete="off" id="nav-search-input" type="text" name="QUERY_KEY" value="${pd.QUERY_KEY}" />
						</span>
		<!-- 			</td>
					<td class="td_gap"> -->
						<span>用户类型</span>
						<select name="query_profession_sup"  class="td_gap">
							<option  value=""></option>
							<c:choose>
								<c:when test="${not empty professionSupList}">
									<c:forEach items="${professionSupList}" var="var" varStatus="vs">
										<option  value="${var.professionSupName}">${var.professionSupName}</option>
									</c:forEach>
									<option  value="其它">其它</option>
								</c:when>
							</c:choose>
						</select>
				<!-- 	</td>
					<td> -->
						<span>所在省</span>
						<select style="width:100px;" name="query_province"  class="query_province td_gap" >
							<option  value=""></option>
							<c:choose>
								<c:when test="${not empty provinceList}">
									<c:forEach items="${provinceList}" var="var" varStatus="vs">
										<option  value="${var.divisionName}">${var.divisionName}</option>
									</c:forEach>
								</c:when>
							</c:choose>
						</select>
			<!-- 		</td>
					<td> -->
						<span>所在市</span>
						<select style="width:100px;" name="query_city" class="query_city td_gap" >
						</select>
				<!-- 	</td>
					<td style="vertical-align:top;"> --><button class="btn btn-mini btn-info" onclick="search();"style="padding:0 30px;"  title="检索">搜索</button></td>
<%-- 					<td style="vertical-align:top;"><a class="btn btn-mini btn-light" onclick="searchReset();" title="重置"><i class="fa form-btn-icon fa-refresh"></i></a></td>
					<c:if test="${QX.cha == 1 }">
					<td style="vertical-align:top;"><a class="btn btn-mini btn-light" onclick="toExcel();" title="导出到EXCEL"><i class="fa form-btn-icon fa-download"></i></a></td>
					</c:if> --%>
			<!-- 	</tr>
			</table> -->
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
						<th>所属区域</th>
						<th>IDFA设备号</th>
						<%--<th>账户余额</th>--%>
						<th style="text-align:center">操作</th>
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
										<td><a title="${var.NICKNAME}" onclick="memberDetail('${var.ID}')">${var.NICKNAME}</a></td>
										<td>${var.PROFESSION_SUP}/${var.PROFESSION_SUB}</td>
										<td>${var.MOBILE}</td>
										<td>${var.ADDR_PROVINCE}/${var.ADDR_CITY}</td>
										<td>${var.IDFA}</td>
										<%--<td>1314.00</td>--%>
								<td style="width: 250px;" class="center">
									<div>
									
										<c:if test="${QX.edit != 1 && QX.del != 1 }">
										<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="fa fa-lock" title="无权限"></i></span>
										</c:if>
										<div class="inline position-relative">
											<c:if test="${QX.edit == 1 }">
												<a class='btn btn-mini btn-info' title="编辑" onclick="edit('${var.ID}');">编辑</a>
											</c:if>

											<a class='btn btn-mini btn-info' title="购物车" onclick="toMemberCart('${var.ID}','${var.NICKNAME}');">购物车</a>

											<c:if test="${QX.del == 1 }">
												<a class='btn btn-mini btn-danger' title="加入黑名单" onclick="toBlackList('${var.ID}','${var.NICKNAME}');">加入黑名单</a>
											</c:if>
											<a class='btn btn-mini btn-danger' title="删除" onclick="deleteMember('${var.ID}','${var.NICKNAME}');">删除</a>

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
<%-- 					<c:if test="${QX.add == 1 }">
					<a class="btn btn-small btn-success" onclick="add();">注册新用户</a>
					</c:if> --%>
<%-- 					<c:if test="${QX.del == 1 }">
					<a class="btn btn-small btn-danger" onclick="makeAll('确定要删除选中的数据吗?');" title="批量删除" ><i class='fa fa-trash'></i></a>
					</c:if> --%>
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
		
		//用户详情
		function memberDetail(id){
			location.href = '<%=basePath%>memberdetail/goMemberDetail/'+id;
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
		//拉黑
		function toBlackList(id){
			BootstrapDialog.show({cssClass:'two-row-dialog',
	            message: $('<div></div>').load('<%=basePath%>member/goBlackList.do?ID='+id),
	            title: '编辑'
	          });
		}
		//删除
<%-- 		function del(Id){
			bootbox.confirm("确定要删除吗?", function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>member/delete.do?ID="+Id+"&tm="+new Date().getTime();
					$.get(url,function(data){
						nextPage(${page.currentPage});
					});
				}
			});
		} --%>
		
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


		function toMemberCart(id, nickname){
			var menu_name = '购物车(' + nickname + ')';
			var menu_url = 'membercart/list.do?member_id=' + id;
			var tab_id = 'membercart_' + id;
			top.mainFrame.tabAddHandler(tab_id, menu_name, menu_url);
		}

		function deleteMember(id, nickname){
            layer.alert('是否删除',
			{
				closeBtn: 1    // 是否显示关闭按钮
				,anim: 1 //动画类型
				,btn: ['确定','取消'] //按钮
				,icon: 2    // icon
				,yes:function(){
					$.ajax({
						type: "POST",
						url: '<%=basePath%>member/deleteAll.do?tm='+new Date().getTime(),
						data: {DATA_IDS:id},
						dataType:'json',
						cache: false,
						success: function(data){
							layer.msg('删除成功');
							setTimeout(function(){
								location.reload();
							}, 1500);
						}
					});
				},
				btn2:function(){
					layer.closeAll();
				}
            });
        }

		</script>
	<script type="text/javascript">
/* 选择省份，并展示城市 */
var addrProvince=null;
$(".query_province").change(function(){
	addrProvince = $(this).val()
	var ajaxObject={
			url:"chinadivision/rest/goSelectCity",
			data:{
				divisionName:addrProvince
				},
			success:function (data) { 
					var str="<option >请选择城市</option>";
					for(var i=0;i<data.data.length;i++){
						str=str+"<option value="+data.data[i].divisionName+">"+data.data[i].divisionName+"</option>";
					}
					$(".query_city").html(str);
				 }, 
			fail:function(){console.log("fail")},
			error:function(){console.log("error")},
	}
	ajaxPost(ajaxObject);			
})
</script>
	</body>
</html>

