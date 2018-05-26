<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
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

					<h4 class="modal-title" id="myModalLabel">删除</h4>

				</div>

				<div class="modal-body">确定删除选中吗？</div>

				<div class="modal-footer">

					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>

					<button type="button" class="btn btn-primary"
						v-on:click="destroyCollection">确定删除</button>

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

					<h4 class="modal-title" id="myModalLabel">删除</h4>

				</div>

				<div class="modal-body">确定删除吗？</div>

				<div class="modal-footer">

					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>

					<button type="button" class="btn btn-primary"
						v-on:click="destroyCollection">确定删除</button>

				</div>

			</div>

		</div>

	</div>

	<div class="row">

		<div class="col-xs-12 col-sm-4">

			<div class="btn-group mar_tb10" role="group">

				<a class="btn btn-default add" role="button"
					href="{{url()->current().'/create'}}"><i
					class="glyphicon glyphicon-plus"></i>添加</a> <a
					class="btn btn-default deletepage" role="button"
					data-toggle="modal" data-target="#destroyCollection"><i
					class="glyphicon glyphicon-trash"></i>删除选中</a>

			</div>

		</div>

		<div class="col-xs-12 col-sm-5 col-sm-offset-3 mar_tb10">

			<form class="input-group" action="{{url()->current()}}" method="get">

				<input type="text" name="search" class="form-control"
					placeholder="按组名称，面料标签，风格搜索">

				<div class="input-group-btn">

					<button class="btn btn-default" type="submit">
						<i class="glyphicon glyphicon-search"></i> 搜索

					</button>

				</div>

			</form>
			<!-- /input-group -->

		</div>

		<div class="col-xs-12 mar_t10">

			<div class="panel panel-default">

				<div class="panel-heading">列表</div>

				<div class="table-responsive">

					<table class="table table-hover table-striped">

						<thead>

							<tr>

								<th><input id="checkAll" type="checkbox"
									v-on:click="checkAll"></th>

								<th>名称</th>

								<th>位置</th>

								<th>模版号</th>

								<th>封面图</th>

								<th>创建时间</th>

								<th>操作</th>

							</tr>

						</thead>

						<tbody class="listpage">

							@foreach($values as $value)

							<tr>

								<td><input type="checkbox" name="list" v-model="id"
									v-on:click="checkItem" v-bind:value="{{$value['id']}}">

								</td>

								<td>{{$value['name']}}</td>

								<td>{{$value['position']}}</td>

								<td>{{$value['model']}}</td>

								<td><img src="{{$value['pic']}}" alt="{{$value['name']}}"
									height="100"></td>

								<td>{{$value['created_at']}}</td>

								<td><a class="btn btn-primary"
									href="{{route('group.edit',$value['id'])}}" role="button">

										编辑 </a> <a class="btn btn-danger delete" href="javascript:void(0)"
									v-on:click="destroyItem('{{$value['id']}}')"
									data-toggle="modal" data-target="#destroyItem" role="button">

										删除 </a></td>

							</tr>

							@endforeach
						</tbody>
					</table>
				</div>
			</div>
			{{ $values->links() }}
		</div>
	</div>
</div>

<script type="text/javascript">
	table = new Vue({

		el : '.cblock',

		data : function() {

			return {

				id : [],

				search : '',

			}

		},

		methods : {

			destroyCollection : function() {

				var id = this.id;

				var ids = '';

				for (i = 0; i < id.length; i++) {

					ids += id[i] + ",";

				}

				ids = ids.substr(0, ids.length - 1);

				$.ajax({

					type : 'DELETE',

					url : '{{url()->current()}}' + '/' + ids,

					dataType : 'json',

					success : function(data) {

						if (data.code) {

							alert(data.msg);

							location.reload();

						}

					}

				})

			},

			checkAll : function() {

				var all = document.querySelectorAll("[name=list]");

				var checked = document.querySelectorAll("[name=list]:checked");

				if (all.length == checked.length) {

					for (j = 0; j < all.length; j++) {

						all[j].checked = false;

						this.id = [];

					}

				} else {

					for (j = 0; j < all.length; j++) {

						all[j].checked = true;

						this.id.push(Number(all[j].value))

					}

				}

			},

			checkItem : function() {

				var all = document.querySelectorAll("[name=list]");

				var checked = document.querySelectorAll("[name=list]:checked");

				var checkAll = document.querySelector("#checkAll");

				if (all.length !== checked.length) {

					checkAll.checked = false;

				} else {

					checkAll.checked = true;

				}

			},

			destroyItem : function(id) {

				this.id = [];

				this.id.push(id);

			},

		}

	});

	//        table.destroy();
</script>