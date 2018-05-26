package com.fh.extend.logic;

import java.io.Serializable;

import com.fh.util.encrypt.EncryptUtil;

/**
 * Token WMS管理实体
 * 
 * @comment
 * @update
 */
public class AccessToken implements Serializable {

	/** 
	 *  
	 */
	private static final long serialVersionUID = 4759692267927548118L;

	private String token;// AccessToken字符串

	private String userId;

	
	public AccessToken(){
	}
	
	public AccessToken(String userId){
		this.userId = userId;
//		this.token = EncryptUtil.encrypt(userId, System.currentTimeMillis() + "");
		this.token = EncryptUtil.encrypt(userId, "123456");
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
