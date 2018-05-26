
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
                <p style="float:left; margin-left:30px; font-size:20px; font-weight:bold;">购物车列表</p>
                <div style="float:right;">
                    
                   


                    <%--<c:if test="${QX.del == 1 }">--%>
                        <%--<!-- <button class="btn btn-mini btn-info"  style="padding:0 30px; margin:3px 0 0 30px;" title="删除产品">删除产品</button> -->--%>
<%--<button class="btn btn-mini btn-info" onclick="return addOrder();" style="padding:0 30px; margin:3px 0 0 30px;" title="添加订单">添加订单</button>--%>
                    <%--</c:if>--%>
                    
                </div> 
            </div>
            <div style="clear:both;"></div>    
            <hr/>               
            
        
        
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
                        <th>价格种类</th>
                        <th>数量</th>
                        <%--<th>操作</th>--%>
                    </tr>
                </thead>
                                        
                <tbody>
                    
                <!-- 开始循环 -->   
                <c:choose>
                    <c:when test="${varList == null}">
                        <tr class="main_info">
                            <td colspan="100" class="center" >暂无数据</td>
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
<%--                                        <td><a class="btn btn-mini" title="产品详情" onclick="productDetial('${var.id}');">${var.productName}</a></td> --%>
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
                                        <span style="height:20px;display:block;overflow:hidden;">${var.colorStrList}</span>
                                        <div class="colorIcon" style="display:none;width:200px;z-index:10;position:absolute;left:30px;top:40px;"></div>                                 
                                    </td> 
                                    <td class="td_ellipsis" title="${var.styleStrList}">${var.styleStrList}</td>
                                    <td>${var.craftStr}</td>
                                    <td>${var.materialStr}</td>
                                    <td class="" title="${var.applicationStrList}">${var.applicationStrList}</td>
                            <%--<c:forEach items="${cart_map.get(var.id)}" var="cart" varStatus="vs">--%>
                                    <%--<td class="">${cart.gerLong("option_id")}</td>--%>
                                    <%--<td class="">${cart.gerLong("total_num")}</td>--%>
                            <%--</c:forEach>--%>
                            <c:if test="${var.getOptionID(cart_map) == 0}">
                                    <td class="product_price" title="">${var.getProductNarrowPrice()}</td>
                                </c:if>
                                <c:if test="${var.getOptionID(cart_map) == 1}">
                                    <td class="product_price" title="">${var.getProductWidePrice()}</td>
                                </c:if>
                               <!--  <c:if test="${var.getOptionID(cart_map) == 0}">
                                    <td class="product_price" title="">单幅/${var.getProductNarrowPrice()}</td>
                                </c:if>
                                <c:if test="${var.getOptionID(cart_map) == 1}">
                                    <td class="product_price" title="">宽幅/${var.getProductWidePrice()}</td>
                                </c:if> -->
                                    <td class="product_num" title="">${var.getTotalNum(cart_map)}</td>
                                <%--<td style="width: 70px; " class="center">--%>
                                    <%--<div>--%>
                                    <%----%>
                                        <%--<c:if test="${QX.edit != 1 && QX.del != 1 }">--%>
                                        <%--<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="fa fa-lock" title="无权限"></i></span>--%>
                                        <%--</c:if>--%>
                                        <%--<div class="inline position-relative">--%>
                                            <%----%>
                                            <%--<c:if test="${QX.edit == 1 }">--%>
                                                <%--<a class='btn btn-mini btn-info' title="删除" onclick="deleteProduct('${var.id}', '${member_id}');">删除</a>--%>
                                            <%--</c:if>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</td>--%>
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
    
    $(document).ready(function(){
    	if(top != undefined){
    		top.hangge();
    	}
    });
    
    //检索
    // function search(){
    //     top.jzts();
    //     $("#Form").submit();
    // }
    
    //新增
    <%--function add(){--%>
         <%--BootstrapDialog.show({cssClass:'four-row-dialog',--%>
            <%--message: $('<div></div>').load('<%=basePath%>product/goAdd.do'),--%>
            <%--title: '新增'--%>
          <%--});--%>
    <%--}--%>

    
    //弹出新增或编辑界面
    <%--function open_win_add() {--%>
        <%--location.href = '<%=basePath%>product/goAdd.do?';--%>
        <%--&lt;%&ndash; window.open('<%=basePath%>product/goAdd.do?',"_self","新增","width=1024,height=880,screenX=400,screenY=100") &ndash;%&gt;--%>
    <%--}--%>
    
    //修改
    <%--function edit(id){--%>
        <%--location.href = '<%=basePath%>product/goEdit/'+id;--%>
    <%--}--%>


    //产品详情
    function productDetail(id){
        location.href = '<%=basePath%>productdetail/goProductDetail/'+id;
    }
    function deleteProduct(product_id, member_id){
        var url = "<%=basePath%>membercart/rest/deleteMemberCart/";

        bootbox.confirm("是否确认从购物车删除这个产品？", function(result) {
            if(!result){return;}
            var ajaxObject = {};
            ajaxObject.url = url;
            ajaxObject.data = {
                product_id:product_id,
                member_id:member_id
            };
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
        var product_msgs =[];
        $('input[name="ids"]:checked').each(function(){
            var product_msg ={};
            // product_msg.push({"chk_value":$(this).val()});
            // product_msg.push({"productNameList":$(this).parent().next().val()});
            // product_msg.push({"productPriceList":$(this).parent().parent().parent().find(".product_price").text()});
            // product_msg.push({"productNumList":$(this).parent().parent().parent().find(".product_num").text()});
            product_msg["chk_value"] = $(this).val();
            product_msg["productNameList"] = $(this).parent().next().val();
            product_msg["productPriceList"] = $(this).parent().parent().parent().find(".product_price").text();
            product_msg["productNumList"] = $(this).parent().parent().parent().find(".product_num").text();
            product_msgs.push(product_msg);
        });

        return product_msgs;
    }

    //添加订单
    function addOrder(){
        var selList =get_selected_products();       
        if(selList.length < 1 ){
            alert("请选择一个产品");
            return false;
        }
        var productHTML = "";
        for(i=0 ;i<selList.length;i++){
            var chk_value = selList[i]["chk_value"];
            var productName = selList[i]["productNameList"];
            var productPrice = selList[i]["productPriceList"];
            var productPrice;
            var productNum = selList[i]["productNumList"];
            var msg_html = "<div class='fl w-100 o_item' data-id='" + chk_value +"'>"+
           		"<div class='fl w-25'>"+productName+"</div>"+
                "<div class='fl w-25' id='order_type' >"+productPrice+"</div>"+
                "<div class='fl w-25'><font>米数</font><input style='width:80px; height:25px' class='meter' type='text' name='meter' value='1' /> </div>"+
                "<div class='p_num fl w-25'>" +
                "<span class='sy_minus'>-</span>"+
                "<input class='sy_num' type='text' name='number1' value='"+productNum+"' /> " +
                "<span class='sy_plus'>+</span>				" +
                "</div></div>";
            productHTML+=msg_html;
        }
        var strHTML = "<div class='pop_dialog'>" 
			        +productHTML
			        +"<div style='margin-top: 10px'><font>收货人姓名：</font>"
			        +"<input style='width: 250px; class = 'user_name' id = 'user_name' type='text' name='username' placeholder='请输入收货人姓名' /></div>"
			                            
			        +"<div style='margin-top: 10px'><font>收货人电话：</font>"
			        +"<input style='width: 250px;' class = 'user_mobile' id = 'user_mobile' type='text' name='usermobile' placeholder='请输入收货人电话' /></div>"
			                            
			        +"<div style='margin-top: 10px'><font>详&nbsp;细&nbsp;地&nbsp;址：</font>"
			        +"<input style='width: 250px;' class = 'user_address' id = 'user_address' type='text' name='useraddress' placeholder='请输入收货的详情地址' /></div>"
			        +"<div style='padding-top:10px;'> " 
			        +"<button class='add_order add_order_confirm'>确定支付</button>"
			        +"</div>" 
	        +"</div>";
        
        layer.open({
            type: 1,
            title: '添加订单',
            shadeClose: true,
            shade: 0.8,
            area: ['500px', '400px'],
            content: strHTML
        });
        $ (document).off("click.sy_minus").on("click.sy_minus", ".sy_minus", function (){
            var number = parseInt($(this).next().val());
            if(number !=undefined && number !=null && number > 1){
                number -=1;
            }
            $(this).next().val(number);
        });
               
        $ (document).off("click.sy_plus").on("click.sy_plus", ".sy_plus", function (){
            var number = parseInt($(this).prev().val());
            number +=1;
            $(this).prev().val(number);
        });
        $(document).off('click.add_order').on('click.add_order', '.add_confirm', function(){
            var selList =get_selected_products();
            var member_id = $('#sel_member_id').val();

            var ajaxObject = {};
            ajaxObject.url = "<%=basePath%>membercart/rest/addList";
            ajaxObject.data = {
                product_list : selList.chk_value,
                member_id    : member_id
            };
            ajaxObject.success = function(result){
                //location.reload();
            };
            ajaxObject.fail = function(result){
                 bootbox.alert("添加购物车失败");
            };
            ajaxPost(ajaxObject);
        });

        return false;
    }
    
    function createAddress(addrInfo, orderInfo, callback){
    	var ajaxObject = {};
        ajaxObject.url = "receiver/createReceiver";
        ajaxObject.data = addrInfo;
        ajaxObject.success = function(result){
        	var receiver_id = result.data;
        	orderInfo['receiver_id'] = receiver_id;
        	//创建收件人成功
        	callback(orderInfo);          
        };
        ajaxObject.fail = function(result){
        	//创建收件人失败
          
        };          
        ajaxPost(ajaxObject);
    }
    
    function createOrder(orderInfo){
    	var ajaxObject = {};
        ajaxObject.url = "orders/createOrder";
        ajaxObject.data = orderInfo;
        ajaxObject.success = function(result){
        	var aa = result;
        	//订单创建成功
        	
        	//删除订单
        	
        	
        	layer.closeAll();
        	layer.alert("订单编号是："+result.data.orderId,function(index){
        		location.reload();
				layer.close(index);
			});
        };
        ajaxObject.fail = function(result){
        	//订单创建失败
        };          
        ajaxPost(ajaxObject);
    } 
    
  	$(document).off("click.add_order_confirm").on("click.add_order_confirm", ".add_order_confirm", function(){
  		//1.创建收件人   2.创建订单跟收件人关联    3.获取支付的信息    4.支付
  		var username = $("#user_name").val();
  		var usermobile = $(".user_mobile").val();
  		var useraddress = $(".user_address").val();
  		
  		//非空判断
  		if(username.lenght < 1){
  		  alert("请输入姓名");
          return false;
  		}
  		if(usermobile.lenght < 1){
    		alert("请输入手机号码");
        	return false;
    	}
  		if(useraddress.lenght < 1){
  		  alert("请输入收货详情地址");
          return false;
  		}
  		
  		//购买者id
  		var a = location.href;
  		var member_id = a.substring(a.lastIndexOf('=')+1);
  		
  		//订单数据
  		var arr = [];
  		var all_price=0;
  		$(".pop_dialog").find(".o_item").each(function(){
  			var o_item = $(this);
  			var id = o_item.attr("data-id");
  			var num = o_item.find(".sy_num").val();
  			var meter = o_item.find(".meter").val();
  			var type_c = document.getElementById("order_type").textContent;
  			var type;
  			if(type_c.indexOf("单幅")>-1){
  				type = 0;
  			}else{
  				type = 1;
  			}
  			var unit_price = 1;
  			var total_price = unit_price * parseFloat(meter);
  			all_price+=total_price;
  			
  			var obj = {
  				fabric_id : id,//面料id
  				amount : num,//数量
  				meter : meter,//米数
  				option_id : type,//宽幅和单幅
  				unit_price :  unit_price+"",// 单价
  				total_price : total_price+"",// 总价
  				discount : "0"// 折扣
  			};
  			arr.push(obj);
  		});
  		
  		var addrInfo = {
  			create_user : member_id,//创建用户的id
 			name : username,//用户名
 			mobile : usermobile,//手机号
 			addr_province : "",// 收件人省份
 			addr_city : "",// 收件人城市
 			addr_county : "",// 收件人区、县
 			addr_detail :useraddress//详细地址				
  		};
  		
  		var orderInfo = {
        	order_item : arr,//商品数据
        	discount : "1",//折扣
        	coupon_id : 0,//优惠券id
        	receiver_id : 0,//收件人id
        	pay_way : "支付宝",//支付方式
        	buy_id : member_id//购买者id
        };
  		
  		
  		createAddress(addrInfo, orderInfo, function(info){
  			createOrder(info);
  		});
  	});



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