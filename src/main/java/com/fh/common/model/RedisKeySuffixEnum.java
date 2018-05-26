package com.fh.common.model;

public enum RedisKeySuffixEnum {
	
	REGISTER_MOBILE_CODE("bumofang_register_mobile_code_", 30 * 60), // register_mobile_code_{mobile}格式

/*    USER_TOKEN("bumofang_user_token_", 120); // user_token_{mobile}格式
*/	
	USER_TOKEN("bumofang_user_token_", 7 * 60 * 60 * 24); // user_token_{mobile}格式
	
	private String key;
	private long expireTime; 
	
	private RedisKeySuffixEnum(String key, long expireTime){
		this.key = key;
		this.expireTime = expireTime;
	}
	
	public String getKey(){
		return key;
	}

	public long getExpireTime() {
		return expireTime;
	}
	

}
