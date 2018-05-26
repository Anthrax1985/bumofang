
function ajaxSave(target, url, action, codeErrorHandle, resetFun, afterHandle, jsonData){

    var data;
    var formItem;
    if(jsonData){
        data = jsonData;
    }else{
        formItem = getFormItem(target);
        data = serializeArray(formItem);
    }
        
    $.ajax({
        type: "POST",
        url: url,
        data: JSON.stringify(data),
        dataType:'json',
        contentType: "application/json;charset=UTF-8",
        cache: false,
        success: function(data){
            var isAdd = "save" == action || action.indexOf("save") == 0;
            hideSavingMessage(target);  
            if(200 == data.code){
                if(isAdd){
                    showSuccessMessage();
                    if(resetFun){
                        resetFun();
                    }else{
                        if(formItem)
                            formItem.reset();
                    }
                }else{
                    showSuccessMessage();
                    hideDialog(target);
                }

                if(afterHandle){
                    afterHandle();
                }else{
                	location.reload();
                }
            }else if(codeErrorHandle){
                codeErrorHandle(data.code);
            }else{
                bootbox.alert("保存失败");
            }
        },
        error: function(data){
            hideSavingMessage(target);  
            bootbox.alert("出现异常");
        }
    });
    
    showSavingMessage(target);
}


var isAjaxPost = false;
/***
 * 
 * {
 * 		target:
 * 		url:
 * 		data:
 * 		success:
 * 		fail:
 * 		error:
 * 		
 * }
 * @returns
 */
function ajaxPost(ajaxObject){
	if(isAjaxPost){
		return;
	}
	
	var target = ajaxObject.target;
	var url = ajaxObject.url;
	var data = ajaxObject.data;
	var success = ajaxObject.success;
	var fail = ajaxObject.fail;
	var error = ajaxObject.error;
	
    $.ajax({
        type: "POST",
        url: url,
        data: JSON.stringify(data),
        dataType:'json',
        contentType: "application/json;charset=UTF-8",
        cache: false,
        success: function(result){
        	isAjaxPost = false;
            if(200 == result.code){
                success(result);
            }else if(fail){
            	fail(data);
            }else{
                bootbox.alert("失败");
            }
        },
        error: function(result){
        	isAjaxPost = false;
            bootbox.alert("出现异常");
        }
    });
   
}

function serializeArray(formItem)
{
	var o = {};
	var a = $(formItem).serializeArray();
	$.each(a, function() {
    	if (o[this.name] !== undefined) {
        	if (!o[this.name].push) {
            	o[this.name] = [o[this.name]];
        	}
        	o[this.name].push(this.value || '');
    	} else {
        	o[this.name] = this.value || '';
    	}
	});
	return o;
};

function showSuccessMessage(){
	toastr.options = {
      "closeButton": false,
      "debug": false,
      "newestOnTop": false,
      "progressBar": false,
      "positionClass": "toast-bottom-center",
      "preventDuplicates": true,
      "onclick": null,
      "showDuration": "300",
      "hideDuration": "1000",
      "timeOut": "2000",
      "extendedTimeOut": "1000",
      "showEasing": "swing",
      "hideEasing": "linear",
      "showMethod": "fadeIn",
      "hideMethod": "fadeOut"
    }
    toastr.success("保存成功");
}

function showSavingMessage(target){
	$(target).text("保存中...");
    $(target).attr("disabled", "disabled");
}

function hideSavingMessage(target){
    $(target).text("保存");
	$(target).removeAttr("disabled");
}

function hideDialog(formItem){
    var id = getDialogId(formItem);
    if(id){
        BootstrapDialog.getDialog(id).close();
    }
}

function getDialogId(formItem){
    var parentEle = formItem.parentNode;
    while(parentEle){
        var role = parentEle.attributes["role"];
        if(role && role.value=="dialog"){
            return parentEle.id;
        }

        parentEle = parentEle.parentNode;
    }

    return "";
}

function getFormItem(btnItem){
    var parentEle = btnItem.parentNode;
    while(parentEle){
        var method = parentEle.attributes["method"];
        if(method && method.value=="post"){
            return parentEle;
        }

        parentEle = parentEle.parentNode;
    }    
}
