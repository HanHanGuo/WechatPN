package com.xianguo.wechatpn.handle;

import org.springframework.stereotype.Component;

import com.thoughtworks.xstream.XStream;
import com.xianguo.wechatpn.beans.TextMsg;
import com.xianguo.wechatpn.beans.WechatMessage;
import com.xianguo.wechatpn.utils.XmlUtils;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MsgHandle {

	/**
	 * 消息处理头，分发任务
	 *
	 * @author 鲜果
	 * @param @param xml
	 * @param @return
	 * @date 2019年4月8日
	 * @return String
	 * @throws
	 */
	public String HandleHead(String xml) {
		XStream xStream = XmlUtils.GetXmlToBean();
		xStream.alias("xml", WechatMessage.class);
		WechatMessage wechatMessage = (WechatMessage) xStream.fromXML(xml);
		if (wechatMessage == null) {
			log.info("无法处理的xml");
			return "";
		}
		if (wechatMessage.getMsgType() == null) {
			log.info("无法处理的消息类型");
			return "";
		}

		switch (wechatMessage.getMsgType()) {
		case TEXT:
			xStream = XmlUtils.GetXmlToBean();
			xStream.alias("xml", TextMsg.class);
			TextMsg textMsg = (TextMsg) xStream.fromXML(xml);
			return "";
		case IMAGE:

			break;
		case LINK:

			break;
		case LOCATION:

			break;
		case SHORTVIDEO:

			break;
		case VIDEO:

			break;
		case VOICE:

			break;
		}
		return "";
	}

}
