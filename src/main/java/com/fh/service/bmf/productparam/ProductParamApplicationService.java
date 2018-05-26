package com.fh.service.bmf.productparam;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fh.service.BaseService;
import com.fh.entity.bmf.productparam.ProductParamApplication;
import com.fh.entity.bmf.productparam.ProductParamStyle;


/** 
 * 类名称：ProductParamApplication
 * 创建人：tyj
 * 创建时间：2017-07-19
 */

@Service("productParamApplicationService")
public class ProductParamApplicationService extends BaseService<ProductParamApplication>{

	protected String getNamespace() {
		return "ProductParamApplicationMapper";
	}
	/*
	 *列表(包含是否被被选中等信息)
	 */
	@SuppressWarnings("unchecked")
	public List<ProductParamApplication> listContainSelected(Long productId)throws Exception{
		return (List<ProductParamApplication>)dao.findForList(getNamespace() + ".listContainSelected", productId);
	}
}

