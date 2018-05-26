package com.fh.service.bmf.member;

import org.springframework.stereotype.Service;

import com.fh.service.BaseService;
import com.fh.entity.bmf.member.MemberProfessionSup;


/** 
 * 类名称：MemberProfessionSup
 * 创建人：tyj
 * 创建时间：2017-07-23
 */

@Service("memberProfessionSupService")
public class MemberProfessionSupService extends BaseService<MemberProfessionSup>{

	protected String getNamespace() {
		return "MemberProfessionSupMapper";
	}
	
}

