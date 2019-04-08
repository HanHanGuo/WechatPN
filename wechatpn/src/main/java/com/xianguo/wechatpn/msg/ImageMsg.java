package com.xianguo.wechatpn.msg;

import com.xianguo.wechatpn.WechatMessage;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 图片类型消息
 * @author 鲜果
 * @date 2019年4月8日
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ImageMsg extends WechatMessage {
	private String PicUrl;//	图片链接（由系统生成）
	private String MediaId;//	图片消息媒体id，可以调用获取临时素材接口拉取数据。
}
