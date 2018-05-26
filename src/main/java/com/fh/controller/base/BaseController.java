package com.fh.controller.base;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fh.entity.Page;
import com.fh.entity.system.User;
import com.fh.util.Const;
import com.fh.util.Logger;
import com.fh.util.PageData;
import com.fh.util.UuidUtil;

public class BaseController {
	
	protected final static String JSON_UTF8 = "application/json;charset=UTF-8";
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 得到PageData
	 */
	public PageData getPageData(){
		return new PageData(this.getRequest());
	}
	
	/**
	 * 得到ModelAndView
	 */
	public ModelAndView getModelAndView(){
		return new ModelAndView();
	}
	
	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		return request;
	}

	/**
	 * 得到32位的uuid
	 * @return
	 */
	public String get32UUID(){
		
		return UuidUtil.get32UUID();
	}
	
	/**
	 * 得到分页列表的信息 
	 */
	public Page getPage(){
		
		return new Page();
	}
	
	public static void logBefore(Logger logger, String interfaceName){
		logger.info("");
		logger.info("start");
		logger.info(interfaceName);
	}
	
	public static void logAfter(Logger logger){
		logger.info("end");
		logger.info("");
	}
	
	/* ===============================权限================================== */
	@SuppressWarnings("unchecked")
	public Map<String, String> getHC(){
		Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
		Session session = currentUser.getSession();
		return (Map<String, String>)session.getAttribute(Const.SESSION_QX);
	}
	/* ===============================权限================================== */
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
	
	public User getSessionUser(HttpServletRequest request){
		return (User) request.getSession().getAttribute(Const.SESSION_USER);
	}
	

	
	public User getSessionUser(){
		return (User) SecurityUtils.getSubject().getSession().getAttribute(Const.SESSION_USER);
	}
	
	public Long getSessionUserId(){
		User user = getSessionUser();
		if(user == null){
			return null;
		}		
		return Long.valueOf(user.getUSER_ID());
	}
	

	
	public User getCurrentUser(){
		return getSessionUser();
	}
	
	public boolean isLogin(){
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User)session.getAttribute(Const.SESSION_USER);
		
//		if(user == null){
//			user = new User();
//
//			long uid = 1;
//			user.setUSER_ID(uid);
//			user.setUSERNAME("admin");
//			user.setPASSWORD("");
//			user.setNAME("admin");
//			user.setRIGHTS("1133671055321055258374707980945218933803269864762743594642571294");
//			user.setROLE_ID("1");
//			user.setLAST_LOGIN("2017-11-27 17:20:33");
//			user.setIP("0:0:0:0:0:0:0:1");
//			user.setSTATUS("0");
//			session.setAttribute(Const.SESSION_USER, user);
//			session.removeAttribute(Const.SESSION_SECURITY_CODE);
//
//		}
		
		return user != null;
	}
	
	
}
