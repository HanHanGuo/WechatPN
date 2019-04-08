package com.xianguo.wechatpn.beans;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 语音消息
 * @author 鲜果
 * @date 2019年4月8日
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class VoiceMsg {
	private String MediaId;//语音消息媒体id，可以调用获取临时素材接口拉取数据。
	private String Format;//语音格式，如amr，speex等
}
