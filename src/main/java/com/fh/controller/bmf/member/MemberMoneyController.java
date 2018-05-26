package com.fh.controller.bmf.member;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fh.controller.base.BaseWebController;
import com.fh.service.BaseService;
import com.fh.service.bmf.member.MemberMoneyService;
import com.fh.entity.bmf.member.MemberMoney;

/** 
 * 类名称：MemberMoneyController
 * 创建人：tyj
 * 创建时间：2017-07-26
 */
@Controller
@RequestMapping(value="/membermoney")
public class MemberMoneyController extends BaseWebController<MemberMoney> {
	
	String menuUrl = "membermoney/list.do"; //菜单地址(权限用)
	@Resource(name="memberMoneyService")
	private MemberMoneyService memberMoneyService;
	
	@Override
	protected BaseService<MemberMoney> getBaseService() {
		return memberMoneyService;
	}
	@Override
	protected String getPackageName() {
		return "member";
	}
	@Override
	protected String getObjectName() {
		return "MemberMoney";
	}
	
	
}
