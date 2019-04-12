package com.xianguo.wechatpn.api;

import com.xianguo.wechatpn.WechatApiFull;
import com.xianguo.wechatpn.WechatApiPublicResponse;
import com.xianguo.wechatpn.api.CustomerApi.BindingCustomerApi.BindingCustomerRequest;
import com.xianguo.wechatpn.api.CustomerApi.CreateCustomerApi.CreateCustomerRequest;
import com.xianguo.wechatpn.enums.HttpRequestType;
import com.xianguo.wechatpn.utils.AccessToken;

import lombok.Data;

/**
 * 客服api
 * @author 鲜果
 * @date 2019年4月12日
 *
 */
public class CustomerApi {
	
	/**
	 * 添加客服帐号
	 * @author 鲜果
	 * @date 2019年4月12日
	 *
	 */
	public static class CreateCustomerApi extends WechatApiFull<CreateCustomerRequest, WechatApiPublicResponse> {
		
		public CreateCustomerApi() {
			super(WechatApiPublicResponse.class, "https://api.weixin.qq.com/customservice/kfaccount/add?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}

		@Data
		public static class CreateCustomerRequest {
			private String kf_account;//完整客服帐号，格式为：帐号前缀@公众号微信号，帐号前缀最多10个字符，必须是英文、数字字符或者下划线，后缀为公众号微信号，长度不超过30个字符
			private String nickname;//客服昵称，最长16个字
		}
		
	}
	
	/**
	 * 绑定客服帐号
	 * @author 鲜果
	 * @date 2019年4月12日
	 *
	 */
	public static class BindingCustomerApi extends WechatApiFull<BindingCustomerRequest, WechatApiPublicResponse> {
		
		public BindingCustomerApi() {
			super(WechatApiPublicResponse.class, "https://api.weixin.qq.com/customservice/kfaccount/inviteworker?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}

		@Data
		public static class BindingCustomerRequest {
			private String kf_account;//完整客服帐号，格式为：帐号前缀@公众号微信号，帐号前缀最多10个字符，必须是英文、数字字符或者下划线，后缀为公众号微信号，长度不超过30个字符
			private String invite_wx;//接收绑定邀请的客服微信号
		}
		
	}
	
}
