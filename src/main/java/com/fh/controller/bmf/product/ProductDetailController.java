package com.fh.controller.bmf.product;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fh.common.model.ResponseMessageEnum;
import com.fh.controller.base.BaseWebController;
import com.fh.service.BaseService;
import com.fh.service.bmf.datacenter.DataStatisticsBizService;
import com.fh.service.bmf.product.ProductService;
import com.fh.service.bmf.productparam.ProductParamApplicationService;
import com.fh.service.bmf.productparam.ProductParamColorService;
import com.fh.service.bmf.productparam.ProductParamCraftService;
import com.fh.service.bmf.productparam.ProductParamMaterialService;
import com.fh.service.bmf.productparam.ProductParamStyleService;
import com.fh.service.bmf.productparam.ProductParamWashingMethodService;
import com.fh.service.bmf.productrecord.ProductRecordApplicationService;
import com.fh.service.bmf.productrecord.ProductRecordColorService;
import com.fh.service.bmf.productrecord.ProductRecordMatchSchemeService;
import com.fh.service.bmf.productrecord.ProductRecordStyleService;
import com.fh.service.bmf.productrecord.ProductRecordWashingMethodService;
import com.fh.util.Jurisdiction;
import com.fh.util.PageData;
import com.fh.util.StringUtil;
import com.yunpian.sdk.util.JsonUtil;
import com.fh.entity.bmf.datacenter.DataReqItem4Browse;
import com.fh.entity.bmf.datacenter.DataResDiffType4PieList;
import com.fh.entity.bmf.datacenter.ProductDataResTotal;
import com.fh.entity.bmf.product.Product;
import com.fh.entity.bmf.product.ProductDetail;
import com.fh.entity.bmf.productparam.ProductParamApplication;
import com.fh.entity.bmf.productparam.ProductParamColor;
import com.fh.entity.bmf.productparam.ProductParamCraft;
import com.fh.entity.bmf.productparam.ProductParamMaterial;
import com.fh.entity.bmf.productparam.ProductParamStyle;
import com.fh.entity.bmf.productparam.ProductParamWashingMethod;
import com.fh.entity.bmf.productrecord.ProductMatchSchemeItem;
import com.fh.entity.bmf.productrecord.ProductRecordApplication;
import com.fh.entity.bmf.productrecord.ProductRecordColor;
import com.fh.entity.bmf.productrecord.ProductRecordStyle;
import com.fh.entity.bmf.productrecord.ProductRecordWashingMethod;
import com.fh.entity.system.User;
import com.fh.extend.util.FileUploadUtil;

/** 
 * 类名称：ProductDetailController
 * 创建人：tyj
 * 创建时间：2017-07-17
 */
@Controller
@RequestMapping(value="/productdetail")
public class ProductDetailController extends BaseWebController<Product> {
	
	String menuUrl = "productdetail/list.do"; //菜单地址(权限用)
	
	@Resource(name="productService")
	private ProductService productService;
	
	@Resource(name="productParamColorService")
	private ProductParamColorService productParamColorService;
	
	@Resource(name="productRecordColorService")
	private ProductRecordColorService productRecordColorService;
	
	@Resource(name="productParamStyleService")
	private ProductParamStyleService productParamStyleService;
	
	@Resource(name="productRecordStyleService")
	private ProductRecordStyleService productRecordStyleService;
	
	@Resource(name="productParamCraftService")
	private ProductParamCraftService productParamCraftService;
	
	@Resource(name="productParamMaterialService")
	private ProductParamMaterialService productParamMaterialService;
	
	@Resource(name="productParamApplicationService")
	private ProductParamApplicationService productParamApplicationService;
	
	@Resource(name="productRecordApplicationService")
	private ProductRecordApplicationService productRecordApplicationService;
	
	@Resource(name="productParamWashingMethodService")
	private ProductParamWashingMethodService productParamWashingMethodService;
	
	@Resource(name="productRecordWashingMethodService")
	private ProductRecordWashingMethodService productRecordWashingMethodService;
	
	@Resource(name="productRecordMatchSchemeService")
	private ProductRecordMatchSchemeService productRecordMatchSchemeService;
	
	@Resource(name="dataStatisticsBizService")
	private DataStatisticsBizService dataStatisticsBizService;
	
