package com.fh.entity.bmf.member;

import com.fh.entity.BaseEntity;
import com.fh.util.PageData;

import java.sql.Timestamp;
import java.util.List;

/** 
 * 类名称：Member3DMatchMethod
 * 创建人：tyj
 * 创建时间：2017-08-04
 */

public class Member3DMatchMethod extends BaseEntity {
	
	private Long id;
	private Long memberId; // 用户id
	private Long productId; // 产品id
	private String productIdStr; // 产品ID 
	private String methodName; // 方案名称
	private String sceneNum; // 场景编号
	private String layerChannel; // 图层通道
	private List<PageData> productList; // 图层列表

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
	public String getMethodName() {
		return this.methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getSceneNum() {
		return this.sceneNum;
	}

	public void setSceneNum(String sceneNum) {
		this.sceneNum = sceneNum;
	}
	public String getLayerChannel() {
		return this.layerChannel;
	}

	public void setLayerChannel(String layerChannel) {
		this.layerChannel = layerChannel;
	}

	public String getProductIdStr() {
		return productIdStr;
	}

	public void setProductIdStr(String productIdStr) {
		this.productIdStr = productIdStr;
	}

	public List<PageData> getProductList() {
		return productList;
	}

	public void setProductList(List<PageData> productList) {
		this.productList = productList;
	}
}
