package com.xianguo.wechatpn.api;

import java.util.List;

import com.xianguo.wechatpn.WechatApiFull;
import com.xianguo.wechatpn.WechatApiPublicResponse;
import com.xianguo.wechatpn.api.MenuApi.CreateMenuApi.CreateMenuRequest;
import com.xianguo.wechatpn.enums.HttpRequestType;
import com.xianguo.wechatpn.enums.WechatMenuType;
import com.xianguo.wechatpn.utils.AccessToken;
import com.xianguo.wechatpn.utils.WechatConstants;

import lombok.Data;

/**
 * 菜单api
 * 
 * @author 鲜果
 * @date 2019年4月9日
 *
 */
public class MenuApi {
	
	/**
	 * 创建菜单
	 * @author 鲜果
	 * @date 2019年4月9日
	 *
	 */
	public static class CreateMenuApi extends WechatApiFull<CreateMenuRequest, WechatApiPublicResponse> {

		public CreateMenuApi() {
			super(WechatApiPublicResponse.class, "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+AccessToken.Token, HttpRequestType.POST);
		}
		
		@Data
		public static class CreateMenuRequest {
			private List<Button> button;//菜单组
		}
		
		@Data
		public static class Button {
			private WechatMenuType type;//菜单类型
			private String name;//菜单名称
			private String key;//菜单key
			private List<SubButton> sub_button;//子菜单
		}
		
		@Data
		public static class SubButton {
			private WechatMenuType type;//菜单类型
			private String name;//菜单名称
			private String url;//菜单跳转地址
			private String key;//菜单key
		}
	}

}
