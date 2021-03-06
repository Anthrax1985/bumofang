package com.fh.controller.bmf.app;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.common.model.ResponseMessageEnum;
import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.extend.logic.MapValueParser;
import com.fh.extend.logic.map.AppKeyMapEnum;
import com.fh.service.bmf.accessory.AccessoryService;
import com.fh.util.PageData;

@Controller(value="AppAccessoryController")
@RequestMapping(value = "/app/accessory")
public class AccessoryController extends BaseController{
	
	@Resource(name="accessoryService")
	private AccessoryService accessoryService;
	
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
		
		List<PageData> list = accessoryService.list(pageItem);
		
		AppKeyMapEnum[] mapArray = new AppKeyMapEnum[]{AppKeyMapEnum.ID, 
				AppKeyMapEnum.NAME, AppKeyMapEnum.ART_PICTURE};
		List<Map<String,Object>> dataList = MapValueParser.parse(list, mapArray);
		return ResponseMessageEnum.SUCCESS.appendMapListToString(dataList);
	}

}
