package com.fh.entity.bmf.chinadivision;

import com.fh.entity.BaseEntity;

import java.sql.Date;
import java.sql.Timestamp;

/** 
 * 类名称：ChinaDivision
 * 创建人：tyj
 * 创建时间：2017-07-23
 */

public class ChinaDivision extends BaseEntity {
	
	private Long id;
	private String divisionName; // division_name
	private Long divisionOrderby; // 行政区划排序
	private Long divisionLevel; // 行政区划级别
	private Long divisionParentid; // 行政区划父节点id
	private String zipCode; // 邮编
	private String addressCode; // 区域编码

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDivisionName() {
		return this.divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public Long getDivisionOrderby() {
		return this.divisionOrderby;
	}

	public void setDivisionOrderby(Long divisionOrderby) {
		this.divisionOrderby = divisionOrderby;
	}
	public Long getDivisionLevel() {
		return this.divisionLevel;
	}

	public void setDivisionLevel(Long divisionLevel) {
		this.divisionLevel = divisionLevel;
	}
	public Long getDivisionParentid() {
		return this.divisionParentid;
	}

	public void setDivisionParentid(Long divisionParentid) {
		this.divisionParentid = divisionParentid;
	}
	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getAddressCode() {
		return this.addressCode;
	}

	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}


}
