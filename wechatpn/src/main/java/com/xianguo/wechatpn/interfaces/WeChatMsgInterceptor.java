package com.xianguo.wechatpn.interfaces;

import com.xianguo.wechatpn.beans.WechatMessage;

/**
 * 微信消息拦截器
 * @author 鲜果
 * @date 2019年4月8日
 *
 */
public interface WeChatMsgInterceptor {
	
	/**
	 * 拦截方法
	 *
	 * @author 鲜果
	 * @param @param wechatMessage 微信消息
	 * @param @param xml 微信发送过来的xml
	 * @param @return
	 * @date 2019年4月8日
	 * @return String
	 * @throws
	 */
	public String Reply(WechatMessage wechatMessage,String xml);
	
}
