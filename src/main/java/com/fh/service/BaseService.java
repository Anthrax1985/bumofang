package com.fh.service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;

import javax.annotation.Resource;
import java.util.List;

public abstract class BaseService<T> {
	
	@Resource(name = "daoSupport")
	protected DaoSupport dao;
	
	protected abstract String getNamespace();
	
	/*
	* 新增
	*/
	public void save(T entity)throws Exception{
		dao.save(getNamespace() + ".save", entity);
	}
	
	/*
	* 删除
	*/
	public void delete(Long id)throws Exception{
		dao.delete(getNamespace() + ".delete", id);
	}

	/*
	* 修改
	*/
	public void edit(T entity)throws Exception{
		dao.update(getNamespace() + ".edit", entity);
	}
	
	/*
	*列表
	*/
	@SuppressWarnings("unchecked")
	public List<T> list(Page page)throws Exception{
		return (List<T>)dao.findForList(getNamespace() + ".datalistPage", page);
	}

	
	/*
	*列表(全部)
	*/
	@SuppressWarnings("unchecked")
	public List<T> listAll()throws Exception{
		return (List<T>)dao.findForList(getNamespace() + ".listAll", null);
	}

	
	/*
	* 通过id获取数据
	*/
	@SuppressWarnings("unchecked")
	public T findById(Long id)throws Exception{
		return (T)dao.findForObject(getNamespace() + ".findById", id);
	}
	
	/*
	 * 通过条件获取数据
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByCondition(T t)throws Exception{
		return (List<T>)dao.findForList(getNamespace() + ".findByCondition", t);
	}
	
	/*
	 * 通过条件获取数据
	 */
	public T findByUniqueCondition(T t)throws Exception{
		List<T> list = findByCondition(t);
		if(list == null || list.size() == 0)
			return null;
		
		return list.get(0);
	}
	
	/*
	* 批量删除
	*/
	public void deleteBatch(Object[] ids)throws Exception{
		dao.delete(getNamespace() + ".deleteBatch", ids);
	}	
}
