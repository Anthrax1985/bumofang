package com.fh.entity.bmf.product;



import java.sql.Timestamp;
import java.util.ArrayList;

import com.fh.entity.BaseEntity;



/** 
 * 类名称：Product
 * 创建人：tyj
 * 创建时间：2017-07-17
 */

public class ProductEviNameAsso  {
	
	private Long id;
	private Long productId;
	private String eviName;
	
	private Long createUserId;
	
	private Timestamp createTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getEviName() {
		return eviName;
	}
	public void setEviName(String eviName) {
		this.eviName = eviName;
	}
	public Long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	
}
