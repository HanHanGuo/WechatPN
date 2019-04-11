package com.xianguo.wechatpn;

import java.io.InputStream;

import com.xianguo.wechatpn.interfaces.ApiIsSuccess;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信api请求架构流，可用于下载图片以及文件
 * @author 鲜果
 * @date 2019年4月10日
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class WechatApiInputStream implements ApiIsSuccess {
	
	private String errcode;//错误码
	private String errmsg;//错误信息
	private InputStream inputStream;//文件下载流，接口调用成功后自行处理
	
	@Override
	public Boolean check() {
		if(("0".equals(errcode) && "ok".equals(errmsg)) || ("".equals(errcode) && "".equals(errmsg)) || (errcode == null && errmsg == null)) {
			return true;
		}
		return false;
	}

}
