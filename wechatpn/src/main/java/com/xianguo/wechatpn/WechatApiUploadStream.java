package com.xianguo.wechatpn;

import java.io.InputStream;

import com.alibaba.fastjson.annotation.JSONField;
import com.xianguo.wechatpn.enums.WechatMediaType;
import com.xianguo.wechatpn.utils.FastJsonEnmuDecoder;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信素材文件上传流
 * @author 鲜果
 * @date 2019年4月11日
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class WechatApiUploadStream {

	private String fileName;//文件名称
	private InputStream InputStream;//文件流
	@JSONField(serializeUsing = FastJsonEnmuDecoder.class, deserializeUsing = FastJsonEnmuDecoder.class)
	private WechatMediaType type;//媒体类型
	
	private String title;//视频素材的标题 type为视频的时候必填
	private String introduction;//视频素材的描述 type为视频的时候必填

}
