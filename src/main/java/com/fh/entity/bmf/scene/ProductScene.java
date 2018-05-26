package com.fh.entity.bmf.scene;

import com.fh.entity.BaseEntity;

/**
 * 类名称：ProductScene
 * 创建人：SX
 * 创建时间：2017-11-30
 */

public class ProductScene extends BaseEntity
{
    private Long id;
    private Long product_id; // 产品id
    private Long scene_id; // 场景id
    private String name; // 用户id
    private String product_name; // 产品name
    private String scene_name; // 场景name
    private Integer createTime; //创建时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Long getScene_id() {
        return scene_id;
    }

    public void setScene_id(Long scene_id) {
        this.scene_id = scene_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getScene_name() {
        return scene_name;
    }

    public void setScene_name(String scene_name) {
        this.scene_name = scene_name;
    }

}
