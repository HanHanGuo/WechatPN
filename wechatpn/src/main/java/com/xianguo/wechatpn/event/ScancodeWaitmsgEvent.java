package com.xianguo.wechatpn.event;

import com.xianguo.wechatpn.WechatEventMessage;
import com.xianguo.wechatpn.event.ScancodePushEvent.ScanCodeInfo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 扫码推事件且弹出“消息接收中”提示框的事件推送
 * @author 鲜果
 * @date 2019年4月10日
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ScancodeWaitmsgEvent extends WechatEventMessage {
	private ScanCodeInfo ScanCodeInfo;//扫描信息
}
