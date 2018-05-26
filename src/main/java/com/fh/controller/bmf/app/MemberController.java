package com.fh.controller.bmf.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.fh.service.bmf.syslog.SysLogBizService;
import com.fh.util.QNUploadUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fh.common.model.ResponseMessageEnum;
import com.fh.controller.base.AppBaseController;
import com.fh.extend.logic.AccessTokenManager;
import com.fh.extend.logic.AuthCodeManager;
import com.fh.extend.util.FileUploadUtil;
import com.fh.service.bmf.member.MemberService;
import com.fh.service.bmf.receiver.ReceiverService;
import com.fh.service.bmf.sms.SmsSendService;
import com.fh.util.PageData;
import com.fh.util.Tools;
import com.fh.util.UuidUtil;

/**
 */
@Controller("AppMemberController")
@RequestMapping(value = "/app/user")
public class MemberController extends AppBaseController {

	@Resource(name = "memberService")
	private MemberService memberService;

	@Resource(name = "receiverService")
	private ReceiverService receiverService;

	@Resource(name="sysLogBizService")
	private SysLogBizService sysLogBizService;

	@Autowired
	private SmsSendService smsSendService;



	/* 登录接口 */
	/**
	 * 
	 * @param map
	 *            {"mobile":"15068865038","code":"987776"}
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/login" }, method = {RequestMethod.POST }, produces = { JSON_UTF8 })
	@ResponseBody
	public Object login(@RequestBody Map<String, String> map) throws Exception {
		String mobile = map.get("mobile");
		String code = map.get("code");
		String IDFA = map.get("IDFA");

		String strLog = "mobile=" + mobile + ",code=" + code + ",IDFA=" + IDFA;
		sysLogBizService.saveAppLog(request, "/app/user/login", strLog);

		if (StringUtils.isBlank(mobile) || StringUtils.isEmpty(code)) {
			return ResponseMessageEnum.ARGUMENT_EXCEPTION.toString();
		}

		PageData pd = new PageData();
		pd.put("MOBILE", mobile);
		
		// 判断用户是否存在
		List<PageData> list = memberService.findByMobile(pd);
		if (null == list || list.size()<1) {
			// 处理逻辑，用户不存在，注册一个账户并返回成功,登录返回成功
			PageData pdMobile = new PageData();
			pdMobile.put("MOBILE", map.get("mobile"));
			List<PageData> mobileList = memberService.findByMobile(pdMobile);
			if (mobileList.size() > 0) {
				return ResponseMessageEnum.ERROR_MOBILE_EXIST.toString();
			}
			// 获取request信息
			PageData unsrInfo = new PageData();
			if (null != IDFA && !IDFA.equals("")) {
				unsrInfo.put("IDFA", IDFA);
			}
			unsrInfo.put("MOBILE", map.get("mobile"));
			unsrInfo.put("DEL_FLAG", 1);
			unsrInfo.put("REGISTER_DATE", Tools.date2Str(new Date()));
			// 保存数据
			try {
				memberService.saveInfo(unsrInfo);
			} catch (Exception e) {
				return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
			}
		}
		
		pd = memberService.findByMobile(pd).get(0);
		// 校验手机验证码
		String cacheCode = AuthCodeManager.getInstance().getCode(mobile);
		if (!code.equals(cacheCode)) {
			if (mobile.equals("15869193819") && code.equals("15869193819")) {
				// 测试账号除外，手机验证码就等于手机号
			} else {
				return ResponseMessageEnum.ERROR_AUTH_CODE.toString();
			}
		}
		// 判断用户水是否在黑名单内
		if (pd.getInteger("DEL_FLAG") != 1) {
			return ResponseMessageEnum.ERROR_USER_IN_BLACKLIST;
		}
		String userId = pd.getLong("ID").toString();
		String token = AccessTokenManager.getInstance().putToken(userId);
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		tokenMap.put("token", token);

		return ResponseMessageEnum.SUCCESS.appendMapToString(tokenMap);
	}

	/* 登录获取验证码接口 */
	@RequestMapping(value = { "/code/{mobile}" }, method = { RequestMethod.GET }, produces = { JSON_UTF8 })
	@ResponseBody
	public Object getAuthCode(@PathVariable("mobile") String mobile)
			throws Exception {
		String strLog = "mobile=" + mobile;
		sysLogBizService.saveAppLog(request, "/app/user/code/{mobile}", strLog);

		if (StringUtils.isBlank(mobile)) {
			return ResponseMessageEnum.ARGUMENT_EXCEPTION.toString();
		}

		PageData pd = new PageData();
		pd.put("MOBILE", mobile);
//		if (memberService.findByMobile(pd).size() > 0) {
//			pd = memberService.findByMobile(pd).get(0);
//		} else {
//			return ResponseMessageEnum.ERROR_USER_NOT_EXIT.toString();
//		}

//		// 判断用户是否存在
//		if (pd == null || pd.isEmpty()) {
//			return ResponseMessageEnum.ERROR_USER_NOT_EXIT.toString();
//		}

		String code = AuthCodeManager.getInstance().putCode(mobile);
		smsSendService.sendMobileCode(code, mobile);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);

