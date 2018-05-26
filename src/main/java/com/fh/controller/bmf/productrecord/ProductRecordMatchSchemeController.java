package com.fh.controller.bmf.productrecord;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fh.controller.base.BaseWebController;
import com.fh.service.BaseService;
import com.fh.service.bmf.productrecord.ProductRecordMatchSchemeService;
import com.fh.entity.bmf.productrecord.ProductRecordMatchScheme;

/** 
 * 类名称：ProductRecordMatchSchemeController
 * 创建人：tyj
 * 创建时间：2017-07-24
 */
@Controller
@RequestMapping(value="/productrecordmatchscheme")
public class ProductRecordMatchSchemeController extends BaseWebController<ProductRecordMatchScheme> {
	
	String menuUrl = "productrecordmatchscheme/list.do"; //菜单地址(权限用)
	@Resource(name="productRecordMatchSchemeService")
	private ProductRecordMatchSchemeService productRecordMatchSchemeService;
	
	@Override
	protected BaseService<ProductRecordMatchScheme> getBaseService() {
		return productRecordMatchSchemeService;
	}
	@Override
	protected String getPackageName() {
		return "productrecord";
	}
	@Override
	protected String getObjectName() {
		return "ProductRecordMatchScheme";
	}
	
	
}
