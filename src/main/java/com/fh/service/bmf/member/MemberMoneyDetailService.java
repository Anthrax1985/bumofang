package com.fh.service.bmf.member;

import org.springframework.stereotype.Service;

import com.fh.service.BaseService;
import com.fh.entity.bmf.member.MemberMoneyDetail;


/** 
 * 类名称：MemberMoneyDetail
 * 创建人：tyj
 * 创建时间：2017-07-26
 */

@Service("memberMoneyDetailService")
public class MemberMoneyDetailService extends BaseService<MemberMoneyDetail>{

	protected String getNamespace() {
		return "MemberMoneyDetailMapper";
	}
	
}

