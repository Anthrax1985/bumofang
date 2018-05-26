package com.fh.entity.bmf.product;

import com.fh.entity.BaseEntity;
import com.fh.entity.bmf.app.ProductMathMethodItem;
import com.fh.util.PageData;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

/** 
 * 类名称：Product
 * 创建人：tyj
 * 创建时间：2017-07-17
 */

public class Product extends BaseEntity {
	
	private Long id;
	private String productName; // 品名
	private String productCraft; // 工艺
	private String productMaterial; // 材质
	private Double productNarrowPrice; // 单幅价格
	private Double productWidePrice; // 宽幅价格
	private String productComponent; // 成份
	private Double productUnitWeight; // 每平方客重
	private Double productNarrowWidth; // 幅宽单幅
	private Double productWideWidth; // 幅宽宽幅
	private Double patternHorizontalSize; // 花型尺寸-水平
	private Double patternVerticalSize; // 花型尺寸-垂直
	private String productSourceArea; // 原产地
	private String patternPicture1; // 花型图1600*800
	private String patternPicture2; // 花型图800*800
	private String qualityPicture1; // 质地图1600*800
	private String qualityPicture2; // 质地图400*200
	private String qualityPicture3; // 质地图800*800
	private String qualityPicture4; // 质地图400*400
	private String qualityControlReport; // 质检报告
/*	private Integer delFlag; // 删除标记(1删除，0不删除)
*/	
	private String colorStrList;//颜色组合
	private String styleStrList;//风格组合
	private String applicationStrList;//应用组合
	
	private String craftStr;//工艺组合
	private String materialStr;//材质组合

	private Integer productUploadTime;//布料上传时间戳
	private Integer d3dUploadTime;//3d渲染图上传时间戳

	private ArrayList<ProductMathMethodItem> matchMethodIdList;//搭配方案列表
	
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCraft() {
		return this.productCraft;
	}

	public void setProductCraft(String productCraft) {
		this.productCraft = productCraft;
	}
	public String getProductMaterial() {
		return this.productMaterial;
	}

	public void setProductMaterial(String productMaterial) {
		this.productMaterial = productMaterial;
	}
	public Double getProductNarrowPrice() {
		return this.productNarrowPrice;
	}

	public void setProductNarrowPrice(Double productNarrowPrice) {
		this.productNarrowPrice = productNarrowPrice;
	}
	public Double getProductWidePrice() {
		return this.productWidePrice;
	}

	public void setProductWidePrice(Double productWidePrice) {
		this.productWidePrice = productWidePrice;
	}
	public String getProductComponent() {
		return this.productComponent;
	}

	public void setProductComponent(String productComponent) {
		this.productComponent = productComponent;
	}
	public Double getProductUnitWeight() {
		return this.productUnitWeight;
	}

	public void setProductUnitWeight(Double productUnitWeight) {
		this.productUnitWeight = productUnitWeight;
	}
	public Double getProductNarrowWidth() {
		return this.productNarrowWidth;
	}

	public void setProductNarrowWidth(Double productNarrowWidth) {
		this.productNarrowWidth = productNarrowWidth;
	}
	public Double getProductWideWidth() {
		return this.productWideWidth;
	}

	public void setProductWideWidth(Double productWideWidth) {
		this.productWideWidth = productWideWidth;
	}
	public Double getPatternHorizontalSize() {
		return this.patternHorizontalSize;
	}

	public void setPatternHorizontalSize(Double patternHorizontalSize) {
		this.patternHorizontalSize = patternHorizontalSize;
	}
	public Double getPatternVerticalSize() {
		return this.patternVerticalSize;
	}

	public void setPatternVerticalSize(Double patternVerticalSize) {
		this.patternVerticalSize = patternVerticalSize;
	}
	public String getProductSourceArea() {
		return this.productSourceArea;
	}

	public void setProductSourceArea(String productSourceArea) {
		this.productSourceArea = productSourceArea;
	}
	public String getPatternPicture1() {
		return this.patternPicture1;
	}

	public void setPatternPicture1(String patternPicture1) {
		this.patternPicture1 = patternPicture1;
	}
	public String getPatternPicture2() {
		return this.patternPicture2;
	}

	public void setPatternPicture2(String patternPicture2) {
		this.patternPicture2 = patternPicture2;
	}
	public String getQualityPicture1() {
		return this.qualityPicture1;
	}

	public void setQualityPicture1(String qualityPicture1) {
		this.qualityPicture1 = qualityPicture1;
	}
	public String getQualityPicture2() {
		return this.qualityPicture2;
	}

	public void setQualityPicture2(String qualityPicture2) {
		this.qualityPicture2 = qualityPicture2;
	}
	public String getQualityPicture3() {
		return this.qualityPicture3;
	}

	public void setQualityPicture3(String qualityPicture3) {
		this.qualityPicture3 = qualityPicture3;
	}
	public String getQualityPicture4() {
		return this.qualityPicture4;
	}

	public void setQualityPicture4(String qualityPicture4) {
		this.qualityPicture4 = qualityPicture4;
	}
	public String getQualityControlReport() {
		return this.qualityControlReport;
	}

	public void setQualityControlReport(String qualityControlReport) {
		this.qualityControlReport = qualityControlReport;
	}


	public ArrayList<ProductMathMethodItem> getMatchMethodIdList() {
		return matchMethodIdList;
	}

	public void setMatchMethodIdList(ArrayList<ProductMathMethodItem> matchMethodIdList) {
		this.matchMethodIdList = matchMethodIdList;
	}

	public String getColorStrList() {
		return colorStrList;
	}

	public void setColorStrList(String colorStrList) {
		this.colorStrList = colorStrList;
	}

	public String getStyleStrList() {
		return styleStrList;
	}

	public void setStyleStrList(String styleStrList) {
		this.styleStrList = styleStrList;
	}

	public String getApplicationStrList() {
		return applicationStrList;
	}

	public void setApplicationStrList(String applicationStrList) {
		this.applicationStrList = applicationStrList;
	}

	public String getCraftStr() {
		return craftStr;
	}

	public void setCraftStr(String craftStr) {
		this.craftStr = craftStr;
	}

	public String getMaterialStr() {
		return materialStr;
	}

	public void setMaterialStr(String materialStr) {
		this.materialStr = materialStr;
	}
	
/*	public Integer getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}*/

	public Integer getTotalNum(Map<String, PageData> cart_map){
		String id = String.valueOf(this.getId());
		PageData elem = cart_map.get(id);
		Integer total_num = elem.getInteger("total_num");
		return total_num;
	}
	public Integer getOptionID(Map<String, PageData> cart_map){
		String id = String.valueOf(this.getId());
		PageData elem = cart_map.get(id);
		Integer option_id = elem.getInteger("option_id");
		return option_id;
	}

	public Integer getProductUploadTime() {return productUploadTime;}

	public void setProductUploadTime(Integer productUploadTime) {this.productUploadTime = productUploadTime;}

	public Integer getD3dUploadTime() {return d3dUploadTime;}

	public void setD3dUploadTime(Integer d3dUploadTime) {this.d3dUploadTime = d3dUploadTime;}
}
