package com.fh.entity.bmf.productrecord;

import com.fh.entity.BaseEntity;
import java.sql.Timestamp;

/** 
 * 类名称：ProductRecordWashingMethod
 * 创建人：tyj
 * 创建时间：2017-07-19
 */

public class ProductRecordWashingMethod extends BaseEntity {
	
	private Long id;
	private Long productId; // 产品id
	private Long washingMethodId; // 水洗标志id
	private String washingMethodIcon; // 水洗标志icon



	public String getWashingMethodIcon() {
		return washingMethodIcon;
	}

	public void setWashingMethodIcon(String washingMethodIcon) {
		this.washingMethodIcon = washingMethodIcon;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getWashingMethodId() {
		return this.washingMethodId;
	}

	public void setWashingMethodId(Long washingMethodId) {
		this.washingMethodId = washingMethodId;
	}

}
