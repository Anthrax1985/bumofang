package com.fh.service.bmf.productrecord;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fh.service.BaseService;
import com.fh.util.PageData;
import com.fh.entity.bmf.productrecord.ProductRecordApplication;


/** 
 * 类名称：ProductRecordApplication
 * 创建人：tyj
 * 创建时间：2017-07-19
 */

@Service("productRecordApplicationService")
public class ProductRecordApplicationService extends BaseService<ProductRecordApplication>{

	protected String getNamespace() {
		return "ProductRecordApplicationMapper";
	}
	/*
	* 删除某个产品的所有应用记录
	*/
	public void deleteProductApplication(Long productId)throws Exception{
		dao.delete(getNamespace() + ".deleteProductApplication", productId);
	}
	
	/**
	 * 根据产品id查询应用
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> findByProductId(PageData pd) throws Exception{
		return (List<PageData>) dao.findForList(getNamespace() + ".findByProductId", pd);
	}
}

