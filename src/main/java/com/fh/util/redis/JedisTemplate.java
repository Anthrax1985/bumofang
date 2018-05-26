package com.fh.util.redis;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import redis.clients.jedis.exceptions.JedisException;

public class JedisTemplate {
	private static RedisTemplate<String, String> template;
    private static String keyPrefix;
    
	public void setTemplate(RedisTemplate<String, String> template) {
		JedisTemplate.template = template;
	}

	public void setKeyPrefix(String keyPrefix) {
		JedisTemplate.keyPrefix = keyPrefix;
	}

	/**
     * 获取缓存-String.
     * @param key 键
     * @return 值
     */
    public static String get(String key) {
        String value = null;
        key = keyPrefix + key;
        try {
            if (template.hasKey(key)) {
            	ValueOperations<String, String> opsValue = template.opsForValue();
            	value = opsValue.get(key).trim();
                value = StringUtils.isNotBlank(value) && !"nil".equalsIgnoreCase(value) ? value.trim() : null;
            }
        } catch (JedisException e) {
            throw e;
        }
        return value;
    }
    
    /**
     * 从HashMap中获取单个属性-String.
     * @param key 关键字
     * @param property 属性
     * @return 值
     */
    public static String get(String key, String property) {
        String value = null;
        key = keyPrefix + key;
        try {
            if (template.hasKey(key)) {
            	HashOperations<String, Object, Object>  hash = template.opsForHash();
            	value = hash.entries(key).get(property).toString().trim();
                value = StringUtils.isNotBlank(value) && !"nil".equalsIgnoreCase(value) ? value.trim() : null;
            }
        } catch (JedisException e) {
            throw e;
        }
        return value;
    }
    
    /**
     * 设置缓存-String.
     * @param key 关键字
     * @param value 值
     * @param cacheSeconds 缓存时间
     * @return 值
     */
    public static boolean set(String key, String value, long cacheSeconds) {
        boolean result = true;
        key = keyPrefix + key;
        try {
        	ValueOperations<String, String> optValue = template.opsForValue();
            if (cacheSeconds != 0) {
            	optValue.set(key, value,cacheSeconds);
            }
        } catch (Exception e) {
        	result = false;
//            throw e;
        }
        return result;
    }
    
    /**
     * 设置缓存-HashMap.
     * 
     * @param key 关键字
     * @param map HashMap
     * @return 值
     */
    public static boolean setHash(String key,String hKey, HashMap<String, String> map) {
    	boolean result = true;
        key = keyPrefix + key;
        try {
            HashOperations<String, String, Object>  hash = template.opsForHash();
            if(hash.hasKey(key, hKey)){
            	hash.delete(hKey, hKey);
        	}
            hash.putAll(key, map);
        } catch (Exception e) {
        	result = false;
//            throw e;
        }
        return result;
    }
    
    
    
    /**
     * 删除缓存.
     * 
     * @param key 键
     * @return 值
     */
    public static boolean del(String key) {
    	boolean result = true;
        key = keyPrefix + key;
        try {
            if (template.hasKey(key)) {
            	template.delete(key);
            }
        } catch (Exception e) {
        	result = false;
//            throw e;
        }
        return result;
    }
}
