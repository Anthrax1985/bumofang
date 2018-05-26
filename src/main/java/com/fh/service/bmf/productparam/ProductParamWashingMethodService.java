package com.fh.service.bmf.productparam;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fh.service.BaseService;
import com.fh.entity.bmf.productparam.ProductParamApplication;
import com.fh.entity.bmf.productparam.ProductParamWashingMethod;


/** 
 * 类名称：ProductParamWashingMethod
 * 创建人：tyj
 * 创建时间：2017-07-19
 */

@Service("productParamWashingMethodService")
public class ProductParamWashingMethodService extends BaseService<ProductParamWashingMethod>{

	protected String getNamespace() {
		return "ProductParamWashingMethodMapper";
	}
	/*
	 *列表(包含是否被被选中等信息)
	 */
	@SuppressWarnings("unchecked")
	public List<ProductParamWashingMethod> listContainSelected(Long productId)throws Exception{
		return (List<ProductParamWashingMethod>)dao.findForList(getNamespace() + ".listContainSelected", productId);
	}
}

