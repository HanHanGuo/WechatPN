package com.xianguo.wechatpn.utils;

import com.alibaba.fastjson.JSON;
import com.xianguo.wechatpn.api.TokenApi;
import com.xianguo.wechatpn.api.TokenApi.TokenResponse;
import com.xianguo.wechatpn.beans.Token;

public class AccessToken {
	

	static {
		TokenApi api = new TokenApi();
		TokenResponse token = api.execute();
		Token = token.getAccess_token();
	}
	
	public static String Token;
	
	private static Token accessToken;

	public static Token getAccessToken() {
		// 1、检查token是否有值 有值则直接返回
		if (accessToken != null) {
			return accessToken;
		}

		/**
		 * 2、token通过scoket进行操作
		 */

//		  String host = "127.0.0.1"; 
//		  int port = 55533; 
//		  Socket socket = new Socket(host,port);
//		  if(socket != null) {
//		  
//		  }
//		  return ;

		// 3、发起token请求连接获取token
		StringBuilder sb = new StringBuilder();// 传参拼接
		sb.append("grant_type=client_credential&");
		sb.append("appid=" + WechatConstants.WX_APPID + "&");
		sb.append("secret=" + WechatConstants.WX_SECRET);
		String json = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token", sb.toString());
		Token token = JSON.parseObject(json,Token.class);
		return token;
	}
}
