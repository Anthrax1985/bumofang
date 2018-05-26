package com.fh.controller.bmf.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.common.logic.ItemColorManager;
import com.fh.common.model.ResponseMessageEnum;
import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.extend.logic.MapValueParser;
import com.fh.extend.logic.map.AppKeyMapEnum;
import com.fh.service.bmf.fabric.FabricService;
import com.fh.util.PageData;

@Controller(value="AppFabricController")
@RequestMapping(value = "/app/fabric")
public class FabricController extends BaseController{
	
	@Resource(name="fabricService")
	private FabricService fabricService;
	
	@Autowired
	 private HttpServletRequest request;
	
	@RequestMapping(value = { "/list/{page}/{pageSize}" }, method = {RequestMethod.GET }, produces = {JSON_UTF8})
	@ResponseBody
	public Object getPageList(@PathVariable("page") int page, @PathVariable("pageSize") int pageSize) throws Exception {
		String token = request.getHeader("token");
		
		PageData pd = new PageData();
		Page pageItem = new Page();
		pageItem.setCurrentPage(page);
		pageItem.setShowCount(pageSize);
		pageItem.setPd(pd);
		
		List<PageData> list = fabricService.list(pageItem);
		
		AppKeyMapEnum[] mapArray = AppKeyMapEnum.values();
		List<Map<String,Object>> dataList = MapValueParser.parse(list, mapArray);
		return ResponseMessageEnum.SUCCESS.appendMapListToString(dataList);
	}
	
	@RequestMapping(value = { "/detail/{id}" }, method = {RequestMethod.GET }, produces = {JSON_UTF8})
	@ResponseBody
	public Object getDetail(@PathVariable("id") String id) throws Exception {
		String token = request.getHeader("token");
		
		PageData pd = new PageData();
		pd.put("ID", id);
		
		PageData rs = fabricService.findById(pd);
		if(rs == null || rs.isEmpty())
			return ResponseMessageEnum.SERVER_DATA_NOTEXIST.toString();
		
		Map<String,Object> map = rs.parseMapLowerCase();
		map.put("imageUrl", map.get("FLAT_PICTURE"));
		
		return ResponseMessageEnum.SUCCESS.appendMapToString(map);
	}
	
	@RequestMapping(value = { "/search" }, method = {RequestMethod.GET }, produces = {JSON_UTF8})
	@ResponseBody
	public Object getListByCondition(@RequestParam(value="color", required=false) String color, @RequestParam(value="alpha", required=false) String alpha,
			@RequestParam(value="style", required=false) String style) throws Exception {
		String token = request.getHeader("token");
		
		PageData pd = new PageData();
		if(StringUtils.isNotBlank(style)){
			pd.put("style_tag", style);
		}
		if(StringUtils.isNotBlank(color)){
			int index = ItemColorManager.getSimilarColorIndex(color);
			pd.put("color_label", "," + (index + 1) + ",");
		}
		
		List<PageData> pdList = fabricService.listByCondition(pd);
		if(pdList == null || pdList.size() == 0){
			return ResponseMessageEnum.SUCCESS.appendMapListToString(new ArrayList<Map<String,Object>>());
		}
		
		AppKeyMapEnum[] mapArray = AppKeyMapEnum.values();
		List<Map<String,Object>> dataList = MapValueParser.parse(pdList, mapArray);
		return ResponseMessageEnum.SUCCESS.appendMapListToString(dataList);
	}

}
