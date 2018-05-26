package com.fh.controller.bmf.member;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fh.controller.base.BaseWebController;
import com.fh.service.BaseService;
import com.fh.service.bmf.member.MemberMoneyDetailService;
import com.fh.entity.bmf.member.MemberMoneyDetail;

/** 
 * 类名称：MemberMoneyDetailController
 * 创建人：tyj
 * 创建时间：2017-07-26
 */
@Controller
@RequestMapping(value="/membermoneydetail")
public class MemberMoneyDetailController extends BaseWebController<MemberMoneyDetail> {
	
	String menuUrl = "membermoneydetail/list.do"; //菜单地址(权限用)
	@Resource(name="memberMoneyDetailService")
	private MemberMoneyDetailService memberMoneyDetailService;
	
	@Override
	protected BaseService<MemberMoneyDetail> getBaseService() {
		return memberMoneyDetailService;
	}
	@Override
	protected String getPackageName() {
		return "member";
	}
	@Override
	protected String getObjectName() {
		return "MemberMoneyDetail";
	}
	
	
}
