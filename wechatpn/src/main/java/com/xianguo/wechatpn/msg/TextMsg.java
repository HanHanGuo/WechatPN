package com.xianguo.wechatpn.msg;

import com.xianguo.wechatpn.WechatMessage;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文本类型消息
 * @author 鲜果
 * @date 2019年4月8日
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class TextMsg extends WechatMessage {
	private String Content;//文本内容
}
