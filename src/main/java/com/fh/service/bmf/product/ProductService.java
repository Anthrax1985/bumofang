package com.fh.service.bmf.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fh.service.BaseService;
import com.fh.util.PageData;
import com.fh.entity.Page;
import com.fh.entity.bmf.app.ProductResItem;
import com.fh.entity.bmf.app.SelectProductReq;
import com.fh.entity.bmf.product.Product;
import com.fh.entity.bmf.product.ProductEviNameAsso;


/** 
 * 类名称：Product
 * 创建人：tyj
 * 创建时间：2017-07-17
 */

@Service("productService")
public class ProductService extends BaseService<Product>{

	protected String getNamespace() {
		return "ProductMapper";
	}
	
	/*
	*列表(全部)
	*/
	@SuppressWarnings("unchecked")
	public List<ProductResItem> listAll4App()throws Exception{
		return (List<ProductResItem>)dao.findForList(getNamespace() + ".listAll4App", null);
	}

	public List<ProductResItem> search4App(String name)throws Exception{
		PageData pd = new PageData();
		pd.put("product_name", name);
		return (List<ProductResItem>)dao.findForList(getNamespace() + ".search4App", pd);
	}

	/*
	*列表
	*/
	@SuppressWarnings("unchecked")
	public List<Product> listWhereIn(String[] IDS)throws Exception{
		return (List<Product>)dao.findForList(getNamespace() + ".listWhereIn", IDS);
	}
	
	/*
	 *列表(根据颜色筛选)
	 */
	@SuppressWarnings("unchecked")
	public List<ProductResItem> listAllColor(ProductResItem proRes)throws Exception{
		return (List<ProductResItem>)dao.findForList(getNamespace() + ".listAllColor", proRes);
	}
	/*
	 *列表(根据风格筛选)
	 */
	@SuppressWarnings("unchecked")
	public List<ProductResItem> listAllStyle(ProductResItem proRes)throws Exception{
		return (List<ProductResItem>)dao.findForList(getNamespace() + ".listAllStyle", proRes);
	}
	/*
	 *列表(根据材料筛选)
	 */
	@SuppressWarnings("unchecked")
	public List<ProductResItem> listAllMaterial(ProductResItem proRes)throws Exception{
		return (List<ProductResItem>)dao.findForList(getNamespace() + ".listAllMaterial", proRes);
	}
	/*
	 *列表(根据材料筛选)
	 */
	@SuppressWarnings("unchecked")
	public List<ProductResItem> listAllApplication(ProductResItem proRes)throws Exception{
		return (List<ProductResItem>)dao.findForList(getNamespace() + ".listAllApplication", proRes);
	}
	/*
	 *产品列表(黑名单)
	 */
	@SuppressWarnings("unchecked")
	public List<Product> listAllBlackProduct(Page page)throws Exception{
		return (List<Product>)dao.findForList(getNamespace() + ".blackDatalistPage", page);
	}
	
	/*
	 *产品列表(根据产品名称前缀筛选)
	 */
	@SuppressWarnings("unchecked")
	public List<ProductResItem> listSameDesignedPro(String prefix)throws Exception{
		return (List<ProductResItem>)dao.findForList(getNamespace() + ".listSameDesignedPro", prefix);
	}
	
	/*
	 *产品列表(根据产品名称前缀筛选)
	 */
	@SuppressWarnings("unchecked")
	public List<ProductEviNameAsso> userfulEvi4Pro(Long productId)throws Exception{
		return (List<ProductEviNameAsso>)dao.findForList(getNamespace() + ".userfulEvi4Pro", productId);
	}
	/*
	 *产品信息(部分)
	 */
	@SuppressWarnings("unchecked")
	public Product productPartInfo(Long id)throws Exception{
		return (Product)dao.findForObject(getNamespace() + ".productPartInfo", id);
	}
	
	/*
	 *多次筛选产品
	 */
	@SuppressWarnings("unchecked")
	public List<ProductResItem> listByMultiSelected(SelectProductReq proRes)throws Exception{
		return (List<ProductResItem>)dao.findForList(getNamespace() + ".listByMultiSelected", proRes);
	}

	public Product findByProductName(String product_name)throws Exception{
		return (Product)dao.findForObject(getNamespace() + ".findByProductName", product_name);
	}
	
	/**
	 * 根据产品名称查询后面的十个产品
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> selectByProductName(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList(getNamespace() + ".selectByProductName", pd);
	}
	
	/**
	 * 根据id查询产品
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> selectById(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList(getNamespace() + ".selectById", pd);
	}
}

