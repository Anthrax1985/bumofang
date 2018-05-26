package com.fh.extend.util;

import java.util.Random;

public class AuthCodeUtil {
	
	public static String getCode(){
		String authCode = "";
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sb.append(rand);
		}
		authCode = sb.toString();
		
		return authCode;
	}

}
