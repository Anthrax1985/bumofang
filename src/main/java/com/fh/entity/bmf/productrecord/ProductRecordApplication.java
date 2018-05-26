package com.fh.entity.bmf.productrecord;

import com.fh.entity.BaseEntity;
import java.sql.Timestamp;

/** 
 * 类名称：ProductRecordApplication
 * 创建人：tyj
 * 创建时间：2017-07-19
 */

public class ProductRecordApplication extends BaseEntity {
	
	private Long id;
	private Long productId; // 产品id
	private Long applicationId; // 应用id
	private String applicationName; // 应用名称

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
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
	public Long getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

}
