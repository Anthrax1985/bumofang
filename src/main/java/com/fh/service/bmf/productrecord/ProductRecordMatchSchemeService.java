package com.fh.service.bmf.productrecord;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fh.service.BaseService;
import com.fh.entity.bmf.product.Product;
import com.fh.entity.bmf.productrecord.ProductMatchSchemeItem;
import com.fh.entity.bmf.productrecord.ProductRecordMatchScheme;


/** 
 * 类名称：ProductRecordMatchScheme
 * 创建人：tyj
 * 创建时间：2017-07-24
 */

@Service("productRecordMatchSchemeService")
public class ProductRecordMatchSchemeService extends BaseService<ProductRecordMatchScheme>{

	protected String getNamespace() {
		return "ProductRecordMatchSchemeMapper";
	}
	
	/*
	 * 通过产品id湖区对应的产品搭配方案
	 */
	@SuppressWarnings("unchecked")
	public List<ProductMatchSchemeItem> findByProductId(Long productId)throws Exception{
		return (List<ProductMatchSchemeItem>)dao.findForList(getNamespace() + ".findByProductId", productId);
	}
	
	/*
	* 删除某个产品的所有搭配方案记录
	*/
	public void deleteProductMatchSheme(Long productId)throws Exception{
		dao.delete(getNamespace() + ".deleteProductMatchSheme", productId);
	}
	
}

