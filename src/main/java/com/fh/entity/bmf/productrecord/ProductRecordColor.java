package com.fh.entity.bmf.productrecord;

import com.fh.entity.BaseEntity;
import java.sql.Timestamp;

/** 
 * 类名称：ProductRecordColor
 * 创建人：tyj
 * 创建时间：2017-07-18
 */

public class ProductRecordColor extends BaseEntity {
	
	private Long id;
	private Long productId; // 产品id
	private Long colorId; // 颜色id

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
	public Long getColorId() {
		return this.colorId;
	}

	public void setColorId(Long colorId) {
		this.colorId = colorId;
	}

}
