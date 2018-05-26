package com.fh.controller.bmf.statistics;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fh.controller.base.BaseWebController;
import com.fh.service.BaseService;
import com.fh.service.bmf.statistics.ProductBrowseService;
import com.fh.entity.bmf.statistics.ProductBrowse;

/** 
 * 类名称：ProductBrowseController
 * 创建人：tyj
 * 创建时间：2017-08-02
 */
@Controller
@RequestMapping(value="/productbrowse")
public class ProductBrowseController extends BaseWebController<ProductBrowse> {
	
	String menuUrl = "productbrowse/list.do"; //菜单地址(权限用)
	@Resource(name="productBrowseService")
	private ProductBrowseService productBrowseService;
	
	@Override
	protected BaseService<ProductBrowse> getBaseService() {
		return productBrowseService;
	}
	@Override
	protected String getPackageName() {
		return "statistics";
	}
	@Override
	protected String getObjectName() {
		return "ProductBrowse";
	}
	
	
}
