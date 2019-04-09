package com.xianguo.wechatpn.msg;

import com.thoughtworks.xstream.XStream;
import com.xianguo.wechatpn.WechatReplyMsg;
import com.xianguo.wechatpn.enums.WechatMsgType;
import com.xianguo.wechatpn.utils.XmlUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 回复文本消息
 * @author 鲜果
 * @date 2019年4月9日
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ReplyTextMsg extends WechatReplyMsg {
	
	private String Content;//内容
	
	public ReplyTextMsg() {
		super.setMsgType(WechatMsgType.TEXT);
	}
	
	@Override
	public String returnXml() {
		XStream xml = XmlUtils.GetXmlBean();
		xml.alias("xml", ReplyTextMsg.class);
		return xml.toXML(this);
	}
}
