package com.fh.controller.bmf.app;

import java.util.*;
import javax.annotation.Resource;

import com.fh.service.bmf.member.MemberService;
import com.fh.service.bmf.syslog.SysLogBizService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fh.common.model.ResponseMessageEnum;
import com.fh.controller.base.AppBaseController;
import com.fh.entity.Page;
import com.fh.service.bmf.fabric.FabricService;
import com.fh.service.bmf.member.MemberCartService;
import com.fh.service.bmf.orderitem.OrderItemService;
import com.fh.service.bmf.orders.OrdersExtendService;
import com.fh.service.bmf.orders.OrdersService;
import com.fh.service.bmf.productrecord.ProductRecordApplicationService;
import com.fh.service.bmf.productrecord.ProductRecordStyleService;
import com.fh.service.bmf.receiver.ReceiverService;
import com.fh.service.bmf.shopcart.ShopcartService;
import com.fh.util.CommonUtil;
import com.fh.util.DateUtil;
import com.fh.util.PageData;
import com.fh.util.Tools;
import org.apache.commons.lang.StringUtils;

/**
 * 订单
 */
@Controller("AppOrderController")
@RequestMapping(value = "/app/order")
public class OrderController extends AppBaseController {

	@Resource(name = "fabricService")
	private FabricService fabricService;

	@Resource(name = "shopcartService")
	private ShopcartService shopcartService;

	@Resource(name = "memberService")
	private MemberService memberService;
	
	@Resource(name = "memberCartService")
	private MemberCartService memberCartService;

	@Resource(name = "ordersService")
	private OrdersService ordersService;

	@Resource(name = "receiverService")
	private ReceiverService receiverService;

	@Resource(name = "orderitemService")
	private OrderItemService orderItemService;

	@Resource(name = "ordersExtendService")
	private OrdersExtendService ordersExtendService;
	
	@Resource(name = "productRecordStyleService")
	private ProductRecordStyleService productRecordStyleService;
	
	@Resource(name = "productRecordApplicationService")
	private ProductRecordApplicationService productRecordApplicationService;

	@Resource(name="sysLogBizService")
	private SysLogBizService sysLogBizService;

	public void log(String path, String msg){
		sysLogBizService.saveAppLog(request, path, msg);
	}

