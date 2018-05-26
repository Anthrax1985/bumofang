package com.fh.service.bmf.fabric;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;


@Service("fabricService")
public class FabricService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("FabricMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("FabricMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("FabricMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("FabricMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("FabricMapper.listAll", pd);
	}
	
	/*
	 *列表(全部)
	 */
	public List<PageData> listByCondition(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("FabricMapper.listByCondition", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("FabricMapper.findById", pd);
	}
	
	/*
	 * 通过id获取数据
	 */
	public List<PageData> findByIdArray(String[] idArray)throws Exception{
		return (List<PageData>)dao.findForList("FabricMapper.findByIdArray", idArray);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("FabricMapper.deleteAll", ArrayDATA_IDS);
	}
	
	/*
	* 修改是否显示
	*/
	public void editShow(PageData pd)throws Exception{
		dao.update("FabricMapper.editShow", pd);
	}
	
}

