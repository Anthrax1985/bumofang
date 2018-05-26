package com.fh.controller.bmf.productparam;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fh.controller.base.BaseWebController;
import com.fh.service.BaseService;
import com.fh.service.bmf.productparam.ProductParamApplicationService;
import com.fh.entity.bmf.productparam.ProductParamApplication;

/** 
 * 类名称：ProductParamApplicationController
 * 创建人：tyj
 * 创建时间：2017-07-19
 */
@Controller
@RequestMapping(value="/productparamapplication")
public class ProductParamApplicationController extends BaseWebController<ProductParamApplication> {
	
	String menuUrl = "productparamapplication/list.do"; //菜单地址(权限用)
	@Resource(name="productParamApplicationService")
	private ProductParamApplicationService productParamApplicationService;
	
	@Override
	protected BaseService<ProductParamApplication> getBaseService() {
		return productParamApplicationService;
	}
	@Override
	protected String getPackageName() {
		return "productparam";
	}
	@Override
	protected String getObjectName() {
		return "ProductParamApplication";
	}
	
	
}
