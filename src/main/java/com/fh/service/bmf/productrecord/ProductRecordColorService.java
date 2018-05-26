package com.fh.service.bmf.productrecord;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fh.service.BaseService;
import com.fh.util.PageData;
import com.fh.entity.Page;
import com.fh.entity.bmf.productrecord.ProductRecordColor;


/** 
 * 类名称：ProductRecordColor
 * 创建人：tyj
 * 创建时间：2017-07-18
 */

@Service("productRecordColorService")
public class ProductRecordColorService extends BaseService<ProductRecordColor>{

	protected String getNamespace() {
		return "ProductRecordColorMapper";
	}
	
	/*
	* 删除某个产品的所有颜色记录
	*/
	public void deleteProductColor(Long productId)throws Exception{
		dao.delete(getNamespace() + ".deleteProductColor", productId);
	}
	/*
	 *	<!-- 获取某个产品的颜色Icon，供产品列表悬浮展示-->
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listColorIconOfProduct(Long req)throws Exception{
		return (List<PageData>)dao.findForList(getNamespace() + ".listColorIconOfProduct", req);
	}
	
	/**
	 * 获取某一个产品的颜色
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listColorWithProduct(PageData pd) throws Exception{
		return (List<PageData>)dao.findForList(getNamespace() + ".listColorWithProduct", pd);
	}
}

