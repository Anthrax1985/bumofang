function initTableInfo(){
	//1.初始化Table
	var oTable = new TableInit();
	 oTable.Init();
	 
    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();
    
    //3.初始化selectedTable
    var selectTable = new selectTableInit();
    selectTable.Init();
}


var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#productList').bootstrapTable({
            url: 'product/rest/list',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 1,                       //每页的记录行数（*）
            pageList: [20, 50, 100],        //可供选择的每页的行数（*）
            clickToSelect: true,                //是否启用点击选中行
            height: 300,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            columns: [{
                checkbox: true
            }, {
                field: 'id',
                title: '产品id'
            }, {
                field: 'productName',
                title: '品名'
            }, {
                field: 'patternPicture2',
                title: '花型图'
            }, {
                field: 'qualityPicture4',
                title: '质地图'
            }, {
                field: 'id',
                title: '颜色'
            }, {
                field: 'id',
                title: '风格'
            }, {
                field: 'productCraft',
                title: '工艺'
            }, {
                field: 'id',
                title: '材质'
            }, {
                field: 'id',
                title: '应用'
            }
            ]
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,   //页面大小
            offset: params.offset,  //页码
            searchKey: $("#search_key").val(),
            productCategoryId: $("#productCategorySelect").val(),
            supplierId: $("#supplierCode").val()
        };
        return temp;
    };
    return oTableInit;
};

var selectTableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#selectProduct').bootstrapTable({
            url: '',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: false,                   //是否显示分页（*）
            toolbar: "#toolbar",
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 100,                       //每页的记录行数（*）
            pageList: [10, 20, 30, 50],        //可供选择的每页的行数（*）
            clickToSelect: true,                //是否启用点击选中行
            height: 240,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "productSkuId",                     //每一行的唯一标识，一般为主键列
            columns: [{
                checkbox: true
            }, {
                field: 'id',
                title: '产品id'
            }, {
                field: 'productName',
                title: '品名'
            }, {
                field: 'patternPicture2',
                title: '花型图'
            }, {
                field: 'qualityPicture4',
                title: '质地图'
            }, {
                field: 'id',
                title: '颜色'
            }, {
                field: 'id',
                title: '风格'
            }, {
                field: 'productCraft',
                title: '工艺'
            }, {
                field: 'id',
                title: '材质'
            }, {
                field: 'id',
                title: '应用'
            }
            ]
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,   //页面大小
            offset: params.offset,  //页码
            searchKey: $("#search_key").val()
        };
        return temp;
    };
    return oTableInit;
};


var ButtonInit = function () {
    var oInit = new Object();
    var postdata = {};

    oInit.Init = function () {
        //初始化页面上面的按钮事件
    	
    	$('#btn_query').click(function () {
            $('#productList').bootstrapTable('refresh');
        });
    	$('#productList').on('check.bs.table',function(e, row){
    		var selectArray = $('#selectProduct').bootstrapTable('getData', null);
    		var found = false;
    		for(var i=0;i<selectArray.length;i++){
    			var oneData = selectArray[i];
    			if(oneData.productSkuId == row.productSkuId){
    				found = true;
    				break;
    			}
    		}
    		if(!found){
    			$('#selectProduct').bootstrapTable('append', row);
    		}
    	});
    	$('#productList').on('uncheck.bs.table',function(e, row){
    		$('#selectProduct').bootstrapTable('removeByUniqueId', row.productSkuId);
    	});
    	$("input[name='amount']").blur(function(){
    		alert(this);
    	});
    	
    	var $remove = $('#del');
    	var $table = $('#selectProduct');

    	$remove.click(function () {
	        var ids = $.map($table.bootstrapTable('getSelections'), function (row) {
	            return row.productSkuId;
	        });
	        if (ids.length == 0 ) {
	            bootbox.alert("请选择一行删除!");
	            return;
	        }
	        $table.bootstrapTable('remove', {
	            field: 'productSkuId',
	            values: ids
	        });
	    });
    	
    };
    

    return oInit;
};