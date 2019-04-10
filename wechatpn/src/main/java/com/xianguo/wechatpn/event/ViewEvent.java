package com.xianguo.wechatpn.event;

import com.xianguo.wechatpn.WechatEventMessage;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 点击菜单跳转链接时的事件推送
 * @author 鲜果
 * @date 2019年4月10日
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ViewEvent extends WechatEventMessage {
	private String MenuId;//指菜单ID，如果是个性化菜单，则可以通过这个字段，知道是哪个规则的菜单被点击了。
}
