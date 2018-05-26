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
			<form action="scene/list_scene.do" method="post" name="Form" id="Form">

		
		
			<table id="table_report" class="table table-hover">
				
				<thead>
					<tr>
						<th>
						<label><input type="checkbox" id="zcheckbox" class="ace" /><span class="lbl"></span></label>
						</th>
						<th>场景ID</th>
						<th>场景名称</th>
						<th>物件名称</th>
						<th>场景图</th>
						<th>透明遮罩图</th>
						<th style="text-align:center">创建时间</th>
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

						<c:forEach items="${varList}" var="var" varStatus="vs">
							<tr>
								<td style="width: 30px;">
									<label><input type='checkbox' name='ids' class="ace" value="${var['id']}" /><span class="lbl"></span></label>
								</td>
										<td>${var['id']}</td>
										<td>${var['name']}</td>
										<td>${var['mask']}</td>
										<td class="pic" data-name="${var['mask']}" data-url="${var['url']}">
											<img style="height:30px" src='${var['url']}'/>
										</td>
										<td class="pic" data-name="${var['mask']}" data-url="${var['maskUrl']}" style="background-color:#000">
											<img style="height:30px" src='${var['maskUrl']}'/>
										</td>


								<td style="width: 250px;" class="center">
									<div>
										<c:if test="${QX.edit != 1 && QX.del != 1 }">
										<%--<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="fa fa-lock" title="无权限"></i></span>--%>
										</c:if>
										<div class="inline position-relative">
											<%--<a class='btn btn-mini btn-danger' title="删除" onclick="deleteScene('${var.ID}','${var.NICKNAME}');">删除</a>--%>
										</div>
										${var['strSceneUploadTime']}
									</div>
								</td>
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

        function show_pic_one(name, url){
            var str = "";
            var timestamp = Date.parse(new Date());

            var isMaskPic = false;
            if(url.indexOf('.png') >= 0){
                isMaskPic = true;
			}

            str += "<li>";
            str += "<div class='title'><span>" + name + "</span></div>";
			str += "<div class='picItem' ><img src='" + url  + "'/></div>";


            str += "</li>";

            var strHTML = " <div class='pop_dialog'>     " +
                "		<ul style='text-align:center; padding-top:40px'>															" +
                str +
                "		</ul>															" +
                "		</div>															";

			if(isMaskPic) {
                strHTML = strHTML.replace("<img", "<img style='background-color:#000' ");
			}
            layer.open({
                type: 1,
                title: '显示图片',
                shadeClose: true,
                shade: 0.8,
                area: ['600px', '450px'],
                content: strHTML
            });

            return false;
        }
		
		</script>
		
		<script type="text/javascript">
		
		$(function() {

		    $('.pic').off().on('click', function(){
                var name = $(this).attr('data-name');
		        var url = $(this).attr('data-url');
                show_pic_one(name, url);
			});
			
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


		function deleteScene(id, nickname){
            layer.alert('是否删除',
			{
				closeBtn: 1    // 是否显示关闭按钮
				,anim: 1 //动画类型
				,btn: ['确定','取消'] //按钮
				,icon: 2    // icon
				,yes:function(){
					$.ajax({
						type: "POST",
						url: '<%=basePath%>sence/deleteScene.do?tm='+new Date().getTime(),
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

