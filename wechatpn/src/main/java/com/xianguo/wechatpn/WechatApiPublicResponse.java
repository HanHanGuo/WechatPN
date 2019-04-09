package com.xianguo.wechatpn;

import com.xianguo.wechatpn.interfaces.ApiIsSuccess;

import lombok.Data;

@Data
public class WechatApiPublicResponse implements ApiIsSuccess {
	private String errcode;//错误码
	private String errmsg;//错误信息
	
	@Override
	public Boolean check() {
		if("0".equals(errcode) && "ok".equals(errmsg)) {
			return true;
		}
		return false;
	}
}
