package com.fh.controller.bmf.member;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fh.controller.base.BaseWebController;
import com.fh.service.BaseService;
import com.fh.service.bmf.member.MemberFavorProductService;
import com.fh.entity.bmf.member.MemberFavorProduct;

/** 
 * 类名称：MemberFavorProductController
 * 创建人：tyj
 * 创建时间：2017-07-21
 */
@Controller
@RequestMapping(value="/memberfavorproduct")
public class MemberFavorProductController extends BaseWebController<MemberFavorProduct> {
	
	String menuUrl = "memberfavorproduct/list.do"; //菜单地址(权限用)
	@Resource(name="memberFavorProductService")
	private MemberFavorProductService memberFavorProductService;
	
	@Override
	protected BaseService<MemberFavorProduct> getBaseService() {
		return memberFavorProductService;
	}
	@Override
	protected String getPackageName() {
		return "member";
	}
	@Override
	protected String getObjectName() {
		return "MemberFavorProduct";
	}
	
	
}
