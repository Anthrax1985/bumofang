package com.fh.extend.logic;

import com.fh.common.model.RedisKeySuffixEnum;
import com.fh.extend.cache.Cache;
import com.fh.extend.util.AuthCodeUtil;
import com.fh.util.redis.RedisUtil;

/**
 * 验证码缓存管理
 * @author jill
 *
 */
public class AuthCodeManager {
	
	private static AuthCodeManager instance = new AuthCodeManager();
	
	Cache<String, String> objectCache = null;
	
	private AuthCodeManager(){
	}
	
	public static AuthCodeManager getInstance(){
		return instance;
	}

	public String getCode(String mobile){
		Object authCode = RedisUtil.get(RedisKeySuffixEnum.REGISTER_MOBILE_CODE.getKey() 
				+ mobile);
		if(authCode == null){
			return null;
		}
		
		return (String)authCode;
	}
	
	public String putCode(String mobile){
		
		String authCode = AuthCodeUtil.getCode();
		RedisUtil.set(RedisKeySuffixEnum.REGISTER_MOBILE_CODE.getKey() + mobile, authCode,
				RedisKeySuffixEnum.REGISTER_MOBILE_CODE.getExpireTime());
		return authCode;
	}

}
