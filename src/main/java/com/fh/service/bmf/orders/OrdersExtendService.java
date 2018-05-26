package com.fh.service.bmf.orders;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.common.model.ResponseMessageEnum;
import com.fh.extend.util.OrderNumCreator;
import com.fh.service.bmf.fabric.FabricService;
import com.fh.service.bmf.orderitem.OrderItemService;
import com.fh.service.bmf.shopcart.ShopcartService;
import com.fh.util.PageData;
import com.fh.util.Tools;

@Service("ordersExtendService")
public class OrdersExtendService {
	
	@Resource(name = "ordersService")
	private OrdersService ordersService;
	
	@Resource(name = "orderitemService")
	private OrderItemService orderItemService;
	
	@Resource(name = "shopcartService")
	private ShopcartService shopcartService;
	
	@Resource(name = "fabricService")
	private FabricService fabricService;
	
	public String saveShopchartOrder(String userId, Object[] cartIdArray, String receiverId) throws Exception{
		PageData pdIds = new PageData();
		pdIds.put("ids", cartIdArray);
		List<PageData> cartList = shopcartService.listWithFabricByCondition(pdIds);
		if(cartList == null || cartList.size() == 0){
			return ResponseMessageEnum.SERVER_DATA_NOTEXIST.toString();
		}
		
		float allPrice = 0;
		String num = OrderNumCreator.create(userId);
		for(PageData cart : cartList){
			Float unitPrice = (Float)cart.get("price");
			Integer amount = (Integer)cart.get("amount");
			String fabricId = (String)cart.get("fabric_id");
			float totalPrice = unitPrice * amount;
			allPrice += totalPrice;
			
			PageData orderItemPd = new PageData();
			orderItemPd.put("order_num", num);
			orderItemPd.put("fabric_id", fabricId);
			orderItemPd.put("unit_price", unitPrice);
			orderItemPd.put("amount", amount);
			orderItemPd.put("total_price", totalPrice);
			orderItemPd.put("real_pay", totalPrice);
			orderItemPd.put("buy_id", userId);
			orderItemService.save(orderItemPd);
		}
		
		PageData orderPd = new PageData();
		orderPd.put("num", num);
		orderPd.put("buy_id", userId);
		orderPd.put("total_price", allPrice);
		orderPd.put("real_pay", allPrice);
		orderPd.put("receiver_id", receiverId);
		orderPd.put("create_time", Tools.date2Str(new Date()));
		ordersService.save(orderPd);
		
		shopcartService.deleteAll(cartIdArray);
		
		PageData rsPd = new PageData();
		rsPd.put("order_num", num);
		rsPd.put("total_price", allPrice);
		return ResponseMessageEnum.SUCCESS.appendMapToString(rsPd);
	}

}
