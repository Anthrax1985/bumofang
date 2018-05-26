package com.fh.controller.bmf.member;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.common.model.ResponseMessageEnum;
import com.fh.common.model.SysLogEnum;
import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.bmf.chinadivision.ChinaDivision;
import com.fh.entity.system.User;
import com.fh.service.bmf.chinadivision.ChinaDivisionService;
import com.fh.service.bmf.member.MemberService;
import com.fh.service.bmf.syslog.SysLogBizService;
import com.fh.service.system.user.UserService;
import com.fh.util.AppUtil;
import com.fh.util.Const;
import com.fh.util.Jurisdiction;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Tools;

/** 
 * 类名称：MemberController
 * 创建人：FH 
 * 创建时间：2017-03-08
 */
@Controller
@RequestMapping(value="/memberblack")
public class MemberBlackListController extends BaseController {
	
	String menuUrl = "memberblack/list.do"; //菜单地址(权限用)
	@Resource(name="memberService")
	private MemberService memberService;
	
	@Resource(name="chinaDivisionService")
	private ChinaDivisionService chinaDivisionService;
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="sysLogBizService")
	private SysLogBizService sysLogBizService;
	
	@Autowired
	protected HttpServletRequest request;

	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, "修改Member");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit"))
			{return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData dbUser = memberService.findById(pd);
		memberService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * RESTFUL修改
	 */
	@RequestMapping(value="/rest/edit" , method=RequestMethod.POST  ,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public String restEdit(@RequestBody Map<String,String> map) throws Exception{
		logBefore(logger, "修改Member");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit"))
			{return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString();} //校验权限
		PageData pd = new PageData();
		pd.putAll(map);
		memberService.edit(pd);
		return ResponseMessageEnum.SUCCESS.toString();
	}
	
	/**
	 * RESTFUL审核
	 */
	@RequestMapping(value="/rest/audit" , method=RequestMethod.POST  ,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public String restAudit(@RequestBody Map<String,String> map) throws Exception{
		logBefore(logger, "修改Member");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit"))
		{return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString();} //校验权限
		PageData pd = new PageData();
		pd.putAll(map);
		pd.put("DEL_FLAG", 1);//审核后状态正常，删除标记（1正常，2黑名单，3待审核）		
		pd.put("REASON", "");//清除拉黑/激活原因	
		Long userId = getSessionUserId();
		//获取用户名称和角色
		PageData userReq = new PageData();
		userReq.put("USER_ID", userId);
		PageData userInfo = userService.findForVO(userReq);
		String userResInfo = userInfo.getString("USERNAME")+"("+userInfo.getString("ROLE_NAME")+")";
		pd.put("UPDATE_PERSON", userResInfo);	
		pd.put("UPDATE_TIME",new Timestamp(System.currentTimeMillis()));//时间	
		memberService.edit(pd);
		//日志
		PageData memberId = new PageData();
		memberId.put("ID", pd.getLong("ID"));
		PageData memberInfo = memberService.findById(memberId);
		User user = getCurrentUser();
		sysLogBizService.saveLog(request, user,
				SysLogEnum.MEMBER_AUDIT.getName(), SysLogEnum.MEMBER_AUDIT.getContent()+":"+memberInfo.getString("NICKNAME"));
		return ResponseMessageEnum.SUCCESS.toString();
	}

	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Member");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = memberService.listBlack(page);	//列出Member列表
			mv.addObject("varList", varList);
			mv.setViewName("bmf/member/member_blacklist");
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	/**
	 * 去激活页面
	 */
	@RequestMapping(value="/goActivateMember")
	public ModelAndView goActivateMember(){
		logBefore(logger, "去激活Member页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {			
			pd = memberService.findById(pd);	//根据ID读取
			mv.setViewName("bmf/member/member_activate");
			mv.addObject("msg", "activate");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 去激活页面
	 */
	@RequestMapping(value="/goAudit")
	public ModelAndView goAudit(){
		logBefore(logger, "去审核Member页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {			
			pd = memberService.findById(pd);	//根据ID读取
			mv.setViewName("bmf/member/member_audit");
			mv.addObject("msg", "audit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	
	/**
	 * RESTFUL激活用户
	 */
	@RequestMapping(value="/rest/activate" , method=RequestMethod.POST  ,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public String restActivate(@RequestBody Map<String,String> map) throws Exception{
		logBefore(logger, "激活Member");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit"))
		{return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString();} //校验权限
		PageData pd = new PageData();
		pd.putAll(map);
		pd.put("DEL_FLAG", 3);//激活后待审核，删除标记（1正常，2黑名单，3待审核）	
		Long userId = getSessionUserId();
		//获取用户名称和角色
		PageData userReq = new PageData();
		userReq.put("USER_ID", userId);
		PageData userInfo = userService.findForVO(userReq);
		String userResInfo = userInfo.getString("USERNAME")+"("+userInfo.getString("ROLE_NAME")+")";
		pd.put("UPDATE_PERSON", userResInfo);	
		pd.put("UPDATE_TIME",new Timestamp(System.currentTimeMillis()));//时间	
		memberService.edit(pd);
		//日志
		PageData memberId = new PageData();
		memberId.put("ID", pd.getLong("ID"));
		PageData memberInfo = memberService.findById(memberId);
		User user = getCurrentUser();
		sysLogBizService.saveLog(request, user,
				SysLogEnum.MEMBER_ACTIVATE.getName(), SysLogEnum.MEMBER_ACTIVATE.getContent()+":"+memberInfo.getString("NICKNAME"));
		return ResponseMessageEnum.SUCCESS.toString();
	}
	
}
