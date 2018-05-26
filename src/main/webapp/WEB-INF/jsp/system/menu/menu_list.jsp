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
	$(top.hangge());	
	
	//新增
	function addmenu(){
		 BootstrapDialog.show({cssClass:'one-row-dialog',
	            message: $('<div></div>').load('<%=basePath%>menu/toAdd.do'),
	            title: '新增菜单'
	          });
	}
	
	//修改
	function editmenu(menuId){
		 BootstrapDialog.show({cssClass:'one-row-dialog',
	            message: $('<div></div>').load('<%=basePath%>menu/toEdit.do?MENU_ID='+menuId),
	            title: '编辑菜单'
	          });
	}
	
	//编辑顶部菜单图标
	function editTb(menuId){
		 BootstrapDialog.show({
	            message: $('<div></div>').load('<%=basePath%>menu/toEditicon.do?MENU_ID='+menuId),
	            title: '编辑图标'
	          });
	}
	
	function delmenu(menuId,isParent){
		var flag = false;
		if(isParent){
			if(confirm("确定要删除该菜单吗？其下子菜单将一并删除！")){
				flag = true;
			}
		}else{
			if(confirm("确定要删除该菜单吗？")){
				flag = true;
			}
		}
		if(flag){
			var url = "<%=basePath%>menu/del.do?MENU_ID="+menuId+"&guid="+new Date().getTime();
			$.get(url,function(data){
				$("#leftMenuId").load('<%=basePath%>menu.do');
			});
		}
	}
	
	function openClose(menuId,curObj,trIndex){
		var txt = $(curObj).text();
		if(txt=="展开"){
			$(curObj).text("折叠");
			$("#tr"+menuId).after("<tr id='tempTr"+menuId+"'><td colspan='5'>数据载入中</td></tr>");
			if(trIndex%2==0){
				$("#tempTr"+menuId).addClass("main_table_even");
			}
			var url = "<%=basePath%>menu/sub.do?MENU_ID="+menuId+"&guid="+new Date().getTime();
			$.get(url,function(data){
				if(data.length>0){
					var html = "";
					$.each(data,function(i){
						html = "<tr style='height:24px;line-height:24px;' name='subTr"+menuId+"'>";
						html += "<td></td>";
						html += "<td><span style='width:80px;display:inline-block;'></span>";
						if(i==data.length-1)
							html += "<img src='static/images/joinbottom.gif' style='vertical-align: middle;'/>";
						else
							html += "<img src='static/images/join.gif' style='vertical-align: middle;'/>";
						html += "<span style='width:100px;text-align:left;display:inline-block;'>"+this.MENU_NAME+"</span>";
						html += "</td>";
						html += "<td>"+this.MENU_URL+"</td>";
						html += "<td class='center'>"+this.MENU_ORDER+"</td>";
						html += "<td><a class='btn btn-mini btn-info' title='编辑' onclick='editmenu(\""+this.MENU_ID+"\")'><i class='fa fa-edit'></i></a> <a class='btn btn-mini btn-danger' title='删除' onclick='delmenu(\""+this.MENU_ID+"\",false)'><i class='fa fa-trash'></i></a></td>";
						html += "</tr>";
						$("#tempTr"+menuId).before(html);
					});
					$("#tempTr"+menuId).remove();
					if(trIndex%2==0){
						$("tr[name='subTr"+menuId+"']").addClass("main_table_even");
					}
				}else{
					$("#tempTr"+menuId+" > td").html("没有相关数据");
				}
			},"json");
		}else{
			$("#tempTr"+menuId).remove();
			$("tr[name='subTr"+menuId+"']").remove();
			$(curObj).text("展开");
		}
	}
</script>
</head>

<body>
	<table id="table_report" class="table table-hover">
		<thead>
		<tr>
			<th class="center"  style="width: 50px;">序号</th>
			<th class='center'>名称</th>
			<th class='center'>资源路径</th>
			<th class='center'>排序</th>
			<th class='center'>操作</th>
		</tr>
		</thead>
		<c:choose>
			<c:when test="${not empty menuList}">
				<c:forEach items="${menuList}" var="menu" varStatus="vs">
				<tr id="tr${menu.MENU_ID }">
				<td class="center">${vs.index+1}</td>
				<td class='center'><i class="${menu.MENU_ICON }">&nbsp;</i>${menu.MENU_NAME }&nbsp;
					<c:if test="${menu.MENU_TYPE == '1' }">
					<span class="label label-success arrowed">系统</span>
					</c:if>
					<c:if test="${menu.MENU_TYPE != '1' }">
					<span class="label label-important arrowed-in">业务</span>
					</c:if>
				</td>
				<td>${menu.MENU_URL == '#'? '': menu.MENU_URL}</td>
				<td class='center'>${menu.MENU_ORDER }</td>
				<td style="width: 25%;">
				<a class='btn btn-mini btn-warning' onclick="openClose('${menu.MENU_ID }',this,${vs.index })" >展开</a>
				<a class='btn btn-mini btn-purple' title="图标" onclick="editTb('${menu.MENU_ID }')" ><i class='fa fa-picture-o'></i></a>
				<a class='btn btn-mini btn-info' title="编辑" onclick="editmenu('${menu.MENU_ID }')" ><i class='fa fa-edit'></i></a>
				<a class='btn btn-mini btn-danger' title="删除"  onclick="delmenu('${menu.MENU_ID }',true)"><i class='fa fa-trash'></i></a>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
				<td colspan="100">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	
	<div class="page_and_btn">
		<div>
			&nbsp;&nbsp;<a class="btn btn-small btn-success" onclick="addmenu();">新增</a>
		</div>
	</div>
	
</body>
</html>