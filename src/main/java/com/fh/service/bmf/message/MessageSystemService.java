package com.fh.service.bmf.message;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fh.service.BaseService;
import com.fh.entity.bmf.message.MessageSystem;


/** 
 * 类名称：MessageSystem
 * 创建人：tyj
 * 创建时间：2017-08-07
 */

@Service("messageSystemService")
public class MessageSystemService extends BaseService<MessageSystem>{

	protected String getNamespace() {
		return "MessageSystemMapper";
	}
	/*
	 *	列表(提出被会员删除的消息) 
	 */
	@SuppressWarnings("unchecked")
	public List<MessageSystem> listForMember(Long memberId)throws Exception{
		return (List<MessageSystem>)dao.findForList(getNamespace() + ".listForMember", memberId);
	}
}

