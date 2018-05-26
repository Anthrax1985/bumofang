package com.fh.service.bmf.syslog;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fh.entity.bmf.syslog.SysLog;
import com.fh.entity.system.User;
import com.fh.util.web.IpAddressUtil;

@Service
public class SysLogBizService {
	
	@Autowired
	private SysLogService sysLogService;

	
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

	public void saveAppLog(HttpServletRequest request, String operationName, String operationContent){
		String ip = IpAddressUtil.getIpAddress(request);
		SysLog sysLog = new SysLog();
		sysLog.setCreateTime(new Timestamp(System.currentTimeMillis()));
		sysLog.setUserName("App User");
		sysLog.setOperationContent(operationContent);
		sysLog.setCreateUserId(0L);
		sysLog.setPageName(operationName);
		sysLog.setUserIp(ip);

		try {
			sysLogService.save(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
