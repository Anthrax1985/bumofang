package com.fh.controller.bmf.accessory;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fh.common.model.ResponseMessageEnum;
import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.system.User;
import com.fh.extend.util.FileUploadUtil;
import com.fh.util.AppUtil;
import com.fh.util.ObjectExcelView;
import com.fh.util.Const;
import com.fh.util.PageData;
import com.fh.util.Tools;
import com.fh.util.Jurisdiction;
import com.fh.service.bmf.accessory.AccessoryService;

/** 
 * 类名称：AccessoryController
 * 创建人：FH 
 * 创建时间：2017-03-08
 */
@Controller
@RequestMapping(value="/accessory")
public class AccessoryController extends BaseController {
	
	String menuUrl = "accessory/list.do"; //菜单地址(权限用)
	@Resource(name="accessoryService")
	private AccessoryService accessoryService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/saveMulti")
	public ModelAndView saveMulti(@RequestParam Map<String, Object> params, 
			@RequestParam(value="flat_picture") MultipartFile flatPicture,
			@RequestParam(value="art_picture") MultipartFile artPicture,
			HttpServletRequest request) throws Exception{
		logBefore(logger, "新增Fabrics");
		ModelAndView mv = this.getModelAndView();
		
		User user = getSessionUser(request);
		if(user == null){
			return mv;
		}
		
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.putAllUpperCase(params);
		long time = System.currentTimeMillis();
		
		if(flatPicture != null && !flatPicture.isEmpty()){
			String flatUrl = FileUploadUtil.upload(flatPicture, request, "accessory", "flat" + time);
			pd.put("FLAT_PICTURE", flatUrl);
		}
		if(artPicture != null && !artPicture.isEmpty()){
			String artUrl = FileUploadUtil.upload(artPicture, request, "accessory", "art" + time);
			pd.put("ART_PICTURE", artUrl);
		}
		
		String id = pd.getString("ID");
		if(StringUtils.isBlank(id)){
			pd.put("ID", this.get32UUID());	//id
			pd.put("RECORD_DATE", Tools.date2Str(new Date()));
			pd.put("RECORD_ID", user.getUSER_ID());
			pd.put("RECORD_NAME", user.getUSERNAME());
			accessoryService.save(pd);
		}else{
			accessoryService.edit(pd);
		}
		
		return list(new Page());
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Accessory");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			accessoryService.delete(pd);
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
		logBefore(logger, "修改Accessory");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit"))
			{return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		accessoryService.edit(pd);
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
		logBefore(logger, "修改Accessory");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit"))
			{return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString();} //校验权限
		PageData pd = new PageData();
		pd.putAll(map);
		accessoryService.edit(pd);
		return ResponseMessageEnum.SUCCESS.toString();
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Accessory");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = accessoryService.list(page);	//列出Accessory列表
			mv.addObject("varList", varList);
			mv.setViewName("bmf/accessory/accessory_list");
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
		logBefore(logger, "去新增Accessory页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("bmf/accessory/accessory_edit");
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
		logBefore(logger, "去修改Accessory页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = accessoryService.findById(pd);	//根据ID读取
			mv.setViewName("bmf/accessory/accessory_edit");
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
		logBefore(logger, "批量删除Accessory");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				accessoryService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出Accessory到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("编号");	//1
			titles.add("名称");	//2
			titles.add("品类");	//3
			titles.add("材质");	//4
			titles.add("规格");	//5
			titles.add("系列");	//6
			titles.add("平面图片");	//7
			titles.add("艺术图片");	//8
			titles.add("用途标签");	//9
			titles.add("颜色标签 ");	//10
			titles.add("风格标签");	//11
			titles.add("搭配标签");	//12
			titles.add("款式标签");	//13
			titles.add("其他标签");	//14
			titles.add("单价");	//15
			titles.add("备注");	//16
			titles.add("记录人ID");	//17
			titles.add("记录人");	//18
			titles.add("记录时间");	//19
			dataMap.put("titles", titles);
			List<PageData> varOList = accessoryService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("NUMBER"));	//1
				vpd.put("var2", varOList.get(i).getString("NAME"));	//2
				vpd.put("var3", varOList.get(i).getString("CATEGORY"));	//3
				vpd.put("var4", varOList.get(i).getString("MATERIAL"));	//4
				vpd.put("var5", varOList.get(i).getString("SKU"));	//5
				vpd.put("var6", varOList.get(i).getString("SERIES"));	//6
				vpd.put("var7", varOList.get(i).getString("FLAT_PICTURE"));	//7
				vpd.put("var8", varOList.get(i).getString("ART_PICTURE"));	//8
				vpd.put("var9", varOList.get(i).getString("USE_LABELS"));	//9
				vpd.put("var10", varOList.get(i).getString("COLOR_LABELS"));	//10
				vpd.put("var11", varOList.get(i).getString("STYLE_TAGS"));	//11
				vpd.put("var12", varOList.get(i).getString("COLLOCATION_TAGS"));	//12
				vpd.put("var13", varOList.get(i).getString("DESIGN_TAGS"));	//13
				vpd.put("var14", varOList.get(i).getString("OTHER_TAGS"));	//14
				vpd.put("var15", varOList.get(i).getString("PRICE"));	//15
				vpd.put("var16", varOList.get(i).getString("REMARK"));	//16
				vpd.put("var17", varOList.get(i).getString("RECORD_ID"));	//17
				vpd.put("var18", varOList.get(i).getString("RECORD_NAME"));	//18
				vpd.put("var19", varOList.get(i).getString("RECORD_DATE"));	//19
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
