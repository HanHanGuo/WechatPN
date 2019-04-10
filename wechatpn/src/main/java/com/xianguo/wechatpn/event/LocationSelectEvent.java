package com.xianguo.wechatpn.event;

import com.xianguo.wechatpn.WechatEventMessage;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 弹出地理位置选择器的事件推送
 * @author 鲜果
 * @date 2019年4月10日
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class LocationSelectEvent extends WechatEventMessage {
	
	private SendLocationInfo SendLocationInfo;//发送的位置信息
	
	@Data
	public static class SendLocationInfo {
		private String Location_X;//X坐标信息
		private String Location_Y;//Y坐标信息
		private String Scale;//精度，可理解为精度或者比例尺、越精细的话 scale越高
		private String Label;//地理位置的字符串信息
		private String Poiname;//朋友圈POI的名字，可能为空
	}
	
}
