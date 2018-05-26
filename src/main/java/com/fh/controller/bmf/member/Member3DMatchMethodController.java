package com.fh.controller.bmf.member;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fh.controller.base.BaseWebController;
import com.fh.service.BaseService;
import com.fh.service.bmf.member.Member3DMatchMethodService;
import com.fh.entity.bmf.member.Member3DMatchMethod;

/** 
 * 类名称：Member3DMatchMethodController
 * 创建人：tyj
 * 创建时间：2017-08-04
 */
@Controller
@RequestMapping(value="/member3dmatchmethod")
public class Member3DMatchMethodController extends BaseWebController<Member3DMatchMethod> {
	
	String menuUrl = "member3dmatchmethod/list.do"; //菜单地址(权限用)
	@Resource(name="member3DMatchMethodService")
	private Member3DMatchMethodService member3DMatchMethodService;
	
	@Override
	protected BaseService<Member3DMatchMethod> getBaseService() {
		return member3DMatchMethodService;
	}
	@Override
	protected String getPackageName() {
		return "member";
	}
	@Override
	protected String getObjectName() {
		return "Member3DMatchMethod";
	}
	
	
}
