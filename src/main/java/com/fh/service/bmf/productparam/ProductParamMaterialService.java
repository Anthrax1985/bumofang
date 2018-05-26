package com.fh.service.bmf.productparam;

import org.springframework.stereotype.Service;

import com.fh.service.BaseService;
import com.fh.entity.bmf.productparam.ProductParamMaterial;


/** 
 * 类名称：ProductParamMaterial
 * 创建人：tyj
 * 创建时间：2017-07-19
 */

@Service("productParamMaterialService")
public class ProductParamMaterialService extends BaseService<ProductParamMaterial>{

	protected String getNamespace() {
		return "ProductParamMaterialMapper";
	}
	
}

