package com.fh.service.bmf.shopcart;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;


@Service("shopcartService")
public class ShopcartService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("ShopcartMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("ShopcartMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("ShopcartMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ShopcartMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ShopcartMapper.listAll", pd);
	}
	
	/*
	 *根据条件查询购物车和面料详情
	 */
	public List<PageData> listWithFabricByCondition(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ShopcartMapper.listWithFabricByCondition", pd);
	}
	
	/*
	 *根据条件查询购物车
	 */
	public List<PageData> listByCondition(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ShopcartMapper.listByCondition", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("ShopcartMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(Object[] ArrayDATA_IDS)throws Exception{
		dao.delete("ShopcartMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

