package com.fh.entity.bmf.datacenter;

import com.fh.entity.BaseEntity;
import java.sql.Timestamp;
import java.util.ArrayList;

/** 
 * 类名称：ProductDataStatistics
 * 创建人：tyj
 * 创建时间：2017-08-15
 */

public class ProductDataBrowseAmountList extends BaseEntity {
	
	private String timePeriod;
	private Long   browseAmount;
	private Long   orderCnt;
	private Long   payCnt;
	private Double totalPrice;
	private Double realPay;
	private Long   	orderMeter;
	private String timeFrom;
	private String 	timeEnd;

	public String getTimePeriod() {
		return timePeriod;
	}
	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}
	public Long getBrowseAmount() {
		return browseAmount;
	}
	public void setBrowseAmount(Long browseAmount) {
		this.browseAmount = browseAmount;
	}

	public String getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public Long getOrderCnt() {
		return orderCnt;
	}

	public void setOrderCnt(Long orderCnt) {
		this.orderCnt = orderCnt;
	}

	public Long getPayCnt() {
		return payCnt;
	}

	public void setPayCnt(Long payCnt) {
		this.payCnt = payCnt;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getOrderMeter() {
		return orderMeter;
	}

	public void setOrderMeter(Long orderMeter) {
		this.orderMeter = orderMeter;
	}

	public Double getRealPay() {
		return realPay;
	}

	public void setRealPay(Double realPay) {
		this.realPay = realPay;
	}
}
