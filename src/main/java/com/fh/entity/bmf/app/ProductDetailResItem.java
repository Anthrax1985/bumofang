package com.fh.entity.bmf.app;

import java.util.ArrayList;
import java.util.List;

import com.fh.entity.bmf.productrecord.ProductMatchSchemeItem;

/**
 * @author Administrator
 *
 */
public class ProductDetailResItem {

	private Long id;
	private String productName; // 品名
	private String productComponent; // 成份	
	private String productUnitWeight; // 每平方克重(不带单位)
	private String productNarrowWidth; // 幅宽单幅
	private String productWideWidth; // 幅宽宽幅
	private String patternHorizontalSize; // 花型尺寸-水平
	private String patternVerticalSize; // 花型尺寸-垂直
	private ArrayList<String> applicationNameList;//应用列表
	private String productSourceArea; // 原产地
	private ArrayList<String> washMethodIconList;//水洗标志列表
	private String qualitypicture; // 质地图400:200
	private String qualityControlReport; // 质检报告
	private ArrayList<String> qualityPictureList;//质地图列表
	private List<ProductMatchSchemeItem> ProductMatchSchemeList;//搭配方案列表
	private List<ProductResItem> sameDesignedProList;//相同花型不同颜色的产品列表
	private Integer favorStatus; // 质收藏状态(1收藏，0未收藏);
	private Double productNarrowPrice;//单幅价格
	private Double productWidePrice;//宽幅价格
	private Integer productUploadTime;//布料上传时间戳
	private Integer d3dUploadTime;//3d渲染图上传时间戳

	private  ArrayList<String> styleLetterList;//风格字母缩写列表
	
	private  ArrayList<String> applicationLetterList;//应用字母缩写列表
	
	
	public ArrayList<String> getApplicationNameList() {
		return applicationNameList;
	}
	public void setApplicationNameList(ArrayList<String> applicationNameList) {
		this.applicationNameList = applicationNameList;
	}
	
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
	public ArrayList<String> getQualityPictureList() {
		return qualityPictureList;
	}
	public void setQualityPictureList(ArrayList<String> qualityPictureList) {
		this.qualityPictureList = qualityPictureList;
	}
	public List<ProductMatchSchemeItem> getProductMatchSchemeList() {
		return ProductMatchSchemeList;
	}
	public void setProductMatchSchemeList(List<ProductMatchSchemeItem> productMatchSchemeList) {
		ProductMatchSchemeList = productMatchSchemeList;
	}
	public List<ProductResItem> getSameDesignedProList() {
		return sameDesignedProList;
	}
	public void setSameDesignedProList(List<ProductResItem> sameDesignedProList) {
		this.sameDesignedProList = sameDesignedProList;
	}
	public String getQualitypicture() {
		return qualitypicture;
	}
	public void setQualitypicture(String qualitypicture) {
		this.qualitypicture = qualitypicture;
	}
	public Integer getFavorStatus() {
		return favorStatus;
	}
	public void setFavorStatus(Integer favorStatus) {
		this.favorStatus = favorStatus;
	}
	public ArrayList<String> getStyleLetterList() {
		return styleLetterList;
	}
	public void setStyleLetterList(ArrayList<String> styleLetterList) {
		this.styleLetterList = styleLetterList;
	}
	public ArrayList<String> getApplicationLetterList() {
		return applicationLetterList;
	}
	public void setApplicationLetterList(ArrayList<String> applicationLetterList) {
		this.applicationLetterList = applicationLetterList;
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

	public Integer getProductUploadTime() {return productUploadTime;}

	public void setProductUploadTime(Integer productUploadTime) {this.productUploadTime = productUploadTime;}

	public Integer getD3dUploadTime() {return d3dUploadTime;}

	public void setD3dUploadTime(Integer d3dUploadTime) {this.d3dUploadTime = d3dUploadTime;}
}
