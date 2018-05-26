package com.fh.controller.bmf.message;

import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.common.model.ResponseMessageEnum;
import com.fh.common.model.SysLogEnum;
import com.fh.controller.base.BaseWebController;
import com.fh.service.BaseService;
import com.fh.service.bmf.message.MessageMemberService;
import com.fh.service.bmf.syslog.SysLogBizService;
import com.fh.service.system.user.UserService;
import com.fh.util.Jurisdiction;
import com.fh.util.PageData;
import com.fh.entity.bmf.message.MessageMember;
import com.fh.entity.system.User;

/** 
 * 类名称：MessageMemberController
 * 创建人：tyj
 * 创建时间：2017-08-07
 */
@Controller
@RequestMapping(value="/messagemember")
public class MessageMemberController extends BaseWebController<MessageMember> {
	
	String menuUrl = "messagemember/list.do"; //菜单地址(权限用)
	@Resource(name="messageMemberService")
	private MessageMemberService messageMemberService;
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="sysLogBizService")
	private SysLogBizService sysLogBizService;
	
	@Autowired
	protected HttpServletRequest request;
	
	@Override
	protected BaseService<MessageMember> getBaseService() {
		return messageMemberService;
	}
	@Override
	protected String getPackageName() {
		return "message";
	}
	@Override
	protected String getObjectName() {
		return "MessageMember";
	}
	/**
	 * RESTFUL 保存留言回复
	 */
	@RequestMapping(value = "/editReply", method = RequestMethod.POST, produces = { JSON_UTF8 })
	@ResponseBody
	public String edit(@RequestBody MessageMember entity) throws Exception {
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
			return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString();
		} // 校验权限
		logBefore(logger, "修改" + getObjectName());
		//重组编辑数据
		entity.setReplyTime(new Timestamp(System.currentTimeMillis()));
		entity.setReplierId(getSessionUserId());
		PageData userIdpd = new PageData();
		userIdpd.put("USER_ID", getSessionUserId());
		System.out.println("getCurrentUser()"+getCurrentUser());
		PageData userInfo = userService.findByUserId(userIdpd);
		entity.setReplierName(userInfo.getString("NAME"));
		entity.setReplierAvatar(userInfo.getString("AVATAR"));
		entity.setStatus(new Integer(1));
		try {
			getBaseService().edit(entity);
			//日志
			MessageMember messageInfo = messageMemberService.findById(entity.getId());
			User user = getCurrentUser();
			sysLogBizService.saveLog(request, user,
					SysLogEnum.MEMBER_MESSAGE_REPLY.getName(),"回复会员("+messageInfo.getMemberName()+")的留言");
		} catch (Exception e) {
			logger.warn(e.toString(), e);
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}

		return ResponseMessageEnum.SUCCESS.toString();
	}
	
}
