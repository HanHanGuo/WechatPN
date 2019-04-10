package com.xianguo.wechatpn.utils;

import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xianguo.config.PropertiesUtil;

@Component
public class WechatConstants {
	
	@Autowired
	private WechatConstants constants;
	
	@Autowired
	@SuppressWarnings("unused")
	public void setPropertiesUtil(PropertiesUtil propertiesUtil) {
		init(propertiesUtil.getResource());
	}
	

	private void init(ResourceBundle res) {
		WX_APPID = res.getString("wx.appid");
		WX_SECRET = res.getString("wx.secret");
		WX_TOKEN = res.getString("wx.token");
		WX_TOKEN_HOST = res.getString("wx.tokenHost");
		WX_TOKEN_PROT = Integer.parseInt(res.getString("wx.tokenProt"));
		WX_TOKEN_KEY = res.getString("wx.tokenKey");
	}

	
	public static String WX_MSG_NEXT="~_NEXT_STEP";//拦截器下一步
	
	public static String WX_APPID;//微信公众号appid
	
	public static String WX_TOKEN;//微信token
	
	public static String WX_ACCESS_TOKEN;//微信token
	
	public static String WX_SECRET;//微信公众号secret
	
	public static String WX_TOKEN_HOST;//微信token socket地址
	
	public static int WX_TOKEN_PROT;//微信token socket端口
	
	public static String WX_TOKEN_KEY;//微信分布式TOKEN秘钥
}
