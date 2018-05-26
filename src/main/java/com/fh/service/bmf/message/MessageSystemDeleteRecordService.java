package com.fh.service.bmf.message;

import org.springframework.stereotype.Service;

import com.fh.service.BaseService;
import com.fh.entity.bmf.message.MessageSystemDeleteRecord;


/** 
 * 类名称：MessageSystemDeleteRecord
 * 创建人：tyj
 * 创建时间：2017-08-07
 */

@Service("messageSystemDeleteRecordService")
public class MessageSystemDeleteRecordService extends BaseService<MessageSystemDeleteRecord>{

	protected String getNamespace() {
		return "MessageSystemDeleteRecordMapper";
	}
	
}

