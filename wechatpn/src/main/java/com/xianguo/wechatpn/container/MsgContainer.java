package com.xianguo.wechatpn.container;

import com.xianguo.wechatpn.beans.WechatMessage;

/**
 * 消息监听容器
 * @author 鲜果
 * @date 2019年4月8日
 *
 */
public class MsgContainer {
	/**
	 * 处理消息
	 *
	 * @author 鲜果
	 * @param @param wechatMessage
	 * @param @return
	 * @date 2019年4月8日
	 * @return String
	 * @throws
	 */
	public String HandleMsg(WechatMessage wechatMessage) {
		return "";
	}
	
	public String HandleTextMsg(WechatMessage wechatMessage) {
		return "";
	}
	
	public String HandleImageMsg(WechatMessage wechatMessage) {
		return "";
	}
	
	public String HandleVoiceMsg(WechatMessage wechatMessage) {
		return "";
	}
	
	public String HandleVideoMsg(WechatMessage wechatMessage) {
		return "";
	}
	
	public String HandleShortvideoMsg(WechatMessage wechatMessage) {
		return "";
	}

	public String HandleLocationMsg(WechatMessage wechatMessage) {
		return "";
	}
	
	public String HandleLinkMsg(WechatMessage wechatMessage) {
		return "";
	}
	
}
