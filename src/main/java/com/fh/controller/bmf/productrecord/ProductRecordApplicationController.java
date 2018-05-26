package com.fh.controller.bmf.productrecord;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fh.controller.base.BaseWebController;
import com.fh.service.BaseService;
import com.fh.service.bmf.productrecord.ProductRecordApplicationService;
import com.fh.entity.bmf.productrecord.ProductRecordApplication;

/** 
 * 类名称：ProductRecordApplicationController
 * 创建人：tyj
 * 创建时间：2017-07-19
 */
@Controller
@RequestMapping(value="/productrecordapplication")
public class ProductRecordApplicationController extends BaseWebController<ProductRecordApplication> {
	
	String menuUrl = "productrecordapplication/list.do"; //菜单地址(权限用)
	@Resource(name="productRecordApplicationService")
	private ProductRecordApplicationService productRecordApplicationService;
	
	@Override
	protected BaseService<ProductRecordApplication> getBaseService() {
		return productRecordApplicationService;
	}
	@Override
	protected String getPackageName() {
		return "productrecord";
	}
	@Override
	protected String getObjectName() {
		return "ProductRecordApplication";
	}
	
	
}
