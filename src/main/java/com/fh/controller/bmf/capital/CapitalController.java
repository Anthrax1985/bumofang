package com.fh.controller.bmf.capital;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.common.model.ResponseMessageEnum;
import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.bmf.capital.CapitalService;
import com.fh.util.AppUtil;
import com.fh.util.Const;
import com.fh.util.Jurisdiction;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Tools;

/** 
 * 类名称：CapitalController
 * 创建人：FH 
 * 创建时间：2017-03-07
 */
@Controller
@RequestMapping(value="/capital")
public class CapitalController extends BaseController {
	
	String menuUrl = "capital/list.do"; //菜单地址(权限用)
	@Resource(name="capitalService")
	private CapitalService capitalService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增Capital");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("ID", this.get32UUID());	//主键
		pd.put("USER_ID", "");	//用户ID
		pd.put("USER_NAME", "");	//用户名
		pd.put("CREATE_TIME", Tools.date2Str(new Date()));	//创建时间
		capitalService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * rest新增
	 */
	@RequestMapping(value="/rest/save" , method=RequestMethod.POST  ,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public String restSave(@RequestBody Map<String,String> map) throws Exception{
		logBefore(logger, "REST新增Capital");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add"))
			{return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString();} //校验权限
		PageData pd = new PageData();
		pd.putAll(map);
		pd.put("ID", this.get32UUID());	//主键
		pd.put("USER_ID", "");	//用户ID
		pd.put("USER_NAME", "");	//用户名
		pd.put("CREATE_TIME", Tools.date2Str(new Date()));	//创建时间
		capitalService.save(pd);
		
		return ResponseMessageEnum.SUCCESS.toString();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Capital");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			capitalService.delete(pd);
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, "修改Capital");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit"))
			{return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		capitalService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * RESTFUL修改
	 */
	@RequestMapping(value="/rest/edit" , method=RequestMethod.POST  ,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public String restEdit(@RequestBody Map<String,String> map) throws Exception{
		logBefore(logger, "修改Capital");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit"))
			{return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString();} //校验权限
		PageData pd = new PageData();
		pd.putAll(map);
		capitalService.edit(pd);
		return ResponseMessageEnum.SUCCESS.toString();
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Capital");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = capitalService.list(page);	//列出Capital列表
			mv.addObject("varList", varList);
			mv.setViewName("bmf/capital/capital_list");
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(){
		logBefore(logger, "去新增Capital页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("bmf/capital/capital_edit");
			mv.addObject("msg", "save");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit(){
		logBefore(logger, "去修改Capital页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = capitalService.findById(pd);	//根据ID读取
			mv.setViewName("bmf/capital/capital_edit");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() {
		logBefore(logger, "批量删除Capital");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				capitalService.deleteAll(ArrayDATA_IDS);
				pd.put("msg", "ok");
			}else{
				pd.put("msg", "no");
			}
			pdList.add(pd);
			map.put("list", pdList);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		return AppUtil.returnObject(pd, map);
	}
	
	/*
	 * 导出到excel
	 * @return
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel(){
		logBefore(logger, "导出Capital到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("用户ID");	//1
			titles.add("用户名");	//2
			titles.add("手机号");	//3
			titles.add("支付密码");	//4
			titles.add("余额");	//5
			titles.add("创建时间");	//6
			dataMap.put("titles", titles);
			List<PageData> varOList = capitalService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("USER_ID"));	//1
				vpd.put("var2", varOList.get(i).getString("USER_NAME"));	//2
				vpd.put("var3", varOList.get(i).getString("MOBILE"));	//3
				vpd.put("var4", varOList.get(i).getString("PAY_PASSWORD"));	//4
				vpd.put("var5", varOList.get(i).getString("BALANCE"));	//5
				vpd.put("var6", varOList.get(i).getString("CREATE_TIME"));	//6
				varList.add(vpd);
			}
			dataMap.put("varList", varList);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv,dataMap);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
}
