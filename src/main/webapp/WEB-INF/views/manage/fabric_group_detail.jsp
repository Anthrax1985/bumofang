<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<div class="row">
	<div class="col-xs-12 col-sm-12">
		<div class="cblock">
			<!-- Modal -->
			<div class="modal fade" id="fabricList" tabindex="-1" role="dialog"
				aria-labelledby="fabricList" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">面料列表</h4>
						</div>
						<div class="modal-body fabricTable"
							style="height: 500px; overflow-y: auto">
							<div class="input-group">
								<input type="text" name="search" class="form-control"
									v-model="text" placeholder="按编号，标签，色系，录入人筛选">
								<div class="input-group-btn">
									<button class="btn btn-default" type="button"
										v-on:click="search()">
										<i class="glyphicon glyphicon-search"></i> 搜索
									</button>
								</div>
							</div>
							<table class="table" style="margin-bottom: 0">
								<thead>
									<tr>
										<th><input id="fabric" type="checkbox"
											v-on:click="checkAll('fabric')"></th>
										<th>序号</th>
										<th>编号</th>
										<th>品名</th>
										<th>色号</th>
										<th>成分</th>
									</tr>
								</thead>
								<tbody class="listpage">
									{{--@foreach($fabrics as $fabric)--}}
									<tr v-for="fabric in fabrics" :id="'fabric'+fabric.id">
										<td><input type="checkbox" name="fabric" v-model="fid"
											v-on:click="checkItem('fabric')" v-bind:value="fabric.id">
										</td>
										<td>@{{fabric.id}}</td>
										<td>@{{fabric.number}}</td>
										<td>@{{fabric.name}}</td>
										<td>@{{fabric.color}}</td>
										<td>@{{fabric.ingredients}}</td>
									</tr>
									{{--@endforeach--}}
								</tbody>
							</table>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-success"
								v-on:click="addItem()" data-dismiss="modal">确定</button>
						</div>
					</div>
				</div>
			</div>
			<!-- Modal -->
			<div class="modal fade" id="accessoryList" tabindex="-1"
				role="dialog" aria-labelledby="accessoryList" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">配饰列表</h4>
						</div>
						<div class="modal-body accessoryTable"
							style="height: 500px; overflow-y: auto">
							<div class="input-group">
								<input type="text" name="search" class="form-control"
									v-model="text" placeholder="按编号，名称，标签，录入人筛选">

								<div class="input-group-btn">

									<button class="btn btn-default" type="button"
										v-on:click="search2()">
										<i class="glyphicon glyphicon-search"></i> 搜索

									</button>

								</div>

							</div>

							<table class="table" style="margin-bottom: 0">

								<thead>

									<tr>

										<th><input id="accessory" type="checkbox"
											v-on:click="checkAll('accessory')"></th>

										<th>序号</th>

										<th>编号</th>

										<th>品名</th>

										<th>分类</th>

										<th>材料</th>

									</tr>

								</thead>

								<tbody class="listpage">

									{{--@foreach($fabrics as $fabric)--}}

									<tr v-for="accessory in accessories"
										:id="'accessory'+accessory.id">

										<td><input type="checkbox" name="accessory" v-model="aid"
											v-on:click="checkItem('accessory')"
											v-bind:value="accessory.id">

										</td>

										<td>@{{accessory.id}}</td>

										<td>@{{accessory.number}}</td>

										<td>@{{accessory.name}}</td>

										<td>@{{accessory.category}}</td>

										<td>@{{accessory.material}}</td>

									</tr>

									{{--@endforeach--}}

								</tbody>

							</table>

						</div>

						<div class="modal-footer">

							<button type="button" class="btn btn-success"
								v-on:click="addItem2()" data-dismiss="modal">确定</button>

						</div>

					</div>

				</div>

			</div>

			<div class="row">

				<div class="col-xs-12 col-sm-12">

					<form role="form" id="form"
						action="{{$is_edit?route('group.update',$value->id):route('group.store')}}"
						method="POST" onsubmit="return formcheck()"
						enctype="multipart/form-data">

						{{ csrf_field() }} @if($is_edit) {{ method_field('PUT') }} @endif
						<div class="row">
							<div class="col-sm-4">
								<div class="form-group">

									<label for="input1">名称</label> <input type="text"
										class="form-control" id="input1" placeholder="名称"
										v-model="name" name="name">

								</div>

								<div class="form-group">

									<label for="input2">位置</label> <select class="form-control"
										id="input2" v-model="position" name="position"
										v-on:click="selectType">

										<option value="首页">首页</option>

										<option value="2D模型">2D模型</option>

									</select>

								</div>

								<div class="form-group model_id">

									<label for="input3">模版号</label> <input type="text"
										class="form-control" id="input3" placeholder="模版号"
										v-model="model" name="model">

								</div>

								<div class="form-group">

									<label for="input5">组封面图</label>
									<div class="row">
										<div class="col-sm-8">
											<a href="javascript:void(0)" class="pic newspic"><img
												src="{{$value->pic or asset('admin/img/selectimg.png')}}"></a>

											<input type="file" id="input5" name="pic"
												onchange="showPic(this)" style="display: none">
										</div>
									</div>

								</div>

								<div class="form-group">

									<label for="input3">组描述</label>

									<textarea v-model="description" name="description"
										class="form-control" placeholder="填写描述" rows="5"></textarea>

								</div>
							</div>
						</div>


						<div class="form-group">

							<button type="button" class="btn btn-info" data-toggle="modal"
								data-target="#fabricList">选择面料</button>

							<input type="text" class="form-control" id="input4"
								placeholder="选择面料" name="fabric_id" v-model="fid"
								style="display: none">
						</div>
						<table class="table">
							<thead>
								<tr>
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

								</tr>

							</thead>

							<tbody class="listpage allTable">

								@if($is_edit) @foreach($fabrics as $fabric)

								<tr>

									<td>{{$fabric['name']}}</td>

									<td>{{$fabric['number']}}</td>

									<td>{{$fabric['color']}}</td>

									<td>{{$fabric['ingredients']}}</td>

									<td>{{$fabric['width']}}</td>

									<td>{{$fabric['flower_size']}}</td>

									<td>{{$fabric['flower_distance']}}</td>

									<td>{{$fabric['gram']}}</td>

									<td><img src="{{$fabric['flat_picture']}}"
										alt="{{$fabric['name']}}" height="100"></td>

									<td><img src="{{$fabric['art_picture']}}"
										alt="{{$fabric['name']}}" height="100"></td>

									<td><img src="{{$fabric['stream_picture']}}"
										alt="{{$fabric['name']}}" height="100"></td>

									<td>{{$fabric->user->nickname}}</td>

									<td>{{$fabric['created_at']}}</td>

									<td>{{$fabric['group']}}</td>

									<td>{{$fabric['use_labels']}}</td>

									<td>{{$fabric['color_labels']}}</td>

									<td>{{$fabric['style_tags']}}</td>

									<td>{{$fabric['collocation_tag']}}</td>

									<td>{{$fabric['design_tags']}}</td>

									<td>{{$fabric['other_tags']}}</td>

									<td>{{$fabric['price']}}</td>

								</tr>

								@endforeach @endif

							</tbody>

						</table>

						<div class="form-group accessory_list">

							<button type="button" class="btn btn-info" data-toggle="modal"
								data-target="#accessoryList">选择配饰</button>

							<input type="text" class="form-control" id="input11"
								placeholder="选择面料" name="accessory_id" v-model="aid"
								style="display: none">

						</div>

						<table class="table accessory_list">

							<thead>

								<tr>

									<th>编号</th>

									<th>名称</th>

									<th>品类</th>

									<th>材质</th>

									<th>规格</th>

									<th>系列</th>

									<th>艺术图</th>

									<th>平面图</th>

									<th>备注</th>

									<th>录入人</th>

									<th>录入时间</th>

								</tr>

							</thead>

							<tbody class="listpage allTable2">

								@if($is_edit) @foreach($accessories as $accessory)

								<tr>

									<td>{{$accessory['number']}}</td>

									<td>{{$accessory['name']}}</td>

									<td>{{$accessory['category']}}</td>

									<td>{{$accessory['material']}}</td>

									<td>{{$accessory['specifications']}}</td>

									<td>{{$accessory['series']}}</td>

									<td><img src="{{$accessory['positive_picture']}}"
										alt="{{$accessory['name']}}" height="100"></td>

									<td><img src="{{$accessory['art_picture']}}"
										alt="{{$accessory['name']}}" height="100"></td>

									<td>{{$accessory['remarks']}}</td>

									<td>{{$accessory->user->nickname}}</td>

									<td>{{$accessory['created_at']}}</td>

								</tr>

								@endforeach @endif

							</tbody>

						</table>

						<button type="submit" class="btn btn-default">确定</button>

					</form>

				</div>

			</div>

		</div>

	</div>

