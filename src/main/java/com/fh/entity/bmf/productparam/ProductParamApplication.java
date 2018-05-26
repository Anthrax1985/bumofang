package com.fh.entity.bmf.productparam;

import com.fh.entity.BaseEntity;
import java.sql.Timestamp;

/** 
 * 类名称：ProductParamApplication
 * 创建人：tyj
 * 创建时间：2017-07-19
 */

public class ProductParamApplication extends BaseEntity {
	
	private Long id;
	private String applicationName; // 应用名称
	private Integer applicationOrder; // 应用排序
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

	public String getApplicationName() {
		return this.applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public Integer getApplicationOrder() {
		return this.applicationOrder;
	}

	public void setApplicationOrder(Integer applicationOrder) {
		this.applicationOrder = applicationOrder;
	}

}
