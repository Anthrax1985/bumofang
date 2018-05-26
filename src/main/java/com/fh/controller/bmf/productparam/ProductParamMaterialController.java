package com.fh.controller.bmf.productparam;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fh.controller.base.BaseWebController;
import com.fh.service.BaseService;
import com.fh.service.bmf.productparam.ProductParamMaterialService;
import com.fh.entity.bmf.productparam.ProductParamMaterial;

/** 
 * 类名称：ProductParamMaterialController
 * 创建人：tyj
 * 创建时间：2017-07-19
 */
@Controller
@RequestMapping(value="/productparammaterial")
public class ProductParamMaterialController extends BaseWebController<ProductParamMaterial> {
	
	String menuUrl = "productparammaterial/list.do"; //菜单地址(权限用)
	@Resource(name="productParamMaterialService")
	private ProductParamMaterialService productParamMaterialService;
	
	@Override
	protected BaseService<ProductParamMaterial> getBaseService() {
		return productParamMaterialService;
	}
	@Override
	protected String getPackageName() {
		return "productparam";
	}
	@Override
	protected String getObjectName() {
		return "ProductParamMaterial";
	}
	
	
}
