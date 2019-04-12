package com.xianguo.wechatpn.api;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.xianguo.wechatpn.WechatApiDefect;
import com.xianguo.wechatpn.WechatApiErrorMsg;
import com.xianguo.wechatpn.WechatApiFull;
import com.xianguo.wechatpn.WechatApiPublicResponse;
import com.xianguo.wechatpn.api.MenuApi.CreateMenuApi.CreateMenuRequest;
import com.xianguo.wechatpn.api.MenuApi.QueryMenuApi.CreateMenuResponse;
import com.xianguo.wechatpn.enums.HttpRequestType;
import com.xianguo.wechatpn.enums.WechatMenuType;
import com.xianguo.wechatpn.interfaces.ApiIsSuccess;
import com.xianguo.wechatpn.utils.AccessToken;
import com.xianguo.wechatpn.utils.FastJsonEnmuDecoder;

import lombok.Data;

/**
 * 微信菜单api
 * 
 * @author 鲜果
 * @date 2019年4月9日
 *
 */
public class MenuApi {
	
	/**
	 * 创建微信菜单
	 * @author 鲜果
	 * @date 2019年4月9日
	 *
	 */
	public static class CreateMenuApi extends WechatApiFull<CreateMenuRequest, WechatApiPublicResponse> {

		public CreateMenuApi() {
			super(WechatApiPublicResponse.class, "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}
		
		@Data
		public static class CreateMenuRequest {
			private List<Button> button;//菜单组
		}
		
		@Data
		public static class Button {
			@JSONField(serializeUsing = FastJsonEnmuDecoder.class, deserializeUsing = FastJsonEnmuDecoder.class)
			private WechatMenuType type;//菜单类型
			private String name;//菜单名称
			private String key;//菜单key
			private List<SubButton> sub_button;//子菜单
		}
		
		@Data
		public static class SubButton {
			@JSONField(serializeUsing = FastJsonEnmuDecoder.class, deserializeUsing = FastJsonEnmuDecoder.class)
			private WechatMenuType type;//菜单类型
			private String name;//菜单名称
			private String url;//菜单跳转地址
			private String key;//菜单key
		}
	
	}
	
	
	/**
	 * 查询微信菜单api
	 * @author 鲜果
	 * @date 2019年4月10日
	 *
	 */
	public static class QueryMenuApi extends WechatApiDefect<CreateMenuResponse> {
		
		public QueryMenuApi() {
			super(CreateMenuResponse.class, "https://api.weixin.qq.com/cgi-bin/menu/get?access_token="+AccessToken.getAccessToken(), HttpRequestType.GET);
		}

		public static class CreateMenuResponse extends CreateMenuRequest implements ApiIsSuccess{
			
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
		
	}
	
	/**
	 * 删除微信菜单api
	 * @author 鲜果
	 * @date 2019年4月10日
	 *
	 */
	public static class DeleteMenuApi extends WechatApiDefect<WechatApiPublicResponse> {

		public DeleteMenuApi() {
			super(WechatApiPublicResponse.class, "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="+AccessToken.getAccessToken(), HttpRequestType.GET);
		}
		
	}

}
