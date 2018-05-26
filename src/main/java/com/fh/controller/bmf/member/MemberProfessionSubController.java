package com.fh.controller.bmf.member;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fh.controller.base.BaseWebController;
import com.fh.service.BaseService;
import com.fh.service.bmf.member.MemberProfessionSubService;
import com.fh.entity.bmf.member.MemberProfessionSub;

/** 
 * 类名称：MemberProfessionSubController
 * 创建人：tyj
 * 创建时间：2017-07-23
 */
@Controller
@RequestMapping(value="/memberprofessionsub")
public class MemberProfessionSubController extends BaseWebController<MemberProfessionSub> {
	
	String menuUrl = "memberprofessionsub/list.do"; //菜单地址(权限用)
	@Resource(name="memberProfessionSubService")
	private MemberProfessionSubService memberProfessionSubService;
	
	@Override
	protected BaseService<MemberProfessionSub> getBaseService() {
		return memberProfessionSubService;
	}
	@Override
	protected String getPackageName() {
		return "member";
	}
	@Override
	protected String getObjectName() {
		return "MemberProfessionSub";
	}
	
	
}
