package com.fh.controller.bmf.app;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.common.model.ResponseMessageEnum;
import com.fh.controller.base.AppBaseController;
import com.fh.service.bmf.product.ProductService;
import com.fh.service.bmf.scene.ProductSceneExtService;
import com.fh.service.bmf.scene.ProductSceneService;
import com.fh.util.PageData;

/**
 * 产品场景图的controller
 * 
 * @author zxp
 *
 */
@Controller("ProductSceneController")
@RequestMapping(value = "/app/productscene")
public class ProductSceneController extends AppBaseController {

	@Resource(name = "productService")
	private ProductService productService;

	@Resource(name = "productSceneService")
	private ProductSceneService productSceneService;

	@Resource(name = "productSceneExtService")
	private ProductSceneExtService productSceneExtService;

	
	/**
	 * 获取产品图片和产品的场景图片
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/getOldPic" }, method = { RequestMethod.POST }, produces = { JSON_UTF8 })
	@ResponseBody
	public String getProductSceneOldPic(@RequestBody Map<String, Object> map)throws Exception {

		String name = (String) map.get("name");
		String create_time = (String) map.get("create_time");// 以2017-12-2900:00:00的形式传递
		String[] str = name.split("_");
		String product_name = str[str.length - 1];

		PageData pd = new PageData();
		pd.put("product_name", product_name);// 产品名称
		pd.put("create_time", create_time);// 创建时机

		//查询时间相同并大于传过来的产品名称的下一个产品
		List<PageData> nextOne = productSceneService.selectTopOneProductName1(pd);

		//如果上一种没有查到数据，说明这个相同的时间没有数据了，要查看下一个时间段是否有数据，根据时间和产品名来排序，并且去这个时间段排序后的第一个产品
		if (null == nextOne || nextOne.size() < 1) {
			pd.put("product_name", "");
			nextOne = productSceneService.selectTopOneProductName2(pd);
		}

		//获取渲染图（获取到产品数据后根据这个产品名称和时间，查询和这个产品名和时间相同的产品）
		List<PageData> scene_list = new ArrayList<PageData>();
		if (null != nextOne && nextOne.size()>0) {
			PageData sc = new PageData();
			sc.put("product_name", nextOne.get(0).get("product_name"));// 产品名称
			sc.put("create_time", String.valueOf(nextOne.get(0).get("create_time")));// 创建时机
			scene_list.addAll(productSceneService.selectListProductName(sc));
		}

		//获取产品图
		List<PageData> products = new ArrayList<PageData>();
		if (null != scene_list && scene_list.size() > 0) {
			String productId = String.valueOf(scene_list.get(0).get("product_id"));
			pd.put("product_id", productId);
			products.addAll(productService.selectById(pd));
		}

		//处理渲染图的时间（将时间改为String类型）
		if (null != scene_list && scene_list.size() > 0) {
			for (int i = 0; i < scene_list.size(); i++) {
				scene_list.get(i).put("create_time",String.valueOf(scene_list.get(i).get("create_time")));
			}
		}

		//返回数据的结果
		Map<String, Object> result1 = new WeakHashMap<String, Object>();
		result1.put("prod_info", products);
		result1.put("scene_list", scene_list);

		return ResponseMessageEnum.SUCCESS.appendMapToString(result1);
	}
	
	
	/**
	 * 获取新修改的渲染图
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {"/getNewPic" }, method = { RequestMethod.POST }, produces = { JSON_UTF8 })
	@ResponseBody
	public String getProductSceneNewPic(@RequestBody Map<String, Object> map)throws Exception {
		PageData pd = new PageData();
		pd.putAll(map);
		List<PageData> ext_list = productSceneExtService.selectByTime(pd);
		
		List<PageData> ext_product = new ArrayList<PageData>();
		Set<String> set = new HashSet<String>();
		
		for (PageData pageData : ext_list) {
			String product_id = String.valueOf(pageData.get("product_id"));
			if (set.add(product_id)) {
				pd.put("product_id", product_id);
				ext_product.addAll(productService.selectById(pd));
			}
		}
		
		//处理渲染图的时间（将时间改为String类型）
		if (null != ext_list && ext_list.size() > 0) {
			for (int i = 0; i < ext_list.size(); i++) {
				ext_list.get(i).put("create_time", String.valueOf(ext_list.get(i).get("create_time")));
			}
		}
		
		Map<String, Object> result = new WeakHashMap<String, Object>();
		result.put("ext_list", ext_list);
		result.put("ext_product", ext_product);
		return ResponseMessageEnum.SUCCESS.appendMapToString(result);
	}
	
	
	

	// /**
	// * 获取产品图片和产品的场景图片
	// *
	// * @param map
	// * @return
	// * @throws Exception
	// */
	// @RequestMapping(value = { "/getProductScenePic" }, method = {
	// RequestMethod.POST }, produces = {JSON_UTF8 })
	// @ResponseBody
	// public String getProductScenePic(@RequestBody Map<String, Object>
	// map)throws Exception {
	//
	// String name = (String) map.get("name");
	// String create_time = (String) map.get("create_time");// 以2017-12-29
	// 00:00:00的形式传递
	// String[] str = name.split("_");
	// String product_name = str[str.length-1];
	//
	// PageData pd = new PageData();
	// pd.put("product_name", product_name);//产品名称
	// pd.put("name", name);//场景名称
	// pd.put("create_time", create_time);//创建时机
	//
	// //得到时间戳
	// long stamp = 0, between = 0;
	// if (null != create_time && !create_time.equals("")) {
	// stamp = DateUtil.dateToStamp(create_time);
	// }
	//
	// between = DateUtil.dateToStamp("2018-01-01 00:00:00");
	//
	// List<PageData> products = new ArrayList<PageData>(), scenes = new
	// ArrayList<PageData>();
	//
	// // 判断时间大小 如果大于这个时间，那么只返回扩展表的数据（根据时间排序），反之，返回基础表的数据
	// if (stamp > between) {
	// // 扩展表的数据 扩展表的数据每次返回250张
	// List<PageData> ext_list = productSceneExtService.selectByTime(pd);
	// scenes.addAll(ext_list);
	// } else {
	// products = productService.selectByProductName(pd);
	// if (null == product_name || product_name.equals("")) {
	// pd.put("product_name", products.get(0).get("product_name"));
	// }
	// scenes = new ArrayList<PageData>();
	// if (products.size() > 1) {// 说明有数据
	// // 分为两步走
	// // 1.得到最基础表中没有下载的数据（排除客户端给我们的产品名称所包含的产品场景的图片数据）
	// // 2.根据客户端给我们的产品及其名字得到没有下载过的图片
	// List<PageData> base_list = productSceneService.selectByProductName(pd);
	// List<PageData> product_list =
	// productSceneService.selectByProductNameWithName(pd);
	// scenes.addAll(product_list);
	// scenes.addAll(base_list);
	// } else {
	// List<PageData> product_list =
	// productSceneService.selectByProductNameWithName(pd);
	// if (null != product_list && product_list.size() > 0) {
	// scenes.addAll(product_list);
	// } else {
	// // 扩展表的数据
	// List<PageData> ext_list = productSceneExtService.selectByTime(pd);
	// scenes.addAll(ext_list);
	// }
	// }
	// }
	//
	// // 处理产品图片和产品场景图片的数据
	// String[] products_pic = null;
	// if (null != products) {
	// products_pic = new String[products.size()];
	// for (int i = 0; i < products.size(); i++) {
	// products_pic[i] = (String) products.get(i).get("product_name");
	// }
	// }
	//
	// if (null != scenes) {
	// for (int i = 0; i < scenes.size(); i++) {
	// scenes.get(i).put("create_time",
	// scenes.get(i).get("create_time") + "");
	// }
	// }
	//
	// Map<String, Object> result1 = new WeakHashMap<String, Object>();
	// result1.put("products_pic", products_pic);
	// result1.put("scenes_pic", scenes);
	//
	// return ResponseMessageEnum.SUCCESS.appendMapToString(result1);
	// }

}
