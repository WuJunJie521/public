package com.bdqn.syht.utils;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.alibaba.fastjson.JSON;

public class HttpSendPhoneUtils {

	private HttpSendPhoneUtils(){}
	
	public static String sendMessage(String telephone) throws HttpException, IOException{
		
		HttpClient client = new  HttpClient();
		
		PostMethod post = new PostMethod("https://way.jd.com/chuangxin/VerCodesms");
		
		post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
	
		//设置接口需要的参数
		//https://way.jd.com/chuangxin/VerCodesms?mobile=13568813957&content=【成都创信】验证码为：5873,欢迎注册平台！&appkey=您申请的APPKEY
		NameValuePair [] data = {
				new NameValuePair("mobile",telephone),
				new NameValuePair("content","【成都创信】验证码为：5873,欢迎注册平台！"),//"【青鸟创信】验证码为："+RandomStringUtils.randomNumeric(4)+
				new NameValuePair("appkey","d64cf571f8cea207baca0fcdfb473d4b")};
	
		post.setRequestBody(data);
		
		client.executeMethod(post);
		
		System.out.println(post.getStatusCode());//200
		
		String result = new String(post.getResponseBodyAsString().getBytes("GBK"));
		//对返回的json串解析成map集合
		Map maps = (Map) JSON.parse(result);
		for (Object map : maps.entrySet()) {
			if("code".equals(((Map.Entry)map).getKey())){
				String code = (String) ((Map.Entry)map).getValue();
				if("10000".equals(code)){
					return code;
				}else{
					throw new RuntimeException("发短信,异常~ 异常参数:" + code);
				}
			}
		}
		return null;
	}
}