	/**
	 * { "fabric_id": "123", "amount": 2 }
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = { "/shopcart/add" }, method = { RequestMethod.POST }, produces = { JSON_UTF8 })
	@ResponseBody
	public String addShopcart(@RequestBody Map<String, String> map) {
		Object result = getUser();
		if (result instanceof ResponseMessageEnum) {
			return result.toString();
		}

		PageData pd = (PageData) result;
		String userId = pd.getString("ID");

		String fabricId = map.get("fabric_id");

		try {
			PageData shopPd = getShopcart(userId, fabricId);
			if (shopPd != null) {
				Integer amount = (Integer) shopPd.get("amount");
				int buyAmount = Integer.parseInt(map.get("amount"));
				int totalAmount = amount + buyAmount;
				shopPd.put("amount", totalAmount);
				shopcartService.edit(shopPd);
				return ResponseMessageEnum.SUCCESS.appendEmptyData();
			}

		} catch (Exception e) {
			logger.warn("addShopcart", e);
			return ResponseMessageEnum.SERVER_DATA_STATUS_ERROR.toString();
		}

		PageData fabPd = new PageData();
		fabPd.put("ID", fabricId);

		try {
			PageData fabPageData = fabricService.findById(fabPd);
			if (fabPageData == null || fabPageData.isEmpty()) {
				return ResponseMessageEnum.SERVER_DATA_NOTEXIST.toString();
			}
		} catch (Exception e) {
			logger.warn("addShopcart", e);
			return ResponseMessageEnum.SERVER_DATA_STATUS_ERROR.toString();
		}

		PageData pageData = new PageData();
		pageData.putAll(map);
		pageData.put("buy_id", userId);
		pageData.put("create_time", Tools.date2Str(new Date()));
		try {
			shopcartService.save(pageData);
		} catch (Exception e) {
			logger.warn("addShopcart", e);
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}

		return ResponseMessageEnum.SUCCESS.appendEmptyData();
	}

	/**
	 * 创建订单（支付）
	 *
	 * @param req
	 *            请求的数据
	 * @param rep
	 *            响应的数据
	 * @return 是否添加成功
	 * @throws Exception
	 */
	@RequestMapping(value = "/createOrder", method = RequestMethod.POST, produces = JSON_UTF8)
	@ResponseBody
	public String restOrderSave(@RequestBody Map<String, Object> map)throws Exception {
		Object result = getUser();
		if (result instanceof ResponseMessageEnum) {
			return result.toString();
		}

		PageData userInfo = (PageData) result;
		String buy_id = userInfo.get("ID") + "";// 购买者id
		String orderId = CommonUtil.getOrderNumber();// 订单编号
		
		List<Integer> cart_id = (List<Integer>) map.get("cart_id");// 一个订单的商品
		double allPrice = 0;

		log("app/order/createOrder", "buy_id=" + buy_id + "&orderId=" + orderId + "&cart_id=" + StringUtils.join(cart_id.toArray(), ","));
		
//		Type listType = new TypeToken<List<Object>>(){}.getType();   
//		List<Object> linkedList = new Gson().fromJson(cart_id, listType); 
		

		// 一个订单添加多个商品
		if (null != cart_id && cart_id.size() > 0) {
			for (Integer object : cart_id) {
				String id = object+"";
				PageData pdData = new PageData();
				pdData.put("id", id);
				
				//查询购物车
				PageData item = memberCartService.findById1(pdData);
				if (null!= item) {
					double itemPrice = (double)item.get("price") * (int)item.get("meter");
					Integer meter = item.getInteger("meter");
					if(meter >= 300){
						itemPrice = itemPrice * 0.85;
					}

					allPrice += itemPrice;
					Long use_for = item.getLong("use_for");
					PageData pd = new PageData();
					pd.put("fabric_id", item.get("product_id"));// 面料id
					pd.put("order_num", orderId);// 订单编号
					pd.put("amount", item.get("total_num"));// 数量
					pd.put("meter", item.get("meter"));// 米数
					pd.put("option_id", 0);// 0:单幅 1:宽幅
					pd.put("unit_price", item.get("price"));// 单价
					pd.put("total_price", itemPrice);// 总价
					pd.put("real_pay", 0);// 实际支付
					pd.put("buy_id", buy_id);// 购买用户id
					pd.put("remark", "");// 备注
					pd.put("discount", 0f);// 折扣
					pd.put("use_for", use_for);
					
					orderItemService.save1(pd);// 添加多个商品
				}
			}
		}

		if(allPrice >= 100000D){
			allPrice = allPrice * 0.93;
		}

		String discount = (String) map.get("discount");
		PageData pd = new PageData();
		pd.put("num", orderId);// 订单编号 必填
		pd.put("buy_id", buy_id);// 购买者id 必填
		pd.put("total_price", allPrice);// 总价必填
		pd.put("real_pay", 0);// 实际支付必填
		pd.put("receiver_id", map.get("receiver_id"));// 接收人id 必填
		pd.put("create_time", DateUtil.getTime());// 创建时间 必填
		pd.put("pay_time", "");// 支付时间
		pd.put("pay_way",  map.get("pay_way"));// 支付方式
		pd.put("coupon_id", map.get("coupon_id"));// 优惠券id
		pd.put("pay_account", "");// 支付账户
		pd.put("express_id", "");// 快递id
		pd.put("express_name", "");// 快递名称
		pd.put("remark", "");// 备注

		if (null == discount || discount.equals("")) {
			pd.put("discount", 0f);// 折扣
		} else {
			pd.put("discount", discount);// 折扣
		}

		PageData uid = new PageData();
		userInfo.put("id", buy_id);
		PageData user_info = memberService.findById(uid);
		if(user_info != null){
			pd.put("nickname", user_info.getString("nickname"));// 昵称
			pd.put("mobile", user_info.getString("mobile"));		// 手机号
		}else{
			pd.put("nickname", null);
			pd.put("mobile", null);
		}

		ordersService.save(pd);
		
		//移除购物车的数据
		for (Integer item : cart_id){
			PageData delParam = new PageData();
			delParam.put("ID", item);
			int size = (int) memberCartService.updateByDelete(delParam);
			if (size<1) {
				return ResponseMessageEnum.FAIL.toString();
			}
		}

		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("orderId", orderId);
		map1.put("allPrice", allPrice);

		return ResponseMessageEnum.SUCCESS.appendMapToString(map1);
	}

