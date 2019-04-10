package com.xianguo.wechatpn.enums;

import com.xianguo.wechatpn.interfaces.EnmuDecoderInterface;

/**
 * http请求类别
 * @author 鲜果
 * @date 2019年4月10日
 *
 */
public enum HttpRequestType implements EnmuDecoderInterface{
	POST("POST"),//post请求
	GET("GET");//get请求
	
	private final String value;
	
	private HttpRequestType(String value) {
		this.value = value;
	}
	
	@Override
	public String getValue() {
		return this.value;
	}
	
	public static HttpRequestType getEnmuByKey(String key) {
		HttpRequestType[] enums = HttpRequestType.values();
		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getValue().equals(key)) {
				return enums[i];
			}
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public HttpRequestType getEnmuByKeyInterface(String key) {
		return getEnmuByKey(key);
	}
	
}
