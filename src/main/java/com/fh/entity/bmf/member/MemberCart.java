package com.fh.entity.bmf.member;

import com.fh.entity.BaseEntity;
import java.sql.Timestamp;

/**
 * 类名称：MemberCart
 * 创建人：SX
 * 创建时间：2017-11-30
 */

public class MemberCart extends BaseEntity
{
    private Long id;
    private Long memberId; // 用户id
    private Long productId; // 产品id
    private Integer meter;//米数
    private Integer totalNum;   //总量
    private Double price;       // 价格
    private Integer deleted;    //是否删除
    private Integer optionId;   //选项
    private Integer createTime; //创建时间
    private Integer selected;   //是否默认选中
    private Integer isBuy;       //是否已经购买

    
    public Integer getMeter() {
        return meter;
    }

    public void setMeter(Integer meter) {
        this.meter = meter;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }

    public Integer getIsBuy() {
        return isBuy;
    }

    public void setIsBuy(Integer isBuy) {
        this.isBuy = isBuy;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


}