	@Override
	protected BaseService<Product> getBaseService() {
		return productService;
	}
	@Override
	protected String getPackageName() {
		return "product";
	}
	@Override
	protected String getObjectName() {
		return "Product";
	}
	
	
	/**
	 * 去详情页面
	 */
	@RequestMapping(value = "/goProductDetail/{id}")
	public ModelAndView goProductDetail(@PathVariable(value = "id") Long productId) {
		logBefore(logger, "去修改页面" + getObjectName());
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData(this.getRequest());//接收表单信息
		pd.put("productId", productId);
		System.out.println("pd id"+pd.toString());
		try {		
			ProductDetail productItem = new ProductDetail();
			Product dbProduct = productService.findById(productId);
			//产品id、品名、成分、每平方克重、幅宽单幅、幅宽宽幅,花型尺寸-水平、花型尺寸-垂直、原产地、质检报告
			productItem.setId(productId);
			productItem.setProductName(dbProduct.getProductName());
			productItem.setProductComponent(dbProduct.getProductComponent());
			productItem.setProductUnitWeight(dbProduct.getProductUnitWeight()+"");
			productItem.setProductNarrowWidth(dbProduct.getProductNarrowWidth()+"cm±3cm");
			productItem.setProductWideWidth(dbProduct.getProductWideWidth()+"cm±3cm");
			String patternHorizontalSize = null;
			if(dbProduct.getPatternHorizontalSize() < 0.01){
				patternHorizontalSize = "-";//当花型尺寸为0是，用"-"代替
			}else{
				patternHorizontalSize = dbProduct.getPatternHorizontalSize().toString();
			}
			productItem.setPatternHorizontalSize("H."+patternHorizontalSize+"cm");
			String patternVerticalSize = null;
			if(dbProduct.getPatternVerticalSize() < 0.01){
				patternVerticalSize = "-";//花型尺寸为0是，用"-"代替
			}else{
				patternVerticalSize = dbProduct.getPatternVerticalSize().toString();
			}
			productItem.setPatternVerticalSize("V."+patternVerticalSize+"cm");
			productItem.setProductSourceArea(dbProduct.getProductSourceArea());
			productItem.setQualityControlReport(dbProduct.getQualityControlReport());
			//价格
			productItem.setProductNarrowPrice(dbProduct.getProductNarrowPrice());
			productItem.setProductWidePrice(dbProduct.getProductWidePrice());
			//产品和花型图质地图
			productItem.setPatternPicture1(dbProduct.getPatternPicture1());
			productItem.setPatternPicture2(dbProduct.getPatternPicture2());
			productItem.setQualityPicture1(dbProduct.getQualityPicture1());
			productItem.setQualityPicture2(dbProduct.getQualityPicture2());
			productItem.setQualityPicture3(dbProduct.getQualityPicture3());
			productItem.setQualityPicture4(dbProduct.getQualityPicture4());

			//产品颜色 风格 应用
			Product productPartInfo = productService.productPartInfo(productId);
			String colorName = productPartInfo.getColorStrList();
			colorName = colorName.replace(",", " ");
			productItem.setColorName(colorName);
			String applicationName = productPartInfo.getApplicationStrList();
			applicationName = applicationName.replace(",", " ");
			productItem.setApplicationName(applicationName);
			String styleName = productPartInfo.getStyleStrList();
			styleName = styleName.replace(",", " ");
			productItem.setStyleName(styleName);
			//材质 工艺
			String materialName = productPartInfo.getMaterialStr();
			productItem.setMaterialName(materialName);
			String craftName = productPartInfo.getCraftStr();
			productItem.setCraftName(craftName);
			//产品水洗方案
			ArrayList<String> washMethodIconList = new ArrayList<String>();
			ProductRecordWashingMethod washMethodRes = new ProductRecordWashingMethod();
			washMethodRes.setProductId(productId);
			List<ProductRecordWashingMethod> dbwashMethodInfoList = productRecordWashingMethodService.findByCondition(washMethodRes);
			for(ProductRecordWashingMethod cycle :dbwashMethodInfoList){
				washMethodIconList.add(cycle.getWashingMethodIcon());
			}
			productItem.setWashMethodIconList(washMethodIconList);
			//搭配方案，
			List<ProductMatchSchemeItem> matchItemList = productRecordMatchSchemeService.findByProductId(productId);
			productItem.setProductMatchSchemeList(matchItemList);
			mv.setViewName(getViewNameSuffix() + "_detail");
			mv.addObject("msg", "edit");
			mv.addObject("entity", productItem);
			
			//以下是统计数据
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
			goAddChangeModel(mv);
		} catch (Exception e) {
			logger.warn(e.toString(), e);
		}
		return mv;
	}	
}
