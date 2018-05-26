package com.fh.controller.bmf.app;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.common.model.ResponseMessageEnum;
import com.fh.controller.base.AppBaseController;
import com.fh.entity.bmf.product.Product;
import com.fh.service.bmf.member.MemberCartService;
import com.fh.service.bmf.product.ProductService;
import com.fh.service.bmf.productparam.ProductParamCraftService;
import com.fh.service.bmf.productparam.ProductParamMaterialService;
import com.fh.service.bmf.productrecord.ProductRecordApplicationService;
import com.fh.service.bmf.productrecord.ProductRecordColorService;
import com.fh.service.bmf.productrecord.ProductRecordStyleService;
import com.fh.service.system.debug.DebugService;
import com.fh.util.PageData;
import com.fh.util.Tools;



/**
 * 购物车
 */

@Controller(value = "AppMemberCartController")
@RequestMapping("/app/membercart")
public class MemberCartController extends AppBaseController {

	@Resource(name = "debugService")
	private DebugService debugService;
	
	@Resource(name = "memberCartService")
	private MemberCartService memberCartService;
	
	@Resource(name = "productService")
	private ProductService productService;
	
	@Resource(name="productRecordColorService")
	private ProductRecordColorService productRecordColorService;
	
	@Resource(name = "productRecordStyleService")
	private ProductRecordStyleService productRecordStyleService;
	
	@Resource(name = "productRecordApplicationService")
	private ProductRecordApplicationService productRecordApplicationService;
	

	/**
	 * 新增购物车
	 * @param req
	 * @param rep
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/createMemberCart", method = RequestMethod.POST, produces = JSON_UTF8)
	@ResponseBody
	public String createMemberCart(@RequestBody Map<String, String> map)throws Exception {
		// 保存数据到数据库
		Object result = getUser();
		if (result instanceof ResponseMessageEnum) {
			return result.toString();
		}
		PageData userInfo = (PageData) result;
		String product_id  = map.get("PRODUCT_ID");
		String use_for = map.get("use_for");
		userInfo.put("product_id", product_id);
		//判断数据库是否有该产品的购物车
		List<PageData> list = memberCartService.isProductId(userInfo);
		if (null != list && list.size()>0) {
			return ResponseMessageEnum.ERROR_CART_EXIST.toString();
		}
		
		//根据产品id查询价格
		Product product = productService.findById(Long.parseLong(product_id));
		PageData pd = new PageData();
		pd.put("memter_id", userInfo.get("ID"));
		pd.put("product_id", product_id);
		pd.put("use_for", use_for);
		pd.put("total_num", 1);
		pd.put("price", product.getProductNarrowPrice());
		pd.put("option_id", 0);
		pd.put("selected", 0);
		pd.put("meter", 50);
		pd.put("create_time", Tools.date2Str(new Date())); // 时间
		pd.put("deleted", 0);
		pd.put("is_bug", 0);
		int size = (int) memberCartService.save1(pd);
		if (size>0) {
			return ResponseMessageEnum.ADD_CART_SUCCESS.toString();
			//return ResponseMessageEnum.SUCCESS.toString();
		}else {
			return ResponseMessageEnum.FAIL.toString();
		}
	}
	
	/**
	 * APP购物车列表  入参：token,page(从0开始),
	 * @param req
	 * @param rep
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/listMemberCart", method = RequestMethod.POST, produces = JSON_UTF8)
	@ResponseBody
	public String listMemberCart(@RequestBody Map<String, Object> map) throws Exception {
		Object result = getUser();
		if (result instanceof ResponseMessageEnum) {
			return result.toString();
		}
		PageData pd = (PageData) result;
		int startIndex = (int) map.get("startIndex");
		int count = (int) map.get("count");
		pd.put("startIndex", startIndex);
		pd.put("count", count);

		memberCartService.deleteOldData();
		
		List<PageData> cartDatas = memberCartService.allByMemberId(pd);
		if (null != cartDatas && cartDatas.size()>0) {
			for (int i = 0; i < cartDatas.size(); i++) {
				PageData item = new PageData();
				item.put("product_id", cartDatas.get(i).get("product_id"));
				
				//获取样式
				List<PageData> colors = productRecordColorService.listColorWithProduct(item);
				cartDatas.get(i).put("colors", colors);
				
				//获取样式
				List<PageData> styles = productRecordStyleService.findByProductId(item);
				cartDatas.get(i).put("styles", styles);
				
				//获取应用
				List<PageData> applications = productRecordApplicationService.findByProductId(item);
				cartDatas.get(i).put("applications", applications);	
				
			}
		}
		
		return ResponseMessageEnum.SUCCESS.appendPageDataListToString(cartDatas);
	}
	
	/**
	 * 修改购物车
	 * @param req
	 * @param rep
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateMemberCart", method = RequestMethod.POST, produces = JSON_UTF8)
	@ResponseBody
	public String updateMemberCart(@RequestBody Map<String, String> map) throws Exception{
		Object result = getUser();
		if (result instanceof ResponseMessageEnum) {
			return result.toString();
		}
		String option_id = map.get("option_id");
		PageData pd = new PageData();
		pd.put("id", map.get("cart_id"));
		pd.put("meter", map.get("meter"));
		pd.put("option_id", option_id );
		pd.put("use_for", map.get("use_for") );
		
		PageData product = memberCartService.findByIdWithProduct(pd);
		pd.put("price", product.get("product_narrow_price") );
		
		int size = (int)memberCartService.updateById(pd);
		
		if (size>0) {
			return ResponseMessageEnum.SUCCESS.toString();
		}else {
			return ResponseMessageEnum.FAIL.toString();
		}
	}
	
	/**
	 * 删除购物车
	 * @param req
	 * @param rep
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteMemberCart", method = RequestMethod.POST, produces = JSON_UTF8)
	@ResponseBody
	public String deleteMemberCart(@RequestBody Map<String, Object> map) throws Exception{
		Object result = getUser();
		if (result instanceof ResponseMessageEnum) {
			return result.toString();
		}
		PageData pd = new PageData();
		pd.put("ID", map.get("id"));
		int size = (int) memberCartService.updateByDelete(pd);
		if (size>0) {
			return ResponseMessageEnum.SUCCESS.toString();
		}else {
			return ResponseMessageEnum.FAIL.toString();
		}
	}
	
	 /**
	  * 批量删除购物车
	  * @param map
	  * @return
	  * @throws Exception
	  */
	@RequestMapping(value = "/deleteAllMemberCart", method = RequestMethod.POST, produces = JSON_UTF8)
	@ResponseBody
	public String deleteAllMemberCart(@RequestBody Map<String, Object> map) throws Exception{
		Object result = getUser();
		if (result instanceof ResponseMessageEnum) {
			return result.toString();
		}
		List<Integer> ids = (List<Integer>) map.get("id");
		
		for (int idx=0; idx<ids.size(); idx++) {
			PageData pd = new PageData();
			pd.put("ID", ids.get(idx));
			int size = (int) memberCartService.updateByDelete(pd);
			if (size==0) {
				return ResponseMessageEnum.FAIL.toString();
			}
		}
		return ResponseMessageEnum.SUCCESS.toString();
	}
}

