var batchUploader = {
	url: baseUrl + "batch_upload",
	arr:[],
	init:function(finishCallback){
		var total = 0;
		var loaded = 0;
		var self = this;
		self.arr = [];
		var fileUploader = $('#fileupload').fileupload({
			url: self.url,
			dataType: 'json',
			accept_file_types: /(\.|\/)(gif|jpe?g|png)$/i,
			start:function(e, uploader){
				$.ajax({
					url: baseUrl + 'clear_session',
					asnyc : true,
					success: function(){
						console.log("session clear");
					}
				});

				total = 0;
				loaded = 0;
				self.arr = [];
				$(".progress-bar").remove();
				$("#progress").append($("<div class='progress-bar progress-bar-success'></div>"));

				$("#files").html("");
				uploader._progress.loaded = 0;
				uploader._progress.total = 0;
				uploader._progress.bitrate = 0;
			},
			done: function (e, data) {
				$.each(data.result.files, function (index, file) {
					var tmpArr = file.name.split(".");
					var template_value = tmpArr[0];
					var obj = {"template_value":template_value, "url":file.url};
					self.arr.push(obj);

					var files = $(".files").find("p");
					$("<p id='" + template_value + "'/>").text(file.name).appendTo('#files');
					if(files.length > 4){
						files[0].remove();
					}
				});

				loaded += data.loaded;

				if(loaded == total){
					finishCallback(self.arr);
				}

//			            httpUtil.postData("/index.php/pc_admin/batch_upload_finish", {"data":arr}, function(result){
//				            	$.each(result.files, function (index, file) {
//					                if(file["error"] != undefined){
//					                	var template_value = file.template_value;	                	
//					                	$("#" + template_value).append("<span style='padding-left:20px;color:red'>" + file.error + "</span>");
//					                }
//					            });
//					            result.ret;
//				        });
			},
			progressall: function (e, data) {
				var progress = parseInt(data.loaded / data.total * 100, 10);
				$('#progress .progress-bar').css(
					'width',
					progress + '%'
				);

				total = data.total;
			}
		}).prop('disabled', !$.support.fileInput)
			.parent().addClass($.support.fileInput ? undefined : 'disabled');

	}
};

