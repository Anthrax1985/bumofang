package com.fh.controller.bmf.orders;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.fh.service.bmf.member.MemberService;
import com.fh.service.bmf.productrecord.ProductRecordApplicationService;
import com.fh.service.bmf.productrecord.ProductRecordStyleService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.common.model.ResponseMessageEnum;
import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.bmf.orderitem.OrderItemService;
import com.fh.service.bmf.orders.OrdersService;
import com.fh.util.AppUtil;
import com.fh.util.CommonUtil;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.Jurisdiction;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;

/**
 * 类名称：OrdersController 创建人：FH 创建时间：2017-03-16
 */
@Controller
@RequestMapping(value = "/orders")
public class OrdersController extends BaseController {

	String menuUrl = "orders/list.do"; // 菜单地址(权限用)
	@Resource(name = "ordersService")
	private OrdersService ordersService;

	@Resource(name = "orderitemService")
	private OrderItemService orderItemService;

	@Resource(name = "productRecordStyleService")
	private ProductRecordStyleService productRecordStyleService;

	@Resource(name = "productRecordApplicationService")
	private ProductRecordApplicationService productRecordApplicationService;

//	@Resource(name = "memberCartService")
//	private MemberCartService memberCartService;

	/**
	 * rest新增
	 */
	@RequestMapping(value = "/rest/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String restSave(@RequestBody Map<String, String> map)
			throws Exception {
		logBefore(logger, "REST新增Orders");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
			return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString();
		} // 校验权限
		PageData pd = new PageData();
		pd.putAll(map);
		ordersService.save(pd);

		return ResponseMessageEnum.SUCCESS.toString();
	}
	
	
	/**
	 * 创建订单（支付）
	 * 
	 * @param map
	 * @return{"order_item": [{"fabric_id": "10001", "amount": "2", "meter":
	 *                       "1", "option_id": 1, "unit_price":"1"}],
	 *                       "discount": "1", "coupon_id": 0, "receiver_id": 0,
	 *                       "pay_way": "dd", "receiver_id":0}
	 * @throws Exception
	 */
	@RequestMapping(value = "/createOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String restOrderSave(@RequestBody Map<String, Object> map)
			throws Exception {
		logBefore(logger, "REST新增Orders");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
			return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString();
		}
		List<Map<String, Object>> order_item = (List<Map<String, Object>>) map.get("order_item");// 一个订单的商品
		String orderId = CommonUtil.getOrderNumber();// 订单编号
		String buy_id = (String) map.get("buy_id");// 购买者id
		Float allPrice = 0f;

		// 一个订单添加多个商品
		if (null != order_item && order_item.size() > 0) {
			for (Map<String, Object> item : order_item) {

				Float itemPrice = Float.parseFloat((String) item
						.get("total_price"));
				allPrice += itemPrice;
				String discount = (String) item.get("discount");

				PageData pd = new PageData();
				String product_id = (String) item.get("fabric_id");
				pd.put("fabric_id", product_id);// 面料id
				pd.put("order_num", orderId);// 订单编号
				pd.put("amount", Integer.parseInt((String) item.get("amount")));// 数量
				pd.put("meter", Integer.parseInt((String) item.get("meter")));// 米数
				pd.put("option_id", (Integer) item.get("option_id"));// 0:单幅
																		// 1:宽幅
				pd.put("unit_price",
						Float.parseFloat((String) item.get("unit_price")));// 单价
				pd.put("total_price", itemPrice);// 总价
				pd.put("real_pay", 0);// 实际支付
				pd.put("buy_id", buy_id);// 购买用户id
				pd.put("remark", "");// 备注
				if (null == discount || discount.equals("")) {
					pd.put("discount", 0f);// 折扣
				} else {
					pd.put("discount",
							Float.parseFloat((String) map.get("discount")));// 折扣
				}

//				PageData cond = new PageData();
//				cond.put("member_id", buy_id);
//				cond.put("product_id", product_id);
//				PageData result = memberCartService.getUseFor(cond);
//				Long use_for = result.getLong("use_for");
				pd.put("use_for", 0);

				orderItemService.save1(pd);// 添加多个商品
			}
		}

		String discount = (String) map.get("discount");
		
		Object express_id =  map.get("express_id");

		PageData pd = new PageData();
		pd.put("num", orderId);// 订单编号 必填
		pd.put("buy_id", buy_id);// 购买者id 必填
		pd.put("total_price", allPrice);// 总价必填
		pd.put("real_pay", 0);// 实际支付必填
		pd.put("receiver_id", (Integer) map.get("receiver_id"));// 接收人id 必填
		pd.put("create_time", DateUtil.getTime());// 创建时间 必填
		pd.put("pay_time", "");// 支付时间
		pd.put("pay_way", (String) map.get("pay_way"));// 支付方式
		pd.put("pay_account", "");// 支付账户
		pd.put("express_id", "");// 快递id
		pd.put("express_name", "");// 快递名称
		pd.put("remark", "");// 备注
		
		if (null == express_id) {
			pd.put("coupon_id", 0);// 优惠券id
		}else {
			pd.put("coupon_id", (Integer)express_id);// 优惠券id
		}
		
		if (null == discount || discount.endsWith("")) {
			pd.put("discount", 0f);// 折扣
		} else {
			pd.put("discount", Float.parseFloat(discount));// 折扣
		}
		ordersService.save(pd);

		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("orderId", orderId);

		return ResponseMessageEnum.SUCCESS.appendMapToString(map1);
	}
	
