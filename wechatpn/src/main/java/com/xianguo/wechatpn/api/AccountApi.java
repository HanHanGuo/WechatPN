package com.xianguo.wechatpn.api;

import com.xianguo.wechatpn.WechatApiFull;
import com.xianguo.wechatpn.WechatApiPublicResponse;
import com.xianguo.wechatpn.api.AccountApi.CreateQrCodeApi.CreateQrCodeRequest;
import com.xianguo.wechatpn.api.AccountApi.CreateQrCodeApi.CreateQrCodeResponse;
import com.xianguo.wechatpn.api.AccountApi.ShortUrlApi.ShortUrlRequest;
import com.xianguo.wechatpn.api.AccountApi.ShortUrlApi.ShortUrlResponse;
import com.xianguo.wechatpn.enums.HttpRequestType;
import com.xianguo.wechatpn.utils.AccessToken;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * 账号管理api
 * @author 鲜果
 * @date 2019年4月10日
 *
 */
@Slf4j
public class AccountApi {
	
	/**
	 * 创建微信带参数二维码
	 * @author 鲜果
	 * @date 2019年4月10日
	 *
	 */
	public static class CreateQrCodeApi extends WechatApiFull<CreateQrCodeRequest, CreateQrCodeResponse> {
		
		public CreateQrCodeApi() {
			super(CreateQrCodeResponse.class, "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}

		@Data
		public static class CreateQrCodeRequest {
			private String action_name;//二维码类型，QR_SCENE为临时的整型参数值，QR_STR_SCENE为临时的字符串参数值，QR_LIMIT_SCENE为永久的整型参数值，QR_LIMIT_STR_SCENE为永久的字符串参数值
			private ActionInfo action_info;//二维码详细信息
			private String expire_seconds;//二维码有效时间
			
			public CreateQrCodeRequest() {
				try {
					action_info = ActionInfo.class.newInstance();
				} catch (Exception e) {
					log.info("二维码API ActionInfo对象自动构造失败");
					log.error(e.getMessage(),e);
				}
			}
		}
		
		@Data
		public static class ActionInfo {
			private Scene scene;//场景
			
			public ActionInfo() {
				try {
					scene = Scene.class.newInstance();
				} catch (Exception e) {
					log.info("二维码API Scene对象自动构造失败");
					log.error(e.getMessage(),e);
				}
			}
		}
		
		@Data
		public static class Scene {
			private String scene_id;//场景id（id和值任选一个，不能两个都传）
			private String scene_str;//场景值（id和值任选一个，不能两个都传）
		}
		
		@Data
		@EqualsAndHashCode(callSuper=false)
		public static class CreateQrCodeResponse extends WechatApiPublicResponse {
			private String ticket;//获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码。
			private Integer expire_seconds;//该二维码有效时间，以秒为单位。 最大不超过2592000（即30天）。
			private String url;//二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片
		}
	}
	
	/**
	 * 长链接转短链接接口
	 * @author 鲜果
	 * @date 2019年4月11日
	 *
	 */
	public static class ShortUrlApi extends WechatApiFull<ShortUrlRequest, ShortUrlResponse> {
		
		public ShortUrlApi() {
			super(ShortUrlResponse.class, "https://api.weixin.qq.com/cgi-bin/shorturl?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}


		@Data
		public static class ShortUrlRequest {
			private String action;
			private String long_url;
			
			public ShortUrlRequest() {
				this.action = "long2short";
			}
			
		}
		
		
		@Data
		@EqualsAndHashCode(callSuper=false)
		public static class ShortUrlResponse extends WechatApiPublicResponse {
			private String short_url;
		}
		
	}
	
}
