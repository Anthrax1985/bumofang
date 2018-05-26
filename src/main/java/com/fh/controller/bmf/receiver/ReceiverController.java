package com.fh.controller.bmf.receiver;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.common.model.ResponseMessageEnum;
import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.bmf.receiver.ReceiverService;
import com.fh.util.AppUtil;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.Jurisdiction;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Tools;

/**
 * 类名称：ReceiverController 创建人：FH 创建时间：2017-03-16
 */
@Controller
@RequestMapping(value = "/receiver")
public class ReceiverController extends BaseController {

	String menuUrl = "receiver/list.do"; // 菜单地址(权限用)
	@Resource(name = "receiverService")
	private ReceiverService receiverService;

	/**
	 * rest新增
	 */
	@RequestMapping(value = "/rest/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String restSave(@RequestBody Map<String, String> map)
			throws Exception {
		logBefore(logger, "REST新增Receiver");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
			return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString();
		} // 校验权限
		PageData pd = new PageData();
		pd.putAll(map);
		receiverService.save(pd);

		return ResponseMessageEnum.SUCCESS.toString();
	}
	
	/**
	 *  添加收件人的地址
	 * @param map
	 * @return  {"create_user": "94", "name": "王二", "mobile": "15632589654", "addr_detail": "浙江省 西湖区 某某",
	 *  "addr_province": "", "addr_city": "", "addr_county": ""}
	 * @throws Exception
	 */
	@RequestMapping(value = "/createReceiver", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String restReceiverSave(@RequestBody Map<String, Object> map) throws Exception {
		logBefore(logger, "REST新增Receiver");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
			return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString();
		} // 校验权限
		//验证手机号码是否正确
		String mobile = (String) map.get("mobile");
		if (!Tools.checkMobileNumber(mobile)) {
			return ResponseMessageEnum.ERROR_MOBILE_FORMAT.toString();
		}
		PageData pd = new PageData();
		pd.putAll(map);
		String post_code = (String) map.get("post_code");
		if (null == post_code || post_code.equals("")) {
			pd.put("post_code", "");// 邮编
		}
		pd.put("create_time", DateUtil.getTime());// 创建时间
		pd.put("update_time", DateUtil.getTime());// 修改时间
		receiverService.save(pd);
		
		return ResponseMessageEnum.SUCCESS.appendObject(pd.get("id"));
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete")
	public void delete(PrintWriter out) {
		logBefore(logger, "删除Receiver");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
			return;
		} // 校验权限
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			receiverService.delete(pd);
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
		logBefore(logger, "修改Receiver");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
			return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString();
		} // 校验权限
		PageData pd = new PageData();
		pd.putAll(map);
		receiverService.edit(pd);
		return ResponseMessageEnum.SUCCESS.toString();
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(Page page) {
		logBefore(logger, "列表Receiver");
		// if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		// //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData> varList = receiverService.list(page); // 列出Receiver列表
			mv.addObject("varList", varList);
			mv.setViewName("bmf/receiver/receiver_list");
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
		logBefore(logger, "去新增Receiver页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("bmf/receiver/receiver_edit");
			mv.addObject("msg", "save");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * 去修改页面
	 */
	@RequestMapping(value = "/goEdit")
	public ModelAndView goEdit() {
		logBefore(logger, "去修改Receiver页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = receiverService.findById(pd); // 根据ID读取
			mv.setViewName("bmf/receiver/receiver_edit");
			mv.addObject("msg", "edit");
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
		logBefore(logger, "批量删除Receiver");
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
				receiverService.deleteAll(ArrayDATA_IDS);
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

	/*
	 * 导出到excel
	 * 
	 * @return
	 */
	@RequestMapping(value = "/excel")
	public ModelAndView exportExcel() {
		logBefore(logger, "导出Receiver到excel");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "cha")) {
			return null;
		}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("姓名"); // 1
			titles.add("手机号"); // 2
			titles.add("收件人省份"); // 3
			titles.add("收件人城市"); // 4
			titles.add("收件人区、县"); // 5
			titles.add("收件人详细地址"); // 6
			titles.add("邮编"); // 7
			titles.add("创建用户id"); // 8
			titles.add("创建时间"); // 9
			titles.add("修改时间"); // 10
			dataMap.put("titles", titles);
			List<PageData> varOList = receiverService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for (int i = 0; i < varOList.size(); i++) {
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("name")); // 1
				vpd.put("var2", varOList.get(i).getString("mobile")); // 2
				vpd.put("var3", varOList.get(i).getString("addr_province")); // 3
				vpd.put("var4", varOList.get(i).getString("addr_city")); // 4
				vpd.put("var5", varOList.get(i).getString("addr_county")); // 5
				vpd.put("var6", varOList.get(i).getString("addr_detail")); // 6
				vpd.put("var7", varOList.get(i).getString("post_code")); // 7
				vpd.put("var8", varOList.get(i).getString("create_user")); // 8
				vpd.put("var9", varOList.get(i).getString("create_time")); // 9
				vpd.put("var10", varOList.get(i).getString("update_time")); // 10
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
