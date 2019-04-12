package com.xianguo.wechatpn.msg;

import com.thoughtworks.xstream.XStream;
import com.xianguo.wechatpn.WechatReplyMsg;
import com.xianguo.wechatpn.enums.WechatMsgType;
import com.xianguo.wechatpn.utils.XmlUtils;

/**
 * 转接客服消息
 * @author 鲜果
 * @date 2019年4月12日
 *
 */
public class ReplyCustomerMsg extends WechatReplyMsg {
	
	public ReplyCustomerMsg() {
		super.setMsgType(WechatMsgType.TRANSFER_CUSTOMER_SERVICE);
	}
	
	@Override
	public String returnXml() {
		XStream xml = XmlUtils.GetXmlBean();
		xml.alias("xml", WechatReplyMsg.class);
		return xml.toXML(this);
	}
}
