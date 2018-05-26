package com.fh.controller.bmf.scene;

import com.fh.common.model.ResponseMessageEnum;
import com.fh.controller.base.BaseWebController;
import com.fh.entity.Page;
import com.fh.entity.bmf.member.MemberCart;
import com.fh.entity.bmf.product.Product;
import com.fh.entity.bmf.productparam.*;
import com.fh.entity.bmf.scene.Scene;
import com.fh.service.BaseService;
import com.fh.service.bmf.chinadivision.ChinaDivisionService;
import com.fh.service.bmf.member.MemberCartService;
import com.fh.service.bmf.member.MemberProfessionSubService;
import com.fh.service.bmf.member.MemberProfessionSupService;
import com.fh.service.bmf.member.MemberService;
import com.fh.service.bmf.product.ProductService;
import com.fh.service.bmf.productparam.*;
import com.fh.service.bmf.scene.SceneService;
import com.fh.service.bmf.syslog.SysLogBizService;
import com.fh.service.system.user.UserService;
import com.fh.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 类名称：SceneController
 * 创建人：SX
 * 创建时间：2017-11-30
 */
@Controller
@RequestMapping(value="/scene")
public class SceneController extends BaseWebController {

    String menuUrl = "scene/list.do"; //菜单地址(权限用)

    @Resource(name="sceneService")
    private SceneService sceneService;

    @Resource(name="productService")
    private ProductService productService;

    @Autowired
    protected HttpServletRequest request;

    @Override
    protected String getPackageName() {
        return "scene";
    }

    @Override
    protected String getObjectName() {
        return "scene";
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/list_scene")
    public ModelAndView list_scene(Page page) {
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "cha")) {
            return null;
        } // 校验权限
        logBefore(logger, "列表" + getObjectName());
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        try {
           List<PageData> varList = sceneService.list_scene(page); // 列出列表
           listAfter(varList);

           Long timestamp = System.currentTimeMillis();
           QNUploadUtil qnUploader = new QNUploadUtil();
           String domainUrl = qnUploader.getImgSceneDomain();

           for(PageData row : varList){
               String name = row.getString("name");
               String url = domainUrl + name + ".jpg?tm=" + timestamp + "&imageMogr2/thumbnail/!400x";
               row.put("url", url);

               String mask = row.getString("mask");
               String mask_url = domainUrl + mask + ".png?tm=" + timestamp + "&imageMogr2/thumbnail/!400x";
               row.put("maskUrl", mask_url);
           }

            mv.addObject("varList", varList);
            mv.setViewName(getViewNameSuffix() + "_list");
            mv.addObject("pd", pd);
            mv.addObject("page", page);

            mv.addObject(Const.SESSION_QX, this.getHC()); // 按钮权限
            listChangeModel(mv);
        } catch (Exception e) {
            logger.warn(e.toString(), e);
        }
        return mv;
    }


    /**
     * 列表
     */
    @RequestMapping(value = "/list_3d")
    public ModelAndView list_3d(Page page) {
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "cha")) {
            return null;
        } // 校验权限
        logBefore(logger, "列表" + getObjectName());
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        try {
            pd = this.getPageData();
            if(pd.keySet().contains("query_key")){
                String query_key = pd.getString("query_key");
                query_key = query_key.trim();
                pd.put("query_key", query_key);
            }
            page.setPd(pd);
            //
            List<Product> varList = productService.list(page);
            listAfter(varList);
            mv.addObject("varList", varList);

            QNUploadUtil qnUploader = new QNUploadUtil();
            String domainUrl = qnUploader.getImgD3dDomain();

            List<String> in = new ArrayList<String>();
            for(Product item : varList){
                String product_name = item.getProductName();
                in.add(product_name);
            }

            List<PageData> list_3d = sceneService.listWhereIn(Tools.toArray(in)); // 列出列表
            mv.addObject("list_3d", list_3d);
            mv.addObject("domainUrl", domainUrl);

            List<PageData> sceneList = sceneService.list_scene(page);
            mv.addObject("sceneList", sceneList);
            mv.setViewName(getViewNameSuffix() + "_list_3d");
            mv.addObject("pd", pd);
            mv.addObject("page", page);

            mv.addObject(Const.SESSION_QX, this.getHC()); // 按钮权限
            listChangeModel(mv);
        } catch (Exception e) {
            logger.warn(e.toString(), e);
        }
        return mv;
    }



    @Override
    protected BaseService<Scene> getBaseService() {
        return sceneService;
    }

}
