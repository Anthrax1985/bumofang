package com.fh.service.bmf.sms;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fh.common.sms.YunPianSmsUtil;

@Service
public class SmsSendService {
	private static final String API_KEY = "fa254da0db75fa4c8713652b40e907fc";
	private static final String MESSAGE_CONTENT_FORMAT = "【布魔方】您的验证码是${code}。如非本人操作，请忽略本短信";
	
	
	public void sendMobileCode(String code,String mobile){
		String content = MESSAGE_CONTENT_FORMAT.replace("${code}", code);
		try {
			YunPianSmsUtil.sendSms(API_KEY, content, mobile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
