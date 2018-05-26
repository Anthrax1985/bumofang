package com.fh.controller.bmf.message;

import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.common.model.ResponseMessageEnum;
import com.fh.common.model.SysLogEnum;
import com.fh.controller.base.BaseWebController;
import com.fh.service.BaseService;
import com.fh.service.bmf.message.MessageSystemService;
import com.fh.service.bmf.syslog.SysLogBizService;
import com.fh.service.system.user.UserService;
import com.fh.util.Jurisdiction;
import com.fh.util.PageData;
import com.fh.entity.bmf.message.MessageMember;
import com.fh.entity.bmf.message.MessageSystem;
import com.fh.entity.system.User;

/** 
 * 类名称：MessageSystemController
 * 创建人：tyj
 * 创建时间：2017-08-07
 */
@Controller
@RequestMapping(value="/messagesystem")
public class MessageSystemController extends BaseWebController<MessageSystem> {
	
	String menuUrl = "messagesystem/list.do"; //菜单地址(权限用)
	@Resource(name="messageSystemService")
	private MessageSystemService messageSystemService;
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="sysLogBizService")
	private SysLogBizService sysLogBizService;
	
	@Autowired
	protected HttpServletRequest request;
	
	@Override
	protected BaseService<MessageSystem> getBaseService() {
		return messageSystemService;
	}
	@Override
	protected String getPackageName() {
		return "message";
	}
	@Override
	protected String getObjectName() {
		return "MessageSystem";
	}
	/**
	 * 去新增页面
	 */
	@RequestMapping(value = "/goAdd")
	public ModelAndView goAdd() {
		logBefore(logger, "去新增页面" + getObjectName());
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			//后台账户信息
			PageData userIdpd = new PageData();
			userIdpd.put("USER_ID", getSessionUserId());
			PageData userInfo = userService.findByUiId(userIdpd);
			mv.addObject("userInfo", userInfo);
			mv.setViewName(getViewNameSuffix() + "_edit");
			mv.addObject("msg", "save");
			mv.addObject("pd", pd);
			goAddChangeModel(mv);
		} catch (Exception e) {
			logger.warn(e.toString(), e);
		}
		return mv;
	}	

	/**
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = { JSON_UTF8 })
	@ResponseBody
	public String save(@RequestBody MessageSystem entity) throws Exception {
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
			return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString();
		} // 校验权限
		logBefore(logger, "新增" + getObjectName());
		PageData userIdpd = new PageData();
		userIdpd.put("USER_ID", getSessionUserId());
		PageData userInfo = userService.findByUiId(userIdpd);
		try {
			User user = getSessionUser();
			entity.setReleaserId(getSessionUserId());
			entity.setReleaserInfo(userInfo.getString("NAME")+"/"+userInfo.getString("ROLE_NAME"));
			entity.setReleaseTime(new Timestamp(System.currentTimeMillis()));
			entity.setStatus(new Integer(0));//待审批
			entity.setDelFlag(0);
			entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
			entity.setCreateUserId(user.getUSER_ID());			
			getBaseService().save(entity);
			//日志
			sysLogBizService.saveLog(request, user,
					SysLogEnum.SYSTEM_MESSAGE_RELEASE.getName(),SysLogEnum.SYSTEM_MESSAGE_RELEASE.getContent()+":"+entity.getReleaseTitle());
		} catch (Exception e) {
			logger.warn(e.toString(), e);
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}

		return getSaveSuccessResponse(entity);
	}
	/**
	 * 去审批页面
	 */
	@RequestMapping(value = "/goAudit/{id}")
	public ModelAndView goAudit(@PathVariable(value = "id") Long id) {
		logBefore(logger, "去修改页面" + getObjectName());
		ModelAndView mv = this.getModelAndView();
		try {
			MessageSystem entity = getBaseService().findById(id); // 根据ID读取
			entityAfter(entity);
			mv.setViewName(getViewNameSuffix() + "_audit");
			mv.addObject("msg", "audit");
			mv.addObject("entity", entity);
			goAddChangeModel(mv);
		} catch (Exception e) {
			logger.warn(e.toString(), e);
		}
		return mv;
	}
	/**
	 * RESTFUL修改
	 */
	@RequestMapping(value = "/audit", method = RequestMethod.POST, produces = { JSON_UTF8 })
	@ResponseBody
	public String edit(@RequestBody MessageSystem entity) throws Exception {
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
			return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString();
		} // 校验权限		
		PageData userIdpd = new PageData();
		userIdpd.put("USER_ID", getSessionUserId());
		PageData userInfo = userService.findByUiId(userIdpd);
		try {
			entity.setAuditorId(getSessionUserId());
			entity.setAuditorInfo(userInfo.getString("NAME")+"/"+userInfo.getString("ROLE_NAME"));
			entity.setAuditTime(new Timestamp(System.currentTimeMillis()));
			getBaseService().edit(entity);
			//日志
			User user = getSessionUser();
			MessageSystem meaasgeInfo = messageSystemService.findById(entity.getId());
			sysLogBizService.saveLog(request, user,
					SysLogEnum.SYSTEM_MESSAGE_AUDIT.getName(),SysLogEnum.SYSTEM_MESSAGE_AUDIT.getContent()+":"+meaasgeInfo.getReleaseTitle());
		} catch (Exception e) {
			logger.warn(e.toString(), e);
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}

		return ResponseMessageEnum.SUCCESS.toString();
	}
}
