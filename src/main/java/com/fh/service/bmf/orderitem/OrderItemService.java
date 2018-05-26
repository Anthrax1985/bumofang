package com.fh.service.bmf.orderitem;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;


@Service("orderitemService")
public class OrderItemService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("OrderItemMapper.save", pd);
	}
	
	/*
	* 新增
	*/
	public Object save1(PageData pd)throws Exception{
		return dao.save("OrderItemMapper.save1", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("OrderItemMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("OrderItemMapper.edit", pd);
	}
	
	/**
	 * 根据orderId修改
	 * @param pd
	 * @throws Exception
	 */
	public void editByOrderId(PageData pd)throws Exception{
		dao.update("OrderItemMapper.editByOrderId", pd);
	}
	public Object editByOrderId2(PageData pd)throws Exception{
		return dao.update("OrderItemMapper.editByOrderId2", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("OrderItemMapper.datalistPage", page);
	}
	
	/*
	*列表
	*/
	public List<PageData> listByOrderId(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("OrderItemMapper.findByOrderId", pd);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("OrderItemMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("OrderItemMapper.findById", pd);
	}
	
	/*
	 * 通过id获取数据
	 */
	public List<PageData> listWithFabricByCondition(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("OrderItemMapper.listWithFabricByCondition", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("OrderItemMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

