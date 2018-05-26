package com.fh.entity.bmf.datacenter;

import com.fh.entity.BaseEntity;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/** 
 * 类名称：ProductDataStatistics
 * 创建人：tyj
 * 创建时间：2017-08-15
 */

public class DataResDiffType4PieList extends BaseEntity {
	
	private List<ProductDataBrowseAndTypeItem> memberType;
	private List<ProductDataBrowseAndTypeItem> regionType;
	private List<ProductDataBrowseAndTypeItem> colorType;
	private List<ProductDataBrowseAndTypeItem> styleType;
	private List<ProductDataBrowseAndTypeItem> craftType;
	private List<ProductDataBrowseAndTypeItem> materialType;
	private List<ProductDataBrowseAndTypeItem> applicationType;
	
	public List<ProductDataBrowseAndTypeItem> getMemberType() {
		return memberType;
	}
	public void setMemberType(List<ProductDataBrowseAndTypeItem> memberType) {
		this.memberType = memberType;
	}
	public List<ProductDataBrowseAndTypeItem> getRegionType() {
		return regionType;
	}
	public void setRegionType(List<ProductDataBrowseAndTypeItem> regionType) {
		this.regionType = regionType;
	}
	public List<ProductDataBrowseAndTypeItem> getColorType() {
		return colorType;
	}
	public void setColorType(List<ProductDataBrowseAndTypeItem> colorType) {
		this.colorType = colorType;
	}
	public List<ProductDataBrowseAndTypeItem> getStyleType() {
		return styleType;
	}
	public void setStyleType(List<ProductDataBrowseAndTypeItem> styleType) {
		this.styleType = styleType;
	}
	public List<ProductDataBrowseAndTypeItem> getCraftType() {
		return craftType;
	}
	public void setCraftType(List<ProductDataBrowseAndTypeItem> craftType) {
		this.craftType = craftType;
	}
	public List<ProductDataBrowseAndTypeItem> getMaterialType() {
		return materialType;
	}
	public void setMaterialType(List<ProductDataBrowseAndTypeItem> materialType) {
		this.materialType = materialType;
	}
	public List<ProductDataBrowseAndTypeItem> getApplicationType() {
		return applicationType;
	}
	public void setApplicationType(List<ProductDataBrowseAndTypeItem> applicationType) {
		this.applicationType = applicationType;
	}	
}
