package com.xianguo.wechatpn.api;

import java.util.List;

import com.xianguo.wechatpn.WechatApiFull;
import com.xianguo.wechatpn.WechatApiPublicResponse;
import com.xianguo.wechatpn.api.MessageDataApi.UpStreamMsgApi.UpStreamMsgRequest;
import com.xianguo.wechatpn.api.MessageDataApi.UpStreamMsgApi.UpStreamMsgResponse;
import com.xianguo.wechatpn.api.MessageDataApi.UpStreamMsgDistApi.UpStreamMsgDistRequest;
import com.xianguo.wechatpn.api.MessageDataApi.UpStreamMsgDistApi.UpStreamMsgDistResponse;
import com.xianguo.wechatpn.api.MessageDataApi.UpStreamMsgDistMonthApi.UpStreamMsgDistMonthRequest;
import com.xianguo.wechatpn.api.MessageDataApi.UpStreamMsgDistMonthApi.UpStreamMsgDistMonthResponse;
import com.xianguo.wechatpn.api.MessageDataApi.UpStreamMsgDistweekApi.UpStreamMsgDistweekRequest;
import com.xianguo.wechatpn.api.MessageDataApi.UpStreamMsgDistweekApi.UpStreamMsgDistweekResponse;
import com.xianguo.wechatpn.api.MessageDataApi.UpStreamMsgHourApi.UpStreamMsgHourRequest;
import com.xianguo.wechatpn.api.MessageDataApi.UpStreamMsgHourApi.UpStreamMsgHourResponse;
import com.xianguo.wechatpn.api.MessageDataApi.UpStreamMsgMonthApi.UpStreamMsgMonthRequest;
import com.xianguo.wechatpn.api.MessageDataApi.UpStreamMsgMonthApi.UpStreamMsgMonthResponse;
import com.xianguo.wechatpn.api.MessageDataApi.UpStreamMsgWeekApi.UpStreamMsgWeekRequest;
import com.xianguo.wechatpn.api.MessageDataApi.UpStreamMsgWeekApi.UpStreamMsgWeekResponse;
import com.xianguo.wechatpn.enums.HttpRequestType;
import com.xianguo.wechatpn.utils.AccessToken;

import lombok.Data;
import lombok.EqualsAndHashCode;
public class MessageDataApi {
	
