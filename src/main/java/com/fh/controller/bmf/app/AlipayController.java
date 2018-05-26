package com.fh.controller.bmf.app;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fh.service.bmf.member.MemberCartService;
import com.fh.service.bmf.syslog.SysLogBizService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.fh.common.model.ResponseMessageEnum;
import com.fh.controller.base.AppBaseController;
import com.fh.service.bmf.orderitem.OrderItemService;
import com.fh.service.bmf.orders.OrdersService;
import com.fh.service.system.debug.DebugService;
import com.fh.util.PageData;
import com.fh.util.alipay.AlipayConfig;

/**
 * 支付宝支付的控制器
 */
@Controller(value = "AppAlipayController")
@RequestMapping("/app/alipay")
public class AlipayController extends AppBaseController{

	@Resource(name = "debugService")
	private DebugService debugService;
	
	@Resource(name = "ordersService")
	private OrdersService ordersService;
	
	@Resource(name = "memberCartService")
	private MemberCartService memberCartService;

	@Resource(name = "orderitemService")
	private OrderItemService orderItemService;

	@Resource(name="sysLogBizService")
	private SysLogBizService sysLogBizService;

	public void log(String path, String msg){
		sysLogBizService.saveAppLog(request, path, msg);
	}

	/**
	 * 调起支付宝支付
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getAlipay", method = RequestMethod.POST, produces = JSON_UTF8)
	@ResponseBody
	public String getAlipay(@RequestBody Map<String, String> map)throws Exception {

		Object result = getUser();
		if (result instanceof ResponseMessageEnum) {
			return result.toString();
		}
		
		String outtradeno = map.get("outtradeno");//商户网站唯一订单号
		PageData pd = new PageData();
		pd.put("orderId", outtradeno);
		PageData orders = ordersService.findByOrderId(pd);

		log("app/alipay/getAlipay", "orderId=" + outtradeno);

		if (null == orders) {
			return ResponseMessageEnum.FAIL.toString();
		}
		// 获取App传过来的参数
		String totalamount = String.valueOf(orders.get("total_price"));//订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
		String notify_url = AlipayConfig.SERVICECALLBACK;
		// 拼接业务数据
		String jsonStr = "{\"timeout_express\":\"30m\","
				+ "\"product_code\":\"QUICK_MSECURITY_PAY\","
				+ "\"total_amount\":\"" + totalamount + "\","
				+ "\"subject\":\"布魔方\"," 
				+ "\"body\":\"布魔方\","
				+ "\"out_trade_no\":\"" + outtradeno + "\"}";

		// 生成App所需要的参数
		String privateKey = AlipayConfig.APP_PRIVATE_KEY;
		boolean rsa2 = (privateKey.length() > 0);
		Map<String, String> params = AlipayConfig.buildOrderParamMap(jsonStr,notify_url);
		String orderParam = AlipayConfig.buildOrderParam(params);
		String sign = AlipayConfig.getSign(params, privateKey, rsa2);
		String orderInfo = orderParam + "&" + sign;

		log("app/alipay/getAlipay", "orderInfo=" + orderInfo);

		// 返回数据结果
		return ResponseMessageEnum.SUCCESS.appendObject(orderInfo);
	}
	
	/**
	 * 支付宝支付成功的回调
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAlipayCallBack", method = RequestMethod.POST, produces = JSON_UTF8)
	@ResponseBody
	public String getAlipayCallBack(HttpServletRequest req,
			HttpServletResponse rep) throws Exception {
		// 获取支付宝异步回调的数据
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = req.getParameterMap();

		String allStr = "";
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
			allStr = allStr + name + "=" + valueStr + "&";
		}
		log("app/alipay/getAlipayCallBack", allStr);

		// 切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。验签
		boolean flag = AlipaySignature.rsaCheckV1(params,
				AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, "RSA2");
		String trade_status = params.get("trade_status");

		//修改订单的状态，并修改支付金额
		String orderId = params.get("out_trade_no");
		String buyer_pay_amount = params.get("buyer_pay_amount");//实付金额
		String buyer_logon_id = params.get("buyer_logon_id");//支付宝实际账户
		String gmt_payment = params.get("gmt_payment");//支付时间
		
		
		// 保存数据到数据库
		PageData pd = new PageData();
		pd.put("order_id", orderId);
		if (flag && trade_status.equals("TRADE_SUCCESS")) {
			pd.put("status", 0);
			debugService.save(pd);
			PageData orderpd = new PageData();
			orderpd.put("orderId", orderId);
			orderpd.put("real_pay", buyer_pay_amount);
			orderpd.put("pay_way", "支付宝");
			orderpd.put("pay_account", buyer_logon_id);
			orderpd.put("pay_time", gmt_payment);
			orderpd.put("status", 1);
			ordersService.editByOrderId(orderpd);
			orderItemService.editByOrderId(orderpd);

//			PageData param = new PageData();
//			param.put("orderId", orderId);
//			PageData orderInfo = ordersService.findByOrderId(param);
//			String member_id = orderInfo.getString("buy_id");
//
//			List<PageData> item_list =  ordersService.findOrderItemByNum(param);
//			for (PageData item : item_list){
//				String product_id = item.getString("fabric_id");
//
//				PageData delParam = new PageData();
//				delParam.put("PRODUCT_ID", product_id);
//				delParam.put("MEMBER_ID", member_id);
//				memberCartService.deleteByParam(delParam);
//			}

		} else {
			pd.put("status", 1);
			debugService.save(pd);
		}
		return ResponseMessageEnum.SUCCESS.toString();
	}

	/**
	 * 扫码支付
	 * 
	 * @throws AlipayApiException
	 */
	@RequestMapping(value = "/getAlipayScanCode", method = RequestMethod.POST)
	@ResponseBody
	public String getAlipayScanCode(HttpServletRequest req,
			HttpServletResponse rep) throws AlipayApiException {
		String out_trade_no = req.getParameter("out_trade_no");//商户订单号，需要保证不重复
		String total_amount = req.getParameter("total_amount");//订单金额
		String subject = req.getParameter("subject");//订单标题
		String store_id = req.getParameter("store_id");//商户门店编号

		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL,
				AlipayConfig.AppID, AlipayConfig.APP_PRIVATE_KEY, "json",
				AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, "RSA2"); // 获得初始化的AlipayClient
		AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();// 创建API对应的request类
		request.setBizContent("{" + "\"out_trade_no\":\"" + out_trade_no
				+ "\"," + "\"total_amount\":\"" + total_amount + "\","
				+ "\"subject\":\"" + subject + "\"," + "\"store_id\":\""
				+ store_id + "\"," + "\"timeout_express\":\"90m\"}");// 设置业务参数
		AlipayTradePrecreateResponse response = alipayClient.execute(request);
		System.out.print(response.getBody());
		return response.getBody();
	}

}
