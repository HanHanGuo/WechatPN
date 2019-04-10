package com.xianguo.wechatpn.handle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xianguo.wechatpn.WechatEventListenerList;
import com.xianguo.wechatpn.WechatEventMessage;
import com.xianguo.wechatpn.interfaces.WechatEventListener;

/**
 * 微信事件处理器
 * @author 鲜果
 * @date 2019年4月10日
 *
 */
@Component
public class EventHandle {
	
	@Autowired(required=false)
	private WechatEventListenerList wechatEventListenerList;
	
	/**
	 * 处理监听
	 *
	 * @author 鲜果
	 * @param @param wechatEventMessage
	 * @param @param xml
	 * @date 2019年4月10日
	 * @return void
	 * @throws
	 */
	public void HandleEvent(WechatEventMessage wechatEventMessage,String xml) {
		if(wechatEventListenerList == null) {
			return;
		}
		List<WechatEventListener> listeners = wechatEventListenerList.get(wechatEventMessage.getEvent());
		if(listeners == null) {
			return;
		}
		for(WechatEventListener listener : listeners) {
			listener.Listen(wechatEventMessage, xml);
		}
	}
}
