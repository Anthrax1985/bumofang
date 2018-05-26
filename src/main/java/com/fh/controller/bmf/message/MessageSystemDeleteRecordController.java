package com.fh.controller.bmf.message;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fh.controller.base.BaseWebController;
import com.fh.service.BaseService;
import com.fh.service.bmf.message.MessageSystemDeleteRecordService;
import com.fh.entity.bmf.message.MessageSystemDeleteRecord;

/** 
 * 类名称：MessageSystemDeleteRecordController
 * 创建人：tyj
 * 创建时间：2017-08-07
 */
@Controller
@RequestMapping(value="/messagesystemdeleterecord")
public class MessageSystemDeleteRecordController extends BaseWebController<MessageSystemDeleteRecord> {
	
	String menuUrl = "messagesystemdeleterecord/list.do"; //菜单地址(权限用)
	@Resource(name="messageSystemDeleteRecordService")
	private MessageSystemDeleteRecordService messageSystemDeleteRecordService;
	
	@Override
	protected BaseService<MessageSystemDeleteRecord> getBaseService() {
		return messageSystemDeleteRecordService;
	}
	@Override
	protected String getPackageName() {
		return "message";
	}
	@Override
	protected String getObjectName() {
		return "MessageSystemDeleteRecord";
	}
	
	
}
