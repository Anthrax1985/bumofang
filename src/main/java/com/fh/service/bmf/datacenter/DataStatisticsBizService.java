package com.fh.service.bmf.datacenter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fh.service.BaseService;
import com.fh.util.PageData;
import com.fh.entity.Page;
import com.fh.entity.bmf.datacenter.DataReqItem4Browse;
import com.fh.entity.bmf.datacenter.DataResDiffType4PieList;
import com.fh.entity.bmf.datacenter.ProductDataBrowseAmountList;
import com.fh.entity.bmf.datacenter.ProductDataBrowseAndTypeItem;
import com.fh.entity.bmf.datacenter.ProductDataResTotal;
import com.fh.entity.bmf.datacenter.ProductDataStatistics;
import com.fh.entity.bmf.datacenter.ProductDataTableDetailItem;


/** 
 * 类名称：ProductDataStatistics
 * 创建人：tyj
 * 创建时间：2017-08-15
 */

@Service("dataStatisticsBizService")
public class DataStatisticsBizService extends BaseService<ProductDataStatistics>{

	protected String getNamespace() {
		return "ProductDataStatisticsMapper";
	}
	
	/*年份列表*/
	public List<PageData> yearList(String str)throws Exception{
		return (List<PageData>) dao.findForList("ProductDataStatisticsMapper.yearList", str);
	}
	/*浏览总次数统计*/
	public Long totalBrowseAmount(DataReqItem4Browse req)throws Exception{
		return (Long) dao.findForObject("ProductDataStatisticsMapper.totalBrowseAmount", req);
	}
	
	/*每月浏览次数统计*/
	public List<ProductDataBrowseAmountList> browseAmountByMonth(DataReqItem4Browse req)throws Exception{
		return (List<ProductDataBrowseAmountList>) dao.findForList("ProductDataStatisticsMapper.browseAmountByMonth", req);
	}
	/*每季度浏览次数统计*/
	public List<ProductDataBrowseAmountList> browseAmountBySeason(DataReqItem4Browse req)throws Exception{
		return (List<ProductDataBrowseAmountList>) dao.findForList("ProductDataStatisticsMapper.browseAmountBySeason", req);
	}
	/*统计产品每年不同用户类型浏览次数*/
	public  List<ProductDataBrowseAndTypeItem>  browseOfMemberType4Year(DataReqItem4Browse req)throws Exception{
		return (List<ProductDataBrowseAndTypeItem> ) dao.findForList("ProductDataStatisticsMapper.browseOfMemberType4Year", req);
	}
	/*	 统计产品每年所有用户不同用户区域浏览次数*/
	public List<ProductDataBrowseAndTypeItem> browseOfRegionType4Year(DataReqItem4Browse req)throws Exception{
		return (List<ProductDataBrowseAndTypeItem>) dao.findForList("ProductDataStatisticsMapper.browseOfRegionType4Year", req);
	}
	/*	 统计产品每年所有用户不同颜色类型浏览次数*/
	public List<ProductDataBrowseAndTypeItem> browseOfColorType4Year(DataReqItem4Browse req)throws Exception{
		return (List<ProductDataBrowseAndTypeItem>) dao.findForList("ProductDataStatisticsMapper.browseOfColorType4Year", req);
	}
	/*	 统计产品每年所有用户不同风格类型浏览次数*/
	public List<ProductDataBrowseAndTypeItem> browseOfStyleType4Year(DataReqItem4Browse req)throws Exception{
		return (List<ProductDataBrowseAndTypeItem>) dao.findForList("ProductDataStatisticsMapper.browseOfStyleType4Year", req);
	}
	/*	 统计产品每年所有用户不同工艺类型浏览次数*/
	public List<ProductDataBrowseAndTypeItem> browseOfCraftType4Year(DataReqItem4Browse req)throws Exception{
		return (List<ProductDataBrowseAndTypeItem>) dao.findForList("ProductDataStatisticsMapper.browseOfCraftType4Year", req);
	}
	/*	 统计产品每年所有用户不同材质类型浏览次数*/
	public List<ProductDataBrowseAndTypeItem> browseOfMaterialType4Year(DataReqItem4Browse req)throws Exception{
		return (List<ProductDataBrowseAndTypeItem>) dao.findForList("ProductDataStatisticsMapper.browseOfMaterialType4Year", req);
	}
	/*	 统计产品每年所有用户不同应用类型浏览次数*/
	public List<ProductDataBrowseAndTypeItem> browseOfApplicationType4Year(DataReqItem4Browse req)throws Exception{
		return (List<ProductDataBrowseAndTypeItem>) dao.findForList("ProductDataStatisticsMapper.browseOfApplicationType4Year", req);
	}	
	/*浏览表格明细*/
	public List<ProductDataTableDetailItem> listTableDetail(Page page)throws Exception{
		return (List<ProductDataTableDetailItem>) dao.findForList("ProductDataStatisticsMapper.TableDetaillistPage", page);
	}
	
	
	/*	 统计产品每年所有用户不同用户类型浏览次数明细*/
	public List<ProductDataBrowseAndTypeItem> browseOfMemberTypeDetail4Year(Page page)throws Exception{
		return (List<ProductDataBrowseAndTypeItem>) dao.findForList("ProductDataStatisticsMapper.browseOfMemberTypeDetail4YearlistPage", page);
	}
	/*	 统计产品每年所有用户不同用户类型浏览次数明细*/
	public List<ProductDataBrowseAndTypeItem> browseOfRegionTypeDetail4Year(Page page)throws Exception{
		return (List<ProductDataBrowseAndTypeItem>) dao.findForList("ProductDataStatisticsMapper.browseOfRegionTypeDetail4YearlistPage", page);
	}	
	
