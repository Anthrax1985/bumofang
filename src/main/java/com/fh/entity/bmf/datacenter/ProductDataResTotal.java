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

public class ProductDataResTotal extends BaseEntity {
	
	private Long totalBrowseAmount;
	public Long totalOrderCnt;
	public Long totalPayCnt;
	public Long totalMeter;
	public Double totalPrice;
	public Double totalRealPay;

	private ArrayList<Long> yearList;
	private List<ProductDataBrowseAmountList> browseAmountList;
	private ProductDataBrowseMemberType browseMemberType;
	private List<ProductDataBrowseAndTypeItem> browseColorType;

	public List<ProductDataBrowseAmountList> getBrowseAmountList() {
		return browseAmountList;
	}

	public void setBrowseAmountList(List<ProductDataBrowseAmountList> browseAmountList) {
		this.browseAmountList = browseAmountList;
	}

	public Long getTotalBrowseAmount() {
		return totalBrowseAmount;
	}

	public void setTotalBrowseAmount(Long totalBrowseAmount) {
		this.totalBrowseAmount = totalBrowseAmount;
	}

	public ArrayList<Long> getYearList() {
		return yearList;
	}

	public void setYearList(ArrayList<Long> yearList) {
		this.yearList = yearList;
	}

	public ProductDataBrowseMemberType getBrowseMemberType() {
		return browseMemberType;
	}

	public void setBrowseMemberType(ProductDataBrowseMemberType browseMemberType) {
		this.browseMemberType = browseMemberType;
	}

	public List<ProductDataBrowseAndTypeItem> getBrowseColorType() {
		return browseColorType;
	}

	public void setBrowseColorType(List<ProductDataBrowseAndTypeItem> browseColorType) {
		this.browseColorType = browseColorType;
	}


	public Long getTotalPayCnt() {
		return totalPayCnt;
	}

	public void setTotalPayCnt(Long totalPayCnt) {
		this.totalPayCnt = totalPayCnt;
	}

	public Long getTotalMeter() {
		return totalMeter;
	}

	public void setTotalMeter(Long totalMeter) {
		this.totalMeter = totalMeter;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getTotalOrderCnt() {
		return totalOrderCnt;
	}

	public void setTotalOrderCnt(Long totalOrderCnt) {
		this.totalOrderCnt = totalOrderCnt;
	}

	public Double getTotalRealPay() {
		return totalRealPay;
	}

	public void setTotalRealPay(Double totalRealPay) {
		this.totalRealPay = totalRealPay;
	}
}
