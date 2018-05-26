package com.fh.service.bmf.syslog;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.fh.entity.bmf.syslog.SysLog;
import com.fh.entity.system.User;

import com.fh.util.web.IpAddressUtil;

public class SysLogServiceHelper {
	
	private static SysLogServiceHelper instance = new SysLogServiceHelper();
	
	@Autowired
	private SysLogService sysLogService;
	
	public static SysLogServiceHelper getInstance(){
		return instance;
	}
	
	private SysLogServiceHelper(){
		
	}
	
	public void saveLog(HttpServletRequest request, User user, 
			String operationName, String operationContent){
		String ip = IpAddressUtil.getIpAddress(request);
		SysLog sysLog = new SysLog();
		sysLog.setCreateTime(new Timestamp(System.currentTimeMillis()));
		sysLog.setUserName(user.getNAME());
		sysLog.setOperationContent(operationContent);
		sysLog.setCreateUserId(user.getUSER_ID());
		sysLog.setPageName(operationName);
		sysLog.setUserIp(ip);
		
		try {
			sysLogService.save(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
