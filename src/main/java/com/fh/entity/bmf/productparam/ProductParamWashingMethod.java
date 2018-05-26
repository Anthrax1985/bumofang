package com.fh.entity.bmf.productparam;

import com.fh.entity.BaseEntity;
import java.sql.Timestamp;

/** 
 * 类名称：ProductParamWashingMethod
 * 创建人：tyj
 * 创建时间：2017-07-19
 */

public class ProductParamWashingMethod extends BaseEntity {
	
	private Long id;
	private String washingMethodName; // 水洗标志名称
	private String washingMethodIcon; // 水洗标志图标
	private Integer washingMethodOrder; // 水洗标志排序
	private Integer selected ; //是否被选中
	public Long getId() {
		return this.id;
	}

	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWashingMethodName() {
		return this.washingMethodName;
	}

	public void setWashingMethodName(String washingMethodName) {
		this.washingMethodName = washingMethodName;
	}
	public String getWashingMethodIcon() {
		return this.washingMethodIcon;
	}

	public void setWashingMethodIcon(String washingMethodIcon) {
		this.washingMethodIcon = washingMethodIcon;
	}
	public Integer getWashingMethodOrder() {
		return this.washingMethodOrder;
	}

	public void setWashingMethodOrder(Integer washingMethodOrder) {
		this.washingMethodOrder = washingMethodOrder;
	}

}
