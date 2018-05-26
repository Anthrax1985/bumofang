package com.fh.entity.bmf.message;

import com.fh.entity.BaseEntity;
import java.sql.Timestamp;

/** 
 * 类名称：MessageSystem
 * 创建人：tyj
 * 创建时间：2017-08-07
 */

public class MessageSystem extends BaseEntity {
	
	private Long id;
	private String releaseTitle; // 消息标题
	private String releaseInfo; // 消息内容
	private Long releaserId; // 发布人id
	private String releaserInfo; // 消息发布人
	private Timestamp releaseTime; // 发布时间
	private Long auditorId; // 审批人id
	private String auditorInfo; // 审批人
	private Timestamp auditTime; // 审批时间
	private Integer status; // 消息状态(0待审批1审批通过2审批拒绝)

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReleaseTitle() {
		return this.releaseTitle;
	}

	public void setReleaseTitle(String releaseTitle) {
		this.releaseTitle = releaseTitle;
	}
	public String getReleaseInfo() {
		return this.releaseInfo;
	}

	public void setReleaseInfo(String releaseInfo) {
		this.releaseInfo = releaseInfo;
	}
	public Long getReleaserId() {
		return this.releaserId;
	}

	public void setReleaserId(Long releaserId) {
		this.releaserId = releaserId;
	}
	public String getReleaserInfo() {
		return this.releaserInfo;
	}

	public void setReleaserInfo(String releaserInfo) {
		this.releaserInfo = releaserInfo;
	}
	public Timestamp getReleaseTime() {
		return this.releaseTime;
	}

	public void setReleaseTime(Timestamp releaseTime) {
		this.releaseTime = releaseTime;
	}
	public Long getAuditorId() {
		return this.auditorId;
	}

	public void setAuditorId(Long auditorId) {
		this.auditorId = auditorId;
	}
	public String getAuditorInfo() {
		return this.auditorInfo;
	}

	public void setAuditorInfo(String auditorInfo) {
		this.auditorInfo = auditorInfo;
	}
	public Timestamp getAuditTime() {
		return this.auditTime;
	}

	public void setAuditTime(Timestamp auditTime) {
		this.auditTime = auditTime;
	}
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getStatusInfo(){
		String statusInfo = null;
		if(status == 0){
			statusInfo = "待审核";
		}else if(status == 1){
			statusInfo = "审核通过";
		}else if(status == 2){
			statusInfo = "审核拒绝";
		}else{
			statusInfo = "错误状态";
		}
		return statusInfo;
	}
}
