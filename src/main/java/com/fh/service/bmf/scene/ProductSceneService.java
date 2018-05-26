package com.fh.service.bmf.scene;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.bmf.member.MemberCart;
import com.fh.entity.bmf.scene.ProductScene;
import com.fh.entity.bmf.scene.Scene;
import com.fh.service.BaseService;
import com.fh.util.PageData;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

/**
 * 类名称：SceneService 创建人：SX 创建时间：2017-11-30
 */

@Service("productSceneService")
public class ProductSceneService extends BaseService<ProductScene> {
	@Resource(name = "daoSupport")
	private DaoSupport dao;

	protected String getNamespace() {
		return "ProductSceneMapper";
	}

	/**
	 * 根据产品名称查询所需要的产品场景的图片
	 * 
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> selectByProductName(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList(
				"ProductSceneMapper.selectByProductName", pd);
	}

	/**
	 * 根据产品名称和产品场景名称查询这个产品中没有下载过的图片
	 * 
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> selectByProductNameWithName(PageData pd)
			throws Exception {
		return (List<PageData>) dao.findForList(
				"ProductSceneMapper.selectByProductNameWithName", pd);
	}

	@SuppressWarnings("unchecked")
	public List<PageData> selectListProductName(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList(
				"ProductSceneMapper.selectListProductName", pd);
	}

	/**
	 * 根据条件获取第一条数据
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> selectTopOneProductName1(PageData pd)
			throws Exception {
		return (List<PageData>) dao.findForList(
				"ProductSceneMapper.selectTopOneProductName1", pd);
	}
	
	@SuppressWarnings("unchecked")
	public List<PageData> selectTopOneProductName2(PageData pd)
			throws Exception {
		return (List<PageData>) dao.findForList(
				"ProductSceneMapper.selectTopOneProductName2", pd);
	}
}
