package com.fh.entity.bmf.productparam;

import com.fh.entity.BaseEntity;
import java.sql.Timestamp;

/** 
 * 类名称：ProductParamMaterial
 * 创建人：tyj
 * 创建时间：2017-07-19
 */

public class ProductParamMaterial extends BaseEntity {
	
	private Long id;
	private String materialName; // 材质名称
	private Integer materialOrder; // 材质排序

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMaterialName() {
		return this.materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public Integer getMaterialOrder() {
		return this.materialOrder;
	}

	public void setMaterialOrder(Integer materialOrder) {
		this.materialOrder = materialOrder;
	}

}
