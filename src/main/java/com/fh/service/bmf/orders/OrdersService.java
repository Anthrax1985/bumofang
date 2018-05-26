package com.fh.service.bmf.orders;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;


@Service("ordersService")
public class OrdersService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("OrdersMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("OrdersMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("OrdersMapper.edit", pd);
	}
	
	/**
	 * 根据orderId修改
	 * @param pd
	 * @throws Exception
	 */
	public Object editByOrderId(PageData pd)throws Exception{
		 return dao.update("OrdersMapper.editByOrderId", pd);
	}


	public Object setStatus(PageData pd)throws Exception{
		return dao.update("OrdersMapper.setStatus", pd);
	}

	public Object setStatusByNum(PageData pd)throws Exception{
		return dao.update("OrdersMapper.setStatusByNum", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("OrdersMapper.datalistPage", page);
	}
	
	/*
	 *列表with member
	 */
	public List<PageData> listWithMember(Page page)throws Exception{
		return (List<PageData>)dao.findForList("OrdersMapper.datalistPageWithMember", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("OrdersMapper.listAll", pd);
	}
	
	/**
	 * 根据id查询订单
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listWithMemberByBuyId(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("OrdersMapper.findWithMemberByBuyId", pd);
	}
	
	/**
	 * 根据receiver_id查询收货地址
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData listWithReceiverById(PageData pd)throws Exception{
		return (PageData) dao.findForObject("OrdersMapper.findWithReceiverById", pd);
	}
	
	/**
	 * 根据OrderId查询订单
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findByOrderId(PageData pd) throws Exception {
		return (PageData) dao.findForObject("OrdersMapper.findByOrderId", pd);
	}

	/**
	 * 根据OrderId查询订单商品信息
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> findOrderItemByNum(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("OrdersMapper.findOrderItemByNum", pd);
	}

	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("OrdersMapper.findById", pd);
	}
	
	/*
	 * 通过id获取member数据
	 */
	public PageData findWithMemberById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("OrdersMapper.findWithMemberById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("OrdersMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

