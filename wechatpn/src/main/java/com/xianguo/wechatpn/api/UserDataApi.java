package com.xianguo.wechatpn.api;

import java.util.List;

import com.xianguo.wechatpn.WechatApiFull;
import com.xianguo.wechatpn.WechatApiPublicResponse;
import com.xianguo.wechatpn.api.UserDataApi.AccumulateUserApi.AccumulateUserRequest;
import com.xianguo.wechatpn.api.UserDataApi.AccumulateUserApi.AccumulateUserResponse;
import com.xianguo.wechatpn.api.UserDataApi.AccumulateUserChangeApi.AccumulateUserChangeRequest;
import com.xianguo.wechatpn.api.UserDataApi.AccumulateUserChangeApi.AccumulateUserChangeResponse;
import com.xianguo.wechatpn.enums.HttpRequestType;
import com.xianguo.wechatpn.utils.AccessToken;

import lombok.Data;
import lombok.EqualsAndHashCode;

public class UserDataApi {
	
	/**
	 * 获取累计用户数据
	 * @author 武昱坤
	 * @date 2019年4月10日
	 *
	 */
	public static class AccumulateUserApi extends WechatApiFull<AccumulateUserRequest, AccumulateUserResponse> {
		
		public AccumulateUserApi() {
			super(AccumulateUserResponse.class, "https://api.weixin.qq.com/datacube/getusercumulate?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}

		@Data
		public static class AccumulateUserRequest {
			private String begin_date;//	获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
			private String end_date;//	获取数据的结束日期，end_date允许设置的最大值为昨日
		}
		
		@Data
		@EqualsAndHashCode(callSuper=false)
		public static class AccumulateUserResponse extends WechatApiPublicResponse {
			private List<AccumulateInfo> list;
		}
		
		@Data
		public static class AccumulateInfo {
			private String ref_date;//	数据的日期
			private Integer cumulate_user;//总用户量
		}
	}
	
	
	/**
	 * 获取用户增减数据接口
	 * @author 武昱坤
	 * @date 2019年4月10日
	 *
	 */
	public static class AccumulateUserChangeApi extends WechatApiFull<AccumulateUserChangeRequest, AccumulateUserChangeResponse> {

		public AccumulateUserChangeApi() {
			super(AccumulateUserChangeResponse.class, "	https://api.weixin.qq.com/datacube/getusersummary?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
			// TODO Auto-generated constructor stub
		}
		
		@Data
		public static class AccumulateUserChangeRequest {
			private String begin_date;//	获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
			private String end_date;//	获取数据的结束日期，end_date允许设置的最大值为昨日
		}
		
		@Data
		@EqualsAndHashCode(callSuper=false)
		public static class AccumulateUserChangeResponse extends WechatApiPublicResponse {
			private List<AccumulateChangeInfo> list;
		}
		
		@Data
		public static class AccumulateChangeInfo {
			private String ref_date;//	数据的日期
			private String user_source;//用户的渠道，数值代表的含义如下： 0代表其他合计 1代表公众号搜索 17代表名片分享 30代表扫描二维码 43代表图文页右上角菜单 51代表支付后关注（在支付完成页） 57代表图文页内公众号名称 75代表公众号文章广告 78代表朋友圈广告
			private String new_user;//	新增的用户数量
			private String cancel_user;//	取消关注的用户数量，new_user减去cancel_user即为净增用户数量
		}
		
	}
	
}
