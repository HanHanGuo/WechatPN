package com.xianguo.wechatpn.event;

import com.xianguo.wechatpn.WechatEventMessage;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 上报位置事件
 * @author 鲜果
 * @date 2019年4月8日
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class LocationEvent extends WechatEventMessage {
	private String Latitude;//地理位置纬度
	private String Longitude;//地理位置经度
	private String Precision;//地理位置精度
}
