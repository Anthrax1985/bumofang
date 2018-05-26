package com.fh.controller.bmf.app;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.common.model.ResponseMessageEnum;
import com.fh.controller.base.AppBaseController;
import com.fh.entity.bmf.message.MessageMember;
import com.fh.entity.bmf.message.MessageSystem;
import com.fh.entity.bmf.message.MessageSystemDeleteRecord;
import com.fh.service.bmf.member.MemberService;
import com.fh.service.bmf.message.MessageMemberService;
import com.fh.service.bmf.message.MessageSystemDeleteRecordService;
import com.fh.service.bmf.message.MessageSystemService;
import com.fh.util.PageData;

/**
 */
@Controller("AppMessageController")
@RequestMapping(value = "/app/user/message")
public class MessageController extends AppBaseController {

	@Resource(name = "memberService")
	private MemberService memberService;
	
	@Resource(name="messageMemberService")
	private MessageMemberService messageMemberService;
	
	@Resource(name="messageSystemService")
	private MessageSystemService messageSystemService;
	
	@Resource(name="messageSystemDeleteRecordService")
	private MessageSystemDeleteRecordService messageSystemDeleteRecordService;
	
	
	/*用户添加留言接口*/
	/**
	 * 
	 * @param map  {"contentInfo":"请问，产品库何时更新？"}
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/member/add" }, method = {RequestMethod.POST }, produces = {JSON_UTF8})
	@ResponseBody
	public Object addContent(@RequestBody Map<String, String> map) throws Exception {
		Object result = getUser();
		if(result instanceof ResponseMessageEnum){
			return result.toString();
		}
		PageData userInfo = (PageData)result;
		//判断请求参数是否为空
		if(map.get("contentInfo") == null ||map.get("contentInfo").isEmpty()){
			return ResponseMessageEnum.ARGUMENT_EMPTY.toString();
		}
		//获取并组装数据
		MessageMember saveInfo= new MessageMember();
		saveInfo.setMemberId(userInfo.getLong("ID"));
		saveInfo.setMemberName(userInfo.getString("NICKNAME"));
		saveInfo.setMemberAvatar(userInfo.getString("AVATAR"));
		saveInfo.setContentInfo(map.get("contentInfo"));
		saveInfo.setContentTime(new Timestamp(System.currentTimeMillis()));
		saveInfo.setStatus(new Integer(0));//0待回复
		saveInfo.setDelFlag(new Integer(0));//0未删除
		saveInfo.setCreateUserId(userInfo.getLong("ID"));
		saveInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
		try {
			messageMemberService.save(saveInfo);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}
		
		return ResponseMessageEnum.SUCCESS.appendEmptyData();
	}
	
	/*用户留言列表接口*/
	/**
	 * 
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/member/list" }, method = {RequestMethod.GET }, produces = {JSON_UTF8})
	@ResponseBody
	public Object contentList() throws Exception {
		Object result = getUser();
		if(result instanceof ResponseMessageEnum){
			return result.toString();
		}
		PageData userInfo = (PageData)result;
		Long memberId = userInfo.getLong("ID");
		MessageMember req = new MessageMember();
		req.setMemberId(memberId);
		List<MessageMember>  resInfoList = new ArrayList<MessageMember>();
		try {
			resInfoList = messageMemberService.findByCondition(req);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}
		
		return ResponseMessageEnum.SUCCESS.appendObject(resInfoList);
	}
	/*平台信息列表接口*/
	/**
	 * 
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/system/list" }, method = {RequestMethod.GET }, produces = {JSON_UTF8})
	@ResponseBody
	public Object systemList() throws Exception {
		Object result = getUser();
		if(result instanceof ResponseMessageEnum){
			return result.toString();
		}
		PageData userInfo = (PageData)result;
		Long memberId = userInfo.getLong("ID");
		try {
			List<MessageSystem> messageInfoList = messageSystemService.listForMember(memberId);
			List<MessageSystem> messageResList = new ArrayList<MessageSystem>();
			for(MessageSystem cycle : messageInfoList){
				MessageSystem message = new MessageSystem();
				message.setId(cycle.getId());
				message.setReleaseTitle(cycle.getReleaseTitle());
				message.setReleaseInfo(cycle.getReleaseInfo());
				message.setAuditTime(cycle.getAuditTime());
				messageResList.add(message);
			}
			return ResponseMessageEnum.SUCCESS.appendObject(messageResList);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}
	}
		/*删除平台信息接口*/
		@RequestMapping(value = { "/system/delete"}, method = {RequestMethod.GET }, produces = {JSON_UTF8})
		@ResponseBody
		public Object deleteSystem(@RequestParam(value="messageId") Long messageId) throws Exception {
			Object result = getUser();
			if(result instanceof ResponseMessageEnum){
				return result.toString();
			}
			PageData userInfo = (PageData)result;
			Long memberId = userInfo.getLong("ID");
			MessageSystemDeleteRecord saveInfo = new MessageSystemDeleteRecord();
			saveInfo.setMemberId(memberId);
			saveInfo.setMessageId(messageId);
			saveInfo.setDelFlag(new Integer(1));
			saveInfo.setCreateUserId(memberId);
			saveInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
			try {
				messageSystemDeleteRecordService.save(saveInfo);
				return ResponseMessageEnum.SUCCESS.appendEmptyData().toString();
			} catch (Exception e) {
				// TODO: handle exception
				return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
			}
	}
	
}