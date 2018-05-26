package com.fh.controller.bmf.member;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.common.model.ResponseMessageEnum;
import com.fh.common.model.SysLogEnum;
import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.bmf.chinadivision.ChinaDivision;
import com.fh.entity.bmf.member.MemberProfessionSub;
import com.fh.entity.bmf.member.MemberProfessionSup;
import com.fh.entity.bmf.product.Product;
import com.fh.entity.system.User;
import com.fh.service.bmf.chinadivision.ChinaDivisionService;
import com.fh.service.bmf.member.MemberProfessionSubService;
import com.fh.service.bmf.member.MemberProfessionSupService;
import com.fh.service.bmf.member.MemberService;
import com.fh.service.bmf.syslog.SysLogBizService;
import com.fh.service.system.user.UserService;
import com.fh.util.AppUtil;
import com.fh.util.Const;
import com.fh.util.Jurisdiction;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Tools;

/** 
 * 类名称：MemberController
 * 创建人：FH 
 * 创建时间：2017-03-08
 */
@Controller
@RequestMapping(value="/member")
public class MemberController extends BaseController {
	
	String menuUrl = "member/list.do"; //菜单地址(权限用)
	@Resource(name="memberService")
	private MemberService memberService;
	
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
	
	@Autowired
	protected HttpServletRequest request;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增Member");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		//pd.put("ID", this.get32UUID());	//主键
		pd.put("REGISTER_DATE", Tools.date2Str(new Date()));	//注册时间
		pd.put("UPDATE_DATE", Tools.date2Str(new Date()));	//更新时间
		pd.put("LAST_LOGIN_DATE", Tools.date2Str(new Date()));	//最近登录时间
		pd.put("LAST_LOGIN_IP", "");	//最近登录IP
		memberService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * rest新增
	 */
	@RequestMapping(value="/rest/save" , method=RequestMethod.POST  ,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public String restSave(@RequestBody Map<String,String> map) throws Exception{
		logBefore(logger, "REST新增Member");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add"))
			{return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString();} //校验权限
		PageData pd = new PageData();
		pd.putAll(map);
		pd.put("ID", this.get32UUID());	//主键
		pd.put("REGISTER_DATE", Tools.date2Str(new Date()));	//注册时间
		pd.put("UPDATE_DATE", Tools.date2Str(new Date()));	//更新时间
		pd.put("LAST_LOGIN_DATE", Tools.date2Str(new Date()));	//最近登录时间
		pd.put("LAST_LOGIN_IP", "");	//最近登录IP
		memberService.save(pd);
		
		return ResponseMessageEnum.SUCCESS.toString();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Member");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			memberService.delete(pd);
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}		
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, "修改Member");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit"))
			{return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData dbUser = memberService.findById(pd);
		memberService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * RESTFUL修改
	 */
	@RequestMapping(value="/rest/edit" , method=RequestMethod.POST  ,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public String restEdit(@RequestBody Map<String,String> map) throws Exception{
		logBefore(logger, "修改Member");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit"))
			{return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString();} //校验权限
		PageData pd = new PageData();
		pd.putAll(map);
		int chosenType = Integer.valueOf(String.valueOf(map.get("chosenType")));
		//当职业选其它时，进行特殊处理  职业选择类型 chosenType 0没做选择 1下拉选择2手动输入(其它)
		if(chosenType == 2){
			pd.put("PROFESSION_SUP", "其它");
			pd.put("PROFESSION_SUB", map.get("PROFESSION_SUB1"));
		}
		try {
			memberService.edit(pd);
			//日志
			PageData memberId = new PageData();
			memberId.put("ID", pd.getLong("ID"));
			PageData memberInfo = memberService.findById(memberId);
			User user = getCurrentUser();
			sysLogBizService.saveLog(request, user,
					SysLogEnum.MEMBER_EDIT.getName(), SysLogEnum.MEMBER_EDIT.getContent()+":"+memberInfo.getString("NICKNAME"));
			return ResponseMessageEnum.SUCCESS.toString();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.toString(), e);
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}

	}
	

	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Member");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			//一级类型列表，用于下拉搜索
			List<MemberProfessionSup> professionSupList = memberProfessionSupService.listAll();
			mv.addObject("professionSupList", professionSupList);
			//省份列表，用于下拉搜索
			ChinaDivision reqInfo = new ChinaDivision();
			reqInfo.setDivisionLevel(new Long(1));
			List<ChinaDivision> provinceList = chinaDivisionService.findByCondition(reqInfo);
			mv.addObject("provinceList", provinceList);
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = memberService.list(page);	//列出Member列表
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
			mv.addObject("isTestMode", AppUtil.isTestMode);
			mv.setViewName("bmf/member/member_list");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}


