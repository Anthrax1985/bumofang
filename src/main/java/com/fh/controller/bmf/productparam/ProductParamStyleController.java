package com.fh.controller.bmf.productparam;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fh.controller.base.BaseWebController;
import com.fh.service.BaseService;
import com.fh.service.bmf.productparam.ProductParamStyleService;
import com.fh.entity.bmf.productparam.ProductParamStyle;

/** 
 * 类名称：ProductParamStyleController
 * 创建人：tyj
 * 创建时间：2017-07-19
 */
@Controller
@RequestMapping(value="/productparamstyle")
public class ProductParamStyleController extends BaseWebController<ProductParamStyle> {
	
	String menuUrl = "productparamstyle/list.do"; //菜单地址(权限用)
	@Resource(name="productParamStyleService")
	private ProductParamStyleService productParamStyleService;
	
	@Override
	protected BaseService<ProductParamStyle> getBaseService() {
		return productParamStyleService;
	}
	@Override
	protected String getPackageName() {
		return "productparam";
	}
	@Override
	protected String getObjectName() {
		return "ProductParamStyle";
	}
	
	
}
