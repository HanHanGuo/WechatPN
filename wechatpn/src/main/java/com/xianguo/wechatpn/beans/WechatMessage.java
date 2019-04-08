package com.xianguo.wechatpn.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.xianguo.wechatpn.enums.WechatMsgType;

import lombok.Data;

/**
 * 微信消息
 * @author 鲜果
 * @date 2019年4月8日
 *
 */
@Data
@XStreamAlias("xml")
public class WechatMessage {
	private String ToUserName;//开发者微信号
	private String FromUserName;//发送方帐号（一个OpenID）
	private String CreateTime;//消息创建时间 （整型）
	private WechatMsgType MsgType;//消息类型
	private String MsgId;//消息id，64位整型
}
