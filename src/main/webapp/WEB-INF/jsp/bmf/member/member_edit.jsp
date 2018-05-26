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
<style>

</style>		
<script type="text/javascript">
$(function(){
	var chosenType = "${pd.chosenType}";
	 if( chosenType == 1){//下拉选择情况
		 $("#PROFESSION_SUP").attr("disabled",false);//一级下拉框可用
		 $("#PROFESSION_SUB").attr("disabled",false);//二级下拉框可用
		 $("#chosenType1").attr("checked",true);//下拉框左边的单选框被选中
		 $("#PROFESSION_SUB1").val("");//职业输入框初始化
	}else if(chosenType == 2){//职业选择"其它"qingkuang1
		$("#PROFESSION_SUB1").attr("disabled",false);//输入框可用
		 $("#chosenType2").attr("checked",true);//输入框边的单选框被选中
		 $("#default1_1").attr("selected",true);//一级下拉框初始化
		 $("#PROFESSION_SUB").val("");//一级下拉框初始化
		 
	}
})
	 $(":radio").click(function(){
		 $(".disabledOrNot").attr("disabled",true);
		 $(this).siblings().attr("disabled",false);
  	});
function cancel(){
	location.reload();
}
$("#MOBILE").blur(function(){
	var mobile = $(this).val();
	var id = '${pd.ID}';
	if(mobile == null || mobile ==''){
		alert("手机号不可以为空！");
		return false
	};
    var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;  
    if (!myreg.test(mobile)) {  
    	$(this).val("");
    	alert("手机格式不正确！");
        return false;  
    } 
	ajaxObject={};
	ajaxObject.url='member/judgeMobile.do';
	ajaxObject.data={
				MOBILE:mobile,
				ID:id
				};
	ajaxObject.success=function(data){
		if(data.data){
			$("#MOBILE").val('');
			alert("该手机号"+mobile+"已经被注册！");
			return false;
		}
	}
	ajaxPost(ajaxObject);
	
})
</script>
	</head>
<body>
	<form action="member/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="ID" id="ID" value="${pd.ID}"/>
		<div id="zhongxin" style="padding-top:20px;">
		<table id="table_report" class="table noline">
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">ID</td>
				<td><input type="text"  value="${pd.ID}" readonly="readonly"/></td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">名称</td>
				<td><input type="text" name="NICKNAME" id="NICKNAME" value="${pd.NICKNAME}" maxlength="32" placeholder="请输入昵称" title="昵称"/></td>
			</tr>
			<!-- 职业选择类型 chosenType 1选择2其他 -->
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">类型</td>
				<td>
					<label>
						<input type="radio" name="chosenType" id="chosenType1" value="1" />
							<c:choose>
								<c:when test="${not empty professionSupList }">
									<select style="width:100px" name ="PROFESSION_SUP" id ="PROFESSION_SUP" class ="PROFESSION_SUP disabledOrNot" disabled="disabled" >
										<c:if test="${pd.PROFESSION_SUP == null || pd.PROFESSION_SUP =='' || pd.PROFESSION_SUP =='其它'}">
											<option id="default1_1" value="">请选择一级职业</option>											
										</c:if>
										<c:if test="${pd.PROFESSION_SUP != null && pd.PROFESSION_SUP !='' && pd.PROFESSION_SUP !='其它'}">
											<option value="${pd.PROFESSION_SUP}" selected="selected">${pd.PROFESSION_SUP}</option>
										</c:if>
										<c:forEach items="${professionSupList}" var="var" varStatus="vs">
												<option value="${var.professionSupName}">${var.professionSupName}</option>
										</c:forEach>
									</select>
								</c:when>
							</c:choose>
						<select style="width:100px" name ="PROFESSION_SUB" id ="PROFESSION_SUB" class="PROFESSION_SUB disabledOrNot" disabled="disabled" >
							<c:if test="${pd.PROFESSION_SUB != null && pd.PROFESSION_SUB !=''}">
								<option value="${pd.PROFESSION_SUB}" selected="selected">${pd.PROFESSION_SUB}</option>
							</c:if>
						</select>
					</label>					
					<br/>
					<label>
						<input type="radio" name="chosenType" id = "chosenType2"  value="2" />
						<input type="text" name="PROFESSION_SUB1" class="disabledOrNot" id="PROFESSION_SUB1" disabled="disabled"  value="${pd.PROFESSION_SUB}" /><span>其它,请注明</span>
					</label>
				</td>
			</tr>
			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">手机号码</td>
				<td><input type="text" name="MOBILE" id="MOBILE" value="${pd.MOBILE}" /></td>
			</tr>

			<tr>
				<td style="width:80px;text-align: right;padding-top: 13px;">所在区域</td>
				<td>
					<span>
						<c:choose>
							<c:when test="${not empty provinceList }">
								<select style="width:100px" name ="ADDR_PROVINCE" class ="ADDR_PROVINCE" >
									<c:if test="${pd.ADDR_PROVINCE == null || pd.ADDR_PROVINCE ==''}">
										<option value="">请选择省份</option>
									</c:if>
									<c:if test="${pd.ADDR_PROVINCE != null && pd.ADDR_PROVINCE !=''}">
										<option value="${pd.ADDR_PROVINCE}" selected="selected">${pd.ADDR_PROVINCE}</option>
									</c:if>
									<c:forEach items="${provinceList}" var="var" varStatus="vs">
											<option value="${var.divisionName}">${var.divisionName}</option>
									</c:forEach>
								</select>
							</c:when>
						</c:choose>
					</span>
					<span>
						<select style="width:100px" name ="ADDR_CITY" class="ADDR_CITY">
							<c:if test="${pd.ADDR_CITY != null && pd.ADDR_CITY !=''}">
								<option value="${pd.ADDR_CITY}" selected="selected">${pd.ADDR_CITY}</option>
							</c:if>
						</select>
					</span>
				</td>
			</tr>

		</table>
				<span style="margin-left:100px;">
					<a id="saveBtn" class="btn btn-primary" style="width:80px; margin-right:50px;" onclick="save(this);">保 存</a>
					<a id="saveBtn" class="btn btn-primary" style="width:80px" onclick="cancel();">取 消</a>
				</span>
		</div>
		
	</form>
