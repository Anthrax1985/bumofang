package com.fh.entity.bmf.member;

import com.fh.entity.BaseEntity;
import java.sql.Timestamp;

/** 
 * 类名称：MemberMoneyDetail
 * 创建人：tyj
 * 创建时间：2017-07-26
 */

public class MemberMoneyDetail extends BaseEntity {
	
	private Long id;
	private Long memberId; // 用户id
	private String serialNumber; // 明细编号
	private Double amount; // 金额
	private Integer type; // 明细类型

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMemberId() {
		return this.memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
