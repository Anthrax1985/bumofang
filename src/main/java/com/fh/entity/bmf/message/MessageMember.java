package com.fh.entity.bmf.message;

import com.fh.entity.BaseEntity;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/** 
 * 类名称：MessageMember
 * 创建人：tyj
 * 创建时间：2017-08-07
 */

public class MessageMember extends BaseEntity {
	
	private Long id;
	private Long memberId; // 用户id
	private String memberName; // 用户名称
	private String memberAvatar; // 用户头像
	private String contentInfo; // 用户留言
	private Timestamp contentTime; // 留言时间
	private String replyInfo; // 回复内容
	private Timestamp replyTime; // 回复内容
	private Long replierId; // 回复人id
	private String replierName; // 回复人名称
	private String replierAvatar; // 回复人头像
	private Integer status; // 留言状态(0待回复1已回复)

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMemberId() {
		return this.memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return this.memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberAvatar() {
		return this.memberAvatar;
	}

	public void setMemberAvatar(String memberAvatar) {
		this.memberAvatar = memberAvatar;
	}
	public String getContentInfo() {
		return this.contentInfo;
	}

	public void setContentInfo(String contentInfo) {
		this.contentInfo = contentInfo;
	}
	public Timestamp getContentTime() {
		return this.contentTime;
	}

	public void setContentTime(Timestamp contentTime) {
		this.contentTime = contentTime;
	}
	public String getReplyInfo() {
		return this.replyInfo;
	}

	public void setReplyInfo(String replyInfo) {
		this.replyInfo = replyInfo;
	}
	public Timestamp getReplyTime() {
		return this.replyTime;
	}

	public void setReplyTime(Timestamp replyTime) {
		this.replyTime = replyTime;
	}
	public Long getReplierId() {
		return this.replierId;
	}

	public void setReplierId(Long replierId) {
		this.replierId = replierId;
	}
	public String getReplierName() {
		return this.replierName;
	}

	public void setReplierName(String replierName) {
		this.replierName = replierName;
	}
	public String getReplierAvatar() {
		return this.replierAvatar;
	}

	public void setReplierAvatar(String replierAvatar) {
		this.replierAvatar = replierAvatar;
	}
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	//后台留言列表留言时间格式化
	public  String getContentTime4BackStage() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm");  
		String dateInfo = format.format(contentTime);
		return dateInfo;
	}
	//后台留言状态文案化
	public  String getContentStatus4BackStage() {
		String statusInfo = null;
		if(status == 0){
			statusInfo = "未回复";
		}else{
			statusInfo = "已回复";
		}
		return statusInfo;
	}

}
