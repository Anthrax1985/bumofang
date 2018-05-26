package com.fh.common.model;

public enum SysLogEnum {
	
	ACTION_LOGIN("用户登录", "后台登录"),
	
	PRODUCT_ADD("产品中心", "添加产品"),
	PRODUCT_EDIT("产品中心", "编辑产品"),
	PRODUCT_TO_BLACK("产品中心", "删除产品"),
	PRODUCT_ACTIVATE("产品中心", "激活产品"),
	
	MEMBER_EDIT("用户管理", "编辑会员"),
	MEMBER_TO_BLACK("用户管理", "拉黑会员"),
	MEMBER_ACTIVATE("用户管理", "激活会员"),
	MEMBER_AUDIT("用户管理", "审核会员"),
	
	MEMBER_MESSAGE_REPLY("消息管理", "回复留言"),
	SYSTEM_MESSAGE_RELEASE("消息管理", "发布系统消息"),
	SYSTEM_MESSAGE_AUDIT("消息管理", "审核系统消息"),
	
	USER_CHANGE_PASWORD("我的账户", "修改密码"),
	USER_CHANGE_EMAIL("我的账户", "修改邮箱"),
	USER_UPLOAD_AVATAR("我的账户", "上传头像"),
	USER_ADD_USER("我的账户", "新建账户"),
	USER_EDIT_USER("我的账户", "编辑账户"),	
	;
	
	private String name;
	private String content;
	
	private SysLogEnum(String name, String content){
		this.name = name;
		this.content = content;
	}

	public String getName() {
		return name;
	}
	
	public String getContent() {
		return content;
	}

	public String toString(){
		return name;
	}

}
