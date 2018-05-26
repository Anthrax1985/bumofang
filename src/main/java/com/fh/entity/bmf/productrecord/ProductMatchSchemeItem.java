package com.fh.entity.bmf.productrecord;

import com.fh.entity.BaseEntity;
import java.sql.Timestamp;
import java.util.ArrayList;

/** 
 * 类名称：ProductRecordMatchScheme
 * 创建人：tyj
 * 创建时间：2017-07-24
 */

public class ProductMatchSchemeItem extends BaseEntity {	
	private Long matchProductId; // 产品id
	private String matchProductName; // 搭配方案产品名称
	private String matchProductIcon; // 搭配方案图片
	private Long productUploadTime; //布料上传时间
	private Long d3dUploadTime; //3D渲染图上传时间
	private ArrayList<String> matchProductLetterList; // 搭配方案对应的应用
	public Long getMatchProductId() {
		return matchProductId;
	}
	public void setMatchProductId(Long matchProductId) {
		this.matchProductId = matchProductId;
	}
	public String getMatchProductName() {
		return matchProductName;
	}
	public void setMatchProductName(String matchProductName) {
		this.matchProductName = matchProductName;
	}
	public String getMatchProductIcon() {
		return matchProductIcon;
	}
	public void setMatchProductIcon(String matchProductIcon) {
		this.matchProductIcon = matchProductIcon;
	}
	public Long getProductUploadTime() {return productUploadTime;}
	public void setProductUploadTime(Long productUploadTime) {this.productUploadTime = productUploadTime;}
	public Long getD3dUploadTime() {return d3dUploadTime;}
	public void setD3dUploadTime(Long d3dUploadTime) {this.d3dUploadTime = d3dUploadTime;}
	public ArrayList<String> getMatchProductLetterList() {
		return matchProductLetterList;
	}
	public void setMatchProductLetterList(ArrayList<String> matchProductLetterList) {
		this.matchProductLetterList = matchProductLetterList;
	}
}
