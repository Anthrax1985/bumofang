package com.fh.entity.bmf.member;

import com.fh.entity.BaseEntity;
import java.sql.Timestamp;

/** 
 * 类名称：MemberProfessionSup
 * 创建人：tyj
 * 创建时间：2017-07-23
 */

public class MemberProfessionSup extends BaseEntity {
	
	private Long id;
	private String professionSupName; // 一级职业名称
	private Long listOrder; // 排序

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProfessionSupName() {
		return this.professionSupName;
	}

	public void setProfessionSupName(String professionSupName) {
		this.professionSupName = professionSupName;
	}
	public Long getListOrder() {
		return this.listOrder;
	}

	public void setListOrder(Long listOrder) {
		this.listOrder = listOrder;
	}

}
