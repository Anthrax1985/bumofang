package com.fh.service.bmf.productrecord;

import org.springframework.stereotype.Service;

import com.fh.service.BaseService;
import com.fh.entity.bmf.productrecord.ProductRecordWashingMethod;


/** 
 * 类名称：ProductRecordWashingMethod
 * 创建人：tyj
 * 创建时间：2017-07-19
 */

@Service("productRecordWashingMethodService")
public class ProductRecordWashingMethodService extends BaseService<ProductRecordWashingMethod>{

	protected String getNamespace() {
		return "ProductRecordWashingMethodMapper";
	}
	/*
	* 删除某个产品的所有水洗标记记录
	*/
	public void deleteProductWashingMethod(Long productId)throws Exception{
		dao.delete(getNamespace() + ".deleteProductWashingMethod", productId);
	}
}

