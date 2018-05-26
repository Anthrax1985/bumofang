var locat = (window.location+'').split('/'); 
$(function(){if('main'== locat[3]){locat =  locat[0]+'//'+locat[2];}else{locat =  locat[0]+'//'+locat[2]+'/'+locat[3];};});


//菜单状态切换
var fmid = "fhindex";
var mid = "fhindex";
function siMenu(id,fid,MENU_NAME,MENU_URL){
	if(id != mid){
		$("#"+mid).removeClass();
		mid = id;
	}
	if(fid != fmid){
		$("#"+fmid).removeClass();
		fmid = fid;
	}
	$("#"+fid).attr("class","active open");
	$("#"+id).attr("class","active");
	top.mainFrame.tabAddHandler(id,MENU_NAME,MENU_URL);
	if(MENU_URL != "druid/index.html"){
		jzts();
	}
}

$(function(){

	
});

var USER_ID;

var user = "FH";	//用于即时通讯（ 当前登录用户）

$(function(){
	$.ajax({
		type: "POST",
		url: locat+'/head/getUname.do?tm='+new Date().getTime(),
    	data: encodeURI(""),
		dataType:'json',
		//beforeSend: validateData,
		cache: false,
		success: function(data){
			//alert(data.list.length);
			 $.each(data.list, function(i, list){
				 //登陆者资料
				 $("#user_info").html('<small>Welcome</small> '+list.NAME+'');
				 $("#user_info_VO").html(list.NAME+"/"+list.ROLE_NAME);
			/*	 $("#AVATAR").attr("src","static/img/bmf_logo.png");*/
				 if(list.AVATAR != null && list.AVATAR != ""){
					 $("#AVATAR").attr("src",list.AVATAR);
				 }
				 user = list.USERNAME;
				 USER_ID = list.USER_ID;//用户ID
				 if(list.USERNAME != 'admin'){
					 $("#adminmenu").hide();	//隐藏菜单设置
					 $("#adminzidian").hide();	//隐藏数据字典
					 $("#systemset").hide();	//隐藏系统设置
					 $("#productCode").hide();	//隐藏代码生成
				 }
				 if(list.ROLE_ID == "2"){
				 	 $("#systemset").show();
				 }
//				 online();//连接在线管理
			 });
		}
	});
});

//在线管理
var websocket;
function online(){
	if (window.WebSocket) {
		websocket = new WebSocket(encodeURI('ws://127.0.0.1:8889'));
		
		websocket.onopen = function() {
			//连接成功
			websocket.send('[join]'+user);
		};
		websocket.onerror = function() {
			//连接失败
		};
		websocket.onclose = function() {
			//连接断开
		};
		//消息接收
		websocket.onmessage = function(message) {
			var message = JSON.parse(message.data);
			if (message.type == 'count') {
				userCount = message.msg;
			}else if(message.type == 'goOut'){
				$("body").html("");
				goOut("此用户在其它终端已经早于您登录,您暂时无法登录");
			}else if(message.type == 'thegoout'){
				$("body").html("");
				goOut("您被系统管理员强制下线");
			}else if(message.type == 'userlist'){
				userlist = message.list;
			}
		};
	}
}

//在线总数
var userCount = 0;
function getUserCount(){
	websocket.send('[count]'+user);
	return userCount;
}
//用户列表
var userlist = "";
function getUserlist(){
	websocket.send('[getUserlist]'+user);
	return userlist;
}
//强制下线
function goOut(msg){
	alert(msg);
	window.location.href=locat+"/logout";
}
//强制某用户下线
function goOutUser(theuser){
	websocket.send('[goOut]'+theuser);
}






//修改个人资料
function editUserH(){
	 BootstrapDialog.show({cssClass:"one-row-dialog",
        message: $('<div></div>').load(locat+'/user/goEditU.do?USER_ID='+USER_ID+'&fx=head'),
        title: '修改资料',
      });
}

//系统设置
function editSys(){
	 BootstrapDialog.show({
         message: $('<div></div>').load(locat+'/head/goSystem.do'),
         title: '系统设置'
       });
}

//代码生成
function productCode(){
	 BootstrapDialog.show({cssClass:"three-row-dialog",
         message: $('<div></div>').load(locat+'/head/goProductCode.do'),
         title: '代码生成器'
       });
}

//数据字典
function zidian(){
	 BootstrapDialog.show({
         message: $('<div></div>').load(locat+'/dictionaries.do?PARENT_ID=0'),
         title: '数据字典'
       });
	 
}

//菜单
function menu(){
	 BootstrapDialog.show({cssClass:'three-row-dialog',
         message: $('<div id="leftMenuId"></div>').load(locat+'/menu.do'),
         title: '菜单编辑'
       });
	 
}

//切换菜单
function changeMenu(){
	websocket.send('[leave]'+user);
	window.location.href=locat+'/main/yes';
}

//清除加载进度
function hangge(){
	$("#jzts").hide();
}

//显示加载进度
function jzts(){
	$("#jzts").show();
}