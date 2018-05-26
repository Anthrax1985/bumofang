package com.fh.entity.bmf.statistics;

import com.fh.entity.BaseEntity;
import java.sql.Timestamp;

/** 
 * 类名称：ProductBrowse
 * 创建人：tyj
 * 创建时间：2017-08-02
 */

public class ProductBrowse extends BaseEntity {
	
	private Long id;
	private Long memberId; // 用户id
	private Long productId; // 产品id
	private String ipadIp; // iPadIP
	private Timestamp browseTime; // 浏览时间

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
	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getIpadIp() {
		return this.ipadIp;
	}

	public void setIpadIp(String ipadIp) {
		this.ipadIp = ipadIp;
	}

	public Timestamp getBrowseTime() {
		return browseTime;
	}

	public void setBrowseTime(Timestamp browseTime) {
		this.browseTime = browseTime;
	}



}
