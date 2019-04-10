package com.xianguo.wechatpn.api;

import com.xianguo.wechatpn.WechatApiDefect;
import com.xianguo.wechatpn.WechatApiPublicResponse;
import com.xianguo.wechatpn.api.TokenApi.TokenResponse;
import com.xianguo.wechatpn.enums.HttpRequestType;
import com.xianguo.wechatpn.utils.WechatConstants;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取token
 * @author 鲜果
 * @date 2019年4月9日
 *
 */
public class TokenApi extends WechatApiDefect<TokenResponse> {

	public TokenApi() {
		super(TokenResponse.class, "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+WechatConstants.WX_APPID+"&secret="+WechatConstants.WX_SECRET, HttpRequestType.GET);
	}
	
	@Data
	@EqualsAndHashCode(callSuper=false)
	public static class TokenResponse extends WechatApiPublicResponse {
		
		private String access_token;//获取到的凭证
		private String expires_in;//凭证有效时间，单位：秒
		
	}
}