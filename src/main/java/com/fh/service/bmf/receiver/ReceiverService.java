package com.fh.service.bmf.receiver;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;


@Service("receiverService")
public class ReceiverService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public Object save(PageData pd)throws Exception{
		return dao.save("ReceiverMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public Object delete(PageData pd)throws Exception{
		return dao.delete("ReceiverMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("ReceiverMapper.edit", pd);
	}
	
	/**
	 * 根据id修改收件人地址
	 * @param pd
	 * @return
	 * @throws Exception 
	 */
	public Object updateById(PageData pd) throws Exception{
		return dao.update("ReceiverMapper.editById", pd);
	}
	
	/**
	 * 根据Mobile查询
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> findByMemberId(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("ReceiverMapper.findByMemberId", pd);
	}
	
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ReceiverMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ReceiverMapper.listAll", pd);
	}
	
	/*
	 *根据条件获取列表
	 */
	public List<PageData> listByCondition(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ReceiverMapper.listByCondition", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("ReceiverMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("ReceiverMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

