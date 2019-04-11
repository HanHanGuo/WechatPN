package com.xianguo.wechatpn;

import java.io.InputStream;

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
	private String inputName;
	private InputStream InputStream;//文件流
	private String title;//视频素材的标题
	private String introduction;//视频素材的描述
	
	public WechatApiUploadStream() {
		inputName = "media";
	}

}
