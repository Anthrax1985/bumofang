package com.fh.service.system.user;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fh.common.model.ResponseMessageEnum;
import com.fh.common.model.SysLogEnum;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.system.User;
import com.fh.service.bmf.syslog.SysLogBizService;
import com.fh.util.PageData;


@Service("userService")
public class UserService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Resource(name="sysLogBizService")
	private SysLogBizService sysLogBizService;
	
	@Autowired
	protected HttpServletRequest request;
	
	//======================================================================================
	
	//保存账户列表信息
	public Object saveUserList(List<PageData> saveInfoList,User user) throws Exception{
		Object result = null;
		for(PageData temp :saveInfoList){
			String USERNAME = temp.getString("USERNAME");
			String NAME = temp.getString("NAME");
			String JOB_NUMBER = temp.getString("JOB_NUMBER");
			String ROLE_ID = temp.getString("ROLE_ID");
			result = saveUser(USERNAME, NAME, JOB_NUMBER, ROLE_ID, user);
			if(result instanceof ResponseMessageEnum){
				break;
			}
		}
		return result;
	}
	//保存账户信息
	public Object saveUser(String USERNAME,String NAME,String JOB_NUMBER,String ROLE_ID,User user) throws Exception{
		PageData pd = new PageData();
		pd.put("USERNAME", USERNAME);	//账户账号
		pd.put("NAME", NAME);	//账户户主姓名
		pd.put("JOB_NUMBER", JOB_NUMBER);	//账户户主工号
		pd.put("ROLE_ID", ROLE_ID);	//账户角色
		//以下是新账号默认信息
		pd.put("EMAIL", "");	//邮箱暂时为空
		pd.put("PHONE", "");	//手机暂时为空
		pd.put("BZ", "");	//备注暂时为空
		pd.put("RIGHTS", "");					//权限
		pd.put("LAST_LOGIN", "");				//最后登录时间
		pd.put("IP", "");						//IP
		pd.put("STATUS", "0");					//状态
		pd.put("SKIN", "default");				//默认皮肤
		pd.put("LAST_LOGIN", (new Timestamp(System.currentTimeMillis())).toString());	
		pd.put("PASSWORD", new SimpleHash("SHA-1", "bumofang", "12345678").toString());		
		//判断账户账号是否已被注册
		PageData dbResult = findByUId(pd);		
		if(dbResult != null){
			return ResponseMessageEnum.SERVER_DATA_REPEAT;
		}		
		try{
			saveU(pd);
			//日志
			sysLogBizService.saveLog(request, user,
					SysLogEnum.USER_ADD_USER.getName(), SysLogEnum.USER_ADD_USER.getContent()+":"+pd.getString("NAME"));
		}catch(Exception e){
			return ResponseMessageEnum.SERVER_ERROR;
		}
		return null;
	}
	
	
	/*
	* 通过id获取数据
	*/
	public PageData findByUiId(PageData pd)throws Exception{
		return (PageData)dao.findForObject("UserXMapper.findByUiId", pd);
	}
	/*
	 * 通过userid获取sys_user全部数据
	 */
	public PageData findByUserId(PageData pd)throws Exception{
		return (PageData)dao.findForObject("UserXMapper.findByUserId", pd);
	}
	/*
	* 通过loginname获取数据
	*/
	public PageData findByUId(PageData pd)throws Exception{
		return (PageData)dao.findForObject("UserXMapper.findByUId", pd);
	}
	
	/*
	* 通过邮箱获取数据
	*/
	public PageData findByUE(PageData pd)throws Exception{
		return (PageData)dao.findForObject("UserXMapper.findByUE", pd);
	}
	
	/*
	* 通过编号获取数据
	*/
	public PageData findByUN(PageData pd)throws Exception{
		return (PageData)dao.findForObject("UserXMapper.findByUN", pd);
	}
	
	/*
	* 保存用户
	*/
	public void saveU(PageData pd)throws Exception{
		dao.save("UserXMapper.saveU", pd);
	}
	/*
	* 修改用户
	*/
	public void editU(PageData pd)throws Exception{
		dao.update("UserXMapper.editU", pd);
	}
	/*
	* 换皮肤
	*/
	public void setSKIN(PageData pd)throws Exception{
		dao.update("UserXMapper.setSKIN", pd);
	}
	/*
	* 删除用户
	*/
	public void deleteU(PageData pd)throws Exception{
		dao.delete("UserXMapper.deleteU", pd);
	}
	/*
	* 批量删除用户
	*/
	public void deleteAllU(String[] USER_IDS)throws Exception{
		dao.delete("UserXMapper.deleteAllU", USER_IDS);
	}
	/*
	*用户列表(用户组)
	*/
	public List<PageData> listPdPageUser(Page page)throws Exception{
		return (List<PageData>) dao.findForList("UserXMapper.userlistPage", page);
	}
	
	/*
	*用户列表(全部)
	*/
	public List<PageData> listAllUser(PageData pd)throws Exception{
		return (List<PageData>) dao.findForList("UserXMapper.listAllUser", pd);
	}
	
	/*
	*用户列表(供应商用户)
	*/
	public List<PageData> listGPdPageUser(Page page)throws Exception{
		return (List<PageData>) dao.findForList("UserXMapper.userGlistPage", page);
	}
	/*
	* 保存用户IP
	*/
	public void saveIP(PageData pd)throws Exception{
		dao.update("UserXMapper.saveIP", pd);
	}
	
	/*
	* 登录判断
	*/
	public PageData getUserByNameAndPwd(PageData pd)throws Exception{
		return (PageData)dao.findForObject("UserXMapper.getUserInfo", pd);
	}
	
	/*
	 * APP登录判断
	 */
	public PageData getUserAndAppRightByNameAndPwd(PageData pd)throws Exception{
		return (PageData)dao.findForObject("UserXMapper.getUserInfoAndAppRight", pd);
	}

	/*
	* 跟新登录时间
	*/
	public void updateLastLogin(PageData pd)throws Exception{
		dao.update("UserXMapper.updateLastLogin", pd);
	}
	
	/*
	*通过id获取数据
	*/
	public User getUserAndRoleById(Long USER_ID) throws Exception {
		return (User) dao.findForObject("UserMapper.getUserAndRoleById", USER_ID);
	}

	/*
	 * 通过USER_ID获取后台用户名称和角色信息
	 */
	public PageData findForVO(PageData pd)throws Exception{
		return (PageData)dao.findForObject("UserXMapper.findForVO", pd);
	}
	
	/*
	* 通过USERNAME获取后台用户名称和角色信息
	*/
	public PageData findByUNForVo(PageData pd)throws Exception{
		return (PageData)dao.findForObject("UserXMapper.findByUNForVo", pd);
	}
	
}
