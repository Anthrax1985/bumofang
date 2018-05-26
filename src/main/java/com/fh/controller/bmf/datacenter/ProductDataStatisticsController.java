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
@RequestMapping(value="/productdatastatistics")
public class ProductDataStatisticsController extends BaseWebController<ProductDataStatistics> {
	
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
		return "ProductDataStatistics";
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
	/**
	 * 表格详情
	 */
	@RequestMapping(value = "/tableList/{status}/{periodTime}/{productId}/{memberId}")
	public ModelAndView tableList(
			@PathVariable(value="status")int status,
			@PathVariable(value="periodTime")String periodTime,
			@PathVariable(value="productId")long productId,
			@PathVariable(value="memberId")long memberId,
			Page page) {
		logBefore(logger, "列表" + getObjectName());
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			//String[] periodList= {"201707","201708"};
			//String aa ="2017年三季度";
			String[] periodList = getTime4TableList(periodTime);
			pd = this.getPageData();
			pd.put("time", periodList);
			page.setPd(pd);			
			if(status == 2){//关联产品
				pd.put("productId", productId);
			}			
			if(status == 3){//关联用户
				pd.put("memberId", memberId);
			}
			List<ProductDataTableDetailItem> tableDetailList = productDataStatisticsService.listTableDetail(page);

			for(ProductDataTableDetailItem item : tableDetailList){
				String period = item.getPeriod();
				PageData orderInfo = dataStatisticsBizService.browserOrderByDay(period);
				if(orderInfo != null && orderInfo.getLong("order_cnt") > 0){
					Long orderCnt = orderInfo.getLong("order_cnt");
					Long payCnt = orderInfo.getLong("pay_cnt");
					Long meter = orderInfo.getLong("meter");
					Double totalPrice = (Double)orderInfo.get("total_price");
					Double realPay = (Double)orderInfo.get("real_pay");

					item.setOrderCnt(orderCnt);
					item.setPayCnt(payCnt);
					item.setTotalPrice(totalPrice);
					item.setRealPay(realPay);
					item.setMeter(meter);
				}else{
					item.setOrderCnt(0L);
					item.setPayCnt(0L);
					item.setTotalPrice(0D);
					item.setRealPay(0D);
					item.setMeter(0L);
				}
			}


			System.out.println("tableDetailList is"+tableDetailList.toString());
			mv.addObject("pd", pd);
			//mv.addObject("varList", varList);
			mv.setViewName(getViewNameSuffix() + "_table_detail");
			mv.addObject("tableDetailList", tableDetailList);
			mv.addObject("status", status);
			mv.addObject("periodTime", periodTime);
			mv.addObject("productId", productId);
			mv.addObject("memberId", memberId);
			mv.addObject("page", page);
		} catch (Exception e) {
			logger.warn(e.toString(), e);
		}
		return mv;
	}
	/**
	 * 用户类型饼图详情列表
	 */
	@RequestMapping(value = "/memberTypeDetailList/{year}")
	public ModelAndView memberTypeDetailList(
			@PathVariable(value="year")String year,
			@RequestParam(value="memberId",required = false)Long memberId,
			@RequestParam(value="productId",required = false)Long productId,
			Page page) {
		logBefore(logger, "列表" + getObjectName());
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			//String[] periodList= {"201707","201708"};
			//String aa ="2017年三季度";
			pd = this.getPageData();
			pd.put("year", year);
			if(memberId !=null){
				pd.put("memberId", memberId);
			}
			if(productId !=null){
				pd.put("productId", productId);
			}
			System.out.println("pd is "+pd.toString());
			page.setPd(pd);			
			List<ProductDataBrowseAndTypeItem> browseOfMemberTypeDetail4Year = productDataStatisticsService.browseOfMemberTypeDetail4Year(page);
			mv.addObject("pd", pd);
			//mv.addObject("varList", varList);
			mv.setViewName(getViewNameSuffix() + "_memberType_detail");
			mv.addObject("tableDetailList", browseOfMemberTypeDetail4Year);
			mv.addObject("year", year);
			mv.addObject("page", page);
		} catch (Exception e) {
			logger.warn(e.toString(), e);
		}
		return mv;
	}
	/**
	 * 区域类型饼图详情列表
	 */
	@RequestMapping(value = "/regionTypeDetailList/{year}")
	public ModelAndView regionTypeDetailList(
			@PathVariable(value="year")String year,
			@RequestParam(value="memberId",required = false)Long memberId,
			@RequestParam(value="productId",required = false)Long productId,
			Page page) {
		logBefore(logger, "列表" + getObjectName());
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			//String[] periodList= {"201707","201708"};
			//String aa ="2017年三季度";
			pd = this.getPageData();
			pd.put("year", year);
			if(memberId !=null){
				pd.put("memberId", memberId);
			}
			if(productId !=null){
				pd.put("productId", productId);
			}
			System.out.println("pd is "+pd.toString());
			page.setPd(pd);			
			List<ProductDataBrowseAndTypeItem> browseOfMemberTypeDetail4Year = productDataStatisticsService.browseOfRegionTypeDetail4Year(page);
			mv.addObject("pd", pd);
			//mv.addObject("varList", varList);
			mv.setViewName(getViewNameSuffix() + "_regionType_detail");
			mv.addObject("tableDetailList", browseOfMemberTypeDetail4Year);
			mv.addObject("year", year);
			mv.addObject("page", page);
		} catch (Exception e) {
			logger.warn(e.toString(), e);
		}
		return mv;
	}

	//以下是本类需要用的私有方法；
	
	//重组传入时间，用于获取table明细
	/* 传入的时间格式有"2017年一季度","201701"等形式，进行分类组合成String数组返回*/
	public static String[] getTime4TableList(String time){
		if(time == null ||time.isEmpty()){
			return null;
		}
		int len = time.length();
		if(len>7){
			//按季度分
			String[] resTime = new String[3];
			String year  = time.substring(0, 4);
			char season = time.charAt(5);
			switch(season){
				case '一':resTime[0]=year+"01";resTime[1]=year+"02";resTime[2]=year+"03"; break;
				case '二':resTime[0]=year+"04";resTime[1]=year+"05";resTime[2]=year+"06"; break;
				case '三':resTime[0]=year+"07";resTime[1]=year+"08";resTime[2]=year+"09"; break;
				case '四':resTime[0]=year+"10";resTime[1]=year+"11";resTime[2]=year+"12"; break;
				default:break;
			}
			return resTime;
		}		
		if(len<7){
			//按月度分
			String[] resTime = {time};
			return resTime;
		}
		
		return null;
	}
}
