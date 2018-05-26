package com.fh.extend.logic;

import org.apache.commons.lang.StringUtils;

import com.fh.common.model.RedisKeySuffixEnum;
import com.fh.util.redis.RedisUtil;

/**
 * 用户Token管理工具
 * 
 * @comment
 * @update
 */
public class AccessTokenManager {
	
	private static AccessTokenManager instance = new AccessTokenManager();
	
	private AccessTokenManager(){
	}
	
	public static AccessTokenManager getInstance(){
		return instance;
	}

	public AccessToken getToken(String token){
		if(!StringUtils.isBlank(token) && 
			RedisUtil.exists(RedisKeySuffixEnum.USER_TOKEN.getKey() + token)){
			AccessToken assessToken = (AccessToken) RedisUtil.get(RedisKeySuffixEnum.USER_TOKEN.getKey() + token);
			return assessToken;
		}
		
		return null;
	}
	
	public String putToken(String userId){
		AccessToken token = new AccessToken(userId);
    	RedisUtil.set(RedisKeySuffixEnum.USER_TOKEN.getKey() + token.getToken(), 
    			token, RedisKeySuffixEnum.USER_TOKEN.getExpireTime());
		
		return token.getToken();
	}
	
	public void updateToken(String token){
		if(!StringUtils.isBlank(token) && 
			RedisUtil.exists(RedisKeySuffixEnum.USER_TOKEN.getKey() + token)){
			AccessToken assessToken = (AccessToken) RedisUtil.get(RedisKeySuffixEnum.USER_TOKEN.getKey() + token);
			if(assessToken == null){
				return;
			}
			RedisUtil.set(RedisKeySuffixEnum.USER_TOKEN.getKey() + token, token, 
					RedisKeySuffixEnum.USER_TOKEN.getExpireTime());
		}
	}

}
