package com.fh.common.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fh.util.PageData;
import com.yunpian.sdk.util.JsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public enum ResponseMessageEnum {
	SUCCESS(200, "success"), 
	FAIL(400, "fail"), 
	VISIT_NOT_AUTHORIZED(401, "未授权"), 
	ARGUMENT_ERROR(402,"参数错误"), 
	ARGUMENT_EMPTY(403,"参数为空"), 
	ARGUMENT_EXCEPTION(407, "参数存在异常"), 
	ARGUMENT_TOKEN_EMPTY(409, "Token为空"), 
	ARGUMENT_TOKEN_INVALID(410, "Token无效"), 
	SERVER_ERROR(501, "服务端异常"), 
	SERVER_SQL_ERROR(503,"数据库操作出现异常"), 
	SERVER_DATA_REPEAT(504, "服务器数据已存在"), 
	SERVER_DATA_NOTEXIST(505,"数据不存在"), 
	SERVER_DATA_STATUS_ERROR(506, "数据状态错误"), 
	SERVER_SMS_SEND_FAIL(701, "短信发送失败"),
	
	ERROR_LOGIN(601, "用户名或密码错误"), 
	ERROR_MOBILE_EXIST(602, "该手机号已注册"),
	ERROR_AUTH_CODE(603, "验证码错误"),
	ERROR_USER_NOT_EXIT(604, "用户不存在"),
	ERROR_CONFIRM_SHOPCART(605, "提交订单出现错误"),
	ERROR_DEVICE_REGISTERED(607,"该机型已注册"),
	ERROR_USER_IN_BLACKLIST(608,"对不起，您在黑名单内，请联系客服！"),
	ERROR_MOBILE_FORMAT(609,"您的手机号格式不正确！"),
	ERROR_DEVICE_NOT_REGISTERED(610,"本设备未注册"),
	ERROR_MOBILE_NOT_MATCH_DEVICE(611,"该手机号不是本设备的绑定账号"),
	

	ERROR_REPEAT_FAVOR(702, "重复收藏"),
	ERROR_NOT_FAVOR(703, "未收藏该产品"),
	ERROR_NOT_3D_MATCH_METHOD(704, "未收藏该产品"),

	ADD_CART_SUCCESS(200, "添加宝贝成功"),
	ERROR_CART_EXIST(801, "购物车中已存在"),

	ERROR_SEARCH_EXIST(802, "请输入至少三位号码以确保搜索的精准度"),
	; 

	private int code;
	private String message;

	private ResponseMessageEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public String toString() {
		Map rsMap = new HashMap();
		rsMap.put("code", Integer.valueOf(this.code));
		rsMap.put("msg", this.message);
		JSONObject jsonNode = JSONObject.fromObject(rsMap);
		return jsonNode.toString();
	}

	public String appendMapToString(Map<String, Object> appendMap) {
		Map rsMap = new HashMap();
		rsMap.put("data", appendMap);
		rsMap.put("code", code);
		rsMap.put("msg", this.message);
		JSONObject jsonNode = JSONObject.fromObject(rsMap);
		return jsonNode.toString();
	}

	public String appendObject2String(Object obj) {
		if(obj == null){
			obj = new HashMap();
		}
		
		Map rsMap = new HashMap();
		rsMap.put("code", code);
		rsMap.put("msg", this.message);
		JSONObject jsonNode = JSONObject.fromObject(rsMap);
		
		JSONObject dataJson = JSONObject.fromObject(obj);
		jsonNode.put("data", dataJson);

		return jsonNode.toString();
	}

	public String appendArray2String(Object[] obj) {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		rsMap.put("code", code);
		rsMap.put("msg", this.message);
		JSONObject jsonNode = JSONObject.fromObject(rsMap);
		if (obj != null && obj.length > 0) {
			JSONArray jsonArray = JSONArray.fromObject(obj);
			jsonNode.put("data", jsonArray);
		}

		return jsonNode.toString();
	}
	public String appendEmptyData() {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		rsMap.put("code", code);
		rsMap.put("msg", this.message);
		rsMap.put("data", new HashMap<String, String>());
		JSONObject jsonNode = JSONObject.fromObject(rsMap);
		
		return jsonNode.toString();
	}

	public String appendMapListToString(List<Map<String, Object>> appendMapList) {
		Map rsMap = new HashMap();
		rsMap.put("data", appendMapList);
		rsMap.put("code", code);
		rsMap.put("msg", this.message);
		JSONObject jsonNode = JSONObject.fromObject(rsMap);
		return jsonNode.toString();
	}

	public String appendPageDataListToString(List<PageData> appendMapList) {
		Map rsMap = new HashMap();
		rsMap.put("data", appendMapList);
		rsMap.put("code", code);
		rsMap.put("msg", this.message);
		JSONObject jsonNode = JSONObject.fromObject(rsMap);
		return jsonNode.toString();
	}

	public String appendObject(Object obj) {
		if(obj == null){
			obj = new HashMap();
		}

		Map rsMap = new HashMap();
		rsMap.put("code", code);
		rsMap.put("msg", this.message);
		rsMap.put("data", obj);
		
		return JsonUtil.toJson(rsMap);
	}
	
	public int getCode() {
		return this.code;
	}
	public String paging(int total,Object obj){
		if(obj == null){
			obj = new HashMap<String,Object>();
		}
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		rsMap.put("code", code);
		rsMap.put("msg", this.message);
		rsMap.put("total", total);
		rsMap.put("rows", obj);
		
		return JsonUtil.toJson(rsMap);
	}
}