package com.xianguo.wechatpn.enums;

public enum HttpRequestType {
	POST("POST"),//post请求
	GET("GET");//get请求
	
	private final String value;
	
	private HttpRequestType(String value) {
		this.value = value;
	}
	
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
	
}
