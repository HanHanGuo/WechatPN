package com.xianguo.wechatpn.container;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xianguo.wechatpn.beans.ImageMsg;
import com.xianguo.wechatpn.beans.LinkMsg;
import com.xianguo.wechatpn.beans.LocationMsg;
import com.xianguo.wechatpn.beans.ShortvideoMsg;
import com.xianguo.wechatpn.beans.TextMsg;
import com.xianguo.wechatpn.beans.VideoMsg;
import com.xianguo.wechatpn.beans.VoiceMsg;
import com.xianguo.wechatpn.beans.WeChatMsgInterceptorList;
import com.xianguo.wechatpn.beans.WeChatMsgListenerList;
import com.xianguo.wechatpn.beans.WechatMessage;
import com.xianguo.wechatpn.enums.WechatMsgType;
import com.xianguo.wechatpn.interfaces.WeChatMsgInterceptor;
import com.xianguo.wechatpn.interfaces.WeChatMsgListener;
import com.xianguo.wechatpn.utils.Constants;

/**
 * 消息监听容器
 * @author 鲜果
 * @date 2019年4月8日
 *
 */
public class MsgContainer {
	
	@Autowired(required=false)
	private WeChatMsgInterceptorList weChatMsgInterceptorList;
	
	@Autowired(required=false)
	private WeChatMsgListenerList weChatMsgListenerList;
	
	
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
		List<WeChatMsgListener> listeners = weChatMsgListenerList.get(WechatMsgType.TEXT);
		for(WeChatMsgListener listener : listeners) {
			listener.Listen(textMsg, xml);
		}
		List<WeChatMsgInterceptor> Interceptors = weChatMsgInterceptorList.get(WechatMsgType.TEXT);
		String nextStr = Constants.WX_MSG_NEXT;
		Iterator<WeChatMsgInterceptor> iterator = Interceptors.iterator();
		while((nextStr == null || nextStr.equals(Constants.WX_MSG_NEXT)) && iterator.hasNext()) {
			WeChatMsgInterceptor interceptor = iterator.next();
			nextStr = interceptor.Reply(textMsg, xml);
		}
		return "";
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
		return "";
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
		return "";
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
		return "";
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
		return "";
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
		return "";
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
		return "";
	}
	
}
