package com.fh.controller.bmf.product;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.fh.service.bmf.member.MemberService;
import com.fh.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.support.json.JSONUtils;
import com.fh.common.model.ResponseMessageEnum;
import com.fh.common.model.SysLogEnum;
import com.fh.controller.base.BaseWebController;
import com.fh.service.BaseService;
import com.fh.service.bmf.product.ProductSaveBizService;
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
import com.fh.service.bmf.syslog.SysLogBizService;
import com.yunpian.sdk.util.JsonUtil;
import com.fh.entity.Page;
import com.fh.entity.bmf.app.ProductDetailResItem;
import com.fh.entity.bmf.app.ProductResItem;
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
import com.fh.entity.bmf.productrecord.ProductRecordMatchScheme;
import com.fh.entity.bmf.productrecord.ProductRecordStyle;
import com.fh.entity.bmf.productrecord.ProductRecordWashingMethod;
import com.fh.entity.system.User;
import com.fh.extend.util.FileUploadUtil;

/** 
 * 类名称：ProductController
 * 创建人：tyj
 * 创建时间：2017-07-17
 */
@Controller
@RequestMapping(value="/product")
public class ProductController extends BaseWebController<Product> {
	
	String menuUrl = "product/list.do"; //菜单地址(权限用)
	
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
	
	@Resource(name="productSaveBizService")
	private ProductSaveBizService productSaveBizService;
	
	@Resource(name="sysLogBizService")
	private SysLogBizService sysLogBizService;

	@Resource(name="memberService")
	private MemberService memberService;
	
