package com.fh.entity.bmf.productrecord;

import com.fh.entity.BaseEntity;
import java.sql.Timestamp;

/** 
 * 类名称：ProductRecordStyle
 * 创建人：tyj
 * 创建时间：2017-07-19
 */

public class ProductRecordStyle extends BaseEntity {
	
	private Long id;
	private Long productId; // 产品id
	private Long styleId; // styleid

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
	public Long getStyleId() {
		return this.styleId;
	}

	public void setStyleId(Long styleId) {
		this.styleId = styleId;
	}

}
