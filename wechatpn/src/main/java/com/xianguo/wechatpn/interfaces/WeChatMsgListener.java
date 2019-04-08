package com.xianguo.wechatpn.interfaces;

import com.xianguo.wechatpn.beans.WechatMessage;

/**
 * 微信消息监听器
 * @author 鲜果
 * @date 2019年4月8日
 *
 */
public interface WeChatMsgListener {
	
	/**
	 * 微信消息监听
	 *
	 * @author 鲜果
	 * @param @param wechatMessage 微信消息
	 * @param @param xml 微信推送的消息xml
	 * @date 2019年4月8日
	 * @return void
	 * @throws
	 */
	public void Listen(WechatMessage wechatMessage,String xml);
	
}
