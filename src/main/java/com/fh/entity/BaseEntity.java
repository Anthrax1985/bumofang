package com.fh.entity;

import java.sql.Timestamp;

import org.codehaus.jackson.annotate.JsonIgnore;

public class BaseEntity{
	
	private Long createUserId;
	
	private Timestamp createTime;
	
	private Long updateUserId;
	
	private Timestamp updateTime;
	
	private Integer delFlag;

	@JsonIgnore
	public Long getCreateUserId() {
		return createUserId;
	}

	@JsonIgnore
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	@JsonIgnore
	public Timestamp getCreateTime() {
		return createTime;
	}

	@JsonIgnore
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@JsonIgnore
	public Long getUpdateUserId() {
		return updateUserId;
	}

	@JsonIgnore
	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	@JsonIgnore
	public Timestamp getUpdateTime() {
		return updateTime;
	}

	@JsonIgnore
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	

}
