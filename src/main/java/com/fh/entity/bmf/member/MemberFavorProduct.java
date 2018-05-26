package com.fh.entity.bmf.member;

import com.fh.entity.BaseEntity;
import java.sql.Timestamp;

/** 
 * 类名称：MemberFavorProduct
 * 创建人：tyj
 * 创建时间：2017-07-21
 */

public class MemberFavorProduct extends BaseEntity {
	
	private Long id;
	private Long memberId; // 汇演id
	private Long productId; // 产品id
	private String qualityPicture; // 产品图片400:400
	private String productUploadTime; //上传产品图片的时间
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMemberId() {
		return this.memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Long getProductId() {
		return this.productId;
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

	public String getProductUploadTime() {return productUploadTime;}

	public void setProductUploadTime(String productUploadTime) {this.productUploadTime = productUploadTime;}
}
