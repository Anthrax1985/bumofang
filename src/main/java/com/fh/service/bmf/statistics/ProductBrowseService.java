package com.fh.service.bmf.statistics;

import org.springframework.stereotype.Service;

import com.fh.service.BaseService;
import com.fh.entity.bmf.statistics.ProductBrowse;


/** 
 * 类名称：ProductBrowse
 * 创建人：tyj
 * 创建时间：2017-08-02
 */

@Service("productBrowseService")
public class ProductBrowseService extends BaseService<ProductBrowse>{

	protected String getNamespace() {
		return "ProductBrowseMapper";
	}
	
}

