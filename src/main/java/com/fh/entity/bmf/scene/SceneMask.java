package com.fh.entity.bmf.scene;

import com.fh.entity.BaseEntity;

/**
 * 类名称：Scene
 * 创建人：SX
 * 创建时间：2017-11-30
 */

public class SceneMask extends BaseEntity
{
    private Long id;
    private Long sceneId;
    private String sceneName; // mask图片名
    private String maskName; // mask图片名

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSceneId() {
        return sceneId;
    }

    public void setSceneId(Long sceneId) {
        this.sceneId = sceneId;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public String getMaskName() {
        return maskName;
    }

    public void setMaskName(String maskName) {
        this.maskName = maskName;
    }
}
