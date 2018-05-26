<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>

<script src="/FHM/static/js/echarts/jquery.min.js"></script>
<!-- ECharts单文件引入 -->

<script src="/FHM/static/js/echarts/echarts.min.js"></script>
<script type="text/javascript"> 

/* 	window.onload=function(){  
		   startRequest();  
	}  */
	function startRequest(){ 

		// 基于准备好的dom，初始化echarts图表
       var statusTodayChart = echarts.init(document.getElementById('statusTodayChart')); 
       var recentEverydayChart = echarts.init(document.getElementById('recentEverydayChart')); 
       var companyTodayTopNChart = echarts.init(document.getElementById('companyTodayTopNChart'), "macarons"); 
       var companyRecentTopNChart = echarts.init(document.getElementById('companyRecentTopNChart'), "macarons"); 
    
		statusTodayChart.showLoading({
		    text: '读取数据中...',    //loading话术
		});
		companyTodayTopNChart.showLoading({
		    text: '读取数据中...',    //loading话术
		});
		companyRecentTopNChart.showLoading({
		    text: '读取数据中...',    //loading话术
		});
		recentEverydayChart.showLoading({
		    text: '读取数据中...',    //loading话术
		});

		$.getJSON('<%=basePath%>app/express/statistics/total/recent/7', function(responseData) {
			var rs = responseHandle(recentEverydayChart, responseData.code);
			if(!rs)
				return;

			var jsonData = responseData.data;
			var titleArray = new Array();
			var countArray = new Array();
			for (var i=0; i<jsonData.length;i++){
				var json = jsonData[i];
				titleArray[i] = json.TIME_DAY.substring(5);
				countArray[i] = json.NUMBER_COUNT;
			}
			var option = generateLineChartData("最近7天每天快递数量", countArray, titleArray);
	    	// 为echarts对象加载数据 
	    	recentEverydayChart.setOption(option);
		});


		$.getJSON('<%=basePath%>app/express/statistics/total/today/status', function(responseData) {
			var rs = responseHandle(statusTodayChart, responseData.code);
			if(!rs)
				return;

			var jsonData = responseData.data;
			var titleArray = new Array();
			var countArray = new Array();
			for (var i=0; i<jsonData.length;i++){
				var json = jsonData[i];
				var status = json.STATUS;
				var statusName = getStatusName(status);
				titleArray[i] = statusName;
				var statusValue = json.COUNT_STATUS;
				countArray[i] = {value: statusValue, name: statusName};
			}
			var option = generatePieChartData("当天快递各个状态的数量", countArray, titleArray);
	    	// 为echarts对象加载数据 
	    	statusTodayChart.setOption(option);
		});

		$.getJSON('<%=basePath%>app/express/statistics/company/today/top/10', function(responseData) {
			var rs = responseHandle(companyTodayTopNChart, responseData.code);
			if(!rs)
				return;

			var jsonData = responseData.data;
			var titleArray = new Array();
			var countArray = new Array();
			for (var i=0; i<jsonData.length;i++){
				var json = jsonData[i];
				titleArray[i] = json.COMPANY;
				countArray[i] = json.COMPANY_COUNT;
			}
			var option = generateChartData("当天快递公司快递数量TOP10", countArray, titleArray);
	    	// 为echarts对象加载数据 
	    	companyTodayTopNChart.setOption(option);
		});

		$.getJSON('<%=basePath%>app/express/statistics/company/recent/7/top/10', function(responseData) {
			var rs = responseHandle(companyRecentTopNChart, responseData.code);
			if(!rs)
				return;

			var jsonData = responseData.data;
			var titleArray = new Array();
			var countArray = new Array();
			for (var i=0; i<jsonData.length;i++){
				var json = jsonData[i];
				titleArray[i] = json.COMPANY;
				countArray[i] = json.COMPANY_COUNT;
			}
			var option = generateChartData("最近7天快递公司快递数量TOP10", countArray, titleArray);
	    	// 为echarts对象加载数据 
	    	companyRecentTopNChart.setOption(option);
		});
		
	}

	function responseHandle(chart, responseCode){
		chart.hideLoading(); 
		if(responseCode != 200){
			if(responseCode == 505){
				return false;
			}
			chart.showLoading({
	    		text: '数据读取错误',    //loading话术
			});
			return false;
		}

		return true;
	}

	function generateChartData(title, chartData, titleData){
		var option = {
		    title: {
		        x: 'center',
		        text: title
		    },
		    tooltip: {
		        trigger: 'item'
		    },
		    toolbox: {
		        show: false,
		        feature: {
		            dataView: {show: false, readOnly: false},
		            dataZoom : {
				        show : true,
				        title : {
				            dataZoom : '区域缩放',
				            dataZoomReset : '区域缩放后退'
				        }
				    },
		            restore: {show: false}
		        }
		    },
		    calculable: true,
		    grid: {
		        show: false
		    },
		    xAxis: [
		        {
		            type: 'category',
		            show: true,
		            splitLine: {show: false},
		            data: titleData
		        }
		    ],
		    yAxis: [
		        {
		            type: 'value',
		            splitLine: {show: false},
		            show: true
		        }
		    ],
		    series: [
		        {
		            name: title,
		            type: 'bar',
		            itemStyle: {
		            	emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                },
		                normal: {
		                    color: function(params) {
		                        // build a color map as your need.
		                        var colorList = [
		                          '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
		                           '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
		                           '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
		                        ];
		                        return colorList[params.dataIndex]
		                    },
		                    barBorderRadius: 4,
		                    label: {
		                        show: true,
		                        position: 'top',
		                        formatter: '{c}'
		                    }

		                }

		            },
		            data: chartData,
		            
		        }
		    ]
		};
		return option;
	} 

	function generatePieChartData(title, chartData, titleData){
		var option = {
		    title : {
		        text: title,
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        left: 'left',
		        data: titleData
		    },
		    series : [
		        {
		            name: '访问来源',
		            type: 'pie',
		            radius : '55%',
		            center: ['40%', '50%'],
		            data:chartData,
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
		return option;
	} 

	function generateLineChartData(title, chartData, titleData){
		var option = {
		    title: {
		        text: title,
		        x: 'center'
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            data : titleData
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series : [
		        {
		            itemStyle: {
		                normal: {
		                    
		                    label: {
		                        show: true,
		                        position: 'top',
		                        formatter: '{c}'
		                    }
		                }

		            },
		            name:title,
		            type:'line',
		            data:chartData
		        }
		    ]
		};

		return option;
	} 

	function getStatusName(status){
		if(status == 2){
			return "到达中心";
		}else if(status == 3){
			return "分拣分发";
		}else if(status == 4){
			return "到达站点";
		}else if(status == 9){
			return "用户签收";
		}
	}
    
</script>
<base href="<%=basePath%>">

<!-- jsp文件头和头部 -->
<%@ include file="top.jsp"%>

</head>
<body>

	<div class="container-fluid" id="main-container">
		

			<div id="page-content" class="clearfix">
				<!--/page-header-->

				<div class="row-fluid">

					<div class="space-6"></div>
					<div class="row-fluid">

						<!-- 柱状图 -->
						<div class="center">
							<div style="float:left;width:50%;height:300px">
								<div id="statusTodayChart" style="width:100%;height:100%;">  </div>
								
							</div>
							<div style="float:right;width:50%;height:300px">
								<div id="recentEverydayChart" style="width:100%;height:100%;">  </div>
								
							</div>
						</div>
						<div class="center">
							<div style="float:left;width:50%;height:300px">
								<div id="companyTodayTopNChart" style="width:100%;height:100%;">  </div>
								
							</div>
							<div style="float:right;width:50%;height:300px">
								<div id="companyRecentTopNChart" style="width:100%;height:100%;">  </div>
								
							</div>
						</div>
					</div>
				</div>
				<!--/row-->
		</div>
		<!-- #main-content -->
	</div>
	<!--/.fluid-container#main-container-->
	
	<!-- basic scripts -->
	<script type="text/javascript">
		window.jQuery
				|| document
						.write("<script src='static/js/jquery.min.js'>\x3C/script>");
	</script>

	
	<!-- inline scripts related to this page -->


	<script type="text/javascript">

		$(top.hangge());
	
	</script>
</body>
</html>
