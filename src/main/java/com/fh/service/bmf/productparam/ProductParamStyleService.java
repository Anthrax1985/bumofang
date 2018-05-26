package com.fh.service.bmf.productparam;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fh.service.BaseService;
import com.fh.entity.bmf.productparam.ProductParamColor;
import com.fh.entity.bmf.productparam.ProductParamStyle;


/** 
 * 类名称：ProductParamStyle
 * 创建人：tyj
 * 创建时间：2017-07-19
 */

@Service("productParamStyleService")
public class ProductParamStyleService extends BaseService<ProductParamStyle>{

	protected String getNamespace() {
		return "ProductParamStyleMapper";
	}
	/*
	 *列表(包含是否被被选中等信息)
	 */
	@SuppressWarnings("unchecked")
	public List<ProductParamStyle> listContainSelected(Long productId)throws Exception{
		return (List<ProductParamStyle>)dao.findForList(getNamespace() + ".listContainSelected", productId);
	}
}

