package com.hzy.VCode.sysmanage.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 
 *	获取短信验证码类
 *	GetPhoneMessage
 *	@author houzhiyang
 *	@data 2017-7-12 5:36:06
 *	@version 1.0.0
 */

public class GetPhoneMessage {
	//用户ID
	public static final String ACCOUNT_SID = "185c2d932a684bf184186ecd21f5f6fa";
	//密钥
	public static final String AUTH_TOKEN = "d6de65f293814b3fb149df228ce434fc";
	//请求地址前半部分
	public static final String BASE_URL = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";
	//随机数
	public static String randNum = RandomUtil.getRandom();
	//语音验证码
	public static String verifyCode = randNum;
	//短信内容
	public static String smsContent = "【洋仔未来科技】您的验证码为："+randNum+"，打死都不要告诉别人哦！";
	/**
	 * 获取短信验证码
	 * @param to
	 * @return
	 */
	public static String getResult(String to){
		String args = QueryUtil.qureyArguments(ACCOUNT_SID, AUTH_TOKEN, smsContent, to);
		System.out.println(args);
		OutputStreamWriter out = null;
		InputStream in = null;
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		try {
			URL url = new URL(BASE_URL);
			URLConnection connection = url.openConnection();//打开链接
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setConnectTimeout(5000);//设置请求链接超时时间
			connection.setReadTimeout(10000);//设置读取结果超时
			//提交数据
			out = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
			out.write(args);
			out.flush();
			//读取返回数据
			
			in = connection.getInputStream();
			br = new BufferedReader(new InputStreamReader(in));
			String line = "";
			while((line =br.readLine())!= null){
				sb.append(line);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println("验证码："+randNum);
		System.out.println(getResult("18780620516"));
	}
}
