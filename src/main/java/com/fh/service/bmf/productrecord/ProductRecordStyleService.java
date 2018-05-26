package com.fh.service.bmf.productrecord;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fh.service.BaseService;
import com.fh.util.PageData;
import com.fh.entity.bmf.productrecord.ProductRecordStyle;


/** 
 * 类名称：ProductRecordStyle
 * 创建人：tyj
 * 创建时间：2017-07-19
 */

@Service("productRecordStyleService")
public class ProductRecordStyleService extends BaseService<ProductRecordStyle>{

	protected String getNamespace() {
		return "ProductRecordStyleMapper";
	}
	
	/*
	* 删除某个产品的所有风格记录
	*/
	public void deleteProductStyle(Long productId)throws Exception{
		dao.delete(getNamespace() + ".deleteProductStyle", productId);
	}
	
	/**
	 * 根据产品id查询风格
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> findByProductId(PageData pd) throws Exception{
		return (List<PageData>) dao.findForList(getNamespace() + ".findByProductId", pd);
	}
	
}

