package com.fh.entity.bmf.app;

import java.util.ArrayList;

public class ProductResItem {
	private Long selectEleId;//筛选请求具体类型的具体id
	private Long productId;//产品id
	private String qualityPicture;//app展示的质地图（800*800）
	private String productName;//产品名称
	private ArrayList<String> applicationLetterList;//产品应用列表
	private Integer delFlag; //删除标记(1黑名单0白名单)
	private Integer tempFlag; //临时标记
	private Long productUploadTime; //上传布料图片的时间戳
	private Long d3dUploadTime;
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getQualityPicture() {
		return qualityPicture;
	}
	public void setQualityPicture(String qualityPicture) {
		this.qualityPicture = qualityPicture;
	}
	public Long getSelectEleId() {
		return selectEleId;
	}
	public void setSelectEleId(Long selectEleId) {
		this.selectEleId = selectEleId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public ArrayList<String> getApplicationLetterList() {
		return applicationLetterList;
	}
	public void setApplicationLetterList(ArrayList<String> applicationLetterList) {
		this.applicationLetterList = applicationLetterList;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public Integer getTempFlag() {
		return tempFlag;
	}
	public void setTempFlag(Integer tempFlag) {
		this.tempFlag = tempFlag;
	}

	public Long getProductUploadTime() {
		return productUploadTime;
	}
	public void setProductUploadTime(Long productUploadTime) {
		this.productUploadTime = productUploadTime;
	}
	public Long getD3dUploadTime() {return d3dUploadTime;}
	public void setD3dUploadTime(Long d3dUploadTime) {this.d3dUploadTime = d3dUploadTime;}
}