	/**
	 * 根据用户ID查询订单    入参 ：token,page(从0开始),count
	 * 
	 * @param req
	 * @param rep
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/listOrders", method = RequestMethod.POST, produces = JSON_UTF8)
	@ResponseBody
	public String listOrders(@RequestBody Map<String, Object> map)throws Exception {
		Object result = getUser();
		if (result instanceof ResponseMessageEnum) {
			return result.toString();
		}
		PageData pd = (PageData) result;
//		int page = (int) map.get("page");
//		int count = (int) map.get("count");
//		pd.put("page", page * 10);
//		pd.put("count", count);
		List<PageData> varList = ordersService.listWithMemberByBuyId(pd);
		return ResponseMessageEnum.SUCCESS.appendPageDataListToString(varList);
	}

	/**
	 * 订单详情接口  入参：token,orderId
	 * @param req
	 * @param rep
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/detailOrder", method = RequestMethod.POST, produces = JSON_UTF8)
	@ResponseBody
	public String detailOrders(@RequestBody Map<String, String> map)throws Exception {
		Object result = getUser();
		if (result instanceof ResponseMessageEnum) {
			return result.toString();
		}
		PageData pd = (PageData) result;
		String orderId = map.get("orderId");
		pd.put("orderId", orderId);
		//获取收件人信息和产品信息
		PageData receiverData = ordersService.listWithReceiverById(pd);
		List<PageData> items = orderItemService.listByOrderId(pd);
		
		for (int i = 0; i < items.size(); i++) {
			PageData item = new PageData();
			item.put("product_id", items.get(i).get("fabric_id"));
			//获取样式
			List<PageData> styles = productRecordStyleService.findByProductId(item);
			items.get(i).put("styles", styles);
			//获取应用
			List<PageData> applications = productRecordApplicationService.findByProductId(item);
			items.get(i).put("applications", applications);	
		}
		
		Map<String, Object> results = new WeakHashMap<String, Object>();
		results.put("receiver", receiverData);
		results.put("products", items);
		return ResponseMessageEnum.SUCCESS.appendMapToString(results);
	}

	/**
	 * { "code": 200, "msg": "", "data": [ { "id": "购物车ID", "fab_id": "面料ID",
	 * "name": "面料名称", "image_url": "http://www.bmf.com/image/abcdef.jpg",
	 * "unit_price": 100, "amount": 3, "total_price": 300 } ] }
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value = { "/shopcart/list" }, method = { RequestMethod.GET }, produces = { JSON_UTF8 })
	@ResponseBody
	public Object listShopcart() throws Exception {
		Object result = getUser();
		if (result instanceof ResponseMessageEnum) {
			return result.toString();
		}

		PageData pd = (PageData) result;

		String userId = pd.getString("ID");
		PageData pageData = new PageData();
		pageData.put("userId", userId);

		try {
			List<PageData> pdList = shopcartService
					.listWithFabricByCondition(pageData);
			return ResponseMessageEnum.SUCCESS
					.appendPageDataListToString(pdList);
		} catch (Exception e) {
			logger.warn("listShopcart", e);
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}

	}

	/**
	 * { "code": 200, "msg": "", "data": [ { "id": "购物车ID", "fab_id": "面料ID",
	 * "name": "面料名称", "image_url": "http://www.bmf.com/image/abcdef.jpg",
	 * "unit_price": 100, "amount": 3, "total_price": 300 } ] }
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value = { "/list" }, method = { RequestMethod.GET }, produces = { JSON_UTF8 })
	@ResponseBody
	public Object list(
			@RequestParam(value = "status", required = false) Integer status,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "pageSize", required = false) Integer pageSize)
			throws Exception {
		Object result = getUser();
		if (result instanceof ResponseMessageEnum) {
			return result.toString();
		}

		if (status == null) {
			status = 0;
		}
		if (page == null) {
			page = 1;
		}
		if (pageSize == null) {
			pageSize = 100;
		}

		PageData userPd = (PageData) result;
		String userId = userPd.getString("ID");

		Page pageItem = new Page();
		pageItem.setCurrentPage(page);
		pageItem.setShowCount(pageSize);
		PageData pageData = new PageData();
		pageData.put("userId", userId);
		pageData.put("status", status);
		pageItem.setPd(pageData);

		List<PageData> rsList = ordersService.list(pageItem);
		if (rsList == null || rsList.size() == 0) {
			return ResponseMessageEnum.SUCCESS.appendEmptyData();
		}

		for (PageData pd : rsList) {
			String orderNum = pd.getString("num");
			PageData queryPd = new PageData();
			queryPd.put("orderNum", orderNum);
			List<PageData> pdList = orderItemService
					.listWithFabricByCondition(queryPd);
			pd.put("list", pdList);
		}

		for (PageData pd : rsList) {
			String receiverId = pd.get("receiver_id") + "";
			PageData queryPd = new PageData();
			queryPd.put("id", receiverId);
			PageData receiver = receiverService.findById(queryPd);
			pd.put("receiver", receiver);
		}

		for (PageData pd : rsList) {
			pd.remove("receiver_id");
		}

		return ResponseMessageEnum.SUCCESS.appendPageDataListToString(rsList);
	}

	/**
	 * { "ids": [1,2,3], "receiver_id": "111" }
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = { "/shopcart/buy" }, method = { RequestMethod.POST }, produces = { JSON_UTF8 })
	@ResponseBody
	public String submitShopcart(@RequestBody Map<String, Object> map) {
		Object userResult = getUser();
		if (userResult instanceof ResponseMessageEnum) {
			return userResult.toString();
		}

		PageData pd = (PageData) userResult;
		String userId = pd.getString("ID");

		String receiverId = map.get("receiver_id").toString();
		Object[] cartIdArray = ((List<Object>) map.get("ids")).toArray();

		try {
			return ordersExtendService.saveShopchartOrder(userId, cartIdArray,
					receiverId);
		} catch (Exception e) {
			logger.warn("submitShopcart", e);
			return ResponseMessageEnum.ERROR_CONFIRM_SHOPCART.toString();
		}

	}

	private PageData getShopcart(String userId, String fabricId)
			throws Exception {
		PageData pd = new PageData();
		pd.put("userId", userId);
		pd.put("fabricId", fabricId);

		List<PageData> pdList = shopcartService.listByCondition(pd);
		if (pdList == null || pdList.size() == 0)
			return null;

		return pdList.get(0);
	}


	@RequestMapping(value = "/cancel", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String cancel(@RequestBody Map<String, Object> map) throws Exception {
		PageData pd = new PageData();

		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String num = (String) map.get("num");
			//String status = (String) map.get("status");

			log("app/order/cancel", "num=" + num);


			PageData cond = new PageData();
			cond.put("num", num);
			//cond.put("status", status);
			cond.put("status", "9");
			ordersService.setStatusByNum(cond);

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

}
