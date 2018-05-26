package com.fh.util.encrypt;

import org.apache.shiro.crypto.hash.SimpleHash;

public class EncryptUtil {
	
	public static String encrypt(String source, String salt){
		return new SimpleHash("SHA-1", source, salt).toString();
	}
	public static void main(String[] args) {
		System.out.println(new SimpleHash("SHA-1", "13199999999", "123456").toString());
	}
}
