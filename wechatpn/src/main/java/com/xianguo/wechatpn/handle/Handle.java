package com.xianguo.wechatpn.handle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thoughtworks.xstream.XStream;
import com.xianguo.wechatpn.WechatEventMessage;
import com.xianguo.wechatpn.WechatMessage;
import com.xianguo.wechatpn.event.LocationEvent;
import com.xianguo.wechatpn.event.MenuClickEvent;
import com.xianguo.wechatpn.event.SubscribeEvent;
import com.xianguo.wechatpn.msg.ImageMsg;
import com.xianguo.wechatpn.msg.LinkMsg;
import com.xianguo.wechatpn.msg.LocationMsg;
import com.xianguo.wechatpn.msg.ShortvideoMsg;
import com.xianguo.wechatpn.msg.TextMsg;
import com.xianguo.wechatpn.msg.VideoMsg;
import com.xianguo.wechatpn.msg.VoiceMsg;
import com.xianguo.wechatpn.utils.XmlUtils;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Handle {
	
	@Autowired
	public MsgHandle msgHandle;
	
	@Autowired
	public EventHandle eventHandle;
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
		XStream xStream = XmlUtils.GetXmlBean();
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
			xStream = XmlUtils.GetXmlBean();
			xStream.alias("xml", TextMsg.class);
			TextMsg textMsg = (TextMsg) xStream.fromXML(xml);
			return msgHandle.HandleTextMsg(textMsg, xml);
		case IMAGE:
			xStream = XmlUtils.GetXmlBean();
			xStream.alias("xml", ImageMsg.class);
			ImageMsg imageMsg = (ImageMsg) xStream.fromXML(xml);
			return msgHandle.HandleImageMsg(imageMsg, xml);
		case LINK:
			xStream = XmlUtils.GetXmlBean();
			xStream.alias("xml", LinkMsg.class);
			LinkMsg linkMsg = (LinkMsg) xStream.fromXML(xml);
			return msgHandle.HandleLinkMsg(linkMsg, xml);
		case LOCATION:
			xStream = XmlUtils.GetXmlBean();
			xStream.alias("xml", LocationMsg.class);
			LocationMsg locationMsg = (LocationMsg) xStream.fromXML(xml);
			return msgHandle.HandleLocationMsg(locationMsg, xml);
		case SHORTVIDEO:
			xStream = XmlUtils.GetXmlBean();
			xStream.alias("xml", ShortvideoMsg.class);
			ShortvideoMsg shortvideoMsg = (ShortvideoMsg) xStream.fromXML(xml);
			return msgHandle.HandleShortvideoMsg(shortvideoMsg, xml);
		case VIDEO:
			xStream = XmlUtils.GetXmlBean();
			xStream.alias("xml", VideoMsg.class);
			VideoMsg videoMsg = (VideoMsg) xStream.fromXML(xml);
			return msgHandle.HandleVideoMsg(videoMsg, xml);
		case VOICE:
			xStream = XmlUtils.GetXmlBean();
			xStream.alias("xml", VoiceMsg.class);
			VoiceMsg voiceMsg = (VoiceMsg) xStream.fromXML(xml);
			return msgHandle.HandleVoiceMsg(voiceMsg, xml);
		case EVENT://处理事件
			xStream = XmlUtils.GetXmlBean();
			xStream.alias("xml", WechatEventMessage.class);
			WechatEventMessage wechatEventMessage = (WechatEventMessage) xStream.fromXML(xml);
			switch(wechatEventMessage.getEvent()) {
				case SUBSCRIBE:
				case UNSUBSCRIBE:
				case SCAN:
					xStream = XmlUtils.GetXmlBean();
					xStream.alias("xml", SubscribeEvent.class);
					SubscribeEvent subscribeEvent = (SubscribeEvent) xStream.fromXML(xml);
					eventHandle.Handle(subscribeEvent, xml);
					break;
				case LOCATION:
					xStream = XmlUtils.GetXmlBean();
					xStream.alias("xml", LocationEvent.class);
					LocationEvent locationEvent = (LocationEvent) xStream.fromXML(xml);
					eventHandle.HandleLocationEvent(locationEvent, xml);
					break;
				case MENU_CLICK:
					xStream = XmlUtils.GetXmlBean();
					xStream.alias("xml", MenuClickEvent.class);
					MenuClickEvent menuClickEvent = (MenuClickEvent) xStream.fromXML(xml);
					eventHandle.HandleMenuClickEvent(menuClickEvent, xml);
					break;
			}
			return "";
		}
		return "";
	}

}
