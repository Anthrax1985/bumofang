package com.fh.controller.base;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fh.common.model.ResponseMessageEnum;
import com.fh.extend.logic.AccessToken;
import com.fh.extend.logic.AccessTokenManager;
import com.fh.service.bmf.member.MemberService;
import com.fh.util.PageData;

public class AppBaseController extends BaseController{
	
	@Autowired
	protected HttpServletRequest request;
	
	@Resource(name = "memberService")
	protected MemberService memberService;
	
	protected Object getUser(){
//		String token = request.getHeader("token");
		String token = (String) request.getParameter("token");
		if(StringUtils.isBlank(token)){
			return ResponseMessageEnum.ARGUMENT_TOKEN_EMPTY;
		}
		
		AccessToken accessToken = AccessTokenManager.getInstance().getToken(token);
		if(accessToken == null){
			return ResponseMessageEnum.ARGUMENT_TOKEN_INVALID;
		}
		
		String userId = accessToken.getUserId();
		
		PageData pd = new PageData();
		pd.put("ID", userId);
		try {
			PageData rs = memberService.findById(pd);
			if(rs == null || rs.isEmpty()){
				return ResponseMessageEnum.ERROR_USER_NOT_EXIT;
			}
			//判断用户是否在黑名单内
			if(rs.getInteger("DEL_FLAG") !=1){
				return ResponseMessageEnum.ERROR_USER_IN_BLACKLIST;
			}			
			return rs;
		} catch (Exception e) {
			return ResponseMessageEnum.SERVER_SQL_ERROR;
		}
		
	}

}