	/**
	 * 列表
	 */
	@RequestMapping(value="/listForCart")
	public ModelAndView listForCart(Page page){
		logBefore(logger, "列表Member");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			//一级类型列表，用于下拉搜索
			List<MemberProfessionSup> professionSupList = memberProfessionSupService.listAll();
			mv.addObject("professionSupList", professionSupList);
			//省份列表，用于下拉搜索
			ChinaDivision reqInfo = new ChinaDivision();
			reqInfo.setDivisionLevel(new Long(1));
			List<ChinaDivision> provinceList = chinaDivisionService.findByCondition(reqInfo);
			mv.addObject("provinceList", provinceList);
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = memberService.list(page);	//列出Member列表
			mv.addObject("varList", varList);
			mv.setViewName("bmf/member/member_list");
			mv.addObject("pd", pd);
			mv.addObject("listForCart", true);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	@RequestMapping(value="/rest/list", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<PageData> restList(Page page){
		logBefore(logger, "列表Member");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		List<PageData>	varList = new ArrayList<PageData>();
		PageData pd = new PageData();
		try{
			//一级类型列表，用于下拉搜索
			List<MemberProfessionSup> professionSupList = memberProfessionSupService.listAll();
			//mv.addObject("professionSupList", professionSupList);
			//省份列表，用于下拉搜索
			ChinaDivision reqInfo = new ChinaDivision();
			reqInfo.setDivisionLevel(new Long(1));
			List<ChinaDivision> provinceList = chinaDivisionService.findByCondition(reqInfo);
			//mv.addObject("provinceList", provinceList);
			pd = this.getPageData();
			page.setPd(pd);
			varList = memberService.list(page);	//列出Member列表
			
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return varList;
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(){
		logBefore(logger, "去新增Member页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("bmf/member/member_register");
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
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit(){
		logBefore(logger, "去修改Member页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			//获取全部省市信息
			ChinaDivision reqInfo = new ChinaDivision();
			reqInfo.setDivisionLevel(new Long(1));
			List<ChinaDivision> provinceList = chinaDivisionService.findByCondition(reqInfo);
			mv.addObject("provinceList", provinceList);
			//获取一级职业信息
			List<MemberProfessionSup> professionSupList = memberProfessionSupService.listAll();
			mv.addObject("professionSupList", professionSupList);
			pd = memberService.findById(pd);	//根据ID读取
			//补充职业选择方式  职业选择类型 chosenType 0没做选择 1下拉选择2手动输入(其它)
			int chosenType = 0; 
			if(pd.getString("PROFESSION_SUP") != null && pd.getString("PROFESSION_SUP").length()>0){
				String PROFESSION_SUP = pd.getString("PROFESSION_SUP");
				if(PROFESSION_SUP.equals("其它")){
					chosenType = 2;
				}else{
					chosenType = 1;
				}
			}
			pd.put("chosenType", chosenType);
			mv.setViewName("bmf/member/member_edit");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 去拉黑页面
	 */
	@RequestMapping(value="/goBlackList")
	public ModelAndView goBlackList(){
		logBefore(logger, "去拉黑Member页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = memberService.findById(pd);	//根据ID读取	
			mv.setViewName("bmf/member/member_toblacklist");
			mv.addObject("msg", "toblacklist");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() {
		logBefore(logger, "批量删除Member");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				memberService.deleteAll(ArrayDATA_IDS);
				pd.put("msg", "ok");
			}else{
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
	 * @return
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel(){
		logBefore(logger, "导出Member到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("编号");	//1
			titles.add("用户名");	//2
			titles.add("密码");	//3
			titles.add("昵称");	//4
			titles.add("头像");	//5
			titles.add("手机号");	//6
			titles.add("职业");	//7
			titles.add("默认区域");	//8
			titles.add("默认地址");	//9
			titles.add("真实姓名");	//10
			titles.add("身份证号");	//11
			titles.add("银行卡号");	//12
			titles.add("注册时间");	//13
			titles.add("更新时间");	//14
			titles.add("最近登录时间");	//15
			titles.add("最近登录IP");	//16
			titles.add("状态");	//17
			titles.add("三方登录，微信ID");	//18
			titles.add("三方登录，QQID");	//19
			titles.add("三方登录，新浪微博ID");	//20
			dataMap.put("titles", titles);
			List<PageData> varOList = memberService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("NUMBER"));	//1
				vpd.put("var2", varOList.get(i).getString("USERNAME"));	//2
				vpd.put("var3", varOList.get(i).getString("PASSWORD"));	//3
				vpd.put("var4", varOList.get(i).getString("NICKNAME"));	//4
				vpd.put("var5", varOList.get(i).getString("AVATAR"));	//5
				vpd.put("var6", varOList.get(i).getString("MOBILE"));	//6
				vpd.put("var7", varOList.get(i).getString("PROFESSION"));	//7
				vpd.put("var8", varOList.get(i).getString("DEFAULT_AREA"));	//8
				vpd.put("var9", varOList.get(i).getString("DEFAULT_ADDRESS"));	//9
				vpd.put("var10", varOList.get(i).getString("REALNAME"));	//10
				vpd.put("var11", varOList.get(i).getString("ID_CARD"));	//11
				vpd.put("var12", varOList.get(i).getString("BANK_CARD"));	//12
				vpd.put("var13", varOList.get(i).getString("REGISTER_DATE"));	//13
				vpd.put("var14", varOList.get(i).getString("UPDATE_DATE"));	//14
				vpd.put("var15", varOList.get(i).getString("LAST_LOGIN_DATE"));	//15
				vpd.put("var16", varOList.get(i).getString("LAST_LOGIN_IP"));	//16
				vpd.put("var17", varOList.get(i).getString("STATUS"));	//17
				vpd.put("var18", varOList.get(i).getString("WECHAT_ID"));	//18
				vpd.put("var19", varOList.get(i).getString("QQ_ID"));	//19
				vpd.put("var20", varOList.get(i).getString("WEIBO_ID"));	//20
				varList.add(vpd);
			}
			dataMap.put("varList", varList);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv,dataMap);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}


	
	/**
	 * 通过一级职业选择二级职业
	 */
/*		data={divisionName:addrProvince}*/
	@RequestMapping(value="/rest/goSelectProfessionSub" , method=RequestMethod.POST  ,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public String goSelectProfessionSub(@RequestBody MemberProfessionSub reqInfo) throws Exception{
		logBefore(logger, "展示二级职业");	
		logger.info("reqInfo is "+ reqInfo.toString());
		List<MemberProfessionSub> professionSubList = memberProfessionSubService.findByCondition(reqInfo);
		return ResponseMessageEnum.SUCCESS.appendObject(professionSubList);
	}

	
	/**
	 * RESTFUL拉黑用户
	 */
	@RequestMapping(value="/rest/toblacklist" , method=RequestMethod.POST  ,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public String restToblacklist(@RequestBody Map<String,String> map) throws Exception{
		logBefore(logger, "拉黑Member");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit"))
		{return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString();} //校验权限
		PageData pd = new PageData();
		pd.putAll(map);
		pd.put("DEL_FLAG", 2);//拉黑后待激活，删除标记（1正常，2黑名单，3待审核）	
		Long userId = getSessionUserId();
		//获取用户名称和角色
		PageData userReq = new PageData();
		userReq.put("USER_ID", userId);
		PageData userInfo = userService.findForVO(userReq);
		String userResInfo = userInfo.getString("USERNAME")+"("+userInfo.getString("ROLE_NAME")+")";
		pd.put("UPDATE_PERSON", userResInfo);	
		pd.put("UPDATE_TIME",new Timestamp(System.currentTimeMillis()));//时间	
		memberService.edit(pd);
		//日志
		PageData memberId = new PageData();
		memberId.put("ID", pd.getLong("ID"));
		PageData memberInfo = memberService.findById(memberId);
		User user = getCurrentUser();
		sysLogBizService.saveLog(request, user,
				SysLogEnum.MEMBER_TO_BLACK.getName(), SysLogEnum.MEMBER_TO_BLACK.getContent()+":"+memberInfo.getString("NICKNAME"));
		return ResponseMessageEnum.SUCCESS.toString();
	}
	//判断手机号是否应被注册
	/*{"MOBILE":"15068865038"}*/
	@RequestMapping(value="/judgeMobile" , method=RequestMethod.POST  ,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object judgeMobile(@RequestBody Map<String,String>map) throws Exception{
		PageData req =new PageData();
		req.put("MOBILE", map.get("MOBILE"));
		req.put("ID", map.get("ID"));
		logBefore(logger, "判断手机号是否应被注册");	
		logger.info("reqInfo is "+ req.toString());
		List<PageData> userList = memberService.findByMobile(req);
		PageData thisMemberInfo = memberService.findById(req);
		String lastMobile = thisMemberInfo.getString("MOBILE");
		System.out.println("lastMobile"+lastMobile);
		Boolean res = false;
		if(userList.size()>0){
			res = true;
			//如果新的手机号就是这个ID的原手机号，则表示手机号没有变动
			for(PageData cycle :userList){
				if(cycle.getString("MOBILE").equals(lastMobile)){
					res = false;
					break;
				}				
			}
		}
		return ResponseMessageEnum.SUCCESS.appendObject(res);
	}
}
