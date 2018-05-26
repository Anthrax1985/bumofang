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

public class DataReqItem4Browse extends BaseEntity {
	
	private Long memberId; //用户id
	private Long productId;//产品id
	private Integer year;//年份
	private String timeperiod;// 月度m/季度s
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getTimeperiod() {
		return timeperiod;
	}
	public void setTimeperiod(String timeperiod) {
		this.timeperiod = timeperiod;
	}	
}
