package com.xianguo.wechatpn;

import com.xianguo.wechatpn.enums.WechatEventType;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信事件消息
 * @author 鲜果
 * @date 2019年4月8日
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class WechatEventMessage extends WechatMessage {
	private WechatEventType Event;//微信事件
	private String EventKey;//事件KEY值
}
