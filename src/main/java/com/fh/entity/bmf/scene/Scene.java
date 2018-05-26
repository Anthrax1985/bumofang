package com.fh.entity.bmf.scene;

import com.fh.entity.BaseEntity;

/**
 * 类名称：Scene
 * 创建人：SX
 * 创建时间：2017-11-30
 */

public class Scene extends BaseEntity
{
    private Long id;
    private String name; // 用户id
    private Integer createTime; //创建时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
