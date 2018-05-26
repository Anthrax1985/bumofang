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
	body{margin:20px;}
	#title{font-size:26px;}
	#overviewUl{list-style-type:none; }
	#overviewUl li{float:left;margin:20px 20px 10px 0px;font-size:18px;}
	table{border-collapse:collapse;display:inline-block;}
 	th,td{border: 1px solid black;}  
	td,th{text-align:center; height:26px;width:200px;line-height:20px;font-size:16px;}
	.pieItem{width:400px;height:400px;display:inline-block;}
	.barItem{height:720px;}
	.saleDetail{font-weight:bold;}
	.saleDetail,#choseeYear,#choseeMorS,.submit{margin-right:20px;font-size:16px;}
	.tableDetail{border:none;background:white;color:blue;}
	.submit{background:#6fb3e0;border:1px solid #6fb3e0;color:white;border-radius:4px;}
</style>
<script type="text/javascript">

$(function(){
	var year = '${req4Browse.year}';//接收后台传入的年份时间;用于图标详情
	/* 饼图1-用户类型-浏览次数 */
       var memberType = echarts.init(document.getElementById('memberType'));
	option1 = {
	        title : {
	            text: '浏览类型',
	            x:'center',
	            y:"bottom"
	        },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{b} : {c} ({d}%)"
	    },
	    series : [
	        {
	            name:"浏览类型",
	            type: 'pie',
	            radius : '50%',
	            center: ['50%', '50%'],
	            data:${memberType}
	        }
	    ]
	};
	memberType.setOption(option1);
	/* 饼图2-浏览区域-浏览次数 */
       var regionType = echarts.init(document.getElementById('regionType'));
	option2 = {
	        title : {
	            text: '浏览区域',
	            x:'center',
	            y:"bottom"
	        },
	
	    tooltip : {
	        trigger: 'item',
	        formatter: "{b} : {c} ({d}%)"
	    },
	    series : [
	        {
	            name:"浏览区域",
	            type: 'pie',
	            radius : '50%',
	            center: ['50%', '50%'],
	            data: ${regionType} 
	        }
	    ]
	};
	regionType.setOption(option2);
	/* 饼图3-浏览颜色-浏览次数 */
       var colorType = echarts.init(document.getElementById('colorType'));
	option3 = {
        title : {
            text: '浏览颜色',
            x:'center',
            y:"bottom"
        },
		tooltip : {
      	 	 trigger: 'item',
      	  	formatter: "{b} : {c} ({d}%)"
	    },
	    series : [
	        {
	            name:"浏览颜色",
	            type: 'pie',
	            radius : '50%',
	            center: ['50%', '50%'],
	            data: ${colorType}
	        }
	    ]
	};
	colorType.setOption(option3);
	/* 饼图4-浏览风格-浏览次数 */
       var styleType = echarts.init(document.getElementById('styleType'));
	option4 = {
	        title : {
	            text: '浏览风格',
	            x:'center',
	            y:"bottom"
	        },
		tooltip : {
	        trigger: 'item',
	        formatter: "{b} : {c} ({d}%)"
	    },
	    series : [
	        {
	            name:"浏览风格",
	            type: 'pie',
	            radius : '50%',
	            center: ['50%', '50%'],
	            data: ${styleType}
	        }
	    ]
	};
	styleType.setOption(option4);
	/* 饼图5-浏览工艺-浏览次数 */
       var craftType = echarts.init(document.getElementById('craftType'));
	option5 = {
        title : {
	            text: '浏览工艺',
	            x:'center',
	            y:"bottom"
	        },
		tooltip : {
	        trigger: 'item',
	        formatter: "{b} : {c} ({d}%)"
	    },
	    series : [
	        {
	            name:"浏览工艺",
	            type: 'pie',
	            radius : '50%',
	            center: ['50%', '50%'],
	            data: ${styleType}
	        }
	    ]
	};
	craftType.setOption(option5);
	/* 饼图6-浏览材质-浏览次数 */
       var materialType = echarts.init(document.getElementById('materialType'));
	option6 = {
        title : {
	            text: '浏览材质',
	            x:'center',
	            y:"bottom"
	        },
		tooltip : {
	        trigger: 'item',
	        formatter: "{b} : {c} ({d}%)"
	    },
	    series : [
	        {
	            name:"浏览材质",
	            type: 'pie',
	            radius : '50%',
	            center: ['50%', '50%'],
	            data: ${materialType}
	        }
	    ]
	};
	materialType.setOption(option6);
	/* 饼图7-浏览应用-浏览次数 */
       var applicationType = echarts.init(document.getElementById('applicationType'));
	option7 = {
        title : {
	            text: '浏览应用',
	            x:'center',
	            y:"bottom"
	        },
		tooltip : {
	        trigger: 'item',
	        formatter: "{b} : {c} ({d}%)"
	    },
	    series : [
	        {
	            name:"浏览应用",
	            type: 'pie',
	            radius : '50%',
	            center: ['50%', '50%'],
	            data: ${applicationType}
	        }
	    ]
	};
	applicationType.setOption(option7);
	/* 饼图8-浏览价格-浏览次数 */
	/* 目前没有价格，所以是死数据 */
       var priceType = echarts.init(document.getElementById('priceType'));
	option8 = {
        title : {
	            text: '浏览价格',
	            x:'center',
	            y:"bottom"
	        },
		tooltip : {
	        trigger: 'item',
	        formatter: "{b} : {c} ({d}%)"
	    },
	    series : [
	        {
	            name:"浏览价格",
	            type: 'pie',
	            radius : '50%',
	            center: ['50%', '50%'],
	            data: [{"value":0,"name":"价格区间"}]
	        }
	    ]
	};
	priceType.setOption(option8);


	/* 饼图9-销售柱状图*/
    var barChart = echarts.init(document.getElementById('barChart'));
	option9 = {
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    legend: {
		        orient: 'horizontal',
		        left: 'center',
		        bottom:"bottom",
		        data: ['米数','金额']
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis : [
		        {
		            type : 'category',
		            data : ['1月', '2月', '3月', '4月', '5月', '6月'],
		            axisTick: {
		                alignWithLabel: true
		            }
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            show:true
		        }
		    ],
		    series : [
		        {
		            name:'米数',
		            type:'bar',
		            barWidth: '30%',
		            data:[10, 52, 200, 334, 390, 330],
		            label: {
		                normal: {
		                    show: true,
		                    position: 'top'
		                }
		            },
		            itemStyle:{
		                normal:{
		                    color:'#008000'
		                }
		            }
		    
		            
		        },
		                {
		            name:'金额',
		            type:'bar',
		            barWidth: '30%',
		            data:[110, 152, 700, 634, 390, 330],
		            label: {
		                normal: {
		                    show: true,
		                    position: 'top'
		                }
		            },
		            itemStyle:{
		                normal:{
		                    color:'#ffcc00'
		                }
		            }
		        }
		    ]
		};
	
	barChart.setOption(option9);
	//用户浏览用户类型明细
	$("#memberType").click(function(){
		window.open('<%=basePath%>productdatastatistics/memberTypeDetailList/'+year,"新增","width=1080,height=480,screenX=300,screenY=100") 
	})
	//用户浏览区域类型明细
	$("#regionType").click(function(){
		window.open('<%=basePath%>productdatastatistics/regionTypeDetailList/'+year,"新增","width=1080,height=480,screenX=300,screenY=100") 
	})
})
 	$("#submit").click(function(){
 		$("#Form").submit();
	}) 

 </script>
</head>
<body>
<div class="container-fluid" id="main-container">
	<p id="title">用户数据</p>	
	<!-- 时间筛选 -->
	<div class="selectTime">
		<form action="memberdatastatistics/listForData.do" method="post" name="Form" id="Form">
			<span class="saleDetail">用户数据</span>
			<select name="choseeYear" id="choseeYear">
				<c:choose>
					<c:when test="${not empty resInfo.yearList}">
						<c:forEach items="${resInfo.yearList}" var="yearList" >
							<option>${yearList}</option>
						</c:forEach>
					</c:when>
				</c:choose>
			</select>
			<select name="choseeMorS" id="choseeMorS">
				<option value="m">月度</option>
				<option value="s">季度</option>
			</select>
			<button id="submit"  class="submit" value="确认">确认</button>
		</form>
	</div>
	<div>
		<ul id="overviewUl">
			<li>总浏览次数：${resInfo.totalBrowseAmount}次</li>
			<li>总下单次数：0次</li>
			<li>转换率:0%</li>
			<li>总下单米数：0米</li>
			<li>总下单金额：0元</li>
			<li>充值：0元</li>
		</ul>
	</div>
	<div>
		<table class="dataTable" border="1" cellspacing="0">
			<thead>
				<tr>
					<th>时间</th>
					<th>浏览次数(次)</th>
					<th>下单次数(次)</th>
					<th>转化率</th>
					<th>下单米数(米)</th>
					<th>下单金额(元)</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody class="dataTbody">
				<c:choose>
					<c:when test="${not empty resInfo.browseAmountList}">
						<c:forEach items="${resInfo.browseAmountList}" var="list">
							<tr>
								<td>${list.timePeriod}</td>
								<td>${list.browseAmount}</td>
								<td>0</td>
								<td>0%</td>
								<td>0</td>
								<td>0</td>
								<td>
									<button class="tableDetail">查看明细</button>
								<input type="hidden" class="timePeriod" value="${list.timePeriod}"/>
<%-- 										<form action="productdatastatistics/tableList.do" method="post" class="form">
										<input type="hidden" id="timePeriod" value="${list.timePeriod}"/>
										<button class="tableDetail">查看明细</button>
									</form>  --%>
								</td>
							</tr>
						</c:forEach>
					</c:when>
				</c:choose>
			</tbody>
		</table>
	</div>
	<div id="pieChar1">
		<div id="memberType" class="pieItem"></div>
		<div id="regionType" class="pieItem"></div>
		<div id="colorType" class="pieItem"></div>
		<div id="styleType" class="pieItem"></div>
	</div>				
	<div id="pieChar2">
		<div id="craftType" class="pieItem"></div>
		<div id="materialType" class="pieItem"></div>
		<div id="applicationType" class="pieItem"></div>
		<div id="priceType" class="pieItem"></div>
	</div>		
	<hr>	
		<div>
			<span>销量汇总</span>
			<select>
				<c:choose>
					<c:when test="${not empty resInfo.yearList}">
						<c:forEach items="${resInfo.yearList}" var="yearList" >
							<option>${yearList}</option>
						</c:forEach>
					</c:when>
				</c:choose>
			</select>
		</div>
		<div id="barChart" class="barItem"> </div>
</div>
		
<script type="text/javascript">	
	$(top.hangge());
	$(".tableDetail").click(function openTableDetail(){
				<%-- location.href = '<%=basePath%>product/goAdd.do?'; --%>
				var periodTime = $(this).next().val();
				var productId = 0;
				var memberId = 0;
				var status = 1;//1表示只传periodTime；2表示传periodTime和productId；3表示传periodTime和memberId
				<%-- window.open('<%=basePath%>productdatastatistics/tableList/'+periodTime,"新增","width=1080,height=480,screenX=300,screenY=100")  --%>
				<%-- window.open('<%=basePath%>productdatastatistics/tableList/'+status+'/'+periodTime+'/'+productId+'/'+memberId,"新增","width=1080,height=480,screenX=300,screenY=100")  --%>
				layer.open({
					  type: 2, 
					  title:"浏览详情",
					  area: ['1080px', '600px'],
					  content: 'productdatastatistics/tableList/'+status+'/'+periodTime+'/'+productId+'/'+memberId
					}); 		
	}
	)
</script>
		
<script type="text/javascript">
		
	
</script>
		
	</body>
</html>