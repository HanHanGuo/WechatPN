package com.xianguo.wechatpn.handle;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xianguo.wechatpn.WechatMessage;
import com.xianguo.wechatpn.WechatMsgInterceptorList;
import com.xianguo.wechatpn.WechatMsgListenerList;
import com.xianguo.wechatpn.WechatReplyMsg;
import com.xianguo.wechatpn.enums.WechatMsgType;
import com.xianguo.wechatpn.interfaces.WeChatMsgInterceptor;
import com.xianguo.wechatpn.interfaces.WeChatMsgListener;
import com.xianguo.wechatpn.msg.ImageMsg;
import com.xianguo.wechatpn.msg.LinkMsg;
import com.xianguo.wechatpn.msg.LocationMsg;
import com.xianguo.wechatpn.msg.ShortvideoMsg;
import com.xianguo.wechatpn.msg.TextMsg;
import com.xianguo.wechatpn.msg.VideoMsg;
import com.xianguo.wechatpn.msg.VoiceMsg;
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
	 * 处理消息
	 *
	 * @author 鲜果
	 * @param @param wechatMessage 微信消息处理
	 * @param @return
	 * @date 2019年4月8日
	 * @return String
	 * @throws
	 */
	public String HandleMsg(WechatMessage wechatMessage,String xml) {
		if(wechatMessage instanceof TextMsg) {
			return HandleTextMsg((TextMsg)wechatMessage,xml);
		}else if(wechatMessage instanceof ImageMsg) {
			return HandleImageMsg((ImageMsg)wechatMessage,xml);
		}else if(wechatMessage instanceof VoiceMsg) {
			return HandleVoiceMsg((VoiceMsg)wechatMessage,xml);
		}else if(wechatMessage instanceof VideoMsg) {
			return HandleVideoMsg((VideoMsg)wechatMessage,xml);
		}else if(wechatMessage instanceof ShortvideoMsg) {
			return HandleShortvideoMsg((ShortvideoMsg)wechatMessage,xml);
		}else if(wechatMessage instanceof LocationMsg) {
			return HandleLocationMsg((LocationMsg)wechatMessage,xml);
		}else if(wechatMessage instanceof LinkMsg) {
			return HandleLinkMsg((LinkMsg)wechatMessage,xml);
		}
		return "";
	}
	
	/**
	 * 处理文字消息
	 *
	 * @author 鲜果
	 * @param @param textMsg 文字消息
	 * @param @return
	 * @date 2019年4月8日
	 * @return String
	 * @throws
	 */
	public String HandleTextMsg(TextMsg textMsg,String xml) {
		return HandleMsgByType(WechatMsgType.TEXT,textMsg,xml);
	}
	
	/**
	 * 处理图片消息
	 *
	 * @author 鲜果
	 * @param @param imageMsg 图片消息
	 * @param @return
	 * @date 2019年4月8日
	 * @return String
	 * @throws
	 */
	public String HandleImageMsg(ImageMsg imageMsg,String xml) {
		return HandleMsgByType(WechatMsgType.IMAGE,imageMsg,xml);
	}
	
	/**
	 * 处理语音消息
	 *
	 * @author 鲜果
	 * @param @param voiceMsg 语音消息
	 * @param @return
	 * @date 2019年4月8日
	 * @return String
	 * @throws
	 */
	public String HandleVoiceMsg(VoiceMsg voiceMsg,String xml) {
		return HandleMsgByType(WechatMsgType.VOICE,voiceMsg,xml);
	}
	
	/**
	 * 处理视频消息
	 *
	 * @author 鲜果
	 * @param @param videoMsg 视频消息
	 * @param @return
	 * @date 2019年4月8日
	 * @return String
	 * @throws
	 */
	public String HandleVideoMsg(VideoMsg videoMsg,String xml) {
		return HandleMsgByType(WechatMsgType.VIDEO,videoMsg,xml);
	}
	
	/**
	 * 处理短视频消息
	 *
	 * @author 鲜果
	 * @param @param shortvideoMsg 短视频消息
	 * @param @return
	 * @date 2019年4月8日
	 * @return String
	 * @throws
	 */
	public String HandleShortvideoMsg(ShortvideoMsg shortvideoMsg,String xml) {
		return HandleMsgByType(WechatMsgType.SHORTVIDEO,shortvideoMsg,xml);
	}
	
	/**
	 * 处理位置消息
	 *
	 * @author 鲜果
	 * @param @param locationMsg 位置消息
	 * @param @return
	 * @date 2019年4月8日
	 * @return String
	 * @throws
	 */
	public String HandleLocationMsg(LocationMsg locationMsg,String xml) {
		return HandleMsgByType(WechatMsgType.LOCATION,locationMsg,xml);
	}
	
	/**
	 * 处理链接消息
	 *
	 * @author 鲜果
	 * @param @param linkMsg 链接消息
	 * @param @return
	 * @date 2019年4月8日
	 * @return String 
	 * @throws
	 */
	public String HandleLinkMsg(LinkMsg linkMsg,String xml) {
		return HandleMsgByType(WechatMsgType.LINK,linkMsg,xml);
	}
	
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
	private String HandleMsgByType(WechatMsgType wechatMsgType,WechatMessage wechatMessage,String xml) {
		if(weChatMsgListenerList != null) {
			List<WeChatMsgListener> listeners = weChatMsgListenerList.get(wechatMsgType);
			if(listeners != null) {
				for(WeChatMsgListener listener : listeners) {
					listener.Listen(wechatMessage, xml);
				}
			}
		}
		if(weChatMsgInterceptorList != null) {
			List<WeChatMsgInterceptor> Interceptors = weChatMsgInterceptorList.get(wechatMsgType);
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
