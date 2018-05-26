package com.fh.controller.bmf.product;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

import com.fh.common.model.ResponseMessageEnum;
import com.fh.common.model.SysLogEnum;
import com.fh.controller.base.BaseWebController;
import com.fh.service.BaseService;
import com.fh.service.bmf.product.ProductService;
import com.fh.service.bmf.productparam.ProductParamApplicationService;
import com.fh.service.bmf.productparam.ProductParamColorService;
import com.fh.service.bmf.productparam.ProductParamCraftService;
import com.fh.service.bmf.productparam.ProductParamMaterialService;
import com.fh.service.bmf.productparam.ProductParamStyleService;
import com.fh.service.bmf.productparam.ProductParamWashingMethodService;
import com.fh.service.bmf.productrecord.ProductRecordApplicationService;
import com.fh.service.bmf.productrecord.ProductRecordColorService;
import com.fh.service.bmf.productrecord.ProductRecordStyleService;
import com.fh.service.bmf.productrecord.ProductRecordWashingMethodService;
import com.fh.service.bmf.syslog.SysLogBizService;
import com.fh.util.Const;
import com.fh.util.Jurisdiction;
import com.fh.util.PageData;
import com.fh.util.StringUtil;
import com.fh.entity.Page;
import com.fh.entity.bmf.product.Product;
import com.fh.entity.bmf.productparam.ProductParamApplication;
import com.fh.entity.bmf.productparam.ProductParamColor;
import com.fh.entity.bmf.productparam.ProductParamCraft;
import com.fh.entity.bmf.productparam.ProductParamMaterial;
import com.fh.entity.bmf.productparam.ProductParamStyle;
import com.fh.entity.bmf.productparam.ProductParamWashingMethod;
import com.fh.entity.bmf.productrecord.ProductRecordApplication;
import com.fh.entity.bmf.productrecord.ProductRecordColor;
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
@RequestMapping(value="/productblack")
public class ProductBlackListController extends BaseWebController<Product> {
	
	String menuUrl = "productblack/list.do"; //菜单地址(权限用)
	
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
	
	@Resource(name="sysLogBizService")
	private SysLogBizService sysLogBizService;
	
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
			pd = this.getPageData();
			page.setPd(pd);
			/*beforeList(page);*/
			List<Product> varList = productService.listAllBlackProduct(page); // 列出列表
			listAfter(varList);
			mv.addObject("varList", varList);
			mv.setViewName(getViewNameSuffix() + "_blacklist");
			mv.addObject("pd", pd);
			mv.addObject("page", page);
			mv.addObject(Const.SESSION_QX, this.getHC()); // 按钮权限
			listChangeModel(mv);
		} catch (Exception e) {
			logger.warn(e.toString(), e);
		}
		return mv;
	}
	
	
	/**
	 * 去详情页面
	 */
	@RequestMapping(value = "/goProductDetail/{id}")
	public ModelAndView goProductDetail(@PathVariable(value = "id") Long id) {
		logBefore(logger, "去修改页面" + getObjectName());
		ModelAndView mv = this.getModelAndView();
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
	 * RESTFUL修改
	 */
	@RequestMapping(value = "/edit"/*, method = RequestMethod.POST, produces = { JSON_UTF8 }*/)
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
	 * 激活产品
	 * @throws Exception 
	 */
	@RequestMapping(value = "/toWhiteList/{id}", method = RequestMethod.POST, produces = { JSON_UTF8 })
	@ResponseBody
	public String toWhiteList(@PathVariable(value = "id") Long id) throws Exception {
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
			return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString(); // 校验权限
		}
		logBefore(logger, "激活产品" + getObjectName());
		User user = getCurrentUser();
		try {
			Product reqProduct = productService.findById(id);
			reqProduct.setDelFlag(new Integer(0));
			productService.edit(reqProduct);
			sysLogBizService.saveLog(request, user,
					SysLogEnum.PRODUCT_ACTIVATE.getName(), SysLogEnum.PRODUCT_ACTIVATE.getContent()+":"+reqProduct.getProductName());
		} catch (Exception e) {
			logger.warn(e.toString(), e);
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}
		return ResponseMessageEnum.SUCCESS.toString();
	}
	
}
