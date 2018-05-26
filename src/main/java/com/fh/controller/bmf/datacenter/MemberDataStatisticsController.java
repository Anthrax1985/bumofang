package com.fh.controller.bmf.datacenter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseWebController;
import com.fh.service.BaseService;
import com.fh.service.bmf.datacenter.DataStatisticsBizService;
import com.fh.service.bmf.datacenter.ProductDataStatisticsService;
import com.fh.util.Const;
import com.fh.util.Jurisdiction;
import com.fh.util.PageData;
import com.yunpian.sdk.util.JsonUtil;
import com.fh.entity.Page;
import com.fh.entity.bmf.datacenter.DataReqItem4Browse;
import com.fh.entity.bmf.datacenter.DataResDiffType4PieList;
import com.fh.entity.bmf.datacenter.ProductDataBrowseAmountList;
import com.fh.entity.bmf.datacenter.ProductDataBrowseAndTypeItem;
import com.fh.entity.bmf.datacenter.ProductDataBrowseMemberType;
import com.fh.entity.bmf.datacenter.ProductDataResTotal;
import com.fh.entity.bmf.datacenter.ProductDataStatistics;
import com.fh.entity.bmf.datacenter.ProductDataTableDetailItem;

/** 
 * 类名称：ProductDataStatisticsController
 * 创建人：tyj
 * 创建时间：2017-08-15
 */
@Controller
@RequestMapping(value="/memberdatastatistics")
public class MemberDataStatisticsController extends BaseWebController<ProductDataStatistics> {
	
	String menuUrl = "productdatastatistics/list.do"; //菜单地址(权限用)
	@Resource(name="productDataStatisticsService")
	private ProductDataStatisticsService productDataStatisticsService;
	
	@Resource(name="dataStatisticsBizService")
	private DataStatisticsBizService dataStatisticsBizService;
	
	@Override
	protected BaseService<ProductDataStatistics> getBaseService() {
		return productDataStatisticsService;
	}
	@Override
	protected String getPackageName() {
		return "datacenter";
	}
	@Override
	protected String getObjectName() {
		return "MemberDataStatistics";
	}
	
	/**
	 * 数据展示
	 */
	@RequestMapping(value = "/listForData")
	public ModelAndView listForData() {		
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "cha")) {
			return null;
		} // 校验权限
		logBefore(logger, "列表" + getObjectName());
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData(this.getRequest());//接收表单信息
		System.out.println("pd id"+pd.toString());
		try {
			//封装方法需要的请求参数
			DataReqItem4Browse req4Browse = dataStatisticsBizService.getReqParamRecomputed(pd);		
			mv.addObject("req4Browse", req4Browse);//将年份数据传回到jsp,供饼图详情使用
			//数据0-获取总年份列表  数据1-获取总浏览次数  数据2-根据请求参数判断是按月份还是按季度统计详情,用于表格数据
			ProductDataResTotal resInfo = dataStatisticsBizService.getProductDataResTotal(req4Browse);
			mv.addObject("resInfo", resInfo);
			//饼图1-用户类型图  饼图2-区域图  饼图3-颜色类型 饼图4-风格类型  饼图5-工艺类型  饼图6-材质类型  饼图7-应用类型
			DataResDiffType4PieList pieDataTypeList = dataStatisticsBizService.getPieDataTypeList(req4Browse);
			mv.addObject("memberType", JsonUtil.toJson(pieDataTypeList.getMemberType()));
			mv.addObject("regionType", JsonUtil.toJson(pieDataTypeList.getRegionType()));
			mv.addObject("colorType", JsonUtil.toJson(pieDataTypeList.getColorType()));
			mv.addObject("styleType", JsonUtil.toJson(pieDataTypeList.getStyleType()));
			mv.addObject("craftType", JsonUtil.toJson(pieDataTypeList.getCraftType()));
			mv.addObject("materialType", JsonUtil.toJson(pieDataTypeList.getMaterialType()));
			mv.addObject("applicationType", JsonUtil.toJson(pieDataTypeList.getApplicationType()));

			mv.setViewName(getViewNameSuffix() + "_list");
			//mv.addObject(Const.SESSION_QX, this.getHC()); // 按钮权限
		} catch (Exception e) {
			logger.warn(e.toString(), e);
		}
		return mv;

	}
}
