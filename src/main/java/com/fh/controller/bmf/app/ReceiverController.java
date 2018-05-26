package com.fh.controller.bmf.app;

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
import com.fh.service.bmf.receiver.ReceiverService;
import com.fh.util.DateUtil;
import com.fh.util.PageData;
import com.fh.util.Tools;

/**
 * 类名称：ReceiverController 创建人：FH 创建时间：2017-03-16
 */
@Controller("AppReceiverController")
@RequestMapping(value = "/app/receiver")
public class ReceiverController extends AppBaseController {

	@Resource(name = "receiverService")
	private ReceiverService receiverService;

	/**
	 * 添加收件人的地址 
	 * 
	 * @param req
	 * @param rep
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value = "/createReceiver", method = RequestMethod.POST, produces = JSON_UTF8)
	@ResponseBody
	public String createReceiver(@RequestBody Map<String, String> map) throws Exception {
		Object result = getUser();
		if (result instanceof ResponseMessageEnum) {
			return result.toString();
		}
		PageData userInfo = (PageData) result;

		// 验证手机号码是否正确
		String mobile = map.get("mobile");
		if (!Tools.checkMobileNumber(mobile)) {
			return ResponseMessageEnum.ERROR_MOBILE_FORMAT.toString();
		}
		PageData pd = new PageData();
		pd.put("create_user", userInfo.get("ID"));// 创建用户id
		pd.put("name", map.get("name"));// 姓名
		pd.put("mobile", mobile);// 手机号
		pd.put("company_name", map.get("company_name"));//公司
		pd.put("addr_province", map.get("addr_province"));// 收件人省份
		pd.put("addr_city", map.get("addr_city"));// 收件人城市
		pd.put("addr_county", map.get("addr_county"));// 收件人区、县
		pd.put("addr_detail", map.get("addr_detail"));// 收件人详细地址
		pd.put("create_time", DateUtil.getTime());// 创建时间
		pd.put("update_time", null);// 修改时间
		String post_code = map.get("post_code");
		if (null == post_code || post_code.equals("")) {
			pd.put("post_code", 0f);// 邮编
		}else {
			pd.put("post_code", map.get("post_code"));// 邮编	
		}
		
		receiverService.save(pd);
		return ResponseMessageEnum.SUCCESS.appendObject(pd.get("id"));
	}
	
	
	/**
	 * 修改收货地址
	 * @param req
	 * @param rep
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/updateReceiver", method = RequestMethod.POST, produces = JSON_UTF8)
	@ResponseBody
	public String updateReceiver(@RequestBody Map<String, String> map) throws Exception{
		
		Object result = getUser();
		if (result instanceof ResponseMessageEnum) {
			return result.toString();
		}
		
		PageData pd = new PageData();
		pd.put("id", map.get("id"));
		pd.put("name", map.get("name"));
		pd.put("mobile", map.get("mobile"));
		pd.put("company_name", map.get("company_name"));
		pd.put("addr_province", map.get("addr_province"));
		pd.put("addr_city", map.get("addr_city"));
		pd.put("addr_county", map.get("addr_county"));
		pd.put("addr_detail", map.get("addr_detail"));
		pd.put("update_time", DateUtil.getTime());
		String post_code = map.get("post_code");
		if (null == post_code || post_code.equals("")) {
			pd.put("post_code", 0f);// 邮编
		}else {
			pd.put("post_code", map.get("post_code"));// 邮编	
		}
		
		int size = (int) receiverService.updateById(pd);
		if (size>0) {
			return ResponseMessageEnum.SUCCESS.toString();
		}else {
			return ResponseMessageEnum.FAIL.toString();
		}
	}
	
	
	/**
	 * 删除收件人
	 * @param req
	 * @param rep
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteReceiver", method = RequestMethod.POST, produces = JSON_UTF8)
	@ResponseBody
	public String deleteReceriver(@RequestBody Map<String, String> map) throws Exception{
		Object result = getUser();
		if (result instanceof ResponseMessageEnum) {
			return result.toString();
		}
		PageData pd = new PageData();
		String receiver_id = map.get("receiver_id");//收件人id
		pd.put("id", receiver_id);
		int size= (int) receiverService.delete(pd);
		
		if (size>0) {
			return ResponseMessageEnum.SUCCESS.toString();
		}else {
			return ResponseMessageEnum.FAIL.toString();
		}
	}
	
	
	/**
	 * 根据mobile和create_user获取收货地址   入参:token
	 * @param req
	 * @param rep
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/listByMemberId", method = RequestMethod.POST, produces = JSON_UTF8)
	@ResponseBody
	public String listByMemberId() throws Exception{
		Object result = getUser();
		if (result instanceof ResponseMessageEnum) {
			return result.toString();
		}
		PageData pd = (PageData) result;
		List<PageData> receiverDatas = receiverService.findByMemberId(pd);
		return ResponseMessageEnum.SUCCESS.appendPageDataListToString(receiverDatas);
	}
}
