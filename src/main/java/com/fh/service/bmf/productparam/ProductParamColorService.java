package com.fh.service.bmf.productparam;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fh.service.BaseService;
import com.fh.entity.bmf.productparam.ProductParamColor;


/** 
 * 类名称：ProductParamColor
 * 创建人：tyj
 * 创建时间：2017-07-18
 */

@Service("productParamColorService")
public class ProductParamColorService extends BaseService<ProductParamColor>{

	protected String getNamespace() {
		return "ProductParamColorMapper";
	}
	
	/*
	 *列表(包含是否被被选中等信息)
	 */
	@SuppressWarnings("unchecked")
	public List<ProductParamColor> listContainSelected(Long productId)throws Exception{
		return (List<ProductParamColor>)dao.findForList(getNamespace() + ".listContainSelected", productId);
	}
	
}