		return ResponseMessageEnum.SUCCESS.appendEmptyData().toString();
	}

	/* 登录获取验证码2接口 */
	@RequestMapping(value = { "/code/idfa" }, method = { RequestMethod.POST }, produces = { JSON_UTF8 })
	@ResponseBody
	public Object getAuthCodeIDFA(@RequestBody Map<String, String> map)
			throws Exception {
		String strLog = "mobile=" + map.get("mobile") + ",idfa=" + map.get("idfa");
		sysLogBizService.saveAppLog(request, "/app/user/code/idfa", strLog);

		if (StringUtils.isBlank(map.get("mobile"))
				|| StringUtils.isBlank(map.get("idfa"))) {
			return ResponseMessageEnum.ARGUMENT_EXCEPTION.toString();
		}
		// 判断设备是否注册
		String idfa = map.get("idfa");
		PageData pdIdfa = new PageData();
		pdIdfa.put("IDFA", idfa);
		List<PageData> dbIdfaResultList = memberService.findByIDFA(pdIdfa);

		// 判断手机号是否注册
		String mobile = map.get("mobile");
//		PageData pdMobie = new PageData();
//		pdMobie.put("MOBILE", mobile);
//		List<PageData> dbMobileResultList = memberService.findByMobile(pdMobie);
//		if (dbMobileResultList.size() == 0) {
//			return ResponseMessageEnum.ERROR_USER_NOT_EXIT.toString();
//		}

		// 生成验证码，并发送到手机
		String code = AuthCodeManager.getInstance().putCode(mobile);
		smsSendService.sendMobileCode(code, mobile);
		Map<String, Object> codeMap = new HashMap<String, Object>();
		codeMap.put("code", code);
		System.out.println("this code is " + codeMap.toString());
		/*
		 * return
		 * ResponseMessageEnum.SUCCESS.appendObject2String(codeMap).toString();
		 */
		Map<String, String> domainUrl = new HashMap<String, String>();
		QNUploadUtil qnUploader = new QNUploadUtil();
		domainUrl.put("productDomainUrl", qnUploader.getImgProductDomain());
		domainUrl.put("sceneDomainUrl", qnUploader.getImgSceneDomain());
		domainUrl.put("d3dDomainUrl", qnUploader.getImgD3dDomain());
		return ResponseMessageEnum.SUCCESS.appendObject(domainUrl).toString();
	}

	/* 个人信息接口 */
	@RequestMapping(value = { "/info" }, method = { RequestMethod.GET }, produces = { JSON_UTF8 })
	@ResponseBody
	public Object userInfo() throws Exception {
		Object result = getUser();
		if (result instanceof ResponseMessageEnum) {
			return result.toString();
		}
		PageData pd = (PageData) result;
		PageData userInfo = new PageData();
		userInfo.put("id", pd.getLong("ID"));
		userInfo.put("avatar", pd.getString("AVATAR"));
		userInfo.put("nickName", pd.getString("NICKNAME"));
		userInfo.put("mobile", pd.getString("MOBILE"));
		userInfo.put("addrProvince", pd.getString("ADDR_PROVINCE"));
		userInfo.put("addrCity", pd.getString("ADDR_CITY"));
		userInfo.put("professionSup", pd.getString("PROFESSION_SUP"));
		userInfo.put("professionSub", pd.getString("PROFESSION_SUB"));
		return ResponseMessageEnum.SUCCESS.appendObject(userInfo);
	}

	/* 上传头像接口 */
	@RequestMapping(value = "/upload/avatar")
	@ResponseBody
	public Object restSave(
			@RequestParam(value = "avatar") MultipartFile avatar,
			HttpServletRequest request) throws Exception {
		Object result = getUser();
		if (result instanceof ResponseMessageEnum) {
			return result.toString();
		}
		PageData pd = (PageData) result;
		// 上传图片
		long time = System.currentTimeMillis();
		if (avatar != null && !avatar.isEmpty()) {
			String avatarUrl = FileUploadUtil.upload(avatar, request, "avatar",
					"avatar" + time);
			System.out.println("show this " + avatarUrl);
			pd.put("AVATAR", avatarUrl);
		}
		try {
			memberService.edit(pd);
		} catch (Exception e) {
			logger.warn(e);
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}
		return ResponseMessageEnum.SUCCESS.appendEmptyData();

	}

	/**
	 * { "name": "张三", "mobile":"13666666666", "addr_province":"浙江省",
	 * "addr_city":"杭州市", "addr_county":"西湖区", "addr_detail":"蒋村街道西溪花园7-1-201",
	 * "post_code":"310000" }
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/receiver/add" }, method = { RequestMethod.POST }, produces = { JSON_UTF8 })
	@ResponseBody
	public Object addReceiver(@RequestBody Map<String, String> map)
			throws Exception {
		Object result = getUser();
		if (result instanceof ResponseMessageEnum) {
			return result.toString();
		}

		PageData pd = (PageData) result;

		String userId = pd.getString("ID");

		map.put("create_user", userId);

		PageData pageData = new PageData();
		pageData.putAll(map);
		pageData.put("create_time", Tools.date2Str(new Date()));

		try {
			receiverService.save(pageData);
		} catch (Exception e) {
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}

		return ResponseMessageEnum.SUCCESS
				.appendMapToString(new HashMap<String, Object>());
	}

	/**
	 * { "id": 1, "name": "张三", "mobile":"13666666666", "addr_province":"浙江省",
	 * "addr_city":"杭州市", "addr_county":"西湖区", "addr_detail":"蒋村街道西溪花园7-1-201",
	 * "post_code":"310000" }
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/receiver/update" }, method = { RequestMethod.POST }, produces = { JSON_UTF8 })
	@ResponseBody
	public Object updateReceiver(@RequestBody Map<String, String> map)
			throws Exception {
		Object result = getUser();
		if (result instanceof ResponseMessageEnum) {
			return result.toString();
		}

		String id = map.get("id");
		PageData pd = new PageData();
		pd.put("id", id);
		receiverService.findById(pd);

		String userId = pd.getString("ID");

		map.put("create_user", userId);

		PageData pageData = new PageData();
		pageData.putAll(map);
		pageData.put("create_time", Tools.date2Str(new Date()));

		try {
			receiverService.save(pageData);
		} catch (Exception e) {
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}

		return ResponseMessageEnum.SUCCESS
				.appendMapToString(new HashMap<String, Object>());
	}

	/**
	 * { "name": "张三", "mobile":"13666666666", "addr_province":"浙江省",
	 * "addr_city":"杭州市", "addr_county":"西湖区", "addr_detail":"蒋村街道西溪花园7-1-201",
	 * "post_code":"310000" }
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/receiver/list" }, method = { RequestMethod.GET }, produces = { JSON_UTF8 })
	@ResponseBody
	public Object listReceiver() throws Exception {
		Object result = getUser();
		if (result instanceof ResponseMessageEnum) {
			return result.toString();
		}

		PageData pd = (PageData) result;

		String userId = pd.getString("ID");
		PageData pageData = new PageData();
		pageData.put("userId", userId);

		try {
			List<PageData> pdList = receiverService.listByCondition(pageData);
			return ResponseMessageEnum.SUCCESS
					.appendPageDataListToString(pdList);
		} catch (Exception e) {
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}

	}

	/* idfa判断接口 */
	/**
	 * { "UDID": "AAAA123DSF1231DFFFVBBRT6VVD"
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	/* {"IDFA": "AAAA123DSF1231DFFFVBBRT6VVD"} */
	@RequestMapping(value = { "/idfa/exist" }, method = { RequestMethod.POST }, produces = { JSON_UTF8 })
	@ResponseBody
	public Object udidExist(@RequestBody Map<String, String> map)
			throws Exception {
		String strLog = "IDFA=" + map.get("IDFA");
		sysLogBizService.saveAppLog(request, "/app/user/idfa/exist", strLog);

		logger.info("this is udid interface");
		// 判断请求参数是否正常
		if (map.get("IDFA") == null || map.get("IDFA").isEmpty()) {
			return ResponseMessageEnum.ARGUMENT_EXCEPTION.toString();
		}

		// 获取uesrUdid
		PageData userUdid = new PageData();
		userUdid.put("IDFA", map.get("IDFA"));
		logger.info("userUdid.getString()" + userUdid.getString("IDFA"));
		// 匹配dbUdid
		List<PageData> dbIDFAList = new ArrayList<PageData>();
		try {
			dbIDFAList = memberService.findByIDFA(userUdid);
		} catch (Exception e) {
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}
		// 返回结果
		/*
		 * PageData resultMap = new PageData(); if(dbIDFAList.size() == 0){
		 * resultMap.put("idfaExist", 0);//账号不存在 resultMap.put("mobile", "");
		 * }else{
		 * if(StringUtils.isBlank(dbIDFAList.get(0).getString("NICKNAME"))){
		 * resultMap.put("idfaExist", 1);//账号存在，但是信息未完善 }else{
		 * resultMap.put("idfaExist", 2);//账号存在，信息已完善 } resultMap.put("mobile",
		 * dbIDFAList.get(0).get("MOBILE")); }
		 */
		// 返回结果
		PageData resultMap = new PageData();
		if (dbIDFAList.size() == 0) {
			resultMap.put("idfaExist", 1);// 账号不存在,可以注册
			resultMap.put("mobile", "");
		} else {
			resultMap.put("idfaExist", 2);// 账号存在，信息已完善
			resultMap.put("mobile", "");
		}

		QNUploadUtil qnUploader = new QNUploadUtil();
		resultMap.put("productDomainUrl", qnUploader.getImgProductDomain());
		resultMap.put("sceneDomainUrl", qnUploader.getImgSceneDomain());
		resultMap.put("d3dDomainUrl", qnUploader.getImgD3dDomain());

		return ResponseMessageEnum.SUCCESS.appendObject2String(resultMap);
	}

	/* 用户注册接口 */
	/**
	 * { "IDFA": "DDDD-D111-D2222-VVV-DD", "mobile":"15068865038"; "nickName":
	 * "唐大宝", "professionSup": "设计师", "professionSub": "室内设计师", "addrProvince":
	 * "浙江省", "addrCity": "杭州市" }
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	/*
	 * @RequestMapping(value = { "/register" }, method = {RequestMethod.POST },
	 * produces = {JSON_UTF8})
	 */@RequestMapping(value = { "/info/add" }, method = { RequestMethod.POST }, produces = { JSON_UTF8 })
	@ResponseBody
	public Object register(@RequestBody Map<String, String> map)
			throws Exception {
		String strLog = "IDFA=" + map.get("IDFA");
		strLog += ",mobile=" + map.get("mobile");
		strLog += ",nickName=" + map.get("nickName");
		sysLogBizService.saveAppLog(request, "/app/user/register", strLog);

		logger.info("this is register interface");
		// 获取idfa，判断该用户是否已经注册
		String IDFA = map.get("IDFA").toString();
		PageData pdIDFA = new PageData();
		pdIDFA.put("IDFA", IDFA);
		// List<PageData> existIDFAList = memberService.findByIDFA(pdIDFA);
		// if(existIDFAList.size()>0){
		// return ResponseMessageEnum.ERROR_DEVICE_REGISTERED.toString();
		// }
		// 获取手机号，判断改手机号，是否已经注册
		PageData pdMobile = new PageData();
		if (map.get("mobile").length() != 11) {
			return ResponseMessageEnum.ERROR_MOBILE_FORMAT.toString();
		}
		pdMobile.put("MOBILE", map.get("mobile"));
		List<PageData> mobileList = memberService.findByMobile(pdMobile);
		if (mobileList.size() > 0) {
			return ResponseMessageEnum.ERROR_MOBILE_EXIST.toString();
		}

		// 判断request数据是否为空
		for (String str : map.values()) {
			if (str.isEmpty()) {
				return ResponseMessageEnum.ARGUMENT_EMPTY.toString();
			}
		}
		// 获取request信息
		PageData unsrInfo = new PageData();
		unsrInfo.put("IDFA", IDFA);
		unsrInfo.put("MOBILE", map.get("mobile"));
		unsrInfo.put("NICKNAME", map.get("nickName"));
		unsrInfo.put("PROFESSION_SUP", map.get("professionSup"));
		unsrInfo.put("PROFESSION_SUB", map.get("professionSub"));
		unsrInfo.put("ADDR_PROVINCE", map.get("addrProvince"));
		unsrInfo.put("ADDR_CITY", map.get("addrCity"));
		unsrInfo.put("DEL_FLAG", 1);
		unsrInfo.put("REGISTER_DATE", Tools.date2Str(new Date()));
		// 保存数据
		try {
			memberService.saveInfo(unsrInfo);
		} catch (Exception e) {
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}
		return ResponseMessageEnum.SUCCESS.appendEmptyData();
	}

	/* 判断用户是否填写过个人信息接口 */
	/**
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/info/exist" }, method = { RequestMethod.GET }, produces = { JSON_UTF8 })
	@ResponseBody
	public Object infoExist() throws Exception {
		logger.info("this is infoExist interface");
		Object result = getUser();
		if (result instanceof ResponseMessageEnum) {
			return result.toString();
		}
		PageData pd = (PageData) result;
		// 根据用户的昵称是否为空判断是否填写过个人信息；
		// String nickName = pd.getString("NICKNAME");
		PageData infoExist = new PageData();
		if (StringUtils.isBlank(pd.getString("NICKNAME"))) {
			infoExist.put("infoExist", 0);
		} else {
			infoExist.put("infoExist", 1);
		}
		// 保存数据
		try {
			memberService.edit(pd);
		} catch (Exception e) {
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}
		return ResponseMessageEnum.SUCCESS.appendObject2String(infoExist);
	}

	/* 判断用户是否处于登录状态 */
	/**
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/login/status" }, method = { RequestMethod.GET }, produces = { JSON_UTF8 })
	@ResponseBody
	public Object loginStatus() throws Exception {
		logger.info("this is infoExist interface");
		Object result = getUser();
		if (result instanceof ResponseMessageEnum) {
			return result.toString();
		}
		return ResponseMessageEnum.SUCCESS.appendEmptyData().toString();
	}

}
