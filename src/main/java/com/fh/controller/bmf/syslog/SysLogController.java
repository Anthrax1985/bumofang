package com.fh.controller.bmf.syslog;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fh.controller.base.BaseWebController;
import com.fh.service.BaseService;
import com.fh.service.bmf.syslog.SysLogService;
import com.fh.entity.bmf.syslog.SysLog;

/** 
 * 类名称：SysLogController
 * 创建人：tyj
 * 创建时间：2017-07-17
 */
@Controller
@RequestMapping(value="/syslog")
public class SysLogController extends BaseWebController<SysLog> {
	
	String menuUrl = "syslog/list.do"; //菜单地址(权限用)
	@Resource(name="sysLogService")
	private SysLogService sysLogService;
	
	@Override
	protected BaseService<SysLog> getBaseService() {
		return sysLogService;
	}
	@Override
	protected String getPackageName() {
		return "syslog";
	}
	@Override
	protected String getObjectName() {
		return "SysLog";
	}
	
	
}