	/**
	 * 获取消息发送概况数据
	 * @author 武昱坤
	 * @date 2019年4月10日
	 *
	 */
	public static class UpStreamMsgApi extends WechatApiFull<UpStreamMsgRequest, UpStreamMsgResponse>{
		public UpStreamMsgApi() {
			super(UpStreamMsgResponse.class, "https://api.weixin.qq.com/datacube/getupstreammsg?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}
		@Data
		public static class UpStreamMsgRequest{
			private String begin_date;//最大时间跨度7
			private String end_date;
		}
		@Data
		@EqualsAndHashCode(callSuper=false)
		public static class UpStreamMsgResponse extends WechatApiPublicResponse{
			private List<UpStreamMsg> list;
		}
		@Data
		public static class UpStreamMsg{
			private String ref_date;
			private String msg_type;
			private String msg_user;
			private String msg_count;
		} 
	}
	/**
	 * 获取消息分送分时数据
	 * @author 武昱坤
	 * @date 2019年4月10日
	 *
	 */
	public static class UpStreamMsgHourApi extends WechatApiFull<UpStreamMsgHourRequest, UpStreamMsgHourResponse>{
		
		public UpStreamMsgHourApi() {
			super(UpStreamMsgHourResponse.class, "https://api.weixin.qq.com/datacube/getupstreammsghour?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}
		@Data
		public static class UpStreamMsgHourRequest {
			private String begin_date;//最大时间跨度1
			private String end_date;
		}
		@Data
		@EqualsAndHashCode(callSuper=false)
		public static class UpStreamMsgHourResponse extends WechatApiPublicResponse{
			private List<UpStreamMsgHour> list;
		}
		@Data
		public static class UpStreamMsgHour{
			private String ref_date;
			private Integer ref_hour;
			private Integer msg_type;
			private Integer msg_user;
			private Integer msg_count;
		}
	}

	/**
	 * 获取消息发送周数据
	 * @author 武昱坤
	 * @date 2019年4月10日
	 *
	 */
	public static class UpStreamMsgWeekApi extends WechatApiFull<UpStreamMsgWeekRequest, UpStreamMsgWeekResponse>{
		public UpStreamMsgWeekApi() {
			super(UpStreamMsgWeekResponse.class, "https://api.weixin.qq.com/datacube/getupstreammsgweek?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}

		@Data
		public static class UpStreamMsgWeekRequest {
			private String begin_date;//最大时间跨度30
			private String end_date;
		}

		@Data
		@EqualsAndHashCode(callSuper=false)
		public static class UpStreamMsgWeekResponse extends WechatApiPublicResponse{
			private List<UpStreamMsgWeek> list;
		}

		@Data
		public static class UpStreamMsgWeek {
			private String ref_date;
			private Integer msg_type;
			private Integer msg_user;
			private Integer msg_count;
		}
	}
	/**
	 * 获取消息发送月数据
	 * @author 武昱坤
	 * @date 2019年4月10日
	 *
	 */
	public static class UpStreamMsgMonthApi extends WechatApiFull<UpStreamMsgMonthRequest, UpStreamMsgMonthResponse>{

		public UpStreamMsgMonthApi() {
			super(UpStreamMsgMonthResponse.class, "https://api.weixin.qq.com/datacube/getupstreammsgmonth?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}
		@Data
		public static class UpStreamMsgMonthRequest {
			private String begin_date;//最大时间跨度30
			private String end_date;
		}
		@Data
		@EqualsAndHashCode(callSuper=false)
		public static class UpStreamMsgMonthResponse extends WechatApiPublicResponse{
			private List<UpStreamMsgMonth> list;
		}
		@Data
		public static class UpStreamMsgMonth {
			private String ref_date;
			private Integer msg_type;
			private Integer msg_user;
			private Integer msg_count;
		}
	}
	/**
	 * 获取消息发送分布数据
	 * @author 武昱坤
	 * @date 2019年4月10日
	 *
	 */
	public static class UpStreamMsgDistApi extends WechatApiFull<UpStreamMsgDistRequest, UpStreamMsgDistResponse>{
		
		public UpStreamMsgDistApi() {
			super(UpStreamMsgDistResponse.class, "https://api.weixin.qq.com/datacube/getupstreammsgdist?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}
		@Data
		public static class UpStreamMsgDistRequest{
			private String begin_date;//最大时间跨度15
			private String end_date;
		}
		@Data
		@EqualsAndHashCode(callSuper=false)
		public static class UpStreamMsgDistResponse extends WechatApiPublicResponse{
			private List<UpStreamMsgDist> list;
		}
		@Data
		public static class UpStreamMsgDist{
			private String ref_date;
			private Integer count_interval;
			private Integer msg_user;
		}
	}
	/**
	 * 获取消息发送分布周数据
	 * @author 武昱坤
	 * @date 2019年4月11日
	 *
	 */
	public static class UpStreamMsgDistweekApi extends WechatApiFull<UpStreamMsgDistweekRequest, UpStreamMsgDistweekResponse>{
		public UpStreamMsgDistweekApi() {
			super(UpStreamMsgDistweekResponse.class, "https://api.weixin.qq.com/datacube/getupstreammsgdistweek?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}
		@Data
		public static class UpStreamMsgDistweekRequest{
			private String begin_date;//最大时间跨度30
			private String end_date;
		}
		@Data
		@EqualsAndHashCode(callSuper=false)
		public static class UpStreamMsgDistweekResponse extends WechatApiPublicResponse{
			private List<UpStreamMsgDistweek> list;
		}
		@Data
		public static class UpStreamMsgDistweek{
			private String ref_date;
			private Integer count_interval;
			private Integer msg_user;
		}
	}
	/**
	 *	 获取消息发送分布月数据
	 * @author 武昱坤
	 * @date 2019年4月11日
	 *
	 */
	public static class UpStreamMsgDistMonthApi extends WechatApiFull<UpStreamMsgDistMonthRequest, UpStreamMsgDistMonthResponse>{
		public UpStreamMsgDistMonthApi() {
			super(UpStreamMsgDistMonthResponse.class, "https://api.weixin.qq.com/datacube/getupstreammsgdistmonth?access_token="+AccessToken.getAccessToken(), HttpRequestType.POST);
		}
		@Data
		public static class UpStreamMsgDistMonthRequest{
			private String begin_date;//最大时间跨度30
			private String end_date;
		}
		@Data
		@EqualsAndHashCode(callSuper=false)
		public static class UpStreamMsgDistMonthResponse extends WechatApiPublicResponse{
			private List<UpStreamMsgDistMonth> list;
		}
		@Data
		public static class UpStreamMsgDistMonth{
			private String ref_date;
			private Integer count_interval;
			private Integer msg_user;
		}
	}
	
}
