package com.xianguo.wechatpn.handle;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xianguo.wechatpn.WechatMessage;
import com.xianguo.wechatpn.WechatMsgInterceptorList;
import com.xianguo.wechatpn.WechatMsgListenerList;
import com.xianguo.wechatpn.WechatReplyMsg;
import com.xianguo.wechatpn.interfaces.WeChatMsgInterceptor;
import com.xianguo.wechatpn.interfaces.WeChatMsgListener;
import com.xianguo.wechatpn.utils.WechatConstants;

/**
 * 消息监听容器
 * @author 鲜果
 * @date 2019年4月8日
 *
 */
@Component
public class MsgHandle {
	
	@Autowired(required=false)
	private WechatMsgInterceptorList weChatMsgInterceptorList;
	
	@Autowired(required=false)
	private WechatMsgListenerList weChatMsgListenerList;
	
	/**
	 * 消息处理
	 *
	 * @author 鲜果
	 * @param @param wechatMsgType 消息类型
	 * @param @param wechatMessage 消息实体
	 * @param @param xml 微信发送过来的消息xml
	 * @param @return
	 * @date 2019年4月8日
	 * @return String
	 * @throws
	 */
	public String HandleMsg(WechatMessage wechatMessage,String xml) {
		if(weChatMsgListenerList != null) {
			List<WeChatMsgListener> listeners = weChatMsgListenerList.get(wechatMessage.getMsgType());
			if(listeners != null) {
				for(WeChatMsgListener listener : listeners) {
					listener.Listen(wechatMessage, xml);
				}
			}
		}
		if(weChatMsgInterceptorList != null) {
			List<WeChatMsgInterceptor> Interceptors = weChatMsgInterceptorList.get(wechatMessage.getMsgType());
			if(Interceptors != null) {
				String nextStr = WechatConstants.WX_MSG_NEXT;
				Iterator<WeChatMsgInterceptor> iterator = Interceptors.iterator();
				while((nextStr == null || nextStr.equals(WechatConstants.WX_MSG_NEXT)) && iterator.hasNext()) {
					WeChatMsgInterceptor interceptor = iterator.next();
					nextStr = HandleReply(wechatMessage, interceptor, xml);
				}
				return nextStr;
			}
		}
		return "";
	}
	
	/**
	 * 处理微信消息回复对象，转xml操作
	 *
	 * @author 鲜果
	 * @param @param wechatMessage
	 * @param @param interceptor
	 * @param @param xml
	 * @param @return
	 * @date 2019年4月9日
	 * @return String
	 * @throws
	 */
	private String HandleReply(WechatMessage wechatMessage,WeChatMsgInterceptor interceptor,String xml){
		WechatReplyMsg replyMsg = interceptor.Reply(wechatMessage, xml);
		if(replyMsg == null) {
			return null;
		}
		return replyMsg.returnXml();
	}
}
