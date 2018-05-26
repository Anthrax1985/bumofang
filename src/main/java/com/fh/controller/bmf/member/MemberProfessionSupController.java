package com.fh.controller.bmf.member;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fh.controller.base.BaseWebController;
import com.fh.service.BaseService;
import com.fh.service.bmf.member.MemberProfessionSupService;
import com.fh.entity.bmf.member.MemberProfessionSup;

/** 
 * 类名称：MemberProfessionSupController
 * 创建人：tyj
 * 创建时间：2017-07-23
 */
@Controller
@RequestMapping(value="/memberprofessionsup")
public class MemberProfessionSupController extends BaseWebController<MemberProfessionSup> {
	
	String menuUrl = "memberprofessionsup/list.do"; //菜单地址(权限用)
	@Resource(name="memberProfessionSupService")
	private MemberProfessionSupService memberProfessionSupService;
	
	@Override
	protected BaseService<MemberProfessionSup> getBaseService() {
		return memberProfessionSupService;
	}
	@Override
	protected String getPackageName() {
		return "member";
	}
	@Override
	protected String getObjectName() {
		return "MemberProfessionSup";
	}
	
	
}
