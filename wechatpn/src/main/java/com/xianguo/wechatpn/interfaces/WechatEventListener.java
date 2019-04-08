package com.xianguo.wechatpn.interfaces;

import com.xianguo.wechatpn.WechatEventMessage;

/**
 * 微信事件监听器
 * @author 鲜果
 * @date 2019年4月8日
 *
 */
public interface WechatEventListener{
	
	/**
	 * 监听器
	 *
	 * @author 鲜果
	 * @param @param wechatEventMessage 监听对象实体
	 * @param @param xml 微信发送过来的xml信息
	 * @date 2019年4月8日
	 * @return void
	 * @throws
	 */
	public void Listen(WechatEventMessage wechatEventMessage,String xml);
}
