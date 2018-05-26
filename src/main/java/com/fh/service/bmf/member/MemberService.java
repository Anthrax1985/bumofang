package com.fh.service.bmf.member;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;


@Service("memberService")
public class MemberService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("MemberMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("MemberMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("MemberMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("MemberMapper.datalistPage", page);
	}
	/*
	 *列表(黑名单)
	 */
	public List<PageData> listBlack(Page page)throws Exception{
		return (List<PageData>)dao.findForList("MemberMapper.dataBlacklistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("MemberMapper.listAll", pd);
	}
	
	
	/*
	 *查找udid
	 */
	public List<PageData> findByIDFA(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("MemberMapper.findByIDFA", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("MemberMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("MemberMapper.deleteAll", ArrayDATA_IDS);
	}
	
	/*
	 * 
	 */
	public List<PageData> findByMobile(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("MemberMapper.findByMobile", pd);
	}
	
	/*
	 * 用户注册(app)
	 */
	public void saveInfo(PageData pd)throws Exception{
		dao.save("MemberMapper.saveInfo", pd);
	}
}

