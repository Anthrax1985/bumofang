package com.fh.entity.bmf.productrecord;

import com.fh.entity.BaseEntity;
import java.sql.Timestamp;

/** 
 * 类名称：ProductRecordMatchScheme
 * 创建人：tyj
 * 创建时间：2017-07-24
 */

public class ProductRecordMatchScheme extends BaseEntity {
	
	private Long id;
	private Long productId; // 产品id
	private Long matchProductId; // 搭配方案
	private String matchProductIcon; // 搭配方案图片
	

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
	public Long getMatchProductId() {
		return this.matchProductId;
	}

	public void setMatchProductId(Long matchProductId) {
		this.matchProductId = matchProductId;
	}
	public String getMatchProductIcon() {
		return this.matchProductIcon;
	}

	public void setMatchProductIcon(String matchProductIcon) {
		this.matchProductIcon = matchProductIcon;
	}

	
}
