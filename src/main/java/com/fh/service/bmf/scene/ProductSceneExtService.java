package com.fh.service.bmf.scene;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.bmf.scene.ProductScene;
import com.fh.service.BaseService;
import com.fh.util.PageData;

/**
 * 类名称：SceneService 创建人：SX 创建时间：2017-11-30
 */

@Service("productSceneExtService")
public class ProductSceneExtService extends BaseService<ProductScene> {
	@Resource(name = "daoSupport")
	private DaoSupport dao;

	protected String getNamespace() {
		return "ProductSceneExtMapper";
	}

	/**
	 * 根据创建时间返回产品场景的图片
	 * 
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> selectByTime(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList(getNamespace() + ".selectByTime", pd);
	}

}