	@Autowired
	protected HttpServletRequest request;


	
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
	 * 去新增页面
	 */
	@RequestMapping(value = "/goAdd")
	public ModelAndView goAdd() {
		logBefore(logger, "去新增页面" + getObjectName());
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			//获取颜色信息
			List<ProductParamColor> colorList = productParamColorService.listAll();
			mv.addObject("colorList", colorList);
			//获取风格信息
			List<ProductParamStyle> styleList = productParamStyleService.listAll();
			mv.addObject("styleList", styleList);
			//获取工艺信息
			List<ProductParamCraft> craftList = productParamCraftService.listAll();
			mv.addObject("craftList", craftList);
			//获取材料信息
			List<ProductParamMaterial> materialList = productParamMaterialService.listAll();
			mv.addObject("materialList", materialList);
			//获取应用信息
			List<ProductParamApplication> applicationList = productParamApplicationService.listAll();
			mv.addObject("applicationList", applicationList);
			//获取水洗标志信息
			List<ProductParamWashingMethod> washingMethodList = productParamWashingMethodService.listAll();
			mv.addObject("washingMethodList", washingMethodList);
			
			mv.setViewName(getViewNameSuffix() + "_edit");
			mv.addObject("msg", "saveProduct");
			mv.addObject("pd", pd);
			goAddChangeModel(mv);
		} catch (Exception e) {
			logger.warn(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value = "/goEdit/{id}")
	public ModelAndView goEdit(@PathVariable(value = "id") Long id) {
		logBefore(logger, "去修改页面" + getObjectName());
		ModelAndView mv = this.getModelAndView();
		try {			
			//获取颜色信息
			List<ProductParamColor> colorList = productParamColorService.listContainSelected(id);
			mv.addObject("colorList", colorList);
			System.out.println("colorList is  "+ colorList.toString());
			//获取风格信息
			List<ProductParamStyle> styleList = productParamStyleService.listContainSelected(id);
			mv.addObject("styleList", styleList);
			//获取工艺信息
			List<ProductParamCraft> craftList = productParamCraftService.listAll();
			mv.addObject("craftList", craftList);
			//获取材料信息
			List<ProductParamMaterial> materialList = productParamMaterialService.listAll();
			mv.addObject("materialList", materialList);
			//获取应用信息
			List<ProductParamApplication> applicationList = productParamApplicationService.listContainSelected(id);
			mv.addObject("applicationList", applicationList);
			//获取水洗标志信息
			List<ProductParamWashingMethod> washingMethodList = productParamWashingMethodService.listContainSelected(id);
			mv.addObject("washingMethodList", washingMethodList);
			//搭配方案，
			List<ProductMatchSchemeItem> matchItemList = productRecordMatchSchemeService.findByProductId(id);
			mv.addObject("matchItemList", matchItemList);
			Product entity = getBaseService().findById(id); // 根据ID读取
			entityAfter(entity);
			mv.setViewName(getViewNameSuffix() + "_edit");
			mv.addObject("msg", "edit");
			mv.addObject("entity", entity);
			goAddChangeModel(mv);
		} catch (Exception e) {
			logger.warn(e.toString(), e);
		}
		return mv;
	}
	
	
	
	/**
	 * 去搭配方案修改页面
	 */
	@RequestMapping(value = "/goMatchScheduleEdit")
	@ResponseBody
	public ModelAndView goMatchScheduleEdit() {
		logBefore(logger, "去修改页面" + getObjectName());
		ModelAndView mv = this.getModelAndView();
		try {						
			
			List<Product> entityList = getBaseService().listAll(); // 根据ID读取
			JsonUtil.toJson(entityList);
			mv.setViewName(getViewNameSuffix() + "_matchschedule_edit");
			mv.addObject("msg", "matchschedule_edit");
			mv.addObject("entityList", entityList);
			
			goAddChangeModel(mv);
		} catch (Exception e) {
			logger.warn(e.toString(), e);
		}
		return mv;
	}
		
	/**
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveProduct")
	@ResponseBody
			public ModelAndView save(
			@RequestParam Map<String,Object>map,
			@RequestParam(value="qualityControlReport") MultipartFile qualityControlReport,
			@RequestParam(value="patternPicture1") MultipartFile patternPicture1,
			@RequestParam(value="patternPicture2") MultipartFile patternPicture2,
			@RequestParam(value="qualityPicture1") MultipartFile qualityPicture1,
			@RequestParam(value="qualityPicture2") MultipartFile qualityPicture2,
			@RequestParam(value="qualityPicture3") MultipartFile qualityPicture3,
			@RequestParam(value="qualityPicture4") MultipartFile qualityPicture4,
			HttpServletRequest request
			) throws Exception {
		logBefore(logger, "新增" + getObjectName());
		logger.info("map.toString() "+map.toString());
		//保存产品
		Product saveproduct = new Product();
		//保存产品-产品名称
		saveproduct.setProductName(map.get("productName").toString());
		//保存产品-产品工艺
		saveproduct.setProductCraft(map.get("productCraft").toString());
		//保存产品-产品材料
		saveproduct.setProductMaterial(map.get("productMaterial").toString());
		//保存产品-单幅价格
		saveproduct.setProductNarrowPrice(Double.valueOf(String.valueOf(map.get("productNarrowPrice"))));
		//保存产品-宽幅价格
		saveproduct.setProductWidePrice((Double.valueOf(String.valueOf(map.get("productWidePrice")))));
		//保存产品-产品成分
		saveproduct.setProductComponent(String.valueOf(map.get("productComponent")));
		//保存产品-每平方克重
		saveproduct.setProductUnitWeight((Double.valueOf(String.valueOf(map.get("productUnitWeight")))));
		//保存产品-单幅幅宽
		saveproduct.setProductNarrowWidth((Double.valueOf(String.valueOf(map.get("productNarrowWidth")))));
		//保存产品-宽幅幅宽
		saveproduct.setProductWideWidth((Double.valueOf(String.valueOf(map.get("productWideWidth")))));
		//保存产品-花型尺寸-水平
		saveproduct.setPatternHorizontalSize((Double.valueOf(String.valueOf(map.get("patternHorizontalSize")))));
		//保存产品-花型尺寸-垂直
		saveproduct.setPatternVerticalSize((Double.valueOf(String.valueOf(map.get("patternVerticalSize")))));
		//保存产品-原产地
		saveproduct.setProductSourceArea(String.valueOf(map.get("productSourceArea")));
		//保存产品图片
		long time = System.currentTimeMillis();	
		//保存产品图片-花型图1 1600*800
		logger.info("this patternPicture1 is"+patternPicture1);		
		if(patternPicture1 != null && !patternPicture1.isEmpty()){
			//String patternPicture1Url = FileUploadUtil.upload(patternPicture1, request, "product/record/patternpicture1", "pp1" + time);
			String patternPicture1Url = FileUploadUtil.uploadfileNameUnchanged(patternPicture1, request, "product/record/patternpicture");
			logger.info("this patternPicture1Url is"+patternPicture1Url);
			saveproduct.setPatternPicture1(patternPicture1Url);
			saveproduct.setPatternPicture2(patternPicture1Url);
		}
		//保存产品图片-花型图2 800*800
		logger.info("this patternPicture2 is"+patternPicture2);		
		if(patternPicture2 != null && !patternPicture2.isEmpty()){
			//String patternPicture2Url = FileUploadUtil.upload(patternPicture2, request, "product/record/patternpicture2", "pp2" + time);
			String patternPicture2Url = FileUploadUtil.uploadfileNameUnchanged(patternPicture2, request, "product/record/patternpicture");
			logger.info("this patternPicture2Url is"+patternPicture2Url);
			saveproduct.setPatternPicture2(patternPicture2Url);
		}
		//保存产品图片-质地图1
		logger.info("this qualityPicture1 is"+qualityPicture1);		
		if(qualityPicture1 != null && !qualityPicture1.isEmpty()){
			String qualityPicture1Url = FileUploadUtil.uploadfileNameUnchanged(qualityPicture1, request, "product/record/qualitypicture1");
			logger.info("this qualityPicture1Url is"+qualityPicture1Url);
			saveproduct.setQualityPicture1(qualityPicture1Url);
			saveproduct.setQualityPicture2(qualityPicture1Url);
		}
		//保存产品图片-质地图2---20171025-和1保持一致
/*		logger.info("this qualityPicture2 is"+qualityPicture2);		
		if(qualityPicture2 != null && !qualityPicture2.isEmpty()){
			String qualityPicture2Url = FileUploadUtil.upload(qualityPicture2, request, "product/record/qualitypicture2", "qp2" + time);
			logger.info("this qualityPicture2Url is"+qualityPicture2Url);
			saveproduct.setQualityPicture2(qualityPicture2Url);
		}*/ 
		//保存产品图片-质地图3
		logger.info("this qualityPicture3 is"+qualityPicture3);		
		if(qualityPicture3 != null && !qualityPicture3.isEmpty()){
			String qualityPicture3Url = FileUploadUtil.uploadfileNameUnchanged(qualityPicture3, request, "product/record/qualitypicture3");
			logger.info("this qualityPicture3Url is"+qualityPicture3Url);
			saveproduct.setQualityPicture3(qualityPicture3Url);
			saveproduct.setQualityPicture4(qualityPicture3Url);
		}
		//保存产品图片-质地图4
		logger.info("this qualityPicture4 is"+qualityPicture4);		
/*		if(qualityPicture4 != null && !qualityPicture4.isEmpty()){
			String qualityPicture4Url = FileUploadUtil.upload(qualityPicture4, request, "product/record/qualitypicture4", "qp4" + time);
			logger.info("this qualityPicture4Url is"+qualityPicture4Url);
			saveproduct.setQualityPicture4(qualityPicture4Url);
		}*/
		//保存产品-产品质检报告
		logger.info("this qualityControlReport is"+qualityControlReport);		
		if(qualityControlReport != null && !qualityControlReport.isEmpty()){
			String qualityControlReportUrl = FileUploadUtil.upload(qualityControlReport, request, "product/record/report", "report" + time);
			 logger.info("this avatarUrl is"+qualityControlReportUrl);
			 saveproduct.setQualityControlReport(qualityControlReportUrl);
		}		
		Long userId = getSessionUserId();
		User user = getCurrentUser();
		try {
			if(map.get("id")==null ||map.get("id").equals("")){
				//保存产品-创建人，创建时间，删除标记(1删除，0不删除)
				saveproduct.setCreateUserId(getSessionUserId());
				saveproduct.setCreateTime(new Timestamp(System.currentTimeMillis()));
				saveproduct.setDelFlag(new Integer(0));
				//录入产品
				productSaveBizService.saveProduct(saveproduct,map,userId,user);
			}else{
				//编辑产品
				Long productId = Long.valueOf(map.get("id").toString());
				saveproduct.setId(productId);
				productSaveBizService.editProduct(saveproduct,map,userId,user);
			}

		} catch (Exception e) {
			logger.warn(e.toString(), e);
			//return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}
	//return ResponseMessageEnum.SUCCESS.toString();
		Page page = new Page();
		return this.list (page);
	}

	
	/**
	 * RESTFUL修改
	 */
	@RequestMapping(value = "/edit")
	@ResponseBody
	public String edit(@RequestBody Map<Object,Object>map) throws Exception {
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
			return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString();
		} // 校验权限
		logBefore(logger, "修改" + getObjectName());
		logger.info("map.toString() "+map.toString());
		try {

			//getBaseService().edit(entity);
		} catch (Exception e) {
			logger.warn(e.toString(), e);
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}

		return ResponseMessageEnum.SUCCESS.toString();
	}
	/**
	 * 拉入黑名单
	 * @throws Exception 
	 */
	@RequestMapping(value = "/toBlackList/{id}", method = RequestMethod.POST, produces = { JSON_UTF8 })
	@ResponseBody
	public String toBlackList(@PathVariable(value = "id") Long id) throws Exception {
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
			return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString(); // 校验权限
		}
		logBefore(logger, "拉入黑名单" + getObjectName());
		User user = getCurrentUser();
		try {
			Product reqProduct = productService.findById(id);
			reqProduct.setDelFlag(new Integer(1));
			productService.edit(reqProduct);
			sysLogBizService.saveLog(request, user,
					SysLogEnum.PRODUCT_TO_BLACK.getName(), SysLogEnum.PRODUCT_TO_BLACK.getContent()+":"+reqProduct.getProductName());
		} catch (Exception e) {
			logger.warn(e.toString(), e);
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}
		return ResponseMessageEnum.SUCCESS.toString();
	}	
	
	/**
	 * 搭配方案列表(供产品添加搭配方案)
	 */
	@RequestMapping(value = "/goAddMatchSchdule")
	public ModelAndView goAddMatchSchdule(Page page) {
		logBefore(logger, "列表" + getObjectName());
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			/*beforeList(page);*/
			//获取颜色信息
			List<ProductParamColor> colorList = productParamColorService.listAll();
			mv.addObject("colorList", colorList);
			//获取风格信息
			List<ProductParamStyle> styleList = productParamStyleService.listAll();
			mv.addObject("styleList", styleList);
			//获取工艺信息
			List<ProductParamCraft> craftList = productParamCraftService.listAll();
			mv.addObject("craftList", craftList);
			//获取材料信息
			List<ProductParamMaterial> materialList = productParamMaterialService.listAll();
			mv.addObject("materialList", materialList);
			//获取应用信息
			List<ProductParamApplication> applicationList = productParamApplicationService.listAll();
			mv.addObject("applicationList", applicationList);
			
			pd = this.getPageData();
			page.setPd(pd);			
			List<Product> varList = getBaseService().list(page); // 列出列表
			listAfter(varList);
			mv.addObject("varList", varList);
			mv.setViewName(getViewNameSuffix() + "_matchsechdule_list");
			mv.addObject("pd", pd);
			mv.addObject("page", page);
			listChangeModel(mv);
		} catch (Exception e) {
			logger.warn(e.toString(), e);
		}
		return mv;
	}
	/**
	 * 列表(供产品产假搭配方案使用搭配方案)(废弃)
	 */
	@RequestMapping(value = { "/rest/list" }, method = {RequestMethod.GET }, produces = {JSON_UTF8})
	@ResponseBody
	public String restList(@RequestParam(value="offset", required=false) int offset,
			@RequestParam(value="limit", required=false) int limit/*,
			@RequestParam(value="productCategoryId", required=false) String productCategoryId,
			@RequestParam(value="supplierId", required=false) String supplierId,
			@RequestParam(value="searchKey", required=false) String searchKey*/){
		Page pageItem=new Page();
		int page = (offset / limit) + 1;
		pageItem.setShowCount(limit);
		pageItem.setCurrentPage(page);
		
		PageData pd = new PageData();
/*		pd.put("queryKey", searchKey);
		pd.put("productCategoryId", productCategoryId);
		pd.put("supplierId", supplierId);
		pd.put("city", city);*/

		pageItem.setPd(pd);
		try{
			List<Product>	varList = productService.list(pageItem);	//列出Product列表
			return ResponseMessageEnum.SUCCESS.paging(pageItem.getTotalResult(), varList);
			
		} catch(Exception e){
			logger.warn(e);
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}
	}
	/**
	 * 获取某个产品的搭配方案
	 */
	@RequestMapping(value = { "/matchMethod/list" }, method = {RequestMethod.POST }, produces = {JSON_UTF8})
	@ResponseBody
	public String matchMethodList(@RequestBody Map<Object,Object>map ){
		logger.info("i come here 11111111111");
		Long productId = Long.valueOf(String.valueOf(map.get("productId")));
		try{
			List<ProductMatchSchemeItem> matchMethodList = productRecordMatchSchemeService.findByProductId(productId);
			logger.info("matchMethodList.toString()and name"+matchMethodList.get(0).getMatchProductName());
			logger.info("matchMethodList.toString()"+matchMethodList.toString());
			return ResponseMessageEnum.SUCCESS.appendObject(matchMethodList);
			
		} catch(Exception e){
			logger.warn(e);
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(Page page) {
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "cha")) {
			return null;
		} // 校验权限
		logBefore(logger, "列表" + getObjectName());
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			//获取颜色信息
			List<ProductParamColor> colorList = productParamColorService.listAll();
			mv.addObject("colorList", colorList);
			//获取风格信息
			List<ProductParamStyle> styleList = productParamStyleService.listAll();
			mv.addObject("styleList", styleList);
			//获取工艺信息
			List<ProductParamCraft> craftList = productParamCraftService.listAll();
			mv.addObject("craftList", craftList);
			//获取材料信息
			List<ProductParamMaterial> materialList = productParamMaterialService.listAll();
			mv.addObject("materialList", materialList);
			//获取应用信息
			List<ProductParamApplication> applicationList = productParamApplicationService.listAll();
			mv.addObject("applicationList", applicationList);

			List<PageData> memberList = memberService.listAll(null);
			mv.addObject("memberList", memberList);
			
			pd = this.getPageData();
			page.setPd(pd);
//			beforeList(page);
			List<Product> varList = getBaseService().list(page); // 列出列表
			listAfter(varList);

			QNUploadUtil qnUploader = new QNUploadUtil();
			mv.addObject("productDomainUrl", qnUploader.getImgProductDomain());

			mv.addObject("varList", varList);
			mv.setViewName(getViewNameSuffix() + "_list");
			mv.addObject("pd", pd);
			mv.addObject("page", page);
			mv.addObject("isTestMode", AppUtil.isTestMode);
			mv.addObject(Const.SESSION_QX, this.getHC()); // 按钮权限
			listChangeModel(mv);
		} catch (Exception e) {
			logger.warn(e.toString(), e);
		}
		return mv;
	}
	/**
	 * 获取某个产品的颜色Icon，供产品列表悬浮展示
	 */
	@RequestMapping(value = { "/colorIcon/list" }, method = {RequestMethod.POST }, produces = {JSON_UTF8})
	@ResponseBody
	public String colorIconList(@RequestBody Map<Object,Object>map ){
		logger.info("i come here 11111111111");
		Long productId = Long.valueOf(String.valueOf(map.get("productId")));
		try{
			List<PageData> colorIconList = productRecordColorService.listColorIconOfProduct(productId);
			return ResponseMessageEnum.SUCCESS.appendObject(colorIconList);			
		} catch(Exception e){
			logger.warn(e);
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}
	}
}
