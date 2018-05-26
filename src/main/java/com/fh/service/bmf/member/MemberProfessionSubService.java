package com.fh.service.bmf.member;

import org.springframework.stereotype.Service;

import com.fh.service.BaseService;
import com.fh.entity.bmf.member.MemberProfessionSub;


/** 
 * 类名称：MemberProfessionSub
 * 创建人：tyj
 * 创建时间：2017-07-23
 */

@Service("memberProfessionSubService")
public class MemberProfessionSubService extends BaseService<MemberProfessionSub>{

	protected String getNamespace() {
		return "MemberProfessionSubMapper";
	}
	
}

