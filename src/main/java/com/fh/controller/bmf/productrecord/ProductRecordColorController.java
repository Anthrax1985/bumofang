package com.fh.controller.bmf.productrecord;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fh.controller.base.BaseWebController;
import com.fh.service.BaseService;
import com.fh.service.bmf.productrecord.ProductRecordColorService;
import com.fh.entity.bmf.productrecord.ProductRecordColor;

/** 
 * 类名称：ProductRecordColorController
 * 创建人：tyj
 * 创建时间：2017-07-18
 */
@Controller
@RequestMapping(value="/productrecordcolor")
public class ProductRecordColorController extends BaseWebController<ProductRecordColor> {
	
	String menuUrl = "productrecordcolor/list.do"; //菜单地址(权限用)
	@Resource(name="productRecordColorService")
	private ProductRecordColorService productRecordColorService;
	
	@Override
	protected BaseService<ProductRecordColor> getBaseService() {
		return productRecordColorService;
	}
	@Override
	protected String getPackageName() {
		return "productrecord";
	}
	@Override
	protected String getObjectName() {
		return "ProductRecordColor";
	}
	
	
}
