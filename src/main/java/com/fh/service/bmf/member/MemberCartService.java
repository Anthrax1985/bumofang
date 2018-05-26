package com.fh.service.bmf.member;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.bmf.member.MemberCart;
import com.fh.service.BaseService;
import com.fh.util.PageData;

/**
 * 类名称：MemberCartService
 * 创建人：SX
 * 创建时间：2017-11-30
 */

@Service("memberCartService")
public class MemberCartService extends BaseService<MemberCart>{
    @Resource(name = "daoSupport")
    private DaoSupport dao;

    /*
	* 新增
	*/
    public void save(PageData pd)throws Exception{
        dao.save("MemberCartMapper.save", pd);
    }

    /*
	* 新增
	*/
    public Object save1(PageData pd)throws Exception{
        return dao.save("MemberCartMapper.save1", pd);
    }

    /**
     * 购物车列表（多表查询）
     * @param pd
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public List<PageData> allByMemberId(PageData pd) throws Exception{
    	return (List<PageData>)dao.findForList( "MemberCartMapper.allByMemberId", pd);
    }

    public void deleteOldData() throws Exception{
        PageData pd = new PageData();
        dao.update( "MemberCartMapper.deleteOldData", pd);
    }
    
    @SuppressWarnings("unchecked")
	public List<PageData> findByMemberId(Long member_id)throws Exception{
        PageData pd = new PageData();
        pd.put("MEMBER_ID", member_id);
        return (List<PageData>)dao.findForList( "MemberCartMapper.findByMemberId", pd);
    }

    @SuppressWarnings("unchecked")
	public void deleteByParam(PageData pd)throws Exception{
        dao.update( "MemberCartMapper.deleteByParam", pd);
    }
    
    /**
     * 通过id查询购物车
     * @param pd
     * @return
     * @throws Exception
     */
    public PageData findById1(PageData pd) throws Exception{
    	 return  (PageData) dao.findForObject("MemberCartMapper.findById", pd);
    }
    
    /**
     * 根据id查询购物车及其产品价格
     * @param pd
     * @return
     * @throws Exception
     */
    public PageData findByIdWithProduct(PageData pd) throws Exception {
    	 return (PageData) dao.findForObject("MemberCartMapper.findByIdWithProduct", pd);
	}
    
    /**
     * 判断购物车中是否有该产品
     * @param pd
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public List<PageData> isProductId(PageData pd) throws Exception{
    	return (List<PageData>) dao.findForList("MemberCartMapper.isProductId", pd);
    }

    /**
     * 删除
     * @param pd
     * @return
     * @throws Exception
     */
    public Object delete(PageData pd) throws Exception{
    	 return dao.delete( "MemberCartMapper.delete", pd);
    }
    

    /**
     * 假删除
     * @param pd
     * @return
     * @throws Exception
     */
    public Object updateByDelete(PageData pd) throws Exception{
    	 return dao.delete( "MemberCartMapper.updateByDelete", pd);
    }
    
    /**
     * 根据id修改购物车
     * @param pd
     * @return
     * @throws Exception
     */
    public Object updateById(PageData pd) throws Exception{
    	 return dao.update( "MemberCartMapper.editById", pd);
    }
    
    /*
	* 批量删除
	*/
	public Object deleteAll(String[] ArrayDATA_IDS)throws Exception{
		return dao.delete("MemberCartService.deleteAll", ArrayDATA_IDS);
	}

    protected String getNamespace() {
        return "MemberCartService";
    }
}
