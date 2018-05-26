
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
	<link rel="stylesheet" href="static/css/list_3d.css" />
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

});

</script>
	</head>
<body>
		
<div class="container-fluid" id="main-container">

<div id="page-content" class="clearfix">
						
  <div class="row-fluid">

	<div class="row-fluid">


			<!-- 检索  -->
			<form action="scene/list_3d.do" method="post" name="Form" id="Form">
			品名：<input type="text" id="query_key" name="query_key" value="${pd.query_key}">
			场景名：<input type="text" id="scene_name" name="scene_name" value="${pd.scene_name}">
			<input type="submit" id="search" name="search" value="查询">
			<br/>
		
			<table id="table_report" class="table table-hover" style="table-layout:fixed;">				
				<thead>
					<tr>
						<th>
						<label><input type="checkbox" id="zcheckbox" class="ace" /><span class="lbl"></span></label>
						</th>
						<th>品名</th>

						<c:forEach items="${sceneList}" var="scene" varStatus="vs">
							<th>${scene.mask}</th>
						</c:forEach>

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

						<c:forEach items="${varList}" var="var" varStatus="vs">
							<tr>
								<td >
									<label>
										<input type='checkbox' name='ids' class="ace" value="${var.id}" />
										<span class="lbl"></span>
									</label>
									<input type='hidden' name='reqProcuctName'  value="${var.productName}" />
								</td>

								<td>${var.productName}</td>

								<c:forEach items="${sceneList}" var="scene" varStatus="vs">
									<td class="d3d_pic ${scene['mask']}_${var.productName}" data-name="${scene['mask']}_${var.productName}">-</td>
								</c:forEach>


								<td style="width: 70px;" class="center">
									<div>
									
										<c:if test="${QX.edit != 1 && QX.del != 1 }">
										<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="fa fa-lock" title="无权限"></i></span>
										</c:if>
										<div class="inline position-relative">
											<a class="show_pic" product_name="${var.productName}">查看</a>

										</div>
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
					<div class="pagination" style="float:right;margin:0px auto;width:600px;">${page.pageStr}</div>
			</div>

		</form>
	</div>
	<!-- PAGE CONTENT ENDS HERE -->
  </div><!--/row-->
	
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
		
<script type="text/javascript">
	var images = {};
	$(document).ready(function(){
	    if(top.hangge != undefined){
            $(top.hangge());
		}

        $('.d3d_pic').off().on('click', function(){
			var strName =  $(this).attr('data-name');
            show_pic_one(strName);
		});

        function setUploadStatus(scene_name, product_name, create_time){
	        var strName = scene_name + "_" + product_name;
	        var td = $('.' + strName);
            td.html(create_time.substr(0, 19));
            td.off().on('click', function(){
                show_pic_one(strName);
			});

            if(images[product_name] == undefined){
                images[product_name] = [];
			}

            images[product_name].push(scene_name);
        }

        <c:forEach items="${list_3d}" var="item" varStatus="vs">
        	setUploadStatus("${item['mask_name']}", "${item['product_name']}", "${item['create_time']}");
        </c:forEach>
	});
	
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

	$(document).off("click.show_pic").on("click.show_pic", ".show_pic", function(){
	    var product_name = $(this).attr('product_name');
	    show_pic(product_name);
	});

	//添加购物车
    function show_pic(product_name){
        var arr = images[product_name];
        var str = "";

        var domain = "${domainUrl}";
        var timestamp = Date.parse(new Date());
		for(var idx=0; idx<arr.length; idx++){
		    var pic_name = arr[idx] + "_" + product_name;
		    var url = domain + pic_name + ".png?tm=" + timestamp + "&imageMogr2/thumbnail/!400x";
            str += "<li>";
            str += "<div class='title'><span>" + pic_name + "</span></div>";
		    str += "<div class='picItem'><img src='" + url  + "'/></div>";
            str += "</li>";
		}
		var strHTML = " <div class='pop_dialog'>     " +
				"		<ul>															" +
            	str +
				"		</ul>															" +
				"		</div>															";
        layer.open({
            type: 1,
            title: '显示图片',
            shadeClose: true,
            shade: 0.8,
            area: ['1000px', '500px'],
            content: strHTML
        });

		return false;
    }


    function show_pic_one(name){
        var str = "";

        var domain = "${domainUrl}";
        var timestamp = Date.parse(new Date());

		var url = domain + name + ".png?tm=" + timestamp + "&imageMogr2/thumbnail/!400x";
		str += "<li>";
		str += "<div class='title'><span>" + name + "</span></div>";
		str += "<div class='picItem'><img src='" + url  + "'/></div>";
		str += "</li>";

        var strHTML = " <div class='pop_dialog'>     " +
            "		<ul>															" +
            str +
            "		</ul>															" +
            "		</div>															";
        layer.open({
            type: 1,
            title: '显示图片',
            shadeClose: true,
            shade: 0.8,
            area: ['1000px', '500px'],
            content: strHTML
        });

        return false;
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
		var url = '<%=basePath%>product/deleteBatch';
		ajaxDeleteBatch(url);
	}
	
	function searchReset(){
		$("#Form").find(':input').not(':button, :submit, :reset').val('').removeAttr('checked').removeAttr('selected');
	}
</script>
		
	</body>
</html>