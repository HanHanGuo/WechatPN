package com.xianguo.wechatpn.event;

import java.util.List;

import com.xianguo.wechatpn.WechatEventMessage;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 弹出系统拍照发图的事件推送，弹出拍照或者相册发图的事件推送，弹出微信相册发图器的事件推送
 * @author 鲜果
 * @date 2019年4月10日
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class PicEvent extends WechatEventMessage {
	
	private SendPicsInfo SendPicsInfo;//	发送的图片信息
	
	@Data
	public static class SendPicsInfo {
		private Integer Count;//发送的图片数量
		private List<Item> PicList;//图片列表
	}
	
	@Data
	public static class Item {
		private String PicMd5Sum;//图片的MD5值，开发者若需要，可用于验证接收到图片
	}
	
}
