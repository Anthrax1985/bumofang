package com.fh.controller.bmf.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.common.model.ResponseMessageEnum;
import com.fh.controller.base.BaseWebController;
import com.fh.entity.Page;
import com.fh.entity.bmf.member.MemberCart;
import com.fh.entity.bmf.product.Product;
import com.fh.entity.bmf.productparam.ProductParamApplication;
import com.fh.entity.bmf.productparam.ProductParamColor;
import com.fh.entity.bmf.productparam.ProductParamCraft;
import com.fh.entity.bmf.productparam.ProductParamMaterial;
import com.fh.entity.bmf.productparam.ProductParamStyle;
import com.fh.service.BaseService;
import com.fh.service.bmf.chinadivision.ChinaDivisionService;
import com.fh.service.bmf.member.MemberCartService;
import com.fh.service.bmf.member.MemberProfessionSubService;
import com.fh.service.bmf.member.MemberProfessionSupService;
import com.fh.service.bmf.member.MemberService;
import com.fh.service.bmf.product.ProductService;
import com.fh.service.bmf.productparam.ProductParamApplicationService;
import com.fh.service.bmf.productparam.ProductParamColorService;
import com.fh.service.bmf.productparam.ProductParamCraftService;
import com.fh.service.bmf.productparam.ProductParamMaterialService;
import com.fh.service.bmf.productparam.ProductParamStyleService;
import com.fh.service.bmf.syslog.SysLogBizService;
import com.fh.service.system.user.UserService;
import com.fh.util.Const;
import com.fh.util.Jurisdiction;
import com.fh.util.PageData;
import com.fh.util.Tools;

/**
 * 类名称：MemberCartController
 * 创建人：SX
 * 创建时间：2017-11-30
 */
@Controller
@RequestMapping(value="/membercart")
public class MemberCartController extends BaseWebController {

    String menuUrl = "membercart/list.do"; //菜单地址(权限用)
    @Resource(name="memberService")
    private MemberService memberService;

	@Resource(name = "memberCartService")
	private MemberCartService memberCartService;

    @Resource(name="chinaDivisionService")
    private ChinaDivisionService chinaDivisionService;

    @Resource(name="memberProfessionSupService")
    private MemberProfessionSupService memberProfessionSupService;

    @Resource(name="memberProfessionSubService")
    private MemberProfessionSubService memberProfessionSubService;

    @Resource(name="userService")
    private UserService userService;

    @Resource(name="sysLogBizService")
    private SysLogBizService sysLogBizService;

    @Resource(name="productService")
    private ProductService productService;

    @Resource(name="productParamColorService")
    private ProductParamColorService productParamColorService;

    @Resource(name="productParamStyleService")
    private ProductParamStyleService productParamStyleService;

    @Resource(name="productParamCraftService")
    private ProductParamCraftService productParamCraftService;

    @Resource(name="productParamMaterialService")
    private ProductParamMaterialService productParamMaterialService;

    @Resource(name="productParamApplicationService")
    private ProductParamApplicationService productParamApplicationService;

    @Autowired
    protected HttpServletRequest request;

    @Override
    protected String getPackageName() {
        return "member";
    }

