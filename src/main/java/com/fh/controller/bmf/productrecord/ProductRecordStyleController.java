package com.fh.controller.bmf.productrecord;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fh.controller.base.BaseWebController;
import com.fh.service.BaseService;
import com.fh.service.bmf.productrecord.ProductRecordStyleService;
import com.fh.entity.bmf.productrecord.ProductRecordStyle;

/** 
 * 类名称：ProductRecordStyleController
 * 创建人：tyj
 * 创建时间：2017-07-19
 */
@Controller
@RequestMapping(value="/productrecordstyle")
public class ProductRecordStyleController extends BaseWebController<ProductRecordStyle> {
	
	String menuUrl = "productrecordstyle/list.do"; //菜单地址(权限用)
	@Resource(name="productRecordStyleService")
	private ProductRecordStyleService productRecordStyleService;
	
	@Override
	protected BaseService<ProductRecordStyle> getBaseService() {
		return productRecordStyleService;
	}
	@Override
	protected String getPackageName() {
		return "productrecord";
	}
	@Override
	protected String getObjectName() {
		return "ProductRecordStyle";
	}
	
	
}
