package com.fh.entity.bmf.member;

import com.fh.entity.BaseEntity;
import java.sql.Timestamp;

/** 
 * 类名称：MemberProfessionSub
 * 创建人：tyj
 * 创建时间：2017-07-23
 */

public class MemberProfessionSub extends BaseEntity {
	
	private Long id;
	private Long professionSupId; // 一级职业id
	private String professionSupName; // 一级职业名称
	private String professionSubName; // 二级职业名称
	private Long listOrder; // 排序

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProfessionSupId() {
		return this.professionSupId;
	}

	public void setProfessionSupId(Long professionSupId) {
		this.professionSupId = professionSupId;
	}
	public String getProfessionSupName() {
		return this.professionSupName;
	}

	public void setProfessionSupName(String professionSupName) {
		this.professionSupName = professionSupName;
	}
	public String getProfessionSubName() {
		return this.professionSubName;
	}

	public void setProfessionSubName(String professionSubName) {
		this.professionSubName = professionSubName;
	}
	public Long getListOrder() {
		return this.listOrder;
	}

	public void setListOrder(Long listOrder) {
		this.listOrder = listOrder;
	}

}
