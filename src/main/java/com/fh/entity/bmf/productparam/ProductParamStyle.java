package com.fh.entity.bmf.productparam;

import com.fh.entity.BaseEntity;
import java.sql.Timestamp;

/** 
 * 类名称：ProductParamStyle
 * 创建人：tyj
 * 创建时间：2017-07-19
 */

public class ProductParamStyle extends BaseEntity {
	
	private Long id;
	private String styleName; // 风格名称
	private Integer styleOrder; // 风格排序
	private Integer selected ; //是否被选中

	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStyleName() {
		return this.styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	public Integer getStyleOrder() {
		return this.styleOrder;
	}

	public void setStyleOrder(Integer styleOrder) {
		this.styleOrder = styleOrder;
	}

}
