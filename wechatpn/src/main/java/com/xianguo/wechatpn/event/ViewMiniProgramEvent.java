package com.xianguo.wechatpn.event;

import com.xianguo.wechatpn.WechatEventMessage;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 点击菜单跳转小程序的事件推送
 * @author 鲜果
 * @date 2019年4月10日
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ViewMiniProgramEvent extends WechatEventMessage {
	private String MenuID;//菜单ID，如果是个性化菜单，则可以通过这个字段，知道是哪个规则的菜单被点击了
}
