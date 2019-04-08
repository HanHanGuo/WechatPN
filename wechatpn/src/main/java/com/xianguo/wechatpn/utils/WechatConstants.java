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
	public void setPropertiesUtil(PropertiesUtil propertiesUtil) {
		init(propertiesUtil.getResource());
	}
	

	private void init(ResourceBundle res) {
		WX_APPID = res.getString("wx.appid");
		WX_SECRET = res.getString("wx.secret");
	}

	
	public static String WX_MSG_NEXT="~_NEXT_STEP";//拦截器下一步
	
	public static String WX_APPID;//微信公众号appid
	
	public static String WX_SECRET;//微信公众号secret
}
