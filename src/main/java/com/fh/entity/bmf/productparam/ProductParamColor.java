package com.fh.entity.bmf.productparam;

import com.fh.entity.BaseEntity;
import java.sql.Timestamp;

/** 
 * 类名称：ProductParamColor
 * 创建人：tyj
 * 创建时间：2017-07-18
 */

public class ProductParamColor extends BaseEntity {
	
	private Long id;
	private String colorName; // 颜色名称
	private String colorIcon; // 颜色图标
	private Integer colorOrder; // 颜色排序
	private Integer selected ; //是否被选中

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getColorName() {
		return this.colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	public String getColorIcon() {
		return this.colorIcon;
	}

	public void setColorIcon(String colorIcon) {
		this.colorIcon = colorIcon;
	}
	public Integer getColorOrder() {
		return this.colorOrder;
	}

	public void setColorOrder(Integer colorOrder) {
		this.colorOrder = colorOrder;
	}

	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}
}
