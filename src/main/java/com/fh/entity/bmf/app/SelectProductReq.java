package com.fh.entity.bmf.app;

import java.util.List;

import com.fh.entity.bmf.member.Member3DMatchMethod;

public class SelectProductReq {
	private Long colorId;
	private Long styleId;
	private Long applicationId;
	private Long materialId;
	private Long is_new;
	public Long getColorId() {
		return colorId;
	}
	public void setColorId(Long colorId) {
		this.colorId = colorId;
	}
	public Long getStyleId() {
		return styleId;
	}
	public void setStyleId(Long styleId) {
		this.styleId = styleId;
	}
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public Long getMaterialId() {
		return materialId;
	}
	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}

	public Long getIs_new() {
		return is_new;
	}

	public void setIs_new(Long is_new) {
		this.is_new = is_new;
	}
}
