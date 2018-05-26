package com.fh.extend.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fh.extend.logic.map.AppKeyMapEnum;
import com.fh.util.PageData;

public class MapValueParser {
	
	public static Map<String,Object> parse(PageData srcMap,
			AppKeyMapEnum[] mapArray){
		Map<String, Object> rsMap = new HashMap<String, Object>();
		for(AppKeyMapEnum keyEnum  : mapArray){
			Object value = srcMap.get(keyEnum.getSource());
			rsMap.put(keyEnum.getTarget(), value);
		}
		return rsMap;
	}
	
	public static List<Map<String,Object>> parse(List<PageData> srcMapList,
			AppKeyMapEnum[] mapArray){
		List<Map<String,Object>> rsList = new ArrayList<Map<String,Object>>();
		for(PageData srcMap : srcMapList){
			rsList.add(parse(srcMap, mapArray));
		}
		return rsList;
	}

}
