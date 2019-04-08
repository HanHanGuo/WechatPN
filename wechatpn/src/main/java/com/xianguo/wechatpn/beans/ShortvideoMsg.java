package com.xianguo.wechatpn.beans;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 短视频消息
 * @author 鲜果
 * @date 2019年4月8日
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ShortvideoMsg extends WechatMessage {
	private String MediaId;//	视频消息媒体id，可以调用获取临时素材接口拉取数据。
	private String ThumbMediaId;//	视频消息缩略图的媒体id，可以调用获取临时素材接口拉取数据。
}
