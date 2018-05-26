<!-- 面料页面 -->
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="cblock">
	<!-- Modal -->
	<div class="modal fade" id="destroyCollection" tabindex="-1"
		role="dialog" aria-labelledby="destroyCollection" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">隐藏</h4>
				</div>
				<div class="modal-body">确定隐藏选中吗？</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" v-on:click="destroyCollection">确定</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="destroyItem" tabindex="-1" role="dialog"
		aria-labelledby="destroyItem" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">隐藏</h4>
				</div>
				<div class="modal-body">确定隐藏吗？</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" v-on:click="destroyCollection">确定</button>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12 col-sm-4">
			<div class="btn-group mar_tb10" role="group">
				<a class="btn btn-default add" role="button" href="../goAdd"><i class="glyphicon glyphicon-plus"></i>添加</a>
				<a class="btn btn-default deletepage" role="button" data-toggle="modal" data-target="#destroyCollection"><i class="glyphicon glyphicon-trash"></i>隐藏选中</a>
			</div>
		</div>
		<div class="col-xs-12 col-sm-5 col-sm-offset-3 mar_tb10">
			<form class="input-group" action="<%=basePath %>fabrics/list/1" method="get">
				<input type="text" name="search" class="form-control"
					placeholder="按编号，标签，色系，录入人筛选">
				<div class="input-group-btn">
					<button class="btn btn-default" type="submit">
						<i class="glyphicon glyphicon-search"></i> 搜索
					</button>
				</div>
			</form>
		</div>
		<div class="col-xs-12 mar_t10">
			<div class="panel panel-default">
				<div class="panel-heading">列表</div>
				<div class="table-responsive">
					<table class="table table-hover table-striped">
						<thead>
							<tr>
								<th><input id="checkAll" type="checkbox" v-on:click="checkAll"></th>
								<th>编号</th>
								<th>品名</th>
								<th>色号</th>
								<th>成分</th>
								<th>门幅</th>
								<th>花幅</th>
								<th>花距</th>
								<th>克重</th>
								<th>平面图片</th>
								<th>艺术图片</th>
								<th>瀑布流图片</th>
								<th>录入人</th>
								<th>录入时间</th>
								<th>所属面料组</th>
								<th>用途标签</th>
								<th>颜色标签</th>
								<th>风格标签</th>
								<th>搭配标签</th>
								<th>款式标签</th>
								<th>其他标签</th>
								<th>单价（元/米）</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody class="listpage">
							<c:forEach items="${varList }" var="fab">
								<tr>
									<td><input type="checkbox" name="list" v-model="id" v-on:click="checkItem" v-bind:value="${fab.id }"></td>
									<td>${fab.number }</td>
									<td>${fab.name }</td>
									<td>${fab.color }</td>
									<td>${fab.ingredients }</td>
									<td>${fab.width }</td>
									<td>${fab.flower_size }</td>
									<td>${fab.flower_distance }</td>
									<td>${fab.gram }</td>
									<td><img src="<%=basePath %>${fab.flat_picture }" alt="${fab.name }" height="100"></td>
									<td><img src="<%=basePath %>${fab.art_picture }" alt="${fab.name }" height="100"></td>
									<td><img src="<%=basePath %>${fab.stream_picture }" alt="${fab.name }" height="100"></td>
									<td>${fab.user }</td>
									<td>${fab.record_date }</td>
									<td>${fab.group }</td>
									<td>${fab.use_labels }</td>
									<td>${fab.color_labels }</td>
									<td>${fab.style_tags }</td>
									<td>${fab.collocation_tag }</td>
									<td>${fab.design_tags }</td>
									<td>${fab.other_tags }</td>
									<td>${fab.price }</td>
									<td>
										<a class="btn btn-primary" href="<%=basePath %>fabrics/goEdit/${fab.id }" role="button">编辑</a>
										<!-- @if(empty($value['deleted_at'])) -->
											<a class="btn btn-danger delete" href="javascript:void(0)" v-on:click="destroyItem('${fab.id }')" data-toggle="modal" data-target="#destroyItem" role="button">隐藏</a>
										<!-- @else -->
											<a class="btn btn-success" href="javascript:void(0)" v-on:click="showItem('${fab.id }')" role="button">显示</a>
										<!-- @endif -->
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="text-center">${page.pageInfo}</div>
			</div>
		</div>
	</div>
</div>