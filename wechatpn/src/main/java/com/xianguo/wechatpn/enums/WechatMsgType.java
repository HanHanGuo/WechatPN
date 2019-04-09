package com.xianguo.wechatpn.enums;

/**
 * 消息类型枚举
 * @author 鲜果
 * @date 2019年4月8日
 *
 */
public enum WechatMsgType {
	TEXT("text"),//文字消息
	IMAGE("image"),//图片消息
	VOICE("voice"),//语音消息
	VIDEO("video"),//视频消息
	SHORTVIDEO("shortvideo"),//短视频消息
	LOCATION("location"),//地理位置消息
	LINK("link"),//链接消息
	EVENT("event"),//事件推送
	MUSIC("music"),//音乐消息
	NEWS("news");//图文消息
	
	
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