	//封装方法需要的请求参数
	public DataReqItem4Browse getReqParamRecomputed(PageData pd){
		DataReqItem4Browse req4Browse= new DataReqItem4Browse(); 
		int year  = 0;
		String timeperiod = null;
		
        Calendar now = Calendar.getInstance();  
        year = now.get(Calendar.YEAR);
        timeperiod = "m";
        
		if(pd != null && pd.getString("choseeYear") !=null){
			year = pd.getInteger("choseeYear");
		}
		if(pd != null && pd.getString("choseeMorS") !=null){
			timeperiod = pd.getString("choseeMorS");
		}
		if(pd != null && pd.get("ID") != null){
			 req4Browse.setMemberId(pd.getLong("ID"));
		}
		if(pd != null && pd.get("productId") != null){
			req4Browse.setProductId(pd.getLong("productId"));
		}
		req4Browse.setYear(year);
		req4Browse.setTimeperiod(timeperiod);	
		return  req4Browse;
	}
	//获取数据0-获取总年份列表  数据1-获取总浏览次数  数据2-根据请求参数判断是按月份还是按季度统计详情,用于表格数据
	public ProductDataResTotal getProductDataResTotal(DataReqItem4Browse req) throws Exception{
		ProductDataResTotal resInfo = new ProductDataResTotal();
		//获取年份
		List<PageData> yearList = yearList(null);
		ArrayList<Long> yearArray = new ArrayList<Long>();
		if(yearList.size()>0){
			for(PageData cycle : yearList){
				yearArray.add(cycle.getLong("year"));
			}
		}
		resInfo.setYearList(yearArray);
		//数据1-获取总浏览次数
		Long totalBrowseAmount = totalBrowseAmount(req);
		if(totalBrowseAmount == null){totalBrowseAmount = new Long(0);};
		resInfo.setTotalBrowseAmount(totalBrowseAmount);
		//数据2-根据请求参数判断是按月份还是按季度统计详情,用于表格数据
		List<ProductDataBrowseAmountList> browseAmountByMonthList = new ArrayList<ProductDataBrowseAmountList>();

		resInfo.totalOrderCnt = 0L;
		resInfo.totalPayCnt  = 0L;
		resInfo.totalMeter  = 0L;
		resInfo.totalPrice  = 0D;
		resInfo.totalRealPay  = 0D;

		if(req.getTimeperiod().equals("m")){
			//获取每月浏览次数
			 browseAmountByMonthList = browseAmountByMonth(req);

			 for (ProductDataBrowseAmountList item : browseAmountByMonthList){
				 String month = item.getTimePeriod();
				 PageData orderInfo = browserOrderByMonth(month);
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
				 	item.setOrderMeter(meter);

				 	resInfo.totalOrderCnt += orderCnt;
					resInfo.totalPayCnt += payCnt;
					resInfo.totalMeter += meter;
					resInfo.totalPrice += totalPrice;
					resInfo.totalRealPay += realPay;
				 }else{
					 item.setOrderCnt(0L);
					 item.setPayCnt(0L);
					 item.setTotalPrice(0D);
					 item.setRealPay(0D);
					 item.setOrderMeter(0L);
				 }
			 }

		}else{
			//获取每季度浏览次数
			browseAmountByMonthList = browseAmountBySeason(req);

			for (ProductDataBrowseAmountList item : browseAmountByMonthList){
				String timeFrom = item.getTimeFrom();
				String timeEnd = item.getTimeEnd();

				if(timeFrom != null && timeEnd != null && !timeFrom.equals("") && !timeEnd.equals("")){
					PageData orderInfo = browserOrderBySeason(timeFrom, timeEnd);
					if(orderInfo != null && orderInfo.getLong("order_cnt") > 0) {
						Long orderCnt = orderInfo.getLong("order_cnt");
						Long payCnt = orderInfo.getLong("pay_cnt");
						Long meter = orderInfo.getLong("meter");
						Double totalPrice = (Double) orderInfo.get("total_price");
						Double realPay = (Double) orderInfo.get("real_pay");

						item.setOrderCnt(orderCnt);
						item.setPayCnt(payCnt);
						item.setTotalPrice(totalPrice);
						item.setRealPay(realPay);
						item.setOrderMeter(meter);

						resInfo.totalOrderCnt += orderCnt;
						resInfo.totalPayCnt += payCnt;
						resInfo.totalMeter += meter;
						resInfo.totalPrice += totalPrice;
						resInfo.totalRealPay += realPay;
					}else{
						item.setOrderCnt(0L);
						item.setPayCnt(0L);
						item.setTotalPrice(0D);
						item.setRealPay(0D);
						item.setOrderMeter(0L);
					}
				}else{
					item.setOrderCnt(0L);
					item.setPayCnt(0L);
					item.setTotalPrice(0D);
					item.setRealPay(0D);
					item.setOrderMeter(0L);
				}
			}

		}



