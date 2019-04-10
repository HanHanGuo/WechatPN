package com.xianguo.wechatpn.handle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thoughtworks.xstream.XStream;
import com.xianguo.wechatpn.WechatEventMessage;
import com.xianguo.wechatpn.WechatMessage;
import com.xianguo.wechatpn.event.ClickEvent;
import com.xianguo.wechatpn.event.LocationEvent;
import com.xianguo.wechatpn.event.LocationSelectEvent;
import com.xianguo.wechatpn.event.PicEvent;
import com.xianguo.wechatpn.event.PicEvent.Item;
import com.xianguo.wechatpn.event.ScancodeEvent;
import com.xianguo.wechatpn.event.SubscribeEvent;
import com.xianguo.wechatpn.event.ViewEvent;
import com.xianguo.wechatpn.event.ViewMiniProgramEvent;
import com.xianguo.wechatpn.msg.ImageMsg;
import com.xianguo.wechatpn.msg.LinkMsg;
import com.xianguo.wechatpn.msg.LocationMsg;
import com.xianguo.wechatpn.msg.ShortvideoMsg;
import com.xianguo.wechatpn.msg.TextMsg;
import com.xianguo.wechatpn.msg.VideoMsg;
import com.xianguo.wechatpn.msg.VoiceMsg;
import com.xianguo.wechatpn.utils.XmlUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 微信处理器,负责给消息处理器和事件处理器分发任务
 * @author 鲜果
 * @date 2019年4月10日
 *
 */
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
			return msgHandle.HandleMsg(textMsg, xml);
		case IMAGE:
			xStream = XmlUtils.GetXmlBean();
			xStream.alias("xml", ImageMsg.class);
			ImageMsg imageMsg = (ImageMsg) xStream.fromXML(xml);
			return msgHandle.HandleMsg(imageMsg, xml);
		case LINK:
			xStream = XmlUtils.GetXmlBean();
			xStream.alias("xml", LinkMsg.class);
			LinkMsg linkMsg = (LinkMsg) xStream.fromXML(xml);
			return msgHandle.HandleMsg(linkMsg, xml);
		case LOCATION:
			xStream = XmlUtils.GetXmlBean();
			xStream.alias("xml", LocationMsg.class);
			LocationMsg locationMsg = (LocationMsg) xStream.fromXML(xml);
			return msgHandle.HandleMsg(locationMsg, xml);
		case SHORTVIDEO:
			xStream = XmlUtils.GetXmlBean();
			xStream.alias("xml", ShortvideoMsg.class);
			ShortvideoMsg shortvideoMsg = (ShortvideoMsg) xStream.fromXML(xml);
			return msgHandle.HandleMsg(shortvideoMsg, xml);
		case VIDEO:
			xStream = XmlUtils.GetXmlBean();
			xStream.alias("xml", VideoMsg.class);
			VideoMsg videoMsg = (VideoMsg) xStream.fromXML(xml);
			return msgHandle.HandleMsg(videoMsg, xml);
		case VOICE:
			xStream = XmlUtils.GetXmlBean();
			xStream.alias("xml", VoiceMsg.class);
			VoiceMsg voiceMsg = (VoiceMsg) xStream.fromXML(xml);
			return msgHandle.HandleMsg(voiceMsg, xml);
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
					eventHandle.HandleEvent(subscribeEvent, xml);
					break;
				case LOCATION:
					xStream = XmlUtils.GetXmlBean();
					xStream.alias("xml", LocationEvent.class);
					LocationEvent locationEvent = (LocationEvent) xStream.fromXML(xml);
					eventHandle.HandleEvent(locationEvent, xml);
					break;
				case CLICK:
				case MENU_CLICK:
					xStream = XmlUtils.GetXmlBean();
					xStream.alias("xml", ClickEvent.class);
					ClickEvent clickEvent = (ClickEvent) xStream.fromXML(xml);
					eventHandle.HandleEvent(clickEvent, xml);
					break;
				case PIC_PHOTO_OR_ALBUM:
				case PIC_SYSPHOTO:
				case PIC_WEIXIN:
					xStream = XmlUtils.GetXmlBean();
					xStream.alias("xml", PicEvent.class);
					xStream.alias("item", Item.class);
					PicEvent picEvent = (PicEvent) xStream.fromXML(xml);
					eventHandle.HandleEvent(picEvent, xml);
					break;
				case LOCATION_SELECT:
					xStream = XmlUtils.GetXmlBean();
					xStream.alias("xml", LocationSelectEvent.class);
					LocationSelectEvent locationSelectEvent = (LocationSelectEvent) xStream.fromXML(xml);
					eventHandle.HandleEvent(locationSelectEvent, xml);
					break;
				case VIEW_MINIPROGRAM:
					xStream = XmlUtils.GetXmlBean();
					xStream.alias("xml", ViewMiniProgramEvent.class);
					ViewMiniProgramEvent viewMiniProgramEvent = (ViewMiniProgramEvent) xStream.fromXML(xml);
					eventHandle.HandleEvent(viewMiniProgramEvent, xml);
					break;
				case VIEW:
					xStream = XmlUtils.GetXmlBean();
					xStream.alias("xml", ViewEvent.class);
					ViewEvent viewEvent = (ViewEvent) xStream.fromXML(xml);
					eventHandle.HandleEvent(viewEvent, xml);
					break;
				case SCANCODE_PUSH:
				case SCANCODE_WAITMSG:
					xStream = XmlUtils.GetXmlBean();
					xStream.alias("xml", ScancodeEvent.class);
					ScancodeEvent scancodeEvent = (ScancodeEvent) xStream.fromXML(xml);
					eventHandle.HandleEvent(scancodeEvent, xml);
					break;
			}
			return "";
		default:
			return "";
		}
	}

}
