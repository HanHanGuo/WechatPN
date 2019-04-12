package com.xianguo.wechatpn;

import java.util.HashMap;
import java.util.List;

import com.xianguo.wechatpn.enums.WechatEventType;
import com.xianguo.wechatpn.interfaces.WechatEventListener;

/**
 * 微信事件监听器
 * @author 鲜果
 * @date 2019年4月8日
 *
 */
public class WechatEventListenerList extends HashMap<WechatEventType, List<WechatEventListener>> {

	private static final long serialVersionUID = 1L;
	
}