<script type="text/javascript">
	
	//保存
	function save(target){
/* 			if($("#NUMBER").val()==""){
				popupTip('NUMBER', '请输入编号');
				return false;
			}
			if($("#USERNAME").val()==""){
				popupTip('USERNAME', '请输入用户名');
				return false;
			}
			if($("#PASSWORD").val()==""){
				popupTip('PASSWORD', '请输入密码');
				return false;
			}
			if($("#NICKNAME").val()==""){
				popupTip('NICKNAME', '请输入昵称');
				return false;
			}
			if($("#AVATAR").val()==""){
				popupTip('AVATAR', '请输入头像');
				return false;
			} */
			if($("#MOBILE").val()==""){
				popupTip('MOBILE', '请输入手机号');
				return false;
			}
/* 			if($("#PROFESSION").val()==""){
				popupTip('PROFESSION', '请输入职业');
				return false;
			}
			if($("#DEFAULT_AREA").val()==""){
				popupTip('DEFAULT_AREA', '请输入默认区域');
				return false;
			}
			if($("#DEFAULT_ADDRESS").val()==""){
				popupTip('DEFAULT_ADDRESS', '请输入默认地址');
				return false;
			}
			if($("#REALNAME").val()==""){
				popupTip('REALNAME', '请输入真实姓名');
				return false;
			}
			if($("#ID_CARD").val()==""){
				popupTip('ID_CARD', '请输入身份证号');
				return false;
			}
			if($("#BANK_CARD").val()==""){
				popupTip('BANK_CARD', '请输入银行卡号');
				return false;
			}
			if($("#STATUS").val()==""){
				popupTip('STATUS', '请输入状态');
				return false;
			}
			if($("#WECHAT_ID").val()==""){
				popupTip('WECHAT_ID', '请输入三方登录，微信ID');
				return false;
			}
			if($("#QQ_ID").val()==""){
				popupTip('QQ_ID', '请输入三方登录，QQID');
				return false;
			}
			if($("#WEIBO_ID").val()==""){
				popupTip('WEIBO_ID', '请输入三方登录，新浪微博ID');
				return false;
			} */
	
		var action = '${msg}';
		var url = 'member/rest/' + action;
		ajaxSave(target, url, action);
		return false;
	}
	
	function popupTip(field, msg){
		$("#"+field).tips({
			side:3,
            msg:msg,
            bg:'#AE81FF',
            time:2
        });
		$("#field").focus();
	}
	/* 选择省份，并展示城市 */
	var addrProvince=null;
	$(".ADDR_PROVINCE").change(function(){
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
						$(".ADDR_CITY").html(str);
					 }, 
				fail:function(){console.log("fail")},
				error:function(){console.log("error")},
		}
		ajaxPost(ajaxObject);			
	})
	/* 根据一级职业选择二级职业 */
	var professionSupName=null;
	$(".PROFESSION_SUP").change(function(){
		professionSupName = $(this).val()
		var ajaxObject={
				url:"member/rest/goSelectProfessionSub",
				data:{
					professionSupName:professionSupName
					},
				success:function (data) { 
						var str="<option >请选择二级职业</option>";
						for(var i=0;i<data.data.length;i++){
							str=str+"<option value="+data.data[i].professionSubName+">"+data.data[i].professionSubName+"</option>";
						}
						$(".PROFESSION_SUB").html(str);
					 }, 
				fail:function(){console.log("fail")},
				error:function(){console.log("error")},
		}
		ajaxPost(ajaxObject);			
	})
</script>
</body>
</html>