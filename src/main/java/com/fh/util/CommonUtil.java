package com.fh.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.codehaus.jackson.map.ObjectMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class CommonUtil {

	/**
	 * 获取订单编号
	 * 
	 * @return 订单编号
	 */
	public static String getOrderNumber() {
		SimpleDateFormat simpleDateFormat;
		simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String str = simpleDateFormat.format(date);
		Random random = new Random();
		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
		return str + rannum;// 当前时间
	}

	/***
	 * json字符串转java List
	 * 
	 * @param rsContent
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, Object>> jsonStringToList(String rsContent)throws Exception {
		JSONArray arry = JSONArray.fromObject(rsContent);
		List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < arry.size(); i++) {
			JSONObject jsonObject = arry.getJSONObject(i);
			Map<String, Object> map = new HashMap<String, Object>();
			for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();) {
				String key = (String) iter.next();
				Object value = jsonObject.get(key).toString();
				map.put(key, value);
			}
			rsList.add(map);
		}
		return rsList;
	}
	
	  public static<T> String objectToJson(T obj) throws JSONException, IOException {
		    ObjectMapper mapper = new ObjectMapper(); 
		    // Convert object to JSON string 
		    String jsonStr = "";
		    try {
		       jsonStr = mapper.writeValueAsString(obj);
		    } catch (IOException e) {
		      throw e;
		    }
		    return JSONObject.fromObject(obj).toString();
		  }
}
