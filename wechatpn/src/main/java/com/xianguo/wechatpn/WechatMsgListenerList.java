package com.xianguo.wechatpn;

import java.util.HashMap;
import java.util.List;

import com.xianguo.wechatpn.enums.WechatMsgType;
import com.xianguo.wechatpn.interfaces.WeChatMsgListener;

/**
 * 监听器集合
 * @author 鲜果
 * @date 2019年4月8日
 *
 */
public class WechatMsgListenerList extends HashMap<WechatMsgType, List<WeChatMsgListener>> {

	private static final long serialVersionUID = 1L;

}
