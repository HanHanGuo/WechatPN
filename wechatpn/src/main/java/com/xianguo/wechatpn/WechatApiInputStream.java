package com.xianguo.wechatpn;

import java.io.IOException;
import java.io.InputStream;

import com.xianguo.wechatpn.interfaces.ApiIsSuccess;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class WechatApiInputStream extends InputStream implements ApiIsSuccess {
	
	private String errcode;//错误码
	private String errmsg;//错误信息
	
	@Override
	public Boolean check() {
		if(("0".equals(errcode) && "ok".equals(errmsg)) || ("".equals(errcode) && "".equals(errmsg)) || (errcode == null && errmsg == null)) {
			return true;
		}
		return false;
	}
	
	@Override
	public int read() throws IOException {
		return 0;
	}

}
