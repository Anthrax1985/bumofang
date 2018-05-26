package com.fh.controller.bmf.chinadivision;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.common.model.ResponseMessageEnum;
import com.fh.controller.base.BaseWebController;
import com.fh.service.BaseService;
import com.fh.service.bmf.chinadivision.ChinaDivisionService;
import com.fh.util.PageData;
import com.fh.entity.bmf.chinadivision.ChinaDivision;

/** 
 * 类名称：ChinaDivisionController
 * 创建人：tyj
 * 创建时间：2017-07-23
 */
@Controller
@RequestMapping(value="/chinadivision")
public class ChinaDivisionController extends BaseWebController<ChinaDivision> {
	
	String menuUrl = "chinadivision/list.do"; //菜单地址(权限用)
	@Resource(name="chinaDivisionService")
	private ChinaDivisionService chinaDivisionService;
	
	@Override
	protected BaseService<ChinaDivision> getBaseService() {
		return chinaDivisionService;
	}
	@Override
	protected String getPackageName() {
		return "chinadivision";
	}
	@Override
	protected String getObjectName() {
		return "ChinaDivision";
	}
	
	/**
	 * 通过省份确认城市
	 */
/*		data={divisionName:addrProvince}*/
	@RequestMapping(value="/rest/goSelectCity" , method=RequestMethod.POST  ,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public String goSelectCity(@RequestBody ChinaDivision reqInfo) throws Exception{
		logBefore(logger, "修展示城市");		
		ChinaDivision provinceInfo = chinaDivisionService.findByCondition(reqInfo).get(0);
		Long provinceId = provinceInfo.getId();
		//直辖市直接展示区
		ChinaDivision dbReq = new ChinaDivision();
		List<ChinaDivision> cityList = new ArrayList<ChinaDivision>();
		if(provinceId == 2 ||provinceId == 3||provinceId == 10||provinceId == 23){	
			dbReq.setId(provinceInfo.getId());
			cityList = chinaDivisionService.listCountyByProvince(dbReq);
		}else{
			dbReq.setDivisionParentid(provinceInfo.getId());
			cityList = chinaDivisionService.findByCondition(dbReq);
		}
		return ResponseMessageEnum.SUCCESS.appendObject(cityList);
	}
}
