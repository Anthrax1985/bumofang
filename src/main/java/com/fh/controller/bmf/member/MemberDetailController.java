package com.fh.controller.bmf.member;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.common.model.ResponseMessageEnum;
import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.bmf.chinadivision.ChinaDivision;
import com.fh.entity.bmf.datacenter.DataReqItem4Browse;
import com.fh.entity.bmf.datacenter.DataResDiffType4PieList;
import com.fh.entity.bmf.datacenter.ProductDataResTotal;
import com.fh.entity.bmf.member.MemberProfessionSub;
import com.fh.entity.bmf.member.MemberProfessionSup;
import com.fh.entity.bmf.product.Product;
import com.fh.service.bmf.chinadivision.ChinaDivisionService;
import com.fh.service.bmf.datacenter.DataStatisticsBizService;
import com.fh.service.bmf.member.MemberProfessionSubService;
import com.fh.service.bmf.member.MemberProfessionSupService;
import com.fh.service.bmf.member.MemberService;
import com.fh.service.system.user.UserService;
import com.fh.util.AppUtil;
import com.fh.util.Const;
import com.fh.util.Jurisdiction;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Tools;
import com.yunpian.sdk.util.JsonUtil;

/** 
 * 类名称：MemberController
 * 创建人：FH 
 * 创建时间：2017-03-08
 */
@Controller
@RequestMapping(value="/memberdetail")
public class MemberDetailController extends BaseController {
	
	String menuUrl = "memberdetail/list.do"; //菜单地址(权限用)
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
	
	@Resource(name="dataStatisticsBizService")
	private DataStatisticsBizService dataStatisticsBizService;
	

	
	/**
	 * 去用户详情页面
	 */
	@RequestMapping(value = "/goMemberDetail/{id}")
	public ModelAndView goMemberDetail(@PathVariable(value = "id") Long memberId) {
		logBefore(logger, "去修改Member页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData(this.getRequest());//接收表单信息
		pd.put("ID", memberId);
		System.out.println("pd id"+pd.toString());
		try {
			
			PageData memberInfo = memberService.findById(pd);	//根据ID读取
			mv.addObject("msg", "edit");
			mv.addObject("pd", memberInfo);
			//以下是统计数据
			DataReqItem4Browse req4Browse = dataStatisticsBizService.getReqParamRecomputed(pd);		
			mv.addObject("req4Browse", req4Browse);//将年份数据传回到jsp,供饼图详情使用
			//数据0-获取总年份列表  数据1-获取总浏览次数  数据2-根据请求参数判断是按月份还是按季度统计详情,用于表格数据
			ProductDataResTotal resInfo = dataStatisticsBizService.getProductDataResTotal(req4Browse);
			mv.addObject("resInfo", resInfo);
			//饼图1-用户类型图  饼图2-区域图  饼图3-颜色类型 饼图4-风格类型  饼图5-工艺类型  饼图6-材质类型  饼图7-应用类型
			DataResDiffType4PieList pieDataTypeList = dataStatisticsBizService.getPieDataTypeList(req4Browse);
			mv.addObject("memberType", JsonUtil.toJson(pieDataTypeList.getMemberType()));
			mv.addObject("regionType", JsonUtil.toJson(pieDataTypeList.getRegionType()));
			mv.addObject("colorType", JsonUtil.toJson(pieDataTypeList.getColorType()));
			mv.addObject("styleType", JsonUtil.toJson(pieDataTypeList.getStyleType()));
			mv.addObject("craftType", JsonUtil.toJson(pieDataTypeList.getCraftType()));
			mv.addObject("materialType", JsonUtil.toJson(pieDataTypeList.getMaterialType()));
			mv.addObject("applicationType", JsonUtil.toJson(pieDataTypeList.getApplicationType()));

			mv.setViewName("bmf/member/member_detail");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	

}
