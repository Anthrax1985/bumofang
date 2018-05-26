package com.fh.controller.bmf.productparam;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fh.controller.base.BaseWebController;
import com.fh.service.BaseService;
import com.fh.service.bmf.productparam.ProductParamCraftService;
import com.fh.entity.bmf.productparam.ProductParamCraft;

/** 
 * 类名称：ProductParamCraftController
 * 创建人：tyj
 * 创建时间：2017-07-19
 */
@Controller
@RequestMapping(value="/productparamcraft")
public class ProductParamCraftController extends BaseWebController<ProductParamCraft> {
	
	String menuUrl = "productparamcraft/list.do"; //菜单地址(权限用)
	@Resource(name="productParamCraftService")
	private ProductParamCraftService productParamCraftService;
	
	@Override
	protected BaseService<ProductParamCraft> getBaseService() {
		return productParamCraftService;
	}
	@Override
	protected String getPackageName() {
		return "productparam";
	}
	@Override
	protected String getObjectName() {
		return "ProductParamCraft";
	}
	
	
}
