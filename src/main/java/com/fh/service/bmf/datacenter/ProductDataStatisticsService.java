package com.fh.service.bmf.datacenter;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fh.service.BaseService;
import com.fh.util.PageData;
import com.fh.entity.Page;
import com.fh.entity.bmf.datacenter.ProductDataBrowseAmountList;
import com.fh.entity.bmf.datacenter.ProductDataBrowseAndTypeItem;
import com.fh.entity.bmf.datacenter.ProductDataStatistics;
import com.fh.entity.bmf.datacenter.ProductDataTableDetailItem;


/** 
 * 类名称：ProductDataStatistics
 * 创建人：tyj
 * 创建时间：2017-08-15
 */

@Service("productDataStatisticsService")
public class ProductDataStatisticsService extends BaseService<ProductDataStatistics>{

	protected String getNamespace() {
		return "ProductDataStatisticsMapper";
	}
	/*年份列表*/
/*	public List<PageData> yearList(String str)throws Exception{
		return (List<PageData>) dao.findForList("ProductDataStatisticsMapper.yearList", str);
	}*/
	/*浏览总次数统计*/
/*	public Long totalBrowseAmount(Integer year)throws Exception{
		return (Long) dao.findForObject("ProductDataStatisticsMapper.totalBrowseAmount", year);
	}
*/
	/*每月浏览次数统计*/
/*	public List<ProductDataBrowseAmountList> browseAmountByMonth(Integer year)throws Exception{
		return (List<ProductDataBrowseAmountList>) dao.findForList("ProductDataStatisticsMapper.browseAmountByMonth", year);
	}*/
	/*每季度浏览次数统计*/
/*	public List<ProductDataBrowseAmountList> browseAmountBySeason(Integer year)throws Exception{
		return (List<ProductDataBrowseAmountList>) dao.findForList("ProductDataStatisticsMapper.browseAmountBySeason", year);
	}*/
	/*统计产品每年不同用户类型浏览次数*/
	public  Long browseOfMemberType4Year(PageData pd)throws Exception{
		return (Long) dao.findForObject("ProductDataStatisticsMapper.browseOfMemberType4Year", pd);
	}
	/*	 统计产品每年所有用户不同用户区域浏览次数*/
	public List<ProductDataBrowseAndTypeItem> browseOfRegionType4Year(Integer year)throws Exception{
		return (List<ProductDataBrowseAndTypeItem>) dao.findForList("ProductDataStatisticsMapper.browseOfRegionType4Year", year);
	}
	/*	 统计产品每年所有用户不同颜色类型浏览次数*/
	public List<ProductDataBrowseAndTypeItem> browseOfColorType4Year(Integer year)throws Exception{
		return (List<ProductDataBrowseAndTypeItem>) dao.findForList("ProductDataStatisticsMapper.browseOfColorType4Year", year);
	}
	/*	 统计产品每年所有用户不同风格类型浏览次数*/
	public List<ProductDataBrowseAndTypeItem> browseOfStyleType4Year(Integer year)throws Exception{
		return (List<ProductDataBrowseAndTypeItem>) dao.findForList("ProductDataStatisticsMapper.browseOfStyleType4Year", year);
	}
	/*	 统计产品每年所有用户不同工艺类型浏览次数*/
	public List<ProductDataBrowseAndTypeItem> browseOfCraftType4Year(Integer year)throws Exception{
		return (List<ProductDataBrowseAndTypeItem>) dao.findForList("ProductDataStatisticsMapper.browseOfCraftType4Year", year);
	}
	/*	 统计产品每年所有用户不同材质类型浏览次数*/
	public List<ProductDataBrowseAndTypeItem> browseOfMaterialType4Year(Integer year)throws Exception{
		return (List<ProductDataBrowseAndTypeItem>) dao.findForList("ProductDataStatisticsMapper.browseOfMaterialType4Year", year);
	}
	/*	 统计产品每年所有用户不同应用类型浏览次数*/
	public List<ProductDataBrowseAndTypeItem> browseOfApplicationType4Year(Integer year)throws Exception{
		return (List<ProductDataBrowseAndTypeItem>) dao.findForList("ProductDataStatisticsMapper.browseOfApplicationType4Year", year);
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
}

