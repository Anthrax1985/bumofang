package com.fh.service.bmf.member;

import org.springframework.stereotype.Service;

import com.fh.service.BaseService;
import com.fh.entity.bmf.member.Member3DMatchMethod;


/** 
 * 类名称：Member3DMatchMethod
 * 创建人：tyj
 * 创建时间：2017-08-04
 */

@Service("member3DMatchMethodService")
public class Member3DMatchMethodService extends BaseService<Member3DMatchMethod>{

	protected String getNamespace() {
		return "Member3DMatchMethodMapper";
	}
	
}

