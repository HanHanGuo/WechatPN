package com.xianguo.wechatpn.event;

import com.xianguo.wechatpn.WechatEventMessage;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 扫码推事件的事件推送以及，扫码推事件且弹出“消息接收中”提示框的事件推送
 * @author 鲜果
 * @date 2019年4月10日
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ScancodeEvent extends WechatEventMessage {
	private ScanCodeInfo ScanCodeInfo;//扫描信息
	
	@Data
	public static class ScanCodeInfo {
		private String ScanType;//	扫描类型，一般是qrcode
		private String ScanResult;//扫描结果，即二维码对应的字符串信息
	}
}
