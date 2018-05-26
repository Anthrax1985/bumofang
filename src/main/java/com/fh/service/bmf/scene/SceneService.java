package com.fh.service.bmf.scene;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.bmf.member.MemberCart;
import com.fh.entity.bmf.scene.ProductScene;
import com.fh.entity.bmf.scene.Scene;
import com.fh.entity.bmf.scene.SceneMask;
import com.fh.service.BaseService;
import com.fh.util.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 类名称：SceneService
 * 创建人：SX
 * 创建时间：2017-11-30
 */

@Service("sceneService")
public class SceneService extends BaseService<Scene>{
    @Resource(name = "daoSupport")
    private DaoSupport dao;

    /*
	* 新增
	*/
    public void saveScene(PageData pd)throws Exception{
        String name = pd.getString("NAME");
        String mask_name = pd.getString("MASK_NAME");
        Scene sceneObj = (Scene)dao.findForObject("SceneMapper.findBySceneName", name);
        if(sceneObj == null){
            dao.save("SceneMapper.saveScene", pd);
        }else{
            dao.update("SceneMapper.updateScene", pd);
        }

        sceneObj = (Scene)dao.findForObject("SceneMapper.findBySceneName", name);
        pd.put("SCENE_ID", sceneObj.getId());

        SceneMask maskObj = (SceneMask)dao.findForObject("SceneMapper.findBySceneMaskName", mask_name);
        if(maskObj == null){
            dao.save("SceneMapper.saveSceneMask", pd);
        }
    }

    public void saveProductScene(PageData pd)throws Exception{
        String scene_name = pd.getString("SCENE_NAME");
        String mask_name = pd.getString("MASK_NAME");
        String product_name = pd.getString("PRODUCT_NAME");

        PageData find = (PageData)dao.findForObject("SceneMapper.findExist", pd);
        if(find == null){
            dao.save("SceneMapper.saveProductScene", pd);
        }
    }

    public void updateProductUploadTime(String product_name)throws Exception{
        dao.update("SceneMapper.updateProductUploadTime", product_name);
    }

    public void updateD3dUploadTime(String product_name)throws Exception{
        dao.update("SceneMapper.updateD3dUploadTime", product_name);
    }

    public void updateSceneUploadTime(String scene_name)throws Exception{
        dao.update("SceneMapper.updateSceneUploadTime", scene_name);
    }

    public Scene findBySceneName(String scene_name)throws Exception{
        Scene sceneObj = (Scene)dao.findForObject("SceneMapper.findBySceneName", scene_name);
        return sceneObj;
    }

    public SceneMask findBySceneMaskName(String mask_name)throws Exception{
        SceneMask maskObj = (SceneMask)dao.findForObject("SceneMapper.findBySceneMaskName", mask_name);
        return maskObj;
    }

    public List<PageData> list_scene(Page page)throws Exception{
        return (List<PageData>)dao.findForList("SceneMapper.list_scene", page);
    }

    public List<PageData> listWhereIn(String[] in)throws Exception{
        return (List<PageData>)dao.findForList("SceneMapper.listWhereIn", in);
    }
    protected String getNamespace() {
        return "SceneService";
    }
}