	/**
	 * 订单详情接口
	 * @param map   入参：orderId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/detailOrder", method = RequestMethod.POST)
	@ResponseBody
	public String detailOrders(@RequestBody Map<String, Object> map)throws Exception {
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
			return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString();
		}
		
		PageData pd = new PageData();
		pd.putAll(map);
		PageData receiverData = ordersService.listWithReceiverById(pd);
		List<PageData> items = orderItemService.listByOrderId(pd);
		Map<String, Object> map1 = new WeakHashMap<String, Object>();
		map1.put("receiver", receiverData);
		map1.put("products", items);
		return ResponseMessageEnum.SUCCESS.appendMapToString(map);
	}
	
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete")
	public void delete(PrintWriter out) {
		logBefore(logger, "删除Orders");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
			return;
		} // 校验权限
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			ordersService.delete(pd);
			out.write("success");
			out.close();
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}

	}

	/**
	 * RESTFUL修改
	 */
	@RequestMapping(value = "/rest/edit", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String restEdit(@RequestBody Map<String, String> map)
			throws Exception {
		logBefore(logger, "修改Orders");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
			return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString();
		} // 校验权限
		PageData pd = new PageData();
		pd.putAll(map);
		ordersService.edit(pd);
		return ResponseMessageEnum.SUCCESS.toString();
	}

