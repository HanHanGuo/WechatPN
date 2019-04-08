package com.xianguo.wechatpn.handle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xianguo.wechatpn.WechatEventListenerList;
import com.xianguo.wechatpn.WechatEventMessage;
import com.xianguo.wechatpn.enums.WechatEventType;
import com.xianguo.wechatpn.event.LocationEvent;
import com.xianguo.wechatpn.event.MenuClickEvent;
import com.xianguo.wechatpn.event.SubscribeEvent;
import com.xianguo.wechatpn.interfaces.WechatEventListener;

@Component
public class EventHandle {
	
	@Autowired(required=false)
	private WechatEventListenerList wechatEventListenerList;
	
	/**
	 * 处理事件消息
	 *
	 * @author 鲜果
	 * @param @param wechatEventMessage 消息实体
	 * @param @param xml 微信发送过来的xml消息
	 * @date 2019年4月8日
	 * @return void
	 * @throws
	 */
	public void Handle(WechatEventMessage wechatEventMessage,String xml) {
		if(wechatEventMessage instanceof LocationEvent) {
			HandleLocationEvent((LocationEvent)wechatEventMessage, xml);
		}else if(wechatEventMessage instanceof MenuClickEvent) {
			HandleMenuClickEvent((MenuClickEvent)wechatEventMessage, xml);
		}else if(wechatEventMessage instanceof SubscribeEvent) {
			if(wechatEventMessage.getEvent() == WechatEventType.SUBSCRIBE) {
				HandleSubscribeEvent((SubscribeEvent)wechatEventMessage, xml);
			}else if(wechatEventMessage.getEvent() == WechatEventType.UNSUBSCRIBE) {
				HandleUnsubscribeEvent((SubscribeEvent)wechatEventMessage, xml);
			}else if(wechatEventMessage.getEvent() == WechatEventType.SCAN) {
				HandleScanevent((SubscribeEvent)wechatEventMessage, xml);
			}
		}
	}
	
	/**
	 * 关注事件处理
	 *
	 * @author 鲜果
	 * @param @param subscribeEvent 消息实体
	 * @param @param xml 微信发送过来的消息xml
	 * @date 2019年4月8日
	 * @return void
	 * @throws
	 */
	public void HandleSubscribeEvent(SubscribeEvent subscribeEvent,String xml) {
		HandleEventByType(WechatEventType.SUBSCRIBE,subscribeEvent,xml);
	}
	
	/**
	 * 取消关注事件处理
	 *
	 * @author 鲜果
	 * @param @param subscribeEvent 消息实体
	 * @param @param xml 微信发送过来的消息xml
	 * @date 2019年4月8日
	 * @return void
	 * @throws
	 */
	public void HandleUnsubscribeEvent(SubscribeEvent subscribeEvent,String xml) {
		HandleEventByType(WechatEventType.UNSUBSCRIBE,subscribeEvent,xml);
	}
	
	/**
	 * 扫描带参数二维码事件（用户已关注时的事件推送）处理
	 *
	 * @author 鲜果
	 * @param @param subscribeEvent 消息实体
	 * @param @param xml 微信发送过来的消息xml
	 * @date 2019年4月8日
	 * @return void
	 * @throws
	 */
	public void HandleScanevent(SubscribeEvent subscribeEvent,String xml) {
		HandleEventByType(WechatEventType.SCAN,subscribeEvent,xml);
	}
	
	/**
	 * 上报地理位置事件处理
	 *
	 * @author 鲜果
	 * @param @param locationEvent 消息实体
	 * @param @param xml 微信发送过来的消息xml
	 * @date 2019年4月8日
	 * @return void
	 * @throws
	 */
	public void HandleLocationEvent(LocationEvent locationEvent,String xml) {
		HandleEventByType(WechatEventType.LOCATION,locationEvent,xml);
	}
	
	/**
	 * 自定义菜单事件处理
	 *
	 * @author 鲜果
	 * @param @param menuClickEvent 消息实体
	 * @param @param xml 微信发送过来的消息xml
	 * @date 2019年4月8日
	 * @return void
	 * @throws
	 */
	public void HandleMenuClickEvent(MenuClickEvent menuClickEvent,String xml) {
		HandleEventByType(WechatEventType.MENU_CLICK,menuClickEvent,xml);
	}
	
	/**
	 * 微信事件处理
	 *
	 * @author 鲜果
	 * @param @param wechatEventType 微信事件类型
	 * @param @param wechatEventMessage 微信事件消息实体
	 * @param @param xml 微信发送过来的xml消息
	 * @date 2019年4月8日
	 * @return void
	 * @throws
	 */
	private void HandleEventByType(WechatEventType wechatEventType,WechatEventMessage wechatEventMessage,String xml) {
		if(wechatEventListenerList == null) {
			return;
		}
		List<WechatEventListener> listeners = wechatEventListenerList.get(wechatEventType);
		if(listeners == null) {
			return;
		}
		for(WechatEventListener listener : listeners) {
			listener.Listen(wechatEventMessage, xml);
		}
	}
}
