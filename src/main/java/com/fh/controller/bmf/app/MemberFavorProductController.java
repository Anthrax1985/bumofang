package com.fh.controller.bmf.app;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.fh.entity.bmf.member.MemberFavorProduct;
import com.fh.extend.logic.AccessTokenManager;
import com.fh.extend.logic.AuthCodeManager;
import com.fh.extend.util.FileUploadUtil;
import com.fh.service.bmf.member.MemberFavorProductService;
import com.fh.service.bmf.member.MemberService;
import com.fh.service.bmf.receiver.ReceiverService;
import com.fh.service.bmf.sms.SmsSendService;
import com.fh.util.PageData;
import com.fh.util.Tools;
import com.fh.util.UuidUtil;

/**
 */
@Controller("AppMemberFavorProductController")
@RequestMapping(value = "/app/user/favor/product")
public class MemberFavorProductController extends AppBaseController {

	@Resource(name = "memberService")
	private MemberService memberService;
	
	@Resource(name="memberFavorProductService")
	private MemberFavorProductService memberFavorProductService;
	
	/*用户添加商品收藏*/
	/*request:{"productId":2}*/
	@RequestMapping(value = { "/add" }, method = {RequestMethod.POST }, produces = {JSON_UTF8})
	@ResponseBody
	public Object addfavorProduct(@RequestBody MemberFavorProduct favorProReq) throws Exception{
		Object result = getUser();
		if(result instanceof ResponseMessageEnum){
			return result.toString();
		}	
		PageData userInfo = (PageData)result;
		Long memberId = userInfo.getLong("ID");
		favorProReq.setMemberId(memberId);
		//判断收已经被收藏
		List<MemberFavorProduct> dbResult = memberFavorProductService.findByCondition(favorProReq);
		if(dbResult.size() == 0){
			//未收藏
			favorProReq.setCreateUserId(memberId);
			favorProReq.setCreateTime(new Timestamp(System.currentTimeMillis()));
			memberFavorProductService.save(favorProReq);
		}else{
			//已收藏
			return ResponseMessageEnum.ERROR_REPEAT_FAVOR.toString();
		}
		return ResponseMessageEnum.SUCCESS.appendEmptyData();
	}		
	/*用户商品收藏列表*/
	@RequestMapping(value = { "/list" }, method = {RequestMethod. GET}, produces = {JSON_UTF8})
	@ResponseBody
	public Object listFavorProduct(@RequestParam(value="is_new", defaultValue="0") Long is_new) throws Exception{
		Object result = getUser();
		if(result instanceof ResponseMessageEnum){
			return result.toString();
		}
		MemberFavorProduct memberIdReq = new  MemberFavorProduct();
		PageData userInfo = (PageData)result;
		Long memberId = userInfo.getLong("ID");
		memberIdReq.setMemberId(memberId);
		try {
			List<MemberFavorProduct> favorProResList = memberFavorProductService.findByCondition(memberIdReq);
			//低版本没有的字段不输出
			if(is_new == 0){
				for(MemberFavorProduct item : favorProResList){
					item.setProductUploadTime(null);
				}
			}
			return ResponseMessageEnum.SUCCESS.appendObject(favorProResList);
		} catch (Exception e) {
			logger.warn(e);
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}			
	}		
	/*多选取消用户收藏商家店铺*/
/*	{"favorIdList":[3,4,5]}*/
	@RequestMapping(value = { "/delete" }, method = {RequestMethod.POST }, produces = {JSON_UTF8})
	@ResponseBody
	public Object multipledelete(@RequestBody Map<String, Long[]> map) throws Exception{
		Object result = getUser();
		if(result instanceof ResponseMessageEnum){
			return result.toString();
		}		
		//获取需要删除的收藏的信息id
		Long[] FavorIdList=map.get("favorIdList");
		   try{
			   memberFavorProductService.deleteBatch(FavorIdList);	   
		   }catch(Exception e){
			   return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		   }		
		return ResponseMessageEnum.SUCCESS.appendEmptyData();
	}	
	/*单选选取消用户收藏商家店铺*/
	/*	{"productId":1}*/
	@RequestMapping(value = { "/delete/single" }, method = {RequestMethod.POST }, produces = {JSON_UTF8})
	@ResponseBody
	public Object deleteSingle(@RequestBody Map<String, Long> map) throws Exception{
		Object result = getUser();
		if(result instanceof ResponseMessageEnum){
			return result.toString();
		}	
		PageData memberInfo = (PageData)result;
		Long memberId = memberInfo.getLong("ID");
		//获取需要删除产品ID
		Long productId=map.get("productId");
		MemberFavorProduct req = new  MemberFavorProduct();
		req.setMemberId(memberId);;
		req.setProductId(productId);
		List<MemberFavorProduct> dbInfo = memberFavorProductService.findByCondition(req);
		
		try{
			if(dbInfo.size()>0){
				Long favorId = dbInfo.get(0).getId();
				memberFavorProductService.delete(favorId);
			}else{
				return ResponseMessageEnum.ERROR_NOT_FAVOR.toString();
			} 
		}catch(Exception e){
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}		
		return ResponseMessageEnum.SUCCESS.appendEmptyData();
	}	
}
