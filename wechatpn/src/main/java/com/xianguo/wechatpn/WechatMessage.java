package com.xianguo.wechatpn;

import java.util.Date;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.xianguo.wechatpn.enums.WechatMsgType;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 微信消息
 * @author 鲜果
 * @date 2019年4月8日
 *
 */
@Data
@Slf4j
@XStreamAlias("xml")
public class WechatMessage {
	private String ToUserName;//开发者微信号
	private String FromUserName;//发送方帐号（一个OpenID）
	private String CreateTime;//消息创建时间 （整型）
	private WechatMsgType MsgType;//消息类型
	private String MsgId;//消息id，64位整型
	
	@SuppressWarnings("unchecked")
	public <T extends WechatReplyMsg> T Reply(Class<T> classes){
		try {
			WechatReplyMsg obj = classes.newInstance();
			obj.setToUserName(FromUserName);
			obj.setFromUserName(ToUserName);
			obj.setCreateTime(String.valueOf(new Date().getTime()/1000));
			return (T) obj;
		} catch (Exception e) {
			log.info("消息回复对象构造失败");
			log.error(e.getMessage(),e);
			return null;
		}
	}
	
}
