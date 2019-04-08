package com.xianguo.wechatpn.beans;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 链接消息
 * @author 鲜果
 * @date 2019年4月8日
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class LinkMsg extends WechatMessage {
	private String Title;//	消息标题
	private String Description;//	消息描述
	private String Url;//	消息链接
}
