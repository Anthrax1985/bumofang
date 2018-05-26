package com.fh.entity.bmf.product;

import java.util.ArrayList;
import java.util.List;

import com.fh.entity.bmf.productrecord.ProductMatchSchemeItem;

/**
 * @author Administrator
 *
 */
public class ProductDetail {

	private Long id;
	private String productName; // 品名
	private String productComponent; // 成份	
	private String productUnitWeight; // 每平方克重(不带单位)
	private String productNarrowWidth; // 幅宽单幅
	private String productWideWidth; // 幅宽宽幅
	private Double productNarrowPrice; // 单幅价格
	private Double productWidePrice; // 宽幅价格
	private String patternHorizontalSize; // 花型尺寸-水平
	private String patternVerticalSize; // 花型尺寸-垂直
	private String productSourceArea; // 原产地
	private String patternPicture1; // 花型图1600*800
	private String patternPicture2; // 花型图800*800
	private String qualityPicture1; // 质地图1600*800
	private String qualityPicture2; // 质地图400*200
	private String qualityPicture3; // 质地图800*800
	private String qualityPicture4; // 质地图400*400
	private String qualityControlReport; // 质检报告
	private ArrayList<String> washMethodIconList;//水洗标志列表
	private List<ProductMatchSchemeItem> ProductMatchSchemeList;//搭配方案列表
	private String colorName;//颜色
	private String craftName;//工艺
	private String materialName;//材料
	private String applicationName;//应用
	private String StyleName;//风格
	

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductComponent() {
		return productComponent;
	}
	public void setProductComponent(String productComponent) {
		this.productComponent = productComponent;
	}
	public String getProductUnitWeight() {
		return productUnitWeight;
	}
	public void setProductUnitWeight(String productUnitWeight) {
		this.productUnitWeight = productUnitWeight;
	}
	public String getProductNarrowWidth() {
		return productNarrowWidth;
	}
	public void setProductNarrowWidth(String productNarrowWidth) {
		this.productNarrowWidth = productNarrowWidth;
	}
	public String getProductWideWidth() {
		return productWideWidth;
	}
	public void setProductWideWidth(String productWideWidth) {
		this.productWideWidth = productWideWidth;
	}
	public String getPatternHorizontalSize() {
		return patternHorizontalSize;
	}
	public void setPatternHorizontalSize(String patternHorizontalSize) {
		this.patternHorizontalSize = patternHorizontalSize;
	}
	public String getPatternVerticalSize() {
		return patternVerticalSize;
	}
	public void setPatternVerticalSize(String patternVerticalSize) {
		this.patternVerticalSize = patternVerticalSize;
	}
	public String getProductSourceArea() {
		return productSourceArea;
	}
	public void setProductSourceArea(String productSourceArea) {
		this.productSourceArea = productSourceArea;
	}
	public ArrayList<String> getWashMethodIconList() {
		return washMethodIconList;
	}
	public void setWashMethodIconList(ArrayList<String> washMethodIconList) {
		this.washMethodIconList = washMethodIconList;
	}
	public String getQualityControlReport() {
		return qualityControlReport;
	}
	public void setQualityControlReport(String qualityControlReport) {
		this.qualityControlReport = qualityControlReport;
	}

	public List<ProductMatchSchemeItem> getProductMatchSchemeList() {
		return ProductMatchSchemeList;
	}
	public void setProductMatchSchemeList(List<ProductMatchSchemeItem> productMatchSchemeList) {
		ProductMatchSchemeList = productMatchSchemeList;
	}

	public String getCraftName() {
		return craftName;
	}
	public void setCraftName(String craftName) {
		this.craftName = craftName;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getPatternPicture1() {
		return patternPicture1;
	}
	public void setPatternPicture1(String patternPicture1) {
		this.patternPicture1 = patternPicture1;
	}
	public String getPatternPicture2() {
		return patternPicture2;
	}
	public void setPatternPicture2(String patternPicture2) {
		this.patternPicture2 = patternPicture2;
	}
	public String getQualityPicture1() {
		return qualityPicture1;
	}
	public void setQualityPicture1(String qualityPicture1) {
		this.qualityPicture1 = qualityPicture1;
	}
	public String getQualityPicture2() {
		return qualityPicture2;
	}
	public void setQualityPicture2(String qualityPicture2) {
		this.qualityPicture2 = qualityPicture2;
	}
	public String getQualityPicture3() {
		return qualityPicture3;
	}
	public void setQualityPicture3(String qualityPicture3) {
		this.qualityPicture3 = qualityPicture3;
	}
	public String getQualityPicture4() {
		return qualityPicture4;
	}
	public void setQualityPicture4(String qualityPicture4) {
		this.qualityPicture4 = qualityPicture4;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	public String getStyleName() {
		return StyleName;
	}
	public void setStyleName(String styleName) {
		StyleName = styleName;
	}
	public Double getProductNarrowPrice() {
		return productNarrowPrice;
	}
	public void setProductNarrowPrice(Double productNarrowPrice) {
		this.productNarrowPrice = productNarrowPrice;
	}
	public Double getProductWidePrice() {
		return productWidePrice;
	}
	public void setProductWidePrice(Double productWidePrice) {
		this.productWidePrice = productWidePrice;
	}

}
