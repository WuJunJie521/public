package com.bdqn.syht.mq;

import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.stereotype.Service;

import com.bdqn.syht.utils.HttpSendPhoneUtils;

@Service("smsConsumer")
public class SmsConsumer implements MessageListener{

	@Override
	public void onMessage(Message message) {
		MapMessage mapMessage = (MapMessage) message;
		
		//调用发短信
		try {
			String result = HttpSendPhoneUtils.sendMessage(mapMessage.getString("telephone"));
			if("10000".equals(result)){
				//发送成功
				System.out.println("发送短信成功,手机号："
						+ mapMessage.getString("telephone") + "，验证码："
						+ mapMessage.getString("code"));
				System.out.println(mapMessage.getString("msg"));
			}else{
				//发送失败
				throw new RuntimeException("短信发送失败, 信息码：" + result);
			}
		} catch (IOException | JMSException e) {
			e.printStackTrace();
		}
	}
}
