package com.xianguo.wechatpn;

import com.alibaba.fastjson.annotation.JSONField;
import com.xianguo.wechatpn.enums.WechatMsgType;
import com.xianguo.wechatpn.interfaces.XmlInterface;
import com.xianguo.wechatpn.utils.FastJsonEnmuDecoder;

import lombok.Data;

/**
 * 微信回复消息
 * @author 鲜果
 * @date 2019年4月9日
 *
 */
@Data
public class WechatReplyMsg implements XmlInterface {
	private String ToUserName;//开发者微信号
	private String FromUserName;//发送方帐号（一个OpenID）
	private String CreateTime;//消息创建时间 （整型）
	@JSONField(serializeUsing = FastJsonEnmuDecoder.class, deserializeUsing = FastJsonEnmuDecoder.class)
	private WechatMsgType MsgType;//消息类型
}
