package com.fh.entity.bmf.message;

import com.fh.entity.BaseEntity;
import java.sql.Timestamp;

/** 
 * 类名称：MessageSystemDeleteRecord
 * 创建人：tyj
 * 创建时间：2017-08-07
 */

public class MessageSystemDeleteRecord extends BaseEntity {
	
	private Long id;
	private Long memberId; // 用户id
	private Long messageId; // 消息id
	private Integer delFlag; // 删除标记(1删除)

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
	public Long getMessageId() {
		return this.messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
	public Integer getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

}
