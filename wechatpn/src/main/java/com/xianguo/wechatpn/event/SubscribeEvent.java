package com.xianguo.wechatpn.event;

import com.xianguo.wechatpn.WechatEventMessage;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信关注 取消关注 事件
 * @author 鲜果
 * @date 2019年4月8日
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SubscribeEvent extends WechatEventMessage {
	private String EventKey;//事件KEY值，qrscene_为前缀，后面为二维码的参数值
	private String Ticket;//二维码的ticket，可用来换取二维码图片
}
