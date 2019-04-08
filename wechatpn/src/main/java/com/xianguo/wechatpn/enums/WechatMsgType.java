package com.xianguo.wechatpn.enums;

/**
 * 消息类型枚举
 * @author 鲜果
 * @date 2019年4月8日
 *
 */
public enum WechatMsgType {
	TEXT("text"),IMAGE("image"),VOICE("voice"),VIDEO("video"),SHORTVIDEO("shortvideo"),LOCATION("location"),LINK("link");
	
	private final String value;
	
	private WechatMsgType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public static WechatMsgType getEnmuByKey(String key) {
		WechatMsgType[] enums = WechatMsgType.values();
		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getValue().equals(key)) {
				return enums[i];
			}
		}
		return null;
	}
}