    @Override
    protected String getObjectName() {
        return "MemberCart";
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/list")
    public ModelAndView list(Page page) {
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "cha")) {
            return null;
        } // 校验权限
        logBefore(logger, "列表" + getObjectName());
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        try {
            String member_id = request.getParameter("member_id");
            List<PageData> cart_list = memberCartService.findByMemberId(Tools.getLong(member_id));

            List<String> in = new ArrayList<String>();
            Map<String, PageData> cart_map = new  HashMap<String, PageData>();
            for (PageData item : cart_list){
                String product_id = Tools.getString(item.get("product_id"));
                in.add(product_id);
                cart_map.put(product_id, item);
            }
            /*beforeList(page);*/
            //获取颜色信息
            List<ProductParamColor> colorList = productParamColorService.listAll();
            mv.addObject("colorList", colorList);
            //获取风格信息
            List<ProductParamStyle> styleList = productParamStyleService.listAll();
            mv.addObject("styleList", styleList);
            //获取工艺信息
            List<ProductParamCraft> craftList = productParamCraftService.listAll();
            mv.addObject("craftList", craftList);
            //获取材料信息
            List<ProductParamMaterial> materialList = productParamMaterialService.listAll();
            mv.addObject("materialList", materialList);
            //获取应用信息
            List<ProductParamApplication> applicationList = productParamApplicationService.listAll();
            mv.addObject("applicationList", applicationList);

			/*beforeList(page);*/
            List<Product> varList = null;

            if(in.size() > 0){
                varList = productService.listWhereIn(Tools.toArray(in)); // 列出列表
                mv.addObject("cart_map", cart_map);

                for (Product item : varList){
                    String id = String.valueOf(item.getId());
//                    PageData elem = cart_map.get(id);
                    PageData elem = cart_map.get(id);
                    Integer total_num1 = elem.getInteger("total_num");
                    System.out.println(total_num1);
                }
            }else{
                varList = new ArrayList<Product>();
            }

            //List<Product> varList = productService.list(page); // 列出列表
            listAfter(varList);
            mv.addObject("varList", varList);
            mv.addObject("member_id", member_id);
            mv.setViewName(getViewNameSuffix() + "_list");
            mv.addObject("pd", pd);
            mv.addObject("page", page);
            mv.addObject("ctrl", this);


            mv.addObject(Const.SESSION_QX, this.getHC()); // 按钮权限
            listChangeModel(mv);
        } catch (Exception e) {
            logger.warn(e.toString(), e);
        }
        return mv;
    }


    /**
     * rest新增购物车
     */
    @RequestMapping(value="/rest/save" , method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public String restSave(@RequestBody Map<String,String> map) throws Exception{
        logBefore(logger, "REST新增购物车");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "add"))
        {return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString();} //校验权限
        PageData pd = new PageData();
        pd.putAll(map);
        pd.put("CREATE_TIME", Tools.date2Str(new Date()));	//时间
        pd.put("DELETED", 0);
        pd.put("IS_BUY", 0);
        memberCartService.save(pd);

        return ResponseMessageEnum.SUCCESS.toString();
    }

    /**
     * rest新增购物车
     */
    @RequestMapping(value="/rest/addList" , method=RequestMethod.POST  ,
            produces="application/json;charset=UTF-8")
    @ResponseBody
    public String addList(@RequestBody Map<String, Object> map) {
        logBefore(logger, "REST新增购物车");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "add"))
        {return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString();} //校验权限

        List product_list =  (List)map.get("product_list");
        Long member_id = Tools.getLong(map.get("member_id"));
        Long total_num =  Tools.getLong(map.get("total_num"));
        Long option_id =  Tools.getLong(map.get("option_id"));

        try{
            for (Object v : product_list) {
                Long product_id = Tools.getLong(v);

                PageData pd = new PageData();
                pd.put("MEMBER_ID", member_id);	                    //用户ID
                pd.put("PRODUCT_ID", product_id);                      //商品ID
                pd.put("TOTAL_NUM", total_num);
                pd.put("PRICE", 0);
                pd.put("DELETED", 0);
                pd.put("OPTION_ID", option_id);
                pd.put("CREATE_TIME", Tools.date2Str(new Date()));	//时间
                pd.put("SELECTED", 0);
                pd.put("IS_BUY", 0);
                memberCartService.save(pd);
            }
        } catch(Exception e){
            logger.error(e.toString(), e);
            return e.toString();
        }

        return ResponseMessageEnum.SUCCESS.toString();
    }


    @RequestMapping(value="/rest/list", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public List<PageData> restList(Page page){
        logBefore(logger, "列表Member");
        //if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
        List<PageData>	varList = new ArrayList<PageData>();
//        PageData pd = new PageData();
//        try{
//            //一级类型列表，用于下拉搜索
//            List<MemberProfessionSup> professionSupList = memberProfessionSupService.listAll();
//            //mv.addObject("professionSupList", professionSupList);
//            //省份列表，用于下拉搜索
//            ChinaDivision reqInfo = new ChinaDivision();
//            reqInfo.setDivisionLevel(new Long(1));
//            List<ChinaDivision> provinceList = chinaDivisionService.findByCondition(reqInfo);
//            //mv.addObject("provinceList", provinceList);
//            pd = this.getPageData();
//            page.setPd(pd);
//            varList = memberService.list(page);	//列出Member列表
//
//        } catch(Exception e){
//            logger.error(e.toString(), e);
//        }
        return varList;
    }


    /**
     * rest新增购物车
     */
    @RequestMapping(value="/rest/deleteMemberCart" , method=RequestMethod.POST  ,
            produces="application/json;charset=UTF-8")
    @ResponseBody
    public String deleteMemberCart(@RequestBody Map<String,String> map) throws Exception{
        logBefore(logger, "REST新增购物车");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "add"))
        {return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString();} //校验权限

        Long member_id = Tools.getLong(map.get("member_id"));
        Long product_id =  Tools.getLong(map.get("product_id"));


        PageData pd = new PageData();
        pd.putAll(map);
        pd.put("MEMBER_ID", member_id);	//时间
        pd.put("PRODUCT_ID", product_id);
        memberCartService.deleteByParam(pd);

        return ResponseMessageEnum.SUCCESS.toString();
    }


    @Override
    protected BaseService<MemberCart> getBaseService() {
        return memberCartService;
    }

}
