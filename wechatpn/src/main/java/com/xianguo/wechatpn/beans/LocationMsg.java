package com.xianguo.wechatpn.beans;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 地理位置消息
 * @author 鲜果
 * @date 2019年4月8日
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class LocationMsg extends WechatMessage {
	private String Location_X;//	地理位置维度
	private String Location_Y;//	地理位置经度
	private String Scale;//	地图缩放大小
	private String Label;//	地理位置信息
}
