package com.fh.service.bmf.message;

import org.springframework.stereotype.Service;

import com.fh.service.BaseService;
import com.fh.entity.bmf.message.MessageMember;


/** 
 * 类名称：MessageMember
 * 创建人：tyj
 * 创建时间：2017-08-07
 */

@Service("messageMemberService")
public class MessageMemberService extends BaseService<MessageMember>{

	protected String getNamespace() {
		return "MessageMemberMapper";
	}
	
}

