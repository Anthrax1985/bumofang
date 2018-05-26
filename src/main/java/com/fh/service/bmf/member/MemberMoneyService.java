package com.fh.service.bmf.member;

import org.springframework.stereotype.Service;

import com.fh.service.BaseService;
import com.fh.entity.bmf.member.MemberMoney;


/** 
 * 类名称：MemberMoney
 * 创建人：tyj
 * 创建时间：2017-07-26
 */

@Service("memberMoneyService")
public class MemberMoneyService extends BaseService<MemberMoney>{

	protected String getNamespace() {
		return "MemberMoneyMapper";
	}
	
}

