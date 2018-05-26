package com.fh.controller.bmf.orderitem;

import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.fh.service.bmf.orderitem.OrderItemService;
import com.fh.util.AppUtil;
import com.fh.util.Const;
import com.fh.util.Jurisdiction;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;

/** 
 * 类名称：OrderItemController
 * 创建人：FH 
 * 创建时间：2017-03-16
 */
@Controller
@RequestMapping(value="/orderitem")
public class OrderItemController extends BaseController {
	
	String menuUrl = "orderitem/list.do"; //菜单地址(权限用)
	@Resource(name="orderitemService")
	private OrderItemService orderitemService;
	
	
	/**
	 * rest新增
	 */
	@RequestMapping(value="/rest/save" , method=RequestMethod.POST  ,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public String restSave(@RequestBody Map<String,String> map) throws Exception{
		logBefore(logger, "REST新增OrderItem");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add"))
			{return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString();} //校验权限
		PageData pd = new PageData();
		pd.putAll(map);
		orderitemService.save(pd);
		
		return ResponseMessageEnum.SUCCESS.toString();
	}
	
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除OrderItem");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			orderitemService.delete(pd);
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
		logBefore(logger, "修改OrderItem");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit"))
			{return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString();} //校验权限
		PageData pd = new PageData();
		pd.putAll(map);
		orderitemService.edit(pd);
		return ResponseMessageEnum.SUCCESS.toString();
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表OrderItem");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = orderitemService.list(page);	//列出OrderItem列表
			mv.addObject("varList", varList);
			mv.setViewName("bmf/orderitem/orderitem_list");
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
		logBefore(logger, "去新增OrderItem页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("bmf/orderitem/orderitem_edit");
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
		logBefore(logger, "去修改OrderItem页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = orderitemService.findById(pd);	//根据ID读取
			mv.setViewName("bmf/orderitem/orderitem_edit");
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
		logBefore(logger, "批量删除OrderItem");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				orderitemService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出OrderItem到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("面料id");	//1
			titles.add("数量");	//2
			titles.add("单价");	//3
			titles.add("总价");	//4
			titles.add("实际支付");	//5
			titles.add("状态");	//6
			titles.add("折扣");	//7
			titles.add("购买用户id");	//8
			titles.add("备注");	//9
			dataMap.put("titles", titles);
			List<PageData> varOList = orderitemService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("fabric_id"));	//1
				vpd.put("var2", varOList.get(i).getString("amount"));	//2
				vpd.put("var3", varOList.get(i).getString("unit_price"));	//3
				vpd.put("var4", varOList.get(i).getString("total_price"));	//4
				vpd.put("var5", varOList.get(i).getString("real_pay"));	//5
				vpd.put("var6", varOList.get(i).getString("status"));	//6
				vpd.put("var7", varOList.get(i).getString("discount"));	//7
				vpd.put("var8", varOList.get(i).getString("buy_id"));	//8
				vpd.put("var9", varOList.get(i).getString("remark"));	//9
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
