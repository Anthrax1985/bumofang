package com.fh.entity.bmf.syslog;

import com.fh.entity.BaseEntity;
import java.sql.Timestamp;

/** 
 * 类名称：SysLog
 * 创建人：tyj
 * 创建时间：2017-07-17
 */

public class SysLog extends BaseEntity {
	
	private Long id;
	private String userName; // 用户名
	private String userIp; // 用户IP
	private String roleType; // 职级
	private String pageName; // 页面名称
	private String operationContent; // 操作内容
	private String createTimeShow; // 操作时间(被格式话)

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserIp() {
		return this.userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	public String getRoleType() {
		return this.roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public String getPageName() {
		return this.pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getOperationContent() {
		return this.operationContent;
	}

	public void setOperationContent(String operationContent) {
		this.operationContent = operationContent;
	}

	public String getCreateTimeShow() {
		return createTimeShow;
	}

	public void setCreateTimeShow(String createTimeShow) {
		this.createTimeShow = createTimeShow;
	}

}
