package com.xianguo.wechatpn.enums;

import com.xianguo.wechatpn.interfaces.EnmuDecoderInterface;

/**
 * 媒体类型
 * @author 鲜果
 * @date 2019年4月12日
 *
 */
public enum WechatMediaType implements EnmuDecoderInterface {
	
	IMAGE("image"),//图片
	VOICE("voice"),//语音
	VIDEO("video"),//视频
	THUMB("thumb");//缩略图
	
	private final String value;
	
	private WechatMediaType(String value) {
		this.value = value;
	}
	
	@Override
	public String getValue() {
		return this.value;
	}
	
	public static WechatMediaType getEnmuByKey(String key) {
		WechatMediaType[] enums = WechatMediaType.values();
		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getValue().equals(key)) {
				return enums[i];
			}
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public WechatMediaType getEnmuByKeyInterface(String key) {
		return getEnmuByKey(key);
	}
}
