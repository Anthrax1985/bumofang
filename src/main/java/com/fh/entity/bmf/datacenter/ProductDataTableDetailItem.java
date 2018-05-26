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

public class ProductDataTableDetailItem extends BaseEntity {
	
	private Long amount;
	private String period;	
	private ArrayList<String> reqPeriod;
	private Long   orderCnt;
	private Long   payCnt;
	private Double totalPrice;
	private Double realPay;
	private Long   	meter;

	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public ArrayList<String> getReqPeriod() {
		return reqPeriod;
	}
	public void setReqPeriod(ArrayList<String> reqPeriod) {
		this.reqPeriod = reqPeriod;
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

	public Double getRealPay() {
		return realPay;
	}

	public void setRealPay(Double realPay) {
		this.realPay = realPay;
	}



	public Long getMeter() {
		return meter;
	}

	public void setMeter(Long meter) {
		this.meter = meter;
	}
}
