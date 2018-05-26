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
		
		<script type="text/javascript">

			function save(target){
				if($("#YSYNAME").val()==""){
					$("#YSYNAME").tips({
						side:3,
			            msg:'输入系统名称',
			            bg:'#AE81FF',
			            time:3
			        });
					$("#YSYNAME").focus();
					return false;
				}

				if($("#COUNTPAGE").val()==""){
					$("#COUNTPAGE").tips({
						side:3,
			            msg:'输入每页条数',
			            bg:'#AE81FF',
			            time:3
			        });
					$("#COUNTPAGE").focus();
					return false;
				}
		

				var url = 'head/rest/saveSys';
				ajaxSave(target, url, 'edit');

				return false;
			}
		</script>
	</head>
<body>

<div id="zhongxin">
 <div class="span6">
	<div class="tabbable">
            
            <div class="tab-content">
			  <div id="home" class="tab-pane in active">
				<form action="head/saveSys.do" name="Form" id="Form" method="post">
				<table id="table_report" class="table table-striped table-bordered table-hover">
					<tr>
						<td style="text-align: right;padding-top: 13px;">系统名称:</td>
						<td><input type="text" name="YSYNAME" id="YSYNAME" value="${pd.YSYNAME }" placeholder="请输入系统名称" style="width:90%" title="系统名称"/></td>
					
						<td style="text-align: right;padding-top: 13px;">每页条数:</td>
						<td><input type="number" name="COUNTPAGE" id="COUNTPAGE" value="${pd.COUNTPAGE }" placeholder="请输入每页条数" style="width:90%" title="每页条数"/></td>
					</tr>
				</table>
				
				<table id="table_report" class="table table-striped table-bordered table-hover">
					<tr>
						<td style="text-align: center;" colspan="100">
							短信模板配置
						</td>
					</tr>
					<tr>
						<td style="margin-top:0px;" cols="50">
					 		<textarea name="SMS_TEMPLATE" id="SMS_TEMPLATE"  
					 			style="width:100%;" placeholder="请输入短信模板">${pd.SMS_TEMPLATE}
					 		</textarea>
						</td>
					</tr>
					<tr>
						<td style="text-align: center;" colspan="100">
							快递公司列表配置
						</td>
					</tr>
					<tr>
						<td style="margin-top:0px;" cols="50">
					 		<textarea name="EXPRESS_COMPANY" id="EXPRESS_COMPANY"  
					 			style="width:100%;" placeholder="请输入快递公司(多个公司用中文，分隔)">${pd.EXPRESS_COMPANY}
					 		</textarea>
						</td>
					</tr>

					<tr>
						<td style="margin-top:0px;" cols="50">
					 		<textarea name="APP_VERSION" id="APP_VERSION"  
					 			style="width:100%;" placeholder="请输入快递公司(多个公司用中文，分隔)">${pd.APP_VERSION}
					 		</textarea>
						</td>
					</tr>

					<tr>
						<td style="margin-top:0px;" cols="50">
					 		<textarea name="BLINK_LICENSE" id="BLINK_LICENSE"  
					 			style="width:100%;" placeholder="请输入快递公司(多个公司用中文，分隔)">${pd.BLINK_LICENSE}
					 		</textarea>
						</td>
					</tr>

				</table>
		
				<table class="center" style="width:100%" >
					<tr>
						<td style="text-align: center;" colspan="100">
							<a class="btn btn-primary" onclick="save(this);">保存</a>
							
						</td>
					</tr>
				</table>
				</form>
			  </div>
			  
			  
            </div>
	</div>
 </div><!--/span-->



</div>
	
		
</body>
</html>