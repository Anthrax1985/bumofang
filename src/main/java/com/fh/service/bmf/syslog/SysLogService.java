package com.fh.service.bmf.syslog;

import org.springframework.stereotype.Service;

import com.fh.service.BaseService;
import com.fh.entity.bmf.syslog.SysLog;


/** 
 * 类名称：SysLog
 * 创建人：tyj
 * 创建时间：2017-07-17
 */

@Service("sysLogService")
public class SysLogService extends BaseService<SysLog>{

	protected String getNamespace() {
		return "SysLogMapper";
	}
	
}

