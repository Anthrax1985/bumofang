package com.fh.entity.bmf.productparam;

import com.fh.entity.BaseEntity;
import java.sql.Timestamp;

/** 
 * 类名称：ProductParamCraft
 * 创建人：tyj
 * 创建时间：2017-07-19
 */

public class ProductParamCraft extends BaseEntity {
	
	private Long id;
	private String craftName; // 工艺名称
	private Integer craftOrder; // 工艺排序

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCraftName() {
		return this.craftName;
	}

	public void setCraftName(String craftName) {
		this.craftName = craftName;
	}
	public Integer getCraftOrder() {
		return this.craftOrder;
	}

	public void setCraftOrder(Integer craftOrder) {
		this.craftOrder = craftOrder;
	}

}
