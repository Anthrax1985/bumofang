package com.fh.controller.bmf.app;

import com.fh.common.model.ResponseMessageEnum;
import com.fh.controller.base.AppBaseController;
import com.fh.entity.bmf.app.ProductResItem;
import com.fh.util.Const;
import com.fh.util.PageData;
import com.fh.util.redis.RedisUtil;
import net.sf.json.JSONObject;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import com.fh.service.bmf.scene.SceneService;

import javax.annotation.Resource;
import java.io.Serializable;
import java.security.Timestamp;
import java.util.*;

@Controller(value = "AppSceneController")
@RequestMapping("/app/scene")
public class SceneController extends AppBaseController {
    @Resource(name = "sceneService")
    private SceneService sceneService;

    /*所有产品类表接口接口*/
    @RequestMapping(value = { "/all" }, method = {RequestMethod.GET }, produces = {JSON_UTF8})
    @ResponseBody
    public String sceneList() throws Exception {
        List<PageData> sceneList = sceneService.list_scene(null);
        List<PageData> resList = new ArrayList<PageData>();

        PageData scene_item = null;
        String scene_name = "";
        for(PageData item : sceneList){
            String name = item.getString("name");
            String coordinateFile = item.getString("coordinateFile");
            String maskPicture = item.getString("maskPicture");
            Long sceneUploadTime = item.getLong("sceneUploadTime");

            if(!name.equals(scene_name)){
                scene_item = new PageData();
                scene_item.put("name", name);
                scene_item.put("coordinateFile", coordinateFile);
                scene_item.put("sceneUploadTime", sceneUploadTime);
                List<String> maskList = new ArrayList<String>();
                maskList.add(maskPicture);

                scene_item.put("maskPicture", maskList);
                resList.add(scene_item);
            }else{
                List<String> maskList = (List<String>)scene_item.get("maskPicture");
                maskList.add(maskPicture);
            }

            scene_name = name;
        }
        return ResponseMessageEnum.SUCCESS.appendObject(resList);
    }

    @RequestMapping(value = { "/engine/sendMsgByPad" }, method = { RequestMethod.POST }, produces = { JSON_UTF8 })
    @ResponseBody
    public String sendMsgByPad(@RequestBody Map<String, String> map) {
        String msg = map.get("msg");
        String idfa = map.get("idfa");
        Date dt = new Date();
        long timestamp = dt.getTime();

        for(int idx=1; idx<=100; idx++){
            String key = "sendMsgByPad:idx_" + String.valueOf(idx);
            if(!RedisUtil.exists(key)){
                PageData pd = new PageData();
                pd.put("idfa", idfa);
                pd.put("timestamp", timestamp);
                pd.put("msg", msg);

                JSONObject jsonNode = JSONObject.fromObject(pd);

                RedisUtil.set(key, jsonNode.toString(), 1000*60*10);
                break;
            }
        }
        return ResponseMessageEnum.SUCCESS.appendEmptyData();
    }


    @RequestMapping(value = { "/engine/recvMsg" }, method = { RequestMethod.GET }, produces = { JSON_UTF8 })
    @ResponseBody
    public String recvMsg() {
        Object result = null;
        String pattern = "sendMsgByPad:idx_";
        for(int idx=1; idx<=100; idx++){
            String key = "sendMsgByPad:idx_" + String.valueOf(idx);
            if(RedisUtil.exists(key)){
                result = RedisUtil.get(key);
                result = result.toString().replace("\\", "");
                RedisUtil.remove(key);
                break;
            }
        }
        return ResponseMessageEnum.SUCCESS.appendObject(result);
    }

}