		resInfo.setBrowseAmountList(browseAmountByMonthList);
		return resInfo;
	}




	private PageData browserOrderByMonth(String year) throws Exception{
		PageData pd = new PageData();
		pd.put("year", year);
		return (PageData)dao.findForObject("ProductDataStatisticsMapper.browseOrderInfo", pd);
	}





	private PageData browserOrderBySeason(String from, String end) throws Exception{
		PageData pd = new PageData();
		pd.put("time_from", from);
		pd.put("time_end", end);
		return (PageData)dao.findForObject("ProductDataStatisticsMapper.browseSeasonOrderInfo", pd);
	}

	public PageData browserOrderByDay(String tm) throws Exception {
		PageData pd = new PageData();
		pd.put("tm", tm);
		return (PageData)dao.findForObject("ProductDataStatisticsMapper.browseDayOrderInfo", pd);
	}



	//获取饼图1-用户类型图  饼图2-区域图  饼图3-颜色类型 饼图4-风格类型  饼图5-工艺类型  饼图6-材质类型  饼图7-应用类型
	public DataResDiffType4PieList getPieDataTypeList(DataReqItem4Browse req) throws Exception{
		DataResDiffType4PieList res = new DataResDiffType4PieList();
		ProductDataBrowseAndTypeItem defaultData = new ProductDataBrowseAndTypeItem();
		defaultData.setName("无数据");
		defaultData.setValue(new Long(0));
		//饼图1-用户类型图
		List<ProductDataBrowseAndTypeItem> memberType =  browseOfMemberType4Year(req);
		if(memberType.size() == 0){memberType.add(defaultData);};
		res.setMemberType(memberType);
		//饼图2-区域图
		List<ProductDataBrowseAndTypeItem> regionType =  browseOfRegionType4Year(req);
		if(regionType.size() == 0){regionType.add(defaultData);};
		res.setRegionType(regionType);
		//饼图3-颜色类型
		List<ProductDataBrowseAndTypeItem> colorType =  browseOfColorType4Year(req);
		if(colorType.size() == 0){colorType.add(defaultData);};
		res.setColorType(colorType);
		//饼图4-风格类型
		List<ProductDataBrowseAndTypeItem> styleType =  browseOfStyleType4Year(req);
		if(styleType.size() == 0){styleType.add(defaultData);};
		res.setStyleType(styleType);
		//饼图5-工艺类型
		List<ProductDataBrowseAndTypeItem> craftType =  browseOfCraftType4Year(req);
		if(craftType.size() == 0){craftType.add(defaultData);};
		res.setCraftType(craftType);
		//饼图6-材质类型
		List<ProductDataBrowseAndTypeItem> materialType =  browseOfMaterialType4Year(req);
		if(materialType.size() == 0){materialType.add(defaultData);};
		res.setMaterialType(materialType);
		//饼图7-应用类型
		List<ProductDataBrowseAndTypeItem> applicationType =  browseOfApplicationType4Year(req);
		if(applicationType.size() == 0){applicationType.add(defaultData);};
		res.setApplicationType(applicationType);
		return res;
	}
}

