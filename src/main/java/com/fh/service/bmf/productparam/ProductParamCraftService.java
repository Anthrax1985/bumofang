package com.fh.service.bmf.productparam;

import org.springframework.stereotype.Service;

import com.fh.service.BaseService;
import com.fh.entity.bmf.productparam.ProductParamCraft;


/** 
 * 类名称：ProductParamCraft
 * 创建人：tyj
 * 创建时间：2017-07-19
 */

@Service("productParamCraftService")
public class ProductParamCraftService extends BaseService<ProductParamCraft>{

	protected String getNamespace() {
		return "ProductParamCraftMapper";
	}
	
	
	
}

