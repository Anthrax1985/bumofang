package com.fh.controller.bmf.fabric;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
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
import com.fh.service.bmf.fabric.FabricService;
import com.fh.util.AppUtil;
import com.fh.util.Const;
import com.fh.util.Jurisdiction;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Tools;

/** 
 * 类名称：FabricController
 * 创建人：FH 
 * 创建时间：2017-03-07
 */
@Controller
@RequestMapping(value="/fabric")
public class FabricController extends BaseController {
	
	String menuUrl = "fabric/list.do"; //菜单地址(权限用)
	@Resource(name="fabricService")
	private FabricService fabricService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/saveMulti")
	public ModelAndView saveMulti(@RequestParam Map<String, Object> params, 
			@RequestParam(value="flat_picture") MultipartFile flatPicture,
			@RequestParam(value="art_picture") MultipartFile artPicture,
			@RequestParam(value="stream_picture") MultipartFile streamPicture,
			@RequestParam(value="render_file") MultipartFile renderFile,
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
			String flatUrl = FileUploadUtil.upload(flatPicture, request, "fabric", "flat" + time);
			pd.put("FLAT_PICTURE", flatUrl);
		}
		if(artPicture != null && !artPicture.isEmpty()){
			String artUrl = FileUploadUtil.upload(artPicture, request, "fabric", "art" + time);
			pd.put("ART_PICTURE", artUrl);
		}
		if(streamPicture != null && !streamPicture.isEmpty()){
			String streamUrl = FileUploadUtil.upload(streamPicture, request, "fabric", "stream" + time);
			pd.put("STREAM_PICTURE", streamUrl);
		}
		if(renderFile != null && !renderFile.isEmpty()){
			String renderUrl = FileUploadUtil.upload(renderFile, request, "fabric", "render" + time);
			pd.put("RENDER_FILE", renderUrl);
		}
		
		String id = pd.getString("ID");
		if(StringUtils.isBlank(id)){
			pd.put("IS_SHOW", 0);
			pd.put("ID", this.get32UUID());	//id
			pd.put("RECORD_DATE", Tools.date2Str(new Date()));
			pd.put("RECORD_ID", user.getUSER_ID());
			pd.put("RECORD_NAME", user.getUSERNAME());
			fabricService.save(pd);
		}else{
			fabricService.edit(pd);
		}
		
		return list(this.getPage());
	}
	
	/**
	 * rest新增
	 */
	@RequestMapping(value="/rest/save" , method=RequestMethod.POST  ,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public String restSave(@RequestBody Map<String,String> map) throws Exception{
		logBefore(logger, "REST新增Fabric");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add"))
			{return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString();} //校验权限
		PageData pd = new PageData();
		pd.putAll(map);
		pd.put("ID", this.get32UUID());	//主键
		pd.put("RECORD_ID", "");	//记录人ID
		pd.put("RECORD_NAME", "");	//记录人
		pd.put("RECORD_DATE", Tools.date2Str(new Date()));	//记录时间
		fabricService.save(pd);
		
		return ResponseMessageEnum.SUCCESS.toString();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Fabric");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			fabricService.delete(pd);
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	
	/**
	 * RESTFUL修改
	 */
	@RequestMapping(value="/rest/edit" , method=RequestMethod.POST  ,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public String restEdit(@RequestBody Map<String,String> map) throws Exception{
		logBefore(logger, "修改Fabric");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit"))
			{return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString();} //校验权限
		PageData pd = new PageData();
		pd.putAll(map);
		fabricService.edit(pd);
		return ResponseMessageEnum.SUCCESS.toString();
	}
	
	/**
	 * RESTFUL修改
	 */
	@RequestMapping(value="/rest/editStatus" , method=RequestMethod.POST  ,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public String editShow(@RequestBody Map<String,String> map) throws Exception{
		logBefore(logger, "修改Fabric显示");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit"))
		{return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString();} //校验权限
		PageData pd = new PageData();
		pd.putAll(map);
		fabricService.editShow(pd);
		return ResponseMessageEnum.SUCCESS.toString();
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Fabric");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = fabricService.list(page);	//列出Fabric列表
			mv.addObject("varList", varList);
			mv.setViewName("bmf/fabric/fabric_list");
			mv.addObject("pd", pd);
			mv.addObject("page", page);
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
		logBefore(logger, "去新增Fabric页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("bmf/fabric/fabric_edit");
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
		logBefore(logger, "去修改Fabric页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = fabricService.findById(pd);	//根据ID读取
			mv.setViewName("bmf/fabric/fabric_edit");
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
		logBefore(logger, "批量删除Fabric");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				fabricService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出Fabric到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("编号");	//1
			titles.add("品名");	//2
			titles.add("色号");	//3
			titles.add("成份");	//4
			titles.add("门幅");	//5
			titles.add("花幅");	//6
			titles.add("花距");	//7
			titles.add("克重");	//8
			titles.add("平面图片");	//9
			titles.add("艺术图片");	//10
			titles.add("瀑布流图片");	//11
			titles.add("用途标签");	//12
			titles.add("颜色标签 ");	//13
			titles.add("风格标签");	//14
			titles.add("搭配标签");	//15
			titles.add("款式标签");	//16
			titles.add("其他标签");	//17
			titles.add("单价");	//18
			titles.add("显示");	//19
			titles.add("记录人ID");	//20
			titles.add("记录人");	//21
			titles.add("记录时间");	//22
			dataMap.put("titles", titles);
			List<PageData> varOList = fabricService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("NUMBER"));	//1
				vpd.put("var2", varOList.get(i).getString("NAME"));	//2
				vpd.put("var3", varOList.get(i).getString("COLOR"));	//3
				vpd.put("var4", varOList.get(i).getString("INGREDIENTS"));	//4
				vpd.put("var5", varOList.get(i).getString("WIDTH"));	//5
				vpd.put("var6", varOList.get(i).getString("FLOWER_SIZE"));	//6
				vpd.put("var7", varOList.get(i).getString("FLOWER_DISTANCE"));	//7
				vpd.put("var8", varOList.get(i).getString("WEIGHT"));	//8
				vpd.put("var9", varOList.get(i).getString("FLAT_PICTURE"));	//9
				vpd.put("var10", varOList.get(i).getString("ART_PICTURE"));	//10
				vpd.put("var11", varOList.get(i).getString("STREAM_PICTURE"));	//11
				vpd.put("var12", varOList.get(i).getString("USE_LABELS"));	//12
				vpd.put("var13", varOList.get(i).getString("COLOR_LABELS"));	//13
				vpd.put("var14", varOList.get(i).getString("STYLE_TAGS"));	//14
				vpd.put("var15", varOList.get(i).getString("COLLOCATION_TAGS"));	//15
				vpd.put("var16", varOList.get(i).getString("DESIGN_TAGS"));	//16
				vpd.put("var17", varOList.get(i).getString("OTHER_TAGS"));	//17
				vpd.put("var18", varOList.get(i).getString("PRICE"));	//18
				vpd.put("var19", varOList.get(i).getString("IS_SHOW"));	//19
				vpd.put("var20", varOList.get(i).getString("RECORD_ID"));	//20
				vpd.put("var21", varOList.get(i).getString("RECORD_NAME"));	//21
				vpd.put("var22", varOList.get(i).getString("RECORD_DATE"));	//22
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
