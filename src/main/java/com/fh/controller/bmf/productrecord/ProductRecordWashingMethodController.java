package com.fh.controller.bmf.productrecord;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fh.controller.base.BaseWebController;
import com.fh.service.BaseService;
import com.fh.service.bmf.productrecord.ProductRecordWashingMethodService;
import com.fh.entity.bmf.productrecord.ProductRecordWashingMethod;

/** 
 * 类名称：ProductRecordWashingMethodController
 * 创建人：tyj
 * 创建时间：2017-07-19
 */
@Controller
@RequestMapping(value="/productrecordwashingmethod")
public class ProductRecordWashingMethodController extends BaseWebController<ProductRecordWashingMethod> {
	
	String menuUrl = "productrecordwashingmethod/list.do"; //菜单地址(权限用)
	@Resource(name="productRecordWashingMethodService")
	private ProductRecordWashingMethodService productRecordWashingMethodService;
	
	@Override
	protected BaseService<ProductRecordWashingMethod> getBaseService() {
		return productRecordWashingMethodService;
	}
	@Override
	protected String getPackageName() {
		return "productrecord";
	}
	@Override
	protected String getObjectName() {
		return "ProductRecordWashingMethod";
	}
	
	
}
