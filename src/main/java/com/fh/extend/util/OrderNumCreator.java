package com.fh.extend.util;

import java.util.Random;

/**
 * 订单编号产生器
 * 产生规则
 * 1、10位时间戳，精确到秒
 * 2、四位用户id
 * 3、2位随机数
 * @author jill
 *
 */
public class OrderNumCreator {
	
	public static String create(String userId){
		long currentTime = System.currentTimeMillis() / 1000;
		
		String formatUserId = formatLength(userId, 4);
		Random r = new Random();  
		int value = r.nextInt(100);
		String randomNum = formatLength(value + "", 2);
		
		String currentTimeStr = formatLength(currentTime + "", 10);
		
		return currentTimeStr + formatUserId + randomNum;
	}
	
	private static String formatLength(String target, int length){
		String format = target;
		if(format.length() > length){
			format = format.substring(0, length);
		}
		while(format.length() < length){
			format = "0" + format;
		}
		
		return format;
	}
	
	public static void main(String[] args) {
		
		for(int i=0;i<100;i++){
			System.out.println(create("1"));
		}
	}

}
