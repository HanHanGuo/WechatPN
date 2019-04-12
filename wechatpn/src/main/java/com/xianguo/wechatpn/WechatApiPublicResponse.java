package com.xianguo.wechatpn;

import com.xianguo.wechatpn.interfaces.ApiIsSuccess;

import lombok.Data;

/**
 * 微信api公共返回对象
 * @author 鲜果
 * @date 2019年4月10日
 *
 */
@Data
public class WechatApiPublicResponse implements ApiIsSuccess {
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
	public String returnErrorMsgZHCN() {
		return WechatApiErrorMsg.getMsg(errcode);
	}
}