	/**
	 * 列表with member
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(Page page) {
		logBefore(logger, "列表Orders");
		// if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		// //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			String QUERY_KEY = pd.getString("QUERY_KEY");
			if (null != QUERY_KEY && !"".equals(QUERY_KEY)) {
				QUERY_KEY = QUERY_KEY.trim();
				pd.put("QUERY_KEY", QUERY_KEY);
			}

			page.setPd(pd);
			
			String end = (String) page.getPd().get("QUERY_TIME_END"); 
			if (null != end && !end.equals("")) {
				page.getPd().put("QUERY_TIME_END", end + " 24:00:00");
			}
			
			List<PageData> varList = ordersService.listWithMember(page); // 列出Orders列表
			mv.addObject("varList", varList);
			mv.setViewName("bmf/orders/orders_list");
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX, this.getHC()); // 按钮权限
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "/addPage")
	public ModelAndView addPage(Page page) {
		logBefore(logger, "列表Orders");
		// if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		// //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			String QUERY_KEY = pd.getString("QUERY_KEY");
			if (null != QUERY_KEY && !"".equals(QUERY_KEY)) {
				QUERY_KEY = QUERY_KEY.trim();
				pd.put("QUERY_KEY", QUERY_KEY);
			}

			page.setPd(pd);
			mv.setViewName("bmf/orders/orders_add");
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX, this.getHC()); // 按钮权限
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * 去新增页面
	 */
	@RequestMapping(value = "/goAdd")
	public ModelAndView goAdd() {
		logBefore(logger, "去修改Orders页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("bmf/orders/orders_check");
			mv.addObject("msg", "save");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * 去查看页面
	 */
	@RequestMapping(value = "/goCheck")
	public ModelAndView goCheck() {
		logBefore(logger, "去查看Orders页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = ordersService.findWithMemberById(pd); // 根据ID读取
			String orderId = String.valueOf(pd.get("num"));
			PageData cond = new PageData();
			cond.put("orderId", orderId);
			List<PageData> prodList = orderItemService.listByOrderId(cond);

			for (int i = 0; i < prodList.size(); i++) {
				PageData item = new PageData();
				item.put("product_id", prodList.get(i).get("fabric_id"));
				//获取样式
				List<PageData> styles = productRecordStyleService.findByProductId(item);
				prodList.get(i).put("styles", styles);
				//获取应用
				List<PageData> applications = productRecordApplicationService.findByProductId(item);
				prodList.get(i).put("applications", applications);
			}


			mv.setViewName("bmf/orders/orders_check");
			mv.addObject("msg", "check");
			mv.addObject("prodList", prodList);
			mv.addObject("pd", pd);

		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * 批量删除
	 */
	@RequestMapping(value = "/deleteAll")
	@ResponseBody
	public Object deleteAll() {
		logBefore(logger, "批量删除Orders");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "dell")) {
			return null;
		} // 校验权限
		PageData pd = new PageData();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if (null != DATA_IDS && !"".equals(DATA_IDS)) {
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				ordersService.deleteAll(ArrayDATA_IDS);
				pd.put("msg", "ok");
			} else {
				pd.put("msg", "no");
			}
			pdList.add(pd);
			map.put("list", pdList);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		return AppUtil.returnObject(pd, map);
	}


	@RequestMapping(value = "/setStatus", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String setStatus(@RequestBody Map<String, Object> map) throws Exception {
		PageData pd = new PageData();

		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String order_id = (String) map.get("order_id");
			String status = (String) map.get("status");

			PageData cond = new PageData();
			cond.put("orderId", order_id);
			cond.put("status", status);
			cond.put("id", order_id);
			ordersService.setStatus(cond);
			
			PageData list = ordersService.findById(cond);
			if (null != list) {
				cond.put("num", list.get("num"));
				//修改order_item的状态
				int size = (int) orderItemService.editByOrderId2(cond);
				if (size<1) {
					return ResponseMessageEnum.FAIL.toString();
				}
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		return ResponseMessageEnum.SUCCESS.toString();
	}

	/*
	 * 导出到excel
	 * 
	 * @return
	 */
	@RequestMapping(value = "/excel")
	public ModelAndView exportExcel() {
		logBefore(logger, "导出Orders到excel");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "cha")) {
			return null;
		}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("订单编号"); // 1
			titles.add("购买者id"); // 2
			titles.add("总价"); // 3
			titles.add("实际支付"); // 4
			titles.add("折扣"); // 5
			titles.add("优惠券id"); // 6
			titles.add("接收人id"); // 7
			titles.add("快递id"); // 8
			titles.add("快递名称"); // 9
			titles.add("订单状态"); // 10
			titles.add("创建时间"); // 11
			titles.add("支付时间"); // 12
			titles.add("支付方式"); // 13
			titles.add("支付账户"); // 14
			titles.add("备注"); // 15
			dataMap.put("titles", titles);
			List<PageData> varOList = ordersService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for (int i = 0; i < varOList.size(); i++) {
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("num")); // 1
				vpd.put("var2", varOList.get(i).getString("buy_id")); // 2
				vpd.put("var3", varOList.get(i).getString("total_price")); // 3
				vpd.put("var4", varOList.get(i).getString("real_pay")); // 4
				vpd.put("var5", varOList.get(i).getString("discount")); // 5
				vpd.put("var6", varOList.get(i).getString("coupon_id")); // 6
				vpd.put("var7", varOList.get(i).getString("receiver_id")); // 7
				vpd.put("var8", varOList.get(i).getString("express_id")); // 8
				vpd.put("var9", varOList.get(i).getString("express_name")); // 9
				vpd.put("var10", varOList.get(i).getString("status")); // 10
				vpd.put("var11", varOList.get(i).getString("create_time")); // 11
				vpd.put("var12", varOList.get(i).getString("pay_time")); // 12
				vpd.put("var13", varOList.get(i).getString("pay_way")); // 13
				vpd.put("var14", varOList.get(i).getString("pay_account")); // 14
				vpd.put("var15", varOList.get(i).getString("remark")); // 15
				varList.add(vpd);
			}
			dataMap.put("varList", varList);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv, dataMap);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}

}
