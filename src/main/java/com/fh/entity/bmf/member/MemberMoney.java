package com.fh.entity.bmf.member;

import com.fh.entity.BaseEntity;
import java.sql.Timestamp;

/** 
 * 类名称：MemberMoney
 * 创建人：tyj
 * 创建时间：2017-07-26
 */

public class MemberMoney extends BaseEntity {
	
	private Long id;
	private Long memberId; // 用户id
	private Double accountBalance; // 账户余额

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
	public Double getAccountBalance() {
		return this.accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

}
