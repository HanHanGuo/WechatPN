package com.xianguo.wechatpn.api;

import java.util.List;

import com.xianguo.wechatpn.WechatApiFull;
import com.xianguo.wechatpn.WechatApiPublicResponse;
import com.xianguo.wechatpn.api.InterfaceDataApi.InterfaceSummaryApi.InterfaceSummaryRequest;
import com.xianguo.wechatpn.api.InterfaceDataApi.InterfaceSummaryApi.InterfaceSummaryResponse;
import com.xianguo.wechatpn.api.InterfaceDataApi.InterfaceSummaryHourApi.InterfaceSummaryHourRequest;
import com.xianguo.wechatpn.api.InterfaceDataApi.InterfaceSummaryHourApi.InterfaceSummaryHourResponse;
import com.xianguo.wechatpn.enums.HttpRequestType;
import com.xianguo.wechatpn.utils.AccessToken;

import lombok.Data;
import lombok.EqualsAndHashCode;

public class InterfaceDataApi {
	/**
	 * 获取接口分析数据
	 * 
	 * @author 武昱坤
	 * @date 2019年4月11日
	 *
	 */
	public static class InterfaceSummaryApi extends WechatApiFull<InterfaceSummaryRequest, InterfaceSummaryResponse> {
		public InterfaceSummaryApi() {
			super(InterfaceSummaryResponse.class, "https://api.weixin.qq.com/datacube/getinterfacesummary?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}
		@Data
		public static class InterfaceSummaryRequest {
			private String begin_date;//最大时间跨度30
			private String end_date;
		}
		@Data
		@EqualsAndHashCode(callSuper=false)
		public static class InterfaceSummaryResponse extends WechatApiPublicResponse{
			private List<InterfaceSummary> list;
		}
		@Data
		public static class InterfaceSummary {
			private String ref_date;
			private Integer callback_count;
			private Integer fail_count;
			private Integer total_time_cost;
			private Integer max_time_cost;
		}
	}
	/**
	 *	 获取接口分析数据
	 * @author 武昱坤
	 * @date 2019年4月11日
	 *
	 */
	public static class InterfaceSummaryHourApi extends WechatApiFull<InterfaceSummaryHourRequest, InterfaceSummaryHourResponse> {
		public InterfaceSummaryHourApi() {
			super(InterfaceSummaryHourResponse.class, "https://api.weixin.qq.com/datacube/getinterfacesummaryhour?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}
		@Data
		public static class InterfaceSummaryHourRequest{
			private String begin_date;
			private String end_date;
		}
		@Data
		@EqualsAndHashCode(callSuper=false)
		public static class InterfaceSummaryHourResponse extends WechatApiPublicResponse{
			private List<InterfaceSummaryHour> list;
		}
		@Data
		public static class InterfaceSummaryHour{
			private String ref_date;
			private Integer ref_hour;
			private Integer callback_count;
			private Integer fail_count;
			private Integer total_time_cost;
			private Integer max_time_cost;
		}
	}
	
}