</div>

<script type="text/javascript">
        function formcheck() {
            var name = document.getElementsByName('name');
            var position = document.getElementsByName('position');
            var model = document.getElementsByName('model');
            var fabric_id = document.getElementsByName('fabric_id');
            var accessory_id = document.getElementsByName('accessory_id');
            var pic = document.getElementsByName('pic');
            console.log(pic);
            if (name[0].value == undefined || name[0].value == "") {
                alert('请输入名称！');
                return false;
            }
            if (position[0].value == undefined || position[0].value == "") {
                alert('请选择位置！');
                return false;
            }
            if ($(model[0]).is(":visible")) {
                if (model[0].value == undefined || model[0].value == "") {
                    alert('请输入模版号！');
                    return false;
                }
            }
            @if(!$is_edit)
            if (pic[0].files.length == 0) {
                alert('请选择封面图！');
                return false;
            }
            @endif
                return true;
        }

        var num = 2;

        var num2 = 2;

        var total;

        var total2;

        var vue = new Vue({

            el: '.cblock',

            data: {

                name: '{{$value->name or ''}}',

                position: '{{$value->position or '首页'}}',

                model: '{{$value->model or ''}}',

                fid: '{{$fabric_id or ''}}'.split(','),

                aid: '{{$accessory_id or ''}}'.split(','),

                fabrics: [],

                accessories: [],

                text: '',

                description: '{{$value->description or ''}}'

            },

            methods: {

                selectType: function () {

                    var position = this.position;

                    if (position == '首页') {

                        $(".model_id").hide();

                    } else if (position == '2D模型') {

                        $(".model_id").show();

                    }

                },

                search: function () {

                    $.ajax({

                        type: "GET",

                        url: '{{route('fabric.index')}}',

                        dataType: 'json',

                        data: {

                            api: 1,

                            page: 1,

                            per: 20,

                            search: this.text

                        },

                        success: function (data) {

                            total = data.last_page;

                            vue.fabrics = data.data;

                            num = 2;

                        }

                    });

                },

                search2: function () {

                    $.ajax({

                        type: "GET",

                        url: '{{route('accessory.index')}}',

                        dataType: 'json',

                        data: {

                            api: 1,

                            page: 1,

                            per: 20,

                            search: this.text

                        },

                        success: function (data) {

                            total = data.last_page;

                            vue.accessories = data.data;

                            num = 2;

                        }

                    });

                },

                checkAll: function (name) {

                    var all = document.querySelectorAll("[name=" + name + "]");
                    var checkAll = document.querySelector("#" + name);
                    var checked = document.querySelectorAll("[name=" + name + "]:checked");

                    if (all.length == checked.length) {

                        for (j = 0; j < all.length; j++) {

                            all[j].checked = false;

                            if (name == 'fabric') {

                                this.fid = [];

                            } else {

                                this.aid = [];

                            }

                        }
                        checkAll.checked = false;

                    } else {

                        for (j = 0; j < all.length; j++) {

                            all[j].checked = true;

                            if (name == 'fabric') {

                                this.fid.push(Number(all[j].value))

                            } else {

                                this.aid.push(Number(all[j].value))

                            }

                        }
                        checkAll.checked = true;

                    }

                },

                checkItem: function (name) {

                    var all = document.querySelectorAll("[name=" + name + "]");

                    var checked = document.querySelectorAll("[name=" + name + "]:checked");

                    var checkAll = document.querySelector("#" + name + "");

                    if (all.length !== checked.length) {

                        checkAll.checked = false;

                    } else {

                        checkAll.checked = true;

                    }

                },

                addItem: function () {
                    var fid = this.fid;

                    var ids = '';

                    for (i = 0; i < fid.length; i++) {

                        ids += fid[i] + ",";

                    }

                    ids = ids.substr(0, ids.length - 1);
                    $(".allTable").html('');
                    $.ajax({

                        type: "GET",

                        url: '{{route('fabric.index')}}',

                        dataType: 'json',

                        data: {

                            ids: ids

                        },

                        success: function (data) {

                            for (var i = 0; i < data.length; i++) {
                                console.log(data[i].created_at)
                                if (data !== '' && data !== undefined) {

                                    $(".allTable").append('<tr>' + '<td>' + data[i].number + '</td>' +

                                        '<td>' + data[i].name + '</td>' +

                                        '<td>' + data[i].color + '</td>' +

                                        '<td>' + data[i].ingredients + '</td>' +

                                        '<td>' + data[i].width + '</td>' +

                                        '<td>' + data[i].flower_size + '</td>' +

                                        '<td>' + data[i].flower_distance + '</td>' +

                                        '<td>' + data[i].gram + '</td>' +

                                        '<td><img src="' + data[i].flat_picture + '" alt="' + data[i].name + '" height="100">' +
                                        '</td>' +

                                        '<td><img src="' + data[i].art_picture + '" alt="' + data[i].name + '" height="100"></td>' +

                                        '<td><img src="' + data[i].stream_picture + '" alt="' + data[i].name + '" height="100">' +

                                        '</td>' +

                                        '<td>' + data[i].user + '</td>' +

                                        '<td>' + data[i].created_at + '</td>' +

                                        '<td>' + data[i].group + '</td>' +

                                        '<td>' + data[i].use_labels + '</td>' +

                                        '<td>' + data[i].color_labels + '</td>' +

                                        '<td>' + data[i].style_tags + '</td>' +

                                        '<td>' + data[i].collocation_tag + '</td>' +

                                        '<td>' + data[i].design_tags + '</td>' +

                                        '<td>' + data[i].other_tags + '</td>' +

                                        '<td>' + data[i].price + '</td>' + '<tr>');

                                }

                            }

                        }

                    });


                },

                addItem2: function () {

                    var aid = this.aid;

                    var ids = '';

                    for (i = 0; i < aid.length; i++) {

                        ids += aid[i] + ",";

                    }

                    ids = ids.substr(0, ids.length - 1);
                    $(".allTable2").html('');
                    $.ajax({

                        type: "GET",

                        url: '{{route('accessory.index')}}',

                        dataType: 'json',

                        data: {

                            ids: ids

                        },

                        success: function (data) {

                            for (var i = 0; i < data.length; i++) {
                                console.log(data[i].created_at)
                                if (data !== '' && data !== undefined) {

                                    $(".allTable2").append('<tr>' + '<td>' + data[i].number + '</td>' +

                                        '<td>' + data[i].name + '</td>' +

                                        '<td>' + data[i].category + '</td>' +

                                        '<td>' + data[i].material + '</td>' +

                                        '<td>' + data[i].specifications + '</td>' +

                                        '<td>' + data[i].series + '</td>' +


                                        '<td><img src="' + data[i].art_picture + '" alt="' + data[i].name + '" height="100">' +
                                        '</td>' +

                                        '<td><img src="' + data[i].positive_picture + '" alt="' + data[i].name + '" height="100"></td>' +

                                        '<td>' + data[i].remarks + '</td>' +

                                        '<td>' + data[i].users + '</td>' +

                                        '<td>' + data[i].created_at + '</td><tr>');

                                }

                            }

                        }

                    });

                }

            }

        });

        vue.selectType();

        $(".fabricTable").scroll(function () {

            var top = $(this).scrollTop();

            var height = $(this).height();

            var mainheight = $(this).find('table').height() + $(this).find('.input-group').height();

            if (top + height == mainheight) {

                if (total >= num) {

                    $.ajax({

                        type: "GET",

                        url: '{{route('fabric.index')}}',

                        dataType: 'json',

                        data: {

                            api: 1,

                            page: num,

                            per: 20,

                        },

                        success: function (data) {

                            vue.fabrics = vue.fabrics.concat(data.data);

                            num++;

                        }

                    });

                }

            }

        });

        $(".accessoryTable").scroll(function () {

            var top = $(this).scrollTop();

            var height = $(this).height();

            var mainheight = $(this).find('table').height() + $(this).find('.input-group').height();

            if (top + height == mainheight) {

                if (total2 >= num2) {

                    $.ajax({

                        type: "GET",

                        url: '{{route('accessory.index')}}',

                        dataType: 'json',

                        data: {

                            api: 1,

                            page: num2,

                            per: 20,

                        },

                        success: function (data) {

                            vue.values = vue.values.concat(data.data);

                            num2++;

                        }

                    });

                }

            }

        });

        $.ajax({

            type: "GET",

            url: '{{route('fabric.index')}}',

            dataType: 'json',

            data: {

                api: 1,

                page: 1,

                per: 20,

            },

            success: function (data) {

                total = data.last_page;

                vue.fabrics = data.data;

            }

        });

        $.ajax({

            type: "GET",

            url: '{{route('accessory.index')}}',

            dataType: 'json',

            data: {

                api: 1,

                page: 1,

                per: 20,

            },

            success: function (data) {

                total2 = data.last_page;

                vue.accessories = data.data;

            }

        });


        $(".newspic").click(function () {

            $(this).siblings('input').click();

        });

        function showPic(data) {

            if (data.files && data.files[0]) {

                var reader = new FileReader();

                reader.onload = function (evt) {

//                    console.log(data.parentNode);
//                    console.log(data.parentNode.childNodes);
                    data.parentNode.childNodes[0].childNodes[0].src = evt.target.result;

                }

                reader.readAsDataURL(data.files[0]);

            }

        }

    </script>